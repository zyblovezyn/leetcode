package utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * DateTimeUtils Tester.
 */
@SuppressWarnings("DateTimeUtils测试")
@DisplayName("DateTimeUtils测试")
public class DateTimeUtilsTest {


    private Date date1;
    private Date date2;

    private String dateStr1 = "2020-09-07 10:10:10";
    private String dateStr2 = "2020-09-07 14:10:10";

    @Before
    public void before() throws Exception {
        String dateString = "2020-09-07 10:10:10";
        this.date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        String dateString2 = "2020-09-07 14:10:10";
        this.date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString2);
    }

    @After
    public void after() {
        this.date1 = null;
        this.date2 = null;
    }

    /**
     * Method: translateToSeconds(long currentTimeMillis)
     */
    @DisplayName("毫秒转秒")
    @Test
    public void testTranslateToSeconds() {
        long seconds = DateUtils.translateToSeconds(1000);
        Assert.assertEquals(1, seconds);

        seconds = DateUtils.translateToSeconds(2000);
        Assert.assertEquals(2, seconds);

        seconds = DateUtils.translateToSeconds(0);
        Assert.assertEquals(0, seconds);

        seconds = DateUtils.translateToSeconds(1500);
        Assert.assertEquals(1, seconds);
    }

    /**
     * Method: translateToMinutes(long currentTimeMillis)
     */
    @Test
    public void testTranslateToMinutes() {
        long minutes = DateUtils.translateToMinutes(1000 * 60);
        Assert.assertEquals(1, minutes);

        minutes = DateUtils.translateToMinutes(2000 * 60);
        Assert.assertEquals(2, minutes);

        minutes = DateUtils.translateToMinutes(0);
        Assert.assertEquals(0, minutes);

        minutes = DateUtils.translateToMinutes(1500 * 60);
        Assert.assertEquals(1, minutes);
    }

    /**
     * Method: translateToHours(long currentTimeMillis)
     */
    @Test
    public void testTranslateToHours() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDate2Str(String format, Date date)
     */
    @Test
    public void testGetDate2Str() {
        String dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_DEFAULT);
        Assert.assertEquals(dtstr, "2020-09-07 10:10:10");
        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME);
        Assert.assertEquals(dtstr, "20200907101010");
        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_SLASH);
        Assert.assertEquals(dtstr, "2020/09/07 10:10:10");
        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE);
        Assert.assertEquals(dtstr, "20200907");
        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_DEFAULT);
        Assert.assertEquals(dtstr, "2020-09-07");
        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_SLASH);
        Assert.assertEquals(dtstr, "2020/09/07");
        dtstr = DateUtils.getDate2Str(date2, DateFormat.TIME);
        Assert.assertEquals(dtstr, "14:10:10");
    }

    /**
     * Method: getCurrentDateStr(String format)
     */
    @Test
    public void testGetCurrentDateStr() {
        String dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME.getFormat()), dtstr, ""));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_DEFAULT);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME_DEFAULT.getFormat()), dtstr, "-"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_SLASH);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME_SLASH.getFormat()), dtstr, "/"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE.getFormat()), dtstr, ""));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_DEFAULT);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE_DEFAULT.getFormat()), dtstr, "-"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_SLASH);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE_SLASH.getFormat()), dtstr, "/"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.TIME);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.TIME.getFormat()), dtstr, ":"));

    }

    private boolean delimit(String str1, String str2, String delim) {
        str1 = Pattern.compile("\\d").matcher(str1).replaceAll("");
        str2 = Pattern.compile("\\d").matcher(str2).replaceAll("");
        return str1.equals(str2) && str1.contains(delim) && str2.contains(delim);
    }

    /**
     * Method: getCurrentDateTimeStr(String format)
     */
    @Test
    public void testGetCurrentDateTimeStr() {
        String dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME.getFormat()), dtstr, ""));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_DEFAULT);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME_DEFAULT.getFormat()), dtstr, "-"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATETIME_SLASH);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATETIME_SLASH.getFormat()), dtstr, "/"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE.getFormat()), dtstr, ""));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_DEFAULT);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE_DEFAULT.getFormat()), dtstr, "-"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.DATE_SLASH);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.DATE_SLASH.getFormat()), dtstr, "/"));

        dtstr = DateUtils.getDate2Str(date1, DateFormat.TIME);
        Assert.assertTrue(delimit(DateUtils.getCurrentDateStr(DateFormat.TIME.getFormat()), dtstr, ":"));
    }

    /**
     * Method: getDay()
     */
    @Test
    public void testGetDay() {

    }

    /**
     * Method: getNowTime()
     */
    @Test
    public void testGetNowTime() {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMin(Date date)
     */
    @Test
    public void testGetTimeMinDate() {
        Date date = DateUtils.getTimeMin(new Date());
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date, DateFormat.DATETIME_DEFAULT));

        date = DateUtils.getTimeMin(this.date1);
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date, DateFormat.DATETIME_DEFAULT));

        date = DateUtils.getTimeMin(this.date2);
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date, DateFormat.DATETIME_DEFAULT));
    }

    /**
     * Method: getTimeMax(Date date)
     */
    @Test
    public void testGetTimeMaxDate() {
    }

    /**
     * Method: getTimeMin()
     */
    @Test
    public void testGetTimeMin() {
        Date date = DateUtils.getTimeMin();
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));
    }

    /**
     * Method: getTimeMax()
     */
    @Test
    public void testGetTimeMax() {
        Date date = DateUtils.getTimeMax(new Date());
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));

        date = DateUtils.getTimeMax();
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + "   " + DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));
    }

    /**
     * Method: getStrToDate(String format, String str)
     */
    @Test
    public void testGetStrToDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: date2String(Date date)
     */
    @Test
    public void testDate2StringDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: dateFormatString(Date date)
     */
    @Test
    public void testDateFormatString() {
//TODO: Test goes here... 
    }

    /**
     * Method: date2String(Date date, String format)
     */
    @Test
    public void testDate2StringForDateFormat() {
//TODO: Test goes here... 
    }

    /**
     * Method: isDateTime(String dateTime, String pattern)
     */
    @Test
    public void testIsDateTime() {
//TODO: Test goes here... 
    }

    /**
     * Method: getXMLGregorianCalendar(Date date)
     */
    @Test
    public void testGetXMLGregorianCalendar() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromXmlGregorianCalendar(XMLGregorianCalendar da)
     */
    @Test
    public void testGetDateFromXmlGregorianCalendar() {
//TODO: Test goes here... 
    }

    /**
     * Method: passTime(Date tempDate, int hour)
     */
    @Test
    public void testPassTime() {
//TODO: Test goes here... 
    }

    /**
     * Method: getLimitDate(int hour)
     */
    @Test
    public void testGetLimitDate() {
        Date date = DateUtils.getLimitDate(1);
        System.out.println(DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));

        date = DateUtils.getLimitDate(5);
        System.out.println(DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));
    }

    /**
     * Method: getTimeAfterMinute(int minute, Date date)
     */
    @Test
    public void testGetTimeAfterMinute() {
        Date date = DateUtils.getTimeAfterMinute(1,new Date());
        System.out.println(DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));

        date = DateUtils.getTimeAfterMinute(5, new Date());
        System.out.println(DateUtils.getDate2Str(date,DateFormat.DATETIME_DEFAULT));
    }

    /**
     * Method: getTimeBeforeHour(int hour, Date date)
     */
    @Test
    public void testGetTimeBeforeHour() {

    }

    /**
     * Method: getTimeAfterHour(int hour, Date date)
     */
    @Test
    public void testGetTimeAfterHour() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateAfterDate(int day, Date date)
     */
    @Test
    public void testGetDateAfterDateForDayDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getAfterDate(int day)
     */
    @Test
    public void testGetAfterDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeMinute(int minute)
     */
    @Test
    public void testGetBeforeMinute() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeHour(int hour)
     */
    @Test
    public void testGetBeforeHour() {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeBeforeMinute(int minute, Date date)
     */
    @Test
    public void testGetTimeBeforeMinute() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateBeforeDate(int day, Date date)
     */
    @Test
    public void testGetDateBeforeDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDate(int day)
     */
    @Test
    public void testGetBeforeDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateTime(int day)
     */
    @Test
    public void testGetBeforeDateTime() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateStr(int day)
     */
    @Test
    public void testGetBeforeDateStrDay() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateStr(int day, String format)
     */
    @Test
    public void testGetBeforeDateStrForDayFormat() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateTimeStr(int day)
     */
    @Test
    public void testGetBeforeDateTimeStr() {
//TODO: Test goes here... 
    }

    /**
     * Method: getLimitDateByMinute(int minute)
     */
    @Test
    public void testGetLimitDateByMinute() {
//TODO: Test goes here... 
    }

    /**
     * Method: isValidDate(String sDate)
     */
    @Test
    public void testIsValidDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: dateIntervalForDate(Date date1, Date date2, String type)
     */
    @Test
    public void testDateIntervalForDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: dateIntervalForString(String date1, String date2, String type)
     */
    @Test
    public void testDateIntervalForString() {
//TODO: Test goes here... 
    }

    /**
     * Method: coreDateInterval(Date date1, Date date2, String type)
     */
    @Test
    public void testCoreDateInterval() {
//TODO: Test goes here... 
    }

    /**
     * Method: getBetweenDayMs()
     */
    @Test
    public void testGetBetweenDayMs() {
//TODO: Test goes here... 
    }

    /**
     * Method: getPreMonth()
     */
    @Test
    public void testGetPreMonth() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDate2String(Date date, String format)
     */
    @Test
    public void testGetDate2String() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromString(String date)
     */
    @Test
    public void testGetDateFromString() {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromStr(String date, String format)
     */
    @Test
    public void testGetDateFromStr() {
//TODO: Test goes here... 
    }

    /**
     * Method: getString2Date(String date, String format)
     */
    @Test
    public void testGetString2Date() {
//TODO: Test goes here... 
    }

    /**
     * Method: getFormatDate(Date date, String format)
     */
    @Test
    public void testGetFormatDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getSwitchPassword()
     */
    @Test
    public void testGetSwitchPassword() {
//TODO: Test goes here... 
    }

    /**
     * Method: getSwitchPasswordUrlParam(String uri)
     */
    @Test
    public void testGetSwitchPasswordUrlParam() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLastDateStr(Date createTime, String format)
     */
    @Test
    public void testGetLastDateStr() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateStrByLong(long time, String format)
     */
    @Test
    public void testGetDateStrByLong() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDayDiff(Date date1, Date date2)
     */
    @Test
    public void testGetDayDiff() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get5MinuteDiff(Date date1, Date date2)
     */
    @Test
    public void testGet5MinuteDiff() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getHourDiff(Date date1, Date date2)
     */
    @Test
    public void testGetHourDiff() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDayStart(Date date)
     */
    @Test
    public void testGetDayStart() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDayEnd(Date date)
     */
    @Test
    public void testGetDayEnd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get5MStart(Date date)
     */
    @Test
    public void testGet5MStart() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get5MEnd(Date date)
     */
    @Test
    public void testGet5MEnd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getHourStart(Date date)
     */
    @Test
    public void testGetHourStart() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getHourEnd(Date date)
     */
    @Test
    public void testGetHourEnd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateInfo(int day)
     */
    @Test
    public void testGetBeforeDateInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: formatDate(Date date, String format)
     */
    @Test
    public void testFormatDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: formatDateUTC(String dateStr)
     */
    @Test
    public void testFormatDateUTC() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateAfterDate(Date date, Integer unit, Integer k)
     */
    @Test
    public void testGetDateAfterDateForDateUnitK() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: translateToDays(long currentTimeMillis)
     */
    @Test
    public void testTranslateToDays() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFormat()
     */
    @Test
    public void testGetFormat() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: values()
     */
    @Test
    public void testValues() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: valueOf(java.lang.String name)
     */
    @Test
    public void testValueOf() throws Exception {
//TODO: Test goes here... 
    }


    @org.junit.jupiter.api.Test
    void translateToMillis() {
    }

    @org.junit.jupiter.api.Test
    void translateToSeconds() {
    }

    @org.junit.jupiter.api.Test
    void translateToMinutes() {
    }

    @org.junit.jupiter.api.Test
    void translateToHours() {
    }

    @org.junit.jupiter.api.Test
    void getDate2Str() {
    }

    @DisplayName("取得当前日期的指定格式的字符串表示")
    @Test
    public void getCurrentDateStr() {
        String dateStr = DateUtils.getCurrentDateStr("");
        System.out.println(DateFormat.DATETIME_SLASH.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATETIME_DEFAULT.getFormat());
        System.out.println(DateFormat.DATETIME_DEFAULT.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATETIME.getFormat());
        System.out.println(DateFormat.DATETIME.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATETIME_SLASH.getFormat());
        System.out.println(DateFormat.DATETIME_SLASH.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATE.getFormat());
        System.out.println(DateFormat.DATE.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATE_DEFAULT.getFormat());
        System.out.println(DateFormat.DATE_DEFAULT.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.DATE_SLASH.getFormat());
        System.out.println(DateFormat.DATE_SLASH.getFormat() + " " + dateStr);

        dateStr = DateUtils.getCurrentDateStr(DateFormat.TIME.getFormat());
        System.out.println(DateFormat.TIME.getFormat() + " " + dateStr);
    }

    @org.junit.jupiter.api.Test
    void getCurrentDateTimeStr() {
    }

    @org.junit.jupiter.api.Test
    void getDay() {
    }

    @org.junit.jupiter.api.Test
    void getNowTime() {
    }

    @org.junit.jupiter.api.Test
    void getTimeMin() {
    }

    @org.junit.jupiter.api.Test
    void getTimeMax() {
    }

    @org.junit.jupiter.api.Test
    void testGetTimeMin1() {
    }

    @org.junit.jupiter.api.Test
    void testGetTimeMax1() {
    }

    @org.junit.jupiter.api.Test
    void getStrToDate() {
    }

    @org.junit.jupiter.api.Test
    void date2String() {
    }

    @org.junit.jupiter.api.Test
    void dateFormatString() {
    }

    @org.junit.jupiter.api.Test
    void testDate2String() {
    }

    @org.junit.jupiter.api.Test
    void isDateTime() {
    }

    @org.junit.jupiter.api.Test
    void getXMLGregorianCalendar() {
    }

    @org.junit.jupiter.api.Test
    void getDateFromXmlGregorianCalendar() {
    }

    @org.junit.jupiter.api.Test
    void passTime() {
    }

    @org.junit.jupiter.api.Test
    void getLimitDate() {
    }

    @Test
    public void getTimeAfterMinute() {
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeAfterMinute(1)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeAfterMinute(10)));

        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeAfterMinute(1,this.date1)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeAfterMinute(10,this.date1)));

    }

    @org.junit.jupiter.api.Test
    void getTimeBeforeHour() {
    }

    @org.junit.jupiter.api.Test
    void getTimeAfterHour() {
    }

    @Test
    public void getDateAfterDate() {
        Date date = DateUtils.getDateAfterDate(this.date1, Calendar.HOUR, 2);
        System.out.println(DateUtils.getDate2Str(date));
    }

    @org.junit.jupiter.api.Test
    void getAfterDate() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeMinute() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeHour() {
    }

    @org.junit.jupiter.api.Test
    void getTimeBeforeMinute() {
    }

    @org.junit.jupiter.api.Test
    void getDateBeforeDate() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeDate() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeDateTime() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeDateStr() {
    }

    @org.junit.jupiter.api.Test
    void testGetBeforeDateStr() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeDateTimeStr() {
    }

    @org.junit.jupiter.api.Test
    void getLimitDateByMinute() {
    }

    @org.junit.jupiter.api.Test
    void isValidDate() {
    }

    @org.junit.jupiter.api.Test
    void dateIntervalForDate() {
    }

    @org.junit.jupiter.api.Test
    void dateIntervalForString() {
    }

    @org.junit.jupiter.api.Test
    void coreDateInterval() {
    }

    @org.junit.jupiter.api.Test
    void getBetweenDayMs() {
    }

    @org.junit.jupiter.api.Test
    void getPreMonth() {
    }

    @org.junit.jupiter.api.Test
    void getDate2String() {
    }

    @org.junit.jupiter.api.Test
    void getDateFromString() {
    }

    @org.junit.jupiter.api.Test
    void getDateFromStr() {
    }

    @org.junit.jupiter.api.Test
    void getString2Date() {
    }

    @org.junit.jupiter.api.Test
    void getFormatDate() {
    }

    @org.junit.jupiter.api.Test
    void getSwitchPassword() {
    }

    @org.junit.jupiter.api.Test
    void getSwitchPasswordUrlParam() {
    }

    @org.junit.jupiter.api.Test
    void getLastDateStr() {
    }

    @org.junit.jupiter.api.Test
    void getDateStrByLong() {
    }

    @org.junit.jupiter.api.Test
    void getDayDiff() {
    }

    @org.junit.jupiter.api.Test
    void get5MinuteDiff() {
    }

    @org.junit.jupiter.api.Test
    void getHourDiff() {
    }

    @org.junit.jupiter.api.Test
    void getDayStart() {
    }

    @org.junit.jupiter.api.Test
    void getDayEnd() {
    }

    @org.junit.jupiter.api.Test
    void get5MStart() {
    }

    @org.junit.jupiter.api.Test
    void get5MEnd() {
    }

    @org.junit.jupiter.api.Test
    void getHourStart() {
    }

    @org.junit.jupiter.api.Test
    void getHourEnd() {
    }

    @org.junit.jupiter.api.Test
    void getBeforeDateInfo() {
    }

    @org.junit.jupiter.api.Test
    void formatDate() {
    }

    @org.junit.jupiter.api.Test
    void formatDateUTC() {
    }

    @org.junit.jupiter.api.Test
    void testGetDateAfterDate() {
    }

    @org.junit.jupiter.api.Test
    void translateToDays() {
    }

    @Test
    public void getDate() {
        String str = DateUtils.getDate();
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATETIME);
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATETIME_DEFAULT);
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATETIME_SLASH);
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATE);
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATE_DEFAULT);
        System.out.println(str);

        str = DateUtils.getDate(DateFormat.DATE_SLASH);
        System.out.println(str);
    }


    @Test
    public void getTime() {
        String str = DateUtils.getTime();
        System.out.println(str);

        str = DateUtils.getTime(new Date());
        System.out.println(str);

        str = DateUtils.getTime(this.date1);
        System.out.println(str);

        str = DateUtils.getTime(this.date2);
        System.out.println(str);
    }

    @Test
    public void getStr2Date() {
        Date date = DateUtils.getStr2Date(DateFormat.DATETIME_DEFAULT, this.dateStr1);
        Assert.assertEquals(date1, date);

        date = DateUtils.getStr2Date(DateFormat.DATETIME_DEFAULT, this.dateStr2);
        Assert.assertEquals(date2, date);
    }
}
