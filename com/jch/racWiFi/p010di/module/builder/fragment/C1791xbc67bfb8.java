package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {CreateAccountStep5SuccessFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep5SuccessFragment */
public abstract class C1791xbc67bfb8 {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep5SuccessFragment$CreateAccountStep5SuccessFragmentSubcomponent */
    public interface CreateAccountStep5SuccessFragmentSubcomponent extends AndroidInjector<CreateAccountStep5SuccessFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep5SuccessFragment$CreateAccountStep5SuccessFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<CreateAccountStep5SuccessFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(CreateAccountStep5SuccessFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(CreateAccountStep5SuccessFragmentSubcomponent.Factory factory);

    private C1791xbc67bfb8() {
    }
}
