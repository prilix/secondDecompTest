package com.jch.racWiFi.timerPickerDialog.data.source;

import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;
import com.jch.racWiFi.timerPickerDialog.data.WheelCalendar;
import com.jch.racWiFi.timerPickerDialog.utils.C2363Utils;
import java.util.Calendar;

public class TimeRepository implements TimeDataSource {
    WheelCalendar mCalendarMax;
    WheelCalendar mCalendarMin;
    boolean mIsMaxNoRange = this.mCalendarMax.isNoRange();
    boolean mIsMinNoRange = this.mCalendarMin.isNoRange();
    PickerConfig mPickerConfig;

    public TimeRepository(PickerConfig pickerConfig) {
        this.mPickerConfig = pickerConfig;
        this.mCalendarMin = pickerConfig.mMinCalendar;
        this.mCalendarMax = pickerConfig.mMaxCalendar;
    }

    public int getMinYear() {
        if (this.mIsMinNoRange) {
            return 2015;
        }
        return this.mCalendarMin.year;
    }

    public int getMaxYear() {
        if (this.mIsMaxNoRange) {
            return getMinYear() + 50;
        }
        return this.mCalendarMax.year;
    }

    public int getMinMonth(int i) {
        if (!this.mIsMinNoRange) {
            if (C2363Utils.isTimeEquals(this.mCalendarMin, i)) {
                return this.mCalendarMin.month;
            }
        }
        return 1;
    }

    public int getMaxMonth(int i) {
        if (this.mIsMaxNoRange) {
            return 12;
        }
        if (C2363Utils.isTimeEquals(this.mCalendarMax, i)) {
            return this.mCalendarMax.month;
        }
        return 12;
    }

    public int getMinDay(int i, int i2) {
        if (!this.mIsMinNoRange) {
            if (C2363Utils.isTimeEquals(this.mCalendarMin, i, i2)) {
                return this.mCalendarMin.day;
            }
        }
        return 1;
    }

    public int getMaxDay(int i, int i2) {
        if (!this.mIsMaxNoRange) {
            if (C2363Utils.isTimeEquals(this.mCalendarMax, i, i2)) {
                return this.mCalendarMax.day;
            }
        }
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(5, 1);
        instance.set(2, i2 - 1);
        return instance.getActualMaximum(5);
    }

    public int getMinHour(int i, int i2, int i3) {
        if (!this.mIsMinNoRange) {
            if (C2363Utils.isTimeEquals(this.mCalendarMin, i, i2, i3)) {
                return this.mCalendarMin.hour;
            }
        }
        return 0;
    }

    public int getMaxHour(int i, int i2, int i3) {
        if (this.mIsMaxNoRange) {
            return 23;
        }
        if (C2363Utils.isTimeEquals(this.mCalendarMax, i, i2, i3)) {
            return this.mCalendarMax.hour;
        }
        return 23;
    }

    public int getMinMinute(int i, int i2, int i3, int i4) {
        if (!this.mIsMinNoRange) {
            if (C2363Utils.isTimeEquals(this.mCalendarMin, i, i2, i3, i4)) {
                return this.mCalendarMin.minute + 1;
            }
        }
        return 0;
    }

    public int getMaxMinute(int i, int i2, int i3, int i4) {
        if (this.mIsMaxNoRange) {
            return 5;
        }
        if (C2363Utils.isTimeEquals(this.mCalendarMax, i, i2, i3, i4)) {
            return this.mCalendarMax.minute;
        }
        return 5;
    }

    public boolean isMinYear(int i) {
        return C2363Utils.isTimeEquals(this.mCalendarMin, i);
    }

    public boolean isMinMonth(int i, int i2) {
        return C2363Utils.isTimeEquals(this.mCalendarMin, i, i2);
    }

    public boolean isMinDay(int i, int i2, int i3) {
        return C2363Utils.isTimeEquals(this.mCalendarMin, i, i2, i3);
    }

    public boolean isMinHour(int i, int i2, int i3, int i4) {
        return C2363Utils.isTimeEquals(this.mCalendarMin, i, i2, i3, i4);
    }

    public WheelCalendar getDefaultCalendar() {
        return this.mPickerConfig.mCurrentCalendar;
    }
}
