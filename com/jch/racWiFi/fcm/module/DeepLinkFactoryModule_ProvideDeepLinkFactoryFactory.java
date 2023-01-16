package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory implements Factory<DeepLinkFactory> {
    private final Provider<Application> applicationProvider;

    public DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public DeepLinkFactory get() {
        return provideDeepLinkFactory(this.applicationProvider.get());
    }

    public static DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory create(Provider<Application> provider) {
        return new DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory(provider);
    }

    public static DeepLinkFactory provideDeepLinkFactory(Application application) {
        return (DeepLinkFactory) Preconditions.checkNotNullFromProvides(DeepLinkFactoryModule.provideDeepLinkFactory(application));
    }
}
