package com.jch.racWiFi.timerPickerDialog.data.source;

import com.jch.racWiFi.timerPickerDialog.data.WheelCalendar;

public interface TimeDataSource {
    WheelCalendar getDefaultCalendar();

    int getMaxDay(int i, int i2);

    int getMaxHour(int i, int i2, int i3);

    int getMaxMinute(int i, int i2, int i3, int i4);

    int getMaxMonth(int i);

    int getMaxYear();

    int getMinDay(int i, int i2);

    int getMinHour(int i, int i2, int i3);

    int getMinMinute(int i, int i2, int i3, int i4);

    int getMinMonth(int i);

    int getMinYear();

    boolean isMinDay(int i, int i2, int i3);

    boolean isMinHour(int i, int i2, int i3, int i4);

    boolean isMinMonth(int i, int i2);

    boolean isMinYear(int i);
}
