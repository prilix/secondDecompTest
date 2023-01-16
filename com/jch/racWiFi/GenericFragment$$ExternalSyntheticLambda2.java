package com.jch.racWiFi;

import android.app.Dialog;
import android.view.View;
import androidx.navigation.NavController;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericFragment$$ExternalSyntheticLambda2 implements ConfirmationDialog.CustomOnClickListener {
    public final /* synthetic */ NavController f$0;

    public /* synthetic */ GenericFragment$$ExternalSyntheticLambda2(NavController navController) {
        this.f$0 = navController;
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return GenericFragment.lambda$showPermissionDeniedDialog$3(this.f$0, dialog, view);
    }
}
