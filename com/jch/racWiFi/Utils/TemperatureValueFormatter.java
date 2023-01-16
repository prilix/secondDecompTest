package com.jch.racWiFi.Utils;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import java.util.Locale;
import org.slf4j.Marker;

public class TemperatureValueFormatter {
    private static String convertedTemperature(Float f, RacModelWiseData racModelWiseData, OperationMode operationMode) {
        String str;
        boolean isPitchFloating = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationMode).isPitchFloating();
        double convertTemperaureUnitFromCelsiusAccordingToSettings = TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) f.floatValue());
        int i = (int) convertTemperaureUnitFromCelsiusAccordingToSettings;
        if (!isPitchFloating) {
            str = String.valueOf(i);
        } else if (TemperatureUnit.CURRENT.equals(TemperatureUnit.CELSIUS)) {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(convertTemperaureUnitFromCelsiusAccordingToSettings)});
        } else {
            str = String.valueOf(i);
        }
        Logger.m47e("TEMPERATURE_UNIT", "Not Converted : " + f + " Converted Temp : " + str);
        return str;
    }

    private static String convertedTemperatureAuto(Float f, RacModelWiseData racModelWiseData, OperationMode operationMode) {
        double d;
        String str;
        boolean isPitchFloating = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationMode).isPitchFloating();
        if (TemperatureUnit.CURRENT.equals(TemperatureUnit.FAHRENHEIT)) {
            d = TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) f.floatValue(), -1);
        } else {
            d = TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) f.floatValue());
        }
        int i = (int) d;
        if (isPitchFloating) {
            str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(d)});
        } else {
            str = String.valueOf(i);
        }
        Logger.m47e("TEMPERATURE_UNIT", "Not Converted : " + f + " Converted Temp : " + str);
        return str;
    }

    public static String getConvertedValue(double d) {
        double convertTemperaureUnitFromCelsiusAccordingToSettings = TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d, true);
        if (!TemperatureUnit.CURRENT.equals(TemperatureUnit.CELSIUS)) {
            return String.valueOf((int) convertTemperaureUnitFromCelsiusAccordingToSettings);
        }
        return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(convertTemperaureUnitFromCelsiusAccordingToSettings)});
    }

    public static String getConvertedTemperature(Float f, RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
        return convertedTemperature(f, racModelWiseData, detailedIduModel.getOperationModeEnum());
    }

    public static String getConvertedTemperatureAuto(Float f, RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
        StringBuilder sb;
        String str;
        String convertedTemperatureAuto = convertedTemperatureAuto(f, racModelWiseData, detailedIduModel.getOperationModeEnum());
        boolean z = true;
        boolean z2 = f.floatValue() < 0.0f;
        if (f.floatValue() != 0.0f) {
            z = false;
        }
        if (z) {
            sb = new StringBuilder();
            str = "±";
        } else if (z2) {
            return convertedTemperatureAuto;
        } else {
            sb = new StringBuilder();
            str = Marker.ANY_NON_NULL_MARKER;
        }
        sb.append(str);
        sb.append(convertedTemperatureAuto);
        return sb.toString();
    }

    public static String getConvertedTemperature(Float f, RacModelWiseData racModelWiseData, OperationMode operationMode) {
        return convertedTemperature(f, racModelWiseData, operationMode);
    }
}
