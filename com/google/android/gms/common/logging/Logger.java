package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class Logger {
    private final String zza;
    private final String zzb;
    private final GmsLogger zzc;
    private final int zzd;

    public Logger(String str, String... strArr) {
        String str2;
        if (r0 == 0) {
            str2 = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (String str3 : strArr) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(str3);
            }
            sb.append("] ");
            str2 = sb.toString();
        }
        this.zzb = str2;
        this.zza = str;
        this.zzc = new GmsLogger(str);
        int i = 2;
        while (i <= 7 && !Log.isLoggable(this.zza, i)) {
            i++;
        }
        this.zzd = i;
    }

    /* renamed from: d */
    public void mo17266d(String str, Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.zza, format(str, objArr));
        }
    }

    /* renamed from: e */
    public void mo17267e(String str, Throwable th, Object... objArr) {
        Log.e(this.zza, format(str, objArr), th);
    }

    /* access modifiers changed from: protected */
    public String format(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzb.concat(str);
    }

    public String getTag() {
        return this.zza;
    }

    /* renamed from: i */
    public void mo17271i(String str, Object... objArr) {
        Log.i(this.zza, format(str, objArr));
    }

    public boolean isLoggable(int i) {
        return this.zzd <= i;
    }

    /* renamed from: v */
    public void mo17273v(String str, Throwable th, Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.zza, format(str, objArr), th);
        }
    }

    /* renamed from: w */
    public void mo17275w(String str, Object... objArr) {
        Log.w(this.zza, format(str, objArr));
    }

    public void wtf(String str, Throwable th, Object... objArr) {
        Log.wtf(this.zza, format(str, objArr), th);
    }

    /* renamed from: e */
    public void mo17268e(String str, Object... objArr) {
        Log.e(this.zza, format(str, objArr));
    }

    public void wtf(Throwable th) {
        Log.wtf(this.zza, th);
    }

    /* renamed from: v */
    public void mo17274v(String str, Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.zza, format(str, objArr));
        }
    }
}
