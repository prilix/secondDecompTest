package com.jch.racWiFi.userManagement.view.forgot_password;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.presenter.ForgotPasswordStep1Presenter;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep1Fragment extends GenericFragment implements ForgotPasswordStep1Presenter.IForgotPasswordStep1Presenter, TextWatcher {
    private boolean isEmailSelected = false;
    private Activity mActivity;
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
    private ForgotPasswordStep1Presenter mForgotPasswordStep1Presenter;
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
    private Unbinder mUnbinder;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static ForgotPasswordStep1Fragment newInstance() {
        ForgotPasswordStep1Fragment forgotPasswordStep1Fragment = new ForgotPasswordStep1Fragment();
        forgotPasswordStep1Fragment.setNewBundleAsArgument();
        return forgotPasswordStep1Fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = requireActivity();
        CountryCodeUIConfiguration.changeToDeviceDefault(requireActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enter_email_or_mobile_number_forgot_password_frame_updated, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mForgotPasswordStep1Presenter = new ForgotPasswordStep1Presenter(this);
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
        this.mForgotPasswordStep1Presenter.registerEventBus();
        this.mMobileNumberEditText.addTextChangedListener(this);
        this.mEmailEditText.addTextChangedListener(this);
        this.mContinue.setEnabled(false);
        ViewUtils.setOutlineCountryImage(this.mCountryFlag);
        this.mCountryFlag.setElevation(ViewUtils.convertDpToPixel(3.0f, requireActivity()));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        updateCountryCodeSelectionOnResume(CountryCodeUIConfiguration.CURRENT);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mForgotPasswordStep1Presenter.unregisterEventBus();
        this.mForgotPasswordStep1Presenter.removeCallbacks();
        this.mUnbinder.unbind();
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

    @OnClick({2131362137})
    public void onClickContinue(View view) {
        if (this.isEmailSelected) {
            this.mForgotPasswordStep1Presenter.validateEmailAndSendOTP(this.mEmailEditText.getText().toString().trim());
            return;
        }
        this.mForgotPasswordStep1Presenter.validateMobileNumberAndSendOTP(this.mMobileNumberEditText.getText().toString().trim());
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void fieldEmptyCallback() {
        if (this.isEmailSelected) {
            this.mEmailEditText.setErrorBackgroundDrawable();
        } else {
            this.mMobileNumberEditText.setErrorBackgroundDrawable();
        }
    }

    public void invalidField() {
        if (this.isEmailSelected) {
            Toaster.makeToast(requireActivity(), getString(R.string.common_alert_invalidEmailFormat), 0);
        } else {
            Toaster.makeToast(requireActivity(), getString(R.string.common_alert_invalidMobNo), 0);
        }
    }

    public void fieldValidated(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData) {
        showPleaseWaitDialog();
    }

    public void onOTPSendSuccess(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ForgotPasswordDataModels.ForgotPasswordOTPRequestData.FORGOT_PASSWORD_REQUEST_OTP_KEY, forgotPasswordOTPRequestData);
        Bundle arguments = getArguments();
        if (arguments != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, arguments.getLong(Constants.Keys.ENTRY_TIME));
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_forgot_password_step1_to_forgot_password_step2, bundle);
    }

    public void userIDNotRegistered() {
        Toaster.makeToast(requireActivity(), getString(R.string.forgotPassword_userNotExists_alert), 0);
    }

    public void onOTPSendFailure() {
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

    private void showCountryCodeSelectionDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(requireActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                ForgotPasswordStep1Fragment.this.updateCountryCodeSelection(countryCodeUIConfiguration);
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                ForgotPasswordStep1Fragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        countryCodeDialog.show();
        countryCodeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                ForgotPasswordStep1Fragment.this.mParent.setBackgroundResource(R.drawable.transparent);
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
}
