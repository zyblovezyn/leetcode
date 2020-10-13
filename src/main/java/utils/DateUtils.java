package utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    /**
     * @Description: 毫秒转秒
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static long translateToSeconds(long currentTimeMillis) {
        return currentTimeMillis / 1000;
    }

    /**
     * @Description: 毫秒转分钟
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static long translateToMinutes(long currentTimeMillis) {
        return currentTimeMillis / 1000 / 60;
    }

    /**
     * @Description: 毫秒转小时
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static long translateToHours(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60);
    }

    /**
     * @Description: 毫秒转天
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static long translateToDays(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60 * 24);
    }

    /**
     * 取得当前日期的默认格式的字符串表示 默认yyyy-Mm-dd HH:mm:ss
     *
     * @return 某日期的字符串
     */
    public static synchronized String getCurrentDateStr() {
        simpleDateFormat.applyPattern(DateFormat.DATETIME_DEFAULT.getFormat());
        return simpleDateFormat.format(new Date());
    }

    /**
     * 取得当前日期的指定格式的字符串表示 默认yyyy-Mm-dd HH:mm:ss
     *
     * @param format 日期格式
     * @return 某日期的字符串
     */
    public static synchronized String getCurrentDateStr(DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Description: 获取时间 HH:mm:ss
     * @return: string
     */
    public static String getTime(Date date) {
        simpleDateFormat.applyPattern(DateFormat.TIME.getFormat());
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: 字符串转日期
     * @Param: [str]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date getStr2Date(String str) {
        Date date = null;
        simpleDateFormat.applyPattern(DateFormat.DATETIME_DEFAULT.getFormat());
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转日期
     *
     * @param format 日期格式
     * @param str    日期的字符串
     * @return 日期（Date）
     */
    public static synchronized Date getStr2Date(String str, DateFormat format) {
        Date date = null;
        simpleDateFormat.applyPattern(format.getFormat());
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            xmlCalendar = dtf.newXMLGregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    calendar.get(Calendar.MILLISECOND),
                    calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));
        } catch (Exception ignored) {
        }
        return xmlCalendar;
    }

    public static Date getDateFromXmlGregorianCalendar(XMLGregorianCalendar da) {
        if (da != null) {
            int year = da.getYear();
            int month = da.getMonth();
            int day = da.getDay();
            int hour = da.getHour();
            int minute = da.getMinute();
            int second = da.getSecond();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day, hour, minute, second);
            Date date = calendar.getTime();
            return date;
        } else {
            return null;
        }

    }

    /**
     * @Description: 得到指定分钟后的日期
     * @Param: [minute, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDateAfterMinute(int minute, Date date) {
        return getDateAfter(date, minute, Calendar.MINUTE);
    }

    /**
     * @Description: 获取指定单位指定数字后的时间
     * @Param: [number, date, unit]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    private static Date getDateAfter(Date date, int number, int unit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, number);
        return calendar.getTime();
    }

    /**
     * @Description: 得到指定小时之后的日期
     * @Param: [hour, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDateAfterHour(int hour, Date date) {
        return getDateAfter(date, hour, Calendar.HOUR_OF_DAY);
    }

    /**
     * @Description: 得到指定天数后日期
     * @Param: [day, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDateAfterDay(int day, Date date) {
        return getDateAfter(date, day, Calendar.DAY_OF_YEAR);

    }

    /**
     * @Description: 得到指定月数后日期
     * @Param: [month, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDateAfterMonth(int month, Date date) {
        return getDateAfter(date, month, Calendar.MONTH);

    }

    /**
     * @Description: 得到指定年数后日期
     * @Param: [year, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDateAfterYear(int year, Date date) {
        return getDateAfter(date, year, Calendar.YEAR);

    }

    /**
     * @Description: 判断是不是日期(不包括时间)类型 true代表是日期型，false代表非日期类型
     * @Param: [dataStr]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static boolean isValidDate(String dataStr) {
        String patternStr = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((dataStr != null)) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher match = pattern.matcher(dataStr);
            return match.matches();
        }
        return false;
    }

    /**
     * @Description: 检查字符串是否为日期
     * @Param: [dateTime, format]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static boolean isDateTime(String dateTime, DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        ParsePosition pos = new ParsePosition(0);
        Date dt = simpleDateFormat.parse(dateTime, pos);
        return !(dt == null);
    }

    /**
     * @Description: 毫秒转字符串
     * @Param: [time]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static String getLong2Str(long time) {
        Date date = new Date();
        date.setTime(time);
        return getDate2Str(date);
    }

    /**
     * 取得日期的特定表示格式的字符串 默认日期格式yyyy-MM-dd HH:mm:dd
     *
     * @param date 日期（Date）
     * @return 日期的字符串
     */
    public static synchronized String getDate2Str(Date date) {
        simpleDateFormat.applyPattern(DateFormat.DATETIME_DEFAULT.getFormat());
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: 毫秒转日期字符串
     * @Param: [time, format]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static String getLong2Str(long time, DateFormat format) {
        Date date = new Date();
        date.setTime(time);
        return getDate2Str(date, format);
    }

    /**
     * 取得日期的特定表示格式的字符串
     *
     * @param format 日期格式
     * @param date   日期（Date）
     * @return 日期的字符串
     */
    public static synchronized String getDate2Str(Date date, DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        return simpleDateFormat.format(date);
    }

    public static int get5MinuteDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000L / 60L / 5L + 1L);
    }

    /**
     * @Description: 日期相差的小时数
     * @Param: [date1, date2]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static int getHourDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000L / 60L / 60L + 1L);
    }

    /**
     * @Description: 日期相差的天数
     * @Param: [date1, date2]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static int getDayDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000L / 60L / 60L / 24L + 1L);
    }

    /**
     * @Description: 获取5分钟前的时间 秒数为0
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date get5MinutesStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int minute = c.get(Calendar.MINUTE);
        minute = minute - 5;
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取5分钟后的时间 秒数为59
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date get5MinutesEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int minute = c.get(Calendar.MINUTE);
        minute = minute + 5;
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * @Description: 获取小时的开始时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getHourStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取小时的结束时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getHourEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * @Description: 获取一天的开始时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDayStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取一天的结束时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getDayEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * @Description: 获取一周的开始时间（通用一周日为一周的开始时间）
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getWeekStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取一周的结束时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getWeekEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 7);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * @Description: 获取一月的开始时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getMonthStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取一月的结束时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * @Description: 获取一年的开始时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getYearStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.getActualMinimum(Calendar.MONTH);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @Description: 获取一年的结束时间
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static Date getYearEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.getActualMaximum(Calendar.MONTH);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static Date formatDateUTC(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }

}


