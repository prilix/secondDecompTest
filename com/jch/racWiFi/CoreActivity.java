package com.jch.racWiFi;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import com.accord.common.utils.Logger;
import com.accord.fusedlocationmodule.FusedLocationProviderAPIExtension;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SessionManager;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.amplitude.exception.AmplitudeException;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import com.jch.racWiFi.amplitude.model.RequestBody;
import com.jch.racWiFi.amplitude.model.ResponseBody;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.amplitude.util.AmplitudeUtil;
import com.jch.racWiFi.amplitude.util.Mode;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.TokenResponse;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.DeepLink;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.genericViewModel.GlobalViewModelRepository;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.WebSocketWrapper;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GoogleGeoFenceApiExtension;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment;
import com.jch.racWiFi.main.model.RefreshTokenResponse;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.p010di.model.Meta;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.p010di.util.TokenInfo;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.countryCodeManager.Country;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersMap;
import com.jch.racWiFi.userManagement.model.RemoveFromFamilyNotificationModel;
import com.jch.racWiFi.userManagement.view.MyAccountAddressFragment;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep4Fragment;
import com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiActivity;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiScanCallBack;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;
import com.jch.racWiFi.util.listeners.ShowNotRationaleListener;
import com.jch.racWiFi.weather.model.Weather;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.AndroidInjection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.json.JSONException;
import org.slf4j.Marker;

