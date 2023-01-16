package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentEnergyConsumptionBinding extends ViewDataBinding {
    public final ToolbarEnergyConsumptionBinding actionBarLayout;
    public final ImageButton addRacImageButton;
    public final View allAcDivider;
    public final ConstraintLayout allAcLayout;
    public final TextView allAcLbltextView;
    public final RecyclerView allRacListRv;
    public final ConstraintLayout energyCostRacsContentLayout;
    public final ImageView imageViewAllAcs;
    public final ConstraintLayout layoutNoRac;
    public final View selectAcDivider;
    public final LayoutSelectAcBinding selectAcLayout;
    public final TextView textViewClickplusbutton;
    public final TextView textViewNoDevicesFound;
    public final TextView totalBugdetTextView;
    public final TextView totalCostTextView;
    public final TextView totalEnergryTextView;
    public final View viewDisableLayoutAllAc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentEnergyConsumptionBinding(Object obj, View view, int i, ToolbarEnergyConsumptionBinding toolbarEnergyConsumptionBinding, ImageButton imageButton, View view2, ConstraintLayout constraintLayout, TextView textView, RecyclerView recyclerView, ConstraintLayout constraintLayout2, ImageView imageView, ConstraintLayout constraintLayout3, View view3, LayoutSelectAcBinding layoutSelectAcBinding, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view4) {
        super(obj, view, i);
        this.actionBarLayout = toolbarEnergyConsumptionBinding;
        this.addRacImageButton = imageButton;
        this.allAcDivider = view2;
        this.allAcLayout = constraintLayout;
        this.allAcLbltextView = textView;
        this.allRacListRv = recyclerView;
        this.energyCostRacsContentLayout = constraintLayout2;
        this.imageViewAllAcs = imageView;
        this.layoutNoRac = constraintLayout3;
        this.selectAcDivider = view3;
        this.selectAcLayout = layoutSelectAcBinding;
        this.textViewClickplusbutton = textView2;
        this.textViewNoDevicesFound = textView3;
        this.totalBugdetTextView = textView4;
        this.totalCostTextView = textView5;
        this.totalEnergryTextView = textView6;
        this.viewDisableLayoutAllAc = view4;
    }

    public static FragmentEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_energy_consumption, viewGroup, z, obj);
    }

    public static FragmentEnergyConsumptionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_energy_consumption, (ViewGroup) null, false, obj);
    }

    public static FragmentEnergyConsumptionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEnergyConsumptionBinding bind(View view, Object obj) {
        return (FragmentEnergyConsumptionBinding) bind(obj, view, R.layout.fragment_energy_consumption);
    }
}
