package com.jch.racWiFi.p010di.interceptors;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.p010di.util.TokenUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.jch.racWiFi.di.interceptors.RequestInterceptor */
public class RequestInterceptor implements Interceptor {
    private static final String TAG = "RequestInterceptor";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        if (request.url().toString().contains("/iam/auth/refresh-token")) {
            newBuilder.header(Constants.Keys.IS_REFRESH_TOKEN, "true");
            newBuilder.header("Authorization", TokenUtil.getInstance().obtain().getBearerRefreshToken());
        } else if (!(TokenUtil.getInstance().obtain() == null || TokenUtil.getInstance().obtain().getToken() == null || TokenUtil.getInstance().obtain().getToken().isEmpty())) {
            newBuilder.header("Authorization", TokenUtil.getInstance().obtain().getBearerToken());
        }
        newBuilder.header(Constants.NetworkParams.CONTENT_TYPE, "application/json");
        newBuilder.header("Accept", "application/json");
        newBuilder.method(request.method(), request.body());
        Response proceed = chain.proceed(newBuilder.build());
        Logger.m47e(TAG, "" + proceed.request().url());
        JSONObject jSONObject = new JSONObject();
        if (proceed.code() >= 200 && proceed.code() < 300) {
            try {
                jSONObject.put(Constants.Meta.META, getMeta(proceed.code(), proceed.message()));
                if (proceed.body() == null) {
                    return proceed;
                }
                String string = proceed.body().string();
                if (!string.isEmpty()) {
                    if (isJSONObject(string)) {
                        jSONObject.put(Constants.Meta.BODY, new JSONObject(string));
                        return getResponse(proceed, jSONObject);
                    }
                }
                if (string.isEmpty() || !isJSONArray(string)) {
                    jSONObject.put(Constants.Meta.BODY, new JSONObject());
                } else {
                    jSONObject.put(Constants.Meta.BODY, new JSONArray(string));
                }
                return getResponse(proceed, jSONObject);
            } catch (JSONException e) {
                Logger.m47e(TAG, e.getMessage());
            }
        } else if (proceed.code() != 400) {
            try {
                jSONObject.put(Constants.Meta.META, getMeta(proceed.code(), proceed.message()));
                return getResponse(proceed, jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (proceed.body() != null) {
            String string2 = proceed.body().string();
            try {
                if (!string2.isEmpty() && isJSONObject(string2)) {
                    JSONObject jSONObject2 = new JSONObject(string2);
                    if (!jSONObject2.has(Constants.Keys.ERROR_STATE) || !jSONObject2.has("token") || !jSONObject2.has("type")) {
                        jSONObject.put(Constants.Meta.META, getMeta(proceed.code(), proceed.message()));
                    } else {
                        jSONObject.put(Constants.Meta.META, getMeta(proceed.code(), new JSONObject(string2).getString(Constants.Keys.ERROR_STATE), new JSONObject(string2).getString("token"), new JSONObject(string2).getString("type")));
                    }
                    return getResponse(proceed, jSONObject);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return proceed;
    }

    private boolean isJSONObject(String str) throws JSONException {
        return new JSONTokener(str).nextValue() instanceof JSONObject;
    }

    private boolean isJSONArray(String str) throws JSONException {
        return new JSONTokener(str).nextValue() instanceof JSONArray;
    }

    private JSONObject getMeta(int i, String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", i);
        jSONObject.put(Constants.Meta.MESSAGE, str);
        jSONObject.put("token", str2);
        jSONObject.put("type", str3);
        return jSONObject;
    }

    private JSONObject getMeta(int i, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", i);
        jSONObject.put(Constants.Meta.MESSAGE, str);
        return jSONObject;
    }

    private Response getResponse(Response response, JSONObject jSONObject) {
        MediaType contentType = response.body() != null ? response.body().contentType() : null;
        if (contentType != null) {
            return response.newBuilder().body(ResponseBody.create(contentType, jSONObject.toString())).build();
        }
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.code(200).protocol(Protocol.HTTP_2);
        newBuilder.body(ResponseBody.create(MediaType.parse("application/json"), jSONObject.toString()));
        return newBuilder.build();
    }
}
