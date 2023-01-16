package com.jch.racWiFi.customViews.HintCase;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;
import com.jch.racWiFi.customViews.HintCase.ShapeAnimator;
import com.jch.racWiFi.customViews.HintCase.utils.DimenUtils;
import java.util.ArrayList;
import java.util.List;

class HintCaseView extends RelativeLayout {
    private static final int DEFAULT_BACKGROUND_COLOR = -872415232;
    private static final int DEFAULT_HINT_BLOCK_POSITION = 3;
    private static final Shape DEFAULT_SHAPE = new RectangularShape();
    private static final ContentHolder NO_BLOCK_INFO = null;
    private static final View NO_BLOCK_INFO_VIEW = null;
    private static final View NO_TARGETVIEW = null;
    private int backgroundColor;
    private Paint basePaint;
    private Bitmap bitmap;
    private boolean closeOnTouch;
    /* access modifiers changed from: private */
    public List<View> extraBlockViews;
    private List<ContentHolder> extraBlocks;
    private ContentHolderAnimator hideContentHolderAnimator;
    private List<ContentHolderAnimator> hideExtraContentHolderAnimators;
    private ShapeAnimator hideShapeAnimator;
    private ContentHolder hintBlock;
    private int hintBlockPosition;
    private View hintBlockView;
    private C1687HintCase hintCase;
    private int hintCasePosition = 3;
    private boolean isTargetClickable;
    private Point navigationBarSizeIfExistAtTheBottom;
    private Point navigationBarSizeIfExistOnTheRight;
    private int offsetInPx;
    private C1687HintCase.OnClosedListener onClosedListener;
    private ViewGroup parent;
    private int parentIndex;
    private Shape shape = DEFAULT_SHAPE;
    /* access modifiers changed from: private */
    public ContentHolderAnimator showContentHolderAnimator;
    /* access modifiers changed from: private */
    public List<ContentHolderAnimator> showExtraContentHolderAnimators;
    private ShapeAnimator showShapeAnimator;
    private View targetView;
    private boolean wasPressedOnShape;

    public void setHintCasePosition(int i) {
        this.hintCasePosition = i;
    }

    public View getHintBlockView() {
        return this.hintBlockView;
    }

    public HintCaseView(Context context, C1687HintCase hintCase2) {
        super(context);
        init(hintCase2);
    }

    private void init(C1687HintCase hintCase2) {
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.hintCase = hintCase2;
        this.closeOnTouch = true;
        this.showExtraContentHolderAnimators = new ArrayList();
        this.hideExtraContentHolderAnimators = new ArrayList();
        this.hintBlock = NO_BLOCK_INFO;
        this.hintBlockView = NO_BLOCK_INFO_VIEW;
        this.extraBlocks = new ArrayList();
        this.extraBlockViews = new ArrayList();
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
        this.offsetInPx = 0;
        this.hintBlockPosition = 3;
        this.basePaint = new Paint(1);
        this.navigationBarSizeIfExistAtTheBottom = DimenUtils.getNavigationBarSizeIfExistAtTheBottom(getContext());
        this.navigationBarSizeIfExistOnTheRight = DimenUtils.getNavigationBarSizeIfExistOnTheRight(getContext());
    }

