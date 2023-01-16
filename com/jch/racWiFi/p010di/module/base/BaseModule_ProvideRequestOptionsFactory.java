package com.jch.racWiFi.p010di.module.base;

import com.bumptech.glide.request.RequestOptions;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideRequestOptionsFactory */
public final class BaseModule_ProvideRequestOptionsFactory implements Factory<RequestOptions> {
    public RequestOptions get() {
        return provideRequestOptions();
    }

    public static BaseModule_ProvideRequestOptionsFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RequestOptions provideRequestOptions() {
        return (RequestOptions) Preconditions.checkNotNullFromProvides(BaseModule.provideRequestOptions());
    }

    /* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideRequestOptionsFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final BaseModule_ProvideRequestOptionsFactory INSTANCE = new BaseModule_ProvideRequestOptionsFactory();

        private InstanceHolder() {
        }
    }
}
