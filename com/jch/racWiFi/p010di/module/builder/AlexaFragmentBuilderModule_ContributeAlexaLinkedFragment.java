package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AlexaLinkedFragmentSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment */
public abstract class AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment$AlexaLinkedFragmentSubcomponent */
    public interface AlexaLinkedFragmentSubcomponent extends AndroidInjector<AlexaLinkedFragment> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment$AlexaLinkedFragmentSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<AlexaLinkedFragment> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(AlexaLinkedFragment.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(AlexaLinkedFragmentSubcomponent.Factory factory);

    private AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment() {
    }
}
