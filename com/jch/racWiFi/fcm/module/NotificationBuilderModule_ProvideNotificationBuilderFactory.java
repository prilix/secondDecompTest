package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NotificationBuilderModule_ProvideNotificationBuilderFactory implements Factory<NotificationBuilder> {
    private final Provider<Application> applicationProvider;

    public NotificationBuilderModule_ProvideNotificationBuilderFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public NotificationBuilder get() {
        return provideNotificationBuilder(this.applicationProvider.get());
    }

    public static NotificationBuilderModule_ProvideNotificationBuilderFactory create(Provider<Application> provider) {
        return new NotificationBuilderModule_ProvideNotificationBuilderFactory(provider);
    }

    public static NotificationBuilder provideNotificationBuilder(Application application) {
        return (NotificationBuilder) Preconditions.checkNotNullFromProvides(NotificationBuilderModule.provideNotificationBuilder(application));
    }
}
