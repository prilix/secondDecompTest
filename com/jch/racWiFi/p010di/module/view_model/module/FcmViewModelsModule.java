package com.jch.racWiFi.p010di.module.view_model.module;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.fcm.view_model.FcmViewModel;
import com.jch.racWiFi.p010di.key.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
/* renamed from: com.jch.racWiFi.di.module.view_model.module.FcmViewModelsModule */
public abstract class FcmViewModelsModule {
    @IntoMap
    @ViewModelKey(FcmViewModel.class)
    @Binds
    public abstract C0534ViewModel bindFcmViewModel(FcmViewModel fcmViewModel);
}
