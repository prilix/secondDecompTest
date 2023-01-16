package com.jch.racWiFi.userManagement.network.ApiCaller;

import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionApiImpl$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ InitialAppConfigDto f$0;

    public /* synthetic */ PermissionApiImpl$1$$ExternalSyntheticLambda0(InitialAppConfigDto initialAppConfigDto) {
        this.f$0 = initialAppConfigDto;
    }

    public final void run() {
        ConfigurationDataProvider.getInstance().setInitialAppConfigDtoLiveData(this.f$0);
    }
}
