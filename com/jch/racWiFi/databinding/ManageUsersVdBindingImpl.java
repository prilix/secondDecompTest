package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class ManageUsersVdBindingImpl extends ManageUsersVdBinding {
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
        sparseIntArray.put(R.id.guideline172, 1);
        sparseIntArray.put(R.id.guideline174, 2);
        sparseIntArray.put(R.id.guideline175, 3);
        sparseIntArray.put(R.id.guideline176, 4);
        sparseIntArray.put(R.id.guideline177, 5);
        sparseIntArray.put(R.id.recycler_view_mange_users, 6);
        sparseIntArray.put(R.id.layout_add_members_manage_users, 7);
        sparseIntArray.put(R.id.guideline21, 8);
        sparseIntArray.put(R.id.text_view_add_members_manage_users, 9);
        sparseIntArray.put(R.id.layout_manage_permissions, 10);
        sparseIntArray.put(R.id.guideline22, 11);
        sparseIntArray.put(R.id.image_view_manage_permissions, 12);
    }

    public ManageUsersVdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private ManageUsersVdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[8], objArr[11], objArr[12], objArr[7], objArr[10], objArr[6], objArr[9]);
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
