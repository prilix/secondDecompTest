package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class BannerPlannedMaintenanceBinding extends ViewDataBinding {
    public final ImageButton imageButtonClear;
    public final ProgressBar progressBarCloseButton;
    public final TextView textViewServicesNotAvailable;
    public final TextView textViewServicesNotAvailabledesc;
    public final View view53;

    protected BannerPlannedMaintenanceBinding(Object obj, View view, int i, ImageButton imageButton, ProgressBar progressBar, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.imageButtonClear = imageButton;
        this.progressBarCloseButton = progressBar;
        this.textViewServicesNotAvailable = textView;
        this.textViewServicesNotAvailabledesc = textView2;
        this.view53 = view2;
    }

    public static BannerPlannedMaintenanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerPlannedMaintenanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BannerPlannedMaintenanceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_planned_maintenance, viewGroup, z, obj);
    }

    public static BannerPlannedMaintenanceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerPlannedMaintenanceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BannerPlannedMaintenanceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_planned_maintenance, (ViewGroup) null, false, obj);
    }

    public static BannerPlannedMaintenanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerPlannedMaintenanceBinding bind(View view, Object obj) {
        return (BannerPlannedMaintenanceBinding) bind(obj, view, R.layout.banner_planned_maintenance);
    }
}
