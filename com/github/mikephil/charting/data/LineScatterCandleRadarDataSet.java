package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.C1030Utils;
import java.util.List;

public abstract class LineScatterCandleRadarDataSet<T extends Entry> extends BarLineScatterCandleBubbleDataSet<T> implements ILineScatterCandleRadarDataSet<T> {
    protected boolean mDrawHorizontalHighlightIndicator;
    protected boolean mDrawVerticalHighlightIndicator;
    protected DashPathEffect mHighlightDashPathEffect;
    protected float mHighlightLineWidth;

    public LineScatterCandleRadarDataSet(List<T> list, String str) {
        super(list, str);
        this.mDrawVerticalHighlightIndicator = true;
        this.mDrawHorizontalHighlightIndicator = true;
        this.mHighlightLineWidth = 0.5f;
        this.mHighlightDashPathEffect = null;
        this.mHighlightLineWidth = C1030Utils.convertDpToPixel(0.5f);
    }

    public void setDrawHorizontalHighlightIndicator(boolean z) {
        this.mDrawHorizontalHighlightIndicator = z;
    }

    public void setDrawVerticalHighlightIndicator(boolean z) {
        this.mDrawVerticalHighlightIndicator = z;
    }

    public void setDrawHighlightIndicators(boolean z) {
        setDrawVerticalHighlightIndicator(z);
        setDrawHorizontalHighlightIndicator(z);
    }

    public boolean isVerticalHighlightIndicatorEnabled() {
        return this.mDrawVerticalHighlightIndicator;
    }

    public boolean isHorizontalHighlightIndicatorEnabled() {
        return this.mDrawHorizontalHighlightIndicator;
    }

    public void setHighlightLineWidth(float f) {
        this.mHighlightLineWidth = C1030Utils.convertDpToPixel(f);
    }

    public float getHighlightLineWidth() {
        return this.mHighlightLineWidth;
    }

    public void enableDashedHighlightLine(float f, float f2, float f3) {
        this.mHighlightDashPathEffect = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void disableDashedHighlightLine() {
        this.mHighlightDashPathEffect = null;
    }

    public boolean isDashedHighlightLineEnabled() {
        return this.mHighlightDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffectHighlight() {
        return this.mHighlightDashPathEffect;
    }

    /* access modifiers changed from: protected */
    public void copy(LineScatterCandleRadarDataSet lineScatterCandleRadarDataSet) {
        super.copy(lineScatterCandleRadarDataSet);
        lineScatterCandleRadarDataSet.mDrawHorizontalHighlightIndicator = this.mDrawHorizontalHighlightIndicator;
        lineScatterCandleRadarDataSet.mDrawVerticalHighlightIndicator = this.mDrawVerticalHighlightIndicator;
        lineScatterCandleRadarDataSet.mHighlightLineWidth = this.mHighlightLineWidth;
        lineScatterCandleRadarDataSet.mHighlightDashPathEffect = this.mHighlightDashPathEffect;
    }
}
