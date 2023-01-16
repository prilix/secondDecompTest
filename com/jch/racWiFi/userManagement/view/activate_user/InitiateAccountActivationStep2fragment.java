package com.jch.racWiFi.userManagement.view.activate_user;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.SecurityUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.p010di.util.TokenInfo;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.presenter.EnableAccountPresenter;
import com.jch_hitachi.aircloudglobal.R;

public class InitiateAccountActivationStep2fragment extends GenericFragment implements TextWatcher, EnableAccountPresenter.IEnableAccountPresenter {
    EnableAccountPresenter enableAccountPresenter;
    DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel;
    private Activity mActivity;
    @BindView(2131362137)
    Button mButtonContinue;
    @BindView(2131363518)
    TextView mEmailAddressObscure;
    @BindView(2131364240)
    TextView mForgotPasswordTitle;
    @BindView(2131363519)
    TextView mMobileNumberObscure;
    @BindView(2131362387)
    EditText mOTP;
    @BindView(2131364580)
    TextView mStepText;
    private Unbinder mUnbinder;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static InitiateAccountActivationStep2fragment newInstance() {
        InitiateAccountActivationStep2fragment initiateAccountActivationStep2fragment = new InitiateAccountActivationStep2fragment();
        initiateAccountActivationStep2fragment.setNewBundleAsArgument();
        return initiateAccountActivationStep2fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = requireActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel2 = (DisableAccountDataModels.InitiateEnableAccountDataModel) this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Constants.ACTIVATE_ACCOUNT_STEP1_TO_STEP2).getDefaultValue();
        this.initiateEnableAccountDataModel = initiateEnableAccountDataModel2;
        if (initiateEnableAccountDataModel2 != null) {
            view = initiateEnableAccountDataModel2.requestOtpToEmail ? layoutInflater.inflate(R.layout.enter_otp_email_activate_account_frame, viewGroup, false) : layoutInflater.inflate(R.layout.enter_otp_mobile_activate_account_frame, viewGroup, false);
        } else {
            view = null;
        }
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        this.mOTP.addTextChangedListener(this);
        this.mButtonContinue.setEnabled(false);
        if (this.initiateEnableAccountDataModel.requestOtpToEmail) {
            this.mEmailAddressObscure.setText(SecurityUtils.staringEmail(this.initiateEnableAccountDataModel.email));
        } else {
            this.mMobileNumberObscure.setText(SecurityUtils.staringNumber(this.initiateEnableAccountDataModel.mobileNumber));
        }
        this.mForgotPasswordTitle.setText(getString(R.string.activateAccount_title));
        this.mStepText.setText(getString(R.string.common_lbl_step2));
        this.enableAccountPresenter = new EnableAccountPresenter(this);
        return view;
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.enableAccountPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362137})
    public void onClickContinue(View view) {
        String str;
        String trim = this.mOTP.getText().toString().trim();
        if (this.initiateEnableAccountDataModel.requestOtpToEmail) {
            str = this.initiateEnableAccountDataModel.email;
        } else {
            str = this.initiateEnableAccountDataModel.mobileNumber;
        }
        if (!trim.isEmpty() && trim != null) {
            if (this.initiateEnableAccountDataModel.requestOtpToEmail) {
                showPleaseWaitDialog();
                this.enableAccountPresenter.verifyOTPEmail(str, trim);
                return;
            }
            showPleaseWaitDialog();
            this.enableAccountPresenter.verifyOTPMobileNumber(str, trim);
        }
    }

    @OnClick({2131364445})
    public void onClickResendOTP() {
        showPleaseWaitDialog();
        sendOTP();
    }

    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
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
        this.enableAccountPresenter.sendOTP(this.initiateEnableAccountDataModel);
    }

    public void onEnableAccountOtpSendSuccessResponse(DisableAccountDataModels.EnableAccountSuccessResponse enableAccountSuccessResponse) {
        dismissPleaseWaitDialog();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(enableAccountSuccessResponse.token);
        tokenInfo.setRefreshToken(enableAccountSuccessResponse.refreshToken);
        tokenInfo.setNew(enableAccountSuccessResponse.newUser);
        TokenUtil.getInstance().persist(tokenInfo);
        this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Constants.ACTIVATE_ACCOUNT_STEP2_TO_SUCCESS_SCREEN, new NavArgument.Builder().setDefaultValue(enableAccountSuccessResponse).build());
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_account_activation_step2_to_account_activation_success);
    }

    public void onEnableAccountOtpSendFailureResponse(DisableAccountDataModels.EnableAccountFailureResponse enableAccountFailureResponse) {
        dismissPleaseWaitDialog();
        if (enableAccountFailureResponse.code.equals(DisableAccountDataModels.INVALID_OTP)) {
            Toast.makeText(getContext(), getString(R.string.errorCode_alert_IOE001), 0).show();
        } else if (enableAccountFailureResponse.code.equals(DisableAccountDataModels.USER_NOT_FOUND)) {
            Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE001), 0).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.activateAccount_alert_unableActivateAccount), 0).show();
        }
    }

    public void onOtpResendSuccessResponse() {
        dismissPleaseWaitDialog();
        Toast.makeText(getContext(), getString(R.string.common_alert_otpSentSuccessfully), 0).show();
    }

    public void onOtpResendFailureResponse(DisableAccountDataModels.InitiateAccountActivationFailureResponse initiateAccountActivationFailureResponse) {
        dismissPleaseWaitDialog();
        String str = initiateAccountActivationFailureResponse.code;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1995143804:
                if (str.equals("NFE001")) {
                    c = 0;
                    break;
                }
                break;
            case -1940626273:
                if (str.equals(DisableAccountDataModels.InitiateAccountActivationFailureResponse.EMAIL_OR_PHONE_NUMBER_CANNOT_BE_EMPTY)) {
                    c = 1;
                    break;
                }
                break;
            case 2037543532:
                if (str.equals(DisableAccountDataModels.InitiateAccountActivationFailureResponse.ACTIVE_USER_EXIST)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE001), 0).show();
                return;
            case 1:
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_PCF002), 0).show();
                return;
            case 2:
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_EAE005), 0).show();
                return;
            default:
                Toast.makeText(getContext(), getString(R.string.common_alert_unableSendOtp), 0).show();
                return;
        }
    }

    public void onNetworkCallFailure(Throwable th) {
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
}
