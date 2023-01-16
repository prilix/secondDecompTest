package com.jch.racWiFi.main.module;

import com.jch.racWiFi.main.api.MainApi;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {
    @BaseScope
    @Provides
    static MainApi provideRefreshTokenApi(Retrofit retrofit) {
        return (MainApi) retrofit.create(MainApi.class);
    }
}
