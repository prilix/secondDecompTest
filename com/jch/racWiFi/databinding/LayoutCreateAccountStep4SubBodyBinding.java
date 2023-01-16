package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutCreateAccountStep4SubBodyBinding extends ViewDataBinding {
    public final ConstraintLayout addressLineBubbleLayout;
    public final Button buttonContinue;
    public final EditText editTextAddressLine1;
    public final EditText editTextCity;
    public final EditText editTextState;
    public final EditText editTextStreetArea;
    public final EditText editTextZipCode;
    public final ConstraintLayout enterCityBubbleLayout;
    public final ConstraintLayout enterStateBubbleLayout;
    public final ConstraintLayout enterStreetAreaBubbleLayout;
    public final ConstraintLayout enterZipCodeBubbleLayout;
    public final Guideline guideline5;
    public final Guideline guideline6;
    public final Guideline guideline7;
    public final Guideline guideline8;
    public final ImageView imageView3;
    public final ConstraintLayout layoutAutoDetectMyLocation;
    public final TextView textViewAddressLineOneError;
    public final TextView textViewCityError;
    public final TextView textViewOr;
    public final TextView textViewStateError;
    public final TextView textViewStreetAreaError;
    public final TextView textViewZipCodeError;
    public final View view5;
    public final View view7;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutCreateAccountStep4SubBodyBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageView imageView, ConstraintLayout constraintLayout6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view2, View view3) {
        super(obj, view, i);
        this.addressLineBubbleLayout = constraintLayout;
        this.buttonContinue = button;
        this.editTextAddressLine1 = editText;
        this.editTextCity = editText2;
        this.editTextState = editText3;
        this.editTextStreetArea = editText4;
        this.editTextZipCode = editText5;
        this.enterCityBubbleLayout = constraintLayout2;
        this.enterStateBubbleLayout = constraintLayout3;
        this.enterStreetAreaBubbleLayout = constraintLayout4;
        this.enterZipCodeBubbleLayout = constraintLayout5;
        this.guideline5 = guideline;
        this.guideline6 = guideline2;
        this.guideline7 = guideline3;
        this.guideline8 = guideline4;
        this.imageView3 = imageView;
        this.layoutAutoDetectMyLocation = constraintLayout6;
        this.textViewAddressLineOneError = textView;
        this.textViewCityError = textView2;
        this.textViewOr = textView3;
        this.textViewStateError = textView4;
        this.textViewStreetAreaError = textView5;
        this.textViewZipCodeError = textView6;
        this.view5 = view2;
        this.view7 = view3;
    }

    public static LayoutCreateAccountStep4SubBodyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4SubBodyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutCreateAccountStep4SubBodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_sub_body, viewGroup, z, obj);
    }

    public static LayoutCreateAccountStep4SubBodyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4SubBodyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutCreateAccountStep4SubBodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_create_account_step_4_sub_body, (ViewGroup) null, false, obj);
    }

    public static LayoutCreateAccountStep4SubBodyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutCreateAccountStep4SubBodyBinding bind(View view, Object obj) {
        return (LayoutCreateAccountStep4SubBodyBinding) bind(obj, view, R.layout.layout_create_account_step_4_sub_body);
    }
}
