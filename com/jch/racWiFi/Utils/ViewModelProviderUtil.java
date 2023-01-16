package com.jch.racWiFi.Utils;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ViewModelProviderUtil {
    public static <T extends C0534ViewModel> T getViewModelInstance(ViewModelStoreOwner viewModelStoreOwner, Class<T> cls) {
        return new ViewModelProvider(viewModelStoreOwner).get(cls);
    }

    public static <T extends C0534ViewModel> T getViewModelInstance(ViewModelStoreOwner viewModelStoreOwner, ViewModelProvider.Factory factory, Class<T> cls) {
        return new ViewModelProvider(viewModelStoreOwner, factory).get(cls);
    }
}
