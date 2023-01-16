package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.jch.racWiFi.C1662R2;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class GeofencingClient extends GoogleApi<C1048Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public GeofencingClient(Activity activity) {
        super(activity, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> addGeofences(GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzaq(geofencingRequest.zza(getContextAttributionTag()), pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_typescale_body_small_text_size).build());
    }

    public Task<Void> removeGeofences(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzar(pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_typescale_display_large_letter_spacing).build());
    }

    public GeofencingClient(Context context) {
        super(context, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> removeGeofences(List<String> list) {
        return doWrite(TaskApiCall.builder().run(new zzas(list)).setMethodKey(C1662R2.dimen.m3_sys_typescale_display_large_letter_spacing).build());
    }
}
