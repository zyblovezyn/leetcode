package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

class DateUtilsTest {

    private Date date1;
    private Date date2;

    private String dataTimeStr1;
    private String dataTimeStr2;

    private String timeStr1;
    private String timeStr2;

    private String dateStr1;
    private String dateStr2;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 9, 9, 8, 8, 8);
        date1 = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.set(2020, 8, 9, 18, 18, 18);
        date2 = calendar.getTime();

        dataTimeStr1 = "2020-10-09 08:08:08";
        dataTimeStr2 = "2020-09-09 18:18:18";

        timeStr1 = "08:08:08";
        timeStr2 = "18:18:18";

        dateStr1 = "2020-10-09";
        dateStr2 = "2020-09-09";
    }

    @AfterEach
    void tearDown() {
        this.date1 = null;
        this.date2 = null;
        this.dataTimeStr1 = null;
        this.dataTimeStr2 = null;
    }

    @Test
    void translateToSeconds() {
        long seconds = DateUtils.translateToSeconds(1000);
        Assertions.assertEquals(1, seconds);
        seconds = DateUtils.translateToSeconds(10 * 1000);
        Assertions.assertEquals(10, seconds);
        seconds = DateUtils.translateToSeconds(15 * 1000);
        Assertions.assertEquals(15, seconds);
        seconds = DateUtils.translateToSeconds(100 * 1000);
        Assertions.assertEquals(100, seconds);
    }

    @Test
    void translateToMinutes() {
        long minutes = DateUtils.translateToMinutes(60 * 1000);
        Assertions.assertEquals(1, minutes);
        minutes = DateUtils.translateToMinutes(60 * 10 * 1000);
        Assertions.assertEquals(10, minutes);
        minutes = DateUtils.translateToMinutes(60 * 15 * 1000);
        Assertions.assertEquals(15, minutes);
        minutes = DateUtils.translateToMinutes(60 * 100 * 1000);
        Assertions.assertEquals(100, minutes);
    }

    @Test
    void translateToHours() {
        long hours = DateUtils.translateToHours(60 * 60 * 1000);
        Assertions.assertEquals(1, hours);
        hours = DateUtils.translateToHours(60 * 60 * 10 * 1000);
        Assertions.assertEquals(10, hours);
        hours = DateUtils.translateToHours(60 * 60 * 15 * 1000);
        Assertions.assertEquals(15, hours);
        hours = DateUtils.translateToHours(60 * 60 * 100 * 1000);
        Assertions.assertEquals(100, hours);
    }

    @Test
    void translateToDays() {
        long days = DateUtils.translateToDays(24 * 60 * 60 * 1000L);
        Assertions.assertEquals(1, days);
        days = DateUtils.translateToDays(24 * 60 * 60 * 10 * 1000L);
        Assertions.assertEquals(10, days);
        days = DateUtils.translateToDays(24 * 60 * 60 * 15 * 1000L);
        Assertions.assertEquals(15, days);
        days = DateUtils.translateToDays(24 * 60 * 60 * 100 * 1000L);
        Assertions.assertEquals(100, days);
    }

    @Test
    void getCurrentDateStr() {
        System.out.println(DateUtils.getCurrentDateStr());
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATETIME_DEFAULT), DateFormat.DATETIME_DEFAULT);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATETIME), DateFormat.DATETIME);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATETIME_SLASH), DateFormat.DATETIME_SLASH);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATE), DateFormat.DATE);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATE_DEFAULT), DateFormat.DATE_DEFAULT);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.DATE_SLASH), DateFormat.DATE_SLASH);
        CommonUtils.print(DateUtils.getCurrentDateStr(DateFormat.TIME), DateFormat.TIME);
    }

    @Test
    void getTime() {
        Assertions.assertEquals(DateUtils.getTime(date1), this.timeStr1);
        Assertions.assertEquals(DateUtils.getTime(date2), this.timeStr2);
    }

    @Test
    void getStr2Date() {
        Assertions.assertEquals(this.date1.toString(), DateUtils.getStr2Date(this.dataTimeStr1).toString());
        Assertions.assertEquals(this.date2.toString(), DateUtils.getStr2Date(this.dataTimeStr2).toString());

        Assertions.assertEquals(this.date1.toString(), DateUtils.getStr2Date(this.dataTimeStr1, DateFormat.DATETIME_DEFAULT).toString());
        Assertions.assertEquals(this.date2.toString(), DateUtils.getStr2Date(this.dataTimeStr2, DateFormat.DATETIME_DEFAULT).toString());
    }

    @Test
    void getDate2Str() {
        Assertions.assertEquals(this.dataTimeStr1, DateUtils.getDate2Str(this.date1));
        Assertions.assertEquals(this.dataTimeStr2, DateUtils.getDate2Str(this.date2));
    }

    @Test
    void isDateTime() {
        Assertions.assertTrue(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATETIME_DEFAULT));
        Assertions.assertTrue(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATETIME));
        Assertions.assertFalse(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATETIME_SLASH));
        Assertions.assertTrue(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATE));
        Assertions.assertFalse(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATE_SLASH));
        Assertions.assertTrue(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.DATE_DEFAULT));
        Assertions.assertFalse(DateUtils.isDateTime(this.dataTimeStr1, DateFormat.TIME));
    }

    @Test
    void getXMLGregorianCalendar() {
    }

    @Test
    void getDateFromXmlGregorianCalendar() {
    }

    @Test
    void passTime() {
    }

    @Test
    void testGetTimeAfterMinute() {
    }

    @Test
    void getDateBeforeHour() {
    }

    @Test
    void testGetDateBeforeHour() {
    }

    @Test
    void getDateAfterHour() {
        //dataTimeStr1 = "2020-10-09 08:08:08";
        Date date = DateUtils.getDateAfterHour(1, this.date1);
        String str = DateUtils.getDate2Str(date);
        Assertions.assertEquals("2020-10-09 09:08:08", str);

        date = DateUtils.getDateAfterHour(10, this.date1);
        str = DateUtils.getDate2Str(date);
        Assertions.assertEquals("2020-10-09 18:08:08", str);

        date = DateUtils.getDateAfterHour(-1, this.date1);
        str = DateUtils.getDate2Str(date);
        Assertions.assertEquals("2020-10-09 07:08:08", str);

        date = DateUtils.getDateAfterHour(-10, this.date1);
        str = DateUtils.getDate2Str(date);
        Assertions.assertEquals("2020-10-08 22:08:08", str);
    }

    @Test
    void testGetDateAfterHour() {
    }

    @Test
    void testGetDate2Str() {
    }

    @Test
    void getDateAfterDate() {
    }

    @Test
    void testGetDateAfterDate() {
    }

    @Test
    void getBeforeMinute() {
    }

    @Test
    void getBeforeHour() {
    }

    @Test
    void getTimeBeforeMinute() {
    }

    @Test
    void getDateBeforeDate() {
    }

    @Test
    void getBeforeDate() {
    }

    @Test
    void getBeforeDateTime() {
    }

    @Test
    void getBeforeDateStr() {
    }

    @Test
    void testGetBeforeDateStr() {
    }

    @Test
    void getBeforeDateTimeStr() {
    }

    @Test
    void getLimitDateByMinute() {
    }

    @Test
    void isValidDate() {
        boolean isDate = DateUtils.isValidDate("2020-1-1");
        Assertions.assertTrue(isDate);

        isDate = DateUtils.isValidDate("2020-1-32");
        Assertions.assertFalse(isDate);

        isDate = DateUtils.isValidDate("2020-13-15");
        Assertions.assertFalse(isDate);

        isDate = DateUtils.isValidDate("2020-11-15");
        Assertions.assertTrue(isDate);

        isDate = DateUtils.isValidDate("201-11-15");
        Assertions.assertFalse(isDate);

        isDate = DateUtils.isValidDate("20201115");
        Assertions.assertTrue(isDate);

        isDate = DateUtils.isValidDate("2020/11/15");
        Assertions.assertTrue(isDate);
    }

    @Test
    void dateIntervalForDate() {
    }

    @Test
    void coreDateInterval() {
    }

    @Test
    void dateIntervalForString() {
    }

    @Test
    void getBetweenDayMs() {
    }

    @Test
    void getPreMonth() {
    }

    @Test
    void getDateFromString() {
    }

    @Test
    void getDateFromStr() {
    }

    @Test
    void getSwitchPasswordUrlParam() {
    }

    @Test
    void getSwitchPassword() {
    }

    @Test
    void getLastDateStr() {
    }

    @Test
    void getDateStrByLong() {
    }

    @Test
    void getDayDiff() {
    }

    @Test
    void get5MinuteDiff() {
    }

    @Test
    void getHourDiff() {
    }

    @Test
    void getDayStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDayStart(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 00:00:00", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.getDayStart(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 00:00:00", dateStr1);
    }

    @Test
    void getDayEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDayEnd(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 23:59:59", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.getDayEnd(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 23:59:59", dateStr1);
    }

    @Test
    void get5MStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.get5MinutesStart(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:03:00", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.get5MinutesStart(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:13:00", dateStr1);
    }

    @Test
    void get5MEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.get5MinutesEnd(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:13:59", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.get5MinutesEnd(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:23:59", dateStr1);
    }

    @Test
    void getHourStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getHourStart(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:00:00", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.getHourStart(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:00:00", dateStr1);
    }

    @Test
    void getHourEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getHourEnd(this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:59:59", dateStr1);

        //date2 = "2020-09-09 18:18:18";
        date1 = DateUtils.getHourEnd(this.date2);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:59:59", dateStr1);
    }

    @Test
    void getBeforeDateInfo() {
    }

    @Test
    void formatDateUTC() {
    }

    @Test
    void testGetDateAfterDate1() {
    }

    @Test
    void getDateAfterMinute() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDateAfterMinute(1, this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:09:08", dateStr1);

        date1 = DateUtils.getDateAfterMinute(10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:18:08", dateStr1);

        date1 = DateUtils.getDateAfterMinute(61, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 09:09:08", dateStr1);

        date1 = DateUtils.getDateAfterMinute(-1, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:07:08", dateStr1);

        date1 = DateUtils.getDateAfterMinute(-10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 07:58:08", dateStr1);
    }

    @Test
    void getDateAfterDay() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDateAfterDay(1, this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-10 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterDay(10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-19 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterDay(25, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-11-03 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterDay(-1, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-08 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterDay(-10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-29 08:08:08", dateStr1);
    }

    @Test
    void getDateAfterMonth() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDateAfterMonth(1, this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-11-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterMonth(10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2021-08-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterMonth(-1, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterMonth(-14, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2019-08-09 08:08:08", dateStr1);
    }

    @Test
    void getDateAfterYear() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getDateAfterYear(1, this.date1);
        String dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2021-10-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterYear(10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2030-10-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterYear(-1, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2019-10-09 08:08:08", dateStr1);

        date1 = DateUtils.getDateAfterYear(-10, this.date1);
        dateStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2010-10-09 08:08:08", dateStr1);
    }

    @Test
    void get5MinuteStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.get5MinutesStart(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:03:00", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.get5MinutesStart(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:13:00", dataStr1);
    }

    @Test
    void get5MinuteEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.get5MinutesEnd(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-09 08:13:59", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.get5MinutesEnd(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-09 18:23:59", dataStr1);
    }

    @Test
    void getWeekStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getWeekStart(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-04 00:00:00", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getWeekStart(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-06 00:00:00", dataStr1);
    }

    @Test
    void getWeekEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getWeekEnd(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-10 23:59:59", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getWeekEnd(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-12 23:59:59", dataStr1);
    }

    @Test
    void getMonthStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getMonthStart(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-01 00:00:00", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getMonthStart(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-01 00:00:00", dataStr1);
    }

    @Test
    void getMonthEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getMonthEnd(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-10-31 23:59:59", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getMonthEnd(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-09-30 23:59:59", dataStr1);
    }

    @Test
    void getYearStart() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getYearStart(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-01-01 00:00:00", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getYearStart(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-01-01 00:00:00", dataStr1);
    }

    @Test
    void getYearEnd() {
        //this.date1 = 2020-10-09 08:08:08
        Date date1 = DateUtils.getYearEnd(this.date1);
        String dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-12-31 23:59:59", dataStr1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getYearEnd(this.date2);
        dataStr1 = DateUtils.getDate2Str(date1);
        Assertions.assertEquals("2020-12-31 23:59:59", dataStr1);
    }

    @Test
    void getLong2Str() {
        //this.date1 = 2020-10-09 08:08:08
        String date1 = DateUtils.getLong2Str(this.date1.getTime());
        Assertions.assertEquals("2020-10-09 08:08:08", date1);

        //this.date2 = 2020-09-09 18:18:18
        date1 = DateUtils.getLong2Str(this.date2.getTime());
        Assertions.assertEquals("2020-09-09 18:18:18", date1);
    }

    @Test
    void testGetLong2Str() {
        //this.date1 = 2020-10-09 08:08:08
        String date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATETIME);
        Assertions.assertEquals("20201009080808", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.TIME);
        Assertions.assertEquals("08:08:08", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATE);
        Assertions.assertEquals("20201009", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATE_DEFAULT);
        Assertions.assertEquals("2020-10-09", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATE_SLASH);
        Assertions.assertEquals("2020/10/09", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATETIME_DEFAULT);
        Assertions.assertEquals("2020-10-09 08:08:08", date1);

        date1 = DateUtils.getLong2Str(this.date1.getTime(), DateFormat.DATETIME_SLASH);
        Assertions.assertEquals("2020/10/09 08:08:08", date1);
    }
}