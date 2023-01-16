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
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class CustomerCareVdGlobalBinding extends ViewDataBinding {
    public final Button buttonCallCustomerCare;
    public final Button buttonCustomerCarePortal;
    public final Button buttonCustomerCarePortalEurope;
    public final Button buttonEmailCustomerCare;
    public final Button buttonGlobalLink;
    public final ConstraintLayout constraintLayout11;
    public final ConstraintLayout constraintLayout12;
    public final FrameLayout containerLayout;
    public final Guideline guideline139;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline154;
    public final Guideline guideline155;
    public final Guideline guideline156;
    public final Guideline guideline157;
    public final Guideline guideline159;
    public final Guideline guideline232;
    public final Guideline guideline233;
    public final Guideline guideline234;
    public final Guideline guideline235;
    public final Guideline guideline236;
    public final ImageButton imageButtonMenu;
    public final ImageView imageViewArrowDown;
    public final ConstraintLayout layoutBottom;
    public final ConstraintLayout layoutBottomGlobalLink;
    public final ConstraintLayout layoutDeviceName;
    public final ConstraintLayout layoutEuropeRegion;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout parent;
    public final TextView textViewCustomerCareDescEurope;
    public final TextView textViewCustomerCareHelpLine;
    public final TextView textViewCustomerCareSubTitle1;
    public final TextView textViewCustomerCareSubTitle2;
    public final TextView textViewCustomerCareSubTitleThree;
    public final TextView textViewCustomerCareTitle;
    public final TextView textViewOr;
    public final TextView textViewSelectedDeviceName;
    public final TextView textViewSubtitleFour;
    public final TextView textViewVendorThingId;
    public final TextView textViewWorkingHoursTitle;
    public final TextView textViewWorkingHoursValue;
    public final TextView tvCustomerCareSubTitle2;
    public final TextView tvCustomerCareSubTitle3;
    public final TextView tvOr;
    public final View view;
    public final View view11;
    public final View view12;
    public final View view8;
    public final View view9;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected CustomerCareVdGlobalBinding(Object obj, View view2, int i, Button button, Button button2, Button button3, Button button4, Button button5, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FrameLayout frameLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, Guideline guideline12, Guideline guideline13, ImageButton imageButton, ImageView imageView, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, View view3, View view4, View view5, View view6, View view7) {
        super(obj, view2, i);
        this.buttonCallCustomerCare = button;
        this.buttonCustomerCarePortal = button2;
        this.buttonCustomerCarePortalEurope = button3;
        this.buttonEmailCustomerCare = button4;
        this.buttonGlobalLink = button5;
        this.constraintLayout11 = constraintLayout;
        this.constraintLayout12 = constraintLayout2;
        this.containerLayout = frameLayout;
        this.guideline139 = guideline;
        this.guideline150 = guideline2;
        this.guideline151 = guideline3;
        this.guideline154 = guideline4;
        this.guideline155 = guideline5;
        this.guideline156 = guideline6;
        this.guideline157 = guideline7;
        this.guideline159 = guideline8;
        this.guideline232 = guideline9;
        this.guideline233 = guideline10;
        this.guideline234 = guideline11;
        this.guideline235 = guideline12;
        this.guideline236 = guideline13;
        this.imageButtonMenu = imageButton;
        this.imageViewArrowDown = imageView;
        this.layoutBottom = constraintLayout3;
        this.layoutBottomGlobalLink = constraintLayout4;
        this.layoutDeviceName = constraintLayout5;
        this.layoutEuropeRegion = constraintLayout6;
        this.layoutTop = constraintLayout7;
        this.parent = constraintLayout8;
        this.textViewCustomerCareDescEurope = textView;
        this.textViewCustomerCareHelpLine = textView2;
        this.textViewCustomerCareSubTitle1 = textView3;
        this.textViewCustomerCareSubTitle2 = textView4;
        this.textViewCustomerCareSubTitleThree = textView5;
        this.textViewCustomerCareTitle = textView6;
        this.textViewOr = textView7;
        this.textViewSelectedDeviceName = textView8;
        this.textViewSubtitleFour = textView9;
        this.textViewVendorThingId = textView10;
        this.textViewWorkingHoursTitle = textView11;
        this.textViewWorkingHoursValue = textView12;
        this.tvCustomerCareSubTitle2 = textView13;
        this.tvCustomerCareSubTitle3 = textView14;
        this.tvOr = textView15;
        this.view = view3;
        this.view11 = view4;
        this.view12 = view5;
        this.view8 = view6;
        this.view9 = view7;
    }

    public static CustomerCareVdGlobalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareVdGlobalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (CustomerCareVdGlobalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.customer_care_vd_global, viewGroup, z, obj);
    }

    public static CustomerCareVdGlobalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareVdGlobalBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (CustomerCareVdGlobalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.customer_care_vd_global, (ViewGroup) null, false, obj);
    }

    public static CustomerCareVdGlobalBinding bind(View view2) {
        return bind(view2, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomerCareVdGlobalBinding bind(View view2, Object obj) {
        return (CustomerCareVdGlobalBinding) bind(obj, view2, R.layout.customer_care_vd_global);
    }
}
