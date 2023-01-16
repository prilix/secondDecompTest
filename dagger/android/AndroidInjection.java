package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import dagger.internal.Preconditions;

public final class AndroidInjection {
    private static final String TAG = "dagger.android";

    public static void inject(Activity activity) {
        Preconditions.checkNotNull(activity, "activity");
        Application application = activity.getApplication();
        if (application instanceof HasAndroidInjector) {
            inject((Object) activity, (HasAndroidInjector) application);
        } else {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()}));
        }
    }

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasAndroidInjector findHasAndroidInjectorForFragment = findHasAndroidInjectorForFragment(fragment);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), findHasAndroidInjectorForFragment.getClass().getCanonicalName()}));
        }
        inject((Object) fragment, findHasAndroidInjectorForFragment);
    }

    private static HasAndroidInjector findHasAndroidInjectorForFragment(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                Activity activity = fragment.getActivity();
                if (activity instanceof HasAndroidInjector) {
                    return (HasAndroidInjector) activity;
                }
                if (activity.getApplication() instanceof HasAndroidInjector) {
                    return (HasAndroidInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[]{fragment.getClass().getCanonicalName()}));
            }
        } while (!(fragment2 instanceof HasAndroidInjector));
        return (HasAndroidInjector) fragment2;
    }

    public static void inject(Service service) {
        Preconditions.checkNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        Application application = service.getApplication();
        if (application instanceof HasAndroidInjector) {
            inject((Object) service, (HasAndroidInjector) application);
        } else {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()}));
        }
    }

    public static void inject(BroadcastReceiver broadcastReceiver, Context context) {
        Preconditions.checkNotNull(broadcastReceiver, "broadcastReceiver");
        Preconditions.checkNotNull(context, "context");
        Application application = (Application) context.getApplicationContext();
        if (application instanceof HasAndroidInjector) {
            inject((Object) broadcastReceiver, (HasAndroidInjector) application);
        } else {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()}));
        }
    }

    public static void inject(ContentProvider contentProvider) {
        Preconditions.checkNotNull(contentProvider, "contentProvider");
        Application application = (Application) contentProvider.getContext().getApplicationContext();
        if (application instanceof HasAndroidInjector) {
            inject((Object) contentProvider, (HasAndroidInjector) application);
        } else {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()}));
        }
    }

    private static void inject(Object obj, HasAndroidInjector hasAndroidInjector) {
        AndroidInjector<Object> androidInjector = hasAndroidInjector.androidInjector();
        Preconditions.checkNotNull(androidInjector, "%s.androidInjector() returned null", hasAndroidInjector.getClass());
        androidInjector.inject(obj);
    }

    private AndroidInjection() {
    }
}
