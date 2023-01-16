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

public abstract class HelpVdGlobalAppBinding extends ViewDataBinding {
    public final ConstraintLayout constarint1;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final Guideline guideline295;
    public final Guideline guideline296;
    public final Guideline guideline297;
    public final Guideline guideline298;
    public final Guideline guideline299;
    public final Guideline guideline305;
    public final ImageView imageViewArrowDown;
    public final ImageView imageViewDownloadUserManual;
    public final ImageView imageViewPdf;
    public final ConstraintLayout layoutDeviceName;
    public final TextView textViewDeviceNameHelpIdu;
    public final TextView textViewDeviceTitleHelp;
    public final TextView textViewHelpSubTitle;
    public final TextView textViewQuickStartGuide;
    public final TextView textViewQuickStartGuideTitle;
    public final TextView textViewSelectedDeviceName;
    public final Button textViewSpecifications;
    public final Button textViewUserManual;
    public final TextView textViewVisit;
    public final TextView textViewVisitTwo;
    public final View view1;
    public final View view2;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected HelpVdGlobalAppBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, Button button, Button button2, TextView textView7, TextView textView8, View view3, View view4) {
        super(obj, view, i);
        this.constarint1 = constraintLayout;
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.guideline295 = guideline3;
        this.guideline296 = guideline4;
        this.guideline297 = guideline5;
        this.guideline298 = guideline6;
        this.guideline299 = guideline7;
        this.guideline305 = guideline8;
        this.imageViewArrowDown = imageView;
        this.imageViewDownloadUserManual = imageView2;
        this.imageViewPdf = imageView3;
        this.layoutDeviceName = constraintLayout2;
        this.textViewDeviceNameHelpIdu = textView;
        this.textViewDeviceTitleHelp = textView2;
        this.textViewHelpSubTitle = textView3;
        this.textViewQuickStartGuide = textView4;
        this.textViewQuickStartGuideTitle = textView5;
        this.textViewSelectedDeviceName = textView6;
        this.textViewSpecifications = button;
        this.textViewUserManual = button2;
        this.textViewVisit = textView7;
        this.textViewVisitTwo = textView8;
        this.view1 = view3;
        this.view2 = view4;
    }

    public static HelpVdGlobalAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpVdGlobalAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HelpVdGlobalAppBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.help_vd_global_app, viewGroup, z, obj);
    }

    public static HelpVdGlobalAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpVdGlobalAppBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HelpVdGlobalAppBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.help_vd_global_app, (ViewGroup) null, false, obj);
    }

    public static HelpVdGlobalAppBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpVdGlobalAppBinding bind(View view, Object obj) {
        return (HelpVdGlobalAppBinding) bind(obj, view, R.layout.help_vd_global_app);
    }
}
