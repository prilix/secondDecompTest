package com.jch.racWiFi.selectMultipleContacts.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.jch.racWiFi.C1655R;

public class RoundLetterView extends View {
    private static int DEFAULT_BACKGROUND_COLOR = -16711681;
    private static String DEFAULT_TITLE = "A";
    private static int DEFAULT_TITLE_COLOR = -1;
    private static float DEFAULT_TITLE_SIZE = 25.0f;
    private static final int DEFAULT_VIEW_SIZE = 96;
    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private Paint mBackgroundPaint;
    private Typeface mFont = Typeface.defaultFromStyle(1);
    private RectF mInnerRectF;
    private int mTitleColor = DEFAULT_TITLE_COLOR;
    private float mTitleSize = DEFAULT_TITLE_SIZE;
    private String mTitleText = DEFAULT_TITLE;
    private TextPaint mTitleTextPaint;
    private int mViewSize;

    public RoundLetterView(Context context) {
        super(context);
        init((AttributeSet) null, 0);
    }

    public RoundLetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public RoundLetterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C1655R.styleable.RoundedLetterView, i, 0);
        if (obtainStyledAttributes.hasValue(3)) {
            this.mTitleText = obtainStyledAttributes.getString(3);
        }
        this.mTitleColor = obtainStyledAttributes.getColor(1, DEFAULT_TITLE_COLOR);
        this.mBackgroundColor = obtainStyledAttributes.getColor(0, DEFAULT_BACKGROUND_COLOR);
        this.mTitleSize = obtainStyledAttributes.getDimension(2, DEFAULT_TITLE_SIZE);
        obtainStyledAttributes.recycle();
        TextPaint textPaint = new TextPaint();
        this.mTitleTextPaint = textPaint;
        textPaint.setFlags(1);
        this.mTitleTextPaint.setTypeface(this.mFont);
        this.mTitleTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTitleTextPaint.setLinearText(true);
        this.mTitleTextPaint.setColor(this.mTitleColor);
        this.mTitleTextPaint.setTextSize(this.mTitleSize);
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        paint.setFlags(1);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mBackgroundPaint.setColor(this.mBackgroundColor);
        this.mInnerRectF = new RectF();
    }

    private void invalidateTextPaints() {
        this.mTitleTextPaint.setTypeface(this.mFont);
        this.mTitleTextPaint.setTextSize(this.mTitleSize);
        this.mTitleTextPaint.setColor(this.mTitleColor);
    }

    private void invalidatePaints() {
        this.mBackgroundPaint.setColor(this.mBackgroundColor);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int resolveSize = resolveSize(96, i);
        int resolveSize2 = resolveSize(96, i2);
        this.mViewSize = Math.min(resolveSize, resolveSize2);
        setMeasuredDimension(resolveSize, resolveSize2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        RectF rectF = this.mInnerRectF;
        int i = this.mViewSize;
        rectF.set(0.0f, 0.0f, (float) i, (float) i);
        this.mInnerRectF.offset((float) ((getWidth() - this.mViewSize) / 2), (float) ((getHeight() - this.mViewSize) / 2));
        float centerX = this.mInnerRectF.centerX();
        canvas.drawOval(this.mInnerRectF, this.mBackgroundPaint);
        canvas.drawText(this.mTitleText, (float) ((int) centerX), (float) ((int) (this.mInnerRectF.centerY() - ((this.mTitleTextPaint.descent() + this.mTitleTextPaint.ascent()) / 2.0f))), this.mTitleTextPaint);
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public void setTitleText(String str) {
        this.mTitleText = str;
        invalidate();
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        invalidatePaints();
    }

    public float getTitleSize() {
        return this.mTitleSize;
    }

    public void setTitleSize(float f) {
        this.mTitleSize = f;
        invalidateTextPaints();
    }

    public void setTextTypeface(Typeface typeface) {
        this.mFont = typeface;
        invalidateTextPaints();
    }

    public void setTitleColor(int i) {
        this.mTitleColor = i;
        invalidateTextPaints();
    }
}
