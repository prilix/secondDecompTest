package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch_hitachi.aircloudglobal.R;

public class DialogAcAlreadyAddedSameHomeBindingImpl extends DialogAcAlreadyAddedSameHomeBinding {
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
        sparseIntArray.put(R.id.text_view_ac_already_added_title, 1);
        sparseIntArray.put(R.id.text_view_ac_already_added_sub_title, 2);
        sparseIntArray.put(R.id.layout_home_onbaord, 3);
        sparseIntArray.put(R.id.image_view_user_profile_manage_user, 4);
        sparseIntArray.put(R.id.text_view_user_name_manage_user, 5);
        sparseIntArray.put(R.id.text_view_user_type_manage_user, 6);
        sparseIntArray.put(R.id.text_view_ac_already_added_sub_desc, 7);
        sparseIntArray.put(R.id.button_pair_with_another_wirelessNw, 8);
        sparseIntArray.put(R.id.button_quit_paring_process, 9);
        sparseIntArray.put(R.id.guideline241, 10);
        sparseIntArray.put(R.id.guideline242, 11);
    }

    public DialogAcAlreadyAddedSameHomeBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private DialogAcAlreadyAddedSameHomeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[8], objArr[9], objArr[10], objArr[11], objArr[4], objArr[3], objArr[7], objArr[2], objArr[1], objArr[5], objArr[6]);
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
