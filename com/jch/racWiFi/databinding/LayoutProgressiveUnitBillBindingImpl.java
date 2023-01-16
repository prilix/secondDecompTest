package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutProgressiveUnitBillBindingImpl extends LayoutProgressiveUnitBillBinding {
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
        sparseIntArray.put(R.id.text_view_fixed_charges_per_month, 1);
        sparseIntArray.put(R.id.edit_text_fixed_charges, 2);
        sparseIntArray.put(R.id.text_view_currency_code, 3);
        sparseIntArray.put(R.id.guideline57, 4);
        sparseIntArray.put(R.id.layout_header, 5);
        sparseIntArray.put(R.id.text_view_from_units, 6);
        sparseIntArray.put(R.id.text_view_to_units, 7);
        sparseIntArray.put(R.id.text_view_unit_prize_kwh_fix_charges, 8);
        sparseIntArray.put(R.id.guideline48, 9);
        sparseIntArray.put(R.id.guideline50, 10);
        sparseIntArray.put(R.id.recycler_view_progressive_unit, 11);
        sparseIntArray.put(R.id.image_button_add, 12);
        sparseIntArray.put(R.id.image_button_minus, 13);
    }

    public LayoutProgressiveUnitBillBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private LayoutProgressiveUnitBillBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[9], objArr[10], objArr[4], objArr[12], objArr[13], objArr[5], objArr[11], objArr[3], objArr[1], objArr[6], objArr[7], objArr[8]);
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
