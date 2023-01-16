package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchCompat;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartFenceDialogSetModeTempLocationControlsBinding extends ViewDataBinding {
    public final Button buttonNegative;
    public final Button buttonPositive;
    public final Guideline guideline117;
    public final Guideline guideline186;
    public final Guideline guideline187;
    public final Guideline guideline188;
    public final Guideline guideline191;
    public final Guideline guideline195;
    public final Guideline guideline200;
    public final Guideline guideline201;
    public final Guideline guideline400;
    public final Guideline guideline401;
    public final Guideline guideline403;
    public final Guideline guideline501;
    public final Guideline guideline502;
    public final Guideline guideline73;
    public final ImageButton imageButtonDecreaseHumidity;
    public final ImageButton imageButtonDecreaseTemprature;
    public final ImageButton imageButtonIncreaseHumidity;
    public final ImageButton imageButtonIncreaseTemprature;
    public final ImageView imageViewAarivingLeaving;
    public final ImageView imageViewArrowDownMode;
    public final ImageView imageViewSelectedModeHome;
    public final ConstraintLayout layoutModeRoomDeviceControl;
    public final ConstraintLayout layoutSetTempratureRoomDeviceControl;
    public final ConstraintLayout layoutSwitchRoomDeviceControl;
    public final Button selectModeButton;
    public final CustomSwitchCompat switchMainHome;
    public final TextView textViewArrivingLeavingInsideGeorange;
    public final TextView textViewConfirmDialogTitle;
    public final TextView textViewHumidityPercentageIduControl;
    public final TextView textViewHumidityTitleRoomdeviceControl;
    public final TextView textViewOff;
    public final TextView textViewOn;
    public final TextView textViewPercent;
    public final TextView textViewSelectedModeHome;
    public final TextView textViewSetTemp;
    public final TextView textViewTemprature;
    public final TextView textViewTempratureUnit;
    public final View view53;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SmartFenceDialogSetModeTempLocationControlsBinding(Object obj, View view, int i, Button button, Button button2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, Guideline guideline12, Guideline guideline13, Guideline guideline14, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, Button button3, CustomSwitchCompat customSwitchCompat, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, View view2) {
        super(obj, view, i);
        this.buttonNegative = button;
        this.buttonPositive = button2;
        this.guideline117 = guideline;
        this.guideline186 = guideline2;
        this.guideline187 = guideline3;
        this.guideline188 = guideline4;
        this.guideline191 = guideline5;
        this.guideline195 = guideline6;
        this.guideline200 = guideline7;
        this.guideline201 = guideline8;
        this.guideline400 = guideline9;
        this.guideline401 = guideline10;
        this.guideline403 = guideline11;
        this.guideline501 = guideline12;
        this.guideline502 = guideline13;
        this.guideline73 = guideline14;
        this.imageButtonDecreaseHumidity = imageButton;
        this.imageButtonDecreaseTemprature = imageButton2;
        this.imageButtonIncreaseHumidity = imageButton3;
        this.imageButtonIncreaseTemprature = imageButton4;
        this.imageViewAarivingLeaving = imageView;
        this.imageViewArrowDownMode = imageView2;
        this.imageViewSelectedModeHome = imageView3;
        this.layoutModeRoomDeviceControl = constraintLayout;
        this.layoutSetTempratureRoomDeviceControl = constraintLayout2;
        this.layoutSwitchRoomDeviceControl = constraintLayout3;
        this.selectModeButton = button3;
        this.switchMainHome = customSwitchCompat;
        this.textViewArrivingLeavingInsideGeorange = textView;
        this.textViewConfirmDialogTitle = textView2;
        this.textViewHumidityPercentageIduControl = textView3;
        this.textViewHumidityTitleRoomdeviceControl = textView4;
        this.textViewOff = textView5;
        this.textViewOn = textView6;
        this.textViewPercent = textView7;
        this.textViewSelectedModeHome = textView8;
        this.textViewSetTemp = textView9;
        this.textViewTemprature = textView10;
        this.textViewTempratureUnit = textView11;
        this.view53 = view2;
    }

    public static SmartFenceDialogSetModeTempLocationControlsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceDialogSetModeTempLocationControlsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceDialogSetModeTempLocationControlsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_dialog_set_mode_temp_location_controls, viewGroup, z, obj);
    }

    public static SmartFenceDialogSetModeTempLocationControlsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceDialogSetModeTempLocationControlsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceDialogSetModeTempLocationControlsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_dialog_set_mode_temp_location_controls, (ViewGroup) null, false, obj);
    }

    public static SmartFenceDialogSetModeTempLocationControlsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceDialogSetModeTempLocationControlsBinding bind(View view, Object obj) {
        return (SmartFenceDialogSetModeTempLocationControlsBinding) bind(obj, view, R.layout.smart_fence_dialog_set_mode_temp_location_controls);
    }
}
