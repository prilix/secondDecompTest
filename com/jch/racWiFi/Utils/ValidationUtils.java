package com.jch.racWiFi.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.accord.common.utils.Logger;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import java.io.PrintStream;
import java.util.HashMap;
import org.slf4j.Marker;

public class ValidationUtils {
    public static boolean GET_ACTUAL_LENGTH_OF_PHONE_NUMBER_BASED_ON_ISO = false;
    public static int MAX_PHONE_NUMBER_LENGTH = 20;
    public static final int length_phone_number_indonesia = 12;

    public static String getFormattedPhoneNumber() {
        return null;
    }

    public static boolean isEmailAddressValid(String str) {
        return str != null && !TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean isMobileNumberValid(String str, String str2) {
        return validatePhoneNumberBasedOnCountryCode(str, str2);
    }

    public static boolean isMobileNumberValid(String str) {
        return str.length() >= 4 && str.length() <= 14 && !str.matches("^(.)\\1*$");
    }

    public static boolean isOtpValid(String str) {
        return str.length() == 6;
    }

    public enum PasswordStrength {
        EMPTY,
        WEAK,
        FAIR,
        GOOD,
        STRONG,
        VERY_STRONG;
        
        static int MAXIMUM_LENGTH;
        static int REQUIRED_LENGTH;
        static boolean REQUIRE_DIGITS;
        static boolean REQUIRE_LOWER_CASE;
        static boolean REQUIRE_SPECIAL_CHARACTERS;
        static boolean REQUIRE_UPPER_CASE;

        static {
            REQUIRED_LENGTH = 8;
            MAXIMUM_LENGTH = 15;
            REQUIRE_SPECIAL_CHARACTERS = false;
            REQUIRE_DIGITS = true;
            REQUIRE_LOWER_CASE = true;
            REQUIRE_UPPER_CASE = true;
        }

        public boolean isStrongEnough() {
            return equals(STRONG) || equals(VERY_STRONG);
        }
    }

    public static int getMaxLengthOfMobileNumberBasedOnCountryCode(String str) {
        if (!GET_ACTUAL_LENGTH_OF_PHONE_NUMBER_BASED_ON_ISO) {
            return MAX_PHONE_NUMBER_LENGTH;
        }
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        if (regionCodeForCountryCode.equalsIgnoreCase("ZZ")) {
            return 10;
        }
        if (regionCodeForCountryCode.equalsIgnoreCase("ID")) {
            return 12;
        }
        return String.valueOf(instance.getExampleNumberForType(regionCodeForCountryCode, PhoneNumberUtil.PhoneNumberType.MOBILE).getNationalNumber()).length() + 1;
    }

    public static boolean validatePhoneNumberBasedOnCountryCode(String str, String str2) {
        getMaxLengthOfMobileNumberBasedOnCountryCode(str);
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        if (regionCodeForCountryCode.equals("ZZ")) {
            regionCodeForCountryCode = CoreActivity.countryCodeToIsoCodeHashMap.get(str).toUpperCase();
        }
        try {
            return instance.isValidNumber(instance.parse(str2, regionCodeForCountryCode));
        } catch (NumberParseException e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean isMobileNumberValidInviteUser(String str, String str2) {
        int i;
        String str3;
        Phonenumber.PhoneNumber phoneNumber;
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber2 = null;
        if (str == null) {
            i = 81;
            str3 = "JP";
        } else {
            try {
                phoneNumber = instance.parse(str, "IN");
            } catch (NumberParseException e) {
                e.printStackTrace();
                phoneNumber = null;
            }
            i = phoneNumber != null ? phoneNumber.getCountryCode() : 0;
            str3 = instance.getRegionCodeForCountryCode(i);
        }
        try {
            phoneNumber2 = instance.parse(str2, str3);
        } catch (NumberParseException e2) {
            e2.printStackTrace();
            Logger.m47e("printStackTrace", e2.getMessage());
        }
        if (phoneNumber2 == null) {
            return false;
        }
        return isMobileNumberValid(String.valueOf(i), instance.format(phoneNumber2, PhoneNumberUtil.PhoneNumberFormat.E164));
    }

    public static String formatMobileNumberValidInviteUser(String str, String str2) {
        Phonenumber.PhoneNumber phoneNumber;
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber2 = null;
        try {
            phoneNumber = instance.parse(str, "");
        } catch (NumberParseException e) {
            e.printStackTrace();
            phoneNumber = null;
        }
        int i = 0;
        if (phoneNumber != null) {
            i = phoneNumber.getCountryCode();
        } else {
            try {
                i = PhoneNumberUtil.getInstance().parse(str2, "").getCountryCode();
            } catch (NumberParseException e2) {
                System.err.println("NumberParseException was thrown: " + e2.toString());
            }
        }
        try {
            phoneNumber2 = instance.parse(str2, instance.getRegionCodeForCountryCode(i));
        } catch (NumberParseException e3) {
            e3.printStackTrace();
        }
        return instance.format(phoneNumber2, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static String formatMobileNumberFor0Prefix(String str, String str2) {
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        if (regionCodeForCountryCode.equals("ZZ")) {
            regionCodeForCountryCode = CoreActivity.countryCodeToIsoCodeHashMap.get(str).toUpperCase();
        }
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = instance.parse(str2, regionCodeForCountryCode);
        } catch (NumberParseException e) {
            System.err.println(e);
        }
        return instance.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static String removeCountryCodeFromPhoneNumber(Context context, String str, String str2) {
        Phonenumber.PhoneNumber phoneNumber;
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        if (regionCodeForCountryCode.equals("ZZ")) {
            regionCodeForCountryCode = CoreActivity.countryCodeToIsoCodeHashMap.get(str).toUpperCase();
        }
        try {
            phoneNumber = instance.parse(str2, regionCodeForCountryCode);
        } catch (NumberParseException e) {
            e.printStackTrace();
            phoneNumber = null;
        }
        if (phoneNumber == null) {
            return null;
        }
        String valueOf = String.valueOf(phoneNumber.getNationalNumber());
        String.valueOf(phoneNumber.getCountryCode());
        if (!phoneNumber.hasItalianLeadingZero()) {
            return valueOf;
        }
        return StatusCode.BUILTIN_WIRELESS + valueOf;
    }

    private static String getIsoCodeFromCountryCode(Context context, String str) {
        return context.getString(CountryUtils.getByCode(context, CountryUtils.getAllCountries(context), str).getIso()).toUpperCase();
    }

    public static String getCountryCodeOfPhonenumber(String str, String str2) {
        Phonenumber.PhoneNumber phoneNumber;
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        Logger.m47e("Length", instance.getExampleNumber(regionCodeForCountryCode) + "");
        try {
            phoneNumber = instance.parse(str2, regionCodeForCountryCode);
        } catch (NumberParseException e) {
            e.printStackTrace();
            phoneNumber = null;
        }
        if (phoneNumber != null) {
            return String.valueOf(phoneNumber.getCountryCode());
        }
        return null;
    }

    public static int getLengthOfPhoneNumber(Context context, String str) {
        Long l;
        if (!GET_ACTUAL_LENGTH_OF_PHONE_NUMBER_BASED_ON_ISO) {
            return MAX_PHONE_NUMBER_LENGTH;
        }
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        String regionCodeForCountryCode = instance.getRegionCodeForCountryCode(Integer.parseInt(str));
        if (regionCodeForCountryCode.equalsIgnoreCase("ID")) {
            return 12;
        }
        if (regionCodeForCountryCode.equals("ZZ")) {
            l = Long.valueOf(instance.getExampleNumberForType(context.getString(CountryUtils.getByCode(context, CountryUtils.getAllCountries(context), str).getIso()).toUpperCase(), PhoneNumberUtil.PhoneNumberType.MOBILE).getNationalNumber());
        } else {
            l = Long.valueOf(instance.getExampleNumberForType(regionCodeForCountryCode, PhoneNumberUtil.PhoneNumberType.MOBILE).getNationalNumber());
        }
        return l.toString().length() + 1;
    }

    public static Phonenumber.PhoneNumber getPhoneNumberObjFromString(String str) {
        try {
            return PhoneNumberUtil.getInstance().parse(str, "IN");
        } catch (NumberParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCountryCodeFromISOCode(String str) {
        Phonenumber.PhoneNumber exampleNumberForType = PhoneNumberUtil.getInstance().getExampleNumberForType(str, PhoneNumberUtil.PhoneNumberType.MOBILE);
        if (exampleNumberForType == null) {
            return null;
        }
        return Marker.ANY_NON_NULL_MARKER + exampleNumberForType.getCountryCode();
    }

    public static boolean containsCountryCode(String str) {
        return str.startsWith(Marker.ANY_NON_NULL_MARKER);
    }

    public static HashMap<String, String> separateCountryCodeAndPhoneNumber(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(str, "+81");
            hashMap.put("phone_code", Marker.ANY_NON_NULL_MARKER + String.valueOf(parse.getCountryCode()));
            hashMap.put("phone_number", String.valueOf(parse.getNationalNumber()));
            PrintStream printStream = System.out;
            printStream.println("Country code: " + parse.getCountryCode());
        } catch (NumberParseException e) {
            PrintStream printStream2 = System.err;
            printStream2.println("NumberParseException was thrown: " + e.toString());
        }
        return hashMap;
    }
}
