package utils;

public enum WeekDays {
    Sun("Sunday", "���j��"),
    Mon("Monday", "���j��"),
    Tue("Tuesday", "�Ηj��"),
    Wed("Wednesday", "���j��"),
    Thu("Thursday", "�ؗj��"),
    Fri("Friday", "���j��"),
    Sat("Saturday", "�y�j��");
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
