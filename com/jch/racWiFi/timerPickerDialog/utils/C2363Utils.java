package com.jch.racWiFi.timerPickerDialog.utils;

import android.view.View;
import com.jch.racWiFi.timerPickerDialog.data.WheelCalendar;

/* renamed from: com.jch.racWiFi.timerPickerDialog.utils.Utils */
public class C2363Utils {
    public static boolean isTimeEquals(WheelCalendar wheelCalendar, int... iArr) {
        int length = iArr.length;
        if (length != 1) {
            if (length != 2) {
                if (length != 3) {
                    return length == 4 && wheelCalendar.year == iArr[0] && wheelCalendar.month == iArr[1] && wheelCalendar.day == iArr[2] && wheelCalendar.hour == iArr[3];
                }
                if (wheelCalendar.year == iArr[0] && wheelCalendar.month == iArr[1] && wheelCalendar.day == iArr[2]) {
                    return true;
                }
                return false;
            } else if (wheelCalendar.year == iArr[0] && wheelCalendar.month == iArr[1]) {
                return true;
            } else {
                return false;
            }
        } else if (wheelCalendar.year == iArr[0]) {
            return true;
        } else {
            return false;
        }
    }

    public static void hideViews(View... viewArr) {
        for (View visibility : viewArr) {
            visibility.setVisibility(8);
        }
    }
}
