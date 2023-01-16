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

public abstract class BannerRacOfflineBinding extends ViewDataBinding {
    public final Guideline guideline206;
    public final Guideline guideline207;
    public final Guideline guideline209;
    public final Guideline guideline89;
    public final Guideline guideline91;
    public final ImageButton imageButtonClear;
    public final ImageView imageViewGenericNotificationImage;
    public final ConstraintLayout layoutDesc;
    public final ConstraintLayout layoutNotification;
    public final TextView textViewGenericNotificationDesc;
    public final TextView textViewGenericNotificationTitle;
    public final TextView textViewOnOffTimer;
    public final TextView textViewRacOfflineDescTwo;
    public final TextView textViewRoomTempAlert;
    public final TextView textViewSmartFence;
    public final TextView textViewViewHideDetails;
    public final TextView textViewWeeklyTimer;
    public final View view56;
    public final View view58;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BannerRacOfflineBinding(Object obj, View view, int i, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, ImageButton imageButton, ImageView imageView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2, View view3) {
        super(obj, view, i);
        this.guideline206 = guideline;
        this.guideline207 = guideline2;
        this.guideline209 = guideline3;
        this.guideline89 = guideline4;
        this.guideline91 = guideline5;
        this.imageButtonClear = imageButton;
        this.imageViewGenericNotificationImage = imageView;
        this.layoutDesc = constraintLayout;
        this.layoutNotification = constraintLayout2;
        this.textViewGenericNotificationDesc = textView;
        this.textViewGenericNotificationTitle = textView2;
        this.textViewOnOffTimer = textView3;
        this.textViewRacOfflineDescTwo = textView4;
        this.textViewRoomTempAlert = textView5;
        this.textViewSmartFence = textView6;
        this.textViewViewHideDetails = textView7;
        this.textViewWeeklyTimer = textView8;
        this.view56 = view2;
        this.view58 = view3;
    }

    public static BannerRacOfflineBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerRacOfflineBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BannerRacOfflineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_rac_offline, viewGroup, z, obj);
    }

    public static BannerRacOfflineBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerRacOfflineBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BannerRacOfflineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.banner_rac_offline, (ViewGroup) null, false, obj);
    }

    public static BannerRacOfflineBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BannerRacOfflineBinding bind(View view, Object obj) {
        return (BannerRacOfflineBinding) bind(obj, view, R.layout.banner_rac_offline);
    }
}
