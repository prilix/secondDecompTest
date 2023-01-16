package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class DeviceSettingsVdWithSkipBindingImpl extends DeviceSettingsVdWithSkipBinding {
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
        sparseIntArray.put(R.id.view33, 1);
        sparseIntArray.put(R.id.guideline258, 2);
        sparseIntArray.put(R.id.guideline259, 3);
        sparseIntArray.put(R.id.guideline260, 4);
        sparseIntArray.put(R.id.guideline261, 5);
        sparseIntArray.put(R.id.view46, 6);
        sparseIntArray.put(R.id.view47, 7);
        sparseIntArray.put(R.id.view48, 8);
        sparseIntArray.put(R.id.view49, 9);
        sparseIntArray.put(R.id.layout_device_id_edit_device_configuration, 10);
        sparseIntArray.put(R.id.text_view_device_id_title_edit_device_configuration, 11);
        sparseIntArray.put(R.id.tv_idu_device_id, 12);
        sparseIntArray.put(R.id.layout_device_name_edit_device_configuration, 13);
        sparseIntArray.put(R.id.image_view_right_swipe_device_name, 14);
        sparseIntArray.put(R.id.text_view_device_name_title_edit_device_configuration, 15);
        sparseIntArray.put(R.id.tv_device_name, 16);
        sparseIntArray.put(R.id.manageUser, 17);
        sparseIntArray.put(R.id.image_view_right_swipe_manage_owners, 18);
        sparseIntArray.put(R.id.text_view_user_permissions_edit_device_configuration1, 19);
        sparseIntArray.put(R.id.cl_user_permissions, 20);
        sparseIntArray.put(R.id.image_view_right_swipe_user_permissions, 21);
        sparseIntArray.put(R.id.text_view_user_permissions_edit_device_configuration2, 22);
        sparseIntArray.put(R.id.layout_remove_device, 23);
        sparseIntArray.put(R.id.guideline90, 24);
        sparseIntArray.put(R.id.guideline91, 25);
    }

    public DeviceSettingsVdWithSkipBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private DeviceSettingsVdWithSkipBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[20], objArr[2], objArr[3], objArr[4], objArr[5], objArr[24], objArr[25], objArr[14], objArr[18], objArr[21], objArr[10], objArr[13], objArr[23], objArr[17], objArr[11], objArr[15], objArr[19], objArr[22], objArr[16], objArr[12], objArr[1], objArr[6], objArr[7], objArr[8], objArr[9]);
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
