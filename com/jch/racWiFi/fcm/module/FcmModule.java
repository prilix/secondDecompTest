package com.jch.racWiFi.fcm.module;

import com.jch.racWiFi.p010di.api.FcmApi;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FcmModule {
    @BaseScope
    @Provides
    static FcmApi provideFcmApi(Retrofit retrofit) {
        return (FcmApi) retrofit.create(FcmApi.class);
    }
}
