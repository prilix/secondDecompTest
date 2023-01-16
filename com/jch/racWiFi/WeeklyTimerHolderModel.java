package com.jch.racWiFi;

import android.os.Parcelable;

public class WeeklyTimerHolderModel extends TimerHolderModel implements Parcelable {
    public boolean allDay;
    public boolean[] days = new boolean[7];
    public boolean enabled;

    public void copy(WeeklyTimerHolderModel weeklyTimerHolderModel) {
        this.days = weeklyTimerHolderModel.days;
        this.allDay = weeklyTimerHolderModel.allDay;
        this.enabled = weeklyTimerHolderModel.enabled;
        super.copy(weeklyTimerHolderModel);
    }
}
