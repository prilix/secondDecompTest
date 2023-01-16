package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class NotificationBuilderModule {
    @Singleton
    @Provides
    static NotificationBuilder provideNotificationBuilder(Application application) {
        return new NotificationBuilder(application);
    }
}
