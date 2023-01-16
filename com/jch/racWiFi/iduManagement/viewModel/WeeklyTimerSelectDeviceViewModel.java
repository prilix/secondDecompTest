package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.IduAccess.network.IduAccessNetworkHelper;
import com.jch.racWiFi.iduManagement.model.ScheduleCount;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerCopyScheduleDispatcher;

public class WeeklyTimerSelectDeviceViewModel extends C0534ViewModel {
    private MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ScheduleCount.ScheduleCountResponse> scheduleCountMutableLiveData = new MutableLiveData<>();

    public void getWeeklyTimerScheduleCount() {
        new WeeklyTimerCopyScheduleDispatcher().weeklyTimerScheduleCount().observeForever(new WeeklyTimerSelectDeviceViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$getWeeklyTimerScheduleCount$0$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerSelectDeviceViewModel */
    public /* synthetic */ void mo31253x10457040(ScheduleCount.ScheduleCountResponse scheduleCountResponse) {
        this.scheduleCountMutableLiveData.setValue(scheduleCountResponse);
    }

    public MutableLiveData<ScheduleCount.ScheduleCountResponse> getScheduleCountMutableLiveData() {
        return this.scheduleCountMutableLiveData;
    }

    public void onCopyScheduleClicked() {
        this.mutableLiveData.setValue(true);
    }

    public MutableLiveData<Boolean> copyFromRacToRacs() {
        return this.mutableLiveData;
    }

    public void checkPermission() {
        IduAccessNetworkHelper.getInstance().fetchIduAccess();
    }
}
