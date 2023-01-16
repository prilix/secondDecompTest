package com.jch.racWiFi.settings.view;

import com.jch.racWiFi.fcm.util.Binder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ServiceRequestFragment_MembersInjector implements MembersInjector<ServiceRequestFragment> {
    private final Provider<Binder> mBinderProvider;

    public ServiceRequestFragment_MembersInjector(Provider<Binder> provider) {
        this.mBinderProvider = provider;
    }

    public static MembersInjector<ServiceRequestFragment> create(Provider<Binder> provider) {
        return new ServiceRequestFragment_MembersInjector(provider);
    }

    public void injectMembers(ServiceRequestFragment serviceRequestFragment) {
        injectMBinder(serviceRequestFragment, this.mBinderProvider.get());
    }

    public static void injectMBinder(ServiceRequestFragment serviceRequestFragment, Binder binder) {
        serviceRequestFragment.mBinder = binder;
    }
}
