package com.jch.racWiFi.fcm.dashboard;

import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FcmDashboard$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ ViewGroup f$0;

    public /* synthetic */ FcmDashboard$$ExternalSyntheticLambda0(ViewGroup viewGroup) {
        this.f$0 = viewGroup;
    }

    public final void onClick(View view) {
        ((HomePageActivity) this.f$0.getContext()).clearNotificationDrawer();
    }
}
