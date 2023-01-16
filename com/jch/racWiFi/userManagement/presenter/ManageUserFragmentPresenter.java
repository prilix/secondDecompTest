package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.network.GetFamilyMembersListNetworkDispatcher;

public class ManageUserFragmentPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IManageUserFragmentPresenter iManageUserFragmentPresenter;
    public boolean toGetUser = true;

    public interface IManageUserFragmentPresenter extends INetworkConnectivity {
        void onUserFamilyListAvailable(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse);

        void onUserFamilyListFetchFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ManageUserFragmentPresenter(IManageUserFragmentPresenter iManageUserFragmentPresenter2) {
        this.iManageUserFragmentPresenter = iManageUserFragmentPresenter2;
    }

    public void getFamilyMemberList(int i) {
        final SingleLiveEvent<GenericResponse> familyListByFamilyID = new GetFamilyMembersListNetworkDispatcher().getFamilyListByFamilyID(i);
        familyListByFamilyID.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                Logger.m47e("GET_FAMILY_MEMBER", "GET_FAMILY_MEMBER");
                familyListByFamilyID.removeObserver(this);
                if (ManageUserFragmentPresenter.this.iManageUserFragmentPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        ManageUserFragmentPresenter.this.iManageUserFragmentPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        ManageUserFragmentPresenter.this.iManageUserFragmentPresenter.onUserFamilyListAvailable((UserFamilyMemberModels.UserFamilyMemberSuccessResponse) genericResponse.getBodyOfType(UserFamilyMemberModels.UserFamilyMemberSuccessResponse.class));
                    } else {
                        UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse = (UserFamilyMemberModels.UserFamilyMemberFailureResponse) genericResponse.getErrorBodyOfType(UserFamilyMemberModels.UserFamilyMemberFailureResponse.class);
                        userFamilyMemberFailureResponse.statusCode = genericResponse.getResponse().code();
                        ManageUserFragmentPresenter.this.iManageUserFragmentPresenter.onUserFamilyListFetchFailure(userFamilyMemberFailureResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iManageUserFragmentPresenter = null;
    }
}
