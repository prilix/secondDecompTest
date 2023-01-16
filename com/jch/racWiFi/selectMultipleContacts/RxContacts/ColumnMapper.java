package com.jch.racWiFi.selectMultipleContacts.RxContacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

class ColumnMapper {
    private ColumnMapper() {
    }

    static void mapInVisibleGroup(Cursor cursor, Contact contact, int i) {
        contact.setInVisibleGroup(cursor.getInt(i));
    }

    static void mapDisplayName(Cursor cursor, Contact contact, int i) {
        String string = cursor.getString(i);
        if (string != null && !string.isEmpty()) {
            contact.setDisplayName(string);
        }
    }

    static void mapEmail(Cursor cursor, Contact contact, int i) {
        String string = cursor.getString(i);
        if (string != null && !string.trim().isEmpty()) {
            contact.getEmails().add(string);
        }
    }

    static void mapPhoneNumber(Context context, Cursor cursor, Contact contact, int i, int i2, int i3) {
        String string = cursor.getString(i);
        String str = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(context.getResources(), cursor.getInt(i2), cursor.getString(i3));
        if (string != null && !string.isEmpty()) {
            contact.getPhoneNumbers().add(new PhoneNumber(str, string.replaceAll("\\s+", "").trim()));
        }
    }

    static void mapPhoto(Cursor cursor, Contact contact, int i) {
        String string = cursor.getString(i);
        if (string != null && !string.isEmpty()) {
            contact.setPhoto(Uri.parse(string));
        }
    }

    static void mapStarred(Cursor cursor, Contact contact, int i) {
        contact.setStarred(cursor.getInt(i) != 0);
    }

    static void mapThumbnail(Cursor cursor, Contact contact, int i) {
        String string = cursor.getString(i);
        if (string != null && !string.isEmpty()) {
            contact.setThumbnail(Uri.parse(string));
        }
    }
}
