package com.jch.racWiFi.util.module;

import android.app.Application;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class JCIAlertDialogModule {
    @Singleton
    @Provides
    static JCIAlertDialog provideJCIAlertDialog(Application application) {
        return new JCIAlertDialog(application);
    }
}
