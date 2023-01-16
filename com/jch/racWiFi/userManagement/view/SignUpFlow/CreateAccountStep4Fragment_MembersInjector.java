package com.jch.racWiFi.userManagement.view.SignUpFlow;

import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CreateAccountStep4Fragment_MembersInjector implements MembersInjector<CreateAccountStep4Fragment> {
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;

    public CreateAccountStep4Fragment_MembersInjector(Provider<JCIAlertDialog> provider) {
        this.mJciAlertDialogProvider = provider;
    }

    public static MembersInjector<CreateAccountStep4Fragment> create(Provider<JCIAlertDialog> provider) {
        return new CreateAccountStep4Fragment_MembersInjector(provider);
    }

    public void injectMembers(CreateAccountStep4Fragment createAccountStep4Fragment) {
        injectMJciAlertDialog(createAccountStep4Fragment, this.mJciAlertDialogProvider.get());
    }

    public static void injectMJciAlertDialog(CreateAccountStep4Fragment createAccountStep4Fragment, JCIAlertDialog jCIAlertDialog) {
        createAccountStep4Fragment.mJciAlertDialog = jCIAlertDialog;
    }
}
