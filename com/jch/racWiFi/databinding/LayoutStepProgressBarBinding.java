package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutStepProgressBarBinding extends ViewDataBinding {
    public final LinearProgressIndicator linearProgressIndicator;
    public final TextView textViewStep1;

    protected LayoutStepProgressBarBinding(Object obj, View view, int i, LinearProgressIndicator linearProgressIndicator2, TextView textView) {
        super(obj, view, i);
        this.linearProgressIndicator = linearProgressIndicator2;
        this.textViewStep1 = textView;
    }

    public static LayoutStepProgressBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutStepProgressBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutStepProgressBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_step_progress_bar, viewGroup, z, obj);
    }

    public static LayoutStepProgressBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutStepProgressBarBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutStepProgressBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_step_progress_bar, (ViewGroup) null, false, obj);
    }

    public static LayoutStepProgressBarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutStepProgressBarBinding bind(View view, Object obj) {
        return (LayoutStepProgressBarBinding) bind(obj, view, R.layout.layout_step_progress_bar);
    }
}
