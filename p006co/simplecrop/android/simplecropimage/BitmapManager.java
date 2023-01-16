package p006co.simplecrop.android.simplecropimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.accord.common.utils.Logger;
import java.io.FileDescriptor;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: co.simplecrop.android.simplecropimage.BitmapManager */
public class BitmapManager {
    private static final String TAG = "BitmapManager";
    private static BitmapManager sManager;
    private final WeakHashMap<Thread, ThreadStatus> mThreadStatus = new WeakHashMap<>();

    /* renamed from: co.simplecrop.android.simplecropimage.BitmapManager$State */
    private enum State {
        CANCEL,
        ALLOW
    }

    /* renamed from: co.simplecrop.android.simplecropimage.BitmapManager$ThreadStatus */
    private static class ThreadStatus {
        public BitmapFactory.Options mOptions;
        public State mState;

        private ThreadStatus() {
            this.mState = State.ALLOW;
        }

        public String toString() {
            String str;
            if (this.mState == State.CANCEL) {
                str = "Cancel";
            } else {
                str = this.mState == State.ALLOW ? "Allow" : "?";
            }
            return "thread state = " + str + ", options = " + this.mOptions;
        }
    }

    /* renamed from: co.simplecrop.android.simplecropimage.BitmapManager$ThreadSet */
    public static class ThreadSet implements Iterable<Thread> {
        private final WeakHashMap<Thread, Object> mWeakCollection = new WeakHashMap<>();

        public void add(Thread thread) {
            this.mWeakCollection.put(thread, (Object) null);
        }

        public void remove(Thread thread) {
            this.mWeakCollection.remove(thread);
        }

        public Iterator<Thread> iterator() {
            return this.mWeakCollection.keySet().iterator();
        }
    }

    private BitmapManager() {
    }

    private synchronized ThreadStatus getOrCreateThreadStatus(Thread thread) {
        ThreadStatus threadStatus;
        threadStatus = this.mThreadStatus.get(thread);
        if (threadStatus == null) {
            threadStatus = new ThreadStatus();
            this.mThreadStatus.put(thread, threadStatus);
        }
        return threadStatus;
    }

    private synchronized void setDecodingOptions(Thread thread, BitmapFactory.Options options) {
        getOrCreateThreadStatus(thread).mOptions = options;
    }

    /* access modifiers changed from: package-private */
    public synchronized BitmapFactory.Options getDecodingOptions(Thread thread) {
        ThreadStatus threadStatus;
        threadStatus = this.mThreadStatus.get(thread);
        return threadStatus != null ? threadStatus.mOptions : null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeDecodingOptions(Thread thread) {
        this.mThreadStatus.get(thread).mOptions = null;
    }

    public synchronized void allowThreadDecoding(ThreadSet threadSet) {
        Iterator<Thread> it = threadSet.iterator();
        while (it.hasNext()) {
            allowThreadDecoding(it.next());
        }
    }

    public synchronized void cancelThreadDecoding(ThreadSet threadSet) {
        Iterator<Thread> it = threadSet.iterator();
        while (it.hasNext()) {
            cancelThreadDecoding(it.next());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean canThreadDecoding(java.lang.Thread r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.WeakHashMap<java.lang.Thread, co.simplecrop.android.simplecropimage.BitmapManager$ThreadStatus> r0 = r2.mThreadStatus     // Catch:{ all -> 0x0018 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x0018 }
            co.simplecrop.android.simplecropimage.BitmapManager$ThreadStatus r3 = (p006co.simplecrop.android.simplecropimage.BitmapManager.ThreadStatus) r3     // Catch:{ all -> 0x0018 }
            r0 = 1
            if (r3 != 0) goto L_0x000e
            monitor-exit(r2)
            return r0
        L_0x000e:
            co.simplecrop.android.simplecropimage.BitmapManager$State r3 = r3.mState     // Catch:{ all -> 0x0018 }
            co.simplecrop.android.simplecropimage.BitmapManager$State r1 = p006co.simplecrop.android.simplecropimage.BitmapManager.State.CANCEL     // Catch:{ all -> 0x0018 }
            if (r3 == r1) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            monitor-exit(r2)
            return r0
        L_0x0018:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p006co.simplecrop.android.simplecropimage.BitmapManager.canThreadDecoding(java.lang.Thread):boolean");
    }

    public synchronized void allowThreadDecoding(Thread thread) {
        getOrCreateThreadStatus(thread).mState = State.ALLOW;
    }

    public synchronized void cancelThreadDecoding(Thread thread) {
        ThreadStatus orCreateThreadStatus = getOrCreateThreadStatus(thread);
        orCreateThreadStatus.mState = State.CANCEL;
        if (orCreateThreadStatus.mOptions != null) {
            orCreateThreadStatus.mOptions.requestCancelDecode();
        }
        notifyAll();
    }

    public synchronized void dump() {
        for (Map.Entry next : this.mThreadStatus.entrySet()) {
            Logger.m50v(TAG, "[Dump] Thread " + next.getKey() + " (" + ((Thread) next.getKey()).getId() + ")'s status is " + next.getValue());
        }
    }

    public static synchronized BitmapManager instance() {
        BitmapManager bitmapManager;
        synchronized (BitmapManager.class) {
            if (sManager == null) {
                sManager = new BitmapManager();
            }
            bitmapManager = sManager;
        }
        return bitmapManager;
    }

    public Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, BitmapFactory.Options options) {
        if (options.mCancel) {
            return null;
        }
        Thread currentThread = Thread.currentThread();
        if (!canThreadDecoding(currentThread)) {
            return null;
        }
        setDecodingOptions(currentThread, options);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
        removeDecodingOptions(currentThread);
        return decodeFileDescriptor;
    }
}
