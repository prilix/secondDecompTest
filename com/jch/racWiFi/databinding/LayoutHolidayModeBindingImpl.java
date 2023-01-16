package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.iduManagement.viewModel.HolidayModeViewModel;
import com.jch_hitachi.aircloudglobal.R;

public class LayoutHolidayModeBindingImpl extends LayoutHolidayModeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_top, 1);
        sparseIntArray.put(R.id.image_button_menu, 2);
        sparseIntArray.put(R.id.text_view_holiday_mode_title, 3);
        sparseIntArray.put(R.id.text_view_save, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline151, 6);
        sparseIntArray.put(R.id.guideline49, 7);
        sparseIntArray.put(R.id.guideline51, 8);
        sparseIntArray.put(R.id.text_view_holiday_mode_sub_title, 9);
        sparseIntArray.put(R.id.switch_holiday_mode, 10);
        sparseIntArray.put(R.id.layout_bottom, 11);
        sparseIntArray.put(R.id.layout_date, 12);
        sparseIntArray.put(R.id.text_view_end_date_title, 13);
        sparseIntArray.put(R.id.edit_text_end_date, 14);
        sparseIntArray.put(R.id.image_button_pick_end_date, 15);
        sparseIntArray.put(R.id.guideline68, 16);
        sparseIntArray.put(R.id.layout_set_temprature, 17);
        sparseIntArray.put(R.id.text_view_set_temprature_holiday_mode, 18);
        sparseIntArray.put(R.id.text_view_temprature_holiday_mode, 19);
        sparseIntArray.put(R.id.text_view_temprature_unit_holiday_mode, 20);
        sparseIntArray.put(R.id.image_view_right_swipe, 21);
        sparseIntArray.put(R.id.layout_apply_to, 22);
        sparseIntArray.put(R.id.text_view_apply_to, 23);
        sparseIntArray.put(R.id.guideline53, 24);
        sparseIntArray.put(R.id.guideline54, 25);
        sparseIntArray.put(R.id.layout_all_acs, 26);
        sparseIntArray.put(R.id.image_view_all_acs, 27);
        sparseIntArray.put(R.id.cb_all_devices, 28);
        sparseIntArray.put(R.id.view3, 29);
        sparseIntArray.put(R.id.recycler_view_devices, 30);
        sparseIntArray.put(R.id.parent, 31);
    }

    public LayoutHolidayModeBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 32, sIncludes, sViewsWithIds));
    }

    private LayoutHolidayModeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[28], objArr[14], objArr[5], objArr[6], objArr[7], objArr[8], objArr[24], objArr[25], objArr[16], objArr[2], objArr[15], objArr[27], objArr[21], objArr[26], objArr[22], objArr[11], objArr[12], objArr[17], objArr[1], objArr[31], objArr[30], objArr[0], objArr[10], objArr[23], objArr[13], objArr[9], objArr[3], objArr[4], objArr[18], objArr[19], objArr[20], objArr[29]);
        this.mDirtyFlags = -1;
        this.rootLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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

    public boolean setVariable(int i, Object obj) {
        if (3 != i) {
            return false;
        }
        setHolidayModeViewModel((HolidayModeViewModel) obj);
        return true;
    }

    public void setHolidayModeViewModel(HolidayModeViewModel holidayModeViewModel) {
        this.mHolidayModeViewModel = holidayModeViewModel;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
