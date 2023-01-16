package com.jch.racWiFi.settings.view;

import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HelpFragmentGlobal_MembersInjector implements MembersInjector<HelpFragmentGlobal> {
    private final Provider<JCIAlertDialog> mJciAlertDialogProvider;

    public HelpFragmentGlobal_MembersInjector(Provider<JCIAlertDialog> provider) {
        this.mJciAlertDialogProvider = provider;
    }

    public static MembersInjector<HelpFragmentGlobal> create(Provider<JCIAlertDialog> provider) {
        return new HelpFragmentGlobal_MembersInjector(provider);
    }

    public void injectMembers(HelpFragmentGlobal helpFragmentGlobal) {
        injectMJciAlertDialog(helpFragmentGlobal, this.mJciAlertDialogProvider.get());
    }

    public static void injectMJciAlertDialog(HelpFragmentGlobal helpFragmentGlobal, JCIAlertDialog jCIAlertDialog) {
        helpFragmentGlobal.mJciAlertDialog = jCIAlertDialog;
    }
}
