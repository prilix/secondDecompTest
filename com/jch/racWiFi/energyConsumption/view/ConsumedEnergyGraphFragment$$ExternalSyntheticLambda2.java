package com.jch.racWiFi.energyConsumption.view;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.genericModels.GenericResponse;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConsumedEnergyGraphFragment$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ ConsumedEnergyGraphFragment f$0;
    public final /* synthetic */ EnergyConsumptionDataMain f$1;

    public /* synthetic */ ConsumedEnergyGraphFragment$$ExternalSyntheticLambda2(ConsumedEnergyGraphFragment consumedEnergyGraphFragment, EnergyConsumptionDataMain energyConsumptionDataMain) {
        this.f$0 = consumedEnergyGraphFragment;
        this.f$1 = energyConsumptionDataMain;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo28948x5e0aef97(this.f$1, (GenericResponse) obj);
    }
}
