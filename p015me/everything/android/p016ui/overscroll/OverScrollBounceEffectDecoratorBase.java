package p015me.everything.android.p016ui.overscroll;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import p015me.everything.android.p016ui.overscroll.ListenerStubs;
import p015me.everything.android.p016ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase */
public abstract class OverScrollBounceEffectDecoratorBase implements IOverScrollDecor, View.OnTouchListener {
    public static final float DEFAULT_DECELERATE_FACTOR = -2.0f;
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1.0f;
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3.0f;
    protected static final int MAX_BOUNCE_BACK_DURATION_MS = 800;
    protected static final int MIN_BOUNCE_BACK_DURATION_MS = 200;
    public static final String TAG = "OverScrollDecor";
    protected final BounceBackState mBounceBackState;
    protected IDecoratorState mCurrentState;
    protected final IdleState mIdleState;
    protected final OverScrollingState mOverScrollingState;
    protected final OverScrollStartAttributes mStartAttr = new OverScrollStartAttributes();
    protected IOverScrollStateListener mStateListener = new ListenerStubs.OverScrollStateListenerStub();
    protected IOverScrollUpdateListener mUpdateListener = new ListenerStubs.OverScrollUpdateListenerStub();
    protected float mVelocity;
    protected final IOverScrollDecoratorAdapter mViewAdapter;

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$IDecoratorState */
    protected interface IDecoratorState {
        int getStateId();

        void handleEntryTransition(IDecoratorState iDecoratorState);

        boolean handleMoveTouchEvent(MotionEvent motionEvent);

        boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent);
    }

    /* access modifiers changed from: protected */
    public abstract AnimationAttributes createAnimationAttributes();

    /* access modifiers changed from: protected */
    public abstract MotionAttributes createMotionAttributes();

    /* access modifiers changed from: protected */
    public abstract void translateView(View view, float f);

    /* access modifiers changed from: protected */
    public abstract void translateViewAndEvent(View view, float f, MotionEvent motionEvent);

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$MotionAttributes */
    protected static abstract class MotionAttributes {
        public float mAbsOffset;
        public float mDeltaOffset;
        public boolean mDir;

        /* access modifiers changed from: protected */
        public abstract boolean init(View view, MotionEvent motionEvent);

        protected MotionAttributes() {
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$OverScrollStartAttributes */
    protected static class OverScrollStartAttributes {
        protected float mAbsOffset;
        protected boolean mDir;
        protected int mPointerId;

        protected OverScrollStartAttributes() {
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$AnimationAttributes */
    protected static abstract class AnimationAttributes {
        public float mAbsOffset;
        public float mMaxOffset;
        public Property<View, Float> mProperty;

        /* access modifiers changed from: protected */
        public abstract void init(View view);

        protected AnimationAttributes() {
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$IdleState */
    protected class IdleState implements IDecoratorState {
        final MotionAttributes mMoveAttr;

        public int getStateId() {
            return 0;
        }

        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public IdleState() {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
        }

        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            if (!this.mMoveAttr.init(OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView(), motionEvent)) {
                return false;
            }
            if ((!OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteStart() || !this.mMoveAttr.mDir) && (!OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteEnd() || this.mMoveAttr.mDir)) {
                return false;
            }
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId = motionEvent.getPointerId(0);
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset = this.mMoveAttr.mAbsOffset;
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir = this.mMoveAttr.mDir;
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mOverScrollingState);
            return OverScrollBounceEffectDecoratorBase.this.mOverScrollingState.handleMoveTouchEvent(motionEvent);
        }

        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$OverScrollingState */
    protected class OverScrollingState implements IDecoratorState {
        int mCurrDragState;
        final MotionAttributes mMoveAttr;
        protected final float mTouchDragRatioBck;
        protected final float mTouchDragRatioFwd;

        public OverScrollingState(float f, float f2) {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
            this.mTouchDragRatioFwd = f;
            this.mTouchDragRatioBck = f2;
        }

        public int getStateId() {
            return this.mCurrDragState;
        }

        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            if (OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId != motionEvent.getPointerId(0)) {
                OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
                overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mBounceBackState);
                return true;
            }
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            if (!this.mMoveAttr.init(view, motionEvent)) {
                return true;
            }
            float f = this.mMoveAttr.mDeltaOffset / (this.mMoveAttr.mDir == OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? this.mTouchDragRatioFwd : this.mTouchDragRatioBck);
            float f2 = this.mMoveAttr.mAbsOffset + f;
            if ((!OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir || this.mMoveAttr.mDir || f2 > OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset) && (OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir || !this.mMoveAttr.mDir || f2 < OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset)) {
                if (view.getParent() != null) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                }
                long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
                if (eventTime > 0) {
                    OverScrollBounceEffectDecoratorBase.this.mVelocity = f / ((float) eventTime);
                }
                OverScrollBounceEffectDecoratorBase.this.translateView(view, f2);
                OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, f2);
                return true;
            }
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase2 = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase2.translateViewAndEvent(view, overScrollBounceEffectDecoratorBase2.mStartAttr.mAbsOffset, motionEvent);
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, 0.0f);
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase3 = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase3.issueStateTransition(overScrollBounceEffectDecoratorBase3.mIdleState);
            return true;
        }

        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mBounceBackState);
            return false;
        }

        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            this.mCurrDragState = OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? 1 : 2;
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase$BounceBackState */
    protected class BounceBackState implements IDecoratorState, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        protected final AnimationAttributes mAnimAttributes;
        protected final Interpolator mBounceBackInterpolator = new DecelerateInterpolator();
        protected final float mDecelerateFactor;
        protected final float mDoubleDecelerateFactor;

        public int getStateId() {
            return 3;
        }

        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public BounceBackState(float f) {
            this.mDecelerateFactor = f;
            this.mDoubleDecelerateFactor = f * 2.0f;
            this.mAnimAttributes = OverScrollBounceEffectDecoratorBase.this.createAnimationAttributes();
        }

        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
            Animator createAnimator = createAnimator();
            createAnimator.addListener(this);
            createAnimator.start();
        }

        public void onAnimationEnd(Animator animator) {
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mIdleState);
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }

        /* access modifiers changed from: protected */
        public Animator createAnimator() {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            this.mAnimAttributes.init(view);
            float f = 0.0f;
            if (OverScrollBounceEffectDecoratorBase.this.mVelocity == 0.0f || ((OverScrollBounceEffectDecoratorBase.this.mVelocity < 0.0f && OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir) || (OverScrollBounceEffectDecoratorBase.this.mVelocity > 0.0f && !OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir))) {
                return createBounceBackAnimator(this.mAnimAttributes.mAbsOffset);
            }
            float f2 = (-OverScrollBounceEffectDecoratorBase.this.mVelocity) / this.mDecelerateFactor;
            if (f2 >= 0.0f) {
                f = f2;
            }
            float f3 = this.mAnimAttributes.mAbsOffset + (((-OverScrollBounceEffectDecoratorBase.this.mVelocity) * OverScrollBounceEffectDecoratorBase.this.mVelocity) / this.mDoubleDecelerateFactor);
            ObjectAnimator createSlowdownAnimator = createSlowdownAnimator(view, (int) f, f3);
            ObjectAnimator createBounceBackAnimator = createBounceBackAnimator(f3);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{createSlowdownAnimator, createBounceBackAnimator});
            return animatorSet;
        }

        /* access modifiers changed from: protected */
        public ObjectAnimator createSlowdownAnimator(View view, int i, float f) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, this.mAnimAttributes.mProperty, new float[]{f});
            ofFloat.setDuration((long) i);
            ofFloat.setInterpolator(this.mBounceBackInterpolator);
            ofFloat.addUpdateListener(this);
            return ofFloat;
        }

        /* access modifiers changed from: protected */
        public ObjectAnimator createBounceBackAnimator(float f) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView(), this.mAnimAttributes.mProperty, new float[]{OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset});
            ofFloat.setDuration((long) Math.max((int) ((Math.abs(f) / this.mAnimAttributes.mMaxOffset) * 800.0f), 200));
            ofFloat.setInterpolator(this.mBounceBackInterpolator);
            ofFloat.addUpdateListener(this);
            return ofFloat;
        }
    }

    public OverScrollBounceEffectDecoratorBase(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3) {
        this.mViewAdapter = iOverScrollDecoratorAdapter;
        this.mBounceBackState = new BounceBackState(f);
        this.mOverScrollingState = new OverScrollingState(f2, f3);
        IdleState idleState = new IdleState();
        this.mIdleState = idleState;
        this.mCurrentState = idleState;
        attach();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.mCurrentState.handleMoveTouchEvent(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.mCurrentState.handleUpOrCancelTouchEvent(motionEvent);
    }

    public void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener) {
        if (iOverScrollStateListener == null) {
            iOverScrollStateListener = new ListenerStubs.OverScrollStateListenerStub();
        }
        this.mStateListener = iOverScrollStateListener;
    }

    public void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener) {
        if (iOverScrollUpdateListener == null) {
            iOverScrollUpdateListener = new ListenerStubs.OverScrollUpdateListenerStub();
        }
        this.mUpdateListener = iOverScrollUpdateListener;
    }

    public int getCurrentState() {
        return this.mCurrentState.getStateId();
    }

    public View getView() {
        return this.mViewAdapter.getView();
    }

    /* access modifiers changed from: protected */
    public void issueStateTransition(IDecoratorState iDecoratorState) {
        IDecoratorState iDecoratorState2 = this.mCurrentState;
        this.mCurrentState = iDecoratorState;
        iDecoratorState.handleEntryTransition(iDecoratorState2);
    }

    /* access modifiers changed from: protected */
    public void attach() {
        getView().setOnTouchListener(this);
        getView().setOverScrollMode(2);
    }

    public void detach() {
        if (this.mCurrentState != this.mIdleState) {
            Log.w(TAG, "Decorator detached while over-scroll is in effect. You might want to add a precondition of that getCurrentState()==STATE_IDLE, first.");
        }
        getView().setOnTouchListener((View.OnTouchListener) null);
        getView().setOverScrollMode(0);
    }
}
