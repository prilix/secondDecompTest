package p006co.simplecrop.android.simplecropimage;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewCompat;

/* renamed from: co.simplecrop.android.simplecropimage.HighlightView */
class HighlightView {
    public static final int GROW_BOTTOM_EDGE = 16;
    public static final int GROW_LEFT_EDGE = 2;
    public static final int GROW_NONE = 1;
    public static final int GROW_RIGHT_EDGE = 4;
    public static final int GROW_TOP_EDGE = 8;
    public static final int MOVE = 32;
    private static final String TAG = "HighlightView";
    private boolean mCircle = false;
    View mContext;
    RectF mCropRect;
    Rect mDrawRect;
    private final Paint mFocusPaint = new Paint();
    boolean mHidden;
    private RectF mImageRect;
    private float mInitialAspectRatio;
    boolean mIsFocused;
    private boolean mMaintainAspectRatio = false;
    Matrix mMatrix;
    private ModifyMode mMode = ModifyMode.None;
    private final Paint mNoFocusPaint = new Paint();
    private final Paint mOutlinePaint = new Paint();
    private Drawable mResizeDrawableDiagonal;
    private Drawable mResizeDrawableHeight;
    private Drawable mResizeDrawableWidth;

    /* renamed from: co.simplecrop.android.simplecropimage.HighlightView$ModifyMode */
    enum ModifyMode {
        None,
        Move,
        Grow
    }

    public HighlightView(View view) {
        this.mContext = view;
    }

    private void init() {
        Resources resources = this.mContext.getResources();
        this.mResizeDrawableWidth = resources.getDrawable(C0856R.C0858drawable.camera_crop_width);
        this.mResizeDrawableHeight = resources.getDrawable(C0856R.C0858drawable.camera_crop_height);
        this.mResizeDrawableDiagonal = resources.getDrawable(C0856R.C0858drawable.indicator_autocrop);
    }

    public boolean hasFocus() {
        return this.mIsFocused;
    }

    public void setFocus(boolean z) {
        this.mIsFocused = z;
    }

    public void setHidden(boolean z) {
        this.mHidden = z;
    }

    /* access modifiers changed from: protected */
    public void draw(Canvas canvas) {
        if (!this.mHidden) {
            Path path = new Path();
            if (!hasFocus()) {
                this.mOutlinePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                canvas.drawRect(this.mDrawRect, this.mOutlinePaint);
                return;
            }
            Rect rect = new Rect();
            this.mContext.getDrawingRect(rect);
            if (this.mCircle) {
                canvas.save();
                float width = ((float) this.mDrawRect.width()) / 2.0f;
                path.addCircle(((float) this.mDrawRect.left) + width, ((float) this.mDrawRect.top) + (((float) this.mDrawRect.height()) / 2.0f), width, Path.Direction.CW);
                this.mOutlinePaint.setColor(-1112874);
                canvas.clipPath(path, Region.Op.DIFFERENCE);
                canvas.drawRect(rect, hasFocus() ? this.mFocusPaint : this.mNoFocusPaint);
                canvas.restore();
            } else {
                Rect rect2 = new Rect(rect.left, rect.top, rect.right, this.mDrawRect.top);
                if (rect2.width() > 0 && rect2.height() > 0) {
                    canvas.drawRect(rect2, hasFocus() ? this.mFocusPaint : this.mNoFocusPaint);
                }
                Rect rect3 = new Rect(rect.left, this.mDrawRect.bottom, rect.right, rect.bottom);
                if (rect3.width() > 0 && rect3.height() > 0) {
                    canvas.drawRect(rect3, hasFocus() ? this.mFocusPaint : this.mNoFocusPaint);
                }
                Rect rect4 = new Rect(rect.left, rect2.bottom, this.mDrawRect.left, rect3.top);
                if (rect4.width() > 0 && rect4.height() > 0) {
                    canvas.drawRect(rect4, hasFocus() ? this.mFocusPaint : this.mNoFocusPaint);
                }
                Rect rect5 = new Rect(this.mDrawRect.right, rect2.bottom, rect.right, rect3.top);
                if (rect5.width() > 0 && rect5.height() > 0) {
                    canvas.drawRect(rect5, hasFocus() ? this.mFocusPaint : this.mNoFocusPaint);
                }
                path.addRect(new RectF(this.mDrawRect), Path.Direction.CW);
                this.mOutlinePaint.setColor(-30208);
            }
            canvas.drawPath(path, this.mOutlinePaint);
            if (this.mMode != ModifyMode.Grow) {
                return;
            }
            if (this.mCircle) {
                int intrinsicWidth = this.mResizeDrawableDiagonal.getIntrinsicWidth();
                int intrinsicHeight = this.mResizeDrawableDiagonal.getIntrinsicHeight();
                int round = (int) Math.round(Math.cos(0.7853981633974483d) * (((double) this.mDrawRect.width()) / 2.0d));
                int width2 = ((this.mDrawRect.left + (this.mDrawRect.width() / 2)) + round) - (intrinsicWidth / 2);
                int height = ((this.mDrawRect.top + (this.mDrawRect.height() / 2)) - round) - (intrinsicHeight / 2);
                Drawable drawable = this.mResizeDrawableDiagonal;
                drawable.setBounds(width2, height, drawable.getIntrinsicWidth() + width2, this.mResizeDrawableDiagonal.getIntrinsicHeight() + height);
                this.mResizeDrawableDiagonal.draw(canvas);
                return;
            }
            int i = this.mDrawRect.left + 1;
            int i2 = this.mDrawRect.right + 1;
            int i3 = this.mDrawRect.top + 4;
            int i4 = this.mDrawRect.bottom + 3;
            int intrinsicWidth2 = this.mResizeDrawableWidth.getIntrinsicWidth() / 2;
            int intrinsicHeight2 = this.mResizeDrawableWidth.getIntrinsicHeight() / 2;
            int intrinsicHeight3 = this.mResizeDrawableHeight.getIntrinsicHeight() / 2;
            int intrinsicWidth3 = this.mResizeDrawableHeight.getIntrinsicWidth() / 2;
            int i5 = this.mDrawRect.left + ((this.mDrawRect.right - this.mDrawRect.left) / 2);
            int i6 = this.mDrawRect.top + ((this.mDrawRect.bottom - this.mDrawRect.top) / 2);
            int i7 = i6 - intrinsicHeight2;
            int i8 = i6 + intrinsicHeight2;
            this.mResizeDrawableWidth.setBounds(i - intrinsicWidth2, i7, i + intrinsicWidth2, i8);
            this.mResizeDrawableWidth.draw(canvas);
            this.mResizeDrawableWidth.setBounds(i2 - intrinsicWidth2, i7, i2 + intrinsicWidth2, i8);
            this.mResizeDrawableWidth.draw(canvas);
            int i9 = i5 - intrinsicWidth3;
            int i10 = i5 + intrinsicWidth3;
            this.mResizeDrawableHeight.setBounds(i9, i3 - intrinsicHeight3, i10, i3 + intrinsicHeight3);
            this.mResizeDrawableHeight.draw(canvas);
            this.mResizeDrawableHeight.setBounds(i9, i4 - intrinsicHeight3, i10, i4 + intrinsicHeight3);
            this.mResizeDrawableHeight.draw(canvas);
        }
    }

