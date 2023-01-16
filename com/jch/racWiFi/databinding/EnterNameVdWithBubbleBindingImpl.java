package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class EnterNameVdWithBubbleBindingImpl extends EnterNameVdWithBubbleBinding {
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
        sparseIntArray.put(R.id.text_view_step_1_of_4, 1);
        sparseIntArray.put(R.id.text_view_indicates_mandatory_field, 2);
        sparseIntArray.put(R.id.text_view_enter_your_name, 3);
        sparseIntArray.put(R.id.guideline113, 4);
        sparseIntArray.put(R.id.guideline114, 5);
        sparseIntArray.put(R.id.guideline115, 6);
        sparseIntArray.put(R.id.guideline116, 7);
        sparseIntArray.put(R.id.guideline121, 8);
        sparseIntArray.put(R.id.edit_text_enter_first_name, 9);
        sparseIntArray.put(R.id.edit_text_enter_middle_name, 10);
        sparseIntArray.put(R.id.edit_text_enter_last_name, 11);
        sparseIntArray.put(R.id.button_continue, 12);
        sparseIntArray.put(R.id.guideline192, 13);
        sparseIntArray.put(R.id.enter_first_name_bubble_layout, 14);
        sparseIntArray.put(R.id.text_view_first_name_error, 15);
        sparseIntArray.put(R.id.enter_last_name_bubble_layout, 16);
        sparseIntArray.put(R.id.text_view_last_name_error, 17);
        sparseIntArray.put(R.id.guideline194, 18);
        sparseIntArray.put(R.id.guideline226, 19);
    }

    public EnterNameVdWithBubbleBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private EnterNameVdWithBubbleBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[12], objArr[9], objArr[11], objArr[10], objArr[14], objArr[16], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8], objArr[13], objArr[18], objArr[19], objArr[3], objArr[15], objArr[2], objArr[17], objArr[1]);
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
