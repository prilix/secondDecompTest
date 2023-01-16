package com.jch.racWiFi.iduManagement.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MondayWeeklyCanvas extends ConstraintLayout {
    private ConstraintLayout constraintLayout;
    private Float floatHour;
    private Float floatMin;
    private Calendar fromCalender;
    private float fromTime;
    private Paint paint = new Paint();
    private Path path = new Path();
    private SimpleDateFormat simpleDateFormatHour;
    private SimpleDateFormat simpleDateFormatMin;
    private String stringHour;
    private String stringMin;
    private Calendar toCalender;
    private String toDay;
    private float toTime;

    public MondayWeeklyCanvas(Context context) {
        super(context);
    }

    public MondayWeeklyCanvas(Context context, float f, ConstraintLayout constraintLayout2, Calendar calendar) {
        super(context);
        this.toTime = f;
        this.constraintLayout = constraintLayout2;
        this.fromCalender = calendar;
        this.toDay = new SimpleDateFormat("EEE").format(this.fromCalender);
    }

    public MondayWeeklyCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MondayWeeklyCanvas(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = ((float) getHeight()) / 23.0f;
        float f = height / 60.0f;
        convertFromDateToMinHourFloat(f);
        convertToDateToMinHourFloat(f);
        drawMonViewV2(canvas, (float) getWidth(), height);
    }

    public void getTimeInput(Calendar calendar, Calendar calendar2, ConstraintLayout constraintLayout2) {
        this.toCalender = calendar2;
        this.constraintLayout = constraintLayout2;
        this.fromCalender = calendar;
    }

    private void convertFromDateToMinHourFloat(float f) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        this.simpleDateFormatHour = simpleDateFormat;
        String format = simpleDateFormat.format(this.fromCalender.getTime());
        this.stringHour = format;
        this.floatHour = Float.valueOf(Float.parseFloat(format));
        this.simpleDateFormatMin = new SimpleDateFormat("0.mm");
        String format2 = this.simpleDateFormatMin.format(this.fromCalender.getTime());
        this.stringMin = format2;
        Float valueOf = Float.valueOf(Float.parseFloat(format2));
        this.floatMin = valueOf;
        this.fromTime = Float.valueOf(this.floatHour.floatValue() + Float.valueOf((valueOf.floatValue() * f) / 0.6f).floatValue()).floatValue();
    }

    private void convertToDateToMinHourFloat(float f) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        this.simpleDateFormatHour = simpleDateFormat;
        String format = simpleDateFormat.format(this.toCalender.getTime());
        this.stringHour = format;
        this.floatHour = Float.valueOf(Float.parseFloat(format));
        this.simpleDateFormatMin = new SimpleDateFormat("0.mm");
        String format2 = this.simpleDateFormatMin.format(this.toCalender.getTime());
        this.stringMin = format2;
        Float valueOf = Float.valueOf(Float.parseFloat(format2));
        this.floatMin = valueOf;
        this.toTime = Float.valueOf(this.floatHour.floatValue() + Float.valueOf((valueOf.floatValue() * f) / 0.6f).floatValue()).floatValue();
    }

    private void drawMonViewV2(Canvas canvas, float f, float f2) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(-12152331);
        this.path.reset();
        this.path.moveTo(0.0f, this.fromTime * f2);
        this.path.lineTo(f, this.fromTime * f2);
        this.path.lineTo(f, this.toTime * f2);
        this.path.lineTo(0.0f, this.toTime * f2);
        this.path.close();
        canvas.drawPath(this.path, this.paint);
        this.constraintLayout.setY(this.fromTime * f2);
        this.paint.setColor(-1);
        Canvas canvas2 = canvas;
        canvas2.drawLine(0.0f, this.fromTime * f2, 0.0f, this.toTime * f2, this.paint);
        canvas2.drawLine(f, this.fromTime * f2, f, this.toTime * f2, this.paint);
    }
}
