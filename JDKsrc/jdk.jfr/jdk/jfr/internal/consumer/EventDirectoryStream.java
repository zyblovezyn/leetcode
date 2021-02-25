/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.jfr.internal.consumer;

import java.io.IOException;
import java.nio.file.Path;
import java.security.AccessControlContext;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.internal.JVM;
import jdk.jfr.internal.PlatformRecording;
import jdk.jfr.internal.Utils;
import jdk.jfr.internal.consumer.ChunkParser.ParserConfiguration;

/**
 * Implementation of an {@code EventStream}} that operates against a directory
 * with chunk files.
 *
 */
public class EventDirectoryStream extends AbstractEventStream {

    private final static Comparator<? super RecordedEvent> EVENT_COMPARATOR = JdkJfrConsumer.instance().eventComparator();

    private final RepositoryFiles repositoryFiles;
    private final PlatformRecording recording;
    private final FileAccess fileAccess;

    private ChunkParser currentParser;
    private long currentChunkStartNanos;
    private RecordedEvent[] sortedCache;
    private int threadExclusionLevel = 0;

    public EventDirectoryStream(AccessControlContext acc, Path p, FileAccess fileAccess, PlatformRecording recording) throws IOException {
        super(acc, recording);
        this.fileAccess = Objects.requireNonNull(fileAccess);
        this.recording = recording;
        this.repositoryFiles = new RepositoryFiles(fileAccess, p);
    }

    @Override
    public void close() {
        setClosed(true);
        dispatcher().runCloseActions();
        repositoryFiles.close();
        if (currentParser != null) {
            currentParser.close();
        }
    }

    @Override
    public void start() {
        start(Utils.timeToNanos(Instant.now()));
    }

    @Override
    public void startAsync() {
        startAsync(Utils.timeToNanos(Instant.now()));
    }

    @Override
    protected void process() throws IOException {
        JVM jvm = JVM.getJVM();
        Thread t = Thread.currentThread();
        try {
            if (jvm.isExcluded(t)) {
                threadExclusionLevel++;
            } else {
                jvm.exclude(t);
            }
            processRecursionSafe();
        } finally {
            if (threadExclusionLevel > 0) {
                threadExclusionLevel--;
            } else {
                jvm.include(t);
            }
        }
    }

    protected void processRecursionSafe() throws IOException {
        Dispatcher lastDisp = null;
        Dispatcher disp = dispatcher();
        Path path;
        boolean validStartTime = recording != null || disp.startTime != null;
        if (validStartTime) {
            path = repositoryFiles.firstPath(disp.startNanos);
        } else {
            path = repositoryFiles.lastPath();
        }
        if (path == null) { // closed
            return;
        }
        currentChunkStartNanos = repositoryFiles.getTimestamp(path);
        try (RecordingInput input = new RecordingInput(path.toFile(), fileAccess)) {
            currentParser = new ChunkParser(input, disp.parserConfiguration);
            long segmentStart = currentParser.getStartNanos() + currentParser.getChunkDuration();
            long filterStart = validStartTime ? disp.startNanos : segmentStart;
            long filterEnd = disp.endTime != null ? disp.endNanos: Long.MAX_VALUE;

            while (!isClosed()) {
                while (!isClosed() && !currentParser.isChunkFinished()) {
                    disp = dispatcher();
                    if (disp != lastDisp) {
                        ParserConfiguration pc = disp.parserConfiguration;
                        pc.filterStart = filterStart;
                        pc.filterEnd = filterEnd;
                        currentParser.updateConfiguration(pc, true);
                        currentParser.setFlushOperation(getFlushOperation());
                        lastDisp = disp;
                    }
                    if (disp.parserConfiguration.isOrdered()) {
                        processOrdered(disp);
                    } else {
                        processUnordered(disp);
                    }
                    if (currentParser.getStartNanos() + currentParser.getChunkDuration() > filterEnd) {
                        close();
                        return;
                    }
                }
                if (isLastChunk()) {
                    // Recording was stopped/closed externally, and no more data to process.
                    return;
                }

                if (repositoryFiles.hasFixedPath() && currentParser.isFinalChunk()) {
                    // JVM process exited/crashed, or repository migrated to an unknown location
                    return;
                }
                if (isClosed()) {
                    // Stream was closed
                    return;
                }
                long durationNanos = currentParser.getChunkDuration();
                if (durationNanos == 0) {
                    // Avoid reading the same chunk again and again if
                    // duration is 0 ns
                    durationNanos++;
                }
                path = repositoryFiles.nextPath(currentChunkStartNanos + durationNanos);
                if (path == null) {
                    return; // stream closed
                }
                currentChunkStartNanos = repositoryFiles.getTimestamp(path);
                input.setFile(path);
                currentParser = currentParser.newChunkParser();
                // TODO: Optimization. No need filter when we reach new chunk
                // Could set start = 0;
            }
        }
    }

    private boolean isLastChunk() {
        if (recording == null) {
            return false;
        }
        return recording.getFinalChunkStartNanos() >= currentParser.getStartNanos();
    }

    private void processOrdered(Dispatcher c) throws IOException {
        if (sortedCache == null) {
            sortedCache = new RecordedEvent[100_000];
        }
        int index = 0;
        while (true) {
            RecordedEvent e = currentParser.readStreamingEvent();
            if (e == null) {
                break;
            }
            if (index == sortedCache.length) {
                sortedCache = Arrays.copyOf(sortedCache, sortedCache.length * 2);
            }
            sortedCache[index++] = e;
        }
        // no events found
        if (index == 0 && currentParser.isChunkFinished()) {
            return;
        }
        // at least 2 events, sort them
        if (index > 1) {
            Arrays.sort(sortedCache, 0, index, EVENT_COMPARATOR);
        }
        for (int i = 0; i < index; i++) {
            c.dispatch(sortedCache[i]);
        }
        return;
    }

    private boolean processUnordered(Dispatcher c) throws IOException {
        while (true) {
            RecordedEvent e = currentParser.readStreamingEvent();
            if (e == null) {
                return true;
            } else {
                c.dispatch(e);
            }
        }
    }

}
