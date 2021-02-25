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
package jdk.incubator.jpackage.internal;

import java.io.IOException;
import java.util.function.Consumer;

public final class RetryExecutor {
    RetryExecutor() {
        setMaxAttemptsCount(5);
        setAttemptTimeoutMillis(2 * 1000);
    }

    RetryExecutor setMaxAttemptsCount(int v) {
        attempts = v;
        return this;
    }

    RetryExecutor setAttemptTimeoutMillis(int v) {
        timeoutMillis = v;
        return this;
    }

    RetryExecutor setExecutorInitializer(Consumer<Executor> v) {
        executorInitializer = v;
        return this;
    }

    void abort() {
        aborted = true;
    }

    static RetryExecutor retryOnKnownErrorMessage(String v) {
        RetryExecutor result = new RetryExecutor();
        return result.setExecutorInitializer(exec -> {
            exec.setOutputConsumer(output -> {
                if (!output.anyMatch(v::equals)) {
                    result.abort();
                }
            });
        });
    }

    void execute(String cmdline[]) throws IOException {
        aborted = false;
        for (;;) {
            try {
                Executor exec = Executor.of(cmdline);
                if (executorInitializer != null) {
                    executorInitializer.accept(exec);
                }
                exec.executeExpectSuccess();
                break;
            } catch (IOException ex) {
                if (aborted || (--attempts) <= 0) {
                    throw ex;
                }
            }

            try {
                Thread.sleep(timeoutMillis);
            } catch (InterruptedException ex) {
                Log.verbose(ex);
                throw new RuntimeException(ex);
            }
        }
    }

    private Consumer<Executor> executorInitializer;
    private boolean aborted;
    private int attempts;
    private int timeoutMillis;
}
