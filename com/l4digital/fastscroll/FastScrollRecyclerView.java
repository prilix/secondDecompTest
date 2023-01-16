package com.l4digital.fastscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import com.l4digital.fastscroll.FastScroller;

public class FastScrollRecyclerView extends RecyclerView {
    private FastScroller mFastScroller;

    public FastScrollRecyclerView(Context context) {
        super(context);
        layout(context, (AttributeSet) null);
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        layout(context, attributeSet);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof FastScroller.SectionIndexer) {
            setSectionIndexer((FastScroller.SectionIndexer) adapter);
        } else if (adapter == null) {
            setSectionIndexer((FastScroller.SectionIndexer) null);
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        this.mFastScroller.setVisibility(i);
    }

    public void setSectionIndexer(FastScroller.SectionIndexer sectionIndexer) {
        this.mFastScroller.setSectionIndexer(sectionIndexer);
    }

    public void setFastScrollEnabled(boolean z) {
        this.mFastScroller.setEnabled(z);
    }

    public void setHideScrollbar(boolean z) {
        this.mFastScroller.setHideScrollbar(z);
    }

    public void setTrackVisible(boolean z) {
        this.mFastScroller.setTrackVisible(z);
    }

    public void setTrackColor(int i) {
        this.mFastScroller.setTrackColor(i);
    }

    public void setHandleColor(int i) {
        this.mFastScroller.setHandleColor(i);
    }

    public void setBubbleVisible(boolean z) {
        this.mFastScroller.setBubbleVisible(z);
    }

    public void setBubbleColor(int i) {
        this.mFastScroller.setBubbleColor(i);
    }

    public void setBubbleTextColor(int i) {
        this.mFastScroller.setBubbleTextColor(i);
    }

    public void setFastScrollStateChangeListener(FastScrollStateChangeListener fastScrollStateChangeListener) {
        this.mFastScroller.setFastScrollStateChangeListener(fastScrollStateChangeListener);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFastScroller.attachRecyclerView(this);
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.addView(this.mFastScroller);
            this.mFastScroller.setLayoutParams(viewGroup);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mFastScroller.detachRecyclerView();
        super.onDetachedFromWindow();
    }

    private void layout(Context context, AttributeSet attributeSet) {
        FastScroller fastScroller = new FastScroller(context, attributeSet);
        this.mFastScroller = fastScroller;
        fastScroller.setId(C2680R.C2683id.fastscroller);
    }
}
