package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import java.util.List;

public interface IManageDevicesView {
    void onFetchingFailed();

    void onFetchingSuccess(List<IduDetailsModel> list);

    void onStartedFetching();
}
