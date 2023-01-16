package com.jch.racWiFi.p010di.module.base;

import android.app.Application;
import android.content.Context;
import com.accord.common.utils.Logger;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.jch.algo.Security;
import com.jch.racWiFi.BuildConfig;
import com.jch.racWiFi.p010di.interceptors.NetworkInterceptor;
import com.jch.racWiFi.p010di.interceptors.RequestInterceptor;
import com.jch_hitachi.aircloudglobal.R;
import dagger.Module;
import dagger.Provides;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
/* renamed from: com.jch.racWiFi.di.module.base.BaseModule */
public class BaseModule {
    private static final String TAG = "BaseModule";

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        return new Retrofit.Builder().baseUrl("https://api-global-prod.aircloudhome.com").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideClient(HttpLoggingInterceptor httpLoggingInterceptor, Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new NetworkInterceptor());
        if (BuildConfig.TLS.booleanValue()) {
            InputStream openRawResource = application.getResources().openRawResource(R.raw.home);
            try {
                SSLContext instance = SSLContext.getInstance(TlsVersion.TLS_1_3.javaName());
                Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(openRawResource);
                openRawResource.close();
                KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance2.load((InputStream) null, (char[]) null);
                instance2.setCertificateEntry("ca", generateCertificate);
                TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance3.init(instance2);
                instance.init((KeyManager[]) null, instance3.getTrustManagers(), new SecureRandom());
                builder.sslSocketFactory(instance.getSocketFactory(), (X509TrustManager) instance3.getTrustManagers()[0]);
                builder.hostnameVerifier(BaseModule$$ExternalSyntheticLambda0.INSTANCE);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                openRawResource.close();
                throw th;
            }
        }
        builder.addInterceptor(new RequestInterceptor());
        builder.addInterceptor(httpLoggingInterceptor);
        return builder;
    }

    static /* synthetic */ boolean lambda$provideClient$0(String str, SSLSession sSLSession) {
        Logger.m47e(TAG, "name : " + str);
        return str.equals("api-stage.aircloudhome.com");
    }

    @Singleton
    @Provides
    static ConnectionSpec provideConnectionSpec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return (RequestOptions) RequestOptions.placeholderOf((int) R.drawable.ic_action_search).error((int) R.drawable.ic_error_svg);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions) {
        return Glide.with((Context) application).setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Security provideSecurity() {
        return Security.getInstance();
    }
}
