package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.jch.racWiFi.C1662R2;
import java.util.ArrayList;
import java.util.List;

public class ColorTemplate {
    public static final int[] COLORFUL_COLORS = {Color.rgb(C1662R2.attr.buttonCompat, 37, 82), Color.rgb(255, 102, 0), Color.rgb(C1662R2.attr.chipIcon, C1662R2.attr.buttonStyleSmall, 0), Color.rgb(106, C1662R2.attr.behavior_autoHide, 31), Color.rgb(C1662R2.attr.boxCornerRadiusTopEnd, 100, 53)};
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] JOYFUL_COLORS = {Color.rgb(C1662R2.attr.carousel_backwardTransition, 80, C1662R2.attr.backgroundTintMode), Color.rgb(254, C1662R2.attr.barrierMargin, 7), Color.rgb(254, C1662R2.attr.chipIconSize, 120), Color.rgb(106, C1662R2.attr.borderWidth, C1662R2.attr.backgroundOverlayColorAlpha), Color.rgb(53, C1662R2.attr.buttonGravity, C1662R2.attr.cardBackgroundColor)};
    public static final int[] LIBERTY_COLORS = {Color.rgb(C1662R2.attr.cameraTilt, C1662R2.attr.chipIconTint, C1662R2.attr.chipIconEnabled), Color.rgb(C1662R2.attr.barrierDirection, C1662R2.attr.cardForegroundColor, C1662R2.attr.cardForegroundColor), Color.rgb(136, C1662R2.attr.boxCornerRadiusTopStart, C1662R2.attr.bubbleTextColor), Color.rgb(118, C1662R2.attr.boxBackgroundColor, C1662R2.attr.boxBackgroundMode), Color.rgb(42, 109, C1662R2.attr.backgroundInsetBottom)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb(C1662R2.attr.barrierMargin, C1662R2.attr.borderRound, 124), Color.rgb(C1662R2.attr.carousel_backwardTransition, C1662R2.attr.boxStrokeWidthFocused, C1662R2.attr.borderAlpha), Color.rgb(C1662R2.attr.buttonBarPositiveButtonStyle, C1662R2.attr.backgroundOverlayColorAlpha, C1662R2.attr.backgroundOverlayColorAlpha), Color.rgb(C1662R2.attr.boxCornerRadiusTopEnd, 48, 80)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(C1662R2.attr.buttonBarStyle, 255, C1662R2.attr.badgeGravity), Color.rgb(255, C1662R2.attr.chipIconSize, C1662R2.attr.badgeGravity), Color.rgb(255, C1662R2.attr.cameraZoom, C1662R2.attr.badgeGravity), Color.rgb(C1662R2.attr.badgeGravity, 234, 255), Color.rgb(255, C1662R2.attr.badgeGravity, C1662R2.attr.behavior_overlapTop)};

    public static int colorWithAlpha(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((i2 & 255) << 24);
    }

    public static int rgb(String str) {
        int parseLong = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((parseLong >> 16) & 255, (parseLong >> 8) & 255, (parseLong >> 0) & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, C1662R2.attr.boxStrokeColor, C1662R2.attr.checkMarkTint);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int color : iArr) {
            arrayList.add(Integer.valueOf(resources.getColor(color)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }
}
