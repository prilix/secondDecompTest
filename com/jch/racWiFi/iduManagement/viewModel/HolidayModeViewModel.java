package com.jch.racWiFi.iduManagement.viewModel;

import android.content.Context;
import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.FragmentToActivityCallback;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.network.HolidayModeNetworkDispatcher;
import com.jch.racWiFi.iduManagement.view.HolidayModeFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HolidayModeViewModel extends C0534ViewModel {
    private FragmentToActivityCallback fragmentToActivityCallback;
    private SingleLiveEvent<HolidayModeModel.HolidayModeFetchResponse> holidayModeDataV2SingleLiveEvent = new SingleLiveEvent<>();
    /* access modifiers changed from: private */
    public SingleLiveEvent<HolidayModeModel.HolidayModeInterruptResponse> holidayModeInterruptResponseSingleLiveEvent = new SingleLiveEvent<>();
    private List<HolidayModeModel> holidayModeModelList = new ArrayList();
    private SingleLiveEvent<HolidayModeModel.HolidayModeUpdateResponseDataV2> holidayModeSuccessResponceSingleLiveEvent = new SingleLiveEvent<>();
    private List<HolidayModeModel.HolidayModeResponseData> saveLastScheduledOfHolidayMode = new ArrayList();

    public void init(FragmentToActivityCallback fragmentToActivityCallback2) {
        this.fragmentToActivityCallback = fragmentToActivityCallback2;
    }

    public void setHolidayModeModelList(IduList iduList) {
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            RacModelWiseConfigurationList racModelWiseConfigurationList = this.fragmentToActivityCallback.getRacModelWiseConfigurationList();
            if (racModelWiseConfigurationList.containsRacConfiguration(detailedIduModel.cloudId)) {
                if (racModelWiseConfigurationList.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId).getEnableOptions().getEnableOption1().getHolidayModeLeaveHome()) {
                    this.holidayModeModelList.add(new HolidayModeModel(detailedIduModel, true));
                } else {
                    this.holidayModeModelList.add(new HolidayModeModel(detailedIduModel, false));
                }
            }
        }
    }

    public List<HolidayModeModel> getHolidayModeModelList() {
        return this.holidayModeModelList;
    }

    public void getHolidayModeData(Context context) {
        new HolidayModeNetworkDispatcher().getHolidayMode().observeForever(new HolidayModeViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$getHolidayModeData$0$com-jch-racWiFi-iduManagement-viewModel-HolidayModeViewModel */
    public /* synthetic */ void mo31150xef296a7c(HolidayModeModel.HolidayModeFetchResponse holidayModeFetchResponse) {
        this.holidayModeDataV2SingleLiveEvent.setValue(holidayModeFetchResponse);
    }

    public void requestForUpdateHolidayMode(HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2) {
        new HolidayModeNetworkDispatcher().updateHolidayMode(holidayModeRequestDataV2).observeForever(new HolidayModeViewModel$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$requestForUpdateHolidayMode$1$com-jch-racWiFi-iduManagement-viewModel-HolidayModeViewModel */
    public /* synthetic */ void mo31151x9853ccc(HolidayModeModel.HolidayModeUpdateResponseDataV2 holidayModeUpdateResponseDataV2) {
        this.holidayModeSuccessResponceSingleLiveEvent.postValue(holidayModeUpdateResponseDataV2);
    }

    public void interrputToHolidayMode(DetailedIduModel detailedIduModel) {
        new HolidayModeNetworkDispatcher().interrputToHolidayMode(new HolidayModeModel.InterruptHolidayMode(detailedIduModel.f454id.intValue())).observeForever(new Observer<HolidayModeModel.HolidayModeInterruptResponse>() {
            public void onChanged(HolidayModeModel.HolidayModeInterruptResponse holidayModeInterruptResponse) {
                HolidayModeViewModel.this.holidayModeInterruptResponseSingleLiveEvent.setValue(holidayModeInterruptResponse);
            }
        });
    }

    public void selectAllRacs() {
        for (HolidayModeModel holidayModeModel : this.holidayModeModelList) {
            holidayModeModel.isSelected = true;
        }
    }

    public void deSelectAllRacs() {
        for (HolidayModeModel holidayModeModel : this.holidayModeModelList) {
            holidayModeModel.isSelected = false;
        }
    }

    public boolean isValidate() {
        return this.holidayModeModelList.size() > 0;
    }

    public void setHolidayModeResponseData(HolidayModeModel.HolidayModeResponseData[] holidayModeResponseDataArr) {
        this.saveLastScheduledOfHolidayMode.clear();
        if (holidayModeResponseDataArr.length > 0) {
            for (HolidayModeModel.HolidayModeResponseData copy : holidayModeResponseDataArr) {
                HolidayModeModel.HolidayModeResponseData holidayModeResponseData = new HolidayModeModel.HolidayModeResponseData();
                holidayModeResponseData.copy(copy);
                this.saveLastScheduledOfHolidayMode.add(holidayModeResponseData);
            }
        }
    }

    public List<HolidayModeModel.HolidayModeResponseData> getSaveLastScheduledOfHolidayMode() {
        return this.saveLastScheduledOfHolidayMode;
    }

    public void setHolidayModeDataInAdapter() {
        deSelectAllRacs();
        for (HolidayModeModel next : getHolidayModeModelList()) {
            if (Constants.IS_DEMO_MODE) {
                for (HolidayModeModel.HolidayModeResponseData next2 : getSaveLastScheduledOfHolidayMode()) {
                    if (next.racId == next2.racId && next2.scheduleTypes.equalsIgnoreCase(HolidayModeFragment.HolidayMode.HOLIDAY_MODE_ENABLED.name())) {
                        next.isSelected = true;
                    }
                }
            } else if (next.isHolidayModeSupport) {
                for (HolidayModeModel.HolidayModeResponseData next3 : getSaveLastScheduledOfHolidayMode()) {
                    if (next.racId == next3.racId && next3.scheduleTypes.equalsIgnoreCase(HolidayModeFragment.HolidayMode.HOLIDAY_MODE_ENABLED.name())) {
                        next.isSelected = true;
                    }
                }
            }
        }
    }

    public boolean isHolidayModeDataExist() {
        List<HolidayModeModel.HolidayModeResponseData> list = this.saveLastScheduledOfHolidayMode;
        return list != null && list.size() > 0;
    }

    public boolean checkIfAtleastOneItemSelected() {
        for (HolidayModeModel next : getHolidayModeModelList()) {
            if (Constants.IS_DEMO_MODE) {
                if (next.isSelected) {
                    return true;
                }
            } else if (next.isHolidayModeSupport && next.isSelected) {
                return true;
            }
        }
        return false;
    }

    public SingleLiveEvent<HolidayModeModel.HolidayModeFetchResponse> getHolidayModeDataV2SingleLiveEvent() {
        return this.holidayModeDataV2SingleLiveEvent;
    }

    public SingleLiveEvent<HolidayModeModel.HolidayModeUpdateResponseDataV2> updateHolidayModeSinglLiveEvent() {
        return this.holidayModeSuccessResponceSingleLiveEvent;
    }

    public SingleLiveEvent<HolidayModeModel.HolidayModeInterruptResponse> interruptHolidayModeSinglLiveEvent() {
        return this.holidayModeInterruptResponseSingleLiveEvent;
    }

    public boolean isCheckedStatusChanged() {
        for (HolidayModeModel next : getHolidayModeModelList()) {
            if (next.isSelected != ifItemExist(next.racId)) {
                return false;
            }
        }
        return true;
    }

    private boolean ifItemExist(int i) {
        for (HolidayModeModel.HolidayModeResponseData holidayModeResponseData : getSaveLastScheduledOfHolidayMode()) {
            if (holidayModeResponseData.racId == i) {
                return true;
            }
        }
        return false;
    }
}
