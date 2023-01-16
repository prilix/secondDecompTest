package com.jch.racWiFi;

import com.jch_hitachi.aircloudglobal.R;
import retrofit2.Response;

public class GenericResponse<T> {
    public T response;
    public int statusCode;
    public Throwable throwable;

    public boolean isSuccessful() {
        int i = this.statusCode;
        return i >= 200 && i < 300;
    }

    public void setStatusCodeValue(Response response2) {
        this.statusCode = response2.code();
    }

    public int getErrorMessageStringId(String str) {
        if (str == null || str.isEmpty()) {
            return R.string.errorCode_alert_somethingWentWorng;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1995143803:
                if (str.equals("NFE002")) {
                    c = 0;
                    break;
                }
                break;
            case -1995143796:
                if (str.equals("NFE009")) {
                    c = 1;
                    break;
                }
                break;
            case -1995143734:
                if (str.equals(StatusCode.BUDGET_SETTINGS_NOT_FOUND)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.errorCode_alert_NFE002;
            case 1:
                return R.string.errorCode_alert_NFE009;
            case 2:
                return R.string.errorCode_alert_NFE029;
            default:
                return R.string.errorCode_alert_somethingWentWorng;
        }
    }
}
