package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DialogAcAlreadyAddedSameHomeBinding extends ViewDataBinding {
    public final Button buttonPairWithAnotherWirelessNw;
    public final Button buttonQuitParingProcess;
    public final Guideline guideline241;
    public final Guideline guideline242;
    public final ImageView imageViewUserProfileManageUser;
    public final ConstraintLayout layoutHomeOnbaord;
    public final TextView textViewAcAlreadyAddedSubDesc;
    public final TextView textViewAcAlreadyAddedSubTitle;
    public final TextView textViewAcAlreadyAddedTitle;
    public final TextView textViewUserNameManageUser;
    public final TextView textViewUserTypeManageUser;

    protected DialogAcAlreadyAddedSameHomeBinding(Object obj, View view, int i, Button button, Button button2, Guideline guideline, Guideline guideline2, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.buttonPairWithAnotherWirelessNw = button;
        this.buttonQuitParingProcess = button2;
        this.guideline241 = guideline;
        this.guideline242 = guideline2;
        this.imageViewUserProfileManageUser = imageView;
        this.layoutHomeOnbaord = constraintLayout;
        this.textViewAcAlreadyAddedSubDesc = textView;
        this.textViewAcAlreadyAddedSubTitle = textView2;
        this.textViewAcAlreadyAddedTitle = textView3;
        this.textViewUserNameManageUser = textView4;
        this.textViewUserTypeManageUser = textView5;
    }

    public static DialogAcAlreadyAddedSameHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAcAlreadyAddedSameHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAcAlreadyAddedSameHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_ac_already_added_same_home, viewGroup, z, obj);
    }

    public static DialogAcAlreadyAddedSameHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAcAlreadyAddedSameHomeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAcAlreadyAddedSameHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_ac_already_added_same_home, (ViewGroup) null, false, obj);
    }

    public static DialogAcAlreadyAddedSameHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAcAlreadyAddedSameHomeBinding bind(View view, Object obj) {
        return (DialogAcAlreadyAddedSameHomeBinding) bind(obj, view, R.layout.dialog_ac_already_added_same_home);
    }
}
