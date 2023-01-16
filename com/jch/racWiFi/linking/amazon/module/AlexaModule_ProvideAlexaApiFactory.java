package com.jch.racWiFi.linking.amazon.module;

import com.jch.racWiFi.p010di.api.AlexaApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class AlexaModule_ProvideAlexaApiFactory implements Factory<AlexaApi> {
    private final Provider<Retrofit> retrofitProvider;

    public AlexaModule_ProvideAlexaApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public AlexaApi get() {
        return provideAlexaApi(this.retrofitProvider.get());
    }

    public static AlexaModule_ProvideAlexaApiFactory create(Provider<Retrofit> provider) {
        return new AlexaModule_ProvideAlexaApiFactory(provider);
    }

    public static AlexaApi provideAlexaApi(Retrofit retrofit) {
        return (AlexaApi) Preconditions.checkNotNullFromProvides(AlexaModule.provideAlexaApi(retrofit));
    }
}
