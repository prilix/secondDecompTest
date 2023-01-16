package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ManageDevicesFrameBinding extends ViewDataBinding {
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final ImageButton imageButtonMenu;
    public final ManageDevicesVdBinding includedLayout;
    public final TextView textViewManageDevicesTitle;

    protected ManageDevicesFrameBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, ManageDevicesVdBinding manageDevicesVdBinding, TextView textView) {
        super(obj, view, i);
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.imageButtonMenu = imageButton;
        this.includedLayout = manageDevicesVdBinding;
        this.textViewManageDevicesTitle = textView;
    }

    public static ManageDevicesFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ManageDevicesFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_devices_frame, viewGroup, z, obj);
    }

    public static ManageDevicesFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ManageDevicesFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_devices_frame, (ViewGroup) null, false, obj);
    }

    public static ManageDevicesFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageDevicesFrameBinding bind(View view, Object obj) {
        return (ManageDevicesFrameBinding) bind(obj, view, R.layout.manage_devices_frame);
    }
}
