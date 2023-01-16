package com.jch.racWiFi.iduManagement.presenter.presenterImpl;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import retrofit2.Response;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePagePresenterImpl$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ HomePagePresenterImpl f$0;
    public final /* synthetic */ DetailedIduModel f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ LifecycleOwner f$3;

    public /* synthetic */ HomePagePresenterImpl$$ExternalSyntheticLambda1(HomePagePresenterImpl homePagePresenterImpl, DetailedIduModel detailedIduModel, String str, LifecycleOwner lifecycleOwner) {
        this.f$0 = homePagePresenterImpl;
        this.f$1 = detailedIduModel;
        this.f$2 = str;
        this.f$3 = lifecycleOwner;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30056x2dc379ad(this.f$1, this.f$2, this.f$3, (Response) obj);
    }
}
