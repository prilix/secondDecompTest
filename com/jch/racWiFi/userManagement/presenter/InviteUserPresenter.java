package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.network.InviteUserNetworkDispatcher;
import com.jch.racWiFi.userManagement.view.InviteUsersFragmentNewVD;
import java.util.ArrayList;
import java.util.List;

public class InviteUserPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public InviteUserPresenterInterface inviteUserPresenterInterface;

    public interface InviteUserPresenterInterface extends INetworkConnectivity {
        void onInviteSendFailure(InviteMemberModels.InviteMemberFailureResponse inviteMemberFailureResponse);

        void onInviteSendSuccess(InviteMemberModels.InviteMemberSuccessResponse inviteMemberSuccessResponse);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public InviteUserPresenter(InviteUserPresenterInterface inviteUserPresenterInterface2) {
        this.inviteUserPresenterInterface = inviteUserPresenterInterface2;
    }

    public void inviteUserV2(int i, List<InviteUsersFragmentNewVD.UsersToBeInvitedData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            InviteMemberModels.InviteMemberData inviteMemberData = new InviteMemberModels.InviteMemberData();
            inviteMemberData.phoneNumber = list.get(i2).getPhoneNumber();
            inviteMemberData.email = list.get(i2).getEmailID();
            arrayList.add(inviteMemberData);
        }
        final SingleLiveEvent<GenericResponse> inviteUserFamilyGroup = new InviteUserNetworkDispatcher().inviteUserFamilyGroup(i, arrayList);
        inviteUserFamilyGroup.observeSingleEvent(new Observer<GenericResponse>() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<InviteUserPresenter> cls = InviteUserPresenter.class;
            }

            public void onChanged(GenericResponse genericResponse) {
                inviteUserFamilyGroup.removeObserver(this);
                if (InviteUserPresenter.this.inviteUserPresenterInterface != null) {
                    Logger.m47e("INVITE_USER_RESPONSE", genericResponse.isApiSuccessful() + "");
                    if (genericResponse.unableToReachServer()) {
                        InviteUserPresenter.this.inviteUserPresenterInterface.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        InviteUserPresenter.this.inviteUserPresenterInterface.onInviteSendSuccess((InviteMemberModels.InviteMemberSuccessResponse) genericResponse.getBodyOfType(InviteMemberModels.InviteMemberSuccessResponse.class));
                    } else {
                        InviteMemberModels.InviteMemberFailureResponse inviteMemberFailureResponse = (InviteMemberModels.InviteMemberFailureResponse) genericResponse.getErrorBodyOfType(InviteMemberModels.InviteMemberFailureResponse.class);
                        inviteMemberFailureResponse.statusCode = genericResponse.getResponse().code();
                        InviteUserPresenter.this.inviteUserPresenterInterface.onInviteSendFailure(inviteMemberFailureResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.inviteUserPresenterInterface = null;
    }
}