    private void buildBaseBitmap() {
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        if (this.parent.getMeasuredWidth() > 0 && this.parent.getMeasuredHeight() > 0) {
            this.bitmap = Bitmap.createBitmap(this.parent.getMeasuredWidth(), this.parent.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
    }

    private void performShow() {
        this.parent.addView(this, this.parentIndex);
        if (this.showShapeAnimator != ShapeAnimator.NO_ANIMATOR) {
            this.showShapeAnimator.getAnimator(this, this.shape, new ShapeAnimator.OnFinishListener() {
                public void onFinish() {
                    HintCaseView.this.performShowBlocks();
                }
            }).start();
            return;
        }
        this.shape.setMinimumValue();
        performShowBlocks();
    }

    /* access modifiers changed from: private */
    public void performShowBlocks() {
        ArrayList arrayList = new ArrayList();
        if (this.showContentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
            arrayList.add(this.showContentHolderAnimator.getAnimator(this.hintBlockView));
        }
        if (!this.showExtraContentHolderAnimators.isEmpty()) {
            for (int i = 0; i < this.extraBlocks.size(); i++) {
                ContentHolderAnimator contentHolderAnimator = this.showExtraContentHolderAnimators.get(i);
                if (contentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
                    arrayList.add(contentHolderAnimator.getAnimator(this.extraBlockViews.get(i)));
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (arrayList.isEmpty()) {
            if (existHintBlock()) {
                getHintBlockView().setAlpha(1.0f);
            }
            for (View alpha : this.extraBlockViews) {
                alpha.setAlpha(1.0f);
            }
            return;
        }
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (HintCaseView.this.existHintBlock() && HintCaseView.this.showContentHolderAnimator == ContentHolderAnimator.NO_ANIMATOR) {
                    HintCaseView.this.getHintBlockView().setAlpha(1.0f);
                }
                for (int i = 0; i < HintCaseView.this.showExtraContentHolderAnimators.size(); i++) {
                    if (((ContentHolderAnimator) HintCaseView.this.showExtraContentHolderAnimators.get(i)) == ContentHolderAnimator.NO_ANIMATOR) {
                        ((View) HintCaseView.this.extraBlockViews.get(i)).setAlpha(1.0f);
                    }
                }
            }
        });
        animatorSet.start();
    }

    /* access modifiers changed from: package-private */
    public void performHide() {
        ArrayList arrayList = new ArrayList();
        if (this.hideContentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
            arrayList.add(this.hideContentHolderAnimator.getAnimator(this.hintBlockView));
        } else if (existHintBlock()) {
            getHintBlockView().setAlpha(0.0f);
        }
        if (!this.hideExtraContentHolderAnimators.isEmpty()) {
            for (int i = 0; i < this.extraBlocks.size(); i++) {
                ContentHolderAnimator contentHolderAnimator = this.hideExtraContentHolderAnimators.get(i);
                if (contentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
                    arrayList.add(contentHolderAnimator.getAnimator(this.extraBlockViews.get(i)));
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (arrayList.isEmpty()) {
            for (View alpha : this.extraBlockViews) {
                alpha.setAlpha(0.0f);
            }
            performHideShape();
            return;
        }
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                HintCaseView.this.performHideShape();
            }
        });
        animatorSet.start();
    }

    /* access modifiers changed from: private */
    public void performHideShape() {
        ArrayList arrayList = new ArrayList();
        if (this.hideShapeAnimator != ShapeAnimator.NO_ANIMATOR) {
            arrayList.add(this.hideShapeAnimator.getAnimator(this, this.shape));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (arrayList.isEmpty()) {
            close();
            return;
        }
        animatorSet.playSequentially(arrayList);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                HintCaseView.this.close();
            }
        });
        animatorSet.start();
    }

    /* access modifiers changed from: private */
    public void close() {
        removeView();
        clearData();
        C1687HintCase.OnClosedListener onClosedListener2 = this.onClosedListener;
        if (onClosedListener2 != null) {
            onClosedListener2.onClosed();
        }
    }

    private void clearData() {
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.bitmap = null;
        this.parent = null;
        this.hintCase = null;
    }

    private void removeView() {
        ViewGroup viewGroup = this.parent;
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
    }

    private void setViews() {
        if (existHintBlock()) {
            FrameLayout hintBlockFrameLayout = getHintBlockFrameLayout();
            if (this.hintBlockView == NO_BLOCK_INFO_VIEW) {
                View view = this.hintBlock.getView(getContext(), this.hintCase, hintBlockFrameLayout);
                this.hintBlockView = view;
                view.setAlpha(0.0f);
            }
            hintBlockFrameLayout.addView(this.hintBlockView);
            addView(hintBlockFrameLayout);
        }
        if (existExtraBlock()) {
            RelativeLayout extraContentHolderRelativeLayout = getExtraContentHolderRelativeLayout();
            for (int i = 0; i < this.extraBlocks.size(); i++) {
                View view2 = this.extraBlocks.get(i).getView(getContext(), this.hintCase, extraContentHolderRelativeLayout);
                if (this.showExtraContentHolderAnimators.get(i) != ContentHolderAnimator.NO_ANIMATOR) {
                    view2.setAlpha(0.0f);
                }
                this.extraBlockViews.add(view2);
                extraContentHolderRelativeLayout.addView(view2);
            }
            addView(extraContentHolderRelativeLayout);
        }
    }

    /* access modifiers changed from: private */
    public boolean existHintBlock() {
        return this.hintBlock != NO_BLOCK_INFO;
    }

    private boolean existExtraBlock() {
        return !this.extraBlocks.isEmpty();
    }

    private FrameLayout getHintBlockFrameLayout() {
        int i;
        int i2;
        int i3;
        int i4 = this.hintBlockPosition;
        int i5 = 12;
        int i6 = 0;
        if (i4 == 0) {
            i6 = this.shape.getLeft() - this.parent.getLeft();
            i = this.parent.getHeight() - DimenUtils.getStatusBarHeight(getContext());
            i5 = 9;
        } else if (i4 == 1) {
            i6 = this.parent.getWidth();
            i = (this.shape.getTop() - this.parent.getTop()) - DimenUtils.getStatusBarHeight(getContext());
            i5 = 10;
        } else if (i4 != 2) {
            if (i4 == 3) {
                i6 = this.parent.getWidth();
                i3 = this.parent.getBottom() - this.navigationBarSizeIfExistAtTheBottom.y;
                i2 = this.shape.getBottom();
            } else if (i4 != 4) {
                i = 0;
                i5 = 0;
            } else {
                i6 = this.parent.getWidth() - this.navigationBarSizeIfExistOnTheRight.x;
                i3 = this.parent.getHeight() - this.navigationBarSizeIfExistAtTheBottom.y;
                i2 = DimenUtils.getStatusBarHeight(getContext());
            }
            i = i3 - i2;
        } else {
            i6 = (this.parent.getRight() - this.navigationBarSizeIfExistOnTheRight.x) - this.shape.getRight();
            i = this.parent.getHeight() - DimenUtils.getStatusBarHeight(getContext());
            i5 = 11;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i6, i);
        layoutParams.addRule(i5);
        layoutParams.topMargin = DimenUtils.getStatusBarHeight(getContext());
        layoutParams.bottomMargin = this.navigationBarSizeIfExistAtTheBottom.y;
        layoutParams.rightMargin = this.navigationBarSizeIfExistOnTheRight.x;
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(layoutParams);
        return frameLayout;
    }

