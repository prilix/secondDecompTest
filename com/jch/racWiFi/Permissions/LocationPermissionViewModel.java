package com.jch.racWiFi.Permissions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.accord.fusedlocationmodule.FusedLocationProviderAPIExtension;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessDialog;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessDialogHomePage;
import com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.util.listeners.AlertListener;
import java.util.Map;

public class LocationPermissionViewModel extends C0534ViewModel {
    private FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension;
    private LocationAccessDialog locationAccessDialog;
    private LocationAccessDialogHomePage locationAccessDialogHomePage;
    private String[] permissions;

    public static abstract class AbstractResolutionCallBack implements ResolutionCallBack {
        public void allGood() {
        }

        public void locationDisabled() {
        }

        public void locationIsOff() {
        }

        public void permissionDenied() {
        }

        public void permissionNotAvailable() {
        }

        public void resolutionRequired() {
        }
    }

    private interface LocationOnOffCallback {
        void locationDisabled();

        void locationEnabled();
    }

    public interface ResolutionCallBack {
        void allGood();

        void locationDisabled();

        void locationIsOff();

        void permissionDenied();

        void permissionNotAvailable();

        void resolutionRequired();
    }

    public LocationPermissionViewModel(FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension2) {
        String[] strArr;
        if (isAndroid10OrHigher()) {
            strArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, "android.permission.ACCESS_BACKGROUND_LOCATION"};
        } else {
            strArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION};
        }
        this.permissions = strArr;
        this.fusedLocationProviderAPIExtension = fusedLocationProviderAPIExtension2;
    }

    public void needsResolution(AlertListener alertListener, boolean z, CoreActivity coreActivity, LocationAccessRationale locationAccessRationale) {
        if (z) {
            showLocationAccessHomePagePopUpLocationIsdisabled(alertListener, coreActivity, locationAccessRationale);
        } else {
            showLocationAccessPopUpLocationIsdisabled(alertListener, coreActivity, locationAccessRationale);
        }
    }

    private void showLocationAccessPopUpLocationIsdisabled(AlertListener alertListener, CoreActivity coreActivity, LocationAccessRationale locationAccessRationale) {
        LocationAccessDialog locationAccessDialog2 = this.locationAccessDialog;
        if (locationAccessDialog2 != null && locationAccessDialog2.isShowing()) {
            this.locationAccessDialog.dismiss();
        }
        LocationAccessDialog locationAccessDialog3 = new LocationAccessDialog(coreActivity);
        this.locationAccessDialog = locationAccessDialog3;
        locationAccessDialog3.setLocationAccessRationale(locationAccessRationale);
        this.locationAccessDialog.setDimensions(-1);
        this.locationAccessDialog.setOnLocationEnableClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda6(this, alertListener));
        this.locationAccessDialog.setOnCloseClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda7(this, alertListener));
        this.locationAccessDialog.setCancelable(false);
        this.locationAccessDialog.show();
    }

    /* renamed from: lambda$showLocationAccessPopUpLocationIsdisabled$0$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27675x502ad05(AlertListener alertListener, View view) {
        this.locationAccessDialog.dismiss();
        alertListener.onPositive();
    }

    /* renamed from: lambda$showLocationAccessPopUpLocationIsdisabled$1$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27676x2a96b606(AlertListener alertListener, View view) {
        this.locationAccessDialog.dismiss();
        alertListener.onNegative();
    }

    private void showLocationAccessHomePagePopUpLocationIsdisabled(AlertListener alertListener, CoreActivity coreActivity, LocationAccessRationale locationAccessRationale) {
        LocationAccessDialogHomePage locationAccessDialogHomePage2 = this.locationAccessDialogHomePage;
        if (locationAccessDialogHomePage2 == null || !locationAccessDialogHomePage2.isShowing()) {
            LocationAccessDialogHomePage locationAccessDialogHomePage3 = new LocationAccessDialogHomePage(coreActivity);
            this.locationAccessDialogHomePage = locationAccessDialogHomePage3;
            locationAccessDialogHomePage3.setLocationAccessRationale(locationAccessRationale);
            this.locationAccessDialogHomePage.setDimensions(-1, -2);
            this.locationAccessDialogHomePage.setOnLocationEnableClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda2(this, alertListener));
            this.locationAccessDialogHomePage.setOnCloseClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda3(this, alertListener));
            this.locationAccessDialogHomePage.setCancelable(false);
            this.locationAccessDialogHomePage.show();
        }
    }

    /* renamed from: lambda$showLocationAccessHomePagePopUpLocationIsdisabled$2$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27671x74aa4b99(AlertListener alertListener, View view) {
        this.locationAccessDialogHomePage.dismiss();
        alertListener.onPositive();
    }

    /* renamed from: lambda$showLocationAccessHomePagePopUpLocationIsdisabled$3$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27672x9a3e549a(AlertListener alertListener, View view) {
        this.locationAccessDialogHomePage.dismiss();
        alertListener.onNegative();
    }

    @Deprecated
    private void showLocationAccessPop(AlertListener alertListener, CoreActivity coreActivity, LocationAccessRationale locationAccessRationale, Fragment fragment, ResolutionCallBack resolutionCallBack) {
        LocationAccessDialog locationAccessDialog2 = this.locationAccessDialog;
        if (locationAccessDialog2 != null && locationAccessDialog2.isShowing()) {
            this.locationAccessDialog.dismiss();
        }
        LocationAccessDialog locationAccessDialog3 = new LocationAccessDialog(coreActivity);
        this.locationAccessDialog = locationAccessDialog3;
        locationAccessDialog3.setLocationAccessRationale(locationAccessRationale);
        this.locationAccessDialog.setDimensions(-1);
        this.locationAccessDialog.setOnLocationEnableClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda4(this, alertListener));
        this.locationAccessDialog.setOnCloseClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda5(this, alertListener));
        this.locationAccessDialog.setCancelable(false);
        this.locationAccessDialog.show();
    }

    /* renamed from: lambda$showLocationAccessPop$4$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27673x311aea33(AlertListener alertListener, View view) {
        this.locationAccessDialog.dismiss();
        alertListener.onPositive();
    }

    /* renamed from: lambda$showLocationAccessPop$5$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27674x56aef334(AlertListener alertListener, View view) {
        this.locationAccessDialog.dismiss();
        alertListener.onNegative();
    }

    @Deprecated
    private void showHomePageLocationAccessPop(AlertListener alertListener, CoreActivity coreActivity, LocationAccessRationale locationAccessRationale, Fragment fragment, ResolutionCallBack resolutionCallBack) {
        LocationAccessDialogHomePage locationAccessDialogHomePage2 = this.locationAccessDialogHomePage;
        if (locationAccessDialogHomePage2 != null && locationAccessDialogHomePage2.isShowing()) {
            this.locationAccessDialogHomePage.dismiss();
        }
        LocationAccessDialogHomePage locationAccessDialogHomePage3 = new LocationAccessDialogHomePage(coreActivity);
        this.locationAccessDialogHomePage = locationAccessDialogHomePage3;
        locationAccessDialogHomePage3.setLocationAccessRationale(locationAccessRationale);
        this.locationAccessDialogHomePage.setDimensions(-1, -2);
        this.locationAccessDialogHomePage.setOnLocationEnableClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda0(this, alertListener));
        this.locationAccessDialogHomePage.setOnCloseClickListener(new LocationPermissionViewModel$$ExternalSyntheticLambda1(this, alertListener));
        this.locationAccessDialogHomePage.setParentView(coreActivity.getWindow().getDecorView().findViewById(16908290), true);
        this.locationAccessDialogHomePage.setCancelable(false);
        this.locationAccessDialogHomePage.show();
    }

    /* renamed from: lambda$showHomePageLocationAccessPop$6$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27669x9ab31547(AlertListener alertListener, View view) {
        this.locationAccessDialogHomePage.dismiss();
        alertListener.onPositive();
    }

    /* renamed from: lambda$showHomePageLocationAccessPop$7$com-jch-racWiFi-Permissions-LocationPermissionViewModel */
    public /* synthetic */ void mo27670xc0471e48(AlertListener alertListener, View view) {
        this.locationAccessDialogHomePage.dismiss();
        alertListener.onNegative();
    }

    @Deprecated
    private void checkLocationPermission(final CoreActivity coreActivity, final Fragment fragment, final ResolutionCallBack resolutionCallBack) {
        if (isAndroid10OrHigher()) {
            ActivityResultLauncher registerForActivityResult = coreActivity.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
                public void onActivityResult(Map<String, Boolean> map) {
                    if (LocationPermissionViewModel.this.isLocationPermissionAvailable(coreActivity)) {
                        LocationPermissionViewModel.this.checkLocationEnabled(coreActivity, fragment, resolutionCallBack);
                        return;
                    }
                    resolutionCallBack.permissionDenied();
                    resolutionCallBack.resolutionRequired();
                }
            });
            if (ActivityCompat.shouldShowRequestPermissionRationale(coreActivity, this.permissions[0]) || ActivityCompat.shouldShowRequestPermissionRationale(coreActivity, this.permissions[1])) {
                ActivityResultLauncher registerForActivityResult2 = coreActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    public void onActivityResult(ActivityResult activityResult) {
                        if (LocationPermissionViewModel.this.isLocationPermissionAvailable(coreActivity)) {
                            LocationPermissionViewModel.this.checkLocationEnabled(coreActivity, fragment, resolutionCallBack);
                            return;
                        }
                        resolutionCallBack.permissionDenied();
                        resolutionCallBack.resolutionRequired();
                    }
                });
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + coreActivity.getPackageName()));
                intent.addCategory("android.intent.category.DEFAULT");
                registerForActivityResult2.launch(intent);
                return;
            }
            registerForActivityResult.launch(this.permissions);
            return;
        }
        ActivityResultLauncher registerForActivityResult3 = coreActivity.registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            public void onActivityResult(Boolean bool) {
                if (bool.booleanValue()) {
                    LocationPermissionViewModel.this.checkLocationEnabled(coreActivity, fragment, resolutionCallBack);
                    return;
                }
                resolutionCallBack.permissionDenied();
                resolutionCallBack.resolutionRequired();
            }
        });
        if (ActivityCompat.shouldShowRequestPermissionRationale(coreActivity, this.permissions[0])) {
            ActivityResultLauncher registerForActivityResult4 = coreActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                public void onActivityResult(ActivityResult activityResult) {
                    if (LocationPermissionViewModel.this.isLocationPermissionAvailable(coreActivity)) {
                        LocationPermissionViewModel.this.checkLocationEnabled(coreActivity, fragment, resolutionCallBack);
                        return;
                    }
                    resolutionCallBack.permissionDenied();
                    resolutionCallBack.resolutionRequired();
                }
            });
            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + coreActivity.getPackageName()));
            intent2.addCategory("android.intent.category.DEFAULT");
            registerForActivityResult4.launch(intent2);
            return;
        }
        registerForActivityResult3.launch(this.permissions[0]);
    }

    /* access modifiers changed from: private */
    public void checkLocationEnabled(final CoreActivity coreActivity, Fragment fragment, final ResolutionCallBack resolutionCallBack) {
        this.fusedLocationProviderAPIExtension.checkLocationServiceEnabled(new OnSuccessListener<LocationSettingsResponse>() {
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                resolutionCallBack.allGood();
            }
        }, new OnFailureListener() {
            public void onFailure(Exception exc) {
                resolutionCallBack.locationIsOff();
                ApiException apiException = (ApiException) exc;
                if (apiException.getStatusCode() == 6) {
                    try {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) apiException;
                        coreActivity.registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new LocationPermissionViewModel$6$$ExternalSyntheticLambda0(resolutionCallBack)).launch(new IntentSenderRequest.Builder(((ResolvableApiException) apiException).getResolution()).build());
                    } catch (ClassCastException unused) {
                    }
                }
            }

            static /* synthetic */ void lambda$onFailure$0(ResolutionCallBack resolutionCallBack, ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    resolutionCallBack.allGood();
                    return;
                }
                resolutionCallBack.locationDisabled();
                resolutionCallBack.resolutionRequired();
            }
        });
    }

    private boolean isAndroid10OrHigher() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public boolean isLocationPermissionAvailable(CoreActivity coreActivity) {
        if (isAndroid10OrHigher()) {
            if (PermissionChecker.checkSelfPermission(coreActivity.getApplicationContext(), this.permissions[0]) == 0 && PermissionChecker.checkSelfPermission(coreActivity.getApplicationContext(), this.permissions[1]) == 0) {
                return true;
            }
            return false;
        } else if (PermissionChecker.checkSelfPermission(coreActivity.getApplicationContext(), this.permissions[0]) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLocationServiceEnabled() {
        final boolean[] zArr = {false};
        this.fusedLocationProviderAPIExtension.checkLocationServiceEnabled(new OnSuccessListener<LocationSettingsResponse>() {
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                zArr[0] = true;
            }
        }, new OnFailureListener() {
            public void onFailure(Exception exc) {
                zArr[0] = false;
            }
        });
        return zArr[0];
    }

    public boolean isLocationEnabled(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void checkLocationEnabled(LocationOnOffCallback locationOnOffCallback) {
        this.fusedLocationProviderAPIExtension.checkLocationServiceEnabled(new LocationPermissionViewModel$$ExternalSyntheticLambda9(locationOnOffCallback), new LocationPermissionViewModel$$ExternalSyntheticLambda8(locationOnOffCallback));
    }

    public static class LocationPermissionViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension;

        public LocationPermissionViewModelFactory(FusedLocationProviderAPIExtension fusedLocationProviderAPIExtension2) {
            this.fusedLocationProviderAPIExtension = fusedLocationProviderAPIExtension2;
        }

        public <T extends C0534ViewModel> T create(Class<T> cls) {
            return new LocationPermissionViewModel(this.fusedLocationProviderAPIExtension);
        }
    }
}
