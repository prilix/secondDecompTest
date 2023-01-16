package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DeviceSettingsVdWithSkipBinding extends ViewDataBinding {
    public final ConstraintLayout clUserPermissions;
    public final Guideline guideline258;
    public final Guideline guideline259;
    public final Guideline guideline260;
    public final Guideline guideline261;
    public final Guideline guideline90;
    public final Guideline guideline91;
    public final ImageView imageViewRightSwipeDeviceName;
    public final ImageView imageViewRightSwipeManageOwners;
    public final ImageView imageViewRightSwipeUserPermissions;
    public final ConstraintLayout layoutDeviceIdEditDeviceConfiguration;
    public final ConstraintLayout layoutDeviceNameEditDeviceConfiguration;
    public final LinearLayout layoutRemoveDevice;
    public final ConstraintLayout manageUser;
    public final TextView textViewDeviceIdTitleEditDeviceConfiguration;
    public final TextView textViewDeviceNameTitleEditDeviceConfiguration;
    public final TextView textViewUserPermissionsEditDeviceConfiguration1;
    public final TextView textViewUserPermissionsEditDeviceConfiguration2;
    public final TextView tvDeviceName;
    public final TextView tvIduDeviceId;
    public final View view33;
    public final View view46;
    public final View view47;
    public final View view48;
    public final View view49;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DeviceSettingsVdWithSkipBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, ConstraintLayout constraintLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view2, View view3, View view4, View view5, View view6) {
        super(obj, view, i);
        this.clUserPermissions = constraintLayout;
        this.guideline258 = guideline;
        this.guideline259 = guideline2;
        this.guideline260 = guideline3;
        this.guideline261 = guideline4;
        this.guideline90 = guideline5;
        this.guideline91 = guideline6;
        this.imageViewRightSwipeDeviceName = imageView;
        this.imageViewRightSwipeManageOwners = imageView2;
        this.imageViewRightSwipeUserPermissions = imageView3;
        this.layoutDeviceIdEditDeviceConfiguration = constraintLayout2;
        this.layoutDeviceNameEditDeviceConfiguration = constraintLayout3;
        this.layoutRemoveDevice = linearLayout;
        this.manageUser = constraintLayout4;
        this.textViewDeviceIdTitleEditDeviceConfiguration = textView;
        this.textViewDeviceNameTitleEditDeviceConfiguration = textView2;
        this.textViewUserPermissionsEditDeviceConfiguration1 = textView3;
        this.textViewUserPermissionsEditDeviceConfiguration2 = textView4;
        this.tvDeviceName = textView5;
        this.tvIduDeviceId = textView6;
        this.view33 = view2;
        this.view46 = view3;
        this.view47 = view4;
        this.view48 = view5;
        this.view49 = view6;
    }

    public static DeviceSettingsVdWithSkipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingsVdWithSkipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DeviceSettingsVdWithSkipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_settings_vd_with_skip, viewGroup, z, obj);
    }

    public static DeviceSettingsVdWithSkipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingsVdWithSkipBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DeviceSettingsVdWithSkipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.device_settings_vd_with_skip, (ViewGroup) null, false, obj);
    }

    public static DeviceSettingsVdWithSkipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DeviceSettingsVdWithSkipBinding bind(View view, Object obj) {
        return (DeviceSettingsVdWithSkipBinding) bind(obj, view, R.layout.device_settings_vd_with_skip);
    }
}
