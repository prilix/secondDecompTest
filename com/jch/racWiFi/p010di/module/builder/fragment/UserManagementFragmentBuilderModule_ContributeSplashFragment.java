package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SplashFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeSplashFragment */
public abstract class UserManagementFragmentBuilderModule_ContributeSplashFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeSplashFragment$SplashFragmentSubcomponent */
    public interface SplashFragmentSubcomponent extends AndroidInjector<SplashFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeSplashFragment$SplashFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<SplashFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(SplashFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(SplashFragmentSubcomponent.Factory factory);

    private UserManagementFragmentBuilderModule_ContributeSplashFragment() {
    }
}
