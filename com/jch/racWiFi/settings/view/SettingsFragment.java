package com.jch.racWiFi.settings.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.g00fy2.versioncompare.Version;
import com.jch.racWiFi.AppVersionModels;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.databinding.RecyclerViewAppSettingsBinding;
import com.jch.racWiFi.databinding.SettingsFrameBinding;
import com.jch.racWiFi.linking.amazon.activity.AlexaActivity;
import com.jch.racWiFi.main.model.AppVersion;
import com.jch.racWiFi.main.view_model.MainViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsFragment extends GenericFragment {
    private static boolean recreated = false;
    private AppVersionDialog mAppVersionDialog;
    private SettingsFrameBinding mBinding;
    private UserPermissions mUserPermissions;
    private List<SettingsMenuItem> menuItems;

    static /* synthetic */ void lambda$onCreate$0(View view) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment settingsFragment = new SettingsFragment();
        settingsFragment.setNewBundleAsArgument();
        return settingsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppVersionDialog appVersionDialog = new AppVersionDialog(requireActivity());
        this.mAppVersionDialog = appVersionDialog;
        appVersionDialog.setOnClickCheckUpdates(SettingsFragment$$ExternalSyntheticLambda8.INSTANCE);
        this.mAppVersionDialog.setOnCancelListener(new SettingsFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onCreate$1$com-jch-racWiFi-settings-view-SettingsFragment  reason: not valid java name */
    public /* synthetic */ void m1365lambda$onCreate$1$comjchracWiFisettingsviewSettingsFragment(DialogInterface dialogInterface) {
        logEvents(Events.CANCEL_UPDATE.name(), 0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SettingsFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.settings_frame, viewGroup, false);
        this.mUserPermissions = UserPermissions.getInstance();
        this.menuItems = new ArrayList();
        populateMenuItems();
        SettingsRecyclerViewAdapter settingsRecyclerViewAdapter = new SettingsRecyclerViewAdapter(requireActivity(), this.menuItems);
        this.mBinding.layoutInclude.recyclerViewAppSettings.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.mBinding.layoutInclude.recyclerViewAppSettings.setAdapter(settingsRecyclerViewAdapter);
        logEvent(Screens.SCREENS.name(), 13);
        logEvents(Events.SETTINGS_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.layoutInclude.settingsLinkWithAlexa.setOnClickListener(new SettingsFragment$$ExternalSyntheticLambda2(this));
        this.mBinding.imageButtonMenu.setOnClickListener(new SettingsFragment$$ExternalSyntheticLambda3(this));
        getAppVersion();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31963xf838304e(View view) {
        startActivity(new Intent(getActivity(), AlexaActivity.class));
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31964x3202d22d(View view) {
        OnClickMenu();
    }

    private void getAppVersion() {
        MainViewModel mainViewModel;
        LiveData<Resource<AppVersion>> appVersion;
        CoreActivity coreActivity = getCoreActivity();
        if (coreActivity != null && (mainViewModel = coreActivity.getMainViewModel()) != null && (appVersion = mainViewModel.getAppVersion()) != null) {
            appVersion.removeObservers(getViewLifecycleOwner());
            appVersion.observe(getViewLifecycleOwner(), new SettingsFragment$$ExternalSyntheticLambda9(this));
        }
    }

    /* renamed from: com.jch.racWiFi.settings.view.SettingsFragment$1 */
    static /* synthetic */ class C23451 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.settings.view.SettingsFragment.C23451.<clinit>():void");
        }
    }

    /* renamed from: lambda$getAppVersion$4$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31959xb5eba8e3(Resource resource) {
        if (resource != null) {
            int i = C23451.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                onVersionError();
            } else if (i == 3) {
                onVersionSuccess((AppVersion) resource.response);
            }
        }
    }

    private void onVersionSuccess(AppVersion appVersion) {
        AppVersion.Body body;
        AppVersion.Body.Result result;
        if (appVersion != null && (body = appVersion.getBody()) != null && (result = body.getResult()) != null) {
            String latestVersion = result.getLatestVersion();
            String minimumVersion = result.getMinimumVersion();
            String storeLink = result.getStoreLink();
            if (latestVersion == null || latestVersion.isEmpty() || minimumVersion == null || minimumVersion.isEmpty() || storeLink == null || storeLink.isEmpty()) {
                this.mAppVersionDialog.updateLatestVersionTextView(getString(R.string.dots));
                return;
            }
            this.mAppVersionDialog.updateLatestVersionTextView(latestVersion);
            if (new Version(latestVersion).isHigherThan(new Version(Constants.CC.getVersion("3.0.9")))) {
                this.mAppVersionDialog.setInstallUpdateButton(new SettingsFragment$$ExternalSyntheticLambda7(this, storeLink));
            } else {
                this.mAppVersionDialog.setInstallUpdateInvisible();
            }
        }
    }

    /* renamed from: lambda$onVersionSuccess$5$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31962x3d47e1eb(String str, View view) {
        getCoreActivity().openPlayStore(str);
        logEvents(Events.MANUAL_UPDATE.name(), 0);
    }

    private void onVersionError() {
        Toaster.makeToast(requireActivity(), getString(R.string.appLaunch_alert_serversAreDown), 0);
    }

    public void onResume() {
        super.onResume();
        if (recreated) {
            recreated = false;
            getCoreActivity().showPleaseWaitDialog();
            new Handler().postDelayed(new SettingsFragment$$ExternalSyntheticLambda1(this), 1000);
        }
    }

    /* renamed from: lambda$onResume$6$com-jch-racWiFi-settings-view-SettingsFragment  reason: not valid java name */
    public /* synthetic */ void m1366lambda$onResume$6$comjchracWiFisettingsviewSettingsFragment() {
        if (getCoreActivity() != null) {
            getCoreActivity().dismissPleaseWaitDialog();
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void OnClickMenu() {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    private void populateMenuItems() {
        if (this.menuItems.isEmpty()) {
            if (this.mUserPermissions.getPermission(UserPermissions.UserFeatures.SETTINGS_LANGUAGE)) {
                SettingsMenuItem settingsMenuItem = new SettingsMenuItem();
                settingsMenuItem.setMainMenuTitle(getString(R.string.settings_lbl_language));
                Locale locale = getContext().getResources().getConfiguration().locale;
                UserPermissions.getInstance().updatePermissionString(requireActivity());
                settingsMenuItem.setSubMenuInfo(locale.getDisplayLanguage());
                settingsMenuItem.setOnClickListener(new SettingsFragment$$ExternalSyntheticLambda5(this));
                settingsMenuItem.settingFlag = false;
                this.menuItems.add(settingsMenuItem);
            }
            SettingsMenuItem settingsMenuItem2 = new SettingsMenuItem();
            settingsMenuItem2.setMainMenuTitle(getString(R.string.temp_lbl_tempUnit));
            settingsMenuItem2.setSubMenuInfo(getString((UserInfo.getCurrentUserInfo(getCoreActivity()).settingsData.temperatureUnit.equals(TemperatureUnit.SERVER_DATA_CELSIUS) ? TemperatureUnit.CELSIUS : TemperatureUnit.FAHRENHEIT).toStringRes()));
            settingsMenuItem2.setOnClickListener(new SettingsFragment$$ExternalSyntheticLambda6(this));
            this.menuItems.add(settingsMenuItem2);
            settingsMenuItem2.settingFlag = false;
            SettingsMenuItem settingsMenuItem3 = new SettingsMenuItem();
            settingsMenuItem3.setMainMenuTitle(getString(R.string.settings_lbl_appVersion));
            settingsMenuItem3.setSubMenuInfo(AppVersionModels.Platform.ANDROID.getCurrentAppVersion(requireActivity()));
            settingsMenuItem3.setOnClickListener(new SettingsFragment$$ExternalSyntheticLambda4(this));
            this.menuItems.add(settingsMenuItem3);
        }
    }

    /* renamed from: lambda$populateMenuItems$8$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31967xdb516ab5(View view) {
        LanguageSelectionDialog languageSelectionDialog = new LanguageSelectionDialog(requireActivity());
        languageSelectionDialog.setShowingDialogFromSettings(true);
        languageSelectionDialog.setCancelable(true);
        languageSelectionDialog.setLanguageSelectedApplyListener(new SettingsFragment$$ExternalSyntheticLambda10(this, languageSelectionDialog));
        languageSelectionDialog.setParentView(this.mBinding.parent);
        languageSelectionDialog.show();
    }

    /* renamed from: lambda$populateMenuItems$7$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31966xa186c8d6(LanguageSelectionDialog languageSelectionDialog, LanguageModel languageModel) {
        LocaleConfiguration.persistLocalization(languageModel.getLocale());
        languageSelectionDialog.dismiss();
        recreated = true;
        com.jch.racWiFi.Constants.NOT_TO_RESTART = true;
        requireActivity().recreate();
    }

    /* renamed from: lambda$populateMenuItems$9$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31968x151c0c94(View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_settingsFragment_to_temperaturePreferenceFragment);
    }

    private /* synthetic */ void lambda$populateMenuItems$10(View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_settingsFragment_to_userPreferencesFragment);
    }

    /* renamed from: lambda$populateMenuItems$11$com-jch-racWiFi-settings-view-SettingsFragment */
    public /* synthetic */ void mo31965x240a06b9(View view) {
        this.mAppVersionDialog.setParentView(this.mBinding.parent);
        this.mAppVersionDialog.show();
    }

    static class SettingsRecyclerViewAdapter extends RecyclerView.Adapter<SettingsViewHolder> {
        private final Context context;
        private final List<SettingsMenuItem> menuItemList;

        public SettingsRecyclerViewAdapter(Context context2, List<SettingsMenuItem> list) {
            this.context = context2;
            this.menuItemList = list;
        }

        public SettingsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SettingsViewHolder((RecyclerViewAppSettingsBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.recycler_view_app_settings, viewGroup, false));
        }

        public void onBindViewHolder(SettingsViewHolder settingsViewHolder, int i) {
            settingsViewHolder.bind(this.menuItemList, i);
        }

        public int getItemCount() {
            return this.menuItemList.size();
        }

        static class SettingsViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerViewAppSettingsBinding binding;

            public SettingsViewHolder(RecyclerViewAppSettingsBinding recyclerViewAppSettingsBinding) {
                super(recyclerViewAppSettingsBinding.getRoot());
                this.binding = recyclerViewAppSettingsBinding;
                recyclerViewAppSettingsBinding.layoutAppSettings.setOnClickListener(new C2347xfd891430(this, recyclerViewAppSettingsBinding));
                recyclerViewAppSettingsBinding.settingsToggleAmplitude.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                    }
                });
                recyclerViewAppSettingsBinding.settingsToggleAmplitude.setOnCheckedChangeListener(new C2348xfd891431(this, recyclerViewAppSettingsBinding));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-settings-view-SettingsFragment$SettingsRecyclerViewAdapter$SettingsViewHolder */
            public /* synthetic */ void mo31978xd21f2060(RecyclerViewAppSettingsBinding recyclerViewAppSettingsBinding, View view) {
                onClickItem(recyclerViewAppSettingsBinding.layoutAppSettings);
            }

            /* renamed from: lambda$new$1$com-jch-racWiFi-settings-view-SettingsFragment$SettingsRecyclerViewAdapter$SettingsViewHolder */
            public /* synthetic */ void mo31979xfa6560a1(RecyclerViewAppSettingsBinding recyclerViewAppSettingsBinding, SwitchButton switchButton, boolean z) {
                onCheckedChangeListener(recyclerViewAppSettingsBinding.settingsToggleAmplitude);
            }

            public void onCheckedChangeListener(CustomSwitchButton customSwitchButton) {
                ((SettingsMenuItem) customSwitchButton.getTag()).onCheckedChangeListener.onCheckedChanged(customSwitchButton, customSwitchButton.isChecked());
            }

            public void onClickItem(ConstraintLayout constraintLayout) {
                ((SettingsMenuItem) constraintLayout.getTag()).onClickListener.onClick(constraintLayout);
            }

            public void bind(List<SettingsMenuItem> list, int i) {
                SettingsMenuItem settingsMenuItem = list.get(i);
                if (settingsMenuItem != null) {
                    this.binding.textViewSettingType.setText(settingsMenuItem.getMainMenuTitle());
                    if (settingsMenuItem.getMainMenuTitle().equals("User Preferences") || settingsMenuItem.getMainMenuTitle().equals("ユーザー設定")) {
                        this.binding.textViewSettingInfo.setVisibility(8);
                    } else {
                        this.binding.textViewSettingInfo.setVisibility(0);
                        this.binding.textViewSettingInfo.setText(settingsMenuItem.getSubMenuInfo());
                    }
                    updateVisibility(this.binding.imageViewRightSwipe, this.binding.settingsToggleAmplitude, i);
                    if (settingsMenuItem.settingFlag) {
                        this.binding.layoutAppSettings.setEnabled(false);
                        this.binding.textViewSettingType.setAlpha(0.1f);
                        this.binding.textViewSettingInfo.setAlpha(0.1f);
                        this.binding.imageViewRightSwipe.setAlpha(0.1f);
                    }
                    this.binding.layoutAppSettings.setTag(settingsMenuItem);
                    this.binding.settingsToggleAmplitude.setTag(settingsMenuItem);
                }
            }

            private void updateVisibility(ImageView imageView, CustomSwitchButton customSwitchButton, int i) {
                if (i == 3) {
                    customSwitchButton.setVisibility(0);
                    imageView.setVisibility(8);
                    return;
                }
                customSwitchButton.setVisibility(8);
                imageView.setVisibility(0);
            }
        }
    }

    private static class SettingsMenuItem {
        private String mainMenuTitle;
        /* access modifiers changed from: private */
        public SwitchButton.OnCheckedChangeListener onCheckedChangeListener;
        /* access modifiers changed from: private */
        public View.OnClickListener onClickListener;
        boolean settingFlag;
        private String subMenuInfo;

        private SettingsMenuItem() {
            this.settingFlag = false;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public String getMainMenuTitle() {
            return this.mainMenuTitle;
        }

        public void setMainMenuTitle(String str) {
            this.mainMenuTitle = str;
        }

        public String getSubMenuInfo() {
            return this.subMenuInfo;
        }

        public void setSubMenuInfo(String str) {
            this.subMenuInfo = str;
        }

        public void setOnCheckedChangeListener(SwitchButton.OnCheckedChangeListener onCheckedChangeListener2) {
            this.onCheckedChangeListener = onCheckedChangeListener2;
        }
    }
}
