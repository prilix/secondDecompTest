package com.jch.racWiFi.userOnboarding.presenter;

import androidx.navigation.NavArgument;
import androidx.navigation.NavGraph;
import java.util.Map;

public interface FailedPagePresenter {
    void reportStartOverPressed(Map<String, NavArgument> map, NavGraph navGraph);
}
