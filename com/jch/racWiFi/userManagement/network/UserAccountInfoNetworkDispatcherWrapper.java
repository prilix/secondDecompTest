package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UpdateNameModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public class UserAccountInfoNetworkDispatcherWrapper {

    public static class UpdateUserNameNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
        public static final String METHOD_UPDATE_USER_NAME = "/iam/user/update-name";
        private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

        public interface UserNameRequestApi {
            @PUT("/iam/user/update-name")
            Call<ResponseBody> updateUserName(@Body UpdateNameModels.UpdateName updateName);
        }

        public SingleLiveEvent<GenericResponse> updateUserName(UpdateNameModels.UpdateName updateName) {
            ((UserNameRequestApi) getRetrofitService().create(UserNameRequestApi.class)).updateUserName(updateName).enqueue(this);
            return this.mGenericResponseSingleLiveEvent;
        }

        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
        }

        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
        }
    }
}
