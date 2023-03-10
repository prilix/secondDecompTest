package com.jch.racWiFi.tooltip;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Typefaces;", "", "()V", "FONT_CACHE", "Landroid/util/LruCache;", "", "Landroid/graphics/Typeface;", "get", "c", "Landroid/content/Context;", "assetPath", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Typefaces.kt */
public final class Typefaces {
    private static final LruCache<String, Typeface> FONT_CACHE = new LruCache<>(4);
    public static final Typefaces INSTANCE = new Typefaces();

    private Typefaces() {
    }

    public final Typeface get(Context context, String str) {
        Typeface typeface;
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "assetPath");
        LruCache<String, Typeface> lruCache = FONT_CACHE;
        synchronized (lruCache) {
            typeface = lruCache.get(str);
            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(context.getAssets(), str);
                    lruCache.put(str, typeface);
                } catch (Exception unused) {
                    return null;
                }
            }
        }
        return typeface;
    }
}
