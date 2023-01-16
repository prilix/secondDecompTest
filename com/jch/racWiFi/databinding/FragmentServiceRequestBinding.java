package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentServiceRequestBinding extends ViewDataBinding {
    public final Button buttonCallServiceDesk;
    public final Button buttonServiceRequestPortal;
    public final ConstraintLayout constraintLayout11;
    public final Guideline guideline150;
    public final Guideline guideline154;
    public final Guideline guideline155;
    public final Guideline guideline156;
    public final Guideline guideline157;
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final Guideline guideline232;
    public final Guideline guideline233;
    public final Guideline guideline234;
    public final Guideline guideline235;
    public final Guideline guideline236;
    public final ImageButton imageButtonMenu;
    public final FrameLayout layoutRacError;
    public final ConstraintLayout layoutTop;
    public final TextView textViewCustomerCareHelpLine;
    public final TextView textViewCustomerCareSubTitle2;
    public final TextView textViewMondayToSaturday;
    public final TextView textViewOr;
    public final TextView textViewServiceRequestSubTitle;
    public final TextView textViewServiceRequestTitle;
    public final TextView textViewSundayHolidays;
    public final TextView textViewWorkingHoursTitle;
    public final View view8;
    public final View view9;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentServiceRequestBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, Guideline guideline12, ImageButton imageButton, FrameLayout frameLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2, View view3) {
        super(obj, view, i);
        this.buttonCallServiceDesk = button;
        this.buttonServiceRequestPortal = button2;
        this.constraintLayout11 = constraintLayout;
        this.guideline150 = guideline;
        this.guideline154 = guideline2;
        this.guideline155 = guideline3;
        this.guideline156 = guideline4;
        this.guideline157 = guideline5;
        this.guideline158 = guideline6;
        this.guideline159 = guideline7;
        this.guideline232 = guideline8;
        this.guideline233 = guideline9;
        this.guideline234 = guideline10;
        this.guideline235 = guideline11;
        this.guideline236 = guideline12;
        this.imageButtonMenu = imageButton;
        this.layoutRacError = frameLayout;
        this.layoutTop = constraintLayout2;
        this.textViewCustomerCareHelpLine = textView;
        this.textViewCustomerCareSubTitle2 = textView2;
        this.textViewMondayToSaturday = textView3;
        this.textViewOr = textView4;
        this.textViewServiceRequestSubTitle = textView5;
        this.textViewServiceRequestTitle = textView6;
        this.textViewSundayHolidays = textView7;
        this.textViewWorkingHoursTitle = textView8;
        this.view8 = view2;
        this.view9 = view3;
    }

    public static FragmentServiceRequestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentServiceRequestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentServiceRequestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_service_request, viewGroup, z, obj);
    }

    public static FragmentServiceRequestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentServiceRequestBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentServiceRequestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_service_request, (ViewGroup) null, false, obj);
    }

    public static FragmentServiceRequestBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentServiceRequestBinding bind(View view, Object obj) {
        return (FragmentServiceRequestBinding) bind(obj, view, R.layout.fragment_service_request);
    }
}
