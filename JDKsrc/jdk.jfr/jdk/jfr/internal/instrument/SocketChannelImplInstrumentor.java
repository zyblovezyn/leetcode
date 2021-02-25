/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal.instrument;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import jdk.jfr.events.Handlers;
import jdk.jfr.internal.handlers.EventHandler;

/**
 * See {@link JITracer} for an explanation of this code.
 */
@JIInstrumentationTarget("sun.nio.ch.SocketChannelImpl")
final class SocketChannelImplInstrumentor {

    private SocketChannelImplInstrumentor() {
    }

    private InetSocketAddress remoteAddress;

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public int read(ByteBuffer dst) throws IOException {
        EventHandler handler = Handlers.SOCKET_READ;
        if (!handler.isEnabled()) {
            return read(dst);
        }
        int bytesRead = 0;
        long start  = 0;
        try {
            start = EventHandler.timestamp();;
            bytesRead = read(dst);
        } finally {
            long duration = EventHandler.timestamp() - start;
            if (handler.shouldCommit(duration))  {
                String hostString  = remoteAddress.getAddress().toString();
                int delimiterIndex = hostString.lastIndexOf('/');

                String host = hostString.substring(0, delimiterIndex);
                String address = hostString.substring(delimiterIndex + 1);
                int port = remoteAddress.getPort();
                if (bytesRead < 0) {
                    handler.write(start, duration, host, address, port, 0, 0L, true);
                } else {
                    handler.write(start, duration, host, address, port, 0, bytesRead, false);
                }
            }
        }
        return bytesRead;
    }

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        EventHandler handler = Handlers.SOCKET_READ;
        if (!handler.isEnabled()) {
            return read(dsts, offset, length);
        }

        long bytesRead = 0;
        long start = 0;
        try {
            start = EventHandler.timestamp();
            bytesRead = read(dsts, offset, length);
        } finally {
            long duration = EventHandler.timestamp() - start;
            if (handler.shouldCommit(duration)) {
                String hostString  = remoteAddress.getAddress().toString();
                int delimiterIndex = hostString.lastIndexOf('/');

                String host = hostString.substring(0, delimiterIndex);
                String address = hostString.substring(delimiterIndex + 1);
                int port = remoteAddress.getPort();
                if (bytesRead < 0) {
                    handler.write(start, duration, host, address, port, 0, 0L, true);
                } else {
                    handler.write(start, duration, host, address, port, 0, bytesRead, false);
                }
            }
        }
        return bytesRead;
    }

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public int write(ByteBuffer buf) throws IOException {
        EventHandler handler = Handlers.SOCKET_WRITE;
        if (!handler.isEnabled()) {
            return write(buf);
        }
        int bytesWritten = 0;
        long start = 0;
        try {
            start = EventHandler.timestamp();
            bytesWritten = write(buf);
        } finally {
            long duration = EventHandler.timestamp() - start;
            if (handler.shouldCommit(duration)) {
                String hostString  = remoteAddress.getAddress().toString();
                int delimiterIndex = hostString.lastIndexOf('/');

                String host = hostString.substring(0, delimiterIndex);
                String address = hostString.substring(delimiterIndex + 1);
                int port = remoteAddress.getPort();
                long bytes = bytesWritten < 0 ? 0 : bytesWritten;
                handler.write(start, duration, host, address, port, bytes);
            }
        }
        return bytesWritten;
    }

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        EventHandler handler = Handlers.SOCKET_WRITE;
        if (!handler.isEnabled()) {
            return write(srcs, offset, length);
        }
        long bytesWritten = 0;
        long start = 0;
        try {
            start = EventHandler.timestamp();
            bytesWritten = write(srcs, offset, length);
        } finally {
            long duration = EventHandler.timestamp() - start;
            if (handler.shouldCommit(duration)) {
                String hostString  = remoteAddress.getAddress().toString();
                int delimiterIndex = hostString.lastIndexOf('/');

                String host = hostString.substring(0, delimiterIndex);
                String address = hostString.substring(delimiterIndex + 1);
                int port = remoteAddress.getPort();
                long bytes = bytesWritten < 0 ? 0 : bytesWritten;
                handler.write(start, duration, host, address, port, bytes);
            }
        }
        return bytesWritten;
    }
}
