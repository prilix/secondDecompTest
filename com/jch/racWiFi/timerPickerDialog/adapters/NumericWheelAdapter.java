package com.jch.racWiFi.timerPickerDialog.adapters;

import android.content.Context;
import android.text.TextUtils;

public class NumericWheelAdapter extends AbstractWheelTextAdapter {
    public static final int DEFAULT_MAX_VALUE = 9;
    private static final int DEFAULT_MIN_VALUE = 0;
    private String format;
    private int maxValue;
    private int minValue;
    private String unit;

    public NumericWheelAdapter(Context context) {
        this(context, 0, 9);
    }

    public NumericWheelAdapter(Context context, int i, int i2) {
        this(context, i, i2, (String) null);
    }

    public NumericWheelAdapter(Context context, int i, int i2, String str) {
        this(context, i, i2, str, (String) null);
    }

    public NumericWheelAdapter(Context context, int i, int i2, String str, String str2) {
        super(context);
        this.minValue = i;
        this.maxValue = i2;
        this.format = str;
        this.unit = str2;
    }

    public CharSequence getItemText(int i) {
        String str;
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        int i2 = this.minValue + i;
        if (!TextUtils.isEmpty(this.format)) {
            str = String.format(this.format, new Object[]{Integer.valueOf(i2)});
        } else {
            str = Integer.toString(i2);
        }
        if (TextUtils.isEmpty(this.unit)) {
            return str;
        }
        return str + this.unit;
    }

    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }
}
