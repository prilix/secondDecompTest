package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerSelectDeviceViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerMainFrameBinding extends ViewDataBinding {
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final ImageButton imageButtonCopyWeeklyTimer;
    public final ImageButton imageButtonMenu;
    public final WeeklyTimerMainVdBinding include;
    @Bindable
    protected WeeklyTimerSelectDeviceViewModel mWeeklyTimerSelcetDeviceViewModel;
    public final TextView textViewWeeklyTimerTitle;

    public abstract void setWeeklyTimerSelcetDeviceViewModel(WeeklyTimerSelectDeviceViewModel weeklyTimerSelectDeviceViewModel);

    protected WeeklyTimerMainFrameBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, ImageButton imageButton2, WeeklyTimerMainVdBinding weeklyTimerMainVdBinding, TextView textView) {
        super(obj, view, i);
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.imageButtonCopyWeeklyTimer = imageButton;
        this.imageButtonMenu = imageButton2;
        this.include = weeklyTimerMainVdBinding;
        this.textViewWeeklyTimerTitle = textView;
    }

    public WeeklyTimerSelectDeviceViewModel getWeeklyTimerSelcetDeviceViewModel() {
        return this.mWeeklyTimerSelcetDeviceViewModel;
    }

    public static WeeklyTimerMainFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerMainFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_main_frame, viewGroup, z, obj);
    }

    public static WeeklyTimerMainFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerMainFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_main_frame, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerMainFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainFrameBinding bind(View view, Object obj) {
        return (WeeklyTimerMainFrameBinding) bind(obj, view, R.layout.weekly_timer_main_frame);
    }
}
