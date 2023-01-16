package com.jch.racWiFi.userManagement.view;

import android.location.Location;
import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MyAccountAddressFragment$$ExternalSyntheticLambda6 implements Observer {
    public final /* synthetic */ MyAccountAddressFragment f$0;

    public /* synthetic */ MyAccountAddressFragment$$ExternalSyntheticLambda6(MyAccountAddressFragment myAccountAddressFragment) {
        this.f$0 = myAccountAddressFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.onLocationAcquired((Location) obj);
    }
}
