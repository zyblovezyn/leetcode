/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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

package sun.security.util;

/**
 * This class implements an event model with services for reporter and listener.
 * Reporter uses report() method to generate an event.
 * Listener uses setReportListener() to register for listening to an event,
 * and uses clearReportListener() to unregister a listening session.
 * Listener should implement the event handling of the Reporter interface.
 */
public final class Event {
    private Event() {}

    public interface Reporter {
        public void handle(String type, Object... args);
    }

    private static Reporter reporter;
    public static void setReportListener(Reporter re) {
        reporter = re;
    }

    public static void clearReportListener() {
        reporter = null;
    }

    public static void report(String type, Object... args) {
        Reporter currentReporter = reporter;

        if (currentReporter != null) {
            currentReporter.handle(type, args);
        }
    }
}
