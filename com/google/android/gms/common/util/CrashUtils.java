package com.google.android.gms.common.util;

import android.content.Context;
import android.util.Log;
import com.evernote.android.state.StateSaver;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class CrashUtils {
    private static final String[] zza = {StateSaver.ANDROID_PREFIX, "com.android.", "dalvik.", StateSaver.JAVA_PREFIX, "javax."};

    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            return false;
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }
}
