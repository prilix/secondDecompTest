package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import androidx.navigation.NavArgument;
import androidx.navigation.NavGraph;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.userOnboarding.presenter.FailedPagePresenter;
import com.jch.racWiFi.userOnboarding.view.IFailedView;
import java.util.Map;

public class FailedPagePresenterImpl implements FailedPagePresenter {
    IFailedView failedView;

    public FailedPagePresenterImpl(IFailedView iFailedView) {
        this.failedView = iFailedView;
    }

    public void reportStartOverPressed(Map<String, NavArgument> map, NavGraph navGraph) {
        NavArgument navArgument;
        NavArgument navArgument2 = map.get(Values.NAVIGATED_FROM);
        if (navArgument2 == null || !navArgument2.getDefaultValue().equals(Values.NAVIGATED_FROM_AP_TUTORIAL)) {
            this.failedView.goToQrScanPage();
            return;
        }
        NavArgument navArgument3 = map.get(Values.START_OVER_COUNT_KEY);
        Integer num = 1;
        if (navArgument3 == null) {
            navArgument = createStartOverCountArg(1);
        } else {
            num = Integer.valueOf(((Integer) navArgument3.getDefaultValue()).intValue() + 1);
            navArgument = createStartOverCountArg(num.intValue());
        }
        navGraph.addArgument(Values.START_OVER_COUNT_KEY, navArgument);
        if (num.intValue() < 3) {
            this.failedView.goToQrScanPage();
        } else {
            this.failedView.goToSoftAPPage();
        }
    }

    private NavArgument createStartOverCountArg(int i) {
        return new NavArgument.Builder().setDefaultValue(Integer.valueOf(i)).build();
    }
}
