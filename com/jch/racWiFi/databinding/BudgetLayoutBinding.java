package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch_hitachi.aircloudglobal.R;

public abstract class BudgetLayoutBinding extends ViewDataBinding {
    public final AutoCompleteTextView autoCompleteTextView1;
    public final ImageButton clear;
    public final ImageButton close;
    public final ConstraintLayout selectBudgetDropDown;

    protected BudgetLayoutBinding(Object obj, View view, int i, AutoCompleteTextView autoCompleteTextView, ImageButton imageButton, ImageButton imageButton2, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.autoCompleteTextView1 = autoCompleteTextView;
        this.clear = imageButton;
        this.close = imageButton2;
        this.selectBudgetDropDown = constraintLayout;
    }

    public static BudgetLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BudgetLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BudgetLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.budget_layout, viewGroup, z, obj);
    }

    public static BudgetLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BudgetLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BudgetLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.budget_layout, (ViewGroup) null, false, obj);
    }

    public static BudgetLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BudgetLayoutBinding bind(View view, Object obj) {
        return (BudgetLayoutBinding) bind(obj, view, R.layout.budget_layout);
    }
}
