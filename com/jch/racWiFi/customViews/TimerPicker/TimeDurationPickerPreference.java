package com.jch.racWiFi.customViews.TimerPicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jch_hitachi.aircloudglobal.R;

public class TimeDurationPickerPreference extends DialogPreference {
    public static final String PLACEHOLDER_HOURS_MINUTES_SECONDS = "${h:mm:ss}";
    public static final String PLACEHOLDER_MINUTES_SECONDS = "${m:ss}";
    public static final String PLACEHOLDER_SECONDS = "${s}";
    private long duration;
    private TimeDurationPicker picker;
    private String summaryTemplate;

    /* access modifiers changed from: protected */
    public TimeDurationPicker initPicker(TimeDurationPicker timeDurationPicker) {
        return timeDurationPicker;
    }

    public TimeDurationPickerPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimeDurationPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.duration = 0;
        this.picker = null;
        setPositiveButtonText(17039370);
        setNegativeButtonText(17039360);
    }

    public void setDuration(long j) {
        this.duration = j;
        persistLong(j);
        notifyDependencyChange(shouldDisableDependents());
        notifyChanged();
    }

    public long getDuration() {
        return this.duration;
    }

    public TimeDurationPicker getTimeDurationPicker() {
        return this.picker;
    }

    private void updateDescription() {
        if (this.summaryTemplate == null) {
            this.summaryTemplate = getSummary().toString();
        }
        setSummary(this.summaryTemplate.replace(PLACEHOLDER_HOURS_MINUTES_SECONDS, TimeDurationUtil.formatHoursMinutesSeconds(this.duration)).replace(PLACEHOLDER_MINUTES_SECONDS, TimeDurationUtil.formatMinutesSeconds(this.duration).replace(PLACEHOLDER_SECONDS, TimeDurationUtil.formatSeconds(this.duration))));
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder.setTitle((CharSequence) null).setIcon((Drawable) null));
    }

    /* access modifiers changed from: protected */
    public View onCreateDialogView() {
        TimeDurationPicker initPicker = initPicker((TimeDurationPicker) LayoutInflater.from(getContext()).inflate(R.layout.time_duration_picker_dialog, (ViewGroup) null));
        this.picker = initPicker;
        return initPicker;
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.picker.setDuration(this.duration);
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (z) {
            long duration2 = this.picker.getDuration();
            if (callChangeListener(Long.valueOf(duration2))) {
                setDuration(duration2);
                updateDescription();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Long.valueOf((long) typedArray.getInt(i, 0));
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        long j;
        if (z) {
            j = getPersistedLong(0);
        } else {
            j = Long.parseLong(obj.toString());
        }
        setDuration(j);
        updateDescription();
    }
}
