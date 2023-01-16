package com.jch.racWiFi.linking.google.module;

import com.jch.racWiFi.p010di.api.AppFlipApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class AppFlipModule_ProvideAppFlipApiFactory implements Factory<AppFlipApi> {
    private final Provider<Retrofit> retrofitProvider;

    public AppFlipModule_ProvideAppFlipApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public AppFlipApi get() {
        return provideAppFlipApi(this.retrofitProvider.get());
    }

    public static AppFlipModule_ProvideAppFlipApiFactory create(Provider<Retrofit> provider) {
        return new AppFlipModule_ProvideAppFlipApiFactory(provider);
    }

    public static AppFlipApi provideAppFlipApi(Retrofit retrofit) {
        return (AppFlipApi) Preconditions.checkNotNullFromProvides(AppFlipModule.provideAppFlipApi(retrofit));
    }
}
