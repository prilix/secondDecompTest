package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ToolbarEnergyConsumptionBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ConstraintLayout constraintLayout1;
    public final ImageButton imageButtonMenu;
    public final ImageButton textViewSave;
    public final TextView toolbarTitle;

    protected ToolbarEnergyConsumptionBinding(Object obj, View view, int i, ImageButton imageButton, ConstraintLayout constraintLayout, ImageButton imageButton2, ImageButton imageButton3, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.constraintLayout1 = constraintLayout;
        this.imageButtonMenu = imageButton2;
        this.textViewSave = imageButton3;
        this.toolbarTitle = textView;
    }

    public static ToolbarEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ToolbarEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_energy_consumption, viewGroup, z, obj);
    }

    public static ToolbarEnergyConsumptionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarEnergyConsumptionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ToolbarEnergyConsumptionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_energy_consumption, (ViewGroup) null, false, obj);
    }

    public static ToolbarEnergyConsumptionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarEnergyConsumptionBinding bind(View view, Object obj) {
        return (ToolbarEnergyConsumptionBinding) bind(obj, view, R.layout.toolbar_energy_consumption);
    }
}
