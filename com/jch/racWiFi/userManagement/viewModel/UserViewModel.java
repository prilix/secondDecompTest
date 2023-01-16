package com.jch.racWiFi.userManagement.viewModel;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.GetUserInfoNetworkDispatcher;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.genericViewModel.GenericNetworkCallViewModel;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.network.UpdateAddressNetworkDispatcherV2;

public class UserViewModel extends GenericNetworkCallViewModel {
    SingleLiveEvent<GenericResponse> userInfoFailureSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<UserInfo> userInfoSingleLiveEvent = new SingleLiveEvent<>(new UserInfo());

    public SingleLiveEvent<UserInfo> getUserInfoSingleLiveEvent() {
        return this.userInfoSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getUserInfoFailureSingleLiveEvent() {
        return this.userInfoFailureSingleLiveEvent;
    }

    public void fetchUserInfo(LifecycleOwner lifecycleOwner) {
        new GetUserInfoNetworkDispatcher().fetchUserInfo().observeForever(new UserViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$fetchUserInfo$0$com-jch-racWiFi-userManagement-viewModel-UserViewModel */
    public /* synthetic */ void mo33329x6deab0e1(GenericResponse genericResponse) {
        if (genericResponse.isApiSuccessful()) {
            UserInfo userInfo = (UserInfo) genericResponse.getBodyOfType(UserInfo.class);
            if (userInfo != null) {
                getCurrentUserInfo().copy(userInfo);
            }
            getUserInfoSingleLiveEvent().trigger();
            return;
        }
        this.userInfoFailureSingleLiveEvent.postValue(genericResponse);
    }

    public UserInfo getCurrentUserInfo() {
        return getUserInfoSingleLiveEvent().getValue();
    }

    public void updateUserInfoLiveData(UserInfo userInfo) {
        getUserInfoSingleLiveEvent().postValue(userInfo);
    }

    public SingleLiveEvent<GenericResponse> updateAddress(UserAddress userAddress) {
        return new UpdateAddressNetworkDispatcherV2().updateAddress(userAddress);
    }
}
