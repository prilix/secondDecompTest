package com.jch.racWiFi.Listeners.TextWatchers;

import android.text.Editable;
import android.text.TextWatcher;
import com.jch.racWiFi.customViews.customWidgets.EditText;

public class EmailEditTextEmptyTextWatcher implements TextWatcher {
    private EditText mEmailEditText;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public EmailEditTextEmptyTextWatcher(EditText editText) {
        this.mEmailEditText = editText;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mEmailEditText.getText().toString().trim().isEmpty()) {
            this.mEmailEditText.setErrorBackgroundDrawable();
        } else {
            this.mEmailEditText.setNormalBackgroundDrawable();
        }
    }
}
