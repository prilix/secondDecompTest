package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.network.InviteUserVerifyNetworkDispatcher;

public class InviteVerificationPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public InviteUserPresenterInterface inviteUserPresenterInterface;

    public interface InviteUserPresenterInterface extends INetworkConnectivity {
        void onInviteCodeVerificationFailure(InviteMemberModels.InviteMemberCodeVerificationFailure inviteMemberCodeVerificationFailure);

        void onInviteCodeVerificationSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public InviteVerificationPresenter(InviteUserPresenterInterface inviteUserPresenterInterface2) {
        this.inviteUserPresenterInterface = inviteUserPresenterInterface2;
    }

    public void verifyInviteCode(String str) {
        if (str != null && !str.isEmpty()) {
            final SingleLiveEvent<GenericResponse> inviteUserV2 = new InviteUserVerifyNetworkDispatcher().inviteUserV2(str);
            inviteUserV2.observeSingleEvent(new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    inviteUserV2.removeObserver(this);
                    if (InviteVerificationPresenter.this.inviteUserPresenterInterface != null) {
                        if (genericResponse.unableToReachServer()) {
                            InviteVerificationPresenter.this.inviteUserPresenterInterface.onNetworkCallFailure(genericResponse.getThrowable());
                        } else if (genericResponse.isApiSuccessful()) {
                            InviteVerificationPresenter.this.inviteUserPresenterInterface.onInviteCodeVerificationSuccess();
                        } else {
                            InviteMemberModels.InviteMemberCodeVerificationFailure inviteMemberCodeVerificationFailure = (InviteMemberModels.InviteMemberCodeVerificationFailure) genericResponse.getErrorBodyOfType(InviteMemberModels.InviteMemberCodeVerificationFailure.class);
                            inviteMemberCodeVerificationFailure.statusCode = genericResponse.getResponse().code();
                            InviteVerificationPresenter.this.inviteUserPresenterInterface.onInviteCodeVerificationFailure(inviteMemberCodeVerificationFailure);
                        }
                    }
                }
            });
        }
    }

    public void removeCallbacks() {
        this.inviteUserPresenterInterface = null;
    }
}
