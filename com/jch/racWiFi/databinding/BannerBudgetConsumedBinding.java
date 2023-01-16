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

public abstract class BannerBudgetConsumedBinding extends ViewDataBinding {
    public final Guideline guideline206;
    public final Guideline guideline207;
    public final Guideline guideline89;
    public final ImageButton imageButtonClear;
    public final ImageView imageViewGenericNotificationImage;
    public final ConstraintLayout layoutNotification;
    public final TextView textViewGenericNotificationDesc;
    public final TextView textViewGenericNotificationTitle;
    public final TextView textViewNotoficationDate;
    public final View view56;

    protected BannerBudgetConsumedBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.guideline206 = guideline;
        this.guideline207 = guideline2;
        this.guideline89 = guideline3;
        this.imageButtonClear = imageButton;
        this.imageViewGenericNotificationImage = imageView;
        this.layoutNotification = constraintLayout;
        this.textViewGenericNotificationDesc = textView;
        this.textViewGenericNotificationTitle = textView2;
        this.textViewNotoficationDate = textView3;
        this.view56 = view2;
    }

    public static BannerBudgetConsumedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerBudgetConsumedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BannerBudgetConsumedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_budget_consumed, viewGroup, z, obj);
    }

    public static BannerBudgetConsumedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerBudgetConsumedBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BannerBudgetConsumedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_budget_consumed, (ViewGroup) null, false, obj);
    }

    public static BannerBudgetConsumedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerBudgetConsumedBinding bind(View view, Object obj) {
        return (BannerBudgetConsumedBinding) bind(obj, view, R.layout.banner_budget_consumed);
    }
}
