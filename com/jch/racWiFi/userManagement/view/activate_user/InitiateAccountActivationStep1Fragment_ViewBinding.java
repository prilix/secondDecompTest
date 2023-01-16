package com.jch.racWiFi.userManagement.view.activate_user;

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

public class InitiateAccountActivationStep1Fragment_ViewBinding implements Unbinder {
    private InitiateAccountActivationStep1Fragment target;
    private View view7f0a011e;
    private View view7f0a0159;
    private View view7f0a055c;
    private View view7f0a0931;
    private View view7f0a09f5;

    public InitiateAccountActivationStep1Fragment_ViewBinding(final InitiateAccountActivationStep1Fragment initiateAccountActivationStep1Fragment, View view) {
        this.target = initiateAccountActivationStep1Fragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mContinue' and method 'onClickContinue'");
        initiateAccountActivationStep1Fragment.mContinue = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mContinue'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep1Fragment.onClickContinue(view);
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'onbackClick'");
        initiateAccountActivationStep1Fragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep1Fragment.onbackClick();
            }
        });
        initiateAccountActivationStep1Fragment.mEmailEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_email, "field 'mEmailEditText'", EditText.class);
        initiateAccountActivationStep1Fragment.mMobileNumberEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_mobile_number, "field 'mMobileNumberEditText'", EditText.class);
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.text_view_email_forgot_password, "field 'mEmailSelectionTextView' and method 'onClickEmailSelector'");
        initiateAccountActivationStep1Fragment.mEmailSelectionTextView = (TextView) C0840Utils.castView(findRequiredView3, R.id.text_view_email_forgot_password, "field 'mEmailSelectionTextView'", TextView.class);
        this.view7f0a0931 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep1Fragment.onClickEmailSelector();
            }
        });
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.text_view_mobile_number_forgot_password, "field 'mMobileNumberSelectionTextView' and method 'onClickMobileNumberSelector'");
        initiateAccountActivationStep1Fragment.mMobileNumberSelectionTextView = (TextView) C0840Utils.castView(findRequiredView4, R.id.text_view_mobile_number_forgot_password, "field 'mMobileNumberSelectionTextView'", TextView.class);
        this.view7f0a09f5 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep1Fragment.onClickMobileNumberSelector();
            }
        });
        initiateAccountActivationStep1Fragment.mEmailHighLight = C0840Utils.findRequiredView(view, R.id.email_selection_highlight, "field 'mEmailHighLight'");
        initiateAccountActivationStep1Fragment.mMobileNumberHighlight = C0840Utils.findRequiredView(view, R.id.mobile_number_selection_highlight, "field 'mMobileNumberHighlight'");
        initiateAccountActivationStep1Fragment.mMobileNumberBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_mobile_number_bubble_layout, "field 'mMobileNumberBubbleLayout'", ConstraintLayout.class);
        initiateAccountActivationStep1Fragment.mEmailBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_email_bubble_layout, "field 'mEmailBubbleLayout'", ConstraintLayout.class);
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.layout_country_code, "field 'mCountryCodeLayout' and method 'onClickCountryCodeSelection'");
        initiateAccountActivationStep1Fragment.mCountryCodeLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView5, R.id.layout_country_code, "field 'mCountryCodeLayout'", ConstraintLayout.class);
        this.view7f0a055c = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initiateAccountActivationStep1Fragment.onClickCountryCodeSelection();
            }
        });
        initiateAccountActivationStep1Fragment.mCountryCodeTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_number_code_login, "field 'mCountryCodeTextView'", TextView.class);
        initiateAccountActivationStep1Fragment.mTitleText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_forgot_password_title, "field 'mTitleText'", TextView.class);
        initiateAccountActivationStep1Fragment.mSubTitleText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_enter_emaul_or_mobile_number_sub_title, "field 'mSubTitleText'", TextView.class);
        initiateAccountActivationStep1Fragment.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_flag_login, "field 'mCountryFlag'", ImageView.class);
        initiateAccountActivationStep1Fragment.mStepText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mStepText'", TextView.class);
        initiateAccountActivationStep1Fragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
    }

    public void unbind() {
        InitiateAccountActivationStep1Fragment initiateAccountActivationStep1Fragment = this.target;
        if (initiateAccountActivationStep1Fragment != null) {
            this.target = null;
            initiateAccountActivationStep1Fragment.mContinue = null;
            initiateAccountActivationStep1Fragment.mBack = null;
            initiateAccountActivationStep1Fragment.mEmailEditText = null;
            initiateAccountActivationStep1Fragment.mMobileNumberEditText = null;
            initiateAccountActivationStep1Fragment.mEmailSelectionTextView = null;
            initiateAccountActivationStep1Fragment.mMobileNumberSelectionTextView = null;
            initiateAccountActivationStep1Fragment.mEmailHighLight = null;
            initiateAccountActivationStep1Fragment.mMobileNumberHighlight = null;
            initiateAccountActivationStep1Fragment.mMobileNumberBubbleLayout = null;
            initiateAccountActivationStep1Fragment.mEmailBubbleLayout = null;
            initiateAccountActivationStep1Fragment.mCountryCodeLayout = null;
            initiateAccountActivationStep1Fragment.mCountryCodeTextView = null;
            initiateAccountActivationStep1Fragment.mTitleText = null;
            initiateAccountActivationStep1Fragment.mSubTitleText = null;
            initiateAccountActivationStep1Fragment.mCountryFlag = null;
            initiateAccountActivationStep1Fragment.mStepText = null;
            initiateAccountActivationStep1Fragment.mParent = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0931.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0931 = null;
            this.view7f0a09f5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a09f5 = null;
            this.view7f0a055c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a055c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
