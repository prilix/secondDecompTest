package com.jch.racWiFi.userManagement.presenter;

import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.network.RemoveMemberNetworkDispatcher;
import com.jch.racWiFi.userManagement.network.UpdateRoleNetworkDispatcher;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EditUserFragmentPresenter extends AbstractPresenter {
    private IEditUserFragmentPresenter iEditUserFragmentPresenter;

    public interface IEditUserFragmentPresenter extends INetworkConnectivity {
        void onFamilyMemberRemoveFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse);

        void onFamilyMemberRemoveSuccess(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse);

        void onFamilyMemberRoleUpdateSuccess(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse);

        void onOwnerChangeRoleToOwnerFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse);
    }

    public void removeCallbacks() {
    }

    public EditUserFragmentPresenter(IEditUserFragmentPresenter iEditUserFragmentPresenter2) {
        this.iEditUserFragmentPresenter = iEditUserFragmentPresenter2;
    }

    public void updateRole(UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo, int i, int i2) {
        new UpdateRoleNetworkDispatcher().updateRoleByFamilyGroupId(userFamilyMemberInfo, i, i2);
    }

    public void removeUser(int i, int i2) {
        new RemoveMemberNetworkDispatcher().removeMemberByFamilyGroupId(i, i2);
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserRoleUpdateSuccess(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        if (userFamilyMemberSuccessResponse.deleteUser) {
            this.iEditUserFragmentPresenter.onFamilyMemberRemoveSuccess(userFamilyMemberSuccessResponse);
        } else {
            this.iEditUserFragmentPresenter.onFamilyMemberRoleUpdateSuccess(userFamilyMemberSuccessResponse);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserRoleUpdateFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse) {
        if (userFamilyMemberFailureResponse.deleteUser) {
            this.iEditUserFragmentPresenter.onFamilyMemberRemoveFailure(userFamilyMemberFailureResponse);
        } else {
            this.iEditUserFragmentPresenter.onOwnerChangeRoleToOwnerFailure(userFamilyMemberFailureResponse);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkCallFailure(Throwable th) {
        this.iEditUserFragmentPresenter.onNetworkCallFailure(th);
    }
}
