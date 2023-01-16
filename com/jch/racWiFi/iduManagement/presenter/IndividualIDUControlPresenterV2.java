package com.jch.racWiFi.iduManagement.presenter;

import android.os.Handler;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.BasicIDUControls;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.network.IDUBasicControlsNetworkDispatcher;

public class IndividualIDUControlPresenterV2 extends AbstractPresenter {
    private IIndividualIDUControlPresenter iIndividualIDUControlPresenter;
    private ITemperatureHumidityCommandCallback iTemperatureHumidityCommandCallback;
    Observer<GenericResponse> iduCommandFailureForRefreshTokenObserver = new Observer<GenericResponse>() {
        public void onChanged(GenericResponse genericResponse) {
        }
    };
    private DetailedIduModel mDetailedIduModel;
    private Handler mHumidityIncrementHandler = new Handler();
    private LifecycleOwner mLifecycleOwner;
    private RacModelWiseData mRacModelWiseData;
    private Handler mTemperatureIncrementHandler = new Handler();

    public interface IIndividualIDUControlPresenter extends INetworkConnectivity {
        void commandDidNotExecute(CommandStatus.CommandStatusEnum commandStatusEnum);

        void commandExecutionSuccess();

        void onFanSpeedUpdatedFailure();

        void onFanSpeedUpdatedSuccess();

        void onHumidityUpdatedFailure();

        void onHumidityUpdatedSuccess();

        void onIduWholeCommandUpdateFailure(int i, GenericResponse.GenericErrorBody genericErrorBody);

        void onIduWholeCommandUpdateSuccess();

        void onOperationModeUpdateFailure();

        void onOperationModeUpdateSuccess();

        void onRacIdUpdatedFailure();

        void onRacIdUpdatedSuccess();

        void onSwingOperationUpdatedFailure();

        void onSwingOperationUpdatedSuccess();

        void onSwitchOnOffRacUpdatedFailure();

        void onSwitchOnOffRacUpdatedSuccess();

        void onTemperatureUpdatedFailure();

        void onTemperatureUpdatedSuccess();

        void unAuthorized();
    }

    public interface ITemperatureHumidityCommandCallback {
        void onAnyOtherCommandSentCallback();

        void onHumidityCommandSentCallback();

        void onTemperatureCommandSentCallback();
    }

    public static class IndividualIDUControlResponseConstants {
        public static final int IDU_COMMAND_IN_PROGRESS = 429;
        public static final int IDU_COMMAND_LOCK_5_MIN = 423;
        public static final int IDU_PRE_CONDITION_FAILED = 412;
        public static final int NOT_AUTHORIZED_1 = 406;
        public static final int NOT_AUTHORIZED_2 = 403;
        public static final int NOT_FOUND = 404;
        public static final String PCF001_NOT_BELONG_TO_FAMILY = "PCF001";
        public static final String PCF009_THING_OFFLINE = "PCF009";
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public void setDetailedIduModel(DetailedIduModel detailedIduModel) {
        this.mDetailedIduModel = detailedIduModel;
    }

    public void setRacModelWiseData(RacModelWiseData racModelWiseData) {
        this.mRacModelWiseData = racModelWiseData;
    }

    public IndividualIDUControlPresenterV2(IIndividualIDUControlPresenter iIndividualIDUControlPresenter2, ITemperatureHumidityCommandCallback iTemperatureHumidityCommandCallback2, LifecycleOwner lifecycleOwner) {
        this.iIndividualIDUControlPresenter = iIndividualIDUControlPresenter2;
        this.iTemperatureHumidityCommandCallback = iTemperatureHumidityCommandCallback2;
        this.mLifecycleOwner = lifecycleOwner;
    }

    public void cleanUp() {
        this.mTemperatureIncrementHandler.removeCallbacksAndMessages((Object) null);
        this.mHumidityIncrementHandler.removeCallbacksAndMessages((Object) null);
    }

    public void changeTemperature(int i, float f) {
        this.mTemperatureIncrementHandler.removeCallbacksAndMessages((Object) null);
        this.mTemperatureIncrementHandler.postDelayed(new IndividualIDUControlPresenterV2$$ExternalSyntheticLambda0(this, f, i), 700);
    }

    /* renamed from: lambda$changeTemperature$0$com-jch-racWiFi-iduManagement-presenter-IndividualIDUControlPresenterV2 */
    public /* synthetic */ void mo30023xc3860f74(float f, int i) {
        this.iTemperatureHumidityCommandCallback.onTemperatureCommandSentCallback();
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.TEMPERATURE, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.iduTemperature = f;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
    }

