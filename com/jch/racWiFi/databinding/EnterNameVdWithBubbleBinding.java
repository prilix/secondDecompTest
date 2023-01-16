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
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class EnterNameVdWithBubbleBinding extends ViewDataBinding {
    public final Button buttonContinue;
    public final EditText editTextEnterFirstName;
    public final EditText editTextEnterLastName;
    public final EditText editTextEnterMiddleName;
    public final ConstraintLayout enterFirstNameBubbleLayout;
    public final ConstraintLayout enterLastNameBubbleLayout;
    public final Guideline guideline113;
    public final Guideline guideline114;
    public final Guideline guideline115;
    public final Guideline guideline116;
    public final Guideline guideline121;
    public final Guideline guideline192;
    public final Guideline guideline194;
    public final Guideline guideline226;
    public final TextView textViewEnterYourName;
    public final TextView textViewFirstNameError;
    public final TextView textViewIndicatesMandatoryField;
    public final TextView textViewLastNameError;
    public final TextView textViewStep1Of4;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EnterNameVdWithBubbleBinding(Object obj, View view, int i, Button button, EditText editText, EditText editText2, EditText editText3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.buttonContinue = button;
        this.editTextEnterFirstName = editText;
        this.editTextEnterLastName = editText2;
        this.editTextEnterMiddleName = editText3;
        this.enterFirstNameBubbleLayout = constraintLayout;
        this.enterLastNameBubbleLayout = constraintLayout2;
        this.guideline113 = guideline;
        this.guideline114 = guideline2;
        this.guideline115 = guideline3;
        this.guideline116 = guideline4;
        this.guideline121 = guideline5;
        this.guideline192 = guideline6;
        this.guideline194 = guideline7;
        this.guideline226 = guideline8;
        this.textViewEnterYourName = textView;
        this.textViewFirstNameError = textView2;
        this.textViewIndicatesMandatoryField = textView3;
        this.textViewLastNameError = textView4;
        this.textViewStep1Of4 = textView5;
    }

    public static EnterNameVdWithBubbleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameVdWithBubbleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EnterNameVdWithBubbleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enter_name_vd_with_bubble, viewGroup, z, obj);
    }

    public static EnterNameVdWithBubbleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameVdWithBubbleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EnterNameVdWithBubbleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enter_name_vd_with_bubble, (ViewGroup) null, false, obj);
    }

    public static EnterNameVdWithBubbleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterNameVdWithBubbleBinding bind(View view, Object obj) {
        return (EnterNameVdWithBubbleBinding) bind(obj, view, R.layout.enter_name_vd_with_bubble);
    }
}
