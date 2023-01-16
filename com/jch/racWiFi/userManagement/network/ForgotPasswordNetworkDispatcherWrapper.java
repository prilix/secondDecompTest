package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ForgotPassordModel;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ForgotPasswordNetworkDispatcherWrapper {
    public static final String METHOD_REQUEST_OTP_TO_EMAIL = "/iam/account/forgot-password";
    public static final String METHOD_UPDATE_PASSWORD = "/iam/account/recover/v2";
    public static final String METHOD_VERIFY_OTP_TO_EMAIL = "/iam/account/forgot-password/verify";

    public interface ForgotPasswordApis {
        @POST("/iam/account/forgot-password")
        Call<ResponseBody> requestOTPForForgotPassword(@Query("language") String str, @Body ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData);

        @POST("/iam/account/recover/v2")
        Call<ResponseBody> updatePassword(@Body ForgotPasswordDataModels.ForgotPasswordUpdateRequestData forgotPasswordUpdateRequestData);

        @POST("/iam/account/forgot-password/verify")
        Call<ResponseBody> verifyOTPForForgotPassword(@Body ForgotPassordModel forgotPassordModel);
    }

    public static class ForgotPasswordRequestOTP extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
        private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

        public SingleLiveEvent<GenericResponse> requestOTPForgotPassword(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData) {
            ((ForgotPasswordApis) getRetrofitService().create(ForgotPasswordApis.class)).requestOTPForForgotPassword(LocaleConfiguration.getLanguageCodeForCurrentLocale(), forgotPasswordOTPRequestData).enqueue(this);
            return this.mGenericResponseSingleLiveEvent;
        }

        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
        }

        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
        }
    }

    public static class ForgotPasswordVerifyOTPNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
        private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

        public SingleLiveEvent<GenericResponse> verifyOTPForgotPassword(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData) {
            ForgotPassordModel forgotPassordModel = new ForgotPassordModel();
            forgotPassordModel.setSecret(String.valueOf(forgotPasswordVerifyOTPRequestData.OTP));
            forgotPassordModel.email = forgotPasswordVerifyOTPRequestData.email;
            forgotPassordModel.mobileNumber = forgotPasswordVerifyOTPRequestData.mobileNumber;
            ((ForgotPasswordApis) getRetrofitService().create(ForgotPasswordApis.class)).verifyOTPForForgotPassword(forgotPassordModel).enqueue(this);
            return this.mGenericResponseSingleLiveEvent;
        }

        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
        }

        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
        }
    }

    public static class UpdatePasswordNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
        private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

        public SingleLiveEvent<GenericResponse> updatePassword(ForgotPasswordDataModels.ForgotPasswordUpdateRequestData forgotPasswordUpdateRequestData) {
            ((ForgotPasswordApis) getRetrofitService().create(ForgotPasswordApis.class)).updatePassword(forgotPasswordUpdateRequestData).enqueue(this);
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
