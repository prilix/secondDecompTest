package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SocialLoginWechatLayoutBinding extends ViewDataBinding {
    public final LinearLayout linearLayoutWechat;

    protected SocialLoginWechatLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout) {
        super(obj, view, i);
        this.linearLayoutWechat = linearLayout;
    }

    public static SocialLoginWechatLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginWechatLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SocialLoginWechatLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_wechat_layout, viewGroup, z, obj);
    }

    public static SocialLoginWechatLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginWechatLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SocialLoginWechatLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_login_wechat_layout, (ViewGroup) null, false, obj);
    }

    public static SocialLoginWechatLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialLoginWechatLayoutBinding bind(View view, Object obj) {
        return (SocialLoginWechatLayoutBinding) bind(obj, view, R.layout.social_login_wechat_layout);
    }
}
