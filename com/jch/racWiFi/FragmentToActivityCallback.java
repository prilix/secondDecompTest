package com.jch.racWiFi;

import android.view.MotionEvent;
import androidx.navigation.NavController;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Utils.SessionManager;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.WebSocketWrapper;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersMap;
import com.jch.racWiFi.userManagement.model.RemoveFromFamilyNotificationModel;

public interface FragmentToActivityCallback {
    void askForPermission(String str);

    void backPressFromIndividualIdu();

    void changeStatusBarColor(int i);

    void connectStompClient();

    void finishUserManagementActivity();

    FamilyGroupList getFamilyGroupList();

    FamilyMembersMap getFamilyMembersMap();

    SingleLiveEvent<WebSocketNotification<RemoveFromFamilyNotificationModel>> getFamilyUpdateSingleLiveEvent();

    SingleLiveEvent<Boolean> getForceRefreshMutableLiveData();

    HomePageActivity.IDUNotificationRecyclerViewAdapter getIduNotificationAdapter();

    NavController getNavController();

    SingleLiveEvent<WebSocketNotification.RequestType> getNotificationRequestTypeSingleLiveEvent();

    RacModelWiseConfigurationList getRacModelWiseConfigurationList();

    RacModelWiseData getRacModelWiseDataBasedOnRacTypeId(String str);

    SingleLiveEvent<SwipeScreenType> getScreenSwipeSingleLiveEvent();

    SessionManager getSessionManagerInstance();

    SingleLiveEvent<MotionEvent> getTouchPointerCountSingleLiveEvent();

    WebSocketWrapper getWebSocketWrapper();

    boolean isPermissionGranted(String str);

    void logOutFromApplication();

    void moveToHomePageActivity(boolean z, boolean z2);

    void moveToLoginFragment();

    void reCreateUserManagementActivity();

    void refreshAllIduStates();

    void refreshIndividualIduState(CoreActivity coreActivity, int i);
}
