package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class RecyclerViewAppSettingsBindingImpl extends RecyclerViewAppSettingsBinding {
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
        sparseIntArray.put(R.id.text_view_setting_type, 1);
        sparseIntArray.put(R.id.text_view_setting_info, 2);
        sparseIntArray.put(R.id.containerLayout, 3);
        sparseIntArray.put(R.id.image_view_right_swipe, 4);
        sparseIntArray.put(R.id.settings_toggle_amplitude, 5);
        sparseIntArray.put(R.id.view35, 6);
    }

    public RecyclerViewAppSettingsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private RecyclerViewAppSettingsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[4], objArr[0], objArr[5], objArr[2], objArr[1], objArr[6]);
        this.mDirtyFlags = -1;
        this.layoutAppSettings.setTag((Object) null);
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
