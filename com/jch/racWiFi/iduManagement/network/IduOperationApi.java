package com.jch.racWiFi.iduManagement.network;

import com.jch.racWiFi.iduManagement.dto.OperationSwitchOnOffDto;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IduOperationApi {
    @GET("/rac/ownership/idu-list")
    Call<List<DetailedIduModel>> requestDetailedIduList(@Header("Authorization") String str);

    @PUT("/rac/basic-idu-control/switch-on-off/{racId}")
    Call<ResponseBody> requestPowerOnOff(@Header("Authorization") String str, @Path("racId") Long l, @Body OperationSwitchOnOffDto operationSwitchOnOffDto);

    @PUT("/rac/manage-idu/groups/{family-id}/idu/start")
    Call<StopAllUnitsModels.StartAllUnitsSuccessResponse> startAllUnits(@Path("family-id") int i, @Header("Authorization") String str, @Body List<DetailedIduModel> list);

    @PUT("/rac/manage-idu/groups/{family-id}/idu/stop")
    Call<StopAllUnitsModels.StopAllnitsSuccessResponse> stopAllUnits(@Path("family-id") int i, @Header("Authorization") String str, @Body List<DetailedIduModel> list);
}
