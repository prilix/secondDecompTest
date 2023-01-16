package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class WeeklyTimerCopyVdBindingImpl extends WeeklyTimerCopyVdBinding {
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
        sparseIntArray.put(R.id.constarint1, 1);
        sparseIntArray.put(R.id.text_view_copy_from, 2);
        sparseIntArray.put(R.id.layout_device_name, 3);
        sparseIntArray.put(R.id.spinner_device_name, 4);
        sparseIntArray.put(R.id.image_view_arrow_down, 5);
        sparseIntArray.put(R.id.guideline295, 6);
        sparseIntArray.put(R.id.guideline296, 7);
        sparseIntArray.put(R.id.layout_bottom, 8);
        sparseIntArray.put(R.id.guideline195, 9);
        sparseIntArray.put(R.id.guideline196, 10);
        sparseIntArray.put(R.id.text_view_apply_to, 11);
        sparseIntArray.put(R.id.text_view_all_devices_title_home, 12);
        sparseIntArray.put(R.id.frame, 13);
        sparseIntArray.put(R.id.check_box_all_air_conditioners, 14);
        sparseIntArray.put(R.id.iv_partial, 15);
        sparseIntArray.put(R.id.view3, 16);
        sparseIntArray.put(R.id.guideline299, 17);
        sparseIntArray.put(R.id.guideline300, 18);
        sparseIntArray.put(R.id.recycler_view_devices, 19);
    }

    public WeeklyTimerCopyVdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private WeeklyTimerCopyVdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[14], objArr[1], objArr[13], objArr[9], objArr[10], objArr[6], objArr[7], objArr[17], objArr[18], objArr[5], objArr[15], objArr[8], objArr[3], objArr[19], objArr[4], objArr[12], objArr[11], objArr[2], objArr[16]);
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
