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

public abstract class DialogEnergyCostBillingStructureBinding extends ViewDataBinding {
    public final Button buttonCancel;
    public final Button buttonSave;
    public final ConstraintLayout layoutBillingStructure;
    public final RecyclerView recyclerViewBillingStructure;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewSelectBilingStructure;

    protected DialogEnergyCostBillingStructureBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.buttonCancel = button;
        this.buttonSave = button2;
        this.layoutBillingStructure = constraintLayout;
        this.recyclerViewBillingStructure = recyclerView;
        this.textViewConfirmDialogTitle = textView;
        this.textViewSelectBilingStructure = textView2;
    }

    public static DialogEnergyCostBillingStructureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostBillingStructureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogEnergyCostBillingStructureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_energy_cost_billing_structure, viewGroup, z, obj);
    }

    public static DialogEnergyCostBillingStructureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostBillingStructureBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogEnergyCostBillingStructureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_energy_cost_billing_structure, (ViewGroup) null, false, obj);
    }

    public static DialogEnergyCostBillingStructureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogEnergyCostBillingStructureBinding bind(View view, Object obj) {
        return (DialogEnergyCostBillingStructureBinding) bind(obj, view, R.layout.dialog_energy_cost_billing_structure);
    }
}
