package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.SaveChangesListener;
import com.jch.racWiFi.TimerHolderModel;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.EnableDisableOnChangeListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.viewModel.TimerViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.timer.dialog.TimePickerDialogFragment;
import com.jch.racWiFi.timer.standard.TimeSetListener;
import com.jch.racWiFi.timer.util.Switch;
import com.jch.racWiFi.timer.util.TimeFormat;
import com.jch.racWiFi.timerPickerDialog.TimePickerDialog;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

public class SetTimerFragmentV2 extends GenericFragment implements LifecycleOwner, TimeSetListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Bundle args;
    private DetailedIduModel copyDetailedIduModel;
    private int currentDemoScriptIndex = 0;
    private Handler handler = new Handler();
    int humidityUnit = R.string.common_lbl_percentage;
    /* access modifiers changed from: private */
    public boolean isChangesDetected = false;
    private boolean isFragmentObjExist = false;
    private boolean isOnlyEnableTimer;
    private boolean isRequstForEnablingTimer;
    @BindView(2131362078)
    ImageButton mBack;
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel = new DetailedIduModel();
    private TimePickerDialog mDialogAll;
    @BindView(2131362827)
    ImageButton mImageButtonTimerSwitchOffAfter;
    @BindView(2131362828)
    ImageButton mImageButtonTimerSwitchOnAfter;
    @BindView(2131362957)
    ImageView mModeImage;
    @BindView(2131364347)
    TextView mModeName;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131364471)
    ImageButton mSave;
    @BindView(2131363269)
    ConstraintLayout mSetModeAndTemprature;
    @BindView(2131363792)
    CustomSwitchButton mSwitchOffAfter;
    @BindView(2131364608)
    TextView mSwitchOffAfterTitle;
    @BindView(2131363793)
    CustomSwitchButton mSwitchOnAfter;
    @BindView(2131364609)
    TextView mSwitchOnAfterTitle;
    @BindView(2131363796)
    CustomSwitchButton mSwitchTimer;
    @BindView(2131364645)
    TextView mTemperatureUnit;
    @BindView(2131364647)
    TextView mTemprature;
    @BindView(2131364662)
    TextView mTimeSwitchOffAfter;
    @BindView(2131364663)
    TextView mTimeSwitchOnAfter;
    /* access modifiers changed from: private */
    public TimerHolderModel mTimerHolderModel = new TimerHolderModel();
    private Unbinder mUnbinder;
    private Map<String, String> map = new HashMap();
    private DetailedIduModel orgDetailedIduModel;
    public int racId = 1;
    public Runnable runnable = new Runnable() {
        public void run() {
            if (SetTimerFragmentV2.this.timerMode != null) {
                switch (C20099.$SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode[SetTimerFragmentV2.this.timerMode.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        SetTimerFragmentV2.this.timerDisabled();
                        return;
                    case 4:
                        SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(true);
                        SetTimerFragmentV2.this.switchOnEnabled();
                        SetTimerFragmentV2.this.mSwitchOnAfter.setCheckedSilent(true);
                        SetTimerFragmentV2.this.mSetModeAndTemprature.setVisibility(0);
                        SetTimerFragmentV2.this.mSwitchOffAfter.setCheckedSilent(false);
                        SetTimerFragmentV2.this.switchOffDisabled();
                        return;
                    case 5:
                        SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(true);
                        SetTimerFragmentV2.this.mSwitchOffAfter.setCheckedSilent(true);
                        SetTimerFragmentV2.this.switchOffEnabled();
                        SetTimerFragmentV2.this.mSwitchOnAfter.setCheckedSilent(false);
                        SetTimerFragmentV2.this.mSetModeAndTemprature.setVisibility(8);
                        SetTimerFragmentV2.this.switchOnDisabled();
                        return;
                    case 6:
                        SetTimerFragmentV2.this.timerEnabled();
                        return;
                    default:
                        return;
                }
            } else {
                SetTimerFragmentV2.this.timerDisabled();
            }
        }
    };
    /* access modifiers changed from: private */
    public SaveChangesListener saveChangesListener;
    private long scheduleId = -1;
    boolean switchOnTimer = false;
    private int temperatureUnit = R.string.c_degree;
    /* access modifiers changed from: private */
    public WeeklyTimerMode timerMode;
    /* access modifiers changed from: private */
    public TimerViewModel timerViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.temperatureUnit = TemperatureUnit.getTemperatureUnitFromSettings();
        setDefaultValueOnTimer();
        setDefaultValueOffTimer();
        Bundle arguments = getArguments();
        this.args = arguments;
        if (arguments != null) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) arguments.getParcelable(DetailedIduModel.PARCEL_KEY);
            this.orgDetailedIduModel = detailedIduModel;
            this.mDetailedIduModel.copy(detailedIduModel);
            this.mDetailedIduModel.mode = OperationMode.COOLING.name();
            this.mDetailedIduModel.copyDefaults(this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(this.mDetailedIduModel.cloudId));
        }
        this.timerViewModel = (TimerViewModel) ViewModelProviders.m37of(requireActivity()).get(TimerViewModel.class);
        this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SaveChangesListener saveChangesListener2;
        View inflate = layoutInflater.inflate(R.layout.set_timer_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        DetailedIduModel detailedIduModel = this.mDetailedIduModel;
        detailedIduModel.copyModeAndTemperature(detailedIduModel);
        if (this.mDetailedIduModel.scheduletype != null) {
            this.timerMode = WeeklyTimerMode.valueOf(this.mDetailedIduModel.scheduletype);
        }
        this.mSwitchTimer.setOnCheckedChangeListener(new EnableDisableOnChangeListener() {
            private boolean suspend = false;

            public void suspendListener() {
                this.suspend = true;
            }

            public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                if (!this.suspend) {
                    if (z) {
                        SetTimerFragmentV2.this.timerEnabled();
                    } else {
                        SetTimerFragmentV2.this.timerDisabled();
                        SetTimerFragmentV2.this.mTimeSwitchOnAfter.setText(DateAndTimeUtils.setCurrentTime(SetTimerFragmentV2.this.mTimerHolderModel.startHour, SetTimerFragmentV2.this.mTimerHolderModel.startMinute));
                        SetTimerFragmentV2.this.mTimeSwitchOffAfter.setText(DateAndTimeUtils.setCurrentTime(SetTimerFragmentV2.this.mTimerHolderModel.endHour, SetTimerFragmentV2.this.mTimerHolderModel.endMinute));
                    }
                    if (SetTimerFragmentV2.this.saveChangesListener != null) {
                        SetTimerFragmentV2.this.saveChangesListener.onChanged(true);
                    }
                }
                this.suspend = false;
            }
        });
        this.mSwitchOnAfter.setOnCheckedChangeListener(new EnableDisableOnChangeListener() {
            private boolean suspend = false;

            public void suspendListener() {
                this.suspend = true;
            }

            public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                if (!this.suspend) {
                    if (z) {
                        SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(true);
                        SetTimerFragmentV2.this.switchOnEnabled();
                    } else {
                        SetTimerFragmentV2.this.mSetModeAndTemprature.setVisibility(8);
                        SetTimerFragmentV2.this.switchOnDisabled();
                        if (!SetTimerFragmentV2.this.mSwitchOffAfter.isChecked()) {
                            SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(false);
                            SetTimerFragmentV2.this.timerDisabled();
                        }
                    }
                    SetTimerFragmentV2.this.mTimeSwitchOnAfter.setText(DateAndTimeUtils.setCurrentTime(SetTimerFragmentV2.this.mTimerHolderModel.startHour, SetTimerFragmentV2.this.mTimerHolderModel.startMinute));
                    if (SetTimerFragmentV2.this.saveChangesListener != null) {
                        SetTimerFragmentV2.this.saveChangesListener.onChanged(true);
                    }
                }
                this.suspend = false;
            }
        });
        this.mSwitchOffAfter.setOnCheckedChangeListener(new EnableDisableOnChangeListener() {
            private boolean suspend = false;

            public void suspendListener() {
                this.suspend = true;
            }

            public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                if (!this.suspend) {
                    if (z) {
                        SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(true);
                        SetTimerFragmentV2.this.switchOffEnabled();
                    } else {
                        SetTimerFragmentV2.this.switchOffDisabled();
                        if (!SetTimerFragmentV2.this.mSwitchOnAfter.isChecked()) {
                            SetTimerFragmentV2.this.mSwitchTimer.setCheckedSilent(false);
                            SetTimerFragmentV2.this.timerDisabled();
                        }
                    }
                }
                SetTimerFragmentV2.this.mTimeSwitchOffAfter.setText(DateAndTimeUtils.setCurrentTime(SetTimerFragmentV2.this.mTimerHolderModel.endHour, SetTimerFragmentV2.this.mTimerHolderModel.endMinute));
                this.suspend = false;
                if (SetTimerFragmentV2.this.saveChangesListener != null) {
                    SetTimerFragmentV2.this.saveChangesListener.onChanged(true);
                }
            }
        });
        ViewUtils.setOutlineProviderSwitch(this.mSwitchTimer);
        ViewUtils.setOutlineProviderSwitch(this.mSwitchOnAfter);
        ViewUtils.setOutlineProviderSwitch(this.mSwitchOffAfter);
        updateUI();
        if (!this.isFragmentObjExist) {
            boolean is24HourFormat = DateFormat.is24HourFormat(getActivity());
            this.map.put(Switch.SWITCH_ON_AT.name(), (is24HourFormat ? TimeFormat.IN_24_HRS_FORMAT : TimeFormat.IN_12_HRS_FORMAT).name());
            this.map.put(Switch.SWITCH_OFF_AT.name(), (is24HourFormat ? TimeFormat.IN_24_HRS_FORMAT : TimeFormat.IN_12_HRS_FORMAT).name());
            this.mTimerHolderModel.format = this.map.get(Switch.SWITCH_ON_AT.name());
            this.isFragmentObjExist = true;
            showPleaseWaitDialog();
            this.timerViewModel.getTimerSchedule(this.mDetailedIduModel.f454id.intValue(), true);
        } else {
            DetailedIduModel detailedIduModel2 = this.copyDetailedIduModel;
            if (!(detailedIduModel2 == null || ((detailedIduModel2.mode.equals(this.mDetailedIduModel.mode) && this.copyDetailedIduModel.iduTemperature == this.mDetailedIduModel.iduTemperature && this.copyDetailedIduModel.relativeTemperature == this.mDetailedIduModel.relativeTemperature) || (saveChangesListener2 = this.saveChangesListener) == null))) {
                saveChangesListener2.onChanged(true);
            }
        }
        liveDataObservers();
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.post(this.runnable);
        }
        this.saveChangesListener = new SaveChangesListener() {
            public void onChanged(boolean z) {
                SetTimerFragmentV2.this.isChangesDetected = z;
            }
        };
        return inflate;
    }

    private void liveDataObservers() {
        this.timerViewModel.getTimerFetchResponseMutableLiveData().observe(getViewLifecycleOwner(), new SetTimerFragmentV2$$ExternalSyntheticLambda2(this));
        this.timerViewModel.getTimerUpdateResponseMutableLiveData().observe(getViewLifecycleOwner(), new SetTimerFragmentV2$$ExternalSyntheticLambda3(this));
        this.timerViewModel.getTimerEnabledResponseMutableLiveData().observe(getViewLifecycleOwner(), new SetTimerFragmentV2$$ExternalSyntheticLambda1(this));
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new SetTimerFragmentV2$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$liveDataObservers$0$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ void mo30604x14e70f4b(TimerModels.TimerFetchResponse timerFetchResponse) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (timerFetchResponse.throwable != null) {
                dismissPleaseWaitDialog();
                if (timerFetchResponse.throwable != null && !(timerFetchResponse.throwable instanceof SocketException)) {
                    alertDialog(getResources().getString(R.string.common_alert_unableToReachServer), false);
                }
            } else if (timerFetchResponse.isSuccessful()) {
                TimerModels.ResponseData responseData = (TimerModels.ResponseData) timerFetchResponse.response;
                dismissPleaseWaitDialog();
                if (responseData != null) {
                    this.map.put(Switch.SWITCH_ON_AT.name(), responseData.displayFormat != null ? responseData.displayFormat : TimeFormat.IN_24_HRS_FORMAT.name());
                    this.map.put(Switch.SWITCH_OFF_AT.name(), responseData.displayFormat != null ? responseData.displayFormat : TimeFormat.IN_24_HRS_FORMAT.name());
                    if (this.timerViewModel.getRestTimeOfExecutionInMinute(responseData.startsAt) <= 0) {
                        this.mSwitchOnAfter.setChecked(false);
                        switchOnDisabled();
                    } else {
                        String time = DateAndTimeUtils.getTime(responseData.startsAt);
                        int parseInt = Integer.parseInt(DateAndTimeUtils.getHourFromTimerStrings(time));
                        int parseInt2 = Integer.parseInt(DateAndTimeUtils.getMinutesFromTimeStrings(time));
                        this.mTimerHolderModel.startHour = parseInt;
                        this.mTimerHolderModel.startMinute = parseInt2;
                    }
                    if (this.timerViewModel.getRestTimeOfExecutionInMinute(responseData.endsAt) <= 0) {
                        this.mSwitchOffAfter.setChecked(false);
                        switchOffDisabled();
                    } else {
                        String time2 = DateAndTimeUtils.getTime(responseData.endsAt);
                        int parseInt3 = Integer.parseInt(DateAndTimeUtils.getHourFromTimerStrings(time2));
                        int parseInt4 = Integer.parseInt(DateAndTimeUtils.getMinutesFromTimeStrings(time2));
                        this.mTimerHolderModel.endHour = parseInt3;
                        this.mTimerHolderModel.endMinute = parseInt4;
                    }
                    if (!this.mSwitchOnAfter.isChecked() && !this.mSwitchOffAfter.isChecked()) {
                        this.mSwitchTimer.setCheckedSilent(false);
                        timerDisabled();
                    }
                    this.mDetailedIduModel.mode = responseData.mode;
                    RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.orgDetailedIduModel.cloudId).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.valueOf(this.mDetailedIduModel.mode));
                    if (racModeDetailBasedOnMode != null) {
                        RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
                        if (temperatureSettingType == null || !temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                            this.mDetailedIduModel.iduTemperature = (float) responseData.temperature;
                        } else {
                            this.mDetailedIduModel.relativeTemperature = (float) responseData.temperature;
                        }
                    }
                    this.scheduleId = responseData.f461id;
                }
                updateUI();
            } else {
                dismissPleaseWaitDialog();
                int i = timerFetchResponse.statusCode;
                if (i == 400) {
                    showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
                } else if (i == 401) {
                    showPleaseWaitDialog();
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            SetTimerFragmentV2.this.showPleaseWaitDialog();
                            SetTimerFragmentV2.this.timerViewModel.getTimerSchedule(SetTimerFragmentV2.this.mDetailedIduModel.f454id.intValue(), true);
                        }
                    }, false);
                } else if (i != 404) {
                    alertDialog(getResources().getString(R.string.timer_alert_unableToFetchTimerDetails), false);
                } else {
                    showErrorPopUp(getString(timerFetchResponse.getErrorMessageStringId(String.valueOf(timerFetchResponse.statusCode))));
                }
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$1$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ void mo30605x3e3b648c(TimerModels.TimerUpdateResponse timerUpdateResponse) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || timerUpdateResponse.throwable != null) {
            return;
        }
        if (timerUpdateResponse.isSuccessful()) {
            this.isRequstForEnablingTimer = true;
            enableDisableTimer();
            return;
        }
        dismissPleaseWaitDialog();
        int i = timerUpdateResponse.statusCode;
        if (i == 400) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        } else if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    SetTimerFragmentV2.this.timerRequest();
                }
            }, false);
        } else if (i == 404) {
            showErrorPopUp(getString(timerUpdateResponse.getErrorMessageStringId(String.valueOf(timerUpdateResponse.statusCode))));
        } else if (timerUpdateResponse.statusCode == 412) {
            try {
                if (new JSONObject(timerUpdateResponse.response.toString()).getString("code").equals(TimerModels.TimerUpdateResponse.ERROR_PCF011)) {
                    alertDialog(getResources().getString(R.string.errorCode_alert_PCF011), false);
                } else {
                    alertDialog(getResources().getString(R.string.errorCode_alert_PCF009), false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                alertDialog(getResources().getString(R.string.errorCode_alert_PCF009), false);
            }
        } else if (timerUpdateResponse.statusCode >= 500) {
            alertDialog(getResources().getString(R.string.common_alert_somethingWentWrong), false);
        } else {
            alertDialog(getResources().getString(R.string.timer_alert_timerUpdateFailed), false);
        }
    }

    /* renamed from: lambda$liveDataObservers$2$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ void mo30606x678fb9cd(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            dismissPleaseWaitDialog();
            if (timerEnabledResponse.throwable != null) {
                if (!(timerEnabledResponse.throwable instanceof SocketException)) {
                    alertDialog(getResources().getString(R.string.common_alert_unableToConnectToNw), false);
                }
            } else if (timerEnabledResponse.isSuccessful()) {
                this.orgDetailedIduModel.scheduletype = getCurrentState().name();
                this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
                this.timerMode = WeeklyTimerMode.valueOf(getCurrentState().name());
                if (!this.isRequstForEnablingTimer) {
                    alertDialog(getResources().getString(R.string.timer_alert_timerDisabled), true);
                } else if (!this.isOnlyEnableTimer) {
                    if (this.scheduleId == -1) {
                        Toaster.makeToast(getActivity(), getResources().getString(R.string.timer_alert_timerUpdatedSucc), 0);
                    }
                    goBackFragment();
                } else {
                    alertDialog(getResources().getString(R.string.timer_alert_timerEnabled), false);
                }
            } else {
                int i = timerEnabledResponse.statusCode;
                if (i == 400) {
                    showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
                } else if (i == 401) {
                    showPleaseWaitDialog();
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            SetTimerFragmentV2.this.enableDisableTimer();
                        }
                    }, false);
                } else if (i == 404) {
                    showErrorPopUp(getString(timerEnabledResponse.getErrorMessageStringId(String.valueOf(timerEnabledResponse.statusCode))));
                } else if (this.isRequstForEnablingTimer) {
                    if (timerEnabledResponse.statusCode == 412) {
                        alertDialog(getResources().getString(R.string.errorCode_alert_PCF009), false);
                    } else {
                        alertDialog(getResources().getString(R.string.timer_alert_timerEnableFailed), false);
                    }
                } else if (timerEnabledResponse.statusCode == 412) {
                    alertDialog(getResources().getString(R.string.errorCode_alert_PCF009), false);
                } else {
                    alertDialog(getResources().getString(R.string.timer_alert_timerDisableFailed), false);
                }
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$3$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ void mo30607x90e40f0e(DetailedIduModel detailedIduModel) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && detailedIduModel.equals(this.orgDetailedIduModel)) {
            this.orgDetailedIduModel.scheduletype = detailedIduModel.scheduletype;
            this.mDetailedIduModel.scheduletype = detailedIduModel.scheduletype;
            this.timerMode = WeeklyTimerMode.valueOf(detailedIduModel.scheduletype);
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.postDelayed(this.runnable, 100);
            }
        }
    }

    private void setDefaultValueOnTimer() {
        this.mTimerHolderModel.startHour = 0;
        this.mTimerHolderModel.startMinute = 10;
        String times = DateAndTimeUtils.getTimes(DateAndTimeUtils.addTime(this.mTimerHolderModel.startHour, this.mTimerHolderModel.startMinute));
        int parseInt = Integer.parseInt(DateAndTimeUtils.getHourFromTimerStrings(times));
        int parseInt2 = Integer.parseInt(DateAndTimeUtils.getMinutesFromTimeStrings(times));
        this.mTimerHolderModel.startHour = parseInt;
        this.mTimerHolderModel.startMinute = parseInt2;
    }

    private void setDefaultValueOffTimer() {
        this.mTimerHolderModel.endHour = 0;
        this.mTimerHolderModel.endMinute = 20;
        String times = DateAndTimeUtils.getTimes(DateAndTimeUtils.addTime(this.mTimerHolderModel.endHour, this.mTimerHolderModel.endMinute));
        int parseInt = Integer.parseInt(DateAndTimeUtils.getHourFromTimerStrings(times));
        int parseInt2 = Integer.parseInt(DateAndTimeUtils.getMinutesFromTimeStrings(times));
        this.mTimerHolderModel.endHour = parseInt;
        this.mTimerHolderModel.endMinute = parseInt2;
    }

    private void updateUI() {
        String str;
        OperationMode valueOf = OperationMode.valueOf(this.mDetailedIduModel.mode);
        RacModelWiseData racModelWiseDataBasedOnRacTypeId = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.orgDetailedIduModel.cloudId);
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = racModelWiseDataBasedOnRacTypeId.getRacModeDetails().getRacModeDetailBasedOnMode(valueOf);
        if (racModeDetailBasedOnMode != null) {
            RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
            if (temperatureSettingType != null && temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                float referenceTemperature = this.mDetailedIduModel.relativeTemperature + racModeDetailBasedOnMode.getReferenceTemperature();
                if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), racModelWiseDataBasedOnRacTypeId, this.mDetailedIduModel);
                } else {
                    str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(this.mDetailedIduModel.relativeTemperature), racModelWiseDataBasedOnRacTypeId, this.mDetailedIduModel);
                }
                this.mTemprature.setText(str);
            } else if (this.orgDetailedIduModel.iduTemperature != Float.MAX_VALUE) {
                this.mTemprature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(this.mDetailedIduModel.iduTemperature), racModelWiseDataBasedOnRacTypeId, this.mDetailedIduModel));
            }
        }
        if (valueOf.equals(OperationMode.FAN)) {
            this.mTemprature.setVisibility(4);
            this.mTemperatureUnit.setVisibility(4);
        } else if (!valueOf.equals(OperationMode.AUTO)) {
            this.mTemprature.setVisibility(0);
            this.mTemperatureUnit.setVisibility(0);
        } else if (!racModeDetailBasedOnMode.getVisibleSettings().getTemperature()) {
            this.mTemprature.setVisibility(4);
            this.mTemperatureUnit.setVisibility(4);
        }
        this.mTemperatureUnit.setText(this.temperatureUnit);
        this.mModeImage.setBackgroundResource(OperationMode.valueOf(this.mDetailedIduModel.mode).getDrawableResTimer());
        String string = getString(OperationMode.valueOf(this.mDetailedIduModel.mode).getStringRes());
        if (string.length() >= 12) {
            String substring = string.substring(0, 11);
            TextView textView = this.mModeName;
            textView.setText(substring + "...");
        } else {
            this.mModeName.setText(string);
        }
        switch (C20099.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[OperationMode.valueOf(this.mDetailedIduModel.mode).ordinal()]) {
            case 1:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_auto_global));
                break;
            case 2:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_cooling_global));
                break;
            case 3:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_heating_global));
                break;
            case 4:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_dehumidify_global));
                break;
            case 5:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_fan_global));
                break;
            case 6:
                this.mModeName.setTextColor(getResources().getColor(R.color.color_dry_cool_global));
                break;
            case 7:
                this.mModeName.setText(getResources().getString(R.string.common_lbl_off));
                break;
        }
        Map<String, String> map2 = this.map;
        if (map2 == null || map2.isEmpty()) {
            this.mTimeSwitchOnAfter.setText(DateAndTimeUtils.setCurrentTime(this.mTimerHolderModel.startHour, this.mTimerHolderModel.startMinute));
            this.mTimeSwitchOffAfter.setText(DateAndTimeUtils.setCurrentTime(this.mTimerHolderModel.endHour, this.mTimerHolderModel.endMinute));
            return;
        }
        setTime(this.map.get(Switch.SWITCH_ON_AT.name()), this.mTimeSwitchOnAfter, this.mTimerHolderModel.startHour, this.mTimerHolderModel.startMinute);
        setTime(this.map.get(Switch.SWITCH_ON_AT.name()), this.mTimeSwitchOffAfter, this.mTimerHolderModel.endHour, this.mTimerHolderModel.endMinute);
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.SetTimerFragmentV2$9 */
    static /* synthetic */ class C20099 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0065 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008d */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.OperationMode[] r0 = com.jch.racWiFi.iduManagement.model.OperationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode = r0
                r1 = 1
                com.jch.racWiFi.iduManagement.model.OperationMode r2 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r3 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r4 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r5 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.OperationMode r6 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.OperationMode r7 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r6 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.model.OperationMode r7 = com.jch.racWiFi.iduManagement.model.OperationMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r8 = 7
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode[] r6 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode = r6
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r7 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.SCHEDULE_DISABLED     // Catch:{ NoSuchFieldError -> 0x0065 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x0065 }
            L_0x0065:
                int[] r1 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x006f }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r6 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.WEEKLY_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x006f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.HOLIDAY_MODE_ENABLED     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x008d }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x008d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008d }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x008d }
            L_0x008d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0097 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0097 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0097 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x0097 }
            L_0x0097:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.SetTimerFragmentV2.C20099.<clinit>():void");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    public void onDestroy() {
        dismissPleaseWaitDialog();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @OnClick({2131362078})
    public void OnClickBack(ImageButton imageButton) {
        if (this.isChangesDetected) {
            confirmationForSaveChanges();
        } else {
            goBackFragment();
        }
    }

    @OnClick({2131364471})
    public void OnClickSave(ImageButton imageButton) {
        saveTimer();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveTimer() {
        /*
            r4 = this;
            r0 = 0
            com.jch.racWiFi.TimerHolderModel r1 = r4.mTimerHolderModel     // Catch:{ ParseException -> 0x001c }
            int r1 = r1.startHour     // Catch:{ ParseException -> 0x001c }
            com.jch.racWiFi.TimerHolderModel r2 = r4.mTimerHolderModel     // Catch:{ ParseException -> 0x001c }
            int r2 = r2.startMinute     // Catch:{ ParseException -> 0x001c }
            java.util.Calendar r1 = com.jch.racWiFi.Utils.DateAndTimeUtils.getCalendar(r1, r2)     // Catch:{ ParseException -> 0x001c }
            com.jch.racWiFi.TimerHolderModel r2 = r4.mTimerHolderModel     // Catch:{ ParseException -> 0x001a }
            int r2 = r2.endHour     // Catch:{ ParseException -> 0x001a }
            com.jch.racWiFi.TimerHolderModel r3 = r4.mTimerHolderModel     // Catch:{ ParseException -> 0x001a }
            int r3 = r3.endMinute     // Catch:{ ParseException -> 0x001a }
            java.util.Calendar r0 = com.jch.racWiFi.Utils.DateAndTimeUtils.getCalendar(r2, r3)     // Catch:{ ParseException -> 0x001a }
            goto L_0x0021
        L_0x001a:
            r2 = move-exception
            goto L_0x001e
        L_0x001c:
            r2 = move-exception
            r1 = r0
        L_0x001e:
            r2.printStackTrace()
        L_0x0021:
            int r2 = com.jch.racWiFi.Utils.DateAndTimeUtils.compareTime(r1, r0)
            r3 = 1
            if (r2 != 0) goto L_0x0050
            com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton r2 = r4.mSwitchOnAfter
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x0050
            com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton r2 = r4.mSwitchOffAfter
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x0050
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            android.content.res.Resources r1 = r4.getResources()
            r2 = 2131953503(0x7f13075f, float:1.9543479E38)
            java.lang.String r1 = r1.getString(r2)
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r3)
            r0.show()
            goto L_0x00df
        L_0x0050:
            int r0 = com.jch.racWiFi.Utils.DateAndTimeUtils.compareTime(r1, r0)
            if (r0 != r3) goto L_0x007d
            com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton r0 = r4.mSwitchOnAfter
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x007d
            com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton r0 = r4.mSwitchOffAfter
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x007d
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            android.content.res.Resources r1 = r4.getResources()
            r2 = 2131953507(0x7f130763, float:1.9543487E38)
            java.lang.String r1 = r1.getString(r2)
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r3)
            r0.show()
            goto L_0x00df
        L_0x007d:
            java.util.Map<java.lang.String, java.lang.String> r0 = r4.map
            com.jch.racWiFi.timer.util.Switch r1 = com.jch.racWiFi.timer.util.Switch.SWITCH_ON_AT
            java.lang.String r1 = r1.name()
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.util.Map<java.lang.String, java.lang.String> r1 = r4.map
            com.jch.racWiFi.timer.util.Switch r2 = com.jch.racWiFi.timer.util.Switch.SWITCH_OFF_AT
            java.lang.String r2 = r2.name()
            java.lang.Object r1 = r1.get(r2)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00a1
            r4.performSaveTask()
            goto L_0x00df
        L_0x00a1:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            if (r0 == 0) goto L_0x00df
            com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog r0 = new com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog
            androidx.fragment.app.FragmentActivity r1 = r4.getActivity()
            r0.<init>(r1)
            r1 = 2131951732(0x7f130074, float:1.9539887E38)
            java.lang.String r1 = r4.getString(r1)
            r0.setTitleString(r1)
            android.content.res.Resources r1 = r4.getResources()
            r2 = 2131953505(0x7f130761, float:1.9543483E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setMessageString((java.lang.String) r1)
            r1 = 0
            r0.setCancelable(r1)
            androidx.fragment.app.FragmentActivity r1 = r4.getActivity()
            r2 = 2131951781(0x7f1300a5, float:1.9539986E38)
            java.lang.String r1 = r1.getString(r2)
            com.jch.racWiFi.iduManagement.view.SetTimerFragmentV2$$ExternalSyntheticLambda7 r2 = com.jch.racWiFi.iduManagement.view.SetTimerFragmentV2$$ExternalSyntheticLambda7.INSTANCE
            r0.setPositiveButton(r1, r2)
            r0.show()
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.SetTimerFragmentV2.saveTimer():void");
    }

    private void performSaveTask() {
        if (!this.mSwitchTimer.isChecked()) {
            showPleaseWaitDialog();
            this.isRequstForEnablingTimer = false;
            enableDisableTimer();
        } else if (!this.mSwitchOffAfter.isChecked() || !this.mSwitchOnAfter.isChecked() || Math.abs(((this.mTimerHolderModel.startHour * 60) + this.mTimerHolderModel.startMinute) - ((this.mTimerHolderModel.endHour * 60) + this.mTimerHolderModel.endMinute)) >= 10) {
            if (!this.mSwitchOnAfter.isChecked()) {
                this.mTimerHolderModel.startHour = 0;
                this.mTimerHolderModel.startMinute = 0;
            }
            if (!this.mSwitchOffAfter.isChecked()) {
                this.mTimerHolderModel.endHour = 0;
                this.mTimerHolderModel.endMinute = 0;
            }
            timerRequest();
        } else {
            timerScheduledError(getResources().getString(R.string.timer_alert_minTimeDiffDesc));
        }
    }

    /* access modifiers changed from: private */
    public void timerRequest() {
        String str;
        String str2;
        RacModelWiseData.TemperatureSettingType temperatureSettingType;
        float f;
        showPleaseWaitDialog();
        this.isOnlyEnableTimer = false;
        if (this.mSwitchOnAfter.isChecked()) {
            str = DateAndTimeUtils.getTime(this.mTimerHolderModel.startHour, this.mTimerHolderModel.startMinute);
        } else {
            str = DateAndTimeUtils.getCurrentDateTimeAsPerPattern(DateAndTimeUtils.DATE_FORMAT_yyyy_MM_dd_HH_mm, 10);
        }
        String[] split = str.split(" ");
        String str3 = split[0] + ExifInterface.GPS_DIRECTION_TRUE + split[1] + ":00";
        if (this.mSwitchOffAfter.isChecked()) {
            str2 = DateAndTimeUtils.getTime(this.mTimerHolderModel.endHour, this.mTimerHolderModel.endMinute);
        } else {
            str2 = DateAndTimeUtils.getCurrentDateTimeAsPerPattern(DateAndTimeUtils.DATE_FORMAT_yyyy_MM_dd_HH_mm, 10);
        }
        String[] split2 = str2.split(" ");
        String str4 = split2[0] + ExifInterface.GPS_DIRECTION_TRUE + split2[1] + ":00";
        double d = C1030Utils.DOUBLE_EPSILON;
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.orgDetailedIduModel.cloudId).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.valueOf(this.mDetailedIduModel.mode));
        if (!(racModeDetailBasedOnMode == null || (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) == null)) {
            if (temperatureSettingType == RacModelWiseData.TemperatureSettingType.RELATIVE) {
                f = this.mDetailedIduModel.relativeTemperature;
            } else {
                f = this.mDetailedIduModel.iduTemperature;
            }
            d = (double) f;
        }
        this.timerViewModel.changeTimer(this.scheduleId, this.mDetailedIduModel.f454id.intValue(), this.mSwitchTimer.isChecked() ? DetailedIduModel.POWER_ON : DetailedIduModel.POWER_OFF, d, str3, str4, this.mDetailedIduModel.mode, this.mTimerHolderModel.format);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362828})
    public void OnClickTimerSwitchOnAfter(ImageButton imageButton) {
        launchDialog(true, Switch.SWITCH_ON_AT);
    }

    private void launchDialog(boolean z, Switch switchR) {
        if (getActivity() != null) {
            FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SWITCH, switchR.name());
            bundle.putString(Constants.DISPLAY_FORMAT, this.map.get(Switch.SWITCH_ON_AT.name()));
            TimePickerDialogFragment newInstance = TimePickerDialogFragment.newInstance(bundle);
            newInstance.setCancelable(false);
            newInstance.setTimeSetListener(this);
            newInstance.show(supportFragmentManager, TimePickerDialogFragment.class.getCanonicalName());
        }
        this.switchOnTimer = z;
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362827})
    public void OnClickTimerSwitchOffAfter(ImageButton imageButton) {
        launchDialog(false, Switch.SWITCH_OFF_AT);
    }

    public void onTimeSet(int i, int i2, TimeFormat timeFormat, String str) {
        Logger.m47e(GenericFragment.TAG, "onTimeSet: " + timeFormat.name());
        this.map.put(str, timeFormat.name());
        if ((i * 60) + i2 >= 10) {
            if (this.switchOnTimer) {
                this.mTimerHolderModel.startHour = i;
                this.mTimerHolderModel.startMinute = i2;
                setTime(timeFormat.name(), this.mTimeSwitchOnAfter, this.mTimerHolderModel.startHour, this.mTimerHolderModel.startMinute);
            } else {
                this.mTimerHolderModel.endHour = i;
                this.mTimerHolderModel.endMinute = i2;
                setTime(timeFormat.name(), this.mTimeSwitchOffAfter, this.mTimerHolderModel.endHour, this.mTimerHolderModel.endMinute);
            }
            this.mTimerHolderModel.format = timeFormat.name();
            this.saveChangesListener.onChanged(true);
        }
        this.saveChangesListener.onChanged(true);
    }

    private void setTime(String str, TextView textView, int i, int i2) {
        if (!str.equals(TimeFormat.IN_12_HRS_FORMAT.name())) {
            textView.setText(DateAndTimeUtils.setCurrentTime(i, i2));
        } else if (i <= 12) {
            textView.setText(DateAndTimeUtils.setCurrentTime(i, i2) + " AM");
        } else {
            textView.setText(DateAndTimeUtils.setCurrentTime(i - 12, i2) + " PM");
        }
    }

    private void timerScheduledError(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.timer_alert_scheduleAlert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new SetTimerFragmentV2$$ExternalSyntheticLambda6(singleChoiceDialog));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131363269})
    public void OnClickSetModeAndTemperature(ConstraintLayout constraintLayout) {
        DetailedIduModel detailedIduModel = new DetailedIduModel();
        this.copyDetailedIduModel = detailedIduModel;
        detailedIduModel.copyModeAndTemperature(this.mDetailedIduModel);
        this.mDetailedIduModel.scheduletype = getCurrentState().name();
        Bundle bundle = new Bundle();
        bundle.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1001);
        bundle.putParcelable(DetailedIduModel.PARCEL_KEY, this.mDetailedIduModel);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_setTimerFragmentV2_to_setModeAndTempratureFragmentV2, bundle);
    }

    /* access modifiers changed from: private */
    public void timerEnabled() {
        this.mSwitchOnAfter.setEnabled(true);
        this.mSwitchOffAfter.setEnabled(true);
        this.mSwitchOnAfter.setCheckedSilent(true);
        this.mSwitchOffAfter.setCheckedSilent(true);
        this.mSetModeAndTemprature.setVisibility(0);
        this.mSwitchTimer.setCheckedSilent(true);
        this.mTimeSwitchOnAfter.setVisibility(0);
        this.mSwitchOnAfterTitle.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        this.mSwitchOffAfterTitle.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        this.mImageButtonTimerSwitchOnAfter.setVisibility(0);
        this.mImageButtonTimerSwitchOffAfter.setVisibility(0);
        this.mTimeSwitchOffAfter.setVisibility(0);
        this.mSave.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void timerDisabled() {
        this.mSwitchOnAfter.setCheckedSilent(false);
        this.mSwitchOffAfter.setCheckedSilent(false);
        this.mSwitchOnAfter.setEnabled(false);
        this.mSwitchOffAfter.setEnabled(false);
        this.mSetModeAndTemprature.setVisibility(8);
        this.mSwitchTimer.setCheckedSilent(false);
        this.mTimeSwitchOnAfter.setVisibility(8);
        this.mSwitchOnAfterTitle.setTextColor(getResources().getColor(R.color.color_disabled_views));
        this.mSwitchOffAfterTitle.setTextColor(getResources().getColor(R.color.color_disabled_views));
        this.mImageButtonTimerSwitchOnAfter.setVisibility(8);
        this.mImageButtonTimerSwitchOffAfter.setVisibility(8);
        this.mTimeSwitchOffAfter.setVisibility(8);
        saveButtonHideVisible();
    }

    private void saveButtonHideVisible() {
        WeeklyTimerMode valueOf = WeeklyTimerMode.valueOf(this.orgDetailedIduModel.scheduletype);
        if (valueOf != null) {
            switch (C20099.$SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode[valueOf.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    this.mSave.setVisibility(4);
                    return;
                case 4:
                case 5:
                case 6:
                    this.mSave.setVisibility(0);
                    return;
                default:
                    return;
            }
        } else {
            this.mSave.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public void switchOnEnabled() {
        this.mSwitchOnAfterTitle.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        this.mTimeSwitchOnAfter.setVisibility(0);
        this.mImageButtonTimerSwitchOnAfter.setVisibility(0);
        this.mSetModeAndTemprature.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void switchOnDisabled() {
        this.mSwitchOnAfterTitle.setTextColor(getResources().getColor(R.color.color_disabled_views));
        this.mTimeSwitchOnAfter.setVisibility(8);
        this.mImageButtonTimerSwitchOnAfter.setVisibility(8);
        this.mSetModeAndTemprature.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void switchOffEnabled() {
        this.mSwitchOffAfterTitle.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        this.mTimeSwitchOffAfter.setVisibility(0);
        this.mImageButtonTimerSwitchOffAfter.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void switchOffDisabled() {
        this.mSwitchOffAfterTitle.setTextColor(getResources().getColor(R.color.color_disabled_views));
        this.mTimeSwitchOffAfter.setVisibility(8);
        this.mImageButtonTimerSwitchOffAfter.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void enableDisableTimer() {
        if (!this.mSwitchTimer.isChecked()) {
            this.timerViewModel.operationTimerScheduleDisable((long) this.mDetailedIduModel.f454id.intValue());
        } else if (this.mSwitchOnAfter.isChecked() && this.mSwitchOffAfter.isChecked()) {
            this.timerViewModel.operationOnOffTimer((long) this.mDetailedIduModel.f454id.intValue());
        } else if (this.mSwitchOnAfter.isChecked()) {
            this.timerViewModel.operationOnTimerEnable((long) this.mDetailedIduModel.f454id.intValue());
        } else if (this.mSwitchOffAfter.isChecked()) {
            this.timerViewModel.operationOffTimerEnable((long) this.mDetailedIduModel.f454id.intValue());
        }
    }

    public WeeklyTimerMode getCurrentState() {
        if (!this.mSwitchTimer.isChecked()) {
            return WeeklyTimerMode.SCHEDULE_DISABLED;
        }
        if (this.mSwitchOnAfter.isChecked() && this.mSwitchOffAfter.isChecked()) {
            return WeeklyTimerMode.ON_OFF_TIMER_ENABLED;
        }
        if (this.mSwitchOnAfter.isChecked()) {
            return WeeklyTimerMode.ON_TIMER_ENABLED;
        }
        if (this.mSwitchOffAfter.isChecked()) {
            return WeeklyTimerMode.OFF_TIMER_ENABLED;
        }
        return null;
    }

    private String getConvertedTemperatureAuto(Float f) {
        StringBuilder sb;
        String str;
        boolean z = true;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) f.floatValue()))});
        Logger.m47e("TEMPERATURE_UNIT", "Not Converted : " + f + " Converted Temp : " + format);
        boolean z2 = f.floatValue() < 0.0f;
        if (f.floatValue() != 0.0f) {
            z = false;
        }
        if (z) {
            sb = new StringBuilder();
            str = "±";
        } else if (z2) {
            return format;
        } else {
            sb = new StringBuilder();
            str = Marker.ANY_NON_NULL_MARKER;
        }
        sb.append(str);
        sb.append(format);
        return sb.toString();
    }

    private void confirmationForSaveChanges() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setMessageString(getActivity().getString(R.string.common_alert_saveChangesDesc));
        confirmationDialog.setTitleString(getActivity().getString(R.string.common_alert_saveChanges));
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new SetTimerFragmentV2$$ExternalSyntheticLambda4(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new SetTimerFragmentV2$$ExternalSyntheticLambda5(this));
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForSaveChanges$6$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ boolean mo30602xa815d9b6(Dialog dialog, View view) {
        dialog.dismiss();
        saveTimer();
        return false;
    }

    /* renamed from: lambda$confirmationForSaveChanges$7$com-jch-racWiFi-iduManagement-view-SetTimerFragmentV2 */
    public /* synthetic */ boolean mo30603xd16a2ef7(Dialog dialog, View view) {
        dialog.dismiss();
        goBackFragment();
        return true;
    }
}
