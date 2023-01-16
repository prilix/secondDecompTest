package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SmartFenceDialogSetModeTempLocationControlsBindingImpl extends SmartFenceDialogSetModeTempLocationControlsBinding {
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
        sparseIntArray.put(R.id.text_view_confirm_dialog_title, 1);
        sparseIntArray.put(R.id.image_view_aariving_leaving, 2);
        sparseIntArray.put(R.id.guideline501, 3);
        sparseIntArray.put(R.id.guideline502, 4);
        sparseIntArray.put(R.id.text_view_arriving_leaving_inside_georange, 5);
        sparseIntArray.put(R.id.layout_switch_room_device_control, 6);
        sparseIntArray.put(R.id.guideline200, 7);
        sparseIntArray.put(R.id.guideline201, 8);
        sparseIntArray.put(R.id.switch_main_home, 9);
        sparseIntArray.put(R.id.text_view_off, 10);
        sparseIntArray.put(R.id.text_view_on, 11);
        sparseIntArray.put(R.id.layout_set_temprature_room_device_control, 12);
        sparseIntArray.put(R.id.guideline73, 13);
        sparseIntArray.put(R.id.guideline400, 14);
        sparseIntArray.put(R.id.guideline403, 15);
        sparseIntArray.put(R.id.guideline401, 16);
        sparseIntArray.put(R.id.image_button_increase_temprature, 17);
        sparseIntArray.put(R.id.image_button_increase_humidity, 18);
        sparseIntArray.put(R.id.text_view_set_temp, 19);
        sparseIntArray.put(R.id.text_view_humidity_title_roomdevice_control, 20);
        sparseIntArray.put(R.id.guideline117, 21);
        sparseIntArray.put(R.id.guideline186, 22);
        sparseIntArray.put(R.id.text_view_temprature, 23);
        sparseIntArray.put(R.id.text_view_humidity_percentage_idu_control, 24);
        sparseIntArray.put(R.id.text_view_percent, 25);
        sparseIntArray.put(R.id.image_button_decrease_temprature, 26);
        sparseIntArray.put(R.id.image_button_decrease_humidity, 27);
        sparseIntArray.put(R.id.text_view_temprature_unit, 28);
        sparseIntArray.put(R.id.guideline191, 29);
        sparseIntArray.put(R.id.guideline195, 30);
        sparseIntArray.put(R.id.layout_mode_room_device_control, 31);
        sparseIntArray.put(R.id.guideline187, 32);
        sparseIntArray.put(R.id.guideline188, 33);
        sparseIntArray.put(R.id.image_view_selected_mode_home, 34);
        sparseIntArray.put(R.id.text_view_selected_mode_home, 35);
        sparseIntArray.put(R.id.image_view_arrow_down_mode, 36);
        sparseIntArray.put(R.id.selectModeButton, 37);
        sparseIntArray.put(R.id.button_negative, 38);
        sparseIntArray.put(R.id.button_positive, 39);
        sparseIntArray.put(R.id.view53, 40);
    }

    public SmartFenceDialogSetModeTempLocationControlsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 41, sIncludes, sViewsWithIds));
    }

    private SmartFenceDialogSetModeTempLocationControlsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[38], objArr[39], objArr[21], objArr[22], objArr[32], objArr[33], objArr[29], objArr[30], objArr[7], objArr[8], objArr[14], objArr[16], objArr[15], objArr[3], objArr[4], objArr[13], objArr[27], objArr[26], objArr[18], objArr[17], objArr[2], objArr[36], objArr[34], objArr[31], objArr[12], objArr[6], objArr[37], objArr[9], objArr[5], objArr[1], objArr[24], objArr[20], objArr[10], objArr[11], objArr[25], objArr[35], objArr[19], objArr[23], objArr[28], objArr[40]);
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
