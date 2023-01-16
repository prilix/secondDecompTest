package dagger.android.support;

import android.content.Context;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import javax.inject.Inject;

public abstract class DaggerFragment extends Fragment implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    public DaggerFragment() {
    }

    public DaggerFragment(int i) {
        super(i);
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }
}
