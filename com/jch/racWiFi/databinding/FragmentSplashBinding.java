package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentSplashBinding extends ViewDataBinding {
    public final FrameLayout bannerPlannedMaintenance;
    public final ConstraintLayout parent;
    public final ConstraintLayout parentLayout;

    protected FragmentSplashBinding(Object obj, View view, int i, FrameLayout frameLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        super(obj, view, i);
        this.bannerPlannedMaintenance = frameLayout;
        this.parent = constraintLayout;
        this.parentLayout = constraintLayout2;
    }

    public static FragmentSplashBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSplashBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSplashBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_splash, viewGroup, z, obj);
    }

    public static FragmentSplashBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSplashBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSplashBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_splash, (ViewGroup) null, false, obj);
    }

    public static FragmentSplashBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSplashBinding bind(View view, Object obj) {
        return (FragmentSplashBinding) bind(obj, view, R.layout.fragment_splash);
    }
}
