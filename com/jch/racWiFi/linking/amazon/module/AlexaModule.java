package com.jch.racWiFi.linking.amazon.module;

import com.jch.racWiFi.p010di.api.AlexaApi;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AlexaModule {
    @BaseScope
    @Provides
    static AlexaApi provideAlexaApi(Retrofit retrofit) {
        return (AlexaApi) retrofit.create(AlexaApi.class);
    }
}
