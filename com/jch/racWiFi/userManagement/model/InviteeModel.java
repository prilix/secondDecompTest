package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class InviteeModel {
    @SerializedName("familyName")
    public String homeName;
    @SerializedName("fromUserDetailsId")

    /* renamed from: id */
    public Integer f481id;
    public InvitationState invitationState = InvitationState.NONE;
    @SerializedName("invitationCode")
    public String inviteCode;
    @SerializedName("fromUserName")
    public String ownerName;
    @SerializedName("pictureData")
    public ProfilePicture profilePicture;

    public enum InvitationState {
        NONE,
        ACCEPTED,
        DECLINED
    }

    public void setAccepted() {
        this.invitationState = InvitationState.ACCEPTED;
    }

    public void setDeclined() {
        this.invitationState = InvitationState.DECLINED;
    }

    public boolean isAccepted() {
        return this.invitationState.equals(InvitationState.ACCEPTED);
    }

    public boolean isDeclined() {
        return this.invitationState.equals(InvitationState.DECLINED);
    }

    public boolean equals(Object obj) {
        if (obj instanceof InviteeModel) {
            return this.f481id.equals(((InviteeModel) obj).f481id);
        }
        return false;
    }

    public static List<InviteeModel> getDummyList() {
        ArrayList arrayList = new ArrayList();
        InviteeModel inviteeModel = new InviteeModel();
        inviteeModel.f481id = 1;
        inviteeModel.homeName = "Accord 1";
        inviteeModel.ownerName = "Acc 1";
        arrayList.add(inviteeModel);
        InviteeModel inviteeModel2 = new InviteeModel();
        inviteeModel2.f481id = 2;
        inviteeModel2.homeName = "Accord 2";
        inviteeModel2.ownerName = "Acc 2";
        arrayList.add(inviteeModel2);
        InviteeModel inviteeModel3 = new InviteeModel();
        inviteeModel3.f481id = 3;
        inviteeModel3.homeName = "Accord 3";
        inviteeModel3.ownerName = "Acc 3";
        arrayList.add(inviteeModel3);
        InviteeModel inviteeModel4 = new InviteeModel();
        inviteeModel4.f481id = 4;
        inviteeModel4.homeName = "Accord 4";
        inviteeModel4.ownerName = "Acc 4";
        arrayList.add(inviteeModel4);
        InviteeModel inviteeModel5 = new InviteeModel();
        inviteeModel5.f481id = 5;
        inviteeModel5.homeName = "Accord 5";
        inviteeModel5.ownerName = "Acc 5";
        arrayList.add(inviteeModel5);
        return arrayList;
    }

    public String getFamilyName() {
        String str = this.homeName;
        if (str != null) {
            return str.split("'")[0];
        }
        return null;
    }
}
