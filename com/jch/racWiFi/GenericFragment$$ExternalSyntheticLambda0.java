package com.jch.racWiFi;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericFragment$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Fragment f$0;

    public /* synthetic */ GenericFragment$$ExternalSyntheticLambda0(Fragment fragment) {
        this.f$0 = fragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 147);
    }
}
