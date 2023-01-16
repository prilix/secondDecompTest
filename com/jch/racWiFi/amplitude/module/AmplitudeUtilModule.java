package com.jch.racWiFi.amplitude.module;

import android.app.Application;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AmplitudeUtilModule {
    @Singleton
    @Provides
    static AmplitudeUtil provideAmplitudeUtil(Application application) {
        return new AmplitudeUtil(application);
    }
}
