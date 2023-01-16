package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.main.module.MainModule;
import com.jch.racWiFi.main.view_model.module.MainModelsModule;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {CoreActivitySubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeCoreActivity */
public abstract class ActivityBuilderModule_ContributeCoreActivity {

    @Subcomponent(modules = {MainModelsModule.class, MainModule.class})
    @BaseScope
    /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeCoreActivity$CoreActivitySubcomponent */
    public interface CoreActivitySubcomponent extends AndroidInjector<CoreActivity> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeCoreActivity$CoreActivitySubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<CoreActivity> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(CoreActivity.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(CoreActivitySubcomponent.Factory factory);

    private ActivityBuilderModule_ContributeCoreActivity() {
    }
}
