package com.jch.racWiFi.p010di.model;

/* renamed from: com.jch.racWiFi.di.model.Resource */
public class Resource<T> {
    public final String message;
    public final T response;
    public final Status status;

    /* renamed from: com.jch.racWiFi.di.model.Resource$Status */
    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public Resource(Status status2, T t, String str) {
        this.status = status2;
        this.response = t;
        this.message = str;
    }

    public static <T> Resource<T> success(T t) {
        return new Resource<>(Status.SUCCESS, t, (String) null);
    }

    public static <T> Resource<T> error(String str, T t, int i) {
        return new Resource<>(Status.ERROR, t, str);
    }

    public static <T> Resource<T> loading(T t) {
        return new Resource<>(Status.LOADING, t, (String) null);
    }
}
