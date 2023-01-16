package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep4Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {CreateAccountStep4FragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep4Fragment */
public abstract class C1790xaf779aaa {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep4Fragment$CreateAccountStep4FragmentSubcomponent */
    public interface CreateAccountStep4FragmentSubcomponent extends AndroidInjector<CreateAccountStep4Fragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeCreateAccountStep4Fragment$CreateAccountStep4FragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<CreateAccountStep4Fragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(CreateAccountStep4Fragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(CreateAccountStep4FragmentSubcomponent.Factory factory);

    private C1790xaf779aaa() {
    }
}
