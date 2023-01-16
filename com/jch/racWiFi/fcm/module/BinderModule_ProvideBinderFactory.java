package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.util.Binder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class BinderModule_ProvideBinderFactory implements Factory<Binder> {
    private final Provider<Application> applicationProvider;

    public BinderModule_ProvideBinderFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public Binder get() {
        return provideBinder(this.applicationProvider.get());
    }

    public static BinderModule_ProvideBinderFactory create(Provider<Application> provider) {
        return new BinderModule_ProvideBinderFactory(provider);
    }

    public static Binder provideBinder(Application application) {
        return (Binder) Preconditions.checkNotNullFromProvides(BinderModule.provideBinder(application));
    }
}
