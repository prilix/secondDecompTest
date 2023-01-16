package com.jch.racWiFi.userManagement.liveData;

import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.userManagement.model.InviteeList;
import com.jch.racWiFi.userManagement.model.InviteeModel;

public class InviteeLiveDataHolder {
    private static final InviteeLiveDataHolder instance = new InviteeLiveDataHolder();
    private MutableLiveData<InviteeList> inviteeListMutableLiveData = new MutableLiveData<>(new InviteeList());
    private MutableLiveData<InviteeModel> inviteeModelMutableLiveData = new MutableLiveData<>(new InviteeModel());

    public MutableLiveData<InviteeModel> getInvitationAcceptedMutableLiveData() {
        return this.inviteeModelMutableLiveData;
    }

    public MutableLiveData<InviteeList> getInviteeListMutableLiveData() {
        return this.inviteeListMutableLiveData;
    }

    public void clearLiveDataCache() {
        this.inviteeListMutableLiveData = new MutableLiveData<>(new InviteeList());
    }

    private InviteeLiveDataHolder() {
    }

    public static InviteeLiveDataHolder getInstance() {
        return instance;
    }
}
