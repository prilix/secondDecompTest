package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragCreateAccountStep4Binding extends ViewDataBinding {
    public final LayoutCreateAccountStep4BodyBinding includeBody;
    public final ToolbarCreateAccountStep4Binding includeToolbar;

    protected FragCreateAccountStep4Binding(Object obj, View view, int i, LayoutCreateAccountStep4BodyBinding layoutCreateAccountStep4BodyBinding, ToolbarCreateAccountStep4Binding toolbarCreateAccountStep4Binding) {
        super(obj, view, i);
        this.includeBody = layoutCreateAccountStep4BodyBinding;
        this.includeToolbar = toolbarCreateAccountStep4Binding;
    }

    public static FragCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragCreateAccountStep4Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.frag_create_account_step_4, viewGroup, z, obj);
    }

    public static FragCreateAccountStep4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragCreateAccountStep4Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.frag_create_account_step_4, (ViewGroup) null, false, obj);
    }

    public static FragCreateAccountStep4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragCreateAccountStep4Binding bind(View view, Object obj) {
        return (FragCreateAccountStep4Binding) bind(obj, view, R.layout.frag_create_account_step_4);
    }
}
