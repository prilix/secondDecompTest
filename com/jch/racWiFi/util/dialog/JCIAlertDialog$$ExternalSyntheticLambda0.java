package com.jch.racWiFi.util.dialog;

import android.content.DialogInterface;
import com.jch.racWiFi.util.listeners.AlertListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class JCIAlertDialog$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AlertListener f$0;

    public /* synthetic */ JCIAlertDialog$$ExternalSyntheticLambda0(AlertListener alertListener) {
        this.f$0 = alertListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        JCIAlertDialog.m1574showDialog$lambda2(this.f$0, dialogInterface, i);
    }
}
