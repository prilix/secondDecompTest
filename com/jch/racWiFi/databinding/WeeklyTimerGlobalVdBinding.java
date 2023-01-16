package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class WeeklyTimerGlobalVdBinding extends ViewDataBinding {
    public final TriStateCheckbox checkBoxFri;
    public final TriStateCheckbox checkBoxMon;
    public final TriStateCheckbox checkBoxSat;
    public final TriStateCheckbox checkBoxSun;
    public final TriStateCheckbox checkBoxThu;
    public final TriStateCheckbox checkBoxTue;
    public final TriStateCheckbox checkBoxWed;
    public final RadioGroup dailyWeeklyButtonView;
    public final Guideline guideline194;
    public final Guideline guideline195;
    public final Guideline guideline196;
    public final Guideline guideline197;
    public final Guideline guideline198;
    public final Guideline guideline199;
    public final ImageView imageViewNoSchedulesAvailable;
    public final ConstraintLayout layoutCheckBoxes;
    public final ConstraintLayout layoutDays;
    public final ConstraintLayout layoutNoSchedulesAvailable;
    public final ConstraintLayout layoutSettingsWeeklyTimer;
    public final ConstraintLayout layoutTop;
    public final RadioButton radioButtonFri;
    public final RadioButton radioButtonMon;
    public final RadioButton radioButtonSat;
    public final RadioButton radioButtonSun;
    public final RadioButton radioButtonThu;
    public final RadioButton radioButtonTue;
    public final RadioButton radioButtonWed;
    public final LinearLayout rootLayoutForDynamicList;
    public final ConstraintLayout rootLayoutVd;
    public final ScrollView scrollView;
    public final CustomSwitchButton switchWeeklyTimer;
    public final ImageButton textViewAddItemWeeklyTimer;
    public final TextView textViewNoSchedulesAvailableSubTitle;
    public final TextView textViewNoSchedulesAvailableTitle;
    public final TextView textViewWeeklyTimerTitleSmall;
    public final View view1;
    public final View view2;
    public final View view3;
    public final ConstraintLayout weeklyTimerOuterLayout;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected WeeklyTimerGlobalVdBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, TriStateCheckbox triStateCheckbox2, TriStateCheckbox triStateCheckbox3, TriStateCheckbox triStateCheckbox4, TriStateCheckbox triStateCheckbox5, TriStateCheckbox triStateCheckbox6, TriStateCheckbox triStateCheckbox7, RadioGroup radioGroup, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageView imageView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, RadioButton radioButton7, LinearLayout linearLayout, ConstraintLayout constraintLayout6, ScrollView scrollView2, CustomSwitchButton customSwitchButton, ImageButton imageButton, TextView textView, TextView textView2, TextView textView3, View view4, View view5, View view6, ConstraintLayout constraintLayout7) {
        super(obj, view, i);
        this.checkBoxFri = triStateCheckbox;
        this.checkBoxMon = triStateCheckbox2;
        this.checkBoxSat = triStateCheckbox3;
        this.checkBoxSun = triStateCheckbox4;
        this.checkBoxThu = triStateCheckbox5;
        this.checkBoxTue = triStateCheckbox6;
        this.checkBoxWed = triStateCheckbox7;
        this.dailyWeeklyButtonView = radioGroup;
        this.guideline194 = guideline;
        this.guideline195 = guideline2;
        this.guideline196 = guideline3;
        this.guideline197 = guideline4;
        this.guideline198 = guideline5;
        this.guideline199 = guideline6;
        this.imageViewNoSchedulesAvailable = imageView;
        this.layoutCheckBoxes = constraintLayout;
        this.layoutDays = constraintLayout2;
        this.layoutNoSchedulesAvailable = constraintLayout3;
        this.layoutSettingsWeeklyTimer = constraintLayout4;
        this.layoutTop = constraintLayout5;
        this.radioButtonFri = radioButton;
        this.radioButtonMon = radioButton2;
        this.radioButtonSat = radioButton3;
        this.radioButtonSun = radioButton4;
        this.radioButtonThu = radioButton5;
        this.radioButtonTue = radioButton6;
        this.radioButtonWed = radioButton7;
        this.rootLayoutForDynamicList = linearLayout;
        this.rootLayoutVd = constraintLayout6;
        this.scrollView = scrollView2;
        this.switchWeeklyTimer = customSwitchButton;
        this.textViewAddItemWeeklyTimer = imageButton;
        this.textViewNoSchedulesAvailableSubTitle = textView;
        this.textViewNoSchedulesAvailableTitle = textView2;
        this.textViewWeeklyTimerTitleSmall = textView3;
        this.view1 = view4;
        this.view2 = view5;
        this.view3 = view6;
        this.weeklyTimerOuterLayout = constraintLayout7;
    }

    public static WeeklyTimerGlobalVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WeeklyTimerGlobalVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_global_vd, viewGroup, z, obj);
    }

    public static WeeklyTimerGlobalVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WeeklyTimerGlobalVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.weekly_timer_global_vd, (ViewGroup) null, false, obj);
    }

    public static WeeklyTimerGlobalVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeeklyTimerGlobalVdBinding bind(View view, Object obj) {
        return (WeeklyTimerGlobalVdBinding) bind(obj, view, R.layout.weekly_timer_global_vd);
    }
}
