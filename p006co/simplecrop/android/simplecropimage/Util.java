package p006co.simplecrop.android.simplecropimage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import com.jch.racWiFi.C1662R2;
import java.io.Closeable;
import p006co.simplecrop.android.simplecropimage.MonitoredActivity;

/* renamed from: co.simplecrop.android.simplecropimage.Util */
public class Util {
    private static final String TAG = "db.Util";

    private Util() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap transform(android.graphics.Matrix r14, android.graphics.Bitmap r15, int r16, int r17, boolean r18) {
        /*
            r0 = r14
            r7 = r15
            r8 = r16
            r9 = r17
            int r1 = r15.getWidth()
            int r1 = r1 - r8
            int r2 = r15.getHeight()
            int r2 = r2 - r9
            r3 = 0
            r10 = 0
            if (r18 != 0) goto L_0x0061
            if (r1 < 0) goto L_0x0018
            if (r2 >= 0) goto L_0x0061
        L_0x0018:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r8, r9, r0)
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r0)
            int r1 = r1 / 2
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r2 / 2
            int r2 = java.lang.Math.max(r10, r2)
            android.graphics.Rect r5 = new android.graphics.Rect
            int r6 = r15.getWidth()
            int r6 = java.lang.Math.min(r8, r6)
            int r6 = r6 + r1
            int r10 = r15.getHeight()
            int r10 = java.lang.Math.min(r9, r10)
            int r10 = r10 + r2
            r5.<init>(r1, r2, r6, r10)
            int r1 = r5.width()
            int r1 = r8 - r1
            int r1 = r1 / 2
            int r2 = r5.height()
            int r2 = r9 - r2
            int r2 = r2 / 2
            android.graphics.Rect r6 = new android.graphics.Rect
            int r8 = r8 - r1
            int r9 = r9 - r2
            r6.<init>(r1, r2, r8, r9)
            r4.drawBitmap(r15, r5, r6, r3)
            return r0
        L_0x0061:
            int r1 = r15.getWidth()
            float r1 = (float) r1
            int r2 = r15.getHeight()
            float r2 = (float) r2
            float r4 = r1 / r2
            float r5 = (float) r8
            float r6 = (float) r9
            float r11 = r5 / r6
            r12 = 1065353216(0x3f800000, float:1.0)
            r13 = 1063675494(0x3f666666, float:0.9)
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x008a
            float r6 = r6 / r2
            int r1 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r1 < 0) goto L_0x0086
            int r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r1 <= 0) goto L_0x0084
            goto L_0x0086
        L_0x0084:
            r0 = r3
            goto L_0x0099
        L_0x0086:
            r14.setScale(r6, r6)
            goto L_0x0099
        L_0x008a:
            float r5 = r5 / r1
            int r1 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r1 < 0) goto L_0x0096
            int r1 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r1 <= 0) goto L_0x0094
            goto L_0x0096
        L_0x0094:
            r5 = r3
            goto L_0x009a
        L_0x0096:
            r14.setScale(r5, r5)
        L_0x0099:
            r5 = r0
        L_0x009a:
            if (r5 == 0) goto L_0x00ad
            r1 = 0
            r2 = 0
            int r3 = r15.getWidth()
            int r4 = r15.getHeight()
            r6 = 1
            r0 = r15
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x00ae
        L_0x00ad:
            r0 = r7
        L_0x00ae:
            int r1 = r0.getWidth()
            int r1 = r1 - r8
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r0.getHeight()
            int r2 = r2 - r9
            int r2 = java.lang.Math.max(r10, r2)
            int r1 = r1 / 2
            int r2 = r2 / 2
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r8, r9)
            if (r0 == r7) goto L_0x00cd
            r0.recycle()
        L_0x00cd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p006co.simplecrop.android.simplecropimage.Util.transform(android.graphics.Matrix, android.graphics.Bitmap, int, int, boolean):android.graphics.Bitmap");
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: co.simplecrop.android.simplecropimage.Util$BackgroundJob */
    private static class BackgroundJob extends MonitoredActivity.LifeCycleAdapter implements Runnable {
        /* access modifiers changed from: private */
        public final MonitoredActivity mActivity;
        private final Runnable mCleanupRunner = new Runnable() {
            public void run() {
                BackgroundJob.this.mActivity.removeLifeCycleListener(BackgroundJob.this);
                if (BackgroundJob.this.mDialog.getWindow() != null) {
                    BackgroundJob.this.mDialog.dismiss();
                }
            }
        };
        /* access modifiers changed from: private */
        public final ProgressDialog mDialog;
        private final Handler mHandler;
        private final Runnable mJob;

        public BackgroundJob(MonitoredActivity monitoredActivity, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
            this.mActivity = monitoredActivity;
            this.mDialog = progressDialog;
            this.mJob = runnable;
            monitoredActivity.addLifeCycleListener(this);
            this.mHandler = handler;
        }

        public void run() {
            try {
                this.mJob.run();
            } finally {
                this.mHandler.post(this.mCleanupRunner);
            }
        }

        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
            this.mCleanupRunner.run();
            this.mHandler.removeCallbacks(this.mCleanupRunner);
        }

        public void onActivityStopped(MonitoredActivity monitoredActivity) {
            this.mDialog.hide();
        }

        public void onActivityStarted(MonitoredActivity monitoredActivity) {
            this.mDialog.show();
        }
    }

    public static void startBackgroundJob(MonitoredActivity monitoredActivity, String str, String str2, Runnable runnable, Handler handler) {
        new Thread(new BackgroundJob(monitoredActivity, runnable, ProgressDialog.show(monitoredActivity, str, str2, true, false), handler)).start();
    }

    public static BitmapFactory.Options createNativeAllocOptions() {
        return new BitmapFactory.Options();
    }

    public static Bitmap rotateImage(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int getOrientationInDegree(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 0) {
            return 0;
        }
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return C1662R2.attr.boxCornerRadiusTopStart;
        }
        if (rotation != 3) {
            return 0;
        }
        return C1662R2.attr.circleCrop;
    }
}
