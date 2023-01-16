package com.jch.racWiFi.main.module;

import com.jch.racWiFi.main.api.BaseMainApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class BaseMainModule_ProvideRefreshTokenApiFactory implements Factory<BaseMainApi> {
    private final Provider<Retrofit> retrofitProvider;

    public BaseMainModule_ProvideRefreshTokenApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public BaseMainApi get() {
        return provideRefreshTokenApi(this.retrofitProvider.get());
    }

    public static BaseMainModule_ProvideRefreshTokenApiFactory create(Provider<Retrofit> provider) {
        return new BaseMainModule_ProvideRefreshTokenApiFactory(provider);
    }

    public static BaseMainApi provideRefreshTokenApi(Retrofit retrofit) {
        return (BaseMainApi) Preconditions.checkNotNullFromProvides(BaseMainModule.provideRefreshTokenApi(retrofit));
    }
}
