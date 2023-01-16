package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.EnableDisableOnChangeListener;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.Power;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.iduManagement.presenter.HomePagePresenter;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageFragment;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.userManagement.model.dataProvider.RoleWisePermissionProvider;
import com.jch.racWiFi.weather.model.Weather;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.util.ArrayList;
import java.util.List;

public abstract class DetailedIduAdapter extends RecyclerView.Adapter<DetailedIduViewHolder> {
    private static DiffUtil.ItemCallback<DetailedIduModel> DETAILS_IDU_DIFF_CALLBACK = new DiffUtil.ItemCallback<DetailedIduModel>() {
        public boolean areItemsTheSame(DetailedIduModel detailedIduModel, DetailedIduModel detailedIduModel2) {
            return detailedIduModel.equals(detailedIduModel2);
        }

        public boolean areContentsTheSame(DetailedIduModel detailedIduModel, DetailedIduModel detailedIduModel2) {
            return detailedIduModel.equals(detailedIduModel2);
        }
    };
    /* access modifiers changed from: private */
    public AppCompatActivity activity;
    public String currentRacName;
    /* access modifiers changed from: private */
    public List<DetailedIduModel> detailedIduModels = new ArrayList();
    /* access modifiers changed from: private */
    public boolean disableRacClick = true;
    /* access modifiers changed from: private */
    public HomePagePresenter homePagePresenter;
    /* access modifiers changed from: private */
    public IHomePageView homePageView;
    private SingleChoiceDialog internetCheckDialog;
    /* access modifiers changed from: private */
    public HomePageFragment mHomePageFragment;

    /* renamed from: on */
    private boolean f470on = false;
    private boolean preventSwitchApiCall = false;

    private void turnOnAllRac() {
    }

    public abstract void onPowerStateChanged();

    public List<DetailedIduModel> getDetailedIduModelList() {
        return this.detailedIduModels;
    }

    public void setDisableRacClick(boolean z) {
        this.disableRacClick = z;
    }

    public DetailedIduAdapter(SingleChoiceDialog singleChoiceDialog, AppCompatActivity appCompatActivity, HomePagePresenter homePagePresenter2, IHomePageView iHomePageView) {
        this.activity = appCompatActivity;
        this.internetCheckDialog = singleChoiceDialog;
        this.homePagePresenter = homePagePresenter2;
        this.homePageView = iHomePageView;
        this.mHomePageFragment = (HomePageFragment) iHomePageView;
    }

