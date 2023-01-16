package com.jch.racWiFi.amplitude.util;

import android.app.Application;
import com.amplitude.api.AmplitudeClient;
import com.jch.racWiFi.BuildConfig;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.amplitude.exception.AmplitudeException;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import com.jch.racWiFi.fcm.util.EnumUtil;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.p010di.util.Constants;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(mo36737d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u000e\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0015\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J9\u0010\u0018\u001a\u0002H\u0019\"\u0010\b\u0000\u0010\u0019*\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010\u001eR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u001f"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/util/AmplitudeUtil;", "", "mApplication", "Landroid/app/Application;", "(Landroid/app/Application;)V", "mStrings", "", "", "[Ljava/lang/String;", "getEvents", "Lcom/jch/racWiFi/amplitude/util/Events;", "eventType", "isEnabled", "", "logEvent", "", "clientFactory", "Lcom/jch/racWiFi/amplitude/factory/ClientFactory;", "screen", "index", "", "logEvents", "time", "", "obtainEnum", "E", "", "clazz", "Ljava/lang/Class;", "s", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: AmplitudeUtil.kt */
public final class AmplitudeUtil {
    private final Application mApplication;
    private final String[] mStrings = {"Event", "Property", "Engagement Time"};

    public AmplitudeUtil(Application application) {
        this.mApplication = application;
    }

    private final <E extends Enum<E>> E obtainEnum(Class<E> cls, String str) {
        return EnumUtil.getInstance().getString(cls, str);
    }

    public final void logEvent(String str, int i, ClientFactory clientFactory) throws AmplitudeException {
        Intrinsics.checkNotNullParameter(clientFactory, "clientFactory");
        if (isEnabled() && !Constants.IS_DEMO_MODE) {
            Boolean bool = BuildConfig.AMPLITUDE_ENABLED;
            Intrinsics.checkNotNullExpressionValue(bool, "AMPLITUDE_ENABLED");
            if (bool.booleanValue()) {
                clientFactory.getClient().logEvent(Intrinsics.stringPlus(((Screens) obtainEnum(Screens.class, str)).getStrings()[i], " Screen"));
            }
        }
    }

    public final void logEvent(String str, ClientFactory clientFactory) throws AmplitudeException {
        Intrinsics.checkNotNullParameter(clientFactory, "clientFactory");
        if (isEnabled() && !Constants.IS_DEMO_MODE) {
            Boolean bool = BuildConfig.AMPLITUDE_ENABLED;
            Intrinsics.checkNotNullExpressionValue(bool, "AMPLITUDE_ENABLED");
            if (bool.booleanValue()) {
                AmplitudeClient client = clientFactory.getClient();
                Intrinsics.checkNotNull(str);
                Events events = getEvents(str);
                Intrinsics.checkNotNull(events);
                client.logEvent(events.getStrings()[0]);
            }
        }
    }

    public final void logEvents(String str, long j, ClientFactory clientFactory) throws JSONException {
        Intrinsics.checkNotNullParameter(clientFactory, "clientFactory");
        if (isEnabled() && !Constants.IS_DEMO_MODE) {
            Boolean bool = BuildConfig.AMPLITUDE_ENABLED;
            Intrinsics.checkNotNullExpressionValue(bool, "AMPLITUDE_ENABLED");
            if (bool.booleanValue()) {
                Events events = (Events) obtainEnum(Events.class, str);
                String[] strings = events.getStrings();
                JSONObject jSONObject = new JSONObject();
                int length = events.getLength();
                if (length > 0) {
                    int i = 0;
                    while (true) {
                        int i2 = i + 1;
                        jSONObject.put(this.mStrings[i], strings[i]);
                        if (i2 >= length) {
                            break;
                        }
                        i = i2;
                    }
                }
                if (j > 0) {
                    if (j >= 60) {
                        String str2 = this.mStrings[2];
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format(Locale.getDefault(), "%.1f", Arrays.copyOf(new Object[]{Float.valueOf((float) (((double) j) / 60.0d))}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, format, *args)");
                        jSONObject.put(str2, Intrinsics.stringPlus(format, " min"));
                    } else {
                        String str3 = this.mStrings[2];
                        jSONObject.put(str3, j + " sec");
                    }
                }
                AmplitudeClient client = clientFactory.getClient();
                Intrinsics.checkNotNull(str);
                Events events2 = getEvents(str);
                Intrinsics.checkNotNull(events2);
                client.logEvent(events2.getStrings()[0], jSONObject);
            }
        }
    }

    private final Events getEvents(String str) {
        return (Events) EnumUtil.getInstance().getString(Events.class, str);
    }

    private final boolean isEnabled() {
        Boolean bool = (Boolean) new Persistence().obtain(Constants.Keys.IS_AMPLITUDE_ENABLED, Boolean.TYPE);
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }
}
