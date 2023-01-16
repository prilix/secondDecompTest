package com.jch.racWiFi.iduManagement.view.viewImpl;

import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IndividualIDUControlFragmentModelWise_MembersInjector implements MembersInjector<IndividualIDUControlFragmentModelWise> {
    private final Provider<Binder> mBinderProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public IndividualIDUControlFragmentModelWise_MembersInjector(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        this.mBinderProvider = provider;
        this.providerFactoryProvider = provider2;
    }

    public static MembersInjector<IndividualIDUControlFragmentModelWise> create(Provider<Binder> provider, Provider<ViewModelProviderFactory> provider2) {
        return new IndividualIDUControlFragmentModelWise_MembersInjector(provider, provider2);
    }

    public void injectMembers(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise) {
        injectMBinder(individualIDUControlFragmentModelWise, this.mBinderProvider.get());
        injectProviderFactory(individualIDUControlFragmentModelWise, this.providerFactoryProvider.get());
    }

    public static void injectMBinder(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, Binder binder) {
        individualIDUControlFragmentModelWise.mBinder = binder;
    }

    public static void injectProviderFactory(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, ViewModelProviderFactory viewModelProviderFactory) {
        individualIDUControlFragmentModelWise.providerFactory = viewModelProviderFactory;
    }
}
