package utils;

import org.apache.commons.lang3.StringUtils;

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

public class DateTimeUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public static long translateToSeconds(long currentTimeMillis) {
        return currentTimeMillis / 1000;
    }

    public static long translateToMinutes(long currentTimeMillis) {
        return currentTimeMillis / 1000 / 60;
    }

    public static long translateToHours(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60);
    }

    /**
     * 取得日期的特定表示格式的字符串
     *
     * @param format 日期格式
     * @param date   日期（Date）
     * @return 日期的字符串
     */
    public static synchronized String getDate2Str(String format, Date date) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 取得某日期??的特定表示格式的字符串
     *
     * @param format ??格式
     * @return 某日期的字符串
     */
    public static synchronized String getCurrentDateStr(String format) {
        if (StringUtils.isBlank(format)) {
            simpleDateFormat.applyPattern(DateFormat.FORMATE_2.getFormat());
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date());
    }

    /**
     * 取得某日期??的特定表示格式的字符串
     *
     * @param format ??格式
     * @return 某日期的字符串
     */
    public static synchronized String getCurrentDateTimeStr(String format) {
        if (StringUtils.isBlank(format)) {
            simpleDateFormat.applyPattern(DateFormat.FORMATE_1.getFormat());
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date());
    }

    public static String getDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date getTimeMin(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String da = sdf.format(date);
        da = da + " 00:00:00";
        Date d = getStrToDate(DateFormat.FORMATE_1.getFormat(), da);
        return d;
    }

    public static Date getTimeMax(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String da = sdf.format(date);
        da = da + " 23:59:59";
        Date d = getStrToDate(DateFormat.FORMATE_1.getFormat(), da);
        return d;
    }

    public static Date getTimeMin() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String da = sdf.format(date);
        da = da + " 00:00:00";
        Date d = getStrToDate(DateFormat.FORMATE_1.getFormat(), da);
        return d;
    }

    public static Date getTimeMax() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String da = sdf.format(date);
        da = da + " 23:59:59";
        Date d = getStrToDate(DateFormat.FORMATE_1.getFormat(), da);
        return d;
    }

    /**
     * 将特定格式的??字符串?化?Date?型
     *
     * @param format ??格式
     * @param str    某日期的字符串
     * @return 某日期（Date）
     */
    public static synchronized Date getStrToDate(String format, String str) {
        simpleDateFormat.applyPattern(format);
        ParsePosition parseposition = new ParsePosition(0);
        return simpleDateFormat.parse(str, parseposition);
    }

    public static String date2String(Date date) {
        return getDate2Str(DateFormat.FORMATE_0.getFormat(), date);
    }

    public static String dateFormatString(Date date) {
        return getDate2Str(DateFormat.FORMATE_1.getFormat(), date);
    }

    public static String date2String(Date date, String format) {
        return getDate2Str(format, date);
    }

    /**
     * ??字符串是否?日期
     *
     * @param dateTime ??字符串
     * @param pattern  Eg "yyyy-MM-dd"
     * @return 返回?果
     */
    public static boolean isDateTime(String dateTime, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date dt = df.parse(dateTime, pos);
        return !(dt == null);
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
        } catch (Exception e) {
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

    public static boolean passTime(Date tempDate, int hour) {
        return !(tempDate == null || hour <= 0)
                && tempDate.before(getLimitDate(hour));
    }

    /**
     * 得到n小?前??
     *
     * @param hour 小?数
     * @return Date
     */
    public static Date getLimitDate(int hour) {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - hour * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return cl.getTime();
    }

    /**
     * 得到指定??n分?后??
     *
     * @param minute ??
     * @return Date
     * @throws Exception
     */
    public static Date getTimeAfterMinute(int minute, Date date)
            throws Exception {
        long dateTemp = date.getTime() + minute * 60 * 1000L;
        Date newTime = new Date();
        newTime.setTime(dateTemp);
        return getFormatDate(newTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到指定??n小?前??
     *
     * @param minute ??
     * @return Date
     * @throws Exception
     */
    public static Date getTimeBeforeHour(int hour, Date date)
            throws Exception {
        long dateTemp = date.getTime() - hour * 60 * 60 * 1000L;
        Date newTime = new Date();
        newTime.setTime(dateTemp);
        return getFormatDate(newTime, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 得到指定??n小?后??
     *
     * @param minute ??
     * @return Date
     * @throws Exception
     */
    public static Date getTimeAfterHour(int hour, Date date)
            throws Exception {
        long dateTemp = date.getTime() + hour * 60 * 60 * 1000L;
        Date newTime = new Date();
        newTime.setTime(dateTemp);
        return getFormatDate(newTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到指定??n天后日期
     *
     * @param day 日期
     * @return Date
     * @throws Exception
     */
    public static Date getDateAfterDate(int day, Date date) throws Exception {
        long dateTemp = date.getTime() + day * 24 * 60 * 60 * 1000L;
        Date newDate = new Date();
        newDate.setTime(dateTemp);
        return getFormatDate(newDate, "yyyy-MM-dd");
    }

    /**
     * 得到n天后日期
     *
     * @param day 日期
     * @return Date
     */
    public static Date getAfterDate(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() + day * 24 * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return getFormatDate(cl.getTime(), "yyyy-MM-dd");
    }

    /**
     * 得到当前??n分?前??
     *
     * @param day 日期
     * @return Date
     * @throws Exception
     */
    public static Date getBeforeMinute(int minute)
            throws Exception {
        Date nowtime = new Date();
        long dateTemp = nowtime.getTime() - minute * 60 * 1000L;
        nowtime.setTime(dateTemp);
        return getFormatDate(nowtime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前??n分?前??
     *
     * @param day 日期
     * @return Date
     * @throws Exception
     */
    public static Date getBeforeHour(int hour)
            throws Exception {
        Date nowtime = new Date();
        long dateTemp = nowtime.getTime() - hour * 60 * 60 * 1000L;
        nowtime.setTime(dateTemp);
        return getFormatDate(nowtime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到指定??n分?前??
     *
     * @param day 日期
     * @return Date
     * @throws Exception
     */
    public static Date getTimeBeforeMinute(int minute, Date date)
            throws Exception {
        long dateTemp = date.getTime() - minute * 60 * 1000L;
        Date newTime = new Date();
        newTime.setTime(dateTemp);
        return getFormatDate(newTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到指定??n天前日期
     *
     * @param day 日期
     * @return Date
     * @throws Exception
     */
    public static Date getDateBeforeDate(int day, Date date) throws Exception {
        long dateTemp = date.getTime() - day * 24 * 60 * 60 * 1000L;
        date.setTime(dateTemp);
        return getFormatDate(date, "yyyy-MM-dd");
    }

    /**
     * 得到n天前日期
     *
     * @param day 日期
     * @return Date
     */
    public static Date getBeforeDate(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - day * 24 * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return getFormatDate(cl.getTime(), "yyyy-MM-dd");
    }

    /**
     * 得到n天前日期
     *
     * @param day 日期
     * @return Date
     */
    public static Date getBeforeDateTime(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - day * 24 * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return getFormatDate(cl.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到n天前日期
     *
     * @param day 日期
     * @return String
     */
    public static String getBeforeDateStr(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - day * 24 * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return getDate2Str("yyyy-MM-dd", cl.getTime());
    }

    public static String getBeforeDateStr(int day, String format) throws Exception {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(cl.getTimeInMillis());
        cl.add(Calendar.DAY_OF_YEAR, -1 * day);
        return getDate2Str(format, cl.getTime());
    }

    /**
     * 得到n天前日期
     *
     * @param day 日期
     * @return String
     */
    public static String getBeforeDateTimeStr(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(cl.getTimeInMillis());
        cl.add(Calendar.DAY_OF_YEAR, -1 * day);
        return getDate2Str("yyyy-MM-dd HH:mm:ss", cl.getTime());
    }

    /**
     * 得到n分?前??
     *
     * @param minute 小?数
     * @return Date
     */
    public static Date getLimitDateByMinute(int minute) {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - minute * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return cl.getTime();
    }

    // 判断是不是日期型 true代表是日期型，false代表失?
    public static boolean isValidDate(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((sDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * ?算??差 ?入的是date?型
     *
     * @param date1
     * @param date2
     * @param type  返回?型
     * @return
     */
    public static String dateIntervalForDate(Date date1, Date date2, String type) {
        try {
            if (date1 != null && !"".equals(date1) && !"null".equals(date1)
                    && date2 != null && !"".equals(date2)
                    && !"null".equals(date2)) {
                // ??后?用??差算法
                return coreDateInterval(date1, date2, type);
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * ?算??差 ?入的?型如果是字符串，?用此方法??成date?型，再?用??差算法
     *
     * @param date1
     * @param date2
     * @param type  返回?型
     * @return
     */
    public static String dateIntervalForString(String date1, String date2,
                                               String type) {
        try {
            if (date1 != null && !"".equals(date1) && !"null".equals(date1)
                    && date2 != null && !"".equals(date2)
                    && !"null".equals(date2)) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date date3 = sdf.parse(date1);
                Date date4 = sdf.parse(date2);
                // ??后?用??差算法
                return coreDateInterval(date3, date4, type);
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * ?算??差核心方法
     *
     * @param args
     */
    public static String coreDateInterval(Date date1, Date date2, String type) {
        long l = date2.getTime() - date1.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if ("1".equals(type)) {
            return day + "天" + hour + "小?" + min + "分" + s + "秒";
        } else if ("2".equals(type)) {
            return day * 24 + hour + ":" + min + ":" + s;
        } else {
            return "";
        }
    }

    /**
     * ?算当前??到凌晨??差
     *
     * @param args
     */
    public static int getBetweenDayMs() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        int ms = (int) (c.getTime().getTime() - date.getTime()) / 1000;
        return ms;
    }

    public static Date getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // 得到前一天
        cal.add(Calendar.MONTH, -1); // 得到前一个月
        return cal.getTime();
    }

    public static String getDate2String(Date date, String format) {
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        return sformat.format(date);
    }

    public static Date getDateFromString(String date) throws Exception {
        SimpleDateFormat sformat = new SimpleDateFormat(DateFormat.FORMATE_0.getFormat());
        return sformat.parse(date);
    }

    public static Date getDateFromStr(String date, String format)
            throws Exception {
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        return sformat.parse(date);
    }

    public static Date getString2Date(String date, String format)
            throws Exception {
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        return sformat.parse(date);
    }

    public static Date getFormatDate(Date date, String format) throws Exception {
        String dateStr = getDate2String(date, format);
        return getString2Date(dateStr, format);
    }

    // 用于??模??求参数,例:pwd=091416
    public static String getSwitchPassword() {
        String pwd = "";
        Date date = new Date();
        SimpleDateFormat nowtime = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = nowtime.format(date);
        String[] timeArray = timeStr.split("-");

        pwd = timeArray[1] + timeArray[0].substring(2) + timeArray[2];

        return pwd;
    }

    // 用于??模??求参数,例:pwd=091416
    public static String getSwitchPasswordUrlParam(String uri) {
        if (uri.indexOf("?") == -1) {
            return uri + "?pwd=" + getSwitchPassword();
        } else {
            return uri + "&pwd=" + getSwitchPassword();
        }
    }

    public static String getLastDateStr(Date createTime, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(createTime);
        Long clTemp = c.getTimeInMillis() - 1 * 24 * 60 * 60 * 1000;
        c.setTimeInMillis(clTemp);
        return getDate2Str(format, c.getTime());
    }

    public static String getDateStrByLong(long time, String format)
            throws Exception {
        Date date = new Date();
        date.setTime(time);
        return getDate2Str(format, date);
    }

    public static int getDayDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000 / 60 / 60 / 24 + 1);
    }

    public static int get5MinuteDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000 / 60 / 5 + 1);
    }

    public static int getHourDiff(Date date1, Date date2) {
        long difference = Math.abs(date2.getTime() - date1.getTime());
        return (int) (difference / 1000 / 60 / 60 + 1);
    }

    public static Date getDayStart(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return getFormatDate(c.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDayEnd(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return getFormatDate(c.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date get5MStart(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int minute = c.get(Calendar.MINUTE);
        minute = (minute / 5) * 5;
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public static Date get5MEnd(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int minute = c.get(Calendar.MINUTE);
        minute = (minute / 5 + 1) * 5;
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public static Date getHourStart(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public static Date getHourEnd(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int second = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);

        if (second == 0 && minute == 0)
            return c.getTime();
        else {
            c.set(Calendar.HOUR, hour + 1);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }

        return c.getTime();
    }

    public static Date getBeforeDateInfo(int day) throws Exception {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(cl.getTimeInMillis());
        cl.add(Calendar.DAY_OF_YEAR, -1 * day);
        return cl.getTime();
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat inDf = new SimpleDateFormat(format);
        SimpleDateFormat outDf = new SimpleDateFormat(format);
        String reDate = "";
        try {
            reDate = inDf.format(date);
            return outDf.parse(reDate);
        } catch (Exception e) {

        }
        return date;
    }

    public static Date formatDateUTC(String dateStr) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse(dateStr);
        return date;
    }

    /**
     * ?算??延?多久后的??
     *
     * @param date 原始??
     * @param unit 延??位：Calendar.*    eg: Calendar.MONTH
     * @param k    延?量
     * @return
     */
    public static Date getDateAfterDate(Date date, Integer unit, Integer k) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, k);
        return calendar.getTime();
    }

    public long translateToDays(long currentTimeMillis) {
        return currentTimeMillis / 1000 / (60 * 60 * 24);
    }

    public enum DateFormat {
        FORMATE_0("yyyyMMddHHmmss"),
        FORMATE_1("yyyy-MM-dd HH:mm:ss"),
        FORMATE_2("yyyy/MM/dd HH:mm:ss"),
        FORMATE_3("yyyyMMdd"),
        FORMATE_4("yyyy-MM-dd"),
        FORMATE_5("yyyy/MM/dd"),
        FORMATE_6("HH:mm:ss");

        private String format;

        DateFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}


