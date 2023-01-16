package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class WeeklyTimerGlobalVdBindingImpl extends WeeklyTimerGlobalVdBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.layout_settings_weekly_timer, 2);
        sparseIntArray.put(R.id.text_view_weekly_timer_title_small, 3);
        sparseIntArray.put(R.id.switch_weekly_timer, 4);
        sparseIntArray.put(R.id.root_layout_vd, 5);
        sparseIntArray.put(R.id.view3, 6);
        sparseIntArray.put(R.id.layout_days, 7);
        sparseIntArray.put(R.id.guideline195, 8);
        sparseIntArray.put(R.id.guideline196, 9);
        sparseIntArray.put(R.id.daily_weekly_button_view, 10);
        sparseIntArray.put(R.id.radio_button_mon, 11);
        sparseIntArray.put(R.id.radio_button_tue, 12);
        sparseIntArray.put(R.id.radio_button_wed, 13);
        sparseIntArray.put(R.id.radio_button_thu, 14);
        sparseIntArray.put(R.id.radio_button_fri, 15);
        sparseIntArray.put(R.id.radio_button_sat, 16);
        sparseIntArray.put(R.id.radio_button_sun, 17);
        sparseIntArray.put(R.id.layout_check_boxes, 18);
        sparseIntArray.put(R.id.guideline199, 19);
        sparseIntArray.put(R.id.guideline198, 20);
        sparseIntArray.put(R.id.check_box_mon, 21);
        sparseIntArray.put(R.id.check_box_tue, 22);
        sparseIntArray.put(R.id.check_box_wed, 23);
        sparseIntArray.put(R.id.check_box_thu, 24);
        sparseIntArray.put(R.id.check_box_fri, 25);
        sparseIntArray.put(R.id.check_box_sat, 26);
        sparseIntArray.put(R.id.check_box_sun, 27);
        sparseIntArray.put(R.id.view2, 28);
        sparseIntArray.put(R.id.view_1, 29);
        sparseIntArray.put(R.id.layout_no_schedules_available, 30);
        sparseIntArray.put(R.id.image_view_no_schedules_available, 31);
        sparseIntArray.put(R.id.text_view_no_schedules_available_title, 32);
        sparseIntArray.put(R.id.text_view_no_schedules_available_sub_title, 33);
        sparseIntArray.put(R.id.scroll_view, 34);
        sparseIntArray.put(R.id.root_layout_for_dynamic_list, 35);
        sparseIntArray.put(R.id.text_view_add_item_weekly_timer, 36);
        sparseIntArray.put(R.id.guideline194, 37);
        sparseIntArray.put(R.id.guideline197, 38);
    }

    public WeeklyTimerGlobalVdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 39, sIncludes, sViewsWithIds));
    }

    private WeeklyTimerGlobalVdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[25], objArr[21], objArr[26], objArr[27], objArr[24], objArr[22], objArr[23], objArr[10], objArr[37], objArr[8], objArr[9], objArr[38], objArr[20], objArr[19], objArr[31], objArr[18], objArr[7], objArr[30], objArr[2], objArr[1], objArr[15], objArr[11], objArr[16], objArr[17], objArr[14], objArr[12], objArr[13], objArr[35], objArr[5], objArr[34], objArr[4], objArr[36], objArr[33], objArr[32], objArr[3], objArr[29], objArr[28], objArr[6], objArr[0]);
        this.mDirtyFlags = -1;
        this.weeklyTimerOuterLayout.setTag((Object) null);
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
