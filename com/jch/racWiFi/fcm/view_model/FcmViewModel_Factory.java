package com.jch.racWiFi.fcm.view_model;

import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import com.jch.racWiFi.p010di.api.FcmApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FcmViewModel_Factory implements Factory<FcmViewModel> {
    private final Provider<DeepLinkFactory> deepLinkFactoryProvider;
    private final Provider<FcmApi> fcmApiProvider;
    private final Provider<ModelRepository> modelRepositoryProvider;

    public FcmViewModel_Factory(Provider<FcmApi> provider, Provider<ModelRepository> provider2, Provider<DeepLinkFactory> provider3) {
        this.fcmApiProvider = provider;
        this.modelRepositoryProvider = provider2;
        this.deepLinkFactoryProvider = provider3;
    }

    public FcmViewModel get() {
        return newInstance(this.fcmApiProvider.get(), this.modelRepositoryProvider.get(), this.deepLinkFactoryProvider.get());
    }

    public static FcmViewModel_Factory create(Provider<FcmApi> provider, Provider<ModelRepository> provider2, Provider<DeepLinkFactory> provider3) {
        return new FcmViewModel_Factory(provider, provider2, provider3);
    }

    public static FcmViewModel newInstance(FcmApi fcmApi, ModelRepository modelRepository, DeepLinkFactory deepLinkFactory) {
        return new FcmViewModel(fcmApi, modelRepository, deepLinkFactory);
    }
}
