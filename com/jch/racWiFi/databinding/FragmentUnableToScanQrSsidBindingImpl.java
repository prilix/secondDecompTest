package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentUnableToScanQrSsidBindingImpl extends FragmentUnableToScanQrSsidBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(15);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"layout_step_progress_bar"}, new int[]{2}, new int[]{R.layout.layout_step_progress_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_top, 3);
        sparseIntArray.put(R.id.back_button, 4);
        sparseIntArray.put(R.id.text_view_forgot_password_title, 5);
        sparseIntArray.put(R.id.forward_button, 6);
        sparseIntArray.put(R.id.guideline248, 7);
        sparseIntArray.put(R.id.guideline249, 8);
        sparseIntArray.put(R.id.text_view_enter_air_conditioner_ssid_title, 9);
        sparseIntArray.put(R.id.text_view_enter_air_conditioner_ssid_sub_title, 10);
        sparseIntArray.put(R.id.text_view_enter_air_conditioner_ssid, 11);
        sparseIntArray.put(R.id.edit_text_enter_ssid, 12);
        sparseIntArray.put(R.id.button_where_to_find_ssid_name, 13);
        sparseIntArray.put(R.id.parent, 14);
    }

    public FragmentUnableToScanQrSsidBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private FragmentUnableToScanQrSsidBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[4], objArr[13], objArr[12], objArr[6], objArr[7], objArr[8], objArr[2], objArr[3], objArr[14], objArr[11], objArr[10], objArr[9], objArr[5]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.includeLinearProgressIndicator);
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
            this.mDirtyFlags = 2;
        }
        this.includeLinearProgressIndicator.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.includeLinearProgressIndicator.hasPendingBindings() == false) goto L_0x0016;
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
            com.jch.racWiFi.databinding.LayoutStepProgressBarBinding r0 = r6.includeLinearProgressIndicator
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
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragmentUnableToScanQrSsidBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.includeLinearProgressIndicator.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeIncludeLinearProgressIndicator((LayoutStepProgressBarBinding) obj, i2);
    }

    private boolean onChangeIncludeLinearProgressIndicator(LayoutStepProgressBarBinding layoutStepProgressBarBinding, int i) {
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
        executeBindingsOn(this.includeLinearProgressIndicator);
    }
}
