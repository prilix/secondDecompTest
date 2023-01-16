package dagger.android;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import javax.inject.Inject;

@Deprecated
public abstract class DaggerDialogFragment extends DialogFragment implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    public void onAttach(Context context) {
        AndroidInjection.inject((Fragment) this);
        super.onAttach(context);
    }

    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }
}
