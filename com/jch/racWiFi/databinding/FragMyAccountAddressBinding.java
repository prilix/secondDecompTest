package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragMyAccountAddressBinding extends ViewDataBinding {
    public final LayoutCreateAccountStep4BodyBinding includeBody;
    public final ToolbarCreateAccountStep4Binding includeToolbar;

    protected FragMyAccountAddressBinding(Object obj, View view, int i, LayoutCreateAccountStep4BodyBinding layoutCreateAccountStep4BodyBinding, ToolbarCreateAccountStep4Binding toolbarCreateAccountStep4Binding) {
        super(obj, view, i);
        this.includeBody = layoutCreateAccountStep4BodyBinding;
        this.includeToolbar = toolbarCreateAccountStep4Binding;
    }

    public static FragMyAccountAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragMyAccountAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragMyAccountAddressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.frag_my_account_address, viewGroup, z, obj);
    }

    public static FragMyAccountAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragMyAccountAddressBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragMyAccountAddressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.frag_my_account_address, (ViewGroup) null, false, obj);
    }

    public static FragMyAccountAddressBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragMyAccountAddressBinding bind(View view, Object obj) {
        return (FragMyAccountAddressBinding) bind(obj, view, R.layout.frag_my_account_address);
    }
}
