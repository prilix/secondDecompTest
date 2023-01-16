package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.amplitude.util.AppEventService;
import com.jch.racWiFi.fcm.services.FcmService;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule */
public abstract class ServiceBuilderModule {
    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract AppEventService contributeAppEventService();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract FcmService contributeFcmService();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract GeoFenceTransitionNetworkJobService contributeGeoFenceTransitionNetworkJobService();
}