    public ModifyMode getMode() {
        return this.mMode;
    }

    public void setMode(ModifyMode modifyMode) {
        if (modifyMode != this.mMode) {
            this.mMode = modifyMode;
            this.mContext.invalidate();
        }
    }

    public int getHit(float f, float f2) {
        Rect computeLayout = computeLayout();
        if (this.mCircle) {
            float centerX = f - ((float) computeLayout.centerX());
            float centerY = f2 - ((float) computeLayout.centerY());
            int sqrt = (int) Math.sqrt((double) ((centerX * centerX) + (centerY * centerY)));
            int width = this.mDrawRect.width() / 2;
            if (((float) Math.abs(sqrt - width)) <= 20.0f) {
                return Math.abs(centerY) > Math.abs(centerX) ? centerY < 0.0f ? 8 : 16 : centerX < 0.0f ? 2 : 4;
            }
            if (sqrt < width) {
                return 32;
            }
            return 1;
        }
        boolean z = false;
        boolean z2 = f2 >= ((float) computeLayout.top) - 20.0f && f2 < ((float) computeLayout.bottom) + 20.0f;
        if (f >= ((float) computeLayout.left) - 20.0f && f < ((float) computeLayout.right) + 20.0f) {
            z = true;
        }
        int i = (Math.abs(((float) computeLayout.left) - f) >= 20.0f || !z2) ? 1 : 3;
        if (Math.abs(((float) computeLayout.right) - f) < 20.0f && z2) {
            i |= 4;
        }
        if (Math.abs(((float) computeLayout.top) - f2) < 20.0f && z) {
            i |= 8;
        }
        if (Math.abs(((float) computeLayout.bottom) - f2) < 20.0f && z) {
            i |= 16;
        }
        if (i != 1 || !computeLayout.contains((int) f, (int) f2)) {
            return i;
        }
        return 32;
    }

