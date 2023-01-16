package com.jch.racWiFi;

import com.jch.racWiFi.amplitude.factory.ClientFactory;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

public final class CoreActivity_MembersInjector implements MembersInjector<CoreActivity> {
    private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;
    private final Provider<AmplitudeUtil> mAmplitudeUtilProvider;
    private final Provider<ClientFactory> mClientFactoryProvider;
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;

    public CoreActivity_MembersInjector(Provider<DispatchingAndroidInjector<Object>> provider, Provider<ViewModelProviderFactory> provider2, Provider<AmplitudeUtil> provider3, Provider<ClientFactory> provider4, Provider<JCIAlertDialog> provider5) {
        this.androidInjectorProvider = provider;
        this.providerFactoryProvider = provider2;
        this.mAmplitudeUtilProvider = provider3;
        this.mClientFactoryProvider = provider4;
        this.mJciAlertDialogProvider = provider5;
    }

    public static MembersInjector<CoreActivity> create(Provider<DispatchingAndroidInjector<Object>> provider, Provider<ViewModelProviderFactory> provider2, Provider<AmplitudeUtil> provider3, Provider<ClientFactory> provider4, Provider<JCIAlertDialog> provider5) {
        return new CoreActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public void injectMembers(CoreActivity coreActivity) {
        DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(coreActivity, this.androidInjectorProvider.get());
        injectProviderFactory(coreActivity, this.providerFactoryProvider.get());
        injectMAmplitudeUtil(coreActivity, this.mAmplitudeUtilProvider.get());
        injectMClientFactory(coreActivity, this.mClientFactoryProvider.get());
        injectMJciAlertDialog(coreActivity, this.mJciAlertDialogProvider.get());
    }

    public static void injectProviderFactory(CoreActivity coreActivity, ViewModelProviderFactory viewModelProviderFactory) {
        coreActivity.providerFactory = viewModelProviderFactory;
    }

    public static void injectMAmplitudeUtil(CoreActivity coreActivity, AmplitudeUtil amplitudeUtil) {
        coreActivity.mAmplitudeUtil = amplitudeUtil;
    }

    public static void injectMClientFactory(CoreActivity coreActivity, ClientFactory clientFactory) {
        coreActivity.mClientFactory = clientFactory;
    }

    public static void injectMJciAlertDialog(CoreActivity coreActivity, JCIAlertDialog jCIAlertDialog) {
        coreActivity.mJciAlertDialog = jCIAlertDialog;
    }
}
