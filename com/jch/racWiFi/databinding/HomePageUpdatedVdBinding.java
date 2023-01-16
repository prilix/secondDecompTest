package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import p011de.hdodenhof.circleimageview.CircleImageView;

public abstract class HomePageUpdatedVdBinding extends ViewDataBinding {
    public final ConstraintLayout allDevicesSwitchLayout;
    public final ConstraintLayout constraintLayout3;
    public final Guideline guideline124;
    public final Guideline guideline125;
    public final Guideline guideline126;
    public final Guideline guideline127;
    public final Guideline guideline128;
    public final Guideline guideline129;
    public final Guideline guideline130;
    public final Guideline guideline131;
    public final Guideline guideline138;
    public final Guideline guideline140;
    public final Guideline guideline141;
    public final Guideline guideline142;
    public final Guideline guideline148;
    public final Guideline guideline158;
    public final Guideline guideline160;
    public final Guideline guideline30;
    public final Guideline guideline31;
    public final FrameLayout homePageBannerLayout;
    public final ImageButton imageButtonCloseAddDevicesLayout;
    public final ImageButton imageButtonCloseAddMembersLayout;
    public final ImageButton imageButtonMenuHome;
    public final ImageButton imageButtonNotification;
    public final ImageView imageViewDropDown;
    public final CircleImageView imageViewProfileHome;
    public final ImageView imageViewSun;
    public final ImageView ivRefreshButton;
    public final ConstraintLayout layoutAddDevices;
    public final ConstraintLayout layoutAddMembersButton;
    public final FrameLayout layoutDialogs;
    public final ConstraintLayout layoutNameHome;
    public final ConstraintLayout layoutNotification;
    public final ConstraintLayout outer;
    public final ConstraintLayout parent;
    public final SwipeRefreshLayout pullToRefresh;
    public final RecyclerView rvDetailedIdulist;
    public final CustomSwitchButton sbStopAll;
    public final TextView textViewAddDevices;
    public final TextView textViewAddMembers;
    public final TextView textViewAllDevicesTitleHome;
    public final TextView textViewAreaName;
    public final TextView textViewAreaTemprature;
    public final TextView textViewAreaTempratureUnit;
    public final TextView textViewHome;
    public final TextView textViewLastUpdatedOn;
    public final TextView textViewNoDevicesFound;
    public final TextView textViewNotificationCount;
    public final TextView textViewUserNameHomeBlank;
    public final TextView textViewWeatherType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected HomePageUpdatedVdBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, Guideline guideline9, Guideline guideline10, Guideline guideline11, Guideline guideline12, Guideline guideline13, Guideline guideline14, Guideline guideline15, Guideline guideline16, Guideline guideline17, FrameLayout frameLayout, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageView imageView, CircleImageView circleImageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, FrameLayout frameLayout2, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, CustomSwitchButton customSwitchButton, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.allDevicesSwitchLayout = constraintLayout;
        this.constraintLayout3 = constraintLayout2;
        this.guideline124 = guideline;
        this.guideline125 = guideline2;
        this.guideline126 = guideline3;
        this.guideline127 = guideline4;
        this.guideline128 = guideline5;
        this.guideline129 = guideline6;
        this.guideline130 = guideline7;
        this.guideline131 = guideline8;
        this.guideline138 = guideline9;
        this.guideline140 = guideline10;
        this.guideline141 = guideline11;
        this.guideline142 = guideline12;
        this.guideline148 = guideline13;
        this.guideline158 = guideline14;
        this.guideline160 = guideline15;
        this.guideline30 = guideline16;
        this.guideline31 = guideline17;
        this.homePageBannerLayout = frameLayout;
        this.imageButtonCloseAddDevicesLayout = imageButton;
        this.imageButtonCloseAddMembersLayout = imageButton2;
        this.imageButtonMenuHome = imageButton3;
        this.imageButtonNotification = imageButton4;
        this.imageViewDropDown = imageView;
        this.imageViewProfileHome = circleImageView;
        this.imageViewSun = imageView2;
        this.ivRefreshButton = imageView3;
        this.layoutAddDevices = constraintLayout4;
        this.layoutAddMembersButton = constraintLayout5;
        this.layoutDialogs = frameLayout2;
        this.layoutNameHome = constraintLayout6;
        this.layoutNotification = constraintLayout7;
        this.outer = constraintLayout8;
        this.parent = constraintLayout9;
        this.pullToRefresh = swipeRefreshLayout;
        this.rvDetailedIdulist = recyclerView;
        this.sbStopAll = customSwitchButton;
        this.textViewAddDevices = textView;
        this.textViewAddMembers = textView2;
        this.textViewAllDevicesTitleHome = textView3;
        this.textViewAreaName = textView4;
        this.textViewAreaTemprature = textView5;
        this.textViewAreaTempratureUnit = textView6;
        this.textViewHome = textView7;
        this.textViewLastUpdatedOn = textView8;
        this.textViewNoDevicesFound = textView9;
        this.textViewNotificationCount = textView10;
        this.textViewUserNameHomeBlank = textView11;
        this.textViewWeatherType = textView12;
    }

    public static HomePageUpdatedVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomePageUpdatedVdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HomePageUpdatedVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_page_updated_vd, viewGroup, z, obj);
    }

    public static HomePageUpdatedVdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomePageUpdatedVdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HomePageUpdatedVdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_page_updated_vd, (ViewGroup) null, false, obj);
    }

    public static HomePageUpdatedVdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomePageUpdatedVdBinding bind(View view, Object obj) {
        return (HomePageUpdatedVdBinding) bind(obj, view, R.layout.home_page_updated_vd);
    }
}
