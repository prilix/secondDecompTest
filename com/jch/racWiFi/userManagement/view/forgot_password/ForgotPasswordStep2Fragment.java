package com.jch.racWiFi.userManagement.view.forgot_password;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SecurityUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.OTPResendTimeoutUIHolder;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.presenter.ForgotPasswordStep2Presenter;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep2Fragment extends GenericFragment implements ForgotPasswordStep2Presenter.IForgotPasswordStep2Presenter, TextWatcher {
    private boolean comingBackFromStep3 = false;
    private Activity mActivity;
    @BindView(2131362137)
    Button mButtonContinue;
    @BindView(2131363518)
    TextView mEmailAddressObscure;
    private ForgotPasswordDataModels.ForgotPasswordOTPRequestData mForgotPasswordOTPRequestData;
    private ForgotPasswordStep2Presenter mForgotPasswordStep2Presenter;
    private boolean mIsHasFocusOtp;
    private boolean mIsResendCodeClick;
    @BindView(2131363519)
    TextView mMobileNumberObscure;
    @BindView(2131362387)
    EditText mOTP;
    private OTPResendTimeoutUIHolder mOtpResendTimeoutUIHolder;
    private long mStartTimeOtp;
    private boolean mTriggerResendEvent;
    private Unbinder mUnbinder;
    private NavController.OnDestinationChangedListener onDestinationChangedListener = new ForgotPasswordStep2Fragment$$ExternalSyntheticLambda1(this);

    static /* synthetic */ boolean lambda$onOTPSendSuccess$2(Dialog dialog, View view) {
        return true;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-userManagement-view-forgot_password-ForgotPasswordStep2Fragment */
    public /* synthetic */ void mo33159xe55662f6(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (navDestination.getId() == R.id.forgot_password_step3) {
            this.comingBackFromStep3 = true;
        }
    }

    public static ForgotPasswordStep2Fragment newInstance() {
        ForgotPasswordStep2Fragment forgotPasswordStep2Fragment = new ForgotPasswordStep2Fragment();
        forgotPasswordStep2Fragment.setNewBundleAsArgument();
        return forgotPasswordStep2Fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = requireActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mForgotPasswordOTPRequestData = (ForgotPasswordDataModels.ForgotPasswordOTPRequestData) arguments.getParcelable(ForgotPasswordDataModels.ForgotPasswordOTPRequestData.FORGOT_PASSWORD_REQUEST_OTP_KEY);
        }
        ForgotPasswordStep2Presenter forgotPasswordStep2Presenter = new ForgotPasswordStep2Presenter(this);
        this.mForgotPasswordStep2Presenter = forgotPasswordStep2Presenter;
        forgotPasswordStep2Presenter.setModel(this.mForgotPasswordOTPRequestData);
        ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData = this.mForgotPasswordOTPRequestData;
        if (forgotPasswordOTPRequestData != null) {
            view = forgotPasswordOTPRequestData.requestOtpToEmail ? layoutInflater.inflate(R.layout.enter_otp_email_forgot_passsword_frame, viewGroup, false) : layoutInflater.inflate(R.layout.enter_otp_mobile_number_forgot_passsword_frame, viewGroup, false);
        } else {
            view = null;
        }
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        this.mOTP.addTextChangedListener(this);
        this.mForgotPasswordStep2Presenter.registerEventBus();
        this.mButtonContinue.setEnabled(false);
        if (this.mForgotPasswordOTPRequestData.requestOtpToEmail) {
            this.mEmailAddressObscure.setText(SecurityUtils.staringEmail(this.mForgotPasswordOTPRequestData.emailID));
        } else {
            this.mMobileNumberObscure.setText(SecurityUtils.staringNumber(this.mForgotPasswordOTPRequestData.mobileNumber));
        }
        OTPResendTimeoutUIHolder oTPResendTimeoutUIHolder = new OTPResendTimeoutUIHolder(view, getViewLifecycleOwner());
        this.mOtpResendTimeoutUIHolder = oTPResendTimeoutUIHolder;
        oTPResendTimeoutUIHolder.startResendCountDown();
        this.mFragmentToActivityCallback.getNavController().addOnDestinationChangedListener(this.onDestinationChangedListener);
        if (this.comingBackFromStep3) {
            sendOTP();
        }
        return view;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mOTP.setOnFocusChangeListener(new ForgotPasswordStep2Fragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-forgot_password-ForgotPasswordStep2Fragment */
    public /* synthetic */ void mo33160x6053a7bb(View view, boolean z) {
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
        } else {
            logEvents(Events.OTP_ENTER.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeOtp));
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mFragmentToActivityCallback.getNavController().removeOnDestinationChangedListener(this.onDestinationChangedListener);
        this.mOtpResendTimeoutUIHolder.stopResendCountDown();
        this.mForgotPasswordStep2Presenter.removeCallbacks();
        this.mForgotPasswordStep2Presenter.unregisterEventBus();
        this.mOtpResendTimeoutUIHolder.unbind();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362137})
    public void onClickContinue(View view) {
        String str;
        String trim = this.mOTP.getText().toString().trim();
        if (this.mForgotPasswordOTPRequestData.requestOtpToEmail) {
            str = this.mForgotPasswordOTPRequestData.emailID;
        } else {
            str = this.mForgotPasswordOTPRequestData.mobileNumber;
        }
        if (!trim.isEmpty()) {
            int parseInt = Integer.parseInt(trim);
            showPleaseWaitDialog();
            if (this.mForgotPasswordOTPRequestData.requestOtpToEmail) {
                this.mForgotPasswordStep2Presenter.verifyOTPEmail(str, parseInt);
            } else {
                this.mForgotPasswordStep2Presenter.verifyOTPMobileNumber(str, parseInt);
            }
        }
    }

    @OnClick({2131364445})
    public void onClickResendOTP() {
        this.mIsResendCodeClick = true;
        logEvents(Events.OTP_RESEND.name(), 0);
        showPleaseWaitDialog();
        sendOTP();
        this.mOtpResendTimeoutUIHolder.startResendCountDown();
    }

    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        logEvents(Events.QUIT_FORGOT_PASSWORD_STEP_2.name(), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void fieldEmptyCallback() {
        this.mOTP.setErrorBackgroundDrawable();
    }

    public void invalidField() {
        this.mOTP.setErrorBackgroundDrawable();
    }

    public void otpVerificationFailed() {
        this.mOTP.setErrorBackgroundDrawable();
        Toaster.makeToast(requireActivity(), getString(R.string.errorCode_alert_IOE001), 1);
        dismissPleaseWaitDialog();
    }

    public void otpVerificationSuccess(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData, String str) {
        dismissPleaseWaitDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData.FORGOT_PASSWORD_VERIFY_OTP_KEY, forgotPasswordVerifyOTPRequestData);
        bundle.putString(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData.FORGOT_PASSWORD_TOKEN, str);
        Bundle arguments = getArguments();
        if (arguments != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, arguments.getLong(Constants.Keys.ENTRY_TIME));
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_forgot_password_step2_to_forgot_password_step3, bundle);
    }

    public void fieldValidated() {
        showPleaseWaitDialog();
    }

    public void onOTPSendSuccess() {
        dismissPleaseWaitDialog();
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_otpSentSuccessfully));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), ForgotPasswordStep2Fragment$$ExternalSyntheticLambda2.INSTANCE);
            singleChoiceDialog.show();
        }
    }

    public void userIDNotRegistered() {
        Toaster.makeToast(requireActivity(), getString(R.string.forgotPassword_userNotExists_alert), 0);
    }

    public void onOTPSendFailure() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_maxRetryeachedOtpResend));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            singleChoiceDialog.show();
        }
    }

    public void onNetworkCallSuccess() {
        dismissPleaseWaitDialog();
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
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
        Toaster.makeToast(requireActivity(), getString(R.string.common_alert_somethingWentWrong), 0);
        dismissPleaseWaitDialog();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String trim = this.mOTP.getText().toString().trim();
        if (trim.isEmpty() || trim.length() != 6) {
            this.mOTP.setErrorBackgroundDrawable();
            this.mButtonContinue.setEnabled(false);
            return;
        }
        this.mOTP.setNormalBackgroundDrawable();
        this.mButtonContinue.setEnabled(true);
    }

    private void sendOTP() {
        this.mForgotPasswordStep2Presenter.sendOtp(this.mForgotPasswordOTPRequestData);
    }
}
