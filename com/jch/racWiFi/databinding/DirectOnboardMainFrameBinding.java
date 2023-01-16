package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerSelectDeviceViewModel;
import com.jch_hitachi.aircloudglobal.R;

public abstract class DirectOnboardMainFrameBinding extends ViewDataBinding {
    public final TextView directOnboard;
    public final ImageButton imageButtonCopyWeeklyTimer;
    public final ImageButton imageButtonMenu;
    public final DirectOnboardMainVdBinding include;
    @Bindable
    protected WeeklyTimerSelectDeviceViewModel mWeeklyTimerSelcetDeviceViewModel;

    public abstract void setWeeklyTimerSelcetDeviceViewModel(WeeklyTimerSelectDeviceViewModel weeklyTimerSelectDeviceViewModel);

    protected DirectOnboardMainFrameBinding(Object obj, View view, int i, TextView textView, ImageButton imageButton, ImageButton imageButton2, DirectOnboardMainVdBinding directOnboardMainVdBinding) {
        super(obj, view, i);
        this.directOnboard = textView;
        this.imageButtonCopyWeeklyTimer = imageButton;
        this.imageButtonMenu = imageButton2;
        this.include = directOnboardMainVdBinding;
    }

    public WeeklyTimerSelectDeviceViewModel getWeeklyTimerSelcetDeviceViewModel() {
        return this.mWeeklyTimerSelcetDeviceViewModel;
    }

    public static DirectOnboardMainFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DirectOnboardMainFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.direct_onboard_main_frame, viewGroup, z, obj);
    }

    public static DirectOnboardMainFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainFrameBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DirectOnboardMainFrameBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.direct_onboard_main_frame, (ViewGroup) null, false, obj);
    }

    public static DirectOnboardMainFrameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectOnboardMainFrameBinding bind(View view, Object obj) {
        return (DirectOnboardMainFrameBinding) bind(obj, view, R.layout.direct_onboard_main_frame);
    }
}
