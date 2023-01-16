package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.CopyTimerScheduleModel;
import com.jch.racWiFi.iduManagement.model.CopyWeeklyTimerModel;
import com.jch.racWiFi.iduManagement.model.ScheduleCount;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerCopyScheduleDispatcher;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerDispatcherV2;
import java.util.ArrayList;
import java.util.Iterator;

public class WeeklyTimerCopyTimerScheduleViewModel extends C0534ViewModel {
    private ArrayList<ScheduleCount> arrayList = new ArrayList<>();
    private MutableLiveData<ArrayList<CopyWeeklyTimerModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<CopyWeeklyTimerModel> copyTimerScheduleModel;
    private MutableLiveData<CopyTimerScheduleModel.CopyTimerScheduleResponse> copyTimerScheduleResponseMutableLiveData = new MutableLiveData<>();
    private ArrayList<CopyWeeklyTimerModel> newCopyTimerScheduleModel = new ArrayList<>();
    private ArrayList<CopyWeeklyTimerModel> spinnerArrayList = new ArrayList<>();
    private MutableLiveData<TimerEnabledModel.TimerEnabledResponse> timerEnabledResponseMutableLiveData = new MutableLiveData<>();
    private SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerFetchResponse> weeklyTimerModelsMutableLiveData = new SingleLiveEvent<>();

    public void getWeeklyTimerDataList(long j) {
        new WeeklyTimerDispatcherV2().getWeeklyTimerData(j).observeForever(new WeeklyTimerCopyTimerScheduleViewModel$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$getWeeklyTimerDataList$0$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerCopyTimerScheduleViewModel */
    public /* synthetic */ void mo31201xa261a495(WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse) {
        this.weeklyTimerModelsMutableLiveData.setValue(weeklyTimerFetchResponse);
    }

    public void init(ArrayList<CopyWeeklyTimerModel> arrayList2, ArrayList<ScheduleCount> arrayList3) {
        this.copyTimerScheduleModel = arrayList2;
        this.newCopyTimerScheduleModel.addAll(arrayList2);
        this.arrayListMutableLiveData.setValue(this.copyTimerScheduleModel);
        this.arrayList.addAll(arrayList3);
    }

    public void copyTimerScheduleToOthersRacs(CopySchedule.RacWise racWise) {
        new WeeklyTimerCopyScheduleDispatcher().copyRacWiseWeeklyTimerSchedule(racWise).observeForever(new WeeklyTimerCopyTimerScheduleViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$copyTimerScheduleToOthersRacs$1$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerCopyTimerScheduleViewModel */
    public /* synthetic */ void mo31200x39b6808a(CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse) {
        this.copyTimerScheduleResponseMutableLiveData.setValue(copyTimerScheduleResponse);
    }

    public LiveData<CopyTimerScheduleModel.CopyTimerScheduleResponse> getCopyTimerScheduleLiveDataResponse() {
        return this.copyTimerScheduleResponseMutableLiveData;
    }

    public MutableLiveData<ArrayList<CopyWeeklyTimerModel>> getRefreshedList() {
        return this.arrayListMutableLiveData;
    }

    public MutableLiveData<TimerEnabledModel.TimerEnabledResponse> getTimerEnabledResponseMutableLiveData() {
        return this.timerEnabledResponseMutableLiveData;
    }

    public SingleLiveEvent<WeeklyTimerModelV2.WeeklyTimerFetchResponse> getWeeklyTimerModelsMutableLiveData() {
        return this.weeklyTimerModelsMutableLiveData;
    }

    public void selectAllItems(boolean z) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<CopyWeeklyTimerModel> it = this.copyTimerScheduleModel.iterator();
        while (it.hasNext()) {
            CopyWeeklyTimerModel next = it.next();
            next.setSelected(z);
            arrayList2.add(next);
        }
        refreshingList(arrayList2);
    }

    public ArrayList<Long> getSelectedItems() {
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Iterator<CopyWeeklyTimerModel> it = this.copyTimerScheduleModel.iterator();
        while (it.hasNext()) {
            CopyWeeklyTimerModel next = it.next();
            if (next.isSelected()) {
                arrayList2.add(Long.valueOf((long) next.getId()));
            }
        }
        return arrayList2;
    }

    public String getRacName(long j) {
        Iterator<CopyWeeklyTimerModel> it = this.copyTimerScheduleModel.iterator();
        while (it.hasNext()) {
            CopyWeeklyTimerModel next = it.next();
            if (((long) next.getId()) == j && next.isSelected()) {
                return next.getName();
            }
        }
        return null;
    }

    public ArrayList<String> getItemForDropdown() {
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<CopyWeeklyTimerModel> it = this.copyTimerScheduleModel.iterator();
        while (it.hasNext()) {
            CopyWeeklyTimerModel next = it.next();
            if (isWeeklyTimerScheduled(next.getId())) {
                this.spinnerArrayList.add(next);
                arrayList2.add(next.getName());
            }
        }
        return arrayList2;
    }

    public void refreshListExecptSelectedItem(long j) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<CopyWeeklyTimerModel> it = this.newCopyTimerScheduleModel.iterator();
        while (it.hasNext()) {
            CopyWeeklyTimerModel next = it.next();
            if (j != ((long) next.getId())) {
                arrayList2.add(next);
            }
        }
        refreshingList(arrayList2);
    }

    private void refreshingList(ArrayList<CopyWeeklyTimerModel> arrayList2) {
        this.copyTimerScheduleModel.clear();
        this.copyTimerScheduleModel.addAll(arrayList2);
        this.arrayListMutableLiveData.setValue(this.copyTimerScheduleModel);
    }

    private boolean isWeeklyTimerScheduled(int i) {
        Iterator<ScheduleCount> it = this.arrayList.iterator();
        while (it.hasNext()) {
            ScheduleCount next = it.next();
            if (next.racId == ((long) i) && next.count > 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CopyWeeklyTimerModel> getSpinnerArrayList() {
        return this.spinnerArrayList;
    }
}
