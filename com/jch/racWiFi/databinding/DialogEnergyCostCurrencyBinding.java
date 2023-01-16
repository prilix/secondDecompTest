package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DialogEnergyCostCurrencyBinding extends ViewDataBinding {
    public final Button buttonCancel;
    public final Button buttonSave;
    public final ConstraintLayout constraint1;
    public final ConstraintLayout layoutTop;
    public final SearchCurrencyBinding llinearlayoutIncluded;
    public final RecyclerView recyclerViewCurrency;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewSelectCurrency;

    protected DialogEnergyCostCurrencyBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, SearchCurrencyBinding searchCurrencyBinding, RecyclerView recyclerView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.buttonCancel = button;
        this.buttonSave = button2;
        this.constraint1 = constraintLayout;
        this.layoutTop = constraintLayout2;
        this.llinearlayoutIncluded = searchCurrencyBinding;
        this.recyclerViewCurrency = recyclerView;
        this.textViewConfirmDialogTitle = textView;
        this.textViewSelectCurrency = textView2;
    }

    public static DialogEnergyCostCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogEnergyCostCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_energy_cost_currency, viewGroup, z, obj);
    }

    public static DialogEnergyCostCurrencyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostCurrencyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogEnergyCostCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_energy_cost_currency, (ViewGroup) null, false, obj);
    }

    public static DialogEnergyCostCurrencyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostCurrencyBinding bind(View view, Object obj) {
        return (DialogEnergyCostCurrencyBinding) bind(obj, view, R.layout.dialog_energy_cost_currency);
    }
}
