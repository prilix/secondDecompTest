package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class NotificationSectionBinding extends ViewDataBinding {
    public final ImageView imageViewRightSwipe;
    public final ConstraintLayout notificationSection;
    public final TextView notificationSectionClearAll;
    public final TextView notificationSectionTitle;
    public final RecyclerView recyclerView;
    public final TextView textViewNoNotification;

    protected NotificationSectionBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, RecyclerView recyclerView2, TextView textView3) {
        super(obj, view, i);
        this.imageViewRightSwipe = imageView;
        this.notificationSection = constraintLayout;
        this.notificationSectionClearAll = textView;
        this.notificationSectionTitle = textView2;
        this.recyclerView = recyclerView2;
        this.textViewNoNotification = textView3;
    }

    public static NotificationSectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationSectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (NotificationSectionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_section, viewGroup, z, obj);
    }

    public static NotificationSectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationSectionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NotificationSectionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_section, (ViewGroup) null, false, obj);
    }

    public static NotificationSectionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotificationSectionBinding bind(View view, Object obj) {
        return (NotificationSectionBinding) bind(obj, view, R.layout.notification_section);
    }
}
