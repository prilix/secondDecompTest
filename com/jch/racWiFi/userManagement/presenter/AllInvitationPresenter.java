package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.InviteeList;
import com.jch.racWiFi.userManagement.network.AllInvitationNetworkDispatcher;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class AllInvitationPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IAllInvitationPresenter allInvitationPresenter;

    public interface IAllInvitationPresenter extends INetworkConnectivity {
        void getAllInvitationFailureResponse(GenericResponse genericResponse);

        void getAllInvitationSuccessResponse(InviteeList inviteeList);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public AllInvitationPresenter(IAllInvitationPresenter iAllInvitationPresenter) {
        this.allInvitationPresenter = iAllInvitationPresenter;
    }

    public void getAllInvitations(LifecycleOwner lifecycleOwner) {
        final SingleLiveEvent<GenericResponse> allInvitation = new AllInvitationNetworkDispatcher().getAllInvitation();
        allInvitation.observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                allInvitation.removeObserver(this);
                if (AllInvitationPresenter.this.allInvitationPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        AllInvitationPresenter.this.allInvitationPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        InviteeList inviteeList = null;
                        try {
                            inviteeList = (InviteeList) new Gson().fromJson(new JSONObject(genericResponse.getResponse().body().string()).getString(Constants.Meta.RESULT), new TypeToken<InviteeList>() {
                            }.getType());
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                        AllInvitationPresenter.this.allInvitationPresenter.getAllInvitationSuccessResponse(inviteeList);
                    } else {
                        AllInvitationPresenter.this.allInvitationPresenter.getAllInvitationFailureResponse(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.allInvitationPresenter = null;
    }
}
