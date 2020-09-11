package test.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.WeekDays;

/**
 * WeekDays Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9åé 7, 2020</pre>
 */
public class WeekDaysTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getDay_en()
     */
    @Test
    public void testGetDay_en() throws Exception {
        Assert.assertEquals(WeekDays.Mon.getDay_en(),"Monday");
        Assert.assertEquals(WeekDays.Tue.getDay_en(),"Tuesday");
        Assert.assertEquals(WeekDays.Wed.getDay_en(),"Wednesday");
        Assert.assertEquals(WeekDays.Thu.getDay_en(),"Thursday");
        Assert.assertEquals(WeekDays.Fri.getDay_en(),"Friday");
        Assert.assertEquals(WeekDays.Sat.getDay_en(),"Saturday");
        Assert.assertEquals(WeekDays.Sun.getDay_en(),"Sunday");
    }

    /**
     * Method: getDay_jp()
     */
    @Test
    public void testGetDay_jp() throws Exception {
        Assert.assertEquals(WeekDays.Mon.getDay_jp(),"åéójì˙");
        Assert.assertEquals(WeekDays.Tue.getDay_jp(),"âŒójì˙");
        Assert.assertEquals(WeekDays.Wed.getDay_jp(),"êÖójì˙");
        Assert.assertEquals(WeekDays.Thu.getDay_jp(),"ñÿójì˙");
        Assert.assertEquals(WeekDays.Fri.getDay_jp(),"ã‡ójì˙");
        Assert.assertEquals(WeekDays.Sat.getDay_jp(),"ìyójì˙");
        Assert.assertEquals(WeekDays.Sun.getDay_jp(),"ì˙ójì˙");
    }

    /**
     * Method: values()
     */
    @Test
    public void testValues() throws Exception {

    }

    /**
     * Method: valueOf(java.lang.String name)
     */
    @Test
    public void testValueOf() throws Exception {
        Assert.assertEquals(WeekDays.valueOf("Mon"),WeekDays.Mon);
        Assert.assertEquals(WeekDays.valueOf("Tue"),WeekDays.Tue);
        Assert.assertEquals(WeekDays.valueOf("Wed"),WeekDays.Wed);
        Assert.assertEquals(WeekDays.valueOf("Thu"),WeekDays.Thu);
        Assert.assertEquals(WeekDays.valueOf("Fri"),WeekDays.Fri);
        Assert.assertEquals(WeekDays.valueOf("Sat"),WeekDays.Sat);
        Assert.assertEquals(WeekDays.valueOf("Sun"),WeekDays.Sun);
    }

}
