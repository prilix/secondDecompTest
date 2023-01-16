package com.jch.racWiFi.selectMultipleContacts.RxContacts;

import android.net.Uri;
import com.jch.racWiFi.selectMultipleContacts.ColorUtils;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Comparable<Contact> {
    private int backgroundColor = -16776961;
    private boolean isClicked;
    private boolean isSelected;
    private String mDisplayName;
    private List<String> mEmails = new ArrayList();
    private final long mId;
    private int mInVisibleGroup;
    private List<PhoneNumber> mPhoneNumbers = new ArrayList();
    private Uri mPhoto;
    private boolean mStarred;
    private Uri mThumbnail;

    Contact(long j) {
        this.mId = j;
        this.backgroundColor = ColorUtils.getRandomMaterialColor();
    }

    public long getId() {
        return this.mId;
    }

    public int getInVisibleGroup() {
        return this.mInVisibleGroup;
    }

    public void setInVisibleGroup(int i) {
        this.mInVisibleGroup = i;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public boolean isStarred() {
        return this.mStarred;
    }

    public void setStarred(boolean z) {
        this.mStarred = z;
    }

    public Uri getPhoto() {
        return this.mPhoto;
    }

    public void setPhoto(Uri uri) {
        this.mPhoto = uri;
    }

    public Uri getThumbnail() {
        return this.mThumbnail;
    }

    public void setThumbnail(Uri uri) {
        this.mThumbnail = uri;
    }

    public List<String> getEmails() {
        return this.mEmails;
    }

    public void setEmails(List<String> list) {
        this.mEmails = list;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> list) {
        this.mPhoneNumbers = list;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public int compareTo(Contact contact) {
        String str;
        String str2 = this.mDisplayName;
        if (str2 == null || (str = contact.mDisplayName) == null) {
            return -1;
        }
        return str2.compareTo(str);
    }

    public int hashCode() {
        long j = this.mId;
        return (int) (j ^ (j >>> 32));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.mId == ((Contact) obj).mId) {
            return true;
        }
        return false;
    }

    public boolean getIsClicked() {
        return this.isClicked;
    }

    public void setIsClicked(boolean z) {
        this.isClicked = z;
    }
}
