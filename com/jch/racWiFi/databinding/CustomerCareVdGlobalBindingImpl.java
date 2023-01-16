package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class CustomerCareVdGlobalBindingImpl extends CustomerCareVdGlobalBinding {
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
        sparseIntArray.put(R.id.text_view_customer_care_title, 3);
        sparseIntArray.put(R.id.guideline139, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline151, 6);
        sparseIntArray.put(R.id.guideline232, 7);
        sparseIntArray.put(R.id.guideline233, 8);
        sparseIntArray.put(R.id.guideline234, 9);
        sparseIntArray.put(R.id.text_view_customer_care_sub_title1, 10);
        sparseIntArray.put(R.id.containerLayout, 11);
        sparseIntArray.put(R.id.view, 12);
        sparseIntArray.put(R.id.text_view_customer_care_sub_title_three, 13);
        sparseIntArray.put(R.id.layout_device_name, 14);
        sparseIntArray.put(R.id.text_view_selected_device_name, 15);
        sparseIntArray.put(R.id.image_view_arrow_down, 16);
        sparseIntArray.put(R.id.text_view_subtitle_four, 17);
        sparseIntArray.put(R.id.text_view_vendor_thing_id, 18);
        sparseIntArray.put(R.id.layout_bottom, 19);
        sparseIntArray.put(R.id.tv_customer_care_sub_title2, 20);
        sparseIntArray.put(R.id.guideline235, 21);
        sparseIntArray.put(R.id.guideline236, 22);
        sparseIntArray.put(R.id.button_customer_care_portal, 23);
        sparseIntArray.put(R.id.constraintLayout12, 24);
        sparseIntArray.put(R.id.view8, 25);
        sparseIntArray.put(R.id.tv_or, 26);
        sparseIntArray.put(R.id.view9, 27);
        sparseIntArray.put(R.id.button_email_customer_care, 28);
        sparseIntArray.put(R.id.view11, 29);
        sparseIntArray.put(R.id.text_view_or, 30);
        sparseIntArray.put(R.id.view12, 31);
        sparseIntArray.put(R.id.text_view_customer_care_help_line, 32);
        sparseIntArray.put(R.id.guideline154, 33);
        sparseIntArray.put(R.id.text_view_working_hours_title, 34);
        sparseIntArray.put(R.id.guideline159, 35);
        sparseIntArray.put(R.id.constraintLayout11, 36);
        sparseIntArray.put(R.id.text_view_working_hours_value, 37);
        sparseIntArray.put(R.id.guideline155, 38);
        sparseIntArray.put(R.id.button_call_customer_care, 39);
        sparseIntArray.put(R.id.layout_bottom_global_link, 40);
        sparseIntArray.put(R.id.tv_customer_care_sub_title3, 41);
        sparseIntArray.put(R.id.button_global_link, 42);
        sparseIntArray.put(R.id.parent, 43);
        sparseIntArray.put(R.id.layout_europe_region, 44);
        sparseIntArray.put(R.id.text_view_customer_care_desc_europe, 45);
        sparseIntArray.put(R.id.text_view_customer_care_sub_title2, 46);
        sparseIntArray.put(R.id.button_customer_care_portal_europe, 47);
        sparseIntArray.put(R.id.guideline156, 48);
        sparseIntArray.put(R.id.guideline157, 49);
    }

    public CustomerCareVdGlobalBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 50, sIncludes, sViewsWithIds));
    }

    private CustomerCareVdGlobalBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[39], objArr[23], objArr[47], objArr[28], objArr[42], objArr[36], objArr[24], objArr[11], objArr[4], objArr[5], objArr[6], objArr[33], objArr[38], objArr[48], objArr[49], objArr[35], objArr[7], objArr[8], objArr[9], objArr[21], objArr[22], objArr[2], objArr[16], objArr[19], objArr[40], objArr[14], objArr[44], objArr[1], objArr[43], objArr[45], objArr[32], objArr[10], objArr[46], objArr[13], objArr[3], objArr[30], objArr[15], objArr[17], objArr[18], objArr[34], objArr[37], objArr[20], objArr[41], objArr[26], objArr[12], objArr[29], objArr[31], objArr[25], objArr[27]);
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
