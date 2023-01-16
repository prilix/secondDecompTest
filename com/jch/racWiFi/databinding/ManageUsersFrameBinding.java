package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ManageUsersFrameBinding extends ViewDataBinding {
    public final Guideline guideline158;
    public final Guideline guideline159;
    public final ImageButton imageButtonMenu;
    public final ManageUsersVdBinding includedLayout;
    public final LinearLayout layout1;
    public final ConstraintLayout parent;
    public final TextView textViewManageUsers;

    protected ManageUsersFrameBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, ImageButton imageButton, ManageUsersVdBinding manageUsersVdBinding, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.guideline158 = guideline;
        this.guideline159 = guideline2;
        this.imageButtonMenu = imageButton;
        this.includedLayout = manageUsersVdBinding;
        this.layout1 = linearLayout;
        this.parent = constraintLayout;
        this.textViewManageUsers = textView;
    }

    public static ManageUsersFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ManageUsersFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_users_frame, viewGroup, z, obj);
    }

    public static ManageUsersFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ManageUsersFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_users_frame, (ViewGroup) null, false, obj);
    }

    public static ManageUsersFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersFrameBinding bind(View view, Object obj) {
        return (ManageUsersFrameBinding) bind(obj, view, R.layout.manage_users_frame);
    }
}
