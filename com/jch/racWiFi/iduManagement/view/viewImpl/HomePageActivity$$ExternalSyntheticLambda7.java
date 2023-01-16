package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePageActivity$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ HomePageActivity f$0;
    public final /* synthetic */ boolean[] f$1;

    public /* synthetic */ HomePageActivity$$ExternalSyntheticLambda7(HomePageActivity homePageActivity, boolean[] zArr) {
        this.f$0 = homePageActivity;
        this.f$1 = zArr;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30763xf666b915(this.f$1, (FamilyIdGeoFenceDataMap) obj);
    }
}
