package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment;
import com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise;
import com.jch.racWiFi.settings.view.CustomerCareFragmentGlobal;
import com.jch.racWiFi.settings.view.HelpFragmentGlobal;
import com.jch.racWiFi.settings.view.ServiceRequestFragment;
import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment;
import com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule */
public abstract class HomePageFragmentBuilderModule {
    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract CustomerCareFragmentGlobal contributeCustomerCareFragmentGlobal();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract HelpFragmentGlobal contributeHelpFragmentGlobal();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract HomePageFragment contributeHomePageFragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract IndividualIDUControlFragmentModelWise contributeIndividualIDUControlFragmentModelWise();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract MyAccountAddressFragment contributeMyAccountAddressFragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract MyAccountProfilePictureFragmentV3 contributeMyAccountProfilePictureFragmentV3();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract ServiceRequestFragment contributeServiceRequestFragment();
}
