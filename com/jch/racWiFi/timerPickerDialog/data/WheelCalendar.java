package com.jch.racWiFi.timerPickerDialog.data;

import java.util.Calendar;

public class WheelCalendar {
    public int day;
    public int hour;
    public int minute;
    public int month;
    private boolean noRange;
    public int year;

    public WheelCalendar(long j) {
        initData(j);
    }

    private void initData(long j) {
        if (j == 0) {
            this.noRange = true;
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.setTimeInMillis(j);
        this.year = instance.get(1);
        this.month = instance.get(2) + 1;
        this.day = instance.get(5);
        this.hour = instance.get(11);
        this.minute = instance.get(12);
    }

    public boolean isNoRange() {
        return this.noRange;
    }
}
