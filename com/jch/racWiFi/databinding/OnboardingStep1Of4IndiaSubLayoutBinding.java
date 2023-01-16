package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class OnboardingStep1Of4IndiaSubLayoutBinding extends ViewDataBinding {
    public final Button btChangeRouter;
    public final ConstraintLayout constraintLayout6;
    public final EditText etPasswordField;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout rootConstraintLayout;
    public final TextInputLayout textInputLayout;
    public final TextView textViewConfirmWifiNetworkSubTitle;
    public final TextView textViewConfirmWifiNetworkSubTitle2;
    public final TextView textViewConfirmWifiNetworkTitle;
    public final TextView textViewWantToPairWithDifWirelessRouter;
    public final TextView tvHomeRouterSsid;

    protected OnboardingStep1Of4IndiaSubLayoutBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, EditText editText, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout2, TextInputLayout textInputLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btChangeRouter = button;
        this.constraintLayout6 = constraintLayout;
        this.etPasswordField = editText;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.rootConstraintLayout = constraintLayout2;
        this.textInputLayout = textInputLayout2;
        this.textViewConfirmWifiNetworkSubTitle = textView;
        this.textViewConfirmWifiNetworkSubTitle2 = textView2;
        this.textViewConfirmWifiNetworkTitle = textView3;
        this.textViewWantToPairWithDifWirelessRouter = textView4;
        this.tvHomeRouterSsid = textView5;
    }

    public static OnboardingStep1Of4IndiaSubLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaSubLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (OnboardingStep1Of4IndiaSubLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_1_of_4_india_sub_layout, viewGroup, z, obj);
    }

    public static OnboardingStep1Of4IndiaSubLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaSubLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (OnboardingStep1Of4IndiaSubLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_1_of_4_india_sub_layout, (ViewGroup) null, false, obj);
    }

    public static OnboardingStep1Of4IndiaSubLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaSubLayoutBinding bind(View view, Object obj) {
        return (OnboardingStep1Of4IndiaSubLayoutBinding) bind(obj, view, R.layout.onboarding_step_1_of_4_india_sub_layout);
    }
}
