package utils;

public enum WeekDays {
    Sun("Sunday", "“ú—j“ú"),
    Mon("Monday", "Œ—j“ú"),
    Tue("Tuesday", "‰Î—j“ú"),
    Wed("Wednesday", "…—j“ú"),
    Thu("Thursday", "–Ø—j“ú"),
    Fri("Friday", "‹à—j“ú"),
    Sat("Saturday", "“y—j“ú");
    private String day_en;
    private String day_jp;

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
