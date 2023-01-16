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

public abstract class RecyclerViewItemBillingStructureBinding extends ViewDataBinding {
    public final ImageView imageViewTickIcon;
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewBillingName;
    public final View view38;

    protected RecyclerViewItemBillingStructureBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, View view2) {
        super(obj, view, i);
        this.imageViewTickIcon = imageView;
        this.layoutManageDevices = constraintLayout;
        this.textViewBillingName = textView;
        this.view38 = view2;
    }

    public static RecyclerViewItemBillingStructureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemBillingStructureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemBillingStructureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_billing_structure, viewGroup, z, obj);
    }

    public static RecyclerViewItemBillingStructureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemBillingStructureBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemBillingStructureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_billing_structure, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemBillingStructureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemBillingStructureBinding bind(View view, Object obj) {
        return (RecyclerViewItemBillingStructureBinding) bind(obj, view, R.layout.recycler_view_item_billing_structure);
    }
}
