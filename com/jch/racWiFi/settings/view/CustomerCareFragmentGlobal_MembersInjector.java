package com.jch.racWiFi.settings.view;

import com.jch.racWiFi.fcm.util.Binder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CustomerCareFragmentGlobal_MembersInjector implements MembersInjector<CustomerCareFragmentGlobal> {
    private final Provider<Binder> mBinderProvider;

    public CustomerCareFragmentGlobal_MembersInjector(Provider<Binder> provider) {
        this.mBinderProvider = provider;
    }

    public static MembersInjector<CustomerCareFragmentGlobal> create(Provider<Binder> provider) {
        return new CustomerCareFragmentGlobal_MembersInjector(provider);
    }

    public void injectMembers(CustomerCareFragmentGlobal customerCareFragmentGlobal) {
        injectMBinder(customerCareFragmentGlobal, this.mBinderProvider.get());
    }

    public static void injectMBinder(CustomerCareFragmentGlobal customerCareFragmentGlobal, Binder binder) {
        customerCareFragmentGlobal.mBinder = binder;
    }
}
