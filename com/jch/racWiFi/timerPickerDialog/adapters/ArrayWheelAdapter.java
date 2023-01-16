package com.jch.racWiFi.timerPickerDialog.adapters;

import android.content.Context;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;

public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    private T[] items;

    public void setConfig(PickerConfig pickerConfig) {
    }

    public ArrayWheelAdapter(Context context, T[] tArr) {
        super(context);
        this.items = tArr;
    }

    public CharSequence getItemText(int i) {
        if (i < 0) {
            return null;
        }
        T[] tArr = this.items;
        if (i >= tArr.length) {
            return null;
        }
        T t = tArr[i];
        if (t instanceof CharSequence) {
            return (CharSequence) t;
        }
        Logger.m49i("", "Test " + t.toString());
        return t.toString();
    }

    public int getItemsCount() {
        return this.items.length;
    }
}
