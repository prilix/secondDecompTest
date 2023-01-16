package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class HelpVdGlobalAppBindingImpl extends HelpVdGlobalAppBinding {
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
        sparseIntArray.put(R.id.constarint1, 1);
        sparseIntArray.put(R.id.text_view_help_sub_title, 2);
        sparseIntArray.put(R.id.layout_device_name, 3);
        sparseIntArray.put(R.id.text_view_selected_device_name, 4);
        sparseIntArray.put(R.id.image_view_arrow_down, 5);
        sparseIntArray.put(R.id.guideline295, 6);
        sparseIntArray.put(R.id.guideline296, 7);
        sparseIntArray.put(R.id.text_view_device_title_help, 8);
        sparseIntArray.put(R.id.text_view_device_name_help_idu, 9);
        sparseIntArray.put(R.id.guideline298, 10);
        sparseIntArray.put(R.id.guideline305, 11);
        sparseIntArray.put(R.id.guideline299, 12);
        sparseIntArray.put(R.id.guideline297, 13);
        sparseIntArray.put(R.id.text_view_quick_start_guide_title, 14);
        sparseIntArray.put(R.id.image_view_pdf, 15);
        sparseIntArray.put(R.id.text_view_quick_start_guide, 16);
        sparseIntArray.put(R.id.image_view_download_user_manual, 17);
        sparseIntArray.put(R.id.view1, 18);
        sparseIntArray.put(R.id.text_view_visit, 19);
        sparseIntArray.put(R.id.text_view_user_manual, 20);
        sparseIntArray.put(R.id.view2, 21);
        sparseIntArray.put(R.id.text_view_visit_two, 22);
        sparseIntArray.put(R.id.text_view_specifications, 23);
        sparseIntArray.put(R.id.guideline158, 24);
        sparseIntArray.put(R.id.guideline159, 25);
    }

    public HelpVdGlobalAppBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private HelpVdGlobalAppBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[24], objArr[25], objArr[6], objArr[7], objArr[13], objArr[10], objArr[12], objArr[11], objArr[5], objArr[17], objArr[15], objArr[3], objArr[9], objArr[8], objArr[2], objArr[16], objArr[14], objArr[4], objArr[23], objArr[20], objArr[19], objArr[22], objArr[18], objArr[21]);
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
