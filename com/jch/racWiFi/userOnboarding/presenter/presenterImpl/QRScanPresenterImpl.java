package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import com.google.zxing.Result;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.presenter.QRScanPresenter;
import com.jch.racWiFi.userOnboarding.view.IQRView;

public class QRScanPresenterImpl implements QRScanPresenter {
    IQRView iqrView;

    public QRScanPresenterImpl(IQRView iQRView) {
        this.iqrView = iQRView;
    }

    public void handleResult(Result result) {
        String text = result.getText();
        result.getText().toLowerCase();
        QRDetailsModel qRDetailsModel = QRDetailsModel.CURRENT_RAC_DETAILS;
        if (qRDetailsModel.parseQrString(text)) {
            this.iqrView.onScanSuccessful(qRDetailsModel);
        } else {
            this.iqrView.onWrongQRScanned();
        }
    }

    public boolean setRunStatus(NavController navController) {
        NavGraph graph = navController.getGraph();
        NavArgument navArgument = graph.getArguments().get(Values.FRESH_START_DETAILS_KEY);
        if (navArgument == null || ((Boolean) navArgument.getDefaultValue()).booleanValue()) {
            this.iqrView.runOnFreshMode();
        } else {
            this.iqrView.runOnPreviouslyFailedMode();
        }
        graph.addArgument(Values.FRESH_START_DETAILS_KEY, new NavArgument.Builder().setDefaultValue(Boolean.TRUE).build());
        return false;
    }
}
