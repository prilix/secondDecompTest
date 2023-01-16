package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

public class ImageButton extends AppCompatImageButton {
    private Context mContext;

    public ImageButton(Context context) {
        super(context);
        this.mContext = context;
    }

    public ImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public ImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void setImageDrawable(int i) {
        setImageDrawable(this.mContext.getResources().getDrawable(i, (Resources.Theme) null));
    }

    public void setBackground(int i) {
        setBackgroundDrawable(getResources().getDrawable(i));
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    public boolean isDrawableAlreadySet(int i) {
        Object tag = getTag();
        if (tag != null && i == ((Integer) tag).intValue()) {
            return true;
        }
        return false;
    }
}
