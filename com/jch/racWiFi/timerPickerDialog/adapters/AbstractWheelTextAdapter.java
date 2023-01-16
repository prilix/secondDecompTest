package com.jch.racWiFi.timerPickerDialog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;
import com.jch_hitachi.aircloudglobal.R;

public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter {
    public static final int DEFAULT_TEXT_SIZE = 24;
    public static final int LABEL_COLOR = -9437072;
    protected static final int NO_RESOURCE = 0;
    public static final int TEXT_VIEW_ITEM_RESOURCE = -1;
    protected Context context;
    protected int emptyItemResourceId;
    protected LayoutInflater inflater;
    protected int itemResourceId;
    protected int itemTextResourceId;
    private PickerConfig mPickerConfig;
    private int padding;

    /* access modifiers changed from: protected */
    public abstract CharSequence getItemText(int i);

    protected AbstractWheelTextAdapter(Context context2) {
        this(context2, -1);
    }

    protected AbstractWheelTextAdapter(Context context2, int i) {
        this(context2, i, 0);
    }

    protected AbstractWheelTextAdapter(Context context2, int i, int i2) {
        this.padding = 0;
        this.context = context2;
        this.itemResourceId = i;
        this.itemTextResourceId = i2;
        this.padding = context2.getResources().getDimensionPixelSize(R.dimen.textview_default_padding);
        this.inflater = (LayoutInflater) context2.getSystemService("layout_inflater");
    }

    public int getItemResource() {
        return this.itemResourceId;
    }

    public void setItemResource(int i) {
        this.itemResourceId = i;
    }

    public int getItemTextResource() {
        return this.itemTextResourceId;
    }

    public void setItemTextResource(int i) {
        this.itemTextResourceId = i;
    }

    public int getEmptyItemResource() {
        return this.emptyItemResourceId;
    }

    public void setEmptyItemResource(int i) {
        this.emptyItemResourceId = i;
    }

    public View getItem(int i, View view, ViewGroup viewGroup) {
        String str;
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        if (view == null) {
            view = getView(this.itemResourceId, viewGroup);
        }
        TextView textView = getTextView(view, this.itemTextResourceId);
        if (textView != null) {
            CharSequence itemText = getItemText(i);
            if (itemText == null) {
                itemText = "";
            }
            if (itemText.toString().contains(this.mPickerConfig.mMinute)) {
                if (i == 0) {
                    str = "00 " + this.mPickerConfig.mMinute;
                } else {
                    str = (i * this.mPickerConfig.stepsForMinute) + " " + this.mPickerConfig.mMinute;
                }
                textView.setText(str);
            } else {
                textView.setText(itemText);
            }
            if (this.itemResourceId == -1) {
                configureTextView(textView);
            }
        }
        return view;
    }

    public View getEmptyItem(View view, ViewGroup viewGroup) {
        if (view == null) {
            view = getView(this.emptyItemResourceId, viewGroup);
        }
        if (this.emptyItemResourceId == -1 && (view instanceof TextView)) {
            configureTextView((TextView) view);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void configureTextView(TextView textView) {
        if (this.mPickerConfig == null) {
            this.mPickerConfig = new PickerConfig();
        }
        textView.setTextColor(this.mPickerConfig.mWheelTVNormalColor);
        textView.setGravity(17);
        int i = this.padding;
        textView.setPadding(0, i, 0, i);
        textView.setTextSize((float) this.mPickerConfig.mWheelTVSize);
        textView.setLines(1);
    }

    private TextView getTextView(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (ClassCastException e) {
                Logger.m47e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
            }
        }
        if (i != 0) {
            return (TextView) view.findViewById(i);
        }
        return null;
    }

    private View getView(int i, ViewGroup viewGroup) {
        if (i == -1) {
            return new TextView(this.context);
        }
        if (i != 0) {
            return this.inflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    public PickerConfig getConfig() {
        if (this.mPickerConfig == null) {
            this.mPickerConfig = new PickerConfig();
        }
        return this.mPickerConfig;
    }

    public void setConfig(PickerConfig pickerConfig) {
        this.mPickerConfig = pickerConfig;
    }
}
