package p015me.everything.android.p016ui.overscroll.adapters;

import android.view.View;
import android.widget.ScrollView;

/* renamed from: me.everything.android.ui.overscroll.adapters.ScrollViewOverScrollDecorAdapter */
public class ScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final ScrollView mView;

    public ScrollViewOverScrollDecorAdapter(ScrollView scrollView) {
        this.mView = scrollView;
    }

    public View getView() {
        return this.mView;
    }

    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollVertically(-1);
    }

    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollVertically(1);
    }
}
