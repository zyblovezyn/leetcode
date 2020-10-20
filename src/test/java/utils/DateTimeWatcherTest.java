package utils;

import org.junit.jupiter.api.Test;

class DateTimeWatcherTest {

    @Test
    void start() {
    }

    @Test
    void stop() {
    }

    @Test
    void toMillis() throws InterruptedException {
        WatcherTime.start();
        Thread.sleep(1000L);
        WatcherTime.stop();

        System.out.println(WatcherTime.toMillis());
        System.out.println(WatcherTime.toSeconds());
        System.out.println(WatcherTime.toMinutes());
    }

    @Test
    void toSeconds() throws InterruptedException {
        WatcherTime.start();
        Thread.sleep(3000L);
        WatcherTime.stop();

        System.out.println(WatcherTime.toMillis());
        System.out.println(WatcherTime.toSeconds());
        System.out.println(WatcherTime.toMinutes());
    }

    @Test
    void toMinutes() throws InterruptedException {
        WatcherTime.start();
        Thread.sleep(4000L);
        WatcherTime.stop();

        System.out.println(WatcherTime.toMillis());
        System.out.println(WatcherTime.toSeconds());
        System.out.println(WatcherTime.toMinutes());
    }

    @Test
    void testToString() {
    }
}