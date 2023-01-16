package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.p005ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import com.accord.common.utils.Logger;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.HelpWebPageModel;
import com.jch.racWiFi.IDrawerMenuFunctions;
import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.IduAccess.presenter.presenterImpl.IduAccessPresenterImpl;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;
import com.jch.racWiFi.Presenter.PrivacyPolicyPresenter;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.SessionManager;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.SwipeDetector;
import com.jch.racWiFi.databinding.MainActivityBinding;
import com.jch.racWiFi.databinding.RecyclerViewItemsNotificationsBinding;
import com.jch.racWiFi.fcm.adapter.AlertAdapter;
import com.jch.racWiFi.fcm.adapter.ErrorAdapter;
import com.jch.racWiFi.fcm.adapter.ReminderAdapter;
import com.jch.racWiFi.fcm.adapter.SectionAdapter;
import com.jch.racWiFi.fcm.adapter.SmartFenceAdapter;
import com.jch.racWiFi.fcm.dashboard.FcmDashboard;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.FcmResponse;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.model.Silent;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.repository.AdapterRepository;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.util.AcActivitiesList;
import com.jch.racWiFi.fcm.util.DeepLink;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.fcm.util.SilentSubCategory;
import com.jch.racWiFi.fcm.view_model.FcmViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.WebSocketWrapper;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.IDUNotificationType;
import com.jch.racWiFi.iduManagement.model.IDUNotificationTypeUIConfigration;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlStateResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceGeoFenceSettings;
import com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSelectAcFragment;
import com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSelectUserFragment;
import com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSetModeTempDilog;
import com.jch.racWiFi.iduManagement.view.IHomePageView;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.settings.view.CustomerCareFragmentGlobal;
import com.jch.racWiFi.settings.view.HelpFragmentGlobal;
import com.jch.racWiFi.settings.view.SettingsFragment;
import com.jch.racWiFi.userManagement.liveData.InviteeLiveDataHolder;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersMap;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.model.InviteeList;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch.racWiFi.userManagement.model.RemoveFromFamilyNotificationModel;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.network.ApiCaller.PermissionApiImpl;
import com.jch.racWiFi.userManagement.view.ChangePasswordFragment;
import com.jch.racWiFi.userManagement.view.ManageUsersFragment;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.AndroidInjection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p020ua.naiksoftware.stomp.dto.StompMessage;

