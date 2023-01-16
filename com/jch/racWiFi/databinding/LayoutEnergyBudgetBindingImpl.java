package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutEnergyBudgetBindingImpl extends LayoutEnergyBudgetBinding {
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
        sparseIntArray.put(R.id.layout_enery_budget, 1);
        sparseIntArray.put(R.id.text_view_enery_budget_title, 2);
        sparseIntArray.put(R.id.text_view_enery_budget_per_month, 3);
        sparseIntArray.put(R.id.layout_enegy_budget, 4);
        sparseIntArray.put(R.id.selectBudgetDropDown, 5);
        sparseIntArray.put(R.id.enterBudgetEditText, 6);
        sparseIntArray.put(R.id.clearBudgetIcon, 7);
        sparseIntArray.put(R.id.guideline33, 8);
        sparseIntArray.put(R.id.text_view_currency_code, 9);
        sparseIntArray.put(R.id.switch_energy_budget, 10);
    }

    public LayoutEnergyBudgetBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LayoutEnergyBudgetBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[6], objArr[8], objArr[4], objArr[1], objArr[5], objArr[10], objArr[9], objArr[3], objArr[2]);
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
