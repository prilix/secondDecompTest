package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewItemsHolidaymodeBinding extends ViewDataBinding {
    public final TriStateCheckbox cbMemberPermission;
    public final ImageView imageViewAcs;
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewDeviceName;
    public final TextView textViewDoesNotSupportFeatue;
    public final View view38;

    protected RecyclerViewItemsHolidaymodeBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.cbMemberPermission = triStateCheckbox;
        this.imageViewAcs = imageView;
        this.layoutManageDevices = constraintLayout;
        this.textViewDeviceName = textView;
        this.textViewDoesNotSupportFeatue = textView2;
        this.view38 = view2;
    }

    public static RecyclerViewItemsHolidaymodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsHolidaymodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemsHolidaymodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_holidaymode, viewGroup, z, obj);
    }

    public static RecyclerViewItemsHolidaymodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsHolidaymodeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemsHolidaymodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_holidaymode, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemsHolidaymodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsHolidaymodeBinding bind(View view, Object obj) {
        return (RecyclerViewItemsHolidaymodeBinding) bind(obj, view, R.layout.recycler_view_items_holidaymode);
    }
}
