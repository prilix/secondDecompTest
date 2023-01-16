package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class ManageUsersVdBinding extends ViewDataBinding {
    public final Guideline guideline172;
    public final Guideline guideline174;
    public final Guideline guideline175;
    public final Guideline guideline176;
    public final Guideline guideline177;
    public final Guideline guideline21;
    public final Guideline guideline22;
    public final ImageView imageViewManagePermissions;
    public final LinearLayout layoutAddMembersManageUsers;
    public final LinearLayout layoutManagePermissions;
    public final RecyclerView recyclerViewMangeUsers;
    public final TextView textViewAddMembersManageUsers;

    protected ManageUsersVdBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.guideline172 = guideline;
        this.guideline174 = guideline2;
        this.guideline175 = guideline3;
        this.guideline176 = guideline4;
        this.guideline177 = guideline5;
        this.guideline21 = guideline6;
        this.guideline22 = guideline7;
        this.imageViewManagePermissions = imageView;
        this.layoutAddMembersManageUsers = linearLayout;
        this.layoutManagePermissions = linearLayout2;
        this.recyclerViewMangeUsers = recyclerView;
        this.textViewAddMembersManageUsers = textView;
    }

    public static ManageUsersVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ManageUsersVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_users_vd, viewGroup, z, obj);
    }

    public static ManageUsersVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ManageUsersVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.manage_users_vd, (ViewGroup) null, false, obj);
    }

    public static ManageUsersVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ManageUsersVdBinding bind(View view, Object obj) {
        return (ManageUsersVdBinding) bind(obj, view, R.layout.manage_users_vd);
    }
}
