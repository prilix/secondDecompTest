package com.jch.racWiFi.p010di.component;

import android.app.Application;
import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.App;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.CoreActivity_MembersInjector;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import com.jch.racWiFi.amplitude.module.AmplitudeUtilModule_ProvideAmplitudeUtilFactory;
import com.jch.racWiFi.amplitude.module.ClientFactoryModule_ProvideClientFactoryFactory;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import com.jch.racWiFi.amplitude.util.AppEventService;
import com.jch.racWiFi.amplitude.util.AppEventService_MembersInjector;
import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import com.jch.racWiFi.fcm.module.BinderModule_ProvideBinderFactory;
import com.jch.racWiFi.fcm.module.DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory;
import com.jch.racWiFi.fcm.module.FcmModule_ProvideFcmApiFactory;
import com.jch.racWiFi.fcm.module.ModelRepositoryModule_ProvideModelRepositoryFactory;
import com.jch.racWiFi.fcm.module.NotificationBuilderModule_ProvideNotificationBuilderFactory;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.services.FcmService;
import com.jch.racWiFi.fcm.services.FcmService_MembersInjector;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import com.jch.racWiFi.fcm.view_model.FcmViewModel;
import com.jch.racWiFi.fcm.view_model.FcmViewModel_Factory;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService_MembersInjector;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity_MembersInjector;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment_MembersInjector;
import com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise;
import com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise_MembersInjector;
import com.jch.racWiFi.linking.amazon.activity.AlexaActivity;
import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment;
import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment_MembersInjector;
import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment;
import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment_MembersInjector;
import com.jch.racWiFi.linking.amazon.module.AlexaModule_ProvideAlexaApiFactory;
import com.jch.racWiFi.linking.amazon.view_model.AlexaViewModel;
import com.jch.racWiFi.linking.amazon.view_model.AlexaViewModel_Factory;
import com.jch.racWiFi.linking.google.module.AppFlipModule_ProvideAppFlipApiFactory;
import com.jch.racWiFi.linking.google.view_model.AppFlipViewModel;
import com.jch.racWiFi.linking.google.view_model.AppFlipViewModel_Factory;
import com.jch.racWiFi.main.api.BaseMainApi;
import com.jch.racWiFi.main.api.MainApi;
import com.jch.racWiFi.main.module.BaseMainModule_ProvideRefreshTokenApiFactory;
import com.jch.racWiFi.main.module.MainModule_ProvideRefreshTokenApiFactory;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.main.view_model.MainViewModel_Factory;
import com.jch.racWiFi.p010di.api.AlexaApi;
import com.jch.racWiFi.p010di.api.AppFlipApi;
import com.jch.racWiFi.p010di.api.FcmApi;
import com.jch.racWiFi.p010di.component.AppComponent;
import com.jch.racWiFi.p010di.module.base.BaseModule_ProvideClientFactory;
import com.jch.racWiFi.p010di.module.base.BaseModule_ProvideLoggingInterceptorFactory;
import com.jch.racWiFi.p010di.module.base.BaseModule_ProvideRetrofitFactory;
import com.jch.racWiFi.p010di.module.builder.ActivityBuilderModule_ContributeAlexaActivity;
import com.jch.racWiFi.p010di.module.builder.ActivityBuilderModule_ContributeCoreActivity;
import com.jch.racWiFi.p010di.module.builder.ActivityBuilderModule_ContributeHomePageActivity;
import com.jch.racWiFi.p010di.module.builder.ActivityBuilderModule_ContributeUserManagementActivity;
import com.jch.racWiFi.p010di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkFragment;
import com.jch.racWiFi.p010di.module.builder.AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment;
import com.jch.racWiFi.p010di.module.builder.C1786xd20ff6b7;
import com.jch.racWiFi.p010di.module.builder.ServiceBuilderModule_ContributeAppEventService;
import com.jch.racWiFi.p010di.module.builder.ServiceBuilderModule_ContributeFcmService;
import com.jch.racWiFi.p010di.module.builder.fragment.C1787x13db9505;
import com.jch.racWiFi.p010di.module.builder.fragment.C1788xcbaed8be;
import com.jch.racWiFi.p010di.module.builder.fragment.C1789x6faeb380;
import com.jch.racWiFi.p010di.module.builder.fragment.C1790xaf779aaa;
import com.jch.racWiFi.p010di.module.builder.fragment.C1791xbc67bfb8;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeHomePageFragment;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment;
import com.jch.racWiFi.p010di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeServiceRequestFragment;
import com.jch.racWiFi.p010di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeLoginFragment;
import com.jch.racWiFi.p010di.module.builder.fragment.UserManagementFragmentBuilderModule_ContributeSplashFragment;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.settings.view.CustomerCareFragmentGlobal;
import com.jch.racWiFi.settings.view.CustomerCareFragmentGlobal_MembersInjector;
import com.jch.racWiFi.settings.view.HelpFragmentGlobal;
import com.jch.racWiFi.settings.view.HelpFragmentGlobal_MembersInjector;
import com.jch.racWiFi.settings.view.ServiceRequestFragment;
import com.jch.racWiFi.settings.view.ServiceRequestFragment_MembersInjector;
import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment;
import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment_MembersInjector;
import com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3;
import com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3_MembersInjector;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep4Fragment;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep4Fragment_MembersInjector;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import com.jch.racWiFi.userManagement.view.viewImpl.LoginFragment;
import com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment;
import com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment_MembersInjector;
import com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.module.JCIAlertDialogModule_ProvideJCIAlertDialogFactory;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent */
public final class DaggerAppComponent implements AppComponent {
    /* access modifiers changed from: private */
    public Provider<ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent.Factory> alexaActivitySubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent.Factory> appEventServiceSubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<Application> applicationProvider;
    /* access modifiers changed from: private */
    public Provider<ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent.Factory> coreActivitySubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent.Factory> fcmServiceSubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent.Factory> geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent.Factory> homePageActivitySubcomponentFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<AmplitudeUtil> provideAmplitudeUtilProvider;
    /* access modifiers changed from: private */
    public Provider<ClientFactory> provideClientFactoryProvider;
    private Provider<OkHttpClient.Builder> provideClientProvider;
    /* access modifiers changed from: private */
    public Provider<DeepLinkFactory> provideDeepLinkFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<JCIAlertDialog> provideJCIAlertDialogProvider;
    private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;
    /* access modifiers changed from: private */
    public Provider<ModelRepository> provideModelRepositoryProvider;
    /* access modifiers changed from: private */
    public Provider<NotificationBuilder> provideNotificationBuilderProvider;
    /* access modifiers changed from: private */
    public Provider<BaseMainApi> provideRefreshTokenApiProvider;
    /* access modifiers changed from: private */
    public Provider<Retrofit> provideRetrofitProvider;
    /* access modifiers changed from: private */
    public Provider<ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent.Factory> userManagementActivitySubcomponentFactoryProvider;

