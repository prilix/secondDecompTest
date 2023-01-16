package com.jch.racWiFi.userManagement.view.forgot_password;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.model.PasswordStrengthModel;
import com.jch.racWiFi.userManagement.network.PasswordStrengthCheckNetworkDispatcher;
import com.jch.racWiFi.userManagement.presenter.ForgotPasswordStep3Presenter;
import com.jch.racWiFi.userManagement.view.PasswordStrengthUiLogicHolder;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep3Fragment extends GenericFragment implements ForgotPasswordStep3Presenter.IForgotPasswordStep3Presenter, TextWatcher, LifecycleOwner {
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolder;
    ValidationUtils.PasswordStrength currentPasswordStrength;
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131362425)
    ConstraintLayout mConFirmNewPasswordBubbleLayout;
    private ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData mForgotPasswordData;
    private ForgotPasswordStep3Presenter mForgotPasswordStep3Presenter;
    private boolean mIsHasFocusConfirmNewPassword;
    private boolean mIsHasFocusNewPassword;
    @BindView(2131362362)
    EditText mNewConfirmPassword;
    @BindView(2131362377)
    EditText mNewPassword;
    @BindView(2131362817)
    ImageButton mPasswordFormat;
    /* access modifiers changed from: private */
    public PasswordStrengthUiLogicHolder mPasswordStrengthUiLogicHolder;
    @BindView(2131362167)
    Button mResetPasswordButton;
    private long mStartTimeNewConfirmPassword;
    private long mStartTimeNewPassword;
    private Unbinder mUnbinder;
    private String token;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onPasswordStrengthChanged(ValidationUtils.PasswordStrength passwordStrength) {
    }

    public void setToken(String str) {
        this.token = str;
    }

    public static ForgotPasswordStep3Fragment newInstance() {
        ForgotPasswordStep3Fragment forgotPasswordStep3Fragment = new ForgotPasswordStep3Fragment();
        forgotPasswordStep3Fragment.setNewBundleAsArgument();
        return forgotPasswordStep3Fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enter_new_password_forgot_passward_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        PasswordStrengthUiLogicHolder passwordStrengthUiLogicHolder = new PasswordStrengthUiLogicHolder(requireActivity(), inflate);
        this.mPasswordStrengthUiLogicHolder = passwordStrengthUiLogicHolder;
        passwordStrengthUiLogicHolder.hidePasswordStrengthMeter();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mForgotPasswordData = (ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData) arguments.getParcelable(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData.FORGOT_PASSWORD_VERIFY_OTP_KEY);
            this.token = arguments.getString(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData.FORGOT_PASSWORD_TOKEN);
        }
        ForgotPasswordStep3Presenter forgotPasswordStep3Presenter = new ForgotPasswordStep3Presenter(this);
        this.mForgotPasswordStep3Presenter = forgotPasswordStep3Presenter;
        forgotPasswordStep3Presenter.setModel(this.mForgotPasswordData);
        this.createTooltipContentHolder = new CreateTooltipContentHolder(getContext(), (View) this.mPasswordFormat, getString(R.string.password_policy_hint));
        this.mResetPasswordButton.setEnabled(false);
        this.mNewPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().isEmpty()) {
                    ForgotPasswordStep3Fragment.this.mNewPassword.setErrorBackgroundDrawable();
                } else {
                    ForgotPasswordStep3Fragment.this.mNewPassword.setNormalBackgroundDrawable();
                }
                String obj = ForgotPasswordStep3Fragment.this.mNewPassword.getText().toString();
                String obj2 = ForgotPasswordStep3Fragment.this.mNewConfirmPassword.getText().toString();
                if (obj.isEmpty() || obj2.isEmpty() || ForgotPasswordStep3Fragment.this.currentPasswordStrength == null || !ForgotPasswordStep3Fragment.this.currentPasswordStrength.isStrongEnough()) {
                    ForgotPasswordStep3Fragment.this.mResetPasswordButton.setEnabled(false);
                } else {
                    ForgotPasswordStep3Fragment.this.mResetPasswordButton.setEnabled(true);
                }
                if (obj.isEmpty() && ForgotPasswordStep3Fragment.this.mNewPassword.hasFocus()) {
                    ForgotPasswordStep3Fragment.this.currentPasswordStrength = ValidationUtils.PasswordStrength.EMPTY;
                    ForgotPasswordStep3Fragment forgotPasswordStep3Fragment = ForgotPasswordStep3Fragment.this;
                    forgotPasswordStep3Fragment.computeViewState(forgotPasswordStep3Fragment.currentPasswordStrength);
                    ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.makeBackgroundNormal();
                    ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
                    ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                }
            }
        });
        this.mNewPassword.setOnFocusChangeListener(new ForgotPasswordStep3Fragment$$ExternalSyntheticLambda0(this));
        this.mNewConfirmPassword.setOnFocusChangeListener(new ForgotPasswordStep3Fragment$$ExternalSyntheticLambda1(this));
        this.mNewConfirmPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = ForgotPasswordStep3Fragment.this.mNewPassword.getText().toString();
                String obj2 = ForgotPasswordStep3Fragment.this.mNewConfirmPassword.getText().toString();
                if (ForgotPasswordStep3Fragment.this.currentPasswordStrength == null || !ForgotPasswordStep3Fragment.this.currentPasswordStrength.isStrongEnough() || obj.isEmpty() || obj2.isEmpty()) {
                    ForgotPasswordStep3Fragment.this.mResetPasswordButton.setEnabled(false);
                } else {
                    ForgotPasswordStep3Fragment.this.mResetPasswordButton.setEnabled(true);
                }
            }
        });
        this.mForgotPasswordStep3Presenter.registerEventBus();
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mNewConfirmPassword);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mConFirmNewPasswordBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mNewConfirmPassword.addTextChangedListener(genericEmptyEditTextWatcher);
        this.mNewPassword.disableCopyPaste();
        this.mNewConfirmPassword.disableCopyPaste();
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-userManagement-view-forgot_password-ForgotPasswordStep3Fragment */
    public /* synthetic */ void mo33168xf474baf1(View view, boolean z) {
        boolean z2 = true;
        if (!z) {
            logEvents(Events.NEW_PASSWORD.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeNewPassword));
            if (this.mNewPassword.getText().toString().isEmpty()) {
                ValidationUtils.PasswordStrength passwordStrength = ValidationUtils.PasswordStrength.EMPTY;
                this.currentPasswordStrength = passwordStrength;
                computeViewState(passwordStrength);
                this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
                return;
            }
            PasswordStrengthModel.PasswordStrengthRequestModel passwordStrengthRequestModel = new PasswordStrengthModel.PasswordStrengthRequestModel();
            passwordStrengthRequestModel.password = this.mNewPassword.getText().toString();
            if (this.mForgotPasswordData.email == null) {
                z2 = false;
            }
            if (z2) {
                passwordStrengthRequestModel.emailId = this.mForgotPasswordData.email;
            } else {
                passwordStrengthRequestModel.mobileNumber = this.mForgotPasswordData.mobileNumber;
            }
            passwordStrengthRequestModel.sanityInputs = null;
            new PasswordStrengthCheckNetworkDispatcher().checkPasswordStrength(passwordStrengthRequestModel).observeSingleEvent(getViewLifecycleOwner(), new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    if (genericResponse.unableToReachServer()) {
                        SingleChoiceDialog singleChoiceDialogWithAlertTitle = GenericAlertUtils.getSingleChoiceDialogWithAlertTitle(ForgotPasswordStep3Fragment.this.requireActivity(), ForgotPasswordStep3Fragment.this.getString(R.string.common_alert_unableToConnectToNw));
                        singleChoiceDialogWithAlertTitle.setTitleString(ForgotPasswordStep3Fragment.this.getString(R.string.common_lbl_passWordStrengthCriteria));
                        singleChoiceDialogWithAlertTitle.show();
                        return;
                    }
                    PasswordStrengthModel.PasswordStrengthResponseModel passwordStrengthResponseModel = (PasswordStrengthModel.PasswordStrengthResponseModel) genericResponse.getBodyOfType(PasswordStrengthModel.PasswordStrengthResponseModel.class);
                    ValidationUtils.PasswordStrength passwordStrength = ValidationUtils.PasswordStrength.EMPTY;
                    if (passwordStrengthResponseModel != null) {
                        passwordStrength = passwordStrengthResponseModel.strength != null ? ValidationUtils.PasswordStrength.valueOf(passwordStrengthResponseModel.strength) : ValidationUtils.PasswordStrength.EMPTY;
                        if (!passwordStrength.isStrongEnough()) {
                            ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.updatedRecyclerViewWithSuggestions(passwordStrengthResponseModel);
                            ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.showDynamicPasswordStrengthUi();
                            ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                        } else {
                            ForgotPasswordStep3Fragment.this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthUi();
                        }
                    }
                    ForgotPasswordStep3Fragment.this.currentPasswordStrength = passwordStrength;
                    ForgotPasswordStep3Fragment.this.computeViewState(passwordStrength);
                }
            });
            return;
        }
        if (!this.mIsHasFocusNewPassword) {
            this.mIsHasFocusNewPassword = true;
            this.mStartTimeNewPassword = System.currentTimeMillis();
        }
        ValidationUtils.PasswordStrength passwordStrength2 = this.currentPasswordStrength;
        if (passwordStrength2 == null) {
            this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
        } else if (!passwordStrength2.isStrongEnough()) {
            this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
        } else {
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
        }
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-userManagement-view-forgot_password-ForgotPasswordStep3Fragment */
    public /* synthetic */ void mo33169xd4ee10f2(View view, boolean z) {
        if (!z) {
            logEvents(Events.CONFIRM_NEW_PASSWORD.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeNewConfirmPassword));
        } else if (!this.mIsHasFocusConfirmNewPassword) {
            this.mIsHasFocusConfirmNewPassword = true;
            this.mStartTimeNewConfirmPassword = System.currentTimeMillis();
        }
    }

    /* access modifiers changed from: private */
    public void computeViewState(ValidationUtils.PasswordStrength passwordStrength) {
        this.mPasswordStrengthUiLogicHolder.updatePasswordStrengthUI(passwordStrength);
        String obj = this.mNewPassword.getText().toString();
        String obj2 = this.mNewConfirmPassword.getText().toString();
        if (!passwordStrength.isStrongEnough() || obj.isEmpty() || obj2.isEmpty()) {
            this.mResetPasswordButton.setEnabled(false);
        } else {
            this.mResetPasswordButton.setEnabled(true);
        }
        if (!this.mNewPassword.hasFocus() && !passwordStrength.equals(ValidationUtils.PasswordStrength.EMPTY)) {
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
        }
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (ForgotPasswordStep3Fragment.this.createTooltipContentHolder != null && ForgotPasswordStep3Fragment.this.createTooltipContentHolder.isShowing()) {
                    ForgotPasswordStep3Fragment.this.createTooltipContentHolder.dismiss();
                }
                if (ForgotPasswordStep3Fragment.this.requireActivity() != null) {
                    ForgotPasswordStep3Fragment.this.requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mForgotPasswordStep3Presenter.removeCallbacks();
        this.mForgotPasswordStep3Presenter.unregisterEventBus();
        this.mPasswordStrengthUiLogicHolder.unbind();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362167})
    public void onClickResetPassword(View view) {
        this.mForgotPasswordStep3Presenter.validateFields(this.mNewPassword.getText().toString(), this.mNewConfirmPassword.getText().toString(), this.token);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolder;
        if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
            this.createTooltipContentHolder.dismiss();
        }
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362817})
    public void onClickPasswordFormat(ImageButton imageButton) {
        if (!this.createTooltipContentHolder.isShowing()) {
            this.createTooltipContentHolder.setHintCasePosition(1);
            this.createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_grey);
            this.createTooltipContentHolder.setBordercolor(R.color.textview_color_vd_light);
            this.createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
            this.createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
            this.createTooltipContentHolder.build();
            this.createTooltipContentHolder.show();
        } else {
            this.createTooltipContentHolder.dismiss();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (ForgotPasswordStep3Fragment.this.createTooltipContentHolder != null && ForgotPasswordStep3Fragment.this.createTooltipContentHolder.isShowing()) {
                    ForgotPasswordStep3Fragment.this.createTooltipContentHolder.dismiss();
                }
            }
        }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    public void onNetworkCallSuccess() {
        dismissPleaseWaitDialog();
    }

    public void onNetworkCallFailure(Throwable th) {
        dismissPleaseWaitDialog();
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
    }

    public void serverException() {
        dismissPleaseWaitDialog();
    }

    public void passwordFieldEmptyCallback() {
        this.mNewPassword.setErrorBackgroundDrawable();
    }

    public void confirmPasswordFieldEmptyCallback() {
        this.mNewConfirmPassword.setErrorBackgroundDrawable();
    }

    public void confirmPasswordMismatch() {
        Toaster.makeToast(requireActivity(), getString(R.string.common_alert_pwdMismatch), 0);
    }

    public void passwordComplexityNotMet() {
        Toaster.makeToast(requireActivity(), getString(R.string.password_policy_hint), 1);
    }

    public void allFieldValidated() {
        showPleaseWaitDialog();
    }

    public void onPasswordResetSuccess() {
        dismissPleaseWaitDialog();
        Bundle arguments = getArguments();
        if (arguments != null) {
            logEvents(Events.RESET_PASSWORD.name(), 0);
            arguments.clear();
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_forgot_password_step3_to_forgot_password_step4);
    }

    public void onPasswordResetFailure() {
        Toaster.makeToast(requireActivity(), getString(R.string.forgotPassword_alert_unabeResetPassword), 0);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String obj = this.mNewPassword.getText().toString();
        String obj2 = this.mNewConfirmPassword.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            this.mResetPasswordButton.setEnabled(false);
        } else {
            this.mResetPasswordButton.setEnabled(true);
        }
    }
}
