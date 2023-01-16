package org.apache.commons.lang3.builder;

final class IDKey {

    /* renamed from: id */
    private final int f716id;
    private final Object value;

    IDKey(Object obj) {
        this.f716id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f716id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        if (this.f716id == iDKey.f716id && this.value == iDKey.value) {
            return true;
        }
        return false;
    }
}
