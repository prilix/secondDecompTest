package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchCompat;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DilogEnergyBudgetBinding extends ViewDataBinding {
    public final Button buttonNegative;
    public final Button buttonPositive;
    public final Guideline guideline33;
    public final LinearLayout layoutEnegyBudget;
    public final BudgetLayoutBinding llinearlayoutIncluded;
    public final CustomSwitchCompat switchMainHome;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewEnergybudgetPerMonth;
    public final TextView textViewJapaneseUnit;
    public final View view53;

    protected DilogEnergyBudgetBinding(Object obj, View view, int i, Button button, Button button2, Guideline guideline, LinearLayout linearLayout, BudgetLayoutBinding budgetLayoutBinding, CustomSwitchCompat customSwitchCompat, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.buttonNegative = button;
        this.buttonPositive = button2;
        this.guideline33 = guideline;
        this.layoutEnegyBudget = linearLayout;
        this.llinearlayoutIncluded = budgetLayoutBinding;
        this.switchMainHome = customSwitchCompat;
        this.textViewConfirmDialogTitle = textView;
        this.textViewEnergybudgetPerMonth = textView2;
        this.textViewJapaneseUnit = textView3;
        this.view53 = view2;
    }

    public static DilogEnergyBudgetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyBudgetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DilogEnergyBudgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dilog_energy_budget, viewGroup, z, obj);
    }

    public static DilogEnergyBudgetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyBudgetBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DilogEnergyBudgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dilog_energy_budget, (ViewGroup) null, false, obj);
    }

    public static DilogEnergyBudgetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DilogEnergyBudgetBinding bind(View view, Object obj) {
        return (DilogEnergyBudgetBinding) bind(obj, view, R.layout.dilog_energy_budget);
    }
}
