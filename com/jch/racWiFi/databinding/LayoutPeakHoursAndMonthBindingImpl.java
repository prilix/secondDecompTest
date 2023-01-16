package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutPeakHoursAndMonthBindingImpl extends LayoutPeakHoursAndMonthBinding {
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
        sparseIntArray.put(R.id.text_view_tap_to_selection, 1);
        sparseIntArray.put(R.id.recyler_view_peak_hours_and_month, 2);
        sparseIntArray.put(R.id.layout_view_unit_prize_inr_kwh, 3);
        sparseIntArray.put(R.id.text_view_unit_price_currency_kwh, 4);
        sparseIntArray.put(R.id.text_view_peak_hours, 5);
        sparseIntArray.put(R.id.edit_text_peak_hr_unit_price, 6);
        sparseIntArray.put(R.id.text_view_off_peak_hours, 7);
        sparseIntArray.put(R.id.edit_text_off_peak_hr_unit_price, 8);
        sparseIntArray.put(R.id.guideline47, 9);
    }

    public LayoutPeakHoursAndMonthBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LayoutPeakHoursAndMonthBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[8], objArr[6], objArr[9], objArr[3], objArr[2], objArr[7], objArr[5], objArr[1], objArr[4]);
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
