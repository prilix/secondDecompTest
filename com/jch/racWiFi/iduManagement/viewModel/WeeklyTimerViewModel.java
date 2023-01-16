package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.iduManagement.TimerEnableDisable;
import com.jch.racWiFi.iduManagement.Weekday;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.CopyTimerScheduleModel;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerCopyScheduleDispatcher;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerDispatcherV2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class WeeklyTimerViewModel extends C0534ViewModel {
    private static SingleLiveEvent<ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData>> dayWiseFilterMutableData = new SingleLiveEvent<>();
    private static SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerFetchResponse> weeklyTimerModelsMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<CopyTimerScheduleModel.CopyTimerScheduleResponse> copyTimerScheduleResponseMutableLiveData = new SingleLiveEvent<>();
    private DetailedIduModel detailedIduModel;
    private MutableLiveData<ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData>> factoryDataSetMutableData = new MutableLiveData<>();
    private SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerRemoveResponse> removeMutableData = new SingleLiveEvent<>();
    private SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> timerEnabledResponseMutableLiveData = new SingleLiveEvent<>();

    public void init(DetailedIduModel detailedIduModel2) {
        this.detailedIduModel = detailedIduModel2;
    }

    public String getTitle() {
        return this.detailedIduModel.name;
    }

    public void getWeeklyTimerDataList() {
        new WeeklyTimerDispatcherV2().getWeeklyTimerData((long) this.detailedIduModel.f454id.intValue()).observeForever(new WeeklyTimerViewModel$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$getWeeklyTimerDataList$0$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerViewModel */
    public /* synthetic */ void mo31270xa08fc7ac(WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse) {
        generateNewDataSet(weeklyTimerFetchResponse);
        weeklyTimerModelsMutableLiveData.setValue(weeklyTimerFetchResponse);
    }

    public void copyTimerScheduleFromDayToDays(CopySchedule.DayWise dayWise) {
        new WeeklyTimerCopyScheduleDispatcher().copyDayWiseWeeklyTimerSchedule(dayWise).observeForever(new WeeklyTimerViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$copyTimerScheduleFromDayToDays$1$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerViewModel */
    public /* synthetic */ void mo31268x4d56fa4(CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse) {
        this.copyTimerScheduleResponseMutableLiveData.setValue(copyTimerScheduleResponse);
    }

    public void weeklyTimerOperationDisable() {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.weeklyTimerEnabledDisableOperations((long) this.detailedIduModel.f454id.intValue(), WeeklyTimerMode.SCHEDULE_DISABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new WeeklyTimerViewModel$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$weeklyTimerOperationDisable$2$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerViewModel */
    public /* synthetic */ void mo31271xa94e75d5(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void weeklyTimerOperationEnable() {
        TimerEnableDisable timerEnableDisable = new TimerEnableDisable();
        timerEnableDisable.weeklyTimerEnabledDisableOperations((long) this.detailedIduModel.f454id.intValue(), WeeklyTimerMode.WEEKLY_TIMER_ENABLED);
        timerEnableDisable.getTimerEnabledResponseMutableLiveData().observeForever(new WeeklyTimerViewModel$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$weeklyTimerOperationEnable$3$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerViewModel */
    public /* synthetic */ void mo31272x918988b9(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        this.timerEnabledResponseMutableLiveData.setValue(timerEnabledResponse);
    }

    public void deleteWeeklyTimer(long j, long j2) {
        new WeeklyTimerDispatcherV2().removeWeeklyTimer(j, j2).observeForever(new WeeklyTimerViewModel$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$deleteWeeklyTimer$4$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerViewModel */
    public /* synthetic */ void mo31269x8a932d1f(WeeklyTimerModelV2.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse) {
        this.removeMutableData.setValue(weeklyTimerRemoveResponse);
    }

    public boolean isScheduleStartedOnAnotherDay(WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData) {
        return Weekday.getPosition(weeklyTimerFactoryData.startingDay) != Weekday.getPosition(weeklyTimerFactoryData.day);
    }

    public boolean isMaxScheduledReached() {
        Iterator it = dayWiseFilterMutableData.getValue().iterator();
        int i = 0;
        while (it.hasNext()) {
            WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = (WeeklyTimerModelV2.WeeklyTimerFactoryData) it.next();
            if (Weekday.getPosition(weeklyTimerFactoryData.startingDay) == Weekday.getPosition(weeklyTimerFactoryData.day)) {
                i++;
            }
        }
        if (i >= 6) {
            return true;
        }
        return false;
    }

    public boolean isMaxScheduledReached(String str) {
        Iterator it = this.factoryDataSetMutableData.getValue().iterator();
        int i = 0;
        while (it.hasNext()) {
            WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = (WeeklyTimerModelV2.WeeklyTimerFactoryData) it.next();
            if (Weekday.getPosition(weeklyTimerFactoryData.day) == Weekday.getPosition(str) && Weekday.getPosition(weeklyTimerFactoryData.startingDay) == Weekday.getPosition(weeklyTimerFactoryData.day)) {
                i++;
            }
        }
        if (i >= 6) {
            return true;
        }
        return false;
    }

    private void generateNewDataSet(WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse) {
        new WeeklyTimerModelV2.MadeFactoryData((WeeklyTimerModelV2.WeeklyTimerResponseData[]) weeklyTimerFetchResponse.response, this.factoryDataSetMutableData).generateNewDataSet();
    }

    public boolean isScheduledCreatedByAddButtonForTheDay(String str) {
        if (this.factoryDataSetMutableData.getValue() == null) {
            return false;
        }
        Iterator it = this.factoryDataSetMutableData.getValue().iterator();
        while (it.hasNext()) {
            WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = (WeeklyTimerModelV2.WeeklyTimerFactoryData) it.next();
            if (weeklyTimerFactoryData.day.equalsIgnoreCase(str) && weeklyTimerFactoryData.day.equalsIgnoreCase(weeklyTimerFactoryData.startingDay)) {
                return true;
            }
        }
        return false;
    }

    public void filterDayWiseData(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (this.factoryDataSetMutableData.getValue() != null) {
            Iterator it = this.factoryDataSetMutableData.getValue().iterator();
            while (it.hasNext()) {
                WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = (WeeklyTimerModelV2.WeeklyTimerFactoryData) it.next();
                if (weeklyTimerFactoryData.day.equalsIgnoreCase(str)) {
                    if (Weekday.getPosition(weeklyTimerFactoryData.startingDay) != Weekday.getPosition(str)) {
                        arrayList.add(weeklyTimerFactoryData);
                    } else {
                        arrayList2.add(weeklyTimerFactoryData);
                    }
                }
            }
            Collections.sort(arrayList2);
            if (arrayList.size() > 0) {
                arrayList2.add(0, (WeeklyTimerModelV2.WeeklyTimerFactoryData) arrayList.get(0));
            }
        }
        dayWiseFilterMutableData.setValue(arrayList2);
    }

    public static SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerFetchResponse> getWeeklyTimerUpdatedDataItems() {
        return weeklyTimerModelsMutableLiveData;
    }

    public static SingleLiveEvent<ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData>> updatedDaywiseFilterSchedule() {
        return dayWiseFilterMutableData;
    }

    public SingleLiveEvent<CopyTimerScheduleModel.CopyTimerScheduleResponse> getCopyTimerScheduleLiveDataResponse() {
        return this.copyTimerScheduleResponseMutableLiveData;
    }

    public MutableLiveData<ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData>> getFactoryDataSet() {
        return this.factoryDataSetMutableData;
    }

    public SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerRemoveResponse> onRemoveWeeklyTimer() {
        return this.removeMutableData;
    }

    public SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> getTimerEnabledResponseMutableLiveData() {
        return this.timerEnabledResponseMutableLiveData;
    }
}
