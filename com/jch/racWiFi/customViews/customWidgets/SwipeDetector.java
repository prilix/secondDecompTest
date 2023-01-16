package com.jch.racWiFi.customViews.customWidgets;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Arrays;
import java.util.List;

public class SwipeDetector extends GestureDetector.SimpleOnGestureListener {
    public static List<Integer> LIST_OF_FRAGMENTS_TO_ENABLE_SWIPE = Arrays.asList(new Integer[]{Integer.valueOf(R.id.qrScanFragment), Integer.valueOf(R.id.qrCodeScanned), Integer.valueOf(R.id.unableToScanQrSsidFragment), Integer.valueOf(R.id.selectDeviceTypeFragment), Integer.valueOf(R.id.selectWpsOrApMethodFragment), Integer.valueOf(R.id.confirmWirelessNetworkIndianFrag), Integer.valueOf(R.id.addHomeRouter), Integer.valueOf(R.id.wpsConfirmNetworkFragment), Integer.valueOf(R.id.apConfirmNetworkFragment), Integer.valueOf(R.id.addRac), Integer.valueOf(R.id.configuringDeviceUdpExchange), Integer.valueOf(R.id.enableWpsOnRacFragment), Integer.valueOf(R.id.successFragment), Integer.valueOf(R.id.switchOnAirConditionerFrag), Integer.valueOf(R.id.enableWpsOnRacWithoutQrFrag), Integer.valueOf(R.id.connectBackToHomeRouterFragment), Integer.valueOf(R.id.enableWpsOnHomeRouterFragment), Integer.valueOf(R.id.configuringDeviceCheckMdns), Integer.valueOf(R.id.myAccountNameFragment), Integer.valueOf(R.id.myAccountAddressFragment), Integer.valueOf(R.id.myAccountProfilePictureFragment), Integer.valueOf(R.id.changePasswordFragment), Integer.valueOf(R.id.myAccountManageHomesFragment), Integer.valueOf(R.id.userPermissionsManageUsersFragment), Integer.valueOf(R.id.editUserFragment), Integer.valueOf(R.id.inviteUsersFragmentNewVD), Integer.valueOf(R.id.deviceDetailsFragment), Integer.valueOf(R.id.ManageOwnersByRacIdFragment), Integer.valueOf(R.id.userPermisissionsDeviceSettingsFragment), Integer.valueOf(R.id.SmartFenceSelectAcFragment), Integer.valueOf(R.id.SmartFenceSelectUserFragment), Integer.valueOf(R.id.consumedEnergyGraphFragment), Integer.valueOf(R.id.budgetSetUpFragment), Integer.valueOf(R.id.weeklyTimerFragmentV3), Integer.valueOf(R.id.weeklyTimerScheduleSettingsFragmentV3), Integer.valueOf(R.id.setTimerFragmentV2), Integer.valueOf(R.id.setModeAndTempratureFragmentV2), Integer.valueOf(R.id.temperaturePreferenceFragment), Integer.valueOf(R.id.myAccountChangeOwnerShipFragment), Integer.valueOf(R.id.forgot_password_step1), Integer.valueOf(R.id.forgot_password_step2), Integer.valueOf(R.id.forgot_password_step3), Integer.valueOf(R.id.enterNameCreateAccountSignUp1), Integer.valueOf(R.id.createAccountManualEntrySignUp2), Integer.valueOf(R.id.createAccountOtpVerificationSignUp3), Integer.valueOf(R.id.enterAddressSignUp4), Integer.valueOf(R.id.individualIDUControlFragment), Integer.valueOf(R.id.cleaningStartFragment), Integer.valueOf(R.id.cleaningModeInProgressFragment)});
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private int SWIPE_MAX_PIXEL_LIMIT_LEFT = 300;
    private int SWIPE_MAX_PIXEL_LIMIT_RIGHT = 300;
    private final String TAG = getClass().getSimpleName();
    private SwipeScreenCallback swipeScreenCallback;

    public interface SwipeScreenCallback {
        void onSwipeScreenType(SwipeScreenType swipeScreenType);
    }

    public SwipeDetector(SwipeScreenCallback swipeScreenCallback2) {
        this.swipeScreenCallback = swipeScreenCallback2;
    }

    public SwipeDetector(SwipeScreenCallback swipeScreenCallback2, int i) {
        this.swipeScreenCallback = swipeScreenCallback2;
        double d = (double) i;
        this.SWIPE_MAX_PIXEL_LIMIT_LEFT = (int) (0.15d * d);
        this.SWIPE_MAX_PIXEL_LIMIT_RIGHT = (int) (d * 0.85d);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (Math.abs(motionEvent.getY() - motionEvent2.getY()) > 250.0f) {
            return false;
        }
        if (motionEvent2.getX() > motionEvent.getX() && motionEvent2.getX() - motionEvent.getX() > 120.0f && Math.abs(f) > 200.0f && motionEvent.getX() <= ((float) this.SWIPE_MAX_PIXEL_LIMIT_LEFT)) {
            this.swipeScreenCallback.onSwipeScreenType(SwipeScreenType.RIGHT_SWIPE);
            return true;
        } else if (motionEvent.getX() <= motionEvent2.getX() || motionEvent.getX() - motionEvent2.getX() <= 120.0f || Math.abs(f) <= 200.0f || motionEvent.getX() < ((float) this.SWIPE_MAX_PIXEL_LIMIT_RIGHT)) {
            return false;
        } else {
            this.swipeScreenCallback.onSwipeScreenType(SwipeScreenType.LEFT_SWIPE);
            return true;
        }
    }
}
