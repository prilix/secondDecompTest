package com.evernote.android.state;

import android.os.Bundle;
import android.os.Parcelable;

public class Injector {

    public static abstract class Object<T> extends Injector {
        static final Object<?> DEFAULT = new Object<Object>() {
            public void restore(Object obj, Bundle bundle) {
            }

            public void save(Object obj, Bundle bundle) {
            }
        };

        public abstract void restore(T t, Bundle bundle);

        public abstract void save(T t, Bundle bundle);
    }

    public static abstract class View<T> extends Injector {
        static final View<?> DEFAULT = new View<Object>() {
            public Parcelable restore(Object obj, Parcelable parcelable) {
                return parcelable;
            }

            public Parcelable save(Object obj, Parcelable parcelable) {
                return parcelable;
            }
        };

        public abstract Parcelable restore(T t, Parcelable parcelable);

        public abstract Parcelable save(T t, Parcelable parcelable);
    }

    protected Injector() {
    }
}
