package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzd extends zzf {
    final /* synthetic */ long zza;
    final /* synthetic */ PendingIntent zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzd(zzg zzg, GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zza = j;
        this.zzb = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(C1048Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zzq(this.zza, this.zzb);
        setResult(Status.RESULT_SUCCESS);
    }
}
