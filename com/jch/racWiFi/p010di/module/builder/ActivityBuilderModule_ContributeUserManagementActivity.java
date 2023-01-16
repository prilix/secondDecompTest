package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.fcm.module.BinderModule;
import com.jch.racWiFi.main.module.MainModule;
import com.jch.racWiFi.main.view_model.module.MainModelsModule;
import com.jch.racWiFi.p010di.module.builder.fragment.UserManagementFragmentBuilderModule;
import com.jch.racWiFi.p010di.scope.BaseScope;
import com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {UserManagementActivitySubcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeUserManagementActivity */
public abstract class ActivityBuilderModule_ContributeUserManagementActivity {

    @Subcomponent(modules = {MainModelsModule.class, MainModule.class, UserManagementFragmentBuilderModule.class, BinderModule.class})
    @BaseScope
    /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeUserManagementActivity$UserManagementActivitySubcomponent */
    public interface UserManagementActivitySubcomponent extends AndroidInjector<UserManagementActivity> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.ActivityBuilderModule_ContributeUserManagementActivity$UserManagementActivitySubcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<UserManagementActivity> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(UserManagementActivity.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(UserManagementActivitySubcomponent.Factory factory);

    private ActivityBuilderModule_ContributeUserManagementActivity() {
    }
}
