package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class AddDeviceStep1Of5VdBindingImpl extends AddDeviceStep1Of5VdBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(26);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_step_progress_bar"}, new int[]{1}, new int[]{R.layout.layout_step_progress_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline242, 2);
        sparseIntArray.put(R.id.guideline243, 3);
        sparseIntArray.put(R.id.guideline244, 4);
        sparseIntArray.put(R.id.guideline245, 5);
        sparseIntArray.put(R.id.guideline246, 6);
        sparseIntArray.put(R.id.guideline247, 7);
        sparseIntArray.put(R.id.text_view_sacn_qr_code_title, 8);
        sparseIntArray.put(R.id.text_view_sacn_qr_code2_built_in_sub_title, 9);
        sparseIntArray.put(R.id.qr_code_layout, 10);
        sparseIntArray.put(R.id.retry_qr_scan_layout, 11);
        sparseIntArray.put(R.id.retry_image_view, 12);
        sparseIntArray.put(R.id.retry_textview, 13);
        sparseIntArray.put(R.id.view22, 14);
        sparseIntArray.put(R.id.view21, 15);
        sparseIntArray.put(R.id.view16, 16);
        sparseIntArray.put(R.id.view12, 17);
        sparseIntArray.put(R.id.view18, 18);
        sparseIntArray.put(R.id.view17, 19);
        sparseIntArray.put(R.id.view19, 20);
        sparseIntArray.put(R.id.view15, 21);
        sparseIntArray.put(R.id.scannerID, 22);
        sparseIntArray.put(R.id.text_view_sacn_qr_code_title2, 23);
        sparseIntArray.put(R.id.button_where_to_locate_qr_code, 24);
        sparseIntArray.put(R.id.button_unable_to_scan_qr_code, 25);
    }

    public AddDeviceStep1Of5VdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private AddDeviceStep1Of5VdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[25], objArr[24], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[1], objArr[10], objArr[12], objArr[11], objArr[13], objArr[22], objArr[9], objArr[8], objArr[23], objArr[17], objArr[21], objArr[16], objArr[19], objArr[18], objArr[20], objArr[15], objArr[14]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.includeLinearProgressIndicator);
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
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.AddDeviceStep1Of5VdBindingImpl.hasPendingBindings():boolean");
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
