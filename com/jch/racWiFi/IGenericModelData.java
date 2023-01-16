package com.jch.racWiFi;

import com.google.gson.JsonObject;

public interface IGenericModelData<T> {
    String getJsonKey();

    T getJsonValue();

    void importFromJson(JsonObject jsonObject);

    JsonObject toJson();
}
