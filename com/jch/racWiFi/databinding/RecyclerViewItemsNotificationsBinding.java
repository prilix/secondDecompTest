package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class RecyclerViewItemsNotificationsBinding extends ViewDataBinding {
    public final Guideline guideline202;
    public final Guideline guideline203;
    public final Guideline guideline87;
    public final ImageButton imageButtonClearNotification;
    public final ImageView imageViewNotificationType;
    public final ConstraintLayout layoutNotification;
    public final TextView textViewNotificationDetail;
    public final TextView textViewNotoficationDate;
    public final TextView textViewNoyificationType;
    public final View view56;

    protected RecyclerViewItemsNotificationsBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageButton imageButton, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.guideline202 = guideline;
        this.guideline203 = guideline2;
        this.guideline87 = guideline3;
        this.imageButtonClearNotification = imageButton;
        this.imageViewNotificationType = imageView;
        this.layoutNotification = constraintLayout;
        this.textViewNotificationDetail = textView;
        this.textViewNotoficationDate = textView2;
        this.textViewNoyificationType = textView3;
        this.view56 = view2;
    }

    public static RecyclerViewItemsNotificationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsNotificationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RecyclerViewItemsNotificationsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_notifications, viewGroup, z, obj);
    }

    public static RecyclerViewItemsNotificationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsNotificationsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RecyclerViewItemsNotificationsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recycler_view_items_notifications, (ViewGroup) null, false, obj);
    }

    public static RecyclerViewItemsNotificationsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RecyclerViewItemsNotificationsBinding bind(View view, Object obj) {
        return (RecyclerViewItemsNotificationsBinding) bind(obj, view, R.layout.recycler_view_items_notifications);
    }
}
