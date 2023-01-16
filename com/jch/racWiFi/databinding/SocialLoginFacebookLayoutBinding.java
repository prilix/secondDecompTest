package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SocialLoginFacebookLayoutBinding extends ViewDataBinding {
    public final LinearLayout linearLayoutFacebook;

    protected SocialLoginFacebookLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout) {
        super(obj, view, i);
        this.linearLayoutFacebook = linearLayout;
    }

    public static SocialLoginFacebookLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginFacebookLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SocialLoginFacebookLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_facebook_layout, viewGroup, z, obj);
    }

    public static SocialLoginFacebookLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginFacebookLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SocialLoginFacebookLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_facebook_layout, (ViewGroup) null, false, obj);
    }

    public static SocialLoginFacebookLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginFacebookLayoutBinding bind(View view, Object obj) {
        return (SocialLoginFacebookLayoutBinding) bind(obj, view, R.layout.social_login_facebook_layout);
    }
}
