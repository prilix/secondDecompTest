package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ModelRepositoryModule_ProvideModelRepositoryFactory implements Factory<ModelRepository> {
    private final Provider<Application> applicationProvider;

    public ModelRepositoryModule_ProvideModelRepositoryFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public ModelRepository get() {
        return provideModelRepository(this.applicationProvider.get());
    }

    public static ModelRepositoryModule_ProvideModelRepositoryFactory create(Provider<Application> provider) {
        return new ModelRepositoryModule_ProvideModelRepositoryFactory(provider);
    }

    public static ModelRepository provideModelRepository(Application application) {
        return (ModelRepository) Preconditions.checkNotNullFromProvides(ModelRepositoryModule.provideModelRepository(application));
    }
}