    public DetailedIduViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DetailedIduViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_items_home, viewGroup, false));
    }

    public void setDetailedIduModels(List<DetailedIduModel> list) {
        this.detailedIduModels = list;
        this.homePagePresenter.checkAndUpdateStopAllSwitch(list);
        notifyDataSetChanged();
    }

    public void updateIduPowerState(long j, String str) {
        new Thread(new DetailedIduAdapter$$ExternalSyntheticLambda1(this, j, str)).start();
    }

    /* renamed from: lambda$updateIduPowerState$1$com-jch-racWiFi-iduManagement-view-DetailedIduAdapter */
    public /* synthetic */ void mo30449xa7d04d8(long j, String str) {
        int size = this.detailedIduModels.size();
        for (int i = 0; i < size; i++) {
            DetailedIduModel detailedIduModel = this.detailedIduModels.get(i);
            if (((long) detailedIduModel.getId().intValue()) == j) {
                detailedIduModel.setPower(str);
                this.activity.runOnUiThread(new DetailedIduAdapter$$ExternalSyntheticLambda0(this, i));
                return;
            }
        }
    }

    /* renamed from: lambda$updateIduPowerState$0$com-jch-racWiFi-iduManagement-view-DetailedIduAdapter */
    public /* synthetic */ void mo30448xe128af97(int i) {
        notifyItemChanged(i);
    }

    public void onBindViewHolder(DetailedIduViewHolder detailedIduViewHolder, int i) {
        DetailedIduModel detailedIduModel = this.detailedIduModels.get(i);
        detailedIduViewHolder.bind(detailedIduModel);
        if (detailedIduModel.isTurnedOn()) {
            this.f470on = true;
        }
    }

    public int getItemCount() {
        return this.detailedIduModels.size();
    }

    public void stopAll() {
        int size = this.detailedIduModels.size();
        for (int i = 0; i < size; i++) {
            this.detailedIduModels.get(i).setPower(Power.OFF.name());
        }
        notifyDataSetChanged();
    }

    public void startAll() {
        int size = this.detailedIduModels.size();
        for (int i = 0; i < size; i++) {
            this.detailedIduModels.get(i).setPower(Power.ON.name());
        }
        notifyDataSetChanged();
    }

    public void preventCheckListeners() {
        this.preventSwitchApiCall = true;
    }

    public void activateCheckListeners() {
        this.preventSwitchApiCall = false;
    }

    public void stopAllPartially(List<StopAllUnitsModels.IndividualRacStopAllUintResponseData> list) {
        for (StopAllUnitsModels.IndividualRacStopAllUintResponseData next : list) {
            if (next.success) {
                updateIduPowerState((long) next.racId, Power.OFF.name());
            }
        }
        this.homePagePresenter.checkAndUpdateStopAllSwitch(this.detailedIduModels);
    }

    public void startAllPartially(List<StopAllUnitsModels.IndividualRacStartAllUnitResponseData> list) {
        for (StopAllUnitsModels.IndividualRacStartAllUnitResponseData next : list) {
            if (next.success) {
                updateIduPowerState((long) next.racId, Power.ON.name());
            }
        }
        this.homePagePresenter.checkAndUpdateStopAllSwitch(this.detailedIduModels);
    }

    public class DetailedIduViewHolder extends RecyclerView.ViewHolder implements EnableDisableOnChangeListener {
        ConstraintLayout constraintLayout;
        DetailedIduModel detailedIduModel;
        View itemView;
        ImageView ivAcMode;
        ConstraintLayout mHideableLayout;
        private final TextView mOfflineTextTitle;
        CustomSwitchButton sbTempratureInfo;
        boolean suspend = false;
        public boolean turnAllRacOn = false;
        TextView tvHumidityPercent;
        TextView tvHumidityTitle;
        TextView tvIduTemprature;
        TextView tvRoomName;
        TextView tvRoomTemprature;
        TextView tvRoomTempratureUnit;
        TextView tvTempratureUnit;

        private int getOperationModeDrawable(String str) {
            switch (C19653.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[OperationMode.valueOf(str).ordinal()]) {
                case 1:
                    return R.drawable.ic_color_auto_mode_svg;
                case 2:
                    return R.drawable.ic_color_cooling_mode_svg;
                case 3:
                    return R.drawable.ic_color_heating_mode_svg;
                case 4:
                    return R.drawable.ic_color_dehumidify_mode_svg;
                case 5:
                    return R.drawable.ic_color_fan_mode_svg;
                case 6:
                    return R.drawable.ic_color_dry_cool_mode_svg;
                case 7:
                    return R.drawable.circle_off;
                default:
                    return -1;
            }
        }

        public void bind(DetailedIduModel detailedIduModel2) {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
            String str;
            this.detailedIduModel = detailedIduModel2;
            this.mHideableLayout.setTag(detailedIduModel2);
            this.itemView.setTag(detailedIduModel2);
            RacModelWiseData racModelWiseDataBasedOnRacTypeId = DetailedIduAdapter.this.mHomePageFragment.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel2.cloudId);
            Logger.m45d("Testing ", detailedIduModel2.getName() + " " + detailedIduModel2.f454id);
            if (racModelWiseDataBasedOnRacTypeId != null && (racModeDetailBasedOnMode = racModelWiseDataBasedOnRacTypeId.getRacModeDetails().getRacModeDetailBasedOnMode(detailedIduModel2.getOperationModeEnum())) != null) {
                boolean isTurnedOn = detailedIduModel2.power != null ? detailedIduModel2.isTurnedOn() : false;
                int i = isTurnedOn ? 0 : 8;
                this.mHideableLayout.setTag(detailedIduModel2);
                this.tvRoomName.setText(detailedIduModel2.getName());
                this.tvTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                TextView textView = this.tvHumidityPercent;
                textView.setText(detailedIduModel2.getHumidity() + "%");
                detailedIduModel2.getRoomTemperature().floatValue();
                this.tvRoomTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                this.sbTempratureInfo.setCheckedSilent(isTurnedOn);
                RoleWisePermissionProvider.getInstance().getRoleWiseMemberPermissionOnOff(DetailedIduAdapter.this.activity, 2);
                this.itemView.setTag(detailedIduModel2);
                if (this.mHideableLayout.getVisibility() != i) {
                    this.mHideableLayout.setVisibility(i);
                }
                ViewUtils.setOutlineProviderSwitch(this.sbTempratureInfo);
                boolean iduPermission = UserPermissions.getInstance().getIduPermission(UserPermissions.IduFeatures.ON_OFF, detailedIduModel2.getId());
                if (iduPermission) {
                    this.sbTempratureInfo.setEnabled(true);
                } else {
                    this.sbTempratureInfo.setEnabled(false);
                }
                OperationMode valueOf = OperationMode.valueOf(detailedIduModel2.mode);
                RacModelWiseData.BleSettings enableSettings = racModeDetailBasedOnMode.getEnableSettings();
                if (enableSettings == null || !enableSettings.getHumidity()) {
                    this.tvHumidityTitle.setVisibility(8);
                    this.tvHumidityPercent.setVisibility(8);
                } else {
                    this.tvHumidityPercent.setVisibility(0);
                }
                RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
                if (temperatureSettingType == null || !temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    int i2 = (int) detailedIduModel2.iduTemperature;
                    float iduTemperature = detailedIduModel2.getIduTemperature();
                    if (i2 != Integer.MAX_VALUE) {
                        this.tvIduTemprature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(iduTemperature), racModelWiseDataBasedOnRacTypeId, detailedIduModel2));
                    } else {
                        this.tvIduTemprature.setText("--");
                    }
                } else if (((int) detailedIduModel2.relativeTemperature) != Integer.MAX_VALUE) {
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    float referenceTemperature = detailedIduModel2.relativeTemperature + racModeDetailBasedOnMode.getReferenceTemperature();
                    int i3 = (int) referenceTemperature;
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), racModelWiseDataBasedOnRacTypeId, detailedIduModel2);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(detailedIduModel2.relativeTemperature), racModelWiseDataBasedOnRacTypeId, detailedIduModel2);
                        i3 = (int) detailedIduModel2.relativeTemperature;
                    }
                    if (i3 != Integer.MAX_VALUE) {
                        this.tvIduTemprature.setText(str);
                    } else {
                        this.tvIduTemprature.setText("--");
                    }
                } else {
                    this.tvIduTemprature.setText("--");
                }
                if (valueOf.equals(OperationMode.FAN)) {
                    this.tvIduTemprature.setVisibility(8);
                    this.tvTempratureUnit.setVisibility(8);
                } else if (!valueOf.equals(OperationMode.AUTO)) {
                    this.tvIduTemprature.setVisibility(0);
                    this.tvTempratureUnit.setVisibility(0);
                } else if (!racModeDetailBasedOnMode.getVisibleSettings().getTemperature()) {
                    this.tvIduTemprature.setVisibility(8);
                    this.tvTempratureUnit.setVisibility(8);
                } else {
                    this.tvIduTemprature.setVisibility(0);
                    this.tvTempratureUnit.setVisibility(0);
                }
                if (!detailedIduModel2.online) {
                    this.sbTempratureInfo.setVisibility(4);
                    this.mOfflineTextTitle.setVisibility(0);
                    this.ivAcMode.setImageResource(R.drawable.device_offline_home);
                    this.mHideableLayout.setVisibility(8);
                    this.tvRoomName.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.color_cccccc));
                    this.mOfflineTextTitle.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.schedule_no_off_text));
                } else {
                    this.sbTempratureInfo.setVisibility(0);
                    this.mOfflineTextTitle.setVisibility(4);
                    this.tvRoomName.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.rac_name_oofline_color));
                    if (!detailedIduModel2.isTurnedOn()) {
                        this.ivAcMode.setImageResource(R.drawable.circle_off);
                    } else if (detailedIduModel2.isHolidayModeEnabled()) {
                        this.ivAcMode.setImageResource(R.drawable.ic_holiday_mode);
                    } else {
                        this.ivAcMode.setImageResource(getOperationModeDrawable(detailedIduModel2.mode));
                    }
                }
                if (valueOf.equals(OperationMode.UNKNOWN)) {
                    this.tvIduTemprature.setText(R.string.home_lbl_initialising);
                    this.tvTempratureUnit.setText("");
                    this.ivAcMode.setImageResource(R.drawable.device_offline_home);
                    this.sbTempratureInfo.setVisibility(4);
                    this.mOfflineTextTitle.setVisibility(4);
                    this.mHideableLayout.setVisibility(0);
                }
                if (detailedIduModel2.isInErrorState()) {
                    this.sbTempratureInfo.setEnabled(false);
                } else {
                    this.sbTempratureInfo.setEnabled(iduPermission);
                }
                if (detailedIduModel2.online) {
                    if (detailedIduModel2.isInSpecialMode()) {
                        showSpecialModeOrCriticalError(R.drawable.ic_grey_special_state_2_notification);
                    }
                    if (detailedIduModel2.isInErrorState()) {
                        showSpecialModeOrCriticalError(R.drawable.ic_red_critical_error_notification);
                    }
                }
                if (detailedIduModel2.online) {
                    this.constraintLayout.setBackgroundResource(R.drawable.border_with_10_dp_radius);
                } else {
                    this.constraintLayout.setBackgroundResource(R.drawable.filled_border_with_10_dp_radius);
                }
                this.sbTempratureInfo.setEnabled(iduPermission);
            }
        }

        private void showSpecialModeOrCriticalError(int i) {
            this.ivAcMode.setImageDrawable(new BitmapDrawable(DetailedIduAdapter.this.activity.getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) DetailedIduAdapter.this.activity.getResources().getDrawable(i)).getBitmap(), 80, 80, true)));
            this.tvIduTemprature.setVisibility(8);
            this.tvTempratureUnit.setVisibility(8);
            this.tvHumidityPercent.setVisibility(8);
        }

        private void updateIcon(DetailedIduModel detailedIduModel2) {
            OperationMode valueOf = OperationMode.valueOf(detailedIduModel2.mode);
            this.ivAcMode.setImageResource(valueOf.getOperationModeDrawableForHomePage());
            if (!detailedIduModel2.isOnline()) {
                this.ivAcMode.setImageResource(R.drawable.device_offline_home);
            } else if (!detailedIduModel2.isTurnedOn()) {
                this.ivAcMode.setImageResource(R.drawable.circle_off);
            } else if (detailedIduModel2.isInNormalMode()) {
                this.ivAcMode.setImageResource(valueOf.getOperationModeDrawableForHomePage());
            } else if (detailedIduModel2.isInSpecialMode()) {
                this.ivAcMode.setImageDrawable(new BitmapDrawable(DetailedIduAdapter.this.activity.getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) DetailedIduAdapter.this.activity.getResources().getDrawable(R.drawable.ic_grey_special_state_2_notification)).getBitmap(), 80, 80, true)));
            }
        }

        private void checkOfflineMode(DetailedIduModel detailedIduModel2) {
            if (!detailedIduModel2.isOnline()) {
                this.mHideableLayout.setVisibility(8);
                this.sbTempratureInfo.setVisibility(4);
                this.mOfflineTextTitle.setVisibility(0);
                this.tvRoomName.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.color_cccccc));
                this.mOfflineTextTitle.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.schedule_no_off_text));
                this.constraintLayout.setBackgroundResource(R.drawable.filled_border_with_10_dp_radius);
                return;
            }
            this.constraintLayout.setBackgroundResource(R.drawable.border_with_10_dp_radius);
            this.tvRoomName.setTextColor(ContextCompat.getColor(DetailedIduAdapter.this.activity, R.color.rac_name_oofline_color));
        }

        public DetailedIduViewHolder(View view) {
            super(view);
            this.itemView = view;
            this.ivAcMode = (ImageView) view.findViewById(R.id.image_view_ac_mode_home);
            this.tvRoomName = (TextView) view.findViewById(R.id.text_view_room_name_home);
            this.tvIduTemprature = (TextView) view.findViewById(R.id.text_view_room_temprature_home);
            this.tvTempratureUnit = (TextView) view.findViewById(R.id.text_view_temprature_unit_home);
            this.tvHumidityTitle = (TextView) view.findViewById(R.id.text_view_humidity_title_home);
            this.tvHumidityPercent = (TextView) view.findViewById(R.id.text_view_humidity_percent);
            this.tvRoomTemprature = (TextView) view.findViewById(R.id.text_view_room_temprature_small);
            this.tvRoomTempratureUnit = (TextView) view.findViewById(R.id.text_view_room_temprature_unit_small);
            this.sbTempratureInfo = (CustomSwitchButton) view.findViewById(R.id.switch_temprature_info_home);
            this.mHideableLayout = (ConstraintLayout) view.findViewById(R.id.layout_temperature_info);
            this.mOfflineTextTitle = (TextView) view.findViewById(R.id.offline_text_title);
            this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.layout_home);
            this.sbTempratureInfo.setOnCheckedChangeListener(this);
            view.setOnClickListener(new C1969xf6ec42ab(this));
        }

        /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-DetailedIduAdapter$DetailedIduViewHolder */
        public /* synthetic */ void mo30463x2894ca91(View view) {
            if (!DetailedIduAdapter.this.disableRacClick) {
                DetailedIduModel detailedIduModel2 = (DetailedIduModel) view.getTag();
                if (DetailedIduAdapter.this.mHomePageFragment.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel2.cloudId) == null) {
                    final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(DetailedIduAdapter.this.activity);
                    singleChoiceDialog.setTitleString(DetailedIduAdapter.this.activity.getString(R.string.common_alert));
                    singleChoiceDialog.setMessageString(DetailedIduAdapter.this.activity.getString(R.string.common_alert_unableToFindRacConfig));
                    singleChoiceDialog.setPositiveButton(DetailedIduAdapter.this.activity.getString(R.string.common_alert_backToHome), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            singleChoiceDialog.dismiss();
                            return true;
                        }
                    });
                    singleChoiceDialog.show();
                } else if (!detailedIduModel2.isOnline() || detailedIduModel2.mode.equals(OperationMode.UNKNOWN.name())) {
                } else {
                    if (UserPermissions.getInstance().getIduPermission(UserPermissions.UserFeatures.INDIVIDUAL_IDU_PAGE, detailedIduModel2.f454id)) {
                        this.sbTempratureInfo.setEnabled(false);
                        final Bundle bundle = new Bundle();
                        bundle.putParcelable(DetailedIduModel.PARCEL_KEY, detailedIduModel2);
                        bundle.putParcelable(DetailedIduModel.WEATHER_DETAILS, Weather.getCurrent());
                        DetailedIduAdapter.this.mHomePageFragment.getCoreActivity().showPleaseWaitDialog();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                DetailedIduAdapter.this.mHomePageFragment.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_homePageFragment_to_individualIDUControlFragmentV3, bundle);
                            }
                        }, 500);
                        return;
                    }
                    final SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(DetailedIduAdapter.this.activity);
                    singleChoiceDialog2.setTitleString(DetailedIduAdapter.this.activity.getString(R.string.common_alert));
                    singleChoiceDialog2.setMessageString(DetailedIduAdapter.this.activity.getString(R.string.common_alert_noPermissionToAccess));
                    singleChoiceDialog2.setCancelable(false);
                    singleChoiceDialog2.setPositiveButton(DetailedIduAdapter.this.activity.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            singleChoiceDialog2.dismiss();
                            return false;
                        }
                    });
                    singleChoiceDialog2.show();
                }
            }
        }

        public void suspendListener() {
            this.suspend = true;
        }

        public void onCheckedChanged(SwitchButton switchButton, boolean z) {
            RacModelWiseData racModelWiseDataBasedOnRacTypeId = DetailedIduAdapter.this.mHomePageFragment.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(this.detailedIduModel.cloudId);
            boolean iduPermission = UserPermissions.getInstance().getIduPermission(UserPermissions.UserFeatures.INDIVIDUAL_IDU_PAGE, this.detailedIduModel.f454id);
            if (NetworkConnectivity.isNetworkAvailable(DetailedIduAdapter.this.activity)) {
                if (!this.suspend) {
                    switchButton.setEnabled(false);
                    this.itemView.setEnabled(false);
                    switchButton.setEnabled(UserPermissions.getInstance().getIduPermission(UserPermissions.IduFeatures.ON_OFF, this.detailedIduModel.getId()));
                    this.itemView.setEnabled(iduPermission);
                    DetailedIduModel detailedIduModel2 = this.detailedIduModel;
                    if (!(detailedIduModel2 == null || racModelWiseDataBasedOnRacTypeId == null)) {
                        detailedIduModel2.power = (z ? Power.ON : Power.OFF).name();
                        if (this.detailedIduModel.isInSpecialMode()) {
                            this.detailedIduModel.copyDefaults(racModelWiseDataBasedOnRacTypeId);
                        }
                        this.detailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseDataBasedOnRacTypeId);
                        if (!z) {
                            DetailedIduAdapter.this.homePagePresenter.checkAndUpdateStopAllSwitch(DetailedIduAdapter.this.detailedIduModels);
                        } else {
                            DetailedIduAdapter.this.homePageView.checkStopAllButton();
                        }
                    }
                    DetailedIduAdapter.this.onPowerStateChanged();
                    this.mHideableLayout.setVisibility(z ? 0 : 8);
                    DetailedIduAdapter.this.homePagePresenter.requestIduPowerOnOff(DetailedIduAdapter.this.activity, this.detailedIduModel, (z ? Power.ON : Power.OFF).name());
                    if (this.detailedIduModel.isTurnedOn()) {
                        this.ivAcMode.setImageResource(getOperationModeDrawable(this.detailedIduModel.mode));
                    } else {
                        this.ivAcMode.setImageResource(R.drawable.circle_off);
                    }
                    DetailedIduAdapter.this.currentRacName = this.detailedIduModel.name;
                }
                this.suspend = false;
                return;
            }
            switchButton.setEnabled(UserPermissions.getInstance().getIduPermission(UserPermissions.IduFeatures.ON_OFF, this.detailedIduModel.getId()));
            this.itemView.setEnabled(iduPermission);
            DetailedIduAdapter.this.showInternetAlert(this.sbTempratureInfo, this.detailedIduModel.isTurnedOn());
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.DetailedIduAdapter$3 */
    static /* synthetic */ class C19653 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.OperationMode[] r0 = com.jch.racWiFi.iduManagement.model.OperationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode = r0
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.DetailedIduAdapter.C19653.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void showInternetAlert(final CustomSwitchButton customSwitchButton, final boolean z) {
        SingleChoiceDialog singleChoiceDialog = this.internetCheckDialog;
        if (singleChoiceDialog != null) {
            singleChoiceDialog.setTitleString(this.activity.getString(R.string.common_alert));
            this.internetCheckDialog.setMessageString(this.activity.getString(R.string.common_alert_unableToConnectToNw));
            this.internetCheckDialog.setCancelable(false);
            this.internetCheckDialog.setPositiveButton(this.activity.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    customSwitchButton.setCheckedSilent(z);
                    return true;
                }
            });
            if (!this.internetCheckDialog.isShowing()) {
                this.internetCheckDialog.show();
            }
        }
    }
}
