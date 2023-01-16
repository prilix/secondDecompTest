package com.jch.racWiFi.linking.google.module;

import com.jch.racWiFi.p010di.api.AppFlipApi;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppFlipModule {
    @BaseScope
    @Provides
    static AppFlipApi provideAppFlipApi(Retrofit retrofit) {
        return (AppFlipApi) retrofit.create(AppFlipApi.class);
    }
}
