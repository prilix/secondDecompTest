package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DilogEnergyConsuptionCostBinding extends ViewDataBinding {
    public final Button buttonNegative;
    public final Button buttonPositive;
    public final NumberPicker numberPickerUnitPrice;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewUnitPrice;
    public final View view53;

    protected DilogEnergyConsuptionCostBinding(Object obj, View view, int i, Button button, Button button2, NumberPicker numberPicker, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.buttonNegative = button;
        this.buttonPositive = button2;
        this.numberPickerUnitPrice = numberPicker;
        this.textViewConfirmDialogTitle = textView;
        this.textViewUnitPrice = textView2;
        this.view53 = view2;
    }

    public static DilogEnergyConsuptionCostBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyConsuptionCostBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DilogEnergyConsuptionCostBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dilog_energy_consuption_cost, viewGroup, z, obj);
    }

    public static DilogEnergyConsuptionCostBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyConsuptionCostBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DilogEnergyConsuptionCostBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dilog_energy_consuption_cost, (ViewGroup) null, false, obj);
    }

    public static DilogEnergyConsuptionCostBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyConsuptionCostBinding bind(View view, Object obj) {
        return (DilogEnergyConsuptionCostBinding) bind(obj, view, R.layout.dilog_energy_consuption_cost);
    }
}