    @Deprecated
    public void changeHumidity(int i, int i2) {
        this.mHumidityIncrementHandler.removeCallbacksAndMessages((Object) null);
        this.mHumidityIncrementHandler.postDelayed(new IndividualIDUControlPresenterV2$$ExternalSyntheticLambda1(this, i2, i), 700);
    }

    /* renamed from: lambda$changeHumidity$1$com-jch-racWiFi-iduManagement-presenter-IndividualIDUControlPresenterV2 */
    public /* synthetic */ void mo30021xf52d3e2e(int i, int i2) {
        this.iTemperatureHumidityCommandCallback.onHumidityCommandSentCallback();
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.HUMIDITY, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.humidity = String.valueOf(i);
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i2, this.mDetailedIduModel);
    }

    public void changeHumidity(int i, String str) {
        this.mHumidityIncrementHandler.removeCallbacksAndMessages((Object) null);
        this.mHumidityIncrementHandler.postDelayed(new IndividualIDUControlPresenterV2$$ExternalSyntheticLambda2(this, str, i), 700);
    }

    /* renamed from: lambda$changeHumidity$2$com-jch-racWiFi-iduManagement-presenter-IndividualIDUControlPresenterV2 */
    public /* synthetic */ void mo30022xbc39252f(String str, int i) {
        this.iTemperatureHumidityCommandCallback.onHumidityCommandSentCallback();
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.HUMIDITY, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.humidity = str;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
    }

    @Deprecated
    public void changeFanSpeed(int i, int i2) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.FAN, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.fanSpeed = i2;
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void changeFanSpeed(int i, String str) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.FAN, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.fanSpeedStr = str;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    @Deprecated
    public void changeSwingOperation(int i, int i2) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.SWING, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.fanSwing = i2;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void changeSwingOperation(int i, String str) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.SWING, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.fanSwingStr = str;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null) {
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void changeOnOffRac(int i, String str) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.ON_OFF, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.power = str;
        if (this.mRacModelWiseData != null) {
            if (this.mDetailedIduModel.isInSpecialMode()) {
                this.mDetailedIduModel.copyDefaults(this.mRacModelWiseData);
            }
            this.mDetailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(this.mRacModelWiseData);
        }
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    @Deprecated
    public void changeOperationMode(int i, OperationMode operationMode) {
        IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.OPERATION_MODE, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
        this.mDetailedIduModel.mode = operationMode.name();
        this.mDetailedIduModel.iduTemperature = operationMode.getDefaultTemp();
        this.mDetailedIduModel.relativeTemperature = 0.0f;
        this.mDetailedIduModel.humidity = String.valueOf(operationMode.getDefaultHumidity());
        this.mDetailedIduModel.fanSpeed = operationMode.getDefaultFanSpeed();
        iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void changeOperationMode(int i, OperationMode operationMode, RacModelWiseData racModelWiseData) {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationMode);
        if (racModeDetailBasedOnMode != null) {
            IDUBasicControlsNetworkDispatcher iDUBasicControlsNetworkDispatcher = new IDUBasicControlsNetworkDispatcher(BasicIDUControls.OPERATION_MODE, this.mLifecycleOwner, this.iIndividualIDUControlPresenter);
            RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
            this.mDetailedIduModel.mode = operationMode.name();
            if (temperatureSettingType == null || !temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.ABSOLUTE)) {
                this.mDetailedIduModel.relativeTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
            } else {
                this.mDetailedIduModel.iduTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
            }
            this.mDetailedIduModel.humidity = String.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
            this.mDetailedIduModel.fanSpeedStr = racModeDetailBasedOnMode.getDefaultFanSpeedDefaults().getDefaultFanSpeed().name();
            if (this.mDetailedIduModel.getSwingOptionEnum().equals(DetailedIduModel.SwingOption.AUTO)) {
                this.mDetailedIduModel.fanSwingStr = DetailedIduModel.SwingOption.OFF.name();
            }
            iDUBasicControlsNetworkDispatcher.changeIduState(i, this.mDetailedIduModel);
        }
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void refreshRacState(int i) {
        new IDUBasicControlsNetworkDispatcher(BasicIDUControls.REFRESH_IDU_STATE, this.mLifecycleOwner, this.iIndividualIDUControlPresenter).getIduState(i);
        this.iTemperatureHumidityCommandCallback.onAnyOtherCommandSentCallback();
    }

    public void removeCallbacks() {
        this.iIndividualIDUControlPresenter = null;
        this.iTemperatureHumidityCommandCallback = null;
    }
}
