package utils;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:
* <p>
* @Author: Mr.Miles
* @Date: 2020/10/22
*/
public class WatcherTime implements Serializable {

    private static final long serialVersionUID = 5906901289474420339L;

    private static Date startDate;
    private static Date stopDate;

    public WatcherTime() {
    }

    /**
     * @Description: 设置开始日期
     * <p>
     * @Param: []
     * @return: void
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static void start() {
        startDate = new Date();
    }

    /**
     * @Description: 设置结束时间
     * <p>
     * @Param: []
     * @return: void
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static void stop() {
        stopDate = new Date();
    }

    /**
     * @Description: 获取毫秒数
     * <p>
     * @Param: []
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static long toMillis() {
        long milliseconds = DateUtils.getMillisBetweenDate(startDate, stopDate);
        return Math.abs(milliseconds);
    }

    /**
     * @Description: 获取秒数
     * <p>
     * @Param: []
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static long toSeconds() {
        long seconds = DateUtils.getSecondsBetweenDate(startDate, stopDate);
        return Math.abs(seconds);
    }

    /**
     * @Description: 获取分钟数
     * <p>
     * @Param: []
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static long toMinutes() {
        long minutes = DateUtils.getMinutesBetweenDate(startDate, stopDate);
        return Math.abs(minutes);
    }

    public static void print() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("时间差：").append(toMinutes()).append(" 分钟     ").append(toSeconds()).append(" 秒    ").append(toMillis()).append(" 毫秒");
        System.out.println(stringBuffer.toString());
    }

    /**
     * @Description: toString()
     * <p>
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DateTimeWatcher ");
        stringBuffer.append("[ ").append(DateUtils.getDate2Str(startDate)).append(" ]");
        stringBuffer.append("   ");
        stringBuffer.append("[ ").append(DateUtils.getDate2Str(stopDate)).append(" ]");
        return stringBuffer.toString();
    }
}
