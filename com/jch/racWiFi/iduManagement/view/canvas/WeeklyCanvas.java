package com.jch.racWiFi.iduManagement.view.canvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModels;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeeklyCanvas extends ConstraintLayout {
    List<TimeSliceViewHolder> timeSliceViewHolderList = new ArrayList();
    private WeeklyTimerModels.WeeklyTimerData weeklyTimerData = new WeeklyTimerModels.WeeklyTimerData();

    public void addTimeSlice(WeeklyTimerModels.WeeklyTimerData weeklyTimerData2, View.OnClickListener onClickListener) {
        this.weeklyTimerData.copy(weeklyTimerData2);
        Calendar convertHourMinuteStringTOCalenderObject = DateAndTimeUtils.convertHourMinuteStringTOCalenderObject(DateAndTimeUtils.getHoursFromTimeString(weeklyTimerData2.startsAt) + ":" + DateAndTimeUtils.getMinutesFromTimeString(weeklyTimerData2.startsAt));
        addTimeSlice(convertHourMinuteStringTOCalenderObject, DateAndTimeUtils.convertHourMinuteStringTOCalenderObject(DateAndTimeUtils.getHoursFromTimeString(weeklyTimerData2.endsAt) + ":" + DateAndTimeUtils.getMinutesFromTimeString(weeklyTimerData2.endsAt)), weeklyTimerData2, onClickListener);
    }

    public void addTimeSlice(Calendar calendar, Calendar calendar2, WeeklyTimerModels.WeeklyTimerData weeklyTimerData2, View.OnClickListener onClickListener) {
        WeeklyTimerUIDataModel weeklyTimerUIDataModel = new WeeklyTimerUIDataModel();
        weeklyTimerUIDataModel.setFromCalender(calendar);
        weeklyTimerUIDataModel.setToCalender(calendar2);
        TimeSliceViewHolder timeSliceViewHolder = new TimeSliceViewHolder(getContext(), weeklyTimerUIDataModel, this);
        timeSliceViewHolder.setOnClickListener(onClickListener);
        timeSliceViewHolder.setTemperatureTextView(String.format("%.1f", new Object[]{Double.valueOf(weeklyTimerData2.temperature)}));
        timeSliceViewHolder.setModeImage(OperationMode.valueOf(weeklyTimerData2.mode).getDrawableResSmall());
        this.timeSliceViewHolderList.add(timeSliceViewHolder);
        addView((ConstraintLayout) timeSliceViewHolder.getView());
        drawSlice(timeSliceViewHolder);
    }

    private void drawSlice(TimeSliceViewHolder timeSliceViewHolder) {
        getWidth();
        float height = ((float) getHeight()) / 24.0f;
        timeSliceViewHolder.initDisplayMetricsVariables(height / 60.0f);
        OperationMode operationMode = OperationMode.COOLING;
        WeeklyTimerModels.WeeklyTimerData weeklyTimerData2 = this.weeklyTimerData;
        if (weeklyTimerData2 != null) {
            operationMode = OperationMode.valueOf(weeklyTimerData2.mode.toUpperCase());
        }
        int i = C20731.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode.ordinal()];
        int i2 = -7681040;
        if (i != 1 && i == 2) {
            i2 = -22784;
        }
        timeSliceViewHolder.getView().setBackgroundColor(i2);
        timeSliceViewHolder.getView().setY(timeSliceViewHolder.getWeeklyTimerUIDataModel().getFromTime() * height);
        timeSliceViewHolder.getView().setLayoutParams(new ConstraintLayout.LayoutParams(-1, (int) ((timeSliceViewHolder.getWeeklyTimerUIDataModel().getToTime() * height) - (timeSliceViewHolder.getWeeklyTimerUIDataModel().getFromTime() * height))));
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.canvas.WeeklyCanvas$1 */
    static /* synthetic */ class C20731 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.canvas.WeeklyCanvas.C20731.<clinit>():void");
        }
    }

    public void clear() {
        for (TimeSliceViewHolder onClickListener : this.timeSliceViewHolderList) {
            onClickListener.setOnClickListener((View.OnClickListener) null);
            removeAllViews();
        }
    }

    public void addTimeSlice(Calendar calendar, Calendar calendar2, double d) {
        WeeklyTimerUIDataModel weeklyTimerUIDataModel = new WeeklyTimerUIDataModel();
        weeklyTimerUIDataModel.setFromCalender(calendar);
        weeklyTimerUIDataModel.setToCalender(calendar2);
        TimeSliceViewHolder timeSliceViewHolder = new TimeSliceViewHolder(getContext(), weeklyTimerUIDataModel, this);
        timeSliceViewHolder.setTemperatureTextView(String.valueOf(d));
        this.timeSliceViewHolderList.add(timeSliceViewHolder);
        addView((ConstraintLayout) timeSliceViewHolder.getView());
        invalidate();
    }

    public WeeklyCanvas(Context context) {
        super(context);
    }

    public WeeklyCanvas(Context context, float f, ConstraintLayout constraintLayout, Calendar calendar) {
        super(context);
    }

    public WeeklyCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WeeklyCanvas(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
