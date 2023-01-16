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

public abstract class SettingsFrameBinding extends ViewDataBinding {
    public final Guideline guideline140;
    public final Guideline guideline41;
    public final ImageButton imageButtonMenu;
    public final LinearLayout layout1;
    public final SettingsVdBinding layoutInclude;
    public final ConstraintLayout parent;
    public final TextView textViewSettingsTitle;

    protected SettingsFrameBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, LinearLayout linearLayout, SettingsVdBinding settingsVdBinding, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.guideline140 = guideline;
        this.guideline41 = guideline2;
        this.imageButtonMenu = imageButton;
        this.layout1 = linearLayout;
        this.layoutInclude = settingsVdBinding;
        this.parent = constraintLayout;
        this.textViewSettingsTitle = textView;
    }

    public static SettingsFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SettingsFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_frame, viewGroup, z, obj);
    }

    public static SettingsFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SettingsFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_frame, (ViewGroup) null, false, obj);
    }

    public static SettingsFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsFrameBinding bind(View view, Object obj) {
        return (SettingsFrameBinding) bind(obj, view, R.layout.settings_frame);
    }
}
