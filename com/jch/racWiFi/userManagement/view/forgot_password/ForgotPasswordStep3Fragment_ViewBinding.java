package com.jch.racWiFi.userManagement.view.forgot_password;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep3Fragment_ViewBinding implements Unbinder {
    private ForgotPasswordStep3Fragment target;
    private View view7f0a011e;
    private View view7f0a0177;
    private View view7f0a0401;

    public ForgotPasswordStep3Fragment_ViewBinding(final ForgotPasswordStep3Fragment forgotPasswordStep3Fragment, View view) {
        this.target = forgotPasswordStep3Fragment;
        forgotPasswordStep3Fragment.mNewPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_new_password, "field 'mNewPassword'", EditText.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_reset_password, "field 'mResetPasswordButton' and method 'onClickResetPassword'");
        forgotPasswordStep3Fragment.mResetPasswordButton = (Button) C0840Utils.castView(findRequiredView, R.id.button_reset_password, "field 'mResetPasswordButton'", Button.class);
        this.view7f0a0177 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep3Fragment.onClickResetPassword(view);
            }
        });
        forgotPasswordStep3Fragment.mNewConfirmPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_confirm_new_password, "field 'mNewConfirmPassword'", EditText.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'onClickBack'");
        forgotPasswordStep3Fragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep3Fragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.image_button_password_strength_hint, "field 'mPasswordFormat' and method 'onClickPasswordFormat'");
        forgotPasswordStep3Fragment.mPasswordFormat = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.image_button_password_strength_hint, "field 'mPasswordFormat'", ImageButton.class);
        this.view7f0a0401 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep3Fragment.onClickPasswordFormat((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickPasswordFormat", 0, ImageButton.class));
            }
        });
        forgotPasswordStep3Fragment.mConFirmNewPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_confirm_password_bubble_layout, "field 'mConFirmNewPasswordBubbleLayout'", ConstraintLayout.class);
    }

    public void unbind() {
        ForgotPasswordStep3Fragment forgotPasswordStep3Fragment = this.target;
        if (forgotPasswordStep3Fragment != null) {
            this.target = null;
            forgotPasswordStep3Fragment.mNewPassword = null;
            forgotPasswordStep3Fragment.mResetPasswordButton = null;
            forgotPasswordStep3Fragment.mNewConfirmPassword = null;
            forgotPasswordStep3Fragment.mBack = null;
            forgotPasswordStep3Fragment.mPasswordFormat = null;
            forgotPasswordStep3Fragment.mConFirmNewPasswordBubbleLayout = null;
            this.view7f0a0177.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0177 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0401.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0401 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
