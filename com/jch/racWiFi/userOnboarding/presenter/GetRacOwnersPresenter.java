package com.jch.racWiFi.userOnboarding.presenter;

import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.userOnboarding.model.RacOwnersDataModel;
import com.jch.racWiFi.userOnboarding.network.GetRacOwnersNetworkDispatcher;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GetRacOwnersPresenter extends AbstractPresenter {
    IRacOwnersPresenterInterface iRacOwnersPresenterInterface;

    public interface IRacOwnersPresenterInterface extends INetworkConnectivity {
        void onRacOwnersFetchFailure(RacOwnersDataModel.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure);

        void onRacOwnersFetchSuccess(RacOwnersDataModel.RacOwnersDataModelResponseSuccess racOwnersDataModelResponseSuccess);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkCallFailure(Throwable th) {
    }

    public void removeCallbacks() {
    }

    public GetRacOwnersPresenter(IRacOwnersPresenterInterface iRacOwnersPresenterInterface2) {
        this.iRacOwnersPresenterInterface = iRacOwnersPresenterInterface2;
    }

    public void getFamilyGroup(int i, long j) {
        new GetRacOwnersNetworkDispatcher().getRacOwners(i, j);
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserFamilyGroupFetchSuccess(RacOwnersDataModel.RacOwnersDataModelResponseSuccess racOwnersDataModelResponseSuccess) {
        this.iRacOwnersPresenterInterface.onRacOwnersFetchSuccess(racOwnersDataModelResponseSuccess);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserFamilyGroupFetchFailure(RacOwnersDataModel.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure) {
        this.iRacOwnersPresenterInterface.onRacOwnersFetchFailure(familyGroupsModelResponseSuccessFailure);
    }
}
