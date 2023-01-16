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

public abstract class AccountCreationSuccessConstarintVdBinding extends ViewDataBinding {
    public final Guideline guideline10;
    public final Guideline guideline11;
    public final Guideline guideline12;
    public final Guideline guideline13;
    public final ImageView imageViewAcoountCreated;
    public final TextView textViewAccountCreatedSubtitle1;
    public final TextView textViewAcoountCreatedSubtitle2;

    protected AccountCreationSuccessConstarintVdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.guideline10 = guideline;
        this.guideline11 = guideline2;
        this.guideline12 = guideline3;
        this.guideline13 = guideline4;
        this.imageViewAcoountCreated = imageView;
        this.textViewAccountCreatedSubtitle1 = textView;
        this.textViewAcoountCreatedSubtitle2 = textView2;
    }

    public static AccountCreationSuccessConstarintVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AccountCreationSuccessConstarintVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AccountCreationSuccessConstarintVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.account_creation_success_constarint_vd, viewGroup, z, obj);
    }

    public static AccountCreationSuccessConstarintVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AccountCreationSuccessConstarintVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AccountCreationSuccessConstarintVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.account_creation_success_constarint_vd, (ViewGroup) null, false, obj);
    }

    public static AccountCreationSuccessConstarintVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AccountCreationSuccessConstarintVdBinding bind(View view, Object obj) {
        return (AccountCreationSuccessConstarintVdBinding) bind(obj, view, R.layout.account_creation_success_constarint_vd);
    }
}
