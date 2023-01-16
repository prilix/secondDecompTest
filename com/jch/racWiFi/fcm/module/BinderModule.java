package com.jch.racWiFi.fcm.module;

import android.app.Application;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.p010di.scope.BaseScope;
import dagger.Module;
import dagger.Provides;

@Module
public class BinderModule {
    @BaseScope
    @Provides
    static Binder provideBinder(Application application) {
        return new Binder(application);
    }
}
