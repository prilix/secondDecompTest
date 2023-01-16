package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.databinding.AccountCreationSuccessConstarintVdBinding;
import com.jch.racWiFi.userManagement.model.UserLoginDataModel;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.presenter.UserLoginPresenterV2;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep5SuccessFragment extends GenericFragment implements UserLoginPresenterV2.IUserLoginPresenter {
    public static final String ACCOUNT_CREATED = "ACCOUNT_CREATED";
    private boolean homePageLaunched = false;
    private final Handler mDelayHandler = new Handler();
    private Handler mHandlerDelayForLogin;
    private UserLoginPresenterV2 mUserLoginPresenter;

    public void allFieldsValidatedLogin() {
    }

    public void emptyEmailFieldCallback() {
    }

    public void emptyPasswordFieldCallback() {
    }

    public void emptyPhoneNumberFieldCallback() {
    }

    public void invalidEmailFormat() {
    }

    public void invalidMobileNumberFormat() {
    }

    public void onHardLockHappenedFromServer() {
    }

    public void onInActiveUserFailureResponse() {
    }

    public void onLoginApiInitiate(boolean z) {
    }

    public void onLoginPasswordIncorrect(UserLoginDataModel.LoginFailureResponse loginFailureResponse) {
    }

    public void onLoginUserNotVerified() {
    }

    public void onNetworkCallFailure(Throwable th) {
    }

    public void onNetworkCallSuccess() {
    }

    public void onSoftLockHappenedFromServer() {
    }

    public void serverException() {
    }

    public static CreateAccountStep5SuccessFragment newInstance() {
        CreateAccountStep5SuccessFragment createAccountStep5SuccessFragment = new CreateAccountStep5SuccessFragment();
        createAccountStep5SuccessFragment.setNewBundleAsArgument();
        return createAccountStep5SuccessFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setAtleastOneAccountWasCreatedAfterAppInstallation();
        this.mUserLoginPresenter = new UserLoginPresenterV2(this);
        return ((AccountCreationSuccessConstarintVdBinding) DataBindingUtil.inflate(layoutInflater, R.layout.account_creation_success_constarint_vd, viewGroup, false)).getRoot();
    }

    public void onStart() {
        super.onStart();
        Handler handler = new Handler();
        this.mHandlerDelayForLogin = handler;
        handler.postDelayed(new CreateAccountStep5SuccessFragment$$ExternalSyntheticLambda0(this), 3000);
    }

    /* renamed from: lambda$onStart$0$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep5SuccessFragment */
    public /* synthetic */ void mo33110x4c2c835d() {
        String str;
        boolean z;
        if (UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber == null) {
            z = true;
            str = UserRegistrationModels.UserRegistration.NEW_USER.email;
        } else {
            z = false;
            str = UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber;
        }
        this.mUserLoginPresenter.authenticateUser(str, UserRegistrationModels.UserRegistration.NEW_USER.password, z);
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
        this.mUserLoginPresenter.removeCallbacks();
        this.mDelayHandler.removeCallbacksAndMessages((Object) null);
    }

    public static boolean atleastOneAccountWasCreatedAfterAppInstallation() {
        return SharedPref.getInstance().getSharedPreferencesReader().getBoolean(ACCOUNT_CREATED, false);
    }

    public static void setAtleastOneAccountWasCreatedAfterAppInstallation() {
        Constants._NEW_USER_ = false;
        SharedPref.getInstance().getSharedPreferenceEditor().putBoolean(ACCOUNT_CREATED, true).commit();
    }

    public static void revertPrivacyPolicyShowFlags() {
        SharedPref.getInstance().getSharedPreferenceEditor().putBoolean(ACCOUNT_CREATED, false).commit();
    }

    public void onLoginSuccessful() {
        getCoreActivity().getSessionManagerInstance().setDemoMode(false);
        if (!this.homePageLaunched) {
            this.homePageLaunched = true;
            this.mFragmentToActivityCallback.moveToHomePageActivity(true, true);
        }
    }
}
