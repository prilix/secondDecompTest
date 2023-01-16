package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutPeakHoursAndMonthBinding extends ViewDataBinding {
    public final EditText editTextOffPeakHrUnitPrice;
    public final EditText editTextPeakHrUnitPrice;
    public final Guideline guideline47;
    public final ConstraintLayout layoutViewUnitPrizeInrKwh;
    public final RecyclerView recylerViewPeakHoursAndMonth;
    public final TextView textViewOffPeakHours;
    public final TextView textViewPeakHours;
    public final TextView textViewTapToSelection;
    public final TextView textViewUnitPriceCurrencyKwh;

    protected LayoutPeakHoursAndMonthBinding(Object obj, View view, int i, EditText editText, EditText editText2, Guideline guideline, ConstraintLayout constraintLayout, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.editTextOffPeakHrUnitPrice = editText;
        this.editTextPeakHrUnitPrice = editText2;
        this.guideline47 = guideline;
        this.layoutViewUnitPrizeInrKwh = constraintLayout;
        this.recylerViewPeakHoursAndMonth = recyclerView;
        this.textViewOffPeakHours = textView;
        this.textViewPeakHours = textView2;
        this.textViewTapToSelection = textView3;
        this.textViewUnitPriceCurrencyKwh = textView4;
    }

    public static LayoutPeakHoursAndMonthBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPeakHoursAndMonthBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutPeakHoursAndMonthBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_peak_hours_and_month, viewGroup, z, obj);
    }

    public static LayoutPeakHoursAndMonthBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPeakHoursAndMonthBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutPeakHoursAndMonthBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_peak_hours_and_month, (ViewGroup) null, false, obj);
    }

    public static LayoutPeakHoursAndMonthBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPeakHoursAndMonthBinding bind(View view, Object obj) {
        return (LayoutPeakHoursAndMonthBinding) bind(obj, view, R.layout.layout_peak_hours_and_month);
    }
}
