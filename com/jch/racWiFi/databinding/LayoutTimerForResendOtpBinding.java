package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.CircleProgressBar;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutTimerForResendOtpBinding extends ViewDataBinding {
    public final Guideline guideline;
    public final Guideline guideline20;
    public final CircleProgressBar progressBar;
    public final TextView textViewResendCode;
    public final TextView textViewSec;
    public final TextView textViewTime;

    protected LayoutTimerForResendOtpBinding(Object obj, View view, int i, Guideline guideline2, Guideline guideline3, CircleProgressBar circleProgressBar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.guideline = guideline2;
        this.guideline20 = guideline3;
        this.progressBar = circleProgressBar;
        this.textViewResendCode = textView;
        this.textViewSec = textView2;
        this.textViewTime = textView3;
    }

    public static LayoutTimerForResendOtpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTimerForResendOtpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutTimerForResendOtpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_timer_for_resend_otp, viewGroup, z, obj);
    }

    public static LayoutTimerForResendOtpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTimerForResendOtpBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutTimerForResendOtpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_timer_for_resend_otp, (ViewGroup) null, false, obj);
    }

    public static LayoutTimerForResendOtpBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTimerForResendOtpBinding bind(View view, Object obj) {
        return (LayoutTimerForResendOtpBinding) bind(obj, view, R.layout.layout_timer_for_resend_otp);
    }
}
