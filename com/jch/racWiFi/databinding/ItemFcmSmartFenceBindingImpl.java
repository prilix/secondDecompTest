package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class ItemFcmSmartFenceBindingImpl extends ItemFcmSmartFenceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.image_button_clear, 1);
        sparseIntArray.put(R.id.item_fcm_smart_fence_title, 2);
        sparseIntArray.put(R.id.guideline89, 3);
        sparseIntArray.put(R.id.item_fcm_smart_fence_image, 4);
        sparseIntArray.put(R.id.item_fcm_smart_fence_desc, 5);
        sparseIntArray.put(R.id.text_view_notofication_date, 6);
        sparseIntArray.put(R.id.view56, 7);
        sparseIntArray.put(R.id.guideline206, 8);
        sparseIntArray.put(R.id.guideline207, 9);
    }

    public ItemFcmSmartFenceBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ItemFcmSmartFenceBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[8], objArr[9], objArr[3], objArr[1], objArr[5], objArr[4], objArr[2], objArr[0], objArr[6], objArr[7]);
        this.mDirtyFlags = -1;
        this.layoutNotification.setTag((Object) null);
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
