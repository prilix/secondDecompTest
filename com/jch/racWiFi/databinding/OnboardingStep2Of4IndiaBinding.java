package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class OnboardingStep2Of4IndiaBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ImageButton forwardButton;
    public final Guideline guideline227;
    public final Guideline guideline228;
    public final Guideline guideline238;
    public final ImageView imageViewTimerSwitchOnAc;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout layoutTop;
    public final ScrollView scrollView;
    public final TextView textViewForgotPasswordTitle;
    public final TextView textViewSwitchOnAirConditionerSubTitle;
    public final TextView textViewSwitchOnAirConditionerSubTitleOne;
    public final TextView textViewSwitchOnAirConditionerSubTitleTwo;
    public final TextView textViewSwitchOnAirConditionerTile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected OnboardingStep2Of4IndiaBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout, ScrollView scrollView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.forwardButton = imageButton2;
        this.guideline227 = guideline;
        this.guideline228 = guideline2;
        this.guideline238 = guideline3;
        this.imageViewTimerSwitchOnAc = imageView;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.layoutTop = constraintLayout;
        this.scrollView = scrollView2;
        this.textViewForgotPasswordTitle = textView;
        this.textViewSwitchOnAirConditionerSubTitle = textView2;
        this.textViewSwitchOnAirConditionerSubTitleOne = textView3;
        this.textViewSwitchOnAirConditionerSubTitleTwo = textView4;
        this.textViewSwitchOnAirConditionerTile = textView5;
    }

    public static OnboardingStep2Of4IndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep2Of4IndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (OnboardingStep2Of4IndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_2_of_4_india, viewGroup, z, obj);
    }

    public static OnboardingStep2Of4IndiaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep2Of4IndiaBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (OnboardingStep2Of4IndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_2_of_4_india, (ViewGroup) null, false, obj);
    }

    public static OnboardingStep2Of4IndiaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep2Of4IndiaBinding bind(View view, Object obj) {
        return (OnboardingStep2Of4IndiaBinding) bind(obj, view, R.layout.onboarding_step_2_of_4_india);
    }
}
