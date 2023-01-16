package com.jch.racWiFi.fcm.util;

import android.view.View;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Binder$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ Binder f$0;
    public final /* synthetic */ BannerListener f$1;
    public final /* synthetic */ DetailedIduModel.IduErrorStatus f$2;

    public /* synthetic */ Binder$$ExternalSyntheticLambda2(Binder binder, BannerListener bannerListener, DetailedIduModel.IduErrorStatus iduErrorStatus) {
        this.f$0 = binder;
        this.f$1 = bannerListener;
        this.f$2 = iduErrorStatus;
    }

    public final void onClick(View view) {
        this.f$0.m947lambda$inflate$0$comjchracWiFifcmutilBinder(this.f$1, this.f$2, view);
    }
}
