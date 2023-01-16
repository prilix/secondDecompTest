package com.jch.racWiFi.userManagement.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class PasswordStrengthUiLogicHolder_ViewBinding implements Unbinder {
    private PasswordStrengthUiLogicHolder target;

    public PasswordStrengthUiLogicHolder_ViewBinding(PasswordStrengthUiLogicHolder passwordStrengthUiLogicHolder, View view) {
        this.target = passwordStrengthUiLogicHolder;
        passwordStrengthUiLogicHolder.passwordStrengthSuggestionRv = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycler_view_pwdStrength, "field 'passwordStrengthSuggestionRv'", RecyclerView.class);
        passwordStrengthUiLogicHolder.constantPasswordStrengthView = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_constant_pwdStrength, "field 'constantPasswordStrengthView'", ConstraintLayout.class);
        passwordStrengthUiLogicHolder.mViewWeak = C0840Utils.findRequiredView(view, R.id.view_weak, "field 'mViewWeak'");
        passwordStrengthUiLogicHolder.mViewOk = C0840Utils.findRequiredView(view, R.id.view_ok, "field 'mViewOk'");
        passwordStrengthUiLogicHolder.mViewStrong = C0840Utils.findRequiredView(view, R.id.view_strong, "field 'mViewStrong'");
        passwordStrengthUiLogicHolder.mPasswordStrengthState = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_password_strength_state, "field 'mPasswordStrengthState'", TextView.class);
        passwordStrengthUiLogicHolder.mPasswordStrengthTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_password_strength, "field 'mPasswordStrengthTitle'", TextView.class);
        passwordStrengthUiLogicHolder.passwordStrengthUiLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.password_strength_outer_layout, "field 'passwordStrengthUiLayout'", ConstraintLayout.class);
    }

    public void unbind() {
        PasswordStrengthUiLogicHolder passwordStrengthUiLogicHolder = this.target;
        if (passwordStrengthUiLogicHolder != null) {
            this.target = null;
            passwordStrengthUiLogicHolder.passwordStrengthSuggestionRv = null;
            passwordStrengthUiLogicHolder.constantPasswordStrengthView = null;
            passwordStrengthUiLogicHolder.mViewWeak = null;
            passwordStrengthUiLogicHolder.mViewOk = null;
            passwordStrengthUiLogicHolder.mViewStrong = null;
            passwordStrengthUiLogicHolder.mPasswordStrengthState = null;
            passwordStrengthUiLogicHolder.mPasswordStrengthTitle = null;
            passwordStrengthUiLogicHolder.passwordStrengthUiLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
