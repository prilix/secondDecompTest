package com.google.android.gms.internal.measurement;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.5.0 */
final class zzga extends zzfz {
    private final zzfy zza = new zzfy();

    zzga() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.zza.zza(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
