package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerMainVdBinding extends ViewDataBinding {
    public final Guideline guideline193;
    public final Guideline guideline198;
    public final RecyclerView recyclerViewDevices;
    public final TextView textViewSelectDeviceTitle;
    public final View view10;

    protected WeeklyTimerMainVdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, RecyclerView recyclerView, TextView textView, View view2) {
        super(obj, view, i);
        this.guideline193 = guideline;
        this.guideline198 = guideline2;
        this.recyclerViewDevices = recyclerView;
        this.textViewSelectDeviceTitle = textView;
        this.view10 = view2;
    }

    public static WeeklyTimerMainVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerMainVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_main_vd, viewGroup, z, obj);
    }

    public static WeeklyTimerMainVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerMainVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_main_vd, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerMainVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerMainVdBinding bind(View view, Object obj) {
        return (WeeklyTimerMainVdBinding) bind(obj, view, R.layout.weekly_timer_main_vd);
    }
}
