package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragementEnergyConsuptionBudgetSetupBindingImpl extends FragementEnergyConsuptionBudgetSetupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(27);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"layout_energy_budget"}, new int[]{8}, new int[]{R.layout.layout_energy_budget});
        includedLayouts.setIncludes(2, new String[]{"layout_simple_flat_rate"}, new int[]{5}, new int[]{R.layout.layout_simple_flat_rate});
        includedLayouts.setIncludes(3, new String[]{"layout_peak_hours_and_month"}, new int[]{6}, new int[]{R.layout.layout_peak_hours_and_month});
        includedLayouts.setIncludes(4, new String[]{"layout_progressive_unit_bill"}, new int[]{7}, new int[]{R.layout.layout_progressive_unit_bill});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_top, 9);
        sparseIntArray.put(R.id.back_button, 10);
        sparseIntArray.put(R.id.text_view_energy_consuption_title, 11);
        sparseIntArray.put(R.id.text_view_save, 12);
        sparseIntArray.put(R.id.guideline150, 13);
        sparseIntArray.put(R.id.guideline151, 14);
        sparseIntArray.put(R.id.layout_energy_cost, 15);
        sparseIntArray.put(R.id.text_view_energy_cost_title, 16);
        sparseIntArray.put(R.id.text_view_select_currency, 17);
        sparseIntArray.put(R.id.layout_select_currency, 18);
        sparseIntArray.put(R.id.text_view_currency_code, 19);
        sparseIntArray.put(R.id.text_view_currency_name, 20);
        sparseIntArray.put(R.id.image_view_drop_down_currency, 21);
        sparseIntArray.put(R.id.text_view_select_biling_structure, 22);
        sparseIntArray.put(R.id.layout_select_billing_structure, 23);
        sparseIntArray.put(R.id.text_view_selected_rate, 24);
        sparseIntArray.put(R.id.image_view_drop_down_rate, 25);
        sparseIntArray.put(R.id.layout_include, 26);
    }

    public FragementEnergyConsuptionBudgetSetupBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 27, sIncludes, sViewsWithIds));
    }

    private FragementEnergyConsuptionBudgetSetupBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[10], objArr[13], objArr[14], objArr[21], objArr[25], objArr[15], objArr[3], objArr[26], objArr[8], objArr[7], objArr[6], objArr[5], objArr[4], objArr[23], objArr[18], objArr[2], objArr[9], objArr[19], objArr[20], objArr[11], objArr[16], objArr[12], objArr[22], objArr[17], objArr[24]);
        this.mDirtyFlags = -1;
        this.layoutHoursAndMonthUnit.setTag((Object) null);
        setContainedBinding(this.layoutIncludeEnergyBudget);
        setContainedBinding(this.layoutIncludeProgressiveUnitBill);
        setContainedBinding(this.layoutIncludeSelectPeakHoursMonths);
        setContainedBinding(this.layoutIncludeUnitPrice);
        this.layoutProgressiveUnit.setTag((Object) null);
        this.layoutSimpleFlatUnit.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ConstraintLayout constraintLayout2 = objArr[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.layoutIncludeUnitPrice.invalidateAll();
        this.layoutIncludeSelectPeakHoursMonths.invalidateAll();
        this.layoutIncludeProgressiveUnitBill.invalidateAll();
        this.layoutIncludeEnergyBudget.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.layoutIncludeSelectPeakHoursMonths.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r6.layoutIncludeProgressiveUnitBill.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r6.layoutIncludeEnergyBudget.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.layoutIncludeUnitPrice.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0033 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            com.jch.racWiFi.databinding.LayoutSimpleFlatRateBinding r0 = r6.layoutIncludeUnitPrice
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            com.jch.racWiFi.databinding.LayoutPeakHoursAndMonthBinding r0 = r6.layoutIncludeSelectPeakHoursMonths
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            com.jch.racWiFi.databinding.LayoutProgressiveUnitBillBinding r0 = r6.layoutIncludeProgressiveUnitBill
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r4
        L_0x0028:
            com.jch.racWiFi.databinding.LayoutEnergyBudgetBinding r0 = r6.layoutIncludeEnergyBudget
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r4
        L_0x0031:
            r0 = 0
            return r0
        L_0x0033:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragementEnergyConsuptionBudgetSetupBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.layoutIncludeUnitPrice.setLifecycleOwner(lifecycleOwner);
        this.layoutIncludeSelectPeakHoursMonths.setLifecycleOwner(lifecycleOwner);
        this.layoutIncludeProgressiveUnitBill.setLifecycleOwner(lifecycleOwner);
        this.layoutIncludeEnergyBudget.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeLayoutIncludeSelectPeakHoursMonths((LayoutPeakHoursAndMonthBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeLayoutIncludeEnergyBudget((LayoutEnergyBudgetBinding) obj, i2);
        }
        if (i == 2) {
            return onChangeLayoutIncludeUnitPrice((LayoutSimpleFlatRateBinding) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeLayoutIncludeProgressiveUnitBill((LayoutProgressiveUnitBillBinding) obj, i2);
    }

    private boolean onChangeLayoutIncludeSelectPeakHoursMonths(LayoutPeakHoursAndMonthBinding layoutPeakHoursAndMonthBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLayoutIncludeEnergyBudget(LayoutEnergyBudgetBinding layoutEnergyBudgetBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLayoutIncludeUnitPrice(LayoutSimpleFlatRateBinding layoutSimpleFlatRateBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLayoutIncludeProgressiveUnitBill(LayoutProgressiveUnitBillBinding layoutProgressiveUnitBillBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.layoutIncludeUnitPrice);
        executeBindingsOn(this.layoutIncludeSelectPeakHoursMonths);
        executeBindingsOn(this.layoutIncludeProgressiveUnitBill);
        executeBindingsOn(this.layoutIncludeEnergyBudget);
    }
}
