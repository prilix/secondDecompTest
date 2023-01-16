package com.jch.racWiFi.iduManagement.smartFence.networkDispatcher;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceOccupancyModel;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceServerRequestModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlsStateModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class GeoFencesNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_GEO_FENCE_OCCUPANCY = "/rac/location-controls/user-geofence-occupancy";
    private static final String METHOD_GET_GEO_FENCES = "/rac/location-controls/settings";
    private static final String METHOD_STATUS_LOCATION_CONTROLS = "/rac/location-controls/status";
    private SingleLiveEvent<GenericResponse> genericResponseSingleLiveEvent = new SingleLiveEvent<>();

    private interface GetGeoFenceApi {
        @GET("/rac/location-controls/settings")
        Call<ResponseBody> getAllGeoFence();

        @GET("/rac/location-controls/user-geofence-occupancy")
        Call<ResponseBody> getGeoFenceOccupancy();

        @GET("/rac/location-controls/status")
        Call<ResponseBody> getGeoFenceStatus();

        @POST("/rac/location-controls/status")
        Call<ResponseBody> setGeoFenceStatus(@Body LocationControlsStateModel locationControlsStateModel);

        @POST("/rac/location-controls/status")
        Call<ResponseBody> setGeoFenceStatusForParticularFamily(@Body LocationControlsStateModel locationControlsStateModel, @Query("familyId") int i);

        @POST("/rac/location-controls/settings")
        Call<ResponseBody> updateGeoFence(@Body GeoFenceServerRequestModel geoFenceServerRequestModel);

        @POST("/rac/location-controls/user-geofence-occupancy")
        Call<ResponseBody> updateGeoFenceOccupancy(@Body GeoFenceOccupancyModel geoFenceOccupancyModel);
    }

    public SingleLiveEvent<GenericResponse> getAllGeoFences() {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).getAllGeoFence().enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public void getAllGeoFences(Callback<ResponseBody> callback) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).getAllGeoFence().enqueue(callback);
    }

    public SingleLiveEvent<GenericResponse> getGeoFenceStatus() {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).getGeoFenceStatus().enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getGeoFenceStatus(Callback<ResponseBody> callback) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).getGeoFenceStatus().enqueue(callback);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> setGeoFenceStatus(LocationControlsStateModel locationControlsStateModel) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).setGeoFenceStatus(locationControlsStateModel).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> setGeoFenceStatusForParticularFamily(LocationControlsStateModel locationControlsStateModel, int i) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).setGeoFenceStatusForParticularFamily(locationControlsStateModel, i).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> updateGeoFenceSettings(GeoFenceServerRequestModel geoFenceServerRequestModel) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).updateGeoFence(geoFenceServerRequestModel).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getGeoFenceOccupancy() {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).getGeoFenceOccupancy().enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> updateGeoFenceOccupancy(GeoFenceOccupancyModel geoFenceOccupancyModel) {
        ((GetGeoFenceApi) getRetrofitService().create(GetGeoFenceApi.class)).updateGeoFenceOccupancy(geoFenceOccupancyModel).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.genericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.genericResponseSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
