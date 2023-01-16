package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentSelectWpsOrApBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline248;
    public final Guideline guideline249;
    public final Guideline guideline250;
    public final Guideline guideline251;
    public final Guideline guideline36;
    public final Guideline guideline46;
    public final ImageView imageViewConnectUsingApMethod;
    public final ImageView imageViewConnectUsingWpsMethod;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout layoutConnectUsingApMethod;
    public final ConstraintLayout layoutConnectUsingWpsMethod;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout rootView;
    public final ScrollView scrollView;
    public final TextView textViewAddDeviceTitle;
    public final TextView textViewConnectUsingApMethod;
    public final TextView textViewConnectUsingWpsMethod;
    public final TextView textViewConnectionMethodSubTitle;
    public final TextView textViewConnectionMethodTitle;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentSelectWpsOrApBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageView imageView, ImageView imageView2, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ScrollView scrollView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline248 = guideline;
        this.guideline249 = guideline2;
        this.guideline250 = guideline3;
        this.guideline251 = guideline4;
        this.guideline36 = guideline5;
        this.guideline46 = guideline6;
        this.imageViewConnectUsingApMethod = imageView;
        this.imageViewConnectUsingWpsMethod = imageView2;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.layoutConnectUsingApMethod = constraintLayout;
        this.layoutConnectUsingWpsMethod = constraintLayout2;
        this.layoutTop = constraintLayout3;
        this.rootView = constraintLayout4;
        this.scrollView = scrollView2;
        this.textViewAddDeviceTitle = textView;
        this.textViewConnectUsingApMethod = textView2;
        this.textViewConnectUsingWpsMethod = textView3;
        this.textViewConnectionMethodSubTitle = textView4;
        this.textViewConnectionMethodTitle = textView5;
    }

    public static FragmentSelectWpsOrApBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectWpsOrApBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSelectWpsOrApBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_select_wps_or_ap, viewGroup, z, obj);
    }

    public static FragmentSelectWpsOrApBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectWpsOrApBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSelectWpsOrApBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_select_wps_or_ap, (ViewGroup) null, false, obj);
    }

    public static FragmentSelectWpsOrApBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectWpsOrApBinding bind(View view, Object obj) {
        return (FragmentSelectWpsOrApBinding) bind(obj, view, R.layout.fragment_select_wps_or_ap);
    }
}
