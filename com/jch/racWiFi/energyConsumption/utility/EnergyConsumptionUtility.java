package com.jch.racWiFi.energyConsumption.utility;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class EnergyConsumptionUtility {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    interface Months {
        public static final String APR = "APR";
        public static final String AUG = "AUG";
        public static final String DEC = "DEC";
        public static final String FEB = "FEB";
        public static final String JAN = "JAN";
        public static final String JUL = "JUL";
        public static final String JUN = "JUN";
        public static final String MAR = "MAR";
        public static final String MAY = "MAY";
        public static final String NOV = "NOV";
        public static final String OCT = "OCT";
        public static final String SEP = "SEP";
    }

    interface Weeks {
        public static final String FRI = "Friday";
        public static final String MON = "Monday";
        public static final String SAT = "Saturday";
        public static final String SUN = "Sunday";
        public static final String THU = "Thursday";
        public static final String TUE = "Tuesday";
        public static final String WED = "Wednesday";
    }

    public static int getDayIndex(int i) {
        if (2 == i) {
            return 0;
        }
        if (3 == i) {
            return 1;
        }
        if (4 == i) {
            return 2;
        }
        if (5 == i) {
            return 3;
        }
        if (6 == i) {
            return 4;
        }
        if (7 == i) {
            return 5;
        }
        return 1 == i ? 6 : 0;
    }

    public static String getDynamicStartDayOfMonth(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(2, i);
        instance.set(5, instance.getActualMinimum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getDynamicEndDayOfMonth(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(2, i);
        instance.set(5, instance.getActualMaximum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getStartDayOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(2, 0);
        instance.set(5, instance.getActualMinimum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getStartDayOfLastMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(2, -1);
        instance.set(5, instance.getActualMinimum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getEndDayOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(2, 0);
        instance.set(5, instance.getActualMaximum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getDynamicDateofWeek(int i) {
        Calendar instance = Calendar.getInstance();
        instance.set(7, 2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        instance.add(5, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getLastDayOfYear(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar instance = Calendar.getInstance();
        instance.add(1, i);
        instance.set(5, 31);
        instance.set(2, 11);
        System.out.println(simpleDateFormat.format(instance.getTime()));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getDayFromDate(String str) {
        String[] split = str.split("-");
        return split.length == 3 ? split[2] : "";
    }

    public static String getMonthFromDate(String str, String[] strArr) {
        String[] split = str.split("-");
        int parseInt = split.length >= 2 ? Integer.parseInt(split[1]) : 0;
        String str2 = strArr.length >= parseInt ? strArr[parseInt - 1] : "";
        return str2 + StringUtils.f715LF + split[0];
    }

    public static int getMonthLastDate(String str) {
        String[] split = str.split("-");
        if (split.length >= 2) {
            return Integer.parseInt(split[2]);
        }
        return 31;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getPreviousYear() {
        Calendar instance = Calendar.getInstance();
        instance.add(1, -1);
        return instance.get(1);
    }

    public static int getWeekIndexFromDate(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.US);
        if (date != null) {
            String format = simpleDateFormat.format(date);
            format.hashCode();
            char c = 65535;
            switch (format.hashCode()) {
                case -2049557543:
                    if (format.equals(Weeks.SAT)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1807319568:
                    if (format.equals(Weeks.SUN)) {
                        c = 1;
                        break;
                    }
                    break;
                case -897468618:
                    if (format.equals(Weeks.WED)) {
                        c = 2;
                        break;
                    }
                    break;
                case 687309357:
                    if (format.equals(Weeks.TUE)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1636699642:
                    if (format.equals(Weeks.THU)) {
                        c = 4;
                        break;
                    }
                    break;
                case 2112549247:
                    if (format.equals(Weeks.FRI)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return 5;
                case 1:
                    return 6;
                case 2:
                    return 2;
                case 3:
                    return 1;
                case 4:
                    return 3;
                case 5:
                    return 4;
            }
        }
        return 0;
    }

    public static String getDateTimeFromLong(long j) {
        Date date = new Date(j);
        PrintStream printStream = System.out;
        printStream.println("current Date: " + date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MMM/dd  HH:mm", Locale.getDefault());
        Calendar.getInstance().setTimeInMillis(j);
        return simpleDateFormat.format(date);
    }

    public static String getDateFromLong(long j) {
        Date date = new Date(j);
        PrintStream printStream = System.out;
        printStream.println("current Date: " + date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar.getInstance().setTimeInMillis(j);
        return simpleDateFormat.format(date);
    }

    public static String getCurrentMonth() {
        return Calendar.getInstance().getDisplayName(2, 2, Locale.getDefault());
    }

    public static Date convertStringIntoDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getMonthIndexFromGivenMonth(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 65027:
                if (str.equals(Months.APR)) {
                    c = 0;
                    break;
                }
                break;
            case 65171:
                if (str.equals(Months.AUG)) {
                    c = 1;
                    break;
                }
                break;
            case 67554:
                if (str.equals(Months.DEC)) {
                    c = 2;
                    break;
                }
                break;
            case 69475:
                if (str.equals(Months.FEB)) {
                    c = 3;
                    break;
                }
                break;
            case 73825:
                if (str.equals(Months.JUL)) {
                    c = 4;
                    break;
                }
                break;
            case 73827:
                if (str.equals(Months.JUN)) {
                    c = 5;
                    break;
                }
                break;
            case 76094:
                if (str.equals(Months.MAR)) {
                    c = 6;
                    break;
                }
                break;
            case 76101:
                if (str.equals(Months.MAY)) {
                    c = 7;
                    break;
                }
                break;
            case 77493:
                if (str.equals(Months.NOV)) {
                    c = 8;
                    break;
                }
                break;
            case 78080:
                if (str.equals(Months.OCT)) {
                    c = 9;
                    break;
                }
                break;
            case 81982:
                if (str.equals(Months.SEP)) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 3;
            case 1:
                return 7;
            case 2:
                return 11;
            case 3:
                return 1;
            case 4:
                return 6;
            case 5:
                return 5;
            case 6:
                return 2;
            case 7:
                return 4;
            case 8:
                return 10;
            case 9:
                return 9;
            case 10:
                return 8;
            default:
                return 0;
        }
    }
}
