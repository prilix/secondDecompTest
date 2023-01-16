package dagger.android.support;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import javax.inject.Inject;

public abstract class DaggerAppCompatActivity extends AppCompatActivity implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    public DaggerAppCompatActivity() {
    }

    public DaggerAppCompatActivity(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
    }

    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }
}
