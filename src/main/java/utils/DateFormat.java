package utils;

public enum DateFormat {
    /**
     * @Description: 日期格式yyyyMMddHHmmss
     */
    DATETIME("yyyyMMddHHmmss"),

    /**
     * @Description: 日期格式yyyy-MM-dd HH:mm:ss
     */
    DATETIME_DEFAULT("yyyy-MM-dd HH:mm:ss"),

    /**
     * @Description: 日期格式yyyy/MM/dd HH:mm:ss
     */
    DATETIME_SLASH("yyyy/MM/dd HH:mm:ss"),

    /**
     * @Description: 日期格式yyyyMMdd
     */
    DATE("yyyyMMdd"),

    /**
     * @Description: 日期格式yyyy-MM-dd
     */
    DATE_DEFAULT("yyyy-MM-dd"),

    /**
     * @Description: 日期格式yyyy/MM/dd
     */
    DATE_SLASH("yyyy/MM/dd"),

    /**
     * @Description: 时间格式HH:mm:ss
     */
    TIME("HH:mm:ss");

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
