package com.jch.racWiFi;

import android.app.Dialog;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericFragment$$ExternalSyntheticLambda5 implements SingleChoiceDialog.CustomOnClickListener {
    public final /* synthetic */ GenericFragment f$0;
    public final /* synthetic */ SingleChoiceDialog f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ GenericFragment$$ExternalSyntheticLambda5(GenericFragment genericFragment, SingleChoiceDialog singleChoiceDialog, boolean z) {
        this.f$0 = genericFragment;
        this.f$1 = singleChoiceDialog;
        this.f$2 = z;
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return this.f$0.m795lambda$alertDialog$1$comjchracWiFiGenericFragment(this.f$1, this.f$2, dialog, view);
    }
}
