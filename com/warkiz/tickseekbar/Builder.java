package com.warkiz.tickseekbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class Builder {
    public boolean clearPadding = false;
    final Context context;
    float max = 100.0f;
    float min = 0.0f;
    boolean onlyThumbDraggable = false;
    float progress = 0.0f;
    boolean progressValueFloat = false;
    boolean r2l = false;
    boolean seekSmoothly = false;
    int showTickMarksType = 0;
    boolean thumbAutoAdjust = true;
    int thumbColor = Color.parseColor("#FF4081");
    ColorStateList thumbColorStateList = null;
    Drawable thumbDrawable = null;
    int thumbSize;
    int thumbTextColor = Color.parseColor("#FF4081");
    int thumbTextShow = 0;
    int tickCount = 0;
    int tickMarksColor = Color.parseColor("#FF4081");
    ColorStateList tickMarksColorStateList = null;
    Drawable tickMarksDrawable = null;
    boolean tickMarksEndsHide = false;
    int tickMarksSize;
    boolean tickMarksSweptHide = false;
    int tickTextsColor = Color.parseColor("#FF4081");
    ColorStateList tickTextsColorStateList = null;
    String[] tickTextsCustomArray = null;
    int tickTextsShow = 0;
    int tickTextsSize;
    Typeface tickTextsTypeFace = Typeface.DEFAULT;
    int trackBackgroundColor = Color.parseColor("#D7D7D7");
    int trackBackgroundSize;
    int trackProgressColor = Color.parseColor("#FF4081");
    int trackProgressSize;
    boolean trackRoundedCorners = false;
    boolean userSeekable = true;

    Builder(Context context2) {
        this.context = context2;
        this.trackBackgroundSize = SizeUtils.dp2px(context2, 2.0f);
        this.trackProgressSize = SizeUtils.dp2px(context2, 2.0f);
        this.tickMarksSize = SizeUtils.dp2px(context2, 10.0f);
        this.tickTextsSize = SizeUtils.sp2px(context2, 13.0f);
        this.thumbSize = SizeUtils.dp2px(context2, 14.0f);
    }

    public TickSeekBar build() {
        return new TickSeekBar(this);
    }

    public Builder max(float f) {
        this.max = f;
        return this;
    }

    public Builder min(float f) {
        this.min = f;
        return this;
    }

    public Builder progress(float f) {
        this.progress = f;
        return this;
    }

    public Builder progressValueFloat(boolean z) {
        this.progressValueFloat = z;
        return this;
    }

    public Builder seekSmoothly(boolean z) {
        this.seekSmoothly = z;
        return this;
    }

    public Builder r2l(boolean z) {
        this.r2l = z;
        return this;
    }

    public Builder clearPadding(boolean z) {
        this.clearPadding = z;
        return this;
    }

    public Builder userSeekable(boolean z) {
        this.userSeekable = z;
        return this;
    }

    public Builder onlyThumbDraggable(boolean z) {
        this.onlyThumbDraggable = z;
        return this;
    }

    public Builder trackBackgroundSize(int i) {
        this.trackBackgroundSize = SizeUtils.dp2px(this.context, (float) i);
        return this;
    }

    public Builder trackBackgroundColor(int i) {
        this.trackBackgroundColor = i;
        return this;
    }

    public Builder trackProgressSize(int i) {
        this.trackProgressSize = SizeUtils.dp2px(this.context, (float) i);
        return this;
    }

    public Builder trackProgressColor(int i) {
        this.trackProgressColor = i;
        return this;
    }

    public Builder trackRoundedCorners(boolean z) {
        this.trackRoundedCorners = z;
        return this;
    }

    public Builder thumbTextColor(int i) {
        this.thumbTextColor = i;
        return this;
    }

    public Builder thumbTextPosition(int i) {
        this.thumbTextShow = i;
        return this;
    }

    public Builder thumbColor(int i) {
        this.thumbColor = i;
        return this;
    }

    public Builder thumbAutoAdjust(boolean z) {
        this.thumbAutoAdjust = z;
        return this;
    }

    public Builder thumbColorStateList(ColorStateList colorStateList) {
        this.thumbColorStateList = colorStateList;
        return this;
    }

    public Builder thumbSize(int i) {
        this.thumbSize = SizeUtils.dp2px(this.context, (float) i);
        return this;
    }

    public Builder thumbDrawable(Drawable drawable) {
        this.thumbDrawable = drawable;
        return this;
    }

    public Builder thumbDrawable(StateListDrawable stateListDrawable) {
        this.thumbDrawable = stateListDrawable;
        return this;
    }

    public Builder thumbDrawable(int i) {
        this.thumbDrawable = this.context.getResources().getDrawable(i);
        return this;
    }

    public Builder showTickTextsPosition(int i) {
        this.tickTextsShow = i;
        return this;
    }

    public Builder tickTextsColor(int i) {
        this.tickTextsColor = i;
        return this;
    }

    public Builder tickTextsColorStateList(ColorStateList colorStateList) {
        this.tickTextsColorStateList = colorStateList;
        return this;
    }

    public Builder tickTextsSize(int i) {
        this.tickTextsSize = SizeUtils.sp2px(this.context, (float) i);
        return this;
    }

    public Builder tickTextsArray(String[] strArr) {
        this.tickTextsCustomArray = strArr;
        return this;
    }

    public Builder tickTextsArray(int i) {
        this.tickTextsCustomArray = this.context.getResources().getStringArray(i);
        return this;
    }

    public Builder tickTextsTypeFace(Typeface typeface) {
        this.tickTextsTypeFace = typeface;
        return this;
    }

    public Builder tickCount(int i) {
        this.tickCount = i;
        return this;
    }

    public Builder showTickMarksType(int i) {
        this.showTickMarksType = i;
        return this;
    }

    public Builder tickMarksColor(int i) {
        this.tickMarksColor = i;
        return this;
    }

    public Builder tickMarksColor(ColorStateList colorStateList) {
        this.tickMarksColorStateList = colorStateList;
        return this;
    }

    public Builder tickMarksSize(int i) {
        this.tickMarksSize = SizeUtils.dp2px(this.context, (float) i);
        return this;
    }

    public Builder tickMarksDrawable(Drawable drawable) {
        this.tickMarksDrawable = drawable;
        return this;
    }

    public Builder tickMarksDrawable(StateListDrawable stateListDrawable) {
        this.tickMarksDrawable = stateListDrawable;
        return this;
    }

    public Builder tickMarksEndsHide(boolean z) {
        this.tickMarksEndsHide = z;
        return this;
    }

    public Builder tickMarksSweptHide(boolean z) {
        this.tickMarksSweptHide = z;
        return this;
    }
}
