package com.jch.racWiFi.userManagement.view.viewImpl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.g00fy2.versioncompare.Version;
import com.google.gson.Gson;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.NetworkDispatch.UserExistanceCheckForInviteCodeNetworkDispatcher;
import com.jch.racWiFi.Presenter.PrivacyPolicyPresenter;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.BannerPlannedMaintenanceBinding;
import com.jch.racWiFi.databinding.FragmentSplashBinding;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.TokenResponse;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.main.model.AppVersion;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.settings.view.AppVersionDialog;
import com.jch.racWiFi.settings.view.LanguageSelectionDialog;
import com.jch.racWiFi.userManagement.InviteVerificationModels;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import com.jch.racWiFi.userManagement.viewModel.UserViewModel;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import java.net.UnknownHostException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashFragment extends GenericFragment implements PrivacyPolicyPresenter.IPrivacyPolicyPresenter {
    private final Observer<GenericResponse> genericErrorResponseObserver = new Observer<GenericResponse>() {
        public void onChanged(GenericResponse genericResponse) {
            if (genericResponse.getResponse().code() == 401) {
                SplashFragment.this.getCoreActivity().refreshToken(new CallBackListener() {
                    public void onSuccess() {
                        SplashFragment.this.userViewModel.fetchUserInfo(SplashFragment.this.requireActivity());
                    }

                    public void onFailure() {
                        SplashFragment.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
                        TokenUtil.getInstance().clear();
                        UserInfo.resetCurrentUserInfo(SplashFragment.this.getCoreActivity());
                    }
                }, true);
                return;
            }
            SplashFragment splashFragment = SplashFragment.this;
            splashFragment.showErrorPopUp(splashFragment.getString(R.string.errorCode_alert_somethingWentWorng));
        }
    };
    private final Handler handler = new Handler();
    private Activity mActivity;
    @Inject
    Binder mBinder;
    private FragmentSplashBinding mBinding;
    private MainViewModel mMainViewModel;
    private PrivacyPolicyPresenter mPrivacyPolicyPresenter;
    @Inject
    ViewModelProviderFactory providerFactory;
    private final Observer<UserInfo> userInfoObserver = new SplashFragment$$ExternalSyntheticLambda5(this);
    /* access modifiers changed from: private */
    public UserViewModel userViewModel;

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.userViewModel = getCoreActivity().getGlobalViewModelRepository().getUserInfoViewModel();
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.userViewModel.getUserInfoSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.userInfoObserver);
        this.userViewModel.getUserInfoFailureSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.genericErrorResponseObserver);
        this.mBinding = (FragmentSplashBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_splash, viewGroup, false);
        logEvent(Screens.SCREENS.name(), 0);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initiateViewModel();
    }

    private void initiateViewModel() {
        this.mMainViewModel = (MainViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(MainViewModel.class);
    }

    private void getAppVersion() {
        MainViewModel mainViewModel;
        LiveData<Resource<AppVersion>> appVersion;
        CoreActivity coreActivity = getCoreActivity();
        if (coreActivity != null && (mainViewModel = coreActivity.getMainViewModel()) != null && (appVersion = mainViewModel.getAppVersion()) != null) {
            appVersion.removeObservers(getViewLifecycleOwner());
            appVersion.observe(getViewLifecycleOwner(), new SplashFragment$$ExternalSyntheticLambda6(this));
        }
    }

    /* renamed from: lambda$getAppVersion$0$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33280x952f9c40(Resource resource) {
        if (resource != null) {
            int i = C26305.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 2) {
                onVersionError();
            } else if (i == 3) {
                onVersionSuccess((AppVersion) resource.response);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void deRegisterDevice(String str, CallBackListener callBackListener) {
        this.mMainViewModel.deRegisterDevice(str, (String) null).removeObservers(this);
        this.mMainViewModel.deRegisterDevice(str, (String) null).observe(this, new SplashFragment$$ExternalSyntheticLambda4(callBackListener));
    }

    /* renamed from: com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment$5 */
    static /* synthetic */ class C26305 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.di.model.Resource$Status[] r0 = com.jch.racWiFi.p010di.model.Resource.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$di$model$Resource$Status = r0
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.viewImpl.SplashFragment.C26305.<clinit>():void");
        }
    }

    static /* synthetic */ void lambda$deRegisterDevice$1(CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C26305.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 2) {
                callBackListener.onFailure();
            } else if (i == 3) {
                callBackListener.onSuccess();
            }
        }
    }

    public void onStart() {
        final String str;
        super.onStart();
        this.mPrivacyPolicyPresenter = new PrivacyPolicyPresenter(this);
        Uri data = this.mActivity.getIntent().getData();
        Constants.privacyPolicyShown = false;
        Constants._INVITE_ = false;
        Constants._NEW_USER_ = false;
        if (data != null) {
            Constants.isAppOpenedFormInviteLink = true;
            if (data.getQueryParameter("invitecode") != null) {
                str = data.getQueryParameter("invitecode");
            } else if (data.getQueryParameter("inviteCode") != null) {
                str = data.getQueryParameter("inviteCode");
            } else {
                str = data.getQueryParameter("invitec0de");
            }
            if (str != null) {
                TokenResponse.Body body = (TokenResponse.Body) new Persistence().obtain(Constants.Keys.TOKEN_RESPONSE, TokenResponse.Body.class);
                if (body == null || body.getResult() == null) {
                    CallUserExistAPI(str);
                } else {
                    deRegisterDevice(body.getResult(), new CallBackListener() {
                        public void onSuccess() {
                            SplashFragment.this.CallUserExistAPI(str);
                        }

                        public void onFailure() {
                            SplashFragment.this.CallUserExistAPI(str);
                        }
                    });
                }
            } else {
                onInternetConnectionCheck(NetworkConnectivity.isNetworkAvailable(requireActivity()));
            }
        } else {
            UserRegistrationModels.UserRegistration.NEW_USER.inviteBy = "OWNER";
            UserRegistrationModels.UserRegistration.NEW_USER.role = "OWNER";
            onInternetConnectionCheck(NetworkConnectivity.isNetworkAvailable(requireActivity()));
        }
        this.mFragmentToActivityCallback.changeStatusBarColor(R.color.colorRed);
    }

    /* access modifiers changed from: private */
    public void CallUserExistAPI(String str) {
        com.jch.racWiFi.Constants.INVITE_CODE = str;
        TokenUtil.getInstance().clear();
        com.jch.racWiFi.Constants._INVITE_ = true;
        InviteVerificationModels.InviteVerificationRequestData inviteVerificationRequestData = new InviteVerificationModels.InviteVerificationRequestData();
        inviteVerificationRequestData.token = str;
        new UserExistanceCheckForInviteCodeNetworkDispatcher().userExists(inviteVerificationRequestData, new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (!((InviteVerificationModels.InviteVerificationSuccessResponse) new Gson().fromJson(response.body() != null ? response.body().charStream() : null, InviteVerificationModels.InviteVerificationSuccessResponse.class)).doesExist) {
                        com.jch.racWiFi.Constants._NEW_USER_ = true;
                    }
                }
                SplashFragment splashFragment = SplashFragment.this;
                splashFragment.onInternetConnectionCheck(NetworkConnectivity.isNetworkAvailable(splashFragment.requireActivity()));
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                com.jch.racWiFi.Constants._NEW_USER_ = false;
                SplashFragment splashFragment = SplashFragment.this;
                splashFragment.onInternetConnectionCheck(NetworkConnectivity.isNetworkAvailable(splashFragment.requireActivity()));
            }
        });
    }

    public void onStop() {
        super.onStop();
        this.mPrivacyPolicyPresenter.removeCallbacks();
        this.handler.removeCallbacksAndMessages((Object) null);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBinder.unBind();
    }

    public void onNetworkCallFailure(Throwable th) {
        if (th instanceof UnknownHostException) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
            singleChoiceDialog.setTitleString(this.mActivity.getString(R.string.common_alert));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setMessageString(this.mActivity.getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setPositiveButton(this.mActivity.getString(R.string.onboard_btn_retry), new SplashFragment$$ExternalSyntheticLambda8(this, singleChoiceDialog));
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                singleChoiceDialog.show();
            }
        } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
        }
    }

    /* renamed from: lambda$onNetworkCallFailure$3$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ boolean mo33287xe225422d(SingleChoiceDialog singleChoiceDialog, Dialog dialog, View view) {
        com.jch.racWiFi.Constants.NOT_TO_RESTART = true;
        singleChoiceDialog.dismiss();
        new Handler().postDelayed(new SplashFragment$$ExternalSyntheticLambda2(this), 200);
        return false;
    }

    /* renamed from: lambda$onNetworkCallFailure$2$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33286x27afa1ac() {
        this.mFragmentToActivityCallback.reCreateUserManagementActivity();
    }

    private void launchAppropriateActivity() {
        if (PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
            PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED = false;
        } else if (TokenUtil.getInstance().isValid()) {
            com.jch.racWiFi.Constants._FORCE_LOGOUT_ = true;
            this.mFragmentToActivityCallback.moveToHomePageActivity(false, false);
        } else if (getCoreActivity().getSessionManagerInstance().isDemoMode()) {
            this.mFragmentToActivityCallback.moveToHomePageActivity(false, false);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
        }
    }

    private void onVersionSuccess(AppVersion appVersion) {
        AppVersion.Body body;
        AppVersion.Body.Result result;
        if (appVersion != null && (body = appVersion.getBody()) != null && (result = body.getResult()) != null) {
            String latestVersion = result.getLatestVersion();
            String minimumVersion = result.getMinimumVersion();
            String storeLink = result.getStoreLink();
            if (latestVersion == null || latestVersion.isEmpty() || minimumVersion == null || minimumVersion.isEmpty() || storeLink == null || storeLink.isEmpty()) {
                onVersionError();
            } else if (new Version(Constants.CC.getVersion("3.0.9")).isLowerThan(new Version(minimumVersion))) {
                AppVersionDialog appVersionDialog = new AppVersionDialog(this.mActivity);
                appVersionDialog.setParentView(this.mBinding.parentLayout);
                appVersionDialog.setOnCancelListener(new SplashFragment$$ExternalSyntheticLambda0(this));
                appVersionDialog.setMandatory(new SplashFragment$$ExternalSyntheticLambda3(this, storeLink), latestVersion);
                appVersionDialog.show();
            } else {
                checkForPrivacyPolicyUpdate();
            }
        }
    }

    /* renamed from: lambda$onVersionSuccess$4$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33289x838731bb(DialogInterface dialogInterface) {
        logEvents(Events.CANCEL_UPDATE.name(), 0);
    }

    /* renamed from: lambda$onVersionSuccess$5$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33290x3dfcd23c(String str, View view) {
        getCoreActivity().openPlayStore(str);
        this.mFragmentToActivityCallback.finishUserManagementActivity();
        logEvents(Events.MANUAL_UPDATE.name(), 0);
    }

    private void onVersionError() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(this.mActivity.getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(this.mActivity.getString(R.string.appLaunch_alert_serversAreDown));
        singleChoiceDialog.setPositiveButton(this.mActivity.getString(R.string.common_btn_ok), new SplashFragment$$ExternalSyntheticLambda9(this, singleChoiceDialog));
        singleChoiceDialog.show();
    }

    /* renamed from: lambda$onVersionError$6$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ boolean mo33288x546b4b02(SingleChoiceDialog singleChoiceDialog, Dialog dialog, View view) {
        singleChoiceDialog.dismiss();
        this.mFragmentToActivityCallback.finishUserManagementActivity();
        return false;
    }

    private void checkForPrivacyPolicyUpdate() {
        this.mPrivacyPolicyPresenter.checkForPrivacyPolicyUpdate(this);
    }

    public void onPrivacyPolicyReceived(PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse, int i) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return;
        }
        if (i != 200) {
            PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED = true;
            privacyPolicyDataResponse.privacyPolicyData[0].persist();
            launchAppropriateActivity();
        } else if (getCoreActivity().getSessionManagerInstance().isDemoMode()) {
            this.mFragmentToActivityCallback.moveToHomePageActivity(false, false);
        } else if (TokenUtil.getInstance().isValid()) {
            this.userViewModel.fetchUserInfo(requireActivity());
        } else if (CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation()) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_splashFragment_to_loginFragment);
        }
    }

    /* renamed from: lambda$new$7$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33281x4a78667a(UserInfo userInfo) {
        String str = userInfo.settingsData.temperatureUnit;
        if (str != null) {
            TemperatureUnit.CURRENT = TemperatureUnit.getEnumFromServerConstant(str);
        }
        UserInfo.getCurrentUserInfo(getCoreActivity()).copy(userInfo);
        launchAppropriateActivity();
    }

    public void onPrivacyPolicyReceivedFailure() {
        launchAppropriateActivity();
    }

    public void onInternetConnectionCheck(boolean z) {
        if (!z) {
            new Handler().postDelayed(new SplashFragment$$ExternalSyntheticLambda11(this), 1000);
        } else if (LanguageSelectionDialog.Persistence.checkIfNeededToShowLanguageSelectionDialog()) {
            LanguageSelectionDialog languageSelectionDialog = new LanguageSelectionDialog(this.mActivity);
            languageSelectionDialog.hideCloseButton();
            languageSelectionDialog.setLanguageSelectedApplyListener(new SplashFragment$$ExternalSyntheticLambda10(this, languageSelectionDialog));
            languageSelectionDialog.setCancelable(false);
            languageSelectionDialog.setParentViewSplash(this.mBinding.parentLayout);
            languageSelectionDialog.show();
        } else {
            handlePlannedMaintenance();
        }
    }

    /* renamed from: lambda$onInternetConnectionCheck$8$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33284x3a669205(LanguageSelectionDialog languageSelectionDialog, LanguageModel languageModel) {
        LocaleConfiguration.persistLocalization(languageModel.getLocale());
        LanguageSelectionDialog.Persistence.setDoNotShowOnNextStartUp();
        com.jch.racWiFi.Constants.NOT_TO_RESTART = true;
        requireActivity().recreate();
        this.mFragmentToActivityCallback.reCreateUserManagementActivity();
        languageSelectionDialog.dismiss();
        handlePlannedMaintenance();
        getAppVersion();
    }

    /* renamed from: lambda$onInternetConnectionCheck$11$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33283x123e463b() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(this.mActivity.getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(this.mActivity.getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setPositiveButton(this.mActivity.getString(R.string.onboard_btn_retry), new SplashFragment$$ExternalSyntheticLambda7(this, singleChoiceDialog));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$onInternetConnectionCheck$10$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ boolean mo33282x57c8a5ba(SingleChoiceDialog singleChoiceDialog, Dialog dialog, View view) {
        com.jch.racWiFi.Constants.NOT_TO_RESTART = true;
        singleChoiceDialog.dismiss();
        new Handler().postDelayed(new SplashFragment$$ExternalSyntheticLambda1(this), 200);
        return false;
    }

    /* renamed from: lambda$onInternetConnectionCheck$9$com-jch-racWiFi-userManagement-view-viewImpl-SplashFragment */
    public /* synthetic */ void mo33285xf4dc3286() {
        this.mFragmentToActivityCallback.reCreateUserManagementActivity();
    }

    /* access modifiers changed from: private */
    public void handlePlannedMaintenance() {
        Maintenance maintenance = (Maintenance) new Persistence().obtain(Maintenance.SP_KEY, Maintenance.class);
        if (maintenance == null) {
            getAppVersion();
        } else {
            showPlannedMaintenance(maintenance);
        }
    }

    private void showPlannedMaintenance(Maintenance maintenance) {
        View root = this.mBinder.getBanner(maintenance, (BannerListener<Maintenance, BannerPlannedMaintenanceBinding>) new BannerListener<Maintenance, BannerPlannedMaintenanceBinding>() {
            public void onItemClick(View view, Maintenance maintenance, BannerPlannedMaintenanceBinding bannerPlannedMaintenanceBinding) {
            }

            public void onClick(View view, Maintenance maintenance, BannerPlannedMaintenanceBinding bannerPlannedMaintenanceBinding) {
                maintenance.clear();
                SplashFragment.this.handlePlannedMaintenance();
            }
        }).getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mBinding.bannerPlannedMaintenance.addView(root);
    }
}