    private RelativeLayout getExtraContentHolderRelativeLayout() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.parent.getWidth(), this.parent.getHeight());
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        layoutParams.topMargin = DimenUtils.getStatusBarHeight(getContext());
        layoutParams.bottomMargin = this.navigationBarSizeIfExistAtTheBottom.y;
        layoutParams.rightMargin = this.navigationBarSizeIfExistOnTheRight.x;
        relativeLayout.setLayoutParams(layoutParams);
        return relativeLayout;
    }

    private void calculateHintBlockPosition(ViewGroup viewGroup, Shape shape2) {
        if (this.targetView == NO_TARGETVIEW) {
            this.hintBlockPosition = 4;
            return;
        }
        shape2.getLeft();
        viewGroup.getLeft();
        shape2.getTop();
        viewGroup.getTop();
        viewGroup.getRight();
        shape2.getRight();
        viewGroup.getBottom();
        shape2.getBottom();
        this.hintBlockPosition = 0;
        this.hintBlockPosition = this.hintCasePosition;
    }

    public void setOnClosedListener(C1687HintCase.OnClosedListener onClosedListener2) {
        this.onClosedListener = onClosedListener2;
    }

    public void setTargetInfo(View view, Shape shape2, int i, boolean z) {
        this.targetView = view;
        this.shape = shape2;
        this.offsetInPx = i;
        this.isTargetClickable = z;
    }

    public void setOverDecorView(View view) {
        this.parent = (ViewGroup) view;
        this.parentIndex = -1;
    }

    public void setParentView(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        this.parent = viewGroup;
        this.parentIndex = viewGroup.getChildCount();
    }

    public void setCloseOnTouch(boolean z) {
        this.closeOnTouch = z;
    }

    public void show() {
        initializeView();
        performShow();
    }

    public void initializeView() {
        this.shape.setShapeInfo(this.targetView, this.parent, this.offsetInPx, getContext());
        calculateHintBlockPosition(this.parent, this.shape);
        setViews();
        buildBaseBitmap();
    }

    public Shape getShape() {
        return this.shape;
    }

    public void setShape(ShapeAnimator shapeAnimator, ShapeAnimator shapeAnimator2) {
        this.showShapeAnimator = shapeAnimator;
        this.hideShapeAnimator = shapeAnimator2;
    }

    public void setHintBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator, ContentHolderAnimator contentHolderAnimator2) {
        this.hintBlock = contentHolder;
        this.showContentHolderAnimator = contentHolderAnimator;
        this.hideContentHolderAnimator = contentHolderAnimator2;
    }

    public int getHintBlockPosition() {
        return this.hintBlockPosition;
    }

    public void setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator, ContentHolderAnimator contentHolderAnimator2) {
        if (contentHolder != null) {
            this.extraBlocks.add(contentHolder);
            this.showExtraContentHolderAnimators.add(contentHolderAnimator);
            this.hideExtraContentHolderAnimators.add(contentHolderAnimator2);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ContentHolder contentHolder = this.hintBlock;
        if (contentHolder != NO_BLOCK_INFO) {
            contentHolder.onLayout();
        }
        for (ContentHolder onLayout : this.extraBlocks) {
            onLayout.onLayout();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.wasPressedOnShape = this.shape.isTouchEventInsideTheHint(motionEvent);
        } else if (action == 1) {
            if (this.closeOnTouch) {
                performHide();
            }
            if (this.targetView != null && this.isTargetClickable && this.wasPressedOnShape && this.shape.isTouchEventInsideTheHint(motionEvent)) {
                this.targetView.performClick();
            }
        } else if (action == 2 && !this.shape.isTouchEventInsideTheHint(motionEvent)) {
            this.wasPressedOnShape = false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 == null) {
            super.dispatchDraw(canvas);
            return;
        }
        bitmap2.eraseColor(this.backgroundColor);
        if (!(this.shape == null || (this.showShapeAnimator == ShapeAnimator.NO_ANIMATOR && this.hideShapeAnimator == ShapeAnimator.NO_ANIMATOR))) {
            this.shape.draw(new Canvas(this.bitmap));
        }
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.basePaint);
        super.dispatchDraw(canvas);
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }
}
