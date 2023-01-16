package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutCreateAccountStep4BodyBinding extends ViewDataBinding {
    public final Guideline guideline1;
    public final Guideline guideline2;
    public final Guideline guideline3;
    public final Guideline guideline4;
    public final LayoutCreateAccountStep4SubBodyBinding includeSubBody;
    public final LayoutCreateAccountStep4ZipcodeAlertMsgBinding includeZipCode;
    public final TextView textViewEnterAddressDetails;
    public final TextView textViewIndicatesMandatoryField;
    public final TextView textViewStep4Of4;

    protected LayoutCreateAccountStep4BodyBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline5, Guideline guideline6, Guideline guideline7, LayoutCreateAccountStep4SubBodyBinding layoutCreateAccountStep4SubBodyBinding, LayoutCreateAccountStep4ZipcodeAlertMsgBinding layoutCreateAccountStep4ZipcodeAlertMsgBinding, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.guideline1 = guideline;
        this.guideline2 = guideline5;
        this.guideline3 = guideline6;
        this.guideline4 = guideline7;
        this.includeSubBody = layoutCreateAccountStep4SubBodyBinding;
        this.includeZipCode = layoutCreateAccountStep4ZipcodeAlertMsgBinding;
        this.textViewEnterAddressDetails = textView;
        this.textViewIndicatesMandatoryField = textView2;
        this.textViewStep4Of4 = textView3;
    }

    public static LayoutCreateAccountStep4BodyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4BodyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutCreateAccountStep4BodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_body, viewGroup, z, obj);
    }

    public static LayoutCreateAccountStep4BodyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4BodyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutCreateAccountStep4BodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_body, (ViewGroup) null, false, obj);
    }

    public static LayoutCreateAccountStep4BodyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4BodyBinding bind(View view, Object obj) {
        return (LayoutCreateAccountStep4BodyBinding) bind(obj, view, R.layout.layout_create_account_step_4_body);
    }
}
