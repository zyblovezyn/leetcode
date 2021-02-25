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

package jdk.jfr.events;
import jdk.jfr.internal.handlers.EventHandler;
import jdk.jfr.internal.Utils;

public final class Handlers {
    public final static EventHandler SOCKET_READ = Utils.getHandler(SocketReadEvent.class);
    public final static EventHandler SOCKET_WRITE = Utils.getHandler(SocketWriteEvent.class);
    public final static EventHandler FILE_READ = Utils.getHandler(FileReadEvent.class);
    public final static EventHandler FILE_WRITE = Utils.getHandler(FileWriteEvent.class);
    public final static EventHandler FILE_FORCE = Utils.getHandler(FileForceEvent.class);
    public final static EventHandler ERROR_THROWN = Utils.getHandler(ErrorThrownEvent.class);
    public final static EventHandler EXCEPTION_THROWN = Utils.getHandler(ExceptionThrownEvent.class);
}
