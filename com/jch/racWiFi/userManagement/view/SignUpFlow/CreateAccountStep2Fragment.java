package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.app.Dialog;
import android.content.DialogInterface;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.TutorialFragment;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.PasswordStrengthModel;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.network.PasswordStrengthCheckNetworkDispatcher;
import com.jch.racWiFi.userManagement.presenter.ManualEntryOfDetailsPresenterV2;
import com.jch.racWiFi.userManagement.view.PasswordStrengthUiLogicHolder;
import com.jch.racWiFi.userManagement.view.PrivacyPolicyFragment;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep2Fragment extends GenericFragment implements ManualEntryOfDetailsPresenterV2.ICreateAccountManualEntryOfDetailsPresenter, TextWatcher, LifecycleOwner {
    CreateTooltipContentHolder createTooltipContentHolder;
    /* access modifiers changed from: private */
    public ValidationUtils.PasswordStrength currentPasswordStrength;
    private GenericEmptyEditTextWatcher emptyEmailTextWatcher;
    private GenericEmptyEditTextWatcher emptyMobileTextWatcher;
    @BindView(2131364648)
    TextView iAcceptTermsTextView;
    private boolean isEmailSelected = false;
    private boolean isPhoneNumberFieldSelected = true;
    @BindView(2131362363)
    EditText mConfirmPassword;
    @BindView(2131362425)
    ConstraintLayout mConfirmPasswordBubbleLayout;
    @BindView(2131362137)
    Button mContinueButton;
    @BindView(2131363164)
    ConstraintLayout mCountryCodeLayout;
    @BindView(2131364069)
    TextView mCountryCodeTextView;
    @BindView(2131362920)
    ImageView mCountryFlag;
    private ManualEntryOfDetailsPresenterV2 mCreateAccountManualEntryOfDetailsPresenter;
    @BindView(2131362427)
    ConstraintLayout mEmailBubbleLayout;
    @BindView(2131362371)
    EditText mEmailEditText;
    @BindView(2131362404)
    View mEmailHighLight;
    private long mEnterTime;
    private boolean mIsHasFocusEmail;
    @BindView(2131362430)
    ConstraintLayout mMobileBubbleLayout;
    @BindView(2131362384)
    EditText mMobileNumber;
    @BindView(2131363407)
    View mMobileNumberHighlight;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131362378)
    EditText mPassword;
    @BindView(2131362433)
    ConstraintLayout mPasswordBubbleLayout;
    @BindView(2131362817)
    ImageButton mPasswordHint;
    /* access modifiers changed from: private */
    public PasswordStrengthUiLogicHolder mPasswordStrengthUiLogicHolder;
    private long mStartTimeEmail;
    @BindView(2131364559)
    TextView mTextViewStarEmail;
    @BindView(2131364560)
    TextView mTextViewStarMobile;
    private Unbinder mUnbinder;
    /* access modifiers changed from: private */
    public int selectedCountryCode = CountryCodeUIConfiguration.CURRENT.getCountryNameShortForm();
    /* access modifiers changed from: private */
    public int selectedCountryName = CountryCodeUIConfiguration.CURRENT.getCountryName();
    @BindView(2131364583)
    TextView stepNum;

    private void initDebugData() {
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public void termsAndConditionsNotAccepted() {
    }

    public CreateAccountStep2Fragment() {
        Logger.m49i("", "step2 signup");
    }

    public static CreateAccountStep2Fragment newInstance() {
        CreateAccountStep2Fragment createAccountStep2Fragment = new CreateAccountStep2Fragment();
        createAccountStep2Fragment.setNewBundleAsArgument();
        return createAccountStep2Fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CountryCodeUIConfiguration.changeToDeviceDefault(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.manual_entry_new_frame, viewGroup, false);
        this.mEnterTime = System.currentTimeMillis();
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        PasswordStrengthUiLogicHolder passwordStrengthUiLogicHolder = new PasswordStrengthUiLogicHolder(requireActivity(), inflate);
        this.mPasswordStrengthUiLogicHolder = passwordStrengthUiLogicHolder;
        passwordStrengthUiLogicHolder.hidePasswordStrengthMeter();
        ManualEntryOfDetailsPresenterV2 manualEntryOfDetailsPresenterV2 = new ManualEntryOfDetailsPresenterV2(this);
        this.mCreateAccountManualEntryOfDetailsPresenter = manualEntryOfDetailsPresenterV2;
        manualEntryOfDetailsPresenterV2.registerEventBus();
        if (this.isEmailSelected) {
            setSignUpFieldVisibility(0, 4, 0, 4, 4);
        } else {
            setSignUpFieldVisibility(4, 0, 4, 0, 0);
        }
        this.createTooltipContentHolder = new CreateTooltipContentHolder(getContext(), (View) this.mPasswordHint, getString(R.string.password_policy_hint));
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mEmailEditText);
        this.emptyEmailTextWatcher = genericEmptyEditTextWatcher;
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mEmailBubbleLayout);
        this.emptyEmailTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mEmailEditText.addTextChangedListener(this.emptyEmailTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher2 = new GenericEmptyEditTextWatcher(this.mMobileNumber);
        this.emptyMobileTextWatcher = genericEmptyEditTextWatcher2;
        genericEmptyEditTextWatcher2.setLayoutToolTip(this.mMobileBubbleLayout);
        this.emptyMobileTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mMobileNumber.addTextChangedListener(this.emptyMobileTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher3 = new GenericEmptyEditTextWatcher(this.mPassword);
        genericEmptyEditTextWatcher3.setLayoutToolTip(this.mPasswordBubbleLayout);
        genericEmptyEditTextWatcher3.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mPassword.addTextChangedListener(genericEmptyEditTextWatcher3);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher4 = new GenericEmptyEditTextWatcher(this.mConfirmPassword);
        genericEmptyEditTextWatcher4.setLayoutToolTip(this.mConfirmPasswordBubbleLayout);
        genericEmptyEditTextWatcher4.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mConfirmPassword.addTextChangedListener(genericEmptyEditTextWatcher4);
        this.mContinueButton.setEnabled(false);
        this.mEmailEditText.addTextChangedListener(this);
        this.mPassword.addTextChangedListener(this);
        this.mConfirmPassword.addTextChangedListener(this);
        this.mMobileNumber.addTextChangedListener(this);
        this.mPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().isEmpty()) {
                    CreateAccountStep2Fragment.this.mPassword.setErrorBackgroundDrawable();
                } else {
                    CreateAccountStep2Fragment.this.mPassword.setNormalBackgroundDrawable();
                }
                if (charSequence.toString().isEmpty() && CreateAccountStep2Fragment.this.mPassword.hasFocus()) {
                    CreateAccountStep2Fragment.this.currentPasswordStrength = ValidationUtils.PasswordStrength.EMPTY;
                    CreateAccountStep2Fragment createAccountStep2Fragment = CreateAccountStep2Fragment.this;
                    createAccountStep2Fragment.computeViewState(createAccountStep2Fragment.currentPasswordStrength);
                    CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.makeBackgroundNormal();
                    CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
                    CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                }
            }
        });
        this.mPassword.setOnFocusChangeListener(new CreateAccountStep2Fragment$$ExternalSyntheticLambda1(this));
        ViewUtils.setOutlineCountryImage(this.mCountryFlag);
        this.mCountryFlag.setElevation(ViewUtils.convertDpToPixel(3.0f, getActivity()));
        this.mPassword.disableCopyPaste();
        this.mConfirmPassword.disableCopyPaste();
        logEvent(Screens.SCREENS.name(), 16);
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep2Fragment */
    public /* synthetic */ void mo33051x652052fa(View view, boolean z) {
        if (z) {
            ValidationUtils.PasswordStrength passwordStrength = this.currentPasswordStrength;
            if (passwordStrength == null) {
                this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
                this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
            } else if (!passwordStrength.isStrongEnough()) {
                this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
                this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
            } else {
                this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
            }
        } else if (this.mPassword.getText().toString().isEmpty()) {
            ValidationUtils.PasswordStrength passwordStrength2 = ValidationUtils.PasswordStrength.EMPTY;
            this.currentPasswordStrength = passwordStrength2;
            computeViewState(passwordStrength2);
            this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
        } else {
            PasswordStrengthModel.PasswordStrengthRequestModel passwordStrengthRequestModel = new PasswordStrengthModel.PasswordStrengthRequestModel();
            passwordStrengthRequestModel.password = this.mPassword.getText().toString();
            passwordStrengthRequestModel.updateModelBasedOnUserRegistrationData(UserRegistrationModels.UserRegistration.NEW_USER);
            new PasswordStrengthCheckNetworkDispatcher().checkPasswordStrength(passwordStrengthRequestModel).observeSingleEvent(getViewLifecycleOwner(), new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    if (genericResponse.unableToReachServer()) {
                        SingleChoiceDialog singleChoiceDialogWithAlertTitle = GenericAlertUtils.getSingleChoiceDialogWithAlertTitle(CreateAccountStep2Fragment.this.requireActivity(), CreateAccountStep2Fragment.this.getString(R.string.common_alert_unableToConnectToNw));
                        singleChoiceDialogWithAlertTitle.setTitleString(CreateAccountStep2Fragment.this.getString(R.string.common_lbl_passWordStrengthCriteria));
                        singleChoiceDialogWithAlertTitle.show();
                        return;
                    }
                    PasswordStrengthModel.PasswordStrengthResponseModel passwordStrengthResponseModel = (PasswordStrengthModel.PasswordStrengthResponseModel) genericResponse.getBodyOfType(PasswordStrengthModel.PasswordStrengthResponseModel.class);
                    ValidationUtils.PasswordStrength passwordStrength = ValidationUtils.PasswordStrength.EMPTY;
                    if (passwordStrengthResponseModel != null) {
                        passwordStrength = passwordStrengthResponseModel.strength != null ? ValidationUtils.PasswordStrength.valueOf(passwordStrengthResponseModel.strength) : ValidationUtils.PasswordStrength.EMPTY;
                        if (!passwordStrength.isStrongEnough()) {
                            CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.updatedRecyclerViewWithSuggestions(passwordStrengthResponseModel);
                            CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.showDynamicPasswordStrengthUi();
                            CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                        } else {
                            CreateAccountStep2Fragment.this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthUi();
                        }
                    }
                    CreateAccountStep2Fragment.this.currentPasswordStrength = passwordStrength;
                    CreateAccountStep2Fragment.this.computeViewState(passwordStrength);
                }
            });
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEmailEditText.setOnFocusChangeListener(new CreateAccountStep2Fragment$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep2Fragment */
    public /* synthetic */ void mo33052x2abb2305(View view, boolean z) {
        if (!z) {
            logEvents(Events.CREATE_ACCOUNT_EMAIL.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeEmail));
        } else if (!this.mIsHasFocusEmail) {
            this.mIsHasFocusEmail = true;
            this.mStartTimeEmail = System.currentTimeMillis();
        }
    }

    /* access modifiers changed from: private */
    public void computeViewState(ValidationUtils.PasswordStrength passwordStrength) {
        this.mPasswordStrengthUiLogicHolder.updatePasswordStrengthUI(passwordStrength);
        String obj = this.mPassword.getText().toString();
        String obj2 = this.mConfirmPassword.getText().toString();
        if ((this.isEmailSelected ? this.mEmailEditText : this.mMobileNumber).getText().toString().isEmpty() || !passwordStrength.isStrongEnough() || obj.isEmpty() || obj2.isEmpty() || !obj.equals(obj2)) {
            this.mContinueButton.setEnabled(false);
        } else {
            this.mContinueButton.setEnabled(true);
        }
        if (!this.mPassword.hasFocus() && !passwordStrength.equals(ValidationUtils.PasswordStrength.EMPTY)) {
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
        }
    }

    private void enableContinueButtonMobileNumberCondition(String str, String str2, String str3, boolean z) {
        if (str.isEmpty() || str2.isEmpty() || str3.isEmpty() || !str2.equals(str3) || !z) {
            this.mContinueButton.setEnabled(false);
        } else {
            this.mContinueButton.setEnabled(true);
        }
    }

    private void enableContinueButtonEmailCondition(String str, String str2, String str3, boolean z) {
        if (str.isEmpty() || str2.isEmpty() || str3.isEmpty() || !str2.equals(str3) || !z || !ValidationUtils.isEmailAddressValid(this.mEmailEditText.getText().toString())) {
            this.mContinueButton.setEnabled(false);
        } else {
            this.mContinueButton.setEnabled(true);
        }
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (CreateAccountStep2Fragment.this.createTooltipContentHolder != null && CreateAccountStep2Fragment.this.createTooltipContentHolder.isShowing()) {
                    CreateAccountStep2Fragment.this.createTooltipContentHolder.dismiss();
                }
                if (CreateAccountStep2Fragment.this.getActivity() != null) {
                    CreateAccountStep2Fragment.this.getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        updateCountryCodeSelectionOnResume(CountryCodeUIConfiguration.CURRENT);
        if (com.jch.racWiFi.Constants._INVITE_) {
            this.stepNum.setText(R.string.common_lbl_step2of3);
        } else {
            this.stepNum.setText(R.string.common_lbl_step2of4);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mCreateAccountManualEntryOfDetailsPresenter.unregisterEventBus();
        this.mCreateAccountManualEntryOfDetailsPresenter.removeCallbacks();
        this.mPasswordStrengthUiLogicHolder.unbind();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362137})
    public void onClickContinue(View view) {
        String trim = this.mEmailEditText.getText().toString().trim();
        String trim2 = this.mPassword.getText().toString().trim();
        String trim3 = this.mConfirmPassword.getText().toString().trim();
        String trim4 = this.mMobileNumber.getText().toString().trim();
        UserRegistrationModels.UserRegistration.NEW_USER.verificationRequired = true;
        this.mCreateAccountManualEntryOfDetailsPresenter.validateAndInitiateRegisterUser(trim, trim4, trim2, trim3, this.isEmailSelected);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364648})
    public void clickOnTermAndCondition() {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        privacyPolicyFragment.setHideTitle(true);
        privacyPolicyFragment.setHideAcceptance(true);
        privacyPolicyFragment.setTakeToLogin(true);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY, PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
        privacyPolicyFragment.setArguments(bundle);
        privacyPolicyFragment.setCancelable(false);
        privacyPolicyFragment.show(getActivity().getSupportFragmentManager(), "PrivacyPolicyFragment");
        privacyPolicyFragment.setOnClickAgree(new CreateAccountStep2Fragment$$ExternalSyntheticLambda0(privacyPolicyFragment));
        PrivacyPolicyFragment.setPrivacyPolicyShown();
    }

    static /* synthetic */ void lambda$clickOnTermAndCondition$2(PrivacyPolicyFragment privacyPolicyFragment, View view) {
        TutorialFragment.setTutorialShown();
        privacyPolicyFragment.dismiss();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364339})
    public void mobileNoOnClick() {
        logEvents(Events.MOBILE_CREATE_ACCOUNT.name(), 0);
        this.isEmailSelected = false;
        this.isPhoneNumberFieldSelected = true;
        setSignUpFieldVisibility(4, 0, 4, 0, 0);
        this.mEmailEditText.setText("");
        this.mEmailEditText.setNormalBackgroundDrawable();
        this.mPassword.setText("");
        this.mPassword.setNormalBackgroundDrawable();
        this.mConfirmPassword.setText("");
        this.mConfirmPassword.setNormalBackgroundDrawable();
        this.mPasswordBubbleLayout.setVisibility(4);
        this.mConfirmPasswordBubbleLayout.setVisibility(4);
        this.mEmailBubbleLayout.setVisibility(4);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364143})
    public void emailIdOnClick() {
        logEvents(Events.EMAIL_CREATE_ACCOUNT.name(), 0);
        this.isEmailSelected = true;
        this.isPhoneNumberFieldSelected = false;
        setSignUpFieldVisibility(0, 4, 0, 4, 4);
        this.mMobileNumber.setText("");
        this.mMobileNumber.setNormalBackgroundDrawable();
        this.mPassword.setText("");
        this.mPassword.setNormalBackgroundDrawable();
        this.mConfirmPassword.setText("");
        this.mConfirmPassword.setNormalBackgroundDrawable();
        this.mPasswordBubbleLayout.setVisibility(4);
        this.mConfirmPasswordBubbleLayout.setVisibility(4);
        this.mMobileBubbleLayout.setVisibility(4);
    }

    private void setSignUpFieldVisibility(int i, int i2, int i3, int i4, int i5) {
        this.mEmailEditText.setVisibility(i);
        this.mMobileNumber.setVisibility(i2);
        this.mEmailHighLight.setVisibility(i3);
        this.mMobileNumberHighlight.setVisibility(i4);
        this.mCountryCodeLayout.setVisibility(i5);
    }

    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        logEvents(Events.QUIT_REGISTRATION_STEP_2.name(), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolder;
        if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
            this.createTooltipContentHolder.dismiss();
        }
    }

    @OnClick({2131362817})
    public void onClickPasswordHint(ImageButton imageButton) {
        if (!this.createTooltipContentHolder.isShowing()) {
            this.createTooltipContentHolder.setHintCasePosition(1);
            this.createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_grey);
            this.createTooltipContentHolder.setBordercolor(R.color.textview_color_vd_light);
            this.createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
            this.createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
            this.createTooltipContentHolder.build();
            this.createTooltipContentHolder.show();
            Logger.m47e("IF_HINT  : ", this.createTooltipContentHolder.isShowing() + "");
        } else {
            this.createTooltipContentHolder.dismiss();
            Logger.m47e("ELSE_HINT  : ", this.createTooltipContentHolder.isShowing() + "");
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (CreateAccountStep2Fragment.this.createTooltipContentHolder != null && CreateAccountStep2Fragment.this.createTooltipContentHolder.isShowing()) {
                    CreateAccountStep2Fragment.this.createTooltipContentHolder.dismiss();
                }
            }
        }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    @OnClick({2131363164})
    public void onClickCountryCodeSelection() {
        showCountryCodeSelectionDialog();
    }

    public void allFieldsValidated(UserRegistrationModels.UserRegistration userRegistration) {
        showPleaseWaitDialog();
    }

    public void emptyPhoneNumberFieldCallback() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_phnNoFieldCannotBeEmpty), 0);
        dismissPleaseWaitDialog();
    }

    public void emptyEmailFieldCallback() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_emailFieldCannotBeEmpty), 0);
        dismissPleaseWaitDialog();
    }

    public void emptyPasswordFieldCallback() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_passwordCannotBeEmpty), 0);
        dismissPleaseWaitDialog();
    }

    public void emptyConfirmPasswordFieldCallback() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_confirmPasswordCannotBeEmpty), 0);
        dismissPleaseWaitDialog();
    }

    public void onRegistrationSuccessful(UserRegistrationModels.UserRegistrationSuccessResponse userRegistrationSuccessResponse) {
        dismissPleaseWaitDialog();
        this.mCreateAccountManualEntryOfDetailsPresenter.isFromManualEntrySignUp = false;
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_createAccountManualEntrySignUp2_to_createAccountOtpVerificationSignUp3);
    }

    public void onRegistrationFailure(UserRegistrationModels.UserRegistrationFailureResponse userRegistrationFailureResponse) {
        Toaster.makeToast(getActivity(), getString(R.string.createAccount_alert_unableToCreateAcc), 0);
        dismissPleaseWaitDialog();
    }

    public void onPasswordStrengthChanged(ValidationUtils.PasswordStrength passwordStrength) {
        this.mPasswordStrengthUiLogicHolder.updatePasswordStrengthUI(passwordStrength);
    }

    public void accountExists() {
        Toaster.makeToast(getActivity(), getString(R.string.createAccount_alert_accountAlreadyExists), 0);
        dismissPleaseWaitDialog();
    }

    public void invalidEmailFormat() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidEmailFormat), 0);
        dismissPleaseWaitDialog();
    }

    public void invalidMobileNumberFormat() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidMobNo), 0);
        dismissPleaseWaitDialog();
    }

    public void inadequatePasswordStrength() {
        Toaster.makeToast(getActivity(), getString(R.string.createAccount_alert_passwordStrengthError), 0);
        dismissPleaseWaitDialog();
    }

    public void passwordAndConfirmPasswordMismatch() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_pwdMismatch), 0);
        dismissPleaseWaitDialog();
    }

    public void onInitiateUserRegistrationSuccess() {
        dismissPleaseWaitDialog();
        this.mCreateAccountManualEntryOfDetailsPresenter.isFromManualEntrySignUp = false;
        Bundle bundle = new Bundle();
        bundle.putBoolean(com.jch.racWiFi.Constants.IS_PHONE_OPTION_SELECTED, this.isPhoneNumberFieldSelected);
        bundle.putInt(com.jch.racWiFi.Constants.SELECTED_COUNTRY_NAME, this.selectedCountryName);
        bundle.putInt(com.jch.racWiFi.Constants.SELECTED_COUNTRY_CODE, this.selectedCountryCode);
        if (getArguments() != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, getArguments().getLong(Constants.Keys.ENTRY_TIME));
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_createAccountManualEntrySignUp2_to_createAccountOtpVerificationSignUp3, bundle);
        logEvents(Events.CREATE_ACCOUNT_STEP_2_COMPLETION.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mEnterTime));
    }

    public void onInitiateUserRegistrationFail(String str) {
        dismissPleaseWaitDialog();
        if (str == null || str.isEmpty()) {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        } else {
            Toaster.makeToast(getActivity(), str, 1);
        }
    }

    public void onInitiateUserRegistrationFailedErrorCode(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() == 409) {
            Toaster.makeToast(getActivity(), getString(R.string.createAccount_alert_alreadyRegistered), 1);
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        }
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 0);
        dismissPleaseWaitDialog();
    }

    private void showCountryCodeSelectionDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                CreateAccountStep2Fragment.this.updateCountryCodeSelection(countryCodeUIConfiguration);
                CreateAccountStep2Fragment.this.selectedCountryName = countryCodeUIConfiguration.getCountryName();
                CreateAccountStep2Fragment.this.selectedCountryCode = countryCodeUIConfiguration.getCountryNameShortForm();
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                CreateAccountStep2Fragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        countryCodeDialog.show();
        countryCodeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                CreateAccountStep2Fragment.this.mParent.setBackgroundResource(R.drawable.transparent);
            }
        });
        WindowManager.LayoutParams attributes = countryCodeDialog.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        countryCodeDialog.getWindow().setAttributes(attributes);
        countryCodeDialog.getWindow().addFlags(2);
    }

    public void updateCountryCodeSelection(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mCountryCodeTextView.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mMobileNumber.setText("");
        this.mMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void updateCountryCodeSelectionOnResume(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mCountryCodeTextView.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String obj = this.mEmailEditText.getText().toString();
        String obj2 = this.mPassword.getText().toString();
        String obj3 = this.mConfirmPassword.getText().toString();
        this.mCountryCodeTextView.getText().toString().trim();
        this.mMobileNumber.getText().toString().trim();
        String trim = this.mMobileNumber.getText().toString().trim();
        ValidationUtils.PasswordStrength passwordStrength = this.currentPasswordStrength;
        boolean z = passwordStrength != null && passwordStrength.isStrongEnough();
        if (this.isEmailSelected) {
            enableContinueButtonEmailCondition(obj, obj2, obj3, z);
        } else {
            enableContinueButtonMobileNumberCondition(trim, obj2, obj3, z);
        }
    }

    private void emailEnabledandMobileDisabled() {
        this.mEmailEditText.setEnabled(true);
        this.mMobileNumber.setEnabled(false);
        this.mCountryCodeLayout.setEnabled(false);
        this.mEmailEditText.setBackground(getResources().getDrawable(R.drawable.edit_text_background));
        this.mMobileNumber.setBackgroundColor(getResources().getColor(R.color.color_grey_out));
        this.mCountryCodeLayout.setBackgroundColor(getResources().getColor(R.color.color_grey_out));
        this.mEmailEditText.setAlpha(1.0f);
        this.mMobileNumber.setAlpha(0.7f);
        this.mCountryCodeLayout.setAlpha(0.7f);
    }

    private void emailDisabledandMobileEnabled() {
        this.mEmailEditText.setEnabled(false);
        this.mMobileNumber.setEnabled(true);
        this.mCountryCodeLayout.setEnabled(true);
        this.mEmailEditText.setBackgroundColor(getResources().getColor(R.color.color_grey_out));
        this.mMobileNumber.setBackground(getResources().getDrawable(R.drawable.edit_text_background));
        this.mCountryCodeLayout.setBackground(getResources().getDrawable(R.drawable.edit_text_background));
        this.mEmailEditText.setAlpha(0.7f);
        this.mMobileNumber.setAlpha(1.0f);
        this.mCountryCodeLayout.setAlpha(1.0f);
    }
}
