package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public class ChangeHolidayModeDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_HOLIDAY_MODE_CHANGE = "/idu-advance-control/holiday-mode";
    public static final String METHOD_HOLIDAY_MODE_GET = "/idu-advance-control/holiday-mode-schedule";
    private MutableLiveData<HolidayModeModel.HolidayModeFetchResponse> fetchResponseMutableLiveData = new MutableLiveData<>();
    private boolean get = false;

    public interface IHolidayModeAPI {
        @PUT("/idu-advance-control/holiday-mode")
        Call<ResponseBody> changeHolidayMode(@Body HolidayModeModel.HolidayModeRequestData holidayModeRequestData);

        @GET("/idu-advance-control/holiday-mode-schedule")
        Call<ResponseBody> getHolidayMode();
    }

    public ChangeHolidayModeDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void changeHolidayMode(HolidayModeModel.HolidayModeRequestData holidayModeRequestData) {
        ((IHolidayModeAPI) getRetrofitService().create(IHolidayModeAPI.class)).changeHolidayMode(holidayModeRequestData).enqueue(this);
    }

    public MutableLiveData<HolidayModeModel.HolidayModeFetchResponse> getHolidayMode() {
        this.get = true;
        ((IHolidayModeAPI) getRetrofitService().create(IHolidayModeAPI.class)).getHolidayMode().enqueue(this);
        return this.fetchResponseMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (!this.get && !response.isSuccessful()) {
            EventBus.getDefault().post(new HolidayModeModel.HolidayModeFailureResponce());
        }
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        EventBus.getDefault().post(th);
    }
}
