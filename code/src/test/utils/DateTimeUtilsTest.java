package test.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * DateTimeUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9ŒŽ 7, 2020</pre>
 */
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
    public void after() throws Exception {
    }

    /**
     * Method: translateToMillis(long currentTimeMillis)
     */
    @Test
    public void testTranslateToMillis() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: translateToSeconds(long currentTimeMillis)
     */
    @Test
    public void testTranslateToSeconds() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: translateToMinutes(long currentTimeMillis)
     */
    @Test
    public void testTranslateToMinutes() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: translateToHours(long currentTimeMillis)
     */
    @Test
    public void testTranslateToHours() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDate2Str(String format, Date date)
     */
    @Test
    public void testGetDate2Str() throws Exception {
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
    public void testGetCurrentDateStr() throws Exception {
        String dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_0.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_0.getFormat()),dtstr,""),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_1.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_1.getFormat()),dtstr,"-"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_2.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_2.getFormat()),dtstr,"/"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_3.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_3.getFormat()),dtstr,""),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_4.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_4.getFormat()),dtstr,"-"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_5.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_5.getFormat()),dtstr,"/"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_6.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_6.getFormat()),dtstr,":"),true);

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
    public void testGetCurrentDateTimeStr() throws Exception {
        String dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_0.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_0.getFormat()),dtstr,""),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_1.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_1.getFormat()),dtstr,"-"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_2.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_2.getFormat()),dtstr,"/"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_3.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_3.getFormat()),dtstr,""),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_4.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_4.getFormat()),dtstr,"-"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_5.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_5.getFormat()),dtstr,"/"),true);

        dtstr = DateTimeUtils.getDate2Str(DateTimeUtils.DateFormat.FORMATE_6.getFormat(), date1);
        Assert.assertEquals(delimit(DateTimeUtils.getCurrentDateStr(DateTimeUtils.DateFormat.FORMATE_6.getFormat()),dtstr,":"),true);
    }

    /**
     * Method: getDay()
     */
    @Test
    public void testGetDay() throws Exception {

    }

    /**
     * Method: getNowTime()
     */
    @Test
    public void testGetNowTime() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMin(Date date)
     */
    @Test
    public void testGetTimeMinDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMax(Date date)
     */
    @Test
    public void testGetTimeMaxDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMin()
     */
    @Test
    public void testGetTimeMin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeMax()
     */
    @Test
    public void testGetTimeMax() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getStrToDate(String format, String str)
     */
    @Test
    public void testGetStrToDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: date2String(Date date)
     */
    @Test
    public void testDate2StringDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: dateFormatString(Date date)
     */
    @Test
    public void testDateFormatString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: date2String(Date date, String format)
     */
    @Test
    public void testDate2StringForDateFormat() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: isDateTime(String dateTime, String pattern)
     */
    @Test
    public void testIsDateTime() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getXMLGregorianCalendar(Date date)
     */
    @Test
    public void testGetXMLGregorianCalendar() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromXmlGregorianCalendar(XMLGregorianCalendar da)
     */
    @Test
    public void testGetDateFromXmlGregorianCalendar() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: passTime(Date tempDate, int hour)
     */
    @Test
    public void testPassTime() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLimitDate(int hour)
     */
    @Test
    public void testGetLimitDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeAfterMinute(int minute, Date date)
     */
    @Test
    public void testGetTimeAfterMinute() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeBeforeHour(int hour, Date date)
     */
    @Test
    public void testGetTimeBeforeHour() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeAfterHour(int hour, Date date)
     */
    @Test
    public void testGetTimeAfterHour() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateAfterDate(int day, Date date)
     */
    @Test
    public void testGetDateAfterDateForDayDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAfterDate(int day)
     */
    @Test
    public void testGetAfterDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeMinute(int minute)
     */
    @Test
    public void testGetBeforeMinute() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeHour(int hour)
     */
    @Test
    public void testGetBeforeHour() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTimeBeforeMinute(int minute, Date date)
     */
    @Test
    public void testGetTimeBeforeMinute() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateBeforeDate(int day, Date date)
     */
    @Test
    public void testGetDateBeforeDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDate(int day)
     */
    @Test
    public void testGetBeforeDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateTime(int day)
     */
    @Test
    public void testGetBeforeDateTime() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateStr(int day)
     */
    @Test
    public void testGetBeforeDateStrDay() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateStr(int day, String format)
     */
    @Test
    public void testGetBeforeDateStrForDayFormat() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBeforeDateTimeStr(int day)
     */
    @Test
    public void testGetBeforeDateTimeStr() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLimitDateByMinute(int minute)
     */
    @Test
    public void testGetLimitDateByMinute() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: isValidDate(String sDate)
     */
    @Test
    public void testIsValidDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: dateIntervalForDate(Date date1, Date date2, String type)
     */
    @Test
    public void testDateIntervalForDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: dateIntervalForString(String date1, String date2, String type)
     */
    @Test
    public void testDateIntervalForString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: coreDateInterval(Date date1, Date date2, String type)
     */
    @Test
    public void testCoreDateInterval() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBetweenDayMs()
     */
    @Test
    public void testGetBetweenDayMs() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPreMonth()
     */
    @Test
    public void testGetPreMonth() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDate2String(Date date, String format)
     */
    @Test
    public void testGetDate2String() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromString(String date)
     */
    @Test
    public void testGetDateFromString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDateFromStr(String date, String format)
     */
    @Test
    public void testGetDateFromStr() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getString2Date(String date, String format)
     */
    @Test
    public void testGetString2Date() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFormatDate(Date date, String format)
     */
    @Test
    public void testGetFormatDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSwitchPassword()
     */
    @Test
    public void testGetSwitchPassword() throws Exception {
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


} 
