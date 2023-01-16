package com.jch.racWiFi.iduManagement.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.RepeatListener;
import com.jch.racWiFi.SaveChangesListener;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.databinding.ScheduleSettingsWeeklyTimerBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.Weekday;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerScheduleSettingsViewModel;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Objects;

public class WeeklyTimerScheduleSettingsFragmentModelWise extends GenericFragment implements View.OnClickListener, View.OnTouchListener {
    private final float DIM = 0.2f;
    private final float DIP = 1.0f;
    /* access modifiers changed from: private */
    public ScheduleSettingsWeeklyTimerBinding binding;
    @BindView(2131362802)
    ImageButton decrease_temperature;
    private Handler handlerHelp = null;
    @BindView(2131364267)
    TextView humidityPercentageIduControl;
    @BindView(2131364270)
    TextView humidityTitleRoomDeviceControl;
    @BindView(2131362811)
    ImageButton increase_temperature;
    private boolean isChangesDetected = false;
    private RepeatListener mDecreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.decreaseTemperature();
        }
    });
    private DetailedIduModel mDetailedIduModel = new DetailedIduModel();
    private RepeatListener mIncreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.increaseTemperature();
        }
    });
    private AlertDialog mModeSelectDialog;
    private RacModelWiseData mRacModelWiseData;
    @BindView(2131364413)
    TextView percentage;
    private Runnable runnableHelp = new C2068x591cc394(this);
    /* access modifiers changed from: private */
    public SaveChangesListener saveChangesListener;
    private WeeklyTimerScheduleSettingsViewModel.SelectModeRecyclerViewAdapter selectModeRecyclerViewAdapter;
    @BindView(2131364630)
    TextView temperature;
    @BindView(2131364640)
    TextView temperatureUnit;
    /* access modifiers changed from: private */
    public WeeklyTimerScheduleSettingsViewModel viewModel;

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DetailedIduModel detailedIduModel = (DetailedIduModel) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        if (detailedIduModel != null) {
            this.mRacModelWiseData = this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId);
            this.mDetailedIduModel.copyRacInfo(detailedIduModel);
            this.mDetailedIduModel.mode = detailedIduModel.mode;
            this.mDetailedIduModel.copyDefaults(this.mRacModelWiseData);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = (ScheduleSettingsWeeklyTimerBinding) DataBindingUtil.inflate(layoutInflater, R.layout.schedule_settings_weekly_timer, viewGroup, false);
        WeeklyTimerScheduleSettingsViewModel weeklyTimerScheduleSettingsViewModel = (WeeklyTimerScheduleSettingsViewModel) ViewModelProviders.m35of((Fragment) this).get(WeeklyTimerScheduleSettingsViewModel.class);
        this.viewModel = weeklyTimerScheduleSettingsViewModel;
        this.binding.setWeeklyTimerScheduleSettingsViewModel(weeklyTimerScheduleSettingsViewModel);
        this.binding.setLifecycleOwner(this);
        this.binding.textViewSave.setOnClickListener(new C2071x591cc397(this));
        this.binding.textViewSetTemp.setOnClickListener(new C2071x591cc397(this));
        this.binding.imageButtonIncreaseTemprature.setOnTouchListener(this.mIncreaseTemperatureRepeatListener);
        this.binding.imageButtonDecreaseTemprature.setOnTouchListener(this.mDecreaseTemperatureRepeatListener);
        this.binding.layoutModeRoomDeviceControl.setOnClickListener(new C2071x591cc397(this));
        this.binding.imageButtonPickStartTime.setOnClickListener(new C2071x591cc397(this));
        this.binding.imageButtonHelp.setOnClickListener(new C2071x591cc397(this));
        this.binding.imageButtonHelp.setTag(Integer.valueOf(R.id.image_button_help));
        this.binding.backButton.setOnClickListener(new C2071x591cc397(this));
        this.binding.layoutEndTime.setOnClickListener(new C2071x591cc397(this));
        this.binding.layoutStartTime.setOnClickListener(new C2071x591cc397(this));
        this.binding.parent.setOnTouchListener(new C2058xca7bae81(this));
        ViewUtils.setOutlineProviderSwitch(this.binding.switchWeeklyTimer);
        this.viewModel.setRacModelWiseData(this.mRacModelWiseData);
        this.viewModel.setData(getArguments());
        this.binding.textViewRoomTitle.setText(this.viewModel.getTitle());
        initOperationModeItemInMenu();
        this.binding.switchWeeklyTimer.setOnCheckedChangeListener(new C2066x591cc392(this));
        liveDataObservers();
        new Handler().postDelayed(new C2067x591cc393(this), 500);
        return this.binding.getRoot();
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30669x932527ae(SwitchButton switchButton, boolean z) {
        if (z) {
            dipDisplay();
        } else {
            dimDisplay();
        }
        SaveChangesListener saveChangesListener2 = this.saveChangesListener;
        if (saveChangesListener2 != null) {
            saveChangesListener2.onChanged(true);
        }
    }

    /* renamed from: lambda$onCreateView$2$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30671x5417d3b0() {
        this.saveChangesListener = new C2057x591cc38f(this);
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30670x739e7daf(boolean z) {
        this.isChangesDetected = z;
    }

    private void initOperationModeItemInMenu() {
        WeeklyTimerScheduleSettingsViewModel.SelectModeRecyclerViewAdapter selectModeRecyclerViewAdapter2 = new WeeklyTimerScheduleSettingsViewModel.SelectModeRecyclerViewAdapter(getActivity(), new ArrayList(this.viewModel.populateMenuItemsSelectMode(requireActivity())));
        this.selectModeRecyclerViewAdapter = selectModeRecyclerViewAdapter2;
        selectModeRecyclerViewAdapter2.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                if (this.isChangesDetected) {
                    confirmationForSaveChanges();
                    return;
                } else {
                    onBackButton();
                    return;
                }
            case R.id.image_button_decrease_temprature:
                this.viewModel.decreaseTemperature();
                return;
            case R.id.image_button_help:
            case R.id.layout_end_time:
                showHelp();
                return;
            case R.id.image_button_increase_temprature:
                this.viewModel.increaseTemperature();
                return;
            case R.id.image_button_pick_start_time:
            case R.id.layout_start_time:
                setStartTime();
                return;
            case R.id.layout_mode_room_device_control:
                selectedMode();
                return;
            case R.id.text_view_save:
                showPleaseWaitDialog();
                this.viewModel.updateWeeklyTimerSchedule();
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideHelp();
        return false;
    }

    public void onDestroy() {
        Handler handler = this.handlerHelp;
        if (handler != null) {
            handler.removeCallbacks(this.runnableHelp);
        }
        super.onDestroy();
    }

    private void liveDataObservers() {
        this.viewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<String>() {
            public void onChanged(String str) {
                WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.textViewTemprature.setText(str);
                WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.textViewTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                if (WeeklyTimerScheduleSettingsFragmentModelWise.this.saveChangesListener != null) {
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.saveChangesListener.onChanged(true);
                }
            }
        });
        this.viewModel.updatePowerMode().observe(getActivity(), new Observer<String>() {
            public void onChanged(final String str) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (str.equals(DetailedIduModel.POWER_ON)) {
                            WeeklyTimerScheduleSettingsFragmentModelWise.this.dipDisplay();
                            WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.switchWeeklyTimer.setCheckedSilent(true);
                            return;
                        }
                        WeeklyTimerScheduleSettingsFragmentModelWise.this.dimDisplay();
                        WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.switchWeeklyTimer.setCheckedSilent(false);
                    }
                }, 200);
            }
        });
        this.viewModel.getUpdatedTimer().observe(getViewLifecycleOwner(), new Observer<WeeklyTimerModelV2.TimerHolderData>() {
            public void onChanged(WeeklyTimerModelV2.TimerHolderData timerHolderData) {
                String str = "00.00 AM";
                String str2 = "--:--";
                try {
                    String convert12HoursFormat = DateAndTimeUtils.convert12HoursFormat(DateAndTimeUtils.setCurrentTime(timerHolderData.startHour, timerHolderData.startMinute));
                    com.jch.racWiFi.customViews.customWidgets.TextView textView = WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.textViewStartTimeWeektyTimer;
                    if (convert12HoursFormat.equals("12.00 AM")) {
                        convert12HoursFormat = str;
                    }
                    textView.setText(convert12HoursFormat);
                    if (!WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.isNoEndTimer()) {
                        str2 = DateAndTimeUtils.convert12HoursFormat(DateAndTimeUtils.setCurrentTime(timerHolderData.endHour, timerHolderData.endMinute));
                        if (Weekday.getPosition(WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.getSelectedDay()) != timerHolderData.day) {
                            str2 = str2 + " (" + WeeklyTimerScheduleSettingsFragmentModelWise.this.getString(Weekday.valueOf(Weekday.getDay(timerHolderData.day).toUpperCase()).getStringRes()) + ")";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                com.jch.racWiFi.customViews.customWidgets.TextView textView2 = WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.textViewEndTimeWeektyTimer;
                if (!str2.equals("12.00 AM")) {
                    str = str2;
                }
                textView2.setText(str);
            }
        });
        this.viewModel.updateOperationMode().observe(getViewLifecycleOwner(), new C2059xca7bae82(this));
        this.viewModel.removeMutableData.observe(getActivity(), new C2062xca7bae85(this));
        this.viewModel.getTimerEnabledResponseMutableLiveData().observe(getActivity(), new C2060xca7bae83(this));
        this.viewModel.getAddMutableData().observe(getViewLifecycleOwner(), new C2061xca7bae84(this));
        this.viewModel.getUpdateMutableData().observe(getActivity(), new C2063xca7bae86(this));
    }

    /* renamed from: lambda$liveDataObservers$3$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30664x1b0ff05e(OperationMode operationMode) {
        AlertDialog alertDialog = this.mModeSelectDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mModeSelectDialog.dismiss();
        }
        operationMode(operationMode);
        SaveChangesListener saveChangesListener2 = this.saveChangesListener;
        if (saveChangesListener2 != null) {
            saveChangesListener2.onChanged(true);
        }
    }

    /* renamed from: lambda$liveDataObservers$4$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30665xfb89465f(WeeklyTimerModelV2.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse) {
        dismissPleaseWaitDialog();
        if (weeklyTimerRemoveResponse.success) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_weeklyTimerRemoveSuccess), false);
        } else if (weeklyTimerRemoveResponse.statusCode == 404) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_schIdNotFound), false);
        } else {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_weeklyTimerRemoveFailed), false);
        }
    }

    /* renamed from: lambda$liveDataObservers$5$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30666xdc029c60(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        dismissPleaseWaitDialog();
        if (timerEnabledResponse.throwable != null) {
            if (!(timerEnabledResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (timerEnabledResponse.isSuccessful()) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_weeklyTimerEnabledSuccess), true);
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
                    }
                }, false);
            } else if (i == 404) {
                showErrorPopUp(getString(timerEnabledResponse.getErrorMessageStringId(String.valueOf(timerEnabledResponse.statusCode))));
            } else if (timerEnabledResponse.statusCode != 406) {
                if (timerEnabledResponse.statusCode == 412) {
                    alertDialog(getResources().getString(R.string.errorCode_alert_PCF009) + " " + getResources().getString(R.string.weeklyTimer_alert_enableFailed), false);
                    return;
                }
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_enableFailed), false);
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$6$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30667xbc7bf261(WeeklyTimerModelV2.WeeklyTimerAddResponse weeklyTimerAddResponse) {
        dismissPleaseWaitDialog();
        if (weeklyTimerAddResponse.throwable != null) {
            if (!(weeklyTimerAddResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (weeklyTimerAddResponse.isSuccessful()) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_weeklyTimerAddedSuccess), true);
        } else {
            int i = weeklyTimerAddResponse.statusCode;
            if (i == 400) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (i == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.updateWeeklyTimerSchedule();
                    }
                }, false);
            } else if (i == 404) {
                showErrorPopUp(getString(weeklyTimerAddResponse.getErrorMessageStringId(String.valueOf(weeklyTimerAddResponse.statusCode))));
            } else if (weeklyTimerAddResponse.statusCode == 406) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_maxSchReachedPerDay), false);
            } else if (weeklyTimerAddResponse.statusCode == 412) {
                alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
            } else if (weeklyTimerAddResponse.statusCode == 416) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_failedToAddWeeklyTimer), false);
            } else if (weeklyTimerAddResponse.statusCode == 409) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_conflictExistingSch), false);
            } else if (weeklyTimerAddResponse.statusCode >= 500) {
                alertDialog(getResources().getString(R.string.common_alert_somethingWentWrong), false);
            } else {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_failedToAddWeeklyTimer), false);
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$7$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30668x9cf54862(WeeklyTimerModelV2.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse) {
        dismissPleaseWaitDialog();
        if (weeklyTimerUpdateResponse.throwable != null) {
            if (!(weeklyTimerUpdateResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (weeklyTimerUpdateResponse.isSuccessful()) {
            showPleaseWaitDialog();
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_weeklyTimerUpdatedSucc), true);
        } else {
            int i = weeklyTimerUpdateResponse.statusCode;
            if (i == 400) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (i == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.updateWeeklyTimerSchedule();
                    }
                }, false);
            } else if (i == 404) {
                showErrorPopUp(getString(weeklyTimerUpdateResponse.getErrorMessageStringId(String.valueOf(weeklyTimerUpdateResponse.statusCode))));
            } else if (weeklyTimerUpdateResponse.statusCode == 404) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_schNotFound), true);
            } else if (weeklyTimerUpdateResponse.statusCode == 412) {
                alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
            } else if (weeklyTimerUpdateResponse.statusCode == 409) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_conflictExistingSch), false);
            } else if (weeklyTimerUpdateResponse.statusCode >= 500) {
                alertDialog(getResources().getString(R.string.common_alert_somethingWentWrong), false);
            } else {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_failedToUpdateWeeklyTimer), false);
            }
        }
    }

    private void disableEnableControls(boolean z, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (z) {
                childAt.setEnabled(z);
                childAt.setAlpha(1.0f);
            } else if (!(childAt.getId() == R.id.layout_start_time || childAt.getId() == R.id.layout_end_time)) {
                childAt.setEnabled(z);
                childAt.setAlpha(0.2f);
            }
            if (childAt instanceof ViewGroup) {
                disableEnableControls(z, (ViewGroup) childAt);
            }
        }
        enableAlways();
    }

    private void enableAlways() {
        this.binding.textViewStartTimeTitle.setEnabled(true);
        this.binding.textViewStartTimeTitle.setAlpha(1.0f);
        this.binding.textViewEndTimeTitle.setEnabled(true);
        this.binding.textViewEndTimeTitle.setAlpha(1.0f);
        this.binding.textViewStartTimeWeektyTimer.setEnabled(true);
        this.binding.textViewStartTimeWeektyTimer.setAlpha(1.0f);
        this.binding.imageButtonPickStartTime.setEnabled(true);
        this.binding.imageButtonPickStartTime.setAlpha(1.0f);
        this.binding.imageButtonHelp.setEnabled(true);
        this.binding.imageButtonHelp.setAlpha(1.0f);
        this.binding.textViewEndTimeWeektyTimer.setEnabled(true);
        this.binding.textViewEndTimeWeektyTimer.setAlpha(0.2f);
        this.binding.textViewHelpContent.setEnabled(true);
        this.binding.textViewHelpContent.setAlpha(1.0f);
        this.binding.helpBubbleLayoutBottom.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public void dimDisplay() {
        this.viewModel.setPowerMode(DetailedIduModel.POWER_OFF);
        disableEnableControls(false, this.binding.layout3);
    }

    /* access modifiers changed from: private */
    public void dipDisplay() {
        this.viewModel.setPowerMode(DetailedIduModel.POWER_ON);
        disableEnableControls(true, this.binding.layout3);
    }

    private void setStartTime() {
        FragmentActivity activity = getActivity();
        C20559 r3 = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                if (!WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.isAlreadyDefinedSettings(i, i2)) {
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.getTimerHolderData().startHour = i;
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.getTimerHolderData().startMinute = i2;
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.getUpdatedTimer().setValue(WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.getTimerHolderData());
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.viewModel.calculateEndTimer();
                    if (WeeklyTimerScheduleSettingsFragmentModelWise.this.saveChangesListener != null) {
                        WeeklyTimerScheduleSettingsFragmentModelWise.this.saveChangesListener.onChanged(true);
                        return;
                    }
                    return;
                }
                WeeklyTimerScheduleSettingsFragmentModelWise.this.scheduleErrorDialog();
            }
        };
        WeeklyTimerModelV2.TimerHolderData value = this.viewModel.getUpdatedTimer().getValue();
        Objects.requireNonNull(value);
        WeeklyTimerModelV2.TimerHolderData timerHolderData = value;
        new TimePickerDialog(activity, R.style.TimePickerTheme, r3, value.startHour, this.viewModel.getUpdatedTimer().getValue().startMinute, true).show();
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.WeeklyTimerScheduleSettingsFragmentModelWise$13 */
    static /* synthetic */ class C204613 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x0033 }
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
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.WeeklyTimerScheduleSettingsFragmentModelWise.C204613.<clinit>():void");
        }
    }

    private void operationMode(OperationMode operationMode) {
        switch (C204613.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode.ordinal()]) {
            case 1:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.COOLING);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.white));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.HEATING);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.white));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.DE_HUMIDIFY);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.white));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.white));
                break;
            case 4:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.DRY_COOL);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.white));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.white));
                break;
            case 5:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.FAN);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.white));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.white));
                break;
            case 6:
                changeUIComponentsOfOperationMode(OperationModeUIConfigration.AUTO);
                this.binding.imageViewArrowDownMode.setColorFilter(getResources().getColor(R.color.textview_color_vd_dark));
                this.binding.textViewSelectedModeHome.setTextColor(getResources().getColor(R.color.textview_color_vd_dark));
                break;
        }
        if (operationMode == OperationMode.FAN) {
            setVisiblityOfNavigationButtonAndTemp(false);
        } else if (operationMode == OperationMode.AUTO) {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationMode);
            if (racModeDetailBasedOnMode != null && !racModeDetailBasedOnMode.getVisibleSettings().getTemperature()) {
                setVisiblityOfNavigationButtonAndTemp(false);
            }
        } else {
            setVisiblityOfNavigationButtonAndTemp(true);
        }
        this.viewModel.setOperationMode(operationMode);
        this.viewModel.setDefaultTemperature(operationMode);
    }

    private void connectionFailedDialog() {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(getActivity().getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    private void setVisiblityOfNavigationButtonAndTemp(boolean z) {
        int i = 0;
        this.binding.imageButtonIncreaseTemprature.setVisibility(z ? 0 : 4);
        this.binding.imageButtonDecreaseTemprature.setVisibility(z ? 0 : 4);
        this.binding.textViewTemprature.setVisibility(z ? 0 : 4);
        this.binding.textViewTempratureUnit.setVisibility(z ? 0 : 4);
        com.jch.racWiFi.customViews.customWidgets.TextView textView = this.binding.textViewSetTemp;
        if (!z) {
            i = 4;
        }
        textView.setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void scheduleErrorDialog() {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(getActivity().getString(R.string.weeklyTimer_alert_overlappingSch));
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }

    private void changeUIComponentsOfOperationMode(OperationModeUIConfigration operationModeUIConfigration) {
        this.binding.imageViewSelectedModeHome.setImageResource(operationModeUIConfigration.getSelectedModeDrawableResource());
        this.binding.textViewSelectedModeHome.setText(operationModeUIConfigration.getOperationModeStringResource());
        this.binding.layoutModeRoomDeviceControl.setBackground(getResources().getDrawable(operationModeUIConfigration.getModeBackgroundColorResource()));
    }

    private void selectedMode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true).setView(R.layout.select_mode_dialog);
        AlertDialog create = builder.create();
        this.mModeSelectDialog = create;
        create.setOnShowListener(new C2069x591cc395(this));
        this.mModeSelectDialog.show();
        this.mModeSelectDialog.setOnDismissListener(new C2056x591cc38e(this));
        WindowManager.LayoutParams attributes = this.mModeSelectDialog.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        this.mModeSelectDialog.getWindow().setAttributes(attributes);
        this.mModeSelectDialog.getWindow().addFlags(2);
        View decorView = this.mModeSelectDialog.getWindow().getDecorView();
        ((ImageButton) decorView.findViewById(R.id.image_button_clear)).setOnClickListener(new C2070x591cc396(this));
        RecyclerView recyclerView = (RecyclerView) decorView.findViewById(R.id.recycler_view_select_mode);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.selectModeRecyclerViewAdapter);
    }

    /* renamed from: lambda$selectedMode$8$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30673x7ed7614(DialogInterface dialogInterface) {
        this.binding.parent.setBackgroundResource(R.drawable.white_blur);
    }

    /* renamed from: lambda$selectedMode$9$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30674xe866cc15(DialogInterface dialogInterface) {
        this.binding.parent.setBackgroundResource(R.drawable.transparent);
    }

    /* renamed from: lambda$selectedMode$10$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ void mo30672x97987c6d(View view) {
        this.mModeSelectDialog.dismiss();
    }

    public void onStart() {
        super.onStart();
    }

    private void onBackButton() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: private */
    public synchronized void showHelp() {
        if (this.binding.helpBubbleLayoutBottom.getVisibility() == 0) {
            this.binding.helpBubbleLayoutBottom.setVisibility(8);
            this.handlerHelp.removeCallbacks(this.runnableHelp);
        } else {
            this.binding.helpBubbleLayoutBottom.setVisibility(0);
            this.binding.scrollView.post(new Runnable() {
                public void run() {
                    WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.scrollView.scrollTo(0, WeeklyTimerScheduleSettingsFragmentModelWise.this.binding.scrollView.getBottom());
                }
            });
            if (this.handlerHelp == null) {
                this.handlerHelp = new Handler();
            }
            this.handlerHelp.postDelayed(this.runnableHelp, 5000);
        }
    }

    private synchronized void hideHelp() {
        if (this.binding.helpBubbleLayoutBottom.getVisibility() == 0) {
            this.handlerHelp.removeCallbacks(this.runnableHelp);
            this.binding.helpBubbleLayoutBottom.setVisibility(8);
        }
    }

    private void confirmationForSaveChanges() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setMessageString(getActivity().getString(R.string.common_alert_saveChangesDesc));
        confirmationDialog.setTitleString(getActivity().getString(R.string.common_alert_saveChanges));
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new C2064x591cc390(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new C2065x591cc391(this));
        confirmationDialog.setParentView(this.binding.parent);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForSaveChanges$11$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ boolean mo30662x11ce41fa(Dialog dialog, View view) {
        dialog.dismiss();
        showPleaseWaitDialog();
        this.viewModel.updateWeeklyTimerSchedule();
        return false;
    }

    /* renamed from: lambda$confirmationForSaveChanges$12$com-jch-racWiFi-iduManagement-view-WeeklyTimerScheduleSettingsFragmentModelWise */
    public /* synthetic */ boolean mo30663xf24797fb(Dialog dialog, View view) {
        dialog.dismiss();
        onBackButton();
        return true;
    }
}
