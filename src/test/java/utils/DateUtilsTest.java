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
    void getTimeMin() {
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMin(date1)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMin(date2)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMin(new Date())));
    }

    @Test
    void getStr2Date() {
        Assertions.assertEquals(this.date1.toString(), DateUtils.getStr2Date(this.dataTimeStr1).toString());
        Assertions.assertEquals(this.date2.toString(), DateUtils.getStr2Date(this.dataTimeStr2).toString());

        Assertions.assertEquals(this.date1.toString(), DateUtils.getStr2Date(this.dataTimeStr1, DateFormat.DATETIME_DEFAULT).toString());
        Assertions.assertEquals(this.date2.toString(), DateUtils.getStr2Date(this.dataTimeStr2, DateFormat.DATETIME_DEFAULT).toString());
    }

    @Test
    void getTimeMax() {
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMax(date1)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMax(date2)));
        System.out.println(DateUtils.getDate2Str(DateUtils.getTimeMin(new Date())));
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
    void getLimitDate() {
        Date date = DateUtils.getLimitDate(1);
        String str = DateUtils.getDate2Str(date);
        System.out.println(str);

        date = DateUtils.getLimitDate(10);
        str = DateUtils.getDate2Str(date);
        System.out.println(str);

        date = DateUtils.getLimitDate(24);
        str = DateUtils.getDate2Str(date);
        System.out.println(str);
    }

    @Test
    void getTimeAfterMinute() {
        Date date = DateUtils.getLimitDate(1);
        String str = DateUtils.getDate2Str(date);
        System.out.println(str);

        date = DateUtils.getLimitDate(10);
        str = DateUtils.getDate2Str(date);
        System.out.println(str);

        date = DateUtils.getLimitDate(24);
        str = DateUtils.getDate2Str(date);
        System.out.println(str);
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
    }

    @Test
    void getDayEnd() {
    }

    @Test
    void get5MStart() {
    }

    @Test
    void get5MEnd() {
    }

    @Test
    void getHourStart() {
    }

    @Test
    void getHourEnd() {
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
}