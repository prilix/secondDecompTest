package com.jch.racWiFi.selectMultipleContacts;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.Contact;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

public class ContactResult implements Parcelable {
    public static final Parcelable.Creator<ContactResult> CREATOR = new Parcelable.Creator<ContactResult>() {
        public ContactResult createFromParcel(Parcel parcel) {
            return new ContactResult(parcel);
        }

        public ContactResult[] newArray(int i) {
            return new ContactResult[i];
        }
    };
    private String mContactID;
    private String mDisplayName;
    private List<String> mEmails = new ArrayList();
    private List<PhoneNumber> mPhoneNumbers = new ArrayList();
    private Uri mPhoto;
    private boolean mStarred;
    private Uri mThumbnail;

    public int describeContents() {
        return 0;
    }

    public String getContactID() {
        return this.mContactID;
    }

    public void setContactID(String str) {
        this.mContactID = str;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean isStarred() {
        return this.mStarred;
    }

    public Uri getPhoto() {
        return this.mPhoto;
    }

    public Uri getThumbnail() {
        return this.mThumbnail;
    }

    public List<String> getEmails() {
        return this.mEmails;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    public ContactResult(Contact contact) {
        this.mContactID = String.valueOf(contact.getId());
        this.mDisplayName = contact.getDisplayName();
        this.mStarred = contact.isStarred();
        this.mPhoto = contact.getPhoto();
        this.mThumbnail = contact.getThumbnail();
        this.mEmails.clear();
        this.mEmails.addAll(contact.getEmails());
        this.mPhoneNumbers.clear();
        this.mPhoneNumbers.addAll(contact.getPhoneNumbers());
    }

    protected ContactResult(Parcel parcel) {
        this.mContactID = parcel.readString();
        this.mDisplayName = parcel.readString();
        this.mStarred = parcel.readByte() != 0;
        this.mPhoto = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.mThumbnail = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.mEmails = parcel.createStringArrayList();
        parcel.readTypedList(this.mPhoneNumbers, PhoneNumber.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mContactID);
        parcel.writeString(this.mDisplayName);
        parcel.writeByte(this.mStarred ? (byte) 1 : 0);
        parcel.writeParcelable(this.mPhoto, i);
        parcel.writeParcelable(this.mThumbnail, i);
        parcel.writeStringList(this.mEmails);
        parcel.writeTypedList(this.mPhoneNumbers);
    }
}
