package com.jch.racWiFi.fcm.module;

import com.jch.racWiFi.p010di.api.FcmApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class FcmModule_ProvideFcmApiFactory implements Factory<FcmApi> {
    private final Provider<Retrofit> retrofitProvider;

    public FcmModule_ProvideFcmApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public FcmApi get() {
        return provideFcmApi(this.retrofitProvider.get());
    }

    public static FcmModule_ProvideFcmApiFactory create(Provider<Retrofit> provider) {
        return new FcmModule_ProvideFcmApiFactory(provider);
    }

    public static FcmApi provideFcmApi(Retrofit retrofit) {
        return (FcmApi) Preconditions.checkNotNullFromProvides(FcmModule.provideFcmApi(retrofit));
    }
}
