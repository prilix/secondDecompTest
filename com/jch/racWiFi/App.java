package com.jch.racWiFi;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.accord.common.utils.Logger;
import com.evernote.android.state.StateSaver;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.p010di.component.DaggerAppComponent;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.userManagement.countryCodeManager.Country;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch_hitachi.aircloudglobal.R;
import com.thanosfisherman.wifiutils.WifiUtils;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import java.util.List;

public class App extends DaggerApplication {
    private static final String TAG = "App";
    private static Context context;

    static /* synthetic */ void lambda$onCreate$1(Exception exc) {
    }

    static /* synthetic */ void lambda$onCreate$2() {
    }

    static /* synthetic */ void lambda$onCreate$3(Task task) {
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CountryCodeUIConfiguration.CURRENT = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getApplicatonContext(), CountryUtils.getByNameCodeFromCustomCountries(getApplicatonContext(), (List<Country>) null, "in"));
        CountryCodeUIConfiguration.CURRENT.setCountryCodeString(getApplicatonContext());
        FirebaseAnalytics.getInstance(this);
        PrivacyPolicyModel.PrivacyPolicyData.importFromPreference();
        UserPermissions.getInstance().init(getApplicationContext());
        WifiUtils.enableLog(true);
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
        new BackgroundExecutor().start();
        TemperatureUnit.ConversionUtil.init();
        getString(R.string.onboard_btn_noCancel);
        getString(R.string.createAccount_lbl_createUserAccount);
        getString(R.string.createAccount_lbl_passwordCriteria);
        getString(R.string.createAccount_lbl_minCharactersMsg);
        getString(R.string.createAccount_lbl_uppercaseCharactersMsg);
        getString(R.string.createAccount_lbl_lowercaseCharactersMsg);
        getString(R.string.createAccount_lbl_digitsMsg);
        getString(R.string.timer_lbl_switchOnAt);
        getString(R.string.timer_lbl_switchOffAt);
        LocaleConfiguration.LanguageSelectionUtils.init();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(App$$ExternalSyntheticLambda3.INSTANCE).addOnFailureListener(App$$ExternalSyntheticLambda2.INSTANCE).addOnCanceledListener(App$$ExternalSyntheticLambda0.INSTANCE).addOnCompleteListener(App$$ExternalSyntheticLambda1.INSTANCE);
    }

    static /* synthetic */ void lambda$onCreate$0(InstanceIdResult instanceIdResult) {
        if (instanceIdResult != null) {
            String token = instanceIdResult.getToken();
            if (!TextUtils.isEmpty(token)) {
                Logger.m45d(TAG, "retrieve token successful : " + token);
                return;
            }
            return;
        }
        Logger.m52w(TAG, "instanceIdResult should not be null..");
    }

    public static Context getApplicatonContext() {
        return context;
    }

    public void onConfigurationChanged(Configuration configuration) {
        LocaleHelper.INSTANCE.onAttach(this);
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        SharedPref.getInstance().init(context2);
        super.attachBaseContext(LocaleHelper.INSTANCE.onAttach(context2));
    }

    /* access modifiers changed from: protected */
    public AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static void initialize(Context context2) {
        SharedPref.getInstance().init(context2);
        LocaleConfiguration.LanguageSelectionUtils.init();
    }
}
