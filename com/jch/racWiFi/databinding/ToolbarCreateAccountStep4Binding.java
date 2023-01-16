package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ToolbarCreateAccountStep4Binding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ImageButton textViewSave;
    public final TextView toolbarTitle;

    protected ToolbarCreateAccountStep4Binding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.textViewSave = imageButton2;
        this.toolbarTitle = textView;
    }

    public static ToolbarCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ToolbarCreateAccountStep4Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_create_account_step_4, viewGroup, z, obj);
    }

    public static ToolbarCreateAccountStep4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarCreateAccountStep4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ToolbarCreateAccountStep4Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_create_account_step_4, (ViewGroup) null, false, obj);
    }

    public static ToolbarCreateAccountStep4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarCreateAccountStep4Binding bind(View view, Object obj) {
        return (ToolbarCreateAccountStep4Binding) bind(obj, view, R.layout.toolbar_create_account_step_4);
    }
}
