package dagger.android.support;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import javax.inject.Inject;

public abstract class DaggerDialogFragment extends DialogFragment implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }
}
