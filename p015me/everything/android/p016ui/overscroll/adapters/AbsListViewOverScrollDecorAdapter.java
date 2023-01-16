package p015me.everything.android.p016ui.overscroll.adapters;

import android.view.View;
import android.widget.AbsListView;

/* renamed from: me.everything.android.ui.overscroll.adapters.AbsListViewOverScrollDecorAdapter */
public class AbsListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final AbsListView mView;

    public AbsListViewOverScrollDecorAdapter(AbsListView absListView) {
        this.mView = absListView;
    }

    public View getView() {
        return this.mView;
    }

    public boolean isInAbsoluteStart() {
        return this.mView.getChildCount() > 0 && !canScrollListUp();
    }

    public boolean isInAbsoluteEnd() {
        return this.mView.getChildCount() > 0 && !canScrollListDown();
    }

    public boolean canScrollListUp() {
        int top = this.mView.getChildAt(0).getTop();
        if (this.mView.getFirstVisiblePosition() > 0 || top < this.mView.getListPaddingTop()) {
            return true;
        }
        return false;
    }

    public boolean canScrollListDown() {
        int childCount = this.mView.getChildCount();
        int count = this.mView.getCount();
        int firstVisiblePosition = this.mView.getFirstVisiblePosition() + childCount;
        int bottom = this.mView.getChildAt(childCount - 1).getBottom();
        if (firstVisiblePosition < count || bottom > this.mView.getHeight() - this.mView.getListPaddingBottom()) {
            return true;
        }
        return false;
    }
}
