package com.jch.racWiFi.iduManagement.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.DynamicScheduledTimerList;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.CopyScheduleConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.ScheduleErrorDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.databinding.WeeklyTimerGlobalFrameBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.Weekday;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.CopyTimerScheduleModel;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerViewModel;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;

public class WeeklyTimerFragmentV3 extends GenericFragment implements CompoundButton.OnCheckedChangeListener, View.OnTouchListener, View.OnClickListener, DynamicScheduledTimerList.DynamicViewClickListener {
    public static final int MAX_SCHEDULE = 6;
    /* access modifiers changed from: private */
    public WeeklyTimerGlobalFrameBinding binding;
    private RadioButton[] buttonDays = new RadioButton[7];
    private TriStateCheckbox[] checkboxesDaysSelection = new TriStateCheckbox[7];
    private DynamicScheduledTimerList dynamicScheduledTimerList;
    private Handler handler = new Handler();
    /* access modifiers changed from: private */
    public boolean isEnablingTimer;
    private boolean isWeeklyTimerEnable = true;
    private boolean isboolean;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel = new DetailedIduModel();
    /* access modifiers changed from: private */
    public DetailedIduModel orgDetailedIduModel;
    private int position;
    public Runnable run = new Runnable() {
        public void run() {
            try {
                if (WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype != null) {
                    switch (C203310.$SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode[WeeklyTimerMode.valueOf(WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype).ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(false);
                            WeeklyTimerFragmentV3.this.disableWeeklyTimer();
                            return;
                        case 6:
                            WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(true);
                            WeeklyTimerFragmentV3.this.enableWeeklyTimer();
                            return;
                        default:
                            return;
                    }
                } else {
                    WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(false);
                    WeeklyTimerFragmentV3.this.disableWeeklyTimer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable = new Runnable() {
        public void run() {
            WeeklyTimerFragmentV3.this.weeklyTimerViewModel.getWeeklyTimerDataList();
        }
    };
    /* access modifiers changed from: private */
    public String selectedDay = Weekday.getDay(Weekday.MON.ordinal());
    /* access modifiers changed from: private */
    public SingleChoiceDialog singleChoiceDialog;
    private ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> weeklyTimerFactoryDataArrayList = new ArrayList<>();
    /* access modifiers changed from: private */
    public WeeklyTimerViewModel weeklyTimerViewModel;

    private void confirmationDailogForNoSchedule() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        DetailedIduModel detailedIduModel = (DetailedIduModel) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        this.orgDetailedIduModel = detailedIduModel;
        this.mDetailedIduModel.copy(detailedIduModel);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = (WeeklyTimerGlobalFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.weekly_timer_global_frame, viewGroup, false);
        WeeklyTimerViewModel weeklyTimerViewModel2 = (WeeklyTimerViewModel) ViewModelProviders.m35of((Fragment) this).get(WeeklyTimerViewModel.class);
        this.weeklyTimerViewModel = weeklyTimerViewModel2;
        this.binding.setWeeklyTimerViewModel(weeklyTimerViewModel2);
        this.binding.setLifecycleOwner(this);
        this.weeklyTimerViewModel.init(this.mDetailedIduModel);
        this.binding.imageButtonCopyWeeklyTimer.setOnClickListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda0(this));
        this.binding.backButton.setOnClickListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda0(this));
        this.binding.include.textViewAddItemWeeklyTimer.setOnClickListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda0(this));
        this.binding.textViewSave.setOnClickListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda0(this));
        ViewUtils.setOutlineProviderSwitch(this.binding.include.switchWeeklyTimer);
        initDays();
        initDaysOfSelection();
        DynamicScheduledTimerList dynamicScheduledTimerList2 = new DynamicScheduledTimerList(this.binding.include.rootLayoutForDynamicList, this.mActivity);
        this.dynamicScheduledTimerList = dynamicScheduledTimerList2;
        dynamicScheduledTimerList2.initArrayList(this.weeklyTimerFactoryDataArrayList, this);
        this.dynamicScheduledTimerList.setRacModelWise(this.mFragmentToActivityCallback, this.orgDetailedIduModel);
        this.dynamicScheduledTimerList.showList();
        enableDisableCopyOptions(false);
        this.binding.textViewRoomTitle.setText(this.weeklyTimerViewModel.getTitle());
        this.binding.include.switchWeeklyTimer.setOnCheckedChangeListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda8(this));
        this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.post(this.run);
        }
        liveDataObservers();
        showPleaseWaitDialog(false);
        getWeeklyTimerList();
        OverScrollDecoratorHelper.setUpOverScroll(this.binding.include.scrollView);
        return this.binding.getRoot();
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30648x1b9d2be2(SwitchButton switchButton, boolean z) {
        if (z) {
            enableWeeklyTimer();
            showPleaseWaitDialog();
            this.isEnablingTimer = true;
            this.weeklyTimerViewModel.weeklyTimerOperationEnable();
            return;
        }
        disableWeeklyTimer();
        showPleaseWaitDialog();
        this.isEnablingTimer = false;
        this.weeklyTimerViewModel.weeklyTimerOperationDisable();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.WeeklyTimerFragmentV3$10 */
    static /* synthetic */ class C203310 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode[] r0 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode = r0
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.SCHEDULE_DISABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.HOLIDAY_MODE_ENABLED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.WEEKLY_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.WeeklyTimerFragmentV3.C203310.<clinit>():void");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        Logger.m49i("", "WeeklyTimerFragmentV3.onActivityCreated");
        super.onActivityCreated(bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.isboolean = true;
        Logger.m49i("", "WeeklyTimerFragmentV3.onDestroyView");
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    public void onDestroy() {
        dismissPleaseWaitDialog();
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                goBackFragment();
                return;
            case R.id.image_button_copy_weekly_timer:
                this.binding.imageButtonCopyWeeklyTimer.setVisibility(8);
                this.binding.textViewSave.setVisibility(0);
                this.binding.include.layoutCheckBoxes.setVisibility(0);
                disableEnableAllDays(false);
                selectedUnselectedCheckBoxexOfDays();
                lockSelectedDays();
                return;
            case R.id.text_view_add_item_weekly_timer:
                if (this.weeklyTimerViewModel.isMaxScheduledReached()) {
                    notifyingForMaxReachedSchedule();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailedIduModel.PARCEL_KEY, this.mDetailedIduModel);
                bundle.putBoolean("ADD_BUTTON", true);
                bundle.putString("SELECTED_DAY", this.selectedDay);
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_weeklyTimerFragmentV2_to_weeklyTimerScheduleSettingsFragmentV3, bundle);
                return;
            case R.id.text_view_save:
                Integer[] selectedDaysForCopySchedule = getSelectedDaysForCopySchedule();
                if (selectedDaysForCopySchedule.length == 0) {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_atLeastOneDayToCopy), false);
                    return;
                } else {
                    confirmationForChanges(getSelectedDaysPosition(), selectedDaysForCopySchedule);
                    return;
                }
            default:
                return;
        }
    }

    private String[] copyToDays() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            TriStateCheckbox[] triStateCheckboxArr = this.checkboxesDaysSelection;
            if (i >= triStateCheckboxArr.length) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            if (triStateCheckboxArr[i].isChecked().booleanValue() && getSelectedDaysPosition() != i) {
                arrayList.add(this.buttonDays[i].getText().toString().toUpperCase());
            }
            i++;
        }
    }

    private Integer[] getSelectedDaysForCopySchedule() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            TriStateCheckbox[] triStateCheckboxArr = this.checkboxesDaysSelection;
            if (i >= triStateCheckboxArr.length) {
                return (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
            }
            if (triStateCheckboxArr[i].isChecked().booleanValue() && getSelectedDaysPosition() != i) {
                arrayList.add(Integer.valueOf(i));
            }
            i++;
        }
    }

    public void onDeleteItemClickListener(View view, int i) {
        this.position = i;
        WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = this.weeklyTimerFactoryDataArrayList.get(i);
        if (this.weeklyTimerViewModel.isScheduleStartedOnAnotherDay(weeklyTimerFactoryData)) {
            scheduleErrorDialog(weeklyTimerFactoryData.startingDay, true);
        } else {
            confirmationForDeletion();
        }
    }

    public void onItemClickListener(View view, int i) {
        WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = this.weeklyTimerFactoryDataArrayList.get(i);
        if (this.weeklyTimerViewModel.isScheduleStartedOnAnotherDay(weeklyTimerFactoryData)) {
            scheduleErrorDialog(weeklyTimerFactoryData.startingDay, false);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(WeeklyTimerModelV2.WeeklyTimerFactoryData.PARCEL_KEY, weeklyTimerFactoryData);
        bundle.putBoolean("ADD_BUTTON", false);
        bundle.putParcelable(DetailedIduModel.PARCEL_KEY, this.mDetailedIduModel);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_weeklyTimerFragmentV2_to_weeklyTimerScheduleSettingsFragmentV3, bundle);
    }

    /* access modifiers changed from: private */
    public void getWeeklyTimerList() {
        this.handler.postDelayed(this.runnable, 500);
    }

    private void copyingSchedule(String str, String[] strArr) {
        showPleaseWaitDialog(false);
        this.weeklyTimerViewModel.copyTimerScheduleFromDayToDays(new CopySchedule.DayWise((long) this.mDetailedIduModel.f454id.intValue(), str, strArr));
    }

    private void liveDataObservers() {
        WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda14(this));
        WeeklyTimerViewModel.updatedDaywiseFilterSchedule().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda16(this));
        this.weeklyTimerViewModel.getCopyTimerScheduleLiveDataResponse().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda12(this));
        this.weeklyTimerViewModel.onRemoveWeeklyTimer().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda15(this));
        this.weeklyTimerViewModel.getTimerEnabledResponseMutableLiveData().observeSingleEvent(getViewLifecycleOwner(), new Observer<TimerEnabledModel.TimerEnabledResponse>() {
            public void onChanged(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
                WeeklyTimerFragmentV3.this.dismissPleaseWaitDialog();
                if (timerEnabledResponse.throwable != null) {
                    if (!(timerEnabledResponse.throwable instanceof SocketException)) {
                        WeeklyTimerFragmentV3.this.connectionFailedDialog();
                    }
                    if (WeeklyTimerFragmentV3.this.isEnablingTimer) {
                        WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype = WeeklyTimerMode.WEEKLY_TIMER_ENABLED.name();
                        WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(false);
                        WeeklyTimerFragmentV3.this.disableWeeklyTimer();
                        return;
                    }
                    WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                    WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(true);
                    WeeklyTimerFragmentV3.this.enableWeeklyTimer();
                } else if (timerEnabledResponse.isSuccessful()) {
                    if (WeeklyTimerFragmentV3.this.isEnablingTimer) {
                        WeeklyTimerFragmentV3 weeklyTimerFragmentV3 = WeeklyTimerFragmentV3.this;
                        weeklyTimerFragmentV3.selectedDay = weeklyTimerFragmentV3.getSelectedDays();
                        WeeklyTimerFragmentV3.this.weeklyTimerViewModel.filterDayWiseData(WeeklyTimerFragmentV3.this.selectedDay);
                    } else {
                        WeeklyTimerFragmentV3 weeklyTimerFragmentV32 = WeeklyTimerFragmentV3.this;
                        weeklyTimerFragmentV32.alertDialog(weeklyTimerFragmentV32.getResources().getString(R.string.weeklyTimer_alert_disabledSuccess), true);
                    }
                    WeeklyTimerFragmentV3.this.mFragmentToActivityCallback.refreshIndividualIduState(WeeklyTimerFragmentV3.this.getCoreActivity(), WeeklyTimerFragmentV3.this.mDetailedIduModel.f454id.intValue());
                    if (!Constants.IS_DEMO_MODE) {
                        return;
                    }
                    if (WeeklyTimerFragmentV3.this.isEnablingTimer) {
                        WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype = WeeklyTimerMode.WEEKLY_TIMER_ENABLED.name();
                        return;
                    }
                    WeeklyTimerFragmentV3.this.orgDetailedIduModel.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                } else if (WeeklyTimerFragmentV3.this.isEnablingTimer) {
                    if (timerEnabledResponse.statusCode != 406) {
                        if (timerEnabledResponse.statusCode == 412) {
                            WeeklyTimerFragmentV3 weeklyTimerFragmentV33 = WeeklyTimerFragmentV3.this;
                            weeklyTimerFragmentV33.alertDialog(WeeklyTimerFragmentV3.this.getResources().getString(R.string.errorCode_alert_PCF009) + " " + WeeklyTimerFragmentV3.this.getResources().getString(R.string.weeklyTimer_alert_enableFailed), false);
                        } else {
                            WeeklyTimerFragmentV3 weeklyTimerFragmentV34 = WeeklyTimerFragmentV3.this;
                            weeklyTimerFragmentV34.alertDialog(weeklyTimerFragmentV34.getResources().getString(R.string.weeklyTimer_alert_enableFailed), false);
                        }
                    }
                    WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(false);
                    WeeklyTimerFragmentV3.this.disableWeeklyTimer();
                } else {
                    if (timerEnabledResponse.statusCode != 406) {
                        if (timerEnabledResponse.statusCode == 412) {
                            WeeklyTimerFragmentV3 weeklyTimerFragmentV35 = WeeklyTimerFragmentV3.this;
                            weeklyTimerFragmentV35.alertDialog(WeeklyTimerFragmentV3.this.getResources().getString(R.string.errorCode_alert_PCF009) + " " + WeeklyTimerFragmentV3.this.getResources().getString(R.string.weeklyTimer_alert_weeklyTimerDisabledFailed), false);
                        } else {
                            WeeklyTimerFragmentV3 weeklyTimerFragmentV36 = WeeklyTimerFragmentV3.this;
                            weeklyTimerFragmentV36.alertDialog(weeklyTimerFragmentV36.getResources().getString(R.string.weeklyTimer_alert_enableFailed), false);
                        }
                    }
                    WeeklyTimerFragmentV3.this.binding.include.switchWeeklyTimer.setCheckedSilent(true);
                    WeeklyTimerFragmentV3.this.enableWeeklyTimer();
                }
            }
        });
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda13(this));
    }

    /* renamed from: lambda$liveDataObservers$1$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30642x8cc03af4(WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse) {
        this.isboolean = false;
        dismissPleaseWaitDialog();
        this.weeklyTimerViewModel.filterDayWiseData(this.selectedDay);
        if (weeklyTimerFetchResponse.throwable != null) {
            if (!(weeklyTimerFetchResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (!weeklyTimerFetchResponse.isSuccessful()) {
            int i = weeklyTimerFetchResponse.statusCode;
            if (i == 400) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (i == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        WeeklyTimerFragmentV3.this.weeklyTimerViewModel.getWeeklyTimerDataList();
                    }
                }, false);
            } else if (i != 404) {
                updateUIIfNoScheduleAvailale(false);
                if (weeklyTimerFetchResponse.statusCode == 404) {
                    alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
                } else if (weeklyTimerFetchResponse.statusCode == 412) {
                    alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
                } else {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_schNotFound), false);
                }
            } else {
                showErrorPopUp(getString(weeklyTimerFetchResponse.getErrorMessageStringId(String.valueOf(weeklyTimerFetchResponse.statusCode))));
            }
        } else if (((WeeklyTimerModelV2.WeeklyTimerResponseData[]) weeklyTimerFetchResponse.response).length > 0) {
            updateUIIfNoScheduleAvailale(true);
        } else {
            updateUIIfNoScheduleAvailale(false);
        }
    }

    /* renamed from: lambda$liveDataObservers$2$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30643x19ad5213(ArrayList arrayList) {
        this.weeklyTimerFactoryDataArrayList.clear();
        this.weeklyTimerFactoryDataArrayList.addAll(arrayList);
        this.dynamicScheduledTimerList.notifyDataSetChanged();
        if (this.weeklyTimerViewModel.isScheduledCreatedByAddButtonForTheDay(this.selectedDay)) {
            enableDisableCopyOptions(true);
        } else {
            enableDisableCopyOptions(false);
        }
        if (this.binding.include.switchWeeklyTimer.isChecked() && arrayList.size() != 0 && !this.weeklyTimerViewModel.isScheduledCreatedByAddButtonForTheDay(this.selectedDay)) {
            confirmationDailogForNoSchedule();
        }
    }

    /* renamed from: lambda$liveDataObservers$3$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30644xa69a6932(CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse) {
        dismissPleaseWaitDialog();
        if (copyTimerScheduleResponse.throwable != null) {
            if (!(copyTimerScheduleResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (copyTimerScheduleResponse.isSuccessful()) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_copiedSuccess), false);
            resetUI();
            showPleaseWaitDialog();
            getWeeklyTimerList();
        } else {
            int i = copyTimerScheduleResponse.statusCode;
            if (i == 400) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (i == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        WeeklyTimerFragmentV3.this.requestForCopyTimerSchedule();
                    }
                }, false);
            } else if (i == 404) {
                showErrorPopUp(getString(copyTimerScheduleResponse.getErrorMessageStringId(String.valueOf(copyTimerScheduleResponse.statusCode))));
            } else if (copyTimerScheduleResponse.statusCode == 406) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_maxSchReached), false);
            } else if (copyTimerScheduleResponse.statusCode == 409) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_conflictExistingSch), false);
            } else if (copyTimerScheduleResponse.statusCode == 412) {
                alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
            } else {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_failedToCopySch), false);
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$4$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30645x33878051(WeeklyTimerModelV2.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse) {
        if (weeklyTimerRemoveResponse.throwable != null) {
            dismissPleaseWaitDialog();
            if (!(weeklyTimerRemoveResponse.throwable instanceof SocketException)) {
                connectionFailedDialog();
            }
        } else if (weeklyTimerRemoveResponse.isSuccessful()) {
            getWeeklyTimerList();
        } else {
            dismissPleaseWaitDialog();
            int i = weeklyTimerRemoveResponse.statusCode;
            if (i == 400) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (i == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        WeeklyTimerFragmentV3.this.deleteWeeklyTimer();
                    }
                }, false);
            } else if (i == 404) {
                showErrorPopUp(getString(weeklyTimerRemoveResponse.getErrorMessageStringId(String.valueOf(weeklyTimerRemoveResponse.statusCode))));
            } else if (weeklyTimerRemoveResponse.statusCode == 404) {
                notifyingForNoScheduleExist();
            } else if (weeklyTimerRemoveResponse.statusCode == 406) {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_schNotFound), false);
                notifyingForNoScheduleExist();
            } else if (weeklyTimerRemoveResponse.statusCode == 412) {
                alertDialog(getResources().getString(R.string.common_alert_acNotExist), true);
            } else {
                alertDialog(getResources().getString(R.string.common_alert_somethingWentWrong), false);
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$5$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30646xc0749770(DetailedIduModel detailedIduModel) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && detailedIduModel.equals(this.orgDetailedIduModel)) {
            this.orgDetailedIduModel.scheduletype = detailedIduModel.scheduletype;
            this.mDetailedIduModel.scheduletype = detailedIduModel.scheduletype;
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.postDelayed(this.run, 100);
            }
        }
    }

    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    private void resetUI() {
        this.binding.imageButtonCopyWeeklyTimer.setVisibility(0);
        this.binding.textViewSave.setVisibility(8);
        this.binding.include.layoutCheckBoxes.setVisibility(8);
        disableEnableAllDays(true);
        selectedUnselectedCheckBoxexOfDays();
        unlockUnSlectedDays();
    }

    /* access modifiers changed from: private */
    public void connectionFailedDialog() {
        SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog2.setTitleString(this.mActivity.getString(R.string.common_alert));
        singleChoiceDialog2.setCancelable(false);
        singleChoiceDialog2.setMessageString(this.mActivity.getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog2.setPositiveButton(this.mActivity.getString(R.string.common_btn_ok), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda7(singleChoiceDialog2));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog2.show();
        }
    }

    /* access modifiers changed from: private */
    public void disableWeeklyTimer() {
        this.isWeeklyTimerEnable = false;
        this.binding.include.rootLayoutVd.setVisibility(8);
        addScheduleButtonVisability(false);
        enableDisableCopyOptions(false);
    }

    /* access modifiers changed from: private */
    public void enableWeeklyTimer() {
        this.isWeeklyTimerEnable = true;
        this.binding.include.rootLayoutVd.setVisibility(0);
        addScheduleButtonVisability(true);
        if (this.weeklyTimerViewModel.isScheduledCreatedByAddButtonForTheDay(this.selectedDay)) {
            enableDisableCopyOptions(true);
        } else {
            enableDisableCopyOptions(false);
        }
    }

    public void addScheduleButtonVisability(boolean z) {
        if (z) {
            this.binding.include.textViewAddItemWeeklyTimer.setVisibility(0);
        } else {
            this.binding.include.textViewAddItemWeeklyTimer.setVisibility(8);
        }
    }

    private void updateUIIfNoScheduleAvailale(boolean z) {
        if (z) {
            this.binding.include.view1.setVisibility(0);
            this.binding.include.layoutNoSchedulesAvailable.setVisibility(8);
            return;
        }
        this.binding.include.view1.setVisibility(8);
        this.binding.include.layoutNoSchedulesAvailable.setVisibility(0);
    }

    private void enableDisableCopyOptions(boolean z) {
        if (!z || !this.isWeeklyTimerEnable) {
            this.binding.imageButtonCopyWeeklyTimer.setEnabled(false);
            this.binding.imageButtonCopyWeeklyTimer.setAlpha(0.2f);
            this.binding.textViewSave.setEnabled(false);
            this.binding.textViewSave.setAlpha(0.2f);
            return;
        }
        this.binding.imageButtonCopyWeeklyTimer.setEnabled(true);
        this.binding.imageButtonCopyWeeklyTimer.setAlpha(1.0f);
        this.binding.textViewSave.setEnabled(true);
        this.binding.textViewSave.setAlpha(1.0f);
    }

    private void lockSelectedDays() {
        int selectedDaysPosition = getSelectedDaysPosition();
        this.checkboxesDaysSelection[selectedDaysPosition].setChecked(true);
        this.checkboxesDaysSelection[selectedDaysPosition].setEnabled(false);
        this.checkboxesDaysSelection[selectedDaysPosition].setAlpha(0.1f);
    }

    private void disableDaysThatReachedMaxSchedule() {
        int i = 0;
        while (true) {
            RadioButton[] radioButtonArr = this.buttonDays;
            if (i < radioButtonArr.length) {
                if (this.weeklyTimerViewModel.isMaxScheduledReached(radioButtonArr[i].getText().toString())) {
                    this.checkboxesDaysSelection[i].setEnabled(false);
                    this.checkboxesDaysSelection[i].setAlpha(0.1f);
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void unlockUnSlectedDays() {
        int selectedDaysPosition = getSelectedDaysPosition();
        this.checkboxesDaysSelection[selectedDaysPosition].setChecked(false);
        this.checkboxesDaysSelection[selectedDaysPosition].setEnabled(true);
        this.checkboxesDaysSelection[selectedDaysPosition].setAlpha(1.0f);
    }

    private void selectedUnselectedCheckBoxexOfDays() {
        for (TriStateCheckbox checked : this.checkboxesDaysSelection) {
            checked.setChecked(false);
        }
    }

    private void initDays() {
        int i = 0;
        while (true) {
            RadioButton[] radioButtonArr = this.buttonDays;
            if (i < radioButtonArr.length) {
                radioButtonArr[i] = (RadioButton) this.binding.include.dailyWeeklyButtonView.getChildAt(i);
                this.buttonDays[i].setOnCheckedChangeListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda11(this));
                this.buttonDays[i].setOnTouchListener(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda10(this));
                i++;
            } else {
                return;
            }
        }
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        Logger.m49i("", "WeeklyTimerFragmentV3.onViewStateRestored");
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.handler.postDelayed(new WeeklyTimerFragmentV3$$ExternalSyntheticLambda9(this), 500);
        }
    }

    /* renamed from: lambda$onCheckedChanged$7$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ void mo30647x5f04cef() {
        Logger.m49i("", "WeeklyTimerFragmentV3.onCheckedChanged " + this.isboolean);
        if (!this.isboolean) {
            String selectedDays = getSelectedDays();
            this.selectedDay = selectedDays;
            this.weeklyTimerViewModel.filterDayWiseData(selectedDays);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        int i = 0;
        while (true) {
            RadioButton[] radioButtonArr = this.buttonDays;
            if (i >= radioButtonArr.length) {
                break;
            }
            if (radioButtonArr[i].getId() == view.getId()) {
                if (this.buttonDays[i].isChecked()) {
                    break;
                } else if (this.checkboxesDaysSelection[i].isChecked().booleanValue()) {
                    this.checkboxesDaysSelection[i].setChecked(false);
                } else {
                    this.checkboxesDaysSelection[i].setChecked(true);
                }
            }
            i++;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public String getSelectedDays() {
        int selectedDaysPosition = getSelectedDaysPosition() + 1;
        if (selectedDaysPosition == 7) {
            return Weekday.getDay(0);
        }
        return Weekday.getDay(selectedDaysPosition);
    }

    private String getSelectedDays(int i) {
        int i2 = i + 1;
        if (i2 == 7) {
            return Weekday.getDay(0);
        }
        return Weekday.getDay(i2);
    }

    private int getSelectedDaysPosition() {
        int i = 0;
        while (true) {
            RadioButton[] radioButtonArr = this.buttonDays;
            if (i >= radioButtonArr.length) {
                return -1;
            }
            if (radioButtonArr[i].isChecked()) {
                return i;
            }
            i++;
        }
    }

    private void disableEnableAllDays(boolean z) {
        for (RadioButton clickable : this.buttonDays) {
            clickable.setClickable(z);
        }
    }

    private void initDaysOfSelection() {
        int i = 0;
        while (true) {
            TriStateCheckbox[] triStateCheckboxArr = this.checkboxesDaysSelection;
            if (i < triStateCheckboxArr.length) {
                triStateCheckboxArr[i] = (TriStateCheckbox) this.binding.include.layoutCheckBoxes.getChildAt(i + 2);
                i++;
            } else {
                return;
            }
        }
    }

    private void timerEnabledDisabledConfirmation(boolean z) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(this.mActivity);
        if (z) {
            confirmationDialog.setTitleString(this.mActivity.getString(R.string.common_alert_saveChanges));
            confirmationDialog.setMessageString(this.mActivity.getString(R.string.weeklyTimer_alert_weeklyTimerEnableConfirmation));
        } else {
            confirmationDialog.setTitleString(this.mActivity.getString(R.string.common_alert_saveChanges));
            confirmationDialog.setMessageString(this.mActivity.getString(R.string.weeklyTimer_alert_weeklyTimerDisableConfirmation));
        }
        confirmationDialog.setCancelable(false);
        confirmationDialog.setPositiveButton(this.mActivity.getString(R.string.common_btn_yes), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda18(this, z));
        confirmationDialog.setNegativeButton(this.mActivity.getString(R.string.common_btn_no), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda1(this, z));
        confirmationDialog.setParentView(this.binding.rootLayout);
        confirmationDialog.show();
    }

    /* renamed from: lambda$timerEnabledDisabledConfirmation$8$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ boolean mo30650x46bd730d(boolean z, Dialog dialog, View view) {
        showPleaseWaitDialog();
        if (z) {
            this.isEnablingTimer = true;
            this.weeklyTimerViewModel.weeklyTimerOperationEnable();
        } else {
            this.isEnablingTimer = false;
            this.weeklyTimerViewModel.weeklyTimerOperationDisable();
        }
        return true;
    }

    /* renamed from: lambda$timerEnabledDisabledConfirmation$9$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ boolean mo30651xd3aa8a2c(boolean z, Dialog dialog, View view) {
        if (z) {
            disableWeeklyTimer();
            this.binding.include.switchWeeklyTimer.setCheckedSilent(false);
        } else {
            enableWeeklyTimer();
            this.binding.include.switchWeeklyTimer.setCheckedSilent(true);
        }
        dialog.dismiss();
        return true;
    }

    private void scheduleErrorDialog(String str, boolean z) {
        ScheduleErrorDialog scheduleErrorDialog = new ScheduleErrorDialog(this.mActivity, true);
        if (z) {
            scheduleErrorDialog.setTitleString(this.mActivity.getString(R.string.weeklyTimer_alert_unableToDeleteSchedule));
            scheduleErrorDialog.setMessageString((int) R.string.weeklyTimer_alert_unableToDeleteScheduleDesc);
        } else {
            scheduleErrorDialog.setTitleString(this.mActivity.getString(R.string.weeklyTimer_alert_unableToOpenSchedule));
            scheduleErrorDialog.setMessageString((int) R.string.weeklyTimer_alert_unableToOpenScheduleDesc);
        }
        scheduleErrorDialog.setmDialogOperationalModeTitleString(this.mActivity.getString(R.string.weeklyTimer_alert_startDay));
        int i = 0;
        while (true) {
            if (i >= this.buttonDays.length) {
                break;
            } else if (str.equalsIgnoreCase(getSelectedDays(i))) {
                scheduleErrorDialog.setmDialogOperationalModeTitleValueString(this.buttonDays[i].getText().toString());
                break;
            } else {
                i++;
            }
        }
        scheduleErrorDialog.setPositiveButton(this.mActivity.getString(R.string.common_btn_continue), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda5(this, str));
        scheduleErrorDialog.setNegativeButton(this.mActivity.getString(R.string.common_btn_cancel), WeeklyTimerFragmentV3$$ExternalSyntheticLambda6.INSTANCE);
        scheduleErrorDialog.setParentView(this.binding.rootLayout);
        scheduleErrorDialog.show();
    }

    /* renamed from: lambda$scheduleErrorDialog$10$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ boolean mo30649x33e60be4(String str, Dialog dialog, View view) {
        dialog.dismiss();
        int i = 0;
        while (true) {
            if (i >= this.buttonDays.length) {
                break;
            }
            String selectedDays = getSelectedDays(i);
            if (!str.equalsIgnoreCase(selectedDays)) {
                i++;
            } else if (this.binding.imageButtonCopyWeeklyTimer.getVisibility() == 8) {
                this.selectedDay = selectedDays;
                this.weeklyTimerViewModel.filterDayWiseData(selectedDays);
            } else {
                this.buttonDays[i].setChecked(true);
            }
        }
        return true;
    }

    private void confirmationForChanges(int i, Integer[] numArr) {
        CopyScheduleConfirmationDialog copyScheduleConfirmationDialog = new CopyScheduleConfirmationDialog(this.mActivity);
        copyScheduleConfirmationDialog.setDialogTitle(this.mActivity.getString(R.string.weeklyTimer_alert_copySchedules));
        copyScheduleConfirmationDialog.setDialogSubTitle(this.mActivity.getString(R.string.weeklyTimer_alert_confirmCopyDesc));
        copyScheduleConfirmationDialog.setCopyFromValueStr(this.buttonDays[i].getText().toString());
        String[] strArr = new String[numArr.length];
        for (int i2 = 0; i2 < numArr.length; i2++) {
            strArr[i2] = this.buttonDays[numArr[i2].intValue()].getText().toString();
        }
        copyScheduleConfirmationDialog.setCopyToValueStr(Arrays.toString(strArr));
        copyScheduleConfirmationDialog.setCancelable(false);
        copyScheduleConfirmationDialog.setParentView((View) null);
        copyScheduleConfirmationDialog.setPositiveButton(getResources().getString(R.string.common_btn_continue), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda3(this));
        copyScheduleConfirmationDialog.setNegativeButton(getResources().getString(R.string.common_btn_cancel), WeeklyTimerFragmentV3$$ExternalSyntheticLambda4.INSTANCE);
        copyScheduleConfirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForChanges$12$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ boolean mo30640x700b1972(Dialog dialog, View view) {
        dialog.dismiss();
        requestForCopyTimerSchedule();
        return false;
    }

    /* access modifiers changed from: private */
    public void requestForCopyTimerSchedule() {
        int selectedDaysPosition = getSelectedDaysPosition();
        Integer[] selectedDaysForCopySchedule = getSelectedDaysForCopySchedule();
        String upperCase = getSelectedDays(selectedDaysPosition).toUpperCase();
        String[] strArr = new String[selectedDaysForCopySchedule.length];
        for (int i = 0; i < selectedDaysForCopySchedule.length; i++) {
            strArr[i] = getSelectedDays(selectedDaysForCopySchedule[i].intValue()).toUpperCase();
        }
        copyingSchedule(upperCase, strArr);
    }

    private void confirmationForDeletion() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(this.mActivity);
        confirmationDialog.setTitleString(getString(R.string.common_alert_saveChanges));
        confirmationDialog.setParentView(this.binding.getRoot());
        confirmationDialog.setMessageString((int) R.string.weeklyTimer_alert_deleteSchComfirmation);
        confirmationDialog.setPositiveButton(this.mActivity.getString(R.string.common_btn_yes), new WeeklyTimerFragmentV3$$ExternalSyntheticLambda17(this));
        confirmationDialog.setNegativeButton(this.mActivity.getString(R.string.common_btn_no), WeeklyTimerFragmentV3$$ExternalSyntheticLambda2.INSTANCE);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForDeletion$14$com-jch-racWiFi-iduManagement-view-WeeklyTimerFragmentV3 */
    public /* synthetic */ boolean mo30641xecba1149(Dialog dialog, View view) {
        dialog.dismiss();
        deleteWeeklyTimer();
        return false;
    }

    /* access modifiers changed from: private */
    public void deleteWeeklyTimer() {
        WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = this.weeklyTimerFactoryDataArrayList.get(this.position);
        long j = weeklyTimerFactoryData.f465id;
        long j2 = weeklyTimerFactoryData.scheduleId;
        showPleaseWaitDialog(false);
        this.weeklyTimerViewModel.deleteWeeklyTimer(j, j2);
    }

    private void notifyingForNoScheduleExist() {
        final SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
        singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog2.setMessageString(getResources().getString(R.string.weeklyTimer_alert_schDeletedOrDisabled));
        singleChoiceDialog2.setCancelable(false);
        singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog2.dismiss();
                WeeklyTimerFragmentV3.this.showPleaseWaitDialog(false);
                WeeklyTimerFragmentV3.this.getWeeklyTimerList();
                return false;
            }
        });
        singleChoiceDialog2.show();
    }

    private void notifyingForMaxReachedSchedule() {
        final SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
        singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog2.setMessageString(getString(R.string.weeklyTimer_alert_maxSchReachedPerDay));
        singleChoiceDialog2.setCancelable(false);
        singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog2.dismiss();
                return false;
            }
        });
        singleChoiceDialog2.show();
    }
}
