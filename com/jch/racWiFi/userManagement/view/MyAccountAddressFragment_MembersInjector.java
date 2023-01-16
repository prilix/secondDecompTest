package com.jch.racWiFi.userManagement.view;

import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MyAccountAddressFragment_MembersInjector implements MembersInjector<MyAccountAddressFragment> {
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;

    public MyAccountAddressFragment_MembersInjector(Provider<JCIAlertDialog> provider) {
        this.mJciAlertDialogProvider = provider;
    }

    public static MembersInjector<MyAccountAddressFragment> create(Provider<JCIAlertDialog> provider) {
        return new MyAccountAddressFragment_MembersInjector(provider);
    }

    public void injectMembers(MyAccountAddressFragment myAccountAddressFragment) {
        injectMJciAlertDialog(myAccountAddressFragment, this.mJciAlertDialogProvider.get());
    }

    public static void injectMJciAlertDialog(MyAccountAddressFragment myAccountAddressFragment, JCIAlertDialog jCIAlertDialog) {
        myAccountAddressFragment.mJciAlertDialog = jCIAlertDialog;
    }
}
