package com.jch.racWiFi.userManagement.view.forgot_password;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.GenericFragment;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordResetSuccessFragment extends GenericFragment {
    public static boolean isPasswordResetSuccessful;

    public static ForgotPasswordResetSuccessFragment newInstance() {
        ForgotPasswordResetSuccessFragment forgotPasswordResetSuccessFragment = new ForgotPasswordResetSuccessFragment();
        forgotPasswordResetSuccessFragment.setNewBundleAsArgument();
        return forgotPasswordResetSuccessFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.password_changed_vd, viewGroup, false);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ForgotPasswordResetSuccessFragment.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_global_loginFragment2);
            }
        }, 2000);
        isPasswordResetSuccessful = true;
        return inflate;
    }
}
