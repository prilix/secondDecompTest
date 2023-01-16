package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class SetModeAndTemperatureModelWise_ViewBinding implements Unbinder {
    private SetModeAndTemperatureModelWise target;

    public SetModeAndTemperatureModelWise_ViewBinding(SetModeAndTemperatureModelWise setModeAndTemperatureModelWise, View view) {
        this.target = setModeAndTemperatureModelWise;
        setModeAndTemperatureModelWise.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
    }

    public void unbind() {
        SetModeAndTemperatureModelWise setModeAndTemperatureModelWise = this.target;
        if (setModeAndTemperatureModelWise != null) {
            this.target = null;
            setModeAndTemperatureModelWise.mParent = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
