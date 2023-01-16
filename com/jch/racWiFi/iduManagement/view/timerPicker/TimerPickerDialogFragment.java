package com.jch.racWiFi.iduManagement.view.timerPicker;

import android.content.Context;
import android.os.Build;
import com.jch.racWiFi.customViews.TimerPicker.TimeDurationPicker;
import com.jch.racWiFi.customViews.TimerPicker.TimeDurationPickerDialogFragment;
import com.jch.racWiFi.customViews.TimerPicker.TimeDurationUtil;

public class TimerPickerDialogFragment extends TimeDurationPickerDialogFragment {
    public static final String DURATION = "DURATION";
    int checkOnOrOffTimeDuration;
    onChangeTimeDuration onChangeTimeDuration;

    public interface onChangeTimeDuration {
        void timeDuration(String str, int i);
    }

    /* access modifiers changed from: protected */
    public long getInitialDuration() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int setTimeUnits() {
        return 1;
    }

    public void setOnChangeTimeDuration(onChangeTimeDuration onchangetimeduration) {
        this.onChangeTimeDuration = onchangetimeduration;
    }

    public void setCheckOnOrOffTimeDuration(int i) {
        this.checkOnOrOffTimeDuration = i;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDurationSet(TimeDurationPicker timeDurationPicker, long j) {
        String formatHoursMinutesSeconds = TimeDurationUtil.formatHoursMinutesSeconds(j);
        if (Build.VERSION.SDK_INT >= 23 && this.onChangeTimeDuration != null) {
            this.onChangeTimeDuration.timeDuration(formatHoursMinutesSeconds.substring(0, formatHoursMinutesSeconds.length() - 3), this.checkOnOrOffTimeDuration);
        }
    }
}
