package com.jch.racWiFi.p010di.module.base;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.ConnectionSpec;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideConnectionSpecFactory */
public final class BaseModule_ProvideConnectionSpecFactory implements Factory<ConnectionSpec> {
    public ConnectionSpec get() {
        return provideConnectionSpec();
    }

    public static BaseModule_ProvideConnectionSpecFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ConnectionSpec provideConnectionSpec() {
        return (ConnectionSpec) Preconditions.checkNotNullFromProvides(BaseModule.provideConnectionSpec());
    }

    /* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideConnectionSpecFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final BaseModule_ProvideConnectionSpecFactory INSTANCE = new BaseModule_ProvideConnectionSpecFactory();

        private InstanceHolder() {
        }
    }
}
