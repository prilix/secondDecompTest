package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.highlight.RadarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart;
import com.github.mikephil.charting.renderer.YAxisRendererRadarChart;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.C1662R2;

public class RadarChart extends PieRadarChartBase<RadarData> {
    private boolean mDrawWeb = true;
    private float mInnerWebLineWidth = 1.5f;
    private int mSkipWebLineCount = 0;
    private int mWebAlpha = C1662R2.attr.behavior_autoHide;
    private int mWebColor = Color.rgb(122, 122, 122);
    private int mWebColorInner = Color.rgb(122, 122, 122);
    private float mWebLineWidth = 2.5f;
    protected XAxisRendererRadarChart mXAxisRenderer;
    private YAxis mYAxis;
    protected YAxisRendererRadarChart mYAxisRenderer;

    public RadarChart(Context context) {
        super(context);
    }

    public RadarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.mYAxis = new YAxis(YAxis.AxisDependency.LEFT);
        this.mWebLineWidth = C1030Utils.convertDpToPixel(1.5f);
        this.mInnerWebLineWidth = C1030Utils.convertDpToPixel(0.75f);
        this.mRenderer = new RadarChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mYAxisRenderer = new YAxisRendererRadarChart(this.mViewPortHandler, this.mYAxis, this);
        this.mXAxisRenderer = new XAxisRendererRadarChart(this.mViewPortHandler, this.mXAxis, this);
        this.mHighlighter = new RadarHighlighter(this);
    }

    /* access modifiers changed from: protected */
    public void calcMinMax() {
        super.calcMinMax();
        this.mYAxis.calculate(((RadarData) this.mData).getYMin(YAxis.AxisDependency.LEFT), ((RadarData) this.mData).getYMax(YAxis.AxisDependency.LEFT));
        this.mXAxis.calculate(0.0f, (float) ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount());
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            calcMinMax();
            this.mYAxisRenderer.computeAxis(this.mYAxis.mAxisMinimum, this.mYAxis.mAxisMaximum, this.mYAxis.isInverted());
            this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            if (this.mLegend != null && !this.mLegend.isLegendCustom()) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            if (this.mXAxis.isEnabled()) {
                this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            }
            this.mXAxisRenderer.renderAxisLabels(canvas);
            if (this.mDrawWeb) {
                this.mRenderer.drawExtras(canvas);
            }
            if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mYAxisRenderer.renderLimitLines(canvas);
            }
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            if (this.mYAxis.isEnabled() && !this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mYAxisRenderer.renderLimitLines(canvas);
            }
            this.mYAxisRenderer.renderAxisLabels(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
        }
    }

    public float getFactor() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f) / this.mYAxis.mAxisRange;
    }

    public float getSliceAngle() {
        return 360.0f / ((float) ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount());
    }

    public int getIndexForAngle(float f) {
        float normalizedAngle = C1030Utils.getNormalizedAngle(f - getRotationAngle());
        float sliceAngle = getSliceAngle();
        int entryCount = ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount();
        int i = 0;
        while (i < entryCount) {
            int i2 = i + 1;
            if ((((float) i2) * sliceAngle) - (sliceAngle / 2.0f) > normalizedAngle) {
                return i;
            }
            i = i2;
        }
        return 0;
    }

    public YAxis getYAxis() {
        return this.mYAxis;
    }

    public void setWebLineWidth(float f) {
        this.mWebLineWidth = C1030Utils.convertDpToPixel(f);
    }

    public float getWebLineWidth() {
        return this.mWebLineWidth;
    }

    public void setWebLineWidthInner(float f) {
        this.mInnerWebLineWidth = C1030Utils.convertDpToPixel(f);
    }

    public float getWebLineWidthInner() {
        return this.mInnerWebLineWidth;
    }

    public void setWebAlpha(int i) {
        this.mWebAlpha = i;
    }

    public int getWebAlpha() {
        return this.mWebAlpha;
    }

    public void setWebColor(int i) {
        this.mWebColor = i;
    }

    public int getWebColor() {
        return this.mWebColor;
    }

    public void setWebColorInner(int i) {
        this.mWebColorInner = i;
    }

    public int getWebColorInner() {
        return this.mWebColorInner;
    }

    public void setDrawWeb(boolean z) {
        this.mDrawWeb = z;
    }

    public void setSkipWebLineCount(int i) {
        this.mSkipWebLineCount = Math.max(0, i);
    }

    public int getSkipWebLineCount() {
        return this.mSkipWebLineCount;
    }

    /* access modifiers changed from: protected */
    public float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 4.0f;
    }

    /* access modifiers changed from: protected */
    public float getRequiredBaseOffset() {
        if (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled()) {
            return C1030Utils.convertDpToPixel(10.0f);
        }
        return (float) this.mXAxis.mLabelRotatedWidth;
    }

    public float getRadius() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f);
    }

    public float getYChartMax() {
        return this.mYAxis.mAxisMaximum;
    }

    public float getYChartMin() {
        return this.mYAxis.mAxisMinimum;
    }

    public float getYRange() {
        return this.mYAxis.mAxisRange;
    }
}
