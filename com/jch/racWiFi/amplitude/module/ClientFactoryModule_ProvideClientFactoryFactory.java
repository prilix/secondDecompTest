package com.jch.racWiFi.amplitude.module;

import android.app.Application;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ClientFactoryModule_ProvideClientFactoryFactory implements Factory<ClientFactory> {
    private final Provider<Application> applicationProvider;

    public ClientFactoryModule_ProvideClientFactoryFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public ClientFactory get() {
        return provideClientFactory(this.applicationProvider.get());
    }

    public static ClientFactoryModule_ProvideClientFactoryFactory create(Provider<Application> provider) {
        return new ClientFactoryModule_ProvideClientFactoryFactory(provider);
    }

    public static ClientFactory provideClientFactory(Application application) {
        return (ClientFactory) Preconditions.checkNotNullFromProvides(ClientFactoryModule.provideClientFactory(application));
    }
}
