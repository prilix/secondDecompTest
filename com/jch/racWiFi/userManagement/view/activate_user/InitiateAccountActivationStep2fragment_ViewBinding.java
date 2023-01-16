package com.jch.racWiFi.userManagement.view.activate_user;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class InitiateAccountActivationStep2fragment_ViewBinding implements Unbinder {
    private InitiateAccountActivationStep2fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a0a5d;

    public InitiateAccountActivationStep2fragment_ViewBinding(final InitiateAccountActivationStep2fragment initiateAccountActivationStep2fragment, View view) {
        this.target = initiateAccountActivationStep2fragment;
        initiateAccountActivationStep2fragment.mOTP = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_otp, "field 'mOTP'", EditText.class);
        initiateAccountActivationStep2fragment.mEmailAddressObscure = (TextView) C0840Utils.findOptionalViewAsType(view, R.id.otp_sent_over_email, "field 'mEmailAddressObscure'", TextView.class);
        initiateAccountActivationStep2fragment.mMobileNumberObscure = (TextView) C0840Utils.findOptionalViewAsType(view, R.id.otp_sent_over_mobile_number, "field 'mMobileNumberObscure'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mButtonContinue' and method 'onClickContinue'");
        initiateAccountActivationStep2fragment.mButtonContinue = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mButtonContinue'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep2fragment.onClickContinue(view);
            }
        });
        initiateAccountActivationStep2fragment.mForgotPasswordTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_forgot_password_title, "field 'mForgotPasswordTitle'", TextView.class);
        initiateAccountActivationStep2fragment.mStepText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_2, "field 'mStepText'", TextView.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_resend_code, "method 'onClickResendOTP'");
        this.view7f0a0a5d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep2fragment.onClickResendOTP();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBack'");
        this.view7f0a011e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep2fragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
    }

    public void unbind() {
        InitiateAccountActivationStep2fragment initiateAccountActivationStep2fragment = this.target;
        if (initiateAccountActivationStep2fragment != null) {
            this.target = null;
            initiateAccountActivationStep2fragment.mOTP = null;
            initiateAccountActivationStep2fragment.mEmailAddressObscure = null;
            initiateAccountActivationStep2fragment.mMobileNumberObscure = null;
            initiateAccountActivationStep2fragment.mButtonContinue = null;
            initiateAccountActivationStep2fragment.mForgotPasswordTitle = null;
            initiateAccountActivationStep2fragment.mStepText = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a0a5d.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a5d = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
