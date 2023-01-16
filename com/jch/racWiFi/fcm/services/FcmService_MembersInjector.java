package com.jch.racWiFi.fcm.services;

import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class FcmService_MembersInjector implements MembersInjector<FcmService> {
    private final Provider<DeepLinkFactory> mDeepLinkFactoryProvider;
    private final Provider<NotificationBuilder> mNotificationBuilderProvider;
    private final Provider<ModelRepository> modelRepositoryProvider;

    public FcmService_MembersInjector(Provider<ModelRepository> provider, Provider<NotificationBuilder> provider2, Provider<DeepLinkFactory> provider3) {
        this.modelRepositoryProvider = provider;
        this.mNotificationBuilderProvider = provider2;
        this.mDeepLinkFactoryProvider = provider3;
    }

    public static MembersInjector<FcmService> create(Provider<ModelRepository> provider, Provider<NotificationBuilder> provider2, Provider<DeepLinkFactory> provider3) {
        return new FcmService_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(FcmService fcmService) {
        injectModelRepository(fcmService, this.modelRepositoryProvider.get());
        injectMNotificationBuilder(fcmService, this.mNotificationBuilderProvider.get());
        injectMDeepLinkFactory(fcmService, this.mDeepLinkFactoryProvider.get());
    }

    public static void injectModelRepository(FcmService fcmService, ModelRepository modelRepository) {
        fcmService.modelRepository = modelRepository;
    }

    public static void injectMNotificationBuilder(FcmService fcmService, NotificationBuilder notificationBuilder) {
        fcmService.mNotificationBuilder = notificationBuilder;
    }

    public static void injectMDeepLinkFactory(FcmService fcmService, DeepLinkFactory deepLinkFactory) {
        fcmService.mDeepLinkFactory = deepLinkFactory;
    }
}
