package com.jch.racWiFi.settings.view;

import android.view.View;
import android.widget.RadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import info.hoang8f.android.segmented.SegmentedGroup;

public class TemperaturePreferenceFragment_ViewBinding implements Unbinder {
    private TemperaturePreferenceFragment target;
    private View view7f0a011e;
    private View view7f0a0a77;

    public TemperaturePreferenceFragment_ViewBinding(final TemperaturePreferenceFragment temperaturePreferenceFragment, View view) {
        this.target = temperaturePreferenceFragment;
        temperaturePreferenceFragment.mTemperatureSelection = (SegmentedGroup) C0840Utils.findRequiredViewAsType(view, R.id.segment_unit, "field 'mTemperatureSelection'", SegmentedGroup.class);
        temperaturePreferenceFragment.mCelsiusSelection = (RadioButton) C0840Utils.findRequiredViewAsType(view, R.id.radio_button_celsius, "field 'mCelsiusSelection'", RadioButton.class);
        temperaturePreferenceFragment.mFahrenheitSelection = (RadioButton) C0840Utils.findRequiredViewAsType(view, R.id.radio_button_fahrenheit, "field 'mFahrenheitSelection'", RadioButton.class);
        temperaturePreferenceFragment.mTemperatureUnitText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_unit_units, "field 'mTemperatureUnitText'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'onClickSave'");
        temperaturePreferenceFragment.mSave = (ImageButton) C0840Utils.castView(findRequiredView, R.id.text_view_save, "field 'mSave'", ImageButton.class);
        this.view7f0a0a77 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                temperaturePreferenceFragment.onClickSave();
            }
        });
        temperaturePreferenceFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickMenu'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                temperaturePreferenceFragment.onClickMenu((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickMenu", 0, ImageButton.class));
            }
        });
    }

    public void unbind() {
        TemperaturePreferenceFragment temperaturePreferenceFragment = this.target;
        if (temperaturePreferenceFragment != null) {
            this.target = null;
            temperaturePreferenceFragment.mTemperatureSelection = null;
            temperaturePreferenceFragment.mCelsiusSelection = null;
            temperaturePreferenceFragment.mFahrenheitSelection = null;
            temperaturePreferenceFragment.mTemperatureUnitText = null;
            temperaturePreferenceFragment.mSave = null;
            temperaturePreferenceFragment.mParent = null;
            this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a77 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
