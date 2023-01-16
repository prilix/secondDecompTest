package com.jch.racWiFi.iduManagement.smartFence.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ManufacturerListUtil;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.SmartfenceFragmentBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceData;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlsStateModel;
import com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSetModeTempDilog;
import com.jch.racWiFi.iduManagement.smartFence.viewModels.GetAllUsersViewModel;
import com.jch.racWiFi.main.model.CountryUnit;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SmartFenceFragment extends GenericFragment implements View.OnClickListener {
    public static boolean isAnySettingsChanged = false;
    public static ArrayList<OperationMode> modeArrayListForRecyclerViewCopy = new ArrayList<>();
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolder;
    private FamilyIdGeoFenceDataMap familyIdGeoFenceDataMapOld;
    /* access modifiers changed from: private */
    public GetAllUsersViewModel getAllUsersViewModel;
    private boolean isClickedOnHamBurger = false;
    private boolean isEnableButtonClicked = false;
    private boolean isFirstTime = true;
    private LocationPermissionViewModel locationPermissionViewModel;
    SmartfenceFragmentBinding mBinding;
    /* access modifiers changed from: private */
    public CoreActivity mCoreActivity;
    /* access modifiers changed from: private */
    public GeoFencePair mGeoFencePair;
    private MutableLiveData<FamilyIdGeoFenceDataMap> mGeoFencePairLiveData;
    /* access modifiers changed from: private */
    public boolean mIsLocationRequested;
    /* access modifiers changed from: private */
    public final LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            SmartFenceFragment.this.mCoreActivity.removeLocationUpdates(SmartFenceFragment.this.mLocationCallback);
            if (SmartFenceFragment.this.mBinding.switchMainHome.isChecked()) {
                SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(SmartFenceFragment.this.getViewLifecycleOwner());
                SmartFenceFragment.this.showUiSetting();
            } else if (SmartFenceFragment.this.mIsLocationRequested) {
                SmartFenceFragment.this.mIsLocationRequested = false;
                SmartFenceFragment.this.mBinding.switchMainHome.setChecked(true);
            }
            SmartFenceFragment.this.dismissPleaseWaitDialog();
        }
    };
    /* access modifiers changed from: private */
    public Scenario mScenario;
    private final ArrayList<OperationMode> modeArrayListForRecyclerView = new ArrayList<>();
    private final LocationControlsStateModel newLocationControlsStateModel = new LocationControlsStateModel(false);
    private final LocationControlsStateModel oldLocationControlsStateModel = new LocationControlsStateModel(false);
    private final List<String> racListByCloudId = new ArrayList();
    private final List<Long> racListByRacId = new ArrayList();
    Observer<Boolean> racListChangedObserver = new Observer<Boolean>() {
        public void onChanged(Boolean bool) {
            SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacListChangedSingleLiveEvent().removeObserver(SmartFenceFragment.this.racListChangedObserver);
        }
    };
    /* access modifiers changed from: private */
    public final Map<String, List<RacModelWiseData.RacModeDetail>> racListCloudIDToModeMap = new HashMap();

    private boolean enableSaveModeTempConditions(boolean z, String str, boolean z2, String str2) {
        if (z && str != null && z2 && str2 != null) {
            return true;
        }
        if (z && str != null && !z2) {
            return true;
        }
        if (z || !z2 || str2 == null) {
            return !z2 && !z;
        }
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CoreActivity coreActivity = getCoreActivity();
        this.mCoreActivity = coreActivity;
        if (coreActivity != null && !NetworkConnectivity.isNetworkAvailable(coreActivity)) {
            showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.common_alert_unableToConnectToNw));
        }
        CoreActivity coreActivity2 = this.mCoreActivity;
        if (coreActivity2 != null) {
            coreActivity2.mLocationRationaleMutableLiveData.postValue(null);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SmartfenceFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.smartfence_fragment, viewGroup, false);
        if (this.isFirstTime) {
            this.isFirstTime = false;
            this.mGeoFencePairLiveData = this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getAllGeoFences(getViewLifecycleOwner());
            this.familyIdGeoFenceDataMapOld = this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().parcelClone();
        }
        return this.mBinding.getRoot();
    }

    private void commonTask() {
        this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacListChangedSingleLiveEvent().observeForever(this.racListChangedObserver);
        calculateAndAddRacForGeoFenceNotNullV2();
        if (this.mGeoFencePair.racListChanged) {
            this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().resetSettings();
            this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().resetSettings();
            this.mGeoFencePair.racListChanged = false;
        }
        this.oldLocationControlsStateModel.enabled = this.mGeoFencePair.isEnabled;
        this.mBinding.switchMainHome.setChecked(this.oldLocationControlsStateModel.enabled);
        if (!this.mBinding.switchMainHome.isChecked()) {
            hideUiSettings();
        } else if (Constants.IS_DEMO_MODE) {
            showUiSetting();
        } else if (!this.mCoreActivity.isForeGroundLocationPermissionGranted() || !this.mCoreActivity.isBackGroundLocationPermissionGranted()) {
            this.locationPermissionViewModel.needsResolution(new AlertListener() {
                public void onPositive() {
                    if (SmartFenceFragment.this.mScenario != null && SmartFenceFragment.this.mScenario.isRationale()) {
                        SmartFenceFragment.this.mCoreActivity.handleRationale(SmartFenceFragment.this.mScenario.getMode());
                    } else if (SmartFenceFragment.this.mScenario == null || SmartFenceFragment.this.mScenario.isRationale()) {
                        SmartFenceFragment.this.mIsLocationRequested = true;
                        SmartFenceFragment.this.checkPermissions();
                    } else {
                        SmartFenceFragment.this.mCoreActivity.openSettings(SmartFenceFragment.this.mScenario.getMode());
                    }
                }

                public void onNegative() {
                    SmartFenceFragment.this.hideUiSettings();
                }
            }, false, this.mCoreActivity, LocationAccessRationale.SMART_FENCE);
        } else {
            checkPermissions();
        }
        this.mBinding.switchMainHome.setOnCheckedChangeListener(new SmartFenceFragment$$ExternalSyntheticLambda5(this));
        this.createTooltipContentHolder = new CreateTooltipContentHolder((Context) this.mCoreActivity, (View) this.mBinding.imageButtonHelp, (int) R.string.smartFence_lbl_automaticallyControlAC);
        initClickListeners();
        this.mBinding.imageButtonMenu.setOnClickListener(new SmartFenceFragment$$ExternalSyntheticLambda0(this));
        changeSaveButtonStateOnSettingsChanged();
    }

    /* renamed from: lambda$commonTask$0$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30282xefa8d244(CompoundButton compoundButton, boolean z) {
        this.mGeoFencePair.isEnabled = z;
        if (!z) {
            hideUiSettings();
        } else if (Constants.IS_DEMO_MODE) {
            showUiSetting();
        } else if (!this.mCoreActivity.isForeGroundLocationPermissionGranted() || !this.mCoreActivity.isBackGroundLocationPermissionGranted()) {
            this.locationPermissionViewModel.needsResolution(new AlertListener() {
                public void onPositive() {
                    if (SmartFenceFragment.this.mScenario != null && SmartFenceFragment.this.mScenario.isRationale()) {
                        SmartFenceFragment.this.mCoreActivity.handleRationale(SmartFenceFragment.this.mScenario.getMode());
                    } else if (SmartFenceFragment.this.mScenario == null || SmartFenceFragment.this.mScenario.isRationale()) {
                        SmartFenceFragment.this.mIsLocationRequested = true;
                        SmartFenceFragment.this.checkPermissions();
                    } else {
                        SmartFenceFragment.this.mCoreActivity.openSettings(SmartFenceFragment.this.mScenario.getMode());
                    }
                }

                public void onNegative() {
                    SmartFenceFragment.this.hideUiSettings();
                }
            }, false, this.mCoreActivity, LocationAccessRationale.SMART_FENCE);
        } else {
            checkPermissions();
        }
    }

    /* renamed from: lambda$commonTask$1$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30283x59d85a63(View view) {
        CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolder;
        if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
            this.createTooltipContentHolder.dismiss();
        }
        this.isClickedOnHamBurger = true;
        if (!this.mBinding.textViewSave.isEnabled()) {
            this.isClickedOnHamBurger = false;
            this.iDrawerMenuFunctions.openMenuDrawer();
        } else if (!this.mBinding.switchMainHome.isChecked()) {
            disableSmartFence();
        } else if (this.mGeoFencePair.getAssociatedRacs().size() > 0 && this.mGeoFencePair.getLatLng() != null) {
            boolean z = this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled;
            boolean z2 = this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled;
            if (!z && !z2) {
                handleSave();
            } else if (z && this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode == null) {
                showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseSetArrivingModeTempSettings));
            } else if (!z2 || this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode != null) {
                handleSave();
            } else {
                showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseSetLeavingModeTempSettings));
            }
        } else if (this.mGeoFencePair.getAssociatedRacs().size() <= 0) {
            showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseAddAcToEnableSmartFence));
        } else {
            showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_please_set_smart_fence_settings));
        }
    }

    /* access modifiers changed from: private */
    public void checkPermissions() {
        if (getActivity() == null || NetworkConnectivity.isNetworkAvailable(getActivity())) {
            this.mCoreActivity.checkLocationPermissions(SmartFenceFragment.class.getCanonicalName());
        } else {
            GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
        }
    }

    /* access modifiers changed from: private */
    public void checkLocationService() {
        showPleaseWaitDialog();
        CoreActivity coreActivity = this.mCoreActivity;
        coreActivity.verifyLocationService(coreActivity, this.mLocationCallback, new LocationServiceListener() {
            public void onActivityResult() {
                SmartFenceFragment.this.checkLocationService();
            }

            public void onNegative() {
                SmartFenceFragment.this.dismissPleaseWaitDialog();
                SmartFenceFragment.this.hideUiSettings();
            }
        });
    }

    private void calculateAndAddRacForGeoFenceNotNullV2() {
        this.mCoreActivity.getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduListSingleLiveEvent().observeForever(new SmartFenceFragment$$ExternalSyntheticLambda7(this));
    }

    /* renamed from: lambda$calculateAndAddRacForGeoFenceNotNullV2$2$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30278x991bc2e1(IduList iduList) {
        this.mGeoFencePair.getAssociatedRacCloudId().clear();
        if (iduList.isEmpty()) {
            this.mGeoFencePair.getAssociatedRacs().clear();
        }
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (this.mGeoFencePair.getAssociatedRacs().contains(Long.valueOf(detailedIduModel.f454id.longValue()))) {
                this.mGeoFencePair.getAssociatedRacCloudId().add(detailedIduModel.cloudId);
            }
        }
        this.modeArrayListForRecyclerView.clear();
        this.modeArrayListForRecyclerView.addAll(commonModeSelectedV2(this.mGeoFencePair));
        if (this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode != null && this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode.equals("AUTO")) {
            setTempAfterRacsLoaded(this.mGeoFencePair.getArrivingGeoFence(), this.mBinding.tempTextViewArriving);
        }
        if (this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode != null && this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode.equals("AUTO")) {
            setTempAfterRacsLoaded(this.mGeoFencePair.getLeavingGeoFence(), this.mBinding.tempTextViewLeaving);
        }
        enableDisableArrivingButton();
        enableDisableLeavingButton();
        if (this.mGeoFencePair.getAssociatedRacs().isEmpty()) {
            this.mBinding.textViewAllAcs.setText(this.mCoreActivity.getString(R.string.home_lbl_noAcsFound));
            this.mBinding.allFeatureLayout.setVisibility(4);
            this.mBinding.layoutAddAc.setVisibility(0);
            this.mBinding.textViewSave.setVisibility(4);
            this.mBinding.textViewNoRacFound.setVisibility(0);
        } else if (this.mGeoFencePair.getAssociatedRacs().size() == 1) {
            DetailedIduModel copyOfDetailedIduModelFromRacId = iduList.getCopyOfDetailedIduModelFromRacId(this.mGeoFencePair.getAssociatedRacs().get(0).intValue());
            if (copyOfDetailedIduModelFromRacId != null) {
                this.mBinding.textViewAllAcs.setText(copyOfDetailedIduModelFromRacId.name);
            }
        } else if (this.mGeoFencePair.getAssociatedRacs().size() == iduList.size()) {
            this.mBinding.textViewAllAcs.setText(this.mCoreActivity.getString(R.string.manageUser_lbl_allACs));
        } else {
            DetailedIduModel copyOfDetailedIduModelFromRacId2 = iduList.getCopyOfDetailedIduModelFromRacId(this.mGeoFencePair.getAssociatedRacs().get(0).intValue());
            if (copyOfDetailedIduModelFromRacId2 != null) {
                TextView textView = this.mBinding.textViewAllAcs;
                textView.setText(copyOfDetailedIduModelFromRacId2.name + ",...(" + (this.mGeoFencePair.getAssociatedRacs().size() - 1) + ")");
            }
        }
    }

    private void calculateAndAddRacsForGeoFenceNullV2() {
        this.mCoreActivity.getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduListSingleLiveEvent().observeWithCachedTrigger(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda8(this));
    }

    /* renamed from: lambda$calculateAndAddRacsForGeoFenceNullV2$3$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30279xae4f4e54(IduList iduList) {
        this.mGeoFencePair.getAssociatedRacs().clear();
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (this.mCoreActivity.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId).getEnableOptions().getEnableOption0().isOutOfHomeReminder()) {
                this.mGeoFencePair.getAssociatedRacs().add(Long.valueOf((long) detailedIduModel.f454id.intValue()));
                this.mGeoFencePair.getAssociatedRacCloudId().add(detailedIduModel.cloudId);
            }
        }
    }

    /* access modifiers changed from: private */
    public void putOldDataIntoFamilyIDToGeoPairMap() {
        this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().clear();
        this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().putAll(this.familyIdGeoFenceDataMapOld.parcelClone());
    }

    private void calculateUSerIfGeofenceIsNullV3() {
        showPleaseWaitDialog();
        this.getAllUsersViewModel.callGetAllUserApi();
        this.getAllUsersViewModel.singleLiveEventForFailure.observeSingleEvent(getViewLifecycleOwner(), new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse == null || genericResponse.getResponse() == null) {
                    SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                    smartFenceFragment.showErrorPopUp(smartFenceFragment.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                } else if (genericResponse.getResponse().code() != 401) {
                    SmartFenceFragment smartFenceFragment2 = SmartFenceFragment.this;
                    smartFenceFragment2.showErrorPopUp(smartFenceFragment2.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                } else {
                    SmartFenceFragment.this.showPleaseWaitDialog();
                    SmartFenceFragment.this.mCoreActivity.refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            C19065.this.callInviationApi();
                        }
                    }, false);
                }
            }

            /* access modifiers changed from: private */
            public void callInviationApi() {
                SmartFenceFragment.this.getAllUsersViewModel.callGetAllUserApi();
            }
        });
        this.getAllUsersViewModel.getUserListSingleLiveEvent().observeWithCachedTrigger(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda11(this));
    }

    /* renamed from: lambda$calculateUSerIfGeofenceIsNullV3$4$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30280x7be3b04e(List list) {
        dismissPleaseWaitDialog();
        this.mGeoFencePair.getAssociatedUsers().clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.mGeoFencePair.getAssociatedUsers().add(Long.valueOf((long) ((UserFamilyMemberModels.UserFamilyMemberInfo) it.next()).detailsUserInfoId));
        }
        commonUpdateName(this.mGeoFencePair, list);
    }

    private void calculateUsersIfGeofenceIsNotNullV3() {
        showPleaseWaitDialog();
        this.getAllUsersViewModel.callGetAllUserApi();
        this.getAllUsersViewModel.singleLiveEventForFailure.observeSingleEvent(getViewLifecycleOwner(), new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse.getResponse() != null) {
                    if (genericResponse.getResponse().code() != 401) {
                        SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                        smartFenceFragment.showErrorPopUp(smartFenceFragment.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                        return;
                    }
                    SmartFenceFragment.this.showPleaseWaitDialog();
                    SmartFenceFragment.this.mCoreActivity.refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            C19086.this.callInviationApi();
                        }
                    }, false);
                }
            }

            /* access modifiers changed from: private */
            public void callInviationApi() {
                SmartFenceFragment.this.getAllUsersViewModel.callGetAllUserApi();
            }
        });
        this.getAllUsersViewModel.getUserListSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda12(this));
    }

    /* renamed from: lambda$calculateUsersIfGeofenceIsNotNullV3$5$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30281x3b44e9db(List list) {
        dismissPleaseWaitDialog();
        if (!this.mGeoFencePair.getAssociatedUsers().contains(Long.valueOf((long) UserInfo.getCurrentUserInfo(this.mCoreActivity).f424id))) {
            this.mGeoFencePair.getAssociatedUsers().add(Long.valueOf((long) UserInfo.getCurrentUserInfo(this.mCoreActivity).f424id));
        }
        commonUpdateName(this.mGeoFencePair, list);
    }

    private void commonUpdateName(GeoFencePair geoFencePair, List<UserFamilyMemberModels.UserFamilyMemberInfo> list) {
        if (geoFencePair.getAssociatedUsers().isEmpty()) {
            return;
        }
        if (geoFencePair.getAssociatedUsers().size() == 1) {
            this.mBinding.textViewAllUsers.setText(UserInfo.getCurrentUserInfo(this.mCoreActivity).firstName + " " + UserInfo.getCurrentUserInfo(this.mCoreActivity).lastName);
        } else if (this.mGeoFencePair.getAssociatedUsers().size() == list.size()) {
            this.mBinding.textViewAllUsers.setText(this.mCoreActivity.getString(R.string.common_lbl_all_users));
        } else {
            TextView textView = this.mBinding.textViewAllUsers;
            textView.setText((UserInfo.getCurrentUserInfo(this.mCoreActivity).firstName + " " + UserInfo.getCurrentUserInfo(this.mCoreActivity).lastName) + ",...(" + (this.mGeoFencePair.getAssociatedUsers().size() - 1) + ")");
        }
    }

    private void enableDisableLeavingButton() {
        if (this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled()) {
            ArrayList<OperationMode> arrayList = this.modeArrayListForRecyclerView;
            if (arrayList == null || arrayList.isEmpty()) {
                changeModeTempButtonState(this.mBinding.setTemoModeForLeavingLayout, 8, this.mBinding.buttonEnabledLeaving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
                return;
            }
            changeModeTempButtonState(this.mBinding.setTemoModeForLeavingLayout, 0, this.mBinding.buttonEnabledLeaving, R.string.common_lbl_enabled, R.drawable.drawable_button_green_border);
            return;
        }
        changeModeTempButtonState(this.mBinding.setTemoModeForLeavingLayout, 8, this.mBinding.buttonEnabledLeaving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
    }

    private void enableDisableArrivingButton() {
        if (this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled()) {
            ArrayList<OperationMode> arrayList = this.modeArrayListForRecyclerView;
            if (arrayList == null || arrayList.isEmpty()) {
                changeModeTempButtonState(this.mBinding.setTemoModeForArrivingLayout, 8, this.mBinding.buttonEnabledArrving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
                return;
            }
            changeModeTempButtonState(this.mBinding.setTemoModeForArrivingLayout, 0, this.mBinding.buttonEnabledArrving, R.string.common_lbl_enabled, R.drawable.drawable_button_green_border);
            return;
        }
        changeModeTempButtonState(this.mBinding.setTemoModeForArrivingLayout, 8, this.mBinding.buttonEnabledArrving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
    }

    private void changeModeTempButtonState(ConstraintLayout constraintLayout, int i, Button button, int i2, int i3) {
        constraintLayout.setVisibility(i);
        button.setText(this.mCoreActivity.getResources().getString(i2));
        button.setBackground(ContextCompat.getDrawable(this.mCoreActivity, i3));
    }

    private void initClickListeners() {
        this.mBinding.layoutAcs.setOnClickListener(this);
        this.mBinding.imageButtonHelp.setOnClickListener(this);
        this.mBinding.layoutGeofenceSettings.setOnClickListener(this);
        this.mBinding.layoutUsers.setOnClickListener(this);
        this.mBinding.textViewSave.setOnClickListener(this);
        this.mBinding.setTemoModeForArrivingLayout.setOnClickListener(this);
        this.mBinding.setTemoModeForLeavingLayout.setOnClickListener(this);
        this.mBinding.buttonEnabledArrving.setOnClickListener(this);
        this.mBinding.buttonEnabledLeaving.setOnClickListener(this);
        this.mBinding.layoutAddAc.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void hideUiSettings() {
        this.mBinding.scrollView.setEnabled(false);
        this.mBinding.switchMainHome.setChecked(false);
        this.newLocationControlsStateModel.enabled = false;
        changeSaveButtonState();
        this.mBinding.scrollView.setEnabled(false);
        this.mBinding.layoutGeofenceSettings.setVisibility(4);
        this.mBinding.layoutAcs.setVisibility(4);
        this.mBinding.layoutUsers.setVisibility(4);
        this.mBinding.layoutArriving.setVisibility(4);
        this.mBinding.layoutLeaving.setVisibility(4);
        if (ManufacturerListUtil.isChineseManufacturer()) {
            this.mBinding.layoutNoteBottom.setVisibility(0);
            this.mBinding.layoutNote.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public void showUiSetting() {
        this.mBinding.scrollView.setEnabled(true);
        this.mBinding.switchMainHome.setChecked(true);
        this.newLocationControlsStateModel.enabled = true;
        changeSaveButtonState();
        this.mBinding.layoutGeofenceSettings.setVisibility(0);
        this.mBinding.layoutAcs.setVisibility(0);
        this.mBinding.layoutUsers.setVisibility(0);
        this.mBinding.layoutArriving.setVisibility(0);
        this.mBinding.layoutLeaving.setVisibility(0);
        if (ManufacturerListUtil.isChineseManufacturer()) {
            this.mBinding.layoutNoteBottom.setVisibility(4);
            this.mBinding.layoutNote.setVisibility(0);
        }
        if (this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode == null || this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode.isEmpty()) {
            this.mBinding.textViewSetModeAndTempArriving.setVisibility(0);
            this.mBinding.modeTextViewConstraintLayoutArriving.setVisibility(4);
        } else {
            this.mBinding.textViewSetModeAndTempArriving.setVisibility(4);
            this.mBinding.modeTextViewConstraintLayoutArriving.setVisibility(0);
            if (this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
                if (OperationMode.valueOf(this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode) == OperationMode.FAN) {
                    this.mBinding.tempTextViewArriving.setVisibility(4);
                } else if (OperationMode.valueOf(this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode) == OperationMode.AUTO) {
                    for (Map.Entry<String, List<RacModelWiseData.RacModeDetail>> key : this.racListCloudIDToModeMap.entrySet()) {
                        if (!this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId((String) key.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.AUTO).getVisibleSettings().getTemperature()) {
                            this.mBinding.tempTextViewArriving.setVisibility(4);
                        }
                    }
                } else {
                    this.mBinding.tempTextViewArriving.setVisibility(0);
                }
                this.mBinding.textViewOffArriving.setVisibility(4);
                this.mBinding.modeImageViewArriving.setVisibility(0);
                this.mBinding.modeTextViewArriving.setVisibility(0);
            } else {
                this.mBinding.textViewOffArriving.setVisibility(0);
                this.mBinding.modeImageViewArriving.setVisibility(4);
                this.mBinding.modeTextViewArriving.setVisibility(4);
                this.mBinding.tempTextViewArriving.setVisibility(4);
            }
            setModeAndTemp(this.mGeoFencePair.getArrivingGeoFence(), this.mBinding.modeTextViewArriving, this.mBinding.modeImageViewArriving, this.mBinding.tempTextViewArriving, this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().temperature);
        }
        if (this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode == null || this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode.isEmpty()) {
            this.mBinding.textViewSetModeAndTempLeaving.setVisibility(0);
            this.mBinding.modeTextViewConstraintLayoutLeaving.setVisibility(4);
            return;
        }
        this.mBinding.modeTextViewConstraintLayoutLeaving.setVisibility(0);
        this.mBinding.textViewSetModeAndTempLeaving.setVisibility(4);
        if (this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
            if (OperationMode.valueOf(this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode) == OperationMode.FAN) {
                this.mBinding.tempTextViewLeaving.setVisibility(4);
            } else if (OperationMode.valueOf(this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode) == OperationMode.AUTO) {
                for (Map.Entry<String, List<RacModelWiseData.RacModeDetail>> key2 : this.racListCloudIDToModeMap.entrySet()) {
                    if (!this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId((String) key2.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.AUTO).getVisibleSettings().getTemperature()) {
                        this.mBinding.tempTextViewLeaving.setVisibility(4);
                    }
                }
            } else {
                this.mBinding.tempTextViewLeaving.setVisibility(0);
            }
            this.mBinding.textViewOffLeaving.setVisibility(4);
            this.mBinding.modeImageViewLeaving.setVisibility(0);
            this.mBinding.modeTextViewLeaving.setVisibility(0);
        } else {
            this.mBinding.textViewOffLeaving.setVisibility(0);
            this.mBinding.modeImageViewLeaving.setVisibility(4);
            this.mBinding.modeTextViewLeaving.setVisibility(4);
            this.mBinding.tempTextViewLeaving.setVisibility(4);
        }
        setModeAndTemp(this.mGeoFencePair.getLeavingGeoFence(), this.mBinding.modeTextViewLeaving, this.mBinding.modeImageViewLeaving, this.mBinding.tempTextViewLeaving, this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().temperature);
    }

    /* access modifiers changed from: private */
    public void setModeAndTemp(GeoFenceData geoFenceData, TextView textView, ImageView imageView, TextView textView2, double d) {
        OperationMode valueOf = OperationMode.valueOf(geoFenceData.getModeTempSettings().mode);
        textView.setText(this.mCoreActivity.getString(valueOf.getStringRes()));
        textView.setTextColor(ContextCompat.getColor(this.mCoreActivity, valueOf.getModeColor()));
        imageView.setImageDrawable(ContextCompat.getDrawable(this.mCoreActivity, valueOf.getDrawableResSmartFence()));
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d))});
        textView2.setText(format + this.mCoreActivity.getResources().getString(TemperatureUnit.getTemperatureUnitFromSettings()));
        textView2.setTextColor(ContextCompat.getColor(this.mCoreActivity, valueOf.getModeColor()));
    }

    /* access modifiers changed from: package-private */
    public void setTempAfterRacsLoaded(GeoFenceData geoFenceData, TextView textView) {
        String str;
        String[] tempType = getTempType();
        if (tempType[0].isEmpty()) {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(geoFenceData.getModeTempSettings().temperature))});
        } else if (tempType[0].equals("RELATIVE")) {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(geoFenceData.getModeTempSettings().relativeTemperature))});
        } else if (tempType[0].equals("RELATIVE_ABSOLUTE")) {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(geoFenceData.getModeTempSettings().relativeTemperature + Double.parseDouble(tempType[1])))});
        } else {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(geoFenceData.getModeTempSettings().temperature))});
        }
        textView.setText(String.format("%s%s", new Object[]{str, this.mCoreActivity.getString(TemperatureUnit.getTemperatureUnitFromSettings())}));
    }

    private void changeSaveButtonState() {
        if (this.newLocationControlsStateModel.enabled == this.oldLocationControlsStateModel.enabled) {
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mCoreActivity, R.color.textview_color_vd_light));
            this.mBinding.textViewSave.setEnabled(false);
        } else if (this.mGeoFencePair.getLatLng() != null && !this.mGeoFencePair.getAssociatedRacs().isEmpty()) {
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mCoreActivity, R.color.colorRed));
            this.mBinding.textViewSave.setEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void changeSaveButtonStateOnSettingsChanged() {
        if (!SmartFenceGeoFenceSettings.isGeoFenceSettingChange && !SmartFenceSelectAcFragment.isAcSettingChange && !SmartFenceSelectUserFragment.isUserSettingChange && !SmartFenceSetModeTempDilog.isModeTempSettingChange && !this.isEnableButtonClicked) {
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mCoreActivity, R.color.textview_color_vd_light));
            this.mBinding.textViewSave.setEnabled(false);
        } else if (this.mGeoFencePair.getLatLng() != null && !this.mGeoFencePair.getAssociatedRacs().isEmpty()) {
            if (enableSaveModeTempConditions(this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled, this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode, this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled, this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode)) {
                this.mGeoFencePair.valueChanged = true;
                this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mCoreActivity, R.color.colorRed));
                this.mBinding.textViewSave.setEnabled(true);
                return;
            }
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mCoreActivity, R.color.textview_color_vd_light));
            this.mBinding.textViewSave.setEnabled(false);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_enabled_arrving:
                ArrayList<OperationMode> arrayList = this.modeArrayListForRecyclerView;
                if (arrayList != null && arrayList.size() > 0) {
                    if (this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled()) {
                        this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().setSettingsEnabled(false);
                        changeModeTempButtonState(this.mBinding.setTemoModeForArrivingLayout, 8, this.mBinding.buttonEnabledArrving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
                    } else {
                        this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().setSettingsEnabled(true);
                        changeModeTempButtonState(this.mBinding.setTemoModeForArrivingLayout, 0, this.mBinding.buttonEnabledArrving, R.string.common_lbl_enabled, R.drawable.drawable_button_green_border);
                    }
                    this.isEnableButtonClicked = true;
                    changeSaveButtonStateOnSettingsChanged();
                    return;
                } else if (this.mGeoFencePair.getAssociatedRacs().isEmpty()) {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.home_lbl_noAcsFound));
                    return;
                } else {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smart_fence_selected_acs_does_not_support));
                    return;
                }
            case R.id.button_enabled_leaving:
                ArrayList<OperationMode> arrayList2 = this.modeArrayListForRecyclerView;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    if (this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled()) {
                        this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().setSettingsEnabled(false);
                        changeModeTempButtonState(this.mBinding.setTemoModeForLeavingLayout, 8, this.mBinding.buttonEnabledLeaving, R.string.common_lbl_disabled, R.drawable.drawable_button_red_border);
                    } else {
                        this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().setSettingsEnabled(true);
                        changeModeTempButtonState(this.mBinding.setTemoModeForLeavingLayout, 0, this.mBinding.buttonEnabledLeaving, R.string.common_lbl_enabled, R.drawable.drawable_button_green_border);
                    }
                    this.isEnableButtonClicked = true;
                    changeSaveButtonStateOnSettingsChanged();
                    return;
                } else if (this.mGeoFencePair.getAssociatedRacs().isEmpty()) {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.home_lbl_noAcsFound));
                    return;
                } else {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smart_fence_selected_acs_does_not_support));
                    return;
                }
            case R.id.image_button_help:
                if (!this.createTooltipContentHolder.isShowing()) {
                    this.createTooltipContentHolder.setHintCasePosition(3);
                    this.createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_without_radius);
                    this.createTooltipContentHolder.setBordercolor(R.color.colorRed);
                    this.createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
                    this.createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
                    this.createTooltipContentHolder.buildWithNoDimensions();
                    this.createTooltipContentHolder.show();
                } else {
                    this.createTooltipContentHolder.dismiss();
                }
                new Handler().postDelayed(new SmartFenceFragment$$ExternalSyntheticLambda4(this), JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
                return;
            case R.id.layoutAddAc:
                this.mCoreActivity.getNavController().navigate((int) R.id.action_SmartFenceFragment_to_OnBoarding);
                return;
            case R.id.layout_acs:
                modeArrayListForRecyclerViewCopy.clear();
                modeArrayListForRecyclerViewCopy.addAll(this.modeArrayListForRecyclerView);
                this.mCoreActivity.getNavController().navigate((int) R.id.action_smartFenceFragment_to_SmartFenceSelectAcFragmentt);
                return;
            case R.id.layout_geofence_settings:
                launchSettings();
                return;
            case R.id.layout_users:
                this.mCoreActivity.getNavController().navigate((int) R.id.action_smartFenceFragment_to_SmartFenceSelectAcFragment);
                return;
            case R.id.set_Temo_mode_for_arriving_layout:
                CoreActivity coreActivity = this.mCoreActivity;
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = new SmartFenceSetModeTempDilog(coreActivity, R.layout.smart_fence_dialog_set_mode_temp_location_controls, R.string.smartFence_alert_arrivingInsideGeoFenceRange, R.drawable.ic_geofence_arriving_grey, coreActivity, this.mGeoFencePair, this.modeArrayListForRecyclerView, this.racListCloudIDToModeMap, true, coreActivity);
                smartFenceSetModeTempDilog.setParentView(this.mBinding.getRoot(), true);
                smartFenceSetModeTempDilog.show();
                final SingleLiveEvent<SmartFenceSetModeTempDilog.SettingsModel> singleLiveEvent = smartFenceSetModeTempDilog.getSingleLiveEvent();
                singleLiveEvent.observeSingleEvent(getViewLifecycleOwner(), new Observer<SmartFenceSetModeTempDilog.SettingsModel>() {
                    public void onChanged(SmartFenceSetModeTempDilog.SettingsModel settingsModel) {
                        singleLiveEvent.removeObserver(this);
                        SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode = settingsModel.mode;
                        SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().temperature = settingsModel.temp;
                        SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().powerMode = settingsModel.onOffStatus;
                        SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().relativeTemperature = settingsModel.relativeTemp;
                        SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().humidity = settingsModel.defaultHumidity;
                        SmartFenceFragment.this.changeSaveButtonStateOnSettingsChanged();
                        SmartFenceFragment.this.mBinding.modeTextViewConstraintLayoutArriving.setVisibility(0);
                        SmartFenceFragment.this.mBinding.textViewSetModeAndTempArriving.setVisibility(4);
                        if (SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
                            if (OperationMode.valueOf(settingsModel.mode) == OperationMode.FAN) {
                                SmartFenceFragment.this.mBinding.tempTextViewArriving.setVisibility(4);
                            } else if (OperationMode.valueOf(settingsModel.mode) == OperationMode.AUTO) {
                                for (Map.Entry key : SmartFenceFragment.this.racListCloudIDToModeMap.entrySet()) {
                                    if (!SmartFenceFragment.this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId((String) key.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.AUTO).getVisibleSettings().getTemperature()) {
                                        SmartFenceFragment.this.mBinding.tempTextViewArriving.setVisibility(4);
                                    }
                                }
                            } else {
                                SmartFenceFragment.this.mBinding.tempTextViewArriving.setVisibility(0);
                            }
                            SmartFenceFragment.this.mBinding.textViewOffArriving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.modeImageViewArriving.setVisibility(0);
                            SmartFenceFragment.this.mBinding.modeTextViewArriving.setVisibility(0);
                        } else {
                            SmartFenceFragment.this.mBinding.textViewOffArriving.setVisibility(0);
                            SmartFenceFragment.this.mBinding.modeImageViewArriving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.modeTextViewArriving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.tempTextViewArriving.setVisibility(4);
                        }
                        SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                        smartFenceFragment.setModeAndTemp(smartFenceFragment.mGeoFencePair.getArrivingGeoFence(), SmartFenceFragment.this.mBinding.modeTextViewArriving, SmartFenceFragment.this.mBinding.modeImageViewArriving, SmartFenceFragment.this.mBinding.tempTextViewArriving, SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().temperature);
                        if (SmartFenceFragment.this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode.equals("AUTO")) {
                            SmartFenceFragment smartFenceFragment2 = SmartFenceFragment.this;
                            smartFenceFragment2.setTempAfterRacsLoaded(smartFenceFragment2.mGeoFencePair.getArrivingGeoFence(), SmartFenceFragment.this.mBinding.tempTextViewArriving);
                        }
                    }
                });
                return;
            case R.id.set_Temo_mode_for_leaving_layout:
                CoreActivity coreActivity2 = this.mCoreActivity;
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog2 = new SmartFenceSetModeTempDilog(coreActivity2, R.layout.smart_fence_dialog_set_mode_temp_location_controls, R.string.smartFence_alert_leavingOutsideGeoFenceRange, R.drawable.ic_geofence_leaving_grey, coreActivity2, this.mGeoFencePair, this.modeArrayListForRecyclerView, this.racListCloudIDToModeMap, false, coreActivity2);
                smartFenceSetModeTempDilog2.setParentView(this.mBinding.getRoot(), true);
                smartFenceSetModeTempDilog2.show();
                final SingleLiveEvent<SmartFenceSetModeTempDilog.SettingsModel> singleLiveEvent2 = smartFenceSetModeTempDilog2.getSingleLiveEvent();
                singleLiveEvent2.observeSingleEvent(getViewLifecycleOwner(), new Observer<SmartFenceSetModeTempDilog.SettingsModel>() {
                    public void onChanged(SmartFenceSetModeTempDilog.SettingsModel settingsModel) {
                        singleLiveEvent2.removeObserver(this);
                        SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode = settingsModel.mode;
                        SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().temperature = settingsModel.temp;
                        SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().powerMode = settingsModel.onOffStatus;
                        SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().relativeTemperature = settingsModel.relativeTemp;
                        SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().humidity = settingsModel.defaultHumidity;
                        SmartFenceFragment.this.changeSaveButtonStateOnSettingsChanged();
                        SmartFenceFragment.this.mBinding.modeTextViewConstraintLayoutLeaving.setVisibility(0);
                        SmartFenceFragment.this.mBinding.textViewSetModeAndTempLeaving.setVisibility(4);
                        if (SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
                            if (OperationMode.valueOf(settingsModel.mode) == OperationMode.FAN) {
                                SmartFenceFragment.this.mBinding.tempTextViewLeaving.setVisibility(4);
                            } else if (OperationMode.valueOf(settingsModel.mode) == OperationMode.AUTO) {
                                for (Map.Entry key : SmartFenceFragment.this.racListCloudIDToModeMap.entrySet()) {
                                    if (!SmartFenceFragment.this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId((String) key.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.AUTO).getVisibleSettings().getTemperature()) {
                                        SmartFenceFragment.this.mBinding.tempTextViewLeaving.setVisibility(4);
                                    }
                                }
                            } else {
                                SmartFenceFragment.this.mBinding.tempTextViewLeaving.setVisibility(0);
                            }
                            SmartFenceFragment.this.mBinding.textViewOffLeaving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.modeImageViewLeaving.setVisibility(0);
                            SmartFenceFragment.this.mBinding.modeTextViewLeaving.setVisibility(0);
                        } else {
                            SmartFenceFragment.this.mBinding.textViewOffLeaving.setVisibility(0);
                            SmartFenceFragment.this.mBinding.modeImageViewLeaving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.modeTextViewLeaving.setVisibility(4);
                            SmartFenceFragment.this.mBinding.tempTextViewLeaving.setVisibility(4);
                        }
                        SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                        smartFenceFragment.setModeAndTemp(smartFenceFragment.mGeoFencePair.getLeavingGeoFence(), SmartFenceFragment.this.mBinding.modeTextViewLeaving, SmartFenceFragment.this.mBinding.modeImageViewLeaving, SmartFenceFragment.this.mBinding.tempTextViewLeaving, SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().temperature);
                        if (SmartFenceFragment.this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode.equals("AUTO")) {
                            SmartFenceFragment smartFenceFragment2 = SmartFenceFragment.this;
                            smartFenceFragment2.setTempAfterRacsLoaded(smartFenceFragment2.mGeoFencePair.getLeavingGeoFence(), SmartFenceFragment.this.mBinding.tempTextViewLeaving);
                        }
                    }
                });
                return;
            case R.id.text_view_save:
                CoreActivity coreActivity3 = this.mCoreActivity;
                if (coreActivity3 != null && !NetworkConnectivity.isNetworkAvailable(coreActivity3)) {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), getString(R.string.common_alert_unableToConnectToNw));
                    return;
                } else if (!this.mBinding.switchMainHome.isChecked()) {
                    disableSmartFence();
                    return;
                } else if (this.mGeoFencePair.getAssociatedRacs().size() > 0 && this.mGeoFencePair.getLatLng() != null) {
                    boolean z = this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled;
                    boolean z2 = this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled;
                    if (!z && !z2) {
                        handleSave();
                        return;
                    } else if (z && this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().mode == null) {
                        showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseSetArrivingModeTempSettings));
                        return;
                    } else if (!z2 || this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().mode != null) {
                        handleSave();
                        return;
                    } else {
                        showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseSetLeavingModeTempSettings));
                        return;
                    }
                } else if (this.mGeoFencePair.getAssociatedRacs().size() <= 0) {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_pleaseAddAcToEnableSmartFence));
                    return;
                } else {
                    showSingleChoicePopUp(this.mCoreActivity.getString(R.string.common_alert), this.mCoreActivity.getString(R.string.smartFence_alert_please_set_smart_fence_settings));
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: lambda$onClick$6$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30287x74de629f() {
        CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolder;
        if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
            this.createTooltipContentHolder.dismiss();
        }
    }

    private void disableSmartFence() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(this.mCoreActivity);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setTitleString(this.mCoreActivity.getString(R.string.common_btn_save));
        confirmationDialog.setParentView(this.mBinding.getRoot());
        confirmationDialog.setMessageString((int) R.string.common_alert_saveChangesDesc);
        confirmationDialog.setPositiveButton(this.mCoreActivity.getString(R.string.common_btn_yes), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                if (SmartFenceFragment.this.mCoreActivity == null || NetworkConnectivity.isNetworkAvailable(SmartFenceFragment.this.mCoreActivity)) {
                    SmartFenceFragment.this.showPleaseWaitDialog();
                    SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().disableLocationControl(SmartFenceFragment.this.getViewLifecycleOwner());
                    final SingleLiveEvent<GenericResponse> onEnableSingleLiveEvent = SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getOnEnableSingleLiveEvent();
                    onEnableSingleLiveEvent.observeSingleEvent(SmartFenceFragment.this.getViewLifecycleOwner(), new Observer<GenericResponse>() {
                        public void onChanged(GenericResponse genericResponse) {
                            SmartFenceFragment.this.dismissPleaseWaitDialog();
                            if (genericResponse.isApiSuccessful()) {
                                onEnableSingleLiveEvent.removeObserver(this);
                                SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(SmartFenceFragment.this.getViewLifecycleOwner());
                                SmartFenceFragment.this.mCoreActivity.getNavController().navigate((int) R.id.action_global_homePageFragment);
                            } else if (genericResponse.getResponse() == null) {
                                SmartFenceFragment.this.showSingleChoicePopUp(SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert), SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert_somethingWentWrong));
                            } else if (genericResponse.getResponse().code() != 401) {
                                SmartFenceFragment.this.showErrorPopUp(SmartFenceFragment.this.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                            } else {
                                SmartFenceFragment.this.showPleaseWaitDialog();
                                SmartFenceFragment.this.mCoreActivity.refreshToken(new CallBackListener() {
                                    public void onFailure() {
                                    }

                                    public void onSuccess() {
                                        C19131.this.callDisableAPI();
                                    }
                                }, false);
                            }
                        }

                        /* access modifiers changed from: private */
                        public void callDisableAPI() {
                            SmartFenceFragment.this.dismissPleaseWaitDialog();
                            SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().disableLocationControl(SmartFenceFragment.this.getViewLifecycleOwner());
                        }
                    });
                    return true;
                }
                SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                smartFenceFragment.showSingleChoicePopUp(smartFenceFragment.mCoreActivity.getString(R.string.common_alert), SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert_unableToConnectToNw));
                return true;
            }
        });
        confirmationDialog.setNegativeButton(this.mCoreActivity.getString(R.string.common_btn_no), new SmartFenceFragment$$ExternalSyntheticLambda1(this));
        confirmationDialog.show();
    }

    /* renamed from: lambda$disableSmartFence$7$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ boolean mo30284x565ab717(Dialog dialog, View view) {
        this.mBinding.switchMainHome.setChecked(this.oldLocationControlsStateModel.enabled);
        if (!this.isClickedOnHamBurger) {
            return true;
        }
        this.iDrawerMenuFunctions.openMenuDrawer();
        this.isClickedOnHamBurger = false;
        return true;
    }

    private void launchSettings() {
        getCountryUnit();
    }

    private void getCountryUnit() {
        MainViewModel mainViewModel;
        LiveData<Resource<CountryUnit>> countryUnit;
        CoreActivity coreActivity = this.mCoreActivity;
        if (coreActivity != null && (mainViewModel = coreActivity.getMainViewModel()) != null && (countryUnit = mainViewModel.getCountryUnit()) != null) {
            countryUnit.removeObservers(getViewLifecycleOwner());
            countryUnit.observe(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda6(this));
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$13 */
    static /* synthetic */ class C190213 {
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
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C190213.<clinit>():void");
        }
    }

    /* renamed from: lambda$getCountryUnit$8$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30285x38736a48(Resource resource) {
        CountryUnit.Body body;
        if (resource != null) {
            int i = C190213.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            String str = Constants.Keys.KILO_METER;
            if (i == 1) {
                dismissPleaseWaitDialog();
                launchSettings(str);
            } else if (i == 2) {
                showPleaseWaitDialog();
            } else if (i == 3) {
                dismissPleaseWaitDialog();
                CountryUnit countryUnit = (CountryUnit) resource.response;
                if (countryUnit != null && (body = countryUnit.getBody()) != null) {
                    if (body.isMiles()) {
                        str = Constants.Keys.MILES;
                    }
                    launchSettings(str);
                }
            }
        }
    }

    private void launchSettings(String str) {
        NavController navController;
        Bundle bundle = new Bundle();
        bundle.putString(Constants.Keys.UNIT, str);
        CoreActivity coreActivity = this.mCoreActivity;
        if (coreActivity != null && (navController = coreActivity.getNavController()) != null) {
            navController.navigate((int) R.id.action_smartFenceFragment_to_SmartFenceGeoFenceSettings, bundle);
        }
    }

    public void onStart() {
        super.onStart();
        this.mCoreActivity.getOnBackPressedDispatcher().addCallback(this.mCoreActivity, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (SmartFenceFragment.this.createTooltipContentHolder != null && SmartFenceFragment.this.createTooltipContentHolder.isShowing()) {
                    SmartFenceFragment.this.createTooltipContentHolder.dismiss();
                }
                SmartFenceFragment.this.putOldDataIntoFamilyIDToGeoPairMap();
                SmartFenceFragment.this.mCoreActivity.getNavController().navigate((int) R.id.action_global_homePageFragment);
            }
        });
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    private void handleSave() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(this.mCoreActivity);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setTitleString(this.mCoreActivity.getString(R.string.common_btn_save));
        confirmationDialog.setParentView(this.mBinding.getRoot());
        confirmationDialog.setMessageString((int) R.string.common_alert_saveChangesDesc);
        confirmationDialog.setPositiveButton(this.mCoreActivity.getString(R.string.common_btn_yes), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                if (SmartFenceFragment.this.mCoreActivity == null || NetworkConnectivity.isNetworkAvailable(SmartFenceFragment.this.mCoreActivity)) {
                    SmartFenceFragment.this.showPleaseWaitDialog();
                    SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().enableLocationControl(SmartFenceFragment.this.getViewLifecycleOwner());
                    final SingleLiveEvent<GenericResponse> onEnableSingleLiveEvent = SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getOnEnableSingleLiveEvent();
                    onEnableSingleLiveEvent.observeSingleEvent(SmartFenceFragment.this.getViewLifecycleOwner(), new Observer<GenericResponse>() {
                        public void onChanged(GenericResponse genericResponse) {
                            if (!NetworkConnectivity.isNetworkAvailable(SmartFenceFragment.this.mCoreActivity)) {
                                SmartFenceFragment.this.dismissPleaseWaitDialog();
                                SmartFenceFragment.this.showSingleChoicePopUp(SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert), SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert_unableToConnectToNw));
                            } else if (genericResponse.isApiSuccessful()) {
                                onEnableSingleLiveEvent.removeObserver(this);
                                if (!SmartFenceFragment.this.mGeoFencePair.valueChanged) {
                                    SmartFenceFragment.this.dismissPleaseWaitDialog();
                                    SmartFenceFragment.this.mGeoFencePair.valueChanged = false;
                                    SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(SmartFenceFragment.this.getViewLifecycleOwner());
                                    SmartFenceFragment.this.mCoreActivity.getNavController().navigate((int) R.id.action_global_homePageFragment);
                                    return;
                                }
                                final SingleLiveEvent<GenericResponse> updateGeoFenceSettings = SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().updateGeoFenceSettings(SmartFenceFragment.this.mGeoFencePair);
                                updateGeoFenceSettings.observeSingleEvent(SmartFenceFragment.this.getViewLifecycleOwner(), new Observer<GenericResponse>() {
                                    /* JADX WARNING: Can't fix incorrect switch cases order */
                                    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0147, code lost:
                                        if (r6.equals(com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceErrorResponse.NO_MAPPING_FOR_THE_USER_LOCATION_CONTROL) == false) goto L_0x00f2;
                                     */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void onChanged(com.jch.racWiFi.genericModels.GenericResponse r6) {
                                        /*
                                            r5 = this;
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r0.dismissPleaseWaitDialog()
                                            boolean r0 = r6.isApiSuccessful()
                                            if (r0 == 0) goto L_0x0049
                                            com.jch.racWiFi.Listeners.SingleLiveEvent r6 = r4
                                            r6.removeObserver(r5)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r6 = r6.mCoreActivity
                                            com.jch.racWiFi.genericViewModel.GlobalViewModelRepository r6 = r6.getGlobalViewModelRepository()
                                            com.jch.racWiFi.iduManagement.smartFence.viewModels.GeoFenceListViewModel r6 = r6.getGeoFenceListViewModel()
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            androidx.lifecycle.LifecycleOwner r0 = r0.getViewLifecycleOwner()
                                            r6.getGeoFenceStatus(r0)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r6 = r6.mCoreActivity
                                            androidx.navigation.NavController r6 = r6.getNavController()
                                            r0 = 2131361929(0x7f0a0089, float:1.8343624E38)
                                            r6.navigate((int) r0)
                                            goto L_0x0294
                                        L_0x0049:
                                            retrofit2.Response r0 = r6.getResponse()
                                            r1 = 2131951765(0x7f130095, float:1.9539954E38)
                                            r2 = 2131951732(0x7f130074, float:1.9539887E38)
                                            if (r0 != 0) goto L_0x007b
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r0 = r0.mCoreActivity
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r2 = r2.mCoreActivity
                                            java.lang.String r1 = r2.getString(r1)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            return
                                        L_0x007b:
                                            retrofit2.Response r0 = r6.getResponse()
                                            int r0 = r0.code()
                                            r3 = 401(0x191, float:5.62E-43)
                                            r4 = 0
                                            if (r0 == r3) goto L_0x0279
                                            java.lang.Class<com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceErrorResponse> r0 = com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceErrorResponse.class
                                            java.lang.Object r6 = r6.getErrorBodyOfType(r0)
                                            com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceErrorResponse r6 = (com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceErrorResponse) r6
                                            if (r6 != 0) goto L_0x00b8
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r0 = r0.mCoreActivity
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r2 = r2.mCoreActivity
                                            java.lang.String r1 = r2.getString(r1)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            return
                                        L_0x00b8:
                                            java.lang.String r0 = r6.code
                                            if (r0 != 0) goto L_0x00e5
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r0 = r0.mCoreActivity
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r1 = r1.mCoreActivity
                                            r2 = 2131953486(0x7f13074e, float:1.9543444E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            return
                                        L_0x00e5:
                                            java.lang.String r6 = r6.code
                                            r6.hashCode()
                                            r0 = -1
                                            int r3 = r6.hashCode()
                                            switch(r3) {
                                                case -1995143740: goto L_0x0141;
                                                case -1995143739: goto L_0x0136;
                                                case -1995143738: goto L_0x012b;
                                                case -1995143737: goto L_0x0120;
                                                case -1995143736: goto L_0x0115;
                                                case -1995143735: goto L_0x010a;
                                                case -1940626235: goto L_0x00ff;
                                                case -1940626213: goto L_0x00f4;
                                                default: goto L_0x00f2;
                                            }
                                        L_0x00f2:
                                            r4 = -1
                                            goto L_0x014a
                                        L_0x00f4:
                                            java.lang.String r3 = "PCF020"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x00fd
                                            goto L_0x00f2
                                        L_0x00fd:
                                            r4 = 7
                                            goto L_0x014a
                                        L_0x00ff:
                                            java.lang.String r3 = "PCF019"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x0108
                                            goto L_0x00f2
                                        L_0x0108:
                                            r4 = 6
                                            goto L_0x014a
                                        L_0x010a:
                                            java.lang.String r3 = "NFE028"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x0113
                                            goto L_0x00f2
                                        L_0x0113:
                                            r4 = 5
                                            goto L_0x014a
                                        L_0x0115:
                                            java.lang.String r3 = "NFE027"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x011e
                                            goto L_0x00f2
                                        L_0x011e:
                                            r4 = 4
                                            goto L_0x014a
                                        L_0x0120:
                                            java.lang.String r3 = "NFE026"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x0129
                                            goto L_0x00f2
                                        L_0x0129:
                                            r4 = 3
                                            goto L_0x014a
                                        L_0x012b:
                                            java.lang.String r3 = "NFE025"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x0134
                                            goto L_0x00f2
                                        L_0x0134:
                                            r4 = 2
                                            goto L_0x014a
                                        L_0x0136:
                                            java.lang.String r3 = "NFE024"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x013f
                                            goto L_0x00f2
                                        L_0x013f:
                                            r4 = 1
                                            goto L_0x014a
                                        L_0x0141:
                                            java.lang.String r3 = "NFE023"
                                            boolean r6 = r6.equals(r3)
                                            if (r6 != 0) goto L_0x014a
                                            goto L_0x00f2
                                        L_0x014a:
                                            switch(r4) {
                                                case 0: goto L_0x0258;
                                                case 1: goto L_0x0237;
                                                case 2: goto L_0x0216;
                                                case 3: goto L_0x01f4;
                                                case 4: goto L_0x01d2;
                                                case 5: goto L_0x01b0;
                                                case 6: goto L_0x018e;
                                                case 7: goto L_0x016c;
                                                default: goto L_0x014d;
                                            }
                                        L_0x014d:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r2 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r1 = r2.getString(r1)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x016c:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952850(0x7f1304d2, float:1.9542154E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x018e:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952849(0x7f1304d1, float:1.9542152E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x01b0:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952843(0x7f1304cb, float:1.954214E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x01d2:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952842(0x7f1304ca, float:1.9542138E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x01f4:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952841(0x7f1304c9, float:1.9542136E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x0216:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952840(0x7f1304c8, float:1.9542134E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x0237:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952839(0x7f1304c7, float:1.9542132E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x0258:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r0 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            java.lang.String r0 = r0.getString(r2)
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r1 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r2 = 2131952838(0x7f1304c6, float:1.954213E38)
                                            java.lang.String r1 = r1.getString(r2)
                                            r6.showSingleChoicePopUp(r0, r1)
                                            goto L_0x0294
                                        L_0x0279:
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            r6.showPleaseWaitDialog()
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11 r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.this
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment r6 = com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.this
                                            com.jch.racWiFi.CoreActivity r6 = r6.mCoreActivity
                                            com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1$2$1 r0 = new com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment$11$1$2$1
                                            r0.<init>()
                                            r6.refreshToken(r0, r4)
                                        L_0x0294:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceFragment.C189611.C18971.C18992.onChanged(com.jch.racWiFi.genericModels.GenericResponse):void");
                                    }

                                    /* access modifiers changed from: private */
                                    public void callUpdateApi() {
                                        SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().updateGeoFenceSettings(SmartFenceFragment.this.mGeoFencePair);
                                    }
                                });
                            } else if (genericResponse.getResponse() == null) {
                                SmartFenceFragment.this.dismissPleaseWaitDialog();
                                SmartFenceFragment.this.showErrorPopUp(SmartFenceFragment.this.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                            } else if (genericResponse.getResponse().code() != 401) {
                                SmartFenceFragment.this.dismissPleaseWaitDialog();
                                SmartFenceFragment.this.showErrorPopUp(SmartFenceFragment.this.mCoreActivity.getString(R.string.errorCode_alert_somethingWentWorng));
                            } else {
                                SmartFenceFragment.this.mCoreActivity.refreshToken(new CallBackListener() {
                                    public void onFailure() {
                                    }

                                    public void onSuccess() {
                                        C18971.this.callEnableApi();
                                    }
                                }, false);
                            }
                        }

                        /* access modifiers changed from: private */
                        public void callEnableApi() {
                            SmartFenceFragment.this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().enableLocationControl(SmartFenceFragment.this.getViewLifecycleOwner());
                        }
                    });
                    return true;
                }
                SmartFenceFragment smartFenceFragment = SmartFenceFragment.this;
                smartFenceFragment.showSingleChoicePopUp(smartFenceFragment.mCoreActivity.getString(R.string.common_alert), SmartFenceFragment.this.mCoreActivity.getString(R.string.common_alert_unableToConnectToNw));
                return true;
            }
        });
        confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), new SmartFenceFragment$$ExternalSyntheticLambda2(this));
        confirmationDialog.show();
    }

    /* renamed from: lambda$handleSave$9$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ boolean mo30286xb43f0186(Dialog dialog, View view) {
        this.mBinding.switchMainHome.setChecked(this.oldLocationControlsStateModel.enabled);
        if (!this.isClickedOnHamBurger) {
            return true;
        }
        this.iDrawerMenuFunctions.openMenuDrawer();
        this.isClickedOnHamBurger = false;
        return true;
    }

    /* access modifiers changed from: private */
    public void showSingleChoicePopUp(String str, String str2) {
        if (this.mCoreActivity != null) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mCoreActivity);
            singleChoiceDialog.setTitleString(str);
            singleChoiceDialog.setMessageString(str2);
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(this.mCoreActivity.getString(R.string.common_btn_ok), new SmartFenceFragment$$ExternalSyntheticLambda3(singleChoiceDialog));
            singleChoiceDialog.show();
        }
    }

    public ArrayList<OperationMode> commonModeSelectedV2(GeoFencePair geoFencePair) {
        ArrayList<OperationMode> arrayList = new ArrayList<>();
        this.racListByRacId.clear();
        this.racListByCloudId.clear();
        this.racListByCloudId.addAll(geoFencePair.getAssociatedRacCloudId());
        this.racListByRacId.addAll(geoFencePair.getAssociatedRacs());
        this.racListCloudIDToModeMap.clear();
        if (!this.racListByCloudId.isEmpty()) {
            putRacListCloudIdToMap();
            addModeArrayListForRecyclerView(arrayList);
            arrayList.size();
            Iterator it = new ArrayList(arrayList).iterator();
            while (it.hasNext()) {
                OperationMode operationMode = (OperationMode) it.next();
                boolean z = true;
                Iterator<Map.Entry<String, List<RacModelWiseData.RacModeDetail>>> it2 = this.racListCloudIDToModeMap.entrySet().iterator();
                Long l = null;
                String str = null;
                String str2 = null;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mCoreActivity.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId((String) it2.next().getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(operationMode);
                    if (z) {
                        z = false;
                        l = Long.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
                        str2 = racModeDetailBasedOnMode.getTemperatureSettingType().name();
                        str = racModeDetailBasedOnMode.getVisibleTemperatureSetting();
                    } else if (l.longValue() != racModeDetailBasedOnMode.getDefaultHumidity() || !str.equalsIgnoreCase(racModeDetailBasedOnMode.getVisibleTemperatureSetting()) || !str2.equalsIgnoreCase(racModeDetailBasedOnMode.getTemperatureSettingType().name())) {
                        arrayList.remove(operationMode);
                    }
                }
            }
        }
        return arrayList;
    }

    private void addModeArrayListForRecyclerView(ArrayList<OperationMode> arrayList) {
        for (Map.Entry next : this.racListCloudIDToModeMap.entrySet()) {
            int i = 0;
            if (arrayList.size() <= 0) {
                while (i < ((List) next.getValue()).size()) {
                    arrayList.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i)).getMode());
                    i++;
                }
            } else {
                ArrayList arrayList2 = new ArrayList();
                while (i < ((List) next.getValue()).size()) {
                    arrayList2.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i)).getMode());
                    i++;
                }
                arrayList.retainAll(arrayList2);
            }
        }
    }

    private void putRacListCloudIdToMap() {
        List<String> list = this.racListByCloudId;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < this.racListByCloudId.size(); i++) {
                RacModelWiseData racModelWiseDataBasedOnRacTypeId = this.mCoreActivity.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.racListByCloudId.get(i));
                if (racModelWiseDataBasedOnRacTypeId != null) {
                    this.racListCloudIDToModeMap.put(this.racListByCloudId.get(i), new ArrayList(racModelWiseDataBasedOnRacTypeId.getRacModeDetails()));
                }
            }
        }
    }

    private String[] getTempType() {
        String[] strArr = {"", ""};
        Iterator<OperationMode> it = this.modeArrayListForRecyclerView.iterator();
        while (it.hasNext()) {
            OperationMode next = it.next();
            if (next.equals(OperationMode.AUTO)) {
                for (Map.Entry<String, List<RacModelWiseData.RacModeDetail>> key : this.racListCloudIDToModeMap.entrySet()) {
                    RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mCoreActivity.getRacModelWiseDataBasedOnRacTypeId((String) key.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(next);
                    if (racModeDetailBasedOnMode.getMode().equals(OperationMode.AUTO)) {
                        if (racModeDetailBasedOnMode.getTemperatureSetting().equals("ABSOLUTE")) {
                            strArr[0] = "ABSOLUTE";
                        } else if (!racModeDetailBasedOnMode.getTemperatureSetting().equals("RELATIVE") || !racModeDetailBasedOnMode.getVisibleTemperatureSetting().equals("ABSOLUTE")) {
                            strArr[0] = "RELATIVE";
                        } else {
                            strArr[0] = "RELATIVE_ABSOLUTE";
                            strArr[1] = racModeDetailBasedOnMode.getReferenceTemperature() + "";
                        }
                    }
                }
            }
        }
        return strArr;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        hideUiSettings();
        this.locationPermissionViewModel = (LocationPermissionViewModel) ViewModelProviderUtil.getViewModelInstance(this, new LocationPermissionViewModel.LocationPermissionViewModelFactory(this.mCoreActivity.getFusedLocationProviderAPIExtension()), LocationPermissionViewModel.class);
        this.getAllUsersViewModel = (GetAllUsersViewModel) ViewModelProviderUtil.getViewModelInstance(requireActivity(), GetAllUsersViewModel.class);
        MutableLiveData<FamilyIdGeoFenceDataMap> mutableLiveData = this.mGeoFencePairLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.observe(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda9(this));
        }
        logEvent(Screens.SCREENS.name(), 8);
        logEvents(Events.SMART_FENCE_FREQUENCY.name(), 0);
        this.mCoreActivity.mLocationPermissionMutableLiveData.observe(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda10(this));
        this.mCoreActivity.mLocationRationaleMutableLiveData.observe(getViewLifecycleOwner(), new SmartFenceFragment$$ExternalSyntheticLambda13(this));
    }

    /* renamed from: lambda$onViewCreated$11$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30288x5a8a1640(FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap) {
        if (familyIdGeoFenceDataMap != null) {
            GeoFencePair geoFencePair = (GeoFencePair) familyIdGeoFenceDataMap.get(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
            this.mGeoFencePair = geoFencePair;
            if (geoFencePair == null) {
                GeoFencePair parcelClone = GeoFencePair.DEFAULT.parcelClone();
                this.mGeoFencePair = parcelClone;
                parcelClone.isDefault = true;
                this.mGeoFencePair.setLatLng((LatLng) null);
                this.mGeoFencePair.familyId = Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId);
                this.mCoreActivity.getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().put(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId), this.mGeoFencePair);
                this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().sendNullObjectToServer = true;
                this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().sendNullObjectToServer = true;
                calculateAndAddRacsForGeoFenceNullV2();
                CoreActivity coreActivity = this.mCoreActivity;
                if (coreActivity != null && NetworkConnectivity.isNetworkAvailable(coreActivity)) {
                    calculateUSerIfGeofenceIsNullV3();
                }
            } else {
                CoreActivity coreActivity2 = this.mCoreActivity;
                if (coreActivity2 != null && NetworkConnectivity.isNetworkAvailable(coreActivity2)) {
                    calculateUsersIfGeofenceIsNotNullV3();
                }
            }
            commonTask();
        }
    }

    /* renamed from: lambda$onViewCreated$12$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30289xc4b99e5f(String str) {
        if (str == null) {
            hideUiSettings();
        } else if (str.trim().equals(SmartFenceFragment.class.getCanonicalName())) {
            checkLocationService();
        }
    }

    /* renamed from: lambda$onViewCreated$13$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceFragment */
    public /* synthetic */ void mo30290x2ee9267e(Map map) {
        if (map != null) {
            this.mScenario = (Scenario) map.get(SmartFenceFragment.class.getCanonicalName());
            hideUiSettings();
            Scenario scenario = this.mScenario;
            if (scenario == null || !scenario.isRationale() || !this.mScenario.isWithoutLaunch()) {
                Scenario scenario2 = this.mScenario;
                if (scenario2 != null && !scenario2.isWithoutLaunch() && this.mScenario.getResultCode() != 0) {
                    if (!this.mCoreActivity.isForeGroundLocationPermissionGranted() || !this.mCoreActivity.isBackGroundLocationPermissionGranted()) {
                        this.mCoreActivity.openSettings(this.mScenario.getMode());
                    }
                }
            } else if (!this.mCoreActivity.isForeGroundLocationPermissionGranted() || !this.mCoreActivity.isBackGroundLocationPermissionGranted()) {
                this.mCoreActivity.handleRationale(this.mScenario.getMode());
            }
        }
    }
}
