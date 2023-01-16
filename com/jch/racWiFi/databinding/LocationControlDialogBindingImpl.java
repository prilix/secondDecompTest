package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class LocationControlDialogBindingImpl extends LocationControlDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

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
        sparseIntArray.put(R.id.constraintLayoutLocationDialog, 1);
        sparseIntArray.put(R.id.image_button_clear, 2);
        sparseIntArray.put(R.id.text_view_loaction_access, 3);
        sparseIntArray.put(R.id.image_view_loaction, 4);
        sparseIntArray.put(R.id.text_view_location_access_descOne, 5);
        sparseIntArray.put(R.id.text_view_location_access_descTwo, 6);
        sparseIntArray.put(R.id.text_view_realtime_weatherInfo, 7);
        sparseIntArray.put(R.id.text_view_scan_wifi, 8);
        sparseIntArray.put(R.id.text_view_run_operation_based_onGeoLocation, 9);
        sparseIntArray.put(R.id.location_control_note, 10);
        sparseIntArray.put(R.id.location_control_note_my_account_address, 11);
        sparseIntArray.put(R.id.button_enable_locationAccess, 12);
    }

    public LocationControlDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private LocationControlDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[12], objArr[1], objArr[2], objArr[4], objArr[10], objArr[11], objArr[3], objArr[5], objArr[6], objArr[7], objArr[9], objArr[8]);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
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
