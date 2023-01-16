package com.evernote.android.state;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

final class AndroidLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    static final AndroidLifecycleCallbacks INSTANCE = new AndroidLifecycleCallbacks();
    boolean mEnabled;

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    private AndroidLifecycleCallbacks() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.mEnabled) {
            StateSaver.restoreInstanceState(activity, bundle);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this, true);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (this.mEnabled) {
            StateSaver.saveInstanceState(activity, bundle);
        }
    }

    public void onFragmentPreCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        if (this.mEnabled) {
            StateSaver.restoreInstanceState(fragment, bundle);
        }
    }

    public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        if (this.mEnabled) {
            StateSaver.saveInstanceState(fragment, bundle);
        }
    }
}
