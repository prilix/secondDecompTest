package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewItemCurrencyBinding extends ViewDataBinding {
    public final ImageView imageViewTickMark;
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewCurrencyCode;
    public final TextView textViewCurrencyValue;
    public final View view38;

    protected RecyclerViewItemCurrencyBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.imageViewTickMark = imageView;
        this.layoutManageDevices = constraintLayout;
        this.textViewCurrencyCode = textView;
        this.textViewCurrencyValue = textView2;
        this.view38 = view2;
    }

    public static RecyclerViewItemCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_currency, viewGroup, z, obj);
    }

    public static RecyclerViewItemCurrencyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemCurrencyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_currency, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemCurrencyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemCurrencyBinding bind(View view, Object obj) {
        return (RecyclerViewItemCurrencyBinding) bind(obj, view, R.layout.recycler_view_item_currency);
    }
}
