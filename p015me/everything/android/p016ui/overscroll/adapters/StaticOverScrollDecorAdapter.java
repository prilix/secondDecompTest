package p015me.everything.android.p016ui.overscroll.adapters;

import android.view.View;

/* renamed from: me.everything.android.ui.overscroll.adapters.StaticOverScrollDecorAdapter */
public class StaticOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final View mView;

    public boolean isInAbsoluteEnd() {
        return true;
    }

    public boolean isInAbsoluteStart() {
        return true;
    }

    public StaticOverScrollDecorAdapter(View view) {
        this.mView = view;
    }

    public View getView() {
        return this.mView;
    }
}
