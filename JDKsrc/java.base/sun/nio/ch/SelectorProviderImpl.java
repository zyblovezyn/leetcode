/*
 * Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.
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

package sun.nio.ch;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

public abstract class SelectorProviderImpl
    extends SelectorProvider
{
    @Override
    public DatagramChannel openDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this, /*interruptible*/true);
    }

    /**
     * SelectorProviderImpl specific method to create a DatagramChannel that
     * is not interruptible.
     */
    public DatagramChannel openUninterruptibleDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this, /*interruptible*/false);
    }

    @Override
    public DatagramChannel openDatagramChannel(ProtocolFamily family) throws IOException {
        return new DatagramChannelImpl(this, family, /*interruptible*/true);
    }

    @Override
    public Pipe openPipe() throws IOException {
        return new PipeImpl(this);
    }

    @Override
    public abstract AbstractSelector openSelector() throws IOException;

    @Override
    public ServerSocketChannel openServerSocketChannel() throws IOException {
        return new ServerSocketChannelImpl(this);
    }

    @Override
    public SocketChannel openSocketChannel() throws IOException {
        return new SocketChannelImpl(this);
    }

    @Override
    public SocketChannel openSocketChannel(ProtocolFamily family) throws IOException {
        return new SocketChannelImpl(this, family);
    }

    @Override
    public ServerSocketChannel openServerSocketChannel(ProtocolFamily family) {
        return new ServerSocketChannelImpl(this, family);
    }
}
