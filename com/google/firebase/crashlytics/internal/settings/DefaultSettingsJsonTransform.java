package com.google.firebase.crashlytics.internal.settings;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.FeaturesSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SessionSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import com.jch.racWiFi.C1662R2;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", C1662R2.C1665id.CTRL);
        return new SettingsData(getExpiresAtFrom(currentTimeProvider, (long) optInt2, jSONObject), buildAppDataFrom(jSONObject.getJSONObject("app")), buildSessionDataFrom(jSONObject.getJSONObject("session")), buildFeaturesSessionDataFrom(jSONObject.getJSONObject("features")), optInt, optInt2);
    }

    static Settings defaultSettings(CurrentTimeProvider currentTimeProvider) {
        JSONObject jSONObject = new JSONObject();
        return new SettingsData(getExpiresAtFrom(currentTimeProvider, 3600, jSONObject), (AppSettingsData) null, buildSessionDataFrom(jSONObject), buildFeaturesSessionDataFrom(jSONObject), 0, C1662R2.C1665id.CTRL);
    }

    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put("expires_at", settingsData.expiresAtMillis).put("cache_duration", settingsData.cacheDuration).put("settings_version", settingsData.settingsVersion).put("features", toFeaturesJson(settingsData.featuresData)).put("app", toAppJson(settingsData.appData)).put("session", toSessionJson(settingsData.sessionData));
    }

    private static AppSettingsData buildAppDataFrom(JSONObject jSONObject) throws JSONException {
        return new AppSettingsData(jSONObject.getString(NotificationCompat.CATEGORY_STATUS), jSONObject.getString(ImagesContract.URL), jSONObject.getString("reports_url"), jSONObject.getString("ndk_reports_url"), jSONObject.optBoolean("update_required", false));
    }

    private static FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject jSONObject) {
        return new FeaturesSettingsData(jSONObject.optBoolean("collect_reports", true));
    }

    private static SessionSettingsData buildSessionDataFrom(JSONObject jSONObject) {
        return new SessionSettingsData(jSONObject.optInt("max_custom_exception_events", 8), 4);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) {
        if (jSONObject.has("expires_at")) {
            return jSONObject.optLong("expires_at");
        }
        return currentTimeProvider.getCurrentTimeMillis() + (j * 1000);
    }

    private JSONObject toAppJson(AppSettingsData appSettingsData) throws JSONException {
        return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, appSettingsData.status).put(ImagesContract.URL, appSettingsData.url).put("reports_url", appSettingsData.reportsUrl).put("ndk_reports_url", appSettingsData.ndkReportsUrl).put("update_required", appSettingsData.updateRequired);
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData featuresSettingsData) throws JSONException {
        return new JSONObject().put("collect_reports", featuresSettingsData.collectReports);
    }

    private JSONObject toSessionJson(SessionSettingsData sessionSettingsData) throws JSONException {
        return new JSONObject().put("max_custom_exception_events", sessionSettingsData.maxCustomExceptionEvents).put("max_complete_sessions_count", sessionSettingsData.maxCompleteSessionsCount);
    }
}
