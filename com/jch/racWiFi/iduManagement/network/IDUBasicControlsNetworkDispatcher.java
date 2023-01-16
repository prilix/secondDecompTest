package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.BasicIDUControls;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.CommandStatusList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.presenter.IndividualIDUControlPresenterV2;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.io.IOException;
import java.util.Collections;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class IDUBasicControlsNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_GET_IDU_STATUS = "/rac/status/{racId}";
    public static final String METHOD_UPDATE_IDU_STATE_AS_WHOLE = "/rac/basic-idu-control/general-control-command/{racId}";
    public static final boolean UPDATE_WHOLE = true;
    DetailedIduModel detailedIduModel;
    /* access modifiers changed from: private */
    public IndividualIDUControlPresenterV2.IIndividualIDUControlPresenter iIndividualIDUControlPresenter;
    private BasicIDUControls mBasicIDUControls;
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();
    private LifecycleOwner mLifecycleOwner;
    private boolean powerChangeFromHomePage = false;
    private MutableLiveData<Response<ResponseBody>> powerModeChangeLiveData;
    int racId;
    SingleLiveEvent<Response<ResponseBody>> singleLiveEvent;

    public interface IBasicControlsApi {
        @PUT("/rac/basic-idu-control/general-control-command/{racId}")
        Call<ResponseBody> changeIduState(@Path("racId") int i, @Query("familyId") int i2, @Body DetailedIduModel detailedIduModel);

        @PUT("/rac/status/{racId}")
        Call<ResponseBody> refreshRacState(@Path("racId") int i, @Query("familyId") int i2);
    }

    public IDUBasicControlsNetworkDispatcher(BasicIDUControls basicIDUControls) {
        super("https://api-global-prod.aircloudhome.com");
        this.mBasicIDUControls = basicIDUControls;
    }

    public IDUBasicControlsNetworkDispatcher(BasicIDUControls basicIDUControls, LifecycleOwner lifecycleOwner, IndividualIDUControlPresenterV2.IIndividualIDUControlPresenter iIndividualIDUControlPresenter2) {
        super("https://api-global-prod.aircloudhome.com");
        this.mBasicIDUControls = basicIDUControls;
        this.mLifecycleOwner = lifecycleOwner;
        this.iIndividualIDUControlPresenter = iIndividualIDUControlPresenter2;
    }

    public SingleLiveEvent<GenericResponse> changeIduState(int i, DetailedIduModel detailedIduModel2) {
        this.racId = i;
        this.detailedIduModel = detailedIduModel2;
        ((IBasicControlsApi) getRetrofitService().create(IBasicControlsApi.class)).changeIduState(i, FamilyGroupList.getCurrentFamily().familyId, detailedIduModel2).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public LiveData<Response<ResponseBody>> changeIduStatePower(int i, DetailedIduModel detailedIduModel2) {
        this.powerModeChangeLiveData = new MutableLiveData<>();
        ((IBasicControlsApi) getRetrofitService().create(IBasicControlsApi.class)).changeIduState(i, FamilyGroupList.getCurrentFamily().familyId, detailedIduModel2).enqueue(this);
        this.powerChangeFromHomePage = true;
        return this.powerModeChangeLiveData;
    }

    public void getIduState(int i) {
        ((IBasicControlsApi) getRetrofitService().create(IBasicControlsApi.class)).refreshRacState(i, FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        IndividualIDUControlPresenterV2.IIndividualIDUControlPresenter iIndividualIDUControlPresenter2;
        if (response != null) {
            Logger.m49i("", "res code = " + response.code());
        }
        int code = response.code();
        if (!response.isSuccessful()) {
            if (response.code() == 401 && (iIndividualIDUControlPresenter2 = this.iIndividualIDUControlPresenter) != null) {
                iIndividualIDUControlPresenter2.unAuthorized();
            }
            if (this.powerChangeFromHomePage) {
                this.powerModeChangeLiveData.setValue(response);
                this.powerModeChangeLiveData = new MutableLiveData<>();
                this.powerChangeFromHomePage = false;
                return;
            }
            switch (C18702.$SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls[this.mBasicIDUControls.ordinal()]) {
                case 1:
                    this.iIndividualIDUControlPresenter.onTemperatureUpdatedFailure();
                    break;
                case 2:
                    this.iIndividualIDUControlPresenter.onFanSpeedUpdatedFailure();
                    break;
                case 3:
                    this.iIndividualIDUControlPresenter.onSwingOperationUpdatedFailure();
                    break;
                case 4:
                    this.iIndividualIDUControlPresenter.onHumidityUpdatedFailure();
                    break;
                case 5:
                    this.iIndividualIDUControlPresenter.onSwitchOnOffRacUpdatedFailure();
                    break;
                case 6:
                    this.iIndividualIDUControlPresenter.onOperationModeUpdateFailure();
                    break;
                case 7:
                    this.iIndividualIDUControlPresenter.onRacIdUpdatedFailure();
                    break;
            }
            Gson gson = new Gson();
            try {
                if (response.errorBody() != null) {
                    this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateFailure(code, (GenericResponse.GenericErrorBody) gson.fromJson(response.errorBody().string(), GenericResponse.GenericErrorBody.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.powerChangeFromHomePage) {
            this.powerModeChangeLiveData.setValue(response);
            this.powerModeChangeLiveData = new MutableLiveData<>();
            this.powerChangeFromHomePage = false;
        } else {
            CommandStatus commandStatus = (CommandStatus) new Gson().fromJson(response.body().charStream(), CommandStatus.class);
            if (commandStatus != null) {
                commandStatus.setBasicIDUControls(this.mBasicIDUControls);
            }
            setSuccessData(response.code(), commandStatus);
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.network.IDUBasicControlsNetworkDispatcher$2 */
    static /* synthetic */ class C18702 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.BasicIDUControls[] r0 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls = r0
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.TEMPERATURE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.FAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.SWING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.HUMIDITY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.ON_OFF     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.OPERATION_MODE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.model.BasicIDUControls r1 = com.jch.racWiFi.iduManagement.model.BasicIDUControls.REFRESH_IDU_STATE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.network.IDUBasicControlsNetworkDispatcher.C18702.<clinit>():void");
        }
    }

    private void setSuccessData(int i, CommandStatus commandStatus) {
        switch (C18702.$SwitchMap$com$jch$racWiFi$iduManagement$model$BasicIDUControls[this.mBasicIDUControls.ordinal()]) {
            case 1:
                this.iIndividualIDUControlPresenter.onTemperatureUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 2:
                this.iIndividualIDUControlPresenter.onFanSpeedUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 3:
                this.iIndividualIDUControlPresenter.onSwingOperationUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 4:
                this.iIndividualIDUControlPresenter.onHumidityUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 5:
                this.iIndividualIDUControlPresenter.onSwitchOnOffRacUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 6:
                this.iIndividualIDUControlPresenter.onOperationModeUpdateSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
            case 7:
                this.iIndividualIDUControlPresenter.onRacIdUpdatedSuccess();
                this.iIndividualIDUControlPresenter.onIduWholeCommandUpdateSuccess();
                break;
        }
        new CommandExecutionNetworkDispatcher().pollForCmdStatus(Collections.singletonList(commandStatus)).observeSingleEvent(this.mLifecycleOwner, new Observer<CommandStatusList>() {
            public void onChanged(CommandStatusList commandStatusList) {
                if (commandStatusList.areCommandExecuted()) {
                    IDUBasicControlsNetworkDispatcher.this.iIndividualIDUControlPresenter.commandExecutionSuccess();
                } else {
                    IDUBasicControlsNetworkDispatcher.this.iIndividualIDUControlPresenter.commandDidNotExecute(((CommandStatus) commandStatusList.get(0)).getStatus());
                }
            }
        });
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
