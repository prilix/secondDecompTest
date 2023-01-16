package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavArgument;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.accord.common.utils.Logger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.IduAccess.presenter.IduAccessPresenter;
import com.jch.racWiFi.IduAccess.presenter.presenterImpl.IduAccessPresenterImpl;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.LocationUpdatesBroadcast;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.LifeCycleUtils;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.EnableDisableOnChangeListener;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.BannerPlannedMaintenanceBinding;
import com.jch.racWiFi.databinding.BannerRacOfflineBinding;
import com.jch.racWiFi.databinding.HomePageUpdatedVdBinding;
import com.jch.racWiFi.databinding.LayoutCleaningAndTrialBinding;
import com.jch.racWiFi.databinding.RecyclerViewItemsFamilyHomeBinding;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.fcm.util.DeepLink;
import com.jch.racWiFi.fcm.util.MaintenanceSubCategory;
import com.jch.racWiFi.fcm.util.ReminderSubCategory;
import com.jch.racWiFi.fcm.view_model.FcmViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.iduManagement.presenter.HomePagePresenter;
import com.jch.racWiFi.iduManagement.presenter.presenterImpl.HomePagePresenterImpl;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.view.DetailedIduAdapter;
import com.jch.racWiFi.iduManagement.view.IHomePageView;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.userManagement.countryCodeManager.Country;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.liveData.InviteeLiveDataHolder;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;
import com.jch.racWiFi.userManagement.model.FamilyMembersList;
import com.jch.racWiFi.userManagement.model.FamilyMembersMap;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.model.InviteeList;
import com.jch.racWiFi.userManagement.model.InviteeModel;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.RemoveFromFamilyNotificationModel;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.network.ApiCaller.PermissionApiImpl;
import com.jch.racWiFi.userManagement.presenter.AllInvitationPresenter;
import com.jch.racWiFi.userManagement.presenter.GetFamilyGroupPresenter;
import com.jch.racWiFi.userManagement.presenter.InviteVerificationPresenter;
import com.jch.racWiFi.userManagement.presenter.ManageUserFragmentPresenter;
import com.jch.racWiFi.userManagement.presenter.UserOnBoardingPresenter;
import com.jch.racWiFi.userManagement.view.IDevicePermissionView;
import com.jch.racWiFi.weather.JSONWeatherParser;
import com.jch.racWiFi.weather.WeatherHttpClient;
import com.jch.racWiFi.weather.WeatherIconsFactory;
import com.jch.racWiFi.weather.model.Weather;
import com.jch.racWiFi.weather.model.WeatherDataModel;
import com.jch.racWiFi.weather.model.WeatherDataPresenter;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import dagger.android.support.AndroidSupportInjection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;
import retrofit2.Response;

