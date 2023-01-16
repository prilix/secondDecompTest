package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

public class MPPointF extends ObjectPool.Poolable {
    public static final Parcelable.Creator<MPPointF> CREATOR = new Parcelable.Creator<MPPointF>() {
        public MPPointF createFromParcel(Parcel parcel) {
            MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
            mPPointF.my_readFromParcel(parcel);
            return mPPointF;
        }

        public MPPointF[] newArray(int i) {
            return new MPPointF[i];
        }
    };
    private static ObjectPool<MPPointF> pool;

    /* renamed from: x */
    public float f195x;

    /* renamed from: y */
    public float f196y;

    static {
        ObjectPool<MPPointF> create = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    public MPPointF() {
    }

    public MPPointF(float f, float f2) {
        this.f195x = f;
        this.f196y = f2;
    }

    public static MPPointF getInstance(float f, float f2) {
        MPPointF mPPointF = pool.get();
        mPPointF.f195x = f;
        mPPointF.f196y = f2;
        return mPPointF;
    }

    public static MPPointF getInstance() {
        return pool.get();
    }

    public static MPPointF getInstance(MPPointF mPPointF) {
        MPPointF mPPointF2 = pool.get();
        mPPointF2.f195x = mPPointF.f195x;
        mPPointF2.f196y = mPPointF.f196y;
        return mPPointF2;
    }

    public static void recycleInstance(MPPointF mPPointF) {
        pool.recycle(mPPointF);
    }

    public static void recycleInstances(List<MPPointF> list) {
        pool.recycle(list);
    }

    public void my_readFromParcel(Parcel parcel) {
        this.f195x = parcel.readFloat();
        this.f196y = parcel.readFloat();
    }

    public float getX() {
        return this.f195x;
    }

    public float getY() {
        return this.f196y;
    }

    /* access modifiers changed from: protected */
    public ObjectPool.Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }
}
