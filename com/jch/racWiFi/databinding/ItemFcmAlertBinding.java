package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ItemFcmAlertBinding extends ViewDataBinding {
    public final Guideline guideline206;
    public final Guideline guideline207;
    public final Guideline guideline89;
    public final ImageButton itemFcmAlertClearImage;
    public final TextView itemFcmAlertDate;
    public final TextView itemFcmAlertDescription;
    public final ImageView itemFcmAlertImage;
    public final TextView itemFcmAlertTitle;
    public final View view56;

    protected ItemFcmAlertBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, TextView textView, TextView textView2, ImageView imageView, TextView textView3, View view2) {
        super(obj, view, i);
        this.guideline206 = guideline;
        this.guideline207 = guideline2;
        this.guideline89 = guideline3;
        this.itemFcmAlertClearImage = imageButton;
        this.itemFcmAlertDate = textView;
        this.itemFcmAlertDescription = textView2;
        this.itemFcmAlertImage = imageView;
        this.itemFcmAlertTitle = textView3;
        this.view56 = view2;
    }

    public static ItemFcmAlertBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmAlertBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemFcmAlertBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fcm_alert, viewGroup, z, obj);
    }

    public static ItemFcmAlertBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmAlertBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemFcmAlertBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fcm_alert, (ViewGroup) null, false, obj);
    }

    public static ItemFcmAlertBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFcmAlertBinding bind(View view, Object obj) {
        return (ItemFcmAlertBinding) bind(obj, view, R.layout.item_fcm_alert);
    }
}
