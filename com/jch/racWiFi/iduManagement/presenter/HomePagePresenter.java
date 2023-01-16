package com.jch.racWiFi.iduManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import java.util.List;

public interface HomePagePresenter {
    void checkAndUpdateStopAllSwitch(List<DetailedIduModel> list);

    void getModelWiseData(String[] strArr);

    void getRacModelTypesForFamily(int i);

    void requestAllOnOff(Boolean bool, List<DetailedIduModel> list, LifecycleOwner lifecycleOwner);

    void requestIduPowerOnOff(LifecycleOwner lifecycleOwner, DetailedIduModel detailedIduModel, String str);
}
