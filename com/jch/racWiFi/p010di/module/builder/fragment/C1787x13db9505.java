package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.settings.view.CustomerCareFragmentGlobal;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {CustomerCareFragmentGlobalSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeCustomerCareFragmentGlobal */
public abstract class C1787x13db9505 {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeCustomerCareFragmentGlobal$CustomerCareFragmentGlobalSubcomponent */
    public interface CustomerCareFragmentGlobalSubcomponent extends AndroidInjector<CustomerCareFragmentGlobal> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeCustomerCareFragmentGlobal$CustomerCareFragmentGlobalSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<CustomerCareFragmentGlobal> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(CustomerCareFragmentGlobal.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(CustomerCareFragmentGlobalSubcomponent.Factory factory);

    private C1787x13db9505() {
    }
}
