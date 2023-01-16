package com.jch.racWiFi.util.module;

import android.app.Application;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class JCIAlertDialogModule_ProvideJCIAlertDialogFactory implements Factory<JCIAlertDialog> {
    private final Provider<Application> applicationProvider;

    public JCIAlertDialogModule_ProvideJCIAlertDialogFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public JCIAlertDialog get() {
        return provideJCIAlertDialog(this.applicationProvider.get());
    }

    public static JCIAlertDialogModule_ProvideJCIAlertDialogFactory create(Provider<Application> provider) {
        return new JCIAlertDialogModule_ProvideJCIAlertDialogFactory(provider);
    }

    public static JCIAlertDialog provideJCIAlertDialog(Application application) {
        return (JCIAlertDialog) Preconditions.checkNotNullFromProvides(JCIAlertDialogModule.provideJCIAlertDialog(application));
    }
}
