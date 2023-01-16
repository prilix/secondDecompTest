package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SocialLoginLinkedinLayoutBinding extends ViewDataBinding {
    public final LinearLayout linearLayoutLinkedin;

    protected SocialLoginLinkedinLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout) {
        super(obj, view, i);
        this.linearLayoutLinkedin = linearLayout;
    }

    public static SocialLoginLinkedinLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginLinkedinLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SocialLoginLinkedinLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_linkedin_layout, viewGroup, z, obj);
    }

    public static SocialLoginLinkedinLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginLinkedinLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SocialLoginLinkedinLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_linkedin_layout, (ViewGroup) null, false, obj);
    }

    public static SocialLoginLinkedinLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginLinkedinLayoutBinding bind(View view, Object obj) {
        return (SocialLoginLinkedinLayoutBinding) bind(obj, view, R.layout.social_login_linkedin_layout);
    }
}
