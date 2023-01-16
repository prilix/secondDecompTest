package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutCleaningAndTrialBinding extends ViewDataBinding {
    public final ImageButton imageButtonClearCleanAndTrial;
    public final ImageView imageViewCleanAndTrial;
    public final TextView textViewCleaningAndTrialDesc;
    public final TextView textViewCleaningAndTrialHeading;

    protected LayoutCleaningAndTrialBinding(Object obj, View view, int i, ImageButton imageButton, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imageButtonClearCleanAndTrial = imageButton;
        this.imageViewCleanAndTrial = imageView;
        this.textViewCleaningAndTrialDesc = textView;
        this.textViewCleaningAndTrialHeading = textView2;
    }

    public static LayoutCleaningAndTrialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCleaningAndTrialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutCleaningAndTrialBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_cleaning_and_trial, viewGroup, z, obj);
    }

    public static LayoutCleaningAndTrialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCleaningAndTrialBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutCleaningAndTrialBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_cleaning_and_trial, (ViewGroup) null, false, obj);
    }

    public static LayoutCleaningAndTrialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCleaningAndTrialBinding bind(View view, Object obj) {
        return (LayoutCleaningAndTrialBinding) bind(obj, view, R.layout.layout_cleaning_and_trial);
    }
}
