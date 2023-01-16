package butterknife.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

public abstract class DebouncingOnClickListener implements View.OnClickListener {
    private static final Runnable ENABLE_AGAIN = DebouncingOnClickListener$$ExternalSyntheticLambda0.INSTANCE;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: package-private */
    public static boolean enabled = true;

    public abstract void doClick(View view);

    public final void onClick(View view) {
        if (enabled) {
            enabled = false;
            MAIN.post(ENABLE_AGAIN);
            doClick(view);
        }
    }
}
