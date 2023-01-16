package com.github.druk.dnssd;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class DNSSDEmbedded extends DNSSD {
    public static final int DEFAULT_STOP_TIMER_DELAY = 5000;
    private static final String TAG = "DNSSDEmbedded";
    private final Handler handler;
    /* access modifiers changed from: private */
    public volatile boolean isStarted;
    private final long mStopTimerDelay;
    private Thread mThread;
    private int serviceCount;

    static native void nativeExit();

    static native int nativeInit();

    static native int nativeLoop();

    public DNSSDEmbedded(Context context) {
        this(context, 5000);
    }

    public DNSSDEmbedded(Context context, long j) {
        super(context, "jdns_sd_embedded");
        this.handler = new Handler(Looper.getMainLooper());
        this.isStarted = false;
        this.serviceCount = 0;
        this.mStopTimerDelay = j;
    }

    public void init() {
        this.handler.removeCallbacks(DNSSDEmbedded$$ExternalSyntheticLambda0.INSTANCE);
        Thread thread = this.mThread;
        if (thread == null || !thread.isAlive()) {
            this.isStarted = false;
            InternalDNSSD.getInstance();
            C09841 r0 = new Thread() {
                public void run() {
                    Log.i(DNSSDEmbedded.TAG, "init");
                    int nativeInit = DNSSDEmbedded.nativeInit();
                    synchronized (DNSSDEmbedded.class) {
                        boolean unused = DNSSDEmbedded.this.isStarted = true;
                        DNSSDEmbedded.class.notifyAll();
                    }
                    if (nativeInit != 0) {
                        Log.e(DNSSDEmbedded.TAG, "error: " + nativeInit);
                        return;
                    }
                    Log.i(DNSSDEmbedded.TAG, "start");
                    int nativeLoop = DNSSDEmbedded.nativeLoop();
                    boolean unused2 = DNSSDEmbedded.this.isStarted = false;
                    Log.i(DNSSDEmbedded.TAG, "finish with code: " + nativeLoop);
                }
            };
            this.mThread = r0;
            r0.setPriority(10);
            this.mThread.setName("DNS-SDEmbedded");
            this.mThread.start();
            waitUntilStarted();
            return;
        }
        Log.i(TAG, "already started");
        waitUntilStarted();
    }

    public void exit() {
        synchronized (DNSSDEmbedded.class) {
            Log.i(TAG, "post exit");
            this.handler.postDelayed(DNSSDEmbedded$$ExternalSyntheticLambda0.INSTANCE, this.mStopTimerDelay);
        }
    }

    private void waitUntilStarted() {
        Class<DNSSDEmbedded> cls = DNSSDEmbedded.class;
        synchronized (cls) {
            while (!this.isStarted) {
                try {
                    cls.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "waitUntilStarted exception: ", e);
                }
            }
        }
    }

    public void onServiceStarting() {
        super.onServiceStarting();
        init();
        this.serviceCount++;
    }

    public void onServiceStopped() {
        super.onServiceStopped();
        int i = this.serviceCount - 1;
        this.serviceCount = i;
        if (i == 0) {
            exit();
        }
    }
}
