package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import com.google.firebase.iid.Metadata;
import com.jch.racWiFi.C1662R2;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
class TopicsSyncTask implements Runnable {
    private static final Object TOPIC_SYNC_TASK_LOCK = new Object();
    private static Boolean hasAccessNetworkStatePermission;
    private static Boolean hasWakeLockPermission;
    /* access modifiers changed from: private */
    public final Context context;
    private final Metadata metadata;
    private final long nextDelaySeconds;
    private final PowerManager.WakeLock syncWakeLock;
    /* access modifiers changed from: private */
    public final TopicsSubscriber topicsSubscriber;

    TopicsSyncTask(TopicsSubscriber topicsSubscriber2, Context context2, Metadata metadata2, long j) {
        this.topicsSubscriber = topicsSubscriber2;
        this.context = context2;
        this.nextDelaySeconds = j;
        this.metadata = metadata2;
        this.syncWakeLock = ((PowerManager) context2.getSystemService("power")).newWakeLock(1, Constants.FCM_WAKE_LOCK);
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
    class ConnectivityChangeReceiver extends BroadcastReceiver {
        private TopicsSyncTask task;

        public ConnectivityChangeReceiver(TopicsSyncTask topicsSyncTask) {
            this.task = topicsSyncTask;
        }

        public synchronized void onReceive(Context context, Intent intent) {
            TopicsSyncTask topicsSyncTask = this.task;
            if (topicsSyncTask != null) {
                if (topicsSyncTask.isDeviceConnected()) {
                    if (TopicsSyncTask.isLoggable()) {
                        Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
                    }
                    this.task.topicsSubscriber.scheduleSyncTaskWithDelaySeconds(this.task, 0);
                    context.unregisterReceiver(this);
                    this.task = null;
                }
            }
        }

        public void registerReceiver() {
            if (TopicsSyncTask.isLoggable()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            TopicsSyncTask.this.context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public void run() {
        if (hasWakeLockPermission(this.context)) {
            this.syncWakeLock.acquire(Constants.WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
        }
        try {
            this.topicsSubscriber.setSyncScheduledOrRunning(true);
            if (!this.metadata.isGmscorePresent()) {
                this.topicsSubscriber.setSyncScheduledOrRunning(false);
                if (hasWakeLockPermission(this.context)) {
                    try {
                        this.syncWakeLock.release();
                    } catch (RuntimeException unused) {
                        Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                    }
                }
            } else if (!hasAccessNetworkStatePermission(this.context) || isDeviceConnected()) {
                if (this.topicsSubscriber.syncTopics()) {
                    this.topicsSubscriber.setSyncScheduledOrRunning(false);
                } else {
                    this.topicsSubscriber.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                }
                if (hasWakeLockPermission(this.context)) {
                    try {
                        this.syncWakeLock.release();
                    } catch (RuntimeException unused2) {
                        Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                    }
                }
            } else {
                new ConnectivityChangeReceiver(this).registerReceiver();
                if (hasWakeLockPermission(this.context)) {
                    try {
                        this.syncWakeLock.release();
                    } catch (RuntimeException unused3) {
                        Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                    }
                }
            }
        } catch (IOException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e(Constants.TAG, valueOf.length() != 0 ? "Failed to sync topics. Won't retry sync. ".concat(valueOf) : new String("Failed to sync topics. Won't retry sync. "));
            this.topicsSubscriber.setSyncScheduledOrRunning(false);
            if (hasWakeLockPermission(this.context)) {
                try {
                    this.syncWakeLock.release();
                } catch (RuntimeException unused4) {
                    Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                }
            }
        } catch (Throwable th) {
            if (hasWakeLockPermission(this.context)) {
                try {
                    this.syncWakeLock.release();
                } catch (RuntimeException unused5) {
                    Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public synchronized boolean isDeviceConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* access modifiers changed from: private */
    public static boolean isLoggable() {
        if (!Log.isLoggable(Constants.TAG, 3)) {
            return Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3);
        }
        return true;
    }

    private static boolean hasWakeLockPermission(Context context2) {
        boolean z;
        boolean booleanValue;
        synchronized (TOPIC_SYNC_TASK_LOCK) {
            Boolean bool = hasWakeLockPermission;
            if (bool == null) {
                z = hasPermission(context2, "android.permission.WAKE_LOCK", bool);
            } else {
                z = bool.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(z);
            hasWakeLockPermission = valueOf;
            booleanValue = valueOf.booleanValue();
        }
        return booleanValue;
    }

    private static boolean hasAccessNetworkStatePermission(Context context2) {
        boolean z;
        boolean booleanValue;
        synchronized (TOPIC_SYNC_TASK_LOCK) {
            Boolean bool = hasAccessNetworkStatePermission;
            if (bool == null) {
                z = hasPermission(context2, "android.permission.ACCESS_NETWORK_STATE", bool);
            } else {
                z = bool.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(z);
            hasAccessNetworkStatePermission = valueOf;
            booleanValue = valueOf.booleanValue();
        }
        return booleanValue;
    }

    private static boolean hasPermission(Context context2, String str, Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = context2.checkCallingOrSelfPermission(str) == 0;
        if (!z && Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, createPermissionMissingLog(str));
        }
        return z;
    }

    private static String createPermissionMissingLog(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + C1662R2.attr.badgeStyle);
        sb.append("Missing Permission: ");
        sb.append(str);
        sb.append(". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        return sb.toString();
    }
}
