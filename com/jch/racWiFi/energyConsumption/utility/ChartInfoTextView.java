package com.jch.racWiFi.energyConsumption.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ChartInfoTextView extends TextView {
    public ChartInfoTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public ChartInfoTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ChartInfoTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setCompoundDrawablesWithIntrinsicBounds((int) R.drawable.ic_back_arrow_grey_long_svg, 0, (int) R.drawable.ic_forward_arrow_grey_svg, 0);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText("  " + charSequence + "  ", bufferType);
    }
}
