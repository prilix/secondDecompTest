package com.jch.racWiFi.userManagement.network.ApiCaller;

import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionApiImpl$1$$ExternalSyntheticLambda2 implements Runnable {
    public static final /* synthetic */ PermissionApiImpl$1$$ExternalSyntheticLambda2 INSTANCE = new PermissionApiImpl$1$$ExternalSyntheticLambda2();

    private /* synthetic */ PermissionApiImpl$1$$ExternalSyntheticLambda2() {
    }

    public final void run() {
        ConfigurationDataProvider.getInstance().setInitialAppConfigDtoLiveData((InitialAppConfigDto) null);
    }
}
