package com.jch.racWiFi.userOnboarding.presenter;

import androidx.fragment.app.Fragment;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;

public interface DeviceDetailsPresenter {
    void renameThing(Fragment fragment, IduDetailsModel iduDetailsModel, String str);

    void unBoardIdu(Fragment fragment, int i, long j);
}
