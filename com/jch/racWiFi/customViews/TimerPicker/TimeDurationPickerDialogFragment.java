package com.jch.racWiFi.customViews.TimerPicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import com.jch.racWiFi.customViews.TimerPicker.TimeDurationPickerDialog;

public abstract class TimeDurationPickerDialogFragment extends DialogFragment implements TimeDurationPickerDialog.OnDurationSetListener {
    /* access modifiers changed from: protected */
    public long getInitialDuration() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int setTimeUnits() {
        return 0;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new TimeDurationPickerDialog(getActivity(), this, getInitialDuration(), setTimeUnits());
    }
}
