package com.jch.racWiFi.userManagement.view.viewImpl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Presenter.PrivacyPolicyPresenter;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.TutorialFragment;
import com.jch.racWiFi.Utils.SessionManager;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.PrivacyPolicyCustomDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.FragmentLoginBinding;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch.racWiFi.settings.view.LanguageSelectionDialog;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch.racWiFi.userManagement.model.UserLoginDataModel;
import com.jch.racWiFi.userManagement.presenter.UserLoginPresenterV2;
import com.jch.racWiFi.userManagement.view.PrivacyPolicyFragment;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import com.jch.racWiFi.userManagement.view.activate_user.ActivateAccountParcelData;
import com.jch.racWiFi.userManagement.view.forgot_password.ForgotPasswordResetSuccessFragment;
import com.jch_hitachi.aircloudglobal.R;
import java.util.HashMap;

public class LoginFragment extends GenericFragment implements UserLoginPresenterV2.IUserLoginPresenter, TextWatcher, PrivacyPolicyPresenter.IPrivacyPolicyPresenter {
    private boolean homePageLaunched = false;
    /* access modifiers changed from: private */
    public boolean isEmailSelected = false;
    /* access modifiers changed from: private */
    public AppCompatActivity mActivity;
    /* access modifiers changed from: private */
    public FragmentLoginBinding mBinding;
    private boolean mIsHasFocusEmail;
    private boolean mIsHasFocusMobileNumber;
    private boolean mIsHasFocusPassword;
    private LanguageSelectionViewHolder mLanguageSelectionViewHolder;
    private PrivacyPolicyPresenter mPrivacyPolicyPresenter;
    private SessionManager mSessionManager;
    private long mStartTime;
    private long mStartTimeEmail;
    private long mStartTimeMobileNumber;
    private long mStartTimePassword;
    private UserLoginPresenterV2 mUserLoginPresenter;
    private PrivacyPolicyCustomDialog privacyPolicyCustomDialog;

    private void initDebugData() {
    }

