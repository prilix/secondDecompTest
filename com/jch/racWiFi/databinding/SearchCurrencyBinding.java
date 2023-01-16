package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SearchCurrencyBinding extends ViewDataBinding {
    public final ImageButton clear;
    public final EditText editTextSearchCurrency;

    protected SearchCurrencyBinding(Object obj, View view, int i, ImageButton imageButton, EditText editText) {
        super(obj, view, i);
        this.clear = imageButton;
        this.editTextSearchCurrency = editText;
    }

    public static SearchCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchCurrencyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SearchCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_currency, viewGroup, z, obj);
    }

    public static SearchCurrencyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchCurrencyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SearchCurrencyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_currency, (ViewGroup) null, false, obj);
    }

    public static SearchCurrencyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchCurrencyBinding bind(View view, Object obj) {
        return (SearchCurrencyBinding) bind(obj, view, R.layout.search_currency);
    }
}
