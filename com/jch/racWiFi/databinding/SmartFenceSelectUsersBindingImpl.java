package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SmartFenceSelectUsersBindingImpl extends SmartFenceSelectUsersBinding {
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
        sparseIntArray.put(R.id.layout_top, 1);
        sparseIntArray.put(R.id.back_button, 2);
        sparseIntArray.put(R.id.text_view_users_title, 3);
        sparseIntArray.put(R.id.text_view_save, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline151, 6);
        sparseIntArray.put(R.id.guideline31, 7);
        sparseIntArray.put(R.id.guideline32, 8);
        sparseIntArray.put(R.id.text_view_select_users_subtitle, 9);
        sparseIntArray.put(R.id.image_button_help, 10);
        sparseIntArray.put(R.id.layout_all_acs, 11);
        sparseIntArray.put(R.id.image_view_all_users, 12);
        sparseIntArray.put(R.id.cb_all_devices, 13);
        sparseIntArray.put(R.id.view3, 14);
        sparseIntArray.put(R.id.recycler_view_devices, 15);
    }

    public SmartFenceSelectUsersBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private SmartFenceSelectUsersBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[13], objArr[5], objArr[6], objArr[7], objArr[8], objArr[10], objArr[12], objArr[11], objArr[1], objArr[15], objArr[4], objArr[9], objArr[3], objArr[14]);
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
