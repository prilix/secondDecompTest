package com.jch.racWiFi.energyConsumption.adapter;

import android.widget.CompoundButton;
import com.jch.racWiFi.energyConsumption.model.response.AllRac;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ECSelectACsAdapter$$ExternalSyntheticLambda0 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ ECSelectACsAdapter f$0;
    public final /* synthetic */ AllRac f$1;

    public /* synthetic */ ECSelectACsAdapter$$ExternalSyntheticLambda0(ECSelectACsAdapter eCSelectACsAdapter, AllRac allRac) {
        this.f$0 = eCSelectACsAdapter;
        this.f$1 = allRac;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ECSelectACsAdapter.m897onBindViewHolder$lambda0(this.f$0, this.f$1, compoundButton, z);
    }
}
