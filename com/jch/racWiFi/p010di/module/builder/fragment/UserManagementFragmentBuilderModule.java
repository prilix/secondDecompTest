package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep4Fragment;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import com.jch.racWiFi.userManagement.view.viewImpl.LoginFragment;
import com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule */
public abstract class UserManagementFragmentBuilderModule {
    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract CreateAccountStep4Fragment contributeCreateAccountStep4Fragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract CreateAccountStep5SuccessFragment contributeCreateAccountStep5SuccessFragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract LoginFragment contributeLoginFragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract SplashFragment contributeSplashFragment();
}
