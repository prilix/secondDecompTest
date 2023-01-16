package p006co.simplecrop.android.simplecropimage;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: co.simplecrop.android.simplecropimage.MonitoredActivity */
public class MonitoredActivity extends Activity {
    private final ArrayList<LifeCycleListener> mListeners = new ArrayList<>();

    /* renamed from: co.simplecrop.android.simplecropimage.MonitoredActivity$LifeCycleAdapter */
    public static class LifeCycleAdapter implements LifeCycleListener {
        public void onActivityCreated(MonitoredActivity monitoredActivity) {
        }

        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
        }

        public void onActivityPaused(MonitoredActivity monitoredActivity) {
        }

        public void onActivityResumed(MonitoredActivity monitoredActivity) {
        }

        public void onActivityStarted(MonitoredActivity monitoredActivity) {
        }

        public void onActivityStopped(MonitoredActivity monitoredActivity) {
        }
    }

    /* renamed from: co.simplecrop.android.simplecropimage.MonitoredActivity$LifeCycleListener */
    public interface LifeCycleListener {
        void onActivityCreated(MonitoredActivity monitoredActivity);

        void onActivityDestroyed(MonitoredActivity monitoredActivity);

        void onActivityPaused(MonitoredActivity monitoredActivity);

        void onActivityResumed(MonitoredActivity monitoredActivity);

        void onActivityStarted(MonitoredActivity monitoredActivity);

        void onActivityStopped(MonitoredActivity monitoredActivity);
    }

    public void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        if (!this.mListeners.contains(lifeCycleListener)) {
            this.mListeners.add(lifeCycleListener);
        }
    }

    public void removeLifeCycleListener(LifeCycleListener lifeCycleListener) {
        this.mListeners.remove(lifeCycleListener);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator<LifeCycleListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityCreated(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Iterator<LifeCycleListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityDestroyed(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Iterator<LifeCycleListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityStarted(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Iterator<LifeCycleListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityStopped(this);
        }
    }
}
