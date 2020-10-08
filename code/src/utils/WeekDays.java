package utils;

public enum WeekDays {
    Sun("Sunday", "日曜日"),
    Mon("Monday", "月曜日"),
    Tue("Tuesday", "火曜日"),
    Wed("Wednesday", "水曜日"),
    Thu("Thursday", "木曜日"),
    Fri("Friday", "金曜日"),
    Sat("Saturday", "土曜日");

    private final String day_en;
    private final String day_jp;

    WeekDays(String day_en, String day_jp) {
        this.day_en = day_en;
        this.day_jp = day_jp;
    }

    public String getDay_en() {
        return day_en;
    }

    public String getDay_jp() {
        return day_jp;
    }
}
