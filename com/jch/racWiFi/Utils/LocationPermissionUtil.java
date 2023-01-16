package com.jch.racWiFi.Utils;

import android.content.Intent;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.p010di.util.Constants;

public class LocationPermissionUtil {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 234;
    private CoreActivity context;
    private OnPermissionDeniedCallback onPermissionDeniedCallback;

    public interface OnPermissionDeniedCallback {
        void onPermissionDeniedPermanently();
    }

    public LocationPermissionUtil(CoreActivity coreActivity, OnPermissionDeniedCallback onPermissionDeniedCallback2) {
        this.onPermissionDeniedCallback = onPermissionDeniedCallback2;
        this.context = coreActivity;
    }

    public boolean isLocationPermissionDeniedPermanently() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this.context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION);
    }

    public boolean isLocationPermissionAvailable() {
        return ActivityCompat.checkSelfPermission(this.context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0 && ActivityCompat.checkSelfPermission(this.context, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION) == 0;
    }

    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this.context, new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, Constants.GrantedPermissions.ACCESS_COARSE_LOCATION}, 234);
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (isLocationPermissionDeniedPermanently()) {
            this.onPermissionDeniedCallback.onPermissionDeniedPermanently();
            return false;
        } else if (i == 234 && strArr[0].equals(Constants.GrantedPermissions.ACCESS_FINE_LOCATION) && iArr[0] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void takeUserToSettingPageOfApp() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.context.getPackageName(), (String) null));
        this.context.startActivity(intent);
    }
}
