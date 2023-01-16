package com.jch.racWiFi.settings.view;

import com.jch.racWiFi.databinding.RecyclerViewAppSettingsBinding;
import com.jch.racWiFi.settings.view.SettingsFragment;
import com.suke.widget.SwitchButton;

/* renamed from: com.jch.racWiFi.settings.view.SettingsFragment$SettingsRecyclerViewAdapter$SettingsViewHolder$$ExternalSyntheticLambda1 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class C2348xfd891431 implements SwitchButton.OnCheckedChangeListener {
    public final /* synthetic */ SettingsFragment.SettingsRecyclerViewAdapter.SettingsViewHolder f$0;
    public final /* synthetic */ RecyclerViewAppSettingsBinding f$1;

    public /* synthetic */ C2348xfd891431(SettingsFragment.SettingsRecyclerViewAdapter.SettingsViewHolder settingsViewHolder, RecyclerViewAppSettingsBinding recyclerViewAppSettingsBinding) {
        this.f$0 = settingsViewHolder;
        this.f$1 = recyclerViewAppSettingsBinding;
    }

    public final void onCheckedChanged(SwitchButton switchButton, boolean z) {
        this.f$0.mo31979xfa6560a1(this.f$1, switchButton, z);
    }
}
