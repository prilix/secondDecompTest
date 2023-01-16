package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DirectOnboardMainVdBinding extends ViewDataBinding {
    public final RecyclerView directOnboardingRecyclerView;
    public final Guideline guideline193;
    public final Guideline guideline198;
    public final TextView textViewSelectDeviceTitle;
    public final View view10;

    protected DirectOnboardMainVdBinding(Object obj, View view, int i, RecyclerView recyclerView, Guideline guideline, Guideline guideline2, TextView textView, View view2) {
        super(obj, view, i);
        this.directOnboardingRecyclerView = recyclerView;
        this.guideline193 = guideline;
        this.guideline198 = guideline2;
        this.textViewSelectDeviceTitle = textView;
        this.view10 = view2;
    }

    public static DirectOnboardMainVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DirectOnboardMainVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.direct_onboard_main_vd, viewGroup, z, obj);
    }

    public static DirectOnboardMainVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DirectOnboardMainVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.direct_onboard_main_vd, (ViewGroup) null, false, obj);
    }

    public static DirectOnboardMainVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainVdBinding bind(View view, Object obj) {
        return (DirectOnboardMainVdBinding) bind(obj, view, R.layout.direct_onboard_main_vd);
    }
}
