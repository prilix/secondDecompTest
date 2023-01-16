package com.jch.racWiFi.fcm.builder;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jch.racWiFi.fcm.builder.NotificationBuilder;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GoogleGeoFenceApiExtension;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NotificationBuilder$1$2$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ GoogleGeoFenceApiExtension f$0;

    public /* synthetic */ NotificationBuilder$1$2$$ExternalSyntheticLambda0(GoogleGeoFenceApiExtension googleGeoFenceApiExtension) {
        this.f$0 = googleGeoFenceApiExtension;
    }

    public final void onComplete(Task task) {
        NotificationBuilder.C18131.C18152.lambda$onResponse$1(this.f$0, task);
    }
}
