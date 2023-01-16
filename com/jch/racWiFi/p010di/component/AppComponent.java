package com.jch.racWiFi.p010di.component;

import android.app.Application;
import com.jch.racWiFi.App;
import com.jch.racWiFi.amplitude.module.AmplitudeUtilModule;
import com.jch.racWiFi.amplitude.module.ClientFactoryModule;
import com.jch.racWiFi.fcm.module.DeepLinkFactoryModule;
import com.jch.racWiFi.fcm.module.ModelRepositoryModule;
import com.jch.racWiFi.fcm.module.NotificationBuilderModule;
import com.jch.racWiFi.main.module.BaseMainModule;
import com.jch.racWiFi.p010di.module.base.BaseModule;
import com.jch.racWiFi.p010di.module.builder.ActivityBuilderModule;
import com.jch.racWiFi.p010di.module.builder.ServiceBuilderModule;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelFactoryModule;
import com.jch.racWiFi.util.module.JCIAlertDialogModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Component(modules = {AndroidSupportInjectionModule.class, ViewModelFactoryModule.class, ActivityBuilderModule.class, ServiceBuilderModule.class, ModelRepositoryModule.class, DeepLinkFactoryModule.class, NotificationBuilderModule.class, AmplitudeUtilModule.class, ClientFactoryModule.class, JCIAlertDialogModule.class, BaseModule.class, BaseMainModule.class})
@Singleton
/* renamed from: com.jch.racWiFi.di.component.AppComponent */
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    /* renamed from: com.jch.racWiFi.di.component.AppComponent$Builder */
    public interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
