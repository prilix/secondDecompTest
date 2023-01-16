package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SmartfenceFragmentBindingImpl extends SmartfenceFragmentBinding {
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
        sparseIntArray.put(R.id.image_button_menu, 2);
        sparseIntArray.put(R.id.text_view_room_title, 3);
        sparseIntArray.put(R.id.text_view_save, 4);
        sparseIntArray.put(R.id.guideline150, 5);
        sparseIntArray.put(R.id.guideline151, 6);
        sparseIntArray.put(R.id.allFeatureLayout, 7);
        sparseIntArray.put(R.id.scroll_view, 8);
        sparseIntArray.put(R.id.guideline31, 9);
        sparseIntArray.put(R.id.guideline32, 10);
        sparseIntArray.put(R.id.text_view_location_control_subtitle, 11);
        sparseIntArray.put(R.id.image_button_help, 12);
        sparseIntArray.put(R.id.switch_main_home, 13);
        sparseIntArray.put(R.id.layout_geofence_settings, 14);
        sparseIntArray.put(R.id.view38, 15);
        sparseIntArray.put(R.id.text_view_geofence_settings_sub_title, 16);
        sparseIntArray.put(R.id.view39, 17);
        sparseIntArray.put(R.id.image_view_right_swipe_1, 18);
        sparseIntArray.put(R.id.layout_acs, 19);
        sparseIntArray.put(R.id.view43, 20);
        sparseIntArray.put(R.id.text_view_acs, 21);
        sparseIntArray.put(R.id.text_view_all_acs, 22);
        sparseIntArray.put(R.id.view48, 23);
        sparseIntArray.put(R.id.image_view_right_swipe_3, 24);
        sparseIntArray.put(R.id.layout_users, 25);
        sparseIntArray.put(R.id.view40, 26);
        sparseIntArray.put(R.id.text_view_users, 27);
        sparseIntArray.put(R.id.text_view_all_users, 28);
        sparseIntArray.put(R.id.view41, 29);
        sparseIntArray.put(R.id.image_view_right_swipe_2, 30);
        sparseIntArray.put(R.id.guideline33, 31);
        sparseIntArray.put(R.id.guideline34, 32);
        sparseIntArray.put(R.id.layout_settings, 33);
        sparseIntArray.put(R.id.layout_arriving, 34);
        sparseIntArray.put(R.id.layout_inside_geofence_range, 35);
        sparseIntArray.put(R.id.text_view_arriving, 36);
        sparseIntArray.put(R.id.button_enabled_arrving, 37);
        sparseIntArray.put(R.id.set_Temo_mode_for_arriving_layout, 38);
        sparseIntArray.put(R.id.text_view_set_mode_and_temp_arriving, 39);
        sparseIntArray.put(R.id.modeTextViewConstraintLayoutArriving, 40);
        sparseIntArray.put(R.id.modeImageViewArriving, 41);
        sparseIntArray.put(R.id.modeTextViewArriving, 42);
        sparseIntArray.put(R.id.tempTextViewArriving, 43);
        sparseIntArray.put(R.id.textViewOffArriving, 44);
        sparseIntArray.put(R.id.layout_leaving, 45);
        sparseIntArray.put(R.id.layout_outside_geofence_range, 46);
        sparseIntArray.put(R.id.text_view_leaving, 47);
        sparseIntArray.put(R.id.button_enabled_leaving, 48);
        sparseIntArray.put(R.id.set_Temo_mode_for_leaving_layout, 49);
        sparseIntArray.put(R.id.text_view_set_mode_and_temp_leaving, 50);
        sparseIntArray.put(R.id.modeTextViewConstraintLayoutLeaving, 51);
        sparseIntArray.put(R.id.modeImageViewLeaving, 52);
        sparseIntArray.put(R.id.modeTextViewLeaving, 53);
        sparseIntArray.put(R.id.tempTextViewLeaving, 54);
        sparseIntArray.put(R.id.textViewOffLeaving, 55);
        sparseIntArray.put(R.id.layout_note, 56);
        sparseIntArray.put(R.id.textView_note_desc_one, 57);
        sparseIntArray.put(R.id.textView_note_desc_two, 58);
        sparseIntArray.put(R.id.textView_note_desc_three, 59);
        sparseIntArray.put(R.id.textView_note_desc_four, 60);
        sparseIntArray.put(R.id.layout_note_bottom, 61);
        sparseIntArray.put(R.id.textView_note_desc_one_new, 62);
        sparseIntArray.put(R.id.textView_note_desc_two_new, 63);
        sparseIntArray.put(R.id.textView_note_desc_three_new, 64);
        sparseIntArray.put(R.id.textView_note_desc_four_new, 65);
        sparseIntArray.put(R.id.textViewNoRacFound, 66);
        sparseIntArray.put(R.id.layoutAddAc, 67);
    }

    public SmartfenceFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 68, sIncludes, sViewsWithIds));
    }

    private SmartfenceFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[37], objArr[48], objArr[5], objArr[6], objArr[9], objArr[10], objArr[31], objArr[32], objArr[12], objArr[2], objArr[18], objArr[30], objArr[24], objArr[19], objArr[67], objArr[34], objArr[14], objArr[35], objArr[45], objArr[56], objArr[61], objArr[46], objArr[33], objArr[1], objArr[25], objArr[41], objArr[52], objArr[42], objArr[40], objArr[51], objArr[53], objArr[0], objArr[8], objArr[38], objArr[49], objArr[13], objArr[43], objArr[54], objArr[21], objArr[22], objArr[28], objArr[36], objArr[16], objArr[47], objArr[11], objArr[66], objArr[60], objArr[65], objArr[57], objArr[62], objArr[59], objArr[64], objArr[58], objArr[63], objArr[44], objArr[55], objArr[3], objArr[4], objArr[39], objArr[50], objArr[27], objArr[15], objArr[17], objArr[26], objArr[29], objArr[20], objArr[23]);
        this.mDirtyFlags = -1;
        this.parent.setTag((Object) null);
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
