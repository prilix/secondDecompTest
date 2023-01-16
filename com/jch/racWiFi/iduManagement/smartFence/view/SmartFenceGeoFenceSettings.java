package com.jch.racWiFi.iduManagement.smartFence.view;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import com.accord.fusedlocationmodule.FusedLocationProviderAPIExtension;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.SmartFenceArrivingInfoWindowLayoutBinding;
import com.jch.racWiFi.databinding.SmartFenceGeoFenceSettingsFragmentBinding;
import com.jch.racWiFi.databinding.SmartFenceLeavingInfoWindowLayoutBinding;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils.GeoCodingUtil;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils.GoogleMapUtil;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceLayerHolder;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceGeoFenceSettingsModel;
import com.jch.racWiFi.iduManagement.smartFence.view.MyAccounAddressEditDialog;
import com.jch.racWiFi.iduManagement.smartFence.viewModels.SmartFenceSettingsViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;
import com.jch_hitachi.aircloudglobal.R;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SmartFenceGeoFenceSettings extends GenericGeoFenceFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, MyAccounAddressEditDialog.AllFieldsValidation {
    public static boolean isGeoFenceSettingChange;
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolderArriving;
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolderLeaving;
    private CustomInfoWindowAdapter customInfoWindowAdapter;
    private final float disabledAlfa = 0.5f;
    private final float enabledAlfa = 1.0f;
    /* access modifiers changed from: private */
    public GeoFenceLayerHolder geoFenceLayerHolder;
    private boolean isArrivingTab = true;
    private boolean isAutoDetectClicked = false;
    private boolean isFirstTimeOpened = true;
    private boolean isManualEntryDone = false;
    private LocationPermissionViewModel locationPermissionViewModel;
    SmartFenceGeoFenceSettingsFragmentBinding mBinding;
    private FusedLocationProviderAPIExtension mFusedLocationProviderAPIExtension;
    /* access modifiers changed from: private */
    public GeoFencePair mGeoFencePair;
    private GeoFencePair mGeoFencePairOld;
    /* access modifiers changed from: private */
    public final LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            SmartFenceGeoFenceSettings.this.getCoreActivity().removeLocationUpdates(SmartFenceGeoFenceSettings.this.mLocationCallback);
            SmartFenceGeoFenceSettings.this.markMyCurrentLocation(locationResult.getLocations().get(0));
            SmartFenceGeoFenceSettings.this.dismissPleaseWaitDialog();
        }
    };
    /* access modifiers changed from: private */
    public Scenario mScenario;
    private TickSeekBar mSeekBarArriving;
    private TickSeekBar mSeekBarLeaving;
    /* access modifiers changed from: private */
    public TabViewUIHolder mTabViewUIHolder;
    /* access modifiers changed from: private */
    public String mUnit;
    private float previousZoom = 14.0f;
    Observer<SmartFenceGeoFenceSettingsModel> smartFenceGeoFenceSettingsModelView = new Observer<SmartFenceGeoFenceSettingsModel>() {
        public void onChanged(SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel) {
            if (smartFenceGeoFenceSettingsModel.arrivingSettingsAvailable) {
                if (smartFenceGeoFenceSettingsModel.arrivingEnabled) {
                    if (!(SmartFenceGeoFenceSettings.this.geoFenceLayerHolder == null || SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker() == null)) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().showInfoWindow();
                    }
                    SmartFenceGeoFenceSettings.this.makeArrivingViewEnabled();
                    if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null && SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.notFullyDrawn()) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.clearAll();
                        if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                            SmartFenceGeoFenceSettings smartFenceGeoFenceSettings = SmartFenceGeoFenceSettings.this;
                            smartFenceGeoFenceSettings.geoFenceLayerHolder = smartFenceGeoFenceSettings.drawGeoFence(smartFenceGeoFenceSettings.mGeoFencePair);
                            SmartFenceGeoFenceSettings smartFenceGeoFenceSettings2 = SmartFenceGeoFenceSettings.this;
                            smartFenceGeoFenceSettings2.setArrivingAndLeavingProgress(smartFenceGeoFenceSettings2.mGeoFencePair);
                        }
                    }
                } else {
                    SmartFenceGeoFenceSettings.this.makeArrivingViewDisabled();
                }
                SmartFenceGeoFenceSettings.this.mBinding.layoutArriving.setVisibility(0);
                SmartFenceGeoFenceSettings.this.mBinding.layoutLeaving.setVisibility(4);
                SmartFenceGeoFenceSettings.this.mTabViewUIHolder.selectArrivingTab();
                if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null) {
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.addStrokeColorArrivingGeoFence();
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.removeStrokeColorLeavingGeoFence();
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().hideInfoWindow();
                    if (!(SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArriving() == null || SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker() == null)) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArriving().setVisible(smartFenceGeoFenceSettingsModel.arrivingEnabled);
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().setVisible(smartFenceGeoFenceSettingsModel.arrivingEnabled);
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().showInfoWindow();
                    }
                }
            }
            if (smartFenceGeoFenceSettingsModel.leavingSettingsAvailable) {
                if (smartFenceGeoFenceSettingsModel.leavingEnabled) {
                    if (!(SmartFenceGeoFenceSettings.this.geoFenceLayerHolder == null || SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker() == null)) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().showInfoWindow();
                    }
                    SmartFenceGeoFenceSettings.this.makeLeavingViewEnabled();
                    if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null && SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.notFullyDrawn()) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.clearAll();
                        if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                            SmartFenceGeoFenceSettings smartFenceGeoFenceSettings3 = SmartFenceGeoFenceSettings.this;
                            smartFenceGeoFenceSettings3.geoFenceLayerHolder = smartFenceGeoFenceSettings3.drawGeoFence(smartFenceGeoFenceSettings3.mGeoFencePair);
                            SmartFenceGeoFenceSettings smartFenceGeoFenceSettings4 = SmartFenceGeoFenceSettings.this;
                            smartFenceGeoFenceSettings4.setArrivingAndLeavingProgress(smartFenceGeoFenceSettings4.mGeoFencePair);
                        }
                    }
                } else {
                    SmartFenceGeoFenceSettings.this.makeLeavingViewDisabled();
                }
                SmartFenceGeoFenceSettings.this.mBinding.layoutArriving.setVisibility(4);
                SmartFenceGeoFenceSettings.this.mBinding.layoutLeaving.setVisibility(0);
                SmartFenceGeoFenceSettings.this.mTabViewUIHolder.selectLeavingTab();
                if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null) {
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.addStrokeColorLeavingGeoFence();
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.removeStrokeColorArrivingGeoFence();
                    SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().hideInfoWindow();
                    if (!(SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeaving() == null || SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker() == null)) {
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeaving().setVisible(smartFenceGeoFenceSettingsModel.leavingEnabled);
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().setVisible(smartFenceGeoFenceSettingsModel.leavingEnabled);
                        SmartFenceGeoFenceSettings.this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().showInfoWindow();
                    }
                }
            }
            if (smartFenceGeoFenceSettingsModel.arrivingEnabled) {
                SmartFenceGeoFenceSettings.this.makeArrivingViewEnabled();
            } else {
                SmartFenceGeoFenceSettings.this.makeArrivingViewDisabled();
            }
            if (smartFenceGeoFenceSettingsModel.leavingEnabled) {
                SmartFenceGeoFenceSettings.this.makeLeavingViewEnabled();
            } else {
                SmartFenceGeoFenceSettings.this.makeLeavingViewDisabled();
            }
        }
    };
    private SmartFenceSettingsViewModel smartFenceSettingsViewModel;
    UserAddress userAddressForManualEntryPopUp = new UserAddress();

    private String commonLiving(String str, int i, int i2, String str2, String str3) {
        if (i == 0) {
            str = str2;
        }
        return i == i2 + -1 ? str3 : str;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void showManualEntryDialog() {
        MyAccounAddressEditDialog myAccounAddressEditDialog = new MyAccounAddressEditDialog(requireActivity(), R.layout.dialog_address_update, getCoreActivity(), getLifecycle(), getActivity(), this);
        myAccounAddressEditDialog.setParentView(this.mBinding.getRoot());
        myAccounAddressEditDialog.updateAddressView(this.userAddressForManualEntryPopUp);
        myAccounAddressEditDialog.show();
    }

    private void showSingleChoicePopUp(String str, String str2) {
        if (getContext() != null) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getContext());
            singleChoiceDialog.setTitleString(str);
            singleChoiceDialog.setMessageString(str2);
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda9(singleChoiceDialog));
            singleChoiceDialog.show();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getCoreActivity().mLocationRationaleMutableLiveData.postValue(null);
        if (getContext() != null && !NetworkConnectivity.isNetworkAvailable(getContext())) {
            showSingleChoicePopUp(getString(R.string.common_alert), getString(R.string.common_alert_unableToConnectToNw));
        }
        this.locationPermissionViewModel = (LocationPermissionViewModel) ViewModelProviderUtil.getViewModelInstance(this, new LocationPermissionViewModel.LocationPermissionViewModelFactory(getCoreActivity().getFusedLocationProviderAPIExtension()), LocationPermissionViewModel.class);
        this.mGeoFencePair = (GeoFencePair) getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().get(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
        if (Constants.IS_DEMO_MODE) {
            this.previousZoom = 12.0f;
        }
        GeoFencePair geoFencePair = this.mGeoFencePair;
        boolean z = geoFencePair != null ? geoFencePair.isDefault : true;
        GeoFencePair geoFencePair2 = this.mGeoFencePair;
        if (geoFencePair2 != null) {
            GeoFencePair parcelClone = geoFencePair2.parcelClone();
            this.mGeoFencePair = parcelClone;
            this.mGeoFencePairOld = parcelClone.parcelClone();
            this.mGeoFencePair.isDefault = z;
        }
        this.smartFenceSettingsViewModel = (SmartFenceSettingsViewModel) ViewModelProviderUtil.getViewModelInstance(this, SmartFenceSettingsViewModel.class);
        this.mFusedLocationProviderAPIExtension = getCoreActivity().getFusedLocationProviderAPIExtension();
        this.customInfoWindowAdapter = new CustomInfoWindowAdapter(requireActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SmartFenceGeoFenceSettingsFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.smart_fence_geo_fence_settings_fragment, viewGroup, false);
        showPleaseWaitDialog();
        if (getArguments() != null) {
            this.mUnit = getArguments().getString(Constants.Keys.UNIT);
        }
        updateViews();
        dismissPleaseWaitDialog();
        arrivingTickSeekBar(this.mBinding.seekBarContainer);
        leavingTickSeekBar(this.mBinding.seekbarLeaving);
        this.createTooltipContentHolderArriving = new CreateTooltipContentHolder((Context) requireActivity(), (View) this.mBinding.imageButtonHelpArriving, (int) R.string.smartFence_lbl_arrivingLeavingHelp);
        this.createTooltipContentHolderLeaving = new CreateTooltipContentHolder((Context) requireActivity(), (View) this.mBinding.imageButtonHelpLeaving, (int) R.string.smartFence_lbl_arrivingLeavingHelp);
        initClickListeners();
        this.mTabViewUIHolder = new TabViewUIHolder(this.mBinding);
        this.mBinding.textViewLeavingKm.setText(getResources().getString(R.string.dash_dash));
        this.mBinding.textViewArrivingKm.setText(getResources().getString(R.string.dash_dash));
        this.smartFenceSettingsViewModel.getLocationControlsGeoFenceSettingsModelMutableLiveData().observeSingleEvent(getViewLifecycleOwner(), this.smartFenceGeoFenceSettingsModelView);
        this.mBinding.imageButtonCurrentLocation.setEnabled(!com.jch.racWiFi.Constants.IS_DEMO_MODE);
        this.mFusedLocationProviderAPIExtension.getLocationMutableLiveData().observe(getViewLifecycleOwner(), new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda0(this));
        this.mSeekBarArriving.setOnSeekChangeListener(new OnSeekChangeListener() {
            public void onStartTrackingTouch(TickSeekBar tickSeekBar) {
            }

            public void onStopTrackingTouch(TickSeekBar tickSeekBar) {
            }

            public void onSeeking(SeekParams seekParams) {
                if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null) {
                    SmartFenceGeoFenceSettings.this.onArrivingSeekBarChanged(seekParams.progress, true);
                    if (SmartFenceGeoFenceSettings.this.mUnit.equalsIgnoreCase(Constants.Keys.KILO_METER)) {
                        if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                            SmartFenceGeoFenceSettings.this.mGeoFencePair.getArrivingGeoFence().getGeoFenceDynamics().setGeoFenceRadiusInMeters((float) seekParams.progress);
                        }
                    } else if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                        SmartFenceGeoFenceSettings.this.mGeoFencePair.getArrivingGeoFence().getGeoFenceDynamics().setGeoFenceRadiusInMeters((float) Constants.CC.milesToMeters(((double) seekParams.progress) / 1000.0d));
                    }
                }
                if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null && SmartFenceGeoFenceSettings.this.mGeoFencePair.getLatLng() != null) {
                    SmartFenceGeoFenceSettings.this.changeSaveButtonState();
                }
            }
        });
        this.mSeekBarLeaving.setOnSeekChangeListener(new OnSeekChangeListener() {
            public void onStartTrackingTouch(TickSeekBar tickSeekBar) {
            }

            public void onStopTrackingTouch(TickSeekBar tickSeekBar) {
            }

            public void onSeeking(SeekParams seekParams) {
                if (SmartFenceGeoFenceSettings.this.geoFenceLayerHolder != null) {
                    SmartFenceGeoFenceSettings.this.onLeavingSeekBarChanged(seekParams.progress, true);
                    if (SmartFenceGeoFenceSettings.this.mUnit.equalsIgnoreCase(Constants.Keys.KILO_METER)) {
                        if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                            SmartFenceGeoFenceSettings.this.mGeoFencePair.getLeavingGeoFence().getGeoFenceDynamics().setGeoFenceRadiusInMeters((float) seekParams.progress);
                        }
                    } else if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null) {
                        SmartFenceGeoFenceSettings.this.mGeoFencePair.getLeavingGeoFence().getGeoFenceDynamics().setGeoFenceRadiusInMeters((float) Constants.CC.milesToMeters(((double) seekParams.progress) / 1000.0d));
                    }
                }
                if (SmartFenceGeoFenceSettings.this.mGeoFencePair != null && SmartFenceGeoFenceSettings.this.mGeoFencePair.getLatLng() != null) {
                    SmartFenceGeoFenceSettings.this.changeSaveButtonState();
                }
            }
        });
        getCoreActivity().mLocationPermissionMutableLiveData.observe(getViewLifecycleOwner(), new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda4(this));
        getCoreActivity().mLocationRationaleMutableLiveData.observe(getViewLifecycleOwner(), new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda5(this));
        return super.onCreateView(layoutInflater, (ViewGroup) this.mBinding.getRoot(), bundle);
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30307x88f80306(Location location) {
        dismissPleaseWaitDialog();
        this.mFusedLocationProviderAPIExtension.stopFusedLocationCallback();
        markMyCurrentLocation(location);
    }

    /* renamed from: lambda$onCreateView$2$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30308xa368fc25(String str) {
        if (str != null && str.trim().equals(SmartFenceGeoFenceSettings.class.getCanonicalName())) {
            checkLocationService();
        }
    }

    /* renamed from: lambda$onCreateView$3$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30309xbdd9f544(Map map) {
        if (map != null) {
            Scenario scenario = (Scenario) map.get(SmartFenceGeoFenceSettings.class.getCanonicalName());
            this.mScenario = scenario;
            if (scenario == null || !scenario.isRationale() || !this.mScenario.isWithoutLaunch()) {
                Scenario scenario2 = this.mScenario;
                if (scenario2 != null && !scenario2.isWithoutLaunch() && this.mScenario.getResultCode() != 0) {
                    if (!getCoreActivity().isForeGroundLocationPermissionGranted() || !getCoreActivity().isBackGroundLocationPermissionGranted()) {
                        getCoreActivity().openSettings(this.mScenario.getMode());
                    }
                }
            } else if (!getCoreActivity().isForeGroundLocationPermissionGranted() || !getCoreActivity().isBackGroundLocationPermissionGranted()) {
                getCoreActivity().handleRationale(this.mScenario.getMode());
            }
        }
    }

    private void leavingTickSeekBar(LinearLayout linearLayout) {
        String str;
        ArrayList arrayList = new ArrayList();
        int length = com.jch.racWiFi.p010di.util.Constants.arrLivingMeter.length;
        for (int i = 0; i < length; i++) {
            if (this.mUnit.equalsIgnoreCase(Constants.Keys.MILES)) {
                str = commonLiving(String.valueOf(((double) com.jch.racWiFi.p010di.util.Constants.arrLivingMeter[i]) / 1000.0d), i, length, getString(R.string.smartFence_lbl_0_5mi), getString(R.string.smartFence_lbl_1mi));
            } else {
                str = commonLiving(String.valueOf(com.jch.racWiFi.p010di.util.Constants.arrLivingMeter[i]), i, length, getString(R.string.smartFence_lbl_500m), getString(R.string.smartFence_lbl_1km));
            }
            arrayList.add(String.valueOf(str));
        }
        if (getActivity() != null) {
            this.mSeekBarLeaving = TickSeekBar.with(getActivity()).max((float) com.jch.racWiFi.p010di.util.Constants.arrLivingMeter[com.jch.racWiFi.p010di.util.Constants.arrLivingMeter.length - 1]).min((float) com.jch.racWiFi.p010di.util.Constants.arrLivingMeter[0]).progressValueFloat(true).tickCount(arrayList.size()).showTickMarksType(3).tickMarksColor(ContextCompat.getColor(getActivity(), R.color.white)).tickTextsColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).tickTextsSize(12).showTickTextsPosition(2).thumbSize(37).seekSmoothly(false).thumbAutoAdjust(false).onlyThumbDraggable(false).thumbDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_range_slider_circle_grey)).trackProgressColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).build();
            this.mSeekBarLeaving.customTickTexts((String[]) arrayList.toArray(new String[arrayList.size()]));
            linearLayout.addView(this.mSeekBarLeaving);
        }
    }

    private void updateViews() {
        if (this.mUnit.equalsIgnoreCase(Constants.Keys.KILO_METER)) {
            this.mBinding.textViewStartRangeArriving.setText(getResources().getString(R.string.smartFence_lbl_500m));
            this.mBinding.textViewEndRangeArriving.setText(getResources().getString(R.string.smartFence_lbl_10km));
            return;
        }
        this.mBinding.textViewStartRangeArriving.setText(getResources().getString(R.string.smartFence_lbl_0_5mi));
        this.mBinding.textViewEndRangeArriving.setText(getResources().getString(R.string.smartFence_lbl_10mi));
    }

    private void arrivingTickSeekBar(LinearLayout linearLayout) {
        String str;
        ArrayList arrayList = new ArrayList();
        int length = com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length;
        int i = 0;
        while (i < length - 1) {
            int i2 = i + 1;
            int i3 = ((com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[i2] - com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[i]) / 500) - 1;
            if (i == length - 2) {
                i3--;
            }
            if (i == 0) {
                str = "";
            } else {
                str = String.valueOf(i);
            }
            arrayList.add(str);
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add("");
            }
            i = i2;
        }
        if (i == com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length - 1) {
            arrayList.add("");
        }
        if (getActivity() != null) {
            this.mSeekBarArriving = TickSeekBar.with(getActivity()).max((float) com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length - 1]).min((float) com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[0]).progressValueFloat(true).tickCount(arrayList.size() + 1).showTickMarksType(3).tickMarksColor(ContextCompat.getColor(getActivity(), R.color.white)).tickTextsColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).tickTextsSize(12).showTickTextsPosition(2).thumbSize(37).seekSmoothly(false).thumbAutoAdjust(false).onlyThumbDraggable(false).thumbDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_range_slider_circle_grey)).trackProgressColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).build();
            this.mSeekBarArriving.customTickTexts((String[]) arrayList.toArray(new String[arrayList.size()]));
            linearLayout.addView(this.mSeekBarArriving);
        }
    }

    private void arrivingKMTickSeekBar(LinearLayout linearLayout) {
        ArrayList arrayList = new ArrayList();
        int length = com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length;
        int i = 0;
        while (i < length - 1) {
            int i2 = i + 1;
            int i3 = ((com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[i2] - com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[i]) / 100) - 1;
            if (i == length - 2) {
                i3--;
            }
            if (i == 0) {
                arrayList.add("");
            } else {
                arrayList.add(String.valueOf(i));
            }
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add("");
            }
            i = i2;
        }
        if (i == com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length - 1) {
            arrayList.add("");
        }
        if (getActivity() != null) {
            this.mSeekBarArriving = TickSeekBar.with(getActivity()).max((float) com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter.length - 1]).min((float) com.jch.racWiFi.p010di.util.Constants.arrArrivingMeter[0]).progressValueFloat(true).tickCount(arrayList.size() + 1).showTickMarksType(3).tickMarksColor(ContextCompat.getColor(getActivity(), R.color.white)).tickTextsColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).tickTextsSize(12).showTickTextsPosition(2).thumbSize(37).seekSmoothly(false).thumbAutoAdjust(false).onlyThumbDraggable(false).thumbDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_range_slider_circle_grey)).trackProgressColor(ContextCompat.getColor(getActivity(), R.color.textview_color_vd_light)).build();
            this.mSeekBarArriving.customTickTexts((String[]) arrayList.toArray(new String[arrayList.size()]));
            linearLayout.addView(this.mSeekBarArriving);
        }
    }

    public void onStart() {
        super.onStart();
        if (getGoogleMap() == null) {
            getMapView().getMapAsync(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda7(this));
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (SmartFenceGeoFenceSettings.this.createTooltipContentHolderArriving != null && SmartFenceGeoFenceSettings.this.createTooltipContentHolderArriving.isShowing()) {
                    SmartFenceGeoFenceSettings.this.createTooltipContentHolderArriving.dismiss();
                }
                if (SmartFenceGeoFenceSettings.this.createTooltipContentHolderLeaving != null && SmartFenceGeoFenceSettings.this.createTooltipContentHolderLeaving.isShowing()) {
                    SmartFenceGeoFenceSettings.this.createTooltipContentHolderLeaving.dismiss();
                }
                SmartFenceGeoFenceSettings.this.mFragmentToActivityCallback.getNavController().navigateUp();
            }
        });
    }

    /* renamed from: lambda$onStart$5$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30311xaa23c8b9(GoogleMap googleMap) {
        setGoogleMap(googleMap);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            getGoogleMap().getUiSettings().setZoomControlsEnabled(false);
            getGoogleMap().getUiSettings().setAllGesturesEnabled(false);
            getGoogleMap().setMapType(0);
            BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(R.drawable.jch_tokyo_map);
            GeoFencePair geoFencePair = this.mGeoFencePair;
            if (geoFencePair != null) {
                getGoogleMap().addGroundOverlay(new GroundOverlayOptions().image(fromResource).position(geoFencePair.getLatLng(), 15000.0f).transparency(0.0f).zIndex(-1.0f));
                getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(this.mGeoFencePair.getLatLng(), this.previousZoom));
            }
        } else {
            getGoogleMap().getUiSettings().setZoomControlsEnabled(true);
            getGoogleMap().getUiSettings().setMyLocationButtonEnabled(true);
            getGoogleMap().getUiSettings().setCompassEnabled(false);
        }
        this.customInfoWindowAdapter.setSmartFenceGeoFenceSettingsModel(this.smartFenceSettingsViewModel.getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue());
        getGoogleMap().setInfoWindowAdapter(this.customInfoWindowAdapter);
        GoogleMapUtil.moveZoomControls(getMapView());
        GoogleMapUtil.moveCompassButton(getMapView());
        GeoFencePair geoFencePair2 = this.mGeoFencePair;
        if (geoFencePair2 != null) {
            this.smartFenceSettingsViewModel.updateLocationControlsGeoFenceSettingsModel(geoFencePair2);
        }
        GeoFencePair geoFencePair3 = this.mGeoFencePair;
        if (geoFencePair3 == null || geoFencePair3.getLatLng() == null) {
            UserAddress userAddress = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress;
            if (userAddress != null) {
                try {
                    LatLng locationFromAddress = GeoCodingUtil.Reverse.getLocationFromAddress(getCoreActivity(), userAddress);
                    if (locationFromAddress != null) {
                        markMyCurrentLocation(GeoCodingUtil.locationFromLatLng(locationFromAddress));
                    } else {
                        this.mBinding.imageButtonCurrentLocation.performClick();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    this.mBinding.imageButtonCurrentLocation.performClick();
                }
            } else {
                this.mBinding.imageButtonCurrentLocation.performClick();
            }
        } else {
            markMyCurrentLocationWithLatLangFromGeofencePair();
        }
        this.smartFenceSettingsViewModel.selectedArrivingTab();
        getGoogleMap().setOnCameraIdleListener(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda6(this));
    }

    /* renamed from: lambda$onStart$4$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30310x8fb2cf9a() {
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            if (this.isAutoDetectClicked || this.isFirstTimeOpened || this.isManualEntryDone) {
                this.isAutoDetectClicked = false;
                this.isFirstTimeOpened = false;
                this.isManualEntryDone = false;
                return;
            }
            float f = getGoogleMap().getCameraPosition().zoom;
            if (f == this.previousZoom) {
                markMyCurrentLocationWithLatLangOnCameraIdle(getGoogleMap().getCameraPosition().target);
            } else {
                this.previousZoom = f;
            }
        }
    }

    private void initClickListeners() {
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.tabArriving.setOnClickListener(this);
        this.mBinding.tabLeaving.setOnClickListener(this);
        this.mBinding.imageButtonCurrentLocation.setOnClickListener(this);
        this.mBinding.textViewSave.setOnClickListener(this);
        this.mBinding.imageButtonHelpArriving.setOnClickListener(this);
        this.mBinding.imageButtonHelpLeaving.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                CreateTooltipContentHolder createTooltipContentHolder = this.createTooltipContentHolderArriving;
                if (createTooltipContentHolder != null && createTooltipContentHolder.isShowing()) {
                    this.createTooltipContentHolderArriving.dismiss();
                }
                CreateTooltipContentHolder createTooltipContentHolder2 = this.createTooltipContentHolderLeaving;
                if (createTooltipContentHolder2 != null && createTooltipContentHolder2.isShowing()) {
                    this.createTooltipContentHolderLeaving.dismiss();
                }
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            case R.id.image_button_current_location:
                if (!getCoreActivity().isForeGroundLocationPermissionGranted() || !getCoreActivity().isBackGroundLocationPermissionGranted()) {
                    this.locationPermissionViewModel.needsResolution(new AlertListener() {
                        public void onNegative() {
                        }

                        public void onPositive() {
                            if (SmartFenceGeoFenceSettings.this.mScenario != null && SmartFenceGeoFenceSettings.this.mScenario.isRationale()) {
                                SmartFenceGeoFenceSettings.this.getCoreActivity().handleRationale(SmartFenceGeoFenceSettings.this.mScenario.getMode());
                            } else if (SmartFenceGeoFenceSettings.this.mScenario == null || SmartFenceGeoFenceSettings.this.mScenario.isRationale()) {
                                SmartFenceGeoFenceSettings.this.checkPermissions();
                            } else {
                                SmartFenceGeoFenceSettings.this.getCoreActivity().openSettings(SmartFenceGeoFenceSettings.this.mScenario.getMode());
                            }
                        }
                    }, false, getCoreActivity(), LocationAccessRationale.SMART_FENCE);
                    return;
                } else {
                    checkPermissions();
                    return;
                }
            case R.id.image_button_edit:
                showManualEntryDialog();
                return;
            case R.id.image_button_help_arriving:
                showArrivingInfo();
                return;
            case R.id.image_button_help_leaving:
                showLeavingInfo();
                return;
            case R.id.tab_arriving:
                this.isArrivingTab = true;
                this.smartFenceSettingsViewModel.selectedArrivingTab();
                return;
            case R.id.tab_leaving:
                this.isArrivingTab = false;
                this.smartFenceSettingsViewModel.selectedLeavingTab();
                return;
            case R.id.text_view_save:
                if (!NetworkConnectivity.isNetworkAvailable(getContext())) {
                    showSingleChoicePopUp(getString(R.string.common_alert), getString(R.string.common_alert_unableToConnectToNw));
                    return;
                }
                if (this.mGeoFencePair != null) {
                    getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().put(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId), this.mGeoFencePair);
                }
                isGeoFenceSettingChange = true;
                SmartFenceFragment.isAnySettingsChanged = true;
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void checkPermissions() {
        if (getActivity() == null || NetworkConnectivity.isNetworkAvailable(getActivity())) {
            getCoreActivity().checkLocationPermissions(SmartFenceGeoFenceSettings.class.getCanonicalName());
        } else {
            GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
        }
    }

    /* access modifiers changed from: private */
    public void checkLocationService() {
        showPleaseWaitDialog();
        getCoreActivity().verifyLocationService(getCoreActivity(), this.mLocationCallback, new LocationServiceListener() {
            public void onActivityResult() {
                SmartFenceGeoFenceSettings.this.checkLocationService();
            }

            public void onNegative() {
                SmartFenceGeoFenceSettings.this.dismissPleaseWaitDialog();
            }
        });
    }

    private void showLeavingInfo() {
        if (!this.createTooltipContentHolderLeaving.isShowing()) {
            this.createTooltipContentHolderLeaving.setHintCasePosition(3);
            this.createTooltipContentHolderLeaving.setBorderRes(R.drawable.tool_tip_without_radius);
            this.createTooltipContentHolderLeaving.setBordercolor(R.color.colorRed);
            this.createTooltipContentHolderLeaving.setHitCaseYOffset(R.dimen.hint_case_y_offset);
            this.createTooltipContentHolderLeaving.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
            this.createTooltipContentHolderLeaving.buildWithNoDimensions();
            this.createTooltipContentHolderLeaving.show();
        } else {
            this.createTooltipContentHolderLeaving.dismiss();
        }
        new Handler().postDelayed(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda11(this), JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    /* renamed from: lambda$showLeavingInfo$6$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30313x740ec416() {
        CreateTooltipContentHolder createTooltipContentHolder = this.createTooltipContentHolderLeaving;
        if (createTooltipContentHolder != null && createTooltipContentHolder.isShowing()) {
            this.createTooltipContentHolderLeaving.dismiss();
        }
    }

    private void showArrivingInfo() {
        if (!this.createTooltipContentHolderArriving.isShowing()) {
            this.createTooltipContentHolderArriving.setHintCasePosition(3);
            this.createTooltipContentHolderArriving.setBorderRes(R.drawable.tool_tip_without_radius);
            this.createTooltipContentHolderArriving.setBordercolor(R.color.colorRed);
            this.createTooltipContentHolderArriving.setHitCaseYOffset(R.dimen.hint_case_y_offset);
            this.createTooltipContentHolderArriving.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
            this.createTooltipContentHolderArriving.buildWithNoDimensions();
            this.createTooltipContentHolderArriving.show();
        } else {
            this.createTooltipContentHolderArriving.dismiss();
        }
        new Handler().postDelayed(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda10(this), JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    /* renamed from: lambda$showArrivingInfo$7$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30312x453807a1() {
        CreateTooltipContentHolder createTooltipContentHolder = this.createTooltipContentHolderArriving;
        if (createTooltipContentHolder != null && createTooltipContentHolder.isShowing()) {
            this.createTooltipContentHolderArriving.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void makeArrivingViewDisabled() {
        this.mSeekBarArriving.setEnabled(false);
        this.mBinding.textViewStartRangeArriving.setAlpha(0.5f);
        this.mBinding.textViewEndRangeArriving.setAlpha(0.5f);
        this.mBinding.textViewSetGeofenceRangeTitleArriving.setAlpha(0.5f);
        this.mBinding.textViewSetGeofenceRangeArriving.setVisibility(4);
        this.mBinding.textViewArrivingKm.setVisibility(4);
    }

    /* access modifiers changed from: private */
    public void makeArrivingViewEnabled() {
        this.mSeekBarArriving.setEnabled(true);
        this.mBinding.textViewStartRangeArriving.setAlpha(1.0f);
        this.mBinding.textViewEndRangeArriving.setAlpha(1.0f);
        this.mBinding.textViewSetGeofenceRangeTitleArriving.setAlpha(1.0f);
        this.mBinding.textViewSetGeofenceRangeArriving.setVisibility(0);
        this.mBinding.textViewArrivingKm.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void makeLeavingViewEnabled() {
        this.mSeekBarLeaving.setEnabled(true);
        this.mBinding.textViewSetGeofenceRangeTitleLeaving.setAlpha(1.0f);
        this.mBinding.textViewSetGeofenceRangeLeaving.setVisibility(0);
        this.mBinding.textViewLeavingKm.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void makeLeavingViewDisabled() {
        this.mSeekBarLeaving.setEnabled(false);
        this.mBinding.textViewSetGeofenceRangeTitleLeaving.setAlpha(0.5f);
        this.mBinding.textViewSetGeofenceRangeLeaving.setVisibility(4);
        this.mBinding.textViewLeavingKm.setVisibility(4);
    }

    public void onAllFieldsAreValidate(UserAddress userAddress) {
        this.isManualEntryDone = true;
        markMyCurrentLocationAfterManualEntry(userAddress);
    }

    private static class TabViewUIHolder {
        private final Context context;
        private final SmartFenceGeoFenceSettingsFragmentBinding sma;

        public TabViewUIHolder(SmartFenceGeoFenceSettingsFragmentBinding smartFenceGeoFenceSettingsFragmentBinding) {
            this.context = smartFenceGeoFenceSettingsFragmentBinding.getRoot().getContext();
            this.sma = smartFenceGeoFenceSettingsFragmentBinding;
        }

        public void selectArrivingTab() {
            this.sma.textViewArrivingKm.setTextColor(this.context.getResources().getColor(R.color.colorRed));
            this.sma.textViewArrivingTitle.setTextColor(this.context.getResources().getColor(R.color.colorRed));
            this.sma.imageViewArriving.setColorFilter(this.context.getResources().getColor(R.color.colorRed));
            this.sma.arrivingSelectionHighlight.setVisibility(0);
            this.sma.tabArriving.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            this.sma.textViewLeavingKm.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.textViewLeavingTitle.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.imageViewLeaving.setColorFilter(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.tabLeaving.setBackgroundColor(this.context.getResources().getColor(R.color.color_lightest_grey));
            this.sma.leavingSelectionHighlight.setVisibility(4);
        }

        public void selectLeavingTab() {
            this.sma.textViewLeavingKm.setTextColor(this.context.getResources().getColor(R.color.colorRed));
            this.sma.textViewLeavingTitle.setTextColor(this.context.getResources().getColor(R.color.colorRed));
            this.sma.imageViewLeaving.setColorFilter(this.context.getResources().getColor(R.color.colorRed));
            this.sma.leavingSelectionHighlight.setVisibility(0);
            this.sma.tabLeaving.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            this.sma.textViewArrivingKm.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.textViewArrivingTitle.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.imageViewArriving.setColorFilter(this.context.getResources().getColor(R.color.textview_color_vd_light));
            this.sma.tabArriving.setBackgroundColor(this.context.getResources().getColor(R.color.color_lightest_grey));
            this.sma.arrivingSelectionHighlight.setVisibility(4);
        }
    }

    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final SmartFenceArrivingInfoWindowLayoutBinding arrivingInfoWindowLayoutBinding;
        private final SmartFenceLeavingInfoWindowLayoutBinding leavingInfoWindowLayoutBinding;
        private SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel;

        public View getInfoContents(Marker marker) {
            return null;
        }

        public void setSmartFenceGeoFenceSettingsModel(SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel2) {
            this.smartFenceGeoFenceSettingsModel = smartFenceGeoFenceSettingsModel2;
        }

        public SmartFenceArrivingInfoWindowLayoutBinding getArrivingInfoWindowLayoutBinding() {
            return this.arrivingInfoWindowLayoutBinding;
        }

        public SmartFenceLeavingInfoWindowLayoutBinding getLeavingInfoWindowLayoutBinding() {
            return this.leavingInfoWindowLayoutBinding;
        }

        public CustomInfoWindowAdapter(Context context) {
            LayoutInflater from = LayoutInflater.from(context);
            SmartFenceArrivingInfoWindowLayoutBinding smartFenceArrivingInfoWindowLayoutBinding = (SmartFenceArrivingInfoWindowLayoutBinding) DataBindingUtil.inflate(from, R.layout.smart_fence_arriving_info_window_layout, (ViewGroup) null, false);
            this.arrivingInfoWindowLayoutBinding = smartFenceArrivingInfoWindowLayoutBinding;
            SmartFenceLeavingInfoWindowLayoutBinding smartFenceLeavingInfoWindowLayoutBinding = (SmartFenceLeavingInfoWindowLayoutBinding) DataBindingUtil.inflate(from, R.layout.smart_fence_leaving_info_window_layout, (ViewGroup) null, false);
            this.leavingInfoWindowLayoutBinding = smartFenceLeavingInfoWindowLayoutBinding;
            smartFenceLeavingInfoWindowLayoutBinding.imageViewLeaving.setColorFilter(context.getResources().getColor(R.color.colorRed));
            smartFenceArrivingInfoWindowLayoutBinding.imageViewLeaving.setColorFilter(context.getResources().getColor(R.color.colorRed));
            smartFenceLeavingInfoWindowLayoutBinding.textViewLeavingKm.setText(String.format(SmartFenceGeoFenceSettings.this.getString(R.string.smartFence_lbl_leavingkm), new Object[]{"--"}));
            smartFenceArrivingInfoWindowLayoutBinding.textViewLeavingKm.setText(String.format(SmartFenceGeoFenceSettings.this.getString(R.string.smartFence_lbl_arrivingkm), new Object[]{"--"}));
        }

        public View getInfoWindow(Marker marker) {
            if (this.smartFenceGeoFenceSettingsModel.arrivingSettingsAvailable) {
                return this.arrivingInfoWindowLayoutBinding.getRoot();
            }
            if (this.smartFenceGeoFenceSettingsModel.leavingSettingsAvailable) {
                return this.leavingInfoWindowLayoutBinding.getRoot();
            }
            return null;
        }
    }

    private void autoDetectMyLocation() {
        this.mFusedLocationProviderAPIExtension.getLastKnownLocation(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda8(this));
    }

    /* renamed from: lambda$autoDetectMyLocation$8$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30306x2aeccaac(Location location) {
        if (location == null) {
            showPleaseWaitDialog();
            this.mFusedLocationProviderAPIExtension.requestForLocation(getLifecycle());
            return;
        }
        markMyCurrentLocation(location);
    }

    /* access modifiers changed from: private */
    public void markMyCurrentLocation(Location location) {
        LatLng latLngFromLocation = GeoCodingUtil.latLngFromLocation(location);
        GeoFencePair geoFencePair = this.mGeoFencePair;
        if (geoFencePair != null) {
            geoFencePair.setLatLng(latLngFromLocation);
        }
        if (this.geoFenceLayerHolder == null) {
            GeoFencePair geoFencePair2 = this.mGeoFencePair;
            if (geoFencePair2 != null) {
                this.geoFenceLayerHolder = drawGeoFence(geoFencePair2);
            }
        } else {
            GeoFencePair geoFencePair3 = this.mGeoFencePair;
            if (geoFencePair3 != null) {
                setArrivingAndLeavingProgress(geoFencePair3);
            }
            this.geoFenceLayerHolder.updateArrivingCircleRadius(this.mSeekBarArriving.getProgress());
            this.geoFenceLayerHolder.updateLeavingCircleRadius(this.mSeekBarLeaving.getProgress());
            this.geoFenceLayerHolder.updateCenterForGeoFenceCircles(latLngFromLocation, this.mSeekBarArriving.getProgress(), this.mSeekBarLeaving.getProgress());
            this.isAutoDetectClicked = true;
            GeoFencePair geoFencePair4 = this.mGeoFencePair;
            if (geoFencePair4 != null) {
                getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(geoFencePair4.getLatLng(), this.previousZoom));
                setProgress(this.mGeoFencePair);
            }
        }
        updateTextView(latLngFromLocation);
        if (this.isArrivingTab) {
            this.smartFenceSettingsViewModel.selectedArrivingTab();
        } else {
            this.smartFenceSettingsViewModel.selectedLeavingTab();
        }
    }

    private void markMyCurrentLocationWithLatLangOnCameraIdle(LatLng latLng) {
        GeoFencePair geoFencePair;
        GeoFencePair geoFencePair2 = this.mGeoFencePair;
        if (geoFencePair2 != null) {
            geoFencePair2.setLatLng(latLng);
        }
        if (this.geoFenceLayerHolder == null && (geoFencePair = this.mGeoFencePair) != null) {
            this.geoFenceLayerHolder = drawGeoFence(geoFencePair);
        }
        GeoFenceLayerHolder geoFenceLayerHolder2 = this.geoFenceLayerHolder;
        if (geoFenceLayerHolder2 != null) {
            geoFenceLayerHolder2.updateCenterForGeoFenceCircles(latLng, this.mSeekBarArriving.getProgress(), this.mSeekBarLeaving.getProgress());
            GeoFencePair geoFencePair3 = this.mGeoFencePair;
            if (geoFencePair3 != null) {
                setProgress(geoFencePair3);
            }
        }
        updateTextView(latLng);
    }

    private void markMyCurrentLocationWithLatLangFromGeofencePair() {
        GeoFencePair geoFencePair = this.mGeoFencePair;
        if (geoFencePair != null) {
            LatLng latLng = geoFencePair.getLatLng();
            if (this.geoFenceLayerHolder == null) {
                this.geoFenceLayerHolder = drawGeoFence(this.mGeoFencePair);
            }
            if (this.geoFenceLayerHolder != null) {
                setArrivingAndLeavingProgress(this.mGeoFencePair);
                this.geoFenceLayerHolder.updateArrivingCircleRadius(this.mSeekBarArriving.getProgress());
                this.geoFenceLayerHolder.updateLeavingCircleRadius(this.mSeekBarLeaving.getProgress());
                this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().hideInfoWindow();
                this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().showInfoWindow();
                getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(this.mGeoFencePair.getLatLng(), this.previousZoom));
                setProgress(this.mGeoFencePair);
            }
            updateTextView(latLng);
        }
    }

    private void markMyCurrentLocationAfterManualEntry(UserAddress userAddress) {
        LatLng latLng;
        GeoFencePair geoFencePair;
        try {
            latLng = GeoCodingUtil.Reverse.getLocationFromAddress(getCoreActivity(), userAddress);
        } catch (IOException e) {
            e.printStackTrace();
            latLng = null;
        }
        if (latLng != null && (geoFencePair = this.mGeoFencePair) != null) {
            geoFencePair.setLatLng(latLng);
            if (this.geoFenceLayerHolder == null) {
                this.geoFenceLayerHolder = drawGeoFence(this.mGeoFencePair);
            }
            GeoFenceLayerHolder geoFenceLayerHolder2 = this.geoFenceLayerHolder;
            if (geoFenceLayerHolder2 != null) {
                geoFenceLayerHolder2.updateCenterForGeoFenceCircles(latLng, this.mSeekBarArriving.getProgress(), this.mSeekBarLeaving.getProgress());
                setProgress(this.mGeoFencePair);
                getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, getGoogleMap().getCameraPosition().zoom));
            }
            updateTextViewOnManualEntry(userAddress);
        }
    }

    /* access modifiers changed from: private */
    public void setArrivingAndLeavingProgress(GeoFencePair geoFencePair) {
        onArrivingSeekBarChanged(geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().intValue(), false);
        onLeavingSeekBarChanged(geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().intValue(), false);
    }

    /* access modifiers changed from: private */
    public void onArrivingSeekBarChanged(int i, boolean z) {
        GeoFenceLayerHolder geoFenceLayerHolder2 = this.geoFenceLayerHolder;
        if (geoFenceLayerHolder2 != null) {
            geoFenceLayerHolder2.updateArrivingCircleRadius(this.mSeekBarArriving.getProgress());
            String arrivingRange = Constants.CC.getArrivingRange((double) i, this.mUnit, z);
            this.mBinding.textViewSetGeofenceRangeArriving.setText(arrivingRange);
            this.mBinding.textViewArrivingKm.setText(arrivingRange);
            this.customInfoWindowAdapter.getArrivingInfoWindowLayoutBinding().textViewLeavingKm.setText(String.format(getString(R.string.smartFence_lbl_arrivingkm), new Object[]{arrivingRange}));
            this.geoFenceLayerHolder.getCircleArrivingTopInvisibleMarker().showInfoWindow();
        }
    }

    /* access modifiers changed from: private */
    public void onLeavingSeekBarChanged(int i, boolean z) {
        GeoFenceLayerHolder geoFenceLayerHolder2 = this.geoFenceLayerHolder;
        if (geoFenceLayerHolder2 != null) {
            geoFenceLayerHolder2.updateLeavingCircleRadius(this.mSeekBarLeaving.getProgress());
            String leavingRange = Constants.CC.getLeavingRange((double) i, this.mUnit, z);
            this.mBinding.textViewSetGeofenceRangeLeaving.setText(leavingRange);
            this.mBinding.textViewLeavingKm.setText(leavingRange);
            this.customInfoWindowAdapter.getLeavingInfoWindowLayoutBinding().textViewLeavingKm.setText(String.format(getString(R.string.smartFence_lbl_leavingkm), new Object[]{leavingRange}));
            this.geoFenceLayerHolder.getCircleLeavingTopInvisibleMarker().showInfoWindow();
        }
    }

    private void updateTextView(LatLng latLng) {
        changeSaveButtonState();
        BackgroundExecutor.post(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda12(this, latLng));
    }

    /* renamed from: lambda$updateTextView$10$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30314x84eca5c5(LatLng latLng) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            try {
                Address addressFromLatLong = GeoCodingUtil.GeoCode.getAddressFromLatLong((Context) requireActivity(), latLng);
                UserAddress userAddress = new UserAddress();
                if (addressFromLatLong != null) {
                    userAddress.parseAddress(addressFromLatLong);
                    this.userAddressForManualEntryPopUp.parseAddress(addressFromLatLong);
                }
                BackgroundExecutor.postOnMainThread(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda1(this, userAddress));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: lambda$updateTextView$9$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30315x1a336803(UserAddress userAddress) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            return;
        }
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            TextView textView = this.mBinding.textViewAddress;
            textView.setText(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.toString() + "," + getString(R.string.common_lbl_japanCountryName));
            return;
        }
        this.mBinding.textViewAddress.setText(userAddress.toString());
    }

    private void updateTextViewOnManualEntry(UserAddress userAddress) {
        changeSaveButtonState();
        BackgroundExecutor.post(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda3(this, userAddress));
    }

    /* renamed from: lambda$updateTextViewOnManualEntry$12$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30317xf92820a0(UserAddress userAddress) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            BackgroundExecutor.postOnMainThread(new SmartFenceGeoFenceSettings$$ExternalSyntheticLambda2(this, userAddress));
        }
    }

    /* renamed from: lambda$updateTextViewOnManualEntry$11$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceGeoFenceSettings */
    public /* synthetic */ void mo30316xdeb72781(UserAddress userAddress) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            this.userAddressForManualEntryPopUp.copy(userAddress);
            this.mBinding.textViewAddress.setText(userAddress.toString());
        }
    }

    private void setProgress(GeoFencePair geoFencePair) {
        this.mSeekBarArriving.setProgress(Float.parseFloat(Constants.CC.getProgress(Constants.CC.getArrivingRange((double) geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue(), this.mUnit, false))));
        this.mSeekBarLeaving.setProgress(Float.parseFloat(Constants.CC.getProgress(Constants.CC.getLeavingRange((double) geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue(), this.mUnit, false))));
    }

    /* access modifiers changed from: private */
    public void changeSaveButtonState() {
        GeoFencePair geoFencePair = this.mGeoFencePair;
        if (geoFencePair == null) {
            return;
        }
        if (geoFencePair.hasChanged(this.mGeoFencePairOld)) {
            this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.colorRed));
            this.mBinding.textViewSave.setEnabled(true);
            return;
        }
        this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.textview_color_vd_light));
        this.mBinding.textViewSave.setEnabled(false);
    }
}
