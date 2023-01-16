package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.fcm.services.FcmService;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FcmServiceSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeFcmService */
public abstract class ServiceBuilderModule_ContributeFcmService {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeFcmService$FcmServiceSubcomponent */
    public interface FcmServiceSubcomponent extends AndroidInjector<FcmService> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeFcmService$FcmServiceSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<FcmService> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(FcmService.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(FcmServiceSubcomponent.Factory factory);

    private ServiceBuilderModule_ContributeFcmService() {
    }
}
