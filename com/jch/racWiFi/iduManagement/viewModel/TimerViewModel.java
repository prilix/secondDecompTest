package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.iduManagement.TimerEnableDisable;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.network.TimerDispatcher;

public class TimerViewModel extends C0534ViewModel {
    private final MutableLiveData<TimerEnabledModel.TimerEnabledResponse> timerEnabledResponseMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<TimerModels.TimerFetchResponse> timerFetchResponseMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<TimerModels.TimerUpdateResponse> timerUpdateResponseMutableLiveData = new MutableLiveData<>();

    public void changeTimer(long j, int i, String str, double d, String str2, String str3, String str4, String str5) {
        LiveData<TimerModels.TimerUpdateResponse> liveData;
        TimerDispatcher timerDispatcher = new TimerDispatcher();
        if (j == -1) {
            TimerModels.AddRequestData addRequestData = new TimerModels.AddRequestData();
            addRequestData.racId = (long) i;
            addRequestData.temperature = d;
            addRequestData.mode = str4;
            addRequestData.startsAt = str2;
            addRequestData.endsAt = str3;
            addRequestData.power = str;
            addRequestData.displayFormat = str5;
            liveData = timerDispatcher.addTimer(addRequestData);
        } else {
            TimerModels.UpdateRequestData updateRequestData = new TimerModels.UpdateRequestData();
            updateRequestData.f462id = j;
            updateRequestData.racId = (long) i;
            updateRequestData.temperature = d;
            updateRequestData.mode = str4;
            updateRequestData.startsAt = str2;
            updateRequestData.endsAt = str3;
            updateRequestData.power = str;
            updateRequestData.displayFormat = str5;
            liveData = timerDispatcher.updateTimer(updateRequestData);
        }
        liveData.observeForever(new TimerViewModel$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$changeTimer$0$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31179x62a61e66(TimerModels.TimerUpdateResponse timerUpdateResponse) {
        this.timerUpdateResponseMutableLiveData.setValue(timerUpdateResponse);
    }

    public void getTimerSchedule(int i, boolean z) {
        new TimerDispatcher().getTimerSchedule(i).observeForever(new TimerViewModel$$ExternalSyntheticLambda5(this, z));
    }

    /* renamed from: lambda$getTimerSchedule$1$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31180x2944df3a(boolean z, TimerModels.TimerFetchResponse timerFetchResponse) {
        timerFetchResponse.isFromTimerPage = z;
        this.timerFetchResponseMutableLiveData.setValue(timerFetchResponse);
    }

    public void operationTimerScheduleDisable(long j) {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.timerimerEnabledDisableOperations(j, WeeklyTimerMode.SCHEDULE_DISABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new TimerViewModel$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$operationTimerScheduleDisable$2$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31184xdb7fc6(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void operationOffTimerEnable(long j) {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.timerimerEnabledDisableOperations(j, WeeklyTimerMode.OFF_TIMER_ENABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new TimerViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$operationOffTimerEnable$3$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31181x9700b1d8(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void operationOnTimerEnable(long j) {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.timerimerEnabledDisableOperations(j, WeeklyTimerMode.ON_TIMER_ENABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new TimerViewModel$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$operationOnTimerEnable$4$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31183x9c5742bb(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void operationOnOffTimer(long j) {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.timerimerEnabledDisableOperations(j, WeeklyTimerMode.ON_OFF_TIMER_ENABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new TimerViewModel$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$operationOnOffTimer$5$com-jch-racWiFi-iduManagement-viewModel-TimerViewModel */
    public /* synthetic */ void mo31182x4fe2783a(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public MutableLiveData<TimerEnabledModel.TimerEnabledResponse> getTimerEnabledResponseMutableLiveData() {
        return this.timerEnabledResponseMutableLiveData;
    }

    public MutableLiveData<TimerModels.TimerUpdateResponse> getTimerUpdateResponseMutableLiveData() {
        return this.timerUpdateResponseMutableLiveData;
    }

    public MutableLiveData<TimerModels.TimerFetchResponse> getTimerFetchResponseMutableLiveData() {
        return this.timerFetchResponseMutableLiveData;
    }

    public int getRestTimeOfExecutionInMinute(String str) {
        String substractBetweenTwoDateTime = DateAndTimeUtils.substractBetweenTwoDateTime(str);
        return (Integer.parseInt(DateAndTimeUtils.getHourFromTimerStrings(substractBetweenTwoDateTime)) * 60) + Integer.parseInt(DateAndTimeUtils.getMinutesFromTimeStrings(substractBetweenTwoDateTime));
    }
}
