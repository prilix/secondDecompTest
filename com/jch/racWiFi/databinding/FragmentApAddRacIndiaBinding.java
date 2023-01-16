package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentApAddRacIndiaBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Button buttonChangeRouter;
    public final TextView connectToAcTextview;
    public final ConstraintLayout constraintLayoutError;
    public final EditText etPasswordField;
    public final ImageButton forwardButton;
    public final Guideline guideline248;
    public final Guideline guideline249;
    public final Guideline guideline250;
    public final Guideline guideline251;
    public final Guideline guideline800;
    public final Guideline guideline801;
    public final LayoutGracTooltipBinding include;
    public final LayoutBannerErrorBinding includeBannerError;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout layoutPassword;
    public final ConstraintLayout layoutTop;
    public final TextInputLayout passwordTextInputLayout;
    public final ScrollView scrollView;
    public final TextView textViewAddDeviceTitle;
    public final TextView textViewConnectToAirConditionerTitle;
    public final TextView textViewPasswordRacWifi;
    public final TextView textViewPasswordTitle;
    public final TextView tvSsid;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentApAddRacIndiaBinding(Object obj, View view, int i, ImageButton imageButton, Button button, TextView textView, ConstraintLayout constraintLayout, EditText editText, ImageButton imageButton2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, LayoutGracTooltipBinding layoutGracTooltipBinding, LayoutBannerErrorBinding layoutBannerErrorBinding, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextInputLayout textInputLayout, ScrollView scrollView2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.buttonChangeRouter = button;
        this.connectToAcTextview = textView;
        this.constraintLayoutError = constraintLayout;
        this.etPasswordField = editText;
        this.forwardButton = imageButton2;
        this.guideline248 = guideline;
        this.guideline249 = guideline2;
        this.guideline250 = guideline3;
        this.guideline251 = guideline4;
        this.guideline800 = guideline5;
        this.guideline801 = guideline6;
        this.include = layoutGracTooltipBinding;
        this.includeBannerError = layoutBannerErrorBinding;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.layoutPassword = constraintLayout2;
        this.layoutTop = constraintLayout3;
        this.passwordTextInputLayout = textInputLayout;
        this.scrollView = scrollView2;
        this.textViewAddDeviceTitle = textView2;
        this.textViewConnectToAirConditionerTitle = textView3;
        this.textViewPasswordRacWifi = textView4;
        this.textViewPasswordTitle = textView5;
        this.tvSsid = textView6;
    }

    public static FragmentApAddRacIndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApAddRacIndiaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentApAddRacIndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ap_add_rac_india, viewGroup, z, obj);
    }

    public static FragmentApAddRacIndiaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApAddRacIndiaBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentApAddRacIndiaBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ap_add_rac_india, (ViewGroup) null, false, obj);
    }

    public static FragmentApAddRacIndiaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApAddRacIndiaBinding bind(View view, Object obj) {
        return (FragmentApAddRacIndiaBinding) bind(obj, view, R.layout.fragment_ap_add_rac_india);
    }
}
