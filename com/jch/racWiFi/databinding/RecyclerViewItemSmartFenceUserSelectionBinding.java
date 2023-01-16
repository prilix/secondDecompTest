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

public abstract class RecyclerViewItemSmartFenceUserSelectionBinding extends ViewDataBinding {
    public final TriStateCheckbox cbMemberPermission;
    public final ImageView imageViewUserlist;
    public final ConstraintLayout layoutManageDevices;
    public final TextView textViewDoesNotSupportFeatue;
    public final TextView textViewUserName;
    public final View view38;

    protected RecyclerViewItemSmartFenceUserSelectionBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.cbMemberPermission = triStateCheckbox;
        this.imageViewUserlist = imageView;
        this.layoutManageDevices = constraintLayout;
        this.textViewDoesNotSupportFeatue = textView;
        this.textViewUserName = textView2;
        this.view38 = view2;
    }

    public static RecyclerViewItemSmartFenceUserSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemSmartFenceUserSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemSmartFenceUserSelectionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_smart_fence_user_selection, viewGroup, z, obj);
    }

    public static RecyclerViewItemSmartFenceUserSelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemSmartFenceUserSelectionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemSmartFenceUserSelectionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_item_smart_fence_user_selection, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemSmartFenceUserSelectionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemSmartFenceUserSelectionBinding bind(View view, Object obj) {
        return (RecyclerViewItemSmartFenceUserSelectionBinding) bind(obj, view, R.layout.recycler_view_item_smart_fence_user_selection);
    }
}
