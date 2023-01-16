package com.jch.racWiFi.amplitude.module;

import android.app.Application;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AmplitudeUtilModule_ProvideAmplitudeUtilFactory implements Factory<AmplitudeUtil> {
    private final Provider<Application> applicationProvider;

    public AmplitudeUtilModule_ProvideAmplitudeUtilFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public AmplitudeUtil get() {
        return provideAmplitudeUtil(this.applicationProvider.get());
    }

    public static AmplitudeUtilModule_ProvideAmplitudeUtilFactory create(Provider<Application> provider) {
        return new AmplitudeUtilModule_ProvideAmplitudeUtilFactory(provider);
    }

    public static AmplitudeUtil provideAmplitudeUtil(Application application) {
        return (AmplitudeUtil) Preconditions.checkNotNullFromProvides(AmplitudeUtilModule.provideAmplitudeUtil(application));
    }
}
