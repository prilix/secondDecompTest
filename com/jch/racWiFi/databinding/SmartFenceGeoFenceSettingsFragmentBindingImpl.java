package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SmartFenceGeoFenceSettingsFragmentBindingImpl extends SmartFenceGeoFenceSettingsFragmentBinding {
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
        sparseIntArray.put(R.id.back_button, 2);
        sparseIntArray.put(R.id.text_view_forgot_password_title, 3);
        sparseIntArray.put(R.id.text_view_save, 4);
        sparseIntArray.put(R.id.guideline140, 5);
        sparseIntArray.put(R.id.guideline41, 6);
        sparseIntArray.put(R.id.text_view_address, 7);
        sparseIntArray.put(R.id.guideline9, 8);
        sparseIntArray.put(R.id.f789info, 9);
        sparseIntArray.put(R.id.image_view_auto_detect_help, 10);
        sparseIntArray.put(R.id.text_view_auto_dectect_desc, 11);
        sparseIntArray.put(R.id.constarint1, 12);
        sparseIntArray.put(R.id.arriving_selection_highlight, 13);
        sparseIntArray.put(R.id.leaving_selection_highlight, 14);
        sparseIntArray.put(R.id.guideline44, 15);
        sparseIntArray.put(R.id.tab_arriving, 16);
        sparseIntArray.put(R.id.image_view_arriving, 17);
        sparseIntArray.put(R.id.text_view_arriving_title, 18);
        sparseIntArray.put(R.id.text_view_arriving_km, 19);
        sparseIntArray.put(R.id.tab_leaving, 20);
        sparseIntArray.put(R.id.image_view_leaving, 21);
        sparseIntArray.put(R.id.text_view_leaving_title, 22);
        sparseIntArray.put(R.id.text_view_leaving_km, 23);
        sparseIntArray.put(R.id.layout_arriving, 24);
        sparseIntArray.put(R.id.guideline1, 25);
        sparseIntArray.put(R.id.guideline15, 26);
        sparseIntArray.put(R.id.check_box_setsmartfence_range_arriving, 27);
        sparseIntArray.put(R.id.text_view_set_geofence_range_title_arriving, 28);
        sparseIntArray.put(R.id.text_view_set_geofence_range_arriving, 29);
        sparseIntArray.put(R.id.guideline16, 30);
        sparseIntArray.put(R.id.guideline21, 31);
        sparseIntArray.put(R.id.image_button_help_arriving, 32);
        sparseIntArray.put(R.id.seekBarContainer, 33);
        sparseIntArray.put(R.id.text_view_start_range_arriving, 34);
        sparseIntArray.put(R.id.text_view_end_range_arriving, 35);
        sparseIntArray.put(R.id.layout_leaving, 36);
        sparseIntArray.put(R.id.guideline5, 37);
        sparseIntArray.put(R.id.guideline18, 38);
        sparseIntArray.put(R.id.check_box_setsmartfence_range_leaving, 39);
        sparseIntArray.put(R.id.text_view_set_geofence_range_title_leaving, 40);
        sparseIntArray.put(R.id.text_view_set_geofence_range_leaving, 41);
        sparseIntArray.put(R.id.guideline19, 42);
        sparseIntArray.put(R.id.guideline20, 43);
        sparseIntArray.put(R.id.image_button_help_leaving, 44);
        sparseIntArray.put(R.id.seekbar_leaving, 45);
        sparseIntArray.put(R.id.text_view_start_range_leaving, 46);
        sparseIntArray.put(R.id.text_view_end_range_leaving, 47);
        sparseIntArray.put(R.id.mapview_holder, 48);
        sparseIntArray.put(R.id.image_button_current_location, 49);
    }

    public SmartFenceGeoFenceSettingsFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 50, sIncludes, sViewsWithIds));
    }

    private SmartFenceGeoFenceSettingsFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[13], objArr[2], objArr[27], objArr[39], objArr[12], objArr[25], objArr[5], objArr[26], objArr[30], objArr[38], objArr[42], objArr[43], objArr[31], objArr[6], objArr[15], objArr[37], objArr[8], objArr[49], objArr[32], objArr[44], objArr[17], objArr[10], objArr[21], objArr[9], objArr[24], objArr[36], objArr[1], objArr[14], objArr[48], objArr[33], objArr[45], objArr[16], objArr[20], objArr[7], objArr[19], objArr[18], objArr[11], objArr[35], objArr[47], objArr[3], objArr[23], objArr[22], objArr[4], objArr[29], objArr[41], objArr[28], objArr[40], objArr[34], objArr[46]);
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
