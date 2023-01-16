package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SecurityUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.OTPResendTimeoutUIHolder;
import com.jch.racWiFi.userManagement.model.InitiateSigUpResponseModel;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.presenter.AccountVerificationWithOTPPresenterV2;
import com.jch_hitachi.aircloudglobal.R;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreateAccountStep3Fragment extends GenericFragment implements TextWatcher, AccountVerificationWithOTPPresenterV2.IAccountVerificationWithOTPPresenter {
    private Bundle bundle;
    private AccountVerificationWithOTPPresenterV2 mAccountVerificationWithOTPPresenter;
    private Activity mActivity;
    @BindView(2131362137)
    Button mContinueButton;
    private long mEnterTime;
    private boolean mIsHasFocusOtp;
    private boolean mIsResendCodeClick;
    private long mOTPEnterTime;
    @BindView(2131362387)
    EditText mOtp;
    private OTPResendTimeoutUIHolder mOtpResendTimeoutUIHolder;
    @BindView(2131364546)
    TextView mSMSNumberTextView;
    private long mStartTimeOtp;
    private boolean mTriggerResendEvent;
    private Unbinder mUnbinder;
    @BindView(2131364587)
    TextView stepNum;

    static /* synthetic */ boolean lambda$onReSendOtpFail$3(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$onReSendOtpSuccess$2(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$onRegistrationFailed$4(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$onRegistrationFailedErrorCode$6(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$onRegistrationFailedMessage$5(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showInternetCheckAlert$1(Dialog dialog, View view) {
        return true;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static CreateAccountStep3Fragment newInstance() {
        CreateAccountStep3Fragment createAccountStep3Fragment = new CreateAccountStep3Fragment();
        createAccountStep3Fragment.setNewBundleAsArgument();
        return createAccountStep3Fragment;
    }

    public void onCreate(Bundle bundle2) {
        super.onCreate(bundle2);
        this.mActivity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle2) {
        boolean z;
        View view;
        this.mEnterTime = System.currentTimeMillis();
        String str = UserRegistrationModels.UserRegistration.NEW_USER.email;
        String str2 = UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber;
        if (str != null) {
            view = layoutInflater.inflate(R.layout.account_verification_mail_frame_new, viewGroup, false);
            z = true;
        } else {
            view = layoutInflater.inflate(R.layout.account_verification_mobile_frame_new, viewGroup, false);
            str = str2;
            z = false;
        }
        this.bundle = getArguments();
        AccountVerificationWithOTPPresenterV2 accountVerificationWithOTPPresenterV2 = new AccountVerificationWithOTPPresenterV2(this);
        this.mAccountVerificationWithOTPPresenter = accountVerificationWithOTPPresenterV2;
        accountVerificationWithOTPPresenterV2.registerEventBus();
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        this.mSMSNumberTextView.setText(str);
        if (z) {
            this.mSMSNumberTextView.setText(SecurityUtils.staringEmail(str));
        } else {
            this.mSMSNumberTextView.setText(SecurityUtils.staringNumber(str));
        }
        this.mContinueButton.setEnabled(false);
        this.mOtp.addTextChangedListener(this);
        OTPResendTimeoutUIHolder oTPResendTimeoutUIHolder = new OTPResendTimeoutUIHolder(view, getViewLifecycleOwner());
        this.mOtpResendTimeoutUIHolder = oTPResendTimeoutUIHolder;
        oTPResendTimeoutUIHolder.startResendCountDown();
        logEvent(Screens.SCREENS.name(), 17);
        return view;
    }

    public void onViewCreated(View view, Bundle bundle2) {
        super.onViewCreated(view, bundle2);
        this.mOtp.setOnFocusChangeListener(new CreateAccountStep3Fragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep3Fragment */
    public /* synthetic */ void mo33073x8b516745(View view, boolean z) {
        if (z) {
            if (this.mIsResendCodeClick) {
                this.mIsHasFocusOtp = false;
                this.mIsResendCodeClick = false;
                this.mTriggerResendEvent = true;
            }
            if (!this.mIsHasFocusOtp) {
                this.mIsHasFocusOtp = true;
                this.mStartTimeOtp = System.currentTimeMillis();
            }
        } else if (this.mTriggerResendEvent) {
            this.mTriggerResendEvent = false;
            logEvents(Events.RESEND_AND_VERIFY_OTP.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeOtp));
        } else {
            logEvents(Events.VERIFY_OTP_ENTER_TIME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeOtp));
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        if (com.jch.racWiFi.Constants._INVITE_) {
            this.stepNum.setText(R.string.common_lbl_step3of3);
        } else {
            this.stepNum.setText(R.string.common_lbl_step3of4);
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mOtpResendTimeoutUIHolder.unbind();
        this.mOtpResendTimeoutUIHolder.stopResendCountDown();
        this.mAccountVerificationWithOTPPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
        AccountVerificationWithOTPPresenterV2 accountVerificationWithOTPPresenterV2 = this.mAccountVerificationWithOTPPresenter;
        if (accountVerificationWithOTPPresenterV2 != null) {
            accountVerificationWithOTPPresenterV2.unregisterEventBus();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @OnClick({2131362137})
    public void onClickContinue() {
        if (!NetworkConnectivity.isNetworkAvailable(this.mActivity)) {
            showInternetCheckAlert();
            return;
        }
        showPleaseWaitDialog();
        String trim = this.mOtp.getText().toString().trim();
        if (!trim.isEmpty()) {
            this.mAccountVerificationWithOTPPresenter.registerUserWithOtp(trim);
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_enterOtp), 0);
        }
    }

    private void showInternetCheckAlert() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda7.INSTANCE);
        singleChoiceDialog.show();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362078})
    public void onBackButtonPressed() {
        logEvents(Events.QUIT_REGISTRATION_STEP_3.name(), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364445})
    public void onClickResend() {
        if (!NetworkConnectivity.isNetworkAvailable(this.mActivity)) {
            showInternetCheckAlert();
            return;
        }
        this.mIsResendCodeClick = true;
        showPleaseWaitDialog();
        this.mAccountVerificationWithOTPPresenter.resendOtp();
        this.mOtpResendTimeoutUIHolder.startResendCountDown();
    }

    public void onReSendOtpSuccess(InitiateSigUpResponseModel initiateSigUpResponseModel) {
        dismissPleaseWaitDialog();
        if (initiateSigUpResponseModel != null) {
            String message = initiateSigUpResponseModel.getMessage();
            if (message == null || message.isEmpty()) {
                Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
            } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
                singleChoiceDialog.setTitleString(getString(R.string.common_alert));
                singleChoiceDialog.setMessageString(getString(R.string.common_alert_otpSentSuccessfully));
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda3.INSTANCE);
                singleChoiceDialog.show();
            }
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        }
    }

    public void onReSendOtpFail(String str) {
        dismissPleaseWaitDialog();
        if (str == null || str.isEmpty()) {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(str);
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda2.INSTANCE);
            singleChoiceDialog.show();
        }
    }

    public void onRegistrationSuccessful() {
        logEvents(Events.CREATE_ACCOUNT_STEP_3_COMPLETION.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mEnterTime));
        Bundle bundle2 = this.bundle;
        if (bundle2 != null) {
            logEvents(Events.REGISTRATION_COMPLETION.name(), Constants.CC.getSeconds(System.currentTimeMillis() - bundle2.getLong(Constants.Keys.ENTRY_TIME)));
        }
        String str = UserRegistrationModels.UserRegistration.NEW_USER.email;
        String str2 = UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber;
        if (str == null || str.isEmpty()) {
            str = str2;
        }
        this.mFragmentToActivityCallback.getSessionManagerInstance().setUserName(str);
        if (com.jch.racWiFi.Constants._INVITE_) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_createAccountOtpVerificationSignUp3_to_accountCreationSuccessSignUp5);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_createAccountOtpVerificationSignUp3_to_enterAddressSignUp4, this.bundle);
        }
        dismissPleaseWaitDialog();
        CreateAccountStep5SuccessFragment.setAtleastOneAccountWasCreatedAfterAppInstallation();
    }

    public void onRegistrationFailed() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.errorCode_alert_IOE001));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda4.INSTANCE);
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void onRegistrationFailedMessage(String str) {
        if (str == null || str.isEmpty()) {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableSendOtp));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda6.INSTANCE);
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void onRegistrationFailedErrorCode(Response<ResponseBody> response) {
        dismissPleaseWaitDialog();
        if (response.code() != 400) {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 1);
        } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.errorCode_alert_IOE001));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CreateAccountStep3Fragment$$ExternalSyntheticLambda5.INSTANCE);
            singleChoiceDialog.show();
        }
    }

    private void initDebugData() {
        this.mOtp.setText("");
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mOtp.getText().toString().trim().length() != 6) {
            this.mOtp.setErrorBackgroundDrawable();
            this.mContinueButton.setEnabled(false);
            return;
        }
        this.mOtp.setNormalBackgroundDrawable();
        this.mContinueButton.setEnabled(true);
    }

    public void onNetworkCallFailure(Throwable th) {
        dismissPleaseWaitDialog();
        showServerErrorAlert();
    }

    private void showServerErrorAlert() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToReachServer));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new CreateAccountStep3Fragment$$ExternalSyntheticLambda1(this));
        singleChoiceDialog.show();
    }

    /* renamed from: lambda$showServerErrorAlert$7$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep3Fragment */
    public /* synthetic */ boolean mo33074xc2fc0636(Dialog dialog, View view) {
        if (getActivity() == null) {
            return true;
        }
        dismissPleaseWaitDialog();
        getCoreActivity().dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_global_loginFragment);
        return true;
    }
}
