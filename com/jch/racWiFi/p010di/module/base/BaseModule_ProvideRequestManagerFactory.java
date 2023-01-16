package com.jch.racWiFi.p010di.module.base;

import android.app.Application;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideRequestManagerFactory */
public final class BaseModule_ProvideRequestManagerFactory implements Factory<RequestManager> {
    private final Provider<Application> applicationProvider;
    private final Provider<RequestOptions> requestOptionsProvider;

    public BaseModule_ProvideRequestManagerFactory(Provider<Application> provider, Provider<RequestOptions> provider2) {
        this.applicationProvider = provider;
        this.requestOptionsProvider = provider2;
    }

    public RequestManager get() {
        return provideRequestManager(this.applicationProvider.get(), this.requestOptionsProvider.get());
    }

    public static BaseModule_ProvideRequestManagerFactory create(Provider<Application> provider, Provider<RequestOptions> provider2) {
        return new BaseModule_ProvideRequestManagerFactory(provider, provider2);
    }

    public static RequestManager provideRequestManager(Application application, RequestOptions requestOptions) {
        return (RequestManager) Preconditions.checkNotNullFromProvides(BaseModule.provideRequestManager(application, requestOptions));
    }
}
