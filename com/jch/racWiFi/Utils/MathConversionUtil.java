package com.jch.racWiFi.Utils;

public class MathConversionUtil {
    public static synchronized float fromCelsiusToFahrenheit(float f) {
        float f2;
        synchronized (MathConversionUtil.class) {
            f2 = (f * 1.8f) + 32.0f;
        }
        return f2;
    }
}
