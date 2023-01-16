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

public abstract class RecycleViewItemsSmartFenceMultipleDevicesBinding extends ViewDataBinding {
    public final TriStateCheckbox cbMemberPermission;
    public final ImageView imageViewAcs;
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewDeviceName;
    public final TextView textViewDoesNotSupportFeatue;
    public final View view38;

    protected RecycleViewItemsSmartFenceMultipleDevicesBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.cbMemberPermission = triStateCheckbox;
        this.imageViewAcs = imageView;
        this.layoutManageDevices = constraintLayout;
        this.textViewDeviceName = textView;
        this.textViewDoesNotSupportFeatue = textView2;
        this.view38 = view2;
    }

    public static RecycleViewItemsSmartFenceMultipleDevicesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecycleViewItemsSmartFenceMultipleDevicesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecycleViewItemsSmartFenceMultipleDevicesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycle_view_items_smart_fence_multiple_devices, viewGroup, z, obj);
    }

    public static RecycleViewItemsSmartFenceMultipleDevicesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecycleViewItemsSmartFenceMultipleDevicesBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecycleViewItemsSmartFenceMultipleDevicesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycle_view_items_smart_fence_multiple_devices, (ViewGroup) null, false, obj);
    }

    public static RecycleViewItemsSmartFenceMultipleDevicesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecycleViewItemsSmartFenceMultipleDevicesBinding bind(View view, Object obj) {
        return (RecycleViewItemsSmartFenceMultipleDevicesBinding) bind(obj, view, R.layout.recycle_view_items_smart_fence_multiple_devices);
    }
}
