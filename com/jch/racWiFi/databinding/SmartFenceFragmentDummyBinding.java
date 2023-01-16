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
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchCompat;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartFenceFragmentDummyBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final Button buttonEnabledArrving;
    public final Button buttonEnabledLeaving;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline31;
    public final Guideline guideline32;
    public final Guideline guideline33;
    public final Guideline guideline34;
    public final ImageButton imageButtonHelp;
    public final ImageView imageViewRightSwipe1;
    public final ImageView imageViewRightSwipe2;
    public final ImageView imageViewRightSwipe3;
    public final ConstraintLayout layoutAcs;
    public final ConstraintLayout layoutArriving;
    public final ConstraintLayout layoutGeofenceSettings;
    public final ConstraintLayout layoutInsideGeofenceRange;
    public final ConstraintLayout layoutLeaving;
    public final ConstraintLayout layoutNote;
    public final ConstraintLayout layoutNoteBottom;
    public final ConstraintLayout layoutOutsideGeofenceRange;
    public final ConstraintLayout layoutTop;
    public final ConstraintLayout layoutUsers;
    public final ImageView modeImageViewArriving;
    public final ImageView modeImageViewLeaving;
    public final TextView modeTextViewArriving;
    public final TextView modeTextViewLeaving;
    public final ConstraintLayout parent;
    public final ScrollView scrollView;
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
    public final TextView textViewNoteDescFour;
    public final TextView textViewNoteDescFourNew;
    public final TextView textViewNoteDescOne;
    public final TextView textViewNoteDescOneNew;
    public final TextView textViewNoteDescThree;
    public final TextView textViewNoteDescThreeNew;
    public final TextView textViewNoteDescTwo;
    public final TextView textViewNoteDescTwoNew;
    public final TextView textViewRoomTitle;
    public final TextView textViewSave;
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
    protected SmartFenceFragmentDummyBinding(Object obj, View view, int i, ImageButton imageButton, Button button, Button button2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, ImageButton imageButton2, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ImageView imageView4, ImageView imageView5, TextView textView, TextView textView2, ConstraintLayout constraintLayout11, ScrollView scrollView2, CustomSwitchCompat customSwitchCompat, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, View view2, View view3, View view4, View view5, View view6, View view7) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.buttonEnabledArrving = button;
        this.buttonEnabledLeaving = button2;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.guideline31 = guideline3;
        this.guideline32 = guideline4;
        this.guideline33 = guideline5;
        this.guideline34 = guideline6;
        this.imageButtonHelp = imageButton2;
        this.imageViewRightSwipe1 = imageView;
        this.imageViewRightSwipe2 = imageView2;
        this.imageViewRightSwipe3 = imageView3;
        this.layoutAcs = constraintLayout;
        this.layoutArriving = constraintLayout2;
        this.layoutGeofenceSettings = constraintLayout3;
        this.layoutInsideGeofenceRange = constraintLayout4;
        this.layoutLeaving = constraintLayout5;
        this.layoutNote = constraintLayout6;
        this.layoutNoteBottom = constraintLayout7;
        this.layoutOutsideGeofenceRange = constraintLayout8;
        this.layoutTop = constraintLayout9;
        this.layoutUsers = constraintLayout10;
        this.modeImageViewArriving = imageView4;
        this.modeImageViewLeaving = imageView5;
        this.modeTextViewArriving = textView;
        this.modeTextViewLeaving = textView2;
        this.parent = constraintLayout11;
        this.scrollView = scrollView2;
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
        this.textViewNoteDescFour = textView12;
        this.textViewNoteDescFourNew = textView13;
        this.textViewNoteDescOne = textView14;
        this.textViewNoteDescOneNew = textView15;
        this.textViewNoteDescThree = textView16;
        this.textViewNoteDescThreeNew = textView17;
        this.textViewNoteDescTwo = textView18;
        this.textViewNoteDescTwoNew = textView19;
        this.textViewRoomTitle = textView20;
        this.textViewSave = textView21;
        this.textViewSetModeAndTempArriving = textView22;
        this.textViewSetModeAndTempLeaving = textView23;
        this.textViewUsers = textView24;
        this.view38 = view2;
        this.view39 = view3;
        this.view40 = view4;
        this.view41 = view5;
        this.view43 = view6;
        this.view48 = view7;
    }

    public static SmartFenceFragmentDummyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceFragmentDummyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceFragmentDummyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_fragment_dummy, viewGroup, z, obj);
    }

    public static SmartFenceFragmentDummyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceFragmentDummyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceFragmentDummyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_fragment_dummy, (ViewGroup) null, false, obj);
    }

    public static SmartFenceFragmentDummyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceFragmentDummyBinding bind(View view, Object obj) {
        return (SmartFenceFragmentDummyBinding) bind(obj, view, R.layout.smart_fence_fragment_dummy);
    }
}
