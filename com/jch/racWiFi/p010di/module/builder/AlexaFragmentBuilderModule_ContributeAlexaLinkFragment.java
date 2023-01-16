package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AlexaLinkFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkFragment */
public abstract class AlexaFragmentBuilderModule_ContributeAlexaLinkFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkFragment$AlexaLinkFragmentSubcomponent */
    public interface AlexaLinkFragmentSubcomponent extends AndroidInjector<AlexaLinkFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkFragment$AlexaLinkFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<AlexaLinkFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(AlexaLinkFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(AlexaLinkFragmentSubcomponent.Factory factory);

    private AlexaFragmentBuilderModule_ContributeAlexaLinkFragment() {
    }
}