public class HomePageActivity extends CoreActivity implements NavigationView.OnNavigationItemSelectedListener, IDrawerMenuFunctions, IHomePageView, NetworkConnectivity.CheckInternet.InternetConnectivityCallback, PrivacyPolicyPresenter.IPrivacyPolicyPresenter, SwipeDetector.SwipeScreenCallback {
    private static final int LOGOUT_NETWORK = 0;
    /* access modifiers changed from: private */
    public final String TAG = getClass().getSimpleName();
    private IDUNotificationRecyclerViewAdapter adapter;
    private boolean enableBackButtonGestureListener;
    private final FamilyGroupList familyGroupList = new FamilyGroupList();
    private FamilyIdGeoFenceDataMap familyIdGeoFenceDataMapOld;
    private final FamilyMembersMap familyMembersMap = new FamilyMembersMap();
    private final SingleLiveEvent<WebSocketNotification<RemoveFromFamilyNotificationModel>> forceFamilyRefreshSingleLiveEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> forceRefreshMutableLiveData = new SingleLiveEvent<>();
    private GestureDetector gestureDetector;
    /* access modifiers changed from: private */
    public LocationPermissionViewModel locationPermissionViewModel;
    private AdapterRepository mAdapterRepository;
    private MainActivityBinding mBinding;
    private FcmDashboard mFcmDashboard;
    /* access modifiers changed from: private */
    public FcmViewModel mFcmViewModel;
    private IduAccessPresenterImpl mIduAccessPresenter;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    /* access modifiers changed from: private */
    public final LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            HomePageActivity homePageActivity = HomePageActivity.this;
            homePageActivity.removeLocationUpdates(homePageActivity.mLocationCallback);
            HomePageActivity.this.dismissPleaseWaitDialog();
        }
    };
    /* access modifiers changed from: private */
    public NavController mNavController;
    private final SingleLiveEvent<WebSocketNotification.RequestType> mNotificationRequestTypeSingleLiveEvent = new SingleLiveEvent<>();
    private final RacModelWiseConfigurationList mRacModelWiseConfigurationList = new RacModelWiseConfigurationList();
    private final BroadcastReceiver mRemoteMessageBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, final Intent intent) {
            HomePageActivity.this.getAllNotifications(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    Log.e("Payload", "filter()_remoteMessage_onSuccess");
                    HomePageActivity.this.mFcmViewModel.filter((RemoteMessage) intent.getParcelableExtra(Constants.FCM.REMOTE_MESSAGE));
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public Scenario mScenario;
    private final SingleLiveEvent<SwipeScreenType> mScreenSwipeSingleLiveEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<MotionEvent> mTouchPointerCountSingleLiveEvent = new SingleLiveEvent<>();
    private final UserPermissions mUserPermissions = UserPermissions.getInstance();
    /* access modifiers changed from: private */
    public final WebSocketWrapper mWebSocketWrapper = new WebSocketWrapper(new WebSocketWrapper.OnStompEvents() {
        public void onStompError() {
        }

        public void onStompOpened() {
        }

        public void onStompMessageReceived(StompMessage stompMessage) {
            String str;
            synchronized (HomePageActivity.this) {
                if (HomePageActivity.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    WebSocketNotification.RequestType requestType = null;
                    try {
                        str = new JSONObject(stompMessage.getPayload()).getString("notificationType");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        str = null;
                    }
                    if (str != null) {
                        requestType = WebSocketNotification.RequestType.valueOf(str);
                    }
                    if (requestType != null && requestType.equals(WebSocketNotification.RequestType.SCHEDULE_CONFLICT)) {
                        WebSocketNotification webSocketNotification = (WebSocketNotification) new Gson().fromJson(stompMessage.getPayload(), new TypeToken<WebSocketNotification<DetailedIduModel>>() {
                        }.getType());
                        if (webSocketNotification.getRequestTypeEnum() != null && webSocketNotification.getRequestTypeEnum().equals(WebSocketNotification.RequestType.SCHEDULE_CONFLICT)) {
                            HomePageActivity.this.runOnUiThread(new HomePageActivity$1$$ExternalSyntheticLambda5(this, webSocketNotification, requestType));
                        }
                    } else if (requestType == null || !requestType.equals(WebSocketNotification.RequestType.USER_FAMILY_DETACH)) {
                        WebSocketNotification webSocketNotification2 = (WebSocketNotification) new Gson().fromJson(stompMessage.getPayload(), new TypeToken<WebSocketNotification<IduList>>() {
                        }.getType());
                        HomePageActivity.this.dismissPleaseWaitDialog();
                        int i = C208616.f472x5ae7ea9d[webSocketNotification2.getRequestTypeEnum().ordinal()];
                        if (i == 1 || i == 2) {
                            HomePageActivity.this.dismissPleaseWaitDialog();
                            IduList iduList = (IduList) webSocketNotification2.data;
                            if (iduList != null) {
                                iduList.copyRequestTypeToDetailIduModels(webSocketNotification2.getRequestTypeEnum());
                                HomePageActivity.this.getGlobalViewModelRepository().getIDUsUpdateViewModel().updateListAndPost(iduList);
                                if (HomePageActivity.this.getForceRefreshMutableLiveData().hasActiveObservers()) {
                                    HomePageActivity.this.getForceRefreshMutableLiveData().postValue(true);
                                }
                                if (HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue() != null) {
                                    HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue().clear();
                                }
                                Iterator it = iduList.iterator();
                                while (it.hasNext()) {
                                    DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
                                    if (!(HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent() == null || HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue() == null)) {
                                        HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue().addAll(detailedIduModel.getAcActivitiesList(HomePageActivity.this.modelRepository));
                                        HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().postValue(HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue());
                                    }
                                }
                            }
                        } else if (i == 3 || i == 4) {
                            if (!((List) webSocketNotification2.data).isEmpty()) {
                                DetailedIduModel detailedIduModel2 = (DetailedIduModel) ((IduList) webSocketNotification2.data).get(0);
                                System.out.println("Testing " + detailedIduModel2.scheduletype);
                                detailedIduModel2.setRequestTypeString(webSocketNotification2.getRequestTypeEnum().name());
                                HomePageActivity.this.getGlobalViewModelRepository().getIDUsUpdateViewModel().updateIndividualIduAndPost(detailedIduModel2);
                                if (HomePageActivity.this.getForceRefreshMutableLiveData().hasActiveObservers()) {
                                    HomePageActivity.this.getForceRefreshMutableLiveData().postValue(true);
                                }
                                if (HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue() != null) {
                                    HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue().clear();
                                }
                                Iterator it2 = HomePageActivity.this.getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().iterator();
                                while (it2.hasNext()) {
                                    DetailedIduModel detailedIduModel3 = (DetailedIduModel) it2.next();
                                    if (!(HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent() == null || HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue() == null)) {
                                        HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue().addAll(detailedIduModel3.getAcActivitiesList(HomePageActivity.this.modelRepository));
                                        HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().postValue(HomePageActivity.this.mFcmViewModel.getAcActivitiesLiveEvent().getValue());
                                    }
                                }
                            }
                        }
                        if (HomePageActivity.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                            HomePageActivity.this.runOnUiThread(new HomePageActivity$1$$ExternalSyntheticLambda3(this));
                        }
                    } else {
                        HomePageActivity.this.runOnUiThread(new HomePageActivity$1$$ExternalSyntheticLambda4(this, (WebSocketNotification) new Gson().fromJson(stompMessage.getPayload(), new TypeToken<WebSocketNotification<RemoveFromFamilyNotificationModel>>() {
                        }.getType())));
                    }
                }
            }
        }

        /* renamed from: lambda$onStompMessageReceived$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ void mo30771xacb65c0d(WebSocketNotification webSocketNotification, WebSocketNotification.RequestType requestType) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) webSocketNotification.data;
            if (detailedIduModel.getConflictScheduleType() != null) {
                IduNotificationItem iduNotificationItem = null;
                if (detailedIduModel.getConflictScheduleType().equals(DetailedIduModel.ConflictScheduleType.TIMER)) {
                    if (detailedIduModel.scheduleConflict != null) {
                        iduNotificationItem = new IduNotificationItem(detailedIduModel.scheduleConflict.lastUpdatedAt, detailedIduModel.racId.intValue(), IDUNotificationType.TIMER_CONFLICT, detailedIduModel.name);
                        iduNotificationItem.setPriority(detailedIduModel.scheduleConflict.priority);
                    }
                } else if (detailedIduModel.getConflictScheduleType().equals(DetailedIduModel.ConflictScheduleType.WEEKLY_TIMER) && detailedIduModel.scheduleConflict != null) {
                    iduNotificationItem = new IduNotificationItem(detailedIduModel.scheduleConflict.lastUpdatedAt, detailedIduModel.racId.intValue(), IDUNotificationType.WEEKLY_TIMER_CONFLICT, detailedIduModel.name);
                    iduNotificationItem.setPriority(detailedIduModel.scheduleConflict.priority);
                }
                if (iduNotificationItem != null) {
                    HomePageActivity.this.getIduNotificationAdapter().setIduNotificationItemsList(iduNotificationItem);
                    HomePageActivity.this.getIduNotificationAdapter().notifyDataSetChanged();
                    if (HomePageActivity.this.getForceRefreshMutableLiveData().hasActiveObservers()) {
                        HomePageActivity.this.getForceRefreshMutableLiveData().postValue(true);
                    }
                    if (HomePageActivity.this.getNotificationRequestTypeSingleLiveEvent().hasActiveObservers()) {
                        HomePageActivity.this.getNotificationRequestTypeSingleLiveEvent().postValue(requestType);
                    }
                }
            }
        }

        /* renamed from: lambda$onStompMessageReceived$2$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ void mo30773xa97863cb(WebSocketNotification webSocketNotification) {
            if (HomePageActivity.this.removedFromFamilyAlert != null && !HomePageActivity.this.removedFromFamilyAlert.isShowing()) {
                HomePageActivity.this.removedFromFamilyAlert.setTitleString(HomePageActivity.this.getString(R.string.common_alert));
                if (FamilyGroupList.getCurrentFamily().familyId == ((RemoveFromFamilyNotificationModel) webSocketNotification.data).fromFamilyId) {
                    SingleChoiceDialog r0 = HomePageActivity.this.removedFromFamilyAlert;
                    r0.setMessageString(HomePageActivity.this.getString(R.string.home_alert_removedFromFamily) + StringUtils.f715LF + ((RemoveFromFamilyNotificationModel) webSocketNotification.data).familyName + "\n\n" + HomePageActivity.this.getString(R.string.home_alert_logoutAndLoginAgain));
                } else {
                    SingleChoiceDialog r02 = HomePageActivity.this.removedFromFamilyAlert;
                    r02.setMessageString(HomePageActivity.this.getString(R.string.home_alert_removedFromFamily) + StringUtils.f715LF + ((RemoveFromFamilyNotificationModel) webSocketNotification.data).familyName);
                }
                HomePageActivity.this.removedFromFamilyAlert.setCancelable(false);
                HomePageActivity.this.removedFromFamilyAlert.setPositiveButton(HomePageActivity.this.getString(R.string.common_btn_ok), new HomePageActivity$1$$ExternalSyntheticLambda0(this, webSocketNotification));
                HomePageActivity.this.removedFromFamilyAlert.show();
            }
        }

        /* renamed from: lambda$onStompMessageReceived$1$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ boolean mo30772x2b175fec(WebSocketNotification webSocketNotification, Dialog dialog, View view) {
            HomePageActivity.this.removedFromFamilyAlert.dismiss();
            if (FamilyGroupList.getCurrentFamily().familyId == ((RemoveFromFamilyNotificationModel) webSocketNotification.data).fromFamilyId) {
                HomePageActivity.this.logOutFromApplication();
                return true;
            }
            HomePageActivity.this.mNavController.popBackStack(HomePageActivity.this.mNavController.getGraph().getStartDestination(), false);
            if (!HomePageActivity.this.getFamilyUpdateSingleLiveEvent().hasActiveObservers()) {
                return true;
            }
            HomePageActivity.this.getFamilyUpdateSingleLiveEvent().postValue(webSocketNotification);
            return true;
        }

        /* renamed from: lambda$onStompMessageReceived$3$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ void mo30774x27d967aa() {
            HomePageActivity homePageActivity = HomePageActivity.this;
            homePageActivity.updateRecyclerView(homePageActivity.getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList());
        }

        public void onStompClosed() {
            HomePageActivity.this.runOnUiThread(new HomePageActivity$1$$ExternalSyntheticLambda2(this));
        }

        /* renamed from: lambda$onStompClosed$5$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ void mo30770xaa6301fa() {
            new Handler().postDelayed(new HomePageActivity$1$$ExternalSyntheticLambda1(this), 3000);
        }

        /* renamed from: lambda$onStompClosed$4$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$1 */
        public /* synthetic */ void mo30769x2c01fe1b() {
            HomePageActivity.this.mWebSocketWrapper.reconnectToWebSocket(HomePageActivity.this);
        }
    });
    @Inject
    ModelRepository modelRepository;
    private boolean pingBaidu = false;
    @Inject
    ViewModelProviderFactory providerFactory;
    /* access modifiers changed from: private */
    public SingleChoiceDialog removedFromFamilyAlert;

    private void clearFragmentStack() {
    }

    public void backPressFromIndividualIdu() {
    }

    public void checkStopAllButton() {
    }

    public void onIduListFetchingFailed() {
    }

    public void onIduListsFetched(List<DetailedIduModel> list) {
    }

    public void onModelTypeListAvailable(List<String> list) {
    }

    public void onModelWiseRacInfoAvailable(List<RacModelWiseData> list) {
    }

    public void onNetworkCallFailure(Throwable th) {
    }

    public void onNetworkCallSuccess() {
    }

    public void onPowerCommandExecutionFailure() {
    }

    public void onPowerCommandExecutionSuccess() {
    }

    public void onPowerOnOffFailed(long j, String str, int i) {
    }

    public void onPowerOnOffSuccessful(long j, String str) {
    }

    public void onPrivacyPolicyReceivedFailure() {
    }

    public void onStartAllPartiallyComplete(List<StopAllUnitsModels.IndividualRacStartAllUnitResponseData> list) {
    }

    public void onStartAllSuccessful() {
    }

    public void onStartingAllFailed() {
    }

    public void onStoppingFailed() {
    }

    public void onStoppingPartiallyComplete(List<StopAllUnitsModels.IndividualRacStopAllUintResponseData> list) {
    }

    public void onStoppingSuccessful() {
    }

    public void serverException() {
    }

    public void unCheckStopAllButton() {
    }

    public SingleLiveEvent<WebSocketNotification.RequestType> getNotificationRequestTypeSingleLiveEvent() {
        return this.mNotificationRequestTypeSingleLiveEvent;
    }

    public SingleLiveEvent<WebSocketNotification<RemoveFromFamilyNotificationModel>> getFamilyUpdateSingleLiveEvent() {
        return this.forceFamilyRefreshSingleLiveEvent;
    }

    public WebSocketWrapper getWebSocketWrapper() {
        return this.mWebSocketWrapper;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
        this.mLocationRationaleMutableLiveData.postValue(null);
        com.jch.racWiFi.Constants.IS_DEMO_MODE = getSessionManagerInstance().isDemoMode();
        this.locationPermissionViewModel = (LocationPermissionViewModel) ViewModelProviderUtil.getViewModelInstance(this, new LocationPermissionViewModel.LocationPermissionViewModelFactory(getFusedLocationProviderAPIExtension()), LocationPermissionViewModel.class);
        this.removedFromFamilyAlert = new SingleChoiceDialog(getActivity());
        requestWindowFeature(1);
        this.mBinding = (MainActivityBinding) DataBindingUtil.setContentView(this, R.layout.main_activity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        this.gestureDetector = new GestureDetector(this, new SwipeDetector(this, getScreenWidth()));
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra(com.jch.racWiFi.Constants.DEMOMODE, false);
            DemoModeModel.DEMO_MODE = false;
            DemoModeModel.DEMO_MODE_ON = booleanExtra;
        }
        this.mBinding.navView.setNavigationItemSelectedListener(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            this.mNavController = navHostFragment.getNavController();
        }
        NavigationUI.setupWithNavController(this.mBinding.navView, this.mNavController);
        this.mIduAccessPresenter = new IduAccessPresenterImpl(this);
        this.mNavController.addOnDestinationChangedListener(new HomePageActivity$$ExternalSyntheticLambda8(this));
        this.mBinding.navView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new HomePageActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.navView.getMenu().findItem(R.id.smartFenceNavGraph).setOnMenuItemClickListener(new HomePageActivity$$ExternalSyntheticLambda11(this));
        ((ImageButton) this.mBinding.navView.getHeaderView(0).findViewById(R.id.image_button_clear_navigation_header)).setOnClickListener(new HomePageActivity$$ExternalSyntheticLambda21(this));
        this.adapter = new IDUNotificationRecyclerViewAdapter(getActivity());
        updateNavigationViewItemLocale();
        if (bundle == null || !bundle.getBoolean(com.jch.racWiFi.Constants.TO_RESET_ACTIVITY)) {
            com.jch.racWiFi.Constants.NOT_TO_RESTART = false;
        } else {
            String str = this.TAG;
            Logger.m45d(str, "to reset = " + bundle.getBoolean(com.jch.racWiFi.Constants.TO_RESET_ACTIVITY));
            Intent intent2 = new Intent(this, HomePageActivity.class);
            intent2.setFlags(268468224);
            startActivity(intent2);
        }
        getGlobalViewModelRepository().getGeoFenceListViewModel().getSingleLiveEventStatus().observeSingleEvent(this, new Observer<Map<Long, LocationControlStateResponseModel>>() {
            public void onChanged(Map<Long, LocationControlStateResponseModel> map) {
                boolean z;
                Iterator<Map.Entry<Long, LocationControlStateResponseModel>> it = map.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((LocationControlStateResponseModel) it.next().getValue()).enabled) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!com.jch.racWiFi.Constants.IS_DEMO_MODE && z) {
                    if (!HomePageActivity.this.isForeGroundLocationPermissionGranted() || !HomePageActivity.this.isBackGroundLocationPermissionGranted()) {
                        HomePageActivity.this.locationPermissionViewModel.needsResolution(new AlertListener() {
                            public void onNegative() {
                            }

                            public void onPositive() {
                                if (HomePageActivity.this.mScenario != null && HomePageActivity.this.mScenario.isRationale()) {
                                    HomePageActivity.this.handleRationale(HomePageActivity.this.mScenario.getMode());
                                } else if (HomePageActivity.this.mScenario == null || HomePageActivity.this.mScenario.isRationale()) {
                                    HomePageActivity.this.checkPermissions();
                                } else {
                                    HomePageActivity.this.openSettings(HomePageActivity.this.mScenario.getMode());
                                }
                            }
                        }, true, HomePageActivity.this, LocationAccessRationale.SMART_FENCE);
                    } else {
                        HomePageActivity.this.checkPermissions();
                    }
                }
            }
        });
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().observe(this, new HomePageActivity$$ExternalSyntheticLambda7(this, new boolean[]{true}));
        }
        this.familyIdGeoFenceDataMapOld = getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().parcelClone();
        getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(this);
        initiateFCM(bundle, intent.getBooleanExtra(Constants.Keys.IS_LOGIN, false));
        getAmplitudeStatus();
        this.mLocationPermissionMutableLiveData.observe(this, new HomePageActivity$$ExternalSyntheticLambda26(this));
        this.mLocationRationaleMutableLiveData.observe(this, new HomePageActivity$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$onCreate$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30757x7cf01b5b(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (navDestination.getId() == R.id.helpFragment) {
            navDestination.addArgument(HelpWebPageModel.PARCEL_KEY, new NavArgument.Builder().setDefaultValue(HelpWebPageModel.MAIN_HELP_PAGE).build());
            navDestination.addArgument(Values.NAVIGATING_FROM, new NavArgument.Builder().setDefaultValue(0).build());
        }
        this.enableBackButtonGestureListener = SwipeDetector.LIST_OF_FRAGMENTS_TO_ENABLE_SWIPE.contains(Integer.valueOf(navDestination.getId()));
        switch (navDestination.getId()) {
            case R.id.customerCareFragment:
            case R.id.helpFragment:
            case R.id.homePageFragment:
            case R.id.manageDevicesFragment:
            case R.id.manageUsersFragment:
            case R.id.myAccountFragment2:
            case R.id.privacy_policy:
            case R.id.serviceRequestFragment:
            case R.id.settingsFragment:
            case R.id.weeklyTimerDevicesFragmentV2:
                onUnLockMenuDrawerDrawer();
                return;
            default:
                onLockMenuDrawerDrawer();
                return;
        }
    }

    /* renamed from: lambda$onCreate$1$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ boolean mo30758x112e8afa(MenuItem menuItem) {
        return logoutApplicationMethod();
    }

    /* renamed from: lambda$onCreate$2$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ boolean mo30759xa56cfa99(MenuItem menuItem) {
        SmartFenceGeoFenceSettings.isGeoFenceSettingChange = false;
        SmartFenceSelectAcFragment.isAcSettingChange = false;
        SmartFenceSelectUserFragment.isUserSettingChange = false;
        SmartFenceSetModeTempDilog.isModeTempSettingChange = false;
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().clear();
        }
        getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().putAll(this.familyIdGeoFenceDataMapOld.parcelClone());
        getNavController().navigate((int) R.id.smartFenceNavGraph);
        return true;
    }

    /* renamed from: lambda$onCreate$3$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30760x39ab6a38(View view) {
        this.mBinding.drawerLayout.closeDrawer((int) GravityCompat.START);
    }

    /* renamed from: lambda$onCreate$6$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30763xf666b915(boolean[] zArr, FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap) {
        if (zArr[0]) {
            zArr[0] = false;
            return;
        }
        familyIdGeoFenceDataMap.persist();
        this.familyIdGeoFenceDataMapOld = getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().parcelClone();
        getGoogleGeoFenceApiExtension().setFamilyIdGeoFenceDataMap(familyIdGeoFenceDataMap);
        getGoogleGeoFenceApiExtension().removeGeofences(new HomePageActivity$$ExternalSyntheticLambda10(this));
    }

    /* renamed from: lambda$onCreate$5$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30762x62284976(Task task) {
        getGoogleGeoFenceApiExtension().onComplete(task);
        if (task.isSuccessful()) {
            getGoogleGeoFenceApiExtension().addGeoFencesAll(new HomePageActivity$$ExternalSyntheticLambda9(this));
        }
    }

    /* renamed from: lambda$onCreate$4$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30761xcde9d9d7(Task task) {
        getGoogleGeoFenceApiExtension().onComplete(task);
        Logger.m50v(this.TAG, task.toString());
    }

    /* renamed from: lambda$onCreate$7$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30764x8aa528b4(String str) {
        if (str != null && str.trim().equals(HomePageActivity.class.getCanonicalName())) {
            checkLocationService();
        }
    }

    /* renamed from: lambda$onCreate$8$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30765x1ee39853(Map map) {
        if (map != null) {
            Scenario scenario = (Scenario) map.get(HomePageActivity.class.getCanonicalName());
            this.mScenario = scenario;
            if (scenario == null || !scenario.isRationale() || !this.mScenario.isWithoutLaunch()) {
                Scenario scenario2 = this.mScenario;
                if (scenario2 != null && !scenario2.isWithoutLaunch() && this.mScenario.getResultCode() != 0) {
                    if (!isForeGroundLocationPermissionGranted() || !isBackGroundLocationPermissionGranted()) {
                        openSettings(this.mScenario.getMode());
                    }
                }
            } else if (!isForeGroundLocationPermissionGranted() || !isBackGroundLocationPermissionGranted()) {
                handleRationale(this.mScenario.getMode());
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkPermissions() {
        if (!NetworkConnectivity.isNetworkAvailable(getActivity())) {
            GenericAlertUtils.getNoNetworkAlert(this).show();
        } else {
            checkLocationPermissions(HomePageActivity.class.getCanonicalName());
        }
    }

    /* access modifiers changed from: private */
    public void checkLocationService() {
        showPleaseWaitDialog();
        verifyLocationService(this, this.mLocationCallback, new LocationServiceListener() {
            public void onActivityResult() {
                HomePageActivity.this.checkLocationService();
            }

            public void onNegative() {
                HomePageActivity.this.dismissPleaseWaitDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateRecyclerView(IduList iduList) {
        LinkedList<IduNotificationItem> iduNotificationItemsList = this.adapter.getIduNotificationItemsList();
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            IduNotificationItem iduNotificationItem = null;
            if (detailedIduModel.iduFrostWashStatus.active) {
                iduNotificationItem = new IduNotificationItem(detailedIduModel.iduFrostWashStatus.lastUpdatedAt, detailedIduModel.f454id.intValue(), IDUNotificationType.IDU_FROST_WASH, detailedIduModel.name);
                iduNotificationItem.setPriority(detailedIduModel.iduFrostWashStatus.priority);
            }
            if (detailedIduModel.isInSpecialMode()) {
                iduNotificationItem = new IduNotificationItem(detailedIduModel.specialOperationStatus.lastUpdatedAt, detailedIduModel.f454id.intValue(), IDUNotificationType.SPECIAL_OPERATION, detailedIduModel.name);
                iduNotificationItem.setPriority(detailedIduModel.specialOperationStatus.priority);
            }
            if (iduNotificationItem != null && !iduNotificationItemsList.contains(iduNotificationItem)) {
                iduNotificationItemsList.addFirst(iduNotificationItem);
                Collections.sort(iduNotificationItemsList, HomePageActivity$$ExternalSyntheticLambda20.INSTANCE);
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    static /* synthetic */ int lambda$updateRecyclerView$9(IduNotificationItem iduNotificationItem, IduNotificationItem iduNotificationItem2) {
        return iduNotificationItem.priority - iduNotificationItem2.priority;
    }

    private void initiateFCM(Bundle bundle, boolean z) {
        this.mFcmViewModel = (FcmViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(FcmViewModel.class);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mRemoteMessageBroadcastReceiver, new IntentFilter(Constants.FCM.REMOTE_MESSAGE_BROADCAST_RECEIVER));
        getAllNotifications(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
            }
        });
        obtainToken(z);
        AdapterRepository adapterRepository = new AdapterRepository();
        this.mAdapterRepository = adapterRepository;
        adapterRepository.initInviteeListAdapter(this, new HomePageActivity$$ExternalSyntheticLambda15(this));
        this.mAdapterRepository.initAdapters();
        this.mAdapterRepository.getErrorAdapter().setListener(new FcmListener<Error, ErrorAdapter>() {
            public void onClose(View view, Error error, ErrorAdapter errorAdapter) {
            }

            public void onClick(View view, Error error, ErrorAdapter errorAdapter) {
                DeepLink deepLink;
                if (HomePageActivity.this.getMenuDrawer().isDrawerOpen((int) GravityCompat.END)) {
                    HomePageActivity.this.getMenuDrawer().closeDrawer((int) GravityCompat.END);
                }
                Bundle bundle1 = error.getBundle1();
                if (bundle1 != null && (deepLink = (DeepLink) bundle1.getParcelable(DeepLink.PARCEL_KEY)) != null && deepLink.getDestinationList() != null && !deepLink.getDestinationList().isEmpty()) {
                    HomePageActivity.this.launchScreen(deepLink, error);
                }
            }
        });
        this.mAdapterRepository.getReminderAdapter().setListener(new FcmListener<Reminder, ReminderAdapter>() {
            public void onClick(View view, Reminder reminder, ReminderAdapter reminderAdapter) {
                HomePageActivity.this.navigate(reminder.getBundle1());
            }

            public void onClose(View view, final Reminder reminder, ReminderAdapter reminderAdapter) {
                HomePageActivity.this.clearNotification(reminder.getId(), new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        if (HomePageActivity.this.mFcmViewModel.getReminderLiveData() != null && HomePageActivity.this.mFcmViewModel.getReminderLiveData().getValue() != null) {
                            HomePageActivity.this.mFcmViewModel.getReminderLiveData().getValue().remove(reminder);
                            HomePageActivity.this.mFcmViewModel.getReminderLiveData().postValue(HomePageActivity.this.mFcmViewModel.getReminderLiveData().getValue());
                        }
                    }
                });
            }
        });
        this.mAdapterRepository.getSmartFenceAdapter().setListener(new FcmListener<SmartFence, SmartFenceAdapter>() {
            public void onClick(View view, SmartFence smartFence, SmartFenceAdapter smartFenceAdapter) {
                HomePageActivity.this.navigate(smartFence.getBundle1());
            }

            public void onClose(View view, final SmartFence smartFence, SmartFenceAdapter smartFenceAdapter) {
                HomePageActivity.this.clearNotification(smartFence.getId(), new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        if (HomePageActivity.this.mFcmViewModel.getSmartFenceLiveData() != null && HomePageActivity.this.mFcmViewModel.getSmartFenceLiveData().getValue() != null) {
                            HomePageActivity.this.mFcmViewModel.getSmartFenceLiveData().getValue().remove(smartFence);
                            HomePageActivity.this.mFcmViewModel.getSmartFenceLiveData().postValue(HomePageActivity.this.mFcmViewModel.getSmartFenceLiveData().getValue());
                        }
                    }
                });
            }
        });
        this.mAdapterRepository.getAlertAdapter().setListener(new FcmListener<Alert, AlertAdapter>() {
            public void onClick(View view, Alert alert, AlertAdapter alertAdapter) {
                HomePageActivity.this.navigate(alert.getBundle1());
            }

            public void onClose(View view, Alert alert, final AlertAdapter alertAdapter) {
                HomePageActivity.this.clearNotification(alert.getId(), new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        HomePageActivity.this.getAllNotifications(new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                                if (alertAdapter != null) {
                                    alertAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                });
            }
        });
        this.mAdapterRepository.setIduNotificationRecyclerViewAdapter(this.adapter);
        FcmDashboard fcmDashboard = new FcmDashboard();
        this.mFcmDashboard = fcmDashboard;
        fcmDashboard.setAdapterRepository(this.mAdapterRepository);
        this.mFcmDashboard.onCreateView(LayoutInflater.from(this), this.mBinding.rightDrawerLayout, bundle, new HomePageActivity$$ExternalSyntheticLambda14(this));
        this.mFcmDashboard.getSectionAdapter().setListener(new FcmListener<Section, SectionAdapter>() {
            public void onClick(View view, Section section, SectionAdapter sectionAdapter) {
                sectionAdapter.notifyDataSetChanged();
            }

            public void onClose(View view, Section section, final SectionAdapter sectionAdapter) {
                HomePageActivity.this.clearAllNotification(section.getType().name(), new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        SectionAdapter sectionAdapter = sectionAdapter;
                        if (sectionAdapter != null) {
                            sectionAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
        initFcmObservers();
    }

    /* renamed from: lambda$initiateFCM$10$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30753xa761a996(GenericResponse genericResponse) {
        if (genericResponse.unableToReachServer()) {
            GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
        } else if (!genericResponse.isApiSuccessful()) {
            InviteMemberModels.InviteMemberCodeVerificationFailure inviteMemberCodeVerificationFailure = (InviteMemberModels.InviteMemberCodeVerificationFailure) genericResponse.getErrorBodyOfType(InviteMemberModels.InviteMemberCodeVerificationFailure.class);
            if (inviteMemberCodeVerificationFailure == null || inviteMemberCodeVerificationFailure.code == null) {
                Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE005), 0);
                return;
            }
            String str = inviteMemberCodeVerificationFailure.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2053325627:
                    if (str.equals("LEE001")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143800:
                    if (str.equals(InviteMemberModels.INVALID_CODE)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1995143799:
                    if (str.equals(InviteMemberModels.INVITED_USER_IS_NOT_REGISTERED_WITH_APP)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1995143798:
                    if (str.equals(InviteMemberModels.INVITATION_DOESNOT_BELONG_TO_THIS_USER)) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_LEE001), 0);
                    return;
                case 1:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE002), 0);
                    return;
                case 2:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE005), 0);
                    return;
                case 3:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE006), 0);
                    return;
                case 4:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE007), 0);
                    return;
                default:
                    Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE005), 0);
                    return;
            }
        }
    }

    /* renamed from: lambda$initiateFCM$12$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30755xcfde88d4() {
        this.mFcmViewModel.getInviteeLiveDataHolder().getInviteeListMutableLiveData().observe(this, new HomePageActivity$$ExternalSyntheticLambda25(this));
    }

    /* renamed from: lambda$initiateFCM$11$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30754x3ba01935(InviteeList inviteeList) {
        this.mAdapterRepository.getInviteeListAdapter().setInviteeList(inviteeList, this.mFcmDashboard);
        this.mFcmViewModel.updateCount();
    }

    /* access modifiers changed from: private */
    public void navigate(Bundle bundle) {
        DeepLink deepLink;
        if (getMenuDrawer().isDrawerOpen((int) GravityCompat.END)) {
            getMenuDrawer().closeDrawer((int) GravityCompat.END);
        }
        if (bundle != null && (deepLink = (DeepLink) bundle.getParcelable(DeepLink.PARCEL_KEY)) != null && deepLink.getDestinationList() != null && !deepLink.getDestinationList().isEmpty()) {
            launchScreen(deepLink);
        }
    }

    private void initFcmObservers() {
        this.mFcmViewModel.getErrorLiveData().observe(this, new HomePageActivity$$ExternalSyntheticLambda27(this));
        this.mFcmViewModel.getReminderLiveData().observe(this, new HomePageActivity$$ExternalSyntheticLambda28(this));
        this.mFcmViewModel.getAlertLiveEvent().observe(this, new HomePageActivity$$ExternalSyntheticLambda1(this));
        this.mFcmViewModel.getSmartFenceLiveData().observe(this, new HomePageActivity$$ExternalSyntheticLambda2(this));
        this.mFcmViewModel.getAcActivitiesLiveEvent().observe(this, new HomePageActivity$$ExternalSyntheticLambda24(this));
        this.mFcmViewModel.getSilentLiveEvent().observe(this, new HomePageActivity$$ExternalSyntheticLambda23(this));
    }

    /* renamed from: lambda$initFcmObservers$14$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30747x32b97ed2(List list) {
        if (this.mAdapterRepository != null) {
            Collections.sort(list, HomePageActivity$$ExternalSyntheticLambda17.INSTANCE);
            this.mAdapterRepository.getErrorAdapter().setErrorList(list);
            this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
        }
    }

    static /* synthetic */ int lambda$initFcmObservers$13(Error error, Error error2) {
        return (int) (error2.getTimestamp() - error.getTimestamp());
    }

    /* renamed from: lambda$initFcmObservers$16$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30748x5b365e10(List list) {
        if (this.mAdapterRepository != null) {
            Collections.sort(list, HomePageActivity$$ExternalSyntheticLambda18.INSTANCE);
            this.mAdapterRepository.getReminderAdapter().setReminderList(list);
            this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
        }
    }

    static /* synthetic */ int lambda$initFcmObservers$15(Reminder reminder, Reminder reminder2) {
        return (int) (reminder2.getTimestamp() - reminder.getTimestamp());
    }

    /* renamed from: lambda$initFcmObservers$18$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30749x83b33d4e(List list) {
        if (this.mAdapterRepository != null) {
            Collections.sort(list, HomePageActivity$$ExternalSyntheticLambda16.INSTANCE);
            this.mAdapterRepository.getAlertAdapter().setAlertList(list);
            this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
        }
    }

    static /* synthetic */ int lambda$initFcmObservers$17(Alert alert, Alert alert2) {
        return (int) (alert2.getTimestamp() - alert.getTimestamp());
    }

    /* renamed from: lambda$initFcmObservers$20$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30750xd54f4497(List list) {
        if (this.mAdapterRepository != null) {
            Collections.sort(list, HomePageActivity$$ExternalSyntheticLambda19.INSTANCE);
            this.mAdapterRepository.getSmartFenceAdapter().setSmartFenceList(list);
            this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
        }
    }

    static /* synthetic */ int lambda$initFcmObservers$19(SmartFence smartFence, SmartFence smartFence2) {
        return (int) (smartFence2.getTimestamp() - smartFence.getTimestamp());
    }

    /* renamed from: lambda$initFcmObservers$21$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30751x698db436(AcActivitiesList acActivitiesList) {
        this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
    }

    /* renamed from: lambda$initFcmObservers$22$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30752xfdcc23d5(Silent silent) {
        if (!(silent == null || silent.getSubCategory() == null || !silent.getSubCategory().equals(SilentSubCategory.CHANGE_PASSWORD))) {
            this.mFcmViewModel.getSilentLiveEvent().setValue(null);
            Log.e("Change", "first if");
            if (!ChangePasswordFragment.isPasswordChanged) {
                Log.e("Change", "second if");
                Toaster.makeToast(getApplicationContext(), getString(R.string.notification_lbl_desc_password_change), 1);
                logout();
            }
            ChangePasswordFragment.isPasswordChanged = false;
        }
        if (silent != null && silent.getSubCategory() != null && silent.getSubCategory().equals(SilentSubCategory.USER_DELETION)) {
            this.mFcmViewModel.getSilentLiveEvent().setValue(null);
            Persistence persistence = new Persistence();
            Boolean bool = (Boolean) persistence.obtain(Constants.Keys.IS_ACCOUNT_DELETED, Boolean.class);
            if (bool == null || bool.booleanValue()) {
                persistence.persist(Constants.Keys.IS_ACCOUNT_DELETED, false);
            } else {
                Toaster.makeToast(getApplicationContext(), getString(R.string.notification_lbl_accountWasDeletedOnOtherDevice), 1);
            }
            logout();
        }
    }

    public void clearAllNotification(String str, CallBackListener callBackListener) {
        this.mFcmViewModel.clearAllNotification(str).removeObservers(this);
        this.mFcmViewModel.clearAllNotification(str).observe(this, new HomePageActivity$$ExternalSyntheticLambda5(this, str, callBackListener));
    }

    /* renamed from: lambda$clearAllNotification$23$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30744x739d8ff6(final String str, final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C208616.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    String str2 = this.TAG;
                    Logger.m47e(str2, "clearAllNotification: Loading - " + resource.message);
                } else if (i == 3) {
                    Logger.m47e(this.TAG, "clearAllNotification: Success");
                    getAllNotifications(callBackListener);
                }
            } else if (resource.response != null && ((FcmResponse) resource.response).getMeta() != null && ((FcmResponse) resource.response).getMeta().getCode() == 401) {
                refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        HomePageActivity.this.clearAllNotification(str, callBackListener);
                    }
                }, false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearNotification(String str, CallBackListener callBackListener) {
        this.mFcmViewModel.clearNotification(str).removeObservers(this);
        this.mFcmViewModel.clearNotification(str).observe(this, new HomePageActivity$$ExternalSyntheticLambda6(this, str, callBackListener));
    }

    /* renamed from: lambda$clearNotification$24$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30745x198b5bf4(final String str, final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C208616.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                String str2 = this.TAG;
                Logger.m47e(str2, "clearNotification: Error - " + resource.message);
                if (resource.response != null && ((FcmResponse) resource.response).getMeta().getCode() == 401) {
                    refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            HomePageActivity.this.clearNotification(str, callBackListener);
                        }
                    }, false);
                }
            } else if (i == 2) {
                String str3 = this.TAG;
                Logger.m47e(str3, "clearNotification: Loading - " + resource.message);
            } else if (i == 3) {
                Integer value = this.mFcmViewModel.getNotificationCountLiveData().getValue();
                if (value != null && value.intValue() > 0) {
                    this.mFcmViewModel.getNotificationCountLiveData().setValue(Integer.valueOf(value.intValue() - 1));
                }
                callBackListener.onSuccess();
            }
        }
    }

    public void getAllNotifications(CallBackListener callBackListener) {
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mFcmViewModel.getNotifications().removeObservers(this);
            this.mFcmViewModel.getNotifications().observe(this, new HomePageActivity$$ExternalSyntheticLambda4(this, callBackListener));
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity$16 */
    static /* synthetic */ class C208616 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$WebSocketNotification$RequestType */
        static final /* synthetic */ int[] f472x5ae7ea9d;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                com.jch.racWiFi.di.model.Resource$Status[] r0 = com.jch.racWiFi.p010di.model.Resource.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$di$model$Resource$Status = r0
                r1 = 1
                com.jch.racWiFi.di.model.Resource$Status r2 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r3 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r4 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.jch.racWiFi.iduManagement.WebSocketNotification$RequestType[] r3 = com.jch.racWiFi.iduManagement.WebSocketNotification.RequestType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f472x5ae7ea9d = r3
                com.jch.racWiFi.iduManagement.WebSocketNotification$RequestType r4 = com.jch.racWiFi.iduManagement.WebSocketNotification.RequestType.ON_CONNECT     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = f472x5ae7ea9d     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.jch.racWiFi.iduManagement.WebSocketNotification$RequestType r3 = com.jch.racWiFi.iduManagement.WebSocketNotification.RequestType.REFRESH_ALL     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = f472x5ae7ea9d     // Catch:{ NoSuchFieldError -> 0x004d }
                com.jch.racWiFi.iduManagement.WebSocketNotification$RequestType r1 = com.jch.racWiFi.iduManagement.WebSocketNotification.RequestType.BUCKET_UPDATE     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = f472x5ae7ea9d     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.jch.racWiFi.iduManagement.WebSocketNotification$RequestType r1 = com.jch.racWiFi.iduManagement.WebSocketNotification.RequestType.REFRESH_INDIVIDUAL     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity.C208616.<clinit>():void");
        }
    }

    /* renamed from: lambda$getAllNotifications$25$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30746xcf62f38(final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C208616.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                String str = this.TAG;
                Logger.m47e(str, "getAllNotifications: Error - " + resource.message);
                if (resource.response != null && ((FcmResponse) resource.response).getMeta().getCode() == 401) {
                    refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            HomePageActivity.this.getAllNotifications(callBackListener);
                        }
                    }, false);
                }
            } else if (i == 2) {
                String str2 = this.TAG;
                Logger.m47e(str2, "getAllNotifications: Loading - " + resource.message);
            } else if (i == 3) {
                Logger.m47e(this.TAG, "getAllNotifications: persistToken: Success");
                callBackListener.onSuccess();
            }
        }
    }

    private void obtainToken(boolean z) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new HomePageActivity$$ExternalSyntheticLambda12(this, z));
    }

    /* renamed from: lambda$obtainToken$26$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30756xa79ce1ee(boolean z, Task task) {
        if (!task.isSuccessful()) {
            Logger.m48e(this.TAG, "getInstanceId failed", task.getException());
            return;
        }
        InstanceIdResult instanceIdResult = (InstanceIdResult) task.getResult();
        if (instanceIdResult != null) {
            String token = instanceIdResult.getToken();
            if (z) {
                persistToken(token);
            }
            String str = this.TAG;
            Logger.m47e(str, "Token : " + token);
        }
    }

    private void persistToken(String str) {
        if (str != null && !TextUtils.isEmpty(str) && TokenUtil.getInstance().isValid()) {
            registerDevice(str, new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    Logger.m47e(HomePageActivity.this.TAG, "onSuccess: Register Device");
                }
            });
        }
    }

    public boolean logoutApplicationMethod() {
        logEvents(Events.LOGOUT_FREQUENCY.name(), 0);
        logout();
        return true;
    }

    public void logOutFromApplication() {
        super.logOutFromApplication();
        logoutApplicationMethod();
    }

    public void connectStompClient() {
        super.connectStompClient();
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE && !this.mWebSocketWrapper.isConnected()) {
            this.mWebSocketWrapper.connectToWebSocket(this);
        }
    }

    public SingleLiveEvent<MotionEvent> getTouchPointerCountSingleLiveEvent() {
        return this.mTouchPointerCountSingleLiveEvent;
    }

    public void clearNotificationDrawer() {
        this.mBinding.drawerLayout.closeDrawer((int) GravityCompat.END);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PermissionApiImpl.getInstance().requestConfiguartionAndRoles();
        ConfigurationDataProvider.getInstance().iduAccessModelMutableLiveData.observe(this, new HomePageActivity$$ExternalSyntheticLambda22(this));
        if (DemoModeModel.DEMO_MODE_ON) {
            this.adapter.notifyDataSetChanged();
        }
    }

    /* renamed from: lambda$onResume$27$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity */
    public /* synthetic */ void mo30766x76798dbf(IduAccessModel iduAccessModel) {
        IduAccessPresenterImpl iduAccessPresenterImpl = this.mIduAccessPresenter;
        if (iduAccessPresenterImpl != null) {
            iduAccessPresenterImpl.updatePermissionsMap(iduAccessModel);
        }
    }

    public void onPermissionsUpdated() {
        Menu menu = this.mBinding.navView.getMenu();
        boolean z = false;
        menu.findItem(R.id.direct_onboarding).setVisible(false);
        menu.findItem(R.id.homePageFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.HOME));
        menu.findItem(R.id.weeklyTimerDevicesFragmentV2).setVisible(com.jch.racWiFi.Constants.IS_DEMO_MODE || this.mUserPermissions.getPermission(UserPermissions.UserFeatures.WEEKLY_TIMER));
        menu.findItem(R.id.holidayModeFragment).setVisible(com.jch.racWiFi.Constants.IS_DEMO_MODE || this.mUserPermissions.getPermission(UserPermissions.UserFeatures.HOLIDAY_MODE));
        menu.findItem(R.id.manageUsersFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.MANAGE_USERS));
        menu.findItem(R.id.manageDevicesFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.MANAGE_DEVICES));
        menu.findItem(R.id.energyConsumptionNavGraph).setVisible(com.jch.racWiFi.Constants.IS_DEMO_MODE || this.mUserPermissions.getPermission(UserPermissions.UserFeatures.ENERGY_CONSUMPTION));
        menu.findItem(R.id.customerCareFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.CUSTOMER_CARE));
        menu.findItem(R.id.helpFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.HELP));
        menu.findItem(R.id.myAccountFragment2).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.MY_ACCOUNT));
        menu.findItem(R.id.settingsFragment).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.SETTINGS));
        menu.findItem(R.id.nav_logout).setVisible(this.mUserPermissions.getPermission(UserPermissions.UserFeatures.LOGOUT));
        MenuItem findItem = menu.findItem(R.id.smartFenceNavGraph);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE || this.mUserPermissions.getPermission(UserPermissions.UserFeatures.SMART_FENCE)) {
            z = true;
        }
        findItem.setVisible(z);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mRemoteMessageBroadcastReceiver);
        if (DemoModeModel.DEMO_MODE) {
            TokenUtil.getInstance().clear();
        }
        this.mWebSocketWrapper.disconnectWebSocket();
        this.mWebSocketWrapper.dispose();
        InviteeLiveDataHolder.getInstance().clearLiveDataCache();
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_customer_care) {
            clearFragmentStack();
            CustomerCareFragmentGlobal.replaceFragmentBackStack(getSupportFragmentManager(), CustomerCareFragmentGlobal.newInstance());
            closeMenuDrawer();
            return true;
        } else if (itemId == R.id.nav_help) {
            clearFragmentStack();
            HelpFragmentGlobal newInstance = HelpFragmentGlobal.newInstance();
            newInstance.getArguments().putParcelable(HelpWebPageModel.PARCEL_KEY, HelpWebPageModel.MAIN_HELP_PAGE);
            HelpFragmentGlobal.replaceFragmentBackStack(getSupportFragmentManager(), newInstance);
            closeMenuDrawer();
            return true;
        } else if (itemId != R.id.nav_weekly_timer) {
            switch (itemId) {
                case R.id.nav_logout:
                    logout();
                    return true;
                case R.id.nav_manage_devices:
                    closeMenuDrawer();
                    return true;
                case R.id.nav_manage_users:
                    clearFragmentStack();
                    ManageUsersFragment.replaceFragmentBackStack(getSupportFragmentManager(), ManageUsersFragment.newInstance());
                    closeMenuDrawer();
                    return true;
                case R.id.nav_my_accout:
                    this.mNavController.navigate((int) R.id.action_homePageFragment_to_myAccountFragment2);
                    return true;
                case R.id.nav_service_request:
                    clearFragmentStack();
                    closeMenuDrawer();
                    return true;
                case R.id.nav_settings:
                    clearFragmentStack();
                    SettingsFragment.replaceFragmentBackStack(getSupportFragmentManager(), SettingsFragment.newInstance());
                    closeMenuDrawer();
                    return true;
                default:
                    return true;
            }
        } else {
            clearFragmentStack();
            closeMenuDrawer();
            return true;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        com.jch.racWiFi.Constants.INVITE_CODE = null;
        com.jch.racWiFi.Constants._INVITE_ = false;
        if (DemoModeModel.DEMO_MODE) {
            TokenUtil.getInstance().clear();
            UserInfo.resetCurrentUserInfo(this);
        }
    }

    public void onLockMenuDrawerDrawer() {
        this.mBinding.drawerLayout.setDrawerLockMode(1, (int) GravityCompat.START);
        this.mBinding.drawerLayout.setDrawerLockMode(1, (int) GravityCompat.END);
    }

    public void onUnLockMenuDrawerDrawer() {
        this.mBinding.drawerLayout.setDrawerLockMode(0, (int) GravityCompat.START);
    }

    public void closeMenuDrawer() {
        this.mBinding.drawerLayout.closeDrawer((int) GravityCompat.START);
    }

    public void openMenuDrawer() {
        this.mBinding.drawerLayout.openDrawer((int) GravityCompat.START);
    }

    public void onFragmentBackPressed() {
        onBackPressed();
    }

    public void openNotificationDrawer() {
        this.mBinding.drawerLayout.openDrawer((int) GravityCompat.END);
        this.mFcmDashboard.getSectionAdapter().notifyDataSetChanged();
    }

    public DrawerLayout getMenuDrawer() {
        return this.mBinding.drawerLayout;
    }

    public void onInternetConnectionCheck(boolean z, int i) {
        dismissPleaseWaitDialog();
        if (z) {
            if (i == 0) {
                logout();
            }
        } else if (i == 0) {
            if (!this.pingBaidu) {
                new NetworkConnectivity.CheckInternet(this, 0).execute(new String[]{"http://www.baidu.com/"});
                this.pingBaidu = true;
            }
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this);
            singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getActivity().getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new HomePageActivity$$ExternalSyntheticLambda13(singleChoiceDialog));
            singleChoiceDialog.show();
        }
    }

    private void logout() {
        getSessionManagerInstance().setDemoMode(false);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            com.jch.racWiFi.Constants.IS_DEMO_MODE = false;
            LocaleConfiguration.persistLocalization(LocaleConfiguration.DEFAULT);
            this.modelRepository.clearDirectory();
            moveToLoginFragment();
            return;
        }
        forceLogout((String) null);
    }

    public void moveToLoginFragment() {
        super.moveToLoginFragment();
    }

    public NavController getNavController() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            return navHostFragment.getNavController();
        }
        return this.mNavController;
    }

    public void onPrivacyPolicyReceived(PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse, int i) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (i != 204) {
                privacyPolicyDataResponse.privacyPolicyData[0].persist();
            }
            moveToLoginFragment();
        }
    }

    public class IduNotificationItem {
        public IDUNotificationType iduNotificationType;
        public int imageType;
        public String notificationDetail;
        public String notificationType;
        int priority;
        public int racId;
        public String racName;
        public int status;
        public long timeStamp;

        public int getPriority() {
            return this.priority;
        }

        public void setPriority(int i) {
            this.priority = i;
        }

        public IduNotificationItem(long j, int i, IDUNotificationType iDUNotificationType, String str) {
            this.timeStamp = j;
            this.racId = i;
            this.iduNotificationType = iDUNotificationType;
            IDUNotificationTypeUIConfigration uiConfigration = iDUNotificationType.getUiConfigration();
            this.notificationType = HomePageActivity.this.getString(uiConfigration.getNotificationTitle());
            this.notificationDetail = HomePageActivity.this.getString(uiConfigration.getNotificationMessage(), new Object[]{str});
            this.imageType = uiConfigration.getNotificationIcon();
            this.racName = str;
        }

        public int getRacId() {
            return this.racId;
        }

        public IDUNotificationType getIduNotificationType() {
            return this.iduNotificationType;
        }

        public String getNotificationType() {
            return this.notificationType;
        }

        public void setNotificationType(String str) {
            this.notificationType = str;
        }

        public String getNotificationDetail() {
            return this.notificationDetail;
        }

        public void setNotificationDetail(String str) {
            this.notificationDetail = str;
        }

        public int getImageType() {
            return this.imageType;
        }

        public void setImageType(int i) {
            this.imageType = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IduNotificationItem)) {
                return false;
            }
            IduNotificationItem iduNotificationItem = (IduNotificationItem) obj;
            if (iduNotificationItem.racId != this.racId || !iduNotificationItem.getIduNotificationType().equals(this.iduNotificationType)) {
                return false;
            }
            return true;
        }
    }

    public IDUNotificationRecyclerViewAdapter getIduNotificationAdapter() {
        return this.adapter;
    }

    public static class IDUNotificationRecyclerViewAdapter extends RecyclerView.Adapter<IDUNotificationViewHolder> {
        Context context;
        LinkedList<IduNotificationItem> iduNotificationItemsList = new LinkedList<>();

        public IDUNotificationRecyclerViewAdapter(Context context2) {
            this.context = context2;
        }

        public LinkedList<IduNotificationItem> getIduNotificationItemsList() {
            return this.iduNotificationItemsList;
        }

        public void setIduNotificationItemsList(LinkedList<IduNotificationItem> linkedList) {
            this.iduNotificationItemsList = linkedList;
        }

        public void setIduNotificationItemsList(IduNotificationItem iduNotificationItem) {
            this.iduNotificationItemsList.add(iduNotificationItem);
        }

        public IDUNotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new IDUNotificationViewHolder((RecyclerViewItemsNotificationsBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.recycler_view_items_notifications, viewGroup, false));
        }

        public void onBindViewHolder(IDUNotificationViewHolder iDUNotificationViewHolder, int i) {
            iDUNotificationViewHolder.bind(this.iduNotificationItemsList.get(i));
        }

        public int getItemCount() {
            LinkedList<IduNotificationItem> linkedList = this.iduNotificationItemsList;
            if (linkedList != null) {
                return linkedList.size();
            }
            return 0;
        }

        class IDUNotificationViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerViewItemsNotificationsBinding binding;

            public IDUNotificationViewHolder(RecyclerViewItemsNotificationsBinding recyclerViewItemsNotificationsBinding) {
                super(recyclerViewItemsNotificationsBinding.getRoot());
                this.binding = recyclerViewItemsNotificationsBinding;
                recyclerViewItemsNotificationsBinding.imageButtonClearNotification.setOnClickListener(new C2100xe95eec4e(this, recyclerViewItemsNotificationsBinding));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageActivity$IDUNotificationRecyclerViewAdapter$IDUNotificationViewHolder */
            public /* synthetic */ void mo30792x737357cc(RecyclerViewItemsNotificationsBinding recyclerViewItemsNotificationsBinding, View view) {
                onClickClearItem(recyclerViewItemsNotificationsBinding.imageButtonClearNotification);
            }

            public void bind(IduNotificationItem iduNotificationItem) {
                this.binding.textViewNotificationDetail.setText(iduNotificationItem.getNotificationDetail());
                this.binding.imageButtonClearNotification.setVisibility(4);
                this.binding.textViewNoyificationType.setText(iduNotificationItem.getNotificationType());
                this.binding.imageViewNotificationType.setImageResource(iduNotificationItem.getImageType());
                this.binding.textViewNotoficationDate.setText(DateAndTimeUtils.getTimeFromMilliSec(iduNotificationItem.timeStamp, "dd MMM yyyy, hh:mm aa", LocaleConfiguration.getCurrentAppLocale()));
                this.binding.imageButtonClearNotification.setTag(iduNotificationItem);
            }

            public void onClickClearItem(ImageButton imageButton) {
                IDUNotificationRecyclerViewAdapter.this.iduNotificationItemsList.remove((IduNotificationItem) imageButton.getTag());
                IDUNotificationRecyclerViewAdapter.this.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void refreshIndividualIduState(CoreActivity coreActivity, int i) {
        this.mWebSocketWrapper.individualIduRefresh(coreActivity, i);
    }

    public void refreshAllIduStates() {
        this.mWebSocketWrapper.refreshAllIduStates(this);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Logger.m45d("Multitouch detected!", String.valueOf(motionEvent.getPointerCount()));
        this.mTouchPointerCountSingleLiveEvent.postValue(motionEvent);
        GestureDetector gestureDetector2 = this.gestureDetector;
        if (gestureDetector2 == null || !this.enableBackButtonGestureListener || !gestureDetector2.onTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public RacModelWiseConfigurationList getRacModelWiseConfigurationList() {
        return this.mRacModelWiseConfigurationList;
    }

    public RacModelWiseData getRacModelWiseDataBasedOnRacTypeId(String str) {
        return this.mRacModelWiseConfigurationList.getRacModelWiseDataBasedOnRacTypeId(str);
    }

    public FamilyGroupList getFamilyGroupList() {
        return this.familyGroupList;
    }

    public FamilyMembersMap getFamilyMembersMap() {
        return this.familyMembersMap;
    }

    private void updateNavigationViewItemLocale() {
        Menu menu = this.mBinding.navView.getMenu();
        menu.findItem(R.id.homePageFragment).setTitle(getString(R.string.menu_item_home));
        menu.findItem(R.id.weeklyTimerDevicesFragmentV2).setTitle(R.string.menu_item_weeklyTimer);
        menu.findItem(R.id.manageUsersFragment).setTitle(R.string.menu_item_manageUser);
        menu.findItem(R.id.manageDevicesFragment).setTitle(R.string.menu_item_manageAC);
        menu.findItem(R.id.customerCareFragment).setTitle(R.string.menu_item_customerCare);
        menu.findItem(R.id.helpFragment).setTitle(R.string.menu_item_help);
        menu.findItem(R.id.serviceRequestFragment).setTitle(R.string.serviceRequest_lbl_serviceRequest);
        menu.findItem(R.id.myAccountFragment2).setTitle(R.string.menu_item_myAcc);
        menu.findItem(R.id.privacy_policy).setTitle(R.string.menu_item_privacyPolicy);
        menu.findItem(R.id.settingsFragment).setTitle(R.string.menu_item_settings);
        menu.findItem(R.id.nav_logout).setTitle(R.string.menu_item_logOut);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.enableBackButtonGestureListener && this.gestureDetector.onTouchEvent(motionEvent);
    }

    public void onSwipeScreenType(SwipeScreenType swipeScreenType) {
        if (swipeScreenType == SwipeScreenType.RIGHT_SWIPE && this.enableBackButtonGestureListener) {
            this.mNavController.navigateUp();
        } else if (this.mScreenSwipeSingleLiveEvent.hasActiveObservers()) {
            this.mScreenSwipeSingleLiveEvent.postValue(swipeScreenType);
        }
    }

    public SingleLiveEvent<SwipeScreenType> getScreenSwipeSingleLiveEvent() {
        return this.mScreenSwipeSingleLiveEvent;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!com.jch.racWiFi.Constants.NOT_TO_RESTART) {
            bundle.putBoolean(com.jch.racWiFi.Constants.TO_RESET_ACTIVITY, true);
        }
    }

    public SingleLiveEvent<Boolean> getForceRefreshMutableLiveData() {
        return this.forceRefreshMutableLiveData;
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public SessionManager getSessionManagerInstance() {
        return super.getSessionManagerInstance();
    }
}
