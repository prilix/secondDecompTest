package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch_hitachi.aircloudglobal.R;

public class WeeklyTimerScheduleSettingsFragmentModelWise_ViewBinding implements Unbinder {
    private WeeklyTimerScheduleSettingsFragmentModelWise target;

    public WeeklyTimerScheduleSettingsFragmentModelWise_ViewBinding(WeeklyTimerScheduleSettingsFragmentModelWise weeklyTimerScheduleSettingsFragmentModelWise, View view) {
        this.target = weeklyTimerScheduleSettingsFragmentModelWise;
        weeklyTimerScheduleSettingsFragmentModelWise.increase_temperature = (ImageButton) C0840Utils.findRequiredViewAsType(view, R.id.image_button_increase_temprature, "field 'increase_temperature'", ImageButton.class);
        weeklyTimerScheduleSettingsFragmentModelWise.decrease_temperature = (ImageButton) C0840Utils.findRequiredViewAsType(view, R.id.image_button_decrease_temprature, "field 'decrease_temperature'", ImageButton.class);
        weeklyTimerScheduleSettingsFragmentModelWise.temperature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature, "field 'temperature'", TextView.class);
        weeklyTimerScheduleSettingsFragmentModelWise.humidityPercentageIduControl = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_humidity_percentage_idu_control, "field 'humidityPercentageIduControl'", TextView.class);
        weeklyTimerScheduleSettingsFragmentModelWise.percentage = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_percent, "field 'percentage'", TextView.class);
        weeklyTimerScheduleSettingsFragmentModelWise.temperatureUnit = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_unit, "field 'temperatureUnit'", TextView.class);
        weeklyTimerScheduleSettingsFragmentModelWise.humidityTitleRoomDeviceControl = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_humidity_title_roomdevice_control, "field 'humidityTitleRoomDeviceControl'", TextView.class);
    }

    public void unbind() {
        WeeklyTimerScheduleSettingsFragmentModelWise weeklyTimerScheduleSettingsFragmentModelWise = this.target;
        if (weeklyTimerScheduleSettingsFragmentModelWise != null) {
            this.target = null;
            weeklyTimerScheduleSettingsFragmentModelWise.increase_temperature = null;
            weeklyTimerScheduleSettingsFragmentModelWise.decrease_temperature = null;
            weeklyTimerScheduleSettingsFragmentModelWise.temperature = null;
            weeklyTimerScheduleSettingsFragmentModelWise.humidityPercentageIduControl = null;
            weeklyTimerScheduleSettingsFragmentModelWise.percentage = null;
            weeklyTimerScheduleSettingsFragmentModelWise.temperatureUnit = null;
            weeklyTimerScheduleSettingsFragmentModelWise.humidityTitleRoomDeviceControl = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
