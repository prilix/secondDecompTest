package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.C1030Utils;

public class Entry extends BaseEntry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel parcel) {
            return new Entry(parcel);
        }

        public Entry[] newArray(int i) {
            return new Entry[i];
        }
    };

    /* renamed from: x */
    private float f178x = 0.0f;

    public int describeContents() {
        return 0;
    }

    public Entry() {
    }

    public Entry(float f, float f2) {
        super(f2);
        this.f178x = f;
    }

    public Entry(float f, float f2, Object obj) {
        super(f2, obj);
        this.f178x = f;
    }

    public Entry(float f, float f2, Drawable drawable) {
        super(f2, drawable);
        this.f178x = f;
    }

    public Entry(float f, float f2, Drawable drawable, Object obj) {
        super(f2, drawable, obj);
        this.f178x = f;
    }

    public float getX() {
        return this.f178x;
    }

    public void setX(float f) {
        this.f178x = f;
    }

    public Entry copy() {
        return new Entry(this.f178x, getY(), getData());
    }

    public boolean equalTo(Entry entry) {
        if (entry != null && entry.getData() == getData() && Math.abs(entry.f178x - this.f178x) <= C1030Utils.FLOAT_EPSILON && Math.abs(entry.getY() - getY()) <= C1030Utils.FLOAT_EPSILON) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Entry, x: " + this.f178x + " y: " + getY();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f178x);
        parcel.writeFloat(getY());
        if (getData() == null) {
            parcel.writeInt(0);
        } else if (getData() instanceof Parcelable) {
            parcel.writeInt(1);
            parcel.writeParcelable((Parcelable) getData(), i);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    protected Entry(Parcel parcel) {
        this.f178x = parcel.readFloat();
        setY(parcel.readFloat());
        if (parcel.readInt() == 1) {
            setData(parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
