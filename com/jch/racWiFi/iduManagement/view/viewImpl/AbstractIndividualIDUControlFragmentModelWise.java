package com.jch.racWiFi.iduManagement.view.viewImpl;

import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.presenter.IndividualIDUControlPresenterV2;

public class AbstractIndividualIDUControlFragmentModelWise extends GenericFragment implements IndividualIDUControlPresenterV2.IIndividualIDUControlPresenter {
    public void commandDidNotExecute(CommandStatus.CommandStatusEnum commandStatusEnum) {
    }

    public void commandExecutionSuccess() {
    }

    public void onFanSpeedUpdatedFailure() {
    }

    public void onFanSpeedUpdatedSuccess() {
    }

    public void onHumidityUpdatedFailure() {
    }

    public void onHumidityUpdatedSuccess() {
    }

    public void onIduWholeCommandUpdateFailure(int i, GenericResponse.GenericErrorBody genericErrorBody) {
    }

    public void onIduWholeCommandUpdateSuccess() {
    }

    public void onNetworkCallFailure(Throwable th) {
    }

    public void onNetworkCallSuccess() {
    }

    public void onOperationModeUpdateFailure() {
    }

    public void onOperationModeUpdateSuccess() {
    }

    public void onRacIdUpdatedFailure() {
    }

    public void onRacIdUpdatedSuccess() {
    }

    public void onSwingOperationUpdatedFailure() {
    }

    public void onSwingOperationUpdatedSuccess() {
    }

    public void onSwitchOnOffRacUpdatedFailure() {
    }

    public void onSwitchOnOffRacUpdatedSuccess() {
    }

    public void onTemperatureUpdatedFailure() {
    }

    public void onTemperatureUpdatedSuccess() {
    }

    public void serverException() {
    }

    public void unAuthorized() {
    }
}
