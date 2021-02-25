/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

// Protected by modular boundaries.
public abstract class FileAccess {
    public final static FileAccess UNPRIVILEGED = new UnPrivileged();

    public abstract RandomAccessFile openRAF(File f, String mode) throws IOException;

    public abstract DirectoryStream<Path> newDirectoryStream(Path repository) throws IOException;

    public abstract String getAbsolutePath(File f) throws IOException;

    public abstract long length(File f) throws IOException;

    public abstract long fileSize(Path p) throws IOException;

    private static class UnPrivileged extends FileAccess {
        @Override
        public RandomAccessFile openRAF(File f, String mode) throws IOException {
            return new RandomAccessFile(f, mode);
        }

        @Override
        public DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
            return Files.newDirectoryStream(dir);
        }

        @Override
        public String getAbsolutePath(File f) throws IOException {
            return f.getAbsolutePath();
        }

        @Override
        public long length(File f) throws IOException {
            return f.length();
        }

        @Override
        public long fileSize(Path p) throws IOException {
            return Files.size(p);
        }
    }
}
