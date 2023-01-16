package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutEcHeaderBinding extends ViewDataBinding {
    public final ConstraintLayout ecHeaderQuestionMark;
    public final ImageView ecQuestionMarkImage;
    public final TextView text;

    protected LayoutEcHeaderBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ecHeaderQuestionMark = constraintLayout;
        this.ecQuestionMarkImage = imageView;
        this.text = textView;
    }

    public static LayoutEcHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutEcHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_ec_header, viewGroup, z, obj);
    }

    public static LayoutEcHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcHeaderBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutEcHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_ec_header, (ViewGroup) null, false, obj);
    }

    public static LayoutEcHeaderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEcHeaderBinding bind(View view, Object obj) {
        return (LayoutEcHeaderBinding) bind(obj, view, R.layout.layout_ec_header);
    }
}
