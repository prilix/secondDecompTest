package com.jch.racWiFi.amplitude.util;

import com.jch.racWiFi.amplitude.factory.ClientFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AppEventService_MembersInjector implements MembersInjector<AppEventService> {
    private final Provider<AmplitudeUtil> mAmplitudeUtilProvider;
    private final Provider<ClientFactory> mClientFactoryProvider;

    public AppEventService_MembersInjector(Provider<AmplitudeUtil> provider, Provider<ClientFactory> provider2) {
        this.mAmplitudeUtilProvider = provider;
        this.mClientFactoryProvider = provider2;
    }

    public static MembersInjector<AppEventService> create(Provider<AmplitudeUtil> provider, Provider<ClientFactory> provider2) {
        return new AppEventService_MembersInjector(provider, provider2);
    }

    public void injectMembers(AppEventService appEventService) {
        injectMAmplitudeUtil(appEventService, this.mAmplitudeUtilProvider.get());
        injectMClientFactory(appEventService, this.mClientFactoryProvider.get());
    }

    public static void injectMAmplitudeUtil(AppEventService appEventService, AmplitudeUtil amplitudeUtil) {
        appEventService.mAmplitudeUtil = amplitudeUtil;
    }

    public static void injectMClientFactory(AppEventService appEventService, ClientFactory clientFactory) {
        appEventService.mClientFactory = clientFactory;
    }
}
