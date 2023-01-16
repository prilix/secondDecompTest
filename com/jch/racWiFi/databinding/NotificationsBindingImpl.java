package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class NotificationsBindingImpl extends NotificationsBinding {
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
        sparseIntArray.put(R.id.guideline90, 1);
        sparseIntArray.put(R.id.guideline93, 2);
        sparseIntArray.put(R.id.text_view_notification_title, 3);
        sparseIntArray.put(R.id.image_button_clear_notification_main, 4);
        sparseIntArray.put(R.id.image_view_filter, 5);
        sparseIntArray.put(R.id.layout_all_notifications, 6);
        sparseIntArray.put(R.id.text_view_all_notifications, 7);
        sparseIntArray.put(R.id.image_view_arrow_down, 8);
        sparseIntArray.put(R.id.recycler_view_notifications, 9);
        sparseIntArray.put(R.id.guideline201, 10);
    }

    public NotificationsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private NotificationsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[10], objArr[1], objArr[2], objArr[4], objArr[8], objArr[5], objArr[6], objArr[0], objArr[9], objArr[7], objArr[3]);
        this.mDirtyFlags = -1;
        this.parent.setTag((Object) null);
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
