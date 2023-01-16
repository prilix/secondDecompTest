package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerScheduleSettingsViewModel;
import com.jch_hitachi.aircloudglobal.R;

public class ScheduleSettingsWeeklyTimerBindingImpl extends ScheduleSettingsWeeklyTimerBinding {
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
        sparseIntArray.put(R.id.back_button, 2);
        sparseIntArray.put(R.id.text_view_room_title, 3);
        sparseIntArray.put(R.id.text_view_save, 4);
        sparseIntArray.put(R.id.guideline158, 5);
        sparseIntArray.put(R.id.guideline159, 6);
        sparseIntArray.put(R.id.guideline139, 7);
        sparseIntArray.put(R.id.guideline149, 8);
        sparseIntArray.put(R.id.scroll_view, 9);
        sparseIntArray.put(R.id.constraintLayout, 10);
        sparseIntArray.put(R.id.guideline233, 11);
        sparseIntArray.put(R.id.guideline234, 12);
        sparseIntArray.put(R.id.text_view_schedule_settings_title, 13);
        sparseIntArray.put(R.id.switch_weekly_timer, 14);
        sparseIntArray.put(R.id.layout_3, 15);
        sparseIntArray.put(R.id.guideline200, 16);
        sparseIntArray.put(R.id.guideline201, 17);
        sparseIntArray.put(R.id.image_button_increase_temprature, 18);
        sparseIntArray.put(R.id.text_view_set_temp, 19);
        sparseIntArray.put(R.id.guideline117, 20);
        sparseIntArray.put(R.id.guideline186, 21);
        sparseIntArray.put(R.id.text_view_temprature, 22);
        sparseIntArray.put(R.id.text_view_humidity_percentage_idu_control, 23);
        sparseIntArray.put(R.id.text_view_percent, 24);
        sparseIntArray.put(R.id.image_button_decrease_temprature, 25);
        sparseIntArray.put(R.id.text_view_temprature_unit, 26);
        sparseIntArray.put(R.id.text_view_humidity_title_roomdevice_control, 27);
        sparseIntArray.put(R.id.layout_mode_room_device_control, 28);
        sparseIntArray.put(R.id.guideline187, 29);
        sparseIntArray.put(R.id.guideline188, 30);
        sparseIntArray.put(R.id.image_view_selected_mode_home, 31);
        sparseIntArray.put(R.id.text_view_selected_mode_home, 32);
        sparseIntArray.put(R.id.image_view_arrow_down_mode, 33);
        sparseIntArray.put(R.id.text_view_start_time_title, 34);
        sparseIntArray.put(R.id.layout_start_time, 35);
        sparseIntArray.put(R.id.text_view_start_time_weekty_timer, 36);
        sparseIntArray.put(R.id.image_button_pick_start_time, 37);
        sparseIntArray.put(R.id.layout_end_time, 38);
        sparseIntArray.put(R.id.text_view_end_time_weekty_timer, 39);
        sparseIntArray.put(R.id.image_button_help, 40);
        sparseIntArray.put(R.id.text_view_end_time_title, 41);
        sparseIntArray.put(R.id.help_bubble_layout_bottom, 42);
        sparseIntArray.put(R.id.text_view_help_content, 43);
        sparseIntArray.put(R.id.guideline191, 44);
        sparseIntArray.put(R.id.guideline192, 45);
        sparseIntArray.put(R.id.parent, 46);
    }

    public ScheduleSettingsWeeklyTimerBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 47, sIncludes, sViewsWithIds));
    }

    private ScheduleSettingsWeeklyTimerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[10], objArr[20], objArr[7], objArr[8], objArr[5], objArr[6], objArr[21], objArr[29], objArr[30], objArr[44], objArr[45], objArr[16], objArr[17], objArr[11], objArr[12], objArr[42], objArr[25], objArr[40], objArr[18], objArr[37], objArr[33], objArr[31], objArr[15], objArr[38], objArr[28], objArr[35], objArr[1], objArr[46], objArr[0], objArr[9], objArr[14], objArr[41], objArr[39], objArr[43], objArr[23], objArr[27], objArr[24], objArr[3], objArr[4], objArr[13], objArr[32], objArr[19], objArr[34], objArr[36], objArr[22], objArr[26]);
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
        if (9 != i) {
            return false;
        }
        setWeeklyTimerScheduleSettingsViewModel((WeeklyTimerScheduleSettingsViewModel) obj);
        return true;
    }

    public void setWeeklyTimerScheduleSettingsViewModel(WeeklyTimerScheduleSettingsViewModel weeklyTimerScheduleSettingsViewModel) {
        this.mWeeklyTimerScheduleSettingsViewModel = weeklyTimerScheduleSettingsViewModel;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
