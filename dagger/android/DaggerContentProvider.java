package dagger.android;

import android.content.ContentProvider;

public abstract class DaggerContentProvider extends ContentProvider {
    public boolean onCreate() {
        AndroidInjection.inject((ContentProvider) this);
        return true;
    }
}
