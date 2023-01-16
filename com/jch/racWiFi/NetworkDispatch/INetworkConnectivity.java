package com.jch.racWiFi.NetworkDispatch;

public interface INetworkConnectivity {
    void onNetworkCallFailure(Throwable th);

    void onNetworkCallSuccess();

    void serverException();
}
