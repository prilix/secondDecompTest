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

public abstract class SmartFenceArrivingInfoWindowLayoutBinding extends ViewDataBinding {
    public final ImageView imageViewLeaving;
    public final ConstraintLayout tabLeaving;
    public final TextView textViewLeavingKm;

    protected SmartFenceArrivingInfoWindowLayoutBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.imageViewLeaving = imageView;
        this.tabLeaving = constraintLayout;
        this.textViewLeavingKm = textView;
    }

    public static SmartFenceArrivingInfoWindowLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceArrivingInfoWindowLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceArrivingInfoWindowLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_arriving_info_window_layout, viewGroup, z, obj);
    }

    public static SmartFenceArrivingInfoWindowLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceArrivingInfoWindowLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceArrivingInfoWindowLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_arriving_info_window_layout, (ViewGroup) null, false, obj);
    }

    public static SmartFenceArrivingInfoWindowLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceArrivingInfoWindowLayoutBinding bind(View view, Object obj) {
        return (SmartFenceArrivingInfoWindowLayoutBinding) bind(obj, view, R.layout.smart_fence_arriving_info_window_layout);
    }
}
