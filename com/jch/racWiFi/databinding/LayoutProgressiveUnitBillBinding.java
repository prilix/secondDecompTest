package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutProgressiveUnitBillBinding extends ViewDataBinding {
    public final EditText editTextFixedCharges;
    public final Guideline guideline48;
    public final Guideline guideline50;
    public final Guideline guideline57;
    public final ImageButton imageButtonAdd;
    public final ImageButton imageButtonMinus;
    public final ConstraintLayout layoutHeader;
    public final RecyclerView recyclerViewProgressiveUnit;
    public final TextView textViewCurrencyCode;
    public final TextView textViewFixedChargesPerMonth;
    public final TextView textViewFromUnits;
    public final TextView textViewToUnits;
    public final TextView textViewUnitPrizeKwhFixCharges;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutProgressiveUnitBillBinding(Object obj, View view, int i, EditText editText, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, ImageButton imageButton2, ConstraintLayout constraintLayout, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.editTextFixedCharges = editText;
        this.guideline48 = guideline;
        this.guideline50 = guideline2;
        this.guideline57 = guideline3;
        this.imageButtonAdd = imageButton;
        this.imageButtonMinus = imageButton2;
        this.layoutHeader = constraintLayout;
        this.recyclerViewProgressiveUnit = recyclerView;
        this.textViewCurrencyCode = textView;
        this.textViewFixedChargesPerMonth = textView2;
        this.textViewFromUnits = textView3;
        this.textViewToUnits = textView4;
        this.textViewUnitPrizeKwhFixCharges = textView5;
    }

    public static LayoutProgressiveUnitBillBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutProgressiveUnitBillBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutProgressiveUnitBillBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_progressive_unit_bill, viewGroup, z, obj);
    }

    public static LayoutProgressiveUnitBillBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutProgressiveUnitBillBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutProgressiveUnitBillBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_progressive_unit_bill, (ViewGroup) null, false, obj);
    }

    public static LayoutProgressiveUnitBillBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutProgressiveUnitBillBinding bind(View view, Object obj) {
        return (LayoutProgressiveUnitBillBinding) bind(obj, view, R.layout.layout_progressive_unit_bill);
    }
}
