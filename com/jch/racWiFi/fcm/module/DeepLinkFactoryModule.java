package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DeepLinkFactoryModule {
    @Singleton
    @Provides
    static DeepLinkFactory provideDeepLinkFactory(Application application) {
        return new DeepLinkFactory(application);
    }
}
