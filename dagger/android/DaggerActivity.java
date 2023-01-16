package dagger.android;

import android.app.Activity;
import android.os.Bundle;
import javax.inject.Inject;

public abstract class DaggerActivity extends Activity implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
    }

    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }
}
