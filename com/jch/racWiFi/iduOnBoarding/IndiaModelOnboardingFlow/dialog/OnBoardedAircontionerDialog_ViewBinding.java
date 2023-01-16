package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardedAircontionerDialog_ViewBinding implements Unbinder {
    private OnBoardedAircontionerDialog target;

    public OnBoardedAircontionerDialog_ViewBinding(OnBoardedAircontionerDialog onBoardedAircontionerDialog) {
        this(onBoardedAircontionerDialog, onBoardedAircontionerDialog.getWindow().getDecorView());
    }

    public OnBoardedAircontionerDialog_ViewBinding(OnBoardedAircontionerDialog onBoardedAircontionerDialog, View view) {
        this.target = onBoardedAircontionerDialog;
        onBoardedAircontionerDialog.routerSsidText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_home_router_ssid, "field 'routerSsidText'", TextView.class);
        onBoardedAircontionerDialog.continueButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_positive, "field 'continueButton'", Button.class);
        onBoardedAircontionerDialog.cancelButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_negative, "field 'cancelButton'", Button.class);
    }

    public void unbind() {
        OnBoardedAircontionerDialog onBoardedAircontionerDialog = this.target;
        if (onBoardedAircontionerDialog != null) {
            this.target = null;
            onBoardedAircontionerDialog.routerSsidText = null;
            onBoardedAircontionerDialog.continueButton = null;
            onBoardedAircontionerDialog.cancelButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
