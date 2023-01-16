package com.jch.racWiFi.iduManagement.presenter.presenterImpl;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.iduManagement.dto.OperationSwitchOnOffDto;
import com.jch.racWiFi.iduManagement.model.BasicIDUControls;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.CommandStatusList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.Power;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.iduManagement.network.CommandExecutionNetworkDispatcher;
import com.jch.racWiFi.iduManagement.network.IDUBasicControlsNetworkDispatcher;
import com.jch.racWiFi.iduManagement.network.IduOperationNetworkHelper;
import com.jch.racWiFi.iduManagement.network.RacModelTypeListFromFamilyFetchNetworkDispatcher;
import com.jch.racWiFi.iduManagement.network.RacModelWiseDataFetchNetworkDispatcher;
import com.jch.racWiFi.iduManagement.presenter.HomePagePresenter;
import com.jch.racWiFi.iduManagement.view.IHomePageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class HomePagePresenterImpl implements HomePagePresenter {
    /* access modifiers changed from: private */
    public IHomePageView iHomePageView;

    public HomePagePresenterImpl(IHomePageView iHomePageView2) {
        this.iHomePageView = iHomePageView2;
    }

    public void requestIduPowerOnOff(LifecycleOwner lifecycleOwner, DetailedIduModel detailedIduModel, String str) {
        Logger.m47e("__HP__", "PowerSwichOnOF Test Impl" + detailedIduModel);
        String name = (str.equals(Power.ON.name()) ? Power.OFF : Power.ON).name();
        new OperationSwitchOnOffDto().setPower(str);
        LiveData<Response<ResponseBody>> changeIduStatePower = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.ON_OFF).changeIduStatePower(detailedIduModel.f454id.intValue(), detailedIduModel);
        if (!changeIduStatePower.hasActiveObservers()) {
            changeIduStatePower.observe(lifecycleOwner, new HomePagePresenterImpl$$ExternalSyntheticLambda1(this, detailedIduModel, name, lifecycleOwner));
        }
    }

    /* renamed from: lambda$requestIduPowerOnOff$0$com-jch-racWiFi-iduManagement-presenter-presenterImpl-HomePagePresenterImpl */
    public /* synthetic */ void mo30056x2dc379ad(DetailedIduModel detailedIduModel, String str, LifecycleOwner lifecycleOwner, Response response) {
        if (response == null || !response.isSuccessful()) {
            this.iHomePageView.onPowerOnOffFailed((long) detailedIduModel.getId().intValue(), str, response.code());
            return;
        }
        CommandStatus commandStatus = (CommandStatus) new Gson().fromJson(((ResponseBody) response.body()).charStream(), CommandStatus.class);
        if (commandStatus != null) {
            commandStatus.setBasicIDUControls(BasicIDUControls.ON_OFF);
        }
        this.iHomePageView.onPowerOnOffSuccessful((long) detailedIduModel.getId().intValue(), str);
        new CommandExecutionNetworkDispatcher().pollForCmdStatus(Collections.singletonList(commandStatus)).observeSingleEvent(lifecycleOwner, new Observer<CommandStatusList>() {
            public void onChanged(CommandStatusList commandStatusList) {
                if (commandStatusList.areCommandExecuted()) {
                    HomePagePresenterImpl.this.iHomePageView.onPowerCommandExecutionSuccess();
                } else {
                    HomePagePresenterImpl.this.iHomePageView.onPowerCommandExecutionFailure();
                }
            }
        });
    }

    public void requestAllOnOff(Boolean bool, List<DetailedIduModel> list, final LifecycleOwner lifecycleOwner) {
        if (bool.booleanValue()) {
            LiveData<StopAllUnitsModels.StartAllUnitsSuccessResponse> requestAllIduStart = IduOperationNetworkHelper.getInstance().requestAllIduStart(list);
            if (!requestAllIduStart.hasActiveObservers()) {
                requestAllIduStart.observe(lifecycleOwner, new Observer<StopAllUnitsModels.StartAllUnitsSuccessResponse>() {
                    public void onChanged(final StopAllUnitsModels.StartAllUnitsSuccessResponse startAllUnitsSuccessResponse) {
                        if (startAllUnitsSuccessResponse == null) {
                            HomePagePresenterImpl.this.iHomePageView.onStoppingFailed();
                            return;
                        }
                        if (Constants.IS_DEMO_MODE) {
                            HomePagePresenterImpl.this.iHomePageView.onStartAllSuccessful();
                        }
                        final List<StopAllUnitsModels.IndividualRacStartAllUnitResponseData> list = startAllUnitsSuccessResponse.resultSet;
                        ArrayList arrayList = new ArrayList();
                        final HashMap hashMap = new HashMap();
                        for (StopAllUnitsModels.IndividualRacStartAllUnitResponseData next : list) {
                            if (!StopAllUnitsModels.ERROR_RESPONSE.contains(Integer.valueOf(next.errorCode))) {
                                CommandStatus commandStatus = next.commandStatus;
                                arrayList.add(commandStatus);
                                hashMap.put(Integer.valueOf(next.racId), commandStatus);
                            }
                        }
                        if (arrayList.isEmpty()) {
                            HomePagePresenterImpl.this.iHomePageView.onStartAllPartiallyComplete(list);
                        } else {
                            new CommandExecutionNetworkDispatcher().pollForCmdStatus(arrayList).observeSingleEvent(lifecycleOwner, new Observer<CommandStatusList>() {
                                public void onChanged(CommandStatusList commandStatusList) {
                                    for (int i = 0; i < list.size(); i++) {
                                        StopAllUnitsModels.IndividualRacStartAllUnitResponseData individualRacStartAllUnitResponseData = (StopAllUnitsModels.IndividualRacStartAllUnitResponseData) list.get(i);
                                        CommandStatus commandStatus = (CommandStatus) hashMap.get(Integer.valueOf(individualRacStartAllUnitResponseData.racId));
                                        Iterator it = commandStatusList.iterator();
                                        while (it.hasNext()) {
                                            CommandStatus commandStatus2 = (CommandStatus) it.next();
                                            if (commandStatus2.equals(commandStatus)) {
                                                individualRacStartAllUnitResponseData.commandStatus = commandStatus2;
                                            }
                                        }
                                    }
                                    if (!commandStatusList.areCommandExecuted() || !startAllUnitsSuccessResponse.allSucceeded) {
                                        HomePagePresenterImpl.this.iHomePageView.onStartAllPartiallyComplete(list);
                                    } else {
                                        HomePagePresenterImpl.this.iHomePageView.onStartAllSuccessful();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            }
            return;
        }
        LiveData<StopAllUnitsModels.StopAllnitsSuccessResponse> requestAllIduStartStop = IduOperationNetworkHelper.getInstance().requestAllIduStartStop(list);
        if (!requestAllIduStartStop.hasActiveObservers()) {
            requestAllIduStartStop.observe(lifecycleOwner, new HomePagePresenterImpl$$ExternalSyntheticLambda0(this, lifecycleOwner));
        }
    }

    /* renamed from: lambda$requestAllOnOff$1$com-jch-racWiFi-iduManagement-presenter-presenterImpl-HomePagePresenterImpl */
    public /* synthetic */ void mo30055xda53bc9c(LifecycleOwner lifecycleOwner, final StopAllUnitsModels.StopAllnitsSuccessResponse stopAllnitsSuccessResponse) {
        if (stopAllnitsSuccessResponse == null) {
            this.iHomePageView.onStoppingFailed();
            return;
        }
        if (Constants.IS_DEMO_MODE) {
            this.iHomePageView.onStoppingSuccessful();
        }
        final List<StopAllUnitsModels.IndividualRacStopAllUintResponseData> list = stopAllnitsSuccessResponse.resultSet;
        ArrayList arrayList = new ArrayList();
        final HashMap hashMap = new HashMap();
        for (StopAllUnitsModels.IndividualRacStopAllUintResponseData next : list) {
            if (!StopAllUnitsModels.ERROR_RESPONSE.contains(Integer.valueOf(next.errorCode))) {
                CommandStatus commandStatus = next.commandStatus;
                arrayList.add(commandStatus);
                hashMap.put(Integer.valueOf(next.racId), commandStatus);
            }
        }
        if (arrayList.isEmpty()) {
            this.iHomePageView.onStoppingPartiallyComplete(list);
        } else {
            new CommandExecutionNetworkDispatcher().pollForCmdStatus(arrayList).observeSingleEvent(lifecycleOwner, new Observer<CommandStatusList>() {
                public void onChanged(CommandStatusList commandStatusList) {
                    for (int i = 0; i < list.size(); i++) {
                        StopAllUnitsModels.IndividualRacStopAllUintResponseData individualRacStopAllUintResponseData = (StopAllUnitsModels.IndividualRacStopAllUintResponseData) list.get(i);
                        if (!StopAllUnitsModels.ERROR_RESPONSE.contains(Integer.valueOf(individualRacStopAllUintResponseData.errorCode))) {
                            CommandStatus commandStatus = (CommandStatus) hashMap.get(Integer.valueOf(individualRacStopAllUintResponseData.racId));
                            Iterator it = commandStatusList.iterator();
                            while (it.hasNext()) {
                                CommandStatus commandStatus2 = (CommandStatus) it.next();
                                if (commandStatus2.equals(commandStatus)) {
                                    individualRacStopAllUintResponseData.commandStatus = commandStatus2;
                                }
                            }
                        }
                    }
                    if (!commandStatusList.areCommandExecuted() || !stopAllnitsSuccessResponse.allSucceeded) {
                        HomePagePresenterImpl.this.iHomePageView.onStoppingPartiallyComplete(list);
                    } else {
                        HomePagePresenterImpl.this.iHomePageView.onStoppingSuccessful();
                    }
                }
            });
        }
    }

    public void getModelWiseData(String[] strArr) {
        new RacModelWiseDataFetchNetworkDispatcher().getRacModelInfo(strArr).observeSingleEvent((LifecycleOwner) this.iHomePageView, new Observer<List<RacModelWiseData>>() {
            public void onChanged(List<RacModelWiseData> list) {
                HomePagePresenterImpl.this.iHomePageView.onModelWiseRacInfoAvailable(list);
            }
        });
    }

    public void getRacModelTypesForFamily(int i) {
        new RacModelTypeListFromFamilyFetchNetworkDispatcher().getRacModelTypesForFamily(i).observeSingleEvent((LifecycleOwner) this.iHomePageView, new Observer<List<String>>() {
            public void onChanged(List<String> list) {
                HomePagePresenterImpl.this.iHomePageView.onModelTypeListAvailable(list);
            }
        });
    }

    public void checkAndUpdateStopAllSwitch(List<DetailedIduModel> list) {
        new Thread(new HomePagePresenterImpl$$ExternalSyntheticLambda2(this, list)).start();
    }

    /* renamed from: lambda$checkAndUpdateStopAllSwitch$3$com-jch-racWiFi-iduManagement-presenter-presenterImpl-HomePagePresenterImpl */
    public /* synthetic */ void mo30054xbd57689b(List list) {
        boolean z;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (detailedIduModel.online && detailedIduModel.isTurnedOn()) {
                z = true;
                break;
            }
        }
        IduOperationNetworkHelper.getInstance().getMainThreadHandler().post(new HomePagePresenterImpl$$ExternalSyntheticLambda3(this, z));
    }

    /* renamed from: lambda$checkAndUpdateStopAllSwitch$2$com-jch-racWiFi-iduManagement-presenter-presenterImpl-HomePagePresenterImpl */
    public /* synthetic */ void mo30053xbc88ea1a(boolean z) {
        if (z) {
            this.iHomePageView.checkStopAllButton();
        } else {
            this.iHomePageView.unCheckStopAllButton();
        }
    }
}
