package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class QrCodeScannedFrameBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline34;
    public final Guideline guideline35;
    public final QrCodeScannedVdBinding include;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewAddDeviceTitle;

    protected QrCodeScannedFrameBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, QrCodeScannedVdBinding qrCodeScannedVdBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline34 = guideline;
        this.guideline35 = guideline2;
        this.include = qrCodeScannedVdBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewAddDeviceTitle = textView;
    }

    public static QrCodeScannedFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QrCodeScannedFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (QrCodeScannedFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qr_code_scanned_frame, viewGroup, z, obj);
    }

    public static QrCodeScannedFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QrCodeScannedFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (QrCodeScannedFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qr_code_scanned_frame, (ViewGroup) null, false, obj);
    }

    public static QrCodeScannedFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QrCodeScannedFrameBinding bind(View view, Object obj) {
        return (QrCodeScannedFrameBinding) bind(obj, view, R.layout.qr_code_scanned_frame);
    }
}
