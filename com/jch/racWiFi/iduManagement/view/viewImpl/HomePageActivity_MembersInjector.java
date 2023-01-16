package com.jch.racWiFi.iduManagement.view.viewImpl;

import com.jch.racWiFi.CoreActivity_MembersInjector;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

public final class HomePageActivity_MembersInjector implements MembersInjector<HomePageActivity> {
    private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;
    private final Provider<AmplitudeUtil> mAmplitudeUtilProvider;
    private final Provider<ClientFactory> mClientFactoryProvider;
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider2;
    private final Provider<ModelRepository> modelRepositoryProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider;
    private final Provider<ViewModelProviderFactory> providerFactoryProvider2;

    public HomePageActivity_MembersInjector(Provider<DispatchingAndroidInjector<Object>> provider, Provider<ViewModelProviderFactory> provider2, Provider<AmplitudeUtil> provider3, Provider<ClientFactory> provider4, Provider<JCIAlertDialog> provider5, Provider<ViewModelProviderFactory> provider6, Provider<ModelRepository> provider7, Provider<JCIAlertDialog> provider8) {
        this.androidInjectorProvider = provider;
        this.providerFactoryProvider = provider2;
        this.mAmplitudeUtilProvider = provider3;
        this.mClientFactoryProvider = provider4;
        this.mJciAlertDialogProvider = provider5;
        this.providerFactoryProvider2 = provider6;
        this.modelRepositoryProvider = provider7;
        this.mJciAlertDialogProvider2 = provider8;
    }

    public static MembersInjector<HomePageActivity> create(Provider<DispatchingAndroidInjector<Object>> provider, Provider<ViewModelProviderFactory> provider2, Provider<AmplitudeUtil> provider3, Provider<ClientFactory> provider4, Provider<JCIAlertDialog> provider5, Provider<ViewModelProviderFactory> provider6, Provider<ModelRepository> provider7, Provider<JCIAlertDialog> provider8) {
        return new HomePageActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public void injectMembers(HomePageActivity homePageActivity) {
        DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(homePageActivity, this.androidInjectorProvider.get());
        CoreActivity_MembersInjector.injectProviderFactory(homePageActivity, this.providerFactoryProvider.get());
        CoreActivity_MembersInjector.injectMAmplitudeUtil(homePageActivity, this.mAmplitudeUtilProvider.get());
        CoreActivity_MembersInjector.injectMClientFactory(homePageActivity, this.mClientFactoryProvider.get());
        CoreActivity_MembersInjector.injectMJciAlertDialog(homePageActivity, this.mJciAlertDialogProvider.get());
        injectProviderFactory(homePageActivity, this.providerFactoryProvider2.get());
        injectModelRepository(homePageActivity, this.modelRepositoryProvider.get());
        injectMJciAlertDialog(homePageActivity, this.mJciAlertDialogProvider2.get());
    }

    public static void injectProviderFactory(HomePageActivity homePageActivity, ViewModelProviderFactory viewModelProviderFactory) {
        homePageActivity.providerFactory = viewModelProviderFactory;
    }

    public static void injectModelRepository(HomePageActivity homePageActivity, ModelRepository modelRepository) {
        homePageActivity.modelRepository = modelRepository;
    }

    public static void injectMJciAlertDialog(HomePageActivity homePageActivity, JCIAlertDialog jCIAlertDialog) {
        homePageActivity.mJciAlertDialog = jCIAlertDialog;
    }
}
