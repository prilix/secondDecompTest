package com.jch.racWiFi.NetworkDispatch;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.MutableLiveData;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jch.racWiFi.BuildConfig;
import com.jch.racWiFi.mock.MockInterceptor;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch_hitachi.aircloudglobal.R;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import p020ua.naiksoftware.stomp.dto.StompHeader;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractNetworkDispatcher implements INetworkDispatcher {
    private static Call<ResponseBody> bodyCall;
    public static INetworkCheckCallback iNetworkCheckCallback;
    private Retrofit mRetrofit;
    private Handler mainLopper;
    public MutableLiveData<Throwable> networkFailureMutableLiveData = new MutableLiveData<>();

    public interface INetworkCheckCallback {
        boolean isNetworkAvailable();
    }

    public static Call<ResponseBody> getBodyCall() {
        return bodyCall;
    }

    /* access modifiers changed from: protected */
    public void setBodyCall(Call<ResponseBody> call) {
        bodyCall = call;
    }

    public Retrofit getRetrofitService() {
        return this.mRetrofit;
    }

    public AbstractNetworkDispatcher() {
        buildRetrofit("https://api-global-prod.aircloudhome.com");
    }

    public AbstractNetworkDispatcher(String str) {
        buildRetrofit(str);
    }

    private void buildRetrofit(String str) {
        this.mainLopper = new Handler(Looper.getMainLooper());
        Gson create = new GsonBuilder().setLenient().create();
        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.TLS.booleanValue()) {
            builder.connectionSpecs(Collections.singletonList(build));
        }
        builder.addInterceptor(AbstractNetworkDispatcher$$ExternalSyntheticLambda0.INSTANCE);
        this.mRetrofit = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create(create)).client(builder.build()).build();
    }

    static /* synthetic */ Response lambda$buildRetrofit$0(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (TokenUtil.getInstance().obtain() != null) {
            Logger.m49i("", "Bearer Token " + TokenUtil.getInstance().obtain().getBearerToken());
        }
        Request.Builder newBuilder = request.newBuilder();
        if (request.url().toString().contains("/iam/auth/refresh-token")) {
            newBuilder.header(Constants.Keys.IS_REFRESH_TOKEN, "true");
            newBuilder.header("Authorization", TokenUtil.getInstance().obtain().getBearerRefreshToken());
        } else {
            if (TokenUtil.getInstance().obtain() != null) {
                PrintStream printStream = System.out;
                printStream.println("AbstractNetworkDispatcher token : " + TokenUtil.getInstance().obtain().getBearerToken());
            }
            if (!(TokenUtil.getInstance().obtain() == null || TokenUtil.getInstance().obtain().getToken() == null || TokenUtil.getInstance().obtain().getToken().isEmpty())) {
                newBuilder.header("Authorization", TokenUtil.getInstance().obtain().getBearerToken());
            }
        }
        newBuilder.header(Constants.NetworkParams.CONTENT_TYPE, "application/json");
        newBuilder.header("Accept", "application/json");
        newBuilder.method(request.method(), request.body());
        Request build = newBuilder.build();
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            return MockInterceptor.intercept(chain, build);
        }
        return chain.proceed(build);
    }

    private void buildDemoRetrofit(String str) {
        this.mainLopper = new Handler(Looper.getMainLooper());
        Gson create = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                request.url().toString();
                Logger.m49i("", "receive url = " + request.url());
                return chain.proceed(chain.request()).newBuilder().code(200).protocol(Protocol.HTTP_2).message("").body(ResponseBody.create(MediaType.parse("application/json"), "")).addHeader(StompHeader.CONTENT_TYPE, "application/json").build();
            }
        });
        this.mRetrofit = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create(create)).client(builder.build()).build();
    }

    public Handler getMainThreadHandler() {
        return this.mainLopper;
    }

    public class NoConnectivityException extends IOException {
        public String getMessage() {
            return "No Internet Connection";
        }

        public int hashCode() {
            return R.string.common_alert_unableToConnectToNw;
        }

        public NoConnectivityException() {
        }
    }
}
