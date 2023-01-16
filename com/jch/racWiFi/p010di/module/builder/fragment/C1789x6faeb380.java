package com.jch.racWiFi.p010di.module.builder.fragment;

import com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MyAccountProfilePictureFragmentV3Subcomponent.class})
/* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountProfilePictureFragmentV3 */
public abstract class C1789x6faeb380 {

    @Subcomponent
    /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountProfilePictureFragmentV3$MyAccountProfilePictureFragmentV3Subcomponent */
    public interface MyAccountProfilePictureFragmentV3Subcomponent extends AndroidInjector<MyAccountProfilePictureFragmentV3> {

        @Subcomponent.Factory
        /* renamed from: com.jch.racWiFi.di.module.builder.fragment.HomePageFragmentBuilderModule_ContributeMyAccountProfilePictureFragmentV3$MyAccountProfilePictureFragmentV3Subcomponent$Factory */
        public interface Factory extends AndroidInjector.Factory<MyAccountProfilePictureFragmentV3> {
        }
    }

    /* access modifiers changed from: package-private */
    @Binds
    @IntoMap
    @ClassKey(MyAccountProfilePictureFragmentV3.class)
    public abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(MyAccountProfilePictureFragmentV3Subcomponent.Factory factory);

    private C1789x6faeb380() {
    }
}
