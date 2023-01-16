package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class EnableWpsBuiltInStep4Of4FrameBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final ConstraintLayout constraintToolbar;
    public final ImageButton forwardButton;
    public final EnableWpsBuiltInStep4Of4VdBinding include;
    public final ConstraintLayout includedLayout;
    public final TextView textViewForgotPasswordTitle;

    protected EnableWpsBuiltInStep4Of4FrameBinding(Object obj, View view, int i, ImageButton imageButton, ConstraintLayout constraintLayout, ImageButton imageButton2, EnableWpsBuiltInStep4Of4VdBinding enableWpsBuiltInStep4Of4VdBinding, ConstraintLayout constraintLayout2, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.constraintToolbar = constraintLayout;
        this.forwardButton = imageButton2;
        this.include = enableWpsBuiltInStep4Of4VdBinding;
        this.includedLayout = constraintLayout2;
        this.textViewForgotPasswordTitle = textView;
    }

    public static EnableWpsBuiltInStep4Of4FrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4FrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EnableWpsBuiltInStep4Of4FrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enable_wps_built_in_step_4_of_4_frame, viewGroup, z, obj);
    }

    public static EnableWpsBuiltInStep4Of4FrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4FrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EnableWpsBuiltInStep4Of4FrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enable_wps_built_in_step_4_of_4_frame, (ViewGroup) null, false, obj);
    }

    public static EnableWpsBuiltInStep4Of4FrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnableWpsBuiltInStep4Of4FrameBinding bind(View view, Object obj) {
        return (EnableWpsBuiltInStep4Of4FrameBinding) bind(obj, view, R.layout.enable_wps_built_in_step_4_of_4_frame);
    }
}
