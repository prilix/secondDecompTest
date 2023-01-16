package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class DialogAddressUpdateBindingImpl extends DialogAddressUpdateBinding {
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
        sparseIntArray.put(R.id.text_view_enter_address_details, 1);
        sparseIntArray.put(R.id.text_view_indicates_mandatory_field, 2);
        sparseIntArray.put(R.id.guideline113, 3);
        sparseIntArray.put(R.id.guideline114, 4);
        sparseIntArray.put(R.id.guideline115, 5);
        sparseIntArray.put(R.id.guideline116, 6);
        sparseIntArray.put(R.id.scrollView3, 7);
        sparseIntArray.put(R.id.guideline117, 8);
        sparseIntArray.put(R.id.guideline118, 9);
        sparseIntArray.put(R.id.edit_text_address_line_1, 10);
        sparseIntArray.put(R.id.edit_text_street_area, 11);
        sparseIntArray.put(R.id.edit_text_city, 12);
        sparseIntArray.put(R.id.edit_text_state, 13);
        sparseIntArray.put(R.id.edit_text_zip_code, 14);
        sparseIntArray.put(R.id.address_line_bubble_layout, 15);
        sparseIntArray.put(R.id.text_view_address_line_one_error, 16);
        sparseIntArray.put(R.id.enter_street_area_bubble_layout, 17);
        sparseIntArray.put(R.id.text_view_street_area_error, 18);
        sparseIntArray.put(R.id.enter_city_bubble_layout, 19);
        sparseIntArray.put(R.id.text_view_city_error, 20);
        sparseIntArray.put(R.id.enter_state_bubble_layout, 21);
        sparseIntArray.put(R.id.text_view_state_error, 22);
        sparseIntArray.put(R.id.enter_zip_code_bubble_layout, 23);
        sparseIntArray.put(R.id.text_view_zip_code_error, 24);
        sparseIntArray.put(R.id.guideline192, 25);
        sparseIntArray.put(R.id.guideline194, 26);
        sparseIntArray.put(R.id.button_cancel, 27);
        sparseIntArray.put(R.id.button_positive, 28);
    }

    public DialogAddressUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private DialogAddressUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[15], objArr[27], objArr[28], objArr[10], objArr[12], objArr[13], objArr[11], objArr[14], objArr[19], objArr[21], objArr[17], objArr[23], objArr[3], objArr[4], objArr[5], objArr[6], objArr[8], objArr[9], objArr[25], objArr[26], objArr[7], objArr[16], objArr[20], objArr[1], objArr[2], objArr[22], objArr[18], objArr[24]);
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
