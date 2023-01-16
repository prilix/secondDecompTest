package com.jch.racWiFi.iduManagement.view.canvas;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeeklyTimerUIDataModel {
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

    public float getFromTime() {
        return this.fromTime;
    }

    public float getToTime() {
        return this.toTime;
    }

    public Calendar getFromCalender() {
        return this.fromCalender;
    }

    public Calendar getToCalender() {
        return this.toCalender;
    }

    public ConstraintLayout getConstraintLayout() {
        return this.constraintLayout;
    }

    public String getToDay() {
        return this.toDay;
    }

    public String getStringHour() {
        return this.stringHour;
    }

    public String getStringMin() {
        return this.stringMin;
    }

    public SimpleDateFormat getSimpleDateFormatHour() {
        return this.simpleDateFormatHour;
    }

    public SimpleDateFormat getSimpleDateFormatMin() {
        return this.simpleDateFormatMin;
    }

    public Float getFloatHour() {
        return this.floatHour;
    }

    public Float getFloatMin() {
        return this.floatMin;
    }

    public Path getPath() {
        return this.path;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public void setFromTime(float f) {
        this.fromTime = f;
    }

    public void setToTime(float f) {
        this.toTime = f;
    }

    public void setFromCalender(Calendar calendar) {
        this.fromCalender = calendar;
    }

    public void setToCalender(Calendar calendar) {
        this.toCalender = calendar;
    }

    public void setConstraintLayout(ConstraintLayout constraintLayout2) {
        this.constraintLayout = constraintLayout2;
    }

    public void setToDay(String str) {
        this.toDay = str;
    }

    public void setStringHour(String str) {
        this.stringHour = str;
    }

    public void setStringMin(String str) {
        this.stringMin = str;
    }

    public void setSimpleDateFormatHour(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormatHour = simpleDateFormat;
    }

    public void setSimpleDateFormatMin(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormatMin = simpleDateFormat;
    }

    public void setFloatHour(Float f) {
        this.floatHour = f;
    }

    public void setFloatMin(Float f) {
        this.floatMin = f;
    }

    public void setPath(Path path2) {
        this.path = path2;
    }

    public void setPaint(Paint paint2) {
        this.paint = paint2;
    }

    public void copy(WeeklyTimerUIDataModel weeklyTimerUIDataModel) {
        this.fromTime = weeklyTimerUIDataModel.fromTime;
        this.toTime = weeklyTimerUIDataModel.toTime;
        this.fromCalender = weeklyTimerUIDataModel.fromCalender;
        this.toCalender = weeklyTimerUIDataModel.toCalender;
        this.constraintLayout = weeklyTimerUIDataModel.constraintLayout;
        this.toDay = weeklyTimerUIDataModel.toDay;
        this.stringHour = weeklyTimerUIDataModel.stringHour;
        this.stringMin = weeklyTimerUIDataModel.stringMin;
        this.simpleDateFormatHour = weeklyTimerUIDataModel.simpleDateFormatHour;
        this.simpleDateFormatMin = weeklyTimerUIDataModel.simpleDateFormatMin;
        this.floatHour = weeklyTimerUIDataModel.floatHour;
        this.floatMin = weeklyTimerUIDataModel.floatMin;
        this.path = weeklyTimerUIDataModel.path;
        this.paint = weeklyTimerUIDataModel.paint;
    }
}
