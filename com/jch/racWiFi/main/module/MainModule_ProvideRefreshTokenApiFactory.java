package com.jch.racWiFi.main.module;

import com.jch.racWiFi.main.api.MainApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class MainModule_ProvideRefreshTokenApiFactory implements Factory<MainApi> {
    private final Provider<Retrofit> retrofitProvider;

    public MainModule_ProvideRefreshTokenApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public MainApi get() {
        return provideRefreshTokenApi(this.retrofitProvider.get());
    }

    public static MainModule_ProvideRefreshTokenApiFactory create(Provider<Retrofit> provider) {
        return new MainModule_ProvideRefreshTokenApiFactory(provider);
    }

    public static MainApi provideRefreshTokenApi(Retrofit retrofit) {
        return (MainApi) Preconditions.checkNotNullFromProvides(MainModule.provideRefreshTokenApi(retrofit));
    }
}
