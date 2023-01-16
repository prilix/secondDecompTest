package com.jch.racWiFi.userManagement.view.forgot_password;

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

public class ForgotPasswordStep1Fragment_ViewBinding implements Unbinder {
    private ForgotPasswordStep1Fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a055c;
    private View view7f0a0931;
    private View view7f0a09f5;

    public ForgotPasswordStep1Fragment_ViewBinding(final ForgotPasswordStep1Fragment forgotPasswordStep1Fragment, View view) {
        this.target = forgotPasswordStep1Fragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'onClickBack'");
        forgotPasswordStep1Fragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep1Fragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
        forgotPasswordStep1Fragment.mEmailEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_email, "field 'mEmailEditText'", EditText.class);
        forgotPasswordStep1Fragment.mMobileNumberEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_mobile_number, "field 'mMobileNumberEditText'", EditText.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_email_forgot_password, "field 'mEmailSelectionTextView' and method 'onClickEmailSelector'");
        forgotPasswordStep1Fragment.mEmailSelectionTextView = (TextView) C0840Utils.castView(findRequiredView2, R.id.text_view_email_forgot_password, "field 'mEmailSelectionTextView'", TextView.class);
        this.view7f0a0931 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep1Fragment.onClickEmailSelector();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.text_view_mobile_number_forgot_password, "field 'mMobileNumberSelectionTextView' and method 'onClickMobileNumberSelector'");
        forgotPasswordStep1Fragment.mMobileNumberSelectionTextView = (TextView) C0840Utils.castView(findRequiredView3, R.id.text_view_mobile_number_forgot_password, "field 'mMobileNumberSelectionTextView'", TextView.class);
        this.view7f0a09f5 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep1Fragment.onClickMobileNumberSelector();
            }
        });
        forgotPasswordStep1Fragment.mEmailHighLight = C0840Utils.findRequiredView(view, R.id.email_selection_highlight, "field 'mEmailHighLight'");
        forgotPasswordStep1Fragment.mMobileNumberHighlight = C0840Utils.findRequiredView(view, R.id.mobile_number_selection_highlight, "field 'mMobileNumberHighlight'");
        forgotPasswordStep1Fragment.mMobileNumberBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_mobile_number_bubble_layout, "field 'mMobileNumberBubbleLayout'", ConstraintLayout.class);
        forgotPasswordStep1Fragment.mEmailBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_email_bubble_layout, "field 'mEmailBubbleLayout'", ConstraintLayout.class);
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mContinue' and method 'onClickContinue'");
        forgotPasswordStep1Fragment.mContinue = (Button) C0840Utils.castView(findRequiredView4, R.id.button_continue, "field 'mContinue'", Button.class);
        this.view7f0a0159 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep1Fragment.onClickContinue(view);
            }
        });
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.layout_country_code, "field 'mCountryCodeLayout' and method 'onClickCountryCodeSelection'");
        forgotPasswordStep1Fragment.mCountryCodeLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView5, R.id.layout_country_code, "field 'mCountryCodeLayout'", ConstraintLayout.class);
        this.view7f0a055c = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordStep1Fragment.onClickCountryCodeSelection();
            }
        });
        forgotPasswordStep1Fragment.mCountryCodeTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_number_code_login, "field 'mCountryCodeTextView'", TextView.class);
        forgotPasswordStep1Fragment.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_flag_login, "field 'mCountryFlag'", ImageView.class);
        forgotPasswordStep1Fragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
    }

    public void unbind() {
        ForgotPasswordStep1Fragment forgotPasswordStep1Fragment = this.target;
        if (forgotPasswordStep1Fragment != null) {
            this.target = null;
            forgotPasswordStep1Fragment.mBack = null;
            forgotPasswordStep1Fragment.mEmailEditText = null;
            forgotPasswordStep1Fragment.mMobileNumberEditText = null;
            forgotPasswordStep1Fragment.mEmailSelectionTextView = null;
            forgotPasswordStep1Fragment.mMobileNumberSelectionTextView = null;
            forgotPasswordStep1Fragment.mEmailHighLight = null;
            forgotPasswordStep1Fragment.mMobileNumberHighlight = null;
            forgotPasswordStep1Fragment.mMobileNumberBubbleLayout = null;
            forgotPasswordStep1Fragment.mEmailBubbleLayout = null;
            forgotPasswordStep1Fragment.mContinue = null;
            forgotPasswordStep1Fragment.mCountryCodeLayout = null;
            forgotPasswordStep1Fragment.mCountryCodeTextView = null;
            forgotPasswordStep1Fragment.mCountryFlag = null;
            forgotPasswordStep1Fragment.mParent = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0931.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0931 = null;
            this.view7f0a09f5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a09f5 = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a055c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a055c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
