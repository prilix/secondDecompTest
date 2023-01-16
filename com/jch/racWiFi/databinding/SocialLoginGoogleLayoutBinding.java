package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SocialLoginGoogleLayoutBinding extends ViewDataBinding {
    public final LinearLayout linearLayoutGoogle;

    protected SocialLoginGoogleLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout) {
        super(obj, view, i);
        this.linearLayoutGoogle = linearLayout;
    }

    public static SocialLoginGoogleLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginGoogleLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SocialLoginGoogleLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_google_layout, viewGroup, z, obj);
    }

    public static SocialLoginGoogleLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginGoogleLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SocialLoginGoogleLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_google_layout, (ViewGroup) null, false, obj);
    }

    public static SocialLoginGoogleLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginGoogleLayoutBinding bind(View view, Object obj) {
        return (SocialLoginGoogleLayoutBinding) bind(obj, view, R.layout.social_login_google_layout);
    }
}
