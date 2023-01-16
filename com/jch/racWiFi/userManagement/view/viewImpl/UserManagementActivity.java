package com.jch.racWiFi.userManagement.view.viewImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.amplitude.util.AppEventService;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customWidgets.SwipeDetector;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.view.LanguageSelectionDialog;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.AndroidInjection;
import java.util.Objects;

public class UserManagementActivity extends CoreActivity implements SwipeDetector.SwipeScreenCallback {
    private boolean enableBackButtonGestureListener;
    private boolean enableGestureListener;
    private GestureDetector gestureDetector;
    private NavController mNavController;
    private final SingleLiveEvent<SwipeScreenType> mScreenSwipeSingleLiveEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<MotionEvent> mTouchPointerCountSingleLiveEvent = new SingleLiveEvent<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_user_management);
        this.mNavController = Navigation.findNavController(this, R.id.userManagementNavHostFragment);
        startService(new Intent(this, AppEventService.class));
        Constants.IS_TO_PREVIEW_CAMERA = true;
        Intent intent = getIntent();
        if (intent != null && bundle == null && intent.getBooleanExtra(Constants.SWITCH_TO_LOGIN_SCREEN, false)) {
            NavGraph inflate = this.mNavController.getNavInflater().inflate(R.navigation.user_management_nav_graph);
            inflate.setStartDestination(R.id.loginFragment);
            this.mNavController.setGraph(inflate);
        }
        if (bundle == null || !bundle.getBoolean(Constants.TO_RESET_ACTIVITY)) {
            Constants.NOT_TO_RESTART = false;
        } else {
            finish();
            startActivity(new Intent(this, UserManagementActivity.class));
        }
        this.gestureDetector = new GestureDetector(this, new SwipeDetector(this, getScreenWidth()));
        this.mNavController.addOnDestinationChangedListener(new UserManagementActivity$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onCreate$0$com-jch-racWiFi-userManagement-view-viewImpl-UserManagementActivity */
    public /* synthetic */ void mo33302xa0e01ae(NavController navController, NavDestination navDestination, Bundle bundle) {
        this.enableBackButtonGestureListener = SwipeDetector.LIST_OF_FRAGMENTS_TO_ENABLE_SWIPE.contains(Integer.valueOf(navDestination.getId()));
    }

    public NavController getNavController() {
        NavController navController = this.mNavController;
        return navController == null ? Navigation.findNavController(this, R.id.userManagementNavHostFragment) : navController;
    }

    public void moveToHomePageActivity(boolean z, boolean z2) {
        super.moveToHomePageActivity(z, z2);
        Intent intent = new Intent(this, HomePageActivity.class);
        if (z) {
            intent.putExtra(Constants.Keys.IS_LOGIN, z2);
            intent.setFlags(268468224);
        }
        startActivity(intent);
        finish();
    }

    public void finishUserManagementActivity() {
        super.finishUserManagementActivity();
        finish();
    }

    public void reCreateUserManagementActivity() {
        super.reCreateUserManagementActivity();
        recreate();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        NavDestination currentDestination = getNavController().getCurrentDestination();
        Objects.requireNonNull(currentDestination);
        NavDestination navDestination = currentDestination;
        int id = currentDestination.getId();
        if (z && R.id.splashFragment == id && !LanguageSelectionDialog.Persistence.checkIfNeededToShowLanguageSelectionDialog()) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(C1662R2.C1665id.alexaLinkedView);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Logger.m45d("Multitouch detected!", String.valueOf(motionEvent.getPointerCount()));
        this.mTouchPointerCountSingleLiveEvent.postValue(motionEvent);
        GestureDetector gestureDetector2 = this.gestureDetector;
        if (gestureDetector2 == null || ((!this.enableGestureListener && !this.enableBackButtonGestureListener) || !gestureDetector2.onTouchEvent(motionEvent))) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!com.jch.racWiFi.Constants.NOT_TO_RESTART) {
            bundle.putBoolean(com.jch.racWiFi.Constants.TO_RESET_ACTIVITY, true);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.enableGestureListener && this.gestureDetector.onTouchEvent(motionEvent);
    }

    /* renamed from: com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity$1 */
    static /* synthetic */ class C26311 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.Utils.SwipeScreenType[] r0 = com.jch.racWiFi.Utils.SwipeScreenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType = r0
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.LEFT_SWIPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.RIGHT_SWIPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity.C26311.<clinit>():void");
        }
    }

    public void onSwipeScreenType(SwipeScreenType swipeScreenType) {
        if (C26311.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()] == 2 && this.enableBackButtonGestureListener) {
            this.mNavController.navigateUp();
        }
    }

    public SingleLiveEvent<SwipeScreenType> getScreenSwipeSingleLiveEvent() {
        return this.mScreenSwipeSingleLiveEvent;
    }

    public SingleLiveEvent<MotionEvent> getTouchPointerCountSingleLiveEvent() {
        return this.mTouchPointerCountSingleLiveEvent;
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void onBackPressed() {
        logEvents();
        super.onBackPressed();
    }

    private void logEvents() {
        NavDestination currentDestination = this.mNavController.getCurrentDestination();
        if (currentDestination != null) {
            if (currentDestination.getId() == R.id.enterNameCreateAccountSignUp1) {
                logEvents(Events.QUIT_REGISTRATION_STEP_1.name(), 0);
            }
            if (currentDestination.getId() == R.id.createAccountManualEntrySignUp2) {
                logEvents(Events.QUIT_REGISTRATION_STEP_2.name(), 0);
            }
            if (currentDestination.getId() == R.id.createAccountOtpVerificationSignUp3) {
                logEvents(Events.QUIT_REGISTRATION_STEP_3.name(), 0);
            }
            if (currentDestination.getId() == R.id.enterAddressSignUp4) {
                logEvents(Events.QUIT_ADDRESS_SET_UP_STEP_4.name(), 0);
            }
            if (currentDestination.getId() == R.id.forgot_password_step2) {
                logEvents(Events.QUIT_FORGOT_PASSWORD_STEP_2.name(), 0);
            }
        }
    }
}
