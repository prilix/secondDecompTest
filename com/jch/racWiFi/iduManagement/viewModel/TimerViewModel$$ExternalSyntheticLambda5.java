package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.model.TimerModels;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TimerViewModel$$ExternalSyntheticLambda5 implements Observer {
    public final /* synthetic */ TimerViewModel f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ TimerViewModel$$ExternalSyntheticLambda5(TimerViewModel timerViewModel, boolean z) {
        this.f$0 = timerViewModel;
        this.f$1 = z;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo31180x2944df3a(this.f$1, (TimerModels.TimerFetchResponse) obj);
    }
}
