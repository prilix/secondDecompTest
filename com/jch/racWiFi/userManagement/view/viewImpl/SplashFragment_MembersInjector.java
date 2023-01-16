package com.jch.racWiFi.userManagement.view.viewImpl;

import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SplashFragment_MembersInjector implements MembersInjector<SplashFragment> {
    private final Provider<Binder> mBinderProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public SplashFragment_MembersInjector(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        this.mBinderProvider = provider;
        this.providerFactoryProvider = provider2;
    }

    public static MembersInjector<SplashFragment> create(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        return new SplashFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(SplashFragment splashFragment) {
        injectMBinder(splashFragment, this.mBinderProvider.get());
        injectProviderFactory(splashFragment, this.providerFactoryProvider.get());
    }

    public static void injectMBinder(SplashFragment splashFragment, Binder binder) {
        splashFragment.mBinder = binder;
    }

    public static void injectProviderFactory(SplashFragment splashFragment, ViewModelProviderFactory viewModelProviderFactory) {
        splashFragment.providerFactory = viewModelProviderFactory;
    }
}
