package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DialogSetModeAndTempHolidaymodeBinding extends ViewDataBinding {
    public final Button buttonNegative;
    public final Button buttonPositive;
    public final ImageButton imageButtonDecreaseTemprature;
    public final ImageButton imageButtonIncreaseTemprature;
    public final ConstraintLayout layoutTemperature;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewTemprature;
    public final TextView textViewTempratureUnit;
    public final View view53;

    protected DialogSetModeAndTempHolidaymodeBinding(Object obj, View view, int i, Button button, Button button2, ImageButton imageButton, ImageButton imageButton2, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.buttonNegative = button;
        this.buttonPositive = button2;
        this.imageButtonDecreaseTemprature = imageButton;
        this.imageButtonIncreaseTemprature = imageButton2;
        this.layoutTemperature = constraintLayout;
        this.textViewConfirmDialogTitle = textView;
        this.textViewTemprature = textView2;
        this.textViewTempratureUnit = textView3;
        this.view53 = view2;
    }

    public static DialogSetModeAndTempHolidaymodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSetModeAndTempHolidaymodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogSetModeAndTempHolidaymodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_set_mode_and_temp_holidaymode, viewGroup, z, obj);
    }

    public static DialogSetModeAndTempHolidaymodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSetModeAndTempHolidaymodeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogSetModeAndTempHolidaymodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_set_mode_and_temp_holidaymode, (ViewGroup) null, false, obj);
    }

    public static DialogSetModeAndTempHolidaymodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSetModeAndTempHolidaymodeBinding bind(View view, Object obj) {
        return (DialogSetModeAndTempHolidaymodeBinding) bind(obj, view, R.layout.dialog_set_mode_and_temp_holidaymode);
    }
}
