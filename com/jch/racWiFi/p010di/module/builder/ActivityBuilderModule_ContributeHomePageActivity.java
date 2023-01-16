package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.fcm.module.BinderModule;
import com.jch.racWiFi.fcm.module.FcmModule;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.linking.amazon.module.AlexaModule;
import com.jch.racWiFi.main.module.MainModule;
import com.jch.racWiFi.main.view_model.module.MainModelsModule;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule;
import com.jch.racWiFi.p010di.module.view_model.module.AlexaViewModelsModule;
import com.jch.racWiFi.p010di.module.view_model.module.FcmViewModelsModule;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {HomePageActivitySubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeHomePageActivity */
public abstract class ActivityBuilderModule_ContributeHomePageActivity {

    @Subcomponent(modules = {MainModelsModule.class, MainModule.class, HomePageFragmentBuilderModule.class, FcmViewModelsModule.class, AlexaViewModelsModule.class, FcmModule.class, AlexaModule.class, BinderModule.class})
    @BaseScope
    /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeHomePageActivity$HomePageActivitySubcomponent */
    public interface HomePageActivitySubcomponent extends AndroidInjector<HomePageActivity> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeHomePageActivity$HomePageActivitySubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<HomePageActivity> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(HomePageActivity.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(HomePageActivitySubcomponent.Factory factory);

    private ActivityBuilderModule_ContributeHomePageActivity() {
    }
}
