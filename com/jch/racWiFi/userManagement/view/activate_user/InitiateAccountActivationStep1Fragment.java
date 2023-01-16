package com.jch.racWiFi.userManagement.view.activate_user;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.presenter.InitiateEnableAccountPresenter;
import com.jch_hitachi.aircloudglobal.R;

public class InitiateAccountActivationStep1Fragment extends GenericFragment implements InitiateEnableAccountPresenter.IEnableAccountPresenter, TextWatcher {
    Activity activity;
    InitiateEnableAccountPresenter initiateEnableAccountPresenter;
    private boolean isEmailSelected = false;
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131362137)
    Button mContinue;
    @BindView(2131363164)
    ConstraintLayout mCountryCodeLayout;
    @BindView(2131364069)
    TextView mCountryCodeTextView;
    @BindView(2131362920)
    ImageView mCountryFlag;
    @BindView(2131362427)
    ConstraintLayout mEmailBubbleLayout;
    @BindView(2131362371)
    EditText mEmailEditText;
    @BindView(2131362404)
    View mEmailHighLight;
    @BindView(2131364145)
    TextView mEmailSelectionTextView;
    @BindView(2131362431)
    ConstraintLayout mMobileNumberBubbleLayout;
    @BindView(2131362384)
    EditText mMobileNumberEditText;
    @BindView(2131363407)
    View mMobileNumberHighlight;
    @BindView(2131364341)
    TextView mMobileNumberSelectionTextView;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131364575)
    TextView mStepText;
    @BindView(2131364198)
    TextView mSubTitleText;
    @BindView(2131364240)
    TextView mTitleText;
    private Unbinder mUnbinder;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enter_email_or_mobile_number_forgot_password_frame_updated, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.initiateEnableAccountPresenter = new InitiateEnableAccountPresenter(this);
        ActivateAccountParcelData activateAccountParcelData = (ActivateAccountParcelData) this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Constants.LOGIN_ACTIVITY_TO_ACTIVATION_ACTIVITY_PARCEL_DATA).getDefaultValue();
        Logger.m47e("ACTIVATION_DATA", activateAccountParcelData.getEmailId() + " " + activateAccountParcelData.getPassword() + " " + activateAccountParcelData.getPhoneNumber());
        if (!this.isEmailSelected) {
            this.mEmailEditText.setVisibility(4);
            this.mEmailHighLight.setVisibility(4);
            this.mMobileNumberHighlight.setVisibility(0);
            this.mMobileNumberEditText.setVisibility(0);
            this.mCountryCodeLayout.setVisibility(0);
        } else {
            this.mEmailEditText.setVisibility(0);
            this.mEmailHighLight.setVisibility(0);
            this.mMobileNumberHighlight.setVisibility(4);
            this.mMobileNumberEditText.setVisibility(4);
            this.mCountryCodeLayout.setVisibility(4);
        }
        this.mMobileNumberEditText.addTextChangedListener(this);
        this.mEmailEditText.addTextChangedListener(this);
        this.mTitleText.setText(R.string.activateAccount_title);
        this.mStepText.setText(R.string.common_lbl_step1);
        this.mSubTitleText.setText(R.string.activateAccount_lbl_pleaseEnterEmailMobileNumber_Description);
        this.mContinue.setEnabled(false);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        updateCountryCodeSelectionOnResume(CountryCodeUIConfiguration.CURRENT);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.initiateEnableAccountPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362137})
    public void onClickContinue(View view) {
        if (this.isEmailSelected) {
            this.initiateEnableAccountPresenter.validateEmailAndSendOTP(this.mEmailEditText.getText().toString().trim());
            return;
        }
        this.initiateEnableAccountPresenter.validateMobileNumberAndSendOTP(this.mMobileNumberEditText.getText().toString().trim());
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362078})
    public void onbackClick() {
        getActivity().finish();
    }

    public void fieldEmptyCallback() {
        if (this.isEmailSelected) {
            this.mEmailEditText.setErrorBackgroundDrawable();
        } else {
            this.mMobileNumberEditText.setErrorBackgroundDrawable();
        }
    }

    public void fieldValidated(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel) {
        showPleaseWaitDialog();
    }

    public void invalidField() {
        if (this.isEmailSelected) {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidEmailFormat), 0);
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.common_alert_invalidMobNo), 0);
        }
    }

    public void onEnableAccountOtpSendSuccessResponse(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel) {
        dismissPleaseWaitDialog();
        Toast.makeText(getContext(), getString(R.string.common_alert_otpSentSuccessfully), 0).show();
        new DetailedIduModel().modelTypeId = 3;
        this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Constants.ACTIVATE_ACCOUNT_STEP1_TO_STEP2, new NavArgument.Builder().setDefaultValue(initiateEnableAccountDataModel).build());
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_account_activation_step1_to_account_activation_step2);
    }

    public void onEnableAccountOtpSendFailureResponse(DisableAccountDataModels.InitiateAccountActivationFailureResponse initiateAccountActivationFailureResponse) {
        dismissPleaseWaitDialog();
        if (initiateAccountActivationFailureResponse.statusCode == 400) {
            Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE001), 0).show();
        } else if (initiateAccountActivationFailureResponse.code == null) {
            Toast.makeText(getContext(), getString(R.string.common_alert_unableSendOtp), 0).show();
        } else if (initiateAccountActivationFailureResponse.code.equals(DisableAccountDataModels.USER_NOT_FOUND)) {
            Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE001), 0).show();
        } else if (initiateAccountActivationFailureResponse.code.equals(DisableAccountDataModels.ACTIVE_USER)) {
            Toast.makeText(getContext(), getString(R.string.errorCode_alert_EAE005), 0).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.common_alert_unableSendOtp), 0).show();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String trim = this.mEmailEditText.getText().toString().trim();
        String trim2 = this.mMobileNumberEditText.getText().toString().trim();
        this.mContinue.setEnabled(false);
        if (this.isEmailSelected) {
            if (trim.isEmpty() || !ValidationUtils.isEmailAddressValid(trim)) {
                this.mContinue.setEnabled(false);
                return;
            }
            this.mEmailEditText.setNormalBackgroundDrawable();
            this.mContinue.setEnabled(true);
        } else if (trim2.length() >= 1) {
            this.mMobileNumberEditText.setNormalBackgroundDrawable();
            this.mContinue.setEnabled(true);
        } else {
            this.mContinue.setEnabled(false);
        }
    }

    @OnClick({2131364145})
    public void onClickEmailSelector() {
        this.isEmailSelected = true;
        this.mEmailEditText.setVisibility(0);
        this.mMobileNumberEditText.setVisibility(4);
        this.mMobileNumberEditText.setText("");
        this.mEmailHighLight.setVisibility(0);
        this.mMobileNumberHighlight.setVisibility(4);
        this.mMobileNumberBubbleLayout.setVisibility(4);
        this.mCountryCodeLayout.setVisibility(4);
        this.mEmailEditText.requestFocus();
        if (this.mEmailEditText.getText().toString().isEmpty() || !ValidationUtils.isEmailAddressValid(this.mEmailEditText.getText().toString())) {
            this.mContinue.setEnabled(false);
        } else {
            this.mContinue.setEnabled(true);
        }
    }

    @OnClick({2131364341})
    public void onClickMobileNumberSelector() {
        this.isEmailSelected = false;
        this.mEmailEditText.setVisibility(4);
        this.mMobileNumberEditText.setVisibility(0);
        this.mEmailHighLight.setVisibility(4);
        this.mMobileNumberHighlight.setVisibility(0);
        this.mEmailEditText.setText("");
        this.mEmailBubbleLayout.setVisibility(4);
        this.mCountryCodeLayout.setVisibility(0);
        this.mMobileNumberEditText.requestFocus();
        if (this.mMobileNumberEditText.getText().toString().isEmpty() || !ValidationUtils.isMobileNumberValid(this.mMobileNumberEditText.getText().toString())) {
            this.mContinue.setEnabled(false);
        } else {
            this.mContinue.setEnabled(true);
        }
    }

    @OnClick({2131363164})
    public void onClickCountryCodeSelection() {
        showCountryCodeSelectionDialog();
    }

    private void showCountryCodeSelectionDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                InitiateAccountActivationStep1Fragment.this.updateCountryCodeSelection(countryCodeUIConfiguration);
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                InitiateAccountActivationStep1Fragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        countryCodeDialog.show();
        countryCodeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                InitiateAccountActivationStep1Fragment.this.mParent.setBackgroundResource(R.drawable.transparent);
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
        this.mMobileNumberEditText.setText("");
        this.mMobileNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void updateCountryCodeSelectionOnResume(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mCountryCodeTextView.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mMobileNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    public void onNetworkCallFailure(Throwable th) {
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
}
