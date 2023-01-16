package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutBannerErrorBinding extends ViewDataBinding {
    public final AppCompatImageView appCompatImageView;
    public final TextView descriptionText;

    protected LayoutBannerErrorBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView2, TextView textView) {
        super(obj, view, i);
        this.appCompatImageView = appCompatImageView2;
        this.descriptionText = textView;
    }

    public static LayoutBannerErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutBannerErrorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_banner_error, viewGroup, z, obj);
    }

    public static LayoutBannerErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerErrorBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutBannerErrorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_banner_error, (ViewGroup) null, false, obj);
    }

    public static LayoutBannerErrorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerErrorBinding bind(View view, Object obj) {
        return (LayoutBannerErrorBinding) bind(obj, view, R.layout.layout_banner_error);
    }
}
