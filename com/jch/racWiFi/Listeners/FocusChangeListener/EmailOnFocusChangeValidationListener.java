package com.jch.racWiFi.Listeners.FocusChangeListener;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;

public class EmailOnFocusChangeValidationListener implements View.OnFocusChangeListener {
    private String errorMessage;
    private EditText mEmailEditText;
    private ConstraintLayout mLayoutToolTip;
    private TextView mToolTipTextView;

    public ConstraintLayout getLayoutToolTip() {
        return this.mLayoutToolTip;
    }

    public void setLayoutToolTip(ConstraintLayout constraintLayout) {
        this.mLayoutToolTip = constraintLayout;
        this.mToolTipTextView = (TextView) constraintLayout.getChildAt(0);
    }

    public EmailOnFocusChangeValidationListener(EditText editText) {
        this.mEmailEditText = editText;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            return;
        }
        if (!ValidationUtils.isEmailAddressValid(this.mEmailEditText.getText().toString().trim())) {
            this.mEmailEditText.setErrorBackgroundDrawable();
            ConstraintLayout constraintLayout = this.mLayoutToolTip;
            if (constraintLayout != null && this.mToolTipTextView != null) {
                constraintLayout.setVisibility(0);
                this.mToolTipTextView.setText(this.errorMessage);
                return;
            }
            return;
        }
        this.mEmailEditText.setNormalBackgroundDrawable();
        ConstraintLayout constraintLayout2 = this.mLayoutToolTip;
        if (constraintLayout2 != null) {
            constraintLayout2.setVisibility(4);
        }
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
}
