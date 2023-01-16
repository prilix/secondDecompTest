package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class EnableWpsBuiltInStep4Of4VdBinding extends ViewDataBinding {
    public final Guideline guideline229;
    public final Guideline guideline237;
    public final Guideline guideline238;
    public final ImageView imageViewEnableWpsOnWirelessRouter;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final TextView textViewEnableWpsOnWirelessRouterSubTitle;
    public final TextView textViewEnableWpsOnWirelessRouterTitle;

    protected EnableWpsBuiltInStep4Of4VdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView, LayoutStepProgressBarBinding layoutStepProgressBarBinding, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.guideline229 = guideline;
        this.guideline237 = guideline2;
        this.guideline238 = guideline3;
        this.imageViewEnableWpsOnWirelessRouter = imageView;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.textViewEnableWpsOnWirelessRouterSubTitle = textView;
        this.textViewEnableWpsOnWirelessRouterTitle = textView2;
    }

    public static EnableWpsBuiltInStep4Of4VdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4VdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EnableWpsBuiltInStep4Of4VdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enable_wps_built_in_step_4_of_4_vd, viewGroup, z, obj);
    }

    public static EnableWpsBuiltInStep4Of4VdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4VdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EnableWpsBuiltInStep4Of4VdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enable_wps_built_in_step_4_of_4_vd, (ViewGroup) null, false, obj);
    }

    public static EnableWpsBuiltInStep4Of4VdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4VdBinding bind(View view, Object obj) {
        return (EnableWpsBuiltInStep4Of4VdBinding) bind(obj, view, R.layout.enable_wps_built_in_step_4_of_4_vd);
    }
}
