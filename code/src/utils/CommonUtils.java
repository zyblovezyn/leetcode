package utils;

public class CommonUtils {
    private static long CURRENTTIMEMILLIS;

    public static boolean isIP(String IPStr) {
        boolean isIP = false;
        return isIP;
    }

    public static void startTime() {
        CURRENTTIMEMILLIS = System.currentTimeMillis();
    }

    public static void endTime() {
        long millis = System.currentTimeMillis() - CURRENTTIMEMILLIS;
        long seconds = DateTimeUtils.translateToSeconds(millis);
        System.out.println("Millis=" + millis + "    Seconds=" + seconds);
    }

}
