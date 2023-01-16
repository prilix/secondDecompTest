package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.ChangePasswordModels;
import com.jch.racWiFi.userManagement.model.PasswordStrengthModel;
import com.jch.racWiFi.userManagement.network.PasswordStrengthCheckNetworkDispatcher;
import com.jch.racWiFi.userManagement.presenter.ChangePasswordPresenter;
import com.jch_hitachi.aircloudglobal.R;

public class ChangePasswordFragment extends GenericFragment implements ChangePasswordPresenter.IChangePasswordPresenter, TextWatcher, LifecycleOwner {
    public static boolean isPasswordChanged;
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolder;
    /* access modifiers changed from: private */
    public ValidationUtils.PasswordStrength currentPasswordStrength;
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131362130)
    Button mChangePassword;
    private ChangePasswordPresenter mChangePasswordPresenter;
    @BindView(2131362424)
    ConstraintLayout mConFirmNewPasswordBubbleLayout;
    @BindView(2131362362)
    EditText mConfirmNewPassword;
    @BindView(2131362369)
    EditText mCurrentPassword;
    @BindView(2131362426)
    ConstraintLayout mCurrentPasswordBubbleLayout;
    @BindView(2131362377)
    EditText mNewPassword;
    @BindView(2131362432)
    ConstraintLayout mNewPasswordBubbleLayout;
    @BindView(2131362817)
    ImageButton mPasswordHintButton;
    /* access modifiers changed from: private */
    public PasswordStrengthUiLogicHolder mPasswordStrengthUiLogicHolder;
    @BindView(2131364471)
    TextView mSave;
    private Unbinder mUnbinder;

    private void checkAndUpdatePasswordStrength() {
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @OnClick({2131364471})
    public void onClickSave(TextView textView) {
    }

    public void onNetworkCallSuccess() {
    }

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        changePasswordFragment.setNewBundleAsArgument();
        return changePasswordFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_account_change_password_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        PasswordStrengthUiLogicHolder passwordStrengthUiLogicHolder = new PasswordStrengthUiLogicHolder(requireActivity(), inflate);
        this.mPasswordStrengthUiLogicHolder = passwordStrengthUiLogicHolder;
        passwordStrengthUiLogicHolder.hidePasswordStrengthMeter();
        this.mChangePasswordPresenter = new ChangePasswordPresenter(this);
        this.createTooltipContentHolder = new CreateTooltipContentHolder(getContext(), (View) this.mPasswordHintButton, getString(R.string.password_policy_hint));
        this.mChangePassword.setEnabled(false);
        this.mCurrentPassword.addTextChangedListener(this);
        this.mNewPassword.addTextChangedListener(this);
        this.mConfirmNewPassword.addTextChangedListener(this);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mCurrentPassword);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mCurrentPasswordBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mCurrentPassword.addTextChangedListener(genericEmptyEditTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher2 = new GenericEmptyEditTextWatcher(this.mConfirmNewPassword);
        genericEmptyEditTextWatcher2.setLayoutToolTip(this.mConFirmNewPasswordBubbleLayout);
        genericEmptyEditTextWatcher2.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mConfirmNewPassword.addTextChangedListener(genericEmptyEditTextWatcher2);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher3 = new GenericEmptyEditTextWatcher(this.mNewPassword);
        genericEmptyEditTextWatcher3.setLayoutToolTip(this.mNewPasswordBubbleLayout);
        genericEmptyEditTextWatcher3.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mNewPassword.addTextChangedListener(genericEmptyEditTextWatcher3);
        this.mCurrentPassword.disableCopyPaste();
        this.mNewPassword.disableCopyPaste();
        this.mConfirmNewPassword.disableCopyPaste();
        this.mCurrentPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = ChangePasswordFragment.this.mNewPassword.getText().toString();
                String obj2 = ChangePasswordFragment.this.mConfirmNewPassword.getText().toString();
                String obj3 = ChangePasswordFragment.this.mCurrentPassword.getText().toString();
                if (obj.isEmpty() || obj2.isEmpty() || obj3.isEmpty() || ChangePasswordFragment.this.currentPasswordStrength == null || !ChangePasswordFragment.this.currentPasswordStrength.isStrongEnough()) {
                    ChangePasswordFragment.this.mChangePassword.setEnabled(false);
                } else {
                    ChangePasswordFragment.this.mChangePassword.setEnabled(true);
                }
            }
        });
        this.mNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    if (ChangePasswordFragment.this.mNewPassword.getText().toString().isEmpty()) {
                        ChangePasswordFragment.this.currentPasswordStrength = ValidationUtils.PasswordStrength.EMPTY;
                        ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.this;
                        changePasswordFragment.computeViewState(changePasswordFragment.currentPasswordStrength);
                        ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeBackgroundNormal();
                        ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
                        return;
                    }
                    PasswordStrengthModel.PasswordStrengthRequestModel passwordStrengthRequestModel = new PasswordStrengthModel.PasswordStrengthRequestModel();
                    passwordStrengthRequestModel.password = ChangePasswordFragment.this.mNewPassword.getText().toString();
                    passwordStrengthRequestModel.emailId = UserInfo.getCurrentUserInfo(ChangePasswordFragment.this.getCoreActivity()).email;
                    passwordStrengthRequestModel.mobileNumber = UserInfo.getCurrentUserInfo(ChangePasswordFragment.this.getCoreActivity()).phoneNumber;
                    passwordStrengthRequestModel.sanityInputs = null;
                    new PasswordStrengthCheckNetworkDispatcher().checkPasswordStrength(passwordStrengthRequestModel).observeSingleEvent(ChangePasswordFragment.this.getViewLifecycleOwner(), new Observer<GenericResponse>() {
                        public void onChanged(GenericResponse genericResponse) {
                            if (genericResponse.unableToReachServer()) {
                                SingleChoiceDialog singleChoiceDialogWithAlertTitle = GenericAlertUtils.getSingleChoiceDialogWithAlertTitle(ChangePasswordFragment.this.requireActivity(), ChangePasswordFragment.this.getString(R.string.common_alert_unableToConnectToNw));
                                singleChoiceDialogWithAlertTitle.setTitleString(ChangePasswordFragment.this.getString(R.string.common_lbl_passWordStrengthCriteria));
                                singleChoiceDialogWithAlertTitle.show();
                                return;
                            }
                            PasswordStrengthModel.PasswordStrengthResponseModel passwordStrengthResponseModel = (PasswordStrengthModel.PasswordStrengthResponseModel) genericResponse.getBodyOfType(PasswordStrengthModel.PasswordStrengthResponseModel.class);
                            ValidationUtils.PasswordStrength passwordStrength = ValidationUtils.PasswordStrength.EMPTY;
                            if (passwordStrengthResponseModel != null) {
                                passwordStrength = passwordStrengthResponseModel.strength != null ? ValidationUtils.PasswordStrength.valueOf(passwordStrengthResponseModel.strength) : ValidationUtils.PasswordStrength.EMPTY;
                                if (passwordStrength.isStrongEnough()) {
                                    ChangePasswordFragment.this.mNewPassword.setNormalBackgroundDrawable();
                                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthUi();
                                } else {
                                    ChangePasswordFragment.this.mNewPassword.setErrorBackgroundDrawable();
                                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.updatedRecyclerViewWithSuggestions(passwordStrengthResponseModel);
                                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.showDynamicPasswordStrengthUi();
                                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeBackgroundRed();
                                }
                            }
                            ChangePasswordFragment.this.currentPasswordStrength = passwordStrength;
                            ChangePasswordFragment.this.computeViewState(passwordStrength);
                        }
                    });
                } else if (ChangePasswordFragment.this.currentPasswordStrength == null) {
                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                } else if (!ChangePasswordFragment.this.currentPasswordStrength.isStrongEnough()) {
                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.hidePasswordStrengthMeter();
                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
                } else {
                    ChangePasswordFragment.this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
                }
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void computeViewState(ValidationUtils.PasswordStrength passwordStrength) {
        this.mPasswordStrengthUiLogicHolder.updatePasswordStrengthUI(passwordStrength);
        String obj = this.mNewPassword.getText().toString();
        String obj2 = this.mConfirmNewPassword.getText().toString();
        String obj3 = this.mCurrentPassword.getText().toString();
        if (!passwordStrength.isStrongEnough() || obj3.isEmpty() || obj.isEmpty() || obj2.isEmpty()) {
            this.mChangePassword.setEnabled(false);
        } else {
            this.mChangePassword.setEnabled(true);
        }
        if (!this.mNewPassword.hasFocus() && !passwordStrength.equals(ValidationUtils.PasswordStrength.EMPTY)) {
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthMeter();
        }
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (ChangePasswordFragment.this.createTooltipContentHolder != null && ChangePasswordFragment.this.createTooltipContentHolder.isShowing()) {
                    ChangePasswordFragment.this.createTooltipContentHolder.dismiss();
                }
                ChangePasswordFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mChangePasswordPresenter.removeCallbacks();
        this.mPasswordStrengthUiLogicHolder.unbind();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({2131362817})
    public void onClickPasswordHintTip(ImageButton imageButton) {
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
                if (ChangePasswordFragment.this.createTooltipContentHolder != null && ChangePasswordFragment.this.createTooltipContentHolder.isShowing()) {
                    ChangePasswordFragment.this.createTooltipContentHolder.dismiss();
                }
            }
        }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362130})
    public void onClickChangePassword(Button button) {
        this.mChangePasswordPresenter.validateFields(this.mCurrentPassword.getText().toString(), this.mNewPassword.getText().toString(), this.mConfirmNewPassword.getText().toString());
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362078})
    public void OnClickBack(ImageButton imageButton) {
        CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolder;
        if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
            this.createTooltipContentHolder.dismiss();
        }
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void currentPasswordFieldEmptyCallback() {
        this.mCurrentPassword.setErrorBackgroundDrawable();
    }

    public void newPasswordFieldEmptyCallback() {
        this.mNewPassword.setErrorBackgroundDrawable();
    }

    public void confirmNewPasswordEmptyCallback() {
        this.mConfirmNewPassword.setErrorBackgroundDrawable();
    }

    public void newPasswordAndOldPasswordCannotBeSame() {
        showSingleChiocePopUp(getString(R.string.myAcc_alert_oldAndNewPwdCannotSame));
    }

    public void passwordMismatch() {
        showSingleChiocePopUp(getString(R.string.common_alert_pwdMismatch));
    }

    public void onChangePasswordSuccess() {
        Context applicationContext = getCoreActivity().getApplicationContext();
        Toaster.makeToast(applicationContext, getString(R.string.myAcc_alert_pwdUpdateSuccess) + ". " + getString(R.string.myAcc_alert_loginAgain), 1);
        Log.e("Change", "onChangeSuccess");
        this.mFragmentToActivityCallback.logOutFromApplication();
        TokenUtil.getInstance().removeTokenInfo();
    }

    public void onChangePasswordFailure(int i) {
        Log.e("Change", "onChangeFailure");
        if (i == 400) {
            dismissPleaseWaitDialog();
            showSingleChiocePopUp(getString(R.string.myAcc_alert_oldPwdIncorrect));
        } else if (i != 401) {
            dismissPleaseWaitDialog();
            showSingleChiocePopUp(getString(R.string.myAcc_alert_updatePwdFailed));
        } else {
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    ChangePasswordFragment.this.callChangePasswordAPI();
                }
            }, false);
        }
    }

    /* access modifiers changed from: private */
    public void callChangePasswordAPI() {
        onClickChangePassword((Button) null);
    }

    public void allFieldsValidated(ChangePasswordModels.ChangePasswordData changePasswordData) {
        showPleaseWaitDialog();
    }

    public void passwordStrengthInadequate() {
        showSingleChiocePopUp(getString(R.string.createAccount_alert_passwordStrengthError));
    }

    public void onNetworkCallFailure(Throwable th) {
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        dismissPleaseWaitDialog();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ValidationUtils.PasswordStrength passwordStrength;
        String obj = this.mCurrentPassword.getText().toString();
        String obj2 = this.mNewPassword.getText().toString();
        String obj3 = this.mConfirmNewPassword.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty() || obj3.isEmpty() || (passwordStrength = this.currentPasswordStrength) == null || !passwordStrength.isStrongEnough()) {
            this.mChangePassword.setEnabled(false);
        } else {
            this.mChangePassword.setEnabled(true);
        }
        if (obj2.isEmpty() && this.mNewPassword.hasFocus()) {
            ValidationUtils.PasswordStrength passwordStrength2 = ValidationUtils.PasswordStrength.EMPTY;
            this.currentPasswordStrength = passwordStrength2;
            computeViewState(passwordStrength2);
            this.mPasswordStrengthUiLogicHolder.makeBackgroundNormal();
            this.mPasswordStrengthUiLogicHolder.showInitialPasswordStrengthUi();
            this.mPasswordStrengthUiLogicHolder.makeVisiblePasswordStrengthUi();
        }
    }

    private void showSingleChiocePopUp(String str) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }
}
