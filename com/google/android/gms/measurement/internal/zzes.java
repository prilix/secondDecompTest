package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
final class zzes extends SQLiteOpenHelper {
    private final /* synthetic */ zzet zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzes(zzet zzet, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zza = zzet;
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException unused) {
            this.zza.zzq().zze().zza("Opening the local database failed, dropping and recreating it");
            if (!this.zza.zzm().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zza.zzq().zze().zza("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zza.zzq().zze().zza("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        zzag.zza(this.zza.zzq(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", (String[]) null);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzag.zza(this.zza.zzq(), sQLiteDatabase);
    }
}
