package com.jch.racWiFi.userManagement.view.activate_user;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch_hitachi.aircloudglobal.R;

public class AccountActivatedSuccessFragment extends GenericFragment {
    private Handler mDelayHandler = new Handler();
    private Handler mHandlerDelayForLogin;
    @BindView(2131363857)
    TextView mSubTitle;
    @BindView(2131363858)
    TextView mSubTitleTwo;
    @BindView(2131364807)
    TextView mTitle;
    private Unbinder mUnbinder;

    public static AccountActivatedSuccessFragment newInstance() {
        AccountActivatedSuccessFragment accountActivatedSuccessFragment = new AccountActivatedSuccessFragment();
        accountActivatedSuccessFragment.setNewBundleAsArgument();
        return accountActivatedSuccessFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_onboarding_success, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mSubTitleTwo.setVisibility(4);
        this.mSubTitle.setText(getString(R.string.activateAccount_lbl_accountActivatedSuccessfully));
        this.mTitle.setText(getString(R.string.activateAccount_lbl_accountActivated));
        return inflate;
    }

    public void onStart() {
        super.onStart();
        Handler handler = new Handler();
        this.mHandlerDelayForLogin = handler;
        handler.postDelayed(new Runnable() {
            public void run() {
                AccountActivatedSuccessFragment.this.mFragmentToActivityCallback.moveToHomePageActivity(true, false);
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
