package dagger.android.support;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.internal.Preconditions;

public final class AndroidSupportInjection {
    private static final String TAG = "dagger.android.support";

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasAndroidInjector findHasAndroidInjectorForFragment = findHasAndroidInjectorForFragment(fragment);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), findHasAndroidInjectorForFragment.getClass().getCanonicalName()}));
        }
        inject(fragment, findHasAndroidInjectorForFragment);
    }

    private static void inject(Object obj, HasAndroidInjector hasAndroidInjector) {
        AndroidInjector<Object> androidInjector = hasAndroidInjector.androidInjector();
        Preconditions.checkNotNull(androidInjector, "%s.androidInjector() returned null", hasAndroidInjector.getClass());
        androidInjector.inject(obj);
    }

    private static HasAndroidInjector findHasAndroidInjectorForFragment(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                FragmentActivity activity = fragment.getActivity();
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

    private AndroidSupportInjection() {
    }
}
