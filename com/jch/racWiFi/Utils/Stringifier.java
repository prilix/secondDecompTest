package com.jch.racWiFi.Utils;

import com.google.gson.Gson;

public class Stringifier<T> {
    private Gson gson = new Gson();
    private T object;

    public Stringifier(T t) {
        this.object = t;
    }

    public String getJsonString() {
        return this.gson.toJson((Object) this.object);
    }
}
