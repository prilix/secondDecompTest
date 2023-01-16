package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.Task;
import com.jch.racWiFi.App;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils.DeepLinkHandleUtils;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch_hitachi.aircloudglobal.R;

public class BootCompleteJobIntentService extends JobIntentService {
    public static final int JOB_ID = 1;
    private GoogleGeoFenceApiExtension mGoogleGeoFenceApiExtension;

    public GoogleGeoFenceApiExtension getGoogleGeoFenceApiExtension() {
        return this.mGoogleGeoFenceApiExtension;
    }

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, (Class<?>) BootCompleteJobIntentService.class, 1, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        Log.i("global_boot", "onHandleWork");
        App.initialize(getBaseContext());
        this.mGoogleGeoFenceApiExtension = new GoogleGeoFenceApiExtension(getApplicationContext());
        this.mGoogleGeoFenceApiExtension.setFamilyIdGeoFenceDataMap(FamilyIdGeoFenceDataMap.getInstanceFromPreference());
        App.initialize(getApplicationContext());
        getGoogleGeoFenceApiExtension().removeGeofences(new BootCompleteJobIntentService$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$onHandleWork$1$com-jch-racWiFi-iduManagement-smartFence-geoFenceApi-BootCompleteJobIntentService */
    public /* synthetic */ void mo30068xefd91c7f(Task task) {
        getGoogleGeoFenceApiExtension().onComplete(task);
        Log.i("global_boot", "removeGeofences");
        if (task.isSuccessful()) {
            Log.i("global_boot", "task.isSuccessful()");
            if (TokenUtil.getInstance().isValid()) {
                Log.i("global_boot", "TokenData.isCurrentTokenDataValid()");
                getGoogleGeoFenceApiExtension().addGeoFencesAll(new BootCompleteJobIntentService$$ExternalSyntheticLambda0(this));
                return;
            }
            Log.i("global_boot", "else isCurrentTokenDataValid");
            return;
        }
        Log.i("global_boot", "else task successfull");
    }

    /* renamed from: lambda$onHandleWork$0$com-jch-racWiFi-iduManagement-smartFence-geoFenceApi-BootCompleteJobIntentService */
    public /* synthetic */ void mo30067x485d42be(Task task) {
        Log.i("global_boot", "add allgeofences");
        getGoogleGeoFenceApiExtension().onComplete(task);
        String string = getString(R.string.notification_lbl_restoreGeoFenceComplete);
        if (task.isSuccessful()) {
            Log.i("global_boot", "task1_success");
            sendNotification(getApplicationContext(), R.drawable.ic_smart_fence_notification_icon, getBaseContext().getResources().getString(R.string.smartFence_lbl_smartFence), string, string.hashCode(), DeepLinkHandleUtils.getPendingIntentForNotification(getBaseContext()));
            return;
        }
        Log.i("global_boot", "else task1_success");
    }

    private void sendNotification(Context context, String str) {
        PendingIntent pendingIntent;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("000007", context.getString(R.string.app_name), 3));
        }
        Intent intent = new Intent(context.getApplicationContext(), HomePageActivity.class);
        TaskStackBuilder create = TaskStackBuilder.create(context);
        create.addParentStack((Class<?>) HomePageActivity.class);
        create.addNextIntent(intent);
        if (Build.VERSION.SDK_INT >= 23) {
            pendingIntent = create.getPendingIntent(0, 201326592);
        } else {
            pendingIntent = create.getPendingIntent(0, 134217728);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon((int) R.mipmap.ic_launcher_global).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_global)).setContentTitle(str).setContentText(context.getString(R.string.geofence_transition_notification_text)).setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("000007");
        }
        builder.setAutoCancel(true);
        notificationManager.notify(0, builder.build());
    }

    public static void sendNotification(Context context, int i, CharSequence charSequence, CharSequence charSequence2, int i2, PendingIntent pendingIntent) {
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("000007", context.getString(R.string.app_name), 3));
        }
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon((int) R.mipmap.ic_launcher_global).setColor(context.getResources().getColor(R.color.white)).setContentTitle(charSequence).setContentText(charSequence2).setContentIntent(pendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText(charSequence2));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("000007");
        }
        final int i3 = i2;
        Glide.with(context).asBitmap().load(Integer.valueOf(i)).into(new CustomTarget<Bitmap>(64, 64) {
            public void onLoadCleared(Drawable drawable) {
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                builder.setLargeIcon(BootCompleteJobIntentService.createSquaredBitmap(bitmap));
                builder.setAutoCancel(true);
                notificationManager.notify(i3, builder.build());
            }
        });
    }

    /* access modifiers changed from: private */
    public static Bitmap createSquaredBitmap(Bitmap bitmap) {
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        canvas.drawBitmap(bitmap, (float) ((max - bitmap.getWidth()) / 2), (float) ((max - bitmap.getHeight()) / 2), (Paint) null);
        return createBitmap;
    }
}
