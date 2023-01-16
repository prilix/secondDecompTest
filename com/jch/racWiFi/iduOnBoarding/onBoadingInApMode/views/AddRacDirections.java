package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class AddRacDirections {
    private AddRacDirections() {
    }

    public static NavDirections actionAddRacToConfiguringDeviceUdpExchange() {
        return new ActionOnlyNavDirections(R.id.action_addRac_to_configuringDeviceUdpExchange);
    }

    public static NavDirections actionAddRacToConfiguringFailedUdpExchange() {
        return new ActionOnlyNavDirections(R.id.action_addRac_to_configuringFailedUdpExchange);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
