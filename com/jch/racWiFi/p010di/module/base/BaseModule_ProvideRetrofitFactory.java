package com.jch.racWiFi.p010di.module.base;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideRetrofitFactory */
public final class BaseModule_ProvideRetrofitFactory implements Factory<Retrofit> {
    private final Provider<OkHttpClient.Builder> httpClientProvider;

    public BaseModule_ProvideRetrofitFactory(Provider<OkHttpClient.Builder> provider) {
        this.httpClientProvider = provider;
    }

    public Retrofit get() {
        return provideRetrofit(this.httpClientProvider.get());
    }

    public static BaseModule_ProvideRetrofitFactory create(Provider<OkHttpClient.Builder> provider) {
        return new BaseModule_ProvideRetrofitFactory(provider);
    }

    public static Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        return (Retrofit) Preconditions.checkNotNullFromProvides(BaseModule.provideRetrofit(builder));
    }
}
