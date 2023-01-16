package com.evernote.android.state;

import android.app.Application;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.evernote.android.state.Injector;
import java.util.Map;

final class StateSaverImpl {
    private final Map<Class<?>, Injector> mInjectors;

    StateSaverImpl(Map<Class<?>, Injector> map) {
        this.mInjectors = map;
    }

    private Injector getInjector(Class<?> cls) {
        Injector injector;
        Injector injector2 = this.mInjectors.get(cls);
        if (injector2 != null || this.mInjectors.containsKey(cls)) {
            return injector2;
        }
        String name = cls.getName();
        if (name.startsWith(StateSaver.ANDROID_PREFIX) || name.startsWith(StateSaver.JAVA_PREFIX)) {
            return null;
        }
        try {
            injector = (Injector) Class.forName(name + StateSaver.SUFFIX).newInstance();
        } catch (Exception unused) {
            injector = getInjector(cls.getSuperclass());
        }
        this.mInjectors.put(cls, injector);
        return injector;
    }

    private <T extends Injector> T safeGet(Object obj, Injector injector) {
        try {
            T injector2 = getInjector(obj.getClass());
            return injector2 == null ? injector : injector2;
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject state for " + obj, e);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void saveInstanceState(T t, Bundle bundle) {
        ((Injector.Object) safeGet(t, Injector.Object.DEFAULT)).save(t, bundle);
    }

    /* access modifiers changed from: package-private */
    public <T> void restoreInstanceState(T t, Bundle bundle) {
        if (bundle != null) {
            ((Injector.Object) safeGet(t, Injector.Object.DEFAULT)).restore(t, bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public <T extends View> Parcelable saveInstanceState(T t, Parcelable parcelable) {
        return ((Injector.View) safeGet(t, Injector.View.DEFAULT)).save(t, parcelable);
    }

    /* access modifiers changed from: package-private */
    public <T extends View> Parcelable restoreInstanceState(T t, Parcelable parcelable) {
        if (parcelable != null) {
            return ((Injector.View) safeGet(t, Injector.View.DEFAULT)).restore(t, parcelable);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setEnabledForAllActivitiesAndSupportFragments(Application application, boolean z) {
        if (AndroidLifecycleCallbacks.INSTANCE.mEnabled != z) {
            if (z) {
                application.registerActivityLifecycleCallbacks(AndroidLifecycleCallbacks.INSTANCE);
            } else {
                application.unregisterActivityLifecycleCallbacks(AndroidLifecycleCallbacks.INSTANCE);
            }
            AndroidLifecycleCallbacks.INSTANCE.mEnabled = z;
        }
    }
}
