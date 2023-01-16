package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SingleRowEnergyConsumptionBinding extends ViewDataBinding {
    public final TextView energyUsageTextView;
    public final ImageView imageView5;
    public final ImageView racIcon;
    public final TextView racNameTextView;
    public final TextView racUsageCostTextView;
    public final View viewDisableLayout;

    protected SingleRowEnergyConsumptionBinding(Object obj, View view, int i, TextView textView, ImageView imageView, ImageView imageView2, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.energyUsageTextView = textView;
        this.imageView5 = imageView;
        this.racIcon = imageView2;
        this.racNameTextView = textView2;
        this.racUsageCostTextView = textView3;
        this.viewDisableLayout = view2;
    }

    public static SingleRowEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SingleRowEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SingleRowEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.single_row_energy_consumption, viewGroup, z, obj);
    }

    public static SingleRowEnergyConsumptionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SingleRowEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SingleRowEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.single_row_energy_consumption, (ViewGroup) null, false, obj);
    }

    public static SingleRowEnergyConsumptionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SingleRowEnergyConsumptionBinding bind(View view, Object obj) {
        return (SingleRowEnergyConsumptionBinding) bind(obj, view, R.layout.single_row_energy_consumption);
    }
}
