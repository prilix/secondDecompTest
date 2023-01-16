package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchCompat;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartfenceFragmentBinding extends ViewDataBinding {
    public final ConstraintLayout allFeatureLayout;
    public final Button buttonEnabledArrving;
    public final Button buttonEnabledLeaving;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline31;
    public final Guideline guideline32;
    public final Guideline guideline33;
    public final Guideline guideline34;
    public final ImageButton imageButtonHelp;
    public final ImageButton imageButtonMenu;
    public final ImageView imageViewRightSwipe1;
    public final ImageView imageViewRightSwipe2;
    public final ImageView imageViewRightSwipe3;
    public final ConstraintLayout layoutAcs;
    public final LinearLayout layoutAddAc;
    public final ConstraintLayout layoutArriving;
    public final ConstraintLayout layoutGeofenceSettings;
    public final ConstraintLayout layoutInsideGeofenceRange;
    public final ConstraintLayout layoutLeaving;
    public final ConstraintLayout layoutNote;
    public final ConstraintLayout layoutNoteBottom;
    public final ConstraintLayout layoutOutsideGeofenceRange;
    public final ConstraintLayout layoutSettings;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout layoutUsers;
    public final ImageView modeImageViewArriving;
    public final ImageView modeImageViewLeaving;
    public final TextView modeTextViewArriving;
    public final ConstraintLayout modeTextViewConstraintLayoutArriving;
    public final ConstraintLayout modeTextViewConstraintLayoutLeaving;
    public final TextView modeTextViewLeaving;
    public final ConstraintLayout parent;
    public final ScrollView scrollView;
    public final ConstraintLayout setTemoModeForArrivingLayout;
    public final ConstraintLayout setTemoModeForLeavingLayout;
    public final CustomSwitchCompat switchMainHome;
    public final TextView tempTextViewArriving;
    public final TextView tempTextViewLeaving;
    public final TextView textViewAcs;
    public final TextView textViewAllAcs;
    public final TextView textViewAllUsers;
    public final TextView textViewArriving;
    public final TextView textViewGeofenceSettingsSubTitle;
    public final TextView textViewLeaving;
    public final TextView textViewLocationControlSubtitle;
    public final TextView textViewNoRacFound;
    public final TextView textViewNoteDescFour;
    public final TextView textViewNoteDescFourNew;
    public final TextView textViewNoteDescOne;
    public final TextView textViewNoteDescOneNew;
    public final TextView textViewNoteDescThree;
    public final TextView textViewNoteDescThreeNew;
    public final TextView textViewNoteDescTwo;
    public final TextView textViewNoteDescTwoNew;
    public final TextView textViewOffArriving;
    public final TextView textViewOffLeaving;
    public final TextView textViewRoomTitle;
    public final ImageButton textViewSave;
    public final TextView textViewSetModeAndTempArriving;
    public final TextView textViewSetModeAndTempLeaving;
    public final TextView textViewUsers;
    public final View view38;
    public final View view39;
    public final View view40;
    public final View view41;
    public final View view43;
    public final View view48;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SmartfenceFragmentBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, Button button2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageButton imageButton, ImageButton imageButton2, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout2, LinearLayout linearLayout, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ImageView imageView4, ImageView imageView5, TextView textView, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, TextView textView2, ConstraintLayout constraintLayout15, ScrollView scrollView2, ConstraintLayout constraintLayout16, ConstraintLayout constraintLayout17, CustomSwitchCompat customSwitchCompat, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, ImageButton imageButton3, TextView textView24, TextView textView25, TextView textView26, View view2, View view3, View view4, View view5, View view6, View view7) {
        super(obj, view, i);
        this.allFeatureLayout = constraintLayout;
        this.buttonEnabledArrving = button;
        this.buttonEnabledLeaving = button2;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.guideline31 = guideline3;
        this.guideline32 = guideline4;
        this.guideline33 = guideline5;
        this.guideline34 = guideline6;
        this.imageButtonHelp = imageButton;
        this.imageButtonMenu = imageButton2;
        this.imageViewRightSwipe1 = imageView;
        this.imageViewRightSwipe2 = imageView2;
        this.imageViewRightSwipe3 = imageView3;
        this.layoutAcs = constraintLayout2;
        this.layoutAddAc = linearLayout;
        this.layoutArriving = constraintLayout3;
        this.layoutGeofenceSettings = constraintLayout4;
        this.layoutInsideGeofenceRange = constraintLayout5;
        this.layoutLeaving = constraintLayout6;
        this.layoutNote = constraintLayout7;
        this.layoutNoteBottom = constraintLayout8;
        this.layoutOutsideGeofenceRange = constraintLayout9;
        this.layoutSettings = constraintLayout10;
        this.layoutTop = constraintLayout11;
        this.layoutUsers = constraintLayout12;
        this.modeImageViewArriving = imageView4;
        this.modeImageViewLeaving = imageView5;
        this.modeTextViewArriving = textView;
        this.modeTextViewConstraintLayoutArriving = constraintLayout13;
        this.modeTextViewConstraintLayoutLeaving = constraintLayout14;
        this.modeTextViewLeaving = textView2;
        this.parent = constraintLayout15;
        this.scrollView = scrollView2;
        this.setTemoModeForArrivingLayout = constraintLayout16;
        this.setTemoModeForLeavingLayout = constraintLayout17;
        this.switchMainHome = customSwitchCompat;
        this.tempTextViewArriving = textView3;
        this.tempTextViewLeaving = textView4;
        this.textViewAcs = textView5;
        this.textViewAllAcs = textView6;
        this.textViewAllUsers = textView7;
        this.textViewArriving = textView8;
        this.textViewGeofenceSettingsSubTitle = textView9;
        this.textViewLeaving = textView10;
        this.textViewLocationControlSubtitle = textView11;
        this.textViewNoRacFound = textView12;
        this.textViewNoteDescFour = textView13;
        this.textViewNoteDescFourNew = textView14;
        this.textViewNoteDescOne = textView15;
        this.textViewNoteDescOneNew = textView16;
        this.textViewNoteDescThree = textView17;
        this.textViewNoteDescThreeNew = textView18;
        this.textViewNoteDescTwo = textView19;
        this.textViewNoteDescTwoNew = textView20;
        this.textViewOffArriving = textView21;
        this.textViewOffLeaving = textView22;
        this.textViewRoomTitle = textView23;
        this.textViewSave = imageButton3;
        this.textViewSetModeAndTempArriving = textView24;
        this.textViewSetModeAndTempLeaving = textView25;
        this.textViewUsers = textView26;
        this.view38 = view2;
        this.view39 = view3;
        this.view40 = view4;
        this.view41 = view5;
        this.view43 = view6;
        this.view48 = view7;
    }

    public static SmartfenceFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartfenceFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartfenceFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smartfence_fragment, viewGroup, z, obj);
    }

    public static SmartfenceFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartfenceFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartfenceFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smartfence_fragment, (ViewGroup) null, false, obj);
    }

    public static SmartfenceFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartfenceFragmentBinding bind(View view, Object obj) {
        return (SmartfenceFragmentBinding) bind(obj, view, R.layout.smartfence_fragment);
    }
}
