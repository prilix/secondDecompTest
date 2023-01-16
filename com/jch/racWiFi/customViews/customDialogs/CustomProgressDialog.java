package com.jch.racWiFi.customViews.customDialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.ProgressBar;

public class CustomProgressDialog extends ProgressDialog {
    private OnBackPressedProgressDialog onBackPressedProgressDialog;

    public interface OnBackPressedProgressDialog {
        void onBackPressed();
    }

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public void importFromAttributeSet(AttributeSet attributeSet) {
        setTitle(attributeSet.getTitle());
        setMessage(attributeSet.getMessage());
        setCancelable(attributeSet.isCancelable());
    }

    public void setOnBackPressedListener(OnBackPressedProgressDialog onBackPressedProgressDialog2) {
        this.onBackPressedProgressDialog = onBackPressedProgressDialog2;
    }

    public static class AttributeSet {
        private boolean cancelable;
        private String mMessage;
        private String mTitle;

        public String getTitle() {
            return this.mTitle;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public String getMessage() {
            return this.mMessage;
        }

        public void setMessage(String str) {
            this.mMessage = str;
        }

        public boolean isCancelable() {
            return this.cancelable;
        }

        public void setCancelable(boolean z) {
            this.cancelable = z;
        }
    }

    public void onBackPressed() {
        OnBackPressedProgressDialog onBackPressedProgressDialog2 = this.onBackPressedProgressDialog;
        if (onBackPressedProgressDialog2 != null) {
            onBackPressedProgressDialog2.onBackPressed();
        }
    }

    public void show() {
        super.show();
        ((ProgressBar) findViewById(16908301)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#c3002f"), PorterDuff.Mode.SRC_IN);
    }
}
