package com.jch.racWiFi.Utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter {
    int digitsBeforeZero;
    Pattern mPattern;

    public DecimalDigitsInputFilter(int i, int i2) {
        this.digitsBeforeZero = i;
        StringBuilder sb = new StringBuilder();
        sb.append("[0-9]{0,");
        sb.append(i - 1);
        sb.append("}+((\\.[0-9]{0,");
        sb.append(i2 - 1);
        sb.append("})?)||(\\.)?");
        this.mPattern = Pattern.compile(sb.toString());
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (!this.mPattern.matcher(spanned).matches()) {
            if (!spanned.toString().contains(".")) {
                StringBuilder sb = new StringBuilder();
                sb.append("[0-9]{0,");
                sb.append(this.digitsBeforeZero - 1);
                sb.append("}");
                if (!Pattern.compile(sb.toString()).matcher(spanned).matches()) {
                    if (spanned.toString().contains(".") || !charSequence.toString().equalsIgnoreCase(".")) {
                        return "";
                    }
                    return null;
                }
            } else if (spanned.toString().substring(spanned.toString().indexOf(".")).length() > 2) {
                return "";
            } else {
                return null;
            }
        }
        return null;
    }
}
