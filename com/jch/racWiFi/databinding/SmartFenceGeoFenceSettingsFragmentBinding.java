package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartFenceGeoFenceSettingsFragmentBinding extends ViewDataBinding {
    public final View arrivingSelectionHighlight;
    public final ImageButton backButton;
    public final TriStateCheckbox checkBoxSetsmartfenceRangeArriving;
    public final TriStateCheckbox checkBoxSetsmartfenceRangeLeaving;
    public final ConstraintLayout constarint1;
    public final Guideline guideline1;
    public final Guideline guideline140;
    public final Guideline guideline15;
    public final Guideline guideline16;
    public final Guideline guideline18;
    public final Guideline guideline19;
    public final Guideline guideline20;
    public final Guideline guideline21;
    public final Guideline guideline41;
    public final Guideline guideline44;
    public final Guideline guideline5;
    public final Guideline guideline9;
    public final ImageButton imageButtonCurrentLocation;
    public final ImageButton imageButtonHelpArriving;
    public final ImageButton imageButtonHelpLeaving;
    public final ImageView imageViewArriving;
    public final ImageView imageViewAutoDetectHelp;
    public final ImageView imageViewLeaving;

    /* renamed from: info  reason: collision with root package name */
    public final ConstraintLayout f798info;
    public final ConstraintLayout layoutArriving;
    public final ConstraintLayout layoutLeaving;
    public final ConstraintLayout layoutTop;
    public final View leavingSelectionHighlight;
    public final ConstraintLayout mapviewHolder;
    public final LinearLayout seekBarContainer;
    public final LinearLayout seekbarLeaving;
    public final ConstraintLayout tabArriving;
    public final ConstraintLayout tabLeaving;
    public final TextView textViewAddress;
    public final TextView textViewArrivingKm;
    public final TextView textViewArrivingTitle;
    public final TextView textViewAutoDectectDesc;
    public final TextView textViewEndRangeArriving;
    public final TextView textViewEndRangeLeaving;
    public final TextView textViewForgotPasswordTitle;
    public final TextView textViewLeavingKm;
    public final TextView textViewLeavingTitle;
    public final ImageButton textViewSave;
    public final TextView textViewSetGeofenceRangeArriving;
    public final TextView textViewSetGeofenceRangeLeaving;
    public final TextView textViewSetGeofenceRangeTitleArriving;
    public final TextView textViewSetGeofenceRangeTitleLeaving;
    public final TextView textViewStartRangeArriving;
    public final TextView textViewStartRangeLeaving;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SmartFenceGeoFenceSettingsFragmentBinding(Object obj, View view, int i, View view2, ImageButton imageButton, TriStateCheckbox triStateCheckbox, TriStateCheckbox triStateCheckbox2, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline10, Guideline guideline11, Guideline guideline12, Guideline guideline13, Guideline guideline14, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, View view3, ConstraintLayout constraintLayout6, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, ImageButton imageButton5, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        super(obj, view, i);
        this.arrivingSelectionHighlight = view2;
        this.backButton = imageButton;
        this.checkBoxSetsmartfenceRangeArriving = triStateCheckbox;
        this.checkBoxSetsmartfenceRangeLeaving = triStateCheckbox2;
        this.constarint1 = constraintLayout;
        this.guideline1 = guideline;
        this.guideline140 = guideline2;
        this.guideline15 = guideline3;
        this.guideline16 = guideline4;
        this.guideline18 = guideline6;
        this.guideline19 = guideline7;
        this.guideline20 = guideline8;
        this.guideline21 = guideline10;
        this.guideline41 = guideline11;
        this.guideline44 = guideline12;
        this.guideline5 = guideline13;
        this.guideline9 = guideline14;
        this.imageButtonCurrentLocation = imageButton2;
        this.imageButtonHelpArriving = imageButton3;
        this.imageButtonHelpLeaving = imageButton4;
        this.imageViewArriving = imageView;
        this.imageViewAutoDetectHelp = imageView2;
        this.imageViewLeaving = imageView3;
        this.f798info = constraintLayout2;
        this.layoutArriving = constraintLayout3;
        this.layoutLeaving = constraintLayout4;
        this.layoutTop = constraintLayout5;
        this.leavingSelectionHighlight = view3;
        this.mapviewHolder = constraintLayout6;
        this.seekBarContainer = linearLayout;
        this.seekbarLeaving = linearLayout2;
        this.tabArriving = constraintLayout7;
        this.tabLeaving = constraintLayout8;
        this.textViewAddress = textView;
        this.textViewArrivingKm = textView2;
        this.textViewArrivingTitle = textView3;
        this.textViewAutoDectectDesc = textView4;
        this.textViewEndRangeArriving = textView5;
        this.textViewEndRangeLeaving = textView6;
        this.textViewForgotPasswordTitle = textView7;
        this.textViewLeavingKm = textView8;
        this.textViewLeavingTitle = textView9;
        this.textViewSave = imageButton5;
        this.textViewSetGeofenceRangeArriving = textView10;
        this.textViewSetGeofenceRangeLeaving = textView11;
        this.textViewSetGeofenceRangeTitleArriving = textView12;
        this.textViewSetGeofenceRangeTitleLeaving = textView13;
        this.textViewStartRangeArriving = textView14;
        this.textViewStartRangeLeaving = textView15;
    }

    public static SmartFenceGeoFenceSettingsFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceGeoFenceSettingsFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceGeoFenceSettingsFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_geo_fence_settings_fragment, viewGroup, z, obj);
    }

    public static SmartFenceGeoFenceSettingsFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceGeoFenceSettingsFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceGeoFenceSettingsFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_geo_fence_settings_fragment, (ViewGroup) null, false, obj);
    }

    public static SmartFenceGeoFenceSettingsFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceGeoFenceSettingsFragmentBinding bind(View view, Object obj) {
        return (SmartFenceGeoFenceSettingsFragmentBinding) bind(obj, view, R.layout.smart_fence_geo_fence_settings_fragment);
    }
}
