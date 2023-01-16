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

public abstract class DeviceSettingFrameNewBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final DeviceSettingsVdWithSkipBinding includedLayout;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewDeviceSettingsTitle;
    public final ImageButton tvSkip;

    protected DeviceSettingFrameNewBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, DeviceSettingsVdWithSkipBinding deviceSettingsVdWithSkipBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView, ImageButton imageButton2) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.includedLayout = deviceSettingsVdWithSkipBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewDeviceSettingsTitle = textView;
        this.tvSkip = imageButton2;
    }

    public static DeviceSettingFrameNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingFrameNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DeviceSettingFrameNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_setting_frame_new, viewGroup, z, obj);
    }

    public static DeviceSettingFrameNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingFrameNewBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DeviceSettingFrameNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_setting_frame_new, (ViewGroup) null, false, obj);
    }

    public static DeviceSettingFrameNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingFrameNewBinding bind(View view, Object obj) {
        return (DeviceSettingFrameNewBinding) bind(obj, view, R.layout.device_setting_frame_new);
    }
}
