package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentEcHomeBinding extends ViewDataBinding {
    public final Button buttonSelectAcs;
    public final ToolbarEnergyConsumptionBinding include2;
    public final TextView textView;
    public final TextView textView1;

    protected FragmentEcHomeBinding(Object obj, View view, int i, Button button, ToolbarEnergyConsumptionBinding toolbarEnergyConsumptionBinding, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.buttonSelectAcs = button;
        this.include2 = toolbarEnergyConsumptionBinding;
        this.textView = textView2;
        this.textView1 = textView3;
    }

    public static FragmentEcHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentEcHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ec_home, viewGroup, z, obj);
    }

    public static FragmentEcHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcHomeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentEcHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ec_home, (ViewGroup) null, false, obj);
    }

    public static FragmentEcHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEcHomeBinding bind(View view, Object obj) {
        return (FragmentEcHomeBinding) bind(obj, view, R.layout.fragment_ec_home);
    }
}
