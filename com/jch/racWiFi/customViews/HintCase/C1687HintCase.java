package com.jch.racWiFi.customViews.HintCase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.utils.DimenUtils;

/* renamed from: com.jch.racWiFi.customViews.HintCase.HintCase */
public class C1687HintCase {
    public static final int BACKGROUND_COLOR_TRANSPARENT = 0;
    public static final int DEFAULT_SHAPE_OFFSET_IN_DP = 10;
    public static final int HINT_BLOCK_POSITION_BOTTOM = 3;
    public static final int HINT_BLOCK_POSITION_CENTER = 4;
    public static final int HINT_BLOCK_POSITION_LEFT = 0;
    public static final int HINT_BLOCK_POSITION_RIGHT = 2;
    public static final int HINT_BLOCK_POSITION_TOP = 1;
    public static final int NO_OFFSET_IN_PX = 0;
    public static final boolean TARGET_IS_CLICKABLE = true;
    public static final boolean TARGET_IS_NOT_CLICKABLE = false;
    private Context context;
    private HintCaseView hintCaseView;

    /* renamed from: com.jch.racWiFi.customViews.HintCase.HintCase$OnClosedListener */
    public interface OnClosedListener {
        void onClosed();
    }

    public Shape getShape() {
        return this.hintCaseView.getShape();
    }

    public void hide() {
        this.hintCaseView.performHide();
        this.hintCaseView = null;
    }

    public C1687HintCase(View view) {
        this.context = view.getContext();
        HintCaseView hintCaseView2 = new HintCaseView(this.context, this);
        this.hintCaseView = hintCaseView2;
        hintCaseView2.setTargetInfo((View) null, new RectangularShape(), 0, false);
        this.hintCaseView.setParentView(view);
    }

    public C1687HintCase setHintCasePosition(int i) {
        this.hintCaseView.setHintCasePosition(i);
        return this;
    }

    public View getView() {
        return this.hintCaseView;
    }

    public C1687HintCase setTarget(View view) {
        return setCompleteTarget(view, new RectangularShape(), DimenUtils.dipToPixels(this.context, 10.0f), true);
    }

    public C1687HintCase setTarget(View view, int i) {
        return setCompleteTarget(view, new RectangularShape(), this.context.getResources().getDimensionPixelSize(i), true);
    }

    public C1687HintCase setTarget(View view, boolean z) {
        return setCompleteTarget(view, new RectangularShape(), DimenUtils.dipToPixels(this.context, 10.0f), z);
    }

    public C1687HintCase setTarget(View view, boolean z, int i) {
        return setCompleteTarget(view, new RectangularShape(), this.context.getResources().getDimensionPixelSize(i), z);
    }

    public C1687HintCase setTarget(View view, Shape shape) {
        return setCompleteTarget(view, shape, DimenUtils.dipToPixels(this.context, 10.0f), true);
    }

    public C1687HintCase setTarget(View view, Shape shape, int i) {
        return setCompleteTarget(view, shape, this.context.getResources().getDimensionPixelSize(i), true);
    }

    public C1687HintCase setTarget(View view, Shape shape, boolean z) {
        return setCompleteTarget(view, shape, DimenUtils.dipToPixels(this.context, 10.0f), z);
    }

    public C1687HintCase setTarget(View view, Shape shape, boolean z, int i) {
        return setCompleteTarget(view, shape, this.context.getResources().getDimensionPixelSize(i), z);
    }

    private C1687HintCase setCompleteTarget(View view, Shape shape, int i, boolean z) {
        this.hintCaseView.setTargetInfo(view, shape, i, z);
        return this;
    }

    public C1687HintCase setBackgroundColor(int i) {
        this.hintCaseView.setBackgroundColor(i);
        return this;
    }

    public C1687HintCase setBackgroundColorByResourceId(int i) {
        this.hintCaseView.setBackgroundColor(this.context.getResources().getColor(i));
        return this;
    }

    public C1687HintCase setShapeAnimators(ShapeAnimator shapeAnimator) {
        return setShapeAnimators(shapeAnimator, ShapeAnimator.NO_ANIMATOR);
    }

    public C1687HintCase setShapeAnimators(ShapeAnimator shapeAnimator, ShapeAnimator shapeAnimator2) {
        this.hintCaseView.setShape(shapeAnimator, shapeAnimator2);
        return this;
    }

    public C1687HintCase setHintBlock(ContentHolder contentHolder) {
        this.hintCaseView.setHintBlock(contentHolder, ContentHolderAnimator.NO_ANIMATOR, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public C1687HintCase setHintBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator) {
        this.hintCaseView.setHintBlock(contentHolder, contentHolderAnimator, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public C1687HintCase setHintBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator, ContentHolderAnimator contentHolderAnimator2) {
        this.hintCaseView.setHintBlock(contentHolder, contentHolderAnimator, contentHolderAnimator2);
        return this;
    }

    public C1687HintCase setExtraBlock(ContentHolder contentHolder) {
        this.hintCaseView.setExtraBlock(contentHolder, ContentHolderAnimator.NO_ANIMATOR, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public C1687HintCase setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator) {
        this.hintCaseView.setExtraBlock(contentHolder, contentHolderAnimator, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public C1687HintCase setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator contentHolderAnimator, ContentHolderAnimator contentHolderAnimator2) {
        this.hintCaseView.setExtraBlock(contentHolder, contentHolderAnimator, contentHolderAnimator2);
        return this;
    }

    public C1687HintCase setCloseOnTouchView(boolean z) {
        this.hintCaseView.setCloseOnTouch(z);
        return this;
    }

    public C1687HintCase setOverDecorView(boolean z, Activity activity) {
        if (activity != null) {
            setOverDecorView(z, activity.getWindow().getDecorView());
        }
        return this;
    }

    public C1687HintCase setOverDecorView(boolean z, View view) {
        if (z) {
            this.hintCaseView.setOverDecorView(view);
        }
        return this;
    }

    public C1687HintCase setOnClosedListener(OnClosedListener onClosedListener) {
        this.hintCaseView.setOnClosedListener(onClosedListener);
        return this;
    }

    public int getBlockInfoPosition() {
        return this.hintCaseView.getHintBlockPosition();
    }

    public void show() {
        this.hintCaseView.show();
    }
}
