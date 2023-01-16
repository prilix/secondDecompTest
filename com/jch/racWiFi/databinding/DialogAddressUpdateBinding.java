package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DialogAddressUpdateBinding extends ViewDataBinding {
    public final ConstraintLayout addressLineBubbleLayout;
    public final Button buttonCancel;
    public final Button buttonPositive;
    public final EditText editTextAddressLine1;
    public final EditText editTextCity;
    public final EditText editTextState;
    public final EditText editTextStreetArea;
    public final EditText editTextZipCode;
    public final ConstraintLayout enterCityBubbleLayout;
    public final ConstraintLayout enterStateBubbleLayout;
    public final ConstraintLayout enterStreetAreaBubbleLayout;
    public final ConstraintLayout enterZipCodeBubbleLayout;
    public final Guideline guideline113;
    public final Guideline guideline114;
    public final Guideline guideline115;
    public final Guideline guideline116;
    public final Guideline guideline117;
    public final Guideline guideline118;
    public final Guideline guideline192;
    public final Guideline guideline194;
    public final ScrollView scrollView3;
    public final TextView textViewAddressLineOneError;
    public final TextView textViewCityError;
    public final TextView textViewEnterAddressDetails;
    public final TextView textViewIndicatesMandatoryField;
    public final TextView textViewStateError;
    public final TextView textViewStreetAreaError;
    public final TextView textViewZipCodeError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogAddressUpdateBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, Button button2, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, ScrollView scrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.addressLineBubbleLayout = constraintLayout;
        this.buttonCancel = button;
        this.buttonPositive = button2;
        this.editTextAddressLine1 = editText;
        this.editTextCity = editText2;
        this.editTextState = editText3;
        this.editTextStreetArea = editText4;
        this.editTextZipCode = editText5;
        this.enterCityBubbleLayout = constraintLayout2;
        this.enterStateBubbleLayout = constraintLayout3;
        this.enterStreetAreaBubbleLayout = constraintLayout4;
        this.enterZipCodeBubbleLayout = constraintLayout5;
        this.guideline113 = guideline;
        this.guideline114 = guideline2;
        this.guideline115 = guideline3;
        this.guideline116 = guideline4;
        this.guideline117 = guideline5;
        this.guideline118 = guideline6;
        this.guideline192 = guideline7;
        this.guideline194 = guideline8;
        this.scrollView3 = scrollView;
        this.textViewAddressLineOneError = textView;
        this.textViewCityError = textView2;
        this.textViewEnterAddressDetails = textView3;
        this.textViewIndicatesMandatoryField = textView4;
        this.textViewStateError = textView5;
        this.textViewStreetAreaError = textView6;
        this.textViewZipCodeError = textView7;
    }

    public static DialogAddressUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddressUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAddressUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_address_update, viewGroup, z, obj);
    }

    public static DialogAddressUpdateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddressUpdateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAddressUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_address_update, (ViewGroup) null, false, obj);
    }

    public static DialogAddressUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddressUpdateBinding bind(View view, Object obj) {
        return (DialogAddressUpdateBinding) bind(obj, view, R.layout.dialog_address_update);
    }
}
