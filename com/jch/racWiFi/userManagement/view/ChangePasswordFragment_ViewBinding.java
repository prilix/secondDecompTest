package com.jch.racWiFi.userManagement.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ChangePasswordFragment_ViewBinding implements Unbinder {
    private ChangePasswordFragment target;
    private View view7f0a011e;
    private View view7f0a0152;
    private View view7f0a0401;
    private View view7f0a0a77;

    public ChangePasswordFragment_ViewBinding(final ChangePasswordFragment changePasswordFragment, View view) {
        this.target = changePasswordFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
        changePasswordFragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordFragment.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
            }
        });
        changePasswordFragment.mCurrentPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_current_password, "field 'mCurrentPassword'", EditText.class);
        changePasswordFragment.mNewPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_new_password, "field 'mNewPassword'", EditText.class);
        changePasswordFragment.mConfirmNewPassword = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_confirm_new_password, "field 'mConfirmNewPassword'", EditText.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_change_password, "field 'mChangePassword' and method 'onClickChangePassword'");
        changePasswordFragment.mChangePassword = (Button) C0840Utils.castView(findRequiredView2, R.id.button_change_password, "field 'mChangePassword'", Button.class);
        this.view7f0a0152 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordFragment.onClickChangePassword((Button) C0840Utils.castParam(view, "doClick", 0, "onClickChangePassword", 0, Button.class));
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.image_button_password_strength_hint, "field 'mPasswordHintButton' and method 'onClickPasswordHintTip'");
        changePasswordFragment.mPasswordHintButton = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.image_button_password_strength_hint, "field 'mPasswordHintButton'", ImageButton.class);
        this.view7f0a0401 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordFragment.onClickPasswordHintTip((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickPasswordHintTip", 0, ImageButton.class));
            }
        });
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'onClickSave'");
        changePasswordFragment.mSave = (TextView) C0840Utils.castView(findRequiredView4, R.id.text_view_save, "field 'mSave'", TextView.class);
        this.view7f0a0a77 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordFragment.onClickSave((TextView) C0840Utils.castParam(view, "doClick", 0, "onClickSave", 0, TextView.class));
            }
        });
        changePasswordFragment.mCurrentPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_current_password_bubble_layout, "field 'mCurrentPasswordBubbleLayout'", ConstraintLayout.class);
        changePasswordFragment.mNewPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_new_password_bubble_layout, "field 'mNewPasswordBubbleLayout'", ConstraintLayout.class);
        changePasswordFragment.mConFirmNewPasswordBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_confirm_new_password_bubble_layout, "field 'mConFirmNewPasswordBubbleLayout'", ConstraintLayout.class);
    }

    public void unbind() {
        ChangePasswordFragment changePasswordFragment = this.target;
        if (changePasswordFragment != null) {
            this.target = null;
            changePasswordFragment.mBack = null;
            changePasswordFragment.mCurrentPassword = null;
            changePasswordFragment.mNewPassword = null;
            changePasswordFragment.mConfirmNewPassword = null;
            changePasswordFragment.mChangePassword = null;
            changePasswordFragment.mPasswordHintButton = null;
            changePasswordFragment.mSave = null;
            changePasswordFragment.mCurrentPasswordBubbleLayout = null;
            changePasswordFragment.mNewPasswordBubbleLayout = null;
            changePasswordFragment.mConFirmNewPasswordBubbleLayout = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0152.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0152 = null;
            this.view7f0a0401.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0401 = null;
            this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a77 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
