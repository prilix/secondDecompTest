package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.jch_hitachi.aircloudglobal.R;

public class ImageView extends AppCompatImageView {
    private boolean isEnabled;

    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDeleteButtonEnbled(boolean z) {
        this.isEnabled = z;
        if (z) {
            setBackgroundColor(getContext().getResources().getColor(R.color.colorRed));
        } else {
            setBackgroundColor(getContext().getResources().getColor(R.color.button_disabled));
        }
        super.setEnabled(z);
    }
}
