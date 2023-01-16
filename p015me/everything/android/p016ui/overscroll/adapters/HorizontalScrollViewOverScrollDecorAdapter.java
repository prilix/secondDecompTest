package p015me.everything.android.p016ui.overscroll.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

/* renamed from: me.everything.android.ui.overscroll.adapters.HorizontalScrollViewOverScrollDecorAdapter */
public class HorizontalScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final HorizontalScrollView mView;

    public HorizontalScrollViewOverScrollDecorAdapter(HorizontalScrollView horizontalScrollView) {
        this.mView = horizontalScrollView;
    }

    public View getView() {
        return this.mView;
    }

    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollHorizontally(-1);
    }

    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollHorizontally(1);
    }
}
