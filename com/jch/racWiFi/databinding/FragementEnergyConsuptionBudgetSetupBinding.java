package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragementEnergyConsuptionBudgetSetupBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final ImageView imageViewDropDownCurrency;
    public final ImageView imageViewDropDownRate;
    public final ConstraintLayout layoutEnergyCost;
    public final ConstraintLayout layoutHoursAndMonthUnit;
    public final ConstraintLayout layoutInclude;
    public final LayoutEnergyBudgetBinding layoutIncludeEnergyBudget;
    public final LayoutProgressiveUnitBillBinding layoutIncludeProgressiveUnitBill;
    public final LayoutPeakHoursAndMonthBinding layoutIncludeSelectPeakHoursMonths;
    public final LayoutSimpleFlatRateBinding layoutIncludeUnitPrice;
    public final ConstraintLayout layoutProgressiveUnit;
    public final ConstraintLayout layoutSelectBillingStructure;
    public final ConstraintLayout layoutSelectCurrency;
    public final ConstraintLayout layoutSimpleFlatUnit;
    public final ConstraintLayout layoutTop;
    public final TextView textViewCurrencyCode;
    public final TextView textViewCurrencyName;
    public final TextView textViewEnergyConsuptionTitle;
    public final TextView textViewEnergyCostTitle;
    public final ImageButton textViewSave;
    public final TextView textViewSelectBilingStructure;
    public final TextView textViewSelectCurrency;
    public final TextView textViewSelectedRate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragementEnergyConsuptionBudgetSetupBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LayoutEnergyBudgetBinding layoutEnergyBudgetBinding, LayoutProgressiveUnitBillBinding layoutProgressiveUnitBillBinding, LayoutPeakHoursAndMonthBinding layoutPeakHoursAndMonthBinding, LayoutSimpleFlatRateBinding layoutSimpleFlatRateBinding, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageButton imageButton2, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.imageViewDropDownCurrency = imageView;
        this.imageViewDropDownRate = imageView2;
        this.layoutEnergyCost = constraintLayout;
        this.layoutHoursAndMonthUnit = constraintLayout2;
        this.layoutInclude = constraintLayout3;
        this.layoutIncludeEnergyBudget = layoutEnergyBudgetBinding;
        this.layoutIncludeProgressiveUnitBill = layoutProgressiveUnitBillBinding;
        this.layoutIncludeSelectPeakHoursMonths = layoutPeakHoursAndMonthBinding;
        this.layoutIncludeUnitPrice = layoutSimpleFlatRateBinding;
        this.layoutProgressiveUnit = constraintLayout4;
        this.layoutSelectBillingStructure = constraintLayout5;
        this.layoutSelectCurrency = constraintLayout6;
        this.layoutSimpleFlatUnit = constraintLayout7;
        this.layoutTop = constraintLayout8;
        this.textViewCurrencyCode = textView;
        this.textViewCurrencyName = textView2;
        this.textViewEnergyConsuptionTitle = textView3;
        this.textViewEnergyCostTitle = textView4;
        this.textViewSave = imageButton2;
        this.textViewSelectBilingStructure = textView5;
        this.textViewSelectCurrency = textView6;
        this.textViewSelectedRate = textView7;
    }

    public static FragementEnergyConsuptionBudgetSetupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragementEnergyConsuptionBudgetSetupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragementEnergyConsuptionBudgetSetupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragement_energy_consuption_budget_setup, viewGroup, z, obj);
    }

    public static FragementEnergyConsuptionBudgetSetupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragementEnergyConsuptionBudgetSetupBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragementEnergyConsuptionBudgetSetupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragement_energy_consuption_budget_setup, (ViewGroup) null, false, obj);
    }

    public static FragementEnergyConsuptionBudgetSetupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragementEnergyConsuptionBudgetSetupBinding bind(View view, Object obj) {
        return (FragementEnergyConsuptionBudgetSetupBinding) bind(obj, view, R.layout.fragement_energy_consuption_budget_setup);
    }
}
