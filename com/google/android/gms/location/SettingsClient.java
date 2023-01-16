package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.jch.racWiFi.C1662R2;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class SettingsClient extends GoogleApi<C1048Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public SettingsClient(Activity activity) {
        super(activity, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest locationSettingsRequest) {
        return doRead(TaskApiCall.builder().run(new zzbs(locationSettingsRequest)).setMethodKey(C1662R2.dimen.m3_sys_typescale_display_large_text_size).build());
    }

    public SettingsClient(Context context) {
        super(context, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