    private DaggerAppComponent(Application application) {
        initialize(application);
    }

    public static AppComponent.Builder builder() {
        return new Builder();
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
        return MapBuilder.newMapBuilder(7).put(CoreActivity.class, this.coreActivitySubcomponentFactoryProvider).put(UserManagementActivity.class, this.userManagementActivitySubcomponentFactoryProvider).put(HomePageActivity.class, this.homePageActivitySubcomponentFactoryProvider).put(AlexaActivity.class, this.alexaActivitySubcomponentFactoryProvider).put(FcmService.class, this.fcmServiceSubcomponentFactoryProvider).put(GeoFenceTransitionNetworkJobService.class, this.geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider).put(AppEventService.class, this.appEventServiceSubcomponentFactoryProvider).build();
    }

    /* access modifiers changed from: private */
    public DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
        return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.emptyMap());
    }

    private void initialize(Application application) {
        this.coreActivitySubcomponentFactoryProvider = new Provider<ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent.Factory>() {
            public ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent.Factory get() {
                return new CoreActivitySubcomponentFactory();
            }
        };
        this.userManagementActivitySubcomponentFactoryProvider = new Provider<ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent.Factory>() {
            public ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent.Factory get() {
                return new UserManagementActivitySubcomponentFactory();
            }
        };
        this.homePageActivitySubcomponentFactoryProvider = new Provider<ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent.Factory>() {
            public ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent.Factory get() {
                return new HomePageActivitySubcomponentFactory();
            }
        };
        this.alexaActivitySubcomponentFactoryProvider = new Provider<ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent.Factory>() {
            public ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent.Factory get() {
                return new AlexaActivitySubcomponentFactory();
            }
        };
        this.fcmServiceSubcomponentFactoryProvider = new Provider<ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent.Factory>() {
            public ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent.Factory get() {
                return new FcmServiceSubcomponentFactory();
            }
        };
        this.geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider = new Provider<C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent.Factory>() {
            public C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent.Factory get() {
                return new GeoFenceTransitionNetworkJobServiceSubcomponentFactory();
            }
        };
        this.appEventServiceSubcomponentFactoryProvider = new Provider<ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent.Factory>() {
            public ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent.Factory get() {
                return new AppEventServiceSubcomponentFactory();
            }
        };
        this.provideLoggingInterceptorProvider = DoubleCheck.provider(BaseModule_ProvideLoggingInterceptorFactory.create());
        Factory create = InstanceFactory.create(application);
        this.applicationProvider = create;
        Provider<OkHttpClient.Builder> provider = DoubleCheck.provider(BaseModule_ProvideClientFactory.create(this.provideLoggingInterceptorProvider, create));
        this.provideClientProvider = provider;
        this.provideRetrofitProvider = DoubleCheck.provider(BaseModule_ProvideRetrofitFactory.create(provider));
        this.provideAmplitudeUtilProvider = DoubleCheck.provider(AmplitudeUtilModule_ProvideAmplitudeUtilFactory.create(this.applicationProvider));
        this.provideClientFactoryProvider = DoubleCheck.provider(ClientFactoryModule_ProvideClientFactoryFactory.create(this.applicationProvider));
        this.provideJCIAlertDialogProvider = DoubleCheck.provider(JCIAlertDialogModule_ProvideJCIAlertDialogFactory.create(this.applicationProvider));
        this.provideModelRepositoryProvider = DoubleCheck.provider(ModelRepositoryModule_ProvideModelRepositoryFactory.create(this.applicationProvider));
        this.provideDeepLinkFactoryProvider = DoubleCheck.provider(DeepLinkFactoryModule_ProvideDeepLinkFactoryFactory.create(this.applicationProvider));
        this.provideNotificationBuilderProvider = DoubleCheck.provider(NotificationBuilderModule_ProvideNotificationBuilderFactory.create(this.applicationProvider));
        this.provideRefreshTokenApiProvider = DoubleCheck.provider(BaseMainModule_ProvideRefreshTokenApiFactory.create(this.provideRetrofitProvider));
    }

    public void inject(App app) {
        injectApp(app);
    }

    private App injectApp(App app) {
        DaggerApplication_MembersInjector.injectAndroidInjector(app, dispatchingAndroidInjectorOfObject());
        return app;
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$Builder */
    private static final class Builder implements AppComponent.Builder {
        private Application application;

        private Builder() {
        }

        public Builder application(Application application2) {
            this.application = (Application) Preconditions.checkNotNull(application2);
            return this;
        }

        public AppComponent build() {
            Preconditions.checkBuilderRequirement(this.application, Application.class);
            return new DaggerAppComponent(this.application);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$CoreActivitySubcomponentFactory */
    private final class CoreActivitySubcomponentFactory implements ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent.Factory {
        private CoreActivitySubcomponentFactory() {
        }

        public ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent create(CoreActivity coreActivity) {
            Preconditions.checkNotNull(coreActivity);
            return new CoreActivitySubcomponentImpl(coreActivity);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$CoreActivitySubcomponentImpl */
    private final class CoreActivitySubcomponentImpl implements ActivityBuilderModule_ContributeCoreActivity.CoreActivitySubcomponent {
        private Provider<MainViewModel> mainViewModelProvider;
        private Provider<MainApi> provideRefreshTokenApiProvider;

        private CoreActivitySubcomponentImpl(CoreActivity coreActivity) {
            initialize(coreActivity);
        }

        private Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> mapOfClassOfAndProviderOfViewModel() {
            return Collections.singletonMap(MainViewModel.class, this.mainViewModelProvider);
        }

        private ViewModelProviderFactory viewModelProviderFactory() {
            return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
        }

        private void initialize(CoreActivity coreActivity) {
            Provider<MainApi> provider = DoubleCheck.provider(MainModule_ProvideRefreshTokenApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideRefreshTokenApiProvider = provider;
            this.mainViewModelProvider = MainViewModel_Factory.create(provider);
        }

        public void inject(CoreActivity coreActivity) {
            injectCoreActivity(coreActivity);
        }

        private CoreActivity injectCoreActivity(CoreActivity coreActivity) {
            DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(coreActivity, DaggerAppComponent.this.dispatchingAndroidInjectorOfObject());
            CoreActivity_MembersInjector.injectProviderFactory(coreActivity, viewModelProviderFactory());
            CoreActivity_MembersInjector.injectMAmplitudeUtil(coreActivity, (AmplitudeUtil) DaggerAppComponent.this.provideAmplitudeUtilProvider.get());
            CoreActivity_MembersInjector.injectMClientFactory(coreActivity, (ClientFactory) DaggerAppComponent.this.provideClientFactoryProvider.get());
            CoreActivity_MembersInjector.injectMJciAlertDialog(coreActivity, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
            return coreActivity;
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentFactory */
    private final class UserManagementActivitySubcomponentFactory implements ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent.Factory {
        private UserManagementActivitySubcomponentFactory() {
        }

        public ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent create(UserManagementActivity userManagementActivity) {
            Preconditions.checkNotNull(userManagementActivity);
            return new UserManagementActivitySubcomponentImpl(userManagementActivity);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl */
    private final class UserManagementActivitySubcomponentImpl implements ActivityBuilderModule_ContributeUserManagementActivity.UserManagementActivitySubcomponent {
        private Provider<C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent.Factory> createAccountStep4FragmentSubcomponentFactoryProvider;
        private Provider<C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent.Factory> createAccountStep5SuccessFragmentSubcomponentFactoryProvider;
        private Provider<UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent.Factory> loginFragmentSubcomponentFactoryProvider;
        private Provider<MainViewModel> mainViewModelProvider;
        /* access modifiers changed from: private */
        public Provider<Binder> provideBinderProvider;
        private Provider<MainApi> provideRefreshTokenApiProvider;
        private Provider<UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent.Factory> splashFragmentSubcomponentFactoryProvider;

        private UserManagementActivitySubcomponentImpl(UserManagementActivity userManagementActivity) {
            initialize(userManagementActivity);
        }

        private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
            return MapBuilder.newMapBuilder(11).put(CoreActivity.class, DaggerAppComponent.this.coreActivitySubcomponentFactoryProvider).put(UserManagementActivity.class, DaggerAppComponent.this.userManagementActivitySubcomponentFactoryProvider).put(HomePageActivity.class, DaggerAppComponent.this.homePageActivitySubcomponentFactoryProvider).put(AlexaActivity.class, DaggerAppComponent.this.alexaActivitySubcomponentFactoryProvider).put(FcmService.class, DaggerAppComponent.this.fcmServiceSubcomponentFactoryProvider).put(GeoFenceTransitionNetworkJobService.class, DaggerAppComponent.this.geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider).put(AppEventService.class, DaggerAppComponent.this.appEventServiceSubcomponentFactoryProvider).put(SplashFragment.class, this.splashFragmentSubcomponentFactoryProvider).put(LoginFragment.class, this.loginFragmentSubcomponentFactoryProvider).put(CreateAccountStep5SuccessFragment.class, this.createAccountStep5SuccessFragmentSubcomponentFactoryProvider).put(CreateAccountStep4Fragment.class, this.createAccountStep4FragmentSubcomponentFactoryProvider).build();
        }

        private DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
            return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.emptyMap());
        }

        private Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> mapOfClassOfAndProviderOfViewModel() {
            return Collections.singletonMap(MainViewModel.class, this.mainViewModelProvider);
        }

        /* access modifiers changed from: private */
        public ViewModelProviderFactory viewModelProviderFactory() {
            return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
        }

        private void initialize(UserManagementActivity userManagementActivity) {
            this.splashFragmentSubcomponentFactoryProvider = new Provider<UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent.Factory>() {
                public UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent.Factory get() {
                    return new SplashFragmentSubcomponentFactory();
                }
            };
            this.loginFragmentSubcomponentFactoryProvider = new Provider<UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent.Factory>() {
                public UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent.Factory get() {
                    return new LoginFragmentSubcomponentFactory();
                }
            };
            this.createAccountStep5SuccessFragmentSubcomponentFactoryProvider = new Provider<C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent.Factory>() {
                public C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent.Factory get() {
                    return new CreateAccountStep5SuccessFragmentSubcomponentFactory();
                }
            };
            this.createAccountStep4FragmentSubcomponentFactoryProvider = new Provider<C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent.Factory>() {
                public C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent.Factory get() {
                    return new CreateAccountStep4FragmentSubcomponentFactory();
                }
            };
            Provider<MainApi> provider = DoubleCheck.provider(MainModule_ProvideRefreshTokenApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideRefreshTokenApiProvider = provider;
            this.mainViewModelProvider = MainViewModel_Factory.create(provider);
            this.provideBinderProvider = DoubleCheck.provider(BinderModule_ProvideBinderFactory.create(DaggerAppComponent.this.applicationProvider));
        }

        public void inject(UserManagementActivity userManagementActivity) {
            injectUserManagementActivity(userManagementActivity);
        }

        private UserManagementActivity injectUserManagementActivity(UserManagementActivity userManagementActivity) {
            DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(userManagementActivity, dispatchingAndroidInjectorOfObject());
            CoreActivity_MembersInjector.injectProviderFactory(userManagementActivity, viewModelProviderFactory());
            CoreActivity_MembersInjector.injectMAmplitudeUtil(userManagementActivity, (AmplitudeUtil) DaggerAppComponent.this.provideAmplitudeUtilProvider.get());
            CoreActivity_MembersInjector.injectMClientFactory(userManagementActivity, (ClientFactory) DaggerAppComponent.this.provideClientFactoryProvider.get());
            CoreActivity_MembersInjector.injectMJciAlertDialog(userManagementActivity, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
            return userManagementActivity;
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$SplashFragmentSubcomponentFactory */
        private final class SplashFragmentSubcomponentFactory implements UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent.Factory {
            private SplashFragmentSubcomponentFactory() {
            }

            public UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent create(SplashFragment splashFragment) {
                Preconditions.checkNotNull(splashFragment);
                return new SplashFragmentSubcomponentImpl(splashFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$SplashFragmentSubcomponentImpl */
        private final class SplashFragmentSubcomponentImpl implements UserManagementFragmentBuilderModule_ContributeSplashFragment.SplashFragmentSubcomponent {
            private SplashFragmentSubcomponentImpl(SplashFragment splashFragment) {
            }

            public void inject(SplashFragment splashFragment) {
                injectSplashFragment(splashFragment);
            }

            private SplashFragment injectSplashFragment(SplashFragment splashFragment) {
                SplashFragment_MembersInjector.injectMBinder(splashFragment, (Binder) UserManagementActivitySubcomponentImpl.this.provideBinderProvider.get());
                SplashFragment_MembersInjector.injectProviderFactory(splashFragment, UserManagementActivitySubcomponentImpl.this.viewModelProviderFactory());
                return splashFragment;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$LoginFragmentSubcomponentFactory */
        private final class LoginFragmentSubcomponentFactory implements UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent.Factory {
            private LoginFragmentSubcomponentFactory() {
            }

            public UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent create(LoginFragment loginFragment) {
                Preconditions.checkNotNull(loginFragment);
                return new LoginFragmentSubcomponentImpl(loginFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$LoginFragmentSubcomponentImpl */
        private final class LoginFragmentSubcomponentImpl implements UserManagementFragmentBuilderModule_ContributeLoginFragment.LoginFragmentSubcomponent {
            public void inject(LoginFragment loginFragment) {
            }

            private LoginFragmentSubcomponentImpl(LoginFragment loginFragment) {
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$CreateAccountStep5SuccessFragmentSubcomponentFactory */
        private final class CreateAccountStep5SuccessFragmentSubcomponentFactory implements C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent.Factory {
            private CreateAccountStep5SuccessFragmentSubcomponentFactory() {
            }

            public C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent create(CreateAccountStep5SuccessFragment createAccountStep5SuccessFragment) {
                Preconditions.checkNotNull(createAccountStep5SuccessFragment);
                return new CreateAccountStep5SuccessFragmentSubcomponentImpl(createAccountStep5SuccessFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$CreateAccountStep5SuccessFragmentSubcomponentImpl */
        private final class CreateAccountStep5SuccessFragmentSubcomponentImpl implements C1791xbc67bfb8.CreateAccountStep5SuccessFragmentSubcomponent {
            public void inject(CreateAccountStep5SuccessFragment createAccountStep5SuccessFragment) {
            }

            private CreateAccountStep5SuccessFragmentSubcomponentImpl(CreateAccountStep5SuccessFragment createAccountStep5SuccessFragment) {
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$CreateAccountStep4FragmentSubcomponentFactory */
        private final class CreateAccountStep4FragmentSubcomponentFactory implements C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent.Factory {
            private CreateAccountStep4FragmentSubcomponentFactory() {
            }

            public C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent create(CreateAccountStep4Fragment createAccountStep4Fragment) {
                Preconditions.checkNotNull(createAccountStep4Fragment);
                return new CreateAccountStep4FragmentSubcomponentImpl(createAccountStep4Fragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$UserManagementActivitySubcomponentImpl$CreateAccountStep4FragmentSubcomponentImpl */
        private final class CreateAccountStep4FragmentSubcomponentImpl implements C1790xaf779aaa.CreateAccountStep4FragmentSubcomponent {
            private CreateAccountStep4FragmentSubcomponentImpl(CreateAccountStep4Fragment createAccountStep4Fragment) {
            }

            public void inject(CreateAccountStep4Fragment createAccountStep4Fragment) {
                injectCreateAccountStep4Fragment(createAccountStep4Fragment);
            }

            private CreateAccountStep4Fragment injectCreateAccountStep4Fragment(CreateAccountStep4Fragment createAccountStep4Fragment) {
                CreateAccountStep4Fragment_MembersInjector.injectMJciAlertDialog(createAccountStep4Fragment, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
                return createAccountStep4Fragment;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentFactory */
    private final class HomePageActivitySubcomponentFactory implements ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent.Factory {
        private HomePageActivitySubcomponentFactory() {
        }

        public ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent create(HomePageActivity homePageActivity) {
            Preconditions.checkNotNull(homePageActivity);
            return new HomePageActivitySubcomponentImpl(homePageActivity);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl */
    private final class HomePageActivitySubcomponentImpl implements ActivityBuilderModule_ContributeHomePageActivity.HomePageActivitySubcomponent {
        private Provider<AlexaViewModel> alexaViewModelProvider;
        private Provider<C1787x13db9505.CustomerCareFragmentGlobalSubcomponent.Factory> customerCareFragmentGlobalSubcomponentFactoryProvider;
        private Provider<FcmViewModel> fcmViewModelProvider;
        private Provider<HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent.Factory> helpFragmentGlobalSubcomponentFactoryProvider;
        private Provider<HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent.Factory> homePageFragmentSubcomponentFactoryProvider;
        private Provider<C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent.Factory> individualIDUControlFragmentModelWiseSubcomponentFactoryProvider;
        private Provider<MainViewModel> mainViewModelProvider;
        private Provider<HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent.Factory> myAccountAddressFragmentSubcomponentFactoryProvider;
        private Provider<C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent.Factory> myAccountProfilePictureFragmentV3SubcomponentFactoryProvider;
        private Provider<AlexaApi> provideAlexaApiProvider;
        /* access modifiers changed from: private */
        public Provider<Binder> provideBinderProvider;
        private Provider<FcmApi> provideFcmApiProvider;
        private Provider<MainApi> provideRefreshTokenApiProvider;
        private Provider<HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent.Factory> serviceRequestFragmentSubcomponentFactoryProvider;

        private HomePageActivitySubcomponentImpl(HomePageActivity homePageActivity) {
            initialize(homePageActivity);
        }

        private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
            return MapBuilder.newMapBuilder(14).put(CoreActivity.class, DaggerAppComponent.this.coreActivitySubcomponentFactoryProvider).put(UserManagementActivity.class, DaggerAppComponent.this.userManagementActivitySubcomponentFactoryProvider).put(HomePageActivity.class, DaggerAppComponent.this.homePageActivitySubcomponentFactoryProvider).put(AlexaActivity.class, DaggerAppComponent.this.alexaActivitySubcomponentFactoryProvider).put(FcmService.class, DaggerAppComponent.this.fcmServiceSubcomponentFactoryProvider).put(GeoFenceTransitionNetworkJobService.class, DaggerAppComponent.this.geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider).put(AppEventService.class, DaggerAppComponent.this.appEventServiceSubcomponentFactoryProvider).put(HomePageFragment.class, this.homePageFragmentSubcomponentFactoryProvider).put(IndividualIDUControlFragmentModelWise.class, this.individualIDUControlFragmentModelWiseSubcomponentFactoryProvider).put(ServiceRequestFragment.class, this.serviceRequestFragmentSubcomponentFactoryProvider).put(CustomerCareFragmentGlobal.class, this.customerCareFragmentGlobalSubcomponentFactoryProvider).put(MyAccountAddressFragment.class, this.myAccountAddressFragmentSubcomponentFactoryProvider).put(MyAccountProfilePictureFragmentV3.class, this.myAccountProfilePictureFragmentV3SubcomponentFactoryProvider).put(HelpFragmentGlobal.class, this.helpFragmentGlobalSubcomponentFactoryProvider).build();
        }

        private DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
            return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.emptyMap());
        }

        private Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> mapOfClassOfAndProviderOfViewModel() {
            return MapBuilder.newMapBuilder(3).put(MainViewModel.class, this.mainViewModelProvider).put(FcmViewModel.class, this.fcmViewModelProvider).put(AlexaViewModel.class, this.alexaViewModelProvider).build();
        }

        /* access modifiers changed from: private */
        public ViewModelProviderFactory viewModelProviderFactory() {
            return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
        }

        private void initialize(HomePageActivity homePageActivity) {
            this.homePageFragmentSubcomponentFactoryProvider = new Provider<HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent.Factory>() {
                public HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent.Factory get() {
                    return new HomePageFragmentSubcomponentFactory();
                }
            };
            this.individualIDUControlFragmentModelWiseSubcomponentFactoryProvider = new Provider<C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent.Factory>() {
                public C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent.Factory get() {
                    return new IndividualIDUControlFragmentModelWiseSubcomponentFactory();
                }
            };
            this.serviceRequestFragmentSubcomponentFactoryProvider = new Provider<HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent.Factory>() {
                public HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent.Factory get() {
                    return new ServiceRequestFragmentSubcomponentFactory();
                }
            };
            this.customerCareFragmentGlobalSubcomponentFactoryProvider = new Provider<C1787x13db9505.CustomerCareFragmentGlobalSubcomponent.Factory>() {
                public C1787x13db9505.CustomerCareFragmentGlobalSubcomponent.Factory get() {
                    return new CustomerCareFragmentGlobalSubcomponentFactory();
                }
            };
            this.myAccountAddressFragmentSubcomponentFactoryProvider = new Provider<HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent.Factory>() {
                public HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent.Factory get() {
                    return new MyAccountAddressFragmentSubcomponentFactory();
                }
            };
            this.myAccountProfilePictureFragmentV3SubcomponentFactoryProvider = new Provider<C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent.Factory>() {
                public C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent.Factory get() {
                    return new MyAccountProfilePictureFragmentV3SubcomponentFactory();
                }
            };
            this.helpFragmentGlobalSubcomponentFactoryProvider = new Provider<HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent.Factory>() {
                public HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent.Factory get() {
                    return new HelpFragmentGlobalSubcomponentFactory();
                }
            };
            Provider<MainApi> provider = DoubleCheck.provider(MainModule_ProvideRefreshTokenApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideRefreshTokenApiProvider = provider;
            this.mainViewModelProvider = MainViewModel_Factory.create(provider);
            Provider<FcmApi> provider2 = DoubleCheck.provider(FcmModule_ProvideFcmApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideFcmApiProvider = provider2;
            this.fcmViewModelProvider = FcmViewModel_Factory.create(provider2, DaggerAppComponent.this.provideModelRepositoryProvider, DaggerAppComponent.this.provideDeepLinkFactoryProvider);
            Provider<AlexaApi> provider3 = DoubleCheck.provider(AlexaModule_ProvideAlexaApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideAlexaApiProvider = provider3;
            this.alexaViewModelProvider = AlexaViewModel_Factory.create(provider3);
            this.provideBinderProvider = DoubleCheck.provider(BinderModule_ProvideBinderFactory.create(DaggerAppComponent.this.applicationProvider));
        }

        public void inject(HomePageActivity homePageActivity) {
            injectHomePageActivity(homePageActivity);
        }

        private HomePageActivity injectHomePageActivity(HomePageActivity homePageActivity) {
            DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(homePageActivity, dispatchingAndroidInjectorOfObject());
            CoreActivity_MembersInjector.injectProviderFactory(homePageActivity, viewModelProviderFactory());
            CoreActivity_MembersInjector.injectMAmplitudeUtil(homePageActivity, (AmplitudeUtil) DaggerAppComponent.this.provideAmplitudeUtilProvider.get());
            CoreActivity_MembersInjector.injectMClientFactory(homePageActivity, (ClientFactory) DaggerAppComponent.this.provideClientFactoryProvider.get());
            CoreActivity_MembersInjector.injectMJciAlertDialog(homePageActivity, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
            HomePageActivity_MembersInjector.injectProviderFactory(homePageActivity, viewModelProviderFactory());
            HomePageActivity_MembersInjector.injectModelRepository(homePageActivity, (ModelRepository) DaggerAppComponent.this.provideModelRepositoryProvider.get());
            HomePageActivity_MembersInjector.injectMJciAlertDialog(homePageActivity, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
            return homePageActivity;
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$HomePageFragmentSubcomponentFactory */
        private final class HomePageFragmentSubcomponentFactory implements HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent.Factory {
            private HomePageFragmentSubcomponentFactory() {
            }

            public HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent create(HomePageFragment homePageFragment) {
                Preconditions.checkNotNull(homePageFragment);
                return new HomePageFragmentSubcomponentImpl(homePageFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$HomePageFragmentSubcomponentImpl */
        private final class HomePageFragmentSubcomponentImpl implements HomePageFragmentBuilderModule_ContributeHomePageFragment.HomePageFragmentSubcomponent {
            private HomePageFragmentSubcomponentImpl(HomePageFragment homePageFragment) {
            }

            public void inject(HomePageFragment homePageFragment) {
                injectHomePageFragment(homePageFragment);
            }

            private HomePageFragment injectHomePageFragment(HomePageFragment homePageFragment) {
                HomePageFragment_MembersInjector.injectMBinder(homePageFragment, (Binder) HomePageActivitySubcomponentImpl.this.provideBinderProvider.get());
                HomePageFragment_MembersInjector.injectProviderFactory(homePageFragment, HomePageActivitySubcomponentImpl.this.viewModelProviderFactory());
                return homePageFragment;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$IndividualIDUControlFragmentModelWiseSubcomponentFactory */
        private final class IndividualIDUControlFragmentModelWiseSubcomponentFactory implements C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent.Factory {
            private IndividualIDUControlFragmentModelWiseSubcomponentFactory() {
            }

            public C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent create(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise) {
                Preconditions.checkNotNull(individualIDUControlFragmentModelWise);
                return new IndividualIDUControlFragmentModelWiseSubcomponentImpl(individualIDUControlFragmentModelWise);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$IndividualIDUControlFragmentModelWiseSubcomponentImpl */
        private final class IndividualIDUControlFragmentModelWiseSubcomponentImpl implements C1788xcbaed8be.IndividualIDUControlFragmentModelWiseSubcomponent {
            private IndividualIDUControlFragmentModelWiseSubcomponentImpl(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise) {
            }

            public void inject(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise) {
                injectIndividualIDUControlFragmentModelWise(individualIDUControlFragmentModelWise);
            }

            private IndividualIDUControlFragmentModelWise injectIndividualIDUControlFragmentModelWise(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise) {
                IndividualIDUControlFragmentModelWise_MembersInjector.injectMBinder(individualIDUControlFragmentModelWise, (Binder) HomePageActivitySubcomponentImpl.this.provideBinderProvider.get());
                IndividualIDUControlFragmentModelWise_MembersInjector.injectProviderFactory(individualIDUControlFragmentModelWise, HomePageActivitySubcomponentImpl.this.viewModelProviderFactory());
                return individualIDUControlFragmentModelWise;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$ServiceRequestFragmentSubcomponentFactory */
        private final class ServiceRequestFragmentSubcomponentFactory implements HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent.Factory {
            private ServiceRequestFragmentSubcomponentFactory() {
            }

            public HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent create(ServiceRequestFragment serviceRequestFragment) {
                Preconditions.checkNotNull(serviceRequestFragment);
                return new ServiceRequestFragmentSubcomponentImpl(serviceRequestFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$ServiceRequestFragmentSubcomponentImpl */
        private final class ServiceRequestFragmentSubcomponentImpl implements HomePageFragmentBuilderModule_ContributeServiceRequestFragment.ServiceRequestFragmentSubcomponent {
            private ServiceRequestFragmentSubcomponentImpl(ServiceRequestFragment serviceRequestFragment) {
            }

            public void inject(ServiceRequestFragment serviceRequestFragment) {
                injectServiceRequestFragment(serviceRequestFragment);
            }

            private ServiceRequestFragment injectServiceRequestFragment(ServiceRequestFragment serviceRequestFragment) {
                ServiceRequestFragment_MembersInjector.injectMBinder(serviceRequestFragment, (Binder) HomePageActivitySubcomponentImpl.this.provideBinderProvider.get());
                return serviceRequestFragment;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$CustomerCareFragmentGlobalSubcomponentFactory */
        private final class CustomerCareFragmentGlobalSubcomponentFactory implements C1787x13db9505.CustomerCareFragmentGlobalSubcomponent.Factory {
            private CustomerCareFragmentGlobalSubcomponentFactory() {
            }

            public C1787x13db9505.CustomerCareFragmentGlobalSubcomponent create(CustomerCareFragmentGlobal customerCareFragmentGlobal) {
                Preconditions.checkNotNull(customerCareFragmentGlobal);
                return new CustomerCareFragmentGlobalSubcomponentImpl(customerCareFragmentGlobal);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$CustomerCareFragmentGlobalSubcomponentImpl */
        private final class CustomerCareFragmentGlobalSubcomponentImpl implements C1787x13db9505.CustomerCareFragmentGlobalSubcomponent {
            private CustomerCareFragmentGlobalSubcomponentImpl(CustomerCareFragmentGlobal customerCareFragmentGlobal) {
            }

            public void inject(CustomerCareFragmentGlobal customerCareFragmentGlobal) {
                injectCustomerCareFragmentGlobal(customerCareFragmentGlobal);
            }

            private CustomerCareFragmentGlobal injectCustomerCareFragmentGlobal(CustomerCareFragmentGlobal customerCareFragmentGlobal) {
                CustomerCareFragmentGlobal_MembersInjector.injectMBinder(customerCareFragmentGlobal, (Binder) HomePageActivitySubcomponentImpl.this.provideBinderProvider.get());
                return customerCareFragmentGlobal;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$MyAccountAddressFragmentSubcomponentFactory */
        private final class MyAccountAddressFragmentSubcomponentFactory implements HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent.Factory {
            private MyAccountAddressFragmentSubcomponentFactory() {
            }

            public HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent create(MyAccountAddressFragment myAccountAddressFragment) {
                Preconditions.checkNotNull(myAccountAddressFragment);
                return new MyAccountAddressFragmentSubcomponentImpl(myAccountAddressFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$MyAccountAddressFragmentSubcomponentImpl */
        private final class MyAccountAddressFragmentSubcomponentImpl implements HomePageFragmentBuilderModule_ContributeMyAccountAddressFragment.MyAccountAddressFragmentSubcomponent {
            private MyAccountAddressFragmentSubcomponentImpl(MyAccountAddressFragment myAccountAddressFragment) {
            }

            public void inject(MyAccountAddressFragment myAccountAddressFragment) {
                injectMyAccountAddressFragment(myAccountAddressFragment);
            }

            private MyAccountAddressFragment injectMyAccountAddressFragment(MyAccountAddressFragment myAccountAddressFragment) {
                MyAccountAddressFragment_MembersInjector.injectMJciAlertDialog(myAccountAddressFragment, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
                return myAccountAddressFragment;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$MyAccountProfilePictureFragmentV3SubcomponentFactory */
        private final class MyAccountProfilePictureFragmentV3SubcomponentFactory implements C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent.Factory {
            private MyAccountProfilePictureFragmentV3SubcomponentFactory() {
            }

            public C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent create(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3) {
                Preconditions.checkNotNull(myAccountProfilePictureFragmentV3);
                return new MyAccountProfilePictureFragmentV3SubcomponentImpl(myAccountProfilePictureFragmentV3);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$MyAccountProfilePictureFragmentV3SubcomponentImpl */
        private final class MyAccountProfilePictureFragmentV3SubcomponentImpl implements C1789x6faeb380.MyAccountProfilePictureFragmentV3Subcomponent {
            private MyAccountProfilePictureFragmentV3SubcomponentImpl(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3) {
            }

            public void inject(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3) {
                injectMyAccountProfilePictureFragmentV3(myAccountProfilePictureFragmentV3);
            }

            private MyAccountProfilePictureFragmentV3 injectMyAccountProfilePictureFragmentV3(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3) {
                MyAccountProfilePictureFragmentV3_MembersInjector.injectMJciAlertDialog(myAccountProfilePictureFragmentV3, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
                return myAccountProfilePictureFragmentV3;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$HelpFragmentGlobalSubcomponentFactory */
        private final class HelpFragmentGlobalSubcomponentFactory implements HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent.Factory {
            private HelpFragmentGlobalSubcomponentFactory() {
            }

            public HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent create(HelpFragmentGlobal helpFragmentGlobal) {
                Preconditions.checkNotNull(helpFragmentGlobal);
                return new HelpFragmentGlobalSubcomponentImpl(helpFragmentGlobal);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$HomePageActivitySubcomponentImpl$HelpFragmentGlobalSubcomponentImpl */
        private final class HelpFragmentGlobalSubcomponentImpl implements HomePageFragmentBuilderModule_ContributeHelpFragmentGlobal.HelpFragmentGlobalSubcomponent {
            private HelpFragmentGlobalSubcomponentImpl(HelpFragmentGlobal helpFragmentGlobal) {
            }

            public void inject(HelpFragmentGlobal helpFragmentGlobal) {
                injectHelpFragmentGlobal(helpFragmentGlobal);
            }

            private HelpFragmentGlobal injectHelpFragmentGlobal(HelpFragmentGlobal helpFragmentGlobal) {
                HelpFragmentGlobal_MembersInjector.injectMJciAlertDialog(helpFragmentGlobal, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
                return helpFragmentGlobal;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentFactory */
    private final class AlexaActivitySubcomponentFactory implements ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent.Factory {
        private AlexaActivitySubcomponentFactory() {
        }

        public ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent create(AlexaActivity alexaActivity) {
            Preconditions.checkNotNull(alexaActivity);
            return new AlexaActivitySubcomponentImpl(alexaActivity);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentImpl */
    private final class AlexaActivitySubcomponentImpl implements ActivityBuilderModule_ContributeAlexaActivity.AlexaActivitySubcomponent {
        private Provider<AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent.Factory> alexaLinkFragmentSubcomponentFactoryProvider;
        private Provider<AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent.Factory> alexaLinkedFragmentSubcomponentFactoryProvider;
        private Provider<AlexaViewModel> alexaViewModelProvider;
        private Provider<AppFlipViewModel> appFlipViewModelProvider;
        private Provider<MainViewModel> mainViewModelProvider;
        private Provider<AlexaApi> provideAlexaApiProvider;
        private Provider<AppFlipApi> provideAppFlipApiProvider;
        private Provider<MainApi> provideRefreshTokenApiProvider;

        private AlexaActivitySubcomponentImpl(AlexaActivity alexaActivity) {
            initialize(alexaActivity);
        }

        private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
            return MapBuilder.newMapBuilder(9).put(CoreActivity.class, DaggerAppComponent.this.coreActivitySubcomponentFactoryProvider).put(UserManagementActivity.class, DaggerAppComponent.this.userManagementActivitySubcomponentFactoryProvider).put(HomePageActivity.class, DaggerAppComponent.this.homePageActivitySubcomponentFactoryProvider).put(AlexaActivity.class, DaggerAppComponent.this.alexaActivitySubcomponentFactoryProvider).put(FcmService.class, DaggerAppComponent.this.fcmServiceSubcomponentFactoryProvider).put(GeoFenceTransitionNetworkJobService.class, DaggerAppComponent.this.geoFenceTransitionNetworkJobServiceSubcomponentFactoryProvider).put(AppEventService.class, DaggerAppComponent.this.appEventServiceSubcomponentFactoryProvider).put(AlexaLinkFragment.class, this.alexaLinkFragmentSubcomponentFactoryProvider).put(AlexaLinkedFragment.class, this.alexaLinkedFragmentSubcomponentFactoryProvider).build();
        }

        private DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
            return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.emptyMap());
        }

        private Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> mapOfClassOfAndProviderOfViewModel() {
            return MapBuilder.newMapBuilder(3).put(MainViewModel.class, this.mainViewModelProvider).put(AlexaViewModel.class, this.alexaViewModelProvider).put(AppFlipViewModel.class, this.appFlipViewModelProvider).build();
        }

        /* access modifiers changed from: private */
        public ViewModelProviderFactory viewModelProviderFactory() {
            return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
        }

        private void initialize(AlexaActivity alexaActivity) {
            this.alexaLinkFragmentSubcomponentFactoryProvider = new Provider<AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent.Factory>() {
                public AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent.Factory get() {
                    return new AlexaLinkFragmentSubcomponentFactory();
                }
            };
            this.alexaLinkedFragmentSubcomponentFactoryProvider = new Provider<AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent.Factory>() {
                public AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent.Factory get() {
                    return new AlexaLinkedFragmentSubcomponentFactory();
                }
            };
            Provider<MainApi> provider = DoubleCheck.provider(MainModule_ProvideRefreshTokenApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideRefreshTokenApiProvider = provider;
            this.mainViewModelProvider = MainViewModel_Factory.create(provider);
            Provider<AlexaApi> provider2 = DoubleCheck.provider(AlexaModule_ProvideAlexaApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideAlexaApiProvider = provider2;
            this.alexaViewModelProvider = AlexaViewModel_Factory.create(provider2);
            Provider<AppFlipApi> provider3 = DoubleCheck.provider(AppFlipModule_ProvideAppFlipApiFactory.create(DaggerAppComponent.this.provideRetrofitProvider));
            this.provideAppFlipApiProvider = provider3;
            this.appFlipViewModelProvider = AppFlipViewModel_Factory.create(provider3);
        }

        public void inject(AlexaActivity alexaActivity) {
            injectAlexaActivity(alexaActivity);
        }

        private AlexaActivity injectAlexaActivity(AlexaActivity alexaActivity) {
            DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(alexaActivity, dispatchingAndroidInjectorOfObject());
            CoreActivity_MembersInjector.injectProviderFactory(alexaActivity, viewModelProviderFactory());
            CoreActivity_MembersInjector.injectMAmplitudeUtil(alexaActivity, (AmplitudeUtil) DaggerAppComponent.this.provideAmplitudeUtilProvider.get());
            CoreActivity_MembersInjector.injectMClientFactory(alexaActivity, (ClientFactory) DaggerAppComponent.this.provideClientFactoryProvider.get());
            CoreActivity_MembersInjector.injectMJciAlertDialog(alexaActivity, (JCIAlertDialog) DaggerAppComponent.this.provideJCIAlertDialogProvider.get());
            return alexaActivity;
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentImpl$AlexaLinkFragmentSubcomponentFactory */
        private final class AlexaLinkFragmentSubcomponentFactory implements AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent.Factory {
            private AlexaLinkFragmentSubcomponentFactory() {
            }

            public AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent create(AlexaLinkFragment alexaLinkFragment) {
                Preconditions.checkNotNull(alexaLinkFragment);
                return new AlexaLinkFragmentSubcomponentImpl(alexaLinkFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentImpl$AlexaLinkFragmentSubcomponentImpl */
        private final class AlexaLinkFragmentSubcomponentImpl implements AlexaFragmentBuilderModule_ContributeAlexaLinkFragment.AlexaLinkFragmentSubcomponent {
            private AlexaLinkFragmentSubcomponentImpl(AlexaLinkFragment alexaLinkFragment) {
            }

            public void inject(AlexaLinkFragment alexaLinkFragment) {
                injectAlexaLinkFragment(alexaLinkFragment);
            }

            private AlexaLinkFragment injectAlexaLinkFragment(AlexaLinkFragment alexaLinkFragment) {
                AlexaLinkFragment_MembersInjector.injectProviderFactory(alexaLinkFragment, AlexaActivitySubcomponentImpl.this.viewModelProviderFactory());
                return alexaLinkFragment;
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentImpl$AlexaLinkedFragmentSubcomponentFactory */
        private final class AlexaLinkedFragmentSubcomponentFactory implements AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent.Factory {
            private AlexaLinkedFragmentSubcomponentFactory() {
            }

            public AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent create(AlexaLinkedFragment alexaLinkedFragment) {
                Preconditions.checkNotNull(alexaLinkedFragment);
                return new AlexaLinkedFragmentSubcomponentImpl(alexaLinkedFragment);
            }
        }

        /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AlexaActivitySubcomponentImpl$AlexaLinkedFragmentSubcomponentImpl */
        private final class AlexaLinkedFragmentSubcomponentImpl implements AlexaFragmentBuilderModule_ContributeAlexaLinkedFragment.AlexaLinkedFragmentSubcomponent {
            private AlexaLinkedFragmentSubcomponentImpl(AlexaLinkedFragment alexaLinkedFragment) {
            }

            public void inject(AlexaLinkedFragment alexaLinkedFragment) {
                injectAlexaLinkedFragment(alexaLinkedFragment);
            }

            private AlexaLinkedFragment injectAlexaLinkedFragment(AlexaLinkedFragment alexaLinkedFragment) {
                AlexaLinkedFragment_MembersInjector.injectProviderFactory(alexaLinkedFragment, AlexaActivitySubcomponentImpl.this.viewModelProviderFactory());
                return alexaLinkedFragment;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$FcmServiceSubcomponentFactory */
    private final class FcmServiceSubcomponentFactory implements ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent.Factory {
        private FcmServiceSubcomponentFactory() {
        }

        public ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent create(FcmService fcmService) {
            Preconditions.checkNotNull(fcmService);
            return new FcmServiceSubcomponentImpl(fcmService);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$FcmServiceSubcomponentImpl */
    private final class FcmServiceSubcomponentImpl implements ServiceBuilderModule_ContributeFcmService.FcmServiceSubcomponent {
        private FcmServiceSubcomponentImpl(FcmService fcmService) {
        }

        public void inject(FcmService fcmService) {
            injectFcmService(fcmService);
        }

        private FcmService injectFcmService(FcmService fcmService) {
            FcmService_MembersInjector.injectModelRepository(fcmService, (ModelRepository) DaggerAppComponent.this.provideModelRepositoryProvider.get());
            FcmService_MembersInjector.injectMNotificationBuilder(fcmService, (NotificationBuilder) DaggerAppComponent.this.provideNotificationBuilderProvider.get());
            FcmService_MembersInjector.injectMDeepLinkFactory(fcmService, (DeepLinkFactory) DaggerAppComponent.this.provideDeepLinkFactoryProvider.get());
            return fcmService;
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$GeoFenceTransitionNetworkJobServiceSubcomponentFactory */
    private final class GeoFenceTransitionNetworkJobServiceSubcomponentFactory implements C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent.Factory {
        private GeoFenceTransitionNetworkJobServiceSubcomponentFactory() {
        }

        public C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent create(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService) {
            Preconditions.checkNotNull(geoFenceTransitionNetworkJobService);
            return new GeoFenceTransitionNetworkJobServiceSubcomponentImpl(geoFenceTransitionNetworkJobService);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$GeoFenceTransitionNetworkJobServiceSubcomponentImpl */
    private final class GeoFenceTransitionNetworkJobServiceSubcomponentImpl implements C1786xd20ff6b7.GeoFenceTransitionNetworkJobServiceSubcomponent {
        private GeoFenceTransitionNetworkJobServiceSubcomponentImpl(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService) {
        }

        public void inject(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService) {
            injectGeoFenceTransitionNetworkJobService(geoFenceTransitionNetworkJobService);
        }

        private GeoFenceTransitionNetworkJobService injectGeoFenceTransitionNetworkJobService(GeoFenceTransitionNetworkJobService geoFenceTransitionNetworkJobService) {
            GeoFenceTransitionNetworkJobService_MembersInjector.injectMBaseMainApi(geoFenceTransitionNetworkJobService, (BaseMainApi) DaggerAppComponent.this.provideRefreshTokenApiProvider.get());
            return geoFenceTransitionNetworkJobService;
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AppEventServiceSubcomponentFactory */
    private final class AppEventServiceSubcomponentFactory implements ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent.Factory {
        private AppEventServiceSubcomponentFactory() {
        }

        public ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent create(AppEventService appEventService) {
            Preconditions.checkNotNull(appEventService);
            return new AppEventServiceSubcomponentImpl(appEventService);
        }
    }

    /* renamed from: com.jch.racWiFi.di.component.DaggerAppComponent$AppEventServiceSubcomponentImpl */
    private final class AppEventServiceSubcomponentImpl implements ServiceBuilderModule_ContributeAppEventService.AppEventServiceSubcomponent {
        private AppEventServiceSubcomponentImpl(AppEventService appEventService) {
        }

        public void inject(AppEventService appEventService) {
            injectAppEventService(appEventService);
        }

        private AppEventService injectAppEventService(AppEventService appEventService) {
            AppEventService_MembersInjector.injectMAmplitudeUtil(appEventService, (AmplitudeUtil) DaggerAppComponent.this.provideAmplitudeUtilProvider.get());
            AppEventService_MembersInjector.injectMClientFactory(appEventService, (ClientFactory) DaggerAppComponent.this.provideClientFactoryProvider.get());
            return appEventService;
        }
    }
}
