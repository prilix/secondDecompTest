package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.amplitude.model.Scenario;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.FragCreateAccountStep4Binding;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.p010di.util.Constants;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class CreateAccountStep4Fragment extends GenericFragment implements IncludeAddressDetailsPresenterV2.IIncludeAddressDetailsPresenter, TextWatcher {
    private static final String TAG = "CreateAccountStep4Fragment";
    private boolean isAutoDetect;
    /* access modifiers changed from: private */
    public boolean isPhoneOptionSelected;
    private boolean isProhibitedCountrySelected = false;
    private FragCreateAccountStep4Binding mBinding;
    private long mEnterTime;
    private IncludeAddressDetailsPresenterV2 mIncludeAddressDetailsPresenter;
    private boolean mIsAnyFieldChanged;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    /* access modifiers changed from: private */
    public final LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            CreateAccountStep4Fragment.this.getCoreActivity().removeLocationUpdates(CreateAccountStep4Fragment.this.mLocationCallback);
            CreateAccountStep4Fragment.this.updateAddress(locationResult.getLocations().get(0));
            CreateAccountStep4Fragment.this.mProgressDialog.dismiss();
        }
    };
    private LocationPermissionViewModel mLocationPermissionViewModel;
    /* access modifiers changed from: private */
    public CustomProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public Scenario mScenario;
    private String selectedCountryCode;
    private String selectedCountryName;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public void streetAreaFieldEmptyCallback() {
    }

    public static CreateAccountStep4Fragment newInstance() {
        CreateAccountStep4Fragment createAccountStep4Fragment = new CreateAccountStep4Fragment();
        createAccountStep4Fragment.setNewBundleAsArgument();
        return createAccountStep4Fragment;
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        getCoreActivity().mLocationRationaleMutableLiveData.postValue(null);
        this.isPhoneOptionSelected = arguments.getBoolean(Constants.IS_PHONE_OPTION_SELECTED);
        this.selectedCountryName = getString(arguments.getInt(Constants.SELECTED_COUNTRY_NAME));
        this.selectedCountryCode = getString(arguments.getInt(Constants.SELECTED_COUNTRY_CODE));
        this.mProgressDialog = new CustomProgressDialog(getActivity());
        CustomProgressDialog.AttributeSet attributeSet = new CustomProgressDialog.AttributeSet();
        attributeSet.setMessage(getString(R.string.common_alert_pleaseWait));
        attributeSet.setCancelable(false);
        this.mProgressDialog.importFromAttributeSet(attributeSet);
        this.mProgressDialog.setOnBackPressedListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda2(this));
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (!CreateAccountStep4Fragment.this.isPhoneOptionSelected) {
                    CreateAccountStep4Fragment.this.showSelectCountryDialog();
                }
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33083xccfce5b7() {
        getCoreActivity().stopLocationBroadcast();
        this.mProgressDialog.dismiss();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mBinding = (FragCreateAccountStep4Binding) DataBindingUtil.inflate(layoutInflater, R.layout.frag_create_account_step_4, viewGroup, false);
        this.mLocationPermissionViewModel = (LocationPermissionViewModel) ViewModelProviderUtil.getViewModelInstance(this, new LocationPermissionViewModel.LocationPermissionViewModelFactory(getCoreActivity().getFusedLocationProviderAPIExtension()), LocationPermissionViewModel.class);
        this.mEnterTime = System.currentTimeMillis();
        init();
        this.mIncludeAddressDetailsPresenter = new IncludeAddressDetailsPresenterV2(this);
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
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.addTextChangedListener(this);
        this.mBinding.includeBody.includeSubBody.editTextState.addTextChangedListener(this);
        this.mBinding.includeBody.includeSubBody.editTextCity.addTextChangedListener(this);
        this.mBinding.includeBody.includeSubBody.buttonContinue.setEnabled(false);
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextAddressLine1.lengthFilterAddressLine()});
        this.mBinding.includeBody.includeSubBody.editTextStreetArea.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextStreetArea.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextStreetArea.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextCity.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextCity.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextCity.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextCity.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextCity.setAddressInputFilter(), this.mBinding.includeBody.includeSubBody.editTextCity.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextState.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextState.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextState.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextState.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextState.setAddressInputFilter(), this.mBinding.includeBody.includeSubBody.editTextState.lengthFilter()});
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setFilters(new InputFilter[]{this.mBinding.includeBody.includeSubBody.editTextZipCode.setEmojisInputFilter(), this.mBinding.includeBody.includeSubBody.editTextZipCode.lengthFilterZipCode()});
        if (!this.isPhoneOptionSelected) {
            showSelectCountryDialog();
        } else if (isProhibitedCountry(this.selectedCountryName)) {
            hideAutoDetectButton(8);
        }
        logEvent(Screens.SCREENS.name(), 18);
        getCoreActivity().mLocationPermissionMutableLiveData.observe(getViewLifecycleOwner(), new CreateAccountStep4Fragment$$ExternalSyntheticLambda14(this));
        getCoreActivity().mLocationRationaleMutableLiveData.observe(getViewLifecycleOwner(), new CreateAccountStep4Fragment$$ExternalSyntheticLambda1(this));
        return this.mBinding.getRoot();
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33084xc1575bbd(String str) {
        if (str != null && str.trim().equals(CreateAccountStep4Fragment.class.getCanonicalName())) {
            checkLocationService();
        }
    }

    /* renamed from: lambda$onCreateView$2$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33085xf505867e(Map map) {
        if (map != null) {
            Scenario scenario = (Scenario) map.get(CreateAccountStep4Fragment.class.getCanonicalName());
            this.mScenario = scenario;
            if (scenario == null || !scenario.isRationale() || !this.mScenario.isWithoutLaunch()) {
                Scenario scenario2 = this.mScenario;
                if (scenario2 != null && !scenario2.isRationale() && !this.mScenario.isWithoutLaunch() && this.mScenario.getResultCode() == 0 && !getCoreActivity().isForeGroundLocationPermissionGranted()) {
                    getCoreActivity().showPermissionDeniedDialog(getCoreActivity(), this.mBinding.getRoot(), new AlertListener() {
                        public void onNegative() {
                        }

                        public void onPositive() {
                            CreateAccountStep4Fragment.this.getCoreActivity().openSettings(CreateAccountStep4Fragment.this.mScenario.getMode());
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
        this.mBinding.includeToolbar.toolbarTitle.setText(R.string.createAccount_lbl_createAccount);
        this.mBinding.includeToolbar.backButton.setVisibility(4);
        this.mBinding.includeToolbar.textViewSave.setVisibility(4);
        this.mBinding.includeBody.textViewStep4Of4.setVisibility(0);
        this.mBinding.includeBody.textViewEnterAddressDetails.setText(R.string.createAccount_lbl_enterAddressDetails);
        this.mBinding.includeBody.includeSubBody.buttonContinue.setVisibility(0);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includeBody.includeSubBody.buttonContinue.setOnClickListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda8(this));
        this.mBinding.includeToolbar.backButton.setOnClickListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda6(this));
        this.mBinding.includeBody.includeSubBody.layoutAutoDetectMyLocation.setOnClickListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda7(this));
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setOnFocusChangeListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda9(this));
        this.mBinding.includeBody.includeSubBody.editTextState.setOnFocusChangeListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda10(this));
        this.mBinding.includeBody.includeSubBody.editTextCity.setOnFocusChangeListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda11(this));
        this.mBinding.includeBody.includeSubBody.editTextStreetArea.setOnFocusChangeListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda12(this));
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setOnFocusChangeListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda13(this));
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33087xbaa05689(View view) {
        onBackButtonPressed();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33088xee4e814a(View view) {
        this.isAutoDetect = true;
        onClickAutoDetect();
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33089x21fcac0b(View view, boolean z) {
        postEvent(z, Events.STEP_4_ADDRESS_EDIT_ZIP.name());
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33090x55aad6cc(View view, boolean z) {
        postEvent(z, Events.STEP_4_ADDRESS_EDIT_STATE.name());
    }

    /* renamed from: lambda$onViewCreated$7$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33091x8959018d(View view, boolean z) {
        postEvent(z, Events.STEP_4_ADDRESS_EDIT_CITY.name());
    }

    /* renamed from: lambda$onViewCreated$8$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33092xbd072c4e(View view, boolean z) {
        postEvent(z, Events.STEP_4_ADDRESS_EDIT_STREET.name());
    }

    /* renamed from: lambda$onViewCreated$9$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33093xf0b5570f(View view, boolean z) {
        postEvent(z, Events.STEP_4_ADDRESS_EDIT_ADDRESS_LINE_1.name());
    }

    private boolean isProhibitedCountry(String str) {
        boolean isCountryProhibited = CountryUtils.ProhibitedCountryUtils.isCountryProhibited(str);
        this.isProhibitedCountrySelected = isCountryProhibited;
        return isCountryProhibited;
    }

    /* access modifiers changed from: private */
    public void showSelectCountryDialog() {
        CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda5(this, countryCodeDialog));
        countryCodeDialog.setOnShowListener(new CreateAccountStep4Fragment$$ExternalSyntheticLambda0(this));
        countryCodeDialog.show();
        countryCodeDialog.findViewById(R.id.image_button_clear).setVisibility(8);
        countryCodeDialog.setCancelable(false);
    }

    /* renamed from: lambda$showSelectCountryDialog$10$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33094xfa94a9ee(CountryCodeDialog countryCodeDialog, View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
        if (isProhibitedCountry(getString(countryCodeUIConfiguration.getCountryName()))) {
            hideAutoDetectButton(8);
            this.selectedCountryCode = getString(countryCodeUIConfiguration.getCountryNameShortForm());
        } else {
            hideAutoDetectButton(0);
        }
        countryCodeDialog.dismiss();
    }

    /* renamed from: lambda$showSelectCountryDialog$11$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ void mo33095x2e42d4af(DialogInterface dialogInterface) {
        this.mBinding.getRoot().setBackgroundResource(R.drawable.white_blur);
    }

    private void hideAutoDetectButton(int i) {
        this.mBinding.includeBody.includeSubBody.layoutAutoDetectMyLocation.setVisibility(i);
        this.mBinding.includeBody.includeSubBody.view5.setVisibility(i);
        this.mBinding.includeBody.includeSubBody.view7.setVisibility(i);
        this.mBinding.includeBody.includeSubBody.textViewOr.setVisibility(i);
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
        IncludeAddressDetailsPresenterV2 includeAddressDetailsPresenterV2 = this.mIncludeAddressDetailsPresenter;
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

    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    public void onBackButtonPressed() {
        logEvents(Events.QUIT_ADDRESS_SET_UP_STEP_4.name(), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onClickAutoDetect() {
        if (this.isAutoDetect) {
            logEvents(Events.CLICK_AUTO_DETECT_STEP_4.name(), 0);
        }
        if (Constants.IS_DEMO_MODE) {
            return;
        }
        if (!getCoreActivity().isForeGroundLocationPermissionGranted()) {
            this.mLocationPermissionViewModel.needsResolution(new AlertListener() {
                public void onNegative() {
                }

                public void onPositive() {
                    if (CreateAccountStep4Fragment.this.mScenario != null && CreateAccountStep4Fragment.this.mScenario.isRationale()) {
                        CreateAccountStep4Fragment.this.getCoreActivity().handleRationale(CreateAccountStep4Fragment.this.mScenario.getMode());
                    } else if (CreateAccountStep4Fragment.this.mScenario == null || CreateAccountStep4Fragment.this.mScenario.isRationale()) {
                        CreateAccountStep4Fragment.this.checkPermissions();
                    } else {
                        CreateAccountStep4Fragment.this.getCoreActivity().openSettings(CreateAccountStep4Fragment.this.mScenario.getMode());
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
            getCoreActivity().checkLocationPermissions(CreateAccountStep4Fragment.class.getCanonicalName());
        } else {
            GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
        }
    }

    /* access modifiers changed from: private */
    public void checkLocationService() {
        this.mProgressDialog.show();
        getCoreActivity().verifyLocationService(getCoreActivity(), this.mLocationCallback, new LocationServiceListener() {
            public void onActivityResult() {
                CreateAccountStep4Fragment.this.checkLocationService();
            }

            public void onNegative() {
                CreateAccountStep4Fragment.this.mProgressDialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateAddress(Location location) {
        String str = TAG;
        Log.e(str, "updateAddress: lat - " + location.getLatitude() + ", long - " + location.getLongitude());
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
                verifyZipCode(geocoder, fromLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mIncludeAddressDetailsPresenter.removeCallbacks();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mProgressDialog.setOnBackPressedListener((CustomProgressDialog.OnBackPressedProgressDialog) null);
    }

    public void addressLine1EmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setErrorBackgroundDrawable();
    }

    public void stateFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextState.setErrorBackgroundDrawable();
    }

    public void cityFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextCity.setErrorBackgroundDrawable();
    }

    public void zipCodeFieldEmptyCallback() {
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setErrorBackgroundDrawable();
    }

    public void allFieldsValidated(UserAddress userAddress) {
        if (this.isProhibitedCountrySelected) {
            showPleaseWaitDialog();
            this.mIncludeAddressDetailsPresenter.updateAddressOnServer(getViewLifecycleOwner(), this.selectedCountryCode, userAddress.getZipCode());
            return;
        }
        this.isAutoDetect = false;
        onClickAutoDetect();
    }

    private void verifyZipCode(Geocoder geocoder, List<Address> list) throws IOException {
        Editable text = this.mBinding.includeBody.includeSubBody.editTextZipCode.getText();
        if (text != null) {
            String obj = text.toString();
            if (obj.isEmpty()) {
                this.mIncludeAddressDetailsPresenter.updateAddressOnServer(getViewLifecycleOwner(), list.get(0).getCountryCode(), (String) null);
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
                zipCodeAlertBanner(list.get(0).getCountryCode(), obj);
            } else if (list.get(0).getCountryCode().equalsIgnoreCase(fromLocationName.get(0).getCountryCode())) {
                this.mIncludeAddressDetailsPresenter.updateAddressOnServer(getViewLifecycleOwner(), fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
            } else {
                dismissPleaseWaitDialog();
                zipCodeAlertBanner(fromLocationName.get(0).getCountryCode(), fromLocationName.get(0).getPostalCode());
            }
        }
    }

    private void zipCodeAlertBanner(String str, String str2) {
        String str3 = TAG;
        Log.e(str3, "zipCodeAlertBanner: countryCode : " + str + ", zipCode : " + str2);
        if (this.mBinding.includeBody.includeZipCode.rootView.getVisibility() == 0) {
            this.mIncludeAddressDetailsPresenter.updateAddressOnServer(getViewLifecycleOwner(), str, str2);
        } else {
            this.mBinding.includeBody.includeZipCode.rootView.setVisibility(0);
        }
    }

    public void onAddressUpdateSuccess(UserAddress userAddress) {
        logEvents(Events.STEP_4_SAVE_TIME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mEnterTime));
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setAddressLine(userAddress.getAddressLine());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setStreet(userAddress.getStreet());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setCity(userAddress.getCity());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setState(userAddress.getState());
        UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.setZipCode(userAddress.getZipCode());
        if (this.mIsAnyFieldChanged) {
            logEvents(Events.STEP_4_COMPLETED_WITH_CHANGING.name(), 0);
        } else {
            logEvents(Events.STEP_4_COMPLETED_WITHOUT_CHANGING.name(), 0);
        }
        dismissPleaseWaitDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean(com.jch.racWiFi.Constants.IS_PHONE_OPTION_SELECTED, this.isPhoneOptionSelected);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enterAddressSignUp4_to_accountCreationSuccessSignUp5, bundle);
    }

    public void onAddressUpdateFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (getActivity() != null) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableUpdateAddress));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new CreateAccountStep4Fragment$$ExternalSyntheticLambda3(this));
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$onAddressUpdateFailure$12$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ boolean mo33082xd4f6ceef(Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enterAddressSignUp4_to_accountCreationSuccessSignUp5);
        return true;
    }

    private void initDebugData() {
        this.mBinding.includeBody.includeSubBody.editTextAddressLine1.setText("");
        this.mBinding.includeBody.includeSubBody.editTextStreetArea.setText("");
        this.mBinding.includeBody.includeSubBody.editTextCity.setText("");
        this.mBinding.includeBody.includeSubBody.editTextState.setText("");
        this.mBinding.includeBody.includeSubBody.editTextZipCode.setText("");
    }

    public void onNetworkCallFailure(Throwable th) {
        if (getActivity() != null) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new CreateAccountStep4Fragment$$ExternalSyntheticLambda4(this));
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    /* renamed from: lambda$onNetworkCallFailure$13$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep4Fragment */
    public /* synthetic */ boolean mo33086x53e9cff(Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enterAddressSignUp4_to_accountCreationSuccessSignUp5);
        return true;
    }

    public void serverException() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 0);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Editable text = this.mBinding.includeBody.includeSubBody.editTextAddressLine1.getText();
        Editable text2 = this.mBinding.includeBody.includeSubBody.editTextCity.getText();
        Editable text3 = this.mBinding.includeBody.includeSubBody.editTextState.getText();
        if (text != null && text2 != null && text3 != null) {
            this.mBinding.includeBody.includeSubBody.buttonContinue.setEnabled(!text.toString().trim().isEmpty() && !text2.toString().trim().isEmpty() && !text3.toString().trim().isEmpty());
        }
    }

    private void postEvent(boolean z, String str) {
        if (z) {
            logEvents(str, 0);
            this.mIsAnyFieldChanged = true;
        }
    }
}
