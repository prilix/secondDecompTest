package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.amplitude.util.AppEventService;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AppEventServiceSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeAppEventService */
public abstract class ServiceBuilderModule_ContributeAppEventService {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeAppEventService$AppEventServiceSubcomponent */
    public interface AppEventServiceSubcomponent extends AndroidInjector<AppEventService> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeAppEventService$AppEventServiceSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<AppEventService> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(AppEventService.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(AppEventServiceSubcomponent.Factory factory);

    private ServiceBuilderModule_ContributeAppEventService() {
    }
}
