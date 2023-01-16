package com.jch.racWiFi.userManagement.view;

import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MyAccountAddressFragment$5$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ MyAccountAddressFragment.C24865 f$0;
    public final /* synthetic */ Geocoder f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ MyAccountAddressFragment$5$$ExternalSyntheticLambda0(MyAccountAddressFragment.C24865 r1, Geocoder geocoder, String str) {
        this.f$0 = r1;
        this.f$1 = geocoder;
        this.f$2 = str;
    }

    public final void onSuccess(Object obj) {
        this.f$0.mo32934x9e2d8f3b(this.f$1, this.f$2, (Location) obj);
    }
}
