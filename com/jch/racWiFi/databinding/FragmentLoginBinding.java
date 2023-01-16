package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentLoginBinding extends ViewDataBinding {
    public final Button buttonCreateAccount;
    public final Button buttonDemoMode;
    public final Button buttonLogin;
    public final ConstraintLayout constarint1;
    public final ConstraintLayout constraintLayout10;
    public final EditText editTextEnterEmail;
    public final EditText editTextEnterPassword;
    public final EditText editTextMobileNumber;
    public final View emailSelectionHighlight;
    public final ConstraintLayout enterEmailBubbleLayout;
    public final ConstraintLayout enterMobileNumberBubbleLayout;
    public final ConstraintLayout enterPasswordBubbleLayout;
    public final FrameLayout fragmentAttachLayout;
    public final Guideline guideline140;
    public final Guideline guideline144;
    public final Guideline guideline152;
    public final Guideline guideline153;
    public final Guideline guideline192;
    public final Guideline guideline194;
    public final Guideline guideline196;
    public final Guideline guideline198;
    public final Guideline guideline42;
    public final Guideline guideline43;
    public final Guideline guideline44;
    public final ImageView imageViewArrowDown;
    public final ImageView imageViewCountryFlagItem;
    public final ImageView imageViewFlagLogin;
    public final ImageView imageViewHitachiLogoWhite;
    public final ConstraintLayout layoutCountryCode;
    public final ConstraintLayout layoutLanguageSelection;
    public final ConstraintLayout layoutNumberOfLoginAttempts;
    public final LinearLayout linearLayout6;
    public final ConstraintLayout mobileNumberLayout;
    public final View mobileNumberSelectionHighlight;
    public final ConstraintLayout parent;
    public final TextInputLayout textInputLayout;
    public final TextView textNumberOfLogInAttempts;
    public final TextView textViewCountryNameLogin;
    public final TextView textViewCountryNumberCodeLogin;
    public final TextView textViewEmailForgotPassword;
    public final TextView textViewForgotPassword;
    public final TextView textViewIncorrectUserNameOrPassword;
    public final TextView textViewLanguageItem;
    public final TextView textViewMobileNumberForgotPassword;
    public final TextView textViewOrLoginWith;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentLoginBinding(Object obj, View view, int i, Button button, Button button2, Button button3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, EditText editText, EditText editText2, EditText editText3, View view2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, FrameLayout frameLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, LinearLayout linearLayout, ConstraintLayout constraintLayout9, View view3, ConstraintLayout constraintLayout11, TextInputLayout textInputLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        super(obj, view, i);
        this.buttonCreateAccount = button;
        this.buttonDemoMode = button2;
        this.buttonLogin = button3;
        this.constarint1 = constraintLayout;
        this.constraintLayout10 = constraintLayout2;
        this.editTextEnterEmail = editText;
        this.editTextEnterPassword = editText2;
        this.editTextMobileNumber = editText3;
        this.emailSelectionHighlight = view2;
        this.enterEmailBubbleLayout = constraintLayout3;
        this.enterMobileNumberBubbleLayout = constraintLayout4;
        this.enterPasswordBubbleLayout = constraintLayout5;
        this.fragmentAttachLayout = frameLayout;
        this.guideline140 = guideline;
        this.guideline144 = guideline2;
        this.guideline152 = guideline3;
        this.guideline153 = guideline4;
        this.guideline192 = guideline5;
        this.guideline194 = guideline6;
        this.guideline196 = guideline7;
        this.guideline198 = guideline8;
        this.guideline42 = guideline9;
        this.guideline43 = guideline10;
        this.guideline44 = guideline11;
        this.imageViewArrowDown = imageView;
        this.imageViewCountryFlagItem = imageView2;
        this.imageViewFlagLogin = imageView3;
        this.imageViewHitachiLogoWhite = imageView4;
        this.layoutCountryCode = constraintLayout6;
        this.layoutLanguageSelection = constraintLayout7;
        this.layoutNumberOfLoginAttempts = constraintLayout8;
        this.linearLayout6 = linearLayout;
        this.mobileNumberLayout = constraintLayout9;
        this.mobileNumberSelectionHighlight = view3;
        this.parent = constraintLayout11;
        this.textInputLayout = textInputLayout2;
        this.textNumberOfLogInAttempts = textView;
        this.textViewCountryNameLogin = textView2;
        this.textViewCountryNumberCodeLogin = textView3;
        this.textViewEmailForgotPassword = textView4;
        this.textViewForgotPassword = textView5;
        this.textViewIncorrectUserNameOrPassword = textView6;
        this.textViewLanguageItem = textView7;
        this.textViewMobileNumberForgotPassword = textView8;
        this.textViewOrLoginWith = textView9;
    }

    public static FragmentLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_login, viewGroup, z, obj);
    }

    public static FragmentLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_login, (ViewGroup) null, false, obj);
    }

    public static FragmentLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding bind(View view, Object obj) {
        return (FragmentLoginBinding) bind(obj, view, R.layout.fragment_login);
    }
}
