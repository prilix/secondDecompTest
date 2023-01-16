package com.amplitude.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Build;
import android.util.Pair;
import androidx.core.p003os.EnvironmentCompat;
import com.amplitude.api.ConfigManager;
import com.amplitude.eventexplorer.EventExplorer;
import com.amplitude.util.DoubleCheck;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.UByte;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeClient {
    public static final String DEVICE_ID_KEY = "device_id";
    public static final String END_SESSION_EVENT = "session_end";
    public static final String LAST_EVENT_ID_KEY = "last_event_id";
    public static final String LAST_EVENT_TIME_KEY = "last_event_time";
    public static final String LAST_IDENTIFY_ID_KEY = "last_identify_id";
    public static final String OPT_OUT_KEY = "opt_out";
    public static final String PREVIOUS_SESSION_ID_KEY = "previous_session_id";
    public static final String SEQUENCE_NUMBER_KEY = "sequence_number";
    public static final String START_SESSION_EVENT = "session_start";
    private static final String TAG = "com.amplitude.api.AmplitudeClient";
    public static final String USER_ID_KEY = "user_id";
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    protected String apiKey;
    JSONObject apiPropertiesTrackingOptions;
    TrackingOptions appliedTrackingOptions;
    /* access modifiers changed from: private */
    public boolean backoffUpload;
    /* access modifiers changed from: private */
    public int backoffUploadBatchSize;
    String bearerToken;
    protected Call.Factory callFactory;
    protected Context context;
    private boolean coppaControlEnabled;
    protected DatabaseHelper dbHelper;
    protected String deviceId;
    private DeviceInfo deviceInfo;
    private EventExplorer eventExplorer;
    private int eventMaxCount;
    /* access modifiers changed from: private */
    public int eventUploadMaxBatchSize;
    private long eventUploadPeriodMillis;
    /* access modifiers changed from: private */
    public int eventUploadThreshold;
    /* access modifiers changed from: private */
    public boolean flushEventsOnClose;
    WorkerThread httpThread;
    /* access modifiers changed from: private */
    public boolean inForeground;
    protected boolean initialized;
    TrackingOptions inputTrackingOptions;
    protected String instanceName;
    Throwable lastError;
    long lastEventId;
    long lastEventTime;
    long lastIdentifyId;
    private String libraryName;
    private String libraryVersion;
    private boolean locationListening;
    WorkerThread logThread;
    private long minTimeBetweenSessionsMillis;
    private boolean newDeviceIdPerInstall;
    private boolean offline;
    /* access modifiers changed from: private */
    public boolean optOut;
    protected String platform;
    long previousSessionId;
    long sequenceNumber;
    long sessionId;
    private long sessionTimeoutMillis;
    /* access modifiers changed from: private */
    public boolean trackingSessionEvents;
    /* access modifiers changed from: private */
    public AtomicBoolean updateScheduled;
    AtomicBoolean uploadingCurrently;
    String url;
    private boolean useAdvertisingIdForDeviceId;
    /* access modifiers changed from: private */
    public boolean useDynamicConfig;
    protected String userId;
    private boolean usingForegroundTracking;

    public static /* synthetic */ OkHttpClient $r8$lambda$uWBdBgn9ebx5ZjaSsGzJj44YwX4() {
        return new OkHttpClient();
    }

    public AmplitudeClient disableDiagnosticLogging() {
        return this;
    }

    public AmplitudeClient enableDiagnosticLogging() {
        return this;
    }

    public AmplitudeClient setDiagnosticEventMaxCount(int i) {
        return this;
    }

    public AmplitudeClient() {
        this((String) null);
    }

    public AmplitudeClient(String str) {
        this.newDeviceIdPerInstall = false;
        this.useAdvertisingIdForDeviceId = false;
        this.initialized = false;
        this.optOut = false;
        this.offline = false;
        TrackingOptions trackingOptions = new TrackingOptions();
        this.inputTrackingOptions = trackingOptions;
        TrackingOptions copyOf = TrackingOptions.copyOf(trackingOptions);
        this.appliedTrackingOptions = copyOf;
        this.apiPropertiesTrackingOptions = copyOf.getApiPropertiesTrackingOptions();
        this.coppaControlEnabled = false;
        this.locationListening = true;
        this.sessionId = -1;
        this.sequenceNumber = 0;
        this.lastEventId = -1;
        this.lastIdentifyId = -1;
        this.lastEventTime = -1;
        this.previousSessionId = -1;
        this.eventUploadThreshold = 30;
        this.eventUploadMaxBatchSize = 50;
        this.eventMaxCount = 1000;
        this.eventUploadPeriodMillis = 30000;
        this.minTimeBetweenSessionsMillis = Constants.MIN_TIME_BETWEEN_SESSIONS_MILLIS;
        this.sessionTimeoutMillis = Constants.SESSION_TIMEOUT_MILLIS;
        this.backoffUpload = false;
        this.backoffUploadBatchSize = 50;
        this.usingForegroundTracking = false;
        this.trackingSessionEvents = false;
        this.inForeground = false;
        this.flushEventsOnClose = true;
        this.libraryName = Constants.LIBRARY;
        this.libraryVersion = "2.31.0";
        this.useDynamicConfig = false;
        this.updateScheduled = new AtomicBoolean(false);
        this.uploadingCurrently = new AtomicBoolean(false);
        this.url = Constants.EVENT_LOG_URL;
        this.bearerToken = null;
        this.logThread = new WorkerThread("logThread");
        this.httpThread = new WorkerThread("httpThread");
        this.instanceName = C0895Utils.normalizeInstanceName(str);
        this.logThread.start();
        this.httpThread.start();
    }

    public AmplitudeClient initialize(Context context2, String str) {
        return initialize(context2, str, (String) null);
    }

    public AmplitudeClient initialize(Context context2, String str, String str2) {
        return initialize(context2, str, str2, (String) null, false);
    }

    public synchronized AmplitudeClient initialize(Context context2, String str, String str2, String str3, boolean z) {
        return initializeInternal(context2, str, str2, str3, z, (Call.Factory) null);
    }

    public synchronized AmplitudeClient initialize(Context context2, String str, String str2, String str3, boolean z, Call.Factory factory) {
        return initializeInternal(context2, str, str2, str3, z, factory);
    }

    public synchronized AmplitudeClient initializeInternal(Context context2, String str, String str2, String str3, boolean z, Call.Factory factory) {
        if (context2 == null) {
            logger.mo13082e(TAG, "Argument context cannot be null in initialize()");
            return this;
        } else if (C0895Utils.isEmptyString(str)) {
            logger.mo13082e(TAG, "Argument apiKey cannot be null or blank in initialize()");
            return this;
        } else {
            Context applicationContext = context2.getApplicationContext();
            this.context = applicationContext;
            this.apiKey = str;
            this.dbHelper = DatabaseHelper.getDatabaseHelper(applicationContext, this.instanceName);
            if (C0895Utils.isEmptyString(str3)) {
                str3 = Constants.PLATFORM;
            }
            this.platform = str3;
            runOnLogThread(new AmplitudeClient$$ExternalSyntheticLambda1(this, context2, factory, str2, this));
            return this;
        }
    }

    /* renamed from: lambda$initializeInternal$1$com-amplitude-api-AmplitudeClient  reason: not valid java name */
    public /* synthetic */ void m755lambda$initializeInternal$1$comamplitudeapiAmplitudeClient(Context context2, Call.Factory factory, String str, final AmplitudeClient amplitudeClient) {
        if (!this.initialized) {
            try {
                if (this.instanceName.equals(Constants.DEFAULT_INSTANCE)) {
                    upgradePrefs(context2);
                    upgradeSharedPrefsToDB(context2);
                }
                if (factory == null) {
                    this.callFactory = new AmplitudeClient$$ExternalSyntheticLambda2(DoubleCheck.provider(AmplitudeClient$$ExternalSyntheticLambda0.INSTANCE));
                } else {
                    this.callFactory = factory;
                }
                if (this.useDynamicConfig) {
                    ConfigManager.getInstance().refresh(new ConfigManager.RefreshListener() {
                        public void onFinished() {
                            AmplitudeClient.this.url = ConfigManager.getInstance().getIngestionEndpoint();
                        }
                    });
                }
                this.deviceInfo = new DeviceInfo(context2, this.locationListening);
                this.deviceId = initializeDeviceId();
                this.deviceInfo.prefetch();
                if (str != null) {
                    amplitudeClient.userId = str;
                    this.dbHelper.insertOrReplaceKeyValue(USER_ID_KEY, str);
                } else {
                    amplitudeClient.userId = this.dbHelper.getValue(USER_ID_KEY);
                }
                Long longValue = this.dbHelper.getLongValue(OPT_OUT_KEY);
                this.optOut = longValue != null && longValue.longValue() == 1;
                long longvalue = getLongvalue(PREVIOUS_SESSION_ID_KEY, -1);
                this.previousSessionId = longvalue;
                if (longvalue >= 0) {
                    this.sessionId = longvalue;
                }
                this.sequenceNumber = getLongvalue(SEQUENCE_NUMBER_KEY, 0);
                this.lastEventId = getLongvalue(LAST_EVENT_ID_KEY, -1);
                this.lastIdentifyId = getLongvalue(LAST_IDENTIFY_ID_KEY, -1);
                this.lastEventTime = getLongvalue(LAST_EVENT_TIME_KEY, -1);
                this.dbHelper.setDatabaseResetListener(new DatabaseResetListener() {
                    public void onDatabaseReset(SQLiteDatabase sQLiteDatabase) {
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "store", AmplitudeClient.DEVICE_ID_KEY, amplitudeClient.deviceId);
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "store", AmplitudeClient.USER_ID_KEY, amplitudeClient.userId);
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.OPT_OUT_KEY, Long.valueOf(amplitudeClient.optOut ? 1 : 0));
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.PREVIOUS_SESSION_ID_KEY, Long.valueOf(amplitudeClient.sessionId));
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.LAST_EVENT_TIME_KEY, Long.valueOf(amplitudeClient.lastEventTime));
                    }
                });
                this.initialized = true;
            } catch (CursorWindowAllocationException e) {
                logger.mo13082e(TAG, String.format("Failed to initialize Amplitude SDK due to: %s", new Object[]{e.getMessage()}));
                amplitudeClient.apiKey = null;
            }
        }
    }

    public AmplitudeClient enableForegroundTracking(Application application) {
        if (!this.usingForegroundTracking && contextAndApiKeySet("enableForegroundTracking()") && Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new AmplitudeCallbacks(this));
        }
        return this;
    }

    public AmplitudeClient enableNewDeviceIdPerInstall(boolean z) {
        this.newDeviceIdPerInstall = z;
        return this;
    }

    public AmplitudeClient useAdvertisingIdForDeviceId() {
        this.useAdvertisingIdForDeviceId = true;
        return this;
    }

    public AmplitudeClient enableLocationListening() {
        this.locationListening = true;
        DeviceInfo deviceInfo2 = this.deviceInfo;
        if (deviceInfo2 != null) {
            deviceInfo2.setLocationListening(true);
        }
        return this;
    }

    public AmplitudeClient disableLocationListening() {
        this.locationListening = false;
        DeviceInfo deviceInfo2 = this.deviceInfo;
        if (deviceInfo2 != null) {
            deviceInfo2.setLocationListening(false);
        }
        return this;
    }

    public AmplitudeClient setEventUploadThreshold(int i) {
        this.eventUploadThreshold = i;
        return this;
    }

    public AmplitudeClient setEventUploadMaxBatchSize(int i) {
        this.eventUploadMaxBatchSize = i;
        this.backoffUploadBatchSize = i;
        return this;
    }

    public AmplitudeClient setEventMaxCount(int i) {
        this.eventMaxCount = i;
        return this;
    }

    public AmplitudeClient setEventUploadPeriodMillis(int i) {
        this.eventUploadPeriodMillis = (long) i;
        return this;
    }

    public AmplitudeClient setMinTimeBetweenSessionsMillis(long j) {
        this.minTimeBetweenSessionsMillis = j;
        return this;
    }

    public AmplitudeClient setServerUrl(String str) {
        if (!C0895Utils.isEmptyString(str)) {
            this.url = str;
        }
        return this;
    }

    public AmplitudeClient setBearerToken(String str) {
        this.bearerToken = str;
        return this;
    }

    public AmplitudeClient setSessionTimeoutMillis(long j) {
        this.sessionTimeoutMillis = j;
        return this;
    }

    public AmplitudeClient setTrackingOptions(TrackingOptions trackingOptions) {
        this.inputTrackingOptions = trackingOptions;
        TrackingOptions copyOf = TrackingOptions.copyOf(trackingOptions);
        this.appliedTrackingOptions = copyOf;
        if (this.coppaControlEnabled) {
            copyOf.mergeIn(TrackingOptions.forCoppaControl());
        }
        this.apiPropertiesTrackingOptions = this.appliedTrackingOptions.getApiPropertiesTrackingOptions();
        return this;
    }

    public AmplitudeClient enableCoppaControl() {
        this.coppaControlEnabled = true;
        this.appliedTrackingOptions.mergeIn(TrackingOptions.forCoppaControl());
        this.apiPropertiesTrackingOptions = this.appliedTrackingOptions.getApiPropertiesTrackingOptions();
        return this;
    }

    public AmplitudeClient disableCoppaControl() {
        this.coppaControlEnabled = false;
        TrackingOptions copyOf = TrackingOptions.copyOf(this.inputTrackingOptions);
        this.appliedTrackingOptions = copyOf;
        this.apiPropertiesTrackingOptions = copyOf.getApiPropertiesTrackingOptions();
        return this;
    }

    public AmplitudeClient setOptOut(final boolean z) {
        if (!contextAndApiKeySet("setOptOut()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    boolean unused = this.optOut = z;
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.OPT_OUT_KEY, Long.valueOf(z ? 1 : 0));
                }
            }
        });
        return this;
    }

    public AmplitudeClient setLibraryName(String str) {
        this.libraryName = str;
        return this;
    }

    public AmplitudeClient setLibraryVersion(String str) {
        this.libraryVersion = str;
        return this;
    }

    public boolean isOptedOut() {
        return this.optOut;
    }

    public AmplitudeClient enableLogging(boolean z) {
        logger.setEnableLogging(z);
        return this;
    }

    public AmplitudeClient setLogLevel(int i) {
        logger.setLogLevel(i);
        return this;
    }

    public AmplitudeClient setOffline(boolean z) {
        this.offline = z;
        if (!z) {
            uploadEvents();
        }
        return this;
    }

    public AmplitudeClient setFlushEventsOnClose(boolean z) {
        this.flushEventsOnClose = z;
        return this;
    }

    public AmplitudeClient trackSessionEvents(boolean z) {
        this.trackingSessionEvents = z;
        return this;
    }

    public AmplitudeClient setUseDynamicConfig(boolean z) {
        this.useDynamicConfig = z;
        return this;
    }

    public void showEventExplorer(Activity activity) {
        if (this.eventExplorer == null) {
            this.eventExplorer = new EventExplorer(this.instanceName);
        }
        this.eventExplorer.show(activity);
    }

    /* access modifiers changed from: package-private */
    public void useForegroundTracking() {
        this.usingForegroundTracking = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isUsingForegroundTracking() {
        return this.usingForegroundTracking;
    }

    /* access modifiers changed from: package-private */
    public boolean isInForeground() {
        return this.inForeground;
    }

    public void logEvent(String str) {
        logEvent(str, (JSONObject) null);
    }

    public void logEvent(String str, JSONObject jSONObject) {
        logEvent(str, jSONObject, false);
    }

    public void logEvent(String str, JSONObject jSONObject, boolean z) {
        logEvent(str, jSONObject, (JSONObject) null, z);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        logEvent(str, jSONObject, jSONObject2, false);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, boolean z) {
        logEvent(str, jSONObject, jSONObject2, getCurrentTimeMillis(), z);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, long j, boolean z) {
        if (validateLogEvent(str)) {
            logEventAsync(str, jSONObject, (JSONObject) null, (JSONObject) null, jSONObject2, (JSONObject) null, j, z);
        }
    }

    public void logEventSync(String str) {
        logEventSync(str, (JSONObject) null);
    }

    public void logEventSync(String str, JSONObject jSONObject) {
        logEventSync(str, jSONObject, false);
    }

    public void logEventSync(String str, JSONObject jSONObject, boolean z) {
        logEventSync(str, jSONObject, (JSONObject) null, z);
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        logEventSync(str, jSONObject, jSONObject2, false);
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2, boolean z) {
        logEventSync(str, jSONObject, jSONObject2, getCurrentTimeMillis(), z);
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2, long j, boolean z) {
        if (validateLogEvent(str)) {
            logEvent(str, jSONObject, (JSONObject) null, (JSONObject) null, jSONObject2, (JSONObject) null, j, z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateLogEvent(String str) {
        if (!C0895Utils.isEmptyString(str)) {
            return contextAndApiKeySet("logEvent()");
        }
        logger.mo13082e(TAG, "Argument eventType cannot be null or blank in logEvent()");
        return false;
    }

    /* access modifiers changed from: protected */
    public void logEventAsync(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5, long j, boolean z) {
        final JSONObject cloneJSONObject = jSONObject != null ? C0895Utils.cloneJSONObject(jSONObject) : jSONObject;
        final JSONObject cloneJSONObject2 = jSONObject2 != null ? C0895Utils.cloneJSONObject(jSONObject2) : jSONObject2;
        final JSONObject cloneJSONObject3 = jSONObject3 != null ? C0895Utils.cloneJSONObject(jSONObject3) : jSONObject3;
        final JSONObject cloneJSONObject4 = jSONObject4 != null ? C0895Utils.cloneJSONObject(jSONObject4) : jSONObject4;
        final JSONObject cloneJSONObject5 = jSONObject5 != null ? C0895Utils.cloneJSONObject(jSONObject5) : jSONObject5;
        final String str2 = str;
        final long j2 = j;
        final boolean z2 = z;
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.logEvent(str2, cloneJSONObject, cloneJSONObject2, cloneJSONObject3, cloneJSONObject4, cloneJSONObject5, j2, z2);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public long logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5, long j, boolean z) {
        long j2;
        JSONObject jSONObject6;
        JSONObject jSONObject7;
        JSONObject jSONObject8;
        Location mostRecentLocation;
        String str2 = str;
        JSONObject jSONObject9 = jSONObject;
        JSONObject jSONObject10 = jSONObject3;
        JSONObject jSONObject11 = jSONObject4;
        JSONObject jSONObject12 = jSONObject5;
        long j3 = j;
        logger.mo13080d(TAG, "Logged event to Amplitude: " + str2);
        if (this.optOut) {
            return -1;
        }
        if (!(this.trackingSessionEvents && (str2.equals(START_SESSION_EVENT) || str2.equals(END_SESSION_EVENT))) && !z) {
            if (!this.inForeground) {
                startNewSessionIfNeeded(j3);
            } else {
                refreshSessionTime(j3);
            }
        }
        JSONObject jSONObject13 = new JSONObject();
        try {
            jSONObject13.put("event_type", replaceWithJSONNull(str));
            jSONObject13.put("timestamp", j3);
            jSONObject13.put(USER_ID_KEY, replaceWithJSONNull(this.userId));
            jSONObject13.put(DEVICE_ID_KEY, replaceWithJSONNull(this.deviceId));
            if (z) {
                j2 = -1;
            } else {
                j2 = this.sessionId;
            }
            jSONObject13.put("session_id", j2);
            jSONObject13.put("uuid", UUID.randomUUID().toString());
            jSONObject13.put(SEQUENCE_NUMBER_KEY, getNextSequenceNumber());
            if (this.appliedTrackingOptions.shouldTrackVersionName()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_VERSION_NAME, replaceWithJSONNull(this.deviceInfo.getVersionName()));
            }
            if (this.appliedTrackingOptions.shouldTrackOsName()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_OS_NAME, replaceWithJSONNull(this.deviceInfo.getOsName()));
            }
            if (this.appliedTrackingOptions.shouldTrackOsVersion()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_OS_VERSION, replaceWithJSONNull(this.deviceInfo.getOsVersion()));
            }
            if (this.appliedTrackingOptions.shouldTrackApiLevel()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_API_LEVEL, replaceWithJSONNull(Integer.valueOf(Build.VERSION.SDK_INT)));
            }
            if (this.appliedTrackingOptions.shouldTrackDeviceBrand()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND, replaceWithJSONNull(this.deviceInfo.getBrand()));
            }
            if (this.appliedTrackingOptions.shouldTrackDeviceManufacturer()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER, replaceWithJSONNull(this.deviceInfo.getManufacturer()));
            }
            if (this.appliedTrackingOptions.shouldTrackDeviceModel()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL, replaceWithJSONNull(this.deviceInfo.getModel()));
            }
            if (this.appliedTrackingOptions.shouldTrackCarrier()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_CARRIER, replaceWithJSONNull(this.deviceInfo.getCarrier()));
            }
            if (this.appliedTrackingOptions.shouldTrackCountry()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_COUNTRY, replaceWithJSONNull(this.deviceInfo.getCountry()));
            }
            if (this.appliedTrackingOptions.shouldTrackLanguage()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_LANGUAGE, replaceWithJSONNull(this.deviceInfo.getLanguage()));
            }
            if (this.appliedTrackingOptions.shouldTrackPlatform()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_PLATFORM, this.platform);
            }
            JSONObject jSONObject14 = new JSONObject();
            String str3 = this.libraryName;
            if (str3 == null) {
                str3 = Constants.LIBRARY_UNKNOWN;
            }
            jSONObject14.put("name", str3);
            String str4 = this.libraryVersion;
            if (str4 == null) {
                str4 = Constants.VERSION_UNKNOWN;
            }
            jSONObject14.put("version", str4);
            jSONObject13.put("library", jSONObject14);
            JSONObject jSONObject15 = jSONObject2 == null ? new JSONObject() : jSONObject2;
            JSONObject jSONObject16 = this.apiPropertiesTrackingOptions;
            if (jSONObject16 != null && jSONObject16.length() > 0) {
                jSONObject15.put("tracking_options", this.apiPropertiesTrackingOptions);
            }
            if (this.appliedTrackingOptions.shouldTrackLatLng() && (mostRecentLocation = this.deviceInfo.getMostRecentLocation()) != null) {
                JSONObject jSONObject17 = new JSONObject();
                jSONObject17.put("lat", mostRecentLocation.getLatitude());
                jSONObject17.put("lng", mostRecentLocation.getLongitude());
                jSONObject15.put(FirebaseAnalytics.Param.LOCATION, jSONObject17);
            }
            if (this.appliedTrackingOptions.shouldTrackAdid() && this.deviceInfo.getAdvertisingId() != null) {
                jSONObject15.put("androidADID", this.deviceInfo.getAdvertisingId());
            }
            jSONObject15.put("limit_ad_tracking", this.deviceInfo.isLimitAdTrackingEnabled());
            jSONObject15.put("gps_enabled", this.deviceInfo.isGooglePlayServicesEnabled());
            jSONObject13.put("api_properties", jSONObject15);
            if (jSONObject9 == null) {
                jSONObject6 = new JSONObject();
            } else {
                jSONObject6 = truncate(jSONObject9);
            }
            jSONObject13.put("event_properties", jSONObject6);
            if (jSONObject10 == null) {
                jSONObject7 = new JSONObject();
            } else {
                jSONObject7 = truncate(jSONObject10);
            }
            jSONObject13.put("user_properties", jSONObject7);
            jSONObject13.put("groups", jSONObject11 == null ? new JSONObject() : truncate(jSONObject11));
            if (jSONObject12 == null) {
                jSONObject8 = new JSONObject();
            } else {
                jSONObject8 = truncate(jSONObject12);
            }
            jSONObject13.put("group_properties", jSONObject8);
            return saveEvent(str2, jSONObject13);
        } catch (JSONException e) {
            logger.mo13082e(TAG, String.format("JSON Serialization of event type %s failed, skipping: %s", new Object[]{str2, e.toString()}));
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public long saveEvent(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (C0895Utils.isEmptyString(jSONObject2)) {
            logger.mo13082e(TAG, String.format("Detected empty event string for event type %s, skipping", new Object[]{str}));
            return -1;
        }
        if (str.equals(Constants.IDENTIFY_EVENT) || str.equals(Constants.GROUP_IDENTIFY_EVENT)) {
            long addIdentify = this.dbHelper.addIdentify(jSONObject2);
            this.lastIdentifyId = addIdentify;
            setLastIdentifyId(addIdentify);
        } else {
            long addEvent = this.dbHelper.addEvent(jSONObject2);
            this.lastEventId = addEvent;
            setLastEventId(addEvent);
        }
        int min = Math.min(Math.max(1, this.eventMaxCount / 10), 20);
        if (this.dbHelper.getEventCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper = this.dbHelper;
            databaseHelper.removeEvents(databaseHelper.getNthEventId((long) min));
        }
        if (this.dbHelper.getIdentifyCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper2 = this.dbHelper;
            databaseHelper2.removeIdentifys(databaseHelper2.getNthIdentifyId((long) min));
        }
        long totalEventCount = this.dbHelper.getTotalEventCount();
        int i = this.eventUploadThreshold;
        if (totalEventCount % ((long) i) != 0 || totalEventCount < ((long) i)) {
            updateServerLater(this.eventUploadPeriodMillis);
        } else {
            updateServer();
        }
        return (str.equals(Constants.IDENTIFY_EVENT) || str.equals(Constants.GROUP_IDENTIFY_EVENT)) ? this.lastIdentifyId : this.lastEventId;
    }

    private long getLongvalue(String str, long j) {
        Long longValue = this.dbHelper.getLongValue(str);
        return longValue == null ? j : longValue.longValue();
    }

    /* access modifiers changed from: package-private */
    public long getNextSequenceNumber() {
        long j = this.sequenceNumber + 1;
        this.sequenceNumber = j;
        this.dbHelper.insertOrReplaceKeyLongValue(SEQUENCE_NUMBER_KEY, Long.valueOf(j));
        return this.sequenceNumber;
    }

    /* access modifiers changed from: package-private */
    public void setLastEventTime(long j) {
        this.lastEventTime = j;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_EVENT_TIME_KEY, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public void setLastEventId(long j) {
        this.lastEventId = j;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_EVENT_ID_KEY, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public void setLastIdentifyId(long j) {
        this.lastIdentifyId = j;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_IDENTIFY_ID_KEY, Long.valueOf(j));
    }

    public long getSessionId() {
        return this.sessionId;
    }

    /* access modifiers changed from: package-private */
    public void setPreviousSessionId(long j) {
        this.previousSessionId = j;
        this.dbHelper.insertOrReplaceKeyLongValue(PREVIOUS_SESSION_ID_KEY, Long.valueOf(j));
    }

    public boolean startNewSessionIfNeeded(long j) {
        if (inSession()) {
            if (isWithinMinTimeBetweenSessions(j)) {
                refreshSessionTime(j);
                return false;
            }
            startNewSession(j);
            return true;
        } else if (isWithinMinTimeBetweenSessions(j)) {
            long j2 = this.previousSessionId;
            if (j2 == -1) {
                startNewSession(j);
                return true;
            }
            setSessionId(j2);
            refreshSessionTime(j);
            return false;
        } else {
            startNewSession(j);
            return true;
        }
    }

    private void startNewSession(long j) {
        if (this.trackingSessionEvents) {
            sendSessionEvent(END_SESSION_EVENT);
        }
        setSessionId(j);
        refreshSessionTime(j);
        if (this.trackingSessionEvents) {
            sendSessionEvent(START_SESSION_EVENT);
        }
    }

    private boolean inSession() {
        return this.sessionId >= 0;
    }

    private boolean isWithinMinTimeBetweenSessions(long j) {
        return j - this.lastEventTime < (this.usingForegroundTracking ? this.minTimeBetweenSessionsMillis : this.sessionTimeoutMillis);
    }

    /* access modifiers changed from: private */
    public void setSessionId(long j) {
        this.sessionId = j;
        setPreviousSessionId(j);
    }

    /* access modifiers changed from: package-private */
    public void refreshSessionTime(long j) {
        if (inSession()) {
            setLastEventTime(j);
        }
    }

    /* access modifiers changed from: private */
    public void sendSessionEvent(String str) {
        if (contextAndApiKeySet(String.format("sendSessionEvent('%s')", new Object[]{str})) && inSession()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("special", str);
                logEvent(str, (JSONObject) null, jSONObject, (JSONObject) null, (JSONObject) null, (JSONObject) null, this.lastEventTime, false);
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onExitForeground(final long j) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.refreshSessionTime(j);
                    boolean unused = AmplitudeClient.this.inForeground = false;
                    if (AmplitudeClient.this.flushEventsOnClose) {
                        AmplitudeClient.this.updateServer();
                    }
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.DEVICE_ID_KEY, AmplitudeClient.this.deviceId);
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.USER_ID_KEY, AmplitudeClient.this.userId);
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.OPT_OUT_KEY, Long.valueOf(AmplitudeClient.this.optOut ? 1 : 0));
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.PREVIOUS_SESSION_ID_KEY, Long.valueOf(AmplitudeClient.this.sessionId));
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.LAST_EVENT_TIME_KEY, Long.valueOf(AmplitudeClient.this.lastEventTime));
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onEnterForeground(final long j) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    if (AmplitudeClient.this.useDynamicConfig) {
                        ConfigManager.getInstance().refresh(new ConfigManager.RefreshListener() {
                            public void onFinished() {
                                AmplitudeClient.this.url = ConfigManager.getInstance().getIngestionEndpoint();
                            }
                        });
                    }
                    AmplitudeClient.this.startNewSessionIfNeeded(j);
                    boolean unused = AmplitudeClient.this.inForeground = true;
                }
            }
        });
    }

    public void logRevenue(double d) {
        logRevenue((String) null, 1, d);
    }

    public void logRevenue(String str, int i, double d) {
        logRevenue(str, i, d, (String) null, (String) null);
    }

    public void logRevenue(String str, int i, double d, String str2, String str3) {
        if (contextAndApiKeySet("logRevenue()")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("special", Constants.AMP_REVENUE_EVENT);
                String str4 = str;
                jSONObject.put("productId", str);
                int i2 = i;
                jSONObject.put(FirebaseAnalytics.Param.QUANTITY, i);
                jSONObject.put(FirebaseAnalytics.Param.PRICE, d);
                jSONObject.put("receipt", str2);
                jSONObject.put("receiptSig", str3);
            } catch (JSONException unused) {
            }
            logEventAsync(Constants.AMP_REVENUE_EVENT, (JSONObject) null, jSONObject, (JSONObject) null, (JSONObject) null, (JSONObject) null, getCurrentTimeMillis(), false);
        }
    }

    public void logRevenueV2(Revenue revenue) {
        if (contextAndApiKeySet("logRevenueV2()") && revenue != null && revenue.isValidRevenue()) {
            logEvent(Constants.AMP_REVENUE_EVENT, revenue.toJSONObject());
        }
    }

    public void setUserProperties(JSONObject jSONObject, boolean z) {
        setUserProperties(jSONObject);
    }

    public void setUserProperties(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0 && contextAndApiKeySet("setUserProperties")) {
            JSONObject truncate = truncate(jSONObject);
            if (truncate.length() != 0) {
                Identify identify = new Identify();
                Iterator<String> keys = truncate.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        identify.setUserProperty(next, truncate.get(next));
                    } catch (JSONException e) {
                        logger.mo13082e(TAG, e.toString());
                    }
                }
                identify(identify);
            }
        }
    }

    public void clearUserProperties() {
        identify(new Identify().clearAll());
    }

    public void identify(Identify identify) {
        identify(identify, false);
    }

    public void identify(Identify identify, boolean z) {
        if (identify != null && identify.userPropertiesOperations.length() != 0 && contextAndApiKeySet("identify()")) {
            logEventAsync(Constants.IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, identify.userPropertiesOperations, (JSONObject) null, (JSONObject) null, getCurrentTimeMillis(), z);
        }
    }

    public void setGroup(String str, Object obj) {
        JSONObject jSONObject;
        if (contextAndApiKeySet("setGroup()") && !C0895Utils.isEmptyString(str)) {
            try {
                jSONObject = new JSONObject().put(str, obj);
            } catch (JSONException e) {
                logger.mo13082e(TAG, e.toString());
                jSONObject = null;
            }
            logEventAsync(Constants.IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, new Identify().setUserProperty(str, obj).userPropertiesOperations, jSONObject, (JSONObject) null, getCurrentTimeMillis(), false);
        }
    }

    public void groupIdentify(String str, Object obj, Identify identify) {
        groupIdentify(str, obj, identify, false);
    }

    public void groupIdentify(String str, Object obj, Identify identify, boolean z) {
        JSONObject jSONObject;
        Identify identify2 = identify;
        if (identify2 == null || identify2.userPropertiesOperations.length() == 0) {
            return;
        }
        if (contextAndApiKeySet("groupIdentify()") && !C0895Utils.isEmptyString(str)) {
            try {
                String str2 = str;
                Object obj2 = obj;
                jSONObject = new JSONObject().put(str, obj);
            } catch (JSONException e) {
                logger.mo13082e(TAG, e.toString());
                jSONObject = null;
            }
            logEventAsync(Constants.GROUP_IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, (JSONObject) null, jSONObject, identify2.userPropertiesOperations, getCurrentTimeMillis(), z);
        }
    }

    public JSONObject truncate(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new JSONObject();
        }
        if (jSONObject.length() > 1000) {
            logger.mo13093w(TAG, "Warning: too many properties (more than 1000), ignoring");
            return new JSONObject();
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (!next.equals(Constants.AMP_REVENUE_RECEIPT)) {
                    if (!next.equals(Constants.AMP_REVENUE_RECEIPT_SIG)) {
                        if (obj.getClass().equals(String.class)) {
                            jSONObject.put(next, truncate((String) obj));
                        } else if (obj.getClass().equals(JSONObject.class)) {
                            jSONObject.put(next, truncate((JSONObject) obj));
                        } else if (obj.getClass().equals(JSONArray.class)) {
                            jSONObject.put(next, truncate((JSONArray) obj));
                        }
                    }
                }
                jSONObject.put(next, obj);
            } catch (JSONException e) {
                logger.mo13082e(TAG, e.toString());
            }
        }
        return jSONObject;
    }

    public JSONArray truncate(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return new JSONArray();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj.getClass().equals(String.class)) {
                jSONArray.put(i, truncate((String) obj));
            } else if (obj.getClass().equals(JSONObject.class)) {
                jSONArray.put(i, truncate((JSONObject) obj));
            } else if (obj.getClass().equals(JSONArray.class)) {
                jSONArray.put(i, truncate((JSONArray) obj));
            }
        }
        return jSONArray;
    }

    public static String truncate(String str) {
        return str.length() <= 1024 ? str : str.substring(0, 1024);
    }

    public String getUserId() {
        return this.userId;
    }

    public AmplitudeClient setUserId(String str) {
        return setUserId(str, false);
    }

    public AmplitudeClient setUserId(final String str, final boolean z) {
        if (!contextAndApiKeySet("setUserId()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(this.apiKey)) {
                    if (z && AmplitudeClient.this.trackingSessionEvents) {
                        AmplitudeClient.this.sendSessionEvent(AmplitudeClient.END_SESSION_EVENT);
                    }
                    this.userId = str;
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.USER_ID_KEY, str);
                    if (z) {
                        long currentTimeMillis = AmplitudeClient.this.getCurrentTimeMillis();
                        AmplitudeClient.this.setSessionId(currentTimeMillis);
                        AmplitudeClient.this.refreshSessionTime(currentTimeMillis);
                        if (AmplitudeClient.this.trackingSessionEvents) {
                            AmplitudeClient.this.sendSessionEvent(AmplitudeClient.START_SESSION_EVENT);
                        }
                    }
                }
            }
        });
        return this;
    }

    public AmplitudeClient setDeviceId(final String str) {
        Set<String> invalidDeviceIds = getInvalidDeviceIds();
        if (contextAndApiKeySet("setDeviceId()") && !C0895Utils.isEmptyString(str) && !invalidDeviceIds.contains(str)) {
            runOnLogThread(new Runnable() {
                public void run() {
                    if (!C0895Utils.isEmptyString(this.apiKey)) {
                        this.deviceId = str;
                        AmplitudeClient.this.saveDeviceId(str);
                    }
                }
            });
        }
        return this;
    }

    public AmplitudeClient regenerateDeviceId() {
        if (!contextAndApiKeySet("regenerateDeviceId()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!C0895Utils.isEmptyString(this.apiKey)) {
                    AmplitudeClient.this.setDeviceId(DeviceInfo.generateUUID() + "R");
                }
            }
        });
        return this;
    }

    public void uploadEvents() {
        if (contextAndApiKeySet("uploadEvents()")) {
            this.logThread.post(new Runnable() {
                public void run() {
                    if (!C0895Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                        AmplitudeClient.this.updateServer();
                    }
                }
            });
        }
    }

    private void updateServerLater(long j) {
        if (!this.updateScheduled.getAndSet(true)) {
            this.logThread.postDelayed(new Runnable() {
                public void run() {
                    AmplitudeClient.this.updateScheduled.set(false);
                    AmplitudeClient.this.updateServer();
                }
            }, j);
        }
    }

    /* access modifiers changed from: protected */
    public void updateServer() {
        updateServer(false);
    }

    /* access modifiers changed from: protected */
    public void updateServer(boolean z) {
        if (!this.optOut && !this.offline && !this.uploadingCurrently.getAndSet(true)) {
            long min = Math.min((long) (z ? this.backoffUploadBatchSize : this.eventUploadMaxBatchSize), this.dbHelper.getTotalEventCount());
            if (min <= 0) {
                this.uploadingCurrently.set(false);
                return;
            }
            try {
                Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys = mergeEventsAndIdentifys(this.dbHelper.getEvents(this.lastEventId, min), this.dbHelper.getIdentifys(this.lastIdentifyId, min), min);
                if (((JSONArray) mergeEventsAndIdentifys.second).length() == 0) {
                    this.uploadingCurrently.set(false);
                    return;
                }
                final long longValue = ((Long) ((Pair) mergeEventsAndIdentifys.first).first).longValue();
                final long longValue2 = ((Long) ((Pair) mergeEventsAndIdentifys.first).second).longValue();
                final String jSONArray = ((JSONArray) mergeEventsAndIdentifys.second).toString();
                this.httpThread.post(new Runnable() {
                    public void run() {
                        AmplitudeClient amplitudeClient = AmplitudeClient.this;
                        amplitudeClient.makeEventUploadPostRequest(amplitudeClient.callFactory, jSONArray, longValue, longValue2);
                    }
                });
            } catch (JSONException e) {
                this.uploadingCurrently.set(false);
                logger.mo13082e(TAG, e.toString());
            } catch (CursorWindowAllocationException e2) {
                this.uploadingCurrently.set(false);
                logger.mo13082e(TAG, String.format("Caught Cursor window exception during event upload, deferring upload: %s", new Object[]{e2.getMessage()}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys(List<JSONObject> list, List<JSONObject> list2, long j) throws JSONException {
        long j2;
        long j3;
        List<JSONObject> list3 = list;
        List<JSONObject> list4 = list2;
        JSONArray jSONArray = new JSONArray();
        long j4 = -1;
        long j5 = -1;
        while (true) {
            if (((long) jSONArray.length()) >= j) {
                break;
            }
            boolean isEmpty = list.isEmpty();
            boolean isEmpty2 = list2.isEmpty();
            if (isEmpty && isEmpty2) {
                logger.mo13093w(TAG, String.format("mergeEventsAndIdentifys: number of events and identifys less than expected by %d", new Object[]{Long.valueOf(j - ((long) jSONArray.length()))}));
                break;
            }
            if (isEmpty2) {
                JSONObject remove = list.remove(0);
                j2 = remove.getLong("event_id");
                jSONArray.put(remove);
            } else {
                if (isEmpty) {
                    JSONObject remove2 = list4.remove(0);
                    j3 = remove2.getLong("event_id");
                    jSONArray.put(remove2);
                } else if (!list.get(0).has(SEQUENCE_NUMBER_KEY) || list.get(0).getLong(SEQUENCE_NUMBER_KEY) < list4.get(0).getLong(SEQUENCE_NUMBER_KEY)) {
                    JSONObject remove3 = list.remove(0);
                    j2 = remove3.getLong("event_id");
                    jSONArray.put(remove3);
                } else {
                    JSONObject remove4 = list4.remove(0);
                    j3 = remove4.getLong("event_id");
                    jSONArray.put(remove4);
                }
                j5 = j3;
            }
            j4 = j2;
        }
        return new Pair<>(new Pair(Long.valueOf(j4), Long.valueOf(j5)), jSONArray);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void makeEventUploadPostRequest(okhttp3.Call.Factory r10, java.lang.String r11, long r12, long r14) {
        /*
            r9 = this;
            java.lang.String r0 = "Exception:"
            java.lang.String r1 = "2"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            long r4 = r9.getCurrentTimeMillis()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.append(r1)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r5 = r9.apiKey     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.append(r5)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.append(r11)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.append(r2)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r4 = r4.toString()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            com.amplitude.security.MD5 r5 = new com.amplitude.security.MD5     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r5.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r6 = "UTF-8"
            byte[] r4 = r4.getBytes(r6)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            byte[] r4 = r5.digest(r4)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r3 = r9.bytesToHexString(r4)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            goto L_0x0050
        L_0x0044:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger
            java.lang.String r6 = TAG
            java.lang.String r4 = r4.toString()
            r5.mo13082e(r6, r4)
        L_0x0050:
            okhttp3.FormBody$Builder r4 = new okhttp3.FormBody$Builder
            r4.<init>()
            java.lang.String r5 = "v"
            okhttp3.FormBody$Builder r1 = r4.add(r5, r1)
            java.lang.String r4 = r9.apiKey
            java.lang.String r5 = "client"
            okhttp3.FormBody$Builder r1 = r1.add(r5, r4)
            java.lang.String r4 = "e"
            okhttp3.FormBody$Builder r11 = r1.add(r4, r11)
            java.lang.String r1 = "upload_time"
            okhttp3.FormBody$Builder r11 = r11.add(r1, r2)
            java.lang.String r1 = "checksum"
            okhttp3.FormBody$Builder r11 = r11.add(r1, r3)
            okhttp3.FormBody r11 = r11.build()
            r1 = 0
            okhttp3.Request$Builder r2 = new okhttp3.Request$Builder     // Catch:{ IllegalArgumentException -> 0x01c7 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x01c7 }
            java.lang.String r3 = r9.url     // Catch:{ IllegalArgumentException -> 0x01c7 }
            okhttp3.Request$Builder r2 = r2.url((java.lang.String) r3)     // Catch:{ IllegalArgumentException -> 0x01c7 }
            okhttp3.Request$Builder r11 = r2.post(r11)     // Catch:{ IllegalArgumentException -> 0x01c7 }
            java.lang.String r2 = r9.bearerToken     // Catch:{ IllegalArgumentException -> 0x01c7 }
            boolean r2 = com.amplitude.api.C0895Utils.isEmptyString(r2)     // Catch:{ IllegalArgumentException -> 0x01c7 }
            if (r2 != 0) goto L_0x00a9
            java.lang.String r2 = "Authorization"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x01c7 }
            r3.<init>()     // Catch:{ IllegalArgumentException -> 0x01c7 }
            java.lang.String r4 = "Bearer "
            r3.append(r4)     // Catch:{ IllegalArgumentException -> 0x01c7 }
            java.lang.String r4 = r9.bearerToken     // Catch:{ IllegalArgumentException -> 0x01c7 }
            r3.append(r4)     // Catch:{ IllegalArgumentException -> 0x01c7 }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x01c7 }
            r11.addHeader(r2, r3)     // Catch:{ IllegalArgumentException -> 0x01c7 }
        L_0x00a9:
            okhttp3.Request r11 = r11.build()     // Catch:{ IllegalArgumentException -> 0x01c7 }
            r2 = 1
            okhttp3.Call r10 = r10.newCall(r11)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            okhttp3.Response r10 = r10.execute()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            okhttp3.ResponseBody r11 = r10.body()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = r11.string()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r3 = "success"
            boolean r3 = r11.equals(r3)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r3 == 0) goto L_0x00e5
            com.amplitude.api.WorkerThread r10 = r9.logThread     // Catch:{ ConnectException -> 0x00e2, UnknownHostException -> 0x00df, IOException -> 0x00dc, AssertionError -> 0x00d9, Exception -> 0x00d6 }
            com.amplitude.api.AmplitudeClient$13 r11 = new com.amplitude.api.AmplitudeClient$13     // Catch:{ ConnectException -> 0x00e2, UnknownHostException -> 0x00df, IOException -> 0x00dc, AssertionError -> 0x00d9, Exception -> 0x00d6 }
            r3 = r11
            r4 = r9
            r5 = r12
            r7 = r14
            r3.<init>(r5, r7)     // Catch:{ ConnectException -> 0x00e2, UnknownHostException -> 0x00df, IOException -> 0x00dc, AssertionError -> 0x00d9, Exception -> 0x00d6 }
            r10.post(r11)     // Catch:{ ConnectException -> 0x00e2, UnknownHostException -> 0x00df, IOException -> 0x00dc, AssertionError -> 0x00d9, Exception -> 0x00d6 }
            goto L_0x01bf
        L_0x00d6:
            r10 = move-exception
            goto L_0x0190
        L_0x00d9:
            r10 = move-exception
            goto L_0x019c
        L_0x00dc:
            r10 = move-exception
            goto L_0x01a8
        L_0x00df:
            r10 = move-exception
            goto L_0x01b8
        L_0x00e2:
            r10 = move-exception
            goto L_0x01bd
        L_0x00e5:
            java.lang.String r3 = "invalid_api_key"
            boolean r3 = r11.equals(r3)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r3 == 0) goto L_0x00f8
            com.amplitude.api.AmplitudeLog r10 = logger     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = TAG     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r12 = "Invalid API key, make sure your API key is correct in initialize()"
            r10.mo13082e(r11, r12)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            goto L_0x018c
        L_0x00f8:
            java.lang.String r3 = "bad_checksum"
            boolean r3 = r11.equals(r3)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r3 == 0) goto L_0x010b
            com.amplitude.api.AmplitudeLog r10 = logger     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = TAG     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r12 = "Bad checksum, post request was mangled in transit, will attempt to reupload later"
            r10.mo13093w((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            goto L_0x018c
        L_0x010b:
            java.lang.String r3 = "request_db_write_failed"
            boolean r3 = r11.equals(r3)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r3 == 0) goto L_0x011d
            com.amplitude.api.AmplitudeLog r10 = logger     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = TAG     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r12 = "Couldn't write to request database on server, will attempt to reupload later"
            r10.mo13093w((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            goto L_0x018c
        L_0x011d:
            int r10 = r10.code()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r3 = 413(0x19d, float:5.79E-43)
            if (r10 != r3) goto L_0x016f
            boolean r10 = r9.backoffUpload     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r10 == 0) goto L_0x0141
            int r10 = r9.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            if (r10 != r2) goto L_0x0141
            r10 = 0
            int r3 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r3 < 0) goto L_0x0138
            com.amplitude.api.DatabaseHelper r3 = r9.dbHelper     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r3.removeEvent(r12)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
        L_0x0138:
            int r12 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x0141
            com.amplitude.api.DatabaseHelper r10 = r9.dbHelper     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r10.removeIdentify(r14)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
        L_0x0141:
            r9.backoffUpload = r2     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            com.amplitude.api.DatabaseHelper r10 = r9.dbHelper     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            long r10 = r10.getEventCount()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            int r11 = (int) r10     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            int r10 = r9.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            int r10 = java.lang.Math.min(r11, r10)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            double r10 = (double) r10     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r12 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 / r12
            double r10 = java.lang.Math.ceil(r10)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            int r10 = (int) r10     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r9.backoffUploadBatchSize = r10     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            com.amplitude.api.AmplitudeLog r10 = logger     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = TAG     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r12 = "Request too large, will decrease size and attempt to reupload"
            r10.mo13093w((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            com.amplitude.api.WorkerThread r10 = r9.logThread     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            com.amplitude.api.AmplitudeClient$14 r11 = new com.amplitude.api.AmplitudeClient$14     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r11.<init>()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r10.post(r11)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            goto L_0x018c
        L_0x016f:
            com.amplitude.api.AmplitudeLog r10 = logger     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r12 = TAG     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r13.<init>()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r14 = "Upload failed, "
            r13.append(r14)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r13.append(r11)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = ", will attempt to reupload later"
            r13.append(r11)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            java.lang.String r11 = r13.toString()     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
            r10.mo13093w((java.lang.String) r12, (java.lang.String) r11)     // Catch:{ ConnectException -> 0x01bb, UnknownHostException -> 0x01b6, IOException -> 0x01a6, AssertionError -> 0x019a, Exception -> 0x018e }
        L_0x018c:
            r2 = 0
            goto L_0x01bf
        L_0x018e:
            r10 = move-exception
            r2 = 0
        L_0x0190:
            com.amplitude.api.AmplitudeLog r11 = logger
            java.lang.String r12 = TAG
            r11.mo13083e(r12, r0, r10)
            r9.lastError = r10
            goto L_0x01bf
        L_0x019a:
            r10 = move-exception
            r2 = 0
        L_0x019c:
            com.amplitude.api.AmplitudeLog r11 = logger
            java.lang.String r12 = TAG
            r11.mo13083e(r12, r0, r10)
            r9.lastError = r10
            goto L_0x01bf
        L_0x01a6:
            r10 = move-exception
            r2 = 0
        L_0x01a8:
            com.amplitude.api.AmplitudeLog r11 = logger
            java.lang.String r12 = TAG
            java.lang.String r13 = r10.toString()
            r11.mo13082e(r12, r13)
            r9.lastError = r10
            goto L_0x01bf
        L_0x01b6:
            r10 = move-exception
            r2 = 0
        L_0x01b8:
            r9.lastError = r10
            goto L_0x01bf
        L_0x01bb:
            r10 = move-exception
            r2 = 0
        L_0x01bd:
            r9.lastError = r10
        L_0x01bf:
            if (r2 != 0) goto L_0x01c6
            java.util.concurrent.atomic.AtomicBoolean r10 = r9.uploadingCurrently
            r10.set(r1)
        L_0x01c6:
            return
        L_0x01c7:
            r10 = move-exception
            com.amplitude.api.AmplitudeLog r11 = logger
            java.lang.String r12 = TAG
            java.lang.String r10 = r10.toString()
            r11.mo13082e(r12, r10)
            java.util.concurrent.atomic.AtomicBoolean r10 = r9.uploadingCurrently
            r10.set(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.AmplitudeClient.makeEventUploadPostRequest(okhttp3.Call$Factory, java.lang.String, long, long):void");
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    private Set<String> getInvalidDeviceIds() {
        HashSet hashSet = new HashSet();
        hashSet.add("");
        hashSet.add("9774d56d682e549c");
        hashSet.add(EnvironmentCompat.MEDIA_UNKNOWN);
        hashSet.add("000000000000000");
        hashSet.add(Constants.PLATFORM);
        hashSet.add("DEFACE");
        hashSet.add("00000000-0000-0000-0000-000000000000");
        return hashSet;
    }

    private String initializeDeviceId() {
        Set<String> invalidDeviceIds = getInvalidDeviceIds();
        String value = this.dbHelper.getValue(DEVICE_ID_KEY);
        String stringFromSharedPreferences = C0895Utils.getStringFromSharedPreferences(this.context, this.instanceName, DEVICE_ID_KEY);
        if (!C0895Utils.isEmptyString(value) && !invalidDeviceIds.contains(value)) {
            if (!value.equals(stringFromSharedPreferences)) {
                saveDeviceId(value);
            }
            return value;
        } else if (C0895Utils.isEmptyString(stringFromSharedPreferences) || invalidDeviceIds.contains(stringFromSharedPreferences)) {
            if (!this.newDeviceIdPerInstall && this.useAdvertisingIdForDeviceId && !this.deviceInfo.isLimitAdTrackingEnabled()) {
                String advertisingId = this.deviceInfo.getAdvertisingId();
                if (!C0895Utils.isEmptyString(advertisingId) && !invalidDeviceIds.contains(advertisingId)) {
                    saveDeviceId(advertisingId);
                    return advertisingId;
                }
            }
            String str = DeviceInfo.generateUUID() + "R";
            saveDeviceId(str);
            return str;
        } else {
            saveDeviceId(stringFromSharedPreferences);
            return stringFromSharedPreferences;
        }
    }

    /* access modifiers changed from: private */
    public void saveDeviceId(String str) {
        this.dbHelper.insertOrReplaceKeyValue(DEVICE_ID_KEY, str);
        C0895Utils.writeStringToSharedPreferences(this.context, this.instanceName, DEVICE_ID_KEY, str);
    }

    /* access modifiers changed from: protected */
    public void runOnLogThread(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        WorkerThread workerThread = this.logThread;
        if (currentThread != workerThread) {
            workerThread.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public Object replaceWithJSONNull(Object obj) {
        return obj == null ? JSONObject.NULL : obj;
    }

    /* access modifiers changed from: protected */
    public synchronized boolean contextAndApiKeySet(String str) {
        if (this.context == null) {
            AmplitudeLog amplitudeLog = logger;
            String str2 = TAG;
            amplitudeLog.mo13082e(str2, "context cannot be null, set context with initialize() before calling " + str);
            return false;
        } else if (!C0895Utils.isEmptyString(this.apiKey)) {
            return true;
        } else {
            AmplitudeLog amplitudeLog2 = logger;
            String str3 = TAG;
            amplitudeLog2.mo13082e(str3, "apiKey cannot be null or empty, set apiKey with initialize() before calling " + str);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public String bytesToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & UByte.MAX_VALUE;
            int i2 = i * 2;
            cArr2[i2] = cArr[b >>> 4];
            cArr2[i2 + 1] = cArr[b & 15];
        }
        return new String(cArr2);
    }

    static boolean upgradePrefs(Context context2) {
        return upgradePrefs(context2, (String) null, (String) null);
    }

    static boolean upgradePrefs(Context context2, String str, String str2) {
        if (str == null) {
            try {
                str = Constants.class.getPackage().getName();
            } catch (Exception unused) {
                str = "com.amplitude.api";
            }
        }
        if (str2 == null) {
            str2 = "com.amplitude.api";
        }
        try {
            if (str2.equals(str)) {
                return false;
            }
            String str3 = str + "." + context2.getPackageName();
            SharedPreferences sharedPreferences = context2.getSharedPreferences(str3, 0);
            if (sharedPreferences.getAll().size() == 0) {
                return false;
            }
            String str4 = str2 + "." + context2.getPackageName();
            SharedPreferences.Editor edit = context2.getSharedPreferences(str4, 0).edit();
            if (sharedPreferences.contains(str + ".previousSessionId")) {
                edit.putLong(Constants.PREFKEY_PREVIOUS_SESSION_ID, sharedPreferences.getLong(str + ".previousSessionId", -1));
            }
            if (sharedPreferences.contains(str + ".deviceId")) {
                edit.putString(Constants.PREFKEY_DEVICE_ID, sharedPreferences.getString(str + ".deviceId", (String) null));
            }
            if (sharedPreferences.contains(str + ".userId")) {
                edit.putString(Constants.PREFKEY_USER_ID, sharedPreferences.getString(str + ".userId", (String) null));
            }
            if (sharedPreferences.contains(str + ".optOut")) {
                edit.putBoolean(Constants.PREFKEY_OPT_OUT, sharedPreferences.getBoolean(str + ".optOut", false));
            }
            edit.apply();
            sharedPreferences.edit().clear().apply();
            logger.mo13085i(TAG, "Upgraded shared preferences from " + str3 + " to " + str4);
            return true;
        } catch (Exception e) {
            logger.mo13083e(TAG, "Error upgrading shared preferences", e);
            return false;
        }
    }

    static boolean upgradeSharedPrefsToDB(Context context2) {
        return upgradeSharedPrefsToDB(context2, (String) null);
    }

    static boolean upgradeSharedPrefsToDB(Context context2, String str) {
        if (str == null) {
            str = "com.amplitude.api";
        }
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper(context2);
        String value = databaseHelper.getValue(DEVICE_ID_KEY);
        Long longValue = databaseHelper.getLongValue(PREVIOUS_SESSION_ID_KEY);
        Long longValue2 = databaseHelper.getLongValue(LAST_EVENT_TIME_KEY);
        if (!C0895Utils.isEmptyString(value) && longValue != null && longValue2 != null) {
            return true;
        }
        SharedPreferences sharedPreferences = context2.getSharedPreferences(str + "." + context2.getPackageName(), 0);
        migrateStringValue(sharedPreferences, Constants.PREFKEY_DEVICE_ID, (String) null, databaseHelper, DEVICE_ID_KEY);
        SharedPreferences sharedPreferences2 = sharedPreferences;
        DatabaseHelper databaseHelper2 = databaseHelper;
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_EVENT_TIME, -1, databaseHelper2, LAST_EVENT_TIME_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_EVENT_ID, -1, databaseHelper2, LAST_EVENT_ID_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_IDENTIFY_ID, -1, databaseHelper2, LAST_IDENTIFY_ID_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_PREVIOUS_SESSION_ID, -1, databaseHelper2, PREVIOUS_SESSION_ID_KEY);
        migrateStringValue(sharedPreferences, Constants.PREFKEY_USER_ID, (String) null, databaseHelper, USER_ID_KEY);
        migrateBooleanValue(sharedPreferences, Constants.PREFKEY_OPT_OUT, false, databaseHelper, OPT_OUT_KEY);
        return true;
    }

    private static void migrateLongValue(SharedPreferences sharedPreferences, String str, long j, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getLong(str, j)));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    private static void migrateStringValue(SharedPreferences sharedPreferences, String str, String str2, DatabaseHelper databaseHelper, String str3) {
        if (C0895Utils.isEmptyString(databaseHelper.getValue(str3))) {
            String string = sharedPreferences.getString(str, str2);
            if (!C0895Utils.isEmptyString(string)) {
                databaseHelper.insertOrReplaceKeyValue(str3, string);
                sharedPreferences.edit().remove(str).apply();
            }
        }
    }

    private static void migrateBooleanValue(SharedPreferences sharedPreferences, String str, boolean z, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getBoolean(str, z) ? 1 : 0));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    /* access modifiers changed from: protected */
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
