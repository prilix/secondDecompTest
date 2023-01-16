package com.jch.racWiFi.p010di.module.base;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.p010di.model.Meta;
import retrofit2.HttpException;

/* renamed from: com.jch.racWiFi.di.module.base.BaseViewModel */
public class BaseViewModel extends C0534ViewModel {
    /* access modifiers changed from: protected */
    public Meta getMeta(String str, Throwable th) {
        Meta meta = new Meta();
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            meta.setCode(httpException.code());
            meta.setMessage(httpException.message());
        } else {
            meta.setCode(424);
            meta.setMessage(str);
        }
        meta.setThrowable(th);
        return meta;
    }

    /* access modifiers changed from: protected */
    public Meta getMeta(int i, String str, Throwable th, String str2, String str3) {
        Meta meta = new Meta();
        meta.setCode(i);
        meta.setMessage(str);
        meta.setThrowable(th);
        meta.setToken(str2);
        meta.setType(str3);
        return meta;
    }
}
