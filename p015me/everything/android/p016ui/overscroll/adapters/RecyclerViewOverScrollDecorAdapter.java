package p015me.everything.android.p016ui.overscroll.adapters;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.List;

/* renamed from: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final Impl mImpl;
    protected boolean mIsItemTouchInEffect;
    protected final RecyclerView mRecyclerView;

    /* renamed from: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter$Impl */
    protected interface Impl {
        boolean isInAbsoluteEnd();

        boolean isInAbsoluteStart();
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {
        int i;
        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (z || (layoutManager instanceof StaggeredGridLayoutManager)) {
            if (z) {
                i = ((LinearLayoutManager) layoutManager).getOrientation();
            } else {
                i = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            }
            if (i == 0) {
                this.mImpl = new ImplHorizLayout();
            } else {
                this.mImpl = new ImplVerticalLayout();
            }
        } else {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl) {
        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        this.mImpl = impl;
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, ItemTouchHelper.Callback callback) {
        this(recyclerView);
        setUpTouchHelperCallback(callback);
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl, ItemTouchHelper.Callback callback) {
        this(recyclerView, impl);
        setUpTouchHelperCallback(callback);
    }

    /* access modifiers changed from: protected */
    public void setUpTouchHelperCallback(ItemTouchHelper.Callback callback) {
        new ItemTouchHelper(new ItemTouchHelperCallbackWrapper(callback) {
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
                RecyclerViewOverScrollDecorAdapter.this.mIsItemTouchInEffect = i != 0;
                super.onSelectedChanged(viewHolder, i);
            }
        }).attachToRecyclerView(this.mRecyclerView);
    }

    public View getView() {
        return this.mRecyclerView;
    }

    public boolean isInAbsoluteStart() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteStart();
    }

    public boolean isInAbsoluteEnd() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteEnd();
    }

    /* renamed from: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter$ImplHorizLayout */
    protected class ImplHorizLayout implements Impl {
        protected ImplHorizLayout() {
        }

        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(-1);
        }

        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(1);
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter$ImplVerticalLayout */
    protected class ImplVerticalLayout implements Impl {
        protected ImplVerticalLayout() {
        }

        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(-1);
        }

        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(1);
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter$ItemTouchHelperCallbackWrapper */
    private static class ItemTouchHelperCallbackWrapper extends ItemTouchHelper.Callback {
        final ItemTouchHelper.Callback mCallback;

        private ItemTouchHelperCallbackWrapper(ItemTouchHelper.Callback callback) {
            this.mCallback = callback;
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMovementFlags(recyclerView, viewHolder);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.mCallback.onMove(recyclerView, viewHolder, viewHolder2);
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            this.mCallback.onSwiped(viewHolder, i);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            return this.mCallback.convertToAbsoluteDirection(i, i2);
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.mCallback.canDropOver(recyclerView, viewHolder, viewHolder2);
        }

        public boolean isLongPressDragEnabled() {
            return this.mCallback.isLongPressDragEnabled();
        }

        public boolean isItemViewSwipeEnabled() {
            return this.mCallback.isItemViewSwipeEnabled();
        }

        public int getBoundingBoxMargin() {
            return this.mCallback.getBoundingBoxMargin();
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getSwipeThreshold(viewHolder);
        }

        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMoveThreshold(viewHolder);
        }

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, int i, int i2) {
            return this.mCallback.chooseDropTarget(viewHolder, list, i, i2);
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            this.mCallback.onSelectedChanged(viewHolder, i);
        }

        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
            this.mCallback.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            this.mCallback.clearView(recyclerView, viewHolder);
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            this.mCallback.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            this.mCallback.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            return this.mCallback.getAnimationDuration(recyclerView, i, f, f2);
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            return this.mCallback.interpolateOutOfBoundsScroll(recyclerView, i, i2, i3, j);
        }
    }
}
