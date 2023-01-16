package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentServiceRequestBindingImpl extends FragmentServiceRequestBinding {
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
        sparseIntArray.put(R.id.layout_top, 1);
        sparseIntArray.put(R.id.image_button_menu, 2);
        sparseIntArray.put(R.id.text_view_service_request_title, 3);
        sparseIntArray.put(R.id.guideline158, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline232, 6);
        sparseIntArray.put(R.id.guideline233, 7);
        sparseIntArray.put(R.id.guideline234, 8);
        sparseIntArray.put(R.id.text_view_service_request_sub_title, 9);
        sparseIntArray.put(R.id.layout_rac_error, 10);
        sparseIntArray.put(R.id.text_view_customer_care_sub_title2, 11);
        sparseIntArray.put(R.id.guideline235, 12);
        sparseIntArray.put(R.id.guideline236, 13);
        sparseIntArray.put(R.id.view8, 14);
        sparseIntArray.put(R.id.text_view_or, 15);
        sparseIntArray.put(R.id.view9, 16);
        sparseIntArray.put(R.id.button_service_request_portal, 17);
        sparseIntArray.put(R.id.text_view_customer_care_help_line, 18);
        sparseIntArray.put(R.id.guideline154, 19);
        sparseIntArray.put(R.id.text_view_working_hours_title, 20);
        sparseIntArray.put(R.id.guideline159, 21);
        sparseIntArray.put(R.id.constraintLayout11, 22);
        sparseIntArray.put(R.id.text_view_monday_to_saturday, 23);
        sparseIntArray.put(R.id.text_view_sunday_holidays, 24);
        sparseIntArray.put(R.id.guideline155, 25);
        sparseIntArray.put(R.id.button_call_service_desk, 26);
        sparseIntArray.put(R.id.guideline156, 27);
        sparseIntArray.put(R.id.guideline157, 28);
    }

    public FragmentServiceRequestBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private FragmentServiceRequestBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[26], objArr[17], objArr[22], objArr[5], objArr[19], objArr[25], objArr[27], objArr[28], objArr[4], objArr[21], objArr[6], objArr[7], objArr[8], objArr[12], objArr[13], objArr[2], objArr[10], objArr[1], objArr[18], objArr[11], objArr[23], objArr[15], objArr[9], objArr[3], objArr[24], objArr[20], objArr[14], objArr[16]);
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
