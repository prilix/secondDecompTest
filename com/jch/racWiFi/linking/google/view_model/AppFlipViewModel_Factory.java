package com.jch.racWiFi.linking.google.view_model;

import com.jch.racWiFi.p010di.api.AppFlipApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppFlipViewModel_Factory implements Factory<AppFlipViewModel> {
    private final Provider<AppFlipApi> appFlipApiProvider;

    public AppFlipViewModel_Factory(Provider<AppFlipApi> provider) {
        this.appFlipApiProvider = provider;
    }

    public AppFlipViewModel get() {
        return newInstance(this.appFlipApiProvider.get());
    }

    public static AppFlipViewModel_Factory create(Provider<AppFlipApi> provider) {
        return new AppFlipViewModel_Factory(provider);
    }

    public static AppFlipViewModel newInstance(AppFlipApi appFlipApi) {
        return new AppFlipViewModel(appFlipApi);
    }
}
