package com.jch.racWiFi.customViews.HintCase.extracontentholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatButton;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;

public class SimpleButtonContentHolder extends ExtraContentHolder {
    /* access modifiers changed from: private */
    public int buttonStyleId;
    /* access modifiers changed from: private */
    public boolean closeHintOnClick = false;
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    public OnClickButtonListener onClickButtonListener;
    /* access modifiers changed from: private */
    public int[] rules;
    /* access modifiers changed from: private */
    public CharSequence text;
    /* access modifiers changed from: private */
    public int width;

    public interface OnClickButtonListener {
        void onClick();
    }

    SimpleButtonContentHolder() {
    }

    public View getView(Context context, final C1687HintCase hintCase, ViewGroup viewGroup) {
        AppCompatButton appCompatButton;
        if (this.buttonStyleId != 0) {
            appCompatButton = new AppCompatButton(new ContextThemeWrapper(context, this.buttonStyleId));
        } else {
            appCompatButton = new AppCompatButton(context);
        }
        appCompatButton.setText(this.text);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SimpleButtonContentHolder.this.onClickButtonListener != null) {
                    SimpleButtonContentHolder.this.onClickButtonListener.onClick();
                }
                if (SimpleButtonContentHolder.this.closeHintOnClick) {
                    hintCase.hide();
                }
            }
        });
        appCompatButton.setLayoutParams(getParentLayoutParams(this.width, this.height, this.rules));
        return appCompatButton;
    }

    public static class Builder {
        private SimpleButtonContentHolder buttonBlock;
        private Context context;

        public Builder(Context context2) {
            this.context = context2;
            SimpleButtonContentHolder simpleButtonContentHolder = new SimpleButtonContentHolder();
            this.buttonBlock = simpleButtonContentHolder;
            simpleButtonContentHolder.width = -2;
            this.buttonBlock.height = -2;
            this.buttonBlock.rules = new int[0];
        }

        public Builder setWidth(int i) {
            this.buttonBlock.width = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.buttonBlock.height = i;
            return this;
        }

        public Builder setRules(int... iArr) {
            this.buttonBlock.rules = iArr;
            return this;
        }

        public Builder setButtonText(CharSequence charSequence) {
            this.buttonBlock.text = charSequence;
            return this;
        }

        public Builder setButtonText(int i) {
            this.buttonBlock.text = this.context.getString(i);
            return this;
        }

        public Builder setButtonStyle(int i) {
            this.buttonBlock.buttonStyleId = i;
            return this;
        }

        public Builder setOnClick(OnClickButtonListener onClickButtonListener) {
            this.buttonBlock.onClickButtonListener = onClickButtonListener;
            return this;
        }

        public Builder setCloseHintCaseOnClick(boolean z) {
            this.buttonBlock.closeHintOnClick = z;
            return this;
        }

        public SimpleButtonContentHolder build() {
            return this.buttonBlock;
        }
    }
}
