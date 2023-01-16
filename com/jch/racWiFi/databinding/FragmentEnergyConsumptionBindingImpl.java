package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentEnergyConsumptionBindingImpl extends FragmentEnergyConsumptionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(18);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"toolbar_energy_consumption"}, new int[]{2}, new int[]{R.layout.toolbar_energy_consumption});
        includedLayouts.setIncludes(1, new String[]{"layout_select_ac"}, new int[]{3}, new int[]{R.layout.layout_select_ac});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.selectAcDivider, 4);
        sparseIntArray.put(R.id.allAcLayout, 5);
        sparseIntArray.put(R.id.image_view_all_acs, 6);
        sparseIntArray.put(R.id.allAcLbltextView, 7);
        sparseIntArray.put(R.id.totalEnergryTextView, 8);
        sparseIntArray.put(R.id.totalCostTextView, 9);
        sparseIntArray.put(R.id.totalBugdetTextView, 10);
        sparseIntArray.put(R.id.viewDisableLayoutAllAc, 11);
        sparseIntArray.put(R.id.allAcDivider, 12);
        sparseIntArray.put(R.id.allRacListRv, 13);
        sparseIntArray.put(R.id.layoutNoRac, 14);
        sparseIntArray.put(R.id.text_view_no_devices_found, 15);
        sparseIntArray.put(R.id.text_view_clickplusbutton, 16);
        sparseIntArray.put(R.id.addRacImageButton, 17);
    }

    public FragmentEnergyConsumptionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 18, sIncludes, sViewsWithIds));
    }

    private FragmentEnergyConsumptionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[2], objArr[17], objArr[12], objArr[5], objArr[7], objArr[13], objArr[1], objArr[6], objArr[14], objArr[4], objArr[3], objArr[16], objArr[15], objArr[10], objArr[9], objArr[8], objArr[11]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.actionBarLayout);
        this.energyCostRacsContentLayout.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setContainedBinding(this.selectAcLayout);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        this.actionBarLayout.invalidateAll();
        this.selectAcLayout.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.selectAcLayout.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.actionBarLayout.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            com.jch.racWiFi.databinding.ToolbarEnergyConsumptionBinding r0 = r6.actionBarLayout
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            com.jch.racWiFi.databinding.LayoutSelectAcBinding r0 = r6.selectAcLayout
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragmentEnergyConsumptionBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.actionBarLayout.setLifecycleOwner(lifecycleOwner);
        this.selectAcLayout.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeActionBarLayout((ToolbarEnergyConsumptionBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeSelectAcLayout((LayoutSelectAcBinding) obj, i2);
    }

    private boolean onChangeActionBarLayout(ToolbarEnergyConsumptionBinding toolbarEnergyConsumptionBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSelectAcLayout(LayoutSelectAcBinding layoutSelectAcBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.actionBarLayout);
        executeBindingsOn(this.selectAcLayout);
    }
}
