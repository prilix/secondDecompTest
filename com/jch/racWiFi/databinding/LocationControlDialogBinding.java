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

public abstract class LocationControlDialogBinding extends ViewDataBinding {
    public final Button buttonEnableLocationAccess;
    public final ConstraintLayout constraintLayoutLocationDialog;
    public final ImageButton imageButtonClear;
    public final ImageView imageViewLoaction;
    public final TextView locationControlNote;
    public final TextView locationControlNoteMyAccountAddress;
    public final TextView textViewLoactionAccess;
    public final TextView textViewLocationAccessDescOne;
    public final TextView textViewLocationAccessDescTwo;
    public final TextView textViewRealtimeWeatherInfo;
    public final TextView textViewRunOperationBasedOnGeoLocation;
    public final TextView textViewScanWifi;

    protected LocationControlDialogBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ImageButton imageButton, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.buttonEnableLocationAccess = button;
        this.constraintLayoutLocationDialog = constraintLayout;
        this.imageButtonClear = imageButton;
        this.imageViewLoaction = imageView;
        this.locationControlNote = textView;
        this.locationControlNoteMyAccountAddress = textView2;
        this.textViewLoactionAccess = textView3;
        this.textViewLocationAccessDescOne = textView4;
        this.textViewLocationAccessDescTwo = textView5;
        this.textViewRealtimeWeatherInfo = textView6;
        this.textViewRunOperationBasedOnGeoLocation = textView7;
        this.textViewScanWifi = textView8;
    }

    public static LocationControlDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationControlDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LocationControlDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_control_dialog, viewGroup, z, obj);
    }

    public static LocationControlDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationControlDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LocationControlDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_control_dialog, (ViewGroup) null, false, obj);
    }

    public static LocationControlDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationControlDialogBinding bind(View view, Object obj) {
        return (LocationControlDialogBinding) bind(obj, view, R.layout.location_control_dialog);
    }
}
