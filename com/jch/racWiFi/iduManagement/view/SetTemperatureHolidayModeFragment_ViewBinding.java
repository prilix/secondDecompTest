package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class SetTemperatureHolidayModeFragment_ViewBinding implements Unbinder {
    private SetTemperatureHolidayModeFragment target;

    public SetTemperatureHolidayModeFragment_ViewBinding(SetTemperatureHolidayModeFragment setTemperatureHolidayModeFragment, View view) {
        this.target = setTemperatureHolidayModeFragment;
        setTemperatureHolidayModeFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
    }

    public void unbind() {
        SetTemperatureHolidayModeFragment setTemperatureHolidayModeFragment = this.target;
        if (setTemperatureHolidayModeFragment != null) {
            this.target = null;
            setTemperatureHolidayModeFragment.mParent = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
