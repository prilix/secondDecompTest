package com.jch.racWiFi.timerPickerDialog;

import android.content.Context;
import android.view.View;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.timerPickerDialog.adapters.NumericWheelAdapter;
import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;
import com.jch.racWiFi.timerPickerDialog.data.source.TimeRepository;
import com.jch.racWiFi.timerPickerDialog.utils.C2363Utils;
import com.jch.racWiFi.timerPickerDialog.wheel.OnWheelChangedListener;
import com.jch.racWiFi.timerPickerDialog.wheel.WheelView;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Calendar;

public class TimeWheel {
    WheelView day;
    OnWheelChangedListener dayListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheelView, int i, int i2) {
            TimeWheel.this.updateHours();
        }
    };
    WheelView hour;
    Context mContext;
    NumericWheelAdapter mDayAdapter;
    NumericWheelAdapter mHourAdapter;
    NumericWheelAdapter mMinuteAdapter;
    NumericWheelAdapter mMonthAdapter;
    PickerConfig mPickerConfig;
    TimeRepository mRepository;
    NumericWheelAdapter mYearAdapter;
    WheelView minute;
    OnWheelChangedListener minuteListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheelView, int i, int i2) {
            TimeWheel.this.updateMinutes();
        }
    };
    WheelView month;
    OnWheelChangedListener monthListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheelView, int i, int i2) {
            TimeWheel.this.updateDays();
        }
    };
    WheelView year;
    OnWheelChangedListener yearListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheelView, int i, int i2) {
            TimeWheel.this.updateMonths();
        }
    };

    public TimeWheel(View view, PickerConfig pickerConfig) {
        this.mPickerConfig = pickerConfig;
        this.mRepository = new TimeRepository(pickerConfig);
        this.mContext = view.getContext();
        initialize(view);
    }

    public void setSelectedTimerListener(SelectableTimerListener selectableTimerListener) {
        this.hour.setSelectedTimerListener(selectableTimerListener);
        this.minute.setSelectedTimerListener(selectableTimerListener);
        selectableTimerListener.isTimerSelected();
    }

    public void initialize(View view) {
        initView(view);
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();
    }

    /* access modifiers changed from: package-private */
    public void initView(View view) {
        this.year = (WheelView) view.findViewById(R.id.year);
        this.month = (WheelView) view.findViewById(R.id.month);
        this.day = (WheelView) view.findViewById(R.id.day);
        this.hour = (WheelView) view.findViewById(R.id.hour);
        this.minute = (WheelView) view.findViewById(R.id.minute);
        int i = C23625.$SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type[this.mPickerConfig.mType.ordinal()];
        if (i == 2) {
            C2363Utils.hideViews(this.hour, this.minute);
        } else if (i == 3) {
            C2363Utils.hideViews(this.day, this.hour, this.minute);
        } else if (i == 4) {
            C2363Utils.hideViews(this.year);
        } else if (i == 5) {
            C2363Utils.hideViews(this.year, this.month, this.day);
        } else if (i == 6) {
            C2363Utils.hideViews(this.month, this.day, this.hour, this.minute);
        }
        this.year.addChangingListener(this.yearListener);
        this.year.addChangingListener(this.monthListener);
        this.year.addChangingListener(this.dayListener);
        this.year.addChangingListener(this.minuteListener);
        this.month.addChangingListener(this.monthListener);
        this.month.addChangingListener(this.dayListener);
        this.month.addChangingListener(this.minuteListener);
        this.day.addChangingListener(this.dayListener);
        this.day.addChangingListener(this.minuteListener);
        this.hour.addChangingListener(this.minuteListener);
    }

    /* renamed from: com.jch.racWiFi.timerPickerDialog.TimeWheel$5 */
    static /* synthetic */ class C23625 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.timerPickerDialog.data.Type[] r0 = com.jch.racWiFi.timerPickerDialog.data.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type = r0
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.ALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.YEAR_MONTH_DAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.YEAR_MONTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.MONTH_DAY_HOUR_MIN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.HOURS_MINS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$timerPickerDialog$data$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.timerPickerDialog.data.Type r1 = com.jch.racWiFi.timerPickerDialog.data.Type.YEAR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.timerPickerDialog.TimeWheel.C23625.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void initYear() {
        int minYear = this.mRepository.getMinYear();
        int i = minYear;
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this.mContext, i, this.mRepository.getMaxYear(), "%02d", this.mPickerConfig.mYear);
        this.mYearAdapter = numericWheelAdapter;
        numericWheelAdapter.setConfig(this.mPickerConfig);
        this.year.setViewAdapter(this.mYearAdapter);
        this.year.setCurrentItem(this.mRepository.getDefaultCalendar().year - minYear);
    }

    /* access modifiers changed from: package-private */
    public void initMonth() {
        updateMonths();
        this.month.setCurrentItem(this.mRepository.getDefaultCalendar().month - this.mRepository.getMinMonth(getCurrentYear()));
        this.month.setCyclic(this.mPickerConfig.cyclic);
    }

    /* access modifiers changed from: package-private */
    public void initDay() {
        updateDays();
        this.day.setCurrentItem(this.mRepository.getDefaultCalendar().day - this.mRepository.getMinDay(getCurrentYear(), getCurrentMonth()));
        this.day.setCyclic(this.mPickerConfig.cyclic);
    }

    private void initHour() {
        updateHours();
        int minHour = this.mRepository.getMinHour(getCurrentYear(), getCurrentMonth(), getCurrentDay());
        this.hour.setCurrentItem(Integer.parseInt(this.mPickerConfig.mCurrentTimer.split(":")[0]) - minHour);
        this.hour.setCyclic(this.mPickerConfig.cyclic);
    }

    private void initMinute() {
        updateMinutes();
        int minMinute = this.mRepository.getMinMinute(getCurrentYear(), getCurrentMonth(), getCurrentDay(), getCurrentHour());
        int parseInt = Integer.parseInt(this.mPickerConfig.mCurrentTimer.split(":")[1]);
        int i = parseInt % this.mPickerConfig.stepsForMinute;
        int i2 = parseInt / this.mPickerConfig.stepsForMinute;
        if (i > 5 && i2 < 5) {
            i2++;
        }
        this.minute.setCurrentItem(i2 - minMinute);
        this.minute.setCyclic(this.mPickerConfig.cyclic);
    }

    /* access modifiers changed from: private */
    public void updateMonths() {
        if (this.month.getVisibility() != 8) {
            int currentYear = getCurrentYear();
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this.mContext, this.mRepository.getMinMonth(currentYear), this.mRepository.getMaxMonth(currentYear), "%02d", this.mPickerConfig.mMonth);
            this.mMonthAdapter = numericWheelAdapter;
            numericWheelAdapter.setConfig(this.mPickerConfig);
            this.month.setViewAdapter(this.mMonthAdapter);
            if (this.mRepository.isMinYear(currentYear)) {
                this.month.setCurrentItem(0, false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateDays() {
        if (this.day.getVisibility() != 8) {
            int currentYear = getCurrentYear();
            int currentMonth = getCurrentMonth();
            Calendar instance = Calendar.getInstance();
            instance.set(1, instance.get(1) + this.year.getCurrentItem());
            instance.set(2, currentMonth);
            int maxDay = this.mRepository.getMaxDay(currentYear, currentMonth);
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this.mContext, this.mRepository.getMinDay(currentYear, currentMonth), maxDay, "%02d", this.mPickerConfig.mDay);
            this.mDayAdapter = numericWheelAdapter;
            numericWheelAdapter.setConfig(this.mPickerConfig);
            this.day.setViewAdapter(this.mDayAdapter);
            if (this.mRepository.isMinMonth(currentYear, currentMonth)) {
                this.day.setCurrentItem(0, true);
            }
            int itemsCount = this.mDayAdapter.getItemsCount();
            if (this.day.getCurrentItem() >= itemsCount) {
                this.day.setCurrentItem(itemsCount - 1, true);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateHours() {
        if (this.hour.getVisibility() != 8) {
            int currentYear = getCurrentYear();
            int currentMonth = getCurrentMonth();
            int currentDay = getCurrentDay();
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this.mContext, this.mRepository.getMinHour(currentYear, currentMonth, currentDay), this.mRepository.getMaxHour(currentYear, currentMonth, currentDay), "%02d", this.mPickerConfig.mHour);
            this.mHourAdapter = numericWheelAdapter;
            numericWheelAdapter.setConfig(this.mPickerConfig);
            this.hour.setViewAdapter(this.mHourAdapter);
            if (this.mRepository.isMinDay(currentYear, currentMonth, currentDay)) {
                this.hour.setCurrentItem(0, false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateMinutes() {
        if (this.minute.getVisibility() != 8) {
            int currentYear = getCurrentYear();
            int currentMonth = getCurrentMonth();
            int currentDay = getCurrentDay();
            int currentHour = getCurrentHour();
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this.mContext, this.mRepository.getMinMinute(currentYear, currentMonth, currentDay, currentHour), this.mRepository.getMaxMinute(currentYear, currentMonth, currentDay, currentHour), "%02d", this.mPickerConfig.mMinute);
            this.mMinuteAdapter = numericWheelAdapter;
            numericWheelAdapter.setConfig(this.mPickerConfig);
            this.minute.setViewAdapter(this.mMinuteAdapter);
            if (this.mRepository.isMinHour(currentYear, currentMonth, currentDay, currentHour)) {
                this.minute.setCurrentItem(0, false);
            }
            Logger.m49i("", "TimeWheel.updateMinutes " + this.minute.getCurrentItem());
        }
    }

    public int getCurrentYear() {
        return this.year.getCurrentItem() + this.mRepository.getMinYear();
    }

    public int getCurrentMonth() {
        return this.month.getCurrentItem() + this.mRepository.getMinMonth(getCurrentYear());
    }

    public int getCurrentDay() {
        return this.day.getCurrentItem() + this.mRepository.getMinDay(getCurrentYear(), getCurrentMonth());
    }

    public int getCurrentHour() {
        return this.hour.getCurrentItem() + this.mRepository.getMinHour(getCurrentYear(), getCurrentMonth(), getCurrentDay());
    }

    public int getCurrentMinute() {
        int currentItem = this.minute.getCurrentItem() + this.mRepository.getMinMinute(getCurrentYear(), getCurrentMonth(), getCurrentDay(), getCurrentHour());
        Logger.m49i("", "TimeWheel.getCurrentMinute " + currentItem);
        return currentItem;
    }
}