    static /* synthetic */ boolean lambda$showInternetAlert$16(Dialog dialog, View view) {
        return true;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onLoginApiInitiate(boolean z) {
    }

    public void onLongClickHeading() {
    }

    public void onNetworkCallSuccess() {
    }

    class LanguageSelectionViewHolder {
        private final FragmentLoginBinding binding;

        public void onClickLanguageSelection() {
            if (!Constants.isTutorialFragmentShowing) {
                languageSelectionDialog();
            }
        }

        public LanguageSelectionViewHolder(FragmentLoginBinding fragmentLoginBinding) {
            this.binding = fragmentLoginBinding;
            fragmentLoginBinding.layoutLanguageSelection.setOnClickListener(new C2622x7103a9(this));
        }

        /* renamed from: lambda$new$0$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment$LanguageSelectionViewHolder */
        public /* synthetic */ void mo33232xeb5a1cde(View view) {
            onClickLanguageSelection();
        }

        public void updateViews() {
            LanguageModel languageModelBasedOnLocaleIfPresentInList = LocaleConfiguration.LanguageSelectionUtils.getLanguageModelBasedOnLocaleIfPresentInList(LocaleConfiguration.getCurrentAppLocale());
            this.binding.textViewLanguageItem.setText(languageModelBasedOnLocaleIfPresentInList.getLanguageStringRes());
            this.binding.textViewCountryNameLogin.setText(languageModelBasedOnLocaleIfPresentInList.getEnglishStringRes());
            ViewUtils.convertDpToPixel(3.0f, this.binding.getRoot().getContext());
        }

        private void languageSelectionDialog() {
            LanguageSelectionDialog languageSelectionDialog = new LanguageSelectionDialog(LoginFragment.this.mActivity);
            languageSelectionDialog.setShowingDialogFromSettings(true);
            languageSelectionDialog.setCancelable(false);
            languageSelectionDialog.setLanguageSelectedApplyListener(new C2623x7103aa(this, languageSelectionDialog));
            languageSelectionDialog.setParentView(LoginFragment.this.mBinding.parent);
            languageSelectionDialog.show();
        }

        /* renamed from: lambda$languageSelectionDialog$1$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment$LanguageSelectionViewHolder */
        public /* synthetic */ void mo33231x86c700e1(LanguageSelectionDialog languageSelectionDialog, LanguageModel languageModel) {
            LocaleConfiguration.persistLocalization(languageModel.getLocale());
            Constants.NOT_TO_RESTART = true;
            LoginFragment.this.mFragmentToActivityCallback.reCreateUserManagementActivity();
            languageSelectionDialog.dismiss();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStartTime = System.currentTimeMillis();
        this.mSessionManager = this.mFragmentToActivityCallback.getSessionManagerInstance();
        this.mActivity = (AppCompatActivity) getActivity();
        this.mPrivacyPolicyPresenter = new PrivacyPolicyPresenter(this);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (!Constants.isTutorialFragmentShowing) {
                    Constants.privacyPolicyShown = false;
                }
                LoginFragment.this.mFragmentToActivityCallback.finishUserManagementActivity();
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (FragmentLoginBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, viewGroup, false);
        this.mLanguageSelectionViewHolder = new LanguageSelectionViewHolder(this.mBinding);
        if (!TutorialFragment.isTutorialShown()) {
            TutorialFragment.addFragmentBackStack(getCoreActivity().getSupportFragmentManager(), TutorialFragment.newInstance());
            Constants.isTutorialFragmentShowing = true;
        }
        setUIView();
        new Handler().postDelayed(new LoginFragment$$ExternalSyntheticLambda10(this), 50);
        this.privacyPolicyCustomDialog = new PrivacyPolicyCustomDialog(requireActivity());
        logEvent(Screens.SCREENS.name(), 1);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.textViewEmailForgotPassword.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda13(this));
        this.mBinding.buttonDemoMode.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda14(this));
        this.mBinding.textViewMobileNumberForgotPassword.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda15(this));
        this.mBinding.buttonCreateAccount.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda16(this));
        this.mBinding.textViewForgotPassword.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda17(this));
        this.mBinding.buttonLogin.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda18(this));
        this.mBinding.layoutCountryCode.setOnClickListener(new LoginFragment$$ExternalSyntheticLambda19(this));
        this.mBinding.imageViewHitachiLogoWhite.setOnLongClickListener(new LoginFragment$$ExternalSyntheticLambda5(this));
        this.mBinding.editTextEnterPassword.setOnFocusChangeListener(new LoginFragment$$ExternalSyntheticLambda4(this));
        this.mBinding.editTextMobileNumber.setOnFocusChangeListener(new LoginFragment$$ExternalSyntheticLambda2(this));
        this.mBinding.editTextEnterEmail.setOnFocusChangeListener(new LoginFragment$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33197x6586ea58(View view) {
        onClickEmailSelector();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33200x6b8ab5b7(View view) {
        onClickDemo();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33201x718e8116(View view) {
        onClickMobileNumberSelector();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33202x77924c75(View view) {
        onClickCreateAccount();
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33203x7d9617d4(View view) {
        onClickForgotPassword();
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33204x8399e333(View view) {
        onClickLogin(this.mBinding.buttonLogin);
    }

    /* renamed from: lambda$onViewCreated$7$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33205x899dae92(View view) {
        onClickCountryCodeSelection();
    }

    /* renamed from: lambda$onViewCreated$8$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ boolean mo33206x8fa179f1(View view) {
        onLongClickHeading();
        return false;
    }

    /* renamed from: lambda$onViewCreated$9$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33207x95a54550(View view, boolean z) {
        if (!z) {
            logEvents(Events.LOGIN_PASSWORD.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimePassword));
        } else if (!this.mIsHasFocusPassword) {
            this.mIsHasFocusPassword = true;
            this.mStartTimePassword = System.currentTimeMillis();
        }
    }

    /* renamed from: lambda$onViewCreated$10$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33198x295c1650(View view, boolean z) {
        if (!z) {
            logEvents(Events.LOGIN_MOBILE.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeMobileNumber));
        } else if (!this.mIsHasFocusMobileNumber) {
            this.mIsHasFocusMobileNumber = true;
            this.mStartTimeMobileNumber = System.currentTimeMillis();
        }
    }

    /* renamed from: lambda$onViewCreated$11$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33199x2f5fe1af(View view, boolean z) {
        if (!z) {
            logEvents(Events.LOGIN_EMAIL.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeEmail));
        } else if (!this.mIsHasFocusEmail) {
            this.mIsHasFocusEmail = true;
            this.mStartTimeEmail = System.currentTimeMillis();
        }
    }

    private void setUIView() {
        this.mBinding.editTextEnterEmail.setVisibility(4);
        this.mBinding.emailSelectionHighlight.setVisibility(4);
        this.mUserLoginPresenter = new UserLoginPresenterV2(this);
        this.mBinding.editTextMobileNumber.addTextChangedListener(this);
        this.mBinding.editTextEnterEmail.addTextChangedListener(this);
        this.mBinding.editTextEnterPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (LoginFragment.this.mBinding.editTextEnterPassword.getText().toString().isEmpty()) {
                    LoginFragment.this.mBinding.editTextEnterPassword.setErrorBackgroundDrawable();
                } else {
                    LoginFragment.this.mBinding.editTextEnterPassword.setNormalBackgroundDrawable();
                }
                if (LoginFragment.this.isEmailSelected) {
                    if (LoginFragment.this.mBinding.editTextEnterEmail.getText().toString().isEmpty() || !ValidationUtils.isEmailAddressValid(LoginFragment.this.mBinding.editTextEnterEmail.getText().toString()) || LoginFragment.this.mBinding.editTextEnterPassword.getText().length() < 8) {
                        LoginFragment.this.mBinding.buttonLogin.setEnabledLoginButton(false);
                    } else {
                        LoginFragment.this.mBinding.buttonLogin.setEnabledLoginButton(true);
                    }
                } else if (LoginFragment.this.mBinding.editTextMobileNumber.getText().toString().isEmpty() || LoginFragment.this.mBinding.editTextEnterPassword.getText().length() < 8) {
                    LoginFragment.this.mBinding.buttonLogin.setEnabledLoginButton(false);
                } else {
                    LoginFragment.this.mBinding.buttonLogin.setEnabledLoginButton(true);
                }
            }
        });
        this.mBinding.buttonLogin.setEnabledLoginButton(false);
        CountryCodeUIConfiguration.changeToDeviceDefault(requireActivity());
        updateCountryCodeSelection(CountryCodeUIConfiguration.CURRENT);
        ViewUtils.setOutlineCountryImage(this.mBinding.imageViewFlagLogin);
        this.mBinding.imageViewFlagLogin.setElevation(ViewUtils.convertDpToPixel(2.0f, requireActivity()));
    }

    /* access modifiers changed from: private */
    /* renamed from: showPrivacyPolicyDialog */
    public void mo33196xc1d86f03() {
        if (!com.jch.racWiFi.Constants._NEW_USER_ && !com.jch.racWiFi.Constants.isTutorialFragmentShowing) {
            if (!com.jch.racWiFi.Constants.privacyPolicyShown && (!CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation() || PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED)) {
                Logger.m47e("button_click", "onStart()");
                showPleaseWaitDialog();
                PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
                privacyPolicyFragment.setHideTitle(true);
                Bundle bundle = new Bundle();
                bundle.putParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY, PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
                privacyPolicyFragment.setArguments(bundle);
                privacyPolicyFragment.setCancelable(false);
                privacyPolicyFragment.show(getCoreActivity().getSupportFragmentManager(), "PrivacyPolicyFragment");
                privacyPolicyFragment.setOnClickAgree(new LoginFragment$$ExternalSyntheticLambda12(privacyPolicyFragment));
                PrivacyPolicyFragment.setPrivacyPolicyShown();
                com.jch.racWiFi.Constants.privacyPolicyShown = true;
                dismissPleaseWaitDialog();
            }
            this.mFragmentToActivityCallback.changeStatusBarColor(R.color.colorRed);
        }
    }

    static /* synthetic */ void lambda$showPrivacyPolicyDialog$12(PrivacyPolicyFragment privacyPolicyFragment, View view) {
        TutorialFragment.setTutorialShown();
        privacyPolicyFragment.dismiss();
    }

    public void onResume() {
        super.onResume();
        this.mFragmentToActivityCallback.changeStatusBarColor(R.color.colorRed);
        LanguageSelectionViewHolder languageSelectionViewHolder = this.mLanguageSelectionViewHolder;
        if (languageSelectionViewHolder != null) {
            languageSelectionViewHolder.updateViews();
        }
        String userName = this.mSessionManager.getUserName();
        if (userName != null && !userName.isEmpty()) {
            if (userName.contains("@")) {
                setEmailSelector(userName);
            } else {
                HashMap<String, String> separateCountryCodeAndPhoneNumber = ValidationUtils.separateCountryCodeAndPhoneNumber(userName);
                CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getContext(), CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), separateCountryCodeAndPhoneNumber.get("phone_code")));
                CountryCodeUIConfiguration.changeCurrentConfig(requireActivity(), getContext().getString(countryCodeUIConfigurationFromCountryObject.getCountryNameShortForm()));
                this.mBinding.editTextMobileNumber.setText(separateCountryCodeAndPhoneNumber.get("phone_number"));
                this.mBinding.textViewCountryNumberCodeLogin.setText(separateCountryCodeAndPhoneNumber.get("phone_code"));
                this.mBinding.imageViewFlagLogin.setImageResource(countryCodeUIConfigurationFromCountryObject.getCountryFlag());
            }
        }
        resetAllFields();
        CountryCodeUIConfiguration.changeToDeviceDefault(getActivity());
        updateCountryCodeSelection(CountryCodeUIConfiguration.CURRENT);
        if (com.jch.racWiFi.Constants._NEW_USER_ && com.jch.racWiFi.Constants.isAppOpenedFormInviteLink) {
            openPrivacyPolicyIfInvited();
        }
    }

    private void setEmailSelector(String str) {
        this.isEmailSelected = true;
        this.mBinding.editTextEnterEmail.setVisibility(0);
        this.mBinding.editTextMobileNumber.setVisibility(4);
        this.mBinding.emailSelectionHighlight.setVisibility(0);
        this.mBinding.mobileNumberSelectionHighlight.setVisibility(4);
        this.mBinding.layoutCountryCode.setVisibility(4);
        this.mBinding.editTextEnterPassword.setText("");
        this.mBinding.editTextEnterPassword.setNormalBackgroundDrawable();
        this.mBinding.editTextEnterEmail.setText(str);
        this.mBinding.editTextEnterEmail.setNormalBackgroundDrawable();
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    private void resetAllFields() {
        this.mBinding.editTextEnterEmail.removeTextChangedListener(this);
        this.mBinding.editTextEnterEmail.addTextChangedListener(this);
        this.mBinding.editTextEnterEmail.setNormalBackgroundDrawable();
        this.mBinding.editTextMobileNumber.removeTextChangedListener(this);
        this.mBinding.editTextMobileNumber.addTextChangedListener(this);
        this.mBinding.editTextMobileNumber.setNormalBackgroundDrawable();
        this.mBinding.editTextEnterPassword.removeTextChangedListener(this);
        this.mBinding.editTextEnterPassword.addTextChangedListener(this);
        this.mBinding.editTextEnterPassword.setNormalBackgroundDrawable();
        if (ForgotPasswordResetSuccessFragment.isPasswordResetSuccessful) {
            this.mBinding.editTextEnterEmail.setText("");
            this.mBinding.editTextMobileNumber.setText("");
            this.mBinding.editTextEnterPassword.setText("");
            this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
            ForgotPasswordResetSuccessFragment.isPasswordResetSuccessful = false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        PrivacyPolicyPresenter privacyPolicyPresenter = this.mPrivacyPolicyPresenter;
        if (privacyPolicyPresenter != null) {
            privacyPolicyPresenter.removeCallbacks();
        }
        UserLoginPresenterV2 userLoginPresenterV2 = this.mUserLoginPresenter;
        if (userLoginPresenterV2 != null) {
            userLoginPresenterV2.removeCallbacks();
        }
    }

    public void onClickEmailSelector() {
        this.isEmailSelected = true;
        this.mBinding.editTextEnterEmail.setVisibility(0);
        this.mBinding.editTextMobileNumber.setVisibility(4);
        this.mBinding.emailSelectionHighlight.setVisibility(0);
        this.mBinding.mobileNumberSelectionHighlight.setVisibility(4);
        this.mBinding.layoutCountryCode.setVisibility(4);
        this.mBinding.editTextEnterPassword.setText("");
        this.mBinding.editTextEnterPassword.setNormalBackgroundDrawable();
        this.mBinding.editTextEnterEmail.setText("");
        this.mBinding.editTextEnterEmail.setNormalBackgroundDrawable();
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
        logEvents(Events.TOGGLE_CHOSEN_LOGIN_SCREEN_EMAIL.name(), 0);
    }

    public void onClickDemo() {
        this.mSessionManager.setDemoMode(true);
        logEvents(Events.CLICK_ON_DEMO_MODE.name(), 0);
        this.mFragmentToActivityCallback.moveToHomePageActivity(true, false);
    }

    public void onClickMobileNumberSelector() {
        this.isEmailSelected = false;
        this.mBinding.editTextEnterEmail.setVisibility(4);
        this.mBinding.editTextMobileNumber.setVisibility(0);
        this.mBinding.emailSelectionHighlight.setVisibility(4);
        this.mBinding.mobileNumberSelectionHighlight.setVisibility(0);
        this.mBinding.layoutCountryCode.setVisibility(0);
        this.mBinding.editTextEnterPassword.setText("");
        this.mBinding.editTextEnterPassword.setNormalBackgroundDrawable();
        this.mBinding.editTextMobileNumber.setText("");
        this.mBinding.editTextMobileNumber.setNormalBackgroundDrawable();
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
        logEvents(Events.TOGGLE_CHOSEN_LOGIN_SCREEN_MOBILE.name(), 0);
    }

    public void onClickCreateAccount() {
        logEvents(Events.REGISTRATION_STEP_1.name(), 0);
        if (!com.jch.racWiFi.Constants.isTutorialFragmentShowing) {
            if (CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation()) {
                showPleaseWaitDialog();
                this.mPrivacyPolicyPresenter.checkForPrivacyPolicyUpdate(this);
                return;
            }
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_loginFragment_to_sign_up_nav_graph);
        }
    }

    private void showPrivacyPolicyDialogOnSignUp() {
        if (!this.privacyPolicyCustomDialog.isShowing()) {
            this.privacyPolicyCustomDialog.init(getContext(), PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
            this.privacyPolicyCustomDialog.show();
            this.privacyPolicyCustomDialog.setOnAgreeListener(new LoginFragment$$ExternalSyntheticLambda6(this));
            this.privacyPolicyCustomDialog.setOnCancelListener(new LoginFragment$$ExternalSyntheticLambda7(this));
            com.jch.racWiFi.Constants.isAppOpenedFormInviteLink = false;
        }
    }

    /* renamed from: lambda$showPrivacyPolicyDialogOnSignUp$13$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33212xeb2ecc25() {
        TutorialFragment.setTutorialShown();
        this.privacyPolicyCustomDialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_loginFragment_to_sign_up_nav_graph);
    }

    /* renamed from: lambda$showPrivacyPolicyDialogOnSignUp$14$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33213xf1329784() {
        this.privacyPolicyCustomDialog.dismiss();
    }

    private void openPrivacyPolicyIfInvited() {
        if (com.jch.racWiFi.Constants._INVITE_ || CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation()) {
            PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
            privacyPolicyFragment.setHideTitle(true);
            privacyPolicyFragment.setTakeToLogin(false);
            Bundle bundle = new Bundle();
            bundle.putParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY, PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
            privacyPolicyFragment.setArguments(bundle);
            privacyPolicyFragment.setCancelable(false);
            privacyPolicyFragment.show(this.mActivity.getSupportFragmentManager(), "PrivacyPolicyFragment");
            privacyPolicyFragment.setOnClickAgree(new LoginFragment$$ExternalSyntheticLambda1(this, privacyPolicyFragment));
            PrivacyPolicyFragment.setPrivacyPolicyShown();
            com.jch.racWiFi.Constants.isAppOpenedFormInviteLink = false;
        }
    }

    /* renamed from: lambda$openPrivacyPolicyIfInvited$15$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33208xe40773ef(PrivacyPolicyFragment privacyPolicyFragment, View view) {
        TutorialFragment.setTutorialShown();
        privacyPolicyFragment.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_loginFragment_to_sign_up_nav_graph);
    }

    public void onClickForgotPassword() {
        logEvents(Events.FORGOT_PASSWORD.name(), 0);
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Keys.ENTRY_TIME, System.currentTimeMillis());
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_loginFragment_to_forgot_password_nav_graph, bundle);
    }

    public void onClickLogin(View view) {
        String str;
        logEvents(Events.LOGIN_TIME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTime));
        if (this.isEmailSelected) {
            str = this.mBinding.editTextEnterEmail.getText().toString().trim();
        } else {
            str = this.mBinding.editTextMobileNumber.getText().toString().trim();
        }
        String trim = this.mBinding.editTextEnterPassword.getText().toString().trim();
        if (NetworkConnectivity.isNetworkAvailable(this.mActivity.getApplicationContext())) {
            this.mUserLoginPresenter.authenticateUser(str, trim, this.isEmailSelected);
        } else {
            showInternetAlert();
        }
    }

    public void onClickCountryCodeSelection() {
        showCountryCodeSelectionDialog();
    }

    public void emptyEmailFieldCallback() {
        this.mBinding.editTextEnterEmail.setErrorBackgroundDrawable();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_emailFieldCannotBeEmpty), 0);
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    public void emptyPasswordFieldCallback() {
        this.mBinding.editTextEnterPassword.setErrorBackgroundDrawable();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_passwordCannotBeEmpty), 0);
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    public void emptyPhoneNumberFieldCallback() {
        this.mBinding.editTextMobileNumber.setErrorBackgroundDrawable();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_phnNoFieldCannotBeEmpty), 0);
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    public void invalidEmailFormat() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidEmailFormat), 0);
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    public void invalidMobileNumberFormat() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidMobNo), 0);
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(8);
    }

    public void allFieldsValidatedLogin() {
        showPleaseWaitDialog();
    }

    public void onLoginPasswordIncorrect(UserLoginDataModel.LoginFailureResponse loginFailureResponse) {
        if (loginFailureResponse.errorState.equals(UserLoginDataModel.LoginFailureResponse.UNKNOWN_USER)) {
            this.mBinding.layoutNumberOfLoginAttempts.setVisibility(0);
            this.mBinding.textViewIncorrectUserNameOrPassword.setText(R.string.login_alert_incorrectCredentialsMsg);
            this.mBinding.textNumberOfLogInAttempts.setVisibility(4);
        } else {
            this.mBinding.layoutNumberOfLoginAttempts.setVisibility(0);
            this.mBinding.textNumberOfLogInAttempts.setVisibility(4);
            this.mBinding.textViewIncorrectUserNameOrPassword.setText(R.string.login_alert_incorrectCredentialsMsg);
        }
        dismissPleaseWaitDialog();
    }

    public void onLoginUserNotVerified() {
        Toaster.makeToast(getActivity(), getString(R.string.login_alert_emailNotVerified), 0);
        dismissPleaseWaitDialog();
    }

    public void onLoginSuccessful() {
        com.jch.racWiFi.Constants.IS_DEMO_MODE = false;
        this.mSessionManager.setDemoMode(false);
        if (!this.homePageLaunched) {
            String str = this.mBinding.textViewCountryNumberCodeLogin.getText().toString() + this.mBinding.editTextMobileNumber.getText().toString();
            String obj = this.mBinding.editTextEnterEmail.getText().toString();
            if (this.isEmailSelected) {
                str = obj;
            }
            this.mSessionManager.setUserName(str);
            this.homePageLaunched = true;
            dismissPleaseWaitDialog();
            Intent intent = new Intent(this.mActivity, HomePageActivity.class);
            intent.setFlags(268468224);
            intent.putExtra(Constants.Keys.IS_LOGIN, true);
            startActivity(intent);
            this.mFragmentToActivityCallback.finishUserManagementActivity();
        }
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            showInternetAlert();
        }
        dismissPleaseWaitDialog();
    }

    private void showInternetAlert() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), LoginFragment$$ExternalSyntheticLambda8.INSTANCE);
        singleChoiceDialog.show();
    }

    public void onSoftLockHappenedFromServer() {
        dismissPleaseWaitDialog();
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(0);
        this.mBinding.textNumberOfLogInAttempts.setVisibility(0);
        this.mBinding.textViewIncorrectUserNameOrPassword.setText(R.string.login_alert_accountLocked);
        this.mBinding.textNumberOfLogInAttempts.setText(getActivity().getString(R.string.login_alert_accountSoftLockMsg));
    }

    public void onHardLockHappenedFromServer() {
        dismissPleaseWaitDialog();
        this.mBinding.layoutNumberOfLoginAttempts.setVisibility(0);
        this.mBinding.textNumberOfLogInAttempts.setVisibility(0);
        this.mBinding.textViewIncorrectUserNameOrPassword.setText(R.string.login_alert_accountLocked);
        this.mBinding.textNumberOfLogInAttempts.setText(getActivity().getString(R.string.login_alert_accountHardLockMsg));
    }

    public void onInActiveUserFailureResponse() {
        dismissPleaseWaitDialog();
        String obj = this.mBinding.editTextEnterEmail.getText().toString();
        String obj2 = this.mBinding.editTextMobileNumber.getText().toString();
        ActivateAccountParcelData activateAccountParcelData = new ActivateAccountParcelData();
        activateAccountParcelData.setEmailId(obj);
        activateAccountParcelData.setPhoneNumber(obj2);
        this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(com.jch.racWiFi.Constants.LOGIN_ACTIVITY_TO_ACTIVATION_ACTIVITY_PARCEL_DATA, new NavArgument.Builder().setDefaultValue(activateAccountParcelData).build());
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_loginFragment_to_account_activation_nav_graph);
    }

    public void serverException() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        dismissPleaseWaitDialog();
    }

    private void showCountryCodeSelectionDialog() {
        CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new LoginFragment$$ExternalSyntheticLambda9(this, countryCodeDialog));
        countryCodeDialog.setOnShowListener(new LoginFragment$$ExternalSyntheticLambda11(this));
        countryCodeDialog.show();
        countryCodeDialog.setOnDismissListener(new LoginFragment$$ExternalSyntheticLambda0(this));
        WindowManager.LayoutParams attributes = countryCodeDialog.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        countryCodeDialog.getWindow().setAttributes(attributes);
        countryCodeDialog.getWindow().addFlags(2);
    }

    /* renamed from: lambda$showCountryCodeSelectionDialog$17$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33209xcbc0a0d(CountryCodeDialog countryCodeDialog, View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
        updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfiguration);
        countryCodeDialog.dismiss();
    }

    /* renamed from: lambda$showCountryCodeSelectionDialog$18$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33210x12bfd56c(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.white_blur);
    }

    /* renamed from: lambda$showCountryCodeSelectionDialog$19$com-jch-racWiFi-userManagement-view-viewImpl-LoginFragment */
    public /* synthetic */ void mo33211x18c3a0cb(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
    }

    public void updateCountryCodeSelection(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mBinding.textViewCountryNumberCodeLogin.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mBinding.imageViewFlagLogin.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mBinding.editTextMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void updateCountryCodeSelectionOnCountryCodeChange(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mBinding.textViewCountryNumberCodeLogin.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mBinding.imageViewFlagLogin.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        if (this.mBinding.editTextMobileNumber.getText() != null) {
            this.mBinding.editTextMobileNumber.getText().clear();
        }
        this.mBinding.editTextMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void setBlankEmailToolTip() {
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mBinding.editTextEnterEmail);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mBinding.enterEmailBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.editTextEnterEmail.addTextChangedListener(genericEmptyEditTextWatcher);
    }

    public void setBlankPasswordToolTip() {
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mBinding.editTextEnterPassword);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mBinding.enterPasswordBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.editTextEnterPassword.addTextChangedListener(genericEmptyEditTextWatcher);
    }

    public void setBlankMobileNumberToolTip() {
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mBinding.editTextMobileNumber);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mBinding.enterMobileNumberBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.editTextMobileNumber.addTextChangedListener(genericEmptyEditTextWatcher);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String trim = this.mBinding.editTextEnterEmail.getText().toString().trim();
        String trim2 = this.mBinding.editTextMobileNumber.getText().toString().trim();
        String trim3 = this.mBinding.editTextEnterPassword.getText().toString().trim();
        if (this.isEmailSelected) {
            if (trim.isEmpty() || !ValidationUtils.isEmailAddressValid(this.mBinding.editTextEnterEmail.getText().toString())) {
                this.mBinding.editTextEnterEmail.setErrorBackgroundDrawable();
                this.mBinding.buttonLogin.setEnabledLoginButton(false);
                return;
            }
            this.mBinding.editTextEnterEmail.setNormalBackgroundDrawable();
            if (trim3.length() >= 8) {
                this.mBinding.buttonLogin.setEnabledLoginButton(true);
            } else {
                this.mBinding.buttonLogin.setEnabledLoginButton(false);
            }
        } else if (!trim2.isEmpty()) {
            this.mBinding.editTextMobileNumber.setNormalBackgroundDrawable();
            if (trim3.length() >= 8) {
                this.mBinding.buttonLogin.setEnabledLoginButton(true);
            } else {
                this.mBinding.buttonLogin.setEnabledLoginButton(false);
            }
        } else {
            this.mBinding.editTextMobileNumber.setErrorBackgroundDrawable();
            this.mBinding.buttonLogin.setEnabledLoginButton(false);
        }
    }

    private void setLoginButtonEnabled(boolean z) {
        this.mBinding.buttonLogin.setEnabledLoginButton(z);
    }

    public void onPrivacyPolicyReceived(PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse, int i) {
        dismissPleaseWaitDialog();
        showPrivacyPolicyDialogOnSignUp();
    }

    public void onPrivacyPolicyReceivedFailure() {
        dismissPleaseWaitDialog();
        showPrivacyPolicyDialogOnSignUp();
    }
}
