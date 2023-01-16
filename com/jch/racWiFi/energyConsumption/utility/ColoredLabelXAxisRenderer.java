package com.jch.racWiFi.energyConsumption.utility;

import android.graphics.Canvas;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.C1030Utils;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.Collections;
import java.util.List;

public class ColoredLabelXAxisRenderer extends XAxisRenderer {
    List<Integer> labelColors;

    public ColoredLabelXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, xAxis, transformer);
        this.labelColors = Collections.EMPTY_LIST;
    }

    public ColoredLabelXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer, List<Integer> list) {
        super(viewPortHandler, xAxis, transformer);
        this.labelColors = list;
    }

    /* access modifiers changed from: protected */
    public void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i = this.mXAxis.mEntryCount * 2;
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2 += 2) {
            if (isCenterAxisLabelsEnabled) {
                fArr[i2] = this.mXAxis.mCenteredEntries[i2 / 2];
            } else {
                fArr[i2] = this.mXAxis.mEntries[i2 / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i3 = 0; i3 < i; i3 += 2) {
            float f2 = fArr[i3];
            if (this.mViewPortHandler.isInBoundsX(f2)) {
                int i4 = i3 / 2;
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i4], this.mXAxis);
                this.mAxisLabelPaint.setColor(getColorForXValue((int) this.mXAxis.mEntries[i4]));
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    if (i3 == this.mXAxis.mEntryCount - 1 && this.mXAxis.mEntryCount > 1) {
                        float calcTextWidth = (float) C1030Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue);
                        if (calcTextWidth > this.mViewPortHandler.offsetRight() * 2.0f && f2 + calcTextWidth > this.mViewPortHandler.getChartWidth()) {
                            f2 -= calcTextWidth / 2.0f;
                        }
                    } else if (i3 == 0) {
                        f2 += ((float) C1030Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue)) / 2.0f;
                    }
                }
                drawLabel(canvas, formattedValue, f2, f, mPPointF, labelRotationAngle);
            }
        }
    }

    private int getColorForXValue(int i) {
        if (i >= this.labelColors.size()) {
            return this.mXAxis.getTextColor();
        }
        if (i < 0) {
            return this.mXAxis.getTextColor();
        }
        return this.labelColors.get(i).intValue();
    }
}
