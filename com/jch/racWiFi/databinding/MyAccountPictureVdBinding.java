package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import p011de.hdodenhof.circleimageview.CircleImageView;

public abstract class MyAccountPictureVdBinding extends ViewDataBinding {
    public final Guideline guideline108;
    public final Guideline guideline111;
    public final Guideline guideline112;
    public final Guideline guideline114;
    public final Guideline guideline118;
    public final Guideline guideline202;
    public final Guideline guideline213;
    public final Guideline guideline214;
    public final Guideline guideline87;
    public final Guideline guideline88;
    public final Guideline guideline89;
    public final ImageView imageViewCamera;
    public final ImageView imageViewGallery;
    public final CircleImageView imageViewProfilePictureMyAccount;
    public final ImageView imageViewRemoveProfilePicture;
    public final TextView textViewChangeProfilePicture;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MyAccountPictureVdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, ImageView imageView, ImageView imageView2, CircleImageView circleImageView, ImageView imageView3, TextView textView) {
        super(obj, view, i);
        this.guideline108 = guideline;
        this.guideline111 = guideline2;
        this.guideline112 = guideline3;
        this.guideline114 = guideline4;
        this.guideline118 = guideline5;
        this.guideline202 = guideline6;
        this.guideline213 = guideline7;
        this.guideline214 = guideline8;
        this.guideline87 = guideline9;
        this.guideline88 = guideline10;
        this.guideline89 = guideline11;
        this.imageViewCamera = imageView;
        this.imageViewGallery = imageView2;
        this.imageViewProfilePictureMyAccount = circleImageView;
        this.imageViewRemoveProfilePicture = imageView3;
        this.textViewChangeProfilePicture = textView;
    }

    public static MyAccountPictureVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MyAccountPictureVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_account_picture_vd, viewGroup, z, obj);
    }

    public static MyAccountPictureVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MyAccountPictureVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_account_picture_vd, (ViewGroup) null, false, obj);
    }

    public static MyAccountPictureVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureVdBinding bind(View view, Object obj) {
        return (MyAccountPictureVdBinding) bind(obj, view, R.layout.my_account_picture_vd);
    }
}
