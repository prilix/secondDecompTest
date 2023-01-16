package com.jch.racWiFi.energyConsumption.fragment;

import android.app.Dialog;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ECSelectACFragment$$ExternalSyntheticLambda1 implements ConfirmationDialog.CustomOnClickListener {
    public final /* synthetic */ ECSelectACFragment f$0;

    public /* synthetic */ ECSelectACFragment$$ExternalSyntheticLambda1(ECSelectACFragment eCSelectACFragment) {
        this.f$0 = eCSelectACFragment;
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return ECSelectACFragment.m931showConfirmation$lambda12(this.f$0, dialog, view);
    }
}
