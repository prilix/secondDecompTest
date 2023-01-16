package com.jch.racWiFi.p010di.module.view_model.module;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.linking.google.view_model.AppFlipViewModel;
import com.jch.racWiFi.p010di.key.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
/* renamed from: com.jch.racWiFi.di.module.view_model.module.AppFlipViewModelsModule */
public abstract class AppFlipViewModelsModule {
    @IntoMap
    @ViewModelKey(AppFlipViewModel.class)
    @Binds
    public abstract C0534ViewModel bindAppFlipViewModel(AppFlipViewModel appFlipViewModel);
}
