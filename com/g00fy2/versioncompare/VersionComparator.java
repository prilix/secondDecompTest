package com.g00fy2.versioncompare;

import java.util.List;
import javax.annotation.Nonnull;

final class VersionComparator {
    private static final int ALPHA = 2;
    private static final String ALPHA_STRING = "alpha";
    private static final int BETA = 3;
    private static final String BETA_STRING = "beta";
    static final int MAJOR = 0;
    static final int MINOR = 1;
    static final int PATCH = 2;
    private static final int PRE_ALPHA = 1;
    private static final String PRE_STRING = "pre";

    /* renamed from: RC */
    private static final int f152RC = 4;
    private static final String RC_STRING = "rc";
    private static final int SNAPSHOT = 0;
    private static final String SNAPSHOT_STRING = "snapshot";
    private static final int UNKNOWN = 5;

    VersionComparator() {
    }

    static int compareSubversionNumbers(@Nonnull List<Integer> list, @Nonnull List<Integer> list2) {
        int size = list.size();
        int size2 = list2.size();
        int max = Math.max(size, size2);
        int i = 0;
        while (i < max) {
            if ((i < size ? list.get(i).intValue() : 0) > (i < size2 ? list2.get(i).intValue() : 0)) {
                return 1;
            }
            if ((i < size ? list.get(i).intValue() : 0) < (i < size2 ? list2.get(i).intValue() : 0)) {
                return -1;
            }
            i++;
        }
        return 0;
    }

    static int compareSuffix(@Nonnull String str, @Nonnull String str2) {
        if (str.length() <= 0 && str2.length() <= 0) {
            return 0;
        }
        int qualifierToNumber = qualifierToNumber(str);
        int qualifierToNumber2 = qualifierToNumber(str2);
        if (qualifierToNumber > qualifierToNumber2) {
            return 1;
        }
        if (qualifierToNumber < qualifierToNumber2) {
            return -1;
        }
        if (qualifierToNumber == 5 || qualifierToNumber == 0) {
            return 0;
        }
        int preReleaseVersion = preReleaseVersion(str, qualifierToNumber);
        int preReleaseVersion2 = preReleaseVersion(str2, qualifierToNumber2);
        if (preReleaseVersion > preReleaseVersion2) {
            return 1;
        }
        if (preReleaseVersion < preReleaseVersion2) {
            return -1;
        }
        return 0;
    }

    private static int qualifierToNumber(@Nonnull String str) {
        if (str.length() <= 0) {
            return 5;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.contains(RC_STRING)) {
            return 4;
        }
        if (lowerCase.contains(BETA_STRING)) {
            return 3;
        }
        if (lowerCase.contains("alpha")) {
            return lowerCase.substring(0, lowerCase.indexOf("alpha")).contains(PRE_STRING) ? 1 : 2;
        }
        if (lowerCase.contains(SNAPSHOT_STRING)) {
            return 0;
        }
        return 5;
    }

    private static int preReleaseVersion(@Nonnull String str, int i) {
        int indexOfQualifier = indexOfQualifier(str, i);
        if (indexOfQualifier >= str.length() || !containsNumeric(str.substring(indexOfQualifier, Math.min(indexOfQualifier + 2, str.length())))) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = indexOfQualifier; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            } else if (i2 != indexOfQualifier) {
                break;
            }
        }
        return safeParseInt(sb.toString());
    }

    private static int indexOfQualifier(@Nonnull String str, int i) {
        if (i == 4) {
            return str.indexOf(RC_STRING) + 2;
        }
        if (i == 3) {
            return str.indexOf(BETA_STRING) + 4;
        }
        if (i == 2 || i == 1) {
            return str.indexOf("alpha") + 5;
        }
        return 0;
    }

    static boolean startsNumeric(@Nonnull String str) {
        String trim = str.trim();
        return trim.length() > 0 && Character.isDigit(trim.charAt(0));
    }

    static int safeParseInt(@Nonnull String str) {
        if (str.length() > 9) {
            str = str.substring(0, 9);
        }
        return Integer.parseInt(str);
    }

    static boolean isNumeric(@Nonnull CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsNumeric(@Nonnull CharSequence charSequence) {
        int length = charSequence.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (Character.isDigit(charSequence.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
