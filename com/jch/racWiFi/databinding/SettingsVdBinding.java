package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SettingsVdBinding extends ViewDataBinding {
    public final ImageView imageView2;
    public final RecyclerView recyclerViewAppSettings;
    public final LinearLayout settingsLinkWithAlexa;
    public final TextView textView1;
    public final View view1;

    protected SettingsVdBinding(Object obj, View view, int i, ImageView imageView, RecyclerView recyclerView, LinearLayout linearLayout, TextView textView, View view2) {
        super(obj, view, i);
        this.imageView2 = imageView;
        this.recyclerViewAppSettings = recyclerView;
        this.settingsLinkWithAlexa = linearLayout;
        this.textView1 = textView;
        this.view1 = view2;
    }

    public static SettingsVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SettingsVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_vd, viewGroup, z, obj);
    }

    public static SettingsVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SettingsVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_vd, (ViewGroup) null, false, obj);
    }

    public static SettingsVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsVdBinding bind(View view, Object obj) {
        return (SettingsVdBinding) bind(obj, view, R.layout.settings_vd);
    }
}
