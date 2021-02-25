/*
 * Copyright (c) 2011, 2019, Oracle and/or its affiliates. All rights reserved.
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

package jdk.incubator.jpackage.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Log
 *
 * General purpose logging mechanism.
 */
public class Log {
    public static class Logger {
        private boolean verbose = false;
        private PrintWriter out = null;
        private PrintWriter err = null;

        // verbose defaults to true unless environment variable JPACKAGE_DEBUG
        // is set to true.
        // Then it is only set to true by using --verbose jpackage option

        public Logger() {
            verbose = ("true".equals(System.getenv("JPACKAGE_DEBUG")));
        }

        public void setVerbose() {
            verbose = true;
        }

        public boolean isVerbose() {
            return verbose;
        }

        public void setPrintWriter(PrintWriter out, PrintWriter err) {
            this.out = out;
            this.err = err;
        }

        public void flush() {
            if (out != null) {
                out.flush();
            }

            if (err != null) {
                err.flush();
            }
        }

        public void info(String msg) {
            if (out != null) {
                out.println(msg);
            } else {
                System.out.println(msg);
            }
        }

        public void error(String msg) {
            if (err != null) {
                err.println(msg);
            } else {
                System.err.println(msg);
            }
        }

        public void verbose(Throwable t) {
            if (out != null && verbose) {
                t.printStackTrace(out);
            } else if (verbose) {
                t.printStackTrace(System.out);
            }
        }

        public void verbose(String msg) {
            if (out != null && verbose) {
                out.println(msg);
            } else if (verbose) {
                System.out.println(msg);
            }
        }
    }

    private static Logger delegate = null;

    public static void setLogger(Logger logger) {
        delegate = (logger != null) ? logger : new Logger();
    }

    public static void flush() {
        if (delegate != null) {
            delegate.flush();
        }
    }

    public static void info(String msg) {
        if (delegate != null) {
           delegate.info(msg);
        }
    }

    public static void error(String msg) {
        if (delegate != null) {
            delegate.error(msg);
        }
    }

    public static void setVerbose() {
        if (delegate != null) {
            delegate.setVerbose();
        }
    }

    public static boolean isVerbose() {
        return (delegate != null) ? delegate.isVerbose() : false;
    }

    public static void verbose(String msg) {
        if (delegate != null) {
           delegate.verbose(msg);
        }
    }

    public static void verbose(Throwable t) {
        if (delegate != null) {
           delegate.verbose(t);
        }
    }
}
