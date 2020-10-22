package utils;

/**
 * @Description: <p>
 * @Author: Mr.Miles
 * @Date: 2020/10/22
 */
public enum DateFormat {
    /**
     * @Description: 日期格式  yyyyMMddHHmmss
     */
    DATETIME("yyyyMMddHHmmss"),

    /**
     * @Description: 日期格式  yyyy-MM-dd HH:mm:ss
     */
    DATETIME_DEFAULT("yyyy-MM-dd HH:mm:ss"),

    /**
     * @Description: 日期格式  yyyy/MM/dd HH:mm:ss
     */
    DATETIME_SLASH("yyyy/MM/dd HH:mm:ss"),

    /**
     * @Description: 日期格式  yyyy年MM月dd日 HH:mm:ss
     */
    DATETIME_CN("yyyy年MM月dd日 HH:mm:ss"),

    /**
     * @Description: 日期格式  yyyy年MM月dd日 HH:mm:ss
     */
    DATETIME_JA("yyyy年MM月dd日 HH:mm:ss"),

    /**
     * @Description: 日期格式  yyyy-MM-dd'T'HH:mm:ss.SSS Z
     */
    DATETIME_UTC("yyyy-MM-dd'T'HH:mm:ss.SSS Z"),

    /**
     * @Description: 日期格式  yyyyMMdd
     */
    DATE("yyyyMMdd"),

    /**
     * @Description: 日期格式  yyyy-MM-dd
     */
    DATE_DEFAULT("yyyy-MM-dd"),

    /**
     * @Description: 日期格式  yyyy/MM/dd
     */
    DATE_SLASH("yyyy/MM/dd"),

    /**
     * @Description: 日期格式  yyyy年MM月dd日 HH:mm:ss
     */
    DATE_CN("yyyy年MM月dd日"),

    /**
     * @Description: 日期格式  yyyy年MM月dd日 HH:mm:ss
     */
    DATE_JA("yyyy年MM月dd日"),

    /**
     * @Description: 时间格式  HH:mm:ss
     */
    TIME("HH:mm:ss"),

    /**
     * @Description: 时间格式  HH时mm分ss秒
     */
    TIME_CN("HH时mm分ss秒"),

    /**
     * @Description: 时间格式  HH時mm分ss秒
     */
    TIME_JA("HH時mm分ss秒");

    private final String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return this.format;
    }
}
