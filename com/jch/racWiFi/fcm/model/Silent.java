package com.jch.racWiFi.fcm.model;

import com.jch.racWiFi.fcm.util.SilentSubCategory;
import com.jch.racWiFi.fcm.util.Type;

public class Silent {
    private SilentSubCategory subCategory;
    private Type type = Type.SILENT;

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public SilentSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SilentSubCategory silentSubCategory) {
        this.subCategory = silentSubCategory;
    }
}
