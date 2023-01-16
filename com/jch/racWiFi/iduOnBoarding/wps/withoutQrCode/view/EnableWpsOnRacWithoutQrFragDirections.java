package com.jch.racWiFi.iduOnBoarding.wps.withoutQrCode.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacWithoutQrFragDirections {
    private EnableWpsOnRacWithoutQrFragDirections() {
    }

    public static NavDirections actionEnableWpsOnRacWithoutQrFragToEnableWpsOnHomeRouterFragment() {
        return new ActionOnlyNavDirections(R.id.action_enableWpsOnRacWithoutQrFrag_to_enableWpsOnHomeRouterFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
