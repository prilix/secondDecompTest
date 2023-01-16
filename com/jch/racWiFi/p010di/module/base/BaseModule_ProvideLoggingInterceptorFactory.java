package com.jch.racWiFi.p010di.module.base;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.logging.HttpLoggingInterceptor;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideLoggingInterceptorFactory */
public final class BaseModule_ProvideLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
    public HttpLoggingInterceptor get() {
        return provideLoggingInterceptor();
    }

    public static BaseModule_ProvideLoggingInterceptorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static HttpLoggingInterceptor provideLoggingInterceptor() {
        return (HttpLoggingInterceptor) Preconditions.checkNotNullFromProvides(BaseModule.provideLoggingInterceptor());
    }

    /* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideLoggingInterceptorFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final BaseModule_ProvideLoggingInterceptorFactory INSTANCE = new BaseModule_ProvideLoggingInterceptorFactory();

        private InstanceHolder() {
        }
    }
}
