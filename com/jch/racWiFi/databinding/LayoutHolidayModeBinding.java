package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.iduManagement.viewModel.HolidayModeViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutHolidayModeBinding extends ViewDataBinding {
    public final TriStateCheckbox cbAllDevices;
    public final TextView editTextEndDate;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline49;
    public final Guideline guideline51;
    public final Guideline guideline53;
    public final Guideline guideline54;
    public final Guideline guideline68;
    public final ImageButton imageButtonMenu;
    public final ImageButton imageButtonPickEndDate;
    public final ImageView imageViewAllAcs;
    public final ImageView imageViewRightSwipe;
    public final ConstraintLayout layoutAllAcs;
    public final ConstraintLayout layoutApplyTo;
    public final ConstraintLayout layoutBottom;
    public final ConstraintLayout layoutDate;
    public final ConstraintLayout layoutSetTemprature;
    public final ConstraintLayout layoutTop;
    @Bindable
    protected HolidayModeViewModel mHolidayModeViewModel;
    public final ConstraintLayout parent;
    public final RecyclerView recyclerViewDevices;
    public final ConstraintLayout rootLayout;
    public final CustomSwitchButton switchHolidayMode;
    public final TextView textViewApplyTo;
    public final TextView textViewEndDateTitle;
    public final TextView textViewHolidayModeSubTitle;
    public final TextView textViewHolidayModeTitle;
    public final ImageButton textViewSave;
    public final TextView textViewSetTempratureHolidayMode;
    public final TextView textViewTempratureHolidayMode;
    public final TextView textViewTempratureUnitHolidayMode;
    public final View view3;

    public abstract void setHolidayModeViewModel(HolidayModeViewModel holidayModeViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LayoutHolidayModeBinding(Object obj, View view, int i, TriStateCheckbox triStateCheckbox, TextView textView, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, ImageButton imageButton, ImageButton imageButton2, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, RecyclerView recyclerView, ConstraintLayout constraintLayout8, CustomSwitchButton customSwitchButton, TextView textView2, TextView textView3, TextView textView4, TextView textView5, ImageButton imageButton3, TextView textView6, TextView textView7, TextView textView8, View view2) {
        super(obj, view, i);
        this.cbAllDevices = triStateCheckbox;
        this.editTextEndDate = textView;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.guideline49 = guideline3;
        this.guideline51 = guideline4;
        this.guideline53 = guideline5;
        this.guideline54 = guideline6;
        this.guideline68 = guideline7;
        this.imageButtonMenu = imageButton;
        this.imageButtonPickEndDate = imageButton2;
        this.imageViewAllAcs = imageView;
        this.imageViewRightSwipe = imageView2;
        this.layoutAllAcs = constraintLayout;
        this.layoutApplyTo = constraintLayout2;
        this.layoutBottom = constraintLayout3;
        this.layoutDate = constraintLayout4;
        this.layoutSetTemprature = constraintLayout5;
        this.layoutTop = constraintLayout6;
        this.parent = constraintLayout7;
        this.recyclerViewDevices = recyclerView;
        this.rootLayout = constraintLayout8;
        this.switchHolidayMode = customSwitchButton;
        this.textViewApplyTo = textView2;
        this.textViewEndDateTitle = textView3;
        this.textViewHolidayModeSubTitle = textView4;
        this.textViewHolidayModeTitle = textView5;
        this.textViewSave = imageButton3;
        this.textViewSetTempratureHolidayMode = textView6;
        this.textViewTempratureHolidayMode = textView7;
        this.textViewTempratureUnitHolidayMode = textView8;
        this.view3 = view2;
    }

    public HolidayModeViewModel getHolidayModeViewModel() {
        return this.mHolidayModeViewModel;
    }

    public static LayoutHolidayModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHolidayModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutHolidayModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_holiday_mode, viewGroup, z, obj);
    }

    public static LayoutHolidayModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHolidayModeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutHolidayModeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_holiday_mode, (ViewGroup) null, false, obj);
    }

    public static LayoutHolidayModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHolidayModeBinding bind(View view, Object obj) {
        return (LayoutHolidayModeBinding) bind(obj, view, R.layout.layout_holiday_mode);
    }
}
