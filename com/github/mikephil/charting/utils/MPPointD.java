package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

public class MPPointD extends ObjectPool.Poolable {
    private static ObjectPool<MPPointD> pool;

    /* renamed from: x */
    public double f193x;

    /* renamed from: y */
    public double f194y;

    static {
        ObjectPool<MPPointD> create = ObjectPool.create(64, new MPPointD(C1030Utils.DOUBLE_EPSILON, C1030Utils.DOUBLE_EPSILON));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    public static MPPointD getInstance(double d, double d2) {
        MPPointD mPPointD = pool.get();
        mPPointD.f193x = d;
        mPPointD.f194y = d2;
        return mPPointD;
    }

    public static void recycleInstance(MPPointD mPPointD) {
        pool.recycle(mPPointD);
    }

    public static void recycleInstances(List<MPPointD> list) {
        pool.recycle(list);
    }

    /* access modifiers changed from: protected */
    public ObjectPool.Poolable instantiate() {
        return new MPPointD(C1030Utils.DOUBLE_EPSILON, C1030Utils.DOUBLE_EPSILON);
    }

    private MPPointD(double d, double d2) {
        this.f193x = d;
        this.f194y = d2;
    }

    public String toString() {
        return "MPPointD, x: " + this.f193x + ", y: " + this.f194y;
    }
}
