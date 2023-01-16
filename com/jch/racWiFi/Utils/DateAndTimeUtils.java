package com.jch.racWiFi.Utils;

import androidx.exifinterface.media.ExifInterface;
import com.accord.common.utils.Logger;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.p010di.util.Constants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.DateUtils;

public class DateAndTimeUtils {
    public static final String DATE_FORMAT_WITH_SLASH = "yyyy/MM/dd";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public String convert24HoursFormat(String str) {
        return "00.00";
    }

    public static String getTimeFromMilliSec(long j, String str) {
        return getTimeFromMilliSec(j, str, Locale.ENGLISH);
    }

    public static String getTimeFromMilliSec(long j, String str, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(j));
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentDateTimeAsPerPattern(String str) {
        return new SimpleDateFormat(str).format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentDateTimeAsPerPattern(String str, int i) {
        return new SimpleDateFormat(str).format(new Date(System.currentTimeMillis() - ((long) ((i * 1000) * 60))));
    }

    public static String getNotificationDateWithTime(Long l, String str) {
        return new SimpleDateFormat(str, LocaleConfiguration.getCurrentAppLocale()).format(new Date(l.longValue()));
    }

    public static String getTime(int i, int i2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        return simpleDateFormat.format(instance.getTime());
    }

    public static Calendar getCalendar(int i, int i2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        Calendar instance = Calendar.getInstance();
        instance.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        instance.set(11, i);
        instance.set(12, i2);
        return instance;
    }

    public static Calendar getCurrentCalendar() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        Calendar instance = Calendar.getInstance();
        instance.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        return instance;
    }

    public static Calendar getCalendarInstance(String str) throws ParseException {
        new SimpleDateFormat(str);
        return Calendar.getInstance();
    }

    public static int compareTime(Calendar calendar, Calendar calendar2) {
        return calendar.compareTo(calendar2);
    }

