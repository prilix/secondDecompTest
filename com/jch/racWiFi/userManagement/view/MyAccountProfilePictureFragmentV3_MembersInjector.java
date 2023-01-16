package com.jch.racWiFi.userManagement.view;

import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MyAccountProfilePictureFragmentV3_MembersInjector implements MembersInjector<MyAccountProfilePictureFragmentV3> {
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;

    public MyAccountProfilePictureFragmentV3_MembersInjector(Provider<JCIAlertDialog> provider) {
        this.mJciAlertDialogProvider = provider;
    }

    public static MembersInjector<MyAccountProfilePictureFragmentV3> create(Provider<JCIAlertDialog> provider) {
        return new MyAccountProfilePictureFragmentV3_MembersInjector(provider);
    }

    public void injectMembers(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3) {
        injectMJciAlertDialog(myAccountProfilePictureFragmentV3, this.mJciAlertDialogProvider.get());
    }

    public static void injectMJciAlertDialog(MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3, JCIAlertDialog jCIAlertDialog) {
        myAccountProfilePictureFragmentV3.mJciAlertDialog = jCIAlertDialog;
    }
}
