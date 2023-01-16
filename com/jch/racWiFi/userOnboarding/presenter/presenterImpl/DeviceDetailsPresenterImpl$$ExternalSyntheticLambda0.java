package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import retrofit2.Response;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceDetailsPresenterImpl$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ DeviceDetailsPresenterImpl f$0;
    public final /* synthetic */ IduDetailsModel f$1;

    public /* synthetic */ DeviceDetailsPresenterImpl$$ExternalSyntheticLambda0(DeviceDetailsPresenterImpl deviceDetailsPresenterImpl, IduDetailsModel iduDetailsModel) {
        this.f$0 = deviceDetailsPresenterImpl;
        this.f$1 = iduDetailsModel;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo33465x6f2fc7a(this.f$1, (Response) obj);
    }
}
