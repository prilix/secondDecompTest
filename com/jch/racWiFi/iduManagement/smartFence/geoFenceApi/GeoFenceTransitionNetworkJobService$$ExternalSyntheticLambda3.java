package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.job.JobParameters;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceOccupancyModel;
import com.jch.racWiFi.iduManagement.smartFence.networkDispatcher.GeoFencesNetworkDispatcher;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ GeoFenceTransitionNetworkJobService f$0;
    public final /* synthetic */ GeoFencesNetworkDispatcher f$1;
    public final /* synthetic */ GeoFenceOccupancyModel f$2;
    public final /* synthetic */ JobParameters f$3;

    public /* synthetic */ GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda3(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService, GeoFencesNetworkDispatcher geoFencesNetworkDispatcher, GeoFenceOccupancyModel geoFenceOccupancyModel, JobParameters jobParameters) {
        this.f$0 = geoFenceTransitionNetworkJobService;
        this.f$1 = geoFencesNetworkDispatcher;
        this.f$2 = geoFenceOccupancyModel;
        this.f$3 = jobParameters;
    }

    public final void run() {
        this.f$0.mo30073xa7905758(this.f$1, this.f$2, this.f$3);
    }
}
