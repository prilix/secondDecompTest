package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartFenceSelectAcsBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final TriStateCheckbox cbAllDevices;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline31;
    public final Guideline guideline32;
    public final ImageButton imageButtonHelp;
    public final ImageView imageViewAllAcs;
    public final LinearLayout layoutAddAc;
    public final ConstraintLayout layoutAllAcs;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout listConstraintLayout;
    public final RecyclerView recyclerViewDevices;
    public final TextView textViewAcsTitle;
    public final TextView textViewNoRacFound;
    public final ImageButton textViewSave;
    public final TextView textViewSelectAcsSubtitle;
    public final View view3;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SmartFenceSelectAcsBinding(Object obj, View view, int i, ImageButton imageButton, TriStateCheckbox triStateCheckbox, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageButton imageButton2, ImageView imageView, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, TextView textView, TextView textView2, ImageButton imageButton3, TextView textView3, View view2) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.cbAllDevices = triStateCheckbox;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.guideline31 = guideline3;
        this.guideline32 = guideline4;
        this.imageButtonHelp = imageButton2;
        this.imageViewAllAcs = imageView;
        this.layoutAddAc = linearLayout;
        this.layoutAllAcs = constraintLayout;
        this.layoutTop = constraintLayout2;
        this.listConstraintLayout = constraintLayout3;
        this.recyclerViewDevices = recyclerView;
        this.textViewAcsTitle = textView;
        this.textViewNoRacFound = textView2;
        this.textViewSave = imageButton3;
        this.textViewSelectAcsSubtitle = textView3;
        this.view3 = view2;
    }

    public static SmartFenceSelectAcsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectAcsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceSelectAcsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_select_acs, viewGroup, z, obj);
    }

    public static SmartFenceSelectAcsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectAcsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceSelectAcsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_select_acs, (ViewGroup) null, false, obj);
    }

    public static SmartFenceSelectAcsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectAcsBinding bind(View view, Object obj) {
        return (SmartFenceSelectAcsBinding) bind(obj, view, R.layout.smart_fence_select_acs);
    }
}
