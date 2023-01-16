package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class RecyclerViewItemsNotificationsBindingImpl extends RecyclerViewItemsNotificationsBinding {
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
        sparseIntArray.put(R.id.text_view_noyification_type, 1);
        sparseIntArray.put(R.id.image_button_clear_notification, 2);
        sparseIntArray.put(R.id.guideline87, 3);
        sparseIntArray.put(R.id.image_view_notification_type, 4);
        sparseIntArray.put(R.id.text_view_notification_detail, 5);
        sparseIntArray.put(R.id.text_view_notofication_date, 6);
        sparseIntArray.put(R.id.view56, 7);
        sparseIntArray.put(R.id.guideline202, 8);
        sparseIntArray.put(R.id.guideline203, 9);
    }

    public RecyclerViewItemsNotificationsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private RecyclerViewItemsNotificationsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[8], objArr[9], objArr[3], objArr[2], objArr[4], objArr[0], objArr[5], objArr[6], objArr[1], objArr[7]);
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
