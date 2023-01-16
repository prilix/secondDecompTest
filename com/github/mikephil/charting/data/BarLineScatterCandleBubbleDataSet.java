package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.jch.racWiFi.C1662R2;
import java.util.List;

public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> implements IBarLineScatterCandleBubbleDataSet<T> {
    protected int mHighLightColor = Color.rgb(255, C1662R2.attr.bubbleTextColor, 115);

    public BarLineScatterCandleBubbleDataSet(List<T> list, String str) {
        super(list, str);
    }

    public void setHighLightColor(int i) {
        this.mHighLightColor = i;
    }

    public int getHighLightColor() {
        return this.mHighLightColor;
    }

    /* access modifiers changed from: protected */
    public void copy(BarLineScatterCandleBubbleDataSet barLineScatterCandleBubbleDataSet) {
        super.copy(barLineScatterCandleBubbleDataSet);
        barLineScatterCandleBubbleDataSet.mHighLightColor = this.mHighLightColor;
    }
}
