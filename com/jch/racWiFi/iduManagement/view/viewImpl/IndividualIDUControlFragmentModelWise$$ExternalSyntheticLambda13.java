package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.iduManagement.viewModel.TimerViewModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda13 implements Observer {
    public final /* synthetic */ IndividualIDUControlFragmentModelWise f$0;
    public final /* synthetic */ TimerViewModel f$1;

    public /* synthetic */ IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda13(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, TimerViewModel timerViewModel) {
        this.f$0 = individualIDUControlFragmentModelWise;
        this.f$1 = timerViewModel;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30944xb1ce385(this.f$1, (TimerModels.TimerFetchResponse) obj);
    }
}
