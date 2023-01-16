package com.jch.racWiFi.energyConsumption.networkCall;

import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionBudgetAndPrice;
import com.jch.racWiFi.energyConsumption.model.request.AllRacEnergyUsageReqBody;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings;
import com.jch.racWiFi.energyConsumption.model.request.EnergyConsumptionRequestBody;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EnergyConsumptionApi {
    @GET("/rac/energy-consumptions/all/racs")
    Call<ResponseBody> getAllRacData(@Query("familyId") int i);

    @GET("/iam/user/energy-setting")
    Call<ResponseBody> getEnergyConsumptionBudgetAndPrice();

    @POST("/rac/energy-consumptions")
    Call<ResponseBody> getEnergyConsumptionData(@Query("familyId") int i, @Body EnergyConsumptionRequestBody energyConsumptionRequestBody);

    @GET("/rac/budget-settings/data")
    Call<ResponseBody> getEnergyConsumptionSettingsData(@Query("familyId") int i);

    @POST("/rac/energy-consumptions/summary/v3")
    Call<ResponseBody> getTotalEnergyConsumed(@Query("familyId") int i, @Body AllRacEnergyUsageReqBody allRacEnergyUsageReqBody);

    @POST("/rac/energy-consumptions/settings")
    Call<ResponseBody> saveEcSettings(@Body ECSettings eCSettings);

    @POST("/rac/budget-settings/save")
    Call<ResponseBody> saveEnergyConsumptionSettingsData(@Body EnergyCostSettingsData energyCostSettingsData);

    @POST("/iam/user/energy-setting")
    Call<ResponseBody> setEnergyConsumptionBudgetAndPrice(@Body EnergyConsumptionBudgetAndPrice energyConsumptionBudgetAndPrice);
}
