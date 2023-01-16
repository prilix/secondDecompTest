package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ManageDevicesVdBinding extends ViewDataBinding {
    public final Guideline guideline134;
    public final Guideline guideline135;
    public final Guideline guideline92;
    public final LinearLayout layoutAddDevice;
    public final RecyclerView recyclerViewDevices;
    public final View view3;

    protected ManageDevicesVdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, LinearLayout linearLayout, RecyclerView recyclerView, View view2) {
        super(obj, view, i);
        this.guideline134 = guideline;
        this.guideline135 = guideline2;
        this.guideline92 = guideline3;
        this.layoutAddDevice = linearLayout;
        this.recyclerViewDevices = recyclerView;
        this.view3 = view2;
    }

    public static ManageDevicesVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ManageDevicesVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_devices_vd, viewGroup, z, obj);
    }

    public static ManageDevicesVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ManageDevicesVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_devices_vd, (ViewGroup) null, false, obj);
    }

    public static ManageDevicesVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesVdBinding bind(View view, Object obj) {
        return (ManageDevicesVdBinding) bind(obj, view, R.layout.manage_devices_vd);
    }
}
