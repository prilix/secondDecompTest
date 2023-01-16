package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MyAccountAddressFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment */
public abstract class HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment$MyAccountAddressFragmentSubcomponent */
    public interface MyAccountAddressFragmentSubcomponent extends AndroidInjector<MyAccountAddressFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment$MyAccountAddressFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<MyAccountAddressFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(MyAccountAddressFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(MyAccountAddressFragmentSubcomponent.Factory factory);

    private HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment() {
    }
}
