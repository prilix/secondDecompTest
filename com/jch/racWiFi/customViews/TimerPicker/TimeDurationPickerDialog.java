package com.jch.racWiFi.customViews.TimerPicker;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.jch_hitachi.aircloudglobal.R;

public class TimeDurationPickerDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private static final String DURATION = "duration";
    private final TimeDurationPicker durationInputView;
    private final OnDurationSetListener durationSetListener;

    public interface OnDurationSetListener {
        void onDurationSet(TimeDurationPicker timeDurationPicker, long j);
    }

    public TimeDurationPickerDialog(Context context, OnDurationSetListener onDurationSetListener, long j) {
        super(context);
        this.durationSetListener = onDurationSetListener;
        View inflate = LayoutInflater.from(context).inflate(R.layout.time_duration_picker_dialog, (ViewGroup) null);
        setView(inflate);
        setButton(-1, (CharSequence) context.getString(17039370), (DialogInterface.OnClickListener) this);
        setButton(-2, (CharSequence) context.getString(17039360), (DialogInterface.OnClickListener) this);
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                TimeDurationPickerDialog.this.getButton(-1).setTextColor(TimeDurationPickerDialog.this.getContext().getResources().getColor(R.color.colorRed));
                TimeDurationPickerDialog.this.getButton(-2).setTextColor(TimeDurationPickerDialog.this.getContext().getResources().getColor(R.color.colorRed));
            }
        });
        TimeDurationPicker timeDurationPicker = (TimeDurationPicker) inflate;
        this.durationInputView = timeDurationPicker;
        timeDurationPicker.setDuration(j);
    }

    public TimeDurationPickerDialog(Context context, OnDurationSetListener onDurationSetListener, long j, int i) {
        this(context, onDurationSetListener, j);
        this.durationInputView.setTimeUnits(i);
    }

    public TimeDurationPicker getDurationInput() {
        return this.durationInputView;
    }

    public void setDuration(long j) {
        this.durationInputView.setDuration(j);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        OnDurationSetListener onDurationSetListener;
        if (i == -2) {
            cancel();
        } else if (i == -1 && (onDurationSetListener = this.durationSetListener) != null) {
            TimeDurationPicker timeDurationPicker = this.durationInputView;
            onDurationSetListener.onDurationSet(timeDurationPicker, timeDurationPicker.getDuration());
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putLong("duration", this.durationInputView.getDuration());
        return onSaveInstanceState;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.durationInputView.setDuration(bundle.getLong("duration"));
    }
}
