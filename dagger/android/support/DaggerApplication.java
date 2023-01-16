package dagger.android.support;

import dagger.android.AndroidInjector;

public abstract class DaggerApplication extends dagger.android.DaggerApplication {
    /* access modifiers changed from: protected */
    public abstract AndroidInjector<? extends DaggerApplication> applicationInjector();
}
