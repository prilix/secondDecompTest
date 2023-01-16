package com.jch.racWiFi;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.amplitude.api.Constants;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

public class LocaleHelper {
    public static final LocaleHelper INSTANCE = new LocaleHelper();

    public final Context onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Locale currentAppLocale = LocaleConfiguration.getCurrentAppLocale();
        Intrinsics.checkExpressionValueIsNotNull(currentAppLocale, "LocaleConfiguration.getCurrentAppLocale()");
        return setLocale(context, currentAppLocale);
    }

    public final Context setLocale(Context context, Locale locale) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(locale, Constants.AMP_TRACKING_OPTION_LANGUAGE);
        return Build.VERSION.SDK_INT >= 24 ? updateResources(context, locale) : updateResourcesLegacy(context, locale);
    }

    private Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        Intrinsics.checkExpressionValueIsNotNull(createConfigurationContext, "context.createConfigurationContext(configuration)");
        return createConfigurationContext;
    }

    private Context updateResourcesLegacy(Context context, Locale locale) {
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        configuration.setLayoutDirection(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    private LocaleHelper() {
    }
}
