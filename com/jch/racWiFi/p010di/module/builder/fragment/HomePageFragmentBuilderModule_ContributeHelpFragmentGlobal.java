package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.settings.view.HelpFragmentGlobal;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {HelpFragmentGlobalSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal */
public abstract class HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal$HelpFragmentGlobalSubcomponent */
    public interface HelpFragmentGlobalSubcomponent extends AndroidInjector<HelpFragmentGlobal> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal$HelpFragmentGlobalSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<HelpFragmentGlobal> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(HelpFragmentGlobal.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(HelpFragmentGlobalSubcomponent.Factory factory);

    private HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal() {
    }
}
