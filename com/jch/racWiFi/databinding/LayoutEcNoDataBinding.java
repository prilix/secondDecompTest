package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutEcNoDataBinding extends ViewDataBinding {
    public final ImageButton ecNoDataAddImageButton;
    public final TextView textViewClickplusbutton;
    public final TextView textViewNoDevicesFound;

    protected LayoutEcNoDataBinding(Object obj, View view, int i, ImageButton imageButton, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ecNoDataAddImageButton = imageButton;
        this.textViewClickplusbutton = textView;
        this.textViewNoDevicesFound = textView2;
    }

    public static LayoutEcNoDataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcNoDataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutEcNoDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_ec_no_data, viewGroup, z, obj);
    }

    public static LayoutEcNoDataBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcNoDataBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutEcNoDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_ec_no_data, (ViewGroup) null, false, obj);
    }

    public static LayoutEcNoDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcNoDataBinding bind(View view, Object obj) {
        return (LayoutEcNoDataBinding) bind(obj, view, R.layout.layout_ec_no_data);
    }
}
