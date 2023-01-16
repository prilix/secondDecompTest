package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerCopyTimerScheduleViewModel;
import com.jch_hitachi.aircloudglobal.R;

public class WeeklyTimerCopyFrameBindingImpl extends WeeklyTimerCopyFrameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(8);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"weekly_timer_copy_vd"}, new int[]{2}, new int[]{R.layout.weekly_timer_copy_vd});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.image_button_menu, 3);
        sparseIntArray.put(R.id.text_view_weekly_timer_title, 4);
        sparseIntArray.put(R.id.text_view_save, 5);
        sparseIntArray.put(R.id.guideline150, 6);
        sparseIntArray.put(R.id.guideline151, 7);
    }

    public WeeklyTimerCopyFrameBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private WeeklyTimerCopyFrameBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[6], objArr[7], objArr[3], objArr[2], objArr[0], objArr[5], objArr[4]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.include);
        LinearLayout linearLayout = objArr[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag((Object) null);
        this.rootLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
            com.jch.racWiFi.databinding.WeeklyTimerCopyVdBinding r0 = r6.include
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
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.WeeklyTimerCopyFrameBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int i, Object obj) {
        if (8 != i) {
            return false;
        }
        setWeeklyTimerCopyTimerScheduleViewModel((WeeklyTimerCopyTimerScheduleViewModel) obj);
        return true;
    }

    public void setWeeklyTimerCopyTimerScheduleViewModel(WeeklyTimerCopyTimerScheduleViewModel weeklyTimerCopyTimerScheduleViewModel) {
        this.mWeeklyTimerCopyTimerScheduleViewModel = weeklyTimerCopyTimerScheduleViewModel;
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
        return onChangeInclude((WeeklyTimerCopyVdBinding) obj, i2);
    }

    private boolean onChangeInclude(WeeklyTimerCopyVdBinding weeklyTimerCopyVdBinding, int i) {
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
