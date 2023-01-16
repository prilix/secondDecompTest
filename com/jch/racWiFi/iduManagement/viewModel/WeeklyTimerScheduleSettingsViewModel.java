package com.jch.racWiFi.iduManagement.viewModel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.Weekday;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.network.WeeklyTimerDispatcherV2;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class WeeklyTimerScheduleSettingsViewModel extends C0534ViewModel {
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerAddResponse> addMutableData = new MutableLiveData<>();
    private boolean isAddItemClicked;
    private boolean isFirstTimeCall = true;
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel;
    private RacModelWiseData mRacModelWiseData;
    private MutableLiveData<String> mTempreatureMutableData = new MutableLiveData<>();
    private OperationMode operationMode;
    private String powerMode;
    private MutableLiveData<String> powerModeMutableData = new MutableLiveData<>();
    public MutableLiveData<WeeklyTimerModelV2.WeeklyTimerRemoveResponse> removeMutableData = new MutableLiveData<>();
    private String selectedDay;
    private MutableLiveData<TimerEnabledModel.TimerEnabledResponse> timerEnabledResponseMutableLiveData = new MutableLiveData<>();
    private WeeklyTimerModelV2.TimerHolderData timerHolderData = new WeeklyTimerModelV2.TimerHolderData();
    private MutableLiveData<WeeklyTimerModelV2.TimerHolderData> timerHolderMutableData = new MutableLiveData<>();
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerUpdateResponse> updateMutableData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public MutableLiveData<OperationMode> updateOperationModeMutableData = new MutableLiveData<>();
    private WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = new WeeklyTimerModelV2.WeeklyTimerFactoryData();

    public static class SelectModeRecyclerViewAdapter extends RecyclerView.Adapter<SelectModeViewHolder> {
        private Context context;
        private List<ExpandableItem> menuItemList;

        public class SelectModeViewHolder_ViewBinding implements Unbinder {
            private SelectModeViewHolder target;
            private View view7f0a05c1;

            public SelectModeViewHolder_ViewBinding(final SelectModeViewHolder selectModeViewHolder, View view) {
                this.target = selectModeViewHolder;
                selectModeViewHolder.mMenuImageSetMode = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_mode_item, "field 'mMenuImageSetMode'", ImageView.class);
                selectModeViewHolder.mMenuInfoSetMode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_mode_item, "field 'mMenuInfoSetMode'", TextView.class);
                View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_select_mode, "field 'mOuterLayout' and method 'onClickItem'");
                selectModeViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_select_mode, "field 'mOuterLayout'", ConstraintLayout.class);
                this.view7f0a05c1 = findRequiredView;
                findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View view) {
                        selectModeViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                    }
                });
            }

            public void unbind() {
                SelectModeViewHolder selectModeViewHolder = this.target;
                if (selectModeViewHolder != null) {
                    this.target = null;
                    selectModeViewHolder.mMenuImageSetMode = null;
                    selectModeViewHolder.mMenuInfoSetMode = null;
                    selectModeViewHolder.mOuterLayout = null;
                    this.view7f0a05c1.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a05c1 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public SelectModeRecyclerViewAdapter(Context context2, List<ExpandableItem> list) {
            this.context = context2;
            this.menuItemList = list;
        }

        public SelectModeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SelectModeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_mode, viewGroup, false));
        }

        public void onBindViewHolder(SelectModeViewHolder selectModeViewHolder, int i) {
            selectModeViewHolder.bind(this.menuItemList.get(i));
        }

        public int getItemCount() {
            return this.menuItemList.size();
        }

        class SelectModeViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362950)
            ImageView mMenuImageSetMode;
            @BindView(2131364344)
            TextView mMenuInfoSetMode;
            @BindView(2131363265)
            ConstraintLayout mOuterLayout;

            public SelectModeViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            @OnClick({2131363265})
            public void onClickItem(ConstraintLayout constraintLayout) {
                ((ExpandableItem) constraintLayout.getTag()).getOnClickListener().onClick(constraintLayout);
            }

            public void bind(ExpandableItem expandableItem) {
                this.mMenuImageSetMode.setImageResource(expandableItem.getImageMode());
                this.mMenuInfoSetMode.setText(expandableItem.getTextMode());
                this.mOuterLayout.setTag(expandableItem);
            }
        }
    }

    public void setRacModelWiseData(RacModelWiseData racModelWiseData) {
        this.mRacModelWiseData = racModelWiseData;
    }

    public void setData(Bundle bundle) {
        RacModelWiseData.TemperatureSettingType temperatureSettingType;
        if (bundle != null) {
            this.isAddItemClicked = bundle.getBoolean("ADD_BUTTON");
            DetailedIduModel detailedIduModel = new DetailedIduModel();
            this.mDetailedIduModel = detailedIduModel;
            try {
                DetailedIduModel detailedIduModel2 = (DetailedIduModel) bundle.getParcelable(DetailedIduModel.PARCEL_KEY);
                Objects.requireNonNull(detailedIduModel2);
                DetailedIduModel detailedIduModel3 = detailedIduModel2;
                detailedIduModel.copy(detailedIduModel2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!this.isAddItemClicked) {
                WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData2 = (WeeklyTimerModelV2.WeeklyTimerFactoryData) bundle.getParcelable(WeeklyTimerModelV2.WeeklyTimerFactoryData.PARCEL_KEY);
                this.weeklyTimerFactoryData = weeklyTimerFactoryData2;
                String hourFromTimerStrings = DateAndTimeUtils.getHourFromTimerStrings(weeklyTimerFactoryData2.startAt);
                this.timerHolderData.startHour = Integer.parseInt(hourFromTimerStrings);
                String minuteFromTimerStrings = DateAndTimeUtils.getMinuteFromTimerStrings(this.weeklyTimerFactoryData.startAt);
                this.timerHolderData.startMinute = Integer.parseInt(minuteFromTimerStrings);
                String hourFromTimerStrings2 = DateAndTimeUtils.getHourFromTimerStrings(this.weeklyTimerFactoryData.endAt);
                this.timerHolderData.endHour = Integer.parseInt(hourFromTimerStrings2);
                String minuteFromTimerStrings2 = DateAndTimeUtils.getMinuteFromTimerStrings(this.weeklyTimerFactoryData.endAt);
                this.timerHolderData.endMinute = Integer.parseInt(minuteFromTimerStrings2);
                if (!this.weeklyTimerFactoryData.endingDay.equals("")) {
                    this.timerHolderData.day = Weekday.getPosition(this.weeklyTimerFactoryData.endingDay);
                }
                this.mDetailedIduModel.mode = this.weeklyTimerFactoryData.mode;
                this.operationMode = OperationMode.valueOf(this.weeklyTimerFactoryData.mode);
                this.selectedDay = this.weeklyTimerFactoryData.day;
                this.powerMode = this.weeklyTimerFactoryData.power;
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.operationMode);
                if (racModeDetailBasedOnMode != null) {
                    RacModelWiseData.TemperatureSettingType temperatureSettingType2 = racModeDetailBasedOnMode.getTemperatureSettingType();
                    if (temperatureSettingType2 == null || !temperatureSettingType2.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        this.mDetailedIduModel.iduTemperature = (float) this.weeklyTimerFactoryData.temperature;
                    } else {
                        this.mDetailedIduModel.relativeTemperature = (float) this.weeklyTimerFactoryData.temperature;
                    }
                }
            } else {
                this.mDetailedIduModel.mode = OperationMode.COOLING.name();
                this.operationMode = OperationMode.valueOf(this.mDetailedIduModel.mode);
                this.mDetailedIduModel.copyDefaults(this.mRacModelWiseData);
                this.selectedDay = bundle.getString("SELECTED_DAY");
                this.powerMode = DetailedIduModel.POWER_ON;
            }
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode2 = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.operationMode);
            if (racModeDetailBasedOnMode2 != null && (temperatureSettingType = racModeDetailBasedOnMode2.getTemperatureSettingType()) != null) {
                if (temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode2.getVisibleTemperatureSettingType();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        this.mTempreatureMutableData.setValue(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(racModeDetailBasedOnMode2.getReferenceTemperature() + this.mDetailedIduModel.relativeTemperature), this.mRacModelWiseData, this.mDetailedIduModel));
                    } else {
                        this.mTempreatureMutableData.setValue(TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(this.mDetailedIduModel.relativeTemperature), this.mRacModelWiseData, this.mDetailedIduModel));
                    }
                } else {
                    this.mTempreatureMutableData.setValue(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(this.mDetailedIduModel.iduTemperature), this.mRacModelWiseData, this.mDetailedIduModel));
                }
                this.updateOperationModeMutableData.setValue(this.operationMode);
                this.timerHolderMutableData.setValue(this.timerHolderData);
                this.powerModeMutableData.setValue(this.powerMode);
                calculateEndTimer();
            }
        }
    }

    public void updateWeeklyTimerSchedule() {
        RacModelWiseData.TemperatureSettingType temperatureSettingType;
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.operationMode);
        if (racModeDetailBasedOnMode != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
            if (this.isAddItemClicked) {
                WeeklyTimerModelV2.AddRequestData addRequestData = new WeeklyTimerModelV2.AddRequestData();
                addRequestData.copy(this.mDetailedIduModel.f454id, this.selectedDay.toUpperCase(), this.operationMode.name(), getStartTimeAt(), (double) (temperatureSettingType == RacModelWiseData.TemperatureSettingType.RELATIVE ? this.mDetailedIduModel.relativeTemperature : this.mDetailedIduModel.iduTemperature), this.powerMode);
                new WeeklyTimerDispatcherV2().addWeeklyTimer(addRequestData).observeForever(new WeeklyTimerScheduleSettingsViewModel$$ExternalSyntheticLambda0(this));
                return;
            }
            WeeklyTimerModelV2.UpdateRequestData updateRequestData = new WeeklyTimerModelV2.UpdateRequestData();
            updateRequestData.copy(this.mDetailedIduModel.f454id, this.selectedDay.toUpperCase(), this.operationMode.name(), getStartTimeAt(), temperatureSettingType == RacModelWiseData.TemperatureSettingType.RELATIVE ? this.mDetailedIduModel.relativeTemperature : this.mDetailedIduModel.iduTemperature, this.powerMode, this.weeklyTimerFactoryData.scheduleId);
            new WeeklyTimerDispatcherV2().changeWeeklyTimer(updateRequestData).observeForever(new WeeklyTimerScheduleSettingsViewModel$$ExternalSyntheticLambda1(this));
        }
    }

    /* renamed from: lambda$updateWeeklyTimerSchedule$0$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerScheduleSettingsViewModel */
    public /* synthetic */ void mo31219x776acdf0(WeeklyTimerModelV2.WeeklyTimerAddResponse weeklyTimerAddResponse) {
        this.addMutableData.setValue(weeklyTimerAddResponse);
    }

    /* renamed from: lambda$updateWeeklyTimerSchedule$1$com-jch-racWiFi-iduManagement-viewModel-WeeklyTimerScheduleSettingsViewModel */
    public /* synthetic */ void mo31220x906c1f8f(WeeklyTimerModelV2.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse) {
        this.updateMutableData.setValue(weeklyTimerUpdateResponse);
    }

    private String getStartTimeAt() {
        return DateAndTimeUtils.setCurrentTime(this.timerHolderData.startHour, this.timerHolderData.startMinute) + ":00";
    }

    public boolean isNoEndTimer() {
        WeeklyTimerModelV2.WeeklyTimerFetchResponse value = WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems().getValue();
        if (WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems().getValue() == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(value);
        WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse = value;
        arrayList.addAll(Arrays.asList((WeeklyTimerModelV2.WeeklyTimerResponseData[]) value.response));
        return arrayList.size() == 0 || (arrayList.size() == 1 && !isAddItemClicked());
    }

    public void increaseTemperature() {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
        RacModelWiseData.TemperatureSettingType temperatureSettingType;
        String str;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null && (racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.operationMode)) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
            if (C21942.f475xf7673082[temperatureSettingType.ordinal()] != 1) {
                float f = this.mDetailedIduModel.iduTemperature;
                if (f != Float.MAX_VALUE && f < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                    this.mDetailedIduModel.iduTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    this.mTempreatureMutableData.setValue(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(this.mDetailedIduModel.iduTemperature), this.mRacModelWiseData, this.mDetailedIduModel));
                    return;
                }
                return;
            }
            float f2 = this.mDetailedIduModel.relativeTemperature;
            if (f2 != Float.MAX_VALUE && f2 < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                this.mDetailedIduModel.relativeTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + this.mDetailedIduModel.relativeTemperature;
                RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                } else {
                    str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(this.mDetailedIduModel.relativeTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                }
                this.mTempreatureMutableData.setValue(str);
            }
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerScheduleSettingsViewModel$2 */
    static /* synthetic */ class C21942 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$RacModelWiseData$TemperatureSettingType */
        static final /* synthetic */ int[] f475xf7673082;

        static {
            int[] iArr = new int[RacModelWiseData.TemperatureSettingType.values().length];
            f475xf7673082 = iArr;
            try {
                iArr[RacModelWiseData.TemperatureSettingType.RELATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void decreaseTemperature() {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
        RacModelWiseData.TemperatureSettingType temperatureSettingType;
        String str;
        RacModelWiseData racModelWiseData = this.mRacModelWiseData;
        if (racModelWiseData != null && (racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.operationMode)) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
            if (C21942.f475xf7673082[temperatureSettingType.ordinal()] != 1) {
                float f = this.mDetailedIduModel.iduTemperature;
                if (f != Float.MAX_VALUE && f > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                    this.mDetailedIduModel.iduTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    this.mTempreatureMutableData.setValue(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(this.mDetailedIduModel.iduTemperature), this.mRacModelWiseData, this.mDetailedIduModel));
                    return;
                }
                return;
            }
            float f2 = this.mDetailedIduModel.relativeTemperature;
            if (f2 != Float.MAX_VALUE && f2 > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                this.mDetailedIduModel.relativeTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + this.mDetailedIduModel.relativeTemperature;
                RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                } else {
                    str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(this.mDetailedIduModel.relativeTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                }
                this.mTempreatureMutableData.setValue(str);
            }
        }
    }

    public void setDefaultTemperature(OperationMode operationMode2) {
        if (this.isFirstTimeCall) {
            this.isFirstTimeCall = false;
            return;
        }
        this.mDetailedIduModel.copyDefaults(this.mRacModelWiseData);
        String str = null;
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationMode2);
        if (racModeDetailBasedOnMode != null) {
            RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
            if (temperatureSettingType != null) {
                if (C21942.f475xf7673082[temperatureSettingType.ordinal()] != 1) {
                    str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(racModeDetailBasedOnMode.getDefaultTemperature()), this.mRacModelWiseData, this.mDetailedIduModel);
                    this.mDetailedIduModel.iduTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
                } else {
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    float defaultTemperature = racModeDetailBasedOnMode.getDefaultTemperature() + racModeDetailBasedOnMode.getReferenceTemperature();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(defaultTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(this.mDetailedIduModel.relativeTemperature), this.mRacModelWiseData, this.mDetailedIduModel);
                    }
                }
            }
            this.mTempreatureMutableData.setValue(str);
        }
    }

    public List<ExpandableItem> populateMenuItemsSelectMode(Context context) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mRacModelWiseData.getRacModeDetails().iterator();
        while (it.hasNext()) {
            final OperationMode mode = ((RacModelWiseData.RacModeDetail) it.next()).getMode();
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(mode);
            if (operationModeUIConfigrationBasedOnMode != null) {
                ExpandableItem expandableItem = new ExpandableItem();
                expandableItem.setImageMode(operationModeUIConfigrationBasedOnMode.getModeListDrawableResource());
                expandableItem.setTextMode(context.getString(operationModeUIConfigrationBasedOnMode.getOperationModeStringResource()));
                expandableItem.setDisplayOrder(mode.getDisplayOrder());
                expandableItem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        WeeklyTimerScheduleSettingsViewModel.this.mDetailedIduModel.mode = mode.name();
                        WeeklyTimerScheduleSettingsViewModel.this.updateOperationModeMutableData.setValue(mode);
                    }
                });
                arrayList.add(expandableItem);
            }
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public String getSelectedDay() {
        return this.selectedDay;
    }

    private boolean isAddItemClicked() {
        return this.isAddItemClicked;
    }

    public boolean isAlreadyDefinedSettings(int i, int i2) {
        if (WeeklyTimerViewModel.updatedDaywiseFilterSchedule().getValue() == null) {
            return false;
        }
        ArrayList value = WeeklyTimerViewModel.updatedDaywiseFilterSchedule().getValue();
        Objects.requireNonNull(value);
        ArrayList arrayList = value;
        Iterator it = value.iterator();
        while (it.hasNext()) {
            WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData2 = (WeeklyTimerModelV2.WeeklyTimerFactoryData) it.next();
            if (Weekday.getPosition(weeklyTimerFactoryData2.day) == Weekday.getPosition(weeklyTimerFactoryData2.startingDay)) {
                Date convertToDateObject = DateAndTimeUtils.convertToDateObject(weeklyTimerFactoryData2.startAt);
                Date convertToDateObject2 = DateAndTimeUtils.convertToDateObject(i + ":" + i2);
                if (convertToDateObject.compareTo(convertToDateObject2) == 0) {
                    if (!isAddItemClicked()) {
                        return isUserPickedDisplayTime(convertToDateObject2);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isUserPickedDisplayTime(Date date) {
        return DateAndTimeUtils.convertToDateObject(this.weeklyTimerFactoryData.startAt).compareTo(date) != 0;
    }

    public void calculateEndTimer() {
        WeeklyTimerModelV2.WeeklyTimerFetchResponse value = WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems().getValue();
        if (WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems().getValue() != null) {
            if (value == null || value.response != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList((WeeklyTimerModelV2.WeeklyTimerResponseData[]) value.response));
                Collections.sort(arrayList);
                if (arrayList.size() == 0) {
                    this.timerHolderData.endHour = 0;
                    this.timerHolderData.endMinute = 0;
                } else {
                    boolean z = true;
                    if (arrayList.size() == 1) {
                        String hourFromTimerStrings = DateAndTimeUtils.getHourFromTimerStrings(((WeeklyTimerModelV2.WeeklyTimerResponseData) arrayList.get(0)).startAt);
                        this.timerHolderData.endHour = Integer.parseInt(hourFromTimerStrings);
                        String minuteFromTimerStrings = DateAndTimeUtils.getMinuteFromTimerStrings(((WeeklyTimerModelV2.WeeklyTimerResponseData) arrayList.get(0)).startAt);
                        this.timerHolderData.endMinute = Integer.parseInt(minuteFromTimerStrings);
                        this.timerHolderData.day = Weekday.getPosition(((WeeklyTimerModelV2.WeeklyTimerResponseData) arrayList.get(0)).day);
                    } else {
                        WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData = null;
                        int i = 0;
                        while (true) {
                            if (i >= arrayList.size()) {
                                z = false;
                                break;
                            }
                            weeklyTimerResponseData = (WeeklyTimerModelV2.WeeklyTimerResponseData) arrayList.get(i);
                            if (Weekday.getPosition(weeklyTimerResponseData.day) > Weekday.getPosition(this.selectedDay) || (Weekday.getPosition(weeklyTimerResponseData.day) == Weekday.getPosition(this.selectedDay) && DateAndTimeUtils.convertToDateObject(DateAndTimeUtils.setCurrentTime(this.timerHolderData.startHour, this.timerHolderData.startMinute)).compareTo(DateAndTimeUtils.convertToDateObject(weeklyTimerResponseData.startAt)) < 0)) {
                                break;
                            }
                            i++;
                        }
                        if (!z) {
                            weeklyTimerResponseData = (WeeklyTimerModelV2.WeeklyTimerResponseData) arrayList.get(0);
                        }
                        String hourFromTimerStrings2 = DateAndTimeUtils.getHourFromTimerStrings(weeklyTimerResponseData.startAt);
                        this.timerHolderData.endHour = Integer.parseInt(hourFromTimerStrings2);
                        String minuteFromTimerStrings2 = DateAndTimeUtils.getMinuteFromTimerStrings(weeklyTimerResponseData.startAt);
                        this.timerHolderData.endMinute = Integer.parseInt(minuteFromTimerStrings2);
                        this.timerHolderData.day = Weekday.getPosition(weeklyTimerResponseData.day);
                    }
                }
                this.timerHolderMutableData.setValue(this.timerHolderData);
            }
        }
    }

    public MutableLiveData<OperationMode> updateOperationMode() {
        return this.updateOperationModeMutableData;
    }

    public MutableLiveData<WeeklyTimerModelV2.TimerHolderData> getUpdatedTimer() {
        return this.timerHolderMutableData;
    }

    public MutableLiveData<String> getTemperature() {
        return this.mTempreatureMutableData;
    }

    public WeeklyTimerModelV2.TimerHolderData getTimerHolderData() {
        return this.timerHolderData;
    }

    public MutableLiveData<TimerEnabledModel.TimerEnabledResponse> getTimerEnabledResponseMutableLiveData() {
        return this.timerEnabledResponseMutableLiveData;
    }

    public MutableLiveData<WeeklyTimerModelV2.WeeklyTimerAddResponse> getAddMutableData() {
        return this.addMutableData;
    }

    public MutableLiveData<WeeklyTimerModelV2.WeeklyTimerUpdateResponse> getUpdateMutableData() {
        return this.updateMutableData;
    }

    public MutableLiveData<String> updatePowerMode() {
        return this.powerModeMutableData;
    }

    public String getPowerMode() {
        return this.powerMode;
    }

    public void setPowerMode(String str) {
        this.powerMode = str;
    }

    public DetailedIduModel getmDetailedIduModel() {
        return this.mDetailedIduModel;
    }

    public void setOperationMode(OperationMode operationMode2) {
        this.operationMode = operationMode2;
    }

    public String getTitle() {
        return this.mDetailedIduModel.name;
    }

    public class ExpandableItem implements Comparable<ExpandableItem> {
        private int displayOrder;
        private boolean expanded;
        private int mImageMode;
        private int mImageSwipe;
        private String mTextMode;
        public View.OnClickListener onClickListener;
        private boolean toExpand;

        public ExpandableItem() {
        }

        public int getDisplayOrder() {
            return this.displayOrder;
        }

        public void setDisplayOrder(int i) {
            this.displayOrder = i;
        }

        public boolean tobeExpanded() {
            return this.toExpand;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public int getImageMode() {
            return this.mImageMode;
        }

        public void setImageMode(int i) {
            this.mImageMode = i;
        }

        public String getTextMode() {
            return this.mTextMode;
        }

        public void setImageSwipe(int i) {
            this.mImageSwipe = i;
        }

        public int getImageSwipe() {
            return this.mImageSwipe;
        }

        public void setTextMode(String str) {
            this.mTextMode = str;
        }

        public void setExpanded(boolean z) {
            this.expanded = z;
        }

        public boolean isExpanded() {
            return this.expanded;
        }

        public int compareTo(ExpandableItem expandableItem) {
            if (expandableItem == null) {
                return 0;
            }
            return Integer.compare(this.displayOrder, expandableItem.displayOrder);
        }
    }
}
