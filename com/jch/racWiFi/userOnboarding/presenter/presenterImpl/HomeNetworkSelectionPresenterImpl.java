package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import android.net.wifi.ScanResult;
import androidx.navigation.NavArgument;
import androidx.navigation.NavGraph;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.userOnboarding.presenter.HomeNetworkSelectionPresenter;
import com.jch.racWiFi.userOnboarding.view.IHomeNetworkSelectionView;
import java.util.Map;

public class HomeNetworkSelectionPresenterImpl implements HomeNetworkSelectionPresenter {
    private IHomeNetworkSelectionView IHomeNetworkSelectionView;

    public HomeNetworkSelectionPresenterImpl(IHomeNetworkSelectionView iHomeNetworkSelectionView) {
        this.IHomeNetworkSelectionView = iHomeNetworkSelectionView;
    }

    public void setNavArguments(Map<String, NavArgument> map) {
        String str;
        NavArgument navArgument = map.get(Values.SELECTED_HOME_NETWORK_KEY);
        if (navArgument == null) {
            str = "";
        } else {
            str = (String) navArgument.getDefaultValue();
        }
        Logger.m45d("SSID", str);
        if (str.isEmpty() || str.equals(Values.NOT_CONNECTED)) {
            this.IHomeNetworkSelectionView.onNoSelectedHomeNetworkFound();
        } else {
            this.IHomeNetworkSelectionView.onSelectedHomeNetworkFound(str);
        }
    }

    public NavArgument convertPasswordToNavArgument(String str) {
        return new NavArgument.Builder().setDefaultValue(str).build();
    }

    public NavArgument getNavigatedFromAsNavArgument() {
        return new NavArgument.Builder().setDefaultValue(Values.NAVIGATED_FROM_HOME_NETWORK_SELECTION_PAGE).build();
    }

    public void updateSsidInGraph(NavGraph navGraph, String str, ScanResult scanResult) {
        navGraph.addArgument(Values.SELECTED_HOME_NETWORK_KEY, new NavArgument.Builder().setDefaultValue(str).build());
        navGraph.addArgument(Values.SCAN_RESULT_HOME_ROUTER_KEY, new NavArgument.Builder().setDefaultValue(scanResult).build());
    }
}
