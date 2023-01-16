package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SocialLoginTwitterLayoutBinding extends ViewDataBinding {
    public final LinearLayout linearLayoutTwitter;

    protected SocialLoginTwitterLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout) {
        super(obj, view, i);
        this.linearLayoutTwitter = linearLayout;
    }

    public static SocialLoginTwitterLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginTwitterLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SocialLoginTwitterLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_twitter_layout, viewGroup, z, obj);
    }

    public static SocialLoginTwitterLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginTwitterLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SocialLoginTwitterLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_twitter_layout, (ViewGroup) null, false, obj);
    }

    public static SocialLoginTwitterLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginTwitterLayoutBinding bind(View view, Object obj) {
        return (SocialLoginTwitterLayoutBinding) bind(obj, view, R.layout.social_login_twitter_layout);
    }
}
