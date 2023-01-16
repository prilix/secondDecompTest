package com.jch.racWiFi.p010di.module.view_model.factory;

import androidx.lifecycle.C0534ViewModel;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

/* renamed from: com.jch.racWiFi.di.module.view_model.factory.ViewModelProviderFactory_Factory */
public final class ViewModelProviderFactory_Factory implements Factory<ViewModelProviderFactory> {
    private final Provider<Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>>> creatorsProvider;

    public ViewModelProviderFactory_Factory(Provider<Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>>> provider) {
        this.creatorsProvider = provider;
    }

    public ViewModelProviderFactory get() {
        return newInstance(this.creatorsProvider.get());
    }

    public static ViewModelProviderFactory_Factory create(Provider<Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>>> provider) {
        return new ViewModelProviderFactory_Factory(provider);
    }

    public static ViewModelProviderFactory newInstance(Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> map) {
        return new ViewModelProviderFactory(map);
    }
}
