package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.internal.view.SupportMenu;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.gson.Gson;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceOccupancyModel;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceTransitionsJobIntentService extends JobIntentService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CHANNEL_ID = "000007";
    private static final int JOB_ID = 573;
    private static final String TAG = "GeofenceTransitionsIS";

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, (Class<?>) GeoFenceTransitionsJobIntentService.class, 573, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        GeofencingEvent fromIntent = GeofencingEvent.fromIntent(intent);
        if (fromIntent.hasError()) {
            Toast.makeText(getApplicationContext(), GeofenceErrorMessages.getErrorString((Context) this, fromIntent.getErrorCode()), 1).show();
            return;
        }
        int geofenceTransition = fromIntent.getGeofenceTransition();
        if (geofenceTransition == 1 || geofenceTransition == 2) {
            List<Geofence> triggeringGeofences = fromIntent.getTriggeringGeofences();
            Log.i(TAG, getGeofenceTransitionDetails(geofenceTransition, triggeringGeofences));
            for (Geofence requestId : triggeringGeofences) {
                String requestId2 = requestId.getRequestId();
                String str = requestId2.split("_")[1];
                Log.i(TAG, requestId2);
                GeoFenceOccupancyModel.GeoFenceOccupancyStatus geoFenceOccupancyStatus = null;
                if (geofenceTransition == 1) {
                    geoFenceOccupancyStatus = GeoFenceOccupancyModel.GeoFenceOccupancyStatus.OCCUPIED;
                } else if (geofenceTransition == 2) {
                    geoFenceOccupancyStatus = GeoFenceOccupancyModel.GeoFenceOccupancyStatus.UNOCCUPIED;
                }
                GeoFenceOccupancyModel.GeoFenceOccupancyStatus.getCurrentOccupancyFromPreference();
                GeoFenceOccupancyModel geoFenceOccupancyModel = new GeoFenceOccupancyModel();
                geoFenceOccupancyModel.setLocationControlsSettingsId(Long.valueOf(Long.parseLong(str)));
                geoFenceOccupancyModel.setGeofenceOccupancyStatus(geoFenceOccupancyStatus);
                String json = new Gson().toJson((Object) geoFenceOccupancyModel);
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString("GeoFenceOccupancyModel", json);
                ((JobScheduler) getSystemService("jobscheduler")).schedule(new JobInfo.Builder((str + geoFenceOccupancyModel.getGeofenceOccupancyStatus()).hashCode(), new ComponentName(this, GeoFenceTransitionNetworkJobService.class)).setExtras(persistableBundle).setRequiredNetworkType(1).build());
            }
            return;
        }
        Log.e(TAG, getString(R.string.geofence_transition_invalid_type, new Object[]{Integer.valueOf(geofenceTransition)}));
    }

    private String getGeofenceTransitionDetails(int i, List<Geofence> list) {
        String transitionString = getTransitionString(i);
        ArrayList arrayList = new ArrayList();
        for (Geofence requestId : list) {
            arrayList.add(requestId.getRequestId());
        }
        TextUtils.join(", ", arrayList);
        return transitionString;
    }

    private void sendNotification(String str) {
        PendingIntent pendingIntent;
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, getString(R.string.app_name), 3));
        }
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        TaskStackBuilder create = TaskStackBuilder.create(this);
        create.addParentStack((Class<?>) HomePageActivity.class);
        create.addNextIntent(intent);
        if (Build.VERSION.SDK_INT >= 23) {
            pendingIntent = create.getPendingIntent(0, 201326592);
        } else {
            pendingIntent = create.getPendingIntent(0, 134217728);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon((int) R.mipmap.ic_launcher_global).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_global)).setColor(SupportMenu.CATEGORY_MASK).setContentTitle(str).setContentText("This notification is for DEBUG purpose only. Will not be shown in Production build").setContentIntent(pendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText("This notification is for DEBUG purpose only. Will not be shown in Production build"));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(CHANNEL_ID);
        }
        builder.setAutoCancel(true);
        notificationManager.notify(0, builder.build());
    }

    private String getTransitionString(int i) {
        if (i == 1) {
            return getString(R.string.geofence_transition_entered);
        }
        if (i != 2) {
            return getString(R.string.unknown_geofence_transition);
        }
        return getString(R.string.geofence_transition_exited);
    }
}
