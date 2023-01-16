package com.jch.racWiFi.Listeners.FocusChangeListener;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;

public class MobileNumberOnFocusChangeValidationListener implements View.OnFocusChangeListener {
    private String errorMessage;
    private ConstraintLayout mLayoutToolTip;
    private EditText mMobileNumberEditText;
    private TextView mToolTipTextView;

    public ConstraintLayout getLayoutToolTip() {
        return this.mLayoutToolTip;
    }

    public void setLayoutToolTip(ConstraintLayout constraintLayout) {
        this.mLayoutToolTip = constraintLayout;
        this.mToolTipTextView = (TextView) constraintLayout.getChildAt(0);
    }

    public MobileNumberOnFocusChangeValidationListener(EditText editText) {
        this.mMobileNumberEditText = editText;
    }

    public void onFocusChange(View view, boolean z) {
        if (!z) {
            this.mMobileNumberEditText.getText().toString().trim();
        }
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
}
