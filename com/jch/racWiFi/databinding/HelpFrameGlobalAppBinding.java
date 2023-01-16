package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class HelpFrameGlobalAppBinding extends ViewDataBinding {
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final ImageButton imageButtonMenu;
    public final HelpVdGlobalAppBinding include;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewLanguageTitle;

    protected HelpFrameGlobalAppBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, HelpVdGlobalAppBinding helpVdGlobalAppBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.imageButtonMenu = imageButton;
        this.include = helpVdGlobalAppBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewLanguageTitle = textView;
    }

    public static HelpFrameGlobalAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpFrameGlobalAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HelpFrameGlobalAppBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.help_frame_global_app, viewGroup, z, obj);
    }

    public static HelpFrameGlobalAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpFrameGlobalAppBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HelpFrameGlobalAppBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.help_frame_global_app, (ViewGroup) null, false, obj);
    }

    public static HelpFrameGlobalAppBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HelpFrameGlobalAppBinding bind(View view, Object obj) {
        return (HelpFrameGlobalAppBinding) bind(obj, view, R.layout.help_frame_global_app);
    }
}
