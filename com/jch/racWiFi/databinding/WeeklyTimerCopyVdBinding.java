package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Spinner;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerCopyVdBinding extends ViewDataBinding {
    public final TriStateCheckbox checkBoxAllAirConditioners;
    public final ConstraintLayout constarint1;
    public final FrameLayout frame;
    public final Guideline guideline195;
    public final Guideline guideline196;
    public final Guideline guideline295;
    public final Guideline guideline296;
    public final Guideline guideline299;
    public final Guideline guideline300;
    public final ImageView imageViewArrowDown;
    public final android.widget.ImageView ivPartial;
    public final ConstraintLayout layoutBottom;
    public final ConstraintLayout layoutDeviceName;
    public final RecyclerView recyclerViewDevices;
    public final Spinner spinnerDeviceName;
    public final TextView textViewAllDevicesTitleHome;
    public final TextView textViewApplyTo;
    public final TextView textViewCopyFrom;
    public final View view3;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected WeeklyTimerCopyVdBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, ConstraintLayout constraintLayout, FrameLayout frameLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageView imageView, android.widget.ImageView imageView2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, Spinner spinner, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.checkBoxAllAirConditioners = triStateCheckbox;
        this.constarint1 = constraintLayout;
        this.frame = frameLayout;
        this.guideline195 = guideline;
        this.guideline196 = guideline2;
        this.guideline295 = guideline3;
        this.guideline296 = guideline4;
        this.guideline299 = guideline5;
        this.guideline300 = guideline6;
        this.imageViewArrowDown = imageView;
        this.ivPartial = imageView2;
        this.layoutBottom = constraintLayout2;
        this.layoutDeviceName = constraintLayout3;
        this.recyclerViewDevices = recyclerView;
        this.spinnerDeviceName = spinner;
        this.textViewAllDevicesTitleHome = textView;
        this.textViewApplyTo = textView2;
        this.textViewCopyFrom = textView3;
        this.view3 = view2;
    }

    public static WeeklyTimerCopyVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerCopyVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_copy_vd, viewGroup, z, obj);
    }

    public static WeeklyTimerCopyVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerCopyVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_copy_vd, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerCopyVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerCopyVdBinding bind(View view, Object obj) {
        return (WeeklyTimerCopyVdBinding) bind(obj, view, R.layout.weekly_timer_copy_vd);
    }
}
