package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutGracTooltipBinding extends ViewDataBinding {
    public final ConstraintLayout constraintLayout;
    public final ConstraintLayout constraintLayout1;
    public final ConstraintLayout constraintLayout2;
    public final ConstraintLayout constraintLayout3;
    public final ConstraintLayout constraintLayout4;
    public final ConstraintLayout constraintLayout5;
    public final ImageView imageView1;
    public final ImageView imageView2;
    public final TextView textView1;
    public final TextView textView11;
    public final TextView textView2;
    public final TextView textView21;
    public final TextView textView3;
    public final TextView textView31;
    public final TextView textView4;
    public final TextView textView5;
    public final TextView textView55;
    public final TextView textView6;
    public final TextView textView61;
    public final TextView textView7;
    public final TextView textView71;
    public final TextView textView8;
    public final TextView textView9;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutGracTooltipBinding(Object obj, View view, int i, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ImageView imageView, ImageView imageView3, TextView textView, TextView textView10, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView22, TextView textView23, TextView textView24, TextView textView25) {
        super(obj, view, i);
        this.constraintLayout = constraintLayout6;
        this.constraintLayout1 = constraintLayout7;
        this.constraintLayout2 = constraintLayout8;
        this.constraintLayout3 = constraintLayout9;
        this.constraintLayout4 = constraintLayout10;
        this.constraintLayout5 = constraintLayout11;
        this.imageView1 = imageView;
        this.imageView2 = imageView3;
        this.textView1 = textView;
        this.textView11 = textView10;
        this.textView2 = textView12;
        this.textView21 = textView13;
        this.textView3 = textView14;
        this.textView31 = textView15;
        this.textView4 = textView16;
        this.textView5 = textView17;
        this.textView55 = textView18;
        this.textView6 = textView19;
        this.textView61 = textView20;
        this.textView7 = textView22;
        this.textView71 = textView23;
        this.textView8 = textView24;
        this.textView9 = textView25;
    }

    public static LayoutGracTooltipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGracTooltipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutGracTooltipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_grac_tooltip, viewGroup, z, obj);
    }

    public static LayoutGracTooltipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGracTooltipBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutGracTooltipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_grac_tooltip, (ViewGroup) null, false, obj);
    }

    public static LayoutGracTooltipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGracTooltipBinding bind(View view, Object obj) {
        return (LayoutGracTooltipBinding) bind(obj, view, R.layout.layout_grac_tooltip);
    }
}
