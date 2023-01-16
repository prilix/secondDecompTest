package com.jch.racWiFi;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.model.dto.RoleDto;

public class UserInfo {
    @SerializedName("detailsUserInfoId")
    public int detailsUserInfoId = -1;
    @SerializedName("email")
    public String email;
    @SerializedName("familyId")
    public int familyId = -1;
    @SerializedName("familyName")
    public String familyName;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("id")

    /* renamed from: id */
    public int f424id = -1;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("middleName")
    public String middleName;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("pictureData")
    public ProfilePicture profilePicture;
    @SerializedName("roles")
    public RoleDto[] role;
    @SerializedName("settings")
    public SettingsDataModels.SettingsData settingsData = new SettingsDataModels.SettingsData();
    @SerializedName("address")
    public UserAddress userAddress = new UserAddress();
    public boolean userInfoFetched = false;

    public static void resetCurrentUserInfo(CoreActivity coreActivity) {
        coreActivity.getGlobalViewModelRepository().getUserInfoViewModel().getUserInfoSingleLiveEvent().postValue(new UserInfo());
    }

    public static UserInfo getCurrentUserInfo(CoreActivity coreActivity) {
        return coreActivity.getGlobalViewModelRepository().getUserInfoViewModel().getCurrentUserInfo();
    }

    public void copy(UserInfo userInfo) {
        this.f424id = userInfo.f424id;
        this.detailsUserInfoId = userInfo.detailsUserInfoId;
        this.lastName = userInfo.lastName;
        this.role = userInfo.role;
        this.phoneNumber = userInfo.phoneNumber;
        this.firstName = userInfo.firstName;
        this.middleName = userInfo.middleName;
        this.email = userInfo.email;
        this.familyId = userInfo.familyId;
        this.familyName = userInfo.familyName;
        this.userAddress.copy(userInfo.userAddress);
        this.settingsData.copy(userInfo.settingsData);
        if (userInfo.profilePicture != null) {
            this.profilePicture = new ProfilePicture(userInfo.profilePicture);
        }
        this.userInfoFetched = true;
    }
}
