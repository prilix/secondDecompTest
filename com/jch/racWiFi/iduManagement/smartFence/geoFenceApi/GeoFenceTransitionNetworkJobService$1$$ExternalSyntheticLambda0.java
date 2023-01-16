package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceOccupancyModel;
import com.jch.racWiFi.iduManagement.smartFence.networkDispatcher.GeoFencesNetworkDispatcher;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GeoFenceTransitionNetworkJobService$1$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ GeoFenceTransitionNetworkJobService.C18851 f$0;
    public final /* synthetic */ GeoFencesNetworkDispatcher f$1;
    public final /* synthetic */ GeoFenceOccupancyModel f$2;

    public /* synthetic */ GeoFenceTransitionNetworkJobService$1$$ExternalSyntheticLambda0(GeoFenceTransitionNetworkJobService.C18851 r1, GeoFencesNetworkDispatcher geoFencesNetworkDispatcher, GeoFenceOccupancyModel geoFenceOccupancyModel) {
        this.f$0 = r1;
        this.f$1 = geoFencesNetworkDispatcher;
        this.f$2 = geoFenceOccupancyModel;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30078x22109a44(this.f$1, this.f$2, (Resource) obj);
    }
}