    public static String addTime(int i, int i2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
            instance.add(10, i);
            instance.add(12, i2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(Long.valueOf(instance.getTimeInMillis()));
    }

    public static String subtractTime(int i, int i2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
            instance.add(10, i);
            instance.add(12, i2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(Long.valueOf(instance.getTimeInMillis()));
    }

    public static String getTime(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[1];
    }

    public static String getTimes(String str) {
        return str.split(" ")[1];
    }

    public static String substractBetweenTwoDateTime(String str) {
        try {
            String[] split = str.split(ExifInterface.GPS_DIRECTION_TRUE);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
            String format = simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(simpleDateFormat.parse(split[0] + " " + split[1]).getTime() - simpleDateFormat.parse(format).getTime());
            return ((int) (minutes / 60)) + ":" + ((int) (minutes % 60));
        } catch (Exception e) {
            e.printStackTrace();
            return IdManager.DEFAULT_VERSION_NAME;
        }
    }

    public static long subtractDateFromCurrentDate(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parse = simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
            return TimeUnit.MILLISECONDS.toDays(simpleDateFormat.parse(str).getTime() - parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String convertDateInTwoDigit(int i) {
        if (i >= 10) {
            return String.valueOf(i);
        }
        return StatusCode.BUILTIN_WIRELESS + i;
    }

    public static String setCurrentTime(int i, int i2) {
        if (i <= 9 && i2 <= 9) {
            return StatusCode.BUILTIN_WIRELESS + i + ":0" + i2;
        } else if (i <= 9) {
            return StatusCode.BUILTIN_WIRELESS + i + ":" + i2;
        } else if (i2 <= 9) {
            return i + ":0" + i2;
        } else {
            return i + ":" + i2;
        }
    }

    public static String setCurrentDate(int i, int i2, int i3) {
        if (i <= 9 && i2 <= 9) {
            return StatusCode.BUILTIN_WIRELESS + i + "-0" + i2 + "-" + i3;
        } else if (i <= 9) {
            return StatusCode.BUILTIN_WIRELESS + i + "-" + i2 + "-" + i3;
        } else if (i2 <= 9) {
            return i + "-0" + i2 + "-" + i3;
        } else {
            return i + "-" + i2 + "-" + i3;
        }
    }

    public static synchronized long getTodayMilliseconds(String str, String str2) {
        long time;
        synchronized (DateAndTimeUtils.class) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:MM", Locale.getDefault());
            Date date = null;
            try {
                date = simpleDateFormat.parse(str + ":" + str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            time = date != null ? date.getTime() : 0;
        }
        return time;
    }

    public static synchronized String getTimeZone() {
        String format;
        synchronized (DateAndTimeUtils.class) {
            format = new SimpleDateFormat("Z").format(new Date(System.currentTimeMillis()));
        }
        return format;
    }

    public static synchronized String getUTCTimeZone() {
        String format;
        synchronized (DateAndTimeUtils.class) {
            format = new SimpleDateFormat("Z").format(new Date(System.currentTimeMillis()));
        }
        return format;
    }

    public static synchronized int getHourFromTime(String str) {
        int i;
        synchronized (DateAndTimeUtils.class) {
            i = getCalenderObjFromDateTimeString(str).get(11) - 1;
        }
        return i;
    }

    public static synchronized int getMinuteFromTime(String str) {
        int i;
        synchronized (DateAndTimeUtils.class) {
            i = getCalenderObjFromDateTimeString(str).get(12);
        }
        return i;
    }

    public static String getHoursFromTimeString(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[1].substring(0, 2);
    }

    public static String getHoursFromTimeStrings(String str) {
        return str.split(":")[0];
    }

    public static String getMinutesFromTimeStrings(String str) {
        return str.split(":")[1];
    }

    public static String getMinutesFromTimeString(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[1].substring(3, 5);
    }

    public static String getYearFromTimeString(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[0].substring(0, 4);
    }

    public static String getMonthFromTimeString(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[0].substring(5, 7);
    }

    public static String getDateFromTimeString(String str) {
        return str.split(ExifInterface.GPS_DIRECTION_TRUE)[0].substring(8, 10);
    }

    public static Calendar getCalenderObjFromDateTimeString(String str) {
        String replace = str.replace('T', ' ');
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sssZ").parse(replace));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static String addTime(String str, String str2) {
        int i = 0;
        int parseInt = Integer.parseInt(str.substring(0, 2));
        int parseInt2 = Integer.parseInt(str2.substring(0, 2));
        int parseInt3 = Integer.parseInt(str.substring(3, 5)) + Integer.parseInt(str2.substring(3, 5));
        if (parseInt3 > 59) {
            i = 1;
            parseInt3 %= 60;
        }
        int i2 = i + parseInt + parseInt2;
        Logger.m49i("", i2 + " Hours : " + parseInt3 + " minutes");
        return setCurrentTime(i2, parseInt3);
    }

    public static String subtract(String str, String str2) {
        long j;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long j2 = 0;
        try {
            long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
            j = (time / 60000) % 60;
            try {
                j2 = (time / DateUtils.MILLIS_PER_HOUR) % 24;
                Logger.m45d("DateAndTime", j2 + " hours, ");
                Logger.m45d("DateAndTime", j + " minutes, ");
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            j = 0;
            e.printStackTrace();
            return j2 + ":" + j;
        }
        return j2 + ":" + j;
    }

    public static long addOneDayInCurrentDate() {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, 1);
        return instance.getTime().getTime();
    }

    public static long addDaysInCurrentDate(int i) {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, i);
        return instance.getTime().getTime();
    }

    public static Calendar convertHourMinuteStringTOCalenderObject(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public static long getMilliseconds(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date.getTime();
    }

    public static String convertDateIntoLocaleDateFormat(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return DateFormat.getDateInstance(2, Locale.getDefault()).format(date);
    }

    public static String convertDateFormat(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date);
    }

    public static String convertLocalTimeToUTC(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static String getTime(long j) {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(j));
    }

    public static String convertLocalDateTimeToUTC(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public static String getDefaultDateForHolidayModeDate() {
        return DateFormat.getDateInstance(2, Locale.getDefault()).format(Long.valueOf(addOneDayInCurrentDate()));
    }

    public static String convertDateFormatAsPerServerRequest(String str) {
        Date date;
        try {
            date = DateFormat.getDateInstance(2, Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
    }

    public static String getDate(String str) {
        Date date;
        try {
            date = new SimpleDateFormat(Constants.DateFormat.FORMAT_1, Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return new SimpleDateFormat(Constants.DateFormat.FORMAT_1).format(date);
    }

    public static String convertUTCToLocalTime(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        String format = simpleDateFormat2.format(date);
        Logger.m49i("", "Default Time Zone " + format);
        return format;
    }

    public static String convertUTCTimeToLocalDateTime(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        String format = simpleDateFormat2.format(date);
        Logger.m49i("", "Default Time Zone " + format);
        return format;
    }

    public static String getHourFromTimerStrings(String str) {
        return str.split(":")[0];
    }

    public static String getMinuteFromTimerStrings(String str) {
        return str.split(":")[1];
    }

    public static Date convertToDateObject(String str) {
        try {
            return new SimpleDateFormat("HH:mm").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertsTimerIntoDDmmFormat(String str) {
        int parseInt = Integer.parseInt(getHourFromTimerStrings(str));
        int parseInt2 = Integer.parseInt(getMinuteFromTimerStrings(str));
        return parseInt + ":" + parseInt2;
    }

    public static String getHourFromTimeString(String str) {
        return String.valueOf(convertHourMinuteStringTOCalenderObject(getHoursFromTimeString(str) + ":" + getMinutesFromTimeString(str)).get(11));
    }

    public static String getMinuteFromTimeString(String str) {
        return String.valueOf(convertHourMinuteStringTOCalenderObject(getHoursFromTimeString(str) + ":" + getMinutesFromTimeString(str)).get(12));
    }

    public static boolean checkTimePattern(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)").matcher(str).matches();
    }

    public static int checktimings(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            return simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(str2));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String convert12HoursFormat(String str) {
        try {
            return new SimpleDateFormat("hh.mm aa").format(new SimpleDateFormat("HH:mm").parse(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "00.00";
        }
    }

    public static String convert12HoursFormatWithoutAMandPM(String str) {
        try {
            return new SimpleDateFormat("hh.mm").format(new SimpleDateFormat("HH:mm").parse(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "00.00";
        }
    }
}
