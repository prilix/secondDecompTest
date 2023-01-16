package com.jch.racWiFi.timerPickerDialog.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;

public interface WheelViewAdapter {
    PickerConfig getConfig();

    View getEmptyItem(View view, ViewGroup viewGroup);

    View getItem(int i, View view, ViewGroup viewGroup);

    int getItemsCount();

    void registerDataSetObserver(DataSetObserver dataSetObserver);

    void setConfig(PickerConfig pickerConfig);

    void unregisterDataSetObserver(DataSetObserver dataSetObserver);
}
