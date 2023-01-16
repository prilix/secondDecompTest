package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.accord.fusedlocationmodule.FusedLocationProviderAPIExtension;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.FragMyAccountAddressBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.countryCodeManager.Country;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.presenter.IncludeAddressDetailsPresenterV2;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class MyAccountAddressFragment extends GenericFragment implements IncludeAddressDetailsPresenterV2.IIncludeAddressDetailsPresenter {
    private boolean addressApiAlreadyCalled;
    /* access modifiers changed from: private */
    public String deviceCountryCode;
    /* access modifiers changed from: private */
    public FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension;
    private boolean isAutoDetect;
    private boolean isLocationUpdatesFromFinishClick = false;
    private boolean isProhibitedCountrySelected = false;
    /* access modifiers changed from: private */
    public final Handler locationCheckHandler = new Handler();
    private String[] locationPermissionArr;
    /* access modifiers changed from: private */
    public FragMyAccountAddressBinding mBinding;
    private IncludeAddressDetailsPresenterV2 mIncludeAddressPresenter;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    /* access modifiers changed from: private */
    public final LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            MyAccountAddressFragment.this.getCoreActivity().removeLocationUpdates(MyAccountAddressFragment.this.mLocationCallback);
            MyAccountAddressFragment.this.updateAddress(locationResult.getLocations().get(0));
            MyAccountAddressFragment.this.mProgressDialog.dismiss();
        }
    };
    private LocationPermissionViewModel mLocationPermissionViewModel;
    /* access modifiers changed from: private */
    public CustomProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public Scenario mScenario;
    private UserAddress mUserAddress;
    /* access modifiers changed from: private */
    public String selectedCountryCode;
    private SingleChoiceDialog singleChoiceDialog;

    static /* synthetic */ boolean lambda$onNetworkCallFailure$7(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ void lambda$showSelectCountryDialog$9(DialogInterface dialogInterface) {
    }

    static /* synthetic */ boolean lambda$validateAndSaveAddress$5(Dialog dialog, View view) {
        return true;
    }

    @Deprecated
    public void allFieldsValidated1(UserAddress userAddress) {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public void streetAreaFieldEmptyCallback() {
    }

    public static MyAccountAddressFragment newInstance() {
        MyAccountAddressFragment myAccountAddressFragment = new MyAccountAddressFragment();
        myAccountAddressFragment.setNewBundleAsArgument();
        return myAccountAddressFragment;
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.fusedLocationProviderAPIExtension = new FusedLocationProviderAPIExtension((Context) requireActivity(), FusedLocationProviderAPIExtension.LocationRequestType.HIGH_ACCURACY_ONE_SECOND_INTERVAL);
        getCoreActivity().mLocationRationaleMutableLiveData.postValue(null);
        this.singleChoiceDialog = new SingleChoiceDialog(getActivity());
        this.mProgressDialog = new CustomProgressDialog(getActivity());
        CustomProgressDialog.AttributeSet attributeSet = new CustomProgressDialog.AttributeSet();
        attributeSet.setMessage(getString(R.string.common_alert_pleaseWait));
        attributeSet.setCancelable(false);
        this.mProgressDialog.importFromAttributeSet(attributeSet);
        this.mProgressDialog.setOnBackPressedListener(new MyAccountAddressFragment$$ExternalSyntheticLambda9(this));
        this.locationPermissionArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION};
        this.mIncludeAddressPresenter = new IncludeAddressDetailsPresenterV2(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mBinding = (FragMyAccountAddressBinding) DataBindingUtil.inflate(layoutInflater, R.layout.frag_my_account_address, viewGroup, false);
        this.mLocationPermissionViewModel = (LocationPermissionViewModel) ViewModelProviderUtil.getViewModelInstance(this, new LocationPermissionViewModel.LocationPermissionViewModelFactory(getCoreActivity().getFusedLocationProviderAPIExtension()), LocationPermissionViewModel.class);
        init();
        this.fusedLocationProviderAPIExtension.getLocationMutableLiveData().observe(getViewLifecycleOwner(), new MyAccountAddressFragment$$ExternalSyntheticLambda6(this));
        this.mUserAddress = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress;
        setAddress();
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mBinding.includeBody.includeSubBody.editTextAddressLine1);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mBinding.includeBody.includeSubBody.addressLineBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.addTextChangedListener(genericEmptyEditTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher2 = new GenericEmptyEditTextWatcher(this.mBinding.includeBody.includeSubBody.editTextState);
        genericEmptyEditTextWatcher2.setLayoutToolTip(this.mBinding.includeBody.includeSubBody.enterStateBubbleLayout);
        genericEmptyEditTextWatcher2.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.includeBody.includeSubBody.editTextState.addTextChangedListener(genericEmptyEditTextWatcher2);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher3 = new GenericEmptyEditTextWatcher(this.mBinding.includeBody.includeSubBody.editTextCity);
        genericEmptyEditTextWatcher3.setLayoutToolTip(this.mBinding.includeBody.includeSubBody.enterCityBubbleLayout);
        genericEmptyEditTextWatcher3.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.includeBody.includeSubBody.editTextCity.addTextChangedListener(genericEmptyEditTextWatcher3);
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextAddressLine1.lengthFilterAddressLine()});
        this.mBinding.includeBody.includeSubBody.editTextStreetArea.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextStreetArea.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextStreetArea.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextCity.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextCity.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextCity.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextCity.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextCity.setAddressInputFilter(), this.mBinding.includeBody.includeSubBody.editTextCity.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextState.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextState.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextState.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextState.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextState.setAddressInputFilter(), this.mBinding.includeBody.includeSubBody.editTextState.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextZipCode.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextZipCode.lengthFilterZipCode()});
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode() != null) {
            Country byNameCodeFromCustomCountries = CountryUtils.getByNameCodeFromCustomCountries(getContext(), CountryUtils.getAllCountries(getContext()), UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode());
            if (byNameCodeFromCustomCountries == null) {
                showSelectCountryDialog();
            } else if (isProhibitedCountry(getString(byNameCodeFromCustomCountries.getName()))) {
                hideAutoDetectButton();
                this.selectedCountryCode = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
            }
        } else {
            showSelectCountryDialog();
        }
        getCoreActivity().mLocationPermissionMutableLiveData.observe(getViewLifecycleOwner(), new MyAccountAddressFragment$$ExternalSyntheticLambda7(this));
        getCoreActivity().mLocationRationaleMutableLiveData.observe(getViewLifecycleOwner(), new MyAccountAddressFragment$$ExternalSyntheticLambda8(this));
        return this.mBinding.getRoot();
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment */
    public /* synthetic */ void mo32925x39df6c34(String str) {
        if (str != null && str.trim().equals(MyAccountAddressFragment.class.getCanonicalName())) {
            checkLocationService();
        }
    }

    /* renamed from: lambda$onCreateView$2$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment */
    public /* synthetic */ void mo32926xce1ddbd3(Map map) {
        if (map != null) {
            Scenario scenario = (Scenario) map.get(MyAccountAddressFragment.class.getCanonicalName());
            this.mScenario = scenario;
            if (scenario == null || !scenario.isRationale() || !this.mScenario.isWithoutLaunch()) {
                Scenario scenario2 = this.mScenario;
                if (scenario2 != null && !scenario2.isRationale() && !this.mScenario.isWithoutLaunch() && this.mScenario.getResultCode() == 0 && !getCoreActivity().isForeGroundLocationPermissionGranted()) {
                    getCoreActivity().showPermissionDeniedDialog(getCoreActivity(), this.mBinding.getRoot(), new AlertListener() {
                        public void onNegative() {
                        }

                        public void onPositive() {
                            MyAccountAddressFragment.this.getCoreActivity().openSettings(MyAccountAddressFragment.this.mScenario.getMode());
                        }
                    });
                    return;
                }
                return;
            }
            getCoreActivity().handleRationale(this.mScenario.getMode());
        }
    }

    private void init() {
        this.mBinding.includeToolbar.toolbarTitle.setText(R.string.myAcc_lbl_myAcc);
        this.mBinding.includeToolbar.backButton.setVisibility(0);
        this.mBinding.includeToolbar.textViewSave.setVisibility(0);
        this.mBinding.includeBody.textViewStep4Of4.setVisibility(8);
        this.mBinding.includeBody.textViewEnterAddressDetails.setText(R.string.myAcc_lbl_myAdd);
        this.mBinding.includeBody.includeSubBody.buttonContinue.setVisibility(8);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includeToolbar.textViewSave.setOnClickListener(new MyAccountAddressFragment$$ExternalSyntheticLambda5(this));
        this.mBinding.includeToolbar.backButton.setOnClickListener(new MyAccountAddressFragment$$ExternalSyntheticLambda3(this));
        this.mBinding.includeBody.includeSubBody.layoutAutoDetectMyLocation.setOnClickListener(new MyAccountAddressFragment$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment */
    public /* synthetic */ void mo32927x401ad5e8(View view) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment */
    public /* synthetic */ void mo32928xd4594587(View view) {
        this.isAutoDetect = true;
        onClickAutoDetect();
    }

    public void onClickContinue(View view) {
        String str;
        String str2;
        String str3;
        String str4;
        if (!NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
            return;
        }
        Editable text = this.mBinding.includeBody.includeSubBody.editTextAddressLine1.getText();
        Editable text2 = this.mBinding.includeBody.includeSubBody.editTextStreetArea.getText();
        Editable text3 = this.mBinding.includeBody.includeSubBody.editTextCity.getText();
        Editable text4 = this.mBinding.includeBody.includeSubBody.editTextState.getText();
        Editable text5 = this.mBinding.includeBody.includeSubBody.editTextZipCode.getText();
        IncludeAddressDetailsPresenterV2 includeAddressDetailsPresenterV2 = this.mIncludeAddressPresenter;
        if (text != null) {
            str = text.toString().trim();
        } else {
            str = "";
        }
        if (text2 != null) {
            str2 = text2.toString().trim();
        } else {
            str2 = "";
        }
        if (text3 != null) {
            str3 = text3.toString().trim();
        } else {
            str3 = "";
        }
        if (text4 != null) {
            str4 = text4.toString().trim();
        } else {
            str4 = "";
        }
        includeAddressDetailsPresenterV2.validateFields(str, str2, str3, str4, text5 != null ? text5.toString().trim() : "");
    }

    public void onClickAutoDetect() {
        logEvents(Events.CLICK_AUTO_DETECT_MY_ACCOUNT.name(), 0);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            return;
        }
        if (!getCoreActivity().isForeGroundLocationPermissionGranted()) {
            this.mLocationPermissionViewModel.needsResolution(new AlertListener() {
                public void onNegative() {
                }

                public void onPositive() {
                    if (MyAccountAddressFragment.this.mScenario != null && MyAccountAddressFragment.this.mScenario.isRationale()) {
                        MyAccountAddressFragment.this.getCoreActivity().handleRationale(MyAccountAddressFragment.this.mScenario.getMode());
                    } else if (MyAccountAddressFragment.this.mScenario == null || MyAccountAddressFragment.this.mScenario.isRationale()) {
                        MyAccountAddressFragment.this.checkPermissions();
                    } else {
                        MyAccountAddressFragment.this.getCoreActivity().openSettings(MyAccountAddressFragment.this.mScenario.getMode());
                    }
                }
            }, false, getCoreActivity(), LocationAccessRationale.ACCOUNT);
        } else {
            checkPermissions();
        }
    }

    /* access modifiers changed from: private */
    public void checkPermissions() {
        if (getActivity() == null || NetworkConnectivity.isNetworkAvailable(getActivity())) {
            getCoreActivity().checkLocationPermissions(MyAccountAddressFragment.class.getCanonicalName());
        } else {
            GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
        }
    }

    /* access modifiers changed from: private */
    public void checkLocationService() {
        this.mProgressDialog.show();
        getCoreActivity().verifyLocationService(getCoreActivity(), this.mLocationCallback, new LocationServiceListener() {
            public void onActivityResult() {
                MyAccountAddressFragment.this.checkLocationService();
            }

            public void onNegative() {
                MyAccountAddressFragment.this.mProgressDialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateAddress(Location location) {
        Log.e(GenericFragment.TAG, "updateAddress: lat - " + location.getLatitude() + ", long - " + location.getLongitude());
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> fromLocation = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (fromLocation.isEmpty()) {
                Toaster.makeToast(getActivity(), getString(R.string.common_alert_unableFetchLocation), 0);
            } else if (this.isAutoDetect) {
                this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setText(fromLocation.get(0).getFeatureName() != null ? fromLocation.get(0).getFeatureName() : fromLocation.get(0).getSubLocality());
                this.mBinding.includeBody.includeSubBody.editTextStreetArea.setText(fromLocation.get(0).getSubLocality());
                this.mBinding.includeBody.includeSubBody.editTextCity.setText(fromLocation.get(0).getLocality());
                this.mBinding.includeBody.includeSubBody.editTextState.setText(fromLocation.get(0).getAdminArea());
                this.mBinding.includeBody.includeSubBody.editTextZipCode.setText(fromLocation.get(0).getPostalCode());
            } else {
                verifyZipCode1(geocoder, fromLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verifyZipCode1(Geocoder geocoder, List<Address> list) throws IOException {
        Editable text = this.mBinding.includeBody.includeSubBody.editTextZipCode.getText();
        if (text != null) {
            String obj = text.toString();
            if (obj.isEmpty()) {
                saveValues(list.get(0).getCountryCode(), (String) null);
                this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), list.get(0).getCountryCode(), (String) null);
                return;
            }
            if (list.get(0).getCountryCode().equalsIgnoreCase("jp") && obj.length() == 7) {
                StringBuilder sb = new StringBuilder(obj);
                sb.insert(3, Constants.FCM.DASH);
                obj = sb.toString();
            }
            List<Address> fromLocationName = geocoder.getFromLocationName(obj + ", " + list.get(0).getCountryCode(), 1);
            if (fromLocationName == null || fromLocationName.isEmpty()) {
                dismissPleaseWaitDialog();
                saveValues(list.get(0).getCountryCode(), list.get(0).getPostalCode());
                zipCodeAlertBanner(list.get(0).getCountryCode(), obj);
            } else if (list.get(0).getCountryCode().equalsIgnoreCase(fromLocationName.get(0).getCountryCode())) {
                saveValues(fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
                this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
            } else {
                dismissPleaseWaitDialog();
                saveValues(fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
                zipCodeAlertBanner(fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
            }
        }
    }

    private void saveValues(String str, String str2) {
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setZipCode(str2);
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setCountryCode(str);
    }

    private void zipCodeAlertBanner(String str, String str2) {
        Log.e(GenericFragment.TAG, "zipCodeAlertBanner: countryCode : " + str + ", zipCode : " + str2);
        if (this.mBinding.includeBody.includeZipCode.rootView.getVisibility() == 0) {
            this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), str, str2);
        } else {
            this.mBinding.includeBody.includeZipCode.rootView.setVisibility(0);
        }
    }

    private void setAddress() {
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setText(this.mUserAddress.getAddressLine());
        this.mBinding.includeBody.includeSubBody.editTextStreetArea.setText(this.mUserAddress.getStreet());
        this.mBinding.includeBody.includeSubBody.editTextCity.setText(this.mUserAddress.getCity());
        this.mBinding.includeBody.includeSubBody.editTextState.setText(this.mUserAddress.getState());
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setText(this.mUserAddress.getZipCode());
    }

    @Deprecated
    public void onClickSave() {
        if (!this.addressApiAlreadyCalled) {
            validateAndSaveAddress();
        }
    }

    @Deprecated
    private void validateAndSaveAddress() {
        String trim = this.mBinding.includeBody.includeSubBody.editTextAddressLine1.getText().toString().trim();
        String trim2 = this.mBinding.includeBody.includeSubBody.editTextStreetArea.getText().toString().trim();
        String trim3 = this.mBinding.includeBody.includeSubBody.editTextCity.getText().toString().trim();
        String trim4 = this.mBinding.includeBody.includeSubBody.editTextState.getText().toString().trim();
        String trim5 = this.mBinding.includeBody.includeSubBody.editTextZipCode.getText().toString().trim();
        if (!this.mIncludeAddressPresenter.validateFields(trim, trim2, trim3, trim4, trim5)) {
            return;
        }
        if (this.isProhibitedCountrySelected) {
            this.addressApiAlreadyCalled = true;
            this.mProgressDialog.show();
            this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), this.selectedCountryCode, trim5);
            return;
        }
        this.isLocationUpdatesFromFinishClick = true;
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            final Geocoder geocoder = new Geocoder(getActivity());
            final String obj = this.mBinding.includeBody.includeSubBody.editTextZipCode.getText().toString();
            if (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode() != null) {
                this.deviceCountryCode = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
                verifyZipCode(geocoder, obj);
            } else if (checkPermissions(this.locationPermissionArr)) {
                this.fusedLocationProviderAPIExtension.checkLocationServiceEnabled(new OnSuccessListener<LocationSettingsResponse>() {
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        MyAccountAddressFragment.this.fusedLocationProviderAPIExtension.getLastKnownLocation(new MyAccountAddressFragment$5$$ExternalSyntheticLambda0(this, geocoder, obj));
                    }

                    /* renamed from: lambda$onSuccess$1$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment$5 */
                    public /* synthetic */ void mo32934x9e2d8f3b(Geocoder geocoder, String str, Location location) {
                        if (location != null) {
                            MyAccountAddressFragment myAccountAddressFragment = MyAccountAddressFragment.this;
                            myAccountAddressFragment.deviceCountryCode = myAccountAddressFragment.getDeviceCountryCode(geocoder, location);
                            MyAccountAddressFragment.this.verifyZipCode(geocoder, str);
                            return;
                        }
                        MyAccountAddressFragment.this.mProgressDialog.show();
                        MyAccountAddressFragment.this.fusedLocationProviderAPIExtension.requestForLocation(MyAccountAddressFragment.this.getLifecycle());
                        MyAccountAddressFragment.this.locationCheckHandler.removeCallbacksAndMessages((Object) null);
                        MyAccountAddressFragment.this.locationCheckHandler.postDelayed(new MyAccountAddressFragment$5$$ExternalSyntheticLambda1(this), 15000);
                    }

                    /* renamed from: lambda$onSuccess$0$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment$5 */
                    public /* synthetic */ void mo32933x1fcc8b5c() {
                        MyAccountAddressFragment.this.mo32924xfddf33fa();
                        MyAccountAddressFragment myAccountAddressFragment = MyAccountAddressFragment.this;
                        myAccountAddressFragment.showSingleChoicePopUp(myAccountAddressFragment.getString(R.string.common_alert_unableFetchLocation));
                    }
                }, new OnFailureListener() {
                    public void onFailure(Exception exc) {
                        MyAccountAddressFragment myAccountAddressFragment = MyAccountAddressFragment.this;
                        myAccountAddressFragment.showSingleChoicePopUp(myAccountAddressFragment.getString(R.string.common_alert_enableLocationService));
                    }
                });
            } else {
                requestPermissions(this.locationPermissionArr, 177);
            }
        } else {
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), MyAccountAddressFragment$$ExternalSyntheticLambda2.INSTANCE);
            singleChoiceDialog2.show();
        }
    }

    private String addHypenForJapanRegion(String str) {
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode() != null) {
            if (!UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode().equalsIgnoreCase("jp") || str.length() != 7) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            sb.insert(3, Constants.FCM.DASH);
            return sb.toString();
        } else if (!this.deviceCountryCode.equalsIgnoreCase("jp") || str.length() != 7) {
            return str;
        } else {
            StringBuilder sb2 = new StringBuilder(str);
            sb2.insert(3, Constants.FCM.DASH);
            return sb2.toString();
        }
    }

    /* access modifiers changed from: private */
    public void verifyZipCode(Geocoder geocoder, String str) {
        this.mProgressDialog.show();
        String addHypenForJapanRegion = addHypenForJapanRegion(str);
        try {
            List<Address> fromLocationName = geocoder.getFromLocationName(addHypenForJapanRegion + ", " + this.deviceCountryCode, 1);
            if (fromLocationName == null || fromLocationName.isEmpty()) {
                this.mProgressDialog.dismiss();
                showSingleChoicePopUp(getString(R.string.common_alert_invalidZipCode));
                return;
            }
            Address address = fromLocationName.get(0);
            address.getPostalCode();
            String countryCode = address.getCountryCode();
            if (countryCode == null || countryCode.isEmpty()) {
                this.mProgressDialog.dismiss();
                showSingleChoicePopUp(getString(R.string.common_alert_unableToGetZipCode));
                return;
            }
            saveChanges(countryCode, address.getCountryName(), address.getPostalCode());
        } catch (IOException e) {
            Toast.makeText(getContext(), getString(R.string.common_alert_unableToConnectToNw), 1).show();
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public String getDeviceCountryCode(Geocoder geocoder, Location location) {
        try {
            ArrayList arrayList = new ArrayList(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1));
            if (!arrayList.isEmpty()) {
                this.deviceCountryCode = ((Address) arrayList.get(0)).getCountryCode();
            }
        } catch (IOException e) {
            Toast.makeText(getContext(), getString(R.string.common_alert_unableToConnectToNw), 1).show();
            e.printStackTrace();
        }
        return this.deviceCountryCode;
    }

    private void saveChanges(String str, String str2, String str3) {
        String countryCode = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode();
        if (countryCode != null) {
            if (countryCode.equals(str)) {
                this.selectedCountryCode = str;
                this.addressApiAlreadyCalled = true;
                this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), str, str3);
                return;
            }
            this.mProgressDialog.dismiss();
            showSingleChoicePopUp(getString(R.string.common_alert_invalidZipCode));
        } else if (str.equalsIgnoreCase(this.deviceCountryCode)) {
            this.selectedCountryCode = str;
            this.addressApiAlreadyCalled = true;
            this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), str, str3);
        } else {
            this.mProgressDialog.dismiss();
            showSingleChoicePopUp(getString(R.string.common_alert_invalidZipCode));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        mo32924xfddf33fa();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mProgressDialog.setOnBackPressedListener((CustomProgressDialog.OnBackPressedProgressDialog) null);
    }

    public void addressLine1EmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setText("");
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setErrorBackgroundDrawable();
    }

    public void stateFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextState.setText("");
        this.mBinding.includeBody.includeSubBody.editTextState.setErrorBackgroundDrawable();
    }

    public void cityFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextCity.setText("");
        this.mBinding.includeBody.includeSubBody.editTextCity.setErrorBackgroundDrawable();
    }

    public void zipCodeFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setText("");
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setErrorBackgroundDrawable();
    }

    public void allFieldsValidated(UserAddress userAddress) {
        if (this.isProhibitedCountrySelected) {
            showPleaseWaitDialog();
            this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), this.selectedCountryCode, userAddress.getZipCode());
            return;
        }
        this.isAutoDetect = false;
        onClickAutoDetect();
    }

    public void onAddressUpdateSuccess(UserAddress userAddress) {
        this.mProgressDialog.dismiss();
        this.addressApiAlreadyCalled = false;
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setAddressLine(userAddress.getAddressLine());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setStreet(userAddress.getStreet());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setCity(userAddress.getCity());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setState(userAddress.getState());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setZipCode(userAddress.getZipCode());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setCountryCode(userAddress.getCountryCode());
        SingleChoiceDialog singleChoiceDialog2 = this.singleChoiceDialog;
        if (singleChoiceDialog2 != null && singleChoiceDialog2.isShowing()) {
            this.singleChoiceDialog.dismiss();
        }
        showSingleChoicePopUp2(getString(R.string.myAcc_alert_addressUpdatedSuccessfully)).setPositiveButton(getString(R.string.common_btn_ok), new MyAccountAddressFragment$$ExternalSyntheticLambda11(this));
    }

    /* renamed from: lambda$onAddressUpdateSuccess$6$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment */
    public /* synthetic */ boolean mo32923xd6be5b0a(Dialog dialog, View view) {
        dialog.dismiss();
        getActivity().onBackPressed();
        return false;
    }

    public void onAddressUpdateFailure(GenericResponse genericResponse) {
        this.mProgressDialog.dismiss();
        this.addressApiAlreadyCalled = false;
        if (genericResponse.getResponse().code() != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountAddressFragment.this.callAddressAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callAddressAPI() {
        dismissPleaseWaitDialog();
        this.mBinding.includeBody.includeSubBody.editTextZipCode.getText().toString().trim();
        this.mIncludeAddressPresenter.updateAddressOnServer(getViewLifecycleOwner(), this.mUserAddress.getCountryCode(), this.mUserAddress.getZipCode());
    }

    public void onLocationAcquired(Location location) {
        this.locationCheckHandler.removeCallbacksAndMessages((Object) null);
        this.fusedLocationProviderAPIExtension.stopFusedLocationCallback();
        if (this.isLocationUpdatesFromFinishClick) {
            this.isLocationUpdatesFromFinishClick = false;
            String addHypenForJapanRegion = addHypenForJapanRegion(this.mBinding.includeBody.includeSubBody.editTextZipCode.getText().toString().trim());
            Geocoder geocoder = new Geocoder(getActivity());
            this.deviceCountryCode = getDeviceCountryCode(geocoder, location);
            verifyZipCode(geocoder, addHypenForJapanRegion);
            return;
        }
        updateAddressData(location);
    }

    private void updateAddressData(Location location) {
        final Geocoder geocoder;
        final double latitude = location.getLatitude();
        final double longitude = location.getLongitude();
        Locale locale = getLocale();
        if (locale != null) {
            geocoder = new Geocoder(getActivity(), locale);
        } else {
            geocoder = new Geocoder(getActivity());
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    final ArrayList arrayList = new ArrayList(geocoder.getFromLocation(latitude, longitude, 1));
                    MyAccountAddressFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            if (!arrayList.isEmpty()) {
                                UserAddress userAddress = new UserAddress();
                                userAddress.parseAddress((Address) arrayList.get(0));
                                if (userAddress.getAddressLine() == null) {
                                    userAddress.setAddressLine(userAddress.getAddressLine());
                                }
                                MyAccountAddressFragment.this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setText(userAddress.getAddressLine());
                                MyAccountAddressFragment.this.mBinding.includeBody.includeSubBody.editTextStreetArea.setText(userAddress.getStreet());
                                MyAccountAddressFragment.this.mBinding.includeBody.includeSubBody.editTextCity.setText(userAddress.getCity());
                                MyAccountAddressFragment.this.mBinding.includeBody.includeSubBody.editTextState.setText(userAddress.getState());
                                MyAccountAddressFragment.this.mBinding.includeBody.includeSubBody.editTextZipCode.setText(userAddress.getZipCode());
                            } else {
                                MyAccountAddressFragment.this.showSingleChoicePopUp(MyAccountAddressFragment.this.getString(R.string.common_alert_unableFetchLocation));
                            }
                            MyAccountAddressFragment.this.mo32924xfddf33fa();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    MyAccountAddressFragment.this.getActivity().runOnUiThread(new MyAccountAddressFragment$8$$ExternalSyntheticLambda0(this));
                }
            }

            /* renamed from: lambda$run$0$com-jch-racWiFi-userManagement-view-MyAccountAddressFragment$8 */
            public /* synthetic */ void mo32936xf69bb898() {
                MyAccountAddressFragment.this.mo32924xfddf33fa();
                MyAccountAddressFragment myAccountAddressFragment = MyAccountAddressFragment.this;
                myAccountAddressFragment.showSingleChoicePopUp(myAccountAddressFragment.getString(R.string.common_alert_somethingWentWrong));
            }
        }).start();
    }

    public void onNetworkCallFailure(Throwable th) {
        this.addressApiAlreadyCalled = false;
        this.mProgressDialog.dismiss();
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), MyAccountAddressFragment$$ExternalSyntheticLambda1.INSTANCE);
            singleChoiceDialog2.show();
        }
        dismissPleaseWaitDialog();
    }

    private Locale getLocale() {
        return LocaleConfiguration.getCurrentAppLocale();
    }

    /* access modifiers changed from: private */
    public void showSingleChoicePopUp(String str) {
        if (getActivity() != null) {
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(str);
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), new MyAccountAddressFragment$$ExternalSyntheticLambda10(singleChoiceDialog2));
            singleChoiceDialog2.show();
        }
    }

    private SingleChoiceDialog showSingleChoicePopUp2(String str) {
        this.singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        this.singleChoiceDialog.setMessageString(str);
        this.singleChoiceDialog.setCancelable(false);
        if (!this.singleChoiceDialog.isShowing()) {
            this.singleChoiceDialog.show();
        }
        return this.singleChoiceDialog;
    }

    private void showSelectCountryDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                MyAccountAddressFragment myAccountAddressFragment = MyAccountAddressFragment.this;
                if (myAccountAddressFragment.isProhibitedCountry(myAccountAddressFragment.getString(countryCodeUIConfiguration.getCountryName()))) {
                    MyAccountAddressFragment.this.hideAutoDetectButton();
                    MyAccountAddressFragment myAccountAddressFragment2 = MyAccountAddressFragment.this;
                    myAccountAddressFragment2.selectedCountryCode = myAccountAddressFragment2.getString(countryCodeUIConfiguration.getCountryNameShortForm());
                }
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.setOnShowListener(MyAccountAddressFragment$$ExternalSyntheticLambda0.INSTANCE);
        countryCodeDialog.show();
        countryCodeDialog.findViewById(R.id.image_button_clear).setVisibility(8);
        countryCodeDialog.setCancelable(false);
    }

    /* access modifiers changed from: private */
    public boolean isProhibitedCountry(String str) {
        boolean isCountryProhibited = CountryUtils.ProhibitedCountryUtils.isCountryProhibited(str);
        this.isProhibitedCountrySelected = isCountryProhibited;
        return isCountryProhibited;
    }

    /* access modifiers changed from: private */
    public void hideAutoDetectButton() {
        this.mBinding.includeBody.includeSubBody.layoutAutoDetectMyLocation.setVisibility(8);
        this.mBinding.includeBody.includeSubBody.view5.setVisibility(8);
        this.mBinding.includeBody.includeSubBody.view7.setVisibility(8);
        this.mBinding.includeBody.includeSubBody.textViewOr.setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* renamed from: stopLocationRelatedTasks */
    public void mo32924xfddf33fa() {
        this.fusedLocationProviderAPIExtension.stopFusedLocationCallback();
        this.mProgressDialog.dismiss();
        this.locationCheckHandler.removeCallbacksAndMessages((Object) null);
    }
}
