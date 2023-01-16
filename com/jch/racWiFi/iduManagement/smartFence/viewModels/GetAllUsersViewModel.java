package com.jch.racWiFi.iduManagement.smartFence.viewModels;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.network.GetFamilyMembersListNetworkDispatcher;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsersViewModel extends C0534ViewModel {
    public SingleLiveEvent<GenericResponse> singleLiveEventForFailure = new SingleLiveEvent<>();
    private SingleLiveEvent<List<UserFamilyMemberModels.UserFamilyMemberInfo>> singleLiveEventUserList = new SingleLiveEvent<>();
    List<UserFamilyMemberModels.UserFamilyMemberInfo> userList = new ArrayList();

    public SingleLiveEvent<List<UserFamilyMemberModels.UserFamilyMemberInfo>> getUserListSingleLiveEvent() {
        return this.singleLiveEventUserList;
    }

    public SingleLiveEvent<GenericResponse> getSingleLiveEventForFailure() {
        return this.singleLiveEventForFailure;
    }

    public void callGetAllUserApi() {
        final SingleLiveEvent<GenericResponse> familyListByFamilyIDForSmartFence = new GetFamilyMembersListNetworkDispatcher().getFamilyListByFamilyIDForSmartFence(FamilyGroupList.getCurrentFamily().familyId, false, false);
        familyListByFamilyIDForSmartFence.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                familyListByFamilyIDForSmartFence.removeObserver(this);
                if (genericResponse.isApiSuccessful()) {
                    GetAllUsersViewModel.this.filterOwners((UserFamilyMemberModels.UserFamilyMemberSuccessResponse) genericResponse.getBodyOfType(UserFamilyMemberModels.UserFamilyMemberSuccessResponse.class));
                    return;
                }
                GetAllUsersViewModel.this.singleLiveEventForFailure.postValue(genericResponse);
            }
        });
    }

    /* access modifiers changed from: private */
    public void filterOwners(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        this.userList.clear();
        for (UserFamilyMemberModels.UserFamilyMemberInfo next : userFamilyMemberSuccessResponse.userFamilyMemberInfo) {
            if (next.role.getId().intValue() == 1) {
                this.userList.add(next);
            }
        }
        this.singleLiveEventUserList.setValue(this.userList);
    }
}
