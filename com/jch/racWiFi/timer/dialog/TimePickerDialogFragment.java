package com.jch.racWiFi.timer.dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.timer.standard.TimeSetListener;
import com.jch.racWiFi.timer.util.TimeFormat;
import com.jch_hitachi.aircloudglobal.R;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import p020ua.naiksoftware.stomp.dto.StompHeader;

public class TimePickerDialogFragment extends BaseDialogFragment {
    private static final String TAG = "TimePickerDialog";
    private TimeSetListener mTimeSetListener;

    public static TimePickerDialogFragment newInstance(Bundle bundle) {
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setArguments(bundle);
        return timePickerDialogFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.dialog_fragment_time_picker, viewGroup);
    }

    public void onViewCreated(View view, Bundle bundle) {
        String string;
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string2 = arguments.getString("title", "Enter Name");
            Dialog dialog = getDialog();
            if (dialog != null) {
                dialog.setTitle(string2);
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setSoftInputMode(4);
                }
            }
        }
        TimePicker timePicker = (TimePicker) view.findViewById(R.id.timerPopUpTimePicker);
        setInterval(timePicker);
        int i = Calendar.getInstance().get(12);
        if (Build.VERSION.SDK_INT >= 23) {
            timePicker.setMinute(i / 10);
        } else {
            timePicker.setCurrentMinute(Integer.valueOf(i / 10));
        }
        if (!(arguments == null || (string = arguments.getString(Constants.DISPLAY_FORMAT)) == null || string.isEmpty())) {
            if (string.trim().equals(TimeFormat.IN_24_HRS_FORMAT.name())) {
                updateView(true, timePicker, (AppCompatTextView) view.findViewById(R.id.timer24Hour), (AppCompatTextView) view.findViewById(R.id.timer12Hour));
            } else {
                updateView(false, timePicker, (AppCompatTextView) view.findViewById(R.id.timer12Hour), (AppCompatTextView) view.findViewById(R.id.timer24Hour));
            }
        }
        view.findViewById(R.id.timer12Hour).setOnClickListener(new TimePickerDialogFragment$$ExternalSyntheticLambda2(this, timePicker, view));
        view.findViewById(R.id.timer24Hour).setOnClickListener(new TimePickerDialogFragment$$ExternalSyntheticLambda3(this, timePicker, view));
        view.findViewById(R.id.btnTimerPopUpCancel).setOnClickListener(new TimePickerDialogFragment$$ExternalSyntheticLambda0(this));
        view.findViewById(R.id.btnTimerPopUpSave).setOnClickListener(new TimePickerDialogFragment$$ExternalSyntheticLambda1(this, arguments, timePicker));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-timer-dialog-TimePickerDialogFragment */
    public /* synthetic */ void mo31999x5646e311(TimePicker timePicker, View view, View view2) {
        updateView(false, timePicker, (AppCompatTextView) view.findViewById(R.id.timer12Hour), (AppCompatTextView) view.findViewById(R.id.timer24Hour));
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-timer-dialog-TimePickerDialogFragment */
    public /* synthetic */ void mo32000x7f9b3852(TimePicker timePicker, View view, View view2) {
        updateView(true, timePicker, (AppCompatTextView) view.findViewById(R.id.timer24Hour), (AppCompatTextView) view.findViewById(R.id.timer12Hour));
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-timer-dialog-TimePickerDialogFragment */
    public /* synthetic */ void mo32001xa8ef8d93(View view) {
        dismiss();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-timer-dialog-TimePickerDialogFragment */
    public /* synthetic */ void mo32002xd243e2d4(Bundle bundle, TimePicker timePicker, View view) {
        String string;
        String string2;
        if (Build.VERSION.SDK_INT >= 23) {
            if (bundle != null && (string2 = bundle.getString(Constants.SWITCH, (String) null)) != null) {
                try {
                    notifyListener(timePicker.getHour(), timePicker.getMinute() * 10, timePicker.is24HourView() ? TimeFormat.IN_24_HRS_FORMAT : TimeFormat.IN_12_HRS_FORMAT, string2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else if (bundle != null && (string = bundle.getString(Constants.SWITCH, (String) null)) != null) {
            try {
                notifyListener(timePicker.getCurrentHour().intValue(), timePicker.getCurrentMinute().intValue() * 10, timePicker.is24HourView() ? TimeFormat.IN_24_HRS_FORMAT : TimeFormat.IN_12_HRS_FORMAT, string);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void notifyListener(int i, int i2, TimeFormat timeFormat, String str) throws ParseException {
        if (DateAndTimeUtils.compareTime(DateAndTimeUtils.getCalendar(i, i2), DateAndTimeUtils.getCurrentCalendar()) > 0) {
            this.mTimeSetListener.onTimeSet(i, i2, timeFormat, str);
            dismiss();
            return;
        }
        Toast.makeText(getActivity(), getResources().getString(R.string.timer_alert_selectedTimeCanNotBelessThanCurrentTime), 1).show();
    }

    private void updateView(boolean z, TimePicker timePicker, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        timePicker.setIs24HourView(Boolean.valueOf(z));
        updateView(appCompatTextView, appCompatTextView2);
    }

    private void setInterval(TimePicker timePicker) {
        try {
            NumberPicker numberPicker = (NumberPicker) timePicker.findViewById(Resources.getSystem().getIdentifier("minute", StompHeader.f739ID, "android"));
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(5);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 60; i += 10) {
                arrayList.add(String.format("%02d", new Object[]{Integer.valueOf(i)}));
            }
            numberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
        } catch (Exception e) {
            Logger.m47e(TAG, "Exception: " + e);
        }
    }

    private void updateView(AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        appCompatTextView.setBackgroundColor(getResources().getColor(R.color.white));
        appCompatTextView2.setBackgroundColor(getResources().getColor(R.color.color_disabled_views_light));
        appCompatTextView.setTextColor(getResources().getColor(R.color.dark_red));
        appCompatTextView2.setTextColor(getResources().getColor(R.color.black));
    }

    public void onResume() {
        super.onResume();
    }

    public void setTimeSetListener(TimeSetListener timeSetListener) {
        this.mTimeSetListener = timeSetListener;
    }
}
