package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutSelectAcBinding extends ViewDataBinding {
    public final AppCompatImageView ecEditImageView;
    public final AppCompatImageView helpIcon;
    public final TextView selectAcLbltextView;

    protected LayoutSelectAcBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, TextView textView) {
        super(obj, view, i);
        this.ecEditImageView = appCompatImageView;
        this.helpIcon = appCompatImageView2;
        this.selectAcLbltextView = textView;
    }

    public static LayoutSelectAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSelectAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutSelectAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_select_ac, viewGroup, z, obj);
    }

    public static LayoutSelectAcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSelectAcBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutSelectAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_select_ac, (ViewGroup) null, false, obj);
    }

    public static LayoutSelectAcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSelectAcBinding bind(View view, Object obj) {
        return (LayoutSelectAcBinding) bind(obj, view, R.layout.layout_select_ac);
    }
}
