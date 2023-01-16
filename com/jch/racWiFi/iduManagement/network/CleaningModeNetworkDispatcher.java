package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class CleaningModeNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_CLEAN_FAN = "/rac/clean/clean-fan/{racId}";
    public static final String METHOD_CLEAN_FILTER = "/rac/clean/filter/{racId}";
    public static final String METHOD_CLEAN_MOLD = "/rac/clean/clear-mold/{racId}";
    public static final String METHOD_CLEAN_ODU_FROST_WASH = "/rac/clean/odu-frost-wash/{racId}";
    public static final String METHOD_FROST_WASH = "/rac/clean/idu-frost-wash/{racId}";
    private CleaningModeEnum mCleaningModeEnum;
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();
    private LifecycleOwner mLifecycleOwner;

    public interface CleaningApi {
        @PUT("/rac/clean/clean-fan/{racId}")
        Call<ResponseBody> cleanFan(@Path("racId") int i);

        @PUT("/rac/clean/filter/{racId}")
        Call<ResponseBody> cleanFilter(@Path("racId") int i);

        @PUT("/rac/clean/clear-mold/{racId}")
        Call<ResponseBody> cleanMold(@Path("racId") int i);

        @PUT("/rac/clean/idu-frost-wash/{racId}")
        Call<ResponseBody> frostWash(@Path("racId") int i, @Query("familyId") int i2, @Body DetailedIduModel detailedIduModel);

        @PUT("/rac/clean/odu-frost-wash/{racId}")
        Call<ResponseBody> oduFrostWash(@Path("racId") int i);
    }

    public CleaningModeNetworkDispatcher(CleaningModeEnum cleaningModeEnum) {
        super("https://api-global-prod.aircloudhome.com");
        this.mCleaningModeEnum = cleaningModeEnum;
    }

    public CleaningModeNetworkDispatcher(CleaningModeEnum cleaningModeEnum, LifecycleOwner lifecycleOwner) {
        super("https://api-global-prod.aircloudhome.com");
        this.mCleaningModeEnum = cleaningModeEnum;
        this.mLifecycleOwner = lifecycleOwner;
    }

    public void cleanFilter(int i) {
        ((CleaningApi) getRetrofitService().create(CleaningApi.class)).cleanFilter(i).enqueue(this);
    }

    public void cleanMold(int i) {
        ((CleaningApi) getRetrofitService().create(CleaningApi.class)).cleanMold(i).enqueue(this);
    }

    public void cleanFan(int i) {
        ((CleaningApi) getRetrofitService().create(CleaningApi.class)).cleanFan(i).enqueue(this);
    }

    public SingleLiveEvent<GenericResponse> frostWash(DetailedIduModel detailedIduModel) {
        ((CleaningApi) getRetrofitService().create(CleaningApi.class)).frostWash(detailedIduModel.getId().intValue(), FamilyGroupList.getCurrentFamily().familyId, detailedIduModel).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void oduFrostWash(int i) {
        ((CleaningApi) getRetrofitService().create(CleaningApi.class)).oduFrostWash(i).enqueue(this);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
