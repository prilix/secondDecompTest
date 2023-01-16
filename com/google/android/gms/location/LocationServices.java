package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbi;
import com.google.android.gms.internal.location.zzz;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class LocationServices {
    public static final C1048Api<C1048Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new zzz();
    @Deprecated
    public static final GeofencingApi GeofencingApi = new zzaf();
    @Deprecated
    public static final SettingsApi SettingsApi = new zzbi();
    private static final C1048Api.ClientKey<zzaz> zza;
    private static final C1048Api.AbstractClientBuilder<zzaz, C1048Api.ApiOptions.NoOptions> zzb;

    static {
        C1048Api.ClientKey<zzaz> clientKey = new C1048Api.ClientKey<>();
        zza = clientKey;
        zzbh zzbh = new zzbh();
        zzb = zzbh;
        API = new C1048Api<>("LocationServices.API", zzbh, clientKey);
    }

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    public static GeofencingClient getGeofencingClient(Activity activity) {
        return new GeofencingClient(activity);
    }

    public static SettingsClient getSettingsClient(Activity activity) {
        return new SettingsClient(activity);
    }

    public static zzaz zza(GoogleApiClient googleApiClient) {
        boolean z = true;
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzaz zzaz = (zzaz) googleApiClient.getClient(zza);
        if (zzaz == null) {
            z = false;
        }
        Preconditions.checkState(z, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzaz;
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new FusedLocationProviderClient(context);
    }

    public static GeofencingClient getGeofencingClient(Context context) {
        return new GeofencingClient(context);
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new SettingsClient(context);
    }
}
