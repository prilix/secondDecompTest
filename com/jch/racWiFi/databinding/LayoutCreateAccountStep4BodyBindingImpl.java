package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutCreateAccountStep4BodyBindingImpl extends LayoutCreateAccountStep4BodyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ScrollView mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(11);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_create_account_step_4_zipcode_alert_msg"}, new int[]{2}, new int[]{R.layout.layout_create_account_step_4_zipcode_alert_msg});
        includedLayouts.setIncludes(1, new String[]{"layout_create_account_step_4_sub_body"}, new int[]{3}, new int[]{R.layout.layout_create_account_step_4_sub_body});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_view_step_4_of_4, 4);
        sparseIntArray.put(R.id.text_view_indicates_mandatory_field, 5);
        sparseIntArray.put(R.id.text_view_enter_address_details, 6);
        sparseIntArray.put(R.id.guideline1, 7);
        sparseIntArray.put(R.id.guideline2, 8);
        sparseIntArray.put(R.id.guideline3, 9);
        sparseIntArray.put(R.id.guideline4, 10);
    }

    public LayoutCreateAccountStep4BodyBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LayoutCreateAccountStep4BodyBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[7], objArr[8], objArr[9], objArr[10], objArr[3], objArr[2], objArr[6], objArr[5], objArr[4]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.includeSubBody);
        setContainedBinding(this.includeZipCode);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ScrollView scrollView = objArr[1];
        this.mboundView1 = scrollView;
        scrollView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        this.includeZipCode.invalidateAll();
        this.includeSubBody.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.includeSubBody.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.includeZipCode.hasPendingBindings() == false) goto L_0x0016;
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
            com.jch.racWiFi.databinding.LayoutCreateAccountStep4ZipcodeAlertMsgBinding r0 = r6.includeZipCode
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            com.jch.racWiFi.databinding.LayoutCreateAccountStep4SubBodyBinding r0 = r6.includeSubBody
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
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.LayoutCreateAccountStep4BodyBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.includeZipCode.setLifecycleOwner(lifecycleOwner);
        this.includeSubBody.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeIncludeSubBody((LayoutCreateAccountStep4SubBodyBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeIncludeZipCode((LayoutCreateAccountStep4ZipcodeAlertMsgBinding) obj, i2);
    }

    private boolean onChangeIncludeSubBody(LayoutCreateAccountStep4SubBodyBinding layoutCreateAccountStep4SubBodyBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeIncludeZipCode(LayoutCreateAccountStep4ZipcodeAlertMsgBinding layoutCreateAccountStep4ZipcodeAlertMsgBinding, int i) {
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
        executeBindingsOn(this.includeZipCode);
        executeBindingsOn(this.includeSubBody);
    }
}
