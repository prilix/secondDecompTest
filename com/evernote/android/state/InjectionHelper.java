package com.evernote.android.state;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public final class InjectionHelper {
    private final String mBaseKey;
    private final Map<String, Bundler<?>> mBundlers;

    public InjectionHelper(String str, Map<String, Bundler<?>> map) {
        this.mBaseKey = str;
        this.mBundlers = map;
    }

    public <T> T getWithBundler(Bundle bundle, String str) {
        return this.mBundlers.get(str).get(str + this.mBaseKey, bundle);
    }

    public <T> void putWithBundler(Bundle bundle, String str, T t) {
        if (t != null) {
            this.mBundlers.get(str).put(str + this.mBaseKey, t, bundle);
        }
    }

    public boolean getBoolean(Bundle bundle, String str) {
        return bundle.getBoolean(str + this.mBaseKey);
    }

    public void putBoolean(Bundle bundle, String str, boolean z) {
        bundle.putBoolean(str + this.mBaseKey, z);
    }

    public Boolean getBoxedBoolean(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Boolean.valueOf(bundle.getBoolean(str + this.mBaseKey));
    }

    public void putBoxedBoolean(Bundle bundle, String str, Boolean bool) {
        if (bool != null) {
            bundle.putBoolean(str + this.mBaseKey, bool.booleanValue());
        }
    }

    public boolean[] getBooleanArray(Bundle bundle, String str) {
        return bundle.getBooleanArray(str + this.mBaseKey);
    }

    public void putBooleanArray(Bundle bundle, String str, boolean[] zArr) {
        bundle.putBooleanArray(str + this.mBaseKey, zArr);
    }

    public byte getByte(Bundle bundle, String str) {
        return bundle.getByte(str + this.mBaseKey);
    }

    public void putByte(Bundle bundle, String str, byte b) {
        bundle.putByte(str + this.mBaseKey, b);
    }

    public Byte getBoxedByte(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Byte.valueOf(bundle.getByte(str + this.mBaseKey));
    }

    public void putBoxedByte(Bundle bundle, String str, Byte b) {
        if (b != null) {
            bundle.putByte(str + this.mBaseKey, b.byteValue());
        }
    }

    public byte[] getByteArray(Bundle bundle, String str) {
        return bundle.getByteArray(str + this.mBaseKey);
    }

    public void putByteArray(Bundle bundle, String str, byte[] bArr) {
        bundle.putByteArray(str + this.mBaseKey, bArr);
    }

    public short getShort(Bundle bundle, String str) {
        return bundle.getShort(str + this.mBaseKey);
    }

    public void putShort(Bundle bundle, String str, short s) {
        bundle.putShort(str + this.mBaseKey, s);
    }

    public Short getBoxedShort(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Short.valueOf(bundle.getShort(str + this.mBaseKey));
    }

    public void putBoxedShort(Bundle bundle, String str, Short sh) {
        if (sh != null) {
            bundle.putShort(str + this.mBaseKey, sh.shortValue());
        }
    }

    public short[] getShortArray(Bundle bundle, String str) {
        return bundle.getShortArray(str + this.mBaseKey);
    }

    public void putShortArray(Bundle bundle, String str, short[] sArr) {
        bundle.putShortArray(str + this.mBaseKey, sArr);
    }

    public int getInt(Bundle bundle, String str) {
        return bundle.getInt(str + this.mBaseKey);
    }

    public void putInt(Bundle bundle, String str, int i) {
        bundle.putInt(str + this.mBaseKey, i);
    }

    public Integer getBoxedInt(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Integer.valueOf(bundle.getInt(str + this.mBaseKey));
    }

    public void putBoxedInt(Bundle bundle, String str, Integer num) {
        if (num != null) {
            bundle.putInt(str + this.mBaseKey, num.intValue());
        }
    }

    public int[] getIntArray(Bundle bundle, String str) {
        return bundle.getIntArray(str + this.mBaseKey);
    }

    public void putIntArray(Bundle bundle, String str, int[] iArr) {
        bundle.putIntArray(str + this.mBaseKey, iArr);
    }

    public long getLong(Bundle bundle, String str) {
        return bundle.getLong(str + this.mBaseKey);
    }

    public void putLong(Bundle bundle, String str, long j) {
        bundle.putLong(str + this.mBaseKey, j);
    }

    public Long getBoxedLong(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Long.valueOf(bundle.getLong(str + this.mBaseKey));
    }

    public void putBoxedLong(Bundle bundle, String str, Long l) {
        if (l != null) {
            bundle.putLong(str + this.mBaseKey, l.longValue());
        }
    }

    public long[] getLongArray(Bundle bundle, String str) {
        return bundle.getLongArray(str + this.mBaseKey);
    }

    public void putLongArray(Bundle bundle, String str, long[] jArr) {
        bundle.putLongArray(str + this.mBaseKey, jArr);
    }

    public float getFloat(Bundle bundle, String str) {
        return bundle.getFloat(str + this.mBaseKey);
    }

    public void putFloat(Bundle bundle, String str, float f) {
        bundle.putFloat(str + this.mBaseKey, f);
    }

    public Float getBoxedFloat(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Float.valueOf(bundle.getFloat(str + this.mBaseKey));
    }

    public void putBoxedFloat(Bundle bundle, String str, Float f) {
        if (f != null) {
            bundle.putFloat(str + this.mBaseKey, f.floatValue());
        }
    }

    public float[] getFloatArray(Bundle bundle, String str) {
        return bundle.getFloatArray(str + this.mBaseKey);
    }

    public void putFloatArray(Bundle bundle, String str, float[] fArr) {
        bundle.putFloatArray(str + this.mBaseKey, fArr);
    }

    public double getDouble(Bundle bundle, String str) {
        return bundle.getDouble(str + this.mBaseKey);
    }

    public void putDouble(Bundle bundle, String str, double d) {
        bundle.putDouble(str + this.mBaseKey, d);
    }

    public Double getBoxedDouble(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Double.valueOf(bundle.getDouble(str + this.mBaseKey));
    }

    public void putBoxedDouble(Bundle bundle, String str, Double d) {
        if (d != null) {
            bundle.putDouble(str + this.mBaseKey, d.doubleValue());
        }
    }

    public double[] getDoubleArray(Bundle bundle, String str) {
        return bundle.getDoubleArray(str + this.mBaseKey);
    }

    public void putDoubleArray(Bundle bundle, String str, double[] dArr) {
        bundle.putDoubleArray(str + this.mBaseKey, dArr);
    }

    public char getChar(Bundle bundle, String str) {
        return bundle.getChar(str + this.mBaseKey);
    }

    public void putChar(Bundle bundle, String str, char c) {
        bundle.putChar(str + this.mBaseKey, c);
    }

    public Character getBoxedChar(Bundle bundle, String str) {
        if (!bundle.containsKey(str + this.mBaseKey)) {
            return null;
        }
        return Character.valueOf(bundle.getChar(str + this.mBaseKey));
    }

    public void putBoxedChar(Bundle bundle, String str, Character ch) {
        if (ch != null) {
            bundle.putChar(str + this.mBaseKey, ch.charValue());
        }
    }

    public char[] getCharArray(Bundle bundle, String str) {
        return bundle.getCharArray(str + this.mBaseKey);
    }

    public void putCharArray(Bundle bundle, String str, char[] cArr) {
        bundle.putCharArray(str + this.mBaseKey, cArr);
    }

    public String getString(Bundle bundle, String str) {
        return bundle.getString(str + this.mBaseKey);
    }

    public void putString(Bundle bundle, String str, String str2) {
        bundle.putString(str + this.mBaseKey, str2);
    }

    public String[] getStringArray(Bundle bundle, String str) {
        return bundle.getStringArray(str + this.mBaseKey);
    }

    public void putStringArray(Bundle bundle, String str, String[] strArr) {
        bundle.putStringArray(str + this.mBaseKey, strArr);
    }

    public CharSequence getCharSequence(Bundle bundle, String str) {
        return bundle.getCharSequence(str + this.mBaseKey);
    }

    public void putCharSequence(Bundle bundle, String str, CharSequence charSequence) {
        bundle.putCharSequence(str + this.mBaseKey, charSequence);
    }

    public CharSequence[] getCharSequenceArray(Bundle bundle, String str) {
        return bundle.getCharSequenceArray(str + this.mBaseKey);
    }

    public void putCharSequenceArray(Bundle bundle, String str, CharSequence[] charSequenceArr) {
        bundle.putCharSequenceArray(str + this.mBaseKey, charSequenceArr);
    }

    public Bundle getBundle(Bundle bundle, String str) {
        return bundle.getBundle(str + this.mBaseKey);
    }

    public void putBundle(Bundle bundle, String str, Bundle bundle2) {
        bundle.putBundle(str + this.mBaseKey, bundle2);
    }

    public <T extends Parcelable> T getParcelable(Bundle bundle, String str) {
        return bundle.getParcelable(str + this.mBaseKey);
    }

    public void putParcelable(Bundle bundle, String str, Parcelable parcelable) {
        bundle.putParcelable(str + this.mBaseKey, parcelable);
    }

    public Parcelable[] getParcelableArray(Bundle bundle, String str) {
        return bundle.getParcelableArray(str + this.mBaseKey);
    }

    public void putParcelableArray(Bundle bundle, String str, Parcelable[] parcelableArr) {
        bundle.putParcelableArray(str + this.mBaseKey, parcelableArr);
    }

    public <T extends Serializable> T getSerializable(Bundle bundle, String str) {
        return bundle.getSerializable(str + this.mBaseKey);
    }

    public void putSerializable(Bundle bundle, String str, Serializable serializable) {
        bundle.putSerializable(str + this.mBaseKey, serializable);
    }

    public ArrayList<Integer> getIntegerArrayList(Bundle bundle, String str) {
        return bundle.getIntegerArrayList(str + this.mBaseKey);
    }

    public void putIntegerArrayList(Bundle bundle, String str, ArrayList<Integer> arrayList) {
        bundle.putIntegerArrayList(str + this.mBaseKey, arrayList);
    }

    public ArrayList<String> getStringArrayList(Bundle bundle, String str) {
        return bundle.getStringArrayList(str + this.mBaseKey);
    }

    public void putStringArrayList(Bundle bundle, String str, ArrayList<String> arrayList) {
        bundle.putStringArrayList(str + this.mBaseKey, arrayList);
    }

    public ArrayList<CharSequence> getCharSequenceArrayList(Bundle bundle, String str) {
        return bundle.getCharSequenceArrayList(str + this.mBaseKey);
    }

    public void putCharSequenceArrayList(Bundle bundle, String str, ArrayList<CharSequence> arrayList) {
        bundle.putCharSequenceArrayList(str + this.mBaseKey, arrayList);
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(Bundle bundle, String str) {
        return bundle.getParcelableArrayList(str + this.mBaseKey);
    }

    public void putParcelableArrayList(Bundle bundle, String str, ArrayList<? extends Parcelable> arrayList) {
        bundle.putParcelableArrayList(str + this.mBaseKey, arrayList);
    }

    public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(Bundle bundle, String str) {
        return bundle.getSparseParcelableArray(str + this.mBaseKey);
    }

    public void putSparseParcelableArray(Bundle bundle, String str, SparseArray<? extends Parcelable> sparseArray) {
        bundle.putSparseParcelableArray(str + this.mBaseKey, sparseArray);
    }

    public Parcelable getParent(Bundle bundle) {
        return bundle.getParcelable(this.mBaseKey + "$$SUPER");
    }

    public Bundle putParent(Parcelable parcelable) {
        Bundle bundle = new Bundle();
        if (parcelable != null) {
            bundle.putParcelable(this.mBaseKey + "$$SUPER", parcelable);
        }
        return bundle;
    }
}
