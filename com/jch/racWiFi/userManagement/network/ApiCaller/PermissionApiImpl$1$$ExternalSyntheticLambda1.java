package com.jch.racWiFi.userManagement.network.ApiCaller;

import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import retrofit2.Response;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionApiImpl$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Response f$0;

    public /* synthetic */ PermissionApiImpl$1$$ExternalSyntheticLambda1(Response response) {
        this.f$0 = response;
    }

    public final void run() {
        ConfigurationDataProvider.getInstance().setInitialAppConfigDtoLiveData((InitialAppConfigDto) this.f$0.body());
    }
}
