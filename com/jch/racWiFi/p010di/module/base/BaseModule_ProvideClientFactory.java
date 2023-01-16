package com.jch.racWiFi.p010di.module.base;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideClientFactory */
public final class BaseModule_ProvideClientFactory implements Factory<OkHttpClient.Builder> {
    private final Provider<Application> applicationProvider;
    private final Provider<HttpLoggingInterceptor> loggingProvider;

    public BaseModule_ProvideClientFactory(Provider<HttpLoggingInterceptor> provider, Provider<Application> provider2) {
        this.loggingProvider = provider;
        this.applicationProvider = provider2;
    }

    public OkHttpClient.Builder get() {
        return provideClient(this.loggingProvider.get(), this.applicationProvider.get());
    }

    public static BaseModule_ProvideClientFactory create(Provider<HttpLoggingInterceptor> provider, Provider<Application> provider2) {
        return new BaseModule_ProvideClientFactory(provider, provider2);
    }

    public static OkHttpClient.Builder provideClient(HttpLoggingInterceptor httpLoggingInterceptor, Application application) {
        return (OkHttpClient.Builder) Preconditions.checkNotNullFromProvides(BaseModule.provideClient(httpLoggingInterceptor, application));
    }
}
