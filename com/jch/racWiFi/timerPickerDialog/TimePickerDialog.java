package com.jch.racWiFi.timerPickerDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.timerPickerDialog.config.PickerConfig;
import com.jch.racWiFi.timerPickerDialog.data.Type;
import com.jch.racWiFi.timerPickerDialog.data.WheelCalendar;
import com.jch.racWiFi.timerPickerDialog.listener.OnDateSetListener;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Calendar;

public class TimePickerDialog extends DialogFragment implements View.OnClickListener, SelectableTimerListener {
    private long mCurrentMillSeconds;
    PickerConfig mPickerConfig;
    private TimeWheel mTimeWheel;
    private TextView sure;
    private int timerType;

    /* access modifiers changed from: private */
    public static TimePickerDialog newIntance(PickerConfig pickerConfig) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(pickerConfig);
        return timePickerDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().getWindow().setSoftInputMode(3);
    }

    public void setCurrentTimer(String str) {
        this.mPickerConfig.mCurrentTimer = str;
    }

    public void onResume() {
        super.onResume();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.picker_height);
        Window window = getDialog().getWindow();
        window.setLayout(-1, dimensionPixelSize);
        window.setGravity(80);
    }

    private void initialize(PickerConfig pickerConfig) {
        this.mPickerConfig = pickerConfig;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), C1655R.style.Dialog_NoTitle);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    private View initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.timepicker_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_sure);
        this.sure = textView2;
        textView2.setOnClickListener(this);
        View findViewById = inflate.findViewById(R.id.toolbar);
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(this.mPickerConfig.mTitleString);
        textView.setText(this.mPickerConfig.mCancelString);
        this.sure.setText(this.mPickerConfig.mSureString);
        findViewById.setBackgroundColor(this.mPickerConfig.mThemeColor);
        TimeWheel timeWheel = new TimeWheel(inflate, this.mPickerConfig);
        this.mTimeWheel = timeWheel;
        timeWheel.setSelectedTimerListener(this);
        return inflate;
    }

    public TimeWheel getmTimeWheel() {
        return this.mTimeWheel;
    }

    public void setmTimeWheel(TimeWheel timeWheel) {
        this.mTimeWheel = timeWheel;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancel) {
            dismiss();
        } else if (id == R.id.tv_sure) {
            sureClicked();
        }
    }

    public long getCurrentMillSeconds() {
        long j = this.mCurrentMillSeconds;
        return j == 0 ? System.currentTimeMillis() : j;
    }

    /* access modifiers changed from: package-private */
    public void sureClicked() {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(1, this.mTimeWheel.getCurrentYear());
        instance.set(2, this.mTimeWheel.getCurrentMonth() - 1);
        instance.set(5, this.mTimeWheel.getCurrentDay());
        instance.set(11, this.mTimeWheel.getCurrentHour());
        instance.set(12, this.mTimeWheel.getCurrentMinute() * this.mPickerConfig.stepsForMinute);
        this.mCurrentMillSeconds = instance.getTimeInMillis();
        if (this.mPickerConfig.mCallBack != null) {
            this.mPickerConfig.mCallBack.onDateSet(this, this.mCurrentMillSeconds);
        }
        dismiss();
    }

    public int getTimerType() {
        return this.timerType;
    }

    public void setTimerType(int i) {
        this.timerType = i;
    }

    public void isTimerSelected() {
        int currentItem = this.mTimeWheel.hour.getCurrentItem();
        int currentItem2 = this.mTimeWheel.minute.getCurrentItem();
        if (currentItem == 0 && currentItem2 == 0) {
            this.sure.setEnabled(false);
            this.sure.setAlpha(0.5f);
            return;
        }
        this.sure.setEnabled(true);
        this.sure.setAlpha(1.0f);
    }

    public static class Builder {
        PickerConfig mPickerConfig = new PickerConfig();

        public Builder setType(Type type) {
            this.mPickerConfig.mType = type;
            return this;
        }

        public Builder setThemeColor(int i) {
            this.mPickerConfig.mThemeColor = i;
            return this;
        }

        public Builder setCancelStringId(String str) {
            this.mPickerConfig.mCancelString = str;
            return this;
        }

        public Builder setSureStringId(String str) {
            this.mPickerConfig.mSureString = str;
            return this;
        }

        public Builder setTitleStringId(String str) {
            this.mPickerConfig.mTitleString = str;
            return this;
        }

        public Builder setToolBarTextColor(int i) {
            this.mPickerConfig.mToolBarTVColor = i;
            return this;
        }

        public Builder setNormalTextSize(int i) {
            this.mPickerConfig.mWheelRVNormalSize = i;
            return this;
        }

        public Builder setSelecterTextSize(int i) {
            this.mPickerConfig.mWheelRVSelectorSize = i;
            return this;
        }

        public Builder setWheelItemTextNormalColor(int i) {
            this.mPickerConfig.mWheelTVNormalColor = i;
            return this;
        }

        public Builder setWheelItemTextSelectorColor(int i) {
            this.mPickerConfig.mWheelTVSelectorColor = i;
            return this;
        }

        public Builder setWheelItemTextSize(int i) {
            this.mPickerConfig.mWheelTVSize = i;
            return this;
        }

        public Builder setCyclic(boolean z) {
            this.mPickerConfig.cyclic = z;
            return this;
        }

        public Builder setMinMillseconds(long j) {
            this.mPickerConfig.mMinCalendar = new WheelCalendar(j);
            return this;
        }

        public Builder setMaxMillseconds(long j) {
            this.mPickerConfig.mMaxCalendar = new WheelCalendar(j);
            return this;
        }

        public Builder setCurrentMillseconds(long j) {
            this.mPickerConfig.mCurrentCalendar = new WheelCalendar(j);
            return this;
        }

        public Builder setYearText(String str) {
            this.mPickerConfig.mYear = str;
            return this;
        }

        public Builder setMonthText(String str) {
            this.mPickerConfig.mMonth = str;
            return this;
        }

        public Builder setDayText(String str) {
            this.mPickerConfig.mDay = str;
            return this;
        }

        public Builder setHourText(String str) {
            this.mPickerConfig.mHour = str;
            return this;
        }

        public Builder setMinuteText(String str) {
            this.mPickerConfig.mMinute = str;
            return this;
        }

        public Builder setCallBack(OnDateSetListener onDateSetListener) {
            this.mPickerConfig.mCallBack = onDateSetListener;
            return this;
        }

        public TimePickerDialog build() {
            return TimePickerDialog.newIntance(this.mPickerConfig);
        }
    }
}
