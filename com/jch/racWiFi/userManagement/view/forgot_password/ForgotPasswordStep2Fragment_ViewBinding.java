package com.jch.racWiFi.userManagement.view.forgot_password;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep2Fragment_ViewBinding implements Unbinder {
    private ForgotPasswordStep2Fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a0a5d;

    public ForgotPasswordStep2Fragment_ViewBinding(final ForgotPasswordStep2Fragment forgotPasswordStep2Fragment, View view) {
        this.target = forgotPasswordStep2Fragment;
        forgotPasswordStep2Fragment.mOTP = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_otp, "field 'mOTP'", EditText.class);
        forgotPasswordStep2Fragment.mEmailAddressObscure = (TextView) C0840Utils.findOptionalViewAsType(view, R.id.otp_sent_over_email, "field 'mEmailAddressObscure'", TextView.class);
        forgotPasswordStep2Fragment.mMobileNumberObscure = (TextView) C0840Utils.findOptionalViewAsType(view, R.id.otp_sent_over_mobile_number, "field 'mMobileNumberObscure'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mButtonContinue' and method 'onClickContinue'");
        forgotPasswordStep2Fragment.mButtonContinue = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mButtonContinue'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep2Fragment.onClickContinue(view);
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_resend_code, "method 'onClickResendOTP'");
        this.view7f0a0a5d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep2Fragment.onClickResendOTP();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBack'");
        this.view7f0a011e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep2Fragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
    }

    public void unbind() {
        ForgotPasswordStep2Fragment forgotPasswordStep2Fragment = this.target;
        if (forgotPasswordStep2Fragment != null) {
            this.target = null;
            forgotPasswordStep2Fragment.mOTP = null;
            forgotPasswordStep2Fragment.mEmailAddressObscure = null;
            forgotPasswordStep2Fragment.mMobileNumberObscure = null;
            forgotPasswordStep2Fragment.mButtonContinue = null;
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
