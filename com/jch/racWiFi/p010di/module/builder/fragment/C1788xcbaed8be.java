package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {IndividualIDUControlFragmentModelWiseSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeIndividualIDUControlFragmentModelWise */
public abstract class C1788xcbaed8be {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeIndividualIDUControlFragmentModelWise$IndividualIDUControlFragmentModelWiseSubcomponent */
    public interface IndividualIDUControlFragmentModelWiseSubcomponent extends AndroidInjector<IndividualIDUControlFragmentModelWise> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeIndividualIDUControlFragmentModelWise$IndividualIDUControlFragmentModelWiseSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<IndividualIDUControlFragmentModelWise> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(IndividualIDUControlFragmentModelWise.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(IndividualIDUControlFragmentModelWiseSubcomponent.Factory factory);

    private C1788xcbaed8be() {
    }
}
