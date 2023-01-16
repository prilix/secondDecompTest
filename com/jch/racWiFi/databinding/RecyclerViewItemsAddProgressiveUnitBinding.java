package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewItemsAddProgressiveUnitBinding extends ViewDataBinding {
    public final EditText editTextToUnit;
    public final EditText editTextUnitPrice;
    public final Guideline guideline52;
    public final Guideline guideline55;
    public final Guideline guideline65;
    public final TextView textViewCurrencyCode;
    public final TextView textViewFromUnitPrice;

    protected RecyclerViewItemsAddProgressiveUnitBinding(Object obj, View view, int i, EditText editText, EditText editText2, Guideline guideline, Guideline guideline2, Guideline guideline3, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.editTextToUnit = editText;
        this.editTextUnitPrice = editText2;
        this.guideline52 = guideline;
        this.guideline55 = guideline2;
        this.guideline65 = guideline3;
        this.textViewCurrencyCode = textView;
        this.textViewFromUnitPrice = textView2;
    }

    public static RecyclerViewItemsAddProgressiveUnitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsAddProgressiveUnitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemsAddProgressiveUnitBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_add_progressive_unit, viewGroup, z, obj);
    }

    public static RecyclerViewItemsAddProgressiveUnitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsAddProgressiveUnitBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemsAddProgressiveUnitBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_add_progressive_unit, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemsAddProgressiveUnitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsAddProgressiveUnitBinding bind(View view, Object obj) {
        return (RecyclerViewItemsAddProgressiveUnitBinding) bind(obj, view, R.layout.recycler_view_items_add_progressive_unit);
    }
}
