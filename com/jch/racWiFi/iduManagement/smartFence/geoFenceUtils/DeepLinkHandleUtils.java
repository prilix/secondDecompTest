package com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.TaskStackBuilder;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;

public class DeepLinkHandleUtils {
    public static PendingIntent getPendingIntentForNotificationWithDeepLink(Context context, Bundle bundle) {
        Intent intent = new Intent(context.getApplicationContext(), HomePageActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        TaskStackBuilder.create(context).addNextIntentWithParentStack(intent);
        intent.setFlags(268468224);
        if (Build.VERSION.SDK_INT >= 23) {
            return PendingIntent.getActivity(context, 0, intent, 201326592);
        }
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    public static PendingIntent getPendingIntentForNotification(Context context) {
        return getPendingIntentForNotificationWithDeepLink(context, (Bundle) null);
    }
}
