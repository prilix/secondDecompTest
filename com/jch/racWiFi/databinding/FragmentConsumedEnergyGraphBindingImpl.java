package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentConsumedEnergyGraphBindingImpl extends FragmentConsumedEnergyGraphBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(50);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"banner_budget_consumed"}, new int[]{2}, new int[]{R.layout.banner_budget_consumed});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.actionBarLayout, 3);
        sparseIntArray.put(R.id.back_button, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline151, 6);
        sparseIntArray.put(R.id.top_layout, 7);
        sparseIntArray.put(R.id.text_view_ac_title, 8);
        sparseIntArray.put(R.id.text_view_ac_name, 9);
        sparseIntArray.put(R.id.racNameLayout, 10);
        sparseIntArray.put(R.id.racNameLebelTextView, 11);
        sparseIntArray.put(R.id.racNameTextView, 12);
        sparseIntArray.put(R.id.budgetPriceLayout, 13);
        sparseIntArray.put(R.id.unitPriceLebelTextView, 14);
        sparseIntArray.put(R.id.budgetPriceTextView, 15);
        sparseIntArray.put(R.id.totalBudgetLayout, 16);
        sparseIntArray.put(R.id.budgetAmountTextView, 17);
        sparseIntArray.put(R.id.energyUsageRadioGroup, 18);
        sparseIntArray.put(R.id.weeklyRadioBtn, 19);
        sparseIntArray.put(R.id.monthlyRadioBtn, 20);
        sparseIntArray.put(R.id.yearlyRadioBtn, 21);
        sparseIntArray.put(R.id.energyCostRadioGrp, 22);
        sparseIntArray.put(R.id.costRadioBtn, 23);
        sparseIntArray.put(R.id.energyRadioBtn, 24);
        sparseIntArray.put(R.id.guideLine8, 25);
        sparseIntArray.put(R.id.compareWithLastYrCheckBox, 26);
        sparseIntArray.put(R.id.chartLabelLayout, 27);
        sparseIntArray.put(R.id.barchart_view, 28);
        sparseIntArray.put(R.id.guideLine1, 29);
        sparseIntArray.put(R.id.tv_chart_y_axis_name, 30);
        sparseIntArray.put(R.id.tv_chart_x_axis_name, 31);
        sparseIntArray.put(R.id.lastUpdatedOnLayout, 32);
        sparseIntArray.put(R.id.guideline59, 33);
        sparseIntArray.put(R.id.guideline60, 34);
        sparseIntArray.put(R.id.availableDataDurationText, 35);
        sparseIntArray.put(R.id.monthAndYearText, 36);
        sparseIntArray.put(R.id.lastUpdatedOnHeaderTV, 37);
        sparseIntArray.put(R.id.lastUpdatedOnTextView, 38);
        sparseIntArray.put(R.id.bottomLayout, 39);
        sparseIntArray.put(R.id.guideline56, 40);
        sparseIntArray.put(R.id.guideline58, 41);
        sparseIntArray.put(R.id.lastYearBackButtonLayout, 42);
        sparseIntArray.put(R.id.leftBackArrow, 43);
        sparseIntArray.put(R.id.leftSideTextView, 44);
        sparseIntArray.put(R.id.thisYearTextLayout, 45);
        sparseIntArray.put(R.id.middleTextView, 46);
        sparseIntArray.put(R.id.nextYearBackButtonLayout, 47);
        sparseIntArray.put(R.id.rightSideTextView, 48);
        sparseIntArray.put(R.id.rightArrowIcon, 49);
    }

    public FragmentConsumedEnergyGraphBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 50, sIncludes, sViewsWithIds));
    }

    private FragmentConsumedEnergyGraphBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[3], objArr[35], objArr[4], objArr[28], objArr[39], objArr[17], objArr[13], objArr[15], objArr[27], objArr[26], objArr[23], objArr[22], objArr[24], objArr[18], objArr[29], objArr[25], objArr[5], objArr[6], objArr[40], objArr[41], objArr[33], objArr[34], objArr[2], objArr[37], objArr[32], objArr[38], objArr[42], objArr[1], objArr[43], objArr[44], objArr[46], objArr[36], objArr[20], objArr[47], objArr[10], objArr[11], objArr[12], objArr[49], objArr[48], objArr[9], objArr[8], objArr[45], objArr[7], objArr[16], objArr[31], objArr[30], objArr[14], objArr[19], objArr[21]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.include);
        this.layoutBudgetBanner.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.include.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.include.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            com.jch.racWiFi.databinding.BannerBudgetConsumedBinding r0 = r6.include
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragmentConsumedEnergyGraphBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.include.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeInclude((BannerBudgetConsumedBinding) obj, i2);
    }

    private boolean onChangeInclude(BannerBudgetConsumedBinding bannerBudgetConsumedBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.include);
    }
}
