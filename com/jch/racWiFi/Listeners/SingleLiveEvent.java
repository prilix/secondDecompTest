package com.jch.racWiFi.Listeners;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveEvent";
    /* access modifiers changed from: private */
    public final AtomicBoolean mPending = new AtomicBoolean(false);

    public SingleLiveEvent(T t) {
        super(t);
    }

    public SingleLiveEvent() {
    }

    public void observeWithCachedTrigger(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        super.observe(lifecycleOwner, observer);
    }

    public void observeSingleEvent(LifecycleOwner lifecycleOwner, final Observer<T> observer) {
        if (hasActiveObservers()) {
            Logger.m52w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new Observer<T>() {
            public void onChanged(T t) {
                if (SingleLiveEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @Deprecated
    public void observeSingleEvent(final Observer<T> observer) {
        if (hasActiveObservers()) {
            Logger.m52w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observeForever(new Observer<T>() {
            public void onChanged(T t) {
                if (SingleLiveEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void setValue(T t) {
        this.mPending.set(hasActiveObservers());
        super.setValue(t);
    }

    public void postValue(T t) {
        this.mPending.set(hasActiveObservers());
        super.postValue(t);
    }

    public void setNull() {
        setValue((Object) null);
    }

    public void trigger() {
        setValue(getValue());
    }
}