public class CoreActivity extends WifiActivity implements IBaseActivity, FragmentToActivityCallback, LocationListener, WifiScanCallBack {
    private static final int PERMISSION_REQUEST_CODE = 111;
    private static final String TAG = "CoreActivity";
    public static HashMap<String, String> countryCodeToIsoCodeHashMap = new HashMap<>();
    private final Handler dismissHandler = new Handler();
    private FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension;
    private GlobalViewModelRepository globalViewModelRepository;
    private GoogleGeoFenceApiExtension googleGeoFenceApiExtension;
    private boolean isPermissionWithoutLaunch;
    private boolean locationRequested = false;
    private final ActivityResultLauncher<String> mAccessBackgroundLocationPermissionResult;
    private final ActivityResultLauncher<Intent> mActivityResultBackgroundLocationShowNotRationale;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<Intent> mActivityResultLocationService;
    private final ActivityResultLauncher<Intent> mActivityResultMultiplePermissionsShowNotRationale;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<Intent> mActivityResultNotRationale;
    @Inject
    AmplitudeUtil mAmplitudeUtil;
    private String mCanonicalName;
    @Inject
    ClientFactory mClientFactory;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    private LocationManager mLocationManager;
    public final MutableLiveData<String> mLocationPermissionMutableLiveData;
    public final MutableLiveData<Map<String, Scenario>> mLocationRationaleMutableLiveData;
    /* access modifiers changed from: private */
    public LocationServiceListener mLocationServiceListener;
    private MainViewModel mMainViewModel;
    private final ActivityResultLauncher<String[]> mMultiplePermissionResult;
    private final String[] mPermissions;
    /* access modifiers changed from: private */
    public CustomProgressDialog mPleaseWaitDialog;
    private SessionManager mSessionManager;
    private SettingsClient mSettingsClient;
    /* access modifiers changed from: private */
    public ShowNotRationaleListener mShowNotRationaleListener;
    public Toaster mToaster;
    WifiManager mWifiManager;
    private final Handler mainLooper = new Handler(Looper.getMainLooper());
    @Inject
    ViewModelProviderFactory providerFactory;
    private final BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            CoreActivity.this.wifiScanCallBack.onWifiScanCompleted(CoreActivity.this.mWifiManager.getScanResults());
            CoreActivity.this.unregisterReceiver(this);
        }
    };
    WifiScanCallBack wifiScanCallBack = this;

    static /* synthetic */ boolean lambda$showPermissionDeniedDialog$7(Dialog dialog, View view) {
        return true;
    }

    public void backPressFromIndividualIdu() {
    }

    public void connectStompClient() {
    }

    public void finishUserManagementActivity() {
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public FamilyGroupList getFamilyGroupList() {
        return null;
    }

    public FamilyMembersMap getFamilyMembersMap() {
        return null;
    }

    public SingleLiveEvent<WebSocketNotification<RemoveFromFamilyNotificationModel>> getFamilyUpdateSingleLiveEvent() {
        return null;
    }

    public SingleLiveEvent<Boolean> getForceRefreshMutableLiveData() {
        return null;
    }

    public HomePageActivity.IDUNotificationRecyclerViewAdapter getIduNotificationAdapter() {
        return null;
    }

    public NavController getNavController() {
        return null;
    }

    public SingleLiveEvent<WebSocketNotification.RequestType> getNotificationRequestTypeSingleLiveEvent() {
        return null;
    }

    public RacModelWiseConfigurationList getRacModelWiseConfigurationList() {
        return null;
    }

    public RacModelWiseData getRacModelWiseDataBasedOnRacTypeId(String str) {
        return null;
    }

    public SingleLiveEvent<SwipeScreenType> getScreenSwipeSingleLiveEvent() {
        return null;
    }

    public SingleLiveEvent<MotionEvent> getTouchPointerCountSingleLiveEvent() {
        return null;
    }

    public WebSocketWrapper getWebSocketWrapper() {
        return null;
    }

    public void logOutFromApplication() {
    }

    public void moveToHomePageActivity(boolean z, boolean z2) {
    }

    public void onWifiScanCompleted(List<ScanResult> list) {
    }

    public void printHashKey() {
    }

    public void reCreateUserManagementActivity() {
    }

    public void refreshAllIduStates() {
    }

    public void refreshIndividualIduState(CoreActivity coreActivity, int i) {
    }

    public CoreActivity() {
        String[] strArr;
        if (isAndroid10OrHigher()) {
            strArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION, "android.permission.ACCESS_BACKGROUND_LOCATION"};
        } else {
            strArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION};
        }
        this.mPermissions = strArr;
        this.mActivityResultLocationService = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            public void onActivityResult(ActivityResult activityResult) {
                CoreActivity.this.mLocationServiceListener.onActivityResult();
            }
        });
        this.mActivityResultNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            public void onActivityResult(ActivityResult activityResult) {
                CoreActivity.this.mShowNotRationaleListener.onActivityResult();
            }
        });
        this.mLocationPermissionMutableLiveData = new MutableLiveData<>();
        this.isPermissionWithoutLaunch = true;
        this.mMultiplePermissionResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new CoreActivity$$ExternalSyntheticLambda5(this));
        this.mAccessBackgroundLocationPermissionResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new CoreActivity$$ExternalSyntheticLambda4(this));
        this.mLocationRationaleMutableLiveData = new MutableLiveData<>();
        this.mActivityResultMultiplePermissionsShowNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CoreActivity$$ExternalSyntheticLambda0(this));
        this.mActivityResultBackgroundLocationShowNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CoreActivity$$ExternalSyntheticLambda3(this));
    }

    public FusedLocationProviderAPIExtension getFusedLocationProviderAPIExtension() {
        return this.fusedLocationProviderAPIExtension;
    }

    public GlobalViewModelRepository getGlobalViewModelRepository() {
        return this.globalViewModelRepository;
    }

    public GoogleGeoFenceApiExtension getGoogleGeoFenceApiExtension() {
        return this.googleGeoFenceApiExtension;
    }

    public WifiManager getWifiManager() {
        return this.mWifiManager;
    }

    public Location getLastKnownLocation() {
        return getLocationManager().getLastKnownLocation("gps");
    }

    public LocationManager getLocationManager() {
        return this.mLocationManager;
    }

    public Handler getMainHandler() {
        return this.mainLooper;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
        this.mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        this.mSettingsClient = LocationServices.getSettingsClient((Activity) this);
        this.globalViewModelRepository = new GlobalViewModelRepository(this);
        this.googleGeoFenceApiExtension = new GoogleGeoFenceApiExtension(getApplicationContext());
        this.mSessionManager = new SessionManager(getApplicationContext());
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = LocaleConfiguration.getCurrentAppLocale();
        new Resources(getAssets(), new DisplayMetrics(), configuration);
        Logger.m49i(TAG, "onCreate");
        printHashKey();
        this.mWifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.mLocationManager = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        this.mToaster = new Toaster(getApplicationContext());
        Logger.m49i("Locale:", LocaleConfiguration.getCurrentAppLocale().toString());
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity());
        this.mPleaseWaitDialog = customProgressDialog;
        customProgressDialog.setProgressStyle(R.style.progress_red);
        this.mPleaseWaitDialog.setCancelable(false);
        this.mPleaseWaitDialog.setMessage(getString(R.string.common_alert_pleaseWait));
        for (int i = 0; i < CountryUtils.getAllCountries(getActivity()).size(); i++) {
            String string = getApplicationContext().getString(CountryUtils.getAllCountries(getApplicationContext()).get(i).getIso());
            CountryUtils.getAllCountries(getActivity()).get(i).setCountryCodeAndIsoCode(getApplicationContext().getString(CountryUtils.getAllCountries(getApplicationContext()).get(i).getPhoneCode()), string);
        }
        for (int i2 = 0; i2 < CountryUtils.getAllCountries(getActivity()).size(); i2++) {
            HashMap<String, String> hashMap = countryCodeToIsoCodeHashMap;
            hashMap.put(Marker.ANY_NON_NULL_MARKER + CountryUtils.getAllCountries(getApplicationContext()).get(i2).getCountryCode(), CountryUtils.getAllCountries(getApplicationContext()).get(i2).getIsoCode());
        }
        this.fusedLocationProviderAPIExtension = new FusedLocationProviderAPIExtension((Context) this, FusedLocationProviderAPIExtension.LocationRequestType.HIGH_ACCURACY_ONE_SECOND_INTERVAL);
        Logger.m47e("Locale_current", getResources().getConfiguration().locale.toString());
        initiateViewModel();
    }

    private void initiateViewModel() {
        this.mMainViewModel = (MainViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(MainViewModel.class);
    }

    public void openPlayStore(String str) {
        Intent data = new Intent("android.intent.action.VIEW").setData(Uri.parse(str));
        try {
            startActivity(new Intent(data).setPackage("com.android.vending"));
        } catch (ActivityNotFoundException unused) {
            startActivity(data);
        }
    }

    public MainViewModel getMainViewModel() {
        return this.mMainViewModel;
    }

    /* access modifiers changed from: protected */
    public void deRegisterDevice(String str, String str2, CallBackListener callBackListener) {
        this.mMainViewModel.deRegisterDevice(str, str2).removeObservers(this);
        this.mMainViewModel.deRegisterDevice(str, str2).observe(this, new CoreActivity$$ExternalSyntheticLambda7(this, str, str2, callBackListener));
    }

    /* renamed from: lambda$deRegisterDevice$0$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m783lambda$deRegisterDevice$0$comjchracWiFiCoreActivity(final String str, final String str2, final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C161711.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    Logger.m47e(TAG, "deRegisterDevice: Loading");
                } else if (i == 3) {
                    Logger.m47e(TAG, "deRegisterDevice: Success");
                    callBackListener.onSuccess();
                }
            } else if (resource.response != null && ((TokenResponse) resource.response).getMeta().getCode() == 401) {
                Persistence persistence = new Persistence();
                Boolean bool = (Boolean) persistence.obtain(Constants.Keys.IS_ACCOUNT_DELETED, Boolean.class);
                if (bool == null || !bool.booleanValue()) {
                    refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            CoreActivity.this.deRegisterDevice(str, str2, callBackListener);
                        }
                    }, false);
                } else {
                    bannersSharedPreferenceOperation();
                    persistence.clear(Constants.Keys.TOKEN_RESPONSE);
                    clearAllNotifications();
                    clearDataForLogout();
                }
                persistence.persist(Constants.Keys.IS_ACCOUNT_DELETED, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerDevice(String str, CallBackListener callBackListener) {
        this.mMainViewModel.registerDevice(str).removeObservers(this);
        this.mMainViewModel.registerDevice(str).observe(this, new CoreActivity$$ExternalSyntheticLambda6(this, str, callBackListener));
    }

    /* renamed from: lambda$registerDevice$1$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m789lambda$registerDevice$1$comjchracWiFiCoreActivity(final String str, final CallBackListener callBackListener, Resource resource) {
        TokenResponse.Body body;
        if (resource != null) {
            int i = C161711.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "registerDevice: Error");
                if (resource.response != null && ((TokenResponse) resource.response).getMeta().getCode() == 401) {
                    refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            CoreActivity.this.registerDevice(str, callBackListener);
                        }
                    }, false);
                }
            } else if (i == 2) {
                Logger.m47e(TAG, "registerDevice: Loading");
            } else if (i == 3) {
                Logger.m47e(TAG, "registerDevice: Success");
                TokenResponse tokenResponse = (TokenResponse) resource.response;
                if (tokenResponse != null && (body = tokenResponse.getBody()) != null) {
                    new Persistence().persist(Constants.Keys.TOKEN_RESPONSE, body);
                    callBackListener.onSuccess();
                }
            }
        }
    }

    public void clearAllNotifications() {
        ((NotificationManager) getSystemService("notification")).cancelAll();
    }

    public void forceLogout(final String str) {
        if (str == null) {
            showPleaseWaitDialog();
        }
        final Persistence persistence = new Persistence();
        TokenResponse.Body body = (TokenResponse.Body) persistence.obtain(Constants.Keys.TOKEN_RESPONSE, TokenResponse.Body.class);
        if (body != null && body.getResult() != null) {
            deRegisterDevice(body.getResult(), str, new CallBackListener() {
                public void onSuccess() {
                    CoreActivity.this.refreshTokenUnAuth(persistence);
                    if (str == null) {
                        CoreActivity.this.dismissPleaseWaitDialog();
                    }
                }

                public void onFailure() {
                    if (str == null) {
                        CoreActivity.this.dismissPleaseWaitDialog();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshTokenUnAuth(Persistence<TokenResponse.Body> persistence) {
        bannersSharedPreferenceOperation();
        persistence.clear(Constants.Keys.TOKEN_RESPONSE);
        clearAllNotifications();
        clearDataForLogout();
    }

    private void clearDataForLogout() {
        CountryCodeUIConfiguration.CURRENT.setCountryCodeStr("+81");
        CountryCodeUIConfiguration.CURRENT = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getApplicationContext(), CountryUtils.getByNameCodeFromCustomCountries(getApplicationContext(), (List<Country>) null, "in"));
        TokenUtil.getInstance().clear();
        UserInfo.resetCurrentUserInfo(this);
        Weather.getCurrent().clear();
        getSessionManagerInstance().setDemoMode(false);
        if (Constants.IS_DEMO_MODE) {
            Constants.IS_DEMO_MODE = false;
        }
        Constants._INVITE_ = false;
        Constants.INVITE_CODE = null;
        Constants.privacyPolicyShown = true;
        FamilyGroupList.clearCurrentFamily();
        moveToLoginFragment();
    }

    private void bannersSharedPreferenceOperation() {
        LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(this).f424id);
        if (currentUserConfig != null) {
            currentUserConfig.setIsLoggedIn(false, currentUserConfig);
            int size = getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().size();
            int dayDifference = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getDayDifference(Long.valueOf(currentUserConfig.getSessionTimeInMilSec()));
            if (size == 0) {
                currentUserConfig.setRacRemoved(false, currentUserConfig);
            } else if (HomePageFragment.membersCount == 0 && currentUserConfig.getLoginCount() <= 3 && dayDifference <= 7) {
                currentUserConfig.setRacRemoved(false, currentUserConfig);
            }
            if (HomePageFragment.membersCount == 0) {
                currentUserConfig.setMemberRemoved(false, currentUserConfig);
            } else if (size == 0 && currentUserConfig.getLoginCount() <= 3 && dayDifference <= 7) {
                currentUserConfig.setMemberRemoved(false, currentUserConfig);
            }
        }
    }

    public void refreshToken(CallBackListener callBackListener, boolean z) {
        this.mMainViewModel.refreshToken().removeObservers(this);
        this.mMainViewModel.refreshToken().observe(this, new CoreActivity$$ExternalSyntheticLambda8(this, z, callBackListener));
    }

    /* renamed from: lambda$refreshToken$2$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m788lambda$refreshToken$2$comjchracWiFiCoreActivity(boolean z, CallBackListener callBackListener, Resource resource) {
        Meta meta;
        RefreshTokenResponse.Body body;
        if (resource != null) {
            int i = C161711.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "refreshToken: Error");
                if (resource.response != null && (meta = ((RefreshTokenResponse) resource.response).getMeta()) != null) {
                    if (meta.getCode() == 400) {
                        if (meta.getMessage() != null && meta.getMessage().equals("EXPIRED_TOKEN")) {
                            if (z) {
                                callBackListener.onFailure();
                            } else {
                                forceLogout(meta.getToken());
                            }
                        }
                    } else if (z) {
                        callBackListener.onFailure();
                    } else if (!Constants.IS_DEMO_MODE) {
                        Persistence persistence = new Persistence();
                        Boolean bool = (Boolean) persistence.obtain(Constants.Keys.IS_ACCOUNT_DELETED, Boolean.class);
                        if (bool == null || bool.booleanValue()) {
                            persistence.persist(Constants.Keys.IS_ACCOUNT_DELETED, false);
                        } else {
                            Toaster.makeToast(getApplicationContext(), getString(R.string.notification_lbl_accountWasDeletedOnOtherDevice), 1);
                        }
                        refreshTokenUnAuth(new Persistence());
                    }
                }
            } else if (i == 2) {
                Logger.m47e(TAG, "refreshToken: Loading");
            } else if (i == 3) {
                Logger.m47e(TAG, "refreshToken: Success");
                RefreshTokenResponse refreshTokenResponse = (RefreshTokenResponse) resource.response;
                if (refreshTokenResponse != null && (body = refreshTokenResponse.getBody()) != null) {
                    String refreshToken = body.getRefreshToken();
                    TokenUtil.getInstance().copy(getTokenInfo(body.getToken(), refreshToken, TokenUtil.getInstance().obtain()));
                    callBackListener.onSuccess();
                }
            }
        }
    }

    private TokenInfo getTokenInfo(String str, String str2, TokenInfo tokenInfo) {
        TokenInfo tokenInfo2 = new TokenInfo();
        tokenInfo2.setNew(tokenInfo.isNew());
        tokenInfo2.setToken(str);
        tokenInfo2.setRefreshToken(str2);
        tokenInfo2.setType(tokenInfo.getType());
        tokenInfo2.setId(tokenInfo.getId());
        tokenInfo.clear();
        return tokenInfo2;
    }

    private String getIsoCodeFromCountryCode(String str) {
        return getApplicationContext().getString(CountryUtils.getByCode(getApplicationContext(), CountryUtils.getAllCountries(getApplicationContext()), str).getIso()).toUpperCase();
    }

    public void isLocationServicesEnabled(OnSuccessListener<LocationSettingsResponse> onSuccessListener, OnFailureListener onFailureListener) throws ExecutionException, InterruptedException {
        this.fusedLocationProviderAPIExtension.checkLocationServiceEnabled(onSuccessListener, onFailureListener);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Logger.m49i(TAG, "onStart");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Logger.m49i(TAG, "onResume");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Logger.m49i(TAG, "onStop");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.dismissHandler.removeCallbacksAndMessages((Object) null);
        Logger.m49i(TAG, "onDestroy");
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.INSTANCE.onAttach(context));
        Logger.m47e("Lang::Check::attachBaseContext", "called");
    }

    public boolean isPermissionGranted(String str) {
        return ContextCompat.checkSelfPermission(this, str) == 0;
    }

    public void askForPermission(String str) {
        askForPermission(str, 111);
    }

    public void changeStatusBarColor(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(8192);
            window.setStatusBarColor(getResources().getColor(i));
        }
    }

    public void moveToLoginFragment() {
        Intent intent = new Intent(getApplicationContext(), UserManagementActivity.class);
        intent.putExtra(Constants.SWITCH_TO_LOGIN_SCREEN, true);
        startActivity(intent);
        finish();
    }

    public void askForPermission(String str, int i) {
        if (!isPermissionGranted(str)) {
            ActivityCompat.requestPermissions(this, new String[]{str}, i);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void startBroadcastGPSLocation(int i) {
        if (isPermissionGranted(Constants.GrantedPermissions.ACCESS_FINE_LOCATION) && !this.locationRequested) {
            this.mLocationManager.requestLocationUpdates("gps", (long) i, 0.0f, this);
            this.locationRequested = true;
        }
    }

    public void startBroadcastNetworkLocation(int i) {
        if (isPermissionGranted(Constants.GrantedPermissions.ACCESS_FINE_LOCATION) && !this.locationRequested) {
            this.mLocationManager.requestLocationUpdates("network", (long) i, 0.0f, this);
            this.locationRequested = true;
        }
    }

    public void stopLocationBroadcast() {
        LocationManager locationManager = this.mLocationManager;
        if (locationManager != null) {
            locationManager.removeUpdates(this);
            this.locationRequested = false;
        }
    }

    public void onLocationChanged(Location location) {
        String str = TAG;
        Logger.m45d(str, "onLocationChanged " + location.getProvider());
        Intent intent = new Intent(LocationUpdatesBroadcast.ACTION_ON_LOCATION_CHANGED);
        intent.putExtra(LocationUpdatesBroadcast.LOCATION_PARCEL, location);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        String str2 = TAG;
        Logger.m45d(str2, "onStatusChanged " + str);
    }

    public void onProviderEnabled(String str) {
        String str2 = TAG;
        Logger.m45d(str2, "onProviderEnabled " + str);
    }

    public void onProviderDisabled(String str) {
        String str2 = TAG;
        Logger.m45d(str2, "onProviderDisabled " + str);
    }

    public void showPleaseWaitDialog() {
        CustomProgressDialog customProgressDialog = this.mPleaseWaitDialog;
        if (customProgressDialog != null && !customProgressDialog.isShowing()) {
            this.mPleaseWaitDialog.show();
            this.dismissHandler.postDelayed(new Runnable() {
                public void run() {
                    if (CoreActivity.this.mPleaseWaitDialog != null && CoreActivity.this.mPleaseWaitDialog.isShowing()) {
                        CoreActivity.this.mPleaseWaitDialog.dismiss();
                        Toaster.makeToast(CoreActivity.this.getActivity(), CoreActivity.this.getString(R.string.common_alert_somethingWentWrong), 0);
                    }
                }
            }, 15000);
        }
    }

    public void showPleaseWaitDialog(long j) {
        CustomProgressDialog customProgressDialog = this.mPleaseWaitDialog;
        if (customProgressDialog != null && !customProgressDialog.isShowing()) {
            this.mPleaseWaitDialog.show();
            this.dismissHandler.postDelayed(new Runnable() {
                public void run() {
                    if (CoreActivity.this.mPleaseWaitDialog != null && CoreActivity.this.mPleaseWaitDialog.isShowing()) {
                        CoreActivity.this.mPleaseWaitDialog.dismiss();
                        Toaster.makeToast(CoreActivity.this.getActivity(), CoreActivity.this.getString(R.string.common_alert_somethingWentWrong), 0);
                    }
                }
            }, j);
        }
    }

    public void dismissPleaseWaitDialog() {
        CustomProgressDialog customProgressDialog = this.mPleaseWaitDialog;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.mPleaseWaitDialog.dismiss();
            this.dismissHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    public void scanWifi(WifiScanCallBack wifiScanCallBack2) {
        this.wifiScanCallBack = wifiScanCallBack2;
        registerReceiver(this.wifiReceiver, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        this.mWifiManager.startScan();
        Toast.makeText(this, "Scanning WiFi ...", 0).show();
    }

    public boolean isLocationEnabled() {
        return getLocationManager().isProviderEnabled("network") || getLocationManager().isProviderEnabled("gps");
    }

    public SessionManager getSessionManagerInstance() {
        if (this.mSessionManager == null) {
            new SessionManager(getApplicationContext());
        }
        return this.mSessionManager;
    }

    public void launchScreen(DeepLink deepLink, Error error) {
        Bundle bundle = new Bundle();
        bundle.putString("vendorThingId", error.getVendorThingId());
        bundle.putString(Constants.FCM.SUB_CATEGORY, error.getSubCategory1());
        bundle.putString(Constants.FCM.ERROR_CODE, error.getErrorCode());
        bundle.putString(Constants.FCM.RAC_NAME, error.getName());
        bundle.putLong("racId", error.getRacId().longValue());
        bundle.putInt(Constants.FCM.FAMILY_ID, Integer.parseInt(error.getFamilyId() != null ? error.getFamilyId() : StatusCode.BUILTIN_WIRELESS));
        List<Integer> destinationList = deepLink.getDestinationList();
        if (!destinationList.isEmpty() && destinationList != null) {
            getNavController().navigate(destinationList.get(1).intValue(), bundle);
        }
    }

    public void launchScreen(DeepLink deepLink) {
        IduList iduList = getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        if (deepLink.getRacId() != null) {
            DetailedIduModel detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId((int) deepLink.getRacId().longValue());
            if (detailedIduModelFromRacId != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.Keys.DETAILED_IDU_MODEL, detailedIduModelFromRacId);
                List<Integer> destinationList = deepLink.getDestinationList();
                if (!destinationList.isEmpty() && destinationList != null) {
                    getNavController().navigate(destinationList.get(0).intValue(), bundle);
                }
            }
        } else if (deepLink.getType() == Type.SMART_FENCE) {
            List<Integer> destinationList2 = deepLink.getDestinationList();
            if (!destinationList2.isEmpty()) {
                getNavController().navigate(destinationList2.get(0).intValue());
            }
        }
    }

    public void logEvent(String str, int i) {
        try {
            this.mAmplitudeUtil.logEvent(str, i, this.mClientFactory);
        } catch (AmplitudeException e) {
            e.printStackTrace();
        }
    }

    public void logEvents(String str, long j) {
        try {
            this.mAmplitudeUtil.logEvents(str, j, this.mClientFactory);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isFirstHit() {
        String str = (String) new Persistence().obtain(Constants.Keys.CURRENT_DATE, String.class);
        if (str == null) {
            return true;
        }
        return Constants.CC.isDateChanged(str, Constants.CC.getCurrentDate(Constants.DateFormat.FORMAT_1), Constants.DateFormat.FORMAT_1);
    }

    public void getAmplitudeStatus() {
        MainViewModel mainViewModel;
        if (isFirstHit() && !Constants.IS_DEMO_MODE && (mainViewModel = getMainViewModel()) != null) {
            RequestBody requestBody = new RequestBody();
            requestBody.setApiLevel(String.valueOf(Build.VERSION.SDK_INT));
            requestBody.setDeviceVersion(Build.VERSION.RELEASE);
            requestBody.setAppPlatform(Constants.FirelogAnalytics.SDK_PLATFORM_ANDROID);
            requestBody.setAppVersion(Constants.CC.getVersion("3.0.9"));
            requestBody.setCountryCode(Resources.getSystem().getConfiguration().locale.getCountry());
            requestBody.setCountryName(Resources.getSystem().getConfiguration().locale.getDisplayCountry());
            LiveData<Resource<ResponseBody>> amplitudeStatus = mainViewModel.getAmplitudeStatus(requestBody);
            if (amplitudeStatus != null) {
                amplitudeStatus.removeObservers(this);
                amplitudeStatus.observe(this, CoreActivity$$ExternalSyntheticLambda9.INSTANCE);
            }
        }
    }

    /* renamed from: com.jch.racWiFi.CoreActivity$11 */
    static /* synthetic */ class C161711 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$amplitude$util$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
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
                int[] r2 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r3 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.jch.racWiFi.amplitude.util.Mode[] r2 = com.jch.racWiFi.amplitude.util.Mode.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$jch$racWiFi$amplitude$util$Mode = r2
                com.jch.racWiFi.amplitude.util.Mode r3 = com.jch.racWiFi.amplitude.util.Mode.FOREGROUND_LOCATION     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$jch$racWiFi$amplitude$util$Mode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.jch.racWiFi.amplitude.util.Mode r2 = com.jch.racWiFi.amplitude.util.Mode.BACKGROUND_LOCATION     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.CoreActivity.C161711.<clinit>():void");
        }
    }

    static /* synthetic */ void lambda$getAmplitudeStatus$3(Resource resource) {
        ResponseBody responseBody;
        ResponseBody.Body body;
        if (resource != null && C161711.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()] == 3 && (responseBody = (ResponseBody) resource.response) != null && (body = responseBody.getBody()) != null) {
            new Persistence().persist(Constants.Keys.IS_AMPLITUDE_ENABLED, body.isEnabled());
            new Persistence().persist(Constants.Keys.CURRENT_DATE, Constants.CC.getCurrentDate(Constants.DateFormat.FORMAT_1));
        }
    }

    private boolean isAndroid10OrHigher() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public void verifyLocationService(CoreActivity coreActivity, LocationCallback locationCallback, LocationServiceListener locationServiceListener) {
        this.mLocationServiceListener = locationServiceListener;
        Task<LocationSettingsResponse> locationSettings = getLocationSettings();
        locationSettings.addOnSuccessListener(new CoreActivity$$ExternalSyntheticLambda11(this, coreActivity, locationCallback));
        if (coreActivity != null) {
            locationSettings.addOnFailureListener(new CoreActivity$$ExternalSyntheticLambda10(this, coreActivity, locationServiceListener));
        }
    }

    /* renamed from: lambda$verifyLocationService$4$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m790lambda$verifyLocationService$4$comjchracWiFiCoreActivity(CoreActivity coreActivity, LocationCallback locationCallback, LocationSettingsResponse locationSettingsResponse) {
        onLocationTurnOn(coreActivity, locationCallback);
    }

    /* renamed from: lambda$verifyLocationService$5$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m791lambda$verifyLocationService$5$comjchracWiFiCoreActivity(CoreActivity coreActivity, final LocationServiceListener locationServiceListener, Exception exc) {
        JCIAlertDialog jCIAlertDialog;
        JCIAlertDialog jCIAlertDialog2 = this.mJciAlertDialog;
        if ((jCIAlertDialog2 == null || !jCIAlertDialog2.isShowing()) && (jCIAlertDialog = this.mJciAlertDialog) != null) {
            jCIAlertDialog.showDialog(coreActivity, (String) null, getString(R.string.common_alert_enableLocationService), getString(R.string.common_btn_yes), getString(R.string.common_btn_no), new AlertListener() {
                public void onPositive() {
                    CoreActivity.this.mActivityResultLocationService.launch(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }

                public void onNegative() {
                    locationServiceListener.onNegative();
                }
            });
        }
    }

    private Task<LocationSettingsResponse> getLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(getLocationRequest());
        return this.mSettingsClient.checkLocationSettings(builder.build());
    }

    private void onLocationTurnOn(CoreActivity coreActivity, LocationCallback locationCallback) {
        if (coreActivity != null && ContextCompat.checkSelfPermission(coreActivity, this.mPermissions[0]) == 0 && ContextCompat.checkSelfPermission(coreActivity, this.mPermissions[1]) == 0) {
            requestLocationUpdates(coreActivity, locationCallback);
        }
    }

    private void requestLocationUpdates(CoreActivity coreActivity, LocationCallback locationCallback) {
        if (coreActivity != null && ContextCompat.checkSelfPermission(coreActivity, this.mPermissions[0]) == 0 && ContextCompat.checkSelfPermission(coreActivity, this.mPermissions[1]) == 0) {
            this.mFusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), locationCallback, Looper.myLooper());
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest create = LocationRequest.create();
        create.setInterval(1000);
        create.setFastestInterval(1000);
        create.setSmallestDisplacement(1.0f);
        create.setPriority(102);
        return create;
    }

    public void showPermissionDeniedDialog(CoreActivity coreActivity, View view, AlertListener alertListener) {
        if (coreActivity != null) {
            ConfirmationDialog confirmationDialog = new ConfirmationDialog(coreActivity);
            confirmationDialog.setTitleString(getString(R.string.android_permission_alert_permissionDenied));
            confirmationDialog.setMessageString(getString(R.string.android_permission_alert_permissionDeniedAlertDesc, new Object[]{getString(R.string.android_permission_alert_location)}));
            confirmationDialog.setCancelable(false);
            confirmationDialog.setPositiveButton(getString(R.string.android_permission_btn_allow), new CoreActivity$$ExternalSyntheticLambda1(alertListener));
            confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), CoreActivity$$ExternalSyntheticLambda2.INSTANCE);
            confirmationDialog.setParentView(view);
            confirmationDialog.show();
        }
    }

    public void showNotRationale(final CoreActivity coreActivity, final ShowNotRationaleListener showNotRationaleListener) {
        JCIAlertDialog jCIAlertDialog;
        this.mShowNotRationaleListener = showNotRationaleListener;
        String string = getString(R.string.android_permission_alert_deniedMsg, new Object[]{getString(R.string.android_permission_alert_location)});
        if (coreActivity != null) {
            JCIAlertDialog jCIAlertDialog2 = this.mJciAlertDialog;
            if ((jCIAlertDialog2 == null || !jCIAlertDialog2.isShowing()) && (jCIAlertDialog = this.mJciAlertDialog) != null) {
                jCIAlertDialog.showDialog(getActivity(), (String) null, string, getString(R.string.android_permission_btn_goToSetting), getString(R.string.common_btn_cancel), new AlertListener() {
                    public void onPositive() {
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", coreActivity.getPackageName(), (String) null));
                        CoreActivity.this.mActivityResultNotRationale.launch(intent);
                    }

                    public void onNegative() {
                        showNotRationaleListener.onNegative();
                    }
                });
            }
        }
    }

    public void removeLocationUpdates(LocationCallback locationCallback) {
        this.mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    public boolean isForeGroundLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION) == 0 && ContextCompat.checkSelfPermission(this, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0;
    }

    public boolean isBackGroundLocationPermissionGranted() {
        if ((Build.VERSION.SDK_INT >= 29 ? ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_BACKGROUND_LOCATION") : 0) == 0) {
            return true;
        }
        return false;
    }

    public void checkLocationPermissions(String str) {
        this.mCanonicalName = str;
        this.isPermissionWithoutLaunch = true;
        if (isForeGroundLocationPermissionGranted()) {
            onForeGroundLocationPermissionGranted();
        } else if (shouldShowRequestPermissionRationale(Constants.GrantedPermissions.ACCESS_FINE_LOCATION) || shouldShowRequestPermissionRationale(Constants.GrantedPermissions.ACCESS_COARSE_LOCATION)) {
            showRationale(Mode.FOREGROUND_LOCATION);
        } else {
            requestForeGroundPermission();
        }
    }

    private void showRationale(Mode mode) {
        Scenario scenario = new Scenario(mode, this.isPermissionWithoutLaunch, true, -1);
        HashMap hashMap = new HashMap();
        hashMap.put(this.mCanonicalName, scenario);
        this.mLocationRationaleMutableLiveData.postValue(hashMap);
        this.isPermissionWithoutLaunch = true;
    }

    public void handleRationale(Mode mode) {
        int i = C161711.$SwitchMap$com$jch$racWiFi$amplitude$util$Mode[mode.ordinal()];
        if (i == 1) {
            requestForeGroundPermission();
        } else if (i == 2) {
            requestBackGroundPermission();
        }
    }

    private void requestBackGroundPermission() {
        this.isPermissionWithoutLaunch = false;
        this.mAccessBackgroundLocationPermissionResult.launch("android.permission.ACCESS_BACKGROUND_LOCATION");
    }

    private void requestForeGroundPermission() {
        this.isPermissionWithoutLaunch = false;
        this.mMultiplePermissionResult.launch(new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION});
    }

    /* renamed from: lambda$new$8$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m786lambda$new$8$comjchracWiFiCoreActivity(Map map) {
        if (isForeGroundLocationPermissionGranted()) {
            onForeGroundLocationPermissionGranted();
        } else if (this.isPermissionWithoutLaunch) {
            requestForeGroundPermission();
        } else {
            showNotRationale(Mode.FOREGROUND_LOCATION, 0);
        }
    }

    /* renamed from: lambda$new$9$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m787lambda$new$9$comjchracWiFiCoreActivity(Boolean bool) {
        if (bool.booleanValue()) {
            onAllLocationPermissionGranted();
        } else if (this.isPermissionWithoutLaunch) {
            requestForeGroundPermission();
        } else {
            showNotRationale(Mode.BACKGROUND_LOCATION, 0);
        }
    }

    public void openSettings(Mode mode) {
        if (mode != null) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), (String) null));
            int i = C161711.$SwitchMap$com$jch$racWiFi$amplitude$util$Mode[mode.ordinal()];
            if (i == 1) {
                this.mActivityResultMultiplePermissionsShowNotRationale.launch(intent);
            } else if (i == 2) {
                this.mActivityResultBackgroundLocationShowNotRationale.launch(intent);
            }
        }
    }

    public void showNotRationale(Mode mode, int i) {
        Scenario scenario = new Scenario(mode, this.isPermissionWithoutLaunch, false, i);
        HashMap hashMap = new HashMap();
        hashMap.put(this.mCanonicalName, scenario);
        this.mLocationRationaleMutableLiveData.postValue(hashMap);
        this.isPermissionWithoutLaunch = true;
    }

    /* renamed from: lambda$new$10$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m784lambda$new$10$comjchracWiFiCoreActivity(ActivityResult activityResult) {
        if (isForeGroundLocationPermissionGranted()) {
            onForeGroundLocationPermissionGranted();
            return;
        }
        String str = this.mCanonicalName;
        if (str == null || (!str.equals(MyAccountAddressFragment.class.getCanonicalName()) && !this.mCanonicalName.equals(CreateAccountStep4Fragment.class.getCanonicalName()))) {
            this.mLocationPermissionMutableLiveData.postValue(null);
        } else {
            showNotRationale(Mode.FOREGROUND_LOCATION, activityResult.getResultCode());
        }
    }

    /* renamed from: lambda$new$11$com-jch-racWiFi-CoreActivity  reason: not valid java name */
    public /* synthetic */ void m785lambda$new$11$comjchracWiFiCoreActivity(ActivityResult activityResult) {
        if (isBackGroundLocationPermissionGranted()) {
            onAllLocationPermissionGranted();
        } else {
            this.mLocationPermissionMutableLiveData.postValue(null);
        }
    }

    private void onAllLocationPermissionGranted() {
        this.mLocationPermissionMutableLiveData.postValue(this.mCanonicalName);
    }

    private void onForeGroundLocationPermissionGranted() {
        String str = this.mCanonicalName;
        if (str != null && (str.equals(MyAccountAddressFragment.class.getCanonicalName()) || this.mCanonicalName.equals(CreateAccountStep4Fragment.class.getCanonicalName()))) {
            onAllLocationPermissionGranted();
        } else if (Build.VERSION.SDK_INT < 29) {
            onAllLocationPermissionGranted();
        } else if (isBackGroundLocationPermissionGranted()) {
            onAllLocationPermissionGranted();
        } else if (shouldShowRequestPermissionRationale("android.permission.ACCESS_BACKGROUND_LOCATION")) {
            showRationale(Mode.BACKGROUND_LOCATION);
        } else {
            requestBackGroundPermission();
        }
    }
}
