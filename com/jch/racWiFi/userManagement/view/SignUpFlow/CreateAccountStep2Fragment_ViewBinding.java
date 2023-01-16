package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep2Fragment_ViewBinding implements Unbinder {
    private CreateAccountStep2Fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a0401;
    private View view7f0a055c;
    private View view7f0a092f;
    private View view7f0a09f3;
    private View view7f0a0b28;

    public CreateAccountStep2Fragment_ViewBinding(final CreateAccountStep2Fragment createAccountStep2Fragment, View view) {
        this.target = createAccountStep2Fragment;
        createAccountStep2Fragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mContinueButton' and method 'onClickContinue'");
        createAccountStep2Fragment.mContinueButton = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mContinueButton'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.onClickContinue(view);
            }
        });
        createAccountStep2Fragment.mEmailEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_email, "field 'mEmailEditText'", EditText.class);
        createAccountStep2Fragment.mPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_password, "field 'mPassword'", EditText.class);
        createAccountStep2Fragment.mConfirmPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_confirm_password, "field 'mConfirmPassword'", EditText.class);
        createAccountStep2Fragment.mMobileNumber = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_mobile_number, "field 'mMobileNumber'", EditText.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_terms_and_conditions, "field 'iAcceptTermsTextView' and method 'clickOnTermAndCondition'");
        createAccountStep2Fragment.iAcceptTermsTextView = (TextView) C0840Utils.castView(findRequiredView2, R.id.text_view_terms_and_conditions, "field 'iAcceptTermsTextView'", TextView.class);
        this.view7f0a0b28 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.clickOnTermAndCondition();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.image_button_password_strength_hint, "field 'mPasswordHint' and method 'onClickPasswordHint'");
        createAccountStep2Fragment.mPasswordHint = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.image_button_password_strength_hint, "field 'mPasswordHint'", ImageButton.class);
        this.view7f0a0401 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.onClickPasswordHint((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickPasswordHint", 0, ImageButton.class));
            }
        });
        createAccountStep2Fragment.mEmailBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_email_bubble_layout, "field 'mEmailBubbleLayout'", ConstraintLayout.class);
        createAccountStep2Fragment.mMobileBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_mobile_bubble_layout, "field 'mMobileBubbleLayout'", ConstraintLayout.class);
        createAccountStep2Fragment.mPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_password_bubble_layout, "field 'mPasswordBubbleLayout'", ConstraintLayout.class);
        createAccountStep2Fragment.mConfirmPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_confirm_password_bubble_layout, "field 'mConfirmPasswordBubbleLayout'", ConstraintLayout.class);
        createAccountStep2Fragment.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_flag_login, "field 'mCountryFlag'", ImageView.class);
        createAccountStep2Fragment.stepNum = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_2_of_4, "field 'stepNum'", TextView.class);
        createAccountStep2Fragment.mEmailHighLight = C0840Utils.findRequiredView(view, R.id.email_selection_highlight, "field 'mEmailHighLight'");
        createAccountStep2Fragment.mMobileNumberHighlight = C0840Utils.findRequiredView(view, R.id.mobile_number_selection_highlight, "field 'mMobileNumberHighlight'");
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.layout_country_code, "field 'mCountryCodeLayout' and method 'onClickCountryCodeSelection'");
        createAccountStep2Fragment.mCountryCodeLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView4, R.id.layout_country_code, "field 'mCountryCodeLayout'", ConstraintLayout.class);
        this.view7f0a055c = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.onClickCountryCodeSelection();
            }
        });
        createAccountStep2Fragment.mTextViewStarEmail = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_star_email, "field 'mTextViewStarEmail'", TextView.class);
        createAccountStep2Fragment.mTextViewStarMobile = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_star_mobile_number, "field 'mTextViewStarMobile'", TextView.class);
        createAccountStep2Fragment.mCountryCodeTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_number_code_login, "field 'mCountryCodeTextView'", TextView.class);
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.text_view_mobile_number, "method 'mobileNoOnClick'");
        this.view7f0a09f3 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.mobileNoOnClick();
            }
        });
        View findRequiredView6 = C0840Utils.findRequiredView(view, R.id.text_view_email, "method 'emailIdOnClick'");
        this.view7f0a092f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.emailIdOnClick();
            }
        });
        View findRequiredView7 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBack'");
        this.view7f0a011e = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createAccountStep2Fragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
    }

    public void unbind() {
        CreateAccountStep2Fragment createAccountStep2Fragment = this.target;
        if (createAccountStep2Fragment != null) {
            this.target = null;
            createAccountStep2Fragment.mParent = null;
            createAccountStep2Fragment.mContinueButton = null;
            createAccountStep2Fragment.mEmailEditText = null;
            createAccountStep2Fragment.mPassword = null;
            createAccountStep2Fragment.mConfirmPassword = null;
            createAccountStep2Fragment.mMobileNumber = null;
            createAccountStep2Fragment.iAcceptTermsTextView = null;
            createAccountStep2Fragment.mPasswordHint = null;
            createAccountStep2Fragment.mEmailBubbleLayout = null;
            createAccountStep2Fragment.mMobileBubbleLayout = null;
            createAccountStep2Fragment.mPasswordBubbleLayout = null;
            createAccountStep2Fragment.mConfirmPasswordBubbleLayout = null;
            createAccountStep2Fragment.mCountryFlag = null;
            createAccountStep2Fragment.stepNum = null;
            createAccountStep2Fragment.mEmailHighLight = null;
            createAccountStep2Fragment.mMobileNumberHighlight = null;
            createAccountStep2Fragment.mCountryCodeLayout = null;
            createAccountStep2Fragment.mTextViewStarEmail = null;
            createAccountStep2Fragment.mTextViewStarMobile = null;
            createAccountStep2Fragment.mCountryCodeTextView = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a0b28.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0b28 = null;
            this.view7f0a0401.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0401 = null;
            this.view7f0a055c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a055c = null;
            this.view7f0a09f3.setOnClickListener((View.OnClickListener) null);
            this.view7f0a09f3 = null;
            this.view7f0a092f.setOnClickListener((View.OnClickListener) null);
            this.view7f0a092f = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
