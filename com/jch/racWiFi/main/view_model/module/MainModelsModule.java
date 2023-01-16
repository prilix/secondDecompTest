package com.jch.racWiFi.main.view_model.module;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.p010di.key.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainModelsModule {
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    @Binds
    public abstract C0534ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
