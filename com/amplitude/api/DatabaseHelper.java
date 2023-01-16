package com.amplitude.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_IDENTIFYS_TABLE = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_LONG_STORE_TABLE = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);";
    private static final String CREATE_STORE_TABLE = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);";
    private static final String EVENT_FIELD = "event";
    protected static final String EVENT_TABLE_NAME = "events";
    protected static final String IDENTIFY_TABLE_NAME = "identifys";
    private static final String ID_FIELD = "id";
    private static final String KEY_FIELD = "key";
    protected static final String LONG_STORE_TABLE_NAME = "long_store";
    protected static final String STORE_TABLE_NAME = "store";
    private static final String TAG = "com.amplitude.api.DatabaseHelper";
    private static final String VALUE_FIELD = "value";
    static final Map<String, DatabaseHelper> instances = new HashMap();
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    private boolean callResetListenerOnDatabaseReset;
    private DatabaseResetListener databaseResetListener;
    File file;
    private String instanceName;

    @Deprecated
    static DatabaseHelper getDatabaseHelper(Context context) {
        return getDatabaseHelper(context, (String) null);
    }

    static synchronized DatabaseHelper getDatabaseHelper(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            String normalizeInstanceName = C0895Utils.normalizeInstanceName(str);
            Map<String, DatabaseHelper> map = instances;
            databaseHelper = map.get(normalizeInstanceName);
            if (databaseHelper == null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext(), normalizeInstanceName);
                map.put(normalizeInstanceName, databaseHelper);
            }
        }
        return databaseHelper;
    }

    private static String getDatabaseName(String str) {
        if (C0895Utils.isEmptyString(str) || str.equals(Constants.DEFAULT_INSTANCE)) {
            return "com.amplitude.api";
        }
        return "com.amplitude.api_" + str;
    }

    protected DatabaseHelper(Context context) {
        this(context, (String) null);
    }

    protected DatabaseHelper(Context context, String str) {
        super(context, getDatabaseName(str), (SQLiteDatabase.CursorFactory) null, 3);
        this.callResetListenerOnDatabaseReset = true;
        this.file = context.getDatabasePath(getDatabaseName(str));
        this.instanceName = C0895Utils.normalizeInstanceName(str);
    }

    /* access modifiers changed from: package-private */
    public void setDatabaseResetListener(DatabaseResetListener databaseResetListener2) {
        this.databaseResetListener = databaseResetListener2;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
        DatabaseResetListener databaseResetListener2 = this.databaseResetListener;
        if (databaseResetListener2 != null && this.callResetListenerOnDatabaseReset) {
            try {
                this.callResetListenerOnDatabaseReset = false;
                databaseResetListener2.onDatabaseReset(sQLiteDatabase);
            } catch (SQLiteException e) {
                logger.mo13083e(TAG, String.format("databaseReset callback failed during onCreate", new Object[0]), e);
            } catch (Throwable th) {
                this.callResetListenerOnDatabaseReset = true;
                throw th;
            }
            this.callResetListenerOnDatabaseReset = true;
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i > i2) {
            logger.mo13082e(TAG, "onUpgrade() with invalid oldVersion and newVersion");
            resetDatabase(sQLiteDatabase);
        } else if (i2 > 1) {
            if (i == 1) {
                sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
                if (i2 <= 2) {
                    return;
                }
            } else if (i != 2) {
                if (i != 3) {
                    AmplitudeLog amplitudeLog = logger;
                    String str = TAG;
                    amplitudeLog.mo13082e(str, "onUpgrade() with unknown oldVersion " + i);
                    resetDatabase(sQLiteDatabase);
                    return;
                }
                return;
            }
            sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
            sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        }
    }

    private void resetDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValue(String str, String str2) {
        long j;
        if (str2 == null) {
            j = deleteKeyFromTable(STORE_TABLE_NAME, str);
        } else {
            j = insertOrReplaceKeyValueToTable(STORE_TABLE_NAME, str, str2);
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyLongValue(String str, Long l) {
        long j;
        if (l == null) {
            j = deleteKeyFromTable(LONG_STORE_TABLE_NAME, str);
        } else {
            j = insertOrReplaceKeyValueToTable(LONG_STORE_TABLE_NAME, str, l);
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r2.isOpen() != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r2.isOpen() != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long insertOrReplaceKeyValueToTable(java.lang.String r6, java.lang.String r7, java.lang.Object r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x003b, StackOverflowError -> 0x001a }
            long r6 = r5.insertOrReplaceKeyValueToTable(r2, r6, r7, r8)     // Catch:{ SQLiteException -> 0x003b, StackOverflowError -> 0x001a }
            if (r2 == 0) goto L_0x005b
            boolean r8 = r2.isOpen()     // Catch:{ all -> 0x0069 }
            if (r8 == 0) goto L_0x005b
            r5.close()     // Catch:{ all -> 0x0069 }
            goto L_0x005b
        L_0x0018:
            r6 = move-exception
            goto L_0x005d
        L_0x001a:
            r7 = move-exception
            com.amplitude.api.AmplitudeLog r8 = logger     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0018 }
            r1[r0] = r6     // Catch:{ all -> 0x0018 }
            java.lang.String r6 = java.lang.String.format(r4, r1)     // Catch:{ all -> 0x0018 }
            r8.mo13083e(r3, r6, r7)     // Catch:{ all -> 0x0018 }
            r5.delete()     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0059
            boolean r6 = r2.isOpen()     // Catch:{ all -> 0x0069 }
            if (r6 == 0) goto L_0x0059
        L_0x0037:
            r5.close()     // Catch:{ all -> 0x0069 }
            goto L_0x0059
        L_0x003b:
            r7 = move-exception
            com.amplitude.api.AmplitudeLog r8 = logger     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0018 }
            r1[r0] = r6     // Catch:{ all -> 0x0018 }
            java.lang.String r6 = java.lang.String.format(r4, r1)     // Catch:{ all -> 0x0018 }
            r8.mo13083e(r3, r6, r7)     // Catch:{ all -> 0x0018 }
            r5.delete()     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0059
            boolean r6 = r2.isOpen()     // Catch:{ all -> 0x0069 }
            if (r6 == 0) goto L_0x0059
            goto L_0x0037
        L_0x0059:
            r6 = -1
        L_0x005b:
            monitor-exit(r5)
            return r6
        L_0x005d:
            if (r2 == 0) goto L_0x0068
            boolean r7 = r2.isOpen()     // Catch:{ all -> 0x0069 }
            if (r7 == 0) goto L_0x0068
            r5.close()     // Catch:{ all -> 0x0069 }
        L_0x0068:
            throw r6     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.insertOrReplaceKeyValueToTable(java.lang.String, java.lang.String, java.lang.Object):long");
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValueToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, Object obj) throws SQLiteException, StackOverflowError {
        long insertKeyValueContentValuesIntoTable;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIELD, str2);
        if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            contentValues.put("value", (String) obj);
        }
        insertKeyValueContentValuesIntoTable = insertKeyValueContentValuesIntoTable(sQLiteDatabase, str, contentValues);
        if (insertKeyValueContentValuesIntoTable == -1) {
            logger.mo13093w(TAG, "Insert failed");
        }
        return insertKeyValueContentValuesIntoTable;
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertKeyValueContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
    }

    /* access modifiers changed from: package-private */
    public synchronized long deleteKeyFromTable(String str, String str2) {
        long j;
        try {
            j = (long) getWritableDatabase().delete(str, "key=?", new String[]{str2});
            close();
        } catch (SQLiteException e) {
            logger.mo13083e(TAG, String.format("deleteKey from %s failed", new Object[]{str}), e);
            delete();
            close();
            j = -1;
            return j;
        } catch (StackOverflowError e2) {
            try {
                logger.mo13083e(TAG, String.format("deleteKey from %s failed", new Object[]{str}), e2);
                delete();
                close();
                j = -1;
                return j;
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long addEvent(String str) {
        return addEventToTable(EVENT_TABLE_NAME, str);
    }

    /* access modifiers changed from: package-private */
    public synchronized long addIdentify(String str) {
        return addEventToTable(IDENTIFY_TABLE_NAME, str);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x0033=Splitter:B:12:0x0033, B:20:0x0064=Splitter:B:20:0x0064} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long addEventToTable(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -1
            r2 = 0
            r3 = 1
            android.database.sqlite.SQLiteDatabase r4 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x004f, StackOverflowError -> 0x0039 }
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x004f, StackOverflowError -> 0x0039 }
            r5.<init>()     // Catch:{ SQLiteException -> 0x004f, StackOverflowError -> 0x0039 }
            java.lang.String r6 = "event"
            r5.put(r6, r9)     // Catch:{ SQLiteException -> 0x004f, StackOverflowError -> 0x0039 }
            long r4 = r7.insertEventContentValuesIntoTable(r4, r8, r5)     // Catch:{ SQLiteException -> 0x004f, StackOverflowError -> 0x0039 }
            int r9 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r9 != 0) goto L_0x0033
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            java.lang.String r0 = TAG     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            java.lang.String r1 = "Insert into %s failed"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            r6[r2] = r8     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            java.lang.String r1 = java.lang.String.format(r1, r6)     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            r9.mo13093w((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ SQLiteException -> 0x0030, StackOverflowError -> 0x002d }
            goto L_0x0033
        L_0x002d:
            r9 = move-exception
            r0 = r4
            goto L_0x003a
        L_0x0030:
            r9 = move-exception
            r0 = r4
            goto L_0x0050
        L_0x0033:
            r7.close()     // Catch:{ all -> 0x006e }
            goto L_0x0068
        L_0x0037:
            r8 = move-exception
            goto L_0x006a
        L_0x0039:
            r9 = move-exception
        L_0x003a:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x0037 }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = "addEvent to %s failed"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0037 }
            r3[r2] = r8     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = java.lang.String.format(r6, r3)     // Catch:{ all -> 0x0037 }
            r4.mo13083e(r5, r8, r9)     // Catch:{ all -> 0x0037 }
            r7.delete()     // Catch:{ all -> 0x0037 }
            goto L_0x0064
        L_0x004f:
            r9 = move-exception
        L_0x0050:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x0037 }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = "addEvent to %s failed"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0037 }
            r3[r2] = r8     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = java.lang.String.format(r6, r3)     // Catch:{ all -> 0x0037 }
            r4.mo13083e(r5, r8, r9)     // Catch:{ all -> 0x0037 }
            r7.delete()     // Catch:{ all -> 0x0037 }
        L_0x0064:
            r7.close()     // Catch:{ all -> 0x006e }
            r4 = r0
        L_0x0068:
            monitor-exit(r7)
            return r4
        L_0x006a:
            r7.close()     // Catch:{ all -> 0x006e }
            throw r8     // Catch:{ all -> 0x006e }
        L_0x006e:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.addEventToTable(java.lang.String, java.lang.String):long");
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertEventContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insert(str, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public synchronized String getValue(String str) {
        return (String) getValueFromTable(STORE_TABLE_NAME, str);
    }

    /* access modifiers changed from: package-private */
    public synchronized Long getLongValue(String str) {
        return (Long) getValueFromTable(LONG_STORE_TABLE_NAME, str);
    }

    /* JADX WARNING: type inference failed for: r14v14, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r14v15, types: [java.lang.String] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053 A[SYNTHETIC, Splitter:B:25:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005e A[SYNTHETIC, Splitter:B:32:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007f A[SYNTHETIC, Splitter:B:41:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009b A[SYNTHETIC, Splitter:B:48:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a5 A[SYNTHETIC, Splitter:B:55:0x00a5] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x0085=Splitter:B:45:0x0085, B:29:0x0059=Splitter:B:29:0x0059, B:38:0x0069=Splitter:B:38:0x0069, B:22:0x004e=Splitter:B:22:0x004e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object getValueFromTable(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            monitor-enter(r13)
            r0 = 0
            r1 = 0
            r2 = 1
            android.database.sqlite.SQLiteDatabase r4 = r13.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0083, StackOverflowError -> 0x0067, IllegalStateException -> 0x0057, RuntimeException -> 0x004c, all -> 0x004a }
            java.lang.String r3 = "key"
            java.lang.String r5 = "value"
            java.lang.String[] r6 = new java.lang.String[]{r3, r5}     // Catch:{ SQLiteException -> 0x0083, StackOverflowError -> 0x0067, IllegalStateException -> 0x0057, RuntimeException -> 0x004c, all -> 0x004a }
            java.lang.String r7 = "key = ?"
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0083, StackOverflowError -> 0x0067, IllegalStateException -> 0x0057, RuntimeException -> 0x004c, all -> 0x004a }
            r8[r1] = r15     // Catch:{ SQLiteException -> 0x0083, StackOverflowError -> 0x0067, IllegalStateException -> 0x0057, RuntimeException -> 0x004c, all -> 0x004a }
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r3 = r13
            r5 = r14
            android.database.Cursor r15 = r3.queryDb(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x0083, StackOverflowError -> 0x0067, IllegalStateException -> 0x0057, RuntimeException -> 0x004c, all -> 0x004a }
            boolean r3 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0048, StackOverflowError -> 0x0046, IllegalStateException -> 0x0044, RuntimeException -> 0x0042 }
            if (r3 == 0) goto L_0x003c
            java.lang.String r3 = "store"
            boolean r3 = r14.equals(r3)     // Catch:{ SQLiteException -> 0x0048, StackOverflowError -> 0x0046, IllegalStateException -> 0x0044, RuntimeException -> 0x0042 }
            if (r3 == 0) goto L_0x0033
            java.lang.String r14 = r15.getString(r2)     // Catch:{ SQLiteException -> 0x0048, StackOverflowError -> 0x0046, IllegalStateException -> 0x0044, RuntimeException -> 0x0042 }
            goto L_0x003b
        L_0x0033:
            long r3 = r15.getLong(r2)     // Catch:{ SQLiteException -> 0x0048, StackOverflowError -> 0x0046, IllegalStateException -> 0x0044, RuntimeException -> 0x0042 }
            java.lang.Long r14 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0048, StackOverflowError -> 0x0046, IllegalStateException -> 0x0044, RuntimeException -> 0x0042 }
        L_0x003b:
            r0 = r14
        L_0x003c:
            if (r15 == 0) goto L_0x0061
            r15.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0061
        L_0x0042:
            r14 = move-exception
            goto L_0x004e
        L_0x0044:
            r14 = move-exception
            goto L_0x0059
        L_0x0046:
            r3 = move-exception
            goto L_0x0069
        L_0x0048:
            r3 = move-exception
            goto L_0x0085
        L_0x004a:
            r14 = move-exception
            goto L_0x00a3
        L_0x004c:
            r14 = move-exception
            r15 = r0
        L_0x004e:
            convertIfCursorWindowException(r14)     // Catch:{ all -> 0x00a1 }
            if (r15 == 0) goto L_0x0061
            r15.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0061
        L_0x0057:
            r14 = move-exception
            r15 = r0
        L_0x0059:
            r13.handleIfCursorRowTooLargeException(r14)     // Catch:{ all -> 0x00a1 }
            if (r15 == 0) goto L_0x0061
            r15.close()     // Catch:{ all -> 0x0065 }
        L_0x0061:
            r13.close()     // Catch:{ all -> 0x0065 }
            goto L_0x009f
        L_0x0065:
            r14 = move-exception
            goto L_0x00ac
        L_0x0067:
            r3 = move-exception
            r15 = r0
        L_0x0069:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a1 }
            r2[r1] = r14     // Catch:{ all -> 0x00a1 }
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch:{ all -> 0x00a1 }
            r4.mo13083e(r5, r14, r3)     // Catch:{ all -> 0x00a1 }
            r13.delete()     // Catch:{ all -> 0x00a1 }
            if (r15 == 0) goto L_0x0061
            r15.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0061
        L_0x0083:
            r3 = move-exception
            r15 = r0
        L_0x0085:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a1 }
            r2[r1] = r14     // Catch:{ all -> 0x00a1 }
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch:{ all -> 0x00a1 }
            r4.mo13083e(r5, r14, r3)     // Catch:{ all -> 0x00a1 }
            r13.delete()     // Catch:{ all -> 0x00a1 }
            if (r15 == 0) goto L_0x0061
            r15.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0061
        L_0x009f:
            monitor-exit(r13)
            return r0
        L_0x00a1:
            r14 = move-exception
            r0 = r15
        L_0x00a3:
            if (r0 == 0) goto L_0x00a8
            r0.close()     // Catch:{ all -> 0x0065 }
        L_0x00a8:
            r13.close()     // Catch:{ all -> 0x0065 }
            throw r14     // Catch:{ all -> 0x0065 }
        L_0x00ac:
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public synchronized List<JSONObject> getEvents(long j, long j2) throws JSONException {
        return getEventsFromTable(EVENT_TABLE_NAME, j, j2);
    }

    /* access modifiers changed from: package-private */
    public synchronized List<JSONObject> getIdentifys(long j, long j2) throws JSONException {
        return getEventsFromTable(IDENTIFY_TABLE_NAME, j, j2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<org.json.JSONObject> getEventsFromTable(java.lang.String r19, long r20, long r22) throws org.json.JSONException {
        /*
            r18 = this;
            r11 = r18
            r0 = r20
            r2 = r22
            monitor-enter(r18)
            java.util.LinkedList r12 = new java.util.LinkedList     // Catch:{ all -> 0x00e8 }
            r12.<init>()     // Catch:{ all -> 0x00e8 }
            r13 = 0
            r14 = 1
            r15 = 0
            android.database.sqlite.SQLiteDatabase r4 = r18.getReadableDatabase()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r5 = "id"
            java.lang.String r6 = "event"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r6 = 0
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x0033
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r8.<init>()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r9 = "id <= "
            r8.append(r9)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r8.append(r0)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r0 = r8.toString()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            goto L_0x0034
        L_0x0033:
            r0 = r15
        L_0x0034:
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r16 = "id ASC"
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0051
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r6 = ""
            r1.append(r6)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r1.append(r2)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r17 = r1
            goto L_0x0053
        L_0x0051:
            r17 = r15
        L_0x0053:
            r1 = r18
            r2 = r4
            r3 = r19
            r4 = r5
            r5 = r0
            r6 = r8
            r7 = r9
            r8 = r10
            r9 = r16
            r10 = r17
            android.database.Cursor r15 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
        L_0x0065:
            boolean r0 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            if (r0 == 0) goto L_0x0088
            long r0 = r15.getLong(r13)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r2 = r15.getString(r14)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            boolean r3 = com.amplitude.api.C0895Utils.isEmptyString(r2)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            if (r3 == 0) goto L_0x007a
            goto L_0x0065
        L_0x007a:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r3.<init>(r2)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            java.lang.String r2 = "event_id"
            r3.put(r2, r0)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            r12.add(r3)     // Catch:{ SQLiteException -> 0x00c2, StackOverflowError -> 0x00a7, IllegalStateException -> 0x009d, RuntimeException -> 0x0093 }
            goto L_0x0065
        L_0x0088:
            if (r15 == 0) goto L_0x008d
            r15.close()     // Catch:{ all -> 0x00e8 }
        L_0x008d:
            r18.close()     // Catch:{ all -> 0x00e8 }
            goto L_0x00dd
        L_0x0091:
            r0 = move-exception
            goto L_0x00df
        L_0x0093:
            r0 = move-exception
            convertIfCursorWindowException(r0)     // Catch:{ all -> 0x0091 }
            if (r15 == 0) goto L_0x008d
            r15.close()     // Catch:{ all -> 0x00e8 }
            goto L_0x008d
        L_0x009d:
            r0 = move-exception
            r11.handleIfCursorRowTooLargeException(r0)     // Catch:{ all -> 0x0091 }
            if (r15 == 0) goto L_0x008d
            r15.close()     // Catch:{ all -> 0x00e8 }
            goto L_0x008d
        L_0x00a7:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "getEvents from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[r14]     // Catch:{ all -> 0x0091 }
            r4[r13] = r19     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x0091 }
            r1.mo13083e(r2, r3, r0)     // Catch:{ all -> 0x0091 }
            r18.delete()     // Catch:{ all -> 0x0091 }
            if (r15 == 0) goto L_0x008d
            r15.close()     // Catch:{ all -> 0x00e8 }
            goto L_0x008d
        L_0x00c2:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "getEvents from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[r14]     // Catch:{ all -> 0x0091 }
            r4[r13] = r19     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x0091 }
            r1.mo13083e(r2, r3, r0)     // Catch:{ all -> 0x0091 }
            r18.delete()     // Catch:{ all -> 0x0091 }
            if (r15 == 0) goto L_0x008d
            r15.close()     // Catch:{ all -> 0x00e8 }
            goto L_0x008d
        L_0x00dd:
            monitor-exit(r18)
            return r12
        L_0x00df:
            if (r15 == 0) goto L_0x00e4
            r15.close()     // Catch:{ all -> 0x00e8 }
        L_0x00e4:
            r18.close()     // Catch:{ all -> 0x00e8 }
            throw r0     // Catch:{ all -> 0x00e8 }
        L_0x00e8:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getEventsFromTable(java.lang.String, long, long):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public synchronized long getEventCount() {
        return getEventCountFromTable(EVENT_TABLE_NAME);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getIdentifyCount() {
        return getEventCountFromTable(IDENTIFY_TABLE_NAME);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getTotalEventCount() {
        return getEventCount() + getIdentifyCount();
    }

    private synchronized long getEventCountFromTable(String str) {
        long j;
        SQLiteStatement sQLiteStatement = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            SQLiteStatement compileStatement = readableDatabase.compileStatement("SELECT COUNT(*) FROM " + str);
            j = compileStatement.simpleQueryForLong();
            if (compileStatement != null) {
                compileStatement.close();
            }
            close();
        } catch (SQLiteException e) {
            logger.mo13083e(TAG, String.format("getNumberRows for %s failed", new Object[]{str}), e);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            close();
            j = 0;
            return j;
        } catch (StackOverflowError e2) {
            try {
                logger.mo13083e(TAG, String.format("getNumberRows for %s failed", new Object[]{str}), e2);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                j = 0;
                return j;
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNthEventId(long j) {
        return getNthEventIdFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNthIdentifyId(long j) {
        return getNthEventIdFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized long getNthEventIdFromTable(String str, long j) {
        long j2;
        j2 = -1;
        SQLiteStatement sQLiteStatement = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            sQLiteStatement = readableDatabase.compileStatement("SELECT id FROM " + str + " LIMIT 1 OFFSET " + (j - 1));
            try {
                j2 = sQLiteStatement.simpleQueryForLong();
            } catch (SQLiteDoneException e) {
                logger.mo13095w(TAG, (Throwable) e);
            }
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (SQLiteException e2) {
            logger.mo13083e(TAG, String.format("getNthEventId from %s failed", new Object[]{str}), e2);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (StackOverflowError e3) {
            try {
                logger.mo13083e(TAG, String.format("getNthEventId from %s failed", new Object[]{str}), e3);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        close();
        return j2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeEvents(long j) {
        removeEventsFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeIdentifys(long j) {
        removeEventsFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventsFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id <= " + j, (String[]) null);
        } catch (SQLiteException e) {
            logger.mo13083e(TAG, String.format("removeEvents from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.mo13083e(TAG, String.format("removeEvents from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeEvent(long j) {
        removeEventFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeIdentify(long j) {
        removeEventFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id = " + j, (String[]) null);
        } catch (SQLiteException e) {
            logger.mo13083e(TAG, String.format("removeEvent from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.mo13083e(TAG, String.format("removeEvent from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c7, code lost:
        if (r1.isOpen() != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00c9, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00e5, code lost:
        if (r1.isOpen() != false) goto L_0x00c9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void delete() {
        /*
            r8 = this;
            java.lang.String r0 = "databaseReset callback failed during delete"
            r1 = 0
            r2 = 0
            r3 = 1
            r8.close()     // Catch:{ SecurityException -> 0x005a }
            java.io.File r4 = r8.file     // Catch:{ SecurityException -> 0x005a }
            r4.delete()     // Catch:{ SecurityException -> 0x005a }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener
            if (r4 == 0) goto L_0x00ab
            boolean r4 = r8.callResetListenerOnDatabaseReset
            if (r4 == 0) goto L_0x00ab
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0031 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x0031 }
            r4.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x0031 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
        L_0x002a:
            r8.close()
            goto L_0x00ab
        L_0x002f:
            r0 = move-exception
            goto L_0x004a
        L_0x0031:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x002f }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x002f }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x002f }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x002f }
            r5.mo13083e(r6, r0, r4)     // Catch:{ all -> 0x002f }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x004a:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x0057
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x0057
            r8.close()
        L_0x0057:
            throw r0
        L_0x0058:
            r4 = move-exception
            goto L_0x00ac
        L_0x005a:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x0058 }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x0058 }
            java.lang.String r7 = "delete failed"
            r5.mo13083e(r6, r7, r4)     // Catch:{ all -> 0x0058 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener
            if (r4 == 0) goto L_0x00ab
            boolean r4 = r8.callResetListenerOnDatabaseReset
            if (r4 == 0) goto L_0x00ab
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0084 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x0084 }
            r4.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x0084 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x0082:
            r0 = move-exception
            goto L_0x009d
        L_0x0084:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x0082 }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x0082 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x0082 }
            r5.mo13083e(r6, r0, r4)     // Catch:{ all -> 0x0082 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x009d:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00aa
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x00aa
            r8.close()
        L_0x00aa:
            throw r0
        L_0x00ab:
            return
        L_0x00ac:
            com.amplitude.api.DatabaseResetListener r5 = r8.databaseResetListener
            if (r5 == 0) goto L_0x00f6
            boolean r5 = r8.callResetListenerOnDatabaseReset
            if (r5 == 0) goto L_0x00f6
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00cf }
            com.amplitude.api.DatabaseResetListener r5 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x00cf }
            r5.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x00cf }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f6
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00f6
        L_0x00c9:
            r8.close()
            goto L_0x00f6
        L_0x00cd:
            r0 = move-exception
            goto L_0x00e8
        L_0x00cf:
            r5 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x00cd }
            java.lang.String r7 = TAG     // Catch:{ all -> 0x00cd }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00cd }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x00cd }
            r6.mo13083e(r7, r0, r5)     // Catch:{ all -> 0x00cd }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f6
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00f6
            goto L_0x00c9
        L_0x00e8:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f5
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x00f5
            r8.close()
        L_0x00f5:
            throw r0
        L_0x00f6:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.delete():void");
    }

    /* access modifiers changed from: package-private */
    public boolean dbFileExists() {
        return this.file.exists();
    }

    /* access modifiers changed from: package-private */
    public Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    private void handleIfCursorRowTooLargeException(IllegalStateException illegalStateException) {
        String message = illegalStateException.getMessage();
        if (C0895Utils.isEmptyString(message) || !message.contains("Couldn't read") || !message.contains("CursorWindow")) {
            throw illegalStateException;
        }
        delete();
    }

    private static void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (C0895Utils.isEmptyString(message) || !message.startsWith("Cursor window allocation of")) {
            throw runtimeException;
        }
        throw new CursorWindowAllocationException(message);
    }
}
