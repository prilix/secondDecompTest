package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class CustomerCareDevicesBinding extends ViewDataBinding {
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewDeviceName;

    protected CustomerCareDevicesBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.layoutManageDevices = constraintLayout;
        this.textViewDeviceName = textView;
    }

    public static CustomerCareDevicesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareDevicesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (CustomerCareDevicesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.customer_care_devices, viewGroup, z, obj);
    }

    public static CustomerCareDevicesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareDevicesBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (CustomerCareDevicesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.customer_care_devices, (ViewGroup) null, false, obj);
    }

    public static CustomerCareDevicesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareDevicesBinding bind(View view, Object obj) {
        return (CustomerCareDevicesBinding) bind(obj, view, R.layout.customer_care_devices);
    }
}
