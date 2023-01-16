package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import p015me.dm7.barcodescanner.zxing.ZXingScannerView;

public abstract class AddDeviceStep1Of5VdBinding extends ViewDataBinding {
    public final Button buttonUnableToScanQrCode;
    public final Button buttonWhereToLocateQrCode;
    public final Guideline guideline242;
    public final Guideline guideline243;
    public final Guideline guideline244;
    public final Guideline guideline245;
    public final Guideline guideline246;
    public final Guideline guideline247;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout qrCodeLayout;
    public final ImageView retryImageView;
    public final ConstraintLayout retryQrScanLayout;
    public final TextView retryTextview;
    public final ZXingScannerView scannerID;
    public final TextView textViewSacnQrCode2BuiltInSubTitle;
    public final TextView textViewSacnQrCodeTitle;
    public final TextView textViewSacnQrCodeTitle2;
    public final View view12;
    public final View view15;
    public final View view16;
    public final View view17;
    public final View view18;
    public final View view19;
    public final View view21;
    public final View view22;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AddDeviceStep1Of5VdBinding(Object obj, View view, int i, Button button, Button button2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout, ImageView imageView, ConstraintLayout constraintLayout2, TextView textView, ZXingScannerView zXingScannerView, TextView textView2, TextView textView3, TextView textView4, View view2, View view3, View view4, View view5, View view6, View view7, View view8, View view9) {
        super(obj, view, i);
        this.buttonUnableToScanQrCode = button;
        this.buttonWhereToLocateQrCode = button2;
        this.guideline242 = guideline;
        this.guideline243 = guideline2;
        this.guideline244 = guideline3;
        this.guideline245 = guideline4;
        this.guideline246 = guideline5;
        this.guideline247 = guideline6;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.qrCodeLayout = constraintLayout;
        this.retryImageView = imageView;
        this.retryQrScanLayout = constraintLayout2;
        this.retryTextview = textView;
        this.scannerID = zXingScannerView;
        this.textViewSacnQrCode2BuiltInSubTitle = textView2;
        this.textViewSacnQrCodeTitle = textView3;
        this.textViewSacnQrCodeTitle2 = textView4;
        this.view12 = view2;
        this.view15 = view3;
        this.view16 = view4;
        this.view17 = view5;
        this.view18 = view6;
        this.view19 = view7;
        this.view21 = view8;
        this.view22 = view9;
    }

    public static AddDeviceStep1Of5VdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5VdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AddDeviceStep1Of5VdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_device_step_1_of_5_vd, viewGroup, z, obj);
    }

    public static AddDeviceStep1Of5VdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5VdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AddDeviceStep1Of5VdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.add_device_step_1_of_5_vd, (ViewGroup) null, false, obj);
    }

    public static AddDeviceStep1Of5VdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddDeviceStep1Of5VdBinding bind(View view, Object obj) {
        return (AddDeviceStep1Of5VdBinding) bind(obj, view, R.layout.add_device_step_1_of_5_vd);
    }
}
