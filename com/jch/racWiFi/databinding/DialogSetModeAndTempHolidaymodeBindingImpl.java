package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class DialogSetModeAndTempHolidaymodeBindingImpl extends DialogSetModeAndTempHolidaymodeBinding {
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
        sparseIntArray.put(R.id.text_view_confirm_dialog_title, 1);
        sparseIntArray.put(R.id.layout_temperature, 2);
        sparseIntArray.put(R.id.image_button_increase_temprature, 3);
        sparseIntArray.put(R.id.text_view_temprature, 4);
        sparseIntArray.put(R.id.image_button_decrease_temprature, 5);
        sparseIntArray.put(R.id.text_view_temprature_unit, 6);
        sparseIntArray.put(R.id.button_negative, 7);
        sparseIntArray.put(R.id.button_positive, 8);
        sparseIntArray.put(R.id.view53, 9);
    }

    public DialogSetModeAndTempHolidaymodeBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private DialogSetModeAndTempHolidaymodeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[8], objArr[5], objArr[3], objArr[2], objArr[1], objArr[4], objArr[6], objArr[9]);
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
