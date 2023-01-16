package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class BannerRacOfflineBindingImpl extends BannerRacOfflineBinding {
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
        sparseIntArray.put(R.id.image_button_clear, 1);
        sparseIntArray.put(R.id.text_view_generic_notification_title, 2);
        sparseIntArray.put(R.id.guideline89, 3);
        sparseIntArray.put(R.id.image_view_generic_notification_image, 4);
        sparseIntArray.put(R.id.text_view_generic_notification_desc, 5);
        sparseIntArray.put(R.id.layout_desc, 6);
        sparseIntArray.put(R.id.guideline91, 7);
        sparseIntArray.put(R.id.text_view_on_off_timer, 8);
        sparseIntArray.put(R.id.text_view_weekly_timer, 9);
        sparseIntArray.put(R.id.text_view_smart_fence, 10);
        sparseIntArray.put(R.id.text_view_room_temp_alert, 11);
        sparseIntArray.put(R.id.text_view_rac_offline_desc_two, 12);
        sparseIntArray.put(R.id.guideline209, 13);
        sparseIntArray.put(R.id.text_view_view_hide_details, 14);
        sparseIntArray.put(R.id.view58, 15);
        sparseIntArray.put(R.id.view56, 16);
        sparseIntArray.put(R.id.guideline206, 17);
        sparseIntArray.put(R.id.guideline207, 18);
    }

    public BannerRacOfflineBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 19, sIncludes, sViewsWithIds));
    }

    private BannerRacOfflineBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[17], objArr[18], objArr[13], objArr[3], objArr[7], objArr[1], objArr[4], objArr[6], objArr[0], objArr[5], objArr[2], objArr[8], objArr[12], objArr[11], objArr[10], objArr[14], objArr[9], objArr[16], objArr[15]);
        this.mDirtyFlags = -1;
        this.layoutNotification.setTag((Object) null);
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
