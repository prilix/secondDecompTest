package com.jch.racWiFi.device_camera;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class InternalStorageContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse("content://eu.janmuller.android.simplecropimage.example/");
    private static final HashMap<String, String> MIME_TYPES;
    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        MIME_TYPES = hashMap;
        hashMap.put(".jpg", "image/jpeg");
        hashMap.put(".jpeg", "image/jpeg");
    }

    public boolean onCreate() {
        try {
            File file = new File(getContext().getFilesDir(), TEMP_PHOTO_FILE_NAME);
            if (file.exists()) {
                return true;
            }
            file.createNewFile();
            getContext().getContentResolver().notifyChange(CONTENT_URI, (ContentObserver) null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getType(Uri uri) {
        String uri2 = uri.toString();
        for (String next : MIME_TYPES.keySet()) {
            if (uri2.endsWith(next)) {
                return MIME_TYPES.get(next);
            }
        }
        return null;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        File file = new File(getContext().getFilesDir(), TEMP_PHOTO_FILE_NAME);
        if (file.exists()) {
            return ParcelFileDescriptor.open(file, 805306368);
        }
        throw new FileNotFoundException(uri.getPath());
    }
}
