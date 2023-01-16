package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.jch.racWiFi.C1662R2;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class ActivityRecognitionClient extends GoogleApi<C1048Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public ActivityRecognitionClient(Activity activity) {
        super(activity, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> removeActivityTransitionUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzg(pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_elevation_level0).build());
    }

    public Task<Void> removeActivityUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zze(pendingIntent)).setMethodKey(C1662R2.dimen.m3_ripple_selectable_pressed_alpha).build());
    }

    public Task<Void> removeSleepSegmentUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzh(pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_elevation_level5).build());
    }

    public Task<Void> requestActivityTransitionUpdates(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        activityTransitionRequest.zza(getContextAttributionTag());
        return doWrite(TaskApiCall.builder().run(new zzf(activityTransitionRequest, pendingIntent)).setMethodKey(C1662R2.dimen.m3_snackbar_margin).build());
    }

    public Task<Void> requestActivityUpdates(long j, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzc(j, pendingIntent)).setMethodKey(C1662R2.dimen.m3_ripple_pressed_alpha).build());
    }

    public Task<Void> requestSleepSegmentUpdates(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        return doRead(TaskApiCall.builder().run(new zzd(this, pendingIntent, sleepSegmentRequest)).setFeatures(zzu.zzb).setMethodKey(C1662R2.dimen.m3_sys_elevation_level4).build());
    }

    public ActivityRecognitionClient(Context context) {
        super(context, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
