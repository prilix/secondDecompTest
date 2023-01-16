package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerCopyTimerScheduleViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerCopyFrameBinding extends ViewDataBinding {
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final ImageButton imageButtonMenu;
    public final WeeklyTimerCopyVdBinding include;
    @Bindable
    protected WeeklyTimerCopyTimerScheduleViewModel mWeeklyTimerCopyTimerScheduleViewModel;
    public final LinearLayout rootLayout;
    public final ImageButton textViewSave;
    public final TextView textViewWeeklyTimerTitle;

    public abstract void setWeeklyTimerCopyTimerScheduleViewModel(WeeklyTimerCopyTimerScheduleViewModel weeklyTimerCopyTimerScheduleViewModel);

    protected WeeklyTimerCopyFrameBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, WeeklyTimerCopyVdBinding weeklyTimerCopyVdBinding, LinearLayout linearLayout, ImageButton imageButton2, TextView textView) {
        super(obj, view, i);
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.imageButtonMenu = imageButton;
        this.include = weeklyTimerCopyVdBinding;
        this.rootLayout = linearLayout;
        this.textViewSave = imageButton2;
        this.textViewWeeklyTimerTitle = textView;
    }

    public WeeklyTimerCopyTimerScheduleViewModel getWeeklyTimerCopyTimerScheduleViewModel() {
        return this.mWeeklyTimerCopyTimerScheduleViewModel;
    }

    public static WeeklyTimerCopyFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerCopyFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_copy_frame, viewGroup, z, obj);
    }

    public static WeeklyTimerCopyFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerCopyFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_copy_frame, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerCopyFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyFrameBinding bind(View view, Object obj) {
        return (WeeklyTimerCopyFrameBinding) bind(obj, view, R.layout.weekly_timer_copy_frame);
    }
}
