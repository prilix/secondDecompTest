package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class AddRac_ViewBinding implements Unbinder {
    private AddRac target;
    private View view7f0a011e;
    private View view7f0a0153;
    private View view7f0a02a5;

    public AddRac_ViewBinding(final AddRac addRac, View view) {
        this.target = addRac;
        addRac.passwordLayout = (TextInputLayout) C0840Utils.findRequiredViewAsType(view, R.id.passwordTextInputLayout, "field 'passwordLayout'", TextInputLayout.class);
        addRac.racPasswordEditText = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.et_password_field, "field 'racPasswordEditText'", EditText.class);
        addRac.connectToAcTextview = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.connect_to_ac_textview, "field 'connectToAcTextview'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_change_router, "field 'changeRouterButton' and method 'changeRouter'");
        addRac.changeRouterButton = (Button) C0840Utils.castView(findRequiredView, R.id.button_change_router, "field 'changeRouterButton'", Button.class);
        this.view7f0a0153 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addRac.changeRouter();
            }
        });
        addRac.swipableLayout = C0840Utils.findRequiredView(view, R.id.scroll_view, "field 'swipableLayout'");
        addRac.racSsidText = (TextView) C0840Utils.findOptionalViewAsType(view, R.id.tv_ssid, "field 'racSsidText'", TextView.class);
        addRac.mTextViewStep = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mTextViewStep'", TextView.class);
        addRac.mLinearProgressIndicator = (LinearProgressIndicator) C0840Utils.findRequiredViewAsType(view, R.id.linearProgressIndicator, "field 'mLinearProgressIndicator'", LinearProgressIndicator.class);
        addRac.mTextViewBannerError = (AppCompatTextView) C0840Utils.findRequiredViewAsType(view, R.id.descriptionText, "field 'mTextViewBannerError'", AppCompatTextView.class);
        addRac.mConstraintLayoutError = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.constraintLayoutError, "field 'mConstraintLayoutError'", ConstraintLayout.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'goBack'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addRac.goBack();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.forward_button, "method 'goNext'");
        this.view7f0a02a5 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addRac.goNext();
            }
        });
    }

    public void unbind() {
        AddRac addRac = this.target;
        if (addRac != null) {
            this.target = null;
            addRac.passwordLayout = null;
            addRac.racPasswordEditText = null;
            addRac.connectToAcTextview = null;
            addRac.changeRouterButton = null;
            addRac.swipableLayout = null;
            addRac.racSsidText = null;
            addRac.mTextViewStep = null;
            addRac.mLinearProgressIndicator = null;
            addRac.mTextViewBannerError = null;
            addRac.mConstraintLayoutError = null;
            this.view7f0a0153.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0153 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a02a5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a02a5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
