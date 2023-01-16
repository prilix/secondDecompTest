package com.jch.racWiFi.Listeners.TextWatchers;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;

public class GenericEmptyEditTextWatcher implements TextWatcher {
    private String errorMessage;
    private EditText mEditText;
    private ConstraintLayout mLayoutToolTip;
    private TextView mToolTipTextView;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ConstraintLayout getLayoutToolTip() {
        return this.mLayoutToolTip;
    }

    public void setLayoutToolTip(ConstraintLayout constraintLayout) {
        this.mLayoutToolTip = constraintLayout;
        this.mToolTipTextView = (TextView) constraintLayout.getChildAt(0);
    }

    public GenericEmptyEditTextWatcher(EditText editText) {
        this.mEditText = editText;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mEditText.getText().toString().trim().isEmpty()) {
            this.mEditText.setErrorBackgroundDrawable();
            ConstraintLayout constraintLayout = this.mLayoutToolTip;
            if (constraintLayout != null && this.mToolTipTextView != null) {
                constraintLayout.setVisibility(0);
                this.mToolTipTextView.setText(this.errorMessage);
                return;
            }
            return;
        }
        this.mEditText.setNormalBackgroundDrawable();
        ConstraintLayout constraintLayout2 = this.mLayoutToolTip;
        if (constraintLayout2 != null) {
            constraintLayout2.setVisibility(4);
        }
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
}
