package com.jch.racWiFi.linking.amazon.fragment;

import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AlexaLinkFragment_MembersInjector implements MembersInjector<AlexaLinkFragment> {
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public AlexaLinkFragment_MembersInjector(Provider<ViewModelProviderFactory> provider) {
        this.providerFactoryProvider = provider;
    }

    public static MembersInjector<AlexaLinkFragment> create(Provider<ViewModelProviderFactory> provider) {
        return new AlexaLinkFragment_MembersInjector(provider);
    }

    public void injectMembers(AlexaLinkFragment alexaLinkFragment) {
        injectProviderFactory(alexaLinkFragment, this.providerFactoryProvider.get());
    }

    public static void injectProviderFactory(AlexaLinkFragment alexaLinkFragment, ViewModelProviderFactory viewModelProviderFactory) {
        alexaLinkFragment.providerFactory = viewModelProviderFactory;
    }
}
