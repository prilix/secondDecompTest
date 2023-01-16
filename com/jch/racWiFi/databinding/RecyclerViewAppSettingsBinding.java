package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewAppSettingsBinding extends ViewDataBinding {
    public final LinearLayout containerLayout;
    public final ImageView imageViewRightSwipe;
    public final ConstraintLayout layoutAppSettings;
    public final CustomSwitchButton settingsToggleAmplitude;
    public final TextView textViewSettingInfo;
    public final TextView textViewSettingType;
    public final View view35;

    protected RecyclerViewAppSettingsBinding(Object obj, View view, int i, LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, CustomSwitchButton customSwitchButton, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.containerLayout = linearLayout;
        this.imageViewRightSwipe = imageView;
        this.layoutAppSettings = constraintLayout;
        this.settingsToggleAmplitude = customSwitchButton;
        this.textViewSettingInfo = textView;
        this.textViewSettingType = textView2;
        this.view35 = view2;
    }

    public static RecyclerViewAppSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewAppSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewAppSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_app_settings, viewGroup, z, obj);
    }

    public static RecyclerViewAppSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewAppSettingsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewAppSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_app_settings, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewAppSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewAppSettingsBinding bind(View view, Object obj) {
        return (RecyclerViewAppSettingsBinding) bind(obj, view, R.layout.recycler_view_app_settings);
    }
}
