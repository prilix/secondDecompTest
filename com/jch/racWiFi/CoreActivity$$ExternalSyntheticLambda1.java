package com.jch.racWiFi;

import android.app.Dialog;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.util.listeners.AlertListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda1 implements ConfirmationDialog.CustomOnClickListener {
    public final /* synthetic */ AlertListener f$0;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda1(AlertListener alertListener) {
        this.f$0 = alertListener;
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return this.f$0.onPositive();
    }
}
