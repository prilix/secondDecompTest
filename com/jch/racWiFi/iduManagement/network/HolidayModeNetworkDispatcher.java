package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class HolidayModeNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_HOLIDAY_MODE_CHANGE = "/rac/scheduled-operations/holidayModeSchedule/schedules";
    public static final String METHOD_HOLIDAY_MODE_GET = "/rac/scheduled-operations/holidayModeSchedule/schedules";
    public static final String METHOD_INTERRPUT_HOLIDAY_MODE = "/rac/scheduled-operations/holidayModeSchedule/disableHolidayMode";
    private final int GET = 1;
    private final int INTERRUPT = 3;
    private final int UPDATE = 2;
    public MutableLiveData<HolidayModeModel.HolidayModeFetchResponse> fetchResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<HolidayModeModel.HolidayModeInterruptResponse> holidayModeInterruptResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<HolidayModeModel.HolidayModeUpdateResponseDataV2> holidayModeSuccessResponceMutableLiveData = new MutableLiveData<>();
    private int type = 0;

    public interface IHolidayModeAPI {
        @POST("/rac/scheduled-operations/holidayModeSchedule/schedules")
        Call<ResponseBody> changeHolidayMode(@Query("familyId") int i, @Body HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2);

        @GET("/rac/scheduled-operations/holidayModeSchedule/schedules")
        Call<ResponseBody> getHolidayMode(@Query("familyId") int i);

        @POST("/rac/scheduled-operations/holidayModeSchedule/disableHolidayMode")
        Call<ResponseBody> interrputHolidayMode(@Query("familyId") int i, @Body HolidayModeModel.InterruptHolidayMode interruptHolidayMode);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
    }

    public HolidayModeNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public MutableLiveData<HolidayModeModel.HolidayModeFetchResponse> getHolidayMode() {
        this.type = 1;
        ((IHolidayModeAPI) getRetrofitService().create(IHolidayModeAPI.class)).getHolidayMode(FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
        return this.fetchResponseMutableLiveData;
    }

    public MutableLiveData<HolidayModeModel.HolidayModeUpdateResponseDataV2> updateHolidayMode(HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2) {
        this.type = 2;
        ((IHolidayModeAPI) getRetrofitService().create(IHolidayModeAPI.class)).changeHolidayMode(FamilyGroupList.getCurrentFamily().familyId, holidayModeRequestDataV2).enqueue(this);
        return this.holidayModeSuccessResponceMutableLiveData;
    }

    public MutableLiveData<HolidayModeModel.HolidayModeInterruptResponse> interrputToHolidayMode(HolidayModeModel.InterruptHolidayMode interruptHolidayMode) {
        this.type = 3;
        ((IHolidayModeAPI) getRetrofitService().create(IHolidayModeAPI.class)).interrputHolidayMode(FamilyGroupList.getCurrentFamily().familyId, interruptHolidayMode).enqueue(this);
        return this.holidayModeInterruptResponseMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        int i = this.type;
        if (i == 1) {
            HolidayModeModel.HolidayModeFetchResponse holidayModeFetchResponse = new HolidayModeModel.HolidayModeFetchResponse();
            holidayModeFetchResponse.setStatusCodeValue(response);
            if (response.code() == 200) {
                holidayModeFetchResponse.response = new Gson().fromJson(response.body().charStream(), HolidayModeModel.HolidayModeResponseData[].class);
            }
            this.fetchResponseMutableLiveData.setValue(holidayModeFetchResponse);
        } else if (i == 2) {
            HolidayModeModel.HolidayModeUpdateResponseDataV2 holidayModeUpdateResponseDataV2 = new HolidayModeModel.HolidayModeUpdateResponseDataV2();
            holidayModeUpdateResponseDataV2.statusCode = response.code();
            if (response.code() == 200) {
                holidayModeUpdateResponseDataV2.response = new Gson().fromJson(response.body().charStream(), HolidayModeModel.HolidayModeResponse.class);
            }
            this.holidayModeSuccessResponceMutableLiveData.setValue(holidayModeUpdateResponseDataV2);
        } else if (i == 3) {
            HolidayModeModel.HolidayModeInterruptResponse holidayModeInterruptResponse = new HolidayModeModel.HolidayModeInterruptResponse();
            holidayModeInterruptResponse.statusCode = response.code();
            if (response.code() == 200) {
                holidayModeInterruptResponse.response = response.body().charStream();
            }
            this.holidayModeInterruptResponseMutableLiveData.setValue(holidayModeInterruptResponse);
        }
    }
}
