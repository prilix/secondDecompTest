package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ModelRepositoryModule {
    @Singleton
    @Provides
    static ModelRepository provideModelRepository(Application application) {
        return new ModelRepository(application);
    }
}
