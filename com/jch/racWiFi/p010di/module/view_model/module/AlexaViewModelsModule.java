package com.jch.racWiFi.p010di.module.view_model.module;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.linking.amazon.view_model.AlexaViewModel;
import com.jch.racWiFi.p010di.key.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
/* renamed from: com.jch.racWiFi.di.module.view_model.module.AlexaViewModelsModule */
public abstract class AlexaViewModelsModule {
    @IntoMap
    @ViewModelKey(AlexaViewModel.class)
    @Binds
    public abstract C0534ViewModel bindAlexaViewModel(AlexaViewModel alexaViewModel);
}
