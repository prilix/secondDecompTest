package com.jch.racWiFi.customViews.checkableButton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import androidx.appcompat.widget.AppCompatButton;
import com.accord.common.utils.Logger;

public class CheckableButton extends AppCompatButton implements Checkable {
    private static final int[] CHECKED_STATE_LIST = {16842912};
    private boolean mBroadcasting;
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckableButton checkableButton, boolean z);
    }

    public CheckableButton(Context context) {
        super(context);
    }

    public CheckableButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CheckableButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mChecked) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_LIST);
        }
        return onCreateDrawableState;
    }

    public void setChecked(boolean z) {
        OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeWidgetListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, this.mChecked);
        }
        OnCheckedChangeListener onCheckedChangeListener2 = this.mOnCheckedChangeListener;
        if (onCheckedChangeListener2 != null) {
            onCheckedChangeListener2.onCheckedChanged(this, this.mChecked);
        }
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshDrawableState();
            if (this.mBroadcasting) {
                this.mBroadcasting = false;
            } else {
                this.mBroadcasting = true;
            }
        }
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        Logger.m49i("", "CheckableButton.toggle");
        setChecked(!this.mChecked);
    }

    public boolean performClick() {
        toggle();
        Logger.m49i("", "CheckableButton.performClick");
        return super.performClick();
    }

    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeWidgetListener = onCheckedChangeListener;
    }
}
