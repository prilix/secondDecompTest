package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep3Fragment_ViewBinding implements Unbinder {
    private CreateAccountStep3Fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a0a5d;

    public CreateAccountStep3Fragment_ViewBinding(final CreateAccountStep3Fragment createAccountStep3Fragment, View view) {
        this.target = createAccountStep3Fragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mContinueButton' and method 'onClickContinue'");
        createAccountStep3Fragment.mContinueButton = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mContinueButton'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep3Fragment.onClickContinue();
            }
        });
        createAccountStep3Fragment.mOtp = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_otp, "field 'mOtp'", EditText.class);
        createAccountStep3Fragment.stepNum = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_3_of_4, "field 'stepNum'", TextView.class);
        createAccountStep3Fragment.mSMSNumberTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_sms, "field 'mSMSNumberTextView'", TextView.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onBackButtonPressed'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep3Fragment.onBackButtonPressed();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.text_view_resend_code, "method 'onClickResend'");
        this.view7f0a0a5d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep3Fragment.onClickResend();
            }
        });
    }

    public void unbind() {
        CreateAccountStep3Fragment createAccountStep3Fragment = this.target;
        if (createAccountStep3Fragment != null) {
            this.target = null;
            createAccountStep3Fragment.mContinueButton = null;
            createAccountStep3Fragment.mOtp = null;
            createAccountStep3Fragment.stepNum = null;
            createAccountStep3Fragment.mSMSNumberTextView = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0a5d.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a5d = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
