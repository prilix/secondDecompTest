package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerView extends RelativeLayout implements IMarker {
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private WeakReference<Chart> mWeakChart;

    public MarkerView(Context context, int i) {
        super(context);
        setupLayoutResource(i);
    }

    private void setupLayoutResource(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(i, this);
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        inflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
    }

    public void setOffset(MPPointF mPPointF) {
        this.mOffset = mPPointF;
        if (mPPointF == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setOffset(float f, float f2) {
        this.mOffset.f195x = f;
        this.mOffset.f196y = f2;
    }

    public MPPointF getOffset() {
        return this.mOffset;
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public Chart getChartView() {
        WeakReference<Chart> weakReference = this.mWeakChart;
        if (weakReference == null) {
            return null;
        }
        return (Chart) weakReference.get();
    }

    public MPPointF getOffsetForDrawingAtPoint(float f, float f2) {
        MPPointF offset = getOffset();
        this.mOffset2.f195x = offset.f195x;
        this.mOffset2.f196y = offset.f196y;
        Chart chartView = getChartView();
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (this.mOffset2.f195x + f < 0.0f) {
            this.mOffset2.f195x = -f;
        } else if (chartView != null && f + width + this.mOffset2.f195x > ((float) chartView.getWidth())) {
            this.mOffset2.f195x = (((float) chartView.getWidth()) - f) - width;
        }
        if (this.mOffset2.f196y + f2 < 0.0f) {
            this.mOffset2.f196y = -f2;
        } else if (chartView != null && f2 + height + this.mOffset2.f196y > ((float) chartView.getHeight())) {
            this.mOffset2.f196y = (((float) chartView.getHeight()) - f2) - height;
        }
        return this.mOffset2;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void draw(Canvas canvas, float f, float f2) {
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f, f2);
        int save = canvas.save();
        canvas.translate(f + offsetForDrawingAtPoint.f195x, f2 + offsetForDrawingAtPoint.f196y);
        draw(canvas);
        canvas.restoreToCount(save);
    }
}
