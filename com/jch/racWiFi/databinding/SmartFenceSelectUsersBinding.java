package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public abstract class SmartFenceSelectUsersBinding extends ViewDataBinding {
    public final ImageButton backButton;
    public final TriStateCheckbox cbAllDevices;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline31;
    public final Guideline guideline32;
    public final ImageButton imageButtonHelp;
    public final ImageView imageViewAllUsers;
    public final ConstraintLayout layoutAllAcs;
    public final ConstraintLayout layoutTop;
    public final RecyclerView recyclerViewDevices;
    public final ImageButton textViewSave;
    public final TextView textViewSelectUsersSubtitle;
    public final TextView textViewUsersTitle;
    public final View view3;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SmartFenceSelectUsersBinding(Object obj, View view, int i, ImageButton imageButton, TriStateCheckbox triStateCheckbox, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageButton imageButton2, ImageView imageView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, RecyclerView recyclerView, ImageButton imageButton3, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.backButton = imageButton;
        this.cbAllDevices = triStateCheckbox;
        this.guideline150 = guideline;
        this.guideline151 = guideline2;
        this.guideline31 = guideline3;
        this.guideline32 = guideline4;
        this.imageButtonHelp = imageButton2;
        this.imageViewAllUsers = imageView;
        this.layoutAllAcs = constraintLayout;
        this.layoutTop = constraintLayout2;
        this.recyclerViewDevices = recyclerView;
        this.textViewSave = imageButton3;
        this.textViewSelectUsersSubtitle = textView;
        this.textViewUsersTitle = textView2;
        this.view3 = view2;
    }

    public static SmartFenceSelectUsersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectUsersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SmartFenceSelectUsersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_select_users, viewGroup, z, obj);
    }

    public static SmartFenceSelectUsersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectUsersBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SmartFenceSelectUsersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.smart_fence_select_users, (ViewGroup) null, false, obj);
    }

    public static SmartFenceSelectUsersBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SmartFenceSelectUsersBinding bind(View view, Object obj) {
        return (SmartFenceSelectUsersBinding) bind(obj, view, R.layout.smart_fence_select_users);
    }
}
