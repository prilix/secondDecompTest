package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class HomePageUpdatedVdBindingImpl extends HomePageUpdatedVdBinding {
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
        sparseIntArray.put(R.id.pullToRefresh, 1);
        sparseIntArray.put(R.id.guideline124, 2);
        sparseIntArray.put(R.id.guideline125, 3);
        sparseIntArray.put(R.id.guideline126, 4);
        sparseIntArray.put(R.id.guideline127, 5);
        sparseIntArray.put(R.id.guideline128, 6);
        sparseIntArray.put(R.id.constraintLayout3, 7);
        sparseIntArray.put(R.id.guideline129, 8);
        sparseIntArray.put(R.id.guideline130, 9);
        sparseIntArray.put(R.id.guideline131, 10);
        sparseIntArray.put(R.id.image_button_menu_home, 11);
        sparseIntArray.put(R.id.layout_notification, 12);
        sparseIntArray.put(R.id.image_button_notification, 13);
        sparseIntArray.put(R.id.text_view_notification_count, 14);
        sparseIntArray.put(R.id.image_view_profile_home, 15);
        sparseIntArray.put(R.id.layout_name_home, 16);
        sparseIntArray.put(R.id.text_view_user_name_home_blank, 17);
        sparseIntArray.put(R.id.text_view_home, 18);
        sparseIntArray.put(R.id.image_view_drop_down, 19);
        sparseIntArray.put(R.id.guideline148, 20);
        sparseIntArray.put(R.id.guideline158, 21);
        sparseIntArray.put(R.id.guideline160, 22);
        sparseIntArray.put(R.id.guideline138, 23);
        sparseIntArray.put(R.id.guideline142, 24);
        sparseIntArray.put(R.id.image_view_sun, 25);
        sparseIntArray.put(R.id.text_view_area_temprature, 26);
        sparseIntArray.put(R.id.text_view_area_temprature_unit, 27);
        sparseIntArray.put(R.id.text_view_weather_type, 28);
        sparseIntArray.put(R.id.text_view_area_name, 29);
        sparseIntArray.put(R.id.iv_refresh_button, 30);
        sparseIntArray.put(R.id.text_view_last_updated_on, 31);
        sparseIntArray.put(R.id.home_page_banner_layout, 32);
        sparseIntArray.put(R.id.layout_add_members_button, 33);
        sparseIntArray.put(R.id.image_button_close_add_members_layout, 34);
        sparseIntArray.put(R.id.text_view_add_members, 35);
        sparseIntArray.put(R.id.guideline30, 36);
        sparseIntArray.put(R.id.layout_add_devices, 37);
        sparseIntArray.put(R.id.image_button_close_add_devices_layout, 38);
        sparseIntArray.put(R.id.text_view_add_devices, 39);
        sparseIntArray.put(R.id.guideline31, 40);
        sparseIntArray.put(R.id.text_view_no_devices_found, 41);
        sparseIntArray.put(R.id.all_devices_switch_layout, 42);
        sparseIntArray.put(R.id.text_view_all_devices_title_home, 43);
        sparseIntArray.put(R.id.sb_stop_all, 44);
        sparseIntArray.put(R.id.rv_detailed_idulist, 45);
        sparseIntArray.put(R.id.guideline140, 46);
        sparseIntArray.put(R.id.guideline141, 47);
        sparseIntArray.put(R.id.parent, 48);
        sparseIntArray.put(R.id.layout_dialogs, 49);
    }

    public HomePageUpdatedVdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 50, sIncludes, sViewsWithIds));
    }

    private HomePageUpdatedVdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[42], objArr[7], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[8], objArr[9], objArr[10], objArr[23], objArr[46], objArr[47], objArr[24], objArr[20], objArr[21], objArr[22], objArr[36], objArr[40], objArr[32], objArr[38], objArr[34], objArr[11], objArr[13], objArr[19], objArr[15], objArr[25], objArr[30], objArr[37], objArr[33], objArr[49], objArr[16], objArr[12], objArr[0], objArr[48], objArr[1], objArr[45], objArr[44], objArr[39], objArr[35], objArr[43], objArr[29], objArr[26], objArr[27], objArr[18], objArr[31], objArr[41], objArr[14], objArr[17], objArr[28]);
        this.mDirtyFlags = -1;
        this.outer.setTag((Object) null);
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
