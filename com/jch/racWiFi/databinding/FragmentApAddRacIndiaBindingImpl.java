package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentApAddRacIndiaBindingImpl extends FragmentApAddRacIndiaBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(26);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"layout_step_progress_bar", "layout_grac_tooltip"}, new int[]{3, 5}, new int[]{R.layout.layout_step_progress_bar, R.layout.layout_grac_tooltip});
        includedLayouts.setIncludes(2, new String[]{"layout_banner_error"}, new int[]{4}, new int[]{R.layout.layout_banner_error});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_top, 6);
        sparseIntArray.put(R.id.back_button, 7);
        sparseIntArray.put(R.id.forward_button, 8);
        sparseIntArray.put(R.id.text_view_add_device_title, 9);
        sparseIntArray.put(R.id.scroll_view, 10);
        sparseIntArray.put(R.id.guideline248, 11);
        sparseIntArray.put(R.id.guideline249, 12);
        sparseIntArray.put(R.id.guideline250, 13);
        sparseIntArray.put(R.id.guideline251, 14);
        sparseIntArray.put(R.id.guideline801, 15);
        sparseIntArray.put(R.id.text_view_connect_to_air_conditioner_title, 16);
        sparseIntArray.put(R.id.connect_to_ac_textview, 17);
        sparseIntArray.put(R.id.tv_ssid, 18);
        sparseIntArray.put(R.id.passwordTextInputLayout, 19);
        sparseIntArray.put(R.id.et_password_field, 20);
        sparseIntArray.put(R.id.layout_password, 21);
        sparseIntArray.put(R.id.guideline800, 22);
        sparseIntArray.put(R.id.text_view_password_title, 23);
        sparseIntArray.put(R.id.text_view_password_rac_wifi, 24);
        sparseIntArray.put(R.id.button_change_router, 25);
    }

    public FragmentApAddRacIndiaBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private FragmentApAddRacIndiaBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[7], objArr[25], objArr[17], objArr[2], objArr[20], objArr[8], objArr[11], objArr[12], objArr[13], objArr[14], objArr[22], objArr[15], objArr[5], objArr[4], objArr[3], objArr[21], objArr[6], objArr[19], objArr[10], objArr[9], objArr[16], objArr[24], objArr[23], objArr[18]);
        this.mDirtyFlags = -1;
        this.constraintLayoutError.setTag((Object) null);
        setContainedBinding(this.include);
        setContainedBinding(this.includeBannerError);
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
            this.mDirtyFlags = 8;
        }
        this.includeLinearProgressIndicator.invalidateAll();
        this.includeBannerError.invalidateAll();
        this.include.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.includeBannerError.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r6.include.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
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
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x002a }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            com.jch.racWiFi.databinding.LayoutStepProgressBarBinding r0 = r6.includeLinearProgressIndicator
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            com.jch.racWiFi.databinding.LayoutBannerErrorBinding r0 = r6.includeBannerError
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            com.jch.racWiFi.databinding.LayoutGracTooltipBinding r0 = r6.include
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r4
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragmentApAddRacIndiaBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.includeLinearProgressIndicator.setLifecycleOwner(lifecycleOwner);
        this.includeBannerError.setLifecycleOwner(lifecycleOwner);
        this.include.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeInclude((LayoutGracTooltipBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeIncludeLinearProgressIndicator((LayoutStepProgressBarBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeIncludeBannerError((LayoutBannerErrorBinding) obj, i2);
    }

    private boolean onChangeInclude(LayoutGracTooltipBinding layoutGracTooltipBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeIncludeLinearProgressIndicator(LayoutStepProgressBarBinding layoutStepProgressBarBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeIncludeBannerError(LayoutBannerErrorBinding layoutBannerErrorBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.includeLinearProgressIndicator);
        executeBindingsOn(this.includeBannerError);
        executeBindingsOn(this.include);
    }
}
