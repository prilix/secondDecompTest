package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LocationServicesDisabledDialogBinding extends ViewDataBinding {
    public final Button buttonEnableLocationAccess;
    public final Button buttonTurnOffSmartFence;
    public final ConstraintLayout constraintLayoutLocationDialog;
    public final ImageButton imageButtonClear;
    public final ImageView imageViewLoaction;
    public final TextView locationControlNote;
    public final TextView textViewLoactionAccess;
    public final TextView textViewLocationAccessDescOne;
    public final TextView textViewNotNow;

    protected LocationServicesDisabledDialogBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ImageButton imageButton, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.buttonEnableLocationAccess = button;
        this.buttonTurnOffSmartFence = button2;
        this.constraintLayoutLocationDialog = constraintLayout;
        this.imageButtonClear = imageButton;
        this.imageViewLoaction = imageView;
        this.locationControlNote = textView;
        this.textViewLoactionAccess = textView2;
        this.textViewLocationAccessDescOne = textView3;
        this.textViewNotNow = textView4;
    }

    public static LocationServicesDisabledDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationServicesDisabledDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LocationServicesDisabledDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_services_disabled_dialog, viewGroup, z, obj);
    }

    public static LocationServicesDisabledDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationServicesDisabledDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LocationServicesDisabledDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_services_disabled_dialog, (ViewGroup) null, false, obj);
    }

    public static LocationServicesDisabledDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationServicesDisabledDialogBinding bind(View view, Object obj) {
        return (LocationServicesDisabledDialogBinding) bind(obj, view, R.layout.location_services_disabled_dialog);
    }
}
