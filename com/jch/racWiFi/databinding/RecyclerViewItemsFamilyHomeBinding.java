package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewItemsFamilyHomeBinding extends ViewDataBinding {
    public final ConstraintLayout layoutFamilyGroup;
    public final TextView textViewLanguage;
    public final View view38;

    protected RecyclerViewItemsFamilyHomeBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, View view2) {
        super(obj, view, i);
        this.layoutFamilyGroup = constraintLayout;
        this.textViewLanguage = textView;
        this.view38 = view2;
    }

    public static RecyclerViewItemsFamilyHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsFamilyHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemsFamilyHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_family_home, viewGroup, z, obj);
    }

    public static RecyclerViewItemsFamilyHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsFamilyHomeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemsFamilyHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_family_home, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemsFamilyHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsFamilyHomeBinding bind(View view, Object obj) {
        return (RecyclerViewItemsFamilyHomeBinding) bind(obj, view, R.layout.recycler_view_items_family_home);
    }
}
