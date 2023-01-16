package com.jch.racWiFi.iduManagement.view.viewImpl;

import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HomePageFragment_MembersInjector implements MembersInjector<HomePageFragment> {
    private final Provider<Binder> mBinderProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public HomePageFragment_MembersInjector(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        this.mBinderProvider = provider;
        this.providerFactoryProvider = provider2;
    }

    public static MembersInjector<HomePageFragment> create(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        return new HomePageFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(HomePageFragment homePageFragment) {
        injectMBinder(homePageFragment, this.mBinderProvider.get());
        injectProviderFactory(homePageFragment, this.providerFactoryProvider.get());
    }

    public static void injectMBinder(HomePageFragment homePageFragment, Binder binder) {
        homePageFragment.mBinder = binder;
    }

    public static void injectProviderFactory(HomePageFragment homePageFragment, ViewModelProviderFactory viewModelProviderFactory) {
        homePageFragment.providerFactory = viewModelProviderFactory;
    }
}
