package p015me.relex.circleindicator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.Objects;

/* renamed from: me.relex.circleindicator.CircleIndicator */
public class CircleIndicator extends LinearLayout {
    private static final int DEFAULT_INDICATOR_WIDTH = 5;
    /* access modifiers changed from: private */
    public Animator mAnimatorIn;
    /* access modifiers changed from: private */
    public Animator mAnimatorOut;
    private int mAnimatorResId = C2971R.animator.scale_with_alpha;
    private int mAnimatorReverseResId = 0;
    private Animator mImmediateAnimatorIn;
    private Animator mImmediateAnimatorOut;
    /* access modifiers changed from: private */
    public int mIndicatorBackgroundResId = C2971R.C2972drawable.white_radius;
    private int mIndicatorHeight = -1;
    private int mIndicatorMargin = -1;
    /* access modifiers changed from: private */
    public int mIndicatorUnselectedBackgroundResId = C2971R.C2972drawable.white_radius;
    private int mIndicatorWidth = -1;
    private DataSetObserver mInternalDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            int count;
            super.onChanged();
            if (CircleIndicator.this.mViewpager != null && (count = CircleIndicator.this.mViewpager.getAdapter().getCount()) != CircleIndicator.this.getChildCount()) {
                if (CircleIndicator.this.mLastPosition < count) {
                    CircleIndicator circleIndicator = CircleIndicator.this;
                    int unused = circleIndicator.mLastPosition = circleIndicator.mViewpager.getCurrentItem();
                } else {
                    int unused2 = CircleIndicator.this.mLastPosition = -1;
                }
                CircleIndicator.this.createIndicators();
            }
        }
    };
    private final ViewPager.OnPageChangeListener mInternalPageChangeListener = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (CircleIndicator.this.mViewpager.getAdapter() != null && CircleIndicator.this.mViewpager.getAdapter().getCount() > 0) {
                if (CircleIndicator.this.mAnimatorIn.isRunning()) {
                    CircleIndicator.this.mAnimatorIn.end();
                    CircleIndicator.this.mAnimatorIn.cancel();
                }
                if (CircleIndicator.this.mAnimatorOut.isRunning()) {
                    CircleIndicator.this.mAnimatorOut.end();
                    CircleIndicator.this.mAnimatorOut.cancel();
                }
                if (CircleIndicator.this.mLastPosition >= 0) {
                    CircleIndicator circleIndicator = CircleIndicator.this;
                    View childAt = circleIndicator.getChildAt(circleIndicator.mLastPosition);
                    if (childAt != null) {
                        childAt.setBackgroundResource(CircleIndicator.this.mIndicatorUnselectedBackgroundResId);
                        CircleIndicator.this.mAnimatorIn.setTarget(childAt);
                        CircleIndicator.this.mAnimatorIn.start();
                    }
                }
                View childAt2 = CircleIndicator.this.getChildAt(i);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(CircleIndicator.this.mIndicatorBackgroundResId);
                    CircleIndicator.this.mAnimatorOut.setTarget(childAt2);
                    CircleIndicator.this.mAnimatorOut.start();
                }
                int unused = CircleIndicator.this.mLastPosition = i;
            }
        }
    };
    /* access modifiers changed from: private */
    public int mLastPosition = -1;
    /* access modifiers changed from: private */
    public ViewPager mViewpager;

    public CircleIndicator(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        handleTypedArray(context, attributeSet);
        checkIndicatorConfig(context);
    }

    private void handleTypedArray(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2971R.styleable.CircleIndicator);
            this.mIndicatorWidth = obtainStyledAttributes.getDimensionPixelSize(C2971R.styleable.CircleIndicator_ci_width, -1);
            this.mIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(C2971R.styleable.CircleIndicator_ci_height, -1);
            this.mIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(C2971R.styleable.CircleIndicator_ci_margin, -1);
            this.mAnimatorResId = obtainStyledAttributes.getResourceId(C2971R.styleable.CircleIndicator_ci_animator, C2971R.animator.scale_with_alpha);
            int i = 0;
            this.mAnimatorReverseResId = obtainStyledAttributes.getResourceId(C2971R.styleable.CircleIndicator_ci_animator_reverse, 0);
            this.mIndicatorBackgroundResId = obtainStyledAttributes.getResourceId(C2971R.styleable.CircleIndicator_ci_drawable, C2971R.C2972drawable.white_radius);
            this.mIndicatorUnselectedBackgroundResId = obtainStyledAttributes.getResourceId(C2971R.styleable.CircleIndicator_ci_drawable_unselected, this.mIndicatorBackgroundResId);
            if (obtainStyledAttributes.getInt(C2971R.styleable.CircleIndicator_ci_orientation, -1) == 1) {
                i = 1;
            }
            setOrientation(i);
            int i2 = obtainStyledAttributes.getInt(C2971R.styleable.CircleIndicator_ci_gravity, -1);
            if (i2 < 0) {
                i2 = 17;
            }
            setGravity(i2);
            obtainStyledAttributes.recycle();
        }
    }

    public void configureIndicator(int i, int i2, int i3) {
        configureIndicator(i, i2, i3, C2971R.animator.scale_with_alpha, 0, C2971R.C2972drawable.white_radius, C2971R.C2972drawable.white_radius);
    }

    public void configureIndicator(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mIndicatorWidth = i;
        this.mIndicatorHeight = i2;
        this.mIndicatorMargin = i3;
        this.mAnimatorResId = i4;
        this.mAnimatorReverseResId = i5;
        this.mIndicatorBackgroundResId = i6;
        this.mIndicatorUnselectedBackgroundResId = i7;
        checkIndicatorConfig(getContext());
    }

    private void checkIndicatorConfig(Context context) {
        int i = this.mIndicatorWidth;
        if (i < 0) {
            i = dip2px(5.0f);
        }
        this.mIndicatorWidth = i;
        int i2 = this.mIndicatorHeight;
        if (i2 < 0) {
            i2 = dip2px(5.0f);
        }
        this.mIndicatorHeight = i2;
        int i3 = this.mIndicatorMargin;
        if (i3 < 0) {
            i3 = dip2px(5.0f);
        }
        this.mIndicatorMargin = i3;
        int i4 = this.mAnimatorResId;
        if (i4 == 0) {
            i4 = C2971R.animator.scale_with_alpha;
        }
        this.mAnimatorResId = i4;
        this.mAnimatorOut = createAnimatorOut(context);
        Animator createAnimatorOut = createAnimatorOut(context);
        this.mImmediateAnimatorOut = createAnimatorOut;
        createAnimatorOut.setDuration(0);
        this.mAnimatorIn = createAnimatorIn(context);
        Animator createAnimatorIn = createAnimatorIn(context);
        this.mImmediateAnimatorIn = createAnimatorIn;
        createAnimatorIn.setDuration(0);
        int i5 = this.mIndicatorBackgroundResId;
        if (i5 == 0) {
            i5 = C2971R.C2972drawable.white_radius;
        }
        this.mIndicatorBackgroundResId = i5;
        int i6 = this.mIndicatorUnselectedBackgroundResId;
        if (i6 != 0) {
            i5 = i6;
        }
        this.mIndicatorUnselectedBackgroundResId = i5;
    }

    private Animator createAnimatorOut(Context context) {
        return AnimatorInflater.loadAnimator(context, this.mAnimatorResId);
    }

    private Animator createAnimatorIn(Context context) {
        int i = this.mAnimatorReverseResId;
        if (i != 0) {
            return AnimatorInflater.loadAnimator(context, i);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, this.mAnimatorResId);
        loadAnimator.setInterpolator(new ReverseInterpolator());
        return loadAnimator;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewpager = viewPager;
        if (viewPager != null && viewPager.getAdapter() != null) {
            this.mLastPosition = -1;
            createIndicators();
            this.mViewpager.removeOnPageChangeListener(this.mInternalPageChangeListener);
            this.mViewpager.addOnPageChangeListener(this.mInternalPageChangeListener);
            this.mInternalPageChangeListener.onPageSelected(this.mViewpager.getCurrentItem());
        }
    }

    public DataSetObserver getDataSetObserver() {
        return this.mInternalDataSetObserver;
    }

    @Deprecated
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        ViewPager viewPager = this.mViewpager;
        Objects.requireNonNull(viewPager, "can not find Viewpager , setViewPager first");
        viewPager.removeOnPageChangeListener(onPageChangeListener);
        this.mViewpager.addOnPageChangeListener(onPageChangeListener);
    }

    /* access modifiers changed from: private */
    public void createIndicators() {
        removeAllViews();
        int count = this.mViewpager.getAdapter().getCount();
        if (count > 0) {
            int currentItem = this.mViewpager.getCurrentItem();
            int orientation = getOrientation();
            for (int i = 0; i < count; i++) {
                if (currentItem == i) {
                    addIndicator(orientation, this.mIndicatorBackgroundResId, this.mImmediateAnimatorOut);
                } else {
                    addIndicator(orientation, this.mIndicatorUnselectedBackgroundResId, this.mImmediateAnimatorIn);
                }
            }
        }
    }

    private void addIndicator(int i, int i2, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i2);
        addView(view, this.mIndicatorWidth, this.mIndicatorHeight);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (i == 0) {
            layoutParams.leftMargin = this.mIndicatorMargin;
            layoutParams.rightMargin = this.mIndicatorMargin;
        } else {
            layoutParams.topMargin = this.mIndicatorMargin;
            layoutParams.bottomMargin = this.mIndicatorMargin;
        }
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    /* renamed from: me.relex.circleindicator.CircleIndicator$ReverseInterpolator */
    private class ReverseInterpolator implements Interpolator {
        private ReverseInterpolator() {
        }

        public float getInterpolation(float f) {
            return Math.abs(1.0f - f);
        }
    }

    public int dip2px(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
