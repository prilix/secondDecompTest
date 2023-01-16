package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.settings.view.ServiceRequestFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ServiceRequestFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeServiceRequestFragment */
public abstract class HomePageFragmentBuilderModule_ContributeServiceRequestFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeServiceRequestFragment$ServiceRequestFragmentSubcomponent */
    public interface ServiceRequestFragmentSubcomponent extends AndroidInjector<ServiceRequestFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeServiceRequestFragment$ServiceRequestFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<ServiceRequestFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(ServiceRequestFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(ServiceRequestFragmentSubcomponent.Factory factory);

    private HomePageFragmentBuilderModule_ContributeServiceRequestFragment() {
    }
}
