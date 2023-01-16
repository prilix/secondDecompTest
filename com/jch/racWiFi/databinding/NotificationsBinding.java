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
import com.jch_hitachi.aircloudglobal.R;

public abstract class NotificationsBinding extends ViewDataBinding {
    public final Guideline guideline201;
    public final Guideline guideline90;
    public final Guideline guideline93;
    public final ImageButton imageButtonClearNotificationMain;
    public final ImageView imageViewArrowDown;
    public final ImageView imageViewFilter;
    public final ConstraintLayout layoutAllNotifications;
    public final ConstraintLayout parent;
    public final RecyclerView recyclerViewNotifications;
    public final TextView textViewAllNotifications;
    public final TextView textViewNotificationTitle;

    protected NotificationsBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, RecyclerView recyclerView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.guideline201 = guideline;
        this.guideline90 = guideline2;
        this.guideline93 = guideline3;
        this.imageButtonClearNotificationMain = imageButton;
        this.imageViewArrowDown = imageView;
        this.imageViewFilter = imageView2;
        this.layoutAllNotifications = constraintLayout;
        this.parent = constraintLayout2;
        this.recyclerViewNotifications = recyclerView;
        this.textViewAllNotifications = textView;
        this.textViewNotificationTitle = textView2;
    }

    public static NotificationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (NotificationsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notifications, viewGroup, z, obj);
    }

    public static NotificationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NotificationsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notifications, (ViewGroup) null, false, obj);
    }

    public static NotificationsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationsBinding bind(View view, Object obj) {
        return (NotificationsBinding) bind(obj, view, R.layout.notifications);
    }
}
