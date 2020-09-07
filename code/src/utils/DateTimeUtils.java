package utils;

public class DateTimeUtils {

    public static long translateToMillis(long currentTimeMillis) {
        return currentTimeMillis;
    }

    public static long translateToSeconds(long currentTimeMillis) {
        return currentTimeMillis / 1000;
    }

    public static long translateToMinutes(long currentTimeMillis) {
        return currentTimeMillis / 1000 / 60;
    }

    public static long translateToHours(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60);
    }

    public long translateToDays(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60 * 24);
    }
}
