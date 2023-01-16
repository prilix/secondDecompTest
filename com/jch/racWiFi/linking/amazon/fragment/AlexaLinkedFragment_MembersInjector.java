package com.jch.racWiFi.linking.amazon.fragment;

import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AlexaLinkedFragment_MembersInjector implements MembersInjector<AlexaLinkedFragment> {
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public AlexaLinkedFragment_MembersInjector(Provider<ViewModelProviderFactory> provider) {
        this.providerFactoryProvider = provider;
    }

    public static MembersInjector<AlexaLinkedFragment> create(Provider<ViewModelProviderFactory> provider) {
        return new AlexaLinkedFragment_MembersInjector(provider);
    }

    public void injectMembers(AlexaLinkedFragment alexaLinkedFragment) {
        injectProviderFactory(alexaLinkedFragment, this.providerFactoryProvider.get());
    }

    public static void injectProviderFactory(AlexaLinkedFragment alexaLinkedFragment, ViewModelProviderFactory viewModelProviderFactory) {
        alexaLinkedFragment.providerFactory = viewModelProviderFactory;
    }
}
