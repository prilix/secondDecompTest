package com.jch.racWiFi.energyConsumption.networkCall;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionBudgetAndPrice;
import com.jch.racWiFi.energyConsumption.model.request.AllRacEnergyUsageReqBody;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings;
import com.jch.racWiFi.energyConsumption.model.request.EnergyConsumptionRequestBody;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnergyConsumptionNetworkCall extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private EnergyConsumptionApi energyConsumptionApi = ((EnergyConsumptionApi) getRetrofitService().create(EnergyConsumptionApi.class));
    private SingleLiveEvent<GenericResponse> energyUsageSingleLiveEvent;

    public SingleLiveEvent<GenericResponse> getAllRacTotalEnergyConsumed(int i, AllRacEnergyUsageReqBody allRacEnergyUsageReqBody) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.getTotalEnergyConsumed(i, allRacEnergyUsageReqBody).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getAllRacData(int i) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.getAllRacData(i).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> setBudgetAndPriceData(EnergyConsumptionBudgetAndPrice energyConsumptionBudgetAndPrice) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.setEnergyConsumptionBudgetAndPrice(energyConsumptionBudgetAndPrice).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getEnergyConsumptionData(int i, EnergyConsumptionRequestBody energyConsumptionRequestBody) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.getEnergyConsumptionData(i, energyConsumptionRequestBody).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getEnergyConsumptionSettingsData(int i) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.getEnergyConsumptionSettingsData(i).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> saveEnergyConsumptionSettingsData(EnergyCostSettingsData energyCostSettingsData) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.saveEnergyConsumptionSettingsData(energyCostSettingsData).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> saveEcSettings(ECSettings eCSettings) {
        this.energyUsageSingleLiveEvent = new SingleLiveEvent<>();
        this.energyConsumptionApi.saveEcSettings(eCSettings).enqueue(this);
        return this.energyUsageSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.energyUsageSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.energyUsageSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
