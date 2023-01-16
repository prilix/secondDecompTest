package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class GridViewItemPeakHoursMonthsBinding extends ViewDataBinding {
    public final ConstraintLayout layoutPeakHoursMonth;
    public final ConstraintLayout layoutPeakHoursMonthOuter;
    public final TextView textViewHoursMonth;

    protected GridViewItemPeakHoursMonthsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView) {
        super(obj, view, i);
        this.layoutPeakHoursMonth = constraintLayout;
        this.layoutPeakHoursMonthOuter = constraintLayout2;
        this.textViewHoursMonth = textView;
    }

    public static GridViewItemPeakHoursMonthsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridViewItemPeakHoursMonthsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (GridViewItemPeakHoursMonthsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.grid_view_item_peak_hours_months, viewGroup, z, obj);
    }

    public static GridViewItemPeakHoursMonthsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridViewItemPeakHoursMonthsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (GridViewItemPeakHoursMonthsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.grid_view_item_peak_hours_months, (ViewGroup) null, false, obj);
    }

    public static GridViewItemPeakHoursMonthsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridViewItemPeakHoursMonthsBinding bind(View view, Object obj) {
        return (GridViewItemPeakHoursMonthsBinding) bind(obj, view, R.layout.grid_view_item_peak_hours_months);
    }
}
