package com.jch.racWiFi.selectMultipleContacts.RxContacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.LongSparseArray;
import com.jch.racWiFi.selectMultipleContacts.LimitColumn;
import p012io.reactivex.Observable;
import p012io.reactivex.ObservableEmitter;
import p012io.reactivex.ObservableOnSubscribe;

/* renamed from: com.jch.racWiFi.selectMultipleContacts.RxContacts.RxContacts */
public class C2319RxContacts {
    private static final String DISPLAY_NAME = "display_name";
    private static final Uri EMAIL_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    private static final String[] EMAIL_PROJECTION = {"contact_id", "data1"};
    private static final String[] NUMBER_PROJECTION = {"contact_id", "data1", "data2", "data3"};
    private static final Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final String[] PROJECTION = {"_id", "in_visible_group", "display_name", "starred", "photo_uri", "photo_thumb_uri", "has_phone_number"};
    private Context mContext;
    private ContentResolver mResolver;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public static Observable<Contact> fetch(final LimitColumn limitColumn, final Context context) {
        return Observable.create(new ObservableOnSubscribe<Contact>() {
            public void subscribe(ObservableEmitter<Contact> observableEmitter) throws Exception {
                new C2319RxContacts(context).fetch(limitColumn, (ObservableEmitter) observableEmitter);
            }
        });
    }

    private C2319RxContacts(Context context) {
        this.mContext = context;
        this.mResolver = context.getContentResolver();
    }

    /* access modifiers changed from: private */
    public void fetch(LimitColumn limitColumn, ObservableEmitter observableEmitter) {
        Contact contact;
        long j;
        long j2;
        Contact contact2;
        ObservableEmitter observableEmitter2 = observableEmitter;
        LongSparseArray longSparseArray = new LongSparseArray();
        Cursor createCursor = createCursor(getFilter(limitColumn));
        createCursor.moveToFirst();
        int columnIndex = createCursor.getColumnIndex("_id");
        int columnIndex2 = createCursor.getColumnIndex("in_visible_group");
        int columnIndex3 = createCursor.getColumnIndex(DISPLAY_NAME);
        int columnIndex4 = createCursor.getColumnIndex("starred");
        int columnIndex5 = createCursor.getColumnIndex("photo_uri");
        int columnIndex6 = createCursor.getColumnIndex("photo_thumb_uri");
        int columnIndex7 = createCursor.getColumnIndex("has_phone_number");
        while (!createCursor.isAfterLast()) {
            long j3 = createCursor.getLong(columnIndex);
            Contact contact3 = (Contact) longSparseArray.get(j3, (Object) null);
            if (contact3 == null) {
                contact3 = new Contact(j3);
            }
            Contact contact4 = contact3;
            ColumnMapper.mapInVisibleGroup(createCursor, contact4, columnIndex2);
            ColumnMapper.mapDisplayName(createCursor, contact4, columnIndex3);
            ColumnMapper.mapStarred(createCursor, contact4, columnIndex4);
            ColumnMapper.mapPhoto(createCursor, contact4, columnIndex5);
            ColumnMapper.mapThumbnail(createCursor, contact4, columnIndex6);
            int i = C23212.$SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn[limitColumn.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    contact2 = contact4;
                    j2 = j3;
                    getPhoneNumber(j2, createCursor, contact2, columnIndex7);
                } else if (i != 3) {
                    contact = contact4;
                    j = j3;
                } else {
                    getEmail(j3, contact4);
                    contact2 = contact4;
                    j2 = j3;
                    getPhoneNumber(j3, createCursor, contact2, columnIndex7);
                }
                contact = contact2;
                j = j2;
            } else {
                contact = contact4;
                j = j3;
                getEmail(j, contact);
            }
            if (limitColumn != LimitColumn.EMAIL) {
                longSparseArray.put(j, contact);
                observableEmitter2.onNext(contact);
            } else if (contact.getEmails().size() > 0) {
                longSparseArray.put(j, contact);
                observableEmitter2.onNext(contact);
            }
            createCursor.moveToNext();
        }
        createCursor.close();
        observableEmitter.onComplete();
    }

    /* renamed from: com.jch.racWiFi.selectMultipleContacts.RxContacts.RxContacts$2 */
    static /* synthetic */ class C23212 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.selectMultipleContacts.LimitColumn[] r0 = com.jch.racWiFi.selectMultipleContacts.LimitColumn.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn = r0
                com.jch.racWiFi.selectMultipleContacts.LimitColumn r1 = com.jch.racWiFi.selectMultipleContacts.LimitColumn.EMAIL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.selectMultipleContacts.LimitColumn r1 = com.jch.racWiFi.selectMultipleContacts.LimitColumn.PHONE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.selectMultipleContacts.LimitColumn r1 = com.jch.racWiFi.selectMultipleContacts.LimitColumn.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.selectMultipleContacts.RxContacts.C2319RxContacts.C23212.<clinit>():void");
        }
    }

    private void getEmail(long j, Contact contact) {
        Cursor query = this.mResolver.query(EMAIL_CONTENT_URI, EMAIL_PROJECTION, "contact_id = ?", new String[]{String.valueOf(j)}, (String) null);
        if (query != null) {
            int columnIndex = query.getColumnIndex("data1");
            if (query.moveToFirst()) {
                ColumnMapper.mapEmail(query, contact, columnIndex);
            }
            query.close();
        }
    }

    private void getPhoneNumber(long j, Cursor cursor, Contact contact, int i) {
        Cursor query;
        if (Integer.parseInt(cursor.getString(i)) > 0 && (query = this.mResolver.query(PHONE_CONTENT_URI, NUMBER_PROJECTION, "contact_id = ?", new String[]{String.valueOf(j)}, (String) null)) != null) {
            query.moveToFirst();
            int columnIndex = query.getColumnIndex("data1");
            int columnIndex2 = query.getColumnIndex("data2");
            int columnIndex3 = query.getColumnIndex("data3");
            while (!query.isAfterLast()) {
                ColumnMapper.mapPhoneNumber(this.mContext, query, contact, columnIndex, columnIndex2, columnIndex3);
                query.moveToNext();
            }
            query.close();
        }
    }

    private String getFilter(LimitColumn limitColumn) {
        if (C23212.$SwitchMap$com$jch$racWiFi$selectMultipleContacts$LimitColumn[limitColumn.ordinal()] != 2) {
            return null;
        }
        return "has_phone_number > 0";
    }

    private Cursor createCursor(String str) {
        return this.mResolver.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, str, (String[]) null, "_id");
    }
}
