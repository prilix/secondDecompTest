package com.jch.racWiFi.main.module;

import com.jch.racWiFi.main.api.BaseMainApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Module
public class BaseMainModule {
    @Singleton
    @Provides
    static BaseMainApi provideRefreshTokenApi(Retrofit retrofit) {
        return (BaseMainApi) retrofit.create(BaseMainApi.class);
    }
}
