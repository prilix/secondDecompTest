package p006co.simplecrop.android.simplecropimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import p006co.simplecrop.android.simplecropimage.HighlightView;
import p006co.simplecrop.android.simplecropimage.ImageViewTouchBase;

/* renamed from: co.simplecrop.android.simplecropimage.CropImageView */
public class CropImageView extends ImageViewTouchBase {
    private Context mContext;
    ArrayList<HighlightView> mHighlightViews = new ArrayList<>();
    float mLastX;
    float mLastY;
    int mMotionEdge;
    HighlightView mMotionHighlightView = null;

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public /* bridge */ /* synthetic */ void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

    public /* bridge */ /* synthetic */ void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        super.setImageBitmapResetBase(bitmap, z);
    }

    public /* bridge */ /* synthetic */ void setImageRotateBitmapResetBase(RotateBitmap rotateBitmap, boolean z) {
        super.setImageRotateBitmapResetBase(rotateBitmap, z);
    }

    public /* bridge */ /* synthetic */ void setRecycler(ImageViewTouchBase.Recycler recycler) {
        super.setRecycler(recycler);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mBitmapDisplayed.getBitmap() != null) {
            Iterator<HighlightView> it = this.mHighlightViews.iterator();
            while (it.hasNext()) {
                HighlightView next = it.next();
                next.mMatrix.set(getImageMatrix());
                next.invalidate();
                if (next.mIsFocused) {
                    centerBasedOnHighlightView(next);
                }
            }
        }
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3) {
        super.zoomTo(f, f2, f3);
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.mMatrix.set(getImageMatrix());
            next.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void zoomIn() {
        super.zoomIn();
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.mMatrix.set(getImageMatrix());
            next.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void zoomOut() {
        super.zoomOut();
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.mMatrix.set(getImageMatrix());
            next.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void postTranslate(float f, float f2) {
        super.postTranslate(f, f2);
        for (int i = 0; i < this.mHighlightViews.size(); i++) {
            HighlightView highlightView = this.mHighlightViews.get(i);
            highlightView.mMatrix.postTranslate(f, f2);
            highlightView.invalidate();
        }
    }

    private void recomputeFocus(MotionEvent motionEvent) {
        int i = 0;
        for (int i2 = 0; i2 < this.mHighlightViews.size(); i2++) {
            HighlightView highlightView = this.mHighlightViews.get(i2);
            highlightView.setFocus(false);
            highlightView.invalidate();
        }
        while (true) {
            if (i >= this.mHighlightViews.size()) {
                break;
            }
            HighlightView highlightView2 = this.mHighlightViews.get(i);
            if (highlightView2.getHit(motionEvent.getX(), motionEvent.getY()) == 1) {
                i++;
            } else if (!highlightView2.hasFocus()) {
                highlightView2.setFocus(true);
                highlightView2.invalidate();
            }
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        HighlightView.ModifyMode modifyMode;
        CropImage cropImage = (CropImage) this.mContext;
        int i = 0;
        if (cropImage.mSaving) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (cropImage.mWaitingToPick) {
                    for (int i2 = 0; i2 < this.mHighlightViews.size(); i2++) {
                        HighlightView highlightView = this.mHighlightViews.get(i2);
                        if (highlightView.hasFocus()) {
                            cropImage.mCrop = highlightView;
                            for (int i3 = 0; i3 < this.mHighlightViews.size(); i3++) {
                                if (i3 != i2) {
                                    this.mHighlightViews.get(i3).setHidden(true);
                                }
                            }
                            centerBasedOnHighlightView(highlightView);
                            ((CropImage) this.mContext).mWaitingToPick = false;
                            return true;
                        }
                    }
                } else {
                    HighlightView highlightView2 = this.mMotionHighlightView;
                    if (highlightView2 != null) {
                        centerBasedOnHighlightView(highlightView2);
                        this.mMotionHighlightView.setMode(HighlightView.ModifyMode.None);
                    }
                }
                this.mMotionHighlightView = null;
            } else if (action == 2) {
                if (cropImage.mWaitingToPick) {
                    recomputeFocus(motionEvent);
                } else {
                    HighlightView highlightView3 = this.mMotionHighlightView;
                    if (highlightView3 != null) {
                        highlightView3.handleMotion(this.mMotionEdge, motionEvent.getX() - this.mLastX, motionEvent.getY() - this.mLastY);
                        this.mLastX = motionEvent.getX();
                        this.mLastY = motionEvent.getY();
                        ensureVisible(this.mMotionHighlightView);
                    }
                }
            }
        } else if (cropImage.mWaitingToPick) {
            recomputeFocus(motionEvent);
        } else {
            while (true) {
                if (i >= this.mHighlightViews.size()) {
                    break;
                }
                HighlightView highlightView4 = this.mHighlightViews.get(i);
                int hit = highlightView4.getHit(motionEvent.getX(), motionEvent.getY());
                if (hit != 1) {
                    this.mMotionEdge = hit;
                    this.mMotionHighlightView = highlightView4;
                    this.mLastX = motionEvent.getX();
                    this.mLastY = motionEvent.getY();
                    HighlightView highlightView5 = this.mMotionHighlightView;
                    if (hit == 32) {
                        modifyMode = HighlightView.ModifyMode.Move;
                    } else {
                        modifyMode = HighlightView.ModifyMode.Grow;
                    }
                    highlightView5.setMode(modifyMode);
                } else {
                    i++;
                }
            }
        }
        int action2 = motionEvent.getAction();
        if (action2 == 1) {
            center(true, true);
        } else if (action2 == 2 && getScale() == 1.0f) {
            center(true, true);
        }
        return true;
    }

    private void ensureVisible(HighlightView highlightView) {
        Rect rect = highlightView.mDrawRect;
        int max = Math.max(0, this.mLeft - rect.left);
        int min = Math.min(0, this.mRight - rect.right);
        int max2 = Math.max(0, this.mTop - rect.top);
        int min2 = Math.min(0, this.mBottom - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 == 0) {
            max2 = min2;
        }
        if (max != 0 || max2 != 0) {
            panBy((float) max, (float) max2);
        }
    }

    private void centerBasedOnHighlightView(HighlightView highlightView) {
        Rect rect = highlightView.mDrawRect;
        float max = Math.max(1.0f, Math.min((((float) getWidth()) / ((float) rect.width())) * 0.6f, (((float) getHeight()) / ((float) rect.height())) * 0.6f) * getScale());
        if (((double) (Math.abs(max - getScale()) / max)) > 0.1d) {
            float[] fArr = {highlightView.mCropRect.centerX(), highlightView.mCropRect.centerY()};
            getImageMatrix().mapPoints(fArr);
            zoomTo(max, fArr[0], fArr[1], 300.0f);
        }
        ensureVisible(highlightView);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mHighlightViews.size(); i++) {
            this.mHighlightViews.get(i).draw(canvas);
        }
    }

    public void add(HighlightView highlightView) {
        this.mHighlightViews.add(highlightView);
        invalidate();
    }
}
