package com.jch.racWiFi.iduManagement.presenter.presenterImpl;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePagePresenterImpl$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ HomePagePresenterImpl f$0;
    public final /* synthetic */ LifecycleOwner f$1;

    public /* synthetic */ HomePagePresenterImpl$$ExternalSyntheticLambda0(HomePagePresenterImpl homePagePresenterImpl, LifecycleOwner lifecycleOwner) {
        this.f$0 = homePagePresenterImpl;
        this.f$1 = lifecycleOwner;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30055xda53bc9c(this.f$1, (StopAllUnitsModels.StopAllnitsSuccessResponse) obj);
    }
}
