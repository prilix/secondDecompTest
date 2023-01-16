package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzba;
import com.google.android.gms.internal.location.zzbj;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.Constants;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class FusedLocationProviderClient extends GoogleApi<C1048Api.ApiOptions.NoOptions> {
    public static final String KEY_MOCK_LOCATION = "mockLocation";
    public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";

    public FusedLocationProviderClient(Activity activity) {
        super(activity, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    private final Task<Void> zze(zzba zzba, LocationCallback locationCallback, Looper looper, zzan zzan, int i) {
        Class<LocationCallback> cls = LocationCallback.class;
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(locationCallback, zzbj.zza(looper), "LocationCallback");
        zzak zzak = new zzak(this, createListenerHolder);
        return doRegisterEventListener(RegistrationMethods.builder().register(new zzae(this, zzak, locationCallback, zzan, zzba, createListenerHolder)).unregister(zzak).withHolder(createListenerHolder).setMethodKey(i).build());
    }

    public Task<Void> flushLocations() {
        return doWrite(TaskApiCall.builder().run(zzw.zza).setMethodKey(C1662R2.dimen.m3_sys_typescale_body_medium_text_size).build());
    }

    public Task<Location> getCurrentLocation(int i, CancellationToken cancellationToken) {
        LocationRequest create = LocationRequest.create();
        create.setPriority(i);
        create.setInterval(0);
        create.setFastestInterval(0);
        create.setExpirationDuration(30000);
        zzba zza = zzba.zza((String) null, create);
        zza.zzd(true);
        zza.zzb(Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT);
        Task<Location> doRead = doRead(TaskApiCall.builder().run(new zzab(this, cancellationToken, zza)).setFeatures(zzu.zzd).setMethodKey(C1662R2.dimen.m3_sys_state_dragged_state_layer_opacity).build());
        if (cancellationToken == null) {
            return doRead;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWithTask(new zzac(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<Location> getLastLocation() {
        return doRead(TaskApiCall.builder().run(new zzv(this)).setMethodKey(C1662R2.dimen.m3_sys_shape_small_corner_size).build());
    }

    public Task<LocationAvailability> getLocationAvailability() {
        return doRead(TaskApiCall.builder().run(zzad.zza).setMethodKey(C1662R2.dimen.m3_sys_state_focus_state_layer_opacity).build());
    }

    public Task<Void> removeLocationUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzag(pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_state_pressed_state_layer_opacity).build());
    }

    public Task<Void> requestLocationUpdates(LocationRequest locationRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzaf(this, zzba.zza((String) null, locationRequest), pendingIntent)).setMethodKey(C1662R2.dimen.m3_sys_state_hover_state_layer_opacity).build());
    }

    public Task<Void> setMockLocation(Location location) {
        return doWrite(TaskApiCall.builder().run(new zzai(location)).setMethodKey(C1662R2.dimen.m3_sys_typescale_body_medium_letter_spacing).build());
    }

    public Task<Void> setMockMode(boolean z) {
        return doWrite(TaskApiCall.builder().run(new zzah(z)).setMethodKey(C1662R2.dimen.m3_sys_typescale_body_large_text_size).build());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzba zzba, PendingIntent pendingIntent, zzaz zzaz, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzao zzao = new zzao(taskCompletionSource);
        zzba.zzc(getContextAttributionTag());
        zzaz.zzD(zzba, pendingIntent, zzao);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzap zzap, LocationCallback locationCallback, zzan zzan, zzba zzba, ListenerHolder listenerHolder, zzaz zzaz, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzam zzam = new zzam(taskCompletionSource, new zzx(this, zzap, locationCallback, zzan));
        zzba.zzc(getContextAttributionTag());
        zzaz.zzB(zzba, listenerHolder, zzam);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(CancellationToken cancellationToken, zzba zzba, zzaz zzaz, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaj zzaj = new zzaj(this, taskCompletionSource);
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(new zzy(this, zzaj));
        }
        zze(zzba, zzaj, Looper.getMainLooper(), new zzz(taskCompletionSource), C1662R2.dimen.m3_sys_typescale_label_large_letter_spacing).continueWithTask(new zzaa(taskCompletionSource));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzaz zzaz, TaskCompletionSource taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzaz.zzz(getContextAttributionTag()));
    }

    public FusedLocationProviderClient(Context context) {
        super(context, LocationServices.API, C1048Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    public Task<Void> removeLocationUpdates(LocationCallback locationCallback) {
        Class<LocationCallback> cls = LocationCallback.class;
        return TaskUtil.toVoidTaskThatFailsOnFalse(doUnregisterEventListener(ListenerHolders.createListenerKey(locationCallback, "LocationCallback")));
    }

    public Task<Void> requestLocationUpdates(LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        return zze(zzba.zza((String) null, locationRequest), locationCallback, looper, (zzan) null, C1662R2.dimen.m3_sys_typescale_headline_small_text_size);
    }
}