public class HomePageFragment extends GenericFragment implements IHomePageView, UserOnBoardingPresenter.IUserOnBoardingPresenter, InviteVerificationPresenter.InviteUserPresenterInterface, LocationUpdatesBroadcast.ILocationChangeUpdates, IDevicePermissionView, GetFamilyGroupPresenter.IGetFamilyGroupPresenterInterface, ManageUserFragmentPresenter.IManageUserFragmentPresenter, WeatherDataPresenter.IWeatherDataPresenter, AllInvitationPresenter.IAllInvitationPresenter {
    public static int membersCount;
    public String IS_ADD_DEVICE_BUTTON_HIDDEN_PREFERENCE = "";
    public String IS_ADD_MEMBER_BUTTON_HIDDEN_PREFERENCE = "";
    Handler autoDismissHandler = new Handler();
    private BannerRacOfflineBinding bannerRacOfflineBinding;
    private View customView;
    UsersRecyclerViewAdapter familyMembersRecyclerViewAdapter;
    private final Observer<WebSocketNotification<RemoveFromFamilyNotificationModel>> familyObserver = new HomePageFragment$$ExternalSyntheticLambda5(this);
    private final Observer<Boolean> forceRefreshObserver = new Observer<Boolean>() {
        public void onChanged(Boolean bool) {
            if (HomePageFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                HomePageFragment.this.dismissPleaseWaitDialogHome();
                if (HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().isEmpty()) {
                    HomePageFragment.this.mBinding.textViewNoDevicesFound.setVisibility(0);
                } else {
                    HomePageFragment.this.mBinding.textViewNoDevicesFound.setVisibility(4);
                }
                HomePageFragment.this.stopRefreshButtonRotation();
                IduList iduList = HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
                HomePageFragment.this.onModelTypeListAvailable(IduList.getListOfCloudIds(iduList));
                HomePageFragment.this.onIduListsFetched(iduList);
            }
        }
    };
    private IduAccessPresenter iduAccessPresenter;
    private boolean isClickedOnAddMembersButton;
    private boolean isVisible = false;
    private JSONWeatherTask jsonWeatherTask;
    private AllInvitationPresenter mAllInvitationPresenter;
    @Inject
    Binder mBinder;
    /* access modifiers changed from: private */
    public HomePageUpdatedVdBinding mBinding;
    /* access modifiers changed from: private */
    public ConfirmationDialog mConfirmationDialog;
    /* access modifiers changed from: private */
    public DetailedIduAdapter mDetailedIduAdapter;
    private List<DetailedIduModel> mDetailedIduModels;
    private final Handler mEnableAdapterListHandler = new Handler();
    /* access modifiers changed from: private */
    public GetFamilyGroupPresenter mGetFamilyGroupPresenter;
    /* access modifiers changed from: private */
    public HomePagePresenter mHomePagePresenter;
    private final Observer<IduList> mIduListUpdateObserver = new HomePageFragment$$ExternalSyntheticLambda4(this);
    private final Observer<InviteeModel> mInvitationAcceptedObserver = new HomePageFragment$$ExternalSyntheticLambda6(this);
    private InviteVerificationPresenter mInviteVerificationPresenter;
    private final Handler mJpRegulationHandler = new Handler();
    private LocationUpdatesBroadcast mLocationUpdatesBroadcast;
    private ManageUserFragmentPresenter mManageUserFragmentPresenter;
    private SingleChoiceDialog mNetworkAlertSingleChoiceDialog;
    private PopupWindow mPopupWindow;
    private CustomProgressDialog mProgressDialogNetworkCall;
    private UserOnBoardingPresenter mUserOnBoardingPresenter;
    private final UserPermissions mUserPermissions = UserPermissions.getInstance();
    @Inject
    ViewModelProviderFactory providerFactory;
    /* access modifiers changed from: private */
    public boolean refreshing;
    private final RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 600.0f, 1, 0.5f, 1, 0.5f);
    private SingleChoiceDialog showAlertDialog;
    private final EnableDisableOnChangeListener stopAllCheckListener = new EnableDisableOnChangeListener() {
        private boolean suspend = false;

        public void suspendListener() {
            this.suspend = true;
        }

        public void onCheckedChanged(SwitchButton switchButton, boolean z) {
            if (NetworkConnectivity.isNetworkAvailable(HomePageFragment.this.getActivity().getApplicationContext())) {
                if (!this.suspend) {
                    HomePageFragment.this.mBinding.sbStopAll.setEnabled(false);
                    HomePageFragment.this.mConfirmationDialog.setTitleString(HomePageFragment.this.getString(z ? R.string.home_alert_turnOnAllAcs : R.string.home_alert_turnOffAllAcs));
                    HomePageFragment.this.mConfirmationDialog.setMessageString(HomePageFragment.this.getString(z ? R.string.home_alert_turnOnAllAcsDesc : R.string.home_alert_turnOffAllAcsDesc));
                    HomePageFragment.this.mConfirmationDialog.setCancelable(false);
                    HomePageFragment.this.mConfirmationDialog.setPositiveButton(HomePageFragment.this.getString(R.string.common_btn_yes), new HomePageFragment$5$$ExternalSyntheticLambda1(this, z));
                    HomePageFragment.this.mConfirmationDialog.setNegativeButton(HomePageFragment.this.getString(R.string.common_btn_no), new HomePageFragment$5$$ExternalSyntheticLambda0(this));
                    HomePageFragment.this.mConfirmationDialog.setParentView(HomePageFragment.this.mBinding.parent);
                    if (!HomePageFragment.this.mConfirmationDialog.isShowing()) {
                        HomePageFragment.this.mConfirmationDialog.show();
                    }
                }
                this.suspend = false;
                return;
            }
            HomePageFragment.this.mHomePagePresenter.checkAndUpdateStopAllSwitch(HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList());
            HomePageFragment.this.mConfirmationDialog.dismiss();
            HomePageFragment.this.mBinding.sbStopAll.setEnabled(true);
            HomePageFragment.this.showInternetAlert();
        }

        /* renamed from: lambda$onCheckedChanged$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment$5 */
        public /* synthetic */ boolean mo30884x8ee12e3c(boolean z, Dialog dialog, View view) {
            HomePageFragment.this.mHomePagePresenter.requestAllOnOff(Boolean.valueOf(z), IduList.createStartAllStopAllUnitBody(z, HomePageFragment.this.mDetailedIduAdapter.getDetailedIduModelList(), HomePageFragment.this.mFragmentToActivityCallback.getRacModelWiseConfigurationList()), HomePageFragment.this);
            HomePageFragment.this.mConfirmationDialog.dismiss();
            HomePageFragment.this.showPleaseWaitDialog(15000);
            HomePageFragment.this.mBinding.sbStopAll.setEnabled(true);
            return false;
        }

        /* renamed from: lambda$onCheckedChanged$1$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment$5 */
        public /* synthetic */ boolean mo30885xd42321b(Dialog dialog, View view) {
            HomePageFragment.this.mHomePagePresenter.checkAndUpdateStopAllSwitch(HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList());
            HomePageFragment.this.mConfirmationDialog.dismiss();
            HomePageFragment.this.mBinding.sbStopAll.setEnabled(true);
            return false;
        }
    };
    private final Observer<UserInfo> userInfoObserver = new Observer<UserInfo>() {
        public void onChanged(UserInfo userInfo) {
            if (HomePageFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && userInfo.f424id != -1) {
                String str = userInfo.settingsData.temperatureUnit;
                if (str != null) {
                    TemperatureUnit.CURRENT = TemperatureUnit.getEnumFromServerConstant(str);
                }
                UserInfo.getCurrentUserInfo(HomePageFragment.this.getCoreActivity()).copy(userInfo);
                HomePageFragment.this.mGetFamilyGroupPresenter.getFamilyGroup();
                HomePageFragment.this.weatherDataPresenter.getWeatherData(LocaleConfiguration.getLanguageCodeForCurrentLocale());
                HomePageFragment homePageFragment = HomePageFragment.this;
                homePageFragment.IS_ADD_MEMBER_BUTTON_HIDDEN_PREFERENCE = UserInfo.getCurrentUserInfo(HomePageFragment.this.getCoreActivity()).email + "IS_ADD_MEMBER_BUTTON_HIDDEN_PREFERENCE";
                HomePageFragment homePageFragment2 = HomePageFragment.this;
                homePageFragment2.IS_ADD_DEVICE_BUTTON_HIDDEN_PREFERENCE = UserInfo.getCurrentUserInfo(HomePageFragment.this.getCoreActivity()).email + "IS_ADD_DEVICE_BUTTON_HIDDEN_PREFERENCE";
                HomePageFragment.this.setUserName();
                HomePageFragment homePageFragment3 = HomePageFragment.this;
                homePageFragment3.onProfilePicUpdated(UserInfo.getCurrentUserInfo(homePageFragment3.getCoreActivity()).profilePicture);
                HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(HomePageFragment.this.getViewLifecycleOwner());
            }
        }
    };
    /* access modifiers changed from: private */
    public WeatherDataPresenter weatherDataPresenter;

    static /* synthetic */ boolean lambda$scheduleJpRegulationHandler$15(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showAlert$26(Dialog dialog, View view) {
        return true;
    }

    public void fetchPermissionResponseDatas(Response<AllPermissionDataDto> response) {
    }

    public void onAllCheckedStatusEvaluated(Boolean[] boolArr) {
    }

    public void onNetworkCallSuccess() {
    }

    public void savePermissionResponseDatas(Response<ResponseBody> response) {
    }

    public void serverException() {
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30827x2f5c3df1(IduList iduList) {
        IduList iduList2 = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        onModelTypeListAvailable(IduList.getListOfCloudIds(iduList2));
        onIduListsFetched(iduList);
        if (!iduList2.isEmpty()) {
            incomingIntent(requireActivity().getIntent(), iduList);
        }
    }

    private void incomingIntent(Intent intent, IduList iduList) {
        Bundle extras;
        DeepLink deepLink;
        Alert alert;
        if (intent != null && (extras = intent.getExtras()) != null && (deepLink = (DeepLink) extras.getParcelable(DeepLink.PARCEL_KEY)) != null && deepLink.getType() != null) {
            int i = C210816.$SwitchMap$com$jch$racWiFi$fcm$util$Type[deepLink.getType().ordinal()];
            if (i == 1) {
                Error error = (Error) extras.getParcelable(Error.PARCEL_KEY);
                if (error != null) {
                    getCoreActivity().launchScreen(deepLink, error);
                    intent.removeExtra(DeepLink.PARCEL_KEY);
                    intent.removeExtra(Error.PARCEL_KEY);
                }
            } else if (i == 2) {
                SmartFence smartFence = (SmartFence) extras.getParcelable(SmartFence.PARCEL_KEY);
                if (smartFence != null && smartFence.getSubCategory() != null) {
                    this.mFragmentToActivityCallback.getNavController().navigate(deepLink.getDestinationList().get(0).intValue(), extras);
                    intent.removeExtra(DeepLink.PARCEL_KEY);
                    intent.removeExtra(SmartFence.PARCEL_KEY);
                }
            } else if (i == 3 && (alert = (Alert) extras.getParcelable(Alert.PARCEL_KEY)) != null && alert.getSubCategory() != null) {
                switch (C210816.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[alert.getSubCategory().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        this.mFragmentToActivityCallback.getNavController().navigate(deepLink.getDestinationList().get(0).intValue(), extras);
                        intent.removeExtra(DeepLink.PARCEL_KEY);
                        intent.removeExtra(Alert.PARCEL_KEY);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment$16 */
    static /* synthetic */ class C210816 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(48:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0082 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0097 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00af */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00bb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00c7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00d3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00df */
        static {
            /*
                com.jch.racWiFi.fcm.util.Type[] r0 = com.jch.racWiFi.fcm.util.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$Type = r0
                r1 = 1
                com.jch.racWiFi.fcm.util.Type r2 = com.jch.racWiFi.fcm.util.Type.ERRORS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.Type r3 = com.jch.racWiFi.fcm.util.Type.SMART_FENCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.Type r4 = com.jch.racWiFi.fcm.util.Type.ALERTS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.Type r5 = com.jch.racWiFi.fcm.util.Type.MAINTENANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.Type r6 = com.jch.racWiFi.fcm.util.Type.REMINDER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.Type r7 = com.jch.racWiFi.fcm.util.Type.REMINDERS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.jch.racWiFi.fcm.util.AlertSubCategory[] r6 = com.jch.racWiFi.fcm.util.AlertSubCategory.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory = r6
                com.jch.racWiFi.fcm.util.AlertSubCategory r7 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_ALL     // Catch:{ NoSuchFieldError -> 0x005a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r6 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_ALL     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x006e }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_MULTIPLE_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0082 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_REMOTE_ACTIVITY     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x008c }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_INTERRUPTION     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0097 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x0097 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0097 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0097 }
            L_0x0097:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00a3 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00af }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_UPDATED     // Catch:{ NoSuchFieldError -> 0x00af }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00af }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00af }
            L_0x00af:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00bb }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.ENERGY_CONSUMPTION_BUDGET     // Catch:{ NoSuchFieldError -> 0x00bb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bb }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00bb }
            L_0x00bb:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00c7 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE     // Catch:{ NoSuchFieldError -> 0x00c7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c7 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c7 }
            L_0x00c7:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00d3 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE_20_HOURS     // Catch:{ NoSuchFieldError -> 0x00d3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d3 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d3 }
            L_0x00d3:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00df }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.ROLE_UPDATED     // Catch:{ NoSuchFieldError -> 0x00df }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00df }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00df }
            L_0x00df:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00eb }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.REMOVED_FROM_HOME     // Catch:{ NoSuchFieldError -> 0x00eb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00eb }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00eb }
            L_0x00eb:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment.C210816.<clinit>():void");
        }
    }

    private void showRacOfflineBanner(Alert alert) {
        if (this.bannerRacOfflineBinding == null) {
            BannerRacOfflineBinding bannerRacOfflineBinding2 = (BannerRacOfflineBinding) DataBindingUtil.inflate(LayoutInflater.from(requireActivity()), R.layout.banner_rac_offline, this.mBinding.layoutDialogs, true);
            this.bannerRacOfflineBinding = bannerRacOfflineBinding2;
            bannerRacOfflineBinding2.textViewGenericNotificationTitle.setText(getString(R.string.notification_lbl_racOfflineTitle));
            this.bannerRacOfflineBinding.textViewGenericNotificationDesc.setText(Html.fromHtml(getString(R.string.notification_lbl_racOfflineTryAgain, alert.getHomeName(), alert.getRacName())));
            this.bannerRacOfflineBinding.textViewRacOfflineDescTwo.setText(getString(R.string.notification_lbl_encourageUserToTurnOnLocationservices));
            this.bannerRacOfflineBinding.imageButtonClear.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda38(this));
            this.bannerRacOfflineBinding.textViewViewHideDetails.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda39(this));
            this.mBinding.parent.setBackground(ContextCompat.getDrawable(this.bannerRacOfflineBinding.getRoot().getContext(), R.drawable.splash_white_blur));
        }
    }

    /* renamed from: lambda$showRacOfflineBanner$1$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30852xcdf2357e(View view) {
        this.mBinding.layoutDialogs.removeAllViews();
        this.bannerRacOfflineBinding.textViewViewHideDetails.performClick();
        this.mBinding.parent.setBackground(ContextCompat.getDrawable(this.bannerRacOfflineBinding.getRoot().getContext(), R.drawable.transparent));
        this.bannerRacOfflineBinding.unbind();
        this.bannerRacOfflineBinding = null;
    }

    /* renamed from: lambda$showRacOfflineBanner$2$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30853x6230a51d(View view) {
        if (!this.isVisible) {
            this.isVisible = true;
            this.bannerRacOfflineBinding.layoutDesc.setVisibility(0);
            this.bannerRacOfflineBinding.textViewViewHideDetails.setText(getResources().getString(R.string.notification_btn_hideDetail));
            return;
        }
        this.bannerRacOfflineBinding.layoutDesc.setVisibility(8);
        this.bannerRacOfflineBinding.textViewViewHideDetails.setText(getResources().getString(R.string.notification_btn_viewDetail));
        this.isVisible = false;
    }

    /* renamed from: lambda$new$3$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30829xec178cce(WebSocketNotification webSocketNotification) {
        showPleaseWaitDialog();
        getLatestInfoOnForceRefreshOnly();
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null) {
            FcmViewModel fcmViewModel = (FcmViewModel) new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) this.providerFactory).get(FcmViewModel.class);
            fcmViewModel.getNotificationCountLiveData().removeObservers(getViewLifecycleOwner());
            fcmViewModel.getNotificationCountLiveData().observe(getViewLifecycleOwner(), new HomePageFragment$$ExternalSyntheticLambda7(this));
            if (!(getActivity() == null || getActivity().getIntent() == null)) {
                incomingIntent(getActivity().getIntent());
            }
        }
        this.mBinding.imageButtonMenuHome.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda33(this));
        this.mBinding.imageButtonNotification.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda34(this));
        this.mBinding.imageButtonCloseAddDevicesLayout.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda35(this));
        this.mBinding.imageButtonCloseAddMembersLayout.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda36(this));
        this.mBinding.layoutAddMembersButton.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda37(this));
        this.mBinding.layoutAddDevices.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.layoutNameHome.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda11(this));
        this.mBinding.ivRefreshButton.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda22(this));
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30842x53c11c29(Integer num) {
        String str = "" + num;
        if (num.intValue() <= 0 || Constants.IS_NOTIFICATION_VIEWED) {
            this.mBinding.textViewNotificationCount.setVisibility(4);
        } else {
            this.mBinding.textViewNotificationCount.setVisibility(0);
        }
        if (num.intValue() > 99) {
            str = "99+";
        }
        this.mBinding.textViewNotificationCount.setText(str);
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30843xe7ff8bc8(View view) {
        onClickDrawerMenuButton();
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30844x7c3dfb67(View view) {
        onClickNotificationsBellIcon();
    }

    /* renamed from: lambda$onViewCreated$7$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30845x107c6b06(View view) {
        onClickCloseAddDeviceLayout();
    }

    /* renamed from: lambda$onViewCreated$8$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30846xa4badaa5(View view) {
        onClickCloseAddMemberLayout();
    }

    /* renamed from: lambda$onViewCreated$9$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30847x38f94a44(View view) {
        onClickAddMembersLayoutButton();
    }

    /* renamed from: lambda$onViewCreated$10$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30839xba3fdb44(View view) {
        onClickAddDevicesLayoutButton();
    }

    /* renamed from: lambda$onViewCreated$11$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30840x4e7e4ae3(View view) {
        onClickLayoutName();
    }

    /* renamed from: lambda$onViewCreated$12$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30841xe2bcba82(View view) {
        performRefresh();
    }

    private void incomingIntent(Intent intent) {
        DeepLink deepLink;
        Reminder reminder;
        Bundle extras = intent.getExtras();
        if (extras != null && (deepLink = (DeepLink) extras.getParcelable(DeepLink.PARCEL_KEY)) != null && deepLink.getType() != null) {
            int i = C210816.$SwitchMap$com$jch$racWiFi$fcm$util$Type[deepLink.getType().ordinal()];
            if (i == 3) {
                Alert alert = (Alert) extras.getParcelable(Alert.PARCEL_KEY);
                if (alert != null && alert.getSubCategory() != null) {
                    switch (alert.getSubCategory()) {
                        case RAC_OFFLINE:
                        case RAC_OFFLINE_20_HOURS:
                            showRacOfflineBanner(alert);
                            intent.removeExtra(DeepLink.PARCEL_KEY);
                            intent.removeExtra(Alert.PARCEL_KEY);
                            return;
                        case ROLE_UPDATED:
                            showRoleUpdatedAlert(getString(R.string.notification_lbl_roleUpdateDesc, alert.getHomeName(), alert.getRoleName()));
                            return;
                        case REMOVED_FROM_HOME:
                            showRoleUpdatedAlert(getString(R.string.notification_lbl_removedFromHomeDesc, alert.getHomeName()));
                            return;
                        default:
                            return;
                    }
                }
            } else if (i == 4) {
                Maintenance maintenance = (Maintenance) extras.getParcelable(Maintenance.PARCEL_KEY);
                if (maintenance != null && maintenance.getSubCategory() != null && maintenance.getSubCategory() == MaintenanceSubCategory.PLANNED_MAINTENANCE) {
                    showPlannedMaintenance(maintenance);
                }
            } else if ((i == 5 || i == 6) && (reminder = (Reminder) extras.getParcelable(Reminder.PARCEL_KEY)) != null && reminder.getSubCategory() != null && reminder.getSubCategory() == ReminderSubCategory.CLEANING) {
                showCleanAndStart(reminder);
            }
        }
    }

    private void showCleanAndStart(Reminder reminder) {
        View root = this.mBinder.getBanner(reminder, (BannerListener<Reminder, LayoutCleaningAndTrialBinding>) new BannerListener<Reminder, LayoutCleaningAndTrialBinding>() {
            public void onItemClick(View view, Reminder reminder, LayoutCleaningAndTrialBinding layoutCleaningAndTrialBinding) {
            }

            public void onClick(View view, Reminder reminder, LayoutCleaningAndTrialBinding layoutCleaningAndTrialBinding) {
                HomePageFragment.this.mBinding.homePageBannerLayout.removeAllViews();
            }
        }).getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mBinding.homePageBannerLayout.addView(root);
    }

    private void showRoleUpdatedAlert(String str) {
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda31(this, str), 2000);
    }

    /* renamed from: lambda$showRoleUpdatedAlert$13$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30854x889a35eb(String str) {
        alertDialog(getString(R.string.manageUser_alert_roleUpdated), str, false);
    }

    private void showPlannedMaintenance(Maintenance maintenance) {
        View root = this.mBinder.getBanner(maintenance, (BannerListener<Maintenance, BannerPlannedMaintenanceBinding>) new BannerListener<Maintenance, BannerPlannedMaintenanceBinding>() {
            public void onItemClick(View view, Maintenance maintenance, BannerPlannedMaintenanceBinding bannerPlannedMaintenanceBinding) {
            }

            public void onClick(View view, Maintenance maintenance, BannerPlannedMaintenanceBinding bannerPlannedMaintenanceBinding) {
                HomePageFragment.this.mBinding.homePageBannerLayout.removeAllViews();
            }
        }).getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mBinding.homePageBannerLayout.addView(root);
    }

    private void scheduleEnableAdapterListHandler() {
        this.mEnableAdapterListHandler.removeCallbacksAndMessages((Object) null);
        this.mEnableAdapterListHandler.postDelayed(new HomePageFragment$$ExternalSyntheticLambda26(this), 3000);
    }

    /* renamed from: lambda$scheduleEnableAdapterListHandler$14$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30849xd4e8728d() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mDetailedIduAdapter.setDisableRacClick(false);
        }
    }

    private void stopEnableAdapterListHandler() {
        this.mEnableAdapterListHandler.removeCallbacksAndMessages((Object) null);
    }

    private void scheduleJpRegulationHandler() {
        showPleaseWaitDialog();
        this.mJpRegulationHandler.removeCallbacksAndMessages((Object) null);
        this.mJpRegulationHandler.postDelayed(new HomePageFragment$$ExternalSyntheticLambda27(this), JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    /* renamed from: lambda$scheduleJpRegulationHandler$16$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30850xca1ce61d() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            stopJpRegulationHandler();
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.home_alert_previousCmndDidntExecute));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda16.INSTANCE);
            singleChoiceDialog.show();
        }
    }

    private void stopJpRegulationHandler() {
        dismissPleaseWaitDialog();
        this.mJpRegulationHandler.removeCallbacksAndMessages((Object) null);
    }

    private void dismissDialog() {
        ConfirmationDialog confirmationDialog = this.mConfirmationDialog;
        if (confirmationDialog != null && confirmationDialog.isShowing()) {
            this.mConfirmationDialog.dismiss();
        }
    }

    /* renamed from: lambda$new$17$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30828x2dfc10d9(InviteeModel inviteeModel) {
        if (inviteeModel.isAccepted()) {
            performRefresh();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.showAlertDialog = new SingleChoiceDialog(getActivity());
        this.mNetworkAlertSingleChoiceDialog = GenericAlertUtils.getNoNetworkAlert(requireActivity());
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity());
        this.mProgressDialogNetworkCall = customProgressDialog;
        customProgressDialog.setCancelable(false);
        this.mHomePagePresenter = new HomePagePresenterImpl(this);
        this.iduAccessPresenter = new IduAccessPresenterImpl(this);
        if (Constants.IS_DEMO_MODE) {
            this.iduAccessPresenter.fetchFunctionalAccessDatas();
        }
        LocationUpdatesBroadcast locationUpdatesBroadcast = new LocationUpdatesBroadcast(this);
        this.mLocationUpdatesBroadcast = locationUpdatesBroadcast;
        locationUpdatesBroadcast.registerLocationBroadcastReceiver(getActivity());
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (Constants.IS_DEMO_MODE) {
                    Constants.IS_DEMO_MODE = false;
                }
                HomePageFragment.this.getActivity().finish();
            }
        });
        this.mUserOnBoardingPresenter = new UserOnBoardingPresenter(this);
        PermissionApiImpl.getInstance().requestConfiguartionAndRoles();
        this.mUserOnBoardingPresenter.onBoardUser();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (HomePageUpdatedVdBinding) DataBindingUtil.inflate(layoutInflater, R.layout.home_page_updated_vd, viewGroup, false);
        this.mInviteVerificationPresenter = new InviteVerificationPresenter(this);
        if (WeatherDataModel.CURRENT_HOME_PAGE_WEATHER.weatherResult != null) {
            setWeatherData(WeatherDataModel.CURRENT_HOME_PAGE_WEATHER);
        }
        if (Constants.IS_DEMO_MODE) {
            this.mBinding.layoutAddDevices.setVisibility(0);
            this.mBinding.layoutAddMembersButton.setVisibility(0);
        }
        this.mAllInvitationPresenter = new AllInvitationPresenter(this);
        InviteeLiveDataHolder.getInstance().getInvitationAcceptedMutableLiveData().observe(getViewLifecycleOwner(), this.mInvitationAcceptedObserver);
        getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().fetchUserInfo(requireActivity());
        this.mConfirmationDialog = new ConfirmationDialog(requireActivity());
        this.mGetFamilyGroupPresenter = new GetFamilyGroupPresenter(this);
        this.mManageUserFragmentPresenter = new ManageUserFragmentPresenter(this);
        this.weatherDataPresenter = new WeatherDataPresenter(this);
        this.rotateAnimation.setDuration(5000);
        this.rotateAnimation.setInterpolator(new LinearInterpolator());
        this.rotateAnimation.setRepeatCount(-1);
        this.rotateAnimation.setRepeatMode(1);
        if (!Constants.IS_DEMO_MODE) {
            bannerSettingsOnStart();
        }
        LayoutInflater layoutInflater2 = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        float dimension = getResources().getDimension(R.dimen.popup_height);
        float dimension2 = getResources().getDimension(R.dimen.popup_width);
        int round = Math.round(dimension);
        Math.round(dimension2);
        this.customView = layoutInflater.inflate(R.layout.pop_up_users_name, (ViewGroup) null);
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int round2 = point.x - Math.round(ViewUtils.convertDpToPixel(30.0f, getActivity()));
        UsersRecyclerViewAdapter usersRecyclerViewAdapter = new UsersRecyclerViewAdapter(getActivity());
        this.familyMembersRecyclerViewAdapter = usersRecyclerViewAdapter;
        usersRecyclerViewAdapter.getFamilyGroupMenuItemsUsers().addAll(populateFamilyMembersForRecyclerView(this.mFragmentToActivityCallback.getFamilyGroupList()));
        if (this.familyMembersRecyclerViewAdapter.getFamilyGroupMenuItemsUsers().size() >= 5) {
            this.mPopupWindow = new PopupWindow(this.customView, round2, round);
        } else {
            this.mPopupWindow = new PopupWindow(this.customView, round2, -2);
        }
        this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                HomePageFragment.this.refreshing = true;
            }

            public void onAnimationEnd(Animation animation) {
                HomePageFragment.this.refreshing = false;
            }
        });
        startRefreshButtonRotation();
        this.mBinding.rvDetailedIdulist.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.sbStopAll.setOnCheckedChangeListener(this.stopAllCheckListener);
        this.mDetailedIduAdapter = new DetailedIduAdapter(this.mNetworkAlertSingleChoiceDialog, getCoreActivity(), this.mHomePagePresenter, this) {
            public void onPowerStateChanged() {
                HomePageFragment.this.showPleaseWaitDialog(15000);
            }
        };
        this.mBinding.rvDetailedIdulist.setAdapter(this.mDetailedIduAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(this.mBinding.rvDetailedIdulist, 0);
        ViewUtils.setOutlineProviderSwitch(this.mBinding.sbStopAll);
        ConfigurationDataProvider.getInstance().iduAccessModelMutableLiveData.observe(getViewLifecycleOwner(), new HomePageFragment$$ExternalSyntheticLambda3(this));
        Bundle arguments = getArguments();
        if (arguments != null) {
            DemoModeModel.DEMO_MODE = arguments.getBoolean(Constants.DEMOMODE, false);
        }
        setUserName();
        this.mBinding.pullToRefresh.setOnRefreshListener(new HomePageFragment$$ExternalSyntheticLambda8(this));
        if (!Constants._INVITE_) {
            if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
                getLatestInfoOnForceRefreshOnly();
            } else {
                showInternetAlert();
            }
        }
        logEvent(Screens.SCREENS.name(), 2);
        return this.mBinding.getRoot();
    }

    /* renamed from: lambda$onCreateView$18$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30831x822093f2(IduAccessModel iduAccessModel) {
        IduAccessPresenter iduAccessPresenter2 = this.iduAccessPresenter;
        if (iduAccessPresenter2 != null) {
            iduAccessPresenter2.updatePermissionsMap(iduAccessModel);
            if (Constants.IS_DEMO_MODE) {
                dismissPleaseWaitDialogHome();
            }
        }
    }

    /* renamed from: lambda$onCreateView$19$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30832x165f0391() {
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            this.mBinding.pullToRefresh.setEnabled(false);
            this.mBinding.pullToRefresh.setRefreshing(true);
            getLatestIduInfo();
            return;
        }
        this.mBinding.pullToRefresh.setRefreshing(false);
        showInternetAlert();
    }

    private void bannerSettingsOnStart() {
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).f424id != -1) {
            LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
            if (currentUserConfig != null) {
                if (currentUserConfig.isMemberRemoved()) {
                    this.mBinding.layoutAddMembersButton.setVisibility(8);
                } else {
                    this.mBinding.layoutAddMembersButton.setVisibility(0);
                }
                if (currentUserConfig.isRacRemoved()) {
                    this.mBinding.layoutAddDevices.setVisibility(8);
                } else {
                    this.mBinding.layoutAddDevices.setVisibility(0);
                }
            } else {
                this.mBinding.layoutAddMembersButton.setVisibility(0);
                this.mBinding.layoutAddDevices.setVisibility(0);
            }
        }
    }

    public void onStart() {
        super.onStart();
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduListSingleLiveEvent().observe(getViewLifecycleOwner(), this.mIduListUpdateObserver);
        this.mFragmentToActivityCallback.getFamilyUpdateSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.familyObserver);
        this.mFragmentToActivityCallback.changeStatusBarColor(R.color.colorRed);
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().observe(getViewLifecycleOwner(), this.forceRefreshObserver);
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            showPleaseWaitDialogHome();
            if (UserInfo.getCurrentUserInfo(getCoreActivity()).familyId != -1) {
                this.mGetFamilyGroupPresenter.getFamilyGroup();
                return;
            }
            return;
        }
        showInternetAlert();
    }

    public void onResume() {
        super.onResume();
        getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().getUserInfoSingleLiveEvent().observeWithCachedTrigger(getViewLifecycleOwner(), this.userInfoObserver);
        getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().getUserInfoFailureSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse.getResponse() == null) {
                    return;
                }
                if (genericResponse.getResponse().code() == 401) {
                    HomePageFragment.this.showPleaseWaitDialog();
                    HomePageFragment.this.getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            C21169.this.callRefresh();
                        }
                    }, false);
                    return;
                }
                HomePageFragment homePageFragment = HomePageFragment.this;
                homePageFragment.showErrorPopUp(homePageFragment.getString(R.string.errorCode_alert_somethingWentWorng));
            }

            /* access modifiers changed from: private */
            public void callRefresh() {
                HomePageFragment.this.dismissPleaseWaitDialog();
                HomePageFragment.this.getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().fetchUserInfo(HomePageFragment.this.requireActivity());
            }
        });
        scheduleEnableAdapterListHandler();
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda25(this), 500);
        onProfilePicUpdated(FamilyGroupList.getCurrentFamily().creatorProfilePic);
        this.mBinding.allDevicesSwitchLayout.setVisibility(4);
        if (!NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            showInternetAlert();
        } else if (Weather.getCurrent().isWeatherDataAvailable) {
            loadWeatherData(Weather.getCurrent());
        }
        if (this.mUserPermissions.isPermissionsMapObtained()) {
            onPermissionsUpdated();
        }
        onIduListsFetched(getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList());
    }

    /* renamed from: lambda$onResume$20$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30837x691b6fa7() {
        if (getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED)) {
            getLatestIduInfo();
            this.mFragmentToActivityCallback.refreshAllIduStates();
            this.mDetailedIduAdapter.setDisableRacClick(true);
        }
    }

    public void onStop() {
        super.onStop();
        dismissDialog();
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().removeObserver(this.forceRefreshObserver);
        this.mFragmentToActivityCallback.getFamilyUpdateSingleLiveEvent().removeObserver(this.familyObserver);
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().removeObservers(this);
    }

    private void updateWeather() {
        Country byNameCodeFromCustomCountries;
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress != null && (byNameCodeFromCustomCountries = CountryUtils.getByNameCodeFromCustomCountries(getContext(), CountryUtils.getAllCountries(getContext()), UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode())) != null) {
            if (CountryUtils.ProhibitedCountryUtils.isCountryProhibited(getString(byNameCodeFromCustomCountries.getName()))) {
                requestLocationWeatherForProhibitedCountries(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getZipCode());
            } else {
                requestLocationWeather(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getZipCode());
            }
        }
    }

    public void onPermissionsUpdated() {
        this.mUserPermissions.setPermissionsMapObtained(true);
        boolean permission = this.mUserPermissions.getPermission(UserPermissions.UserFeatures.NOTIFICATIONS);
        this.mUserPermissions.getPermission(UserPermissions.UserFeatures.ADD_DEVICES);
        this.mUserPermissions.getPermission(UserPermissions.UserFeatures.ADD_MEMBERS);
        if (!Constants.IS_DEMO_MODE) {
            bannerSettingsOnStart();
        }
        this.mBinding.layoutNotification.setVisibility(permission ? 0 : 4);
        if (!permission) {
            this.iDrawerMenuFunctions.getMenuDrawer().setDrawerLockMode(1, (int) GravityCompat.END);
        } else {
            this.iDrawerMenuFunctions.getMenuDrawer().setDrawerLockMode(0, (int) GravityCompat.END);
        }
        this.mDetailedIduAdapter.notifyDataSetChanged();
    }

    public void onPowerCommandExecutionFailure() {
        if (this.mFragmentToActivityCallback != null) {
            this.mFragmentToActivityCallback.refreshAllIduStates();
        }
        dismissPleaseWaitDialog();
    }

    public void onPowerCommandExecutionSuccess() {
        dismissPleaseWaitDialog();
    }

    public void onModelWiseRacInfoAvailable(List<RacModelWiseData> list) {
        RacModelWiseConfigurationList racModelWiseConfigurationList = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList();
        for (RacModelWiseData next : list) {
            if (!racModelWiseConfigurationList.contains(next)) {
                racModelWiseConfigurationList.add(next);
            }
        }
        this.mDetailedIduAdapter.notifyDataSetChanged();
    }

    public void onModelTypeListAvailable(List<String> list) {
        ArrayList arrayList = new ArrayList();
        RacModelWiseConfigurationList racModelWiseConfigurationList = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList();
        for (String next : list) {
            if (next != null && !racModelWiseConfigurationList.containsRacConfiguration(next)) {
                arrayList.add(next);
            }
        }
        String[] strArr = new String[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = (String) it.next();
            i++;
        }
        if (!arrayList.isEmpty()) {
            this.mHomePagePresenter.getModelWiseData(strArr);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        DemoModeModel.DEMO_MODE = false;
        this.mLocationUpdatesBroadcast.unregisterLocationBroadcastReceiver(getActivity());
        this.mUserOnBoardingPresenter.removeCallbacks();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mManageUserFragmentPresenter.removeCallbacks();
        this.mInviteVerificationPresenter.removeCallbacks();
        this.mGetFamilyGroupPresenter.removeCallbacks();
        this.weatherDataPresenter.removeCallbacks();
        InviteeLiveDataHolder.getInstance().getInvitationAcceptedMutableLiveData().removeObserver(this.mInvitationAcceptedObserver);
        this.mAllInvitationPresenter.removeCallbacks();
        stopEnableAdapterListHandler();
        this.mBinder.unBind();
        this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
        stopJpRegulationHandler();
        JSONWeatherTask jSONWeatherTask = this.jsonWeatherTask;
        if (jSONWeatherTask != null) {
            jSONWeatherTask.cancel(true);
            this.jsonWeatherTask = null;
        }
    }

    public void onClickDrawerMenuButton() {
        logEvents(Events.MENU_FREQUENCY.name(), 0);
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    public void onClickNotificationsBellIcon() {
        logEvents(Events.NOTIFICATION_HOME.name(), 0);
        if (getActivity() != null) {
            ((HomePageActivity) getActivity()).getAllNotifications(new CallBackListener() {
                public void onSuccess() {
                    HomePageFragment.this.bellIcon();
                }

                public void onFailure() {
                    HomePageFragment.this.bellIcon();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void bellIcon() {
        this.mBinding.textViewNotificationCount.setVisibility(4);
        Constants.IS_NOTIFICATION_VIEWED = true;
        this.iDrawerMenuFunctions.openNotificationDrawer();
    }

    public void onClickCloseAddDeviceLayout() {
        this.mBinding.layoutAddDevices.setVisibility(8);
        if (!Constants.IS_DEMO_MODE) {
            Log.e("Banner_user_id", UserInfo.getCurrentUserInfo(getCoreActivity()).f424id + "");
            LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
            if (currentUserConfig != null) {
                currentUserConfig.setRacRemoved(true, currentUserConfig);
            }
        }
    }

    public void onClickCloseAddMemberLayout() {
        this.mBinding.layoutAddMembersButton.setVisibility(8);
        if (!Constants.IS_DEMO_MODE) {
            Log.e("Banner_user_id", UserInfo.getCurrentUserInfo(getCoreActivity()).f424id + "");
            LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
            if (currentUserConfig != null) {
                currentUserConfig.setMemberRemoved(true, currentUserConfig);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onClickAddMembersLayoutButton() {
        this.isClickedOnAddMembersButton = true;
        if (FamilyGroupList.getCurrentFamily().familyId != -1) {
            FamilyMembersList familyMembersList = (FamilyMembersList) this.mFragmentToActivityCallback.getFamilyMembersMap().get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
            if (familyMembersList == null || familyMembersList.isRequiredToRefreshList()) {
                showPleaseWaitDialog();
                this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
                return;
            }
            openInviteUserFragment(familyMembersList.size());
        }
    }

    public void onClickAddDevicesLayoutButton() {
        List<DetailedIduModel> list = this.mDetailedIduModels;
        if (list != null) {
            if (list.isEmpty()) {
                logEvents(Events.ADD_AC.name(), 0);
            } else {
                logEvents(Events.ADD_ANOTHER_AC.name(), 0);
            }
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_homePageFragment_to_on_board_nav_graph);
    }

    public void onClickLayoutName() {
        RecyclerView recyclerView = (RecyclerView) this.customView.findViewById(R.id.recycler_view_users_name);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.familyMembersRecyclerViewAdapter);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mPopupWindow.setElevation(5.0f);
        }
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setOnDismissListener(new HomePageFragment$$ExternalSyntheticLambda2(this));
        if (this.mPopupWindow.isShowing() || this.familyMembersRecyclerViewAdapter.getFamilyGroupMenuItemsUsers().size() < 1) {
            this.mPopupWindow.dismiss();
            this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
            return;
        }
        int[] iArr = new int[2];
        this.mBinding.textViewHome.getLocationInWindow(iArr);
        this.mPopupWindow.showAtLocation((View) getView().getParent(), 48, 0, iArr[1] + this.mBinding.textViewHome.getHeight());
        this.mBinding.parent.setBackgroundResource(R.drawable.homepageblur);
    }

    /* renamed from: lambda$onClickLayoutName$21$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30830x3b30311e() {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
    }

    public void onPowerOnOffSuccessful(long j, String str) {
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda24(this), 3000);
        showPleaseWaitDialog(15000);
    }

    /* renamed from: lambda$onPowerOnOffSuccessful$22$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30836xa9c39bbd() {
        stopRefreshButtonRotation();
        if (getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED)) {
            this.mBinding.pullToRefresh.setEnabled(true);
            this.mBinding.pullToRefresh.setRefreshing(false);
        }
    }

    public void onPowerOnOffFailed(long j, String str, int i) {
        dismissPleaseWaitDialog();
        this.mDetailedIduAdapter.updateIduPowerState(j, str);
        if (i != 412) {
            if (i != 423) {
                if (i == 429 && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
                    singleChoiceDialog.setTitleString(getString(R.string.common_alert));
                    singleChoiceDialog.setMessageString(getString(R.string.common_alert_prevComndIsUnderExecution));
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new HomePageFragment$$ExternalSyntheticLambda10(singleChoiceDialog));
                    singleChoiceDialog.show();
                    getLatestIduInfo();
                }
            } else if (this.mDetailedIduAdapter.currentRacName != null) {
                showAlert(getString(R.string.common_alert_anotherUserOpertRac, this.mDetailedIduAdapter.currentRacName));
            }
        } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.errorCode_alert_PCF009));
            singleChoiceDialog2.setMessageString(getString(R.string.common_alert_currentlyOffline, this.mDetailedIduAdapter.currentRacName));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), new HomePageFragment$$ExternalSyntheticLambda9(singleChoiceDialog2));
            singleChoiceDialog2.show();
        }
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda23(this), 3000);
    }

    /* renamed from: lambda$onPowerOnOffFailed$25$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30835xa2f007bd() {
        stopRefreshButtonRotation();
        if (getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED)) {
            this.mBinding.pullToRefresh.setEnabled(true);
            this.mBinding.pullToRefresh.setRefreshing(false);
        }
    }

    private void showAlert(String str) {
        this.showAlertDialog.setTitleString(getString(R.string.common_alert));
        this.showAlertDialog.setMessageString(str);
        this.showAlertDialog.setCancelable(false);
        this.showAlertDialog.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda17.INSTANCE);
        SingleChoiceDialog singleChoiceDialog = this.showAlertDialog;
        if (singleChoiceDialog != null && !singleChoiceDialog.isShowing()) {
            this.showAlertDialog.show();
        }
    }

    public void onIduListsFetched(List<DetailedIduModel> list) {
        this.mDetailedIduModels = list;
        stopEnableAdapterListHandler();
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda20(this), 1000);
        Object[] array = list.toArray();
        Arrays.sort(array, HomePageFragment$$ExternalSyntheticLambda32.INSTANCE);
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (list.size() > i) {
                    try {
                        list.set(i, (DetailedIduModel) array[i]);
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda21(this), 3000);
        if (list.size() > 0) {
            this.mBinding.rvDetailedIdulist.setVisibility(0);
            this.mBinding.allDevicesSwitchLayout.setVisibility(0);
            this.mDetailedIduAdapter.setDetailedIduModels(list);
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(DetailedIduModel.IDU_LIST, new NavArgument.Builder().setDefaultValue(new ArrayList(list)).build());
        } else {
            this.mBinding.rvDetailedIdulist.setVisibility(4);
            this.mBinding.allDevicesSwitchLayout.setVisibility(4);
        }
        this.mBinding.textViewLastUpdatedOn.setText(getString(R.string.common_lbl_lastUpdatedAt));
        TextView textView = this.mBinding.textViewLastUpdatedOn;
        textView.append(StringUtils.f715LF + DateAndTimeUtils.getCurrentDateTimeAsPerPattern("HH:mm"));
        this.mDetailedIduAdapter.activateCheckListeners();
        boolean z = true;
        boolean z2 = true;
        for (DetailedIduModel next : list) {
            boolean iduPermission = UserPermissions.getInstance().getIduPermission(UserPermissions.IduFeatures.ON_OFF, next.getId());
            if (z && next.online) {
                z = false;
            }
            if (z2 && iduPermission) {
                z2 = false;
            }
        }
        if (z || z2) {
            this.mBinding.sbStopAll.setVisibility(4);
        } else {
            this.mBinding.sbStopAll.setVisibility(0);
        }
    }

    /* renamed from: lambda$onIduListsFetched$27$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30833xd1348db9() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mDetailedIduAdapter.setDisableRacClick(false);
        }
    }

    static /* synthetic */ int lambda$onIduListsFetched$28(Object obj, Object obj2) {
        return ((DetailedIduModel) obj).f454id.intValue() - ((DetailedIduModel) obj2).f454id.intValue();
    }

    /* renamed from: lambda$onIduListsFetched$29$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30834xf9b16cf7() {
        stopRefreshButtonRotation();
        if (getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED)) {
            this.mBinding.pullToRefresh.setEnabled(true);
            this.mBinding.pullToRefresh.setRefreshing(false);
        }
    }

    public void onIduListFetchingFailed() {
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda29(this), 3000);
        this.mBinding.rvDetailedIdulist.setVisibility(4);
        this.mBinding.allDevicesSwitchLayout.setVisibility(4);
        this.mDetailedIduAdapter.activateCheckListeners();
    }

    public void onProfilePicUpdated(ProfilePicture profilePicture) {
        if (this.mBinding.imageViewProfileHome != null) {
            ProfilePicture.loadIntoImageView(this.mBinding.imageViewProfileHome, profilePicture);
        }
    }

    public void onNetworkCallFailure(Throwable th) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            showInternetAlert();
        }
    }

    public void onUserOnBoardSuccess() {
        if (Constants.INVITE_CODE != null) {
            this.mInviteVerificationPresenter.verifyInviteCode(Constants.INVITE_CODE);
        } else {
            getInvitationList();
        }
    }

    public void onUserOnBoardFailure(GenericResponse genericResponse) {
        if (genericResponse.getResponse().code() == 401) {
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HomePageFragment.this.callRefreshUserOnBoard();
                }
            }, false);
        } else {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        }
        if (Constants.INVITE_CODE != null) {
            this.mInviteVerificationPresenter.verifyInviteCode(Constants.INVITE_CODE);
            Logger.m47e("user_invite", "onUserOnBoardSuccess invite code not null");
            return;
        }
        getInvitationList();
    }

    /* access modifiers changed from: package-private */
    public void performRefresh() {
        if (getActivity() == null || !NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            showInternetAlert();
        } else if (!this.refreshing) {
            getLatestIduInfo();
            startRefreshButtonRotation();
            getLatestInfoOnForceRefreshOnly();
            this.mAllInvitationPresenter.getAllInvitations(getViewLifecycleOwner());
            this.refreshing = true;
            logEvents(Events.REFRESH_HOME.name(), 0);
        }
    }

    private void getLatestIduInfo() {
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            startRefreshButtonRotation();
            Toast.makeText(getActivity(), getString(R.string.home_alert_refreshingPleaseWait), 0).show();
            this.mDetailedIduAdapter.preventCheckListeners();
            if (UserInfo.getCurrentUserInfo(getCoreActivity()).f424id != 0) {
                this.mFragmentToActivityCallback.refreshAllIduStates();
                return;
            }
            return;
        }
        showInternetAlert();
    }

    private void getLatestInfoOnForceRefreshOnly() {
        getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().fetchUserInfo(requireActivity());
    }

    public void onStoppingSuccessful() {
        this.mDetailedIduAdapter.stopAll();
        dismissPleaseWaitDialog();
    }

    public void onStartAllSuccessful() {
        this.mDetailedIduAdapter.startAll();
        dismissPleaseWaitDialog();
    }

    public void onStartAllPartiallyComplete(List<StopAllUnitsModels.IndividualRacStartAllUnitResponseData> list) {
        this.mDetailedIduAdapter.startAllPartially(list);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        List<DetailedIduModel> detailedIduModelList = this.mDetailedIduAdapter.getDetailedIduModelList();
        for (StopAllUnitsModels.IndividualRacStartAllUnitResponseData next : list) {
            for (DetailedIduModel next2 : detailedIduModelList) {
                if (next2.f454id.intValue() == next.racId) {
                    if (!StopAllUnitsModels.ERROR_RESPONSE.contains(Integer.valueOf(next.errorCode)) || next.errorCode == 412 || next.errorCode == 413) {
                        CommandStatus commandStatus = next.commandStatus;
                        if (commandStatus != null && commandStatus.getStatus().isIncomplete()) {
                            sb2.append(next2.name);
                            sb2.append(',');
                        }
                    } else {
                        sb.append(next2.name);
                        sb.append(',');
                    }
                }
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.home_alert_areCurrentlyBusy, sb.toString()));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda12.INSTANCE);
            singleChoiceDialog.show();
        }
        if (sb2.length() > 1) {
            sb2.setLength(sb2.length() - 1);
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(getString(R.string.home_alert_previousCmndDidntExecuteRacName, sb2.toString()));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda13.INSTANCE);
            singleChoiceDialog2.show();
        }
        dismissPleaseWaitDialog();
    }

    public void onStartingAllFailed() {
        Toaster.makeToast(getActivity(), getString(R.string.home_alert_stopAllUnitsFailed), 0);
        dismissPleaseWaitDialog();
    }

    public void onStoppingFailed() {
        Toaster.makeToast(getActivity(), getString(R.string.home_alert_stopAllUnitsFailed), 0);
        dismissPleaseWaitDialog();
    }

    public void onStoppingPartiallyComplete(List<StopAllUnitsModels.IndividualRacStopAllUintResponseData> list) {
        this.mDetailedIduAdapter.stopAllPartially(list);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        List<DetailedIduModel> detailedIduModelList = this.mDetailedIduAdapter.getDetailedIduModelList();
        for (StopAllUnitsModels.IndividualRacStopAllUintResponseData next : list) {
            for (DetailedIduModel next2 : detailedIduModelList) {
                if (next2.f454id.intValue() == next.racId) {
                    if (!StopAllUnitsModels.ERROR_RESPONSE.contains(Integer.valueOf(next.errorCode)) || next.errorCode == 412 || next.errorCode == 413) {
                        CommandStatus commandStatus = next.commandStatus;
                        if (commandStatus != null && commandStatus.getStatus().isIncomplete()) {
                            sb2.append(next2.name);
                            sb2.append(',');
                        }
                    } else {
                        sb.append(next2.name);
                        sb.append(',');
                    }
                }
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.home_alert_areCurrentlyBusy, sb.toString()));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda14.INSTANCE);
            singleChoiceDialog.show();
        }
        if (sb2.length() > 1) {
            sb2.setLength(sb2.length() - 1);
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(getString(R.string.home_alert_previousCmndDidntExecuteRacName, sb2.toString()));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), HomePageFragment$$ExternalSyntheticLambda15.INSTANCE);
            singleChoiceDialog2.show();
        }
        dismissPleaseWaitDialog();
    }

    public void checkStopAllButton() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mBinding.sbStopAll.setCheckedSilent(true);
        }
    }

    public void unCheckStopAllButton() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mBinding.sbStopAll.setCheckedSilent(false);
        }
    }

    public void onInviteCodeVerificationSuccess() {
        getLatestInfoOnForceRefreshOnly();
        Constants._INVITE_ = false;
        Toaster.makeToast(getActivity(), getString(R.string.home_alert_invitationAcceptedSuccessFully), 0);
        Constants.INVITE_CODE = null;
        Constants.isAppOpenedFormInviteLink = false;
        getInvitationList();
    }

    public void onInviteCodeVerificationFailure(InviteMemberModels.InviteMemberCodeVerificationFailure inviteMemberCodeVerificationFailure) {
        if (inviteMemberCodeVerificationFailure.code != null) {
            Constants.INVITE_CODE = null;
            Constants._INVITE_ = false;
            Constants.isAppOpenedFormInviteLink = false;
            getLatestInfoOnForceRefreshOnly();
            getInvitationList();
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
        } else if (inviteMemberCodeVerificationFailure.statusCode == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HomePageFragment.this.callInviationApi();
                }
            }, false);
        } else {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        }
    }

    /* access modifiers changed from: private */
    public void callInviationApi() {
        dismissPleaseWaitDialog();
        if (Constants.INVITE_CODE != null) {
            this.mInviteVerificationPresenter.verifyInviteCode(Constants.INVITE_CODE);
            Logger.m47e("user_invite", "onUserOnBoardSuccess invite code not null");
        } else {
            getInvitationList();
        }
        Constants.INVITE_CODE = null;
        Constants._INVITE_ = false;
        Constants.isAppOpenedFormInviteLink = false;
        getLatestInfoOnForceRefreshOnly();
        getInvitationList();
    }

    public void onLocationChangedFromBroadcast(Location location) {
        getCoreActivity().stopLocationBroadcast();
    }

    private void refreshRacConfigurationList() {
        this.mHomePagePresenter.getRacModelTypesForFamily(FamilyGroupList.getCurrentFamily().familyId);
    }

    public void onUserFamilyGroupFetchSuccess(FamilyGroupsModels.FamilyGroupsModelResponseSuccess familyGroupsModelResponseSuccess) {
        if (LifeCycleUtils.isAtLeastResumed(getLifecycle())) {
            FamilyGroupList familyGroupList = this.mFragmentToActivityCallback.getFamilyGroupList();
            familyGroupList.updateList(familyGroupsModelResponseSuccess.familyResult);
            familyGroupList.updateCurrentFamily(getCoreActivity());
            onProfilePicUpdated(FamilyGroupList.getCurrentFamily().creatorProfilePic);
            GeoFencePair geoFencePair = (GeoFencePair) getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().get(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
            if (geoFencePair != null && geoFencePair.isDefault) {
                getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().remove(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
            }
            setUserName();
            this.iduAccessPresenter.fetchFunctionalAccessDatas();
            this.familyMembersRecyclerViewAdapter.getFamilyGroupMenuItemsUsers().clear();
            this.familyMembersRecyclerViewAdapter.getFamilyGroupMenuItemsUsers().addAll(populateFamilyMembersForRecyclerView(familyGroupList));
            this.familyMembersRecyclerViewAdapter.notifyDataSetChanged();
            if (this.mFragmentToActivityCallback.getWebSocketWrapper().isConnected()) {
                this.mFragmentToActivityCallback.getWebSocketWrapper().disconnectWebSocket(false);
                this.mFragmentToActivityCallback.getWebSocketWrapper().dispose();
            } else {
                this.mFragmentToActivityCallback.connectStompClient();
            }
            refreshRacConfigurationList();
            this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
            Logger.m47e("Family_Group_API", FirebaseAnalytics.Param.SUCCESS + familyGroupsModelResponseSuccess.familyResult[0]);
        }
    }

    public void onUserFamilyGroupFetchFailure(FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure) {
        if (LifeCycleUtils.isAtLeastResumed(getLifecycle())) {
            if (familyGroupsModelResponseSuccessFailure.code != null) {
                String str = familyGroupsModelResponseSuccessFailure.code;
                str.hashCode();
                if (str.equals("NFE002")) {
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE002), 0).show();
                } else if (!str.equals("NFE013")) {
                    Toast.makeText(getContext(), getResources().getString(R.string.myAcc_alert_unableToFetchFamily), 1).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE013), 0).show();
                }
            } else {
                callRefreshApi(familyGroupsModelResponseSuccessFailure.statusCode);
            }
            Logger.m47e("Family_Group_API", "failureResponse" + familyGroupsModelResponseSuccessFailure.statusCode);
        }
    }

    private void callRefreshApi(int i) {
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HomePageFragment.this.callRefresh();
                }
            }, false);
            return;
        }
        showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
    }

    private void callRefreshApiforWeather(int i) {
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HomePageFragment.this.weatherDataPresenter.getWeatherData(LocaleConfiguration.getLanguageCodeForCurrentLocale());
                }
            }, false);
        }
    }

    /* access modifiers changed from: private */
    public void callRefresh() {
        dismissPleaseWaitDialog();
        getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel().fetchUserInfo(this);
    }

    /* access modifiers changed from: private */
    public void callRefreshUserOnBoard() {
        dismissPleaseWaitDialog();
        this.mUserOnBoardingPresenter.onBoardUser();
    }

    private void openInviteUserFragment(int i) {
        this.isClickedOnAddMembersButton = false;
        Bundle bundle = new Bundle();
        bundle.putInt(Values.NAVIGATING_FROM, 0);
        bundle.putInt(Values.FAMILY_MEMBER_COUNT, i);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_homePageFragment_to_inviteUsersFragmentNewVd, bundle);
        logEvents(Events.ADD_USER_HOME.name(), 0);
    }

    public void onUserFamilyListAvailableForBanners(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        if (!Constants.IS_DEMO_MODE) {
            boolean permission = this.mUserPermissions.getPermission(UserPermissions.UserFeatures.ADD_DEVICES);
            boolean permission2 = this.mUserPermissions.getPermission(UserPermissions.UserFeatures.ADD_MEMBERS);
            membersCount = userFamilyMemberSuccessResponse.userFamilyMemberInfo.size();
            LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
            if (currentUserConfig == null) {
                LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.persistUser(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
                currentUserConfig = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getCurrentUserConfig(UserInfo.getCurrentUserInfo(getCoreActivity()).f424id);
            }
            if (permission || permission2) {
                if (!currentUserConfig.getIsLoggedIn()) {
                    currentUserConfig.setLoginCount(currentUserConfig.getLoginCount() + 1, currentUserConfig);
                }
                if (UserInfo.getCurrentUserInfo(getCoreActivity()).role[0].getId().intValue() == 2 || UserInfo.getCurrentUserInfo(getCoreActivity()).role[0].getId().intValue() == 3) {
                    this.mBinding.layoutAddDevices.setVisibility(8);
                    this.mBinding.layoutAddMembersButton.setVisibility(8);
                    return;
                }
                int size = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().size();
                int i = membersCount;
                if (size == 0) {
                    if (!currentUserConfig.isRacRemoved()) {
                        this.mBinding.layoutAddDevices.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddDevices.setVisibility(8);
                    }
                }
                if (i == 0) {
                    if (!currentUserConfig.isMemberRemoved()) {
                        this.mBinding.layoutAddMembersButton.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddMembersButton.setVisibility(8);
                    }
                }
                int dayDifference = LocaleConfiguration.AddUserAndAddAcButtonConfigurationUtils.getDayDifference(Long.valueOf(currentUserConfig.getSessionTimeInMilSec()));
                if (size > 0) {
                    if (currentUserConfig.getLoginCount() > 3 || dayDifference > 7) {
                        this.mBinding.layoutAddMembersButton.setVisibility(8);
                        if (!currentUserConfig.isRacRemoved()) {
                            this.mBinding.layoutAddDevices.setVisibility(0);
                        } else {
                            this.mBinding.layoutAddDevices.setVisibility(8);
                        }
                    } else if (!currentUserConfig.isRacRemoved()) {
                        this.mBinding.layoutAddDevices.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddDevices.setVisibility(8);
                    }
                }
                if (i > 0) {
                    if (currentUserConfig.getLoginCount() > 3 || dayDifference > 7) {
                        if (!currentUserConfig.isMemberRemoved()) {
                            this.mBinding.layoutAddMembersButton.setVisibility(0);
                        } else {
                            this.mBinding.layoutAddMembersButton.setVisibility(8);
                        }
                    } else if (!currentUserConfig.isMemberRemoved()) {
                        this.mBinding.layoutAddMembersButton.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddMembersButton.setVisibility(8);
                    }
                }
                if (i > 0 && size > 0) {
                    if (dayDifference > 7 || currentUserConfig.getLoginCount() > 3) {
                        this.mBinding.layoutAddMembersButton.setVisibility(8);
                        this.mBinding.layoutAddDevices.setVisibility(8);
                        return;
                    }
                    if (!currentUserConfig.isMemberRemoved()) {
                        this.mBinding.layoutAddMembersButton.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddMembersButton.setVisibility(8);
                    }
                    if (!currentUserConfig.isRacRemoved()) {
                        this.mBinding.layoutAddDevices.setVisibility(0);
                    } else {
                        this.mBinding.layoutAddDevices.setVisibility(8);
                    }
                }
            } else {
                this.mBinding.layoutAddDevices.setVisibility(8);
                this.mBinding.layoutAddMembersButton.setVisibility(8);
            }
        }
    }

    public void onUserFamilyListAvailable(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda30(this, userFamilyMemberSuccessResponse), 500);
    }

    /* renamed from: lambda$onUserFamilyListAvailable$34$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30838x40e268bb(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            dismissPleaseWaitDialog();
            FamilyMembersMap familyMembersMap = this.mFragmentToActivityCallback.getFamilyMembersMap();
            FamilyMembersList familyMembersList = (FamilyMembersList) familyMembersMap.get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
            if (familyMembersList != null) {
                familyMembersList.updateList(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
            } else {
                FamilyMembersList familyMembersList2 = new FamilyMembersList();
                familyMembersList2.addAll(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
                familyMembersMap.put(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId), familyMembersList2);
            }
            if (this.isClickedOnAddMembersButton) {
                openInviteUserFragment(userFamilyMemberSuccessResponse.userFamilyMemberInfo.size());
            }
            onUserFamilyListAvailableForBanners(userFamilyMemberSuccessResponse);
        }
    }

    public void onUserFamilyListFetchFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse) {
        dismissPleaseWaitDialog();
        callRefreshApi(userFamilyMemberFailureResponse.statusCode);
        String str = userFamilyMemberFailureResponse.code;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1995143803:
                if (str.equals("NFE002")) {
                    c = 0;
                    break;
                }
                break;
            case -1995143796:
                if (str.equals("NFE009")) {
                    c = 1;
                    break;
                }
                break;
            case -1995143771:
                if (str.equals("NFE013")) {
                    c = 2;
                    break;
                }
                break;
            case 2066172683:
                if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.ROLE_OF_CREATOR_CANNOT_BE_CHANGED)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE002), 0);
                return;
            case 1:
                Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE009), 0);
                return;
            case 2:
                Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_NFE013), 0);
                return;
            case 3:
                Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_FAE005), 0);
                return;
            default:
                Toaster.makeToast(getActivity(), getString(R.string.myAcc_alert_unableToFetchFamilyMembersList), 0);
                return;
        }
    }

    public void onGetWeatherDataSuccess(WeatherDataModel.WeatherDataModelResponseSuccess weatherDataModelResponseSuccess) {
        WeatherDataModel.CURRENT_HOME_PAGE_WEATHER = weatherDataModelResponseSuccess;
        Logger.m47e("Weather API response", weatherDataModelResponseSuccess.weatherResult.cityName);
        setWeatherData(weatherDataModelResponseSuccess);
    }

    public void onGetWeatherDataFailure(WeatherDataModel.WeatherDataModelFailureResponse weatherDataModelFailureResponse) {
        callRefreshApiforWeather(weatherDataModelFailureResponse.statusCode);
        this.mBinding.textViewAreaTemprature.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaName.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaTempratureUnit.setText(getString(R.string.dots_small));
        this.mBinding.textViewWeatherType.setText(getString(R.string.dots_small));
        this.mBinding.imageViewSun.setImageResource(R.drawable.ic_error_outline_white_24dp);
    }

    public void getAllInvitationSuccessResponse(InviteeList inviteeList) {
        if (inviteeList != null) {
            InviteeLiveDataHolder.getInstance().getInviteeListMutableLiveData().postValue(inviteeList);
        }
    }

    public void getAllInvitationFailureResponse(GenericResponse genericResponse) {
        if (genericResponse.getResponse().code() == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HomePageFragment.this.callGetAllInvitationApi();
                }
            }, false);
            return;
        }
        showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
    }

    /* access modifiers changed from: private */
    public void callGetAllInvitationApi() {
        dismissPleaseWaitDialog();
        this.mAllInvitationPresenter.getAllInvitations(getViewLifecycleOwner());
    }

    public class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
        public JSONWeatherTask() {
        }

        public Weather doInBackground(String... strArr) {
            String city;
            String weatherData = new WeatherHttpClient().getWeatherData(strArr[0], strArr[1]);
            if (weatherData == null && (city = UserInfo.getCurrentUserInfo(HomePageFragment.this.getCoreActivity()).userAddress.getCity()) != null) {
                weatherData = new WeatherHttpClient().getWeatherData(city);
            }
            if (weatherData != null) {
                try {
                    return JSONWeatherParser.getWeather(weatherData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                Logger.m47e("NETWORK_FAILURE", "weather failure");
                return null;
            }
        }

        public void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            if (!HomePageFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                return;
            }
            if (weather == null) {
                HomePageFragment.this.loadWeatherData((Weather) null);
            } else if (!isCancelled() && HomePageFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                Weather.getCurrent().copy(weather);
                Weather.getCurrent().isWeatherDataAvailable = true;
                HomePageFragment.this.loadWeatherData(weather);
            }
        }
    }

    private void requestLocationWeather(String str) {
        if (str != null) {
            Logger.m47e("COUNTRY_CODE", str);
            this.jsonWeatherTask = new JSONWeatherTask();
            Geocoder geocoder = new Geocoder(getActivity());
            try {
                String countryCode = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
                if (countryCode != null) {
                    List<Address> fromLocationName = geocoder.getFromLocationName(str + ", " + countryCode, 1);
                    if (fromLocationName != null && !fromLocationName.isEmpty()) {
                        Logger.m47e("COUNTRY_CODE", fromLocationName.get(0).getCountryCode());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestLocationWeatherForProhibitedCountries(String str) {
        if (str != null) {
            Logger.m47e("COUNTRY_CODE", str);
            this.jsonWeatherTask = new JSONWeatherTask();
            if (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode() != null) {
                Logger.m47e("COUNTRY_CODE", UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode());
                UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadWeatherData(Weather weather) {
        if (weather != null) {
            String descr = weather.currentCondition.getDescr();
            try {
                this.mBinding.textViewAreaTemprature.setText(TemperatureValueFormatter.getConvertedValue((double) weather.temperature.getTemp()));
            } catch (Exception e) {
                e.printStackTrace();
                this.mBinding.textViewAreaTemprature.setText(getString(R.string.dots_small));
            }
            this.mBinding.textViewAreaName.setText(weather.location.getCity());
            this.mBinding.textViewAreaTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
            if (LocaleConfiguration.getCurrentAppLocale().getLanguage().toLowerCase().equals("en")) {
                TextView textView = this.mBinding.textViewWeatherType;
                textView.setText(descr + ",");
            } else {
                TextView textView2 = this.mBinding.textViewWeatherType;
                textView2.setText(descr + ",");
            }
            this.mBinding.imageViewSun.setImageResource(weather.currentCondition.getWeatherIcon(true));
            return;
        }
        this.mBinding.textViewAreaTemprature.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaName.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaTempratureUnit.setText(getString(R.string.dots_small));
        this.mBinding.textViewWeatherType.setText(getString(R.string.dots_small));
        this.mBinding.imageViewSun.setImageResource(R.drawable.ic_error_outline_white_24dp);
    }

    private void setWeatherData(WeatherDataModel.WeatherDataModelResponseSuccess weatherDataModelResponseSuccess) {
        if (weatherDataModelResponseSuccess != null) {
            String str = weatherDataModelResponseSuccess.weatherResult.weathers[0].description;
            try {
                this.mBinding.textViewAreaTemprature.setText(TemperatureValueFormatter.getConvertedValue(weatherDataModelResponseSuccess.weatherResult.mainParameters.temperature));
            } catch (Exception e) {
                e.printStackTrace();
                this.mBinding.textViewAreaTemprature.setText(getString(R.string.dots_small));
            }
            this.mBinding.textViewAreaName.setText(weatherDataModelResponseSuccess.weatherResult.cityName);
            this.mBinding.textViewAreaTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
            if (LocaleConfiguration.getCurrentAppLocale().getLanguage().toLowerCase().equals("en")) {
                TextView textView = this.mBinding.textViewWeatherType;
                textView.setText(str + ",");
            } else {
                TextView textView2 = this.mBinding.textViewWeatherType;
                textView2.setText(str + ",");
            }
            this.mBinding.imageViewSun.setImageResource(WeatherIconsFactory.getWeatherIcon(Integer.parseInt(weatherDataModelResponseSuccess.weatherResult.weathers[0].f489id)));
            return;
        }
        this.mBinding.textViewAreaTemprature.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaName.setText(getString(R.string.dots_small));
        this.mBinding.textViewAreaTempratureUnit.setText(getString(R.string.dots_small));
        this.mBinding.textViewWeatherType.setText(getString(R.string.dots_small));
        this.mBinding.imageViewSun.setImageResource(R.drawable.ic_error_outline_white_24dp);
    }

    private void startRefreshButtonRotation() {
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            this.mBinding.ivRefreshButton.startAnimation(this.rotateAnimation);
        } else {
            showInternetAlert();
        }
    }

    /* access modifiers changed from: private */
    public void stopRefreshButtonRotation() {
        this.rotateAnimation.cancel();
    }

    private List<UsersRecyclerItemModel> populateFamilyMembersForRecyclerView(FamilyGroupList familyGroupList) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < familyGroupList.size(); i++) {
            FamilyGroupsModels.FamilyResult familyResult = (FamilyGroupsModels.FamilyResult) familyGroupList.get(i);
            UsersRecyclerItemModel usersRecyclerItemModel = new UsersRecyclerItemModel();
            String str = familyResult.familyName;
            usersRecyclerItemModel.setName(getResources().getString(R.string.common_lbl_homeColon) + " " + str);
            usersRecyclerItemModel.setFamily(familyResult);
            usersRecyclerItemModel.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda1(this, familyResult));
            arrayList.add(usersRecyclerItemModel);
            usersRecyclerItemModel.setSelected(FamilyGroupList.getCurrentFamily().familyId == familyResult.familyId);
        }
        return arrayList;
    }

    /* renamed from: lambda$populateFamilyMembersForRecyclerView$35$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30848x691910b9(FamilyGroupsModels.FamilyResult familyResult, View view) {
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            FamilyGroupList.copyToCurrentFamily(familyResult);
            refreshRacConfigurationList();
            this.mFragmentToActivityCallback.getWebSocketWrapper().disconnectWebSocket(false);
            this.mFragmentToActivityCallback.getWebSocketWrapper().dispose();
            this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
            this.mPopupWindow.dismiss();
            showPleaseWaitDialogHome();
            this.iduAccessPresenter.fetchFunctionalAccessDatas();
            setUserName();
            getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(getViewLifecycleOwner());
            return;
        }
        this.mPopupWindow.dismiss();
        showInternetAlert();
    }

    class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersViewHolder> {
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public final List<UsersRecyclerItemModel> familyGroupMenuItemsUsers = new ArrayList();
        private UsersRecyclerItemModel mSelectedUser;

        public List<UsersRecyclerItemModel> getFamilyGroupMenuItemsUsers() {
            return this.familyGroupMenuItemsUsers;
        }

        public UsersRecyclerItemModel getUserName() {
            return this.mSelectedUser;
        }

        public UsersRecyclerViewAdapter(Context context2) {
            this.context = context2;
        }

        public UsersViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UsersViewHolder((RecyclerViewItemsFamilyHomeBinding) DataBindingUtil.inflate(LayoutInflater.from(HomePageFragment.this.requireActivity()), R.layout.recycler_view_items_family_home, viewGroup, false));
        }

        public void onBindViewHolder(UsersViewHolder usersViewHolder, int i) {
            usersViewHolder.bind(this.familyGroupMenuItemsUsers.get(i));
        }

        public int getItemCount() {
            return this.familyGroupMenuItemsUsers.size();
        }

        class UsersViewHolder extends RecyclerView.ViewHolder {
            RecyclerViewItemsFamilyHomeBinding binding;

            public UsersViewHolder(RecyclerViewItemsFamilyHomeBinding recyclerViewItemsFamilyHomeBinding) {
                super(recyclerViewItemsFamilyHomeBinding.getRoot());
                this.binding = recyclerViewItemsFamilyHomeBinding;
                recyclerViewItemsFamilyHomeBinding.layoutFamilyGroup.setOnClickListener(new C2118x5064becd(this, recyclerViewItemsFamilyHomeBinding));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment$UsersRecyclerViewAdapter$UsersViewHolder */
            public /* synthetic */ void mo30907x16d1538b(RecyclerViewItemsFamilyHomeBinding recyclerViewItemsFamilyHomeBinding, View view) {
                onClickItem(recyclerViewItemsFamilyHomeBinding.layoutFamilyGroup);
            }

            public void onClickItem(ConstraintLayout constraintLayout) {
                UsersRecyclerItemModel usersRecyclerItemModel = (UsersRecyclerItemModel) constraintLayout.getTag();
                usersRecyclerItemModel.onClickListener.onClick(constraintLayout);
                for (UsersRecyclerItemModel usersRecyclerItemModel2 : UsersRecyclerViewAdapter.this.familyGroupMenuItemsUsers) {
                    usersRecyclerItemModel2.setSelected(usersRecyclerItemModel2.equals(usersRecyclerItemModel));
                }
                HomePageFragment.this.onProfilePicUpdated(FamilyGroupList.getCurrentFamily().creatorProfilePic);
                UsersRecyclerViewAdapter.this.notifyDataSetChanged();
            }

            public void bind(UsersRecyclerItemModel usersRecyclerItemModel) {
                this.binding.textViewLanguage.setText(usersRecyclerItemModel.getName());
                if (usersRecyclerItemModel.isSelected()) {
                    this.binding.layoutFamilyGroup.setBackgroundColor(UsersRecyclerViewAdapter.this.context.getResources().getColor(R.color.lyt_grey));
                } else {
                    this.binding.layoutFamilyGroup.setBackgroundColor(UsersRecyclerViewAdapter.this.context.getResources().getColor(R.color.white));
                }
                this.binding.layoutFamilyGroup.setTag(usersRecyclerItemModel);
            }
        }
    }

    public static class UsersRecyclerItemModel {
        private FamilyGroupsModels.FamilyResult familyResult;
        private String name;
        /* access modifiers changed from: private */
        public View.OnClickListener onClickListener;
        private boolean selected;

        public FamilyGroupsModels.FamilyResult getFamily() {
            return this.familyResult;
        }

        public void setFamily(FamilyGroupsModels.FamilyResult familyResult2) {
            this.familyResult = familyResult2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean z) {
            this.selected = z;
        }

        public boolean equals(Object obj) {
            return (obj instanceof UsersRecyclerItemModel) && ((UsersRecyclerItemModel) obj).name.equals(this.name);
        }
    }

    /* access modifiers changed from: private */
    public void setUserName() {
        if (this.mBinding.textViewUserNameHomeBlank == null) {
            return;
        }
        if (FamilyGroupList.getCurrentFamily().getFamilyName() != null) {
            this.mBinding.textViewUserNameHomeBlank.setText(getResources().getString(R.string.common_lbl_homeColon) + " " + FamilyGroupList.getCurrentFamily().getFamilyName());
            return;
        }
        this.mBinding.textViewUserNameHomeBlank.setText(getResources().getString(R.string.common_lbl_homeColon) + " ----");
    }

    public void showPleaseWaitDialogHome() {
        this.mProgressDialogNetworkCall.setMessage(getString(R.string.common_alert_pleaseWait));
        CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
        if (customProgressDialog != null && !customProgressDialog.isShowing()) {
            this.mProgressDialogNetworkCall.show();
            this.autoDismissHandler.postDelayed(new HomePageFragment$$ExternalSyntheticLambda28(this), 15000);
        }
    }

    /* renamed from: lambda$showPleaseWaitDialogHome$36$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30851xf9f66f79() {
        try {
            AbstractNetworkDispatcher.getBodyCall().cancel();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mProgressDialogNetworkCall.dismiss();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 0);
    }

    public void dismissPleaseWaitDialogHome() {
        CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda18(this), 200);
        }
    }

    /* renamed from: lambda$dismissPleaseWaitDialogHome$37$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30825x8f1d548b() {
        this.mProgressDialogNetworkCall.dismiss();
        this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public void showInternetAlert() {
        SingleChoiceDialog singleChoiceDialog = this.mNetworkAlertSingleChoiceDialog;
        if (singleChoiceDialog != null && !singleChoiceDialog.isShowing()) {
            this.mNetworkAlertSingleChoiceDialog.show();
        }
    }

    private void getInvitationList() {
        new Handler().postDelayed(new HomePageFragment$$ExternalSyntheticLambda19(this), 100);
    }

    /* renamed from: lambda$getInvitationList$38$com-jch-racWiFi-iduManagement-view-viewImpl-HomePageFragment */
    public /* synthetic */ void mo30826xd9a9d2e7() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mAllInvitationPresenter.getAllInvitations(getViewLifecycleOwner());
        }
    }
}
