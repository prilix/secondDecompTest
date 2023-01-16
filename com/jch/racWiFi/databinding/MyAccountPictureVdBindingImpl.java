package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountPictureVdBindingImpl extends MyAccountPictureVdBinding {
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
        sparseIntArray.put(R.id.guideline202, 1);
        sparseIntArray.put(R.id.text_view_change_profile_picture, 2);
        sparseIntArray.put(R.id.image_view_profile_picture_my_account, 3);
        sparseIntArray.put(R.id.image_view_camera, 4);
        sparseIntArray.put(R.id.guideline213, 5);
        sparseIntArray.put(R.id.guideline214, 6);
        sparseIntArray.put(R.id.image_view_gallery, 7);
        sparseIntArray.put(R.id.image_view_remove_profile_picture, 8);
        sparseIntArray.put(R.id.guideline87, 9);
        sparseIntArray.put(R.id.guideline88, 10);
        sparseIntArray.put(R.id.guideline89, 11);
        sparseIntArray.put(R.id.guideline108, 12);
        sparseIntArray.put(R.id.guideline111, 13);
        sparseIntArray.put(R.id.guideline114, 14);
        sparseIntArray.put(R.id.guideline112, 15);
        sparseIntArray.put(R.id.guideline118, 16);
    }

    public MyAccountPictureVdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private MyAccountPictureVdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[12], objArr[13], objArr[15], objArr[14], objArr[16], objArr[1], objArr[5], objArr[6], objArr[9], objArr[10], objArr[11], objArr[4], objArr[7], objArr[3], objArr[8], objArr[2]);
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
