package com.jch.racWiFi.iduOnBoarding.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Config;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.LocationUpdatesBroadcast;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customWidgets.SearchLayout;
import com.jch.racWiFi.dataClasses.Country;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil.IMDnsDiscovery;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil.MDnsDiscovery;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil.MDnsDiscoveryAndroid6;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.presenter.SoftApPresenterImpl;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.view.IOnboardingView;
import com.jch.racWiFi.userOnboarding.view.uiComponents.dialog.IduRenameDialogNew;
import com.jch.racWiFi.userOnboarding.view.viewImpl.DevicesAdapter;
import com.jch_hitachi.aircloudglobal.R;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class ConfiguringDeviceCheckMdns extends GenericFragment implements IOnboardingView, LifecycleOwner, NsdManager.DiscoveryListener, LocationUpdatesBroadcast.ILocationChangeUpdates {
    private static final String TAG = "ConfiguringDeviceCheckMdns";
    private boolean androidM = false;
    private final Handler gpsProviderHandler = new Handler();
    /* access modifiers changed from: private */
    public IduRenameDialogNew iduRenameDialog;
    private boolean isApiCallDone;
    private boolean isReqBodyMethodCalled;
    private String[] locationPermissionArr;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public DevicesAdapter mAdapter;
    private Handler mHandler;
    private LocationUpdatesBroadcast mLocationUpdatesBroadcast;
    private IMDnsDiscovery mMDnsDiscovery;
    private WifiManager.MulticastLock mMulticastLock;
    private OnBoardingModel mOnBoardingModel;
    @BindView(2131363530)
    View mParent;
    private long mStartTime;
    private final CountDownTimer mTimerForAlwaysOnDisplay = new CountDownTimer(Long.MAX_VALUE, 5000) {
        public void onFinish() {
        }

        public void onTick(long j) {
            View view;
            if (ConfiguringDeviceCheckMdns.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && (view = ConfiguringDeviceCheckMdns.this.getView()) != null) {
                view.performClick();
            }
        }
    };
    private Unbinder mUnbinder;
    private final Handler mValidConnectionCheckHandler = new Handler();
    private final Handler mWaitForMDNSHandler = new Handler();
    private final Handler networkProviderHandler = new Handler();
    /* access modifiers changed from: private */
    public String racPasssword = "";
    private TextWatcher searchTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ConfiguringDeviceCheckMdns.this.mAdapter.getFilter().filter(charSequence);
            new Handler().postDelayed(new ConfiguringDeviceCheckMdns$5$$ExternalSyntheticLambda0(this, charSequence), 200);
        }

        /* renamed from: lambda$onTextChanged$0$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns$5 */
        public /* synthetic */ void mo31409xf465c775(CharSequence charSequence) {
            if (ConfiguringDeviceCheckMdns.this.mAdapter.deviceListFiltered.size() == 0) {
                ConfiguringDeviceCheckMdns.this.iduRenameDialog.cardView.setVisibility(8);
            } else {
                ConfiguringDeviceCheckMdns.this.iduRenameDialog.cardView.setVisibility(0);
            }
            if (charSequence.toString().length() == 0) {
                ConfiguringDeviceCheckMdns.this.iduRenameDialog.cardView.setVisibility(0);
            }
        }
    };
    private SoftApPresenterImpl softApPresenter;

    private void showToastMessage(String str) {
    }

    public void onDiscoveryStarted(String str) {
    }

    public void onDiscoveryStopped(String str) {
    }

    public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
    }

    public void onStartDiscoveryFailed(String str, int i) {
    }

    public void onStopDiscoveryFailed(String str, int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.mOnBoardingModel = new OnBoardingModel();
        this.locationPermissionArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION};
        this.mLocationUpdatesBroadcast = new LocationUpdatesBroadcast(this);
        if (Build.VERSION.SDK_INT == 23) {
            this.mMDnsDiscovery = new MDnsDiscoveryAndroid6(getCoreActivity(), Config.MDNS_SERVICE_TYPE, this);
            this.androidM = true;
        } else {
            this.mMDnsDiscovery = new MDnsDiscovery(getCoreActivity(), Config.MDNS_SERVICE_TYPE, this);
        }
        WifiManager.MulticastLock createMulticastLock = getCoreActivity().getWifiManager().createMulticastLock("multicastLock");
        this.mMulticastLock = createMulticastLock;
        createMulticastLock.setReferenceCounted(true);
        this.mMulticastLock.acquire();
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        this.mLocationUpdatesBroadcast.registerLocationBroadcastReceiver(getActivity());
        IduRenameDialogNew iduRenameDialogNew = new IduRenameDialogNew(requireActivity());
        this.iduRenameDialog = iduRenameDialogNew;
        iduRenameDialogNew.setParentView(this.mParent);
        this.iduRenameDialog.setCancelable(false);
        this.iduRenameDialog.setCanceledOnTouchOutside(false);
        this.iduRenameDialog.toShowAddAcButton(true);
        new SearchLayout(this.iduRenameDialog.layout).addTextWatcher(this.searchTextWatcher);
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(NavigationTransitionKeyValues.SMART_OR_SOFT_AP_CHOSEN);
        boolean booleanValue = navArgument != null ? ((Boolean) navArgument.getDefaultValue()).booleanValue() : false;
        String adapterType = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getAdapterType();
        if (booleanValue || adapterType.equals("2")) {
            view = layoutInflater.inflate(R.layout.connecting_to_wifi_network_frame, viewGroup, false);
        } else {
            view = layoutInflater.inflate(R.layout.configuring_progress_frame, viewGroup, false);
        }
        this.mTimerForAlwaysOnDisplay.start();
        this.softApPresenter = new SoftApPresenterImpl(getViewLifecycleOwner(), this);
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.TO_ON_BOARDED_DIRECTLY);
            if (navArgument == null || !((Boolean) navArgument.getDefaultValue()).booleanValue()) {
                validateAndSetHandler();
            } else {
                setOnBoardingReqModel(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid());
            }
        } else {
            setOnBoardingReqModel(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid());
        }
    }

    private void validateAndSetHandler() {
        this.mValidConnectionCheckHandler.removeCallbacksAndMessages((Object) null);
        this.mValidConnectionCheckHandler.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda1(this), com.jch.racWiFi.Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT);
    }

    /* renamed from: lambda$validateAndSetHandler$1$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31390xf85e1982() {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(getActivity());
        if (currentSsid == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(com.jch.racWiFi.Constants.WIFI_NETWORK_NOT_CONNECTED, true);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_connectBackToHomeRouterFragment, bundle);
        } else if (!currentSsid.replaceAll("^\"|\"$", "").equals(WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid())) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_connectBackToHomeRouterFragment);
        } else {
            this.mWaitForMDNSHandler.removeCallbacksAndMessages((Object) null);
            this.mWaitForMDNSHandler.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda11(this), com.jch.racWiFi.Constants.SCHEDULE_TIME_OUT_MDNS);
        }
    }

    /* renamed from: lambda$validateAndSetHandler$0$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31389xbf7db8e3() {
        Logger.m45d(TAG, " mdns time-out... ");
        showToastMessage(" mdns time-out... ");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
    }

    private void screenNavigationInTestMode() {
        new Handler().postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda8(this), 2000);
    }

    /* renamed from: lambda$screenNavigationInTestMode$2$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31382x1c90b296() {
        this.mFragmentToActivityCallback.getNavController().navigate(new int[]{R.id.action_configuringPageFragment_to_successFragment, R.id.action_configuringDeviceCheckMdns_to_connectBackToHomeRouterFragment, R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange}[1]);
    }

    public void onResume() {
        super.onResume();
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            Handler handler = new Handler();
            this.mHandler = handler;
            handler.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda6(this), 1000);
            return;
        }
        Handler handler2 = new Handler();
        this.mHandler = handler2;
        handler2.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda7(this), 7000);
    }

    /* renamed from: lambda$onResume$3$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31380xf4909823() {
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceName("JCH");
        onServiceFound(nsdServiceInfo);
    }

    /* renamed from: lambda$onResume$4$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31381x2d70f8c2() {
        this.mMDnsDiscovery.startDiscovery();
    }

    public void onPause() {
        super.onPause();
        this.mMDnsDiscovery.stopDiscovery();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
        this.mLocationUpdatesBroadcast.unregisterLocationBroadcastReceiver(getActivity());
        this.mTimerForAlwaysOnDisplay.cancel();
    }

    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        WifiManager.MulticastLock multicastLock = this.mMulticastLock;
        if (multicastLock != null) {
            multicastLock.release();
            this.mMulticastLock = null;
        }
        Handler handler2 = this.networkProviderHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        Handler handler3 = this.gpsProviderHandler;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages((Object) null);
        }
        this.mValidConnectionCheckHandler.removeCallbacksAndMessages((Object) null);
        this.mWaitForMDNSHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onNetworkError() {
        showToastMessage("onNetworkError");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
        Logger.m45d(TAG, "onboarding api onNetworkError");
    }

    public void onOnboardingSuccessful(IduDetailsModel iduDetailsModel) {
        showToastMessage("onOnboardingSuccessful");
        Logger.m45d(TAG, "onboarding api onOnboardingSuccessful");
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (iduDetailsModel != null) {
                this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.IDU_DETAILS_KEY, new NavArgument.Builder().setDefaultValue(iduDetailsModel).build());
            }
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringPageFragment_to_successFragment, getArguments());
        }
        logEvents(Events.DETECT_DEVICE.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTime));
    }

    public void onAlreadyOnboarded() {
        Logger.m45d(TAG, "onboarding api onAlreadyOnboarded");
    }

    public void onPerformingOnboarding() {
        Logger.m45d(TAG, "onboarding api onPerformingOnboarding");
    }

    public void onboardingFailed() {
        showToastMessage("onboardingFailed");
        Logger.m45d(TAG, "onboarding api onboardingFailed");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
    }

    public void onBoardingErrorCode(int i) {
        String str = TAG;
        Logger.m45d(str, "onboarding api onBoardingErrorCode " + i);
        showToastMessage("onboarding api : " + i);
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    ConfiguringDeviceCheckMdns.this.onBoardApiCall();
                }
            }, false);
            return;
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
    }

    public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
        if (nsdServiceInfo.getServiceName().startsWith("JCH")) {
            final String replaceAll = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid().replaceAll("^\"|\"$", "");
            if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
                setOnBoardingReqModel(replaceAll);
            } else if (nsdServiceInfo.getServiceName().equals(replaceAll)) {
                boolean z = false;
                NavController navController = this.mFragmentToActivityCallback.getNavController();
                if (navController == null) {
                    navController = Navigation.findNavController(getCoreActivity(), R.id.nav_host_fragment);
                }
                NavArgument navArgument = navController.getGraph().getArguments().get(NavigationTransitionKeyValues.WPS_WITHOUT_QR_CODE);
                if (navArgument != null) {
                    z = ((Boolean) navArgument.getDefaultValue()).booleanValue();
                }
                if (!z) {
                    setOnBoardingReqModel(replaceAll);
                } else if (this.androidM) {
                    byte[] bArr = nsdServiceInfo.getAttributes().get("password");
                    if (bArr != null) {
                        String str = new String(bArr, StandardCharsets.UTF_8);
                        this.racPasssword = str;
                        if (!str.isEmpty()) {
                            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword(this.racPasssword);
                        } else {
                            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword("");
                        }
                    } else {
                        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword("");
                    }
                    setOnBoardingReqModel(replaceAll);
                } else {
                    this.mMDnsDiscovery.getNsdManager().resolveService(nsdServiceInfo, new NsdManager.ResolveListener() {
                        public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {
                        }

                        public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
                            byte[] bArr = nsdServiceInfo.getAttributes().get("password");
                            if (bArr != null) {
                                ConfiguringDeviceCheckMdns.this.racPasssword = new String(bArr, StandardCharsets.UTF_8);
                                if (!ConfiguringDeviceCheckMdns.this.racPasssword.isEmpty()) {
                                    WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword(ConfiguringDeviceCheckMdns.this.racPasssword);
                                } else {
                                    WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword("");
                                }
                            } else {
                                WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword("");
                            }
                            ConfiguringDeviceCheckMdns.this.setOnBoardingReqModel(replaceAll);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void setOnBoardingReqModel(String str) {
        Logger.m45d(TAG, " mdns received ..");
        showToastMessage("mdns received ..");
        if (!this.isReqBodyMethodCalled) {
            this.isReqBodyMethodCalled = true;
            this.mValidConnectionCheckHandler.removeCallbacksAndMessages((Object) null);
            this.mWaitForMDNSHandler.removeCallbacksAndMessages((Object) null);
            this.mOnBoardingModel.setVendorThingId(str);
            this.mOnBoardingModel.setThingPassword(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getPassword());
            this.mOnBoardingModel.setName(str);
            this.mOnBoardingModel.setAdapterType(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getAdapterType());
            this.mOnBoardingModel.setRouterSSID(WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid());
            if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
                getCountryCodeFromMyAddress();
            } else {
                this.mActivity.runOnUiThread(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda2(this));
            }
        }
    }

    /* access modifiers changed from: private */
    public void showSelectCountryDialog() {
        Country country = Constants.CC.getCountry();
        if (!CountryUtils.ProhibitedCountryUtils.isCountryProhibited(country.getName())) {
            getCurrentLocation();
            return;
        }
        this.mOnBoardingModel.setCountry(country.getCode());
        selectIduNameDialog();
    }

    private void selectIduNameDialog() {
        this.iduRenameDialog.tvIduVendorThingID.setText(getString(R.string.manageAc_lbl_acIdShort) + " " + this.mOnBoardingModel.getVendorThingId());
        String[] split = requireActivity().getResources().getString(R.string.manageAc_acNames).split(",");
        ArrayList arrayList = new ArrayList();
        this.mAdapter = new DevicesAdapter(arrayList, new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda5(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        Collections.addAll(arrayList, split);
        this.iduRenameDialog.mRecyclerViewDeviceName.setLayoutManager(linearLayoutManager);
        this.iduRenameDialog.mRecyclerViewDeviceName.setItemAnimator(new DefaultItemAnimator());
        this.iduRenameDialog.mRecyclerViewDeviceName.setAdapter(this.mAdapter);
        this.iduRenameDialog.newIduNameField.setOnFocusChangeListener(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda3(this));
        this.iduRenameDialog.newIduNameField.setOnEditorActionListener(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda4(this));
        this.iduRenameDialog.addRacNameButton.setOnClickListener(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda0(this));
        this.iduRenameDialog.show();
    }

    /* renamed from: lambda$selectIduNameDialog$5$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31383x7aa63416(String str) {
        this.iduRenameDialog.newIduNameField.removeTextChangedListener(this.searchTextWatcher);
        this.iduRenameDialog.newIduNameField.setText(str);
        this.iduRenameDialog.newIduNameField.addTextChangedListener(this.searchTextWatcher);
        this.iduRenameDialog.cardView.setVisibility(8);
    }

    /* renamed from: lambda$selectIduNameDialog$6$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31384xb38694b5(View view, boolean z) {
        if (z) {
            this.iduRenameDialog.cardView.setVisibility(0);
        } else {
            this.iduRenameDialog.cardView.setVisibility(8);
        }
    }

    /* renamed from: lambda$selectIduNameDialog$7$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ boolean mo31385xec66f554(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        this.iduRenameDialog.cardView.setVisibility(8);
        this.iduRenameDialog.newIduNameField.clearFocus();
        return false;
    }

    /* renamed from: lambda$selectIduNameDialog$8$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31386x254755f3(View view) {
        String trim = this.iduRenameDialog.newIduNameField.getText().toString().trim();
        if (trim.isEmpty()) {
            showErrorPopUp(getString(R.string.manageAc_alert_enterAcNameDesc));
        } else if (!isIduAlreadyPresent(trim)) {
            showErrorPopUp(getString(R.string.manageAc_alert_acNameAlreadyExists));
        } else {
            this.iduRenameDialog.dismiss();
            this.mOnBoardingModel.setRacName(trim);
            onBoardingValidation();
        }
    }

    private void getCurrentLocation() {
        if (checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
        } else {
            requestPermissions(this.locationPermissionArr, 177);
        }
    }

    private void checkForLocationEnabledOrNot() {
        if (!getCoreActivity().isLocationEnabled()) {
            enableLocationDialog(this, (NavController) null);
        } else {
            autoDetectLocation();
        }
    }

    private void autoDetectLocation() {
        if (!checkPermission()) {
            Location lastKnownLocation = getCoreActivity().getLocationManager().getLastKnownLocation("network");
            if (lastKnownLocation != null) {
                updateAddressData(lastKnownLocation);
                return;
            }
            getCoreActivity().startBroadcastNetworkLocation(1000);
            setFetchLocationTimeOutHandler();
        }
    }

    private void setFetchLocationTimeOutHandler() {
        this.networkProviderHandler.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda9(this), 15000);
    }

    /* renamed from: lambda$setFetchLocationTimeOutHandler$10$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31387x562ab42b() {
        getCoreActivity().stopLocationBroadcast();
        if (!checkPermission()) {
            Location lastKnownLocation = getCoreActivity().getLocationManager().getLastKnownLocation("gps");
            if (lastKnownLocation != null) {
                updateAddressData(lastKnownLocation);
                return;
            }
            getCoreActivity().startBroadcastGPSLocation(1000);
            this.gpsProviderHandler.postDelayed(new ConfiguringDeviceCheckMdns$$ExternalSyntheticLambda10(this), 60000);
        }
    }

    /* renamed from: lambda$setFetchLocationTimeOutHandler$9$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceCheckMdns */
    public /* synthetic */ void mo31388xfe5d7bd3() {
        getCoreActivity().stopLocationBroadcast();
        getCountryCodeFromMyAddress();
    }

    private boolean checkPermission() {
        return (ActivityCompat.checkSelfPermission(this.mActivity, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(this.mActivity, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION) == 0) ? false : true;
    }

    public void onLocationChangedFromBroadcast(Location location) {
        String str = TAG;
        Logger.m45d(str, "onLocationChanged " + location.getProvider());
        updateAddressData(location);
        getCoreActivity().stopLocationBroadcast();
    }

    private void updateAddressData(Location location) {
        if (location != null) {
            Geocoder geocoder = new Geocoder(this.mActivity);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String str = TAG;
            Logger.m45d(str, " lat = " + latitude + " long = " + longitude);
            try {
                ArrayList arrayList = new ArrayList(geocoder.getFromLocation(latitude, longitude, 1));
                if (!arrayList.isEmpty()) {
                    Address address = (Address) arrayList.get(0);
                    if (address.getCountryCode() == null || address.getCountryCode().isEmpty()) {
                        getCountryCodeFromMyAddress();
                        return;
                    }
                    showToastMessage("Location Received");
                    this.mOnBoardingModel.setCountry(address.getCountryCode());
                    this.mOnBoardingModel.setCity(address.getLocality());
                    this.mOnBoardingModel.setZipCode(address.getPostalCode());
                    selectIduNameDialog();
                    return;
                }
                getCountryCodeFromMyAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getCountryCodeFromMyAddress() {
        String countryCode = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
        if (countryCode == null || countryCode.isEmpty()) {
            showAlertPopUp(getString(R.string.common_alert_unableFetchLocation));
            showToastMessage(getString(R.string.common_alert_unableFetchLocation));
            return;
        }
        this.mOnBoardingModel.setCountry(countryCode);
        this.mOnBoardingModel.setCity(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCity());
        this.mOnBoardingModel.setZipCode(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getZipCode());
        selectIduNameDialog();
    }

    private /* synthetic */ boolean lambda$showAlertPopUp$11(Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
        return true;
    }

    private void showAlertPopUp(String str) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
    }

    private void onBoardingValidation() {
        String vendorThingId = this.mOnBoardingModel.getVendorThingId();
        String thingPassword = this.mOnBoardingModel.getThingPassword();
        String name = this.mOnBoardingModel.getName();
        String country = this.mOnBoardingModel.getCountry();
        String adapterType = this.mOnBoardingModel.getAdapterType();
        String routerSSID = this.mOnBoardingModel.getRouterSSID();
        String zoneId = this.mOnBoardingModel.getZoneId();
        if (vendorThingId.isEmpty() || name.isEmpty()) {
            showAlertPopUp("Vendor Thing Id or Name Can not be null");
        } else if (thingPassword.isEmpty()) {
            showAlertPopUp("Thing Password Can not be null");
        } else if (country.isEmpty()) {
            showAlertPopUp("Country Can not be null");
        } else if (adapterType.isEmpty()) {
            showAlertPopUp("Adapter Type Can not be null");
        } else if (routerSSID.isEmpty()) {
            showAlertPopUp("Router SSID Can not be null");
        } else if (zoneId.isEmpty()) {
            showAlertPopUp("Zone Id Can not be null");
        } else {
            onBoardApiCall();
        }
    }

    /* access modifiers changed from: private */
    public void onBoardApiCall() {
        Handler handler = this.networkProviderHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        Handler handler2 = this.gpsProviderHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        if (!this.isApiCallDone) {
            this.isApiCallDone = true;
            showToastMessage("on-board api starts ..");
            Logger.m45d(TAG, " on-board api starts");
            this.mStartTime = System.currentTimeMillis();
            this.softApPresenter.onBoardingApi(this.mOnBoardingModel);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if ((i == 132 || i == 147) && checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 177) {
            return;
        }
        if (checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
            return;
        }
        for (String str : strArr) {
            if (!shouldShowRequestPermissionRationale(str)) {
                showPermissionSettingDialog(str, this, (NavController) null);
            } else {
                showPermissionDeniedDialog(this.mParent, str, (NavController) null);
            }
        }
    }
}
