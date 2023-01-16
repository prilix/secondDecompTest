package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {GeoFenceTransitionNetworkJobServiceSubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeGeoFenceTransitionNetworkJobService */
public abstract class C1786xd20ff6b7 {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeGeoFenceTransitionNetworkJobService$GeoFenceTransitionNetworkJobServiceSubcomponent */
    public interface GeoFenceTransitionNetworkJobServiceSubcomponent extends AndroidInjector<GeoFenceTransitionNetworkJobService> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ServiceBuilderModule_ContributeGeoFenceTransitionNetworkJobService$GeoFenceTransitionNetworkJobServiceSubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<GeoFenceTransitionNetworkJobService> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(GeoFenceTransitionNetworkJobService.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(GeoFenceTransitionNetworkJobServiceSubcomponent.Factory factory);

    private C1786xd20ff6b7() {
    }
}
