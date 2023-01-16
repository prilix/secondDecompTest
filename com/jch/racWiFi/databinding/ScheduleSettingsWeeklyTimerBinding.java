package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerScheduleSettingsViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ScheduleSettingsWeeklyTimerBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ConstraintLayout constraintLayout;
    public final Guideline guideline117;
    public final Guideline guideline139;
    public final Guideline guideline149;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final Guideline guideline186;
    public final Guideline guideline187;
    public final Guideline guideline188;
    public final Guideline guideline191;
    public final Guideline guideline192;
    public final Guideline guideline200;
    public final Guideline guideline201;
    public final Guideline guideline233;
    public final Guideline guideline234;
    public final ConstraintLayout helpBubbleLayoutBottom;
    public final ImageButton imageButtonDecreaseTemprature;
    public final ImageButton imageButtonHelp;
    public final ImageButton imageButtonIncreaseTemprature;
    public final ImageButton imageButtonPickStartTime;
    public final ImageView imageViewArrowDownMode;
    public final ImageView imageViewSelectedModeHome;
    public final ConstraintLayout layout3;
    public final ConstraintLayout layoutEndTime;
    public final ConstraintLayout layoutModeRoomDeviceControl;
    public final ConstraintLayout layoutStartTime;
    public final ConstraintLayout layoutTop;
    @Bindable
    protected WeeklyTimerScheduleSettingsViewModel mWeeklyTimerScheduleSettingsViewModel;
    public final ConstraintLayout parent;
    public final ConstraintLayout rootLayout;
    public final ScrollView scrollView;
    public final CustomSwitchButton switchWeeklyTimer;
    public final TextView textViewEndTimeTitle;
    public final TextView textViewEndTimeWeektyTimer;
    public final TextView textViewHelpContent;
    public final TextView textViewHumidityPercentageIduControl;
    public final TextView textViewHumidityTitleRoomdeviceControl;
    public final TextView textViewPercent;
    public final TextView textViewRoomTitle;
    public final ImageButton textViewSave;
    public final TextView textViewScheduleSettingsTitle;
    public final TextView textViewSelectedModeHome;
    public final TextView textViewSetTemp;
    public final TextView textViewStartTimeTitle;
    public final TextView textViewStartTimeWeektyTimer;
    public final TextView textViewTemprature;
    public final TextView textViewTempratureUnit;

    public abstract void setWeeklyTimerScheduleSettingsViewModel(WeeklyTimerScheduleSettingsViewModel weeklyTimerScheduleSettingsViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ScheduleSettingsWeeklyTimerBinding(Object obj, View view, int i, ImageButton imageButton, ConstraintLayout constraintLayout2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, Guideline guideline12, Guideline guideline13, Guideline guideline14, ConstraintLayout constraintLayout3, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageButton imageButton5, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ScrollView scrollView2, CustomSwitchButton customSwitchButton, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, ImageButton imageButton6, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.constraintLayout = constraintLayout2;
        this.guideline117 = guideline;
        this.guideline139 = guideline2;
        this.guideline149 = guideline3;
        this.guideline158 = guideline4;
        this.guideline159 = guideline5;
        this.guideline186 = guideline6;
        this.guideline187 = guideline7;
        this.guideline188 = guideline8;
        this.guideline191 = guideline9;
        this.guideline192 = guideline10;
        this.guideline200 = guideline11;
        this.guideline201 = guideline12;
        this.guideline233 = guideline13;
        this.guideline234 = guideline14;
        this.helpBubbleLayoutBottom = constraintLayout3;
        this.imageButtonDecreaseTemprature = imageButton2;
        this.imageButtonHelp = imageButton3;
        this.imageButtonIncreaseTemprature = imageButton4;
        this.imageButtonPickStartTime = imageButton5;
        this.imageViewArrowDownMode = imageView;
        this.imageViewSelectedModeHome = imageView2;
        this.layout3 = constraintLayout4;
        this.layoutEndTime = constraintLayout5;
        this.layoutModeRoomDeviceControl = constraintLayout6;
        this.layoutStartTime = constraintLayout7;
        this.layoutTop = constraintLayout8;
        this.parent = constraintLayout9;
        this.rootLayout = constraintLayout10;
        this.scrollView = scrollView2;
        this.switchWeeklyTimer = customSwitchButton;
        this.textViewEndTimeTitle = textView;
        this.textViewEndTimeWeektyTimer = textView2;
        this.textViewHelpContent = textView3;
        this.textViewHumidityPercentageIduControl = textView4;
        this.textViewHumidityTitleRoomdeviceControl = textView5;
        this.textViewPercent = textView6;
        this.textViewRoomTitle = textView7;
        this.textViewSave = imageButton6;
        this.textViewScheduleSettingsTitle = textView8;
        this.textViewSelectedModeHome = textView9;
        this.textViewSetTemp = textView10;
        this.textViewStartTimeTitle = textView11;
        this.textViewStartTimeWeektyTimer = textView12;
        this.textViewTemprature = textView13;
        this.textViewTempratureUnit = textView14;
    }

    public WeeklyTimerScheduleSettingsViewModel getWeeklyTimerScheduleSettingsViewModel() {
        return this.mWeeklyTimerScheduleSettingsViewModel;
    }

    public static ScheduleSettingsWeeklyTimerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ScheduleSettingsWeeklyTimerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ScheduleSettingsWeeklyTimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.schedule_settings_weekly_timer, viewGroup, z, obj);
    }

    public static ScheduleSettingsWeeklyTimerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ScheduleSettingsWeeklyTimerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ScheduleSettingsWeeklyTimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.schedule_settings_weekly_timer, (ViewGroup) null, false, obj);
    }

    public static ScheduleSettingsWeeklyTimerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ScheduleSettingsWeeklyTimerBinding bind(View view, Object obj) {
        return (ScheduleSettingsWeeklyTimerBinding) bind(obj, view, R.layout.schedule_settings_weekly_timer);
    }
}
