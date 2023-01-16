package com.jch.racWiFi.linking.amazon.view_model;

import com.jch.racWiFi.p010di.api.AlexaApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AlexaViewModel_Factory implements Factory<AlexaViewModel> {
    private final Provider<AlexaApi> alexaApiProvider;

    public AlexaViewModel_Factory(Provider<AlexaApi> provider) {
        this.alexaApiProvider = provider;
    }

    public AlexaViewModel get() {
        return newInstance(this.alexaApiProvider.get());
    }

    public static AlexaViewModel_Factory create(Provider<AlexaApi> provider) {
        return new AlexaViewModel_Factory(provider);
    }

    public static AlexaViewModel newInstance(AlexaApi alexaApi) {
        return new AlexaViewModel(alexaApi);
    }
}
