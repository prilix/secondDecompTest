package com.jch.racWiFi.iduManagement.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModels;
import com.jch_hitachi.aircloudglobal.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSliceViewHolder {
    private WeeklyTimerUIDataModel mWeeklyTimerUIDataModel = new WeeklyTimerUIDataModel();
    private ImageView modeImage;
    private int setImageToMode;
    private TextView temperature;
    private final View view;

    public WeeklyTimerUIDataModel getWeeklyTimerUIDataModel() {
        return this.mWeeklyTimerUIDataModel;
    }

    public void setModeImage(int i) {
        this.setImageToMode = i;
        this.modeImage.setBackgroundResource(i);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        View view2 = this.view;
        if (view2 != null) {
            view2.setOnClickListener(onClickListener);
        }
    }

    public TimeSliceViewHolder(Context context, WeeklyTimerUIDataModel weeklyTimerUIDataModel, WeeklyCanvas weeklyCanvas) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.time_slice_view, weeklyCanvas, false);
        this.view = inflate;
        this.temperature = (TextView) inflate.findViewById(R.id.text_view_temp_sun);
        this.modeImage = (ImageView) inflate.findViewById(R.id.image_view_mode_sun);
        this.mWeeklyTimerUIDataModel.copy(weeklyTimerUIDataModel);
        this.mWeeklyTimerUIDataModel.setConstraintLayout((ConstraintLayout) inflate);
    }

    public View getView() {
        return this.view;
    }

    public void setTemperatureTextView(String str) {
        this.temperature.setText(str);
    }

    public void initDisplayMetricsVariables(float f) {
        convertFromDateToMinHourFloat(f);
        convertToDateToMinHourFloat(f);
    }

    public void convertFromDateToMinHourFloat(float f) {
        this.mWeeklyTimerUIDataModel.setSimpleDateFormatHour(new SimpleDateFormat("HH"));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel.setStringHour(weeklyTimerUIDataModel.getSimpleDateFormatHour().format(this.mWeeklyTimerUIDataModel.getFromCalender().getTime()));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel2 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel2.setFloatHour(Float.valueOf(Float.parseFloat(weeklyTimerUIDataModel2.getStringHour())));
        this.mWeeklyTimerUIDataModel.setSimpleDateFormatMin(new SimpleDateFormat("0.mm"));
        Date time = this.mWeeklyTimerUIDataModel.getFromCalender().getTime();
        WeeklyTimerUIDataModel weeklyTimerUIDataModel3 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel3.setStringMin(weeklyTimerUIDataModel3.getSimpleDateFormatMin().format(time));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel4 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel4.setFloatMin(Float.valueOf(Float.parseFloat(weeklyTimerUIDataModel4.getStringMin())));
        this.mWeeklyTimerUIDataModel.setFromTime(this.mWeeklyTimerUIDataModel.getFloatHour().floatValue() + (this.mWeeklyTimerUIDataModel.getFloatMin().floatValue() / 0.6f));
    }

    public void convertToDateToMinHourFloat(float f) {
        this.mWeeklyTimerUIDataModel.setSimpleDateFormatHour(new SimpleDateFormat("HH"));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel.setStringHour(weeklyTimerUIDataModel.getSimpleDateFormatHour().format(this.mWeeklyTimerUIDataModel.getToCalender().getTime()));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel2 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel2.setFloatHour(Float.valueOf(Float.parseFloat(weeklyTimerUIDataModel2.getStringHour())));
        this.mWeeklyTimerUIDataModel.setSimpleDateFormatMin(new SimpleDateFormat("0.mm"));
        Date time = this.mWeeklyTimerUIDataModel.getToCalender().getTime();
        WeeklyTimerUIDataModel weeklyTimerUIDataModel3 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel3.setStringMin(weeklyTimerUIDataModel3.getSimpleDateFormatMin().format(time));
        WeeklyTimerUIDataModel weeklyTimerUIDataModel4 = this.mWeeklyTimerUIDataModel;
        weeklyTimerUIDataModel4.setFloatMin(Float.valueOf(Float.parseFloat(weeklyTimerUIDataModel4.getStringMin())));
        this.mWeeklyTimerUIDataModel.setToTime(this.mWeeklyTimerUIDataModel.getFloatHour().floatValue() + (this.mWeeklyTimerUIDataModel.getFloatMin().floatValue() / 0.6f));
    }

    public void drawTimeSliceView(Canvas canvas, float f, float f2, WeeklyTimerModels.WeeklyTimerData weeklyTimerData) {
        OperationMode operationMode = OperationMode.COOLING;
        this.mWeeklyTimerUIDataModel.getPaint().setStyle(Paint.Style.FILL);
        if (weeklyTimerData != null) {
            operationMode = OperationMode.valueOf(weeklyTimerData.mode);
        }
        int i = C20721.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode.ordinal()];
        int i2 = -12152331;
        if (i != 1 && i == 2) {
            i2 = -22784;
        }
        this.mWeeklyTimerUIDataModel.getPaint().setColor(i2);
        this.mWeeklyTimerUIDataModel.getPath().reset();
        this.mWeeklyTimerUIDataModel.getPath().moveTo(0.0f, this.mWeeklyTimerUIDataModel.getFromTime() * f2);
        this.mWeeklyTimerUIDataModel.getPath().lineTo(f, this.mWeeklyTimerUIDataModel.getFromTime() * f2);
        this.mWeeklyTimerUIDataModel.getPath().lineTo(f, this.mWeeklyTimerUIDataModel.getToTime() * f2);
        this.mWeeklyTimerUIDataModel.getPath().lineTo(0.0f, this.mWeeklyTimerUIDataModel.getToTime() * f2);
        this.mWeeklyTimerUIDataModel.getPath().close();
        canvas.drawPath(this.mWeeklyTimerUIDataModel.getPath(), this.mWeeklyTimerUIDataModel.getPaint());
        this.mWeeklyTimerUIDataModel.getConstraintLayout().setY(this.mWeeklyTimerUIDataModel.getFromTime() * f2);
        this.mWeeklyTimerUIDataModel.getPaint().setColor(-1);
        canvas.drawLine(0.0f, this.mWeeklyTimerUIDataModel.getFromTime() * f2, 0.0f, this.mWeeklyTimerUIDataModel.getToTime() * f2, this.mWeeklyTimerUIDataModel.getPaint());
        canvas.drawLine(f, this.mWeeklyTimerUIDataModel.getFromTime() * f2, f, this.mWeeklyTimerUIDataModel.getToTime() * f2, this.mWeeklyTimerUIDataModel.getPaint());
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.canvas.TimeSliceViewHolder$1 */
    static /* synthetic */ class C20721 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.canvas.TimeSliceViewHolder.C20721.<clinit>():void");
        }
    }
}
