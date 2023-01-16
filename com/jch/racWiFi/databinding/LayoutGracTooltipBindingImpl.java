package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutGracTooltipBindingImpl extends LayoutGracTooltipBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.constraintLayout1, 1);
        sparseIntArray.put(R.id.constraintLayout4, 2);
        sparseIntArray.put(R.id.textView1, 3);
        sparseIntArray.put(R.id.textView11, 4);
        sparseIntArray.put(R.id.textView2, 5);
        sparseIntArray.put(R.id.textView21, 6);
        sparseIntArray.put(R.id.constraintLayout, 7);
        sparseIntArray.put(R.id.textView3, 8);
        sparseIntArray.put(R.id.textView31, 9);
        sparseIntArray.put(R.id.imageView1, 10);
        sparseIntArray.put(R.id.textView4, 11);
        sparseIntArray.put(R.id.constraintLayout2, 12);
        sparseIntArray.put(R.id.constraintLayout5, 13);
        sparseIntArray.put(R.id.textView5, 14);
        sparseIntArray.put(R.id.textView55, 15);
        sparseIntArray.put(R.id.textView6, 16);
        sparseIntArray.put(R.id.textView61, 17);
        sparseIntArray.put(R.id.constraintLayout3, 18);
        sparseIntArray.put(R.id.textView7, 19);
        sparseIntArray.put(R.id.textView71, 20);
        sparseIntArray.put(R.id.imageView2, 21);
        sparseIntArray.put(R.id.textView8, 22);
        sparseIntArray.put(R.id.textView9, 23);
    }

    public LayoutGracTooltipBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private LayoutGracTooltipBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[1], objArr[12], objArr[18], objArr[2], objArr[13], objArr[10], objArr[21], objArr[3], objArr[4], objArr[5], objArr[6], objArr[8], objArr[9], objArr[11], objArr[14], objArr[15], objArr[16], objArr[17], objArr[19], objArr[20], objArr[22], objArr[23]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
