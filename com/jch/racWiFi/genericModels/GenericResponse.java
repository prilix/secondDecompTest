package com.jch.racWiFi.genericModels;

import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.StatusCode;
import com.jch_hitachi.aircloudglobal.R;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

public class GenericResponse {
    private JSONObject jsonObject;
    /* access modifiers changed from: private */
    public Response<ResponseBody> response;
    /* access modifiers changed from: private */
    public Throwable throwable;

    public static class GenericErrorBody {
        @SerializedName("code")
        public String code;
        @SerializedName("details")
        public String details;
        @SerializedName("errorState")
        public String errorState;
        @SerializedName("message")
        public String message;
        @SerializedName("time")
        public String time;
    }

    public Response<ResponseBody> getResponse() {
        return this.response;
    }

    public GenericResponse parseJson() {
        try {
            this.jsonObject = new JSONObject(this.response.body().string());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Object getValue(String str) throws Exception {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject != null) {
            return jSONObject.get(str);
        }
        throw new Exception("call parse() before querying for value");
    }

    public int getErrorMessageStringId(String str) {
        if (str == null || str.isEmpty()) {
            return R.string.errorCode_alert_somethingWentWorng;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1995143803:
                if (str.equals("NFE002")) {
                    c = 0;
                    break;
                }
                break;
            case -1995143796:
                if (str.equals("NFE009")) {
                    c = 1;
                    break;
                }
                break;
            case -1995143734:
                if (str.equals(StatusCode.BUDGET_SETTINGS_NOT_FOUND)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.errorCode_alert_NFE002;
            case 1:
                return R.string.errorCode_alert_NFE009;
            case 2:
                return R.string.errorCode_alert_NFE029;
            default:
                return R.string.errorCode_alert_somethingWentWorng;
        }
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public boolean unableToReachServer() {
        return this.throwable != null;
    }

    public boolean isApiSuccessful() {
        Response<ResponseBody> response2 = this.response;
        return response2 != null && response2.isSuccessful();
    }

    public <T> T getBodyOfType(Class<T> cls) {
        Gson gson = new Gson();
        ResponseBody body = this.response.body();
        if (body != null) {
            return gson.fromJson(body.charStream(), cls);
        }
        return null;
    }

    public <T> T getBodyOfType(Type type) {
        Gson gson = new Gson();
        ResponseBody body = this.response.body();
        if (body != null) {
            return gson.fromJson(body.charStream(), type);
        }
        return null;
    }

    public <T> T getErrorBodyOfType(Class<T> cls) {
        Gson gson = new Gson();
        ResponseBody errorBody = this.response.errorBody();
        Logger.m47e("my_responce", errorBody.charStream().toString());
        if (errorBody != null) {
            return gson.fromJson(errorBody.charStream(), cls);
        }
        return null;
    }

    public static final class GenericResponseBuilder {
        private Response<ResponseBody> response;
        private Throwable throwable;

        public GenericResponseBuilder withResponse(Response<ResponseBody> response2) {
            this.response = response2;
            return this;
        }

        public GenericResponseBuilder withThrowable(Throwable th) {
            this.throwable = th;
            return this;
        }

        public GenericResponse build() {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.throwable = this.throwable;
            genericResponse.response = this.response;
            return genericResponse;
        }
    }

    public static GenericResponse getSuccessGenericResponse(Response<ResponseBody> response2) {
        return new GenericResponseBuilder().withResponse(response2).build();
    }

    public static GenericResponse getFailureGenericResponse(Throwable th) {
        return new GenericResponseBuilder().withThrowable(th).build();
    }

    public static class GenericErrorResponse {
        public int code;
        public Response<ResponseBody> response;
        public Throwable throwable;

        public static GenericErrorResponse getErrorResponse(GenericResponse genericResponse) {
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse();
            genericErrorResponse.code = genericResponse.getResponse().code();
            genericErrorResponse.response = genericResponse.response;
            genericErrorResponse.throwable = genericResponse.throwable;
            return genericErrorResponse;
        }

        public boolean isUnauthorized() {
            Response<ResponseBody> response2 = this.response;
            return response2 != null && response2.code() == 401;
        }

        public boolean isInvalidToken() {
            Response<ResponseBody> response2 = this.response;
            return response2 != null && response2.code() == 400;
        }

        public boolean unableToReachServer() {
            return this.throwable != null;
        }
    }

    public GenericErrorResponse getGenericErrorResponse() {
        return GenericErrorResponse.getErrorResponse(this);
    }
}
