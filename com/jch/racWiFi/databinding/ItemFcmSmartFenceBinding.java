package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ItemFcmSmartFenceBinding extends ViewDataBinding {
    public final Guideline guideline206;
    public final Guideline guideline207;
    public final Guideline guideline89;
    public final ImageButton imageButtonClear;
    public final TextView itemFcmSmartFenceDesc;
    public final ImageView itemFcmSmartFenceImage;
    public final TextView itemFcmSmartFenceTitle;
    public final ConstraintLayout layoutNotification;
    public final TextView textViewNotoficationDate;
    public final View view56;

    protected ItemFcmSmartFenceBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, TextView textView, ImageView imageView, TextView textView2, ConstraintLayout constraintLayout, TextView textView3, View view2) {
        super(obj, view, i);
        this.guideline206 = guideline;
        this.guideline207 = guideline2;
        this.guideline89 = guideline3;
        this.imageButtonClear = imageButton;
        this.itemFcmSmartFenceDesc = textView;
        this.itemFcmSmartFenceImage = imageView;
        this.itemFcmSmartFenceTitle = textView2;
        this.layoutNotification = constraintLayout;
        this.textViewNotoficationDate = textView3;
        this.view56 = view2;
    }

    public static ItemFcmSmartFenceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmSmartFenceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemFcmSmartFenceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fcm_smart_fence, viewGroup, z, obj);
    }

    public static ItemFcmSmartFenceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmSmartFenceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemFcmSmartFenceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fcm_smart_fence, (ViewGroup) null, false, obj);
    }

    public static ItemFcmSmartFenceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmSmartFenceBinding bind(View view, Object obj) {
        return (ItemFcmSmartFenceBinding) bind(obj, view, R.layout.item_fcm_smart_fence);
    }
}
