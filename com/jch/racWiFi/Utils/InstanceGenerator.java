package com.jch.racWiFi.Utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class InstanceGenerator<T> {
    private Gson gson = new Gson();
    private RequestBody requestBody;
    private String string;
    private Class<T> tClass;
    private Type type;

    public InstanceGenerator(Class<T> cls, RequestBody requestBody2) {
        this.requestBody = requestBody2;
        this.tClass = cls;
    }

    public InstanceGenerator(Class<T> cls, String str) {
        this.string = str;
        this.tClass = cls;
    }

    public InstanceGenerator(Type type2, String str) {
        this.string = str;
        this.type = type2;
    }

    public InstanceGenerator(Type type2, RequestBody requestBody2) {
        this.requestBody = requestBody2;
        this.type = type2;
    }

    public T getInstance() {
        return this.gson.fromJson(this.string, this.tClass);
    }

    public T getInstanceForType() {
        return this.gson.fromJson(this.string, this.type);
    }

    public T getInstanceForRequestBody() {
        BufferedSink buffer = Okio.buffer((Sink) new Buffer());
        try {
            this.requestBody.writeTo(buffer);
            String readUtf8 = buffer.buffer().readUtf8();
            Class<T> cls = this.tClass;
            if (cls != null) {
                return new InstanceGenerator(cls, readUtf8).getInstance();
            }
            return new InstanceGenerator(this.type, readUtf8).getInstanceForType();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
