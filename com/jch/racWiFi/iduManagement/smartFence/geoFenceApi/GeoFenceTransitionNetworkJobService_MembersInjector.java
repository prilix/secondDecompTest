package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import com.jch.racWiFi.main.api.BaseMainApi;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class GeoFenceTransitionNetworkJobService_MembersInjector implements MembersInjector<GeoFenceTransitionNetworkJobService> {
    private final Provider<BaseMainApi> mBaseMainApiProvider;

    public GeoFenceTransitionNetworkJobService_MembersInjector(Provider<BaseMainApi> provider) {
        this.mBaseMainApiProvider = provider;
    }

    public static MembersInjector<GeoFenceTransitionNetworkJobService> create(Provider<BaseMainApi> provider) {
        return new GeoFenceTransitionNetworkJobService_MembersInjector(provider);
    }

    public void injectMembers(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService) {
        injectMBaseMainApi(geoFenceTransitionNetworkJobService, this.mBaseMainApiProvider.get());
    }

    public static void injectMBaseMainApi(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService, BaseMainApi baseMainApi) {
        geoFenceTransitionNetworkJobService.mBaseMainApi = baseMainApi;
    }
}
