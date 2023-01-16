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
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerGlobalFrameBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final ImageButton imageButtonCopyWeeklyTimer;
    public final WeeklyTimerGlobalVdBinding include;
    @Bindable
    protected WeeklyTimerViewModel mWeeklyTimerViewModel;
    public final LinearLayout rootLayout;
    public final TextView textViewRoomTitle;
    public final ImageButton textViewSave;

    public abstract void setWeeklyTimerViewModel(WeeklyTimerViewModel weeklyTimerViewModel);

    protected WeeklyTimerGlobalFrameBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, ImageButton imageButton2, WeeklyTimerGlobalVdBinding weeklyTimerGlobalVdBinding, LinearLayout linearLayout, TextView textView, ImageButton imageButton3) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.imageButtonCopyWeeklyTimer = imageButton2;
        this.include = weeklyTimerGlobalVdBinding;
        this.rootLayout = linearLayout;
        this.textViewRoomTitle = textView;
        this.textViewSave = imageButton3;
    }

    public WeeklyTimerViewModel getWeeklyTimerViewModel() {
        return this.mWeeklyTimerViewModel;
    }

    public static WeeklyTimerGlobalFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerGlobalFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_global_frame, viewGroup, z, obj);
    }

    public static WeeklyTimerGlobalFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerGlobalFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_global_frame, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerGlobalFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalFrameBinding bind(View view, Object obj) {
        return (WeeklyTimerGlobalFrameBinding) bind(obj, view, R.layout.weekly_timer_global_frame);
    }
}
