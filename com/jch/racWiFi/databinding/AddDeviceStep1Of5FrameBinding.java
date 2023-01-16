package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class AddDeviceStep1Of5FrameBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline36;
    public final Guideline guideline46;
    public final AddDeviceStep1Of5VdBinding include;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewAddDeviceTitle;

    protected AddDeviceStep1Of5FrameBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, AddDeviceStep1Of5VdBinding addDeviceStep1Of5VdBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline36 = guideline;
        this.guideline46 = guideline2;
        this.include = addDeviceStep1Of5VdBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewAddDeviceTitle = textView;
    }

    public static AddDeviceStep1Of5FrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5FrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AddDeviceStep1Of5FrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_device_step_1_of_5_frame, viewGroup, z, obj);
    }

    public static AddDeviceStep1Of5FrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5FrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AddDeviceStep1Of5FrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_device_step_1_of_5_frame, (ViewGroup) null, false, obj);
    }

    public static AddDeviceStep1Of5FrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5FrameBinding bind(View view, Object obj) {
        return (AddDeviceStep1Of5FrameBinding) bind(obj, view, R.layout.add_device_step_1_of_5_frame);
    }
}
