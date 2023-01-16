package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class MyAccountPictureFrameBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final MyAccountPictureVdBinding include;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewMyAccountTitle;
    public final ImageButton textViewSave;

    protected MyAccountPictureFrameBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, MyAccountPictureVdBinding myAccountPictureVdBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView, ImageButton imageButton2) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.include = myAccountPictureVdBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewMyAccountTitle = textView;
        this.textViewSave = imageButton2;
    }

    public static MyAccountPictureFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MyAccountPictureFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_account_picture_frame, viewGroup, z, obj);
    }

    public static MyAccountPictureFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MyAccountPictureFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_account_picture_frame, (ViewGroup) null, false, obj);
    }

    public static MyAccountPictureFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyAccountPictureFrameBinding bind(View view, Object obj) {
        return (MyAccountPictureFrameBinding) bind(obj, view, R.layout.my_account_picture_frame);
    }
}
