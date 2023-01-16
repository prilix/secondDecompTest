package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutSimpleFlatRateBinding extends ViewDataBinding {
    public final TextView textViewUnitPrizeKwh;
    public final EditText unitPriceFlatRateEditText;

    protected LayoutSimpleFlatRateBinding(Object obj, View view, int i, TextView textView, EditText editText) {
        super(obj, view, i);
        this.textViewUnitPrizeKwh = textView;
        this.unitPriceFlatRateEditText = editText;
    }

    public static LayoutSimpleFlatRateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSimpleFlatRateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutSimpleFlatRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_simple_flat_rate, viewGroup, z, obj);
    }

    public static LayoutSimpleFlatRateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSimpleFlatRateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutSimpleFlatRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_simple_flat_rate, (ViewGroup) null, false, obj);
    }

    public static LayoutSimpleFlatRateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSimpleFlatRateBinding bind(View view, Object obj) {
        return (LayoutSimpleFlatRateBinding) bind(obj, view, R.layout.layout_simple_flat_rate);
    }
}
