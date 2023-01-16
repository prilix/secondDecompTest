package com.jch.racWiFi.userOnboarding.presenter;

import android.net.wifi.ScanResult;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;
import java.util.Map;

public interface WpsConfigurationPresenter extends ConnectionSuccessListener, OnboardingPresenter {
    Integer getSuccessDestinationAction();

    void performConfiguration(Fragment fragment, QRDetailsModel qRDetailsModel, OnBoardingModel onBoardingModel, ScanResult scanResult);

    void performConfiguration(Fragment fragment, Map<String, NavArgument> map);

    void setNavigatedFrom(Integer num);
}
