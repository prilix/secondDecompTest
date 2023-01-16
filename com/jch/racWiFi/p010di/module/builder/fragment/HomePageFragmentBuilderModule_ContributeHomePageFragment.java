package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {HomePageFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHomePageFragment */
public abstract class HomePageFragmentBuilderModule_ContributeHomePageFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHomePageFragment$HomePageFragmentSubcomponent */
    public interface HomePageFragmentSubcomponent extends AndroidInjector<HomePageFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHomePageFragment$HomePageFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<HomePageFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(HomePageFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(HomePageFragmentSubcomponent.Factory factory);

    private HomePageFragmentBuilderModule_ContributeHomePageFragment() {
    }
}
