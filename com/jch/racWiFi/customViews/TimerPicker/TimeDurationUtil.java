package com.jch.racWiFi.customViews.TimerPicker;

public class TimeDurationUtil {
    public static final int MILLIS_PER_HOUR = 3600000;
    public static final int MILLIS_PER_MINUTE = 60000;
    public static final int MILLIS_PER_SECOND = 1000;

    public static long durationOf(int i, int i2, int i3) {
        return (long) ((i * MILLIS_PER_HOUR) + (i2 * 60000) + (i3 * 1000));
    }

    public static int hoursOf(long j) {
        return ((int) j) / MILLIS_PER_HOUR;
    }

    public static int minutesOf(long j) {
        return ((int) j) / 60000;
    }

    public static int minutesInHourOf(long j) {
        return ((int) (j - ((long) (hoursOf(j) * MILLIS_PER_HOUR)))) / 60000;
    }

    public static int secondsOf(long j) {
        return ((int) j) / 1000;
    }

    public static int secondsInMinuteOf(long j) {
        return ((int) ((j - ((long) (hoursOf(j) * MILLIS_PER_HOUR))) - ((long) (minutesInHourOf(j) * 60000)))) / 1000;
    }

    public static String formatHoursMinutesSeconds(long j) {
        return String.format("%d:%02d:%02d", new Object[]{Integer.valueOf(hoursOf(j)), Integer.valueOf(minutesInHourOf(j)), Integer.valueOf(secondsInMinuteOf(j))});
    }

    public static String formatMinutesSeconds(long j) {
        return String.format("%d:%02d", new Object[]{Integer.valueOf(minutesOf(j)), Integer.valueOf(secondsInMinuteOf(j))});
    }

    public static String formatSeconds(long j) {
        return String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(secondsInMinuteOf(j))});
    }
}
