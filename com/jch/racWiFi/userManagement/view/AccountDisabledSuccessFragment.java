package com.jch.racWiFi.userManagement.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch_hitachi.aircloudglobal.R;

public class AccountDisabledSuccessFragment extends GenericFragment {
    private Handler mDelayHandler = new Handler();
    private Handler mHandlerDelayForLogin;
    private Unbinder mUnbinder;

    public static AccountDisabledSuccessFragment newInstance() {
        AccountDisabledSuccessFragment accountDisabledSuccessFragment = new AccountDisabledSuccessFragment();
        accountDisabledSuccessFragment.setNewBundleAsArgument();
        return accountDisabledSuccessFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.account_disabled_success_vd, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        Handler handler = new Handler();
        this.mHandlerDelayForLogin = handler;
        handler.postDelayed(new Runnable() {
            public void run() {
                AccountDisabledSuccessFragment.this.mFragmentToActivityCallback.logOutFromApplication();
            }
        }, 3000);
    }

    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        super.onStop();
        this.mHandlerDelayForLogin.removeCallbacksAndMessages((Object) null);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mDelayHandler.removeCallbacksAndMessages((Object) null);
        this.mUnbinder.unbind();
    }
}
