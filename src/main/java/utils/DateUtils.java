package utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description:
* <p>
* @Author: Mr.Miles
* @Date: 2020/10/22
*/
public class DateUtils implements Serializable {

    private static final long serialVersionUID = -7208099072462241010L;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public DateUtils() {
    }

    /**
     * @Description: 毫秒转秒
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized long translateToSeconds(long currentTimeMillis) {
        return currentTimeMillis / 1000;
    }

    /**
     * @Description: 毫秒转分钟
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized long translateToMinutes(long currentTimeMillis) {
        return currentTimeMillis / 1000 / 60;
    }

    /**
     * @Description: 毫秒转小时
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized long translateToHours(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60);
    }

    /**
     * @Description: 毫秒转天
     * @Param: [currentTimeMillis]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized long translateToDays(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60 * 24);
    }

    /**
     * @Description: 取得当前日期的默认格式的字符串表示 默认yyyy-Mm-dd HH:mm:ss
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized String getCurrentDateStr() {
        simpleDateFormat.applyPattern(DateFormat.DATETIME_DEFAULT.getFormat());
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Description: 取得当前日期的指定格式的字符串表示 默认yyyy-Mm-dd HH:mm:ss
     * @Param: [format]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized String getCurrentDateStr( DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Description: 获取时间 HH:mm:ss
     * @Param: [date]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized String getTime( Date date) {
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
    public static synchronized Date getStr2Date( String str) {
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
     * @Description: 字符串转日期
     * @Param: [str, format]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized Date getStr2Date( String str,  DateFormat format) {
        Date date = null;
        simpleDateFormat.applyPattern(format.getFormat());
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static synchronized XMLGregorianCalendar getXMLGregorianCalendar( Date date) {
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

    public static synchronized Date getDateFromXmlGregorianCalendar( XMLGregorianCalendar da) {
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
    }

    /**
     * @Description: 得到指定分钟后的日期
     * @Param: [minute, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date getDateAfterMinute(int minute,  Date date) {
        return getDateAfter(date, minute, Calendar.MINUTE);
    }

    /**
     * @Description: 获取指定单位指定数字后的时间
     * @Param: [number, date, unit]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    private static synchronized Date getDateAfter( Date date, int number, int unit) {
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
    public static synchronized Date getDateAfterHour(int hour,  Date date) {
        return getDateAfter(date, hour, Calendar.HOUR_OF_DAY);
    }

    /**
     * @Description: 得到指定天数后日期
     * @Param: [day, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date getDateAfterDay(int day,  Date date) {
        return getDateAfter(date, day, Calendar.DAY_OF_YEAR);

    }

    /**
     * @Description: 得到指定月数后日期
     * @Param: [month, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date getDateAfterMonth(int month,  Date date) {
        return getDateAfter(date, month, Calendar.MONTH);

    }

    /**
     * @Description: 得到指定年数后日期
     * @Param: [year, date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date getDateAfterYear(int year,  Date date) {
        return getDateAfter(date, year, Calendar.YEAR);

    }


    /**
     * @Description: 毫秒转字符串
     * @Param: [time]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized String getLong2Str(long time) {
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
    public static synchronized String getDate2Str( Date date) {
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
    public static synchronized String getLong2Str(long time,  DateFormat format) {
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
    public static synchronized String getDate2Str( Date date,  DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: 获取5分钟前的时间 秒数为0
     * @Param: [date]
     * @return: java.util.Date
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized Date get5MinutesStart( Date date) {
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
    public static synchronized Date get5MinutesEnd( Date date) {
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
    public static synchronized Date getHourStart( Date date) {
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
    public static synchronized Date getHourEnd( Date date) {
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
    public static synchronized Date getDayStart( Date date) {
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
    public static synchronized Date getDayEnd( Date date) {
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
    public static synchronized Date getWeekStart( Date date) {
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
    public static synchronized Date getWeekEnd( Date date) {
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
    public static synchronized Date getMonthStart( Date date) {
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
    public static synchronized Date getMonthEnd( Date date) {
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
    public static synchronized Date getYearStart( Date date) {
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
    public static synchronized Date getYearEnd( Date date) {
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

    /**
     * @Description: 获取日期相差毫秒数
     * @Param: [date1, date2]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized long getMillisBetweenDate( Date date1,  Date date2) {
        Instant inst1 = Instant.ofEpochMilli(date1.getTime());
        Instant inst2 = Instant.ofEpochMilli(date2.getTime());
        return Duration.between(inst1, inst2).toMillis();
    }

    /**
     * @Description: 获取日期相差秒数
     * @Param: [date1, date2]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized long getSecondsBetweenDate( Date date1,  Date date2) {
        Instant inst1 = Instant.ofEpochMilli(date1.getTime());
        Instant inst2 = Instant.ofEpochMilli(date2.getTime());
        return Duration.between(inst1, inst2).getSeconds();
    }

    /**
     * @Description: 获取日期相差分钟数
     * @Param: [date1, date2]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized long getMinutesBetweenDate( Date date1,  Date date2) {
        Instant inst1 = Instant.ofEpochMilli(date1.getTime());
        Instant inst2 = Instant.ofEpochMilli(date2.getTime());
        return Duration.between(inst1, inst2).toMinutes();
    }

    /**
     * @Description: 获取日期相差小时数
     * @Param: [date1, date2]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized long getHoursBetweenDate( Date date1,  Date date2) {
        Instant inst1 = Instant.ofEpochMilli(date1.getTime());
        Instant inst2 = Instant.ofEpochMilli(date2.getTime());
        return Duration.between(inst1, inst2).toHours();
    }

    /**
     * @Description: 获取指定日期的秒数
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getSecond( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * @Description: 获取分钟数
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getMinute( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * @Description: 获取小时数, 12小时制
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getHour12( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * @Description: 获取小时数, 24小时制
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getHour24( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @Description: 获取日期的日数, 即是一个周中的第几天
     * 周从周日开始,第一天为1
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getDayOfWeek( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @Description: 获取日期的日数, 即是一个月中的第几天
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getDayOfMonth( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @Description: 获取日期的日数, 即是一个年中的第几天
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getDayOfYear( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * @Description: 获取日期的月数
     * Calendar.MONTH 月份是从0开始  0-11 对应 1月-12月
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getMonth( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @Description: 获取年龄
     * 参考: https://blog.csdn.net/u012811805/article/details/53814736
     * @Param: [birthday]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getAgeByBirth( Date birthday) {
        int age = 0;
        try {
            // 当前时间
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            //如果传入的时间，在当前时间的后面，返回0岁
            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     * @Description: 还有几天过生日
     * @Param: [birthday]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getDayFromBirth( Date birthday) {
        return (int) getDaysBetweenDate(new Date(), birthday);
    }

    /**
     * @Description: 获取日期相差天数
     * @Param: [date1, date2]
     * @return: long
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized long getDaysBetweenDate( Date date1,  Date date2) {
        Instant inst1 = Instant.ofEpochMilli(date1.getTime());
        Instant inst2 = Instant.ofEpochMilli(date2.getTime());
        return Duration.between(inst1, inst2).toDays();
    }

    /**
     * @Description: 判断今天是否为指定日期的生日
     * @Param: [birthDate]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized boolean isBirthDay( Date birthDate) {
        boolean isBirthDay = false;

        Calendar calendar = Calendar.getInstance();
        Calendar calBirthDate = Calendar.getInstance();
        calBirthDate.setTime(birthDate);
        int month = Calendar.MONTH;
        int nowMonth = calendar.get(month);
        int birthMonth = calBirthDate.get(month);
        //月份相等
        if (nowMonth == birthMonth) {
            int day = Calendar.DAY_OF_MONTH;
            int nowDay = calendar.get(day);
            int birthDay = calBirthDate.get(day);
            //日份相等
            if (nowDay == birthDay) {
                isBirthDay = true;
            }
        }
        return isBirthDay;
    }

    /**
     * @Description: 判断是不是日期(不包括时间)类型 true代表是日期型，false代表非日期类型
     * @Param: [dataStr]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized boolean isValidDate( String dataStr) {
        String patternStr = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

        Pattern pattern = Pattern.compile(patternStr);
        Matcher match = pattern.matcher(dataStr);
        return match.matches();
    }

    /**
     * @Description: 检查字符串是否为日期
     * @Param: [dateTime, format]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/13
     */
    public static synchronized boolean isDateTime( String dateTime,  DateFormat format) {
        simpleDateFormat.applyPattern(format.getFormat());
        ParsePosition pos = new ParsePosition(0);
        Date dt = simpleDateFormat.parse(dateTime, pos);
        return !(dt == null);
    }

    /**
     * @Description: 判断是否为瑞年
     * <p>
     * 瑞年判断法
     * 1. 年份是4的倍数而不是100的倍数；
     * 2. 年份是400的倍数。
     * @Param: [date]
     * @return: boolean
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized boolean isLeapYear( Date date) {
        boolean leapYear = false;
        int year = getYear(date);
        if ((year % 4 == 0 & year % 100 != 0) || year % 400 == 0) {
            leapYear = true;
        }
        return leapYear;
    }

    /**
     * @Description: 获取日期的年数
     * @Param: [date]
     * @return: int
     * @Author: Mr.Miles
     * @Date: 2020/10/14
     */
    public static synchronized int getYear( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}


