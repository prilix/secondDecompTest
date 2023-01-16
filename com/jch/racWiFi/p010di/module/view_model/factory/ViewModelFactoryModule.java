package com.jch.racWiFi.p010di.module.view_model.factory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

@Module
/* renamed from: com.jch.racWiFi.di.module.view_model.factory.ViewModelFactoryModule */
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
