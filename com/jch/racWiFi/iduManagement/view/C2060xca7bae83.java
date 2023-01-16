package com.jch.racWiFi.iduManagement.view;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;

/* renamed from: com.jch.racWiFi.iduManagement.view.WeeklyTimerScheduleSettingsFragmentModelWise$$ExternalSyntheticLambda12 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class C2060xca7bae83 implements Observer {
    public final /* synthetic */ WeeklyTimerScheduleSettingsFragmentModelWise f$0;

    public /* synthetic */ C2060xca7bae83(WeeklyTimerScheduleSettingsFragmentModelWise weeklyTimerScheduleSettingsFragmentModelWise) {
        this.f$0 = weeklyTimerScheduleSettingsFragmentModelWise;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30666xdc029c60((TimerEnabledModel.TimerEnabledResponse) obj);
    }
}
