package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import com.jch.racWiFi.fcm.standard.HandlerListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda8 implements HandlerListener {
    public static final /* synthetic */ IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda8 INSTANCE = new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda8();

    private /* synthetic */ IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda8() {
    }

    public final void onDelay(long j, Object obj) {
        new Handler(Looper.getMainLooper()).postDelayed(new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda9((FrameLayout) obj), j);
    }
}
