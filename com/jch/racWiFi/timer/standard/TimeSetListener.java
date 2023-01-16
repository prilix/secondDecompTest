package com.jch.racWiFi.timer.standard;

import com.jch.racWiFi.timer.util.TimeFormat;

public interface TimeSetListener {
    void onTimeSet(int i, int i2, TimeFormat timeFormat, String str);
}