    /* access modifiers changed from: package-private */
    public void handleMotion(int i, float f, float f2) {
        Rect computeLayout = computeLayout();
        int i2 = 1;
        if (i != 1) {
            if (i == 32) {
                moveBy(f * (this.mCropRect.width() / ((float) computeLayout.width())), f2 * (this.mCropRect.height() / ((float) computeLayout.height())));
                return;
            }
            if ((i & 6) == 0) {
                f = 0.0f;
            }
            if ((i & 24) == 0) {
                f2 = 0.0f;
            }
            float width = f * (this.mCropRect.width() / ((float) computeLayout.width()));
            float height = f2 * (this.mCropRect.height() / ((float) computeLayout.height()));
            float f3 = ((float) ((i & 2) != 0 ? -1 : 1)) * width;
            if ((i & 8) != 0) {
                i2 = -1;
            }
            growBy(f3, ((float) i2) * height);
        }
    }

    /* access modifiers changed from: package-private */
    public void moveBy(float f, float f2) {
        Rect rect = new Rect(this.mDrawRect);
        this.mCropRect.offset(f, f2);
        this.mCropRect.offset(Math.max(0.0f, this.mImageRect.left - this.mCropRect.left), Math.max(0.0f, this.mImageRect.top - this.mCropRect.top));
        this.mCropRect.offset(Math.min(0.0f, this.mImageRect.right - this.mCropRect.right), Math.min(0.0f, this.mImageRect.bottom - this.mCropRect.bottom));
        Rect computeLayout = computeLayout();
        this.mDrawRect = computeLayout;
        rect.union(computeLayout);
        rect.inset(-10, -10);
        this.mContext.invalidate(rect);
    }

    /* access modifiers changed from: package-private */
    public void growBy(float f, float f2) {
        if (this.mMaintainAspectRatio) {
            if (f != 0.0f) {
                f2 = f / this.mInitialAspectRatio;
            } else if (f2 != 0.0f) {
                f = this.mInitialAspectRatio * f2;
            }
        }
        RectF rectF = new RectF(this.mCropRect);
        if (f > 0.0f && rectF.width() + (f * 2.0f) > this.mImageRect.width()) {
            f = (this.mImageRect.width() - rectF.width()) / 2.0f;
            if (this.mMaintainAspectRatio) {
                f2 = f / this.mInitialAspectRatio;
            }
        }
        if (f2 > 0.0f && rectF.height() + (f2 * 2.0f) > this.mImageRect.height()) {
            f2 = (this.mImageRect.height() - rectF.height()) / 2.0f;
            if (this.mMaintainAspectRatio) {
                f = this.mInitialAspectRatio * f2;
            }
        }
        rectF.inset(-f, -f2);
        float f3 = 25.0f;
        if (rectF.width() < 25.0f) {
            rectF.inset((-(25.0f - rectF.width())) / 2.0f, 0.0f);
        }
        if (this.mMaintainAspectRatio) {
            f3 = 25.0f / this.mInitialAspectRatio;
        }
        if (rectF.height() < f3) {
            rectF.inset(0.0f, (-(f3 - rectF.height())) / 2.0f);
        }
        if (rectF.left < this.mImageRect.left) {
            rectF.offset(this.mImageRect.left - rectF.left, 0.0f);
        } else if (rectF.right > this.mImageRect.right) {
            rectF.offset(-(rectF.right - this.mImageRect.right), 0.0f);
        }
        if (rectF.top < this.mImageRect.top) {
            rectF.offset(0.0f, this.mImageRect.top - rectF.top);
        } else if (rectF.bottom > this.mImageRect.bottom) {
            rectF.offset(0.0f, -(rectF.bottom - this.mImageRect.bottom));
        }
        this.mCropRect.set(rectF);
        this.mDrawRect = computeLayout();
        this.mContext.invalidate();
    }

    public Rect getCropRect() {
        return new Rect((int) this.mCropRect.left, (int) this.mCropRect.top, (int) this.mCropRect.right, (int) this.mCropRect.bottom);
    }

    private Rect computeLayout() {
        RectF rectF = new RectF(this.mCropRect.left, this.mCropRect.top, this.mCropRect.right, this.mCropRect.bottom);
        this.mMatrix.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void invalidate() {
        this.mDrawRect = computeLayout();
    }

    public void setup(Matrix matrix, Rect rect, RectF rectF, boolean z, boolean z2) {
        if (z) {
            z2 = true;
        }
        this.mMatrix = new Matrix(matrix);
        this.mCropRect = rectF;
        this.mImageRect = new RectF(rect);
        this.mMaintainAspectRatio = z2;
        this.mCircle = z;
        this.mInitialAspectRatio = this.mCropRect.width() / this.mCropRect.height();
        this.mDrawRect = computeLayout();
        this.mFocusPaint.setARGB(125, 50, 50, 50);
        this.mNoFocusPaint.setARGB(125, 50, 50, 50);
        this.mOutlinePaint.setStrokeWidth(3.0f);
        this.mOutlinePaint.setStyle(Paint.Style.STROKE);
        this.mOutlinePaint.setAntiAlias(true);
        this.mMode = ModifyMode.None;
        init();
    }
}
