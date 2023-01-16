package com.jch.racWiFi.userManagement.presenter;

import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.userManagement.model.DetachMeFromFamilyDataModel;
import com.jch.racWiFi.userManagement.network.DetachMeFromFamilyNetworkDispatcher;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetachMeFromFamilyPresenter extends AbstractPresenter {
    private IDetachFromFamilyPresenter iDetachFromFamilyPresenter;

    public interface IDetachFromFamilyPresenter {
        void onDetachFromFamilyFailure(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse detachMeFromFamilyDataModelFailureResponse);

        void onDetachFromFamilySuccess(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelSuccessResponse detachMeFromFamilyDataModelSuccessResponse);
    }

    public void removeCallbacks() {
    }

    public DetachMeFromFamilyPresenter(IDetachFromFamilyPresenter iDetachFromFamilyPresenter2) {
        this.iDetachFromFamilyPresenter = iDetachFromFamilyPresenter2;
    }

    public void detachFromFamily(int i) {
        new DetachMeFromFamilyNetworkDispatcher().detachFromFamily(i);
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDetachFromFamilySuccess(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelSuccessResponse detachMeFromFamilyDataModelSuccessResponse) {
        this.iDetachFromFamilyPresenter.onDetachFromFamilySuccess(detachMeFromFamilyDataModelSuccessResponse);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDetachFromFamilyFailure(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse detachMeFromFamilyDataModelFailureResponse) {
        this.iDetachFromFamilyPresenter.onDetachFromFamilyFailure(detachMeFromFamilyDataModelFailureResponse);
    }
}
