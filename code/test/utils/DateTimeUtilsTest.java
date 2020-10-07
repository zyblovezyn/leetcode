package utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * DateTimeUtils Tester.
 */
@SuppressWarnings("DateTimeUtils测试")
public class DateTimeUtilsTest {


    private Date date1;
    private Date date2;

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
    @Test
    public void testTranslateToSeconds() {
        long seconds = DateTimeUtils.translateToSeconds(1000);
        Assert.assertEquals(1, seconds);

    }

    /**
     * Method: translateToMinutes(long currentTimeMillis)
     */
    @Test
    public void testTranslateToMinutes() {
//TODO: Test goes here... 
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
        String dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_1.getFormat(), date1);
        Assert.assertEquals(dtstr, "2020-09-07 10:10:10");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_0.getFormat(), date1);
        Assert.assertEquals(dtstr, "20200907101010");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_2.getFormat(), date1);
        Assert.assertEquals(dtstr, "2020/09/07 10:10:10");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_3.getFormat(), date1);
        Assert.assertEquals(dtstr, "20200907");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_4.getFormat(), date1);
        Assert.assertEquals(dtstr, "2020-09-07");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_5.getFormat(), date1);
        Assert.assertEquals(dtstr, "2020/09/07");
        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_6.getFormat(), date2);
        Assert.assertEquals(dtstr, "14:10:10");
    }

    /**
     * Method: getCurrentDateStr(String format)
     */
    @Test
    public void testGetCurrentDateStr() {
        String dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_0.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_0.getFormat()), dtstr, ""));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_1.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_1.getFormat()), dtstr, "-"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_2.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_2.getFormat()), dtstr, "/"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_3.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_3.getFormat()), dtstr, ""));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_4.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_4.getFormat()), dtstr, "-"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_5.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_5.getFormat()), dtstr, "/"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_6.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_6.getFormat()), dtstr, ":"));

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
        String dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_0.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_0.getFormat()), dtstr, ""));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_1.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_1.getFormat()), dtstr, "-"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_2.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_2.getFormat()), dtstr, "/"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_3.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_3.getFormat()), dtstr, ""));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_4.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_4.getFormat()), dtstr, "-"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_5.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_5.getFormat()), dtstr, "/"));

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_6.getFormat(), date1);
        Assert.assertTrue(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_6.getFormat()), dtstr, ":"));
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
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMax(Date date)
     */
    @Test
    public void testGetTimeMaxDate() {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMin()
     */
    @Test
    public void testGetTimeMin() {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMax()
     */
    @Test
    public void testGetTimeMax() {
//TODO: Test goes here... 
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
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeAfterMinute(int minute, Date date)
     */
    @Test
    public void testGetTimeAfterMinute() {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeBeforeHour(int hour, Date date)
     */
    @Test
    public void testGetTimeBeforeHour() {
//TODO: Test goes here... 
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

    @org.junit.jupiter.api.Test
    void getCurrentDateStr() {
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

    @org.junit.jupiter.api.Test
    void getTimeAfterMinute() {
    }

    @org.junit.jupiter.api.Test
    void getTimeBeforeHour() {
    }

    @org.junit.jupiter.api.Test
    void getTimeAfterHour() {
    }

    @org.junit.jupiter.api.Test
    void getDateAfterDate() {
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
}
