package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class PairedAirConditionerDialog_ViewBinding implements Unbinder {
    private PairedAirConditionerDialog target;

    public PairedAirConditionerDialog_ViewBinding(PairedAirConditionerDialog pairedAirConditionerDialog) {
        this(pairedAirConditionerDialog, pairedAirConditionerDialog.getWindow().getDecorView());
    }

    public PairedAirConditionerDialog_ViewBinding(PairedAirConditionerDialog pairedAirConditionerDialog, View view) {
        this.target = pairedAirConditionerDialog;
        pairedAirConditionerDialog.routerSsidText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_home_router_ssid, "field 'routerSsidText'", TextView.class);
        pairedAirConditionerDialog.continueButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_positive, "field 'continueButton'", Button.class);
        pairedAirConditionerDialog.changeHomeRouter = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_negative, "field 'changeHomeRouter'", Button.class);
    }

    public void unbind() {
        PairedAirConditionerDialog pairedAirConditionerDialog = this.target;
        if (pairedAirConditionerDialog != null) {
            this.target = null;
            pairedAirConditionerDialog.routerSsidText = null;
            pairedAirConditionerDialog.continueButton = null;
            pairedAirConditionerDialog.changeHomeRouter = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
