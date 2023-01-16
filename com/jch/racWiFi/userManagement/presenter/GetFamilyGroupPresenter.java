package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;
import com.jch.racWiFi.userManagement.network.GetFamilyGroupsNetworkDispatcher;

public class GetFamilyGroupPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IGetFamilyGroupPresenterInterface iGetFamilyGroupPresenterInterface;

    public interface IGetFamilyGroupPresenterInterface extends INetworkConnectivity {
        void onUserFamilyGroupFetchFailure(FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure);

        void onUserFamilyGroupFetchSuccess(FamilyGroupsModels.FamilyGroupsModelResponseSuccess familyGroupsModelResponseSuccess);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public GetFamilyGroupPresenter(IGetFamilyGroupPresenterInterface iGetFamilyGroupPresenterInterface2) {
        this.iGetFamilyGroupPresenterInterface = iGetFamilyGroupPresenterInterface2;
    }

    public void getFamilyGroup() {
        final SingleLiveEvent<GenericResponse> familyGroups = new GetFamilyGroupsNetworkDispatcher().getFamilyGroups();
        familyGroups.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                familyGroups.removeObserver(this);
                if (GetFamilyGroupPresenter.this.iGetFamilyGroupPresenterInterface != null) {
                    if (genericResponse.unableToReachServer()) {
                        GetFamilyGroupPresenter.this.iGetFamilyGroupPresenterInterface.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        GetFamilyGroupPresenter.this.iGetFamilyGroupPresenterInterface.onUserFamilyGroupFetchSuccess((FamilyGroupsModels.FamilyGroupsModelResponseSuccess) genericResponse.getBodyOfType(FamilyGroupsModels.FamilyGroupsModelResponseSuccess.class));
                    } else {
                        FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure = (FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure) genericResponse.getErrorBodyOfType(FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure.class);
                        familyGroupsModelResponseSuccessFailure.statusCode = genericResponse.getResponse().code();
                        GetFamilyGroupPresenter.this.iGetFamilyGroupPresenterInterface.onUserFamilyGroupFetchFailure(familyGroupsModelResponseSuccessFailure);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iGetFamilyGroupPresenterInterface = null;
    }
}
