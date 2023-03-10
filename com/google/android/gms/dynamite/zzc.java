package com.google.android.gms.dynamite;

import com.evernote.android.state.StateSaver;
import dalvik.system.PathClassLoader;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
final class zzc extends PathClassLoader {
    zzc(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    /* access modifiers changed from: protected */
    public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!str.startsWith(StateSaver.JAVA_PREFIX) && !str.startsWith(StateSaver.ANDROID_PREFIX)) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z);
    }
}
