package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.fcm.module.BinderModule;
import com.jch.racWiFi.fcm.module.FcmModule;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.linking.amazon.activity.AlexaActivity;
import com.jch.racWiFi.linking.amazon.module.AlexaModule;
import com.jch.racWiFi.linking.google.module.AppFlipModule;
import com.jch.racWiFi.main.module.MainModule;
import com.jch.racWiFi.main.view_model.module.MainModelsModule;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule;
import com.jch.racWiFi.p010di.module.builder.fragment.UserManagementFragmentBuilderModule;
import com.jch.racWiFi.p010di.module.view_model.module.AlexaViewModelsModule;
import com.jch.racWiFi.p010di.module.view_model.module.AppFlipViewModelsModule;
import com.jch.racWiFi.p010di.module.view_model.module.FcmViewModelsModule;
import com.jch.racWiFi.p010di.scope.BaseScope;
import com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule */
public abstract class ActivityBuilderModule {
    /* access modifiers changed from: package-private */
    @BaseScope
    @ContributesAndroidInjector(modules = {MainModelsModule.class, MainModule.class, AlexaFragmentBuilderModule.class, AlexaViewModelsModule.class, AlexaModule.class, AppFlipViewModelsModule.class, AppFlipModule.class})
    public abstract AlexaActivity contributeAlexaActivity();

    /* access modifiers changed from: package-private */
    @BaseScope
    @ContributesAndroidInjector(modules = {MainModelsModule.class, MainModule.class})
    public abstract CoreActivity contributeCoreActivity();

    /* access modifiers changed from: package-private */
    @BaseScope
    @ContributesAndroidInjector(modules = {MainModelsModule.class, MainModule.class, HomePageFragmentBuilderModule.class, FcmViewModelsModule.class, AlexaViewModelsModule.class, FcmModule.class, AlexaModule.class, BinderModule.class})
    public abstract HomePageActivity contributeHomePageActivity();

    /* access modifiers changed from: package-private */
    @BaseScope
    @ContributesAndroidInjector(modules = {MainModelsModule.class, MainModule.class, UserManagementFragmentBuilderModule.class, BinderModule.class})
    public abstract UserManagementActivity contributeUserManagementActivity();
}
