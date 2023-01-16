package com.jch.racWiFi.iduManagement.view;

import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import java.util.List;

public interface IHomePageView {
    void checkStopAllButton();

    void onIduListFetchingFailed();

    void onIduListsFetched(List<DetailedIduModel> list);

    void onModelTypeListAvailable(List<String> list);

    void onModelWiseRacInfoAvailable(List<RacModelWiseData> list);

    void onPermissionsUpdated();

    void onPowerCommandExecutionFailure();

    void onPowerCommandExecutionSuccess();

    void onPowerOnOffFailed(long j, String str, int i);

    void onPowerOnOffSuccessful(long j, String str);

    void onStartAllPartiallyComplete(List<StopAllUnitsModels.IndividualRacStartAllUnitResponseData> list);

    void onStartAllSuccessful();

    void onStartingAllFailed();

    void onStoppingFailed();

    void onStoppingPartiallyComplete(List<StopAllUnitsModels.IndividualRacStopAllUintResponseData> list);

    void onStoppingSuccessful();

    void unCheckStopAllButton();
}
