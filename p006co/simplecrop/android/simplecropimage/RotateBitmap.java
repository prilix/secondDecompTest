package p006co.simplecrop.android.simplecropimage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.jch.racWiFi.C1662R2;

/* renamed from: co.simplecrop.android.simplecropimage.RotateBitmap */
public class RotateBitmap {
    public static final String TAG = "RotateBitmap";
    private Bitmap mBitmap;
    private int mRotation;

    public RotateBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mRotation = 0;
    }

    public RotateBitmap(Bitmap bitmap, int i) {
        this.mBitmap = bitmap;
        this.mRotation = i % C1662R2.attr.contentInsetEndWithActions;
    }

    public void setRotation(int i) {
        this.mRotation = i;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Matrix getRotateMatrix() {
        Matrix matrix = new Matrix();
        if (this.mRotation != 0) {
            matrix.preTranslate((float) (-(this.mBitmap.getWidth() / 2)), (float) (-(this.mBitmap.getHeight() / 2)));
            matrix.postRotate((float) this.mRotation);
            matrix.postTranslate((float) (getWidth() / 2), (float) (getHeight() / 2));
        }
        return matrix;
    }

    public boolean isOrientationChanged() {
        return (this.mRotation / 90) % 2 != 0;
    }

    public int getHeight() {
        if (isOrientationChanged()) {
            return this.mBitmap.getWidth();
        }
        return this.mBitmap.getHeight();
    }

    public int getWidth() {
        if (isOrientationChanged()) {
            return this.mBitmap.getHeight();
        }
        return this.mBitmap.getWidth();
    }

    public void recycle() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmap = null;
        }
    }
}
