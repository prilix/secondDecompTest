package p006co.simplecrop.android.simplecropimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;

/* renamed from: co.simplecrop.android.simplecropimage.ImageViewTouchBase */
abstract class ImageViewTouchBase extends ImageView {
    static final float SCALE_RATE = 1.25f;
    private static final String TAG = "ImageViewTouchBase";
    protected Matrix mBaseMatrix = new Matrix();
    protected final RotateBitmap mBitmapDisplayed = new RotateBitmap((Bitmap) null);
    int mBottom;
    private final Matrix mDisplayMatrix = new Matrix();
    protected Handler mHandler = new Handler();
    int mLeft;
    private final float[] mMatrixValues = new float[9];
    float mMaxZoom;
    private Runnable mOnLayoutRunnable = null;
    private Recycler mRecycler;
    int mRight;
    protected Matrix mSuppMatrix = new Matrix();
    int mThisHeight = -1;
    int mThisWidth = -1;
    int mTop;

    /* renamed from: co.simplecrop.android.simplecropimage.ImageViewTouchBase$Recycler */
    public interface Recycler {
        void recycle(Bitmap bitmap);
    }

    public void setRecycler(Recycler recycler) {
        this.mRecycler = recycler;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mLeft = i;
        this.mRight = i3;
        this.mTop = i2;
        this.mBottom = i4;
        this.mThisWidth = i3 - i;
        this.mThisHeight = i4 - i2;
        Runnable runnable = this.mOnLayoutRunnable;
        if (runnable != null) {
            this.mOnLayoutRunnable = null;
            runnable.run();
        }
        if (this.mBitmapDisplayed.getBitmap() != null) {
            getProperBaseMatrix(this.mBitmapDisplayed, this.mBaseMatrix);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || getScale() <= 1.0f) {
            return super.onKeyDown(i, keyEvent);
        }
        zoomTo(1.0f);
        return true;
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, 0);
    }

    private void setImageBitmap(Bitmap bitmap, int i) {
        Recycler recycler;
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap bitmap2 = this.mBitmapDisplayed.getBitmap();
        this.mBitmapDisplayed.setBitmap(bitmap);
        this.mBitmapDisplayed.setRotation(i);
        if (bitmap2 != null && bitmap2 != bitmap && (recycler = this.mRecycler) != null) {
            recycler.recycle(bitmap2);
        }
    }

    public void clear() {
        setImageBitmapResetBase((Bitmap) null, true);
    }

    public void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        setImageRotateBitmapResetBase(new RotateBitmap(bitmap), z);
    }

    public void setImageRotateBitmapResetBase(final RotateBitmap rotateBitmap, final boolean z) {
        if (getWidth() <= 0) {
            this.mOnLayoutRunnable = new Runnable() {
                public void run() {
                    ImageViewTouchBase.this.setImageRotateBitmapResetBase(rotateBitmap, z);
                }
            };
            return;
        }
        if (rotateBitmap.getBitmap() != null) {
            getProperBaseMatrix(rotateBitmap, this.mBaseMatrix);
            setImageBitmap(rotateBitmap.getBitmap(), rotateBitmap.getRotation());
        } else {
            this.mBaseMatrix.reset();
            setImageBitmap((Bitmap) null);
        }
        if (z) {
            this.mSuppMatrix.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.mMaxZoom = maxZoom();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void center(boolean r7, boolean r8) {
        /*
            r6 = this;
            co.simplecrop.android.simplecropimage.RotateBitmap r0 = r6.mBitmapDisplayed
            android.graphics.Bitmap r0 = r0.getBitmap()
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            android.graphics.Matrix r0 = r6.getImageViewMatrix()
            android.graphics.RectF r1 = new android.graphics.RectF
            co.simplecrop.android.simplecropimage.RotateBitmap r2 = r6.mBitmapDisplayed
            android.graphics.Bitmap r2 = r2.getBitmap()
            int r2 = r2.getWidth()
            float r2 = (float) r2
            co.simplecrop.android.simplecropimage.RotateBitmap r3 = r6.mBitmapDisplayed
            android.graphics.Bitmap r3 = r3.getBitmap()
            int r3 = r3.getHeight()
            float r3 = (float) r3
            r4 = 0
            r1.<init>(r4, r4, r2, r3)
            r0.mapRect(r1)
            float r0 = r1.height()
            float r2 = r1.width()
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L_0x005f
            int r8 = r6.getHeight()
            float r8 = (float) r8
            int r5 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r5 >= 0) goto L_0x0047
            float r8 = r8 - r0
            float r8 = r8 / r3
            float r0 = r1.top
        L_0x0045:
            float r8 = r8 - r0
            goto L_0x0060
        L_0x0047:
            float r0 = r1.top
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0051
            float r8 = r1.top
            float r8 = -r8
            goto L_0x0060
        L_0x0051:
            float r0 = r1.bottom
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x005f
            int r8 = r6.getHeight()
            float r8 = (float) r8
            float r0 = r1.bottom
            goto L_0x0045
        L_0x005f:
            r8 = 0
        L_0x0060:
            if (r7 == 0) goto L_0x0085
            int r7 = r6.getWidth()
            float r7 = (float) r7
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0072
            float r7 = r7 - r2
            float r7 = r7 / r3
            float r0 = r1.left
        L_0x006f:
            float r4 = r7 - r0
            goto L_0x0085
        L_0x0072:
            float r0 = r1.left
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x007c
            float r7 = r1.left
            float r4 = -r7
            goto L_0x0085
        L_0x007c:
            float r0 = r1.right
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0085
            float r0 = r1.right
            goto L_0x006f
        L_0x0085:
            r6.postTranslate(r4, r8)
            android.graphics.Matrix r7 = r6.getImageViewMatrix()
            r6.setImageMatrix(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p006co.simplecrop.android.simplecropimage.ImageViewTouchBase.center(boolean, boolean):void");
    }

    public ImageViewTouchBase(Context context) {
        super(context);
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* access modifiers changed from: protected */
    public float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    /* access modifiers changed from: protected */
    public float getScale(Matrix matrix) {
        return getValue(matrix, 0);
    }

    /* access modifiers changed from: protected */
    public float getScale() {
        return getScale(this.mSuppMatrix);
    }

    private void getProperBaseMatrix(RotateBitmap rotateBitmap, Matrix matrix) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        float width2 = (float) rotateBitmap.getWidth();
        float height2 = (float) rotateBitmap.getHeight();
        rotateBitmap.getRotation();
        matrix.reset();
        float min = Math.min(Math.min(width / width2, 2.0f), Math.min(height / height2, 2.0f));
        matrix.postConcat(rotateBitmap.getRotateMatrix());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (width2 * min)) / 2.0f, (height - (height2 * min)) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public Matrix getImageViewMatrix() {
        this.mDisplayMatrix.set(this.mBaseMatrix);
        this.mDisplayMatrix.postConcat(this.mSuppMatrix);
        return this.mDisplayMatrix;
    }

    /* access modifiers changed from: protected */
    public float maxZoom() {
        if (this.mBitmapDisplayed.getBitmap() == null) {
            return 1.0f;
        }
        return Math.max(((float) this.mBitmapDisplayed.getWidth()) / ((float) this.mThisWidth), ((float) this.mBitmapDisplayed.getHeight()) / ((float) this.mThisHeight)) * 4.0f;
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3) {
        float f4 = this.mMaxZoom;
        if (f > f4) {
            f = f4;
        }
        float scale = f / getScale();
        this.mSuppMatrix.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        center(true, true);
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3, float f4) {
        final float scale = (f - getScale()) / f4;
        final float scale2 = getScale();
        final long currentTimeMillis = System.currentTimeMillis();
        final float f5 = f4;
        final float f6 = f2;
        final float f7 = f3;
        this.mHandler.post(new Runnable() {
            public void run() {
                float min = Math.min(f5, (float) (System.currentTimeMillis() - currentTimeMillis));
                ImageViewTouchBase.this.zoomTo(scale2 + (scale * min), f6, f7);
                if (min < f5) {
                    ImageViewTouchBase.this.mHandler.post(this);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f) {
        zoomTo(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public void zoomIn() {
        zoomIn(SCALE_RATE);
    }

    /* access modifiers changed from: protected */
    public void zoomOut() {
        zoomOut(SCALE_RATE);
    }

    /* access modifiers changed from: protected */
    public void zoomIn(float f) {
        if (getScale() < this.mMaxZoom && this.mBitmapDisplayed.getBitmap() != null) {
            this.mSuppMatrix.postScale(f, f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    /* access modifiers changed from: protected */
    public void zoomOut(float f) {
        if (this.mBitmapDisplayed.getBitmap() != null) {
            float width = ((float) getWidth()) / 2.0f;
            float height = ((float) getHeight()) / 2.0f;
            Matrix matrix = new Matrix(this.mSuppMatrix);
            float f2 = 1.0f / f;
            matrix.postScale(f2, f2, width, height);
            if (getScale(matrix) < 1.0f) {
                this.mSuppMatrix.setScale(1.0f, 1.0f, width, height);
            } else {
                this.mSuppMatrix.postScale(f2, f2, width, height);
            }
            setImageMatrix(getImageViewMatrix());
            center(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public void postTranslate(float f, float f2) {
        this.mSuppMatrix.postTranslate(f, f2);
    }

    /* access modifiers changed from: protected */
    public void panBy(float f, float f2) {
        postTranslate(f, f2);
        setImageMatrix(getImageViewMatrix());
    }
}
