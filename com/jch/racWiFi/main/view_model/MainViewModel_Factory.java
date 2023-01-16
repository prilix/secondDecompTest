package com.jch.racWiFi.main.view_model;

import com.jch.racWiFi.main.api.MainApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MainViewModel_Factory implements Factory<MainViewModel> {
    private final Provider<MainApi> mainApiProvider;

    public MainViewModel_Factory(Provider<MainApi> provider) {
        this.mainApiProvider = provider;
    }

    public MainViewModel get() {
        return newInstance(this.mainApiProvider.get());
    }

    public static MainViewModel_Factory create(Provider<MainApi> provider) {
        return new MainViewModel_Factory(provider);
    }

    public static MainViewModel newInstance(MainApi mainApi) {
        return new MainViewModel(mainApi);
    }
}
