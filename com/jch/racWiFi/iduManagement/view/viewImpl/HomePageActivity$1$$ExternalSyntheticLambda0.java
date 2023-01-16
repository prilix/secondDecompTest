package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.app.Dialog;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePageActivity$1$$ExternalSyntheticLambda0 implements SingleChoiceDialog.CustomOnClickListener {
    public final /* synthetic */ HomePageActivity.C20741 f$0;
    public final /* synthetic */ WebSocketNotification f$1;

    public /* synthetic */ HomePageActivity$1$$ExternalSyntheticLambda0(HomePageActivity.C20741 r1, WebSocketNotification webSocketNotification) {
        this.f$0 = r1;
        this.f$1 = webSocketNotification;
    }

    public final boolean onButtonClickListener(Dialog dialog, View view) {
        return this.f$0.mo30772x2b175fec(this.f$1, dialog, view);
    }
}
