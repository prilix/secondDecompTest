package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch_hitachi.aircloudglobal.R;

public class SetTimerFragmentV2_ViewBinding implements Unbinder {
    private SetTimerFragmentV2 target;
    private View view7f0a011e;
    private View view7f0a040b;
    private View view7f0a040c;
    private View view7f0a05c5;
    private View view7f0a0a77;

    public SetTimerFragmentV2_ViewBinding(final SetTimerFragmentV2 setTimerFragmentV2, View view) {
        this.target = setTimerFragmentV2;
        setTimerFragmentV2.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
        setTimerFragmentV2.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                setTimerFragmentV2.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'OnClickSave'");
        setTimerFragmentV2.mSave = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.text_view_save, "field 'mSave'", ImageButton.class);
        this.view7f0a0a77 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                setTimerFragmentV2.OnClickSave((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickSave", 0, ImageButton.class));
            }
        });
        setTimerFragmentV2.mModeName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_mode_weekly_timer, "field 'mModeName'", TextView.class);
        setTimerFragmentV2.mTemprature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_weekly_timer, "field 'mTemprature'", TextView.class);
        setTimerFragmentV2.mTemperatureUnit = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_unit_weekly_timer, "field 'mTemperatureUnit'", TextView.class);
        setTimerFragmentV2.mModeImage = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_mode_weekly_timer, "field 'mModeImage'", ImageView.class);
        setTimerFragmentV2.mSwitchTimer = (CustomSwitchButton) C0840Utils.findRequiredViewAsType(view, R.id.switch_timer, "field 'mSwitchTimer'", CustomSwitchButton.class);
        setTimerFragmentV2.mSwitchOnAfter = (CustomSwitchButton) C0840Utils.findRequiredViewAsType(view, R.id.switch_on_after, "field 'mSwitchOnAfter'", CustomSwitchButton.class);
        setTimerFragmentV2.mSwitchOffAfter = (CustomSwitchButton) C0840Utils.findRequiredViewAsType(view, R.id.switch_off_after, "field 'mSwitchOffAfter'", CustomSwitchButton.class);
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.image_button_timer_switch_on_after, "field 'mImageButtonTimerSwitchOnAfter' and method 'OnClickTimerSwitchOnAfter'");
        setTimerFragmentV2.mImageButtonTimerSwitchOnAfter = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.image_button_timer_switch_on_after, "field 'mImageButtonTimerSwitchOnAfter'", ImageButton.class);
        this.view7f0a040c = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                setTimerFragmentV2.OnClickTimerSwitchOnAfter((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickTimerSwitchOnAfter", 0, ImageButton.class));
            }
        });
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.image_button_timer_switch_off_after, "field 'mImageButtonTimerSwitchOffAfter' and method 'OnClickTimerSwitchOffAfter'");
        setTimerFragmentV2.mImageButtonTimerSwitchOffAfter = (ImageButton) C0840Utils.castView(findRequiredView4, R.id.image_button_timer_switch_off_after, "field 'mImageButtonTimerSwitchOffAfter'", ImageButton.class);
        this.view7f0a040b = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                setTimerFragmentV2.OnClickTimerSwitchOffAfter((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickTimerSwitchOffAfter", 0, ImageButton.class));
            }
        });
        setTimerFragmentV2.mTimeSwitchOnAfter = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_time_switch_on_after, "field 'mTimeSwitchOnAfter'", TextView.class);
        setTimerFragmentV2.mTimeSwitchOffAfter = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_time_switch_off_after, "field 'mTimeSwitchOffAfter'", TextView.class);
        setTimerFragmentV2.mSwitchOnAfterTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_switch_on_after, "field 'mSwitchOnAfterTitle'", TextView.class);
        setTimerFragmentV2.mSwitchOffAfterTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_switch_off_after, "field 'mSwitchOffAfterTitle'", TextView.class);
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.layout_set_mode_and_temprature, "field 'mSetModeAndTemprature' and method 'OnClickSetModeAndTemperature'");
        setTimerFragmentV2.mSetModeAndTemprature = (ConstraintLayout) C0840Utils.castView(findRequiredView5, R.id.layout_set_mode_and_temprature, "field 'mSetModeAndTemprature'", ConstraintLayout.class);
        this.view7f0a05c5 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                setTimerFragmentV2.OnClickSetModeAndTemperature((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickSetModeAndTemperature", 0, ConstraintLayout.class));
            }
        });
    }

    public void unbind() {
        SetTimerFragmentV2 setTimerFragmentV2 = this.target;
        if (setTimerFragmentV2 != null) {
            this.target = null;
            setTimerFragmentV2.mParent = null;
            setTimerFragmentV2.mBack = null;
            setTimerFragmentV2.mSave = null;
            setTimerFragmentV2.mModeName = null;
            setTimerFragmentV2.mTemprature = null;
            setTimerFragmentV2.mTemperatureUnit = null;
            setTimerFragmentV2.mModeImage = null;
            setTimerFragmentV2.mSwitchTimer = null;
            setTimerFragmentV2.mSwitchOnAfter = null;
            setTimerFragmentV2.mSwitchOffAfter = null;
            setTimerFragmentV2.mImageButtonTimerSwitchOnAfter = null;
            setTimerFragmentV2.mImageButtonTimerSwitchOffAfter = null;
            setTimerFragmentV2.mTimeSwitchOnAfter = null;
            setTimerFragmentV2.mTimeSwitchOffAfter = null;
            setTimerFragmentV2.mSwitchOnAfterTitle = null;
            setTimerFragmentV2.mSwitchOffAfterTitle = null;
            setTimerFragmentV2.mSetModeAndTemprature = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a77 = null;
            this.view7f0a040c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a040c = null;
            this.view7f0a040b.setOnClickListener((View.OnClickListener) null);
            this.view7f0a040b = null;
            this.view7f0a05c5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a05c5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
