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
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class OnboardingStep1Of4IndiaBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ImageButton forwardButton;
    public final Guideline guideline32;
    public final Guideline guideline33;
    public final OnboardingStep1Of4IndiaSubLayoutBinding include;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout rootView;
    public final ScrollView scrollView;
    public final TextView textViewAddDeviceTitle;

    protected OnboardingStep1Of4IndiaBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, Guideline guideline, Guideline guideline2, OnboardingStep1Of4IndiaSubLayoutBinding onboardingStep1Of4IndiaSubLayoutBinding, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ScrollView scrollView2, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.forwardButton = imageButton2;
        this.guideline32 = guideline;
        this.guideline33 = guideline2;
        this.include = onboardingStep1Of4IndiaSubLayoutBinding;
        this.layoutTop = constraintLayout;
        this.rootView = constraintLayout2;
        this.scrollView = scrollView2;
        this.textViewAddDeviceTitle = textView;
    }

    public static OnboardingStep1Of4IndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (OnboardingStep1Of4IndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_1_of_4_india, viewGroup, z, obj);
    }

    public static OnboardingStep1Of4IndiaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (OnboardingStep1Of4IndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.onboarding_step_1_of_4_india, (ViewGroup) null, false, obj);
    }

    public static OnboardingStep1Of4IndiaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OnboardingStep1Of4IndiaBinding bind(View view, Object obj) {
        return (OnboardingStep1Of4IndiaBinding) bind(obj, view, R.layout.onboarding_step_1_of_4_india);
    }
}
