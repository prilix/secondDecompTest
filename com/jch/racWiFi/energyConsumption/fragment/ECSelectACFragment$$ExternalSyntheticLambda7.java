package com.jch.racWiFi.energyConsumption.fragment;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.genericModels.GenericResponse;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ECSelectACFragment$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ ECSelectACFragment f$0;

    public /* synthetic */ ECSelectACFragment$$ExternalSyntheticLambda7(ECSelectACFragment eCSelectACFragment) {
        this.f$0 = eCSelectACFragment;
    }

    public final void onChanged(Object obj) {
        ECSelectACFragment.m921getAllRacData$lambda7(this.f$0, (GenericResponse) obj);
    }
}
