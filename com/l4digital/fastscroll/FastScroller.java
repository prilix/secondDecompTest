package com.l4digital.fastscroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class FastScroller extends LinearLayout {
    private static final int sBubbleAnimDuration = 100;
    private static final int sScrollbarAnimDuration = 300;
    private static final int sScrollbarHideDelay = 1000;
    private static final int sTrackSnapRange = 5;
    /* access modifiers changed from: private */
    public ViewPropertyAnimator mBubbleAnimator;
    private int mBubbleColor;
    private int mBubbleHeight;
    private Drawable mBubbleImage;
    /* access modifiers changed from: private */
    public TextView mBubbleView;
    private FastScrollStateChangeListener mFastScrollStateChangeListener;
    private int mHandleColor;
    private int mHandleHeight;
    private Drawable mHandleImage;
    /* access modifiers changed from: private */
    public ImageView mHandleView;
    /* access modifiers changed from: private */
    public boolean mHideScrollbar;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private RecyclerView.OnScrollListener mScrollListener;
    /* access modifiers changed from: private */
    public View mScrollbar;
    /* access modifiers changed from: private */
    public ViewPropertyAnimator mScrollbarAnimator;
    /* access modifiers changed from: private */
    public Runnable mScrollbarHider;
    private SectionIndexer mSectionIndexer;
    private boolean mShowBubble;
    private Drawable mTrackImage;
    private ImageView mTrackView;
    private int mViewHeight;

    public interface SectionIndexer {
        String getSectionText(int i);
    }

    public FastScroller(Context context) {
        super(context);
        this.mScrollbarHider = new Runnable() {
            public void run() {
                FastScroller.this.hideScrollbar();
            }
        };
        this.mScrollListener = new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (!FastScroller.this.mHandleView.isSelected() && FastScroller.this.isEnabled()) {
                    FastScroller fastScroller = FastScroller.this;
                    fastScroller.setViewPositions(fastScroller.getScrollProportion(recyclerView));
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (!FastScroller.this.isEnabled()) {
                    return;
                }
                if (i != 0) {
                    if (i == 1) {
                        FastScroller.this.getHandler().removeCallbacks(FastScroller.this.mScrollbarHider);
                        FastScroller fastScroller = FastScroller.this;
                        fastScroller.cancelAnimation(fastScroller.mScrollbarAnimator);
                        FastScroller fastScroller2 = FastScroller.this;
                        if (!fastScroller2.isViewVisible(fastScroller2.mScrollbar)) {
                            FastScroller.this.showScrollbar();
                        }
                    }
                } else if (FastScroller.this.mHideScrollbar && !FastScroller.this.mHandleView.isSelected()) {
                    FastScroller.this.getHandler().postDelayed(FastScroller.this.mScrollbarHider, 1000);
                }
            }
        };
        layout(context, (AttributeSet) null);
        setLayoutParams((ViewGroup.LayoutParams) new LinearLayout.LayoutParams(-2, -1));
    }

    public FastScroller(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FastScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollbarHider = new Runnable() {
            public void run() {
                FastScroller.this.hideScrollbar();
            }
        };
        this.mScrollListener = new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (!FastScroller.this.mHandleView.isSelected() && FastScroller.this.isEnabled()) {
                    FastScroller fastScroller = FastScroller.this;
                    fastScroller.setViewPositions(fastScroller.getScrollProportion(recyclerView));
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (!FastScroller.this.isEnabled()) {
                    return;
                }
                if (i != 0) {
                    if (i == 1) {
                        FastScroller.this.getHandler().removeCallbacks(FastScroller.this.mScrollbarHider);
                        FastScroller fastScroller = FastScroller.this;
                        fastScroller.cancelAnimation(fastScroller.mScrollbarAnimator);
                        FastScroller fastScroller2 = FastScroller.this;
                        if (!fastScroller2.isViewVisible(fastScroller2.mScrollbar)) {
                            FastScroller.this.showScrollbar();
                        }
                    }
                } else if (FastScroller.this.mHideScrollbar && !FastScroller.this.mHandleView.isSelected()) {
                    FastScroller.this.getHandler().postDelayed(FastScroller.this.mScrollbarHider, 1000);
                }
            }
        };
        layout(context, attributeSet);
        setLayoutParams((ViewGroup.LayoutParams) generateLayoutParams(attributeSet));
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = -2;
        super.setLayoutParams(layoutParams);
    }

    public void setLayoutParams(ViewGroup viewGroup) {
        RecyclerView recyclerView = this.mRecyclerView;
        int id = recyclerView != null ? recyclerView.getId() : -1;
        int dimensionPixelSize = getResources().getDimensionPixelSize(C2680R.dimen.fastscroll_scrollbar_margin_top);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C2680R.dimen.fastscroll_scrollbar_margin_bottom);
        if (id != -1) {
            int i = 7;
            if (viewGroup instanceof ConstraintLayout) {
                ConstraintSet constraintSet = new ConstraintSet();
                int id2 = getId();
                ConstraintLayout constraintLayout = (ConstraintLayout) viewGroup;
                constraintSet.clone(constraintLayout);
                constraintSet.connect(id2, 3, id, 3);
                constraintSet.connect(id2, 4, id, 4);
                constraintSet.connect(id2, 7, id, 7);
                constraintSet.applyTo(constraintLayout);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
                layoutParams.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize2);
                setLayoutParams((ViewGroup.LayoutParams) layoutParams);
            } else if (viewGroup instanceof CoordinatorLayout) {
                CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) getLayoutParams();
                layoutParams2.setAnchorId(id);
                layoutParams2.anchorGravity = GravityCompat.END;
                layoutParams2.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize2);
                setLayoutParams((ViewGroup.LayoutParams) layoutParams2);
            } else if (viewGroup instanceof FrameLayout) {
                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) getLayoutParams();
                layoutParams3.gravity = GravityCompat.END;
                layoutParams3.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize2);
                setLayoutParams((ViewGroup.LayoutParams) layoutParams3);
            } else if (viewGroup instanceof RelativeLayout) {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) getLayoutParams();
                if (Build.VERSION.SDK_INT >= 17) {
                    i = 19;
                }
                layoutParams4.addRule(6, id);
                layoutParams4.addRule(8, id);
                layoutParams4.addRule(i, id);
                layoutParams4.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize2);
                setLayoutParams((ViewGroup.LayoutParams) layoutParams4);
            } else {
                throw new IllegalArgumentException("Parent ViewGroup must be a ConstraintLayout, CoordinatorLayout, FrameLayout, or RelativeLayout");
            }
            updateViewHeights();
            return;
        }
        throw new IllegalArgumentException("RecyclerView must have a view ID");
    }

    public void setSectionIndexer(SectionIndexer sectionIndexer) {
        this.mSectionIndexer = sectionIndexer;
    }

    public void attachRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(this.mScrollListener);
            post(new Runnable() {
                public void run() {
                    FastScroller fastScroller = FastScroller.this;
                    fastScroller.setViewPositions(fastScroller.getScrollProportion(fastScroller.mRecyclerView));
                }
            });
        }
    }

    public void detachRecyclerView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(this.mScrollListener);
            this.mRecyclerView = null;
        }
    }

    public void setHideScrollbar(boolean z) {
        this.mHideScrollbar = z;
        this.mScrollbar.setVisibility(z ? 8 : 0);
    }

    public void setBubbleVisible(boolean z) {
        this.mShowBubble = z;
    }

    public void setTrackVisible(boolean z) {
        this.mTrackView.setVisibility(z ? 0 : 8);
    }

    public void setTrackColor(int i) {
        Drawable drawable;
        if (this.mTrackImage == null && (drawable = ContextCompat.getDrawable(getContext(), C2680R.C2682drawable.fastscroll_track)) != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mTrackImage = wrap;
            wrap.mutate();
        }
        DrawableCompat.setTint(this.mTrackImage, i);
        this.mTrackView.setImageDrawable(this.mTrackImage);
    }

    public void setHandleColor(int i) {
        Drawable drawable;
        this.mHandleColor = i;
        if (this.mHandleImage == null && (drawable = ContextCompat.getDrawable(getContext(), C2680R.C2682drawable.fastscroll_handle)) != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mHandleImage = wrap;
            wrap.mutate();
        }
        DrawableCompat.setTint(this.mHandleImage, this.mHandleColor);
        this.mHandleView.setImageDrawable(this.mHandleImage);
    }

    public void setBubbleColor(int i) {
        Drawable drawable;
        this.mBubbleColor = i;
        if (this.mBubbleImage == null && (drawable = ContextCompat.getDrawable(getContext(), C2680R.C2682drawable.fastscroll_bubble)) != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mBubbleImage = wrap;
            wrap.mutate();
        }
        DrawableCompat.setTint(this.mBubbleImage, this.mBubbleColor);
        if (Build.VERSION.SDK_INT >= 16) {
            this.mBubbleView.setBackground(this.mBubbleImage);
        } else {
            this.mBubbleView.setBackgroundDrawable(this.mBubbleImage);
        }
    }

    public void setBubbleTextColor(int i) {
        this.mBubbleView.setTextColor(i);
    }

    public void setFastScrollStateChangeListener(FastScrollStateChangeListener fastScrollStateChangeListener) {
        this.mFastScrollStateChangeListener = fastScrollStateChangeListener;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setVisibility(z ? 0 : 8);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return super.onTouchEvent(motionEvent);
                    }
                }
            }
            requestDisallowInterceptTouchEvent(false);
            setHandleSelected(false);
            if (this.mHideScrollbar) {
                getHandler().postDelayed(this.mScrollbarHider, 1000);
            }
            hideBubble();
            FastScrollStateChangeListener fastScrollStateChangeListener = this.mFastScrollStateChangeListener;
            if (fastScrollStateChangeListener != null) {
                fastScrollStateChangeListener.onFastScrollStop(this);
            }
            return true;
        } else if (motionEvent.getX() < this.mHandleView.getX() - ((float) ViewCompat.getPaddingStart(this.mHandleView))) {
            return false;
        } else {
            requestDisallowInterceptTouchEvent(true);
            setHandleSelected(true);
            getHandler().removeCallbacks(this.mScrollbarHider);
            cancelAnimation(this.mScrollbarAnimator);
            cancelAnimation(this.mBubbleAnimator);
            if (!isViewVisible(this.mScrollbar)) {
                showScrollbar();
            }
            if (this.mShowBubble && this.mSectionIndexer != null) {
                showBubble();
            }
            FastScrollStateChangeListener fastScrollStateChangeListener2 = this.mFastScrollStateChangeListener;
            if (fastScrollStateChangeListener2 != null) {
                fastScrollStateChangeListener2.onFastScrollStart(this);
            }
        }
        float y = motionEvent.getY();
        setViewPositions(y);
        setRecyclerViewPosition(y);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mViewHeight = i2;
    }

    private void setRecyclerViewPosition(float f) {
        SectionIndexer sectionIndexer;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            int itemCount = this.mRecyclerView.getAdapter().getItemCount();
            float f2 = 0.0f;
            if (this.mHandleView.getY() != 0.0f) {
                float y = this.mHandleView.getY() + ((float) this.mHandleHeight);
                int i = this.mViewHeight;
                f2 = y >= ((float) (i + -5)) ? 1.0f : f / ((float) i);
            }
            int round = Math.round(f2 * ((float) itemCount));
            if (isLayoutReversed(this.mRecyclerView.getLayoutManager())) {
                round = itemCount - round;
            }
            int valueInRange = getValueInRange(0, itemCount - 1, round);
            this.mRecyclerView.getLayoutManager().scrollToPosition(valueInRange);
            if (this.mShowBubble && (sectionIndexer = this.mSectionIndexer) != null) {
                this.mBubbleView.setText(sectionIndexer.getSectionText(valueInRange));
            }
        }
    }

    /* access modifiers changed from: private */
    public float getScrollProportion(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return 0.0f;
        }
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int computeVerticalScrollRange = recyclerView.computeVerticalScrollRange();
        int i = this.mViewHeight;
        float f = (float) (computeVerticalScrollRange - i);
        float f2 = (float) computeVerticalScrollOffset;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        return ((float) i) * (f2 / f);
    }

    private int getValueInRange(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    /* access modifiers changed from: private */
    public void setViewPositions(float f) {
        this.mBubbleHeight = this.mBubbleView.getHeight();
        int height = this.mHandleView.getHeight();
        this.mHandleHeight = height;
        int i = this.mViewHeight;
        int i2 = this.mBubbleHeight;
        int valueInRange = getValueInRange(0, (i - i2) - (height / 2), (int) (f - ((float) i2)));
        int i3 = this.mViewHeight;
        int i4 = this.mHandleHeight;
        int valueInRange2 = getValueInRange(0, i3 - i4, (int) (f - ((float) (i4 / 2))));
        if (this.mShowBubble) {
            this.mBubbleView.setY((float) valueInRange);
        }
        this.mHandleView.setY((float) valueInRange2);
    }

    private void updateViewHeights() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mBubbleView.measure(makeMeasureSpec, makeMeasureSpec);
        this.mBubbleHeight = this.mBubbleView.getMeasuredHeight();
        this.mHandleView.measure(makeMeasureSpec, makeMeasureSpec);
        this.mHandleHeight = this.mHandleView.getMeasuredHeight();
    }

    private boolean isLayoutReversed(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getReverseLayout();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getReverseLayout();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isViewVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    /* access modifiers changed from: private */
    public void cancelAnimation(ViewPropertyAnimator viewPropertyAnimator) {
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    private void showBubble() {
        if (!isViewVisible(this.mBubbleView)) {
            this.mBubbleView.setVisibility(0);
            this.mBubbleAnimator = this.mBubbleView.animate().alpha(1.0f).setDuration(100).setListener(new AnimatorListenerAdapter() {
            });
        }
    }

    private void hideBubble() {
        if (isViewVisible(this.mBubbleView)) {
            this.mBubbleAnimator = this.mBubbleView.animate().alpha(0.0f).setDuration(100).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    FastScroller.this.mBubbleView.setVisibility(8);
                    ViewPropertyAnimator unused = FastScroller.this.mBubbleAnimator = null;
                }

                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    FastScroller.this.mBubbleView.setVisibility(8);
                    ViewPropertyAnimator unused = FastScroller.this.mBubbleAnimator = null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showScrollbar() {
        if (this.mRecyclerView.computeVerticalScrollRange() - this.mViewHeight > 0) {
            this.mScrollbar.setTranslationX((float) getResources().getDimensionPixelSize(C2680R.dimen.fastscroll_scrollbar_padding_end));
            this.mScrollbar.setVisibility(0);
            this.mScrollbarAnimator = this.mScrollbar.animate().translationX(0.0f).alpha(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            });
        }
    }

    /* access modifiers changed from: private */
    public void hideScrollbar() {
        this.mScrollbarAnimator = this.mScrollbar.animate().translationX((float) getResources().getDimensionPixelSize(C2680R.dimen.fastscroll_scrollbar_padding_end)).alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FastScroller.this.mScrollbar.setVisibility(8);
                ViewPropertyAnimator unused = FastScroller.this.mScrollbarAnimator = null;
            }

            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                FastScroller.this.mScrollbar.setVisibility(8);
                ViewPropertyAnimator unused = FastScroller.this.mScrollbarAnimator = null;
            }
        });
    }

    private void setHandleSelected(boolean z) {
        this.mHandleView.setSelected(z);
        DrawableCompat.setTint(this.mHandleImage, z ? this.mBubbleColor : this.mHandleColor);
    }

    /* JADX INFO: finally extract failed */
    private void layout(Context context, AttributeSet attributeSet) {
        boolean z;
        TypedArray obtainStyledAttributes;
        inflate(context, C2680R.layout.fastscroller, this);
        boolean z2 = false;
        setClipChildren(false);
        setOrientation(0);
        this.mBubbleView = (TextView) findViewById(C2680R.C2683id.fastscroll_bubble);
        this.mHandleView = (ImageView) findViewById(C2680R.C2683id.fastscroll_handle);
        this.mTrackView = (ImageView) findViewById(C2680R.C2683id.fastscroll_track);
        this.mScrollbar = findViewById(C2680R.C2683id.fastscroll_scrollbar);
        boolean z3 = true;
        int i = -7829368;
        int i2 = -12303292;
        int i3 = -3355444;
        int i4 = -1;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2680R.styleable.FastScroller, 0, 0)) == null) {
            z = true;
        } else {
            try {
                i = obtainStyledAttributes.getColor(C2680R.styleable.FastScroller_bubbleColor, -7829368);
                i2 = obtainStyledAttributes.getColor(C2680R.styleable.FastScroller_handleColor, -12303292);
                i3 = obtainStyledAttributes.getColor(C2680R.styleable.FastScroller_trackColor, -3355444);
                i4 = obtainStyledAttributes.getColor(C2680R.styleable.FastScroller_bubbleTextColor, -1);
                boolean z4 = obtainStyledAttributes.getBoolean(C2680R.styleable.FastScroller_hideScrollbar, true);
                boolean z5 = obtainStyledAttributes.getBoolean(C2680R.styleable.FastScroller_showBubble, true);
                z2 = obtainStyledAttributes.getBoolean(C2680R.styleable.FastScroller_showTrack, false);
                obtainStyledAttributes.recycle();
                z = z5;
                z3 = z4;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        setTrackColor(i3);
        setHandleColor(i2);
        setBubbleColor(i);
        setBubbleTextColor(i4);
        setHideScrollbar(z3);
        setBubbleVisible(z);
        setTrackVisible(z2);
    }
}
