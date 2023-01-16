package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutErrorDisplayBinding extends ViewDataBinding {
    public final ImageView errorCrossImage;
    public final TextView errorDate;
    public final TextView errorDescription;
    public final TextView errorTitle;
    public final ImageView imageView4;

    protected LayoutErrorDisplayBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3, ImageView imageView2) {
        super(obj, view, i);
        this.errorCrossImage = imageView;
        this.errorDate = textView;
        this.errorDescription = textView2;
        this.errorTitle = textView3;
        this.imageView4 = imageView2;
    }

    public static LayoutErrorDisplayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutErrorDisplayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutErrorDisplayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_error_display, viewGroup, z, obj);
    }

    public static LayoutErrorDisplayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutErrorDisplayBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutErrorDisplayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_error_display, (ViewGroup) null, false, obj);
    }

    public static LayoutErrorDisplayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutErrorDisplayBinding bind(View view, Object obj) {
        return (LayoutErrorDisplayBinding) bind(obj, view, R.layout.layout_error_display);
    }
}
