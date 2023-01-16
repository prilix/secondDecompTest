package com.jch.racWiFi.amplitude.module;

import android.app.Application;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ClientFactoryModule {
    @Singleton
    @Provides
    static ClientFactory provideClientFactory(Application application) {
        return new ClientFactory(application);
    }
}
