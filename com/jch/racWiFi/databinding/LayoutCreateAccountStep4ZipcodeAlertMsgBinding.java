package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutCreateAccountStep4ZipcodeAlertMsgBinding extends ViewDataBinding {
    public final CardView cardView;
    public final ConstraintLayout rootView;
    public final TextView textViewIndicatesMandatoryField;

    protected LayoutCreateAccountStep4ZipcodeAlertMsgBinding(Object obj, View view, int i, CardView cardView2, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.cardView = cardView2;
        this.rootView = constraintLayout;
        this.textViewIndicatesMandatoryField = textView;
    }

    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutCreateAccountStep4ZipcodeAlertMsgBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_zipcode_alert_msg, viewGroup, z, obj);
    }

    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutCreateAccountStep4ZipcodeAlertMsgBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_zipcode_alert_msg, (ViewGroup) null, false, obj);
    }

    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4ZipcodeAlertMsgBinding bind(View view, Object obj) {
        return (LayoutCreateAccountStep4ZipcodeAlertMsgBinding) bind(obj, view, R.layout.layout_create_account_step_4_zipcode_alert_msg);
    }
}
