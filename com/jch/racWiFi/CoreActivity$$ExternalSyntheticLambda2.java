package com.jch.racWiFi;

import android.app.Dialog;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda2 implements ConfirmationDialog.CustomOnClickListener {
    public static final /* synthetic */ CoreActivity$$ExternalSyntheticLambda2 INSTANCE = new CoreActivity$$ExternalSyntheticLambda2();

    private /* synthetic */ CoreActivity$$ExternalSyntheticLambda2() {
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return CoreActivity.lambda$showPermissionDeniedDialog$7(dialog, view);
    }
}
