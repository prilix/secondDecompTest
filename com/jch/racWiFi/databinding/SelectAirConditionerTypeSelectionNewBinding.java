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

public abstract class SelectAirConditionerTypeSelectionNewBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline248;
    public final Guideline guideline249;
    public final Guideline guideline250;
    public final Guideline guideline251;
    public final Guideline guideline34;
    public final Guideline guideline35;
    public final ImageView imageViewWithBuiltInWirelessUnit;
    public final ImageView imageViewWithExternalWirelessAdapter;
    public final LayoutStepProgressBarBinding includeLinearProgressIndicator;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout layoutWithBuiltInWirelessUnit;
    public final ConstraintLayout layoutWithWithExternalWirelessAdapter;
    public final ScrollView scrollView;
    public final TextView textViewAddDeviceTitle;
    public final TextView textViewSelectAirConditionerSubTitle;
    public final TextView textViewSelectAirConditionerTitle;
    public final TextView textViewWithBuiltInWirelessUnit;
    public final TextView textViewWithExternalWirelessAdapter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SelectAirConditionerTypeSelectionNewBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageView imageView, ImageView imageView2, LayoutStepProgressBarBinding layoutStepProgressBarBinding, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ScrollView scrollView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline248 = guideline;
        this.guideline249 = guideline2;
        this.guideline250 = guideline3;
        this.guideline251 = guideline4;
        this.guideline34 = guideline5;
        this.guideline35 = guideline6;
        this.imageViewWithBuiltInWirelessUnit = imageView;
        this.imageViewWithExternalWirelessAdapter = imageView2;
        this.includeLinearProgressIndicator = layoutStepProgressBarBinding;
        this.layoutTop = constraintLayout;
        this.layoutWithBuiltInWirelessUnit = constraintLayout2;
        this.layoutWithWithExternalWirelessAdapter = constraintLayout3;
        this.scrollView = scrollView2;
        this.textViewAddDeviceTitle = textView;
        this.textViewSelectAirConditionerSubTitle = textView2;
        this.textViewSelectAirConditionerTitle = textView3;
        this.textViewWithBuiltInWirelessUnit = textView4;
        this.textViewWithExternalWirelessAdapter = textView5;
    }

    public static SelectAirConditionerTypeSelectionNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SelectAirConditionerTypeSelectionNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SelectAirConditionerTypeSelectionNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.select_air_conditioner_type_selection_new, viewGroup, z, obj);
    }

    public static SelectAirConditionerTypeSelectionNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SelectAirConditionerTypeSelectionNewBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SelectAirConditionerTypeSelectionNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.select_air_conditioner_type_selection_new, (ViewGroup) null, false, obj);
    }

    public static SelectAirConditionerTypeSelectionNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SelectAirConditionerTypeSelectionNewBinding bind(View view, Object obj) {
        return (SelectAirConditionerTypeSelectionNewBinding) bind(obj, view, R.layout.select_air_conditioner_type_selection_new);
    }
}
