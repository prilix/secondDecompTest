package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutEnergyBudgetBinding extends ViewDataBinding {
    public final ImageButton clearBudgetIcon;
    public final EditText enterBudgetEditText;
    public final Guideline guideline33;
    public final LinearLayout layoutEnegyBudget;
    public final ConstraintLayout layoutEneryBudget;
    public final ConstraintLayout selectBudgetDropDown;
    public final CustomSwitchButton switchEnergyBudget;
    public final TextView textViewCurrencyCode;
    public final TextView textViewEneryBudgetPerMonth;
    public final TextView textViewEneryBudgetTitle;

    protected LayoutEnergyBudgetBinding(Object obj, View view, int i, ImageButton imageButton, EditText editText, Guideline guideline, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, CustomSwitchButton customSwitchButton, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clearBudgetIcon = imageButton;
        this.enterBudgetEditText = editText;
        this.guideline33 = guideline;
        this.layoutEnegyBudget = linearLayout;
        this.layoutEneryBudget = constraintLayout;
        this.selectBudgetDropDown = constraintLayout2;
        this.switchEnergyBudget = customSwitchButton;
        this.textViewCurrencyCode = textView;
        this.textViewEneryBudgetPerMonth = textView2;
        this.textViewEneryBudgetTitle = textView3;
    }

    public static LayoutEnergyBudgetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEnergyBudgetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutEnergyBudgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_energy_budget, viewGroup, z, obj);
    }

    public static LayoutEnergyBudgetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEnergyBudgetBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutEnergyBudgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_energy_budget, (ViewGroup) null, false, obj);
    }

    public static LayoutEnergyBudgetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutEnergyBudgetBinding bind(View view, Object obj) {
        return (LayoutEnergyBudgetBinding) bind(obj, view, R.layout.layout_energy_budget);
    }
}
