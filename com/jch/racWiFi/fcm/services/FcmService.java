package com.jch.racWiFi.fcm.services;

import android.app.Service;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.accord.common.utils.Logger;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import com.jch.racWiFi.p010di.util.Constants;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import org.json.JSONObject;

public class FcmService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessageService";
    @Inject
    DeepLinkFactory mDeepLinkFactory;
    @Inject
    NotificationBuilder mNotificationBuilder;
    @Inject
    ModelRepository modelRepository;

    public void onCreate() {
        AndroidInjection.inject((Service) this);
        super.onCreate();
    }

    public void onNewToken(String str) {
        Logger.m45d(TAG, "Refreshed token: " + str);
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "Payload - " + new JSONObject(remoteMessage.getData()).toString());
        if (remoteMessage.getData().size() > 0) {
            this.mNotificationBuilder.filter(remoteMessage, this.modelRepository, this.mDeepLinkFactory, getBaseContext());
        }
        remoteMessage.getNotification();
        sendRemoteMessage(remoteMessage);
    }

    private void sendRemoteMessage(RemoteMessage remoteMessage) {
        Intent intent = new Intent(Constants.FCM.REMOTE_MESSAGE_BROADCAST_RECEIVER);
        intent.putExtra(Constants.FCM.REMOTE_MESSAGE, remoteMessage);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
