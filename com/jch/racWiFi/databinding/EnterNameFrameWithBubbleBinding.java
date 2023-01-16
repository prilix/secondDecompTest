package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class EnterNameFrameWithBubbleBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final EnterNameVdWithBubbleBinding includedLayout;
    public final TextView textViewCreateAccount;

    protected EnterNameFrameWithBubbleBinding(Object obj, View view, int i, ImageButton imageButton, Guideline guideline, Guideline guideline2, EnterNameVdWithBubbleBinding enterNameVdWithBubbleBinding, TextView textView) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.includedLayout = enterNameVdWithBubbleBinding;
        this.textViewCreateAccount = textView;
    }

    public static EnterNameFrameWithBubbleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameFrameWithBubbleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EnterNameFrameWithBubbleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enter_name_frame_with_bubble, viewGroup, z, obj);
    }

    public static EnterNameFrameWithBubbleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameFrameWithBubbleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EnterNameFrameWithBubbleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enter_name_frame_with_bubble, (ViewGroup) null, false, obj);
    }

    public static EnterNameFrameWithBubbleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameFrameWithBubbleBinding bind(View view, Object obj) {
        return (EnterNameFrameWithBubbleBinding) bind(obj, view, R.layout.enter_name_frame_with_bubble);
    }
}
