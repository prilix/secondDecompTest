package com.jch.racWiFi.userOnboarding.presenter;

import android.net.wifi.ScanResult;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavArgument;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiActivity;
import java.util.Map;

public interface ConfigurationPresenter extends OnboardingPresenter {
    void checkMdnsStatus(WifiActivity wifiActivity, String str, String str2);

    Integer getSuccessDestinationAction();

    void performCloudOnboarding(WifiActivity wifiActivity, LifecycleOwner lifecycleOwner, String str, String str2, String str3, String str4);

    void performConfiguration(Fragment fragment, QRDetailsModel qRDetailsModel, OnBoardingModel onBoardingModel, ScanResult scanResult);

    void performConfiguration(Fragment fragment, Map<String, NavArgument> map);

    void setNavigatedFrom(Integer num);
}
