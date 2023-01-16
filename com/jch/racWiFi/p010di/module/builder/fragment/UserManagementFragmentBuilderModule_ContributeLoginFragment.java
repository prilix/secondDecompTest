package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.viewImpl.LoginFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {LoginFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeLoginFragment */
public abstract class UserManagementFragmentBuilderModule_ContributeLoginFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeLoginFragment$LoginFragmentSubcomponent */
    public interface LoginFragmentSubcomponent extends AndroidInjector<LoginFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeLoginFragment$LoginFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<LoginFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(LoginFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(LoginFragmentSubcomponent.Factory factory);

    private UserManagementFragmentBuilderModule_ContributeLoginFragment() {
    }
}
