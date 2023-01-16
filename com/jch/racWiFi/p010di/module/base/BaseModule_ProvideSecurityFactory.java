package com.jch.racWiFi.p010di.module.base;

import com.jch.algo.Security;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideSecurityFactory */
public final class BaseModule_ProvideSecurityFactory implements Factory<Security> {
    public Security get() {
        return provideSecurity();
    }

    public static BaseModule_ProvideSecurityFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Security provideSecurity() {
        return (Security) Preconditions.checkNotNullFromProvides(BaseModule.provideSecurity());
    }

    /* renamed from: com.jch.racWiFi.di.module.base.BaseModule_ProvideSecurityFactory$InstanceHolder */
    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final BaseModule_ProvideSecurityFactory INSTANCE = new BaseModule_ProvideSecurityFactory();

        private InstanceHolder() {
        }
    }
}
