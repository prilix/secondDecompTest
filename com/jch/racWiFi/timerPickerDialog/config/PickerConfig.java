package com.jch.racWiFi.timerPickerDialog.config;

import com.jch.racWiFi.timerPickerDialog.data.Type;
import com.jch.racWiFi.timerPickerDialog.data.WheelCalendar;
import com.jch.racWiFi.timerPickerDialog.listener.OnDateSetListener;

public class PickerConfig {
    public boolean cyclic = true;
    public OnDateSetListener mCallBack;
    public String mCancelString = DefaultConfig.CANCEL;
    public WheelCalendar mCurrentCalendar = new WheelCalendar(System.currentTimeMillis());
    public String mCurrentTimer = DefaultConfig.CURRENT_TIMER;
    public String mDay = DefaultConfig.DAY;
    public String mHour = DefaultConfig.HOUR;
    public WheelCalendar mMaxCalendar = new WheelCalendar(0);
    public WheelCalendar mMinCalendar = new WheelCalendar(0);
    public String mMinute = DefaultConfig.MINUTE;
    public String mMonth = DefaultConfig.MONTH;
    public String mSureString = DefaultConfig.SURE;
    public int mThemeColor = DefaultConfig.COLOR;
    public String mTitleString = DefaultConfig.TITLE;
    public int mToolBarTVColor = -1;
    public Type mType = DefaultConfig.TYPE;
    public int mWheelRVNormalSize = 18;
    public int mWheelRVSelectorSize = 22;
    public int mWheelTVNormalColor = DefaultConfig.TV_NORMAL_COLOR;
    public int mWheelTVSelectorColor = DefaultConfig.TV_SELECTOR_COLOR;
    public int mWheelTVSize = 12;
    public String mYear = DefaultConfig.YEAR;
    public int stepsForMinute = 10;
}
