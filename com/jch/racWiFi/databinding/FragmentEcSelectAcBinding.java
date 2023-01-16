package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentEcSelectAcBinding extends ViewDataBinding {
    public final ToolbarEnergyConsumptionBinding includeToolbar;
    public final LayoutEcNoDataBinding layoutEcNoData;
    public final LayoutRacListBinding layoutRacList;

    protected FragmentEcSelectAcBinding(Object obj, View view, int i, ToolbarEnergyConsumptionBinding toolbarEnergyConsumptionBinding, LayoutEcNoDataBinding layoutEcNoDataBinding, LayoutRacListBinding layoutRacListBinding) {
        super(obj, view, i);
        this.includeToolbar = toolbarEnergyConsumptionBinding;
        this.layoutEcNoData = layoutEcNoDataBinding;
        this.layoutRacList = layoutRacListBinding;
    }

    public static FragmentEcSelectAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcSelectAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentEcSelectAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ec_select_ac, viewGroup, z, obj);
    }

    public static FragmentEcSelectAcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcSelectAcBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentEcSelectAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ec_select_ac, (ViewGroup) null, false, obj);
    }

    public static FragmentEcSelectAcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcSelectAcBinding bind(View view, Object obj) {
        return (FragmentEcSelectAcBinding) bind(obj, view, R.layout.fragment_ec_select_ac);
    }
}
