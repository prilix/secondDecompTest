package p015me.everything.android.p016ui.overscroll;

import android.view.View;

/* renamed from: me.everything.android.ui.overscroll.IOverScrollDecor */
public interface IOverScrollDecor {
    void detach();

    int getCurrentState();

    View getView();

    void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener);

    void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener);
}
