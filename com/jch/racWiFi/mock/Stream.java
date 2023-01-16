package com.jch.racWiFi.mock;

import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.jch.racWiFi.App;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoRequestBody;
import com.jch.racWiFi.p010di.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import org.apache.commons.lang3.StringUtils;

public class Stream<T> {
    private String fileName;
    private T object;
    private RequestBody requestBody;
    private Class<T> tClass;
    private Type type;

    public Stream(Type type2, RequestBody requestBody2) {
        this.type = type2;
        this.requestBody = requestBody2;
    }

    public Stream(Type type2, String str) {
        this.type = type2;
        this.fileName = str;
    }

    public Stream(Class<T> cls, RequestBody requestBody2) {
        this.tClass = cls;
        this.requestBody = requestBody2;
    }

    Stream(T t) {
        this.object = t;
    }

    public T getObject() {
        return this.object;
    }

    private Stream<T> readAssets() {
        return new Stream<>(new Gson().fromJson(get(), this.type));
    }

    private Stream<T> readStorage() throws IOException {
        return new Stream<>(new Gson().fromJson(retrieve(), this.type));
    }

    public Stream<T> readBody() {
        if (this.tClass != null) {
            return new Stream<>(new Gson().fromJson(convert(), this.tClass));
        }
        return new Stream<>(new Gson().fromJson(convert(), this.type));
    }

    private String get() {
        try {
            AssetManager assets = App.getApplicatonContext().getAssets();
            InputStream open = assets.open(Constants.Json.PATH + this.fileName);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void store(String str) throws IOException {
        try {
            if (this.fileName != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(App.getApplicatonContext().getFilesDir(), this.fileName)));
                bufferedWriter.write(str);
                bufferedWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String test() {
        OnboardingInfoRequestBody onboardingInfoRequestBody = new OnboardingInfoRequestBody();
        onboardingInfoRequestBody.setCity("Delhi");
        onboardingInfoRequestBody.setCountry("india");
        onboardingInfoRequestBody.setName("Mohit");
        onboardingInfoRequestBody.setThingPassword("xyzdjfkdjfdk");
        onboardingInfoRequestBody.setTimeZoneOffsetInMillis(93739308);
        onboardingInfoRequestBody.setZipCode("110018");
        onboardingInfoRequestBody.setVendorThingId("575757");
        return new Gson().toJson((Object) onboardingInfoRequestBody);
    }

    private String convert() {
        BufferedSink buffer = Okio.buffer((Sink) new Buffer());
        try {
            RequestBody requestBody2 = this.requestBody;
            if (requestBody2 == null) {
                return null;
            }
            requestBody2.writeTo(buffer);
            return buffer.buffer().readUtf8();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String retrieve() throws IOException {
        if (this.fileName == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(App.getApplicatonContext().getFilesDir(), this.fileName)));
        StringBuilder sb = new StringBuilder();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            sb.append(readLine);
            sb.append(StringUtils.f715LF);
        }
        bufferedReader.close();
        return sb.toString();
    }

    public T unWrapStream() throws IOException {
        if (isExists(this.fileName)) {
            return readStorage().getObject();
        }
        return readAssets().getObject();
    }

    private boolean isExists(String str) {
        return new File(App.getApplicatonContext().getFilesDir(), str).exists();
    }
}
