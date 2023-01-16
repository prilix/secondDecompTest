package com.warkiz.tickseekbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class TickSeekBar extends View {
    private static final int THUMB_MAX_WIDTH = 30;
    /* access modifiers changed from: private */
    public float lastProgress;
    private boolean mAdjustAuto;
    private RectF mBackgroundTrack;
    private int mBackgroundTrackColor;
    private int mBackgroundTrackSize;
    private boolean mClearPadding;
    private Context mContext;
    private float mCustomDrawableMaxHeight;
    private boolean mCustomTrackSectionColorResult;
    private int mDefaultTickTextsHeight;
    private float mFaultTolerance;
    private int mHoveredTextColor;
    private boolean mIsFloatProgress;
    private boolean mIsTouching;
    private float mMax;
    private int mMeasuredWidth;
    private float mMin;
    private boolean mOnlyThumbDraggable;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Bitmap mPressedThumbBitmap;
    private int mPressedThumbColor;
    /* access modifiers changed from: private */
    public float mProgress;
    /* access modifiers changed from: private */
    public float[] mProgressArr;
    private RectF mProgressTrack;
    private int mProgressTrackColor;
    private int mProgressTrackSize;
    private boolean mR2L;
    private Rect mRect;
    private int mScale;
    private int[] mSectionTrackColorArray;
    private float mSeekBlockLength;
    private OnSeekChangeListener mSeekChangeListener;
    private float mSeekLength;
    private SeekParams mSeekParams;
    private boolean mSeekSmoothly;
    private Bitmap mSelectTickMarksBitmap;
    private int mSelectedTextsColor;
    private int mSelectedTickMarksColor;
    private int mShowTickMarksType;
    private Paint mStockPaint;
    private float[] mTextCenterX;
    private TextPaint mTextPaint;
    private Typeface mTextsTypeface;
    private Bitmap mThumbBitmap;
    private int mThumbColor;
    private Drawable mThumbDrawable;
    private float mThumbRadius;
    private int mThumbSize;
    private int mThumbTextColor;
    private int mThumbTextShowPos;
    private float mThumbTextY;
    private float mThumbTouchRadius;
    private Drawable mTickMarksDrawable;
    private boolean mTickMarksEndsHide;
    private int mTickMarksSize;
    private boolean mTickMarksSweptHide;
    private float[] mTickMarksX;
    private float mTickRadius;
    private float mTickTextY;
    private String[] mTickTextsArr;
    private CharSequence[] mTickTextsCustomArray;
    private int mTickTextsHeight;
    private int mTickTextsPosition;
    private int mTickTextsSize;
    private float[] mTickTextsWidth;
    private int mTicksCount;
    private boolean mTrackRoundedCorners;
    private int mUnSelectedTickMarksColor;
    private int mUnselectedTextsColor;
    private Bitmap mUnselectedTickMarksBitmap;
    private boolean mUserSeekable;

    public TickSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public TickSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TickSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFaultTolerance = -1.0f;
        this.mScale = 1;
        this.mContext = context;
        initAttrs(context, attributeSet);
        initParams();
    }

    public TickSeekBar(Builder builder) {
        super(builder.context);
        this.mFaultTolerance = -1.0f;
        this.mScale = 1;
        Context context = builder.context;
        this.mContext = context;
        int dp2px = SizeUtils.dp2px(context, 16.0f);
        setPadding(dp2px, getPaddingTop(), dp2px, getPaddingBottom());
        apply(builder);
        initParams();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        Builder builder = new Builder(context);
        if (attributeSet == null) {
            apply(builder);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2766R.styleable.TickSeekBar);
        this.mMax = obtainStyledAttributes.getFloat(C2766R.styleable.TickSeekBar_tsb_max, builder.max);
        this.mMin = obtainStyledAttributes.getFloat(C2766R.styleable.TickSeekBar_tsb_min, builder.min);
        this.mProgress = obtainStyledAttributes.getFloat(C2766R.styleable.TickSeekBar_tsb_progress, builder.progress);
        this.mIsFloatProgress = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_progress_value_float, builder.progressValueFloat);
        this.mUserSeekable = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_user_seekable, builder.userSeekable);
        this.mClearPadding = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_clear_default_padding, builder.clearPadding);
        this.mOnlyThumbDraggable = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_only_thumb_draggable, builder.onlyThumbDraggable);
        this.mSeekSmoothly = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_seek_smoothly, builder.seekSmoothly);
        this.mR2L = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_r2l, builder.r2l);
        this.mBackgroundTrackSize = obtainStyledAttributes.getDimensionPixelSize(C2766R.styleable.TickSeekBar_tsb_track_background_size, builder.trackBackgroundSize);
        this.mProgressTrackSize = obtainStyledAttributes.getDimensionPixelSize(C2766R.styleable.TickSeekBar_tsb_track_progress_size, builder.trackProgressSize);
        this.mBackgroundTrackColor = obtainStyledAttributes.getColor(C2766R.styleable.TickSeekBar_tsb_track_background_color, builder.trackBackgroundColor);
        this.mProgressTrackColor = obtainStyledAttributes.getColor(C2766R.styleable.TickSeekBar_tsb_track_progress_color, builder.trackProgressColor);
        this.mTrackRoundedCorners = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_track_rounded_corners, builder.trackRoundedCorners);
        this.mThumbSize = obtainStyledAttributes.getDimensionPixelSize(C2766R.styleable.TickSeekBar_tsb_thumb_size, builder.thumbSize);
        this.mThumbDrawable = obtainStyledAttributes.getDrawable(C2766R.styleable.TickSeekBar_tsb_thumb_drawable);
        initThumbColor(obtainStyledAttributes.getColorStateList(C2766R.styleable.TickSeekBar_tsb_thumb_color), builder.thumbColor);
        this.mAdjustAuto = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_thumb_adjust_auto, builder.thumbAutoAdjust);
        this.mThumbTextShowPos = obtainStyledAttributes.getInt(C2766R.styleable.TickSeekBar_tsb_show_thumb_text, builder.thumbTextShow);
        this.mThumbTextColor = obtainStyledAttributes.getColor(C2766R.styleable.TickSeekBar_tsb_thumb_text_color, builder.thumbTextColor);
        this.mTicksCount = obtainStyledAttributes.getInt(C2766R.styleable.TickSeekBar_tsb_ticks_count, builder.tickCount);
        this.mShowTickMarksType = obtainStyledAttributes.getInt(C2766R.styleable.TickSeekBar_tsb_show_tick_marks_type, builder.showTickMarksType);
        this.mTickMarksSize = obtainStyledAttributes.getDimensionPixelSize(C2766R.styleable.TickSeekBar_tsb_tick_marks_size, builder.tickMarksSize);
        initTickMarksColor(obtainStyledAttributes.getColorStateList(C2766R.styleable.TickSeekBar_tsb_tick_marks_color), builder.tickMarksColor);
        this.mTickMarksDrawable = obtainStyledAttributes.getDrawable(C2766R.styleable.TickSeekBar_tsb_tick_marks_drawable);
        this.mTickMarksSweptHide = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_tick_marks_swept_hide, builder.tickMarksSweptHide);
        this.mTickMarksEndsHide = obtainStyledAttributes.getBoolean(C2766R.styleable.TickSeekBar_tsb_tick_marks_ends_hide, builder.tickMarksEndsHide);
        this.mTickTextsPosition = obtainStyledAttributes.getInt(C2766R.styleable.TickSeekBar_tsb_show_tick_texts, 0);
        this.mTickTextsSize = obtainStyledAttributes.getDimensionPixelSize(C2766R.styleable.TickSeekBar_tsb_tick_texts_size, builder.tickTextsSize);
        initTickTextsColor(obtainStyledAttributes.getColorStateList(C2766R.styleable.TickSeekBar_tsb_tick_texts_color), builder.tickTextsColor);
        this.mTickTextsCustomArray = obtainStyledAttributes.getTextArray(C2766R.styleable.TickSeekBar_tsb_tick_texts_array);
        initTextsTypeface(obtainStyledAttributes.getInt(C2766R.styleable.TickSeekBar_tsb_tick_texts_typeface, -1), builder.tickTextsTypeFace);
        obtainStyledAttributes.recycle();
    }

    private void initParams() {
        int i = this.mTicksCount;
        if (i < 0 || i > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between 0-50, Now is " + this.mTicksCount);
        }
        initProgressRangeValue();
        int i2 = this.mBackgroundTrackSize;
        int i3 = this.mProgressTrackSize;
        if (i2 > i3) {
            this.mBackgroundTrackSize = i3;
        }
        if (this.mThumbDrawable == null) {
            float f = ((float) this.mThumbSize) / 2.0f;
            this.mThumbRadius = f;
            this.mThumbTouchRadius = f * 1.2f;
        } else {
            float min = ((float) Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize)) / 2.0f;
            this.mThumbRadius = min;
            this.mThumbTouchRadius = min;
        }
        if (this.mTickMarksDrawable == null) {
            this.mTickRadius = ((float) this.mTickMarksSize) / 2.0f;
        } else {
            this.mTickRadius = ((float) Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize)) / 2.0f;
        }
        this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, this.mTickRadius) * 2.0f;
        initStrokePaint();
        measureTickTextsBonds();
        this.lastProgress = this.mProgress;
        collectTicksInfo();
        this.mProgressTrack = new RectF();
        this.mBackgroundTrack = new RectF();
        initDefaultPadding();
    }

    private void collectTicksInfo() {
        int i = this.mTicksCount;
        if (i < 0 || i > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        } else if (i != 0) {
            this.mTickMarksX = new float[i];
            if (this.mTickTextsPosition != 0) {
                this.mTextCenterX = new float[i];
                this.mTickTextsWidth = new float[i];
            }
            this.mProgressArr = new float[i];
            int i2 = 0;
            while (true) {
                float[] fArr = this.mProgressArr;
                if (i2 < fArr.length) {
                    float f = this.mMin;
                    float f2 = ((float) i2) * (this.mMax - f);
                    int i3 = this.mTicksCount;
                    fArr[i2] = f + (f2 / ((float) (i3 + -1 > 0 ? i3 - 1 : 1)));
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    private void initDefaultPadding() {
        if (!this.mClearPadding) {
            int dp2px = SizeUtils.dp2px(this.mContext, 16.0f);
            if (getPaddingLeft() == 0) {
                setPadding(dp2px, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
            if (getPaddingRight() == 0) {
                setPadding(getPaddingLeft(), getPaddingTop(), dp2px, getPaddingBottom());
            }
        }
    }

    private void initProgressRangeValue() {
        float f = this.mMax;
        float f2 = this.mMin;
        if (f >= f2) {
            if (this.mProgress < f2) {
                this.mProgress = f2;
            }
            if (this.mProgress > f) {
                this.mProgress = f;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("the Argument: MAX's value must be larger than MIN's.");
    }

    private void initStrokePaint() {
        if (this.mStockPaint == null) {
            this.mStockPaint = new Paint();
        }
        if (this.mTrackRoundedCorners) {
            this.mStockPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        this.mStockPaint.setAntiAlias(true);
        int i = this.mBackgroundTrackSize;
        if (i > this.mProgressTrackSize) {
            this.mProgressTrackSize = i;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int round = Math.round(this.mCustomDrawableMaxHeight + ((float) getPaddingTop()) + ((float) getPaddingBottom()));
        if (isAboveBelowText()) {
            setMeasuredDimension(resolveSize(SizeUtils.dp2px(this.mContext, 170.0f), i), round + (this.mTickTextsHeight * 2));
        } else {
            setMeasuredDimension(resolveSize(SizeUtils.dp2px(this.mContext, 170.0f), i), round + this.mTickTextsHeight);
        }
        initSeekBarInfo();
        refreshSeekBarLocation();
    }

    private void initSeekBarInfo() {
        this.mMeasuredWidth = getMeasuredWidth();
        if (Build.VERSION.SDK_INT < 17) {
            this.mPaddingLeft = getPaddingLeft();
            this.mPaddingRight = getPaddingRight();
        } else {
            this.mPaddingLeft = getPaddingStart();
            this.mPaddingRight = getPaddingEnd();
        }
        this.mPaddingTop = getPaddingTop();
        float f = (float) ((this.mMeasuredWidth - this.mPaddingLeft) - this.mPaddingRight);
        this.mSeekLength = f;
        int i = this.mTicksCount;
        int i2 = 1;
        if (i - 1 > 0) {
            i2 = i - 1;
        }
        this.mSeekBlockLength = f / ((float) i2);
    }

    private void refreshSeekBarLocation() {
        initTrackLocation();
        initTickTextsYLocation();
        if (this.mTickMarksX != null) {
            initTextsArray();
            if (this.mTicksCount > 2) {
                float f = this.mProgressArr[getClosestIndex()];
                this.mProgress = f;
                this.lastProgress = f;
            }
            refreshThumbCenterXByProgress(this.mProgress);
        }
    }

    private void initTextsArray() {
        if (this.mTickMarksX != null) {
            if (this.mTickTextsPosition != 0) {
                this.mTickTextsArr = new String[this.mTicksCount];
            }
            for (int i = 0; i < this.mTickMarksX.length; i++) {
                if (this.mTickTextsPosition != 0) {
                    this.mTickTextsArr[i] = getTickTextByPosition(i);
                    TextPaint textPaint = this.mTextPaint;
                    String[] strArr = this.mTickTextsArr;
                    textPaint.getTextBounds(strArr[i], 0, strArr[i].length(), this.mRect);
                    this.mTickTextsWidth[i] = (float) this.mRect.width();
                    this.mTextCenterX[i] = ((float) this.mPaddingLeft) + (this.mSeekBlockLength * ((float) i));
                }
                this.mTickMarksX[i] = ((float) this.mPaddingLeft) + (this.mSeekBlockLength * ((float) i));
            }
        }
    }

    private void initTrackLocation() {
        if (this.mR2L) {
            this.mBackgroundTrack.left = (float) this.mPaddingLeft;
            if (hasAboveText()) {
                this.mBackgroundTrack.top = ((float) this.mPaddingTop) + this.mThumbTouchRadius + ((float) this.mDefaultTickTextsHeight) + ((float) SizeUtils.dp2px(this.mContext, 3.0f));
            } else {
                this.mBackgroundTrack.top = ((float) this.mPaddingTop) + this.mThumbTouchRadius;
            }
            RectF rectF = this.mBackgroundTrack;
            float f = this.mSeekLength;
            float f2 = this.mProgress;
            float f3 = this.mMin;
            rectF.right = ((float) this.mPaddingLeft) + (f * (1.0f - ((f2 - f3) / (this.mMax - f3))));
            RectF rectF2 = this.mBackgroundTrack;
            rectF2.bottom = rectF2.top;
            this.mProgressTrack.left = this.mBackgroundTrack.right;
            this.mProgressTrack.top = this.mBackgroundTrack.top;
            this.mProgressTrack.right = (float) (this.mMeasuredWidth - this.mPaddingRight);
            this.mProgressTrack.bottom = this.mBackgroundTrack.bottom;
            return;
        }
        this.mProgressTrack.left = (float) this.mPaddingLeft;
        if (hasAboveText()) {
            this.mProgressTrack.top = ((float) this.mPaddingTop) + this.mThumbTouchRadius + ((float) this.mDefaultTickTextsHeight) + ((float) SizeUtils.dp2px(this.mContext, 3.0f));
        } else {
            this.mProgressTrack.top = ((float) this.mPaddingTop) + this.mThumbTouchRadius;
        }
        RectF rectF3 = this.mProgressTrack;
        float f4 = this.mProgress;
        float f5 = this.mMin;
        rectF3.right = (((f4 - f5) * this.mSeekLength) / (this.mMax - f5)) + ((float) this.mPaddingLeft);
        RectF rectF4 = this.mProgressTrack;
        rectF4.bottom = rectF4.top;
        this.mBackgroundTrack.left = this.mProgressTrack.right;
        this.mBackgroundTrack.top = this.mProgressTrack.bottom;
        this.mBackgroundTrack.right = (float) (this.mMeasuredWidth - this.mPaddingRight);
        this.mBackgroundTrack.bottom = this.mProgressTrack.bottom;
    }

    private String getTickTextByPosition(int i) {
        CharSequence[] charSequenceArr = this.mTickTextsCustomArray;
        if (charSequenceArr == null) {
            return getProgressString(this.mProgressArr[i]);
        }
        return i < charSequenceArr.length ? String.valueOf(charSequenceArr[i]) : "";
    }

    /* access modifiers changed from: package-private */
    public void refreshThumbCenterXByProgress(float f) {
        if (this.mR2L) {
            RectF rectF = this.mBackgroundTrack;
            float f2 = this.mSeekLength;
            float f3 = this.mMin;
            rectF.right = ((float) this.mPaddingLeft) + (f2 * (1.0f - ((f - f3) / (this.mMax - f3))));
            this.mProgressTrack.left = this.mBackgroundTrack.right;
            return;
        }
        RectF rectF2 = this.mProgressTrack;
        float f4 = this.mMin;
        rectF2.right = (((f - f4) * this.mSeekLength) / (this.mMax - f4)) + ((float) this.mPaddingLeft);
        this.mBackgroundTrack.left = this.mProgressTrack.right;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        drawTrack(canvas);
        drawTickMarks(canvas);
        drawTickTexts(canvas);
        drawThumb(canvas);
        drawThumbText(canvas);
    }

    private void drawTrack(Canvas canvas) {
        if (this.mCustomTrackSectionColorResult) {
            int i = this.mTicksCount;
            int i2 = i + -1 > 0 ? i - 1 : 1;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.mR2L) {
                    this.mStockPaint.setColor(this.mSectionTrackColorArray[(i2 - i3) - 1]);
                } else {
                    this.mStockPaint.setColor(this.mSectionTrackColorArray[i3]);
                }
                float thumbPosOnTickFloat = getThumbPosOnTickFloat();
                int i4 = (((float) i3) > thumbPosOnTickFloat ? 1 : (((float) i3) == thumbPosOnTickFloat ? 0 : -1));
                if (i4 < 0) {
                    int i5 = i3 + 1;
                    if (thumbPosOnTickFloat < ((float) i5)) {
                        float thumbCenterX = getThumbCenterX();
                        this.mStockPaint.setStrokeWidth((float) getLeftSideTrackSize());
                        canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, thumbCenterX, this.mProgressTrack.bottom, this.mStockPaint);
                        this.mStockPaint.setStrokeWidth((float) getRightSideTrackSize());
                        canvas.drawLine(thumbCenterX, this.mProgressTrack.top, this.mTickMarksX[i5], this.mProgressTrack.bottom, this.mStockPaint);
                    }
                }
                if (i4 < 0) {
                    this.mStockPaint.setStrokeWidth((float) getLeftSideTrackSize());
                } else {
                    this.mStockPaint.setStrokeWidth((float) getRightSideTrackSize());
                }
                canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, this.mTickMarksX[i3 + 1], this.mProgressTrack.bottom, this.mStockPaint);
            }
            return;
        }
        this.mStockPaint.setColor(this.mProgressTrackColor);
        this.mStockPaint.setStrokeWidth((float) this.mProgressTrackSize);
        canvas.drawLine(this.mProgressTrack.left, this.mProgressTrack.top, this.mProgressTrack.right, this.mProgressTrack.bottom, this.mStockPaint);
        this.mStockPaint.setColor(this.mBackgroundTrackColor);
        this.mStockPaint.setStrokeWidth((float) this.mBackgroundTrackSize);
        canvas.drawLine(this.mBackgroundTrack.left, this.mBackgroundTrack.top, this.mBackgroundTrack.right, this.mBackgroundTrack.bottom, this.mStockPaint);
    }

    private void drawTickMarks(Canvas canvas) {
        int i;
        Bitmap bitmap;
        if (this.mTicksCount == 0) {
            return;
        }
        if (this.mShowTickMarksType != 0 || this.mTickMarksDrawable != null) {
            float thumbCenterX = getThumbCenterX();
            for (int i2 = 0; i2 < this.mTickMarksX.length; i2++) {
                float thumbPosOnTickFloat = getThumbPosOnTickFloat();
                if ((!this.mTickMarksSweptHide || thumbCenterX < this.mTickMarksX[i2]) && ((!this.mTickMarksEndsHide || !(i2 == 0 || i2 == this.mTickMarksX.length - 1)) && (i2 != getThumbPosOnTick() || this.mTicksCount <= 2 || this.mSeekSmoothly))) {
                    int i3 = (((float) i2) > thumbPosOnTickFloat ? 1 : (((float) i2) == thumbPosOnTickFloat ? 0 : -1));
                    if (i3 <= 0) {
                        this.mStockPaint.setColor(getLeftSideTickColor());
                    } else {
                        this.mStockPaint.setColor(getRightSideTickColor());
                    }
                    if (this.mTickMarksDrawable != null) {
                        if (this.mSelectTickMarksBitmap == null || this.mUnselectedTickMarksBitmap == null) {
                            initTickMarksBitmap();
                        }
                        Bitmap bitmap2 = this.mSelectTickMarksBitmap;
                        if (bitmap2 == null || (bitmap = this.mUnselectedTickMarksBitmap) == null) {
                            throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
                        } else if (i3 <= 0) {
                            canvas.drawBitmap(bitmap2, this.mTickMarksX[i2] - (((float) bitmap.getWidth()) / 2.0f), this.mProgressTrack.top - (((float) this.mUnselectedTickMarksBitmap.getHeight()) / 2.0f), this.mStockPaint);
                        } else {
                            canvas.drawBitmap(bitmap, this.mTickMarksX[i2] - (((float) bitmap.getWidth()) / 2.0f), this.mProgressTrack.top - (((float) this.mUnselectedTickMarksBitmap.getHeight()) / 2.0f), this.mStockPaint);
                        }
                    } else {
                        int i4 = this.mShowTickMarksType;
                        if (i4 == 1) {
                            canvas.drawCircle(this.mTickMarksX[i2], this.mProgressTrack.top, this.mTickRadius, this.mStockPaint);
                        } else if (i4 == 3) {
                            int dp2px = SizeUtils.dp2px(this.mContext, 1.0f);
                            if (thumbCenterX >= this.mTickMarksX[i2]) {
                                i = getLeftSideTrackSize();
                            } else {
                                i = getRightSideTrackSize();
                            }
                            float f = (float) dp2px;
                            float f2 = ((float) i) / 2.0f;
                            canvas.drawRect(this.mTickMarksX[i2] - f, this.mProgressTrack.top - f2, this.mTickMarksX[i2] + f, this.mProgressTrack.top + f2, this.mStockPaint);
                        } else if (i4 == 2) {
                            float f3 = this.mTickMarksX[i2] - (((float) this.mTickMarksSize) / 2.0f);
                            float f4 = this.mProgressTrack.top;
                            int i5 = this.mTickMarksSize;
                            canvas.drawRect(f3, f4 - (((float) i5) / 2.0f), this.mTickMarksX[i2] + (((float) i5) / 2.0f), this.mProgressTrack.top + (((float) this.mTickMarksSize) / 2.0f), this.mStockPaint);
                        }
                    }
                }
            }
        }
    }

    private void drawTickTexts(Canvas canvas) {
        if (this.mTickTextsArr != null) {
            float thumbPosOnTickFloat = getThumbPosOnTickFloat();
            for (int i = 0; i < this.mTickTextsArr.length; i++) {
                if (i == getThumbPosOnTick()) {
                    this.mTextPaint.setColor(this.mHoveredTextColor);
                } else if (((float) i) < thumbPosOnTickFloat) {
                    this.mTextPaint.setColor(getLeftSideTickTextsColor());
                } else {
                    this.mTextPaint.setColor(getRightSideTickTextsColor());
                }
                int length = this.mR2L ? (this.mTickTextsArr.length - 1) - i : i;
                if (i == 0) {
                    canvas.drawText(this.mTickTextsArr[length], this.mTextCenterX[i] + (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                } else {
                    String[] strArr = this.mTickTextsArr;
                    if (i == strArr.length - 1) {
                        canvas.drawText(strArr[length], this.mTextCenterX[i] - (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                    } else {
                        canvas.drawText(strArr[length], this.mTextCenterX[i], this.mTickTextY, this.mTextPaint);
                    }
                }
            }
        }
    }

    private void drawThumb(Canvas canvas) {
        float thumbCenterX = getThumbCenterX();
        if (this.mThumbDrawable != null) {
            if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
                initThumbBitmap();
            }
            if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
                throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
            }
            this.mStockPaint.setAlpha(255);
            if (this.mIsTouching) {
                Bitmap bitmap = this.mPressedThumbBitmap;
                canvas.drawBitmap(bitmap, thumbCenterX - (((float) bitmap.getWidth()) / 2.0f), this.mProgressTrack.top - (((float) this.mPressedThumbBitmap.getHeight()) / 2.0f), this.mStockPaint);
                return;
            }
            Bitmap bitmap2 = this.mThumbBitmap;
            canvas.drawBitmap(bitmap2, thumbCenterX - (((float) bitmap2.getWidth()) / 2.0f), this.mProgressTrack.top - (((float) this.mThumbBitmap.getHeight()) / 2.0f), this.mStockPaint);
            return;
        }
        if (this.mIsTouching) {
            this.mStockPaint.setColor(this.mPressedThumbColor);
        } else {
            this.mStockPaint.setColor(this.mThumbColor);
        }
        canvas.drawCircle(thumbCenterX, this.mProgressTrack.top, this.mIsTouching ? this.mThumbTouchRadius : this.mThumbRadius, this.mStockPaint);
    }

    private void drawThumbText(Canvas canvas) {
        int i = this.mThumbTextShowPos;
        if (i != 0 && this.mTickTextsPosition != i) {
            this.mTextPaint.setColor(this.mThumbTextColor);
            canvas.drawText(getProgressString(this.mProgress), getThumbCenterX(), this.mThumbTextY, this.mTextPaint);
        }
    }

    private int getRightSideTrackSize() {
        if (this.mR2L) {
            return this.mProgressTrackSize;
        }
        return this.mBackgroundTrackSize;
    }

    private int getLeftSideTickColor() {
        if (this.mR2L) {
            return this.mUnSelectedTickMarksColor;
        }
        return this.mSelectedTickMarksColor;
    }

    private int getRightSideTickColor() {
        if (this.mR2L) {
            return this.mSelectedTickMarksColor;
        }
        return this.mUnSelectedTickMarksColor;
    }

    private int getLeftSideTrackSize() {
        if (this.mR2L) {
            return this.mBackgroundTrackSize;
        }
        return this.mProgressTrackSize;
    }

    private int getThumbPosOnTick() {
        if (this.mTicksCount != 0) {
            return Math.round((getThumbCenterX() - ((float) this.mPaddingLeft)) / this.mSeekBlockLength);
        }
        return 0;
    }

    private float getThumbPosOnTickFloat() {
        if (this.mTicksCount != 0) {
            return (getThumbCenterX() - ((float) this.mPaddingLeft)) / this.mSeekBlockLength;
        }
        return 0.0f;
    }

    private Bitmap getDrawBitmap(Drawable drawable, boolean z) {
        int i;
        int i2;
        if (drawable == null) {
            return null;
        }
        int dp2px = SizeUtils.dp2px(this.mContext, 30.0f);
        if (drawable.getIntrinsicWidth() > dp2px) {
            if (z) {
                i2 = this.mThumbSize;
            } else {
                i2 = this.mTickMarksSize;
            }
            i = getHeightByRatio(drawable, i2);
            if (i2 > dp2px) {
                i = getHeightByRatio(drawable, dp2px);
            } else {
                dp2px = i2;
            }
        } else {
            dp2px = drawable.getIntrinsicWidth();
            i = drawable.getIntrinsicHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(dp2px, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private int getHeightByRatio(Drawable drawable, int i) {
        return Math.round(((((float) i) * 1.0f) * ((float) drawable.getIntrinsicHeight())) / ((float) drawable.getIntrinsicWidth()));
    }

    private void initThumbColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mThumbColor = i;
            this.mPressedThumbColor = i;
            return;
        }
        int[][] iArr = null;
        int[][] iArr2 = null;
        try {
            int[] iArr3 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr3 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr != null && iArr3 != null) {
                if (iArr.length == 1) {
                    int i2 = iArr3[0];
                    this.mThumbColor = i2;
                    this.mPressedThumbColor = i2;
                } else if (iArr.length == 2) {
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        int[] iArr4 = iArr[i3];
                        if (iArr4.length == 0) {
                            this.mPressedThumbColor = iArr3[i3];
                        } else if (iArr4[0] == 16842919) {
                            this.mThumbColor = iArr3[i3];
                        } else {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
                }
            }
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTickMarksColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mSelectedTickMarksColor = i;
            this.mUnSelectedTickMarksColor = i;
            return;
        }
        int[][] iArr = null;
        int[][] iArr2 = null;
        try {
            int[] iArr3 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr3 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr != null && iArr3 != null) {
                if (iArr.length == 1) {
                    int i2 = iArr3[0];
                    this.mSelectedTickMarksColor = i2;
                    this.mUnSelectedTickMarksColor = i2;
                } else if (iArr.length == 2) {
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        int[] iArr4 = iArr[i3];
                        if (iArr4.length == 0) {
                            this.mUnSelectedTickMarksColor = iArr3[i3];
                        } else if (iArr4[0] == 16842913) {
                            this.mSelectedTickMarksColor = iArr3[i3];
                        } else {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something wrong happened when parsing thumb selector color." + e.getMessage());
        }
    }

    private void initTickTextsColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mUnselectedTextsColor = i;
            this.mSelectedTextsColor = i;
            this.mHoveredTextColor = i;
            return;
        }
        int[][] iArr = null;
        int[][] iArr2 = null;
        try {
            int[] iArr3 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr3 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr != null && iArr3 != null) {
                if (iArr.length == 1) {
                    int i2 = iArr3[0];
                    this.mUnselectedTextsColor = i2;
                    this.mSelectedTextsColor = i2;
                    this.mHoveredTextColor = i2;
                } else if (iArr.length == 3) {
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        int[] iArr4 = iArr[i3];
                        if (iArr4.length == 0) {
                            this.mUnselectedTextsColor = iArr3[i3];
                        } else {
                            int i4 = iArr4[0];
                            if (i4 == 16842913) {
                                this.mSelectedTextsColor = iArr3[i3];
                            } else if (i4 == 16843623) {
                                this.mHoveredTextColor = iArr3[i3];
                            } else {
                                throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
                }
            }
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTextsTypeface(int i, Typeface typeface) {
        if (i == 0) {
            this.mTextsTypeface = Typeface.DEFAULT;
        } else if (i == 1) {
            this.mTextsTypeface = Typeface.MONOSPACE;
        } else if (i == 2) {
            this.mTextsTypeface = Typeface.SANS_SERIF;
        } else if (i == 3) {
            this.mTextsTypeface = Typeface.SERIF;
        } else if (typeface == null) {
            this.mTextsTypeface = Typeface.DEFAULT;
        } else {
            this.mTextsTypeface = typeface;
        }
    }

    private void initThumbBitmap() {
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            if (drawable instanceof StateListDrawable) {
                try {
                    StateListDrawable stateListDrawable = (StateListDrawable) drawable;
                    Class<?> cls = stateListDrawable.getClass();
                    int intValue = ((Integer) cls.getMethod("getStateCount", new Class[0]).invoke(stateListDrawable, new Object[0])).intValue();
                    if (intValue == 2) {
                        Method method = cls.getMethod("getStateSet", new Class[]{Integer.TYPE});
                        Method method2 = cls.getMethod("getStateDrawable", new Class[]{Integer.TYPE});
                        for (int i = 0; i < intValue; i++) {
                            int[] iArr = (int[]) method.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)});
                            if (iArr.length <= 0) {
                                this.mThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)}), true);
                            } else if (iArr[0] == 16842919) {
                                this.mPressedThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)}), true);
                            } else {
                                throw new IllegalArgumentException("the state of the selector thumb drawable is wrong!");
                            }
                        }
                        return;
                    }
                    throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
                } catch (Exception unused) {
                    Bitmap drawBitmap = getDrawBitmap(this.mThumbDrawable, true);
                    this.mThumbBitmap = drawBitmap;
                    this.mPressedThumbBitmap = drawBitmap;
                }
            } else {
                Bitmap drawBitmap2 = getDrawBitmap(drawable, true);
                this.mThumbBitmap = drawBitmap2;
                this.mPressedThumbBitmap = drawBitmap2;
            }
        }
    }

    private void initTickMarksBitmap() {
        Drawable drawable = this.mTickMarksDrawable;
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            try {
                Class<?> cls = stateListDrawable.getClass();
                int intValue = ((Integer) cls.getMethod("getStateCount", new Class[0]).invoke(stateListDrawable, new Object[0])).intValue();
                if (intValue == 2) {
                    Method method = cls.getMethod("getStateSet", new Class[]{Integer.TYPE});
                    Method method2 = cls.getMethod("getStateDrawable", new Class[]{Integer.TYPE});
                    for (int i = 0; i < intValue; i++) {
                        int[] iArr = (int[]) method.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)});
                        if (iArr.length <= 0) {
                            this.mUnselectedTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)}), false);
                        } else if (iArr[0] == 16842913) {
                            this.mSelectTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, new Object[]{Integer.valueOf(i)}), false);
                        } else {
                            throw new IllegalArgumentException("the state of the selector TickMarks drawable is wrong!");
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
            } catch (Exception unused) {
                Bitmap drawBitmap = getDrawBitmap(this.mTickMarksDrawable, false);
                this.mUnselectedTickMarksBitmap = drawBitmap;
                this.mSelectTickMarksBitmap = drawBitmap;
            }
        } else {
            Bitmap drawBitmap2 = getDrawBitmap(drawable, false);
            this.mUnselectedTickMarksBitmap = drawBitmap2;
            this.mSelectTickMarksBitmap = drawBitmap2;
        }
    }

    private void measureTickTextsBonds() {
        if (needDrawText()) {
            initTextPaint();
            this.mTextPaint.setTypeface(this.mTextsTypeface);
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            this.mTickTextsHeight = this.mRect.height() + SizeUtils.dp2px(this.mContext, 3.0f);
        }
    }

    private boolean needDrawText() {
        return ((this.mTickTextsPosition == 0 || this.mTicksCount == 0) && this.mThumbTextShowPos == 0) ? false : true;
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            TextPaint textPaint = new TextPaint();
            this.mTextPaint = textPaint;
            textPaint.setAntiAlias(true);
            this.mTextPaint.setTextAlign(Paint.Align.CENTER);
            this.mTextPaint.setTextSize((float) this.mTickTextsSize);
        }
        if (this.mRect == null) {
            this.mRect = new Rect();
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
    }

    private void initTickTextsYLocation() {
        if (needDrawText()) {
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            this.mDefaultTickTextsHeight = this.mRect.height();
            if (!isAboveBelowText()) {
                if (hasBelowText()) {
                    this.mTickTextY = ((float) this.mPaddingTop) + this.mCustomDrawableMaxHeight + ((float) Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent())) + ((float) SizeUtils.dp2px(this.mContext, 3.0f));
                } else if (hasAboveText()) {
                    this.mTickTextY = (float) (this.mPaddingTop + Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent()) + SizeUtils.dp2px(this.mContext, 3.0f));
                }
                this.mThumbTextY = this.mTickTextY;
            } else if (this.mTickTextsPosition == 1) {
                this.mThumbTextY = (float) (this.mPaddingTop + Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent()) + SizeUtils.dp2px(this.mContext, 3.0f));
                this.mTickTextY = ((float) (this.mTickTextsHeight + this.mPaddingTop)) + this.mCustomDrawableMaxHeight + ((float) Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent())) + ((float) SizeUtils.dp2px(this.mContext, 3.0f));
            } else {
                this.mTickTextY = (float) (this.mPaddingTop + Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent()) + SizeUtils.dp2px(this.mContext, 3.0f));
                this.mThumbTextY = ((float) (this.mTickTextsHeight + this.mPaddingTop)) + this.mCustomDrawableMaxHeight + ((float) Math.round(((float) this.mDefaultTickTextsHeight) - this.mTextPaint.descent())) + ((float) SizeUtils.dp2px(this.mContext, 3.0f));
            }
        }
    }

    private boolean hasBelowText() {
        return (this.mTicksCount != 0 && this.mTickTextsPosition == 1) || this.mThumbTextShowPos == 1;
    }

    private boolean hasAboveText() {
        return (this.mTicksCount != 0 && this.mTickTextsPosition == 2) || this.mThumbTextShowPos == 2;
    }

    private boolean isAboveBelowText() {
        int i = this.mTicksCount;
        if (i != 0 && this.mTickTextsPosition == 2 && this.mThumbTextShowPos == 1) {
            return true;
        }
        return i != 0 && this.mTickTextsPosition == 1 && this.mThumbTextShowPos == 2;
    }

    private float getThumbCenterX() {
        if (this.mR2L) {
            return this.mBackgroundTrack.right;
        }
        return this.mProgressTrack.right;
    }

    private int getLeftSideTickTextsColor() {
        if (this.mR2L) {
            return this.mUnselectedTextsColor;
        }
        return this.mSelectedTextsColor;
    }

    private int getRightSideTickTextsColor() {
        if (this.mR2L) {
            return this.mUnselectedTextsColor;
        }
        return this.mUnselectedTextsColor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r0 != 3) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.mUserSeekable
            r1 = 0
            if (r0 == 0) goto L_0x0061
            boolean r0 = r4.isEnabled()
            if (r0 != 0) goto L_0x000c
            goto L_0x0061
        L_0x000c:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L_0x0033
            if (r0 == r2) goto L_0x0020
            r2 = 2
            if (r0 == r2) goto L_0x001c
            r2 = 3
            if (r0 == r2) goto L_0x0020
            goto L_0x005c
        L_0x001c:
            r4.refreshSeekBar(r5)
            goto L_0x005c
        L_0x0020:
            com.warkiz.tickseekbar.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L_0x0027
            r0.onStopTrackingTouch(r4)
        L_0x0027:
            r4.mIsTouching = r1
            boolean r0 = r4.autoAdjustThumb()
            if (r0 != 0) goto L_0x005c
            r4.invalidate()
            goto L_0x005c
        L_0x0033:
            r4.performClick()
            float r0 = r5.getX()
            float r3 = r5.getY()
            boolean r3 = r4.isTouchSeekBar(r0, r3)
            if (r3 == 0) goto L_0x005c
            boolean r3 = r4.mOnlyThumbDraggable
            if (r3 == 0) goto L_0x004f
            boolean r0 = r4.isTouchThumb(r0)
            if (r0 != 0) goto L_0x004f
            return r1
        L_0x004f:
            com.warkiz.tickseekbar.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L_0x0056
            r0.onStartTrackingTouch(r4)
        L_0x0056:
            r4.mIsTouching = r2
            r4.refreshSeekBar(r5)
            return r2
        L_0x005c:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.warkiz.tickseekbar.TickSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void refreshSeekBar(MotionEvent motionEvent) {
        refreshThumbCenterXByProgress(calculateProgress(calculateTouchX(adjustTouchX(motionEvent))));
        setSeekListener(true);
        invalidate();
    }

    private boolean autoAdjustThumb() {
        if (this.mTicksCount < 3 || !this.mSeekSmoothly || !this.mAdjustAuto) {
            return false;
        }
        final int closestIndex = getClosestIndex();
        final float f = this.mProgress;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, Math.abs(f - this.mProgressArr[closestIndex])});
        ofFloat.start();
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TickSeekBar tickSeekBar = TickSeekBar.this;
                float unused = tickSeekBar.lastProgress = tickSeekBar.mProgress;
                if (f - TickSeekBar.this.mProgressArr[closestIndex] > 0.0f) {
                    float unused2 = TickSeekBar.this.mProgress = f - ((Float) valueAnimator.getAnimatedValue()).floatValue();
                } else {
                    float unused3 = TickSeekBar.this.mProgress = f + ((Float) valueAnimator.getAnimatedValue()).floatValue();
                }
                TickSeekBar tickSeekBar2 = TickSeekBar.this;
                tickSeekBar2.refreshThumbCenterXByProgress(tickSeekBar2.mProgress);
                TickSeekBar.this.setSeekListener(false);
                TickSeekBar.this.invalidate();
            }
        });
        return true;
    }

    private int getClosestIndex() {
        float abs = Math.abs(this.mMax - this.mMin);
        int i = 0;
        int i2 = 0;
        while (true) {
            float[] fArr = this.mProgressArr;
            if (i >= fArr.length) {
                return i2;
            }
            float abs2 = Math.abs(fArr[i] - this.mProgress);
            if (abs2 <= abs) {
                i2 = i;
                abs = abs2;
            }
            i++;
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    /* access modifiers changed from: private */
    public void setSeekListener(boolean z) {
        if (this.mSeekChangeListener != null && progressChange()) {
            this.mSeekChangeListener.onSeeking(collectParams(z));
        }
    }

    private boolean progressChange() {
        if (this.mIsFloatProgress) {
            if (this.lastProgress != this.mProgress) {
                return true;
            }
            return false;
        } else if (Math.round(this.lastProgress) != Math.round(this.mProgress)) {
            return true;
        } else {
            return false;
        }
    }

    private SeekParams collectParams(boolean z) {
        String[] strArr;
        if (this.mSeekParams == null) {
            this.mSeekParams = new SeekParams(this);
        }
        this.mSeekParams.progress = getProgress();
        this.mSeekParams.progressFloat = getProgressFloat();
        this.mSeekParams.fromUser = z;
        if (this.mTicksCount > 2) {
            int thumbPosOnTick = getThumbPosOnTick();
            if (!(this.mTickTextsPosition == 0 || (strArr = this.mTickTextsArr) == null)) {
                this.mSeekParams.tickText = strArr[thumbPosOnTick];
            }
            if (this.mR2L) {
                this.mSeekParams.thumbPosition = (this.mTicksCount - thumbPosOnTick) - 1;
            } else {
                this.mSeekParams.thumbPosition = thumbPosOnTick;
            }
        }
        return this.mSeekParams;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if (parent == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            parent.requestDisallowInterceptTouchEvent(true);
        } else if (action == 1 || action == 3) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("tsb_instance_state", super.onSaveInstanceState());
        bundle.putFloat("tsb_progress", this.mProgress);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            float f = bundle.getFloat("tsb_progress");
            this.mProgress = f;
            setProgress(f);
            super.onRestoreInstanceState(bundle.getParcelable("tsb_instance_state"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setEnabled(boolean z) {
        if (z != isEnabled()) {
            super.setEnabled(z);
            if (isEnabled()) {
                setAlpha(1.0f);
            } else {
                setAlpha(0.3f);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() {
            public void run() {
                TickSeekBar.this.requestLayout();
            }
        });
    }

    private float adjustTouchX(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        int i = this.mPaddingLeft;
        if (x >= ((float) i)) {
            float x2 = motionEvent.getX();
            int i2 = this.mMeasuredWidth;
            int i3 = this.mPaddingRight;
            if (x2 <= ((float) (i2 - i3))) {
                return motionEvent.getX();
            }
            i = i2 - i3;
        }
        return (float) i;
    }

    private float calculateProgress(float f) {
        this.lastProgress = this.mProgress;
        float f2 = this.mMin;
        float f3 = f2 + (((this.mMax - f2) * (f - ((float) this.mPaddingLeft))) / this.mSeekLength);
        this.mProgress = f3;
        return f3;
    }

    private float calculateTouchX(float f) {
        if (this.mTicksCount > 2 && !this.mSeekSmoothly) {
            f = ((float) this.mPaddingLeft) + (this.mSeekBlockLength * ((float) Math.round((f - ((float) this.mPaddingLeft)) / this.mSeekBlockLength)));
        }
        return this.mR2L ? (this.mSeekLength - f) + ((float) (this.mPaddingLeft * 2)) : f;
    }

    private boolean isTouchSeekBar(float f, float f2) {
        if (this.mFaultTolerance == -1.0f) {
            this.mFaultTolerance = (float) SizeUtils.dp2px(this.mContext, 5.0f);
        }
        float f3 = this.mFaultTolerance;
        boolean z = f >= ((float) this.mPaddingLeft) - (f3 * 2.0f) && f <= ((float) (this.mMeasuredWidth - this.mPaddingRight)) + (f3 * 2.0f);
        boolean z2 = f2 >= (this.mProgressTrack.top - this.mThumbTouchRadius) - this.mFaultTolerance && f2 <= (this.mProgressTrack.top + this.mThumbTouchRadius) + this.mFaultTolerance;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    private String getProgressString(float f) {
        if (this.mIsFloatProgress) {
            return FormatUtils.fastFormat((double) f, this.mScale);
        }
        return String.valueOf(Math.round(f));
    }

    private boolean isTouchThumb(float f) {
        float touchX = getTouchX();
        int i = this.mThumbSize;
        return touchX - (((float) i) / 2.0f) <= f && f <= touchX + (((float) i) / 2.0f);
    }

    /* access modifiers changed from: package-private */
    public synchronized float getTouchX() {
        refreshThumbCenterXByProgress(this.mProgress);
        if (this.mR2L) {
            return this.mBackgroundTrack.right;
        }
        return this.mProgressTrack.right;
    }

    private void apply(Builder builder) {
        this.mMax = builder.max;
        this.mMin = builder.min;
        this.mProgress = builder.progress;
        this.mIsFloatProgress = builder.progressValueFloat;
        this.mSeekSmoothly = builder.seekSmoothly;
        this.mR2L = builder.r2l;
        this.mUserSeekable = builder.userSeekable;
        this.mClearPadding = builder.clearPadding;
        this.mOnlyThumbDraggable = builder.onlyThumbDraggable;
        this.mBackgroundTrackSize = builder.trackBackgroundSize;
        this.mBackgroundTrackColor = builder.trackBackgroundColor;
        this.mProgressTrackSize = builder.trackProgressSize;
        this.mProgressTrackColor = builder.trackProgressColor;
        this.mTrackRoundedCorners = builder.trackRoundedCorners;
        this.mThumbSize = builder.thumbSize;
        this.mThumbDrawable = builder.thumbDrawable;
        this.mThumbTextColor = builder.thumbTextColor;
        initThumbColor(builder.thumbColorStateList, builder.thumbColor);
        this.mThumbTextShowPos = builder.thumbTextShow;
        this.mTicksCount = builder.tickCount;
        this.mShowTickMarksType = builder.showTickMarksType;
        this.mTickMarksSize = builder.tickMarksSize;
        this.mTickMarksDrawable = builder.tickMarksDrawable;
        this.mTickMarksEndsHide = builder.tickMarksEndsHide;
        this.mTickMarksSweptHide = builder.tickMarksSweptHide;
        initTickMarksColor(builder.tickMarksColorStateList, builder.tickMarksColor);
        this.mTickTextsPosition = builder.tickTextsShow;
        this.mTickTextsSize = builder.tickTextsSize;
        this.mTickTextsCustomArray = builder.tickTextsCustomArray;
        this.mTextsTypeface = builder.tickTextsTypeFace;
        initTickTextsColor(builder.tickTextsColorStateList, builder.tickTextsColor);
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public int getTickCount() {
        return this.mTicksCount;
    }

    public synchronized float getProgressFloat() {
        return BigDecimal.valueOf((double) this.mProgress).setScale(this.mScale, 4).floatValue();
    }

    public int getProgress() {
        return Math.round(this.mProgress);
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public OnSeekChangeListener getOnSeekChangeListener() {
        return this.mSeekChangeListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setProgress(float r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            float r0 = r2.mProgress     // Catch:{ all -> 0x0033 }
            r2.lastProgress = r0     // Catch:{ all -> 0x0033 }
            float r0 = r2.mMin     // Catch:{ all -> 0x0033 }
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x000d
        L_0x000b:
            r3 = r0
            goto L_0x0014
        L_0x000d:
            float r0 = r2.mMax     // Catch:{ all -> 0x0033 }
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0014
            goto L_0x000b
        L_0x0014:
            r2.mProgress = r3     // Catch:{ all -> 0x0033 }
            int r3 = r2.mTicksCount     // Catch:{ all -> 0x0033 }
            r0 = 2
            if (r3 <= r0) goto L_0x0025
            float[] r3 = r2.mProgressArr     // Catch:{ all -> 0x0033 }
            int r0 = r2.getClosestIndex()     // Catch:{ all -> 0x0033 }
            r3 = r3[r0]     // Catch:{ all -> 0x0033 }
            r2.mProgress = r3     // Catch:{ all -> 0x0033 }
        L_0x0025:
            r3 = 0
            r2.setSeekListener(r3)     // Catch:{ all -> 0x0033 }
            float r3 = r2.mProgress     // Catch:{ all -> 0x0033 }
            r2.refreshThumbCenterXByProgress(r3)     // Catch:{ all -> 0x0033 }
            r2.postInvalidate()     // Catch:{ all -> 0x0033 }
            monitor-exit(r2)
            return
        L_0x0033:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.warkiz.tickseekbar.TickSeekBar.setProgress(float):void");
    }

    public synchronized void setMax(float f) {
        this.mMax = Math.max(this.mMin, f);
        initProgressRangeValue();
        refreshSeekBarLocation();
        invalidate();
    }

    public synchronized void setMin(float f) {
        this.mMin = Math.min(this.mMax, f);
        initProgressRangeValue();
        refreshSeekBarLocation();
        invalidate();
    }

    public void setR2L(boolean z) {
        this.mR2L = z;
        requestLayout();
        invalidate();
    }

    public void setThumbDrawable(Drawable drawable) {
        this.mThumbDrawable = drawable;
        float min = ((float) Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize)) / 2.0f;
        this.mThumbRadius = min;
        this.mThumbTouchRadius = min;
        this.mCustomDrawableMaxHeight = Math.max(min, this.mTickRadius) * 2.0f;
        initThumbBitmap();
        requestLayout();
        invalidate();
    }

    public void thumbColor(int i) {
        this.mThumbColor = i;
        this.mPressedThumbColor = i;
        invalidate();
    }

    public void thumbColorStateList(ColorStateList colorStateList) {
        initThumbColor(colorStateList, this.mThumbColor);
        invalidate();
    }

    public void setTickMarksDrawable(Drawable drawable) {
        this.mTickMarksDrawable = drawable;
        float min = ((float) Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize)) / 2.0f;
        this.mTickRadius = min;
        this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, min) * 2.0f;
        initTickMarksBitmap();
        invalidate();
    }

    public void tickMarksColor(int i) {
        this.mSelectedTickMarksColor = i;
        this.mUnSelectedTickMarksColor = i;
        invalidate();
    }

    public void tickMarksColor(ColorStateList colorStateList) {
        initTickMarksColor(colorStateList, this.mSelectedTickMarksColor);
        invalidate();
    }

    public void tickTextsColor(int i) {
        this.mUnselectedTextsColor = i;
        this.mUnselectedTextsColor = i;
        this.mHoveredTextColor = i;
        invalidate();
    }

    public void tickTextsColorStateList(ColorStateList colorStateList) {
        initTickTextsColor(colorStateList, this.mSelectedTextsColor);
        invalidate();
    }

    public void setDecimalScale(int i) {
        this.mScale = i;
    }

    public void customSectionTrackColor(ColorCollector colorCollector) {
        int i = this.mTicksCount;
        int i2 = 1;
        if (i - 1 > 0) {
            i2 = i - 1;
        }
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = this.mBackgroundTrackColor;
        }
        this.mCustomTrackSectionColorResult = colorCollector.collectSectionTrackColor(iArr);
        this.mSectionTrackColorArray = iArr;
        invalidate();
    }

    public void customTickTexts(String[] strArr) {
        this.mTickTextsCustomArray = strArr;
        if (this.mTickTextsArr != null) {
            int i = 0;
            while (i < this.mTickTextsArr.length) {
                String valueOf = i < strArr.length ? String.valueOf(strArr[i]) : "";
                int i2 = this.mR2L ? (this.mTicksCount - 1) - i : i;
                this.mTickTextsArr[i2] = valueOf;
                TextPaint textPaint = this.mTextPaint;
                if (!(textPaint == null || this.mRect == null)) {
                    textPaint.getTextBounds(valueOf, 0, valueOf.length(), this.mRect);
                    this.mTickTextsWidth[i2] = (float) this.mRect.width();
                }
                i++;
            }
            invalidate();
        }
    }

    public void customTickTextsTypeface(Typeface typeface) {
        this.mTextsTypeface = typeface;
        measureTickTextsBonds();
        requestLayout();
        invalidate();
    }

    public void setOnSeekChangeListener(OnSeekChangeListener onSeekChangeListener) {
        this.mSeekChangeListener = onSeekChangeListener;
    }

    public synchronized void setTickCount(int i) {
        int i2 = this.mTicksCount;
        if (i2 < 0 || i2 > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        }
        this.mTicksCount = i;
        collectTicksInfo();
        initTextsArray();
        initSeekBarInfo();
        refreshSeekBarLocation();
        invalidate();
    }

    public void setThumbAdjustAuto(boolean z) {
        this.mAdjustAuto = z;
    }
}
