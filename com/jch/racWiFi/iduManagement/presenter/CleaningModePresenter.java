package com.jch.racWiFi.iduManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.BasicIDUControls;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.CommandStatusList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.network.CleaningModeNetworkDispatcher;
import com.jch.racWiFi.iduManagement.network.CommandExecutionNetworkDispatcher;
import java.util.Collections;

public class CleaningModePresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public ICleaningModePresenter iCleaningModePresenter;
    /* access modifiers changed from: private */
    public LifecycleOwner mLifecycleOwner;

    public interface ICleaningModePresenter extends INetworkConnectivity {
        void onFrostWashCommandFailure(CommandStatus.CommandStatusEnum commandStatusEnum, int i);

        void onFrostWashCommandSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public CleaningModePresenter(ICleaningModePresenter iCleaningModePresenter2, LifecycleOwner lifecycleOwner) {
        this.iCleaningModePresenter = iCleaningModePresenter2;
        this.mLifecycleOwner = lifecycleOwner;
    }

    public void startFrostWash(DetailedIduModel detailedIduModel) {
        new CleaningModeNetworkDispatcher(CleaningModeEnum.FROST_WASH, this.mLifecycleOwner).frostWash(detailedIduModel).observeSingleEvent(this.mLifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(final GenericResponse genericResponse) {
                if (CleaningModePresenter.this.iCleaningModePresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        CleaningModePresenter.this.iCleaningModePresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        CommandStatus commandStatus = (CommandStatus) genericResponse.getBodyOfType(CommandStatus.class);
                        if (commandStatus != null) {
                            commandStatus.setBasicIDUControls(BasicIDUControls.IDU_FROST_WASH);
                        }
                        new CommandExecutionNetworkDispatcher().pollForCmdStatus(Collections.singletonList(commandStatus)).observeSingleEvent(CleaningModePresenter.this.mLifecycleOwner, new Observer<CommandStatusList>() {
                            public void onChanged(CommandStatusList commandStatusList) {
                                if (commandStatusList.areCommandExecuted()) {
                                    CleaningModePresenter.this.iCleaningModePresenter.onFrostWashCommandSuccess();
                                } else {
                                    CleaningModePresenter.this.onCommandDidNotExecute(commandStatusList, genericResponse.getResponse().code());
                                }
                            }
                        });
                    } else {
                        CleaningModePresenter.this.iCleaningModePresenter.onFrostWashCommandFailure((CommandStatus.CommandStatusEnum) null, genericResponse.getResponse().code());
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iCleaningModePresenter = null;
    }

    public void onCommandDidNotExecute(CommandStatusList commandStatusList, int i) {
        if (((CommandStatus) commandStatusList.get(0)).getStatus().equals(CommandStatus.CommandStatusEnum.INCOMPLETE)) {
            this.iCleaningModePresenter.onFrostWashCommandFailure(CommandStatus.CommandStatusEnum.INCOMPLETE, i);
        }
    }
}
