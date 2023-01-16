package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.linking.amazon.activity.AlexaActivity;
import com.jch.racWiFi.linking.amazon.module.AlexaModule;
import com.jch.racWiFi.linking.google.module.AppFlipModule;
import com.jch.racWiFi.main.module.MainModule;
import com.jch.racWiFi.main.view_model.module.MainModelsModule;
import com.jch.racWiFi.p010di.module.view_model.module.AlexaViewModelsModule;
import com.jch.racWiFi.p010di.module.view_model.module.AppFlipViewModelsModule;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AlexaActivitySubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeAlexaActivity */
public abstract class ActivityBuilderModule_ContributeAlexaActivity {

    @Subcomponent(modules = {MainModelsModule.class, MainModule.class, AlexaFragmentBuilderModule.class, AlexaViewModelsModule.class, AlexaModule.class, AppFlipViewModelsModule.class, AppFlipModule.class})
    @BaseScope
    /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeAlexaActivity$AlexaActivitySubcomponent */
    public interface AlexaActivitySubcomponent extends AndroidInjector<AlexaActivity> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeAlexaActivity$AlexaActivitySubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<AlexaActivity> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(AlexaActivity.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(AlexaActivitySubcomponent.Factory factory);

    private ActivityBuilderModule_ContributeAlexaActivity() {
    }
}
