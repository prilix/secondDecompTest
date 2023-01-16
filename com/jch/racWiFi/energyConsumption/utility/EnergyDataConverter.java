package com.jch.racWiFi.energyConsumption.utility;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;

public class EnergyDataConverter {
    private Context mContext;

    public EnergyDataConverter(Context context) {
        this.mContext = context;
    }

    public BarData getEnergyUsageBarDat(Double[] dArr, Double[] dArr2, boolean z, QueryFilterType queryFilterType) {
        BarData barData;
        BarEntry barEntry;
        BarEntry barEntry2;
        BarEntry barEntry3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (dArr != null) {
            for (int i = 0; i < dArr.length; i++) {
                if (z && dArr2 != null) {
                    Double d = dArr2[i];
                    if (d != null) {
                        float f = (float) i;
                        float floatValue = d.floatValue();
                    } else {
                        barEntry3 = new BarEntry((float) i, 0.0f);
                    }
                    arrayList2.add(barEntry3);
                }
                Double d2 = dArr[i];
                if (d2 != null) {
                    float f2 = (float) i;
                    float floatValue2 = d2.floatValue();
                } else {
                    barEntry2 = new BarEntry((float) i, 0.0f);
                }
                arrayList.add(barEntry2);
            }
        } else if (z && dArr2 != null) {
            for (int i2 = 0; i2 < dArr2.length; i2++) {
                Double d3 = dArr2[i2];
                if (d3 != null) {
                    float f3 = (float) i2;
                    float floatValue3 = d3.floatValue();
                } else {
                    barEntry = new BarEntry((float) i2, 0.0f);
                }
                arrayList2.add(barEntry);
            }
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, "Current Data");
        BarDataSet barDataSet2 = new BarDataSet(arrayList2, "Prev Data");
        barDataSet.setDrawValues(false);
        barDataSet2.setDrawValues(false);
        if (z) {
            IBarDataSet[] iBarDataSetArr = {barDataSet2, barDataSet};
        } else {
            barData = new BarData(barDataSet);
        }
        barDataSet.setColor(ContextCompat.getColor(this.mContext, QueryFilterType.ENERGY == queryFilterType ? R.color.energy_bar_color : R.color.cost_bar_color));
        barDataSet.setHighLightColor(ContextCompat.getColor(this.mContext, QueryFilterType.ENERGY == queryFilterType ? R.color.energy_bar_selected_color : R.color.cost_bar_selected_color));
        Context context = this.mContext;
        QueryFilterType queryFilterType2 = QueryFilterType.ENERGY;
        barDataSet2.setColor(ContextCompat.getColor(context, R.color.compare_last_bar_color));
        float f4 = 0.36f;
        if (!z) {
            f4 = 0.72f;
        }
        barData.setBarWidth(f4);
        return barData;
    }
}
