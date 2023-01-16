package com.jch.racWiFi.iduManagement;

import androidx.lifecycle.MutableLiveData;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.network.TimerEnabledDispatcher;

public class TimerEnableDisable {
    private SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> timerEnabledResponseMutableLiveData = new SingleLiveEvent<>();

    public void weeklyTimerEnabledDisableOperations(long j, WeeklyTimerMode weeklyTimerMode) {
        new SingleLiveEvent();
        new TimerEnabledDispatcher().weeklyTimerOperations(j, weeklyTimerMode).observeForever(new TimerEnableDisable$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$weeklyTimerEnabledDisableOperations$0$com-jch-racWiFi-iduManagement-TimerEnableDisable */
    public /* synthetic */ void mo29513x1d55ae7c(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void timerimerEnabledDisableOperations(long j, WeeklyTimerMode weeklyTimerMode) {
        new SingleLiveEvent();
        new TimerEnabledDispatcher().timerOperations(j, weeklyTimerMode).observeForever(new TimerEnableDisable$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$timerimerEnabledDisableOperations$1$com-jch-racWiFi-iduManagement-TimerEnableDisable */
    public /* synthetic */ void mo29512x9639da49(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public MutableLiveData<TimerEnabledModel.TimerEnabledResponse> getTimerEnabledResponseMutableLiveData() {
        return this.timerEnabledResponseMutableLiveData;
    }

    public static class ScheduleType {
        @SerializedName("schedulerType")
        private String schedule;

        public ScheduleType(WeeklyTimerMode weeklyTimerMode) {
            this.schedule = weeklyTimerMode.getSchedulerTypeCommand(weeklyTimerMode);
        }

        public String getSchedule() {
            return this.schedule;
        }
    }
}
