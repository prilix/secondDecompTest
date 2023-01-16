package com.jch.racWiFi.amplitude.factory;

import android.app.Application;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.TrackingOptions;
import com.jch.racWiFi.BuildConfig;
import com.jch.racWiFi.amplitude.exception.AmplitudeException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0002R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/factory/ClientFactory;", "", "mApplication", "Landroid/app/Application;", "(Landroid/app/Application;)V", "client", "Lcom/amplitude/api/AmplitudeClient;", "getClient", "()Lcom/amplitude/api/AmplitudeClient;", "getTrackingOptions", "Lcom/amplitude/api/TrackingOptions;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ClientFactory.kt */
public final class ClientFactory {
    private final Application mApplication;

    public ClientFactory(Application application) {
        this.mApplication = application;
    }

    public final AmplitudeClient getClient() throws AmplitudeException {
        if (this.mApplication != null) {
            AmplitudeClient enableForegroundTracking = Amplitude.getInstance().setTrackingOptions(getTrackingOptions()).initialize(this.mApplication.getApplicationContext(), BuildConfig.AMPLITUDE_KEY).enableForegroundTracking(this.mApplication);
            Intrinsics.checkNotNullExpressionValue(enableForegroundTracking, "getInstance().setTrackin…undTracking(mApplication)");
            return enableForegroundTracking;
        }
        throw new AmplitudeException("Application object must not be null.");
    }

    private final TrackingOptions getTrackingOptions() {
        TrackingOptions disableRegion = new TrackingOptions().disableCarrier().disableCity().disableCountry().disableDma().disableIpAddress().disableLatLng().disableRegion();
        Intrinsics.checkNotNullExpressionValue(disableRegion, "TrackingOptions().disabl…eLatLng().disableRegion()");
        return disableRegion;
    }
}
