package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentUnableToScanQrSsidBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Button buttonWhereToFindSsidName;
    public final EditText editTextEnterSsid;
    public final ImageButton forwardButton;
    public final Guideline guideline248;
    public final Guideline guideline249;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout parent;
    public final TextView textViewEnterAirConditionerSsid;
    public final TextView textViewEnterAirConditionerSsidSubTitle;
    public final TextView textViewEnterAirConditionerSsidTitle;
    public final TextView textViewForgotPasswordTitle;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentUnableToScanQrSsidBinding(Object obj, View view, int i, ImageButton imageButton, Button button, EditText editText, ImageButton imageButton2, Guideline guideline, Guideline guideline2, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.buttonWhereToFindSsidName = button;
        this.editTextEnterSsid = editText;
        this.forwardButton = imageButton2;
        this.guideline248 = guideline;
        this.guideline249 = guideline2;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.layoutTop = constraintLayout;
        this.parent = constraintLayout2;
        this.textViewEnterAirConditionerSsid = textView;
        this.textViewEnterAirConditionerSsidSubTitle = textView2;
        this.textViewEnterAirConditionerSsidTitle = textView3;
        this.textViewForgotPasswordTitle = textView4;
    }

    public static FragmentUnableToScanQrSsidBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUnableToScanQrSsidBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentUnableToScanQrSsidBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_unable_to_scan_qr_ssid, viewGroup, z, obj);
    }

    public static FragmentUnableToScanQrSsidBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUnableToScanQrSsidBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentUnableToScanQrSsidBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_unable_to_scan_qr_ssid, (ViewGroup) null, false, obj);
    }

    public static FragmentUnableToScanQrSsidBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUnableToScanQrSsidBinding bind(View view, Object obj) {
        return (FragmentUnableToScanQrSsidBinding) bind(obj, view, R.layout.fragment_unable_to_scan_qr_ssid);
    }
}
