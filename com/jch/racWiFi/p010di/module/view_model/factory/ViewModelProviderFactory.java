package com.jch.racWiFi.p010di.module.view_model.factory;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/* renamed from: com.jch.racWiFi.di.module.view_model.factory.ViewModelProviderFactory */
public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>> map) {
        this.creators = map;
    }

    public <T extends C0534ViewModel> T create(Class<T> cls) {
        Provider provider = this.creators.get(cls);
        if (provider == null) {
            Iterator<Map.Entry<Class<? extends C0534ViewModel>, Provider<C0534ViewModel>>> it = this.creators.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (cls.isAssignableFrom((Class) next.getKey())) {
                    provider = (Provider) next.getValue();
                    break;
                }
            }
        }
        if (provider != null) {
            try {
                return (C0534ViewModel) provider.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("unknown model class " + cls);
        }
    }
}
